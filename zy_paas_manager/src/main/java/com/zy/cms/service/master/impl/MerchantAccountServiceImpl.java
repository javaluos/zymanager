package com.zy.cms.service.master.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.zy.cms.common.CacheService;
import com.zy.cms.common.Constant;
import com.zy.cms.common.RedisConstant;
import com.zy.cms.common.RedisOperator;
import com.zy.cms.common.SequeueHelper;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.enums.AuthFlagEnum;
import com.zy.cms.mapper.master.CmppAccountMapper;
import com.zy.cms.mapper.master.VoiceAccountBusinessInfoMapper;
import com.zy.cms.mapper.master.VoiceMerchantAccountBalanceMapper;
import com.zy.cms.mapper.master.VoiceMerchantAccountMapper;
import com.zy.cms.mapper.master.VoiceMerchantRechargeMapper;
import com.zy.cms.service.master.AppManageService;
import com.zy.cms.service.master.MerchantAccountService;
import com.zy.cms.service.master.VoiceMerchantAttrService;
import com.zy.cms.util.DateUtil;
import com.zy.cms.util.IsNumericOrEmail;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.Sequence;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.AppInfo;
import com.zy.cms.vo.CmppAccount;
import com.zy.cms.vo.MerchantAccount;
import com.zy.cms.vo.MerchantAccountAuth;
import com.zy.cms.vo.VoiceAccountBusinessInfo;
import com.zy.cms.vo.VoiceMerchantAccountBalance;
import com.zy.cms.vo.VoiceMerchantAttr;
import com.zy.cms.vo.VoiceMerchantRecharge;
import com.zy.cms.vo.query.AccountBalanceQuery;
import com.zy.cms.vo.query.AccountQuery;

@Service("merchantAccountService")
@Transactional
public class MerchantAccountServiceImpl implements MerchantAccountService {

	private static final ZyLogger logger = ZyLogger.getLogger(MerchantAccountServiceImpl.class);

	@Autowired
	private VoiceMerchantAccountMapper voiceMerchantAccountMapper;

	@Resource
	private CacheService cacheService;

	@Resource
	private VoiceMerchantRechargeMapper voiceMerchantRechargeMapper;

	@Autowired
	private VoiceMerchantAccountBalanceMapper voiceMerchantAccountBalanceMapper;

	@Autowired
	private VoiceAccountBusinessInfoMapper voiceAccountBusinessInfoMapper;

	@Autowired
	private RedisOperator redisOperator;

	@Autowired
	private AppManageService appManageService;

	@Resource
	private VoiceMerchantAttrService voiceMerchantAttrService;

	@Resource
	private CmppAccountMapper cmppAccountMapper;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public MerchantAccount getMerchantAccount(String apiAccount) {

		MerchantAccount rs = voiceMerchantAccountMapper.getMerchantAccount(apiAccount);
		return rs;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public MerchantAccount getMerchantAccountByPhone(String merchantPhone) {

		MerchantAccount rs = voiceMerchantAccountMapper.getMerchantAccountByPhone(merchantPhone);
		return rs;
	}

	@Override
	public List<MerchantAccount> queryMerchantAccounts(AccountQuery query) {

		return voiceMerchantAccountMapper.getMerchantAccounts(query);
	}

	@Override
	public int queryMerchantAccountCount(AccountQuery query) {

		return voiceMerchantAccountMapper.getMerchantAccountCount(query);
	}

	@Override
	public int queryMerchantAcctBalanceCount(AccountBalanceQuery query) {

		return voiceMerchantAccountMapper.queryMerchantAcctBalanceCount(query);
	}

	@Override
	public List<MerchantAccount> queryMerchantAcctBalances(AccountBalanceQuery query) throws SQLException {

		return voiceMerchantAccountMapper.queryMerchantAcctBalances(query);
	}

	@Override
	public boolean updateAccountInfo(MerchantAccountAuth accAuth, MerchantAccount mAccount, String flag) {
		String businessName = "";
		String plName = accAuth.getPlName();
		String cyName = accAuth.getCyName();
		if (StringUtils.isNotBlank(plName)) {
			businessName = plName;
		} else {
			businessName = cyName;
		}
		mAccount.setAuthFlag(AuthFlagEnum.AUTHED.getType());
		mAccount.setBusinessName(businessName);
		mAccount.setUpdateTime(new Date());
		return voiceMerchantAccountMapper.updateByPrimaryKeySelective(mAccount) > 0;
	}

	@Override
	public Map<String, MerchantAccount> queryMerchantAccountListByApis(String[] apis) {

		Map<String, MerchantAccount> rsMap = new HashMap<String, MerchantAccount>();
		List<MerchantAccount> accountList = voiceMerchantAccountMapper.queryMerchantAccountListByApis(apis);
		if (accountList != null && accountList.size() > 0) {
			for (MerchantAccount ma : accountList) {
				rsMap.put(ma.getApiAccount(), ma);
			}
		}
		return rsMap;
	}

	@Override
	public List<MerchantAccount> queryAccountlistLikeName(String businessName) {

		return voiceMerchantAccountMapper.queryAccountlistLikeName(businessName);
	}

	@Override
	public List<String> querylistByNameOrPhone(String name, String phone) {
		return voiceMerchantAccountMapper.querylistByNameOrPhone(name, phone);
	}

	@Override
	public MerchantAccount getInfoByMerchantAccount(String merchantAccount) {
		return voiceMerchantAccountMapper.getInfoByMerchantAccount(merchantAccount);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean saveRegistMerchantAccount(VoiceMerchantAttr voiceMerchantAttr, MerchantAccount merchantAccount,
			CmppAccount cmppAccount) throws Exception {
		boolean flag = false;
		try {
			short isLocked = 0;// 0表示正常 1锁定

			// 1.赠送金额从数据库读取（单位是分）
			Long balance = 500L;// BALANCE_CENT
			if (!StringUtil.isEmpty(cacheService.getConfig("BALANCE_CENT"))) {
				balance = Long.parseLong(cacheService.getConfig("BALANCE_CENT"));
			}
			Date createTime = new Date();

			// 2.保存客户信息
			Map va = new HashMap();
			va.put("API_ACCOUNT", merchantAccount.getApiAccount());

			String merchantaccount = "";
			if (StringUtils.isEmpty(merchantAccount.getMerchantAccount())) {
				if (!StringUtil.isEmpty(merchantAccount.getMerchantEmail())
						&& merchantAccount.getMerchantEmail().split("@").length > 1) {
					merchantaccount = merchantAccount.getMerchantEmail().split("@")[0]; // 获取邮箱的前部分
				} else {
					merchantaccount = merchantAccount.getMerchantPhone();
				}
			} else {
				merchantaccount = merchantAccount.getMerchantAccount();
			}

			va.put("MERCHANT_EMAIL", merchantAccount.getMerchantEmail());
			va.put("MERCHANT_ACCOUNT", merchantaccount);
			va.put("MERCHANT_PHONE", merchantAccount.getMerchantPhone());
			va.put("MERCHANT_PWD", merchantAccount.getMerchantPwd());
			va.put("BUSINESS_NAME", merchantAccount.getBusinessName());
			va.put("API_KEY", merchantAccount.getApikey());
			va.put("IS_LOCKED", isLocked);
			va.put("AUTH_FLAG", voiceMerchantAttr.getAuthFlag());
			va.put("CREATE_TIME", createTime);
			va.put("MERCHANT_TYPE", 2);
			voiceMerchantAccountMapper.insert(va);

			// 3.客户产品套餐
			String orderNo = Sequence.getOrdersId(merchantAccount.getMerchantAccount(), "", "99");
			VoiceMerchantRecharge voiceMerchantRecharge = new VoiceMerchantRecharge();
			// voiceMerchantRecharge.setMerchantAccount(merchantAccount);
			voiceMerchantRecharge.setApiAccount(merchantAccount.getApiAccount());
			voiceMerchantRecharge.setChangeBalance(balance);// 充值前的当前金额（单位为分）
			voiceMerchantRecharge.setBalance(0);// 充值前的当前金额（单位为分）
			voiceMerchantRecharge.setRechargeProductId(-5);// 充值的产品编号,与短信套餐表sms_combo中的ID对应,-5:系统加钱
			voiceMerchantRecharge.setRechargeType((short) 2);// 充值类型：0:套餐充值
																// 1:活动充值
																// ,2注册赠送,3系统加钱
			voiceMerchantRecharge.setPayType((short) 3);// 支付类型:9000:支付宝
														// 1:银联2:礼品卡,3平台赠送,8000:账户加钱
			voiceMerchantRecharge.setStatus((short) 1);// 充值的状态：0：表示提交了充值请求,充值中1：表示充值成功-1：表示充值失败
			// voiceMerchantRecharge.setReason(reason);
			voiceMerchantRecharge.setOrderNo(orderNo);
			// 一个月内有效
			voiceMerchantRecharge.setBalanceExpireTime(DateUtil.addMonths(createTime, 1));
			// voiceMerchantRecharge.setUpdateTime(updateTime);
			voiceMerchantRecharge.setCreateTime(createTime);// createTime
			voiceMerchantRechargeMapper.insert(voiceMerchantRecharge);

			// 4.客户余额保存
			VoiceMerchantAccountBalance voiceMerchantAccountBalance = new VoiceMerchantAccountBalance();
			// voiceMerchantAccountBalance.setMerchantAccount(merchantAccount);
			voiceMerchantAccountBalance.setApiAccount(merchantAccount.getApiAccount());
			voiceMerchantAccountBalance.setBalance(balance);
			voiceMerchantAccountBalance.setIsRecharge(1);// 是否充值0有，1：无
			// voiceMerchantAccountBalance.setUpdateTime(updateTime);
			voiceMerchantAccountBalance.setCreateTime(createTime);
			voiceMerchantAccountBalanceMapper.insert(voiceMerchantAccountBalance);

			// 5.创建系统默认应用
			AppInfo appInfo = new AppInfo();
			appInfo.setApiAccount(merchantAccount.getApiAccount());
			appInfo.setAppId(merchantAccount.getAppId());
			appInfo.setAppName("默认应用");
			appInfo.setAppToken(SequeueHelper.getCharAndNumr(34));
			appInfo.setStatus("2");
			appInfo.setReason((short) 0);
			appInfo.setBalance(0);
			appInfo.setBusinessIds("1,2,3,4,5,6,7,8,9,10,11");
			appManageService.saveAppInfo(appInfo);

			// 6.客户计费规则
			List<Map> list = cacheService.getVoiceBusness();
			List<VoiceAccountBusinessInfo> listVoiceAccountBusinessInfo = null;
			if (null != list && list.size() > 0) {
				listVoiceAccountBusinessInfo = new ArrayList<VoiceAccountBusinessInfo>();
				for (Map map : list) {
					VoiceAccountBusinessInfo voiceAccountBusinessInfo = new VoiceAccountBusinessInfo();
					// voiceAccountBusinessInfo.setMerchantAccount(merchantAccount);
					voiceAccountBusinessInfo.setApiAccount(merchantAccount.getApiAccount());
					voiceAccountBusinessInfo.setBusinessId(map.get("BUSINESS_ID").toString());// businessId
					voiceAccountBusinessInfo.setFeeRule(Integer.parseInt(map.get("FEE_RULE") + ""));// 计费规则0:6+6,1:30,2:60+60，默认0
					voiceAccountBusinessInfo.setFeerate(Integer.parseInt(map.get("FEERATE") + ""));// 费率（100=1F）,
					voiceAccountBusinessInfo.setUpdateTime(createTime);
					voiceAccountBusinessInfo.setCreateTime(createTime);
					listVoiceAccountBusinessInfo.add(voiceAccountBusinessInfo);
				}
				if (listVoiceAccountBusinessInfo != null && listVoiceAccountBusinessInfo.size() > 0) {
					Map map = new HashMap();
					map.put("list", listVoiceAccountBusinessInfo);
					voiceAccountBusinessInfoMapper.batchInsert(map);
				}
			}

			// 7.保存客户属性
			MerchantAccount account = getMerchantAccount(voiceMerchantAttr.getApiAccount());
			account.setBusinessName(merchantAccount.getBusinessName());
			account.setAuthFlag(voiceMerchantAttr.getAuthFlag());
			List<VoiceMerchantAttr> voiceMerchantAttrs = voiceMerchantAttrService
					.getVoiceMerchantAttrs(voiceMerchantAttr);
			voiceMerchantAttrService.saveVoiceMerchantAttrs(voiceMerchantAttrs, account, null);

			// 8.保存CMPP账号信息
			if (cmppAccount != null) {
				if (StringUtils.isNotEmpty(cmppAccount.getApiAccount())
						&& StringUtils.isNotEmpty(cmppAccount.getClientId())
						&& StringUtils.isNotEmpty(cmppAccount.getPwd())) {

					cmppAccount.setApiKey(merchantAccount.getApikey());
					cmppAccount.setAppId(appInfo.getAppId());
					cmppAccountMapper.insertCmppAccount(cmppAccount);
				}
			}

			// 9.将客户信息写入到缓存中
			String accountKey = String.format(RedisConstant.ACCOUNTKEY_PREFIX, merchantAccount.getApiAccount());
			redisOperator.set(accountKey, JsonUtil.objectToJson(va));
			String balanceKey = String.format(RedisConstant.BALANCEKEY_PREFIX, merchantAccount.getApiAccount());
			redisOperator.set(balanceKey, voiceMerchantAccountBalance.getBalance() + "");// JsonUtil.objectToJson(voiceMerchantAccountBalance)

			flag = true;

			logger.info("【注册保存用户信息】" + JsonUtil.wrapjson(Constant.SUCCESS + "", Constant.SUCCESS_DESC));
			logger.info("【注册保存用户账户信息】" + JsonUtil.wrapjson(Constant.SUCCESS + "", va));
			logger.info("【注册保存用户充值信息】" + JsonUtil.wrapjson(Constant.SUCCESS + "", voiceMerchantRecharge));
			logger.info("【注册保存用户余额信息】" + JsonUtil.wrapjson(Constant.SUCCESS + "", voiceMerchantAccountBalance));
			logger.info("【注册保存用户业务信息】" + JsonUtil.wrapjson(Constant.SUCCESS + "", listVoiceAccountBusinessInfo));
		} catch (Exception e) {
			logger.error("【注册保存用户信息失败】" + e.getMessage(), e);
			flag = false;
		}
		return flag;
	}

	@Override
	public MerchantAccount getInfoByMerchantPhone(String merchantPhone) {
		return voiceMerchantAccountMapper.getInfoByMerchantPhone(merchantPhone);
	}

	@Override
	public MerchantAccount getInfoByMerchantEmail(String email) {
		return voiceMerchantAccountMapper.getInfoByMerchantEmail(email);
	}

}
