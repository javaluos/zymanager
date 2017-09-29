package com.zy.cms.mapper.manager;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zy.cms.vo.manager.CdrMonitorSetting;

public interface CdrMonitorSettingMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(CdrMonitorSetting record);

	int insertSelective(CdrMonitorSetting record);

	CdrMonitorSetting selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(CdrMonitorSetting record);

	int updateByPrimaryKey(CdrMonitorSetting record);

	List<CdrMonitorSetting> selectGlobalSettings(@Param("startFlag") Integer startFlag, @Param("globalFlag") Integer globalFlag);
	
	List<CdrMonitorSetting> getCdrAccountMonitorSetting(@Param("startFlag") Integer startFlag,@Param("globalFlag") Integer globalFlag,@Param("apiAccount") String apiAccount);

	Integer selectMaxId();

	List<CdrMonitorSetting> queryMonitorSettingForAll(@Param("globalFlag") Integer globalFlag,
			@Param("apiAccount") String apiAccount, @Param("businessId") String businessId);
}