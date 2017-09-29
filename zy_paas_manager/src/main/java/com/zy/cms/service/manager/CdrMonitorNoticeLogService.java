package com.zy.cms.service.manager;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zy.cms.vo.manager.CdrMonitorNoticeLog;
import com.zy.cms.vo.query.CdrMonitorNoticeLogQuery;

public interface CdrMonitorNoticeLogService {
   
	/**
	 * 根据实体对象查询列表
	 * @param voiceUpload 实体对象
	 * @return 
	 * @throws Exception
	 */
	public List<CdrMonitorNoticeLog> queryCdrMonitorLogByEntity(CdrMonitorNoticeLogQuery cdrMonitorNoticeLogQuery) throws Exception;
	
	public int queryCdrMonitorLogCount(CdrMonitorNoticeLogQuery cdrMonitorNoticeLogQuery) throws Exception;
	
	public int updateLog(String id,String dealUser) throws Exception;
	
	public int updateAll(String dealUser) throws Exception;
}
