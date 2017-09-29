package com.zy.cms.service.manager;

import com.zy.cms.vo.SmsChannelBalMonitorSetting;
import com.zy.cms.vo.channel.SmsChannel;
import com.zy.cms.vo.query.SmsChannelQuery;

import java.sql.SQLException;
import java.util.List;

public interface SmsChannelBalMonitorService {
   
	int deleteByPrimaryKey(Integer id) throws Exception;
	
    int insert(SmsChannelBalMonitorSetting record) throws Exception;

    int insertSelective(SmsChannelBalMonitorSetting record) throws Exception;

    SmsChannelBalMonitorSetting selectByPrimaryKey(Integer id) throws Exception;

    int updateByPrimaryKeySelective(SmsChannelBalMonitorSetting record) throws Exception;

    int updateByPrimaryKey(SmsChannelBalMonitorSetting record) throws Exception;

    int queryCountByEntity(SmsChannelQuery query) throws SQLException;

    List<SmsChannel> queryListByEntity(SmsChannelQuery query) throws SQLException;

    boolean deleteMonitor(String channelId) throws SQLException;
    
    int deleteByChannelId(String channelId) throws SQLException;
}
