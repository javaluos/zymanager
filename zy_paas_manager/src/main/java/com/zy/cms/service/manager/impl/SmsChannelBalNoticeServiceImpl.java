package com.zy.cms.service.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.mapper.manager.SmsChannelBalNoticeSettingMapper;
import com.zy.cms.service.manager.SmsChannelBalNoticeService;
import com.zy.cms.vo.SmsChannelBalNoticeSetting;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Service("smsChannelBalNoticeService")
@Transactional
public class SmsChannelBalNoticeServiceImpl implements SmsChannelBalNoticeService {
	
	private static final ZyLogger logger = ZyLogger.getLogger(SmsChannelBalNoticeServiceImpl.class);

	@Autowired
	private SmsChannelBalNoticeSettingMapper smsChannelBalNoticeSettingMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) throws Exception {
		return smsChannelBalNoticeSettingMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(SmsChannelBalNoticeSetting record) throws Exception {
		return smsChannelBalNoticeSettingMapper.insert(record);
	}

	@Override
	public int insertSelective(SmsChannelBalNoticeSetting record) throws Exception {
		return smsChannelBalNoticeSettingMapper.insertSelective(record);
	}

	@Override
	public SmsChannelBalNoticeSetting selectByPrimaryKey(Integer id) throws Exception {
		return smsChannelBalNoticeSettingMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SmsChannelBalNoticeSetting record) throws Exception {
		return smsChannelBalNoticeSettingMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SmsChannelBalNoticeSetting record) throws Exception {
		return smsChannelBalNoticeSettingMapper.updateByPrimaryKey(record);
	}

	@Override
	public SmsChannelBalNoticeSetting getSetting() throws SQLException {
		SmsChannelBalNoticeSetting result = null;
		List<SmsChannelBalNoticeSetting> settings = smsChannelBalNoticeSettingMapper.selectAllSettings();
		if(null != settings && settings.size() > 0){
			result = settings.get(0);
		}
		return result;
	}

	@Override
	public boolean saveSetting(String noticeType, String noticePhone, String noticeEmail) throws SQLException{
		boolean result = false;
		List<SmsChannelBalNoticeSetting> settings = smsChannelBalNoticeSettingMapper.selectAllSettings();
		if(null != settings && settings.size() > 0){
			SmsChannelBalNoticeSetting setting = settings.get(0);
			setting.setNoticeType(noticeType);
			setting.setNoticePhone(noticePhone);
			setting.setNoticeEmail(noticeEmail);
			setting.setUpdateTime(new Date());
			result = smsChannelBalNoticeSettingMapper.updateByPrimaryKeySelective(setting) > 0;
		}else{
			SmsChannelBalNoticeSetting setting = new SmsChannelBalNoticeSetting();
			setting.setNoticeType(noticeType);
			setting.setNoticePhone(noticePhone);
			setting.setNoticeEmail(noticeEmail);
			setting.setUpdateTime(new Date());
			setting.setCreateTime(new Date());
			result = smsChannelBalNoticeSettingMapper.insert(setting) > 0;
		}
		return result;
	}

}
