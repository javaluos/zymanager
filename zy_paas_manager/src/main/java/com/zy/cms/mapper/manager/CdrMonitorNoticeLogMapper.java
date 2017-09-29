package com.zy.cms.mapper.manager;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zy.cms.vo.manager.CdrMonitorNoticeLog;
import com.zy.cms.vo.query.CdrMonitorNoticeLogQuery;

public interface CdrMonitorNoticeLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CdrMonitorNoticeLog record);

    int insertSelective(CdrMonitorNoticeLog record);

    CdrMonitorNoticeLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CdrMonitorNoticeLog record);

    int updateByPrimaryKey(CdrMonitorNoticeLog record);
    
    public List<CdrMonitorNoticeLog> queryCdrMonitorLogByEntity(CdrMonitorNoticeLogQuery cdrMonitorNoticeLogQuery);
    
    public int queryCdrMonitorLogCount(CdrMonitorNoticeLogQuery cdrMonitorNoticeLogQuery) throws Exception;
    
    public int updateLog(@Param(value="id") String id,@Param(value="dealUser") String dealUser) throws Exception;
    
    public List<CdrMonitorNoticeLog> queryCdrMonitorLogByParam(@Param(value="monitorBody") String monitorBody,@Param(value="monitorType") String monitorType,@Param(value="startTime")String startTime,@Param(value="endTime")String endTime) throws Exception;

    public int updateLog(@Param(value="id") int id) throws Exception;
    
    public int updateAll(String dealUser) throws Exception;
    
}