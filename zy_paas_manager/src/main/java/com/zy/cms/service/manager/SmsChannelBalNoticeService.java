package com.zy.cms.service.manager;

import com.zy.cms.vo.SmsChannelBalNoticeSetting;

import java.sql.SQLException;

public interface SmsChannelBalNoticeService {
   
	int deleteByPrimaryKey(Integer id) throws Exception;

    int insert(SmsChannelBalNoticeSetting record) throws Exception;

    int insertSelective(SmsChannelBalNoticeSetting record) throws Exception;

    SmsChannelBalNoticeSetting selectByPrimaryKey(Integer id) throws Exception;

    int updateByPrimaryKeySelective(SmsChannelBalNoticeSetting record) throws Exception;

    int updateByPrimaryKey(SmsChannelBalNoticeSetting record) throws Exception;

    SmsChannelBalNoticeSetting getSetting() throws SQLException;

    boolean saveSetting(String noticeType, String noticePhone, String noticeEmail) throws SQLException;

}
