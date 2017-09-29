package com.zy.cms.service.master.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zy.cms.common.RedisConstant;
import com.zy.cms.common.RedisOperator;
import com.zy.cms.mapper.master.VoiceAccountBusinessInfoMapper;
import com.zy.cms.service.master.VoiceAccountBusinessInfoService;
import com.zy.cms.util.ValueUtil;
import com.zy.cms.vo.VoiceAccountBusinessInfo;
import com.zy.cms.vo.VoiceUpload;
import com.zy.cms.vo.query.VoiceAccountBusinessInfoQuery;

@Service("voiceAccountBusinessInfoService")
public class VoiceAccountBusinessInfoServiceImpl implements VoiceAccountBusinessInfoService {

	@Autowired
	private RedisOperator redis;

	/**
	 * VoiceUpload数据库访问接口
	 */
	@Autowired
	private VoiceAccountBusinessInfoMapper voiceAccountBusinessInfoMapper;

	@Override
	public int insert(VoiceAccountBusinessInfo record) throws Exception {
		// TODO Auto-generated method stub
		return voiceAccountBusinessInfoMapper.insert(record);
	}

	@Override
	public int update(VoiceAccountBusinessInfo record) throws Exception {
		// TODO Auto-generated method stub
		return voiceAccountBusinessInfoMapper.update(record);
	}

	@Override
	public int batchInsert(Map record) throws Exception {
		// TODO Auto-generated method stub
		return voiceAccountBusinessInfoMapper.batchInsert(record);
	}

	@Override
	public List<VoiceAccountBusinessInfo> queryVoiceAccountBusinessInfoByEntity(
			VoiceAccountBusinessInfoQuery voiceAccountBusinessInfoQuery) throws Exception {
		// TODO Auto-generated method stub
		return voiceAccountBusinessInfoMapper.queryVoiceAccountBusinessInfoByEntity(voiceAccountBusinessInfoQuery);
	}

	@Override
	public int queryVoiceAccountBusinessInfoCountByEntity(VoiceAccountBusinessInfoQuery voiceAccountBusinessInfoQuery)
			throws Exception {
		// TODO Auto-generated method stub
		return voiceAccountBusinessInfoMapper.queryVoiceAccountBusinessInfoCountByEntity(voiceAccountBusinessInfoQuery);
	}

	@Override
	public VoiceAccountBusinessInfo findVoiceAccountBusinessInfo(
			VoiceAccountBusinessInfoQuery voiceAccountBusinessInfoQuery) throws Exception {
		// TODO Auto-generated method stub
		return voiceAccountBusinessInfoMapper.findVoiceAccountBusinessInfo(voiceAccountBusinessInfoQuery);
	}

	@SuppressWarnings("unused")
	private void setTableName(VoiceUpload voiceUpload) {
		String tableName = getTableSuffix(voiceUpload.getApiAccount());
		voiceUpload.setTableName(tableName);
	}

	/**
	 * 20 张表
	 * 
	 * @return 表后缀
	 */
	private String getTableSuffix(String apiAccount) {
		if (null == apiAccount || "".equals(apiAccount)) {
			return null;
		} else {
			int value = Math.abs(apiAccount.hashCode());
			value = (value % tableSheets == 0 ? tableSheets : (value % tableSheets));
			return "" + value;
		}
	}

	private static int tableSheets = 20;

	@Override
	public VoiceAccountBusinessInfo findVoiceBusinessInfo() throws Exception {
		return voiceAccountBusinessInfoMapper.findVoiceBusinessInfo();
	}

	/*
	 * @Override public VoiceAccountBusinessInfo
	 * queryVoiceAccountBusinessInfoByAccountID(Map accountId) throws Exception
	 * { // TODO Auto-generated method stub return
	 * voiceAccountBusinessInfoMapper.queryVoiceAccountBusinessInfoByAccountID(
	 * accountId); }
	 */

	@Override
	public List<VoiceAccountBusinessInfo> queryVoiceAccountBusinessInfoByAccount(String apiAccount) throws Exception {
		return voiceAccountBusinessInfoMapper.queryVoiceAccountBusinessInfoByAccount(apiAccount);
	}

	/**
	 * 批量更新应该所对应的资费
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@Override
	@Transactional
	public void batchUpdate(List<VoiceAccountBusinessInfo> voiceAccountBusinessInfos,
			VoiceAccountBusinessInfoQuery voiceAccountBusinessInfoQuery) throws Exception {
		for (VoiceAccountBusinessInfo vabi : voiceAccountBusinessInfos) {
			String redisKey = String.format(RedisConstant.ACCOUNTFEEKEY_PREFIX, vabi.getApiAccount());
			int count = 0;
			switch (vabi.getBusinessId()) {
			case "1":
				int callback = ValueUtil.calcMul(voiceAccountBusinessInfoQuery.getCallBack(), 10000,
						voiceAccountBusinessInfoQuery.getCallBackRule());// 回拨电话
				vabi.setFeerate(callback);
				vabi.setFeeRule(voiceAccountBusinessInfoQuery.getCallBackRule());
				vabi.setUpdateTime(new Date());
				count = update(vabi);

				if (count > 0) {
					redis.hdel(redisKey, vabi.getBusinessId());// 清除redis
				}
				break;
			case "2":
				int numberGuard = ValueUtil.calcMul(voiceAccountBusinessInfoQuery.getNumberGuard(), 10000,
						voiceAccountBusinessInfoQuery.getNumberGuardRule());// 号码卫士
				vabi.setFeerate(numberGuard);
				vabi.setFeeRule(voiceAccountBusinessInfoQuery.getNumberGuardRule());
				vabi.setUpdateTime(new Date());
				count = update(vabi);

				if (count > 0) {
					redis.hdel(redisKey, vabi.getBusinessId());// 清除redis
				}

				break;
			case "3":
				int directDialTelephone = ValueUtil.calcMul(voiceAccountBusinessInfoQuery.getDirectDialTelephone(),
						10000, voiceAccountBusinessInfoQuery.getDirectDialTelephoneRule());// 直拨电话
				vabi.setFeerate(directDialTelephone);
				vabi.setFeeRule(voiceAccountBusinessInfoQuery.getDirectDialTelephoneRule());
				vabi.setUpdateTime(new Date());
				count = update(vabi);

				if (count > 0) {
					redis.hdel(redisKey, vabi.getBusinessId());// 清除redis
				}

				break;
			case "4":
				int voiceNotification = ValueUtil.calcMul(voiceAccountBusinessInfoQuery.getVoiceNotification(), 10000,
						voiceAccountBusinessInfoQuery.getVoiceNotificationRule());// 语音通知
				vabi.setFeerate(voiceNotification);
				vabi.setFeeRule(voiceAccountBusinessInfoQuery.getVoiceNotificationRule());
				vabi.setUpdateTime(new Date());
				count = update(vabi);

				if (count > 0) {
					redis.hdel(redisKey, vabi.getBusinessId());// 清除redis
				}

				break;
			case "5":
				int voiceVerificationCode = ValueUtil.calcMul(voiceAccountBusinessInfoQuery.getVoiceVerificationCode(),
						10000, voiceAccountBusinessInfoQuery.getVoiceVerificationCodeRule());// 语音验证码
				vabi.setFeerate(voiceVerificationCode);
				vabi.setFeeRule(voiceAccountBusinessInfoQuery.getVoiceVerificationCodeRule());
				vabi.setUpdateTime(new Date());
				count = update(vabi);

				if (count > 0) {
					redis.hdel(redisKey, vabi.getBusinessId());// 清除redis
				}

				break;
			case "6":
				int callCenter = ValueUtil.calcMul(voiceAccountBusinessInfoQuery.getCallCenter(), 10000,
						voiceAccountBusinessInfoQuery.getCallCenterRule());// 呼叫中心
				vabi.setFeerate(callCenter);
				vabi.setFeeRule(voiceAccountBusinessInfoQuery.getCallCenterRule());
				vabi.setUpdateTime(new Date());
				count = update(vabi);

				if (count > 0) {
					redis.hdel(redisKey, vabi.getBusinessId());// 清除redis
				}

				break;
			case "7":
				int multiTalk = ValueUtil.calcMul(voiceAccountBusinessInfoQuery.getMultiTalk(), 10000,
						voiceAccountBusinessInfoQuery.getMultiTalkRule());// 多方通话
				vabi.setFeerate(multiTalk);
				vabi.setFeeRule(voiceAccountBusinessInfoQuery.getMultiTalkRule());
				vabi.setUpdateTime(new Date());
				count = update(vabi);

				if (count > 0) {
					redis.hdel(redisKey, vabi.getBusinessId());// 清除redis
				}

				break;
			case "8":
				int smsNotification = ValueUtil.calcMul(voiceAccountBusinessInfoQuery.getSmsNotification(), 10000);// 短信验通知
				vabi.setFeerate(smsNotification);
				vabi.setFeeRule(voiceAccountBusinessInfoQuery.getSmsNotificationRule());
				vabi.setUpdateTime(new Date());
				count = update(vabi);

				if (count > 0) {
					redis.hdel(redisKey, vabi.getBusinessId());// 清除redis
				}

				break;
			case "9":
				int smsVeriicationCode = ValueUtil.calcMul(voiceAccountBusinessInfoQuery.getSmsVeriicationCode(),
						10000);// 短信验证码
				vabi.setFeerate(smsVeriicationCode);
				vabi.setFeeRule(voiceAccountBusinessInfoQuery.getSmsVeriicationCodeRule());
				vabi.setUpdateTime(new Date());
				count = update(vabi);

				if (count > 0) {
					redis.hdel(redisKey, vabi.getBusinessId());// 清除redis
				}

				break;
			case "10":
				int soundRecording = ValueUtil.calcMul(voiceAccountBusinessInfoQuery.getSoundRecording(), 10000);// 录音
				vabi.setFeerate(soundRecording);
				vabi.setFeeRule(voiceAccountBusinessInfoQuery.getSoundRecordingRule());
				vabi.setUpdateTime(new Date());
				count = update(vabi);

				if (count > 0) {
					redis.hdel(redisKey, vabi.getBusinessId());// 清除redis
				}

				break;
			case "11":
				int smsMarket = ValueUtil.calcMul(voiceAccountBusinessInfoQuery.getSmsMarket(), 10000);// 录音
				vabi.setFeerate(smsMarket);
				vabi.setFeeRule(voiceAccountBusinessInfoQuery.getSmsMarketRule());
				vabi.setUpdateTime(new Date());
				count = update(vabi);

				if (count > 0) {
					redis.hdel(redisKey, vabi.getBusinessId());// 清除redis
				}

				break;
			}
		}
		// voiceAccountBusinessInfoMapper.batchUpdate(voiceAccountBusinessInfos);
	}
}
