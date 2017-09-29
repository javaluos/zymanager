package com.zy.cms.mapper.manager;

import com.zy.cms.vo.SmsChannelBalMonitorSetting;
import com.zy.cms.vo.channel.SmsChannel;
import com.zy.cms.vo.query.SmsChannelQuery;

import java.sql.SQLException;
import java.util.List;

public interface SmsChannelBalMonitorSettingMapper {
	
    int deleteByPrimaryKey(Integer id) throws Exception;

    int insert(SmsChannelBalMonitorSetting record) throws Exception;

    int insertSelective(SmsChannelBalMonitorSetting record) throws Exception;

    SmsChannelBalMonitorSetting selectByPrimaryKey(Integer id) throws Exception;

    SmsChannelBalMonitorSetting selectByChannelId(String channelId) throws SQLException;

    int updateByPrimaryKeySelective(SmsChannelBalMonitorSetting record) throws Exception;

    int updateByPrimaryKey(SmsChannelBalMonitorSetting record) throws Exception;

    int selectCountByQuery(SmsChannelQuery query) throws SQLException;

    List<SmsChannel> selectListByQuery(SmsChannelQuery query) throws SQLException;

    int deleteByChannelId(String channelId) throws SQLException;
}