package com.zy.cms.service.manager.impl;

import com.zy.cms.common.RedisConstant;
import com.zy.cms.common.RedisOperator;
import com.zy.cms.enums.StartFlagEnum;
import com.zy.cms.mapper.master.SmsChannelMapper;
import com.zy.cms.vo.channel.SmsChannel;
import com.zy.cms.vo.query.SmsChannelQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.mapper.manager.SmsChannelBalMonitorSettingMapper;
import com.zy.cms.service.manager.SmsChannelBalMonitorService;
import com.zy.cms.vo.SmsChannelBalMonitorSetting;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Service("smsChannelBalMonitorService")
@Transactional
public class SmsChannelBalMonitorServiceImpl implements SmsChannelBalMonitorService {
	
	private static final ZyLogger logger = ZyLogger.getLogger(SmsChannelBalMonitorServiceImpl.class);
	
	@Autowired
	private SmsChannelBalMonitorSettingMapper smsChannelBalMonitorSettingMapper;

	@Autowired
	private SmsChannelMapper smsChannelMapper;

	@Autowired
	private RedisOperator redis;

	@Override
	public int deleteByPrimaryKey(Integer id) throws Exception {
		return smsChannelBalMonitorSettingMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(SmsChannelBalMonitorSetting record) throws Exception {
		SmsChannelBalMonitorSetting setting = smsChannelBalMonitorSettingMapper
				.selectByChannelId(record.getChannelId());
		if(null != setting){
			setting.setMonitorBalance(record.getMonitorBalance());
			setting.setUpdateTime(new Date());
			return smsChannelBalMonitorSettingMapper.updateByPrimaryKeySelective(setting);
		}
		return smsChannelBalMonitorSettingMapper.insert(record);
	}

	@Override
	public int insertSelective(SmsChannelBalMonitorSetting record) throws Exception {
		return smsChannelBalMonitorSettingMapper.insertSelective(record);
	}

	@Override
	public SmsChannelBalMonitorSetting selectByPrimaryKey(Integer id) throws Exception {
		return smsChannelBalMonitorSettingMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SmsChannelBalMonitorSetting record) throws Exception {
		return smsChannelBalMonitorSettingMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SmsChannelBalMonitorSetting record) throws Exception {
		return smsChannelBalMonitorSettingMapper.updateByPrimaryKey(record);
	}

	@Override
	public int queryCountByEntity(SmsChannelQuery query) throws SQLException {
		return smsChannelBalMonitorSettingMapper.selectCountByQuery(query);
	}

	@Override
	public List<SmsChannel> queryListByEntity(SmsChannelQuery query) throws SQLException {
		return smsChannelBalMonitorSettingMapper.selectListByQuery(query);
	}

	@Override
	@Transactional
	public boolean deleteMonitor(String channelId) throws SQLException {
		boolean result = false;
		SmsChannel smsChannel = smsChannelMapper.selectByPrimaryKey(channelId);
		smsChannel.setBalanceMonitorFlag(StartFlagEnum.DISABLE.getType());
		result = smsChannelMapper.updateByPrimaryKeySelective(smsChannel) > 0;
		if(result){
			SmsChannelBalMonitorSetting setting = smsChannelBalMonitorSettingMapper.selectByChannelId(channelId);
			if(null == setting){
				return true;
			}
			result = smsChannelBalMonitorSettingMapper.deleteByChannelId(channelId) > 0;
		}
		if(result){
			String smsRedisKey = String.format(RedisConstant.ZHIYU_PAAS_SMS_CHANNEL_BALANCE_MONITOR, channelId + "0");
			String emailRedisKey = String.format(RedisConstant.ZHIYU_PAAS_SMS_CHANNEL_BALANCE_MONITOR, channelId + "1");
			redis.del(smsRedisKey);
			redis.del(emailRedisKey);
		}
		return result;
	}

	@Override
	public int deleteByChannelId(String channelId) throws SQLException {
		return smsChannelBalMonitorSettingMapper.deleteByChannelId(channelId);
	}

}
