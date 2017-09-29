package com.zy.cms.mapper.manager;

import java.util.List;

import com.zy.cms.entity.SmsChannelMonitor;
import com.zy.cms.vo.SmsChannelMonitorVO;
import org.apache.ibatis.annotations.Param;

public interface SmsChannelMonitorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SmsChannelMonitor record);

    int insertSelective(SmsChannelMonitor record);

    SmsChannelMonitor selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SmsChannelMonitor record);

    int updateByPrimaryKey(SmsChannelMonitor record);
    
    public List<SmsChannelMonitor> querySmsChannelMonitorList(SmsChannelMonitor smsChannelMonitor) ;
    
    public List<SmsChannelMonitor> querySmsChannelMonitorListByStartFlag(SmsChannelMonitor smsChannelMonitor) ;
    
    public SmsChannelMonitorVO querySmsChannelMonitorVOList(SmsChannelMonitor sSmsChannelMonitor) ;

	public SmsChannelMonitor findSmsChannelMonitor(SmsChannelMonitor smsChannelMonitor);

	public void monitorFlagChange(SmsChannelMonitor smsChannelMonitor);

    List<SmsChannelMonitor> queryMonitorSettings(@Param("channelId") String channelId, @Param("startFlag") Integer startFlag);
}