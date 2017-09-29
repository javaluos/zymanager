package com.zy.cms.service.manager.impl;

import java.sql.SQLException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zy.cms.common.ZyLogger;
import com.zy.cms.entity.SmsChannelMonitorNoticeSetting;
import com.zy.cms.mapper.manager.SmsChannelMonitorNoticeSettingMapper;
import com.zy.cms.service.manager.SmsChannelMonitorNoticeSettingService;

@Service("smsChannelMonitorNoticeSettingService")
@Transactional
public class SmsChannelMonitorNoticeSettingServiceImpl implements SmsChannelMonitorNoticeSettingService {
	
	private static final ZyLogger logger = ZyLogger.getLogger(SmsChannelMonitorNoticeSettingServiceImpl.class);

	@Autowired
	private SmsChannelMonitorNoticeSettingMapper smsChannelMonitorNoticeSettingMapper;


	@Override
	public SmsChannelMonitorNoticeSetting getSetting() throws SQLException {
		return  smsChannelMonitorNoticeSettingMapper.querySmsChannelMonitorNoticeSetting(null);
	}

	@Override
	public boolean saveSetting(String noticeType, String noticePhone, String noticeEmail) throws SQLException{
		boolean result = false;
		SmsChannelMonitorNoticeSetting setting = smsChannelMonitorNoticeSettingMapper.querySmsChannelMonitorNoticeSetting(null);;
		if(null != setting ){
			setting.setNoticeType(noticeType);
			setting.setNoticePhone(noticePhone);
			setting.setNoticeEmail(noticeEmail);
			setting.setUpdateTime(new Date());
			result = smsChannelMonitorNoticeSettingMapper.updateByPrimaryKeySelective(setting) > 0;
		}else{
			setting = new SmsChannelMonitorNoticeSetting();
			setting.setNoticeType(noticeType);
			setting.setNoticePhone(noticePhone);
			setting.setNoticeEmail(noticeEmail);
			setting.setUpdateTime(new Date());
			setting.setCreateTime(new Date());
			setting.setItemType(1);
			result = smsChannelMonitorNoticeSettingMapper.insert(setting) > 0;
		}
		return result;
	}

}
