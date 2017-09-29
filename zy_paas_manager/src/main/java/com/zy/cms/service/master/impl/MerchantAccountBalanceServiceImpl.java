package com.zy.cms.service.master.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zy.cms.common.RedisConstant;
import com.zy.cms.common.RedisOperator;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.mapper.master.BalanceUpdateRecordMapper;
import com.zy.cms.mapper.master.VoiceMerchantAccountBalanceMapper;
import com.zy.cms.service.master.MerchantAccountBalanceService;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.BalanceUpdateRecord;
import com.zy.cms.vo.VoiceMerchantAccountBalance;
import com.zy.cms.vo.query.AccBalUpdateRecordQuery;

@Service("merchantAccountBalanceService")
@Transactional
public class MerchantAccountBalanceServiceImpl implements MerchantAccountBalanceService {

	private static final ZyLogger logger = ZyLogger.getLogger(MerchantAccountBalanceServiceImpl.class);

	@Autowired
	private VoiceMerchantAccountBalanceMapper voiceMerchantAccountBalanceMapper;

	@Autowired
	private BalanceUpdateRecordMapper balanceUpdateRecordMapper;

	@Autowired
	private RedisOperator redisOperator;

	@Override
	public VoiceMerchantAccountBalance getMerchantAccountBalance(String apiAccount) {

		Map param = new HashMap();
		param.put("API_ACCOUNT", apiAccount);
		return voiceMerchantAccountBalanceMapper.getVoiceMerchantAccountBalance(param);
	}

	@Override
	public Integer queryMchAcctBalUpdateCount(AccBalUpdateRecordQuery query) {
		return balanceUpdateRecordMapper.selectCountByQuery(query);
	}

	@Override
	public List<BalanceUpdateRecord> queryMchAcctBalUpdateRecords(AccBalUpdateRecordQuery query) {
		return balanceUpdateRecordMapper.selectByQuery(query);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean addAccountBalanceUpdateRecord(String apiAccount, String merchantAccount, String businessName,
			String updateFee, String operator, String comment) {
		boolean result = false;
		try {

			BalanceUpdateRecord balanceUpdateRecord = new BalanceUpdateRecord();
			balanceUpdateRecord.setApiAccount(apiAccount);
			balanceUpdateRecord.setMerchantAccount(merchantAccount);
			balanceUpdateRecord.setBusinessName(businessName);
			Date now = new Date();
			long updateFeeInt = (long)(Double.valueOf(updateFee) * 10000);
			balanceUpdateRecord.setUpdateFee(updateFeeInt);
			balanceUpdateRecord.setOpTime(now);
			balanceUpdateRecord.setOperator(operator);
			balanceUpdateRecord.setComment(comment);
			balanceUpdateRecord.setCreateTime(now);
			balanceUpdateRecord.setUpdateTime(now);

			// 同时修改账户余额
			Map<String, String> map = new HashMap<String, String>();
			map.put("API_ACCOUNT", apiAccount);
			VoiceMerchantAccountBalance accountBalance = voiceMerchantAccountBalanceMapper.getVoiceMerchantAccountBalance(map);
			result = voiceMerchantAccountBalanceMapper.updateBalance(apiAccount, updateFeeInt, now) > 0;
			if (result) {
				// 同步redis余额
				String balanceKey = String.format(RedisConstant.BALANCEKEY_PREFIX, apiAccount);
//				redisOperator.incrby(balanceKey, (long) updateFeeInt);
				if(null != accountBalance){
					long balance = accountBalance.getBalance() + updateFeeInt;
					redisOperator.set(balanceKey, balance + "");
				}
				// 添加余额修改记录
				balanceUpdateRecordMapper.insert(balanceUpdateRecord);
			}
		} catch (Exception e) {
			logger.error("【更改余额操作】异常error=" + e.getMessage());
		}

		return result;
	}

}
