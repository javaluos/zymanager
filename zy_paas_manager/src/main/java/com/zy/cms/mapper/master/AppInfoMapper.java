package com.zy.cms.mapper.master;

import java.util.List;
import java.util.Map;

import com.zy.cms.vo.AppInfo;

public interface AppInfoMapper {

	int deleteByPrimaryKey(Integer id);

	int delete(Map params);

	int insert(AppInfo record);

	int insertSelective(AppInfo record);

	AppInfo selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(AppInfo record);

	int updateByPrimaryKey(AppInfo record);

	List<AppInfo> getAppInfos(Map params);

	List<Map> getIds(Map params);

	Integer getCounts(Map params);

	AppInfo getAppInfo(Map params);

	int getAppInfoExitCount(Map params);
}
