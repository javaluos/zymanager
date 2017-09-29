package com.zy.cms.mapper.manager;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zy.cms.vo.manager.CdrMonitorNoticeSetting;

public interface CdrMonitorNoticeSettingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CdrMonitorNoticeSetting record);

    int insertSelective(CdrMonitorNoticeSetting record);

    CdrMonitorNoticeSetting selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CdrMonitorNoticeSetting record);

    int updateByPrimaryKey(CdrMonitorNoticeSetting record);
    
    CdrMonitorNoticeSetting selectByApiaccount(String apiAccount);

	List<CdrMonitorNoticeSetting> getGlobalNoticeSetting(@Param("startFlag") int startFlag, @Param("globalFlag") int globalFlag);
}