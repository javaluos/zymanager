package com.zy.cms.mapper.manager;

import com.zy.cms.entity.SmsChannelMonitorNoticeSetting;

public interface SmsChannelMonitorNoticeSettingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SmsChannelMonitorNoticeSetting record);

    int insertSelective(SmsChannelMonitorNoticeSetting record);

    SmsChannelMonitorNoticeSetting selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SmsChannelMonitorNoticeSetting record);

    int updateByPrimaryKey(SmsChannelMonitorNoticeSetting record);
    
    public SmsChannelMonitorNoticeSetting querySmsChannelMonitorNoticeSetting(SmsChannelMonitorNoticeSetting smsChannelMonitorNoticeSetting) ;
}