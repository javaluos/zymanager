package com.zy.cms.service.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zy.cms.enums.GlobalFlagEnum;
import com.zy.cms.enums.StartFlagEnum;
import com.zy.cms.mapper.manager.CdrMonitorNoticeSettingMapper;
import com.zy.cms.service.manager.CdrMonitorNoticeSettingService;
import com.zy.cms.vo.manager.CdrMonitorNoticeSetting;


@Service("cdrMonitorNoticeSettingService")
public class CdrMonitorNoticeSettingServiceImpl implements CdrMonitorNoticeSettingService {
	
	@Autowired
	private CdrMonitorNoticeSettingMapper cdrMonitorNoticeSettingMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return cdrMonitorNoticeSettingMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(CdrMonitorNoticeSetting record) {
		return cdrMonitorNoticeSettingMapper.insert(record);
	}

	@Override
	public int insertSelective(CdrMonitorNoticeSetting record) {
		return cdrMonitorNoticeSettingMapper.insertSelective(record);
	}

	@Override
	public CdrMonitorNoticeSetting selectByPrimaryKey(Integer id) {
		return cdrMonitorNoticeSettingMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(CdrMonitorNoticeSetting record) {
		return cdrMonitorNoticeSettingMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(CdrMonitorNoticeSetting record) {
		return cdrMonitorNoticeSettingMapper.updateByPrimaryKey(record);
	}

	@Override
	public CdrMonitorNoticeSetting selectByApiaccount(String apiAccount) {
		return cdrMonitorNoticeSettingMapper.selectByApiaccount(apiAccount);
	}

	@Override
	public CdrMonitorNoticeSetting getGlobalNoticeSetting() {
		CdrMonitorNoticeSetting result = null;
		List<CdrMonitorNoticeSetting> cdrMonitorNoticeSettings = cdrMonitorNoticeSettingMapper
				.getGlobalNoticeSetting(StartFlagEnum.ENABLE.getType(), GlobalFlagEnum.GLOBAL.getType());
		if(null != cdrMonitorNoticeSettings && cdrMonitorNoticeSettings.size() > 0){
			return cdrMonitorNoticeSettings.get(0);
		}
		return result;
	}

}
