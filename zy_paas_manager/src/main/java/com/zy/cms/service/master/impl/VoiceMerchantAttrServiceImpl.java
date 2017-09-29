package com.zy.cms.service.master.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zy.cms.common.RedisConstant;
import com.zy.cms.common.RedisOperator;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.enums.MerchantAttrEnum;
import com.zy.cms.mapper.master.CmppAccountMapper;
import com.zy.cms.mapper.master.MerchantAccountAuthMapper;
import com.zy.cms.mapper.master.VoiceMerchantAccountMapper;
import com.zy.cms.mapper.master.VoiceMerchantAttrMapper;
import com.zy.cms.service.master.VoiceMerchantAttrService;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.vo.CmppAccount;
import com.zy.cms.vo.MerchantAccount;
import com.zy.cms.vo.VoiceMerchantAttr;

/**
 * 账号属性
 * 
 * @author JasonXu
 *
 */
@Service("voiceMerchantAttrService")
public class VoiceMerchantAttrServiceImpl implements VoiceMerchantAttrService {

	private static final ZyLogger logger = ZyLogger.getLogger(VoiceMerchantAttrServiceImpl.class);

	@Autowired
	private VoiceMerchantAttrMapper mapper;

	@Autowired
	private VoiceMerchantAccountMapper voiceMerchantAccountMapper;

	@Autowired
	MerchantAccountAuthMapper merchantAccountAuthMapper;

	@Autowired
	private RedisOperator redis;

	@Resource
	private CmppAccountMapper cmppAccountMapper;

	@Override
	public int deleteByPrimaryKey(Long id) throws Exception {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(VoiceMerchantAttr record) throws Exception {
		return mapper.insert(record);
	}

	@Override
	public int batchInsert(List<VoiceMerchantAttr> voiceMerchantAttrs) throws Exception {
		return mapper.batchInsert(voiceMerchantAttrs);
	}

	@Override
	public int insertSelective(VoiceMerchantAttr record) throws Exception {
		return mapper.insertSelective(record);
	}

	@Override
	public VoiceMerchantAttr selectByPrimaryKey(Long id) throws Exception {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(VoiceMerchantAttr record) throws Exception {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(VoiceMerchantAttr record) throws Exception {
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<VoiceMerchantAttr> selectByQuery(Map map) throws Exception {
		return mapper.selectByQuery(map);
	}

	/**
	 * 批量删除账号属性
	 */
	@Override
	public void batchDelete(String apiAccount) throws Exception {
		mapper.batchDelete(apiAccount);
	}

	/**
	 * 将数据组合成list格式
	 */
	@Override
	public List<VoiceMerchantAttr> getVoiceMerchantAttrs(VoiceMerchantAttr voiceMerchantAttr) throws Exception {
		List<VoiceMerchantAttr> voiceMerchantAttrs = new ArrayList<VoiceMerchantAttr>();

		VoiceMerchantAttr voiceMerchantAttr_IsBlack = new VoiceMerchantAttr();
		voiceMerchantAttr_IsBlack.setApiAccount(voiceMerchantAttr.getApiAccount());
		voiceMerchantAttr_IsBlack.setBusinessId("0");
		voiceMerchantAttr_IsBlack.setAttrName(MerchantAttrEnum.getName(4));
		voiceMerchantAttr_IsBlack.setAttrValue(voiceMerchantAttr.getIsBlackKey());

		VoiceMerchantAttr voiceMerchantAttr_IsWhite = new VoiceMerchantAttr();
		voiceMerchantAttr_IsWhite.setApiAccount(voiceMerchantAttr.getApiAccount());
		voiceMerchantAttr_IsWhite.setBusinessId("0");
		voiceMerchantAttr_IsWhite.setAttrName(MerchantAttrEnum.getName(5));
		voiceMerchantAttr_IsWhite.setAttrValue(voiceMerchantAttr.getIsWhiteKey());

		VoiceMerchantAttr voiceMerchantAttr8_temp = new VoiceMerchantAttr();
		voiceMerchantAttr8_temp.setApiAccount(voiceMerchantAttr.getApiAccount());
		voiceMerchantAttr8_temp.setBusinessId("8");
		voiceMerchantAttr8_temp.setAttrName(MerchantAttrEnum.getName(1));
		voiceMerchantAttr8_temp.setAttrValue(voiceMerchantAttr.getTemplateAuthFalg8());

		VoiceMerchantAttr voiceMerchantAttr8_sign = new VoiceMerchantAttr();
		voiceMerchantAttr8_sign.setApiAccount(voiceMerchantAttr.getApiAccount());
		voiceMerchantAttr8_sign.setBusinessId("8");
		voiceMerchantAttr8_sign.setAttrName(MerchantAttrEnum.getName(2));
		voiceMerchantAttr8_sign.setAttrValue(voiceMerchantAttr.getSignerAuthFlag8());

		VoiceMerchantAttr voiceMerchantAttr9_temp = new VoiceMerchantAttr();
		voiceMerchantAttr9_temp.setApiAccount(voiceMerchantAttr.getApiAccount());
		voiceMerchantAttr9_temp.setBusinessId("9");
		voiceMerchantAttr9_temp.setAttrName(MerchantAttrEnum.getName(1));
		voiceMerchantAttr9_temp.setAttrValue(voiceMerchantAttr.getTemplateAuthFalg9());

		VoiceMerchantAttr voiceMerchantAttr9_sign = new VoiceMerchantAttr();
		voiceMerchantAttr9_sign.setApiAccount(voiceMerchantAttr.getApiAccount());
		voiceMerchantAttr9_sign.setBusinessId("9");
		voiceMerchantAttr9_sign.setAttrName(MerchantAttrEnum.getName(2));
		voiceMerchantAttr9_sign.setAttrValue(voiceMerchantAttr.getSignerAuthFlag9());

		VoiceMerchantAttr voiceMerchantAttr11_temp = new VoiceMerchantAttr();
		voiceMerchantAttr11_temp.setApiAccount(voiceMerchantAttr.getApiAccount());
		voiceMerchantAttr11_temp.setBusinessId("11");
		voiceMerchantAttr11_temp.setAttrName(MerchantAttrEnum.getName(1));
		voiceMerchantAttr11_temp.setAttrValue(voiceMerchantAttr.getTemplateAuthFalg11());

		VoiceMerchantAttr voiceMerchantAttr11_sign = new VoiceMerchantAttr();
		voiceMerchantAttr11_sign.setApiAccount(voiceMerchantAttr.getApiAccount());
		voiceMerchantAttr11_sign.setBusinessId("11");
		voiceMerchantAttr11_sign.setAttrName(MerchantAttrEnum.getName(2));
		voiceMerchantAttr11_sign.setAttrValue(voiceMerchantAttr.getSignerAuthFlag11());

		VoiceMerchantAttr voiceMerchantAttr4 = new VoiceMerchantAttr();
		voiceMerchantAttr4.setApiAccount(voiceMerchantAttr.getApiAccount());
		voiceMerchantAttr4.setBusinessId("4");
		voiceMerchantAttr4.setAttrName(MerchantAttrEnum.getName(3));
		voiceMerchantAttr4.setAttrValue(voiceMerchantAttr.getVoicefileAuthFlag4());

		VoiceMerchantAttr voiceMerchantAttr5 = new VoiceMerchantAttr();
		voiceMerchantAttr5.setApiAccount(voiceMerchantAttr.getApiAccount());
		voiceMerchantAttr5.setBusinessId("5");
		voiceMerchantAttr5.setAttrName(MerchantAttrEnum.getName(3));
		voiceMerchantAttr5.setAttrValue(voiceMerchantAttr.getVoicefileAuthFlag5());

		if (StringUtils.isNotBlank(voiceMerchantAttr.getSmsChannelPolicy())) {
			VoiceMerchantAttr voiceMerchantAttr6 = new VoiceMerchantAttr();
			voiceMerchantAttr6.setApiAccount(voiceMerchantAttr.getApiAccount());
			voiceMerchantAttr6.setBusinessId("0");
			voiceMerchantAttr6.setAttrName(MerchantAttrEnum.CHANNELPOLICY.getName());
			voiceMerchantAttr6.setAttrValue(voiceMerchantAttr.getSmsChannelPolicy());
			voiceMerchantAttrs.add(voiceMerchantAttr6);
		}

		VoiceMerchantAttr voiceMerchantAttr7 = new VoiceMerchantAttr();
		voiceMerchantAttr7.setApiAccount(voiceMerchantAttr.getApiAccount());
		voiceMerchantAttr7.setBusinessId("0");
		voiceMerchantAttr7.setAttrName(MerchantAttrEnum.SINGLEMOBILESENDFLAG.getName());
		voiceMerchantAttr7.setAttrValue(voiceMerchantAttr.getDowmMobileFlag());
		voiceMerchantAttrs.add(voiceMerchantAttr7);

		VoiceMerchantAttr voiceMerchantAttr8 = new VoiceMerchantAttr();
		voiceMerchantAttr8.setApiAccount(voiceMerchantAttr.getApiAccount());
		voiceMerchantAttr8.setBusinessId("0");
		voiceMerchantAttr8.setAttrName(MerchantAttrEnum.SINGLEMOBILESENDINSECS.getName());
		voiceMerchantAttr8.setAttrValue(voiceMerchantAttr.getSecondItem() + "_" + voiceMerchantAttr.getSecondNum());
		voiceMerchantAttrs.add(voiceMerchantAttr8);

		VoiceMerchantAttr voiceMerchantAttr9 = new VoiceMerchantAttr();
		voiceMerchantAttr9.setApiAccount(voiceMerchantAttr.getApiAccount());
		voiceMerchantAttr9.setBusinessId("0");
		voiceMerchantAttr9.setAttrName(MerchantAttrEnum.SINGLEMOBILESENDINMINS.getName());
		voiceMerchantAttr9.setAttrValue(voiceMerchantAttr.getMinuteItem() + "_" + voiceMerchantAttr.getMinuteNum());
		voiceMerchantAttrs.add(voiceMerchantAttr9);

		VoiceMerchantAttr voiceMerchantAttr10 = new VoiceMerchantAttr();
		voiceMerchantAttr10.setApiAccount(voiceMerchantAttr.getApiAccount());
		voiceMerchantAttr10.setBusinessId("0");
		voiceMerchantAttr10.setAttrName(MerchantAttrEnum.SINGLEMOBILESENDINHOURS.getName());
		voiceMerchantAttr10.setAttrValue(voiceMerchantAttr.getHourItem() + "_" + voiceMerchantAttr.getHourNum());
		voiceMerchantAttrs.add(voiceMerchantAttr10);

		VoiceMerchantAttr voiceMerchantAttr11 = new VoiceMerchantAttr();
		voiceMerchantAttr11.setApiAccount(voiceMerchantAttr.getApiAccount());
		voiceMerchantAttr11.setBusinessId("0");
		voiceMerchantAttr11.setAttrName(MerchantAttrEnum.SMSFILTERPOLICY.getName());
		voiceMerchantAttr11.setAttrValue(voiceMerchantAttr.getSmsFilterPolicy());
		voiceMerchantAttrs.add(voiceMerchantAttr11);

		VoiceMerchantAttr voiceMerchantAttr12 = new VoiceMerchantAttr();
		voiceMerchantAttr12.setApiAccount(voiceMerchantAttr.getApiAccount());
		voiceMerchantAttr12.setBusinessId("0");
		voiceMerchantAttr12.setAttrName(MerchantAttrEnum.SMSNOFILTERPOLICY.getName());
		voiceMerchantAttr12.setAttrValue(voiceMerchantAttr.getSmsNoFilterPolicy());
		voiceMerchantAttrs.add(voiceMerchantAttr12);

		VoiceMerchantAttr voiceMerchantAttr13 = new VoiceMerchantAttr();
		voiceMerchantAttr13.setApiAccount(voiceMerchantAttr.getApiAccount());
		voiceMerchantAttr13.setBusinessId("0");
		voiceMerchantAttr13.setAttrName(MerchantAttrEnum.ISBLACKAUDITFLAG.getName());
		voiceMerchantAttr13.setAttrValue(voiceMerchantAttr.getIsBlackAuditFlag());
		voiceMerchantAttrs.add(voiceMerchantAttr13);

		VoiceMerchantAttr voiceMerchantAttr14 = new VoiceMerchantAttr();
		voiceMerchantAttr14.setApiAccount(voiceMerchantAttr.getApiAccount());
		voiceMerchantAttr14.setBusinessId("0");
		voiceMerchantAttr14.setAttrName(MerchantAttrEnum.CMPP_ACCESS_CODE.getName());
		voiceMerchantAttr14.setAttrValue(voiceMerchantAttr.getCmppAccessCode());
		voiceMerchantAttrs.add(voiceMerchantAttr14);

		VoiceMerchantAttr voiceMerchantAttr15 = new VoiceMerchantAttr();
		voiceMerchantAttr15.setApiAccount(voiceMerchantAttr.getApiAccount());
		voiceMerchantAttr15.setBusinessId("0");
		voiceMerchantAttr15.setAttrName(MerchantAttrEnum.YD_EXTNUMBER.getName());
		voiceMerchantAttr15.setAttrValue(voiceMerchantAttr.getYdExtNumber());
		voiceMerchantAttrs.add(voiceMerchantAttr15);

		VoiceMerchantAttr voiceMerchantAttr16 = new VoiceMerchantAttr();
		voiceMerchantAttr16.setApiAccount(voiceMerchantAttr.getApiAccount());
		voiceMerchantAttr16.setBusinessId("0");
		voiceMerchantAttr16.setAttrName(MerchantAttrEnum.LT_EXTNUMBER.getName());
		voiceMerchantAttr16.setAttrValue(voiceMerchantAttr.getLtExtNumber());
		voiceMerchantAttrs.add(voiceMerchantAttr16);

		VoiceMerchantAttr voiceMerchantAttr17 = new VoiceMerchantAttr();
		voiceMerchantAttr17.setApiAccount(voiceMerchantAttr.getApiAccount());
		voiceMerchantAttr17.setBusinessId("0");
		voiceMerchantAttr17.setAttrName(MerchantAttrEnum.DX_EXTNUMBER.getName());
		voiceMerchantAttr17.setAttrValue(voiceMerchantAttr.getDxExtNumber());
		voiceMerchantAttrs.add(voiceMerchantAttr17);

		VoiceMerchantAttr voiceMerchantAttr18 = new VoiceMerchantAttr();
		voiceMerchantAttr18.setApiAccount(voiceMerchantAttr.getApiAccount());
		voiceMerchantAttr18.setBusinessId("0");
		voiceMerchantAttr18.setAttrName(MerchantAttrEnum.ACC_SEND_PER_SECOND.getName());
		voiceMerchantAttr18.setAttrValue(voiceMerchantAttr.getAccSendPerSecond().toString());
		voiceMerchantAttrs.add(voiceMerchantAttr18);

		voiceMerchantAttrs.add(voiceMerchantAttr_IsBlack);
		voiceMerchantAttrs.add(voiceMerchantAttr_IsWhite);
		voiceMerchantAttrs.add(voiceMerchantAttr8_temp);
		voiceMerchantAttrs.add(voiceMerchantAttr8_sign);
		voiceMerchantAttrs.add(voiceMerchantAttr9_temp);
		voiceMerchantAttrs.add(voiceMerchantAttr9_sign);
		voiceMerchantAttrs.add(voiceMerchantAttr11_temp);
		voiceMerchantAttrs.add(voiceMerchantAttr11_sign);
		voiceMerchantAttrs.add(voiceMerchantAttr4);
		voiceMerchantAttrs.add(voiceMerchantAttr5);
		return voiceMerchantAttrs;
	}

	/**
	 * 保存账号属性和更新账户信息
	 * 
	 * @param voiceMerchantAttrs
	 * @param merchantAccount
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@Transactional
	public boolean saveVoiceMerchantAttrs(List<VoiceMerchantAttr> voiceMerchantAttrs, MerchantAccount merchantAccount,
			CmppAccount cmppAccount) throws Exception {
		String apiAccount = merchantAccount.getApiAccount();
		boolean flag = false;

		if ("13bbf54a6850c393fb8d1b2b3bba997b".equals(merchantAccount.getMerchantPwd())) {
			merchantAccount.setMerchantPwd(null);
		}
		int updataFlag = voiceMerchantAccountMapper.updateByPrimaryKeySelective(merchantAccount);
		batchDelete(merchantAccount.getApiAccount());
		int insertFlag = batchInsert(voiceMerchantAttrs);
		if (insertFlag > 0 && updataFlag > 0) {
			flag = true;
		}

		if (merchantAccount.getAuthFlag() == -1) {
			// 如果从已认证改为未认证 修改认证信息表的状态为0 0是初始状态
			merchantAccountAuthMapper.updateStatusByApiAccount(0, apiAccount);
		} else {
			// 如果从未认证改为已认证 修改认证信息表的状态为1 1是认证状态
			merchantAccountAuthMapper.updateStatusByApiAccount(1, apiAccount);
		}

		// 将帐号信息保存到redis中
		String accountKey = String.format(RedisConstant.ACCOUNTKEY_PREFIX, apiAccount);
		// redis.set(accountKey, JsonUtil.objectToJson(merchantAccount));
		redis.del(accountKey);

		/** 将属性配置存入到redis中 **/
		Map attrMap = new HashMap<String, String>();
		String redisKey = String.format(RedisConstant.ACCOUNTKEY_ATTR_PREFIX, apiAccount);
		String attrJson = "";
		if (voiceMerchantAttrs != null && voiceMerchantAttrs.size() > 0) {
			for (VoiceMerchantAttr voiceMerchantAttr : voiceMerchantAttrs) {
				String _key = voiceMerchantAttr.getAttrName() + "_" + voiceMerchantAttr.getBusinessId();
				attrMap.put(_key, voiceMerchantAttr.getAttrValue());
			}
			attrJson = JsonUtil.objectToJson(attrMap);
			redis.set(redisKey, attrJson);
		}

		if (cmppAccount != null) {
			if (StringUtils.isNotEmpty(cmppAccount.getApiAccount()) && StringUtils.isNotEmpty(cmppAccount.getClientId())
					&& StringUtils.isNotEmpty(cmppAccount.getPwd())) {
				int updateFlag = cmppAccountMapper.updateCmppAccount(cmppAccount);
				if (updateFlag < 1) {
					cmppAccountMapper.insertCmppAccount(cmppAccount);
				}
			}
		}

		return flag;
	}

	@Override
	public VoiceMerchantAttr getVoiceMerchantAttr(List<VoiceMerchantAttr> voiceMerchantAttrs,
			VoiceMerchantAttr voiceMerchantAttr) throws Exception {
		if (voiceMerchantAttrs != null && voiceMerchantAttrs.size() > 0) {
			for (VoiceMerchantAttr vmt : voiceMerchantAttrs) {
				switch (vmt.getAttrName()) {
				case "TEMPLATE_AUTH_FLAG":
					switch (vmt.getBusinessId()) {
					case "8":
						voiceMerchantAttr.setTemplateAuthFalg8(vmt.getAttrValue());
						break;
					case "9":
						voiceMerchantAttr.setTemplateAuthFalg9(vmt.getAttrValue());
						break;
					case "11":
						voiceMerchantAttr.setTemplateAuthFalg11(vmt.getAttrValue());
						break;
					default:
						break;
					}
					break;
				case "SIGNER_AUTH_FLAG":
					switch (vmt.getBusinessId()) {
					case "8":
						voiceMerchantAttr.setSignerAuthFlag8(vmt.getAttrValue());
						break;
					case "9":
						voiceMerchantAttr.setSignerAuthFlag9(vmt.getAttrValue());
						break;
					case "11":
						voiceMerchantAttr.setSignerAuthFlag11(vmt.getAttrValue());
						break;
					default:
						break;
					}
					break;
				case "VOICEFILE_AUTH_FLAG":
					switch (vmt.getBusinessId()) {
					case "4":
						voiceMerchantAttr.setVoicefileAuthFlag4(vmt.getAttrValue());
						break;
					case "5":
						voiceMerchantAttr.setVoicefileAuthFlag5(vmt.getAttrValue());
						break;
					default:
						break;
					}
					break;
				case "IS_BLACK_KEY":
					voiceMerchantAttr.setIsBlackKey(vmt.getAttrValue());
					break;
				case "IS_WHITE_KEY":
					voiceMerchantAttr.setIsWhiteKey(vmt.getAttrValue());
					break;
				case "CHANNEL_POLICY":
					voiceMerchantAttr.setSmsChannelPolicy(vmt.getAttrValue());
					break;
				case "SINGLE_MOBILE_SEND_FLAG":
					voiceMerchantAttr.setDowmMobileFlag(vmt.getAttrValue());
					break;
				case "SINGLE_MOBILE_SEND_IN_SECS":
					String value = vmt.getAttrValue();
					String[] values = value.split("_");
					if (values.length > 0) {
						voiceMerchantAttr.setSecondItem(Integer.parseInt(values[0]));
						voiceMerchantAttr.setSecondNum(Integer.parseInt(values[1]));
					}
					break;
				case "SINGLE_MOBILE_SEND_IN_MINS":
					String value2 = vmt.getAttrValue();
					String[] values2 = value2.split("_");
					if (values2.length > 0) {
						voiceMerchantAttr.setMinuteItem(Integer.parseInt(values2[0]));
						voiceMerchantAttr.setMinuteNum(Integer.parseInt(values2[1]));
					}
					break;
				case "SINGLE_MOBILE_SEND_IN_HOURS":
					String value3 = vmt.getAttrValue();
					String[] values3 = value3.split("_");
					if (values3.length > 0) {
						voiceMerchantAttr.setHourItem(Integer.parseInt(values3[0]));
						voiceMerchantAttr.setHourNum(Integer.parseInt(values3[1]));
					}
					break;
				case "SMS_FILTER_POLICY":
					voiceMerchantAttr.setSmsFilterPolicy(vmt.getAttrValue());
					break;
				case "SMS_NO_FILTER_POLICY":
					voiceMerchantAttr.setSmsNoFilterPolicy(vmt.getAttrValue());
					break;
				case "IS_BLACK_AUDIT_FLAG":
					voiceMerchantAttr.setIsBlackAuditFlag(vmt.getAttrValue());
					break;
				case "CMPP_ACCESS_CODE":
					voiceMerchantAttr.setCmppAccessCode(vmt.getAttrValue());
					break;
				case "YD_EXTNUMBER":
					voiceMerchantAttr.setYdExtNumber(vmt.getAttrValue());
					break;
				case "LT_EXTNUMBER":
					voiceMerchantAttr.setLtExtNumber(vmt.getAttrValue());
					break;
				case "DX_EXTNUMBER":
					voiceMerchantAttr.setDxExtNumber(vmt.getAttrValue());
					break;
				case "ACC_SEND_PER_SECOND":
					if (StringUtils.isNotEmpty(vmt.getAttrValue())) {
						voiceMerchantAttr.setAccSendPerSecond(Integer.parseInt(vmt.getAttrValue()));
					}
					break;
				default:
					break;
				}
			}
		}
		return voiceMerchantAttr;
	}

}
