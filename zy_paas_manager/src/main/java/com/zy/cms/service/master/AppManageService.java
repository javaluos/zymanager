package com.zy.cms.service.master;

import java.util.List;
import java.util.Map;

import com.zy.cms.vo.AppInfo;
import com.zy.cms.vo.IndustryInfo;

/**
 * App service
 * @author hmj
 * @date 2015-6-03
 */
public interface AppManageService {
	/**
	 * 获取应用列表
	 * @param params key名称可为 appName  status  pageNum pageSize
	 * @return
	 */
	public List<AppInfo> getAppInfos(Map params);
	
	/**
	 * 保存应用
	 * @param param 应用信息
	 * @return
	 */
	public Integer saveAppInfo(AppInfo param);
	
	/**
	 * 更新应用
	 * @param merchantAccount 
	 * @return
	 */
	public Integer updateAppInfo(AppInfo param);
	
	/**
	 * 获取总数
	 * @param params key名称可为 appName  status  pageNum pageSize
	 * @return
	 */
	public int getAppInfoCount(Map params);
	
	/**
	 * 通过APPid查询APP信息
	 * @param id
	 * @return
	 */
	public AppInfo getAppInfoById(Integer id);
	
	/**
	 * 查询app信息
	 * @param map
	 * @return
	 */
	public List<AppInfo> getAppInfoByMap(Map<String,Object> map);
	
	/**
	 * 删除应用
	 * @param id
	 * @return
	 */
	public int deleteApp(Map param);
	/**
	 *应用详情
	 * @param id
	 * @return
	 */
	public Map getAppDetail(Integer id);
	/**
	 * 通过行业ID查询行业名称
	 * @param id
	 * @return
	 */
	public String getIndustryNmById(Integer id);
	/**
	 * 通过签名ID查询签名名称
	 * @param id
	 * @return
	 */
	public String getSignerNmById(Integer id);
	/**
	 * 获取所有行业信息
	 * @return
	 */
	public List<IndustryInfo> getAllIndustryInfo();
	
	/**
	 * 获取总数
	 * @param params key名称可为 apiaccount,appid
	 * @return
	 */
	public int getAppInfoExitCount(Map params);
}
