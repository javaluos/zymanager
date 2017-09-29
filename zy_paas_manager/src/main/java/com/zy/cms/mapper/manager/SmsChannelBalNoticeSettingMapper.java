package com.zy.cms.mapper.manager;

import com.zy.cms.vo.SmsChannelBalNoticeSetting;

import java.sql.SQLException;
import java.util.List;

public interface SmsChannelBalNoticeSettingMapper {
	
    int deleteByPrimaryKey(Integer id) throws Exception;

    int insert(SmsChannelBalNoticeSetting record) throws SQLException;

    int insertSelective(SmsChannelBalNoticeSetting record) throws Exception;

    SmsChannelBalNoticeSetting selectByPrimaryKey(Integer id) throws Exception;

    int updateByPrimaryKeySelective(SmsChannelBalNoticeSetting record) throws SQLException;

    int updateByPrimaryKey(SmsChannelBalNoticeSetting record) throws SQLException;

    List<SmsChannelBalNoticeSetting> selectAllSettings() throws SQLException;
}