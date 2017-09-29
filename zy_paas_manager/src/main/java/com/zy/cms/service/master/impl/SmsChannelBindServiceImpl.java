package com.zy.cms.service.master.impl;

import com.zy.cms.common.RedisConstant;
import com.zy.cms.common.RedisOperator;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.enums.OperateLogTypeEnum;
import com.zy.cms.mapper.master.SmsChannelBandMapper;
import com.zy.cms.mapper.master.SmsChannelMapper;
import com.zy.cms.service.master.OperateLogService;
import com.zy.cms.service.master.SmsChannelBindService;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.vo.channel.SmsChannel;
import com.zy.cms.vo.channel.SmsChannelBand;
import com.zy.cms.vo.channel.SmsChannelBandVo;
import com.zy.cms.vo.query.SmsChannelBindQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Service("smsChannelBindService")
public class SmsChannelBindServiceImpl implements SmsChannelBindService {

	private static final ZyLogger logger = ZyLogger.getLogger(SmsChannelBindServiceImpl.class);

	@Autowired
	private SmsChannelBandMapper mapper;

	@Autowired
	private RedisOperator redis;

	@Autowired
	private SmsChannelMapper smsChannelMapper;

	@Autowired
	private OperateLogService operateLogService;

	@Override
	public Integer getCountByQuery(SmsChannelBindQuery query) {
		return mapper.selectCountByQuery(query);
	}

	@Override
	public List<SmsChannelBandVo> getListByQuery(SmsChannelBindQuery query) {
		return mapper.selectListByQuery(query);
	}

	@Override
	public boolean modifyScore(Integer id, Integer score, Integer thresholdValue) throws SQLException {
		boolean result = false;
		SmsChannelBand smsChannelBand = mapper.selectByPrimaryKey(id);
		if (null == smsChannelBand)
			return false;
		smsChannelBand.setChannelScore(score);
		smsChannelBand.setThresholdValue(thresholdValue);
		result = mapper.updateByPrimaryKeySelective(smsChannelBand) > 0;
		String apiAccount = smsChannelBand.getApiAccount();
		String channelId = smsChannelBand.getChannelId();
		if (result) {
			// 修改redis信息
			String redisKey = String.format(RedisConstant.ZHIYU_PAAS_SMS_MCHBAND_CHANNEL, apiAccount);
			redis.hset(redisKey, channelId, JsonUtil.objectToJson(smsChannelBand));
		}
		return result;
	}

	@Override
	public boolean deleteChannelBind(Integer id, String userName) throws SQLException {
		boolean result = false;
		SmsChannelBand smsChannelBand = mapper.selectByPrimaryKey(id);
		if (null == smsChannelBand)
			return false;
		result = mapper.deleteByPrimaryKey(id) > 0;
		if (result) {
			String channelId = smsChannelBand.getChannelId();
			SmsChannel smsChannel = smsChannelMapper.selectByPrimaryKey(channelId);
			result = operateLogService.addOperateLog(smsChannelBand.getApiAccount(),
					OperateLogTypeEnum.DEL_CHNBIND.getType(), userName, channelId, smsChannel.getChannelName());
		}
		String apiAccount = smsChannelBand.getApiAccount();
		String channelId = smsChannelBand.getChannelId();
		logger.info("【删除数据客户通道绑定】，API_ACCOUNT=" + apiAccount + "," + "CHANNEL-ID=" + channelId + "");
		if (result) {
			String redisKey = String.format(RedisConstant.ZHIYU_PAAS_SMS_MCHBAND_CHANNEL, apiAccount);
			Long value = redis.hdel(redisKey, channelId);
			logger.info("【删除Redis客户通道绑定】，key=" + redisKey + ",结果为" + value + "");
		}
		return result;
	}

	@Override
	public List<SmsChannelBandVo> getByApiAccount(String apiAccount) {
		return mapper.selectByApiAccount(apiAccount);
	}

	@Override
	@Transactional
	public boolean dobindChannel(String apiAccount, String channels, String userName) throws Exception {
		boolean result = false;

		String redisKey = String.format(RedisConstant.ZHIYU_PAAS_SMS_MCHBAND_CHANNEL, apiAccount);
		List<SmsChannelBandVo> bandList = mapper.selectByApiAccount(apiAccount);
		for (SmsChannelBandVo smsChannelBandVo : bandList) {
			operateLogService.addOperateLog(apiAccount, OperateLogTypeEnum.DEL_CHNBIND.getType(), userName,
					smsChannelBandVo.getChannelId(), smsChannelBandVo.getChannelName());
		}
		result = mapper.deleteByApiAccount(apiAccount) > 0;
		redis.del(redisKey);
		channels = channels.substring(0, channels.length() - 1);
		String[] channelArrays = channels.split(",");
		for (String channelInfo : channelArrays) {
			String channelIdAndScore = channelInfo.substring(0, channelInfo.lastIndexOf("_"));
			String channelId = channelIdAndScore.substring(0, channelIdAndScore.lastIndexOf("_"));
			String score = channelIdAndScore.substring(channelIdAndScore.lastIndexOf("_") + 1,
					channelIdAndScore.length());
			SmsChannel smsChannel = smsChannelMapper.selectByPrimaryKey(channelId);
			smsChannel.setApiAccount(apiAccount);
			String thresholdValue = channelInfo.substring(channelInfo.lastIndexOf("_") + 1, channelInfo.length());
			if (StringUtils.isBlank(channelId) || StringUtils.isBlank(score) || StringUtils.isBlank(thresholdValue)) {
				return false;
			}
			smsChannel.setChannelScore(Integer.valueOf(score));
			smsChannel.setThresholdValue(Integer.valueOf(thresholdValue));
			Date now = new Date();
			SmsChannelBand smsChannelBandInfo = new SmsChannelBand();
			smsChannelBandInfo.setApiAccount(apiAccount);
			smsChannelBandInfo.setChannelId(channelId);
			smsChannelBandInfo.setChannelScore(Integer.valueOf(score));
			smsChannelBandInfo.setThresholdValue(Integer.valueOf(thresholdValue));
			smsChannelBandInfo.setStatus(1);
			smsChannelBandInfo.setUseType(0);// (0:测试;1:正式)
			smsChannelBandInfo.setCreateTime(now);
			smsChannelBandInfo.setUpdateTime(now);
			result = mapper.insert(smsChannelBandInfo) > 0;
			operateLogService.addOperateLog(apiAccount, OperateLogTypeEnum.ADD_CHNBIND.getType(), userName, channelId,
					smsChannel.getChannelName());
			if (result) {
				redis.hset(redisKey, channelId, JsonUtil.objectToJson(smsChannelBandInfo));
			}
		}
		return result;
	}

	@Override
	public String clearSendTotal(String apiAccount, String channelId) throws Exception {
		String redisKey = String.format(RedisConstant.ZHIYU_PAAS_SMS_ACCOUNT_CHANNEL_DAY_SEND_TOTAL, apiAccount,
				channelId);
		String result = redis.set(redisKey, "0");
		return result;
	}

}
