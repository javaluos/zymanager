package com.zy.cms.service.manager;

import com.zy.cms.vo.manager.CdrMonitorNoticeSetting;

public interface CdrMonitorNoticeSettingService {
	
	int deleteByPrimaryKey(Integer id);

    int insert(CdrMonitorNoticeSetting record);

    int insertSelective(CdrMonitorNoticeSetting record);

    CdrMonitorNoticeSetting selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CdrMonitorNoticeSetting record);

    int updateByPrimaryKey(CdrMonitorNoticeSetting record);
    
    CdrMonitorNoticeSetting selectByApiaccount(String apiAccount);

	CdrMonitorNoticeSetting getGlobalNoticeSetting();

}
