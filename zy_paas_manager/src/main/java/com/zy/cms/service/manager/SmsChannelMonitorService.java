package com.zy.cms.service.manager;

import java.util.List;

import com.zy.cms.entity.SmsChannelMonitor;
import com.zy.cms.vo.chart.ChartUI;
import com.zy.cms.vo.query.ChartQuery;

public interface SmsChannelMonitorService {

	public List<SmsChannelMonitor> querySmsChannelMonitorList(SmsChannelMonitor smsChannelMonitor);

	public SmsChannelMonitor findSmsChannelMonitor(SmsChannelMonitor smsChannelMonitor);

	public void updateSmsChannelMonitor(SmsChannelMonitor smsChannelMonitor);

	public void insertSmsChannelMonitor(SmsChannelMonitor smsChannelMonitor);
	
	public void delSmsChannelMonitor(SmsChannelMonitor smsChannelMonitor);
	
	public SmsChannelMonitor findSmsChannelMonitorById(SmsChannelMonitor smsChannelMonitor);

	public void monitorFlagChange(SmsChannelMonitor smsChannelMonitor);

    ChartUI queryMonitorChannelChartLine(ChartQuery chartQuery) throws Exception;
}