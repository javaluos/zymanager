package com.zy.cms.service.manager;

import java.sql.SQLException;

import com.zy.cms.entity.SmsChannelMonitorNoticeSetting;

public interface SmsChannelMonitorNoticeSettingService {

    SmsChannelMonitorNoticeSetting getSetting() throws SQLException;

    boolean saveSetting(String noticeType, String noticePhone, String noticeEmail) throws SQLException;

}
