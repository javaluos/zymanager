package com.zy.cms.mapper.sms;

import com.zy.cms.entity.SmsChannelMonitor;
import com.zy.cms.vo.SmsChannelMonitorVO;

public interface SmsSlaveChannelMonitorMapper {
	
	 public SmsChannelMonitorVO querySmsChannelMonitorVOList(SmsChannelMonitor sSmsChannelMonitor) ;
}