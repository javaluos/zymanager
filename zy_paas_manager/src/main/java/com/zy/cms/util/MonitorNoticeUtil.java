package com.zy.cms.util;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.elastic.GlobalConfig;
import com.zy.cms.enums.BusinessTypeEnum;
import com.zy.cms.mapper.manager.CdrMonitorNoticeLogMapper;
import com.zy.cms.vo.manager.CdrMonitorNoticeLog;

@Component(value = "monitorNoticeUtil")
public class MonitorNoticeUtil {

	private static final ZyLogger logger = ZyLogger.getLogger(MonitorNoticeUtil.class);

	@Autowired
	private CdrMonitorNoticeLogMapper cdrMonitorNoticeLogMapper;
	
	@Resource
	private GlobalConfig globalConfig;
	
	@Value("#{configProperties['UP_MONITOR_MOBILE']}")
	private String mobiles;
	
	/**
	 * 
	 * @param monitorBody 告警主题
	 * @param businessId 告警类型
	 * @param monitorContent 告警内容
 	 * @param statisticalTime 告警时间
	 * @param flag 如果为false,则表示是平台全局监控或客户业务监控
	 * @return
	 */
	public boolean saveMonitorLog(String monitorBody,String businessId,String monitorContent,Date statisticalTime,boolean flag){
		CdrMonitorNoticeLog noticeLog = new CdrMonitorNoticeLog();
		
		noticeLog.setMonitorBody(monitorBody);
		if(flag){
			noticeLog.setMonitorType(businessId);
		}else{
			if(businessId.equals(BusinessTypeEnum.BT_8)||businessId.equals(BusinessTypeEnum.BT_9)||businessId.equals(BusinessTypeEnum.BT_11)){
				if(monitorBody.equals("平台")){
					noticeLog.setMonitorType("2");
				}else{
					noticeLog.setMonitorType("4");
				}
					
			}else{
				if(monitorBody.equals("平台")){
					noticeLog.setMonitorType("1");
				}else{
					noticeLog.setMonitorType("3");
				}
			}
		}
		noticeLog.setMonitorContent(monitorContent);
		noticeLog.setIsUpMonitor("1");
		noticeLog.setIsDeal("1");
		noticeLog.setStatisticalTime(statisticalTime);
		noticeLog.setCreateTime(new Date());
		boolean successFlag = cdrMonitorNoticeLogMapper.insert(noticeLog) > 0;
		
		return successFlag;
	}
	
	/**
	 * 判断是否需要升级告警，如果需要则返回整数，如果不需要，则返回0
	 * @param monitorBody
	 * @param monitorType
	 * @return
	 */
	public int sendVoiceUPMonitor(String monitorBody,String monitorType) {
		int count = 0;
		try {
			logger.info("【新平台告警升级语音】机制开始。。");
			
			Date now=new Date();
			String mm=DateUtil.formatDate(now, DateUtil.DATE_TIME_FORMAT);//yyyy-MM-dd HH:mm
			now=DateUtil.parseDate(mm, DateUtil.DATE_TIME_FORMAT);
			final String startTime=DateUtil.getDateTime(DateUtil.addMinutes(now, -10));//前10分钟
			final String endTime=DateUtil.getDateTime(DateUtil.addMinutes(now, -5));//前5分钟
			
			List<CdrMonitorNoticeLog> cdrMonitorNoticeLogs=cdrMonitorNoticeLogMapper.queryCdrMonitorLogByParam(monitorBody, monitorType,startTime,endTime);
			if(cdrMonitorNoticeLogs!=null && cdrMonitorNoticeLogs.size()>1){
				logger.info("【新平台告警升级语音】机制告警日志数据为:"+JsonUtil.toJsonString(cdrMonitorNoticeLogs.get(0))+"和"+JsonUtil.toJsonString(cdrMonitorNoticeLogs.get(1)));
				if(cdrMonitorNoticeLogs.size()>=2){
					for(int i=0;i<cdrMonitorNoticeLogs.size();i++){
						cdrMonitorNoticeLogMapper.updateLog(cdrMonitorNoticeLogs.get(i).getId());
					}
					count=1;
				  }
			}
			logger.info("【新平台告警升级语音】机制结束。。");
		}catch (Exception e) {
			logger.error("【新平台告警升级语音】异常" + e.getMessage(), e);
		}
	
		return count;
	}
	
	/**
	 * 发送语音升级告警
	 * @return
	 */
	public String sendVoice(){
		String rsp = "";
		try {
			if(StringUtils.isNotEmpty(mobiles)){
				String mobileArray[]=mobiles.split(",");
				if(mobileArray.length>0){
					 for(String mobile:mobileArray){
						sendNoticeVoice(mobile,"平台有超过十分钟未处理的告警，请注意！");
					} 
				 }
			 }
		} catch (Exception e) {
			logger.error("升级告警发语音告警出现异常，异常是:"+e.getMessage());
		}
	   return rsp;
	}
	
	public String sendNoticeVoice(String mobile, String content) {
		String url = globalConfig.getConfigV("PAAS_MONITOR_VOICE_URL");
		String apiAccount=globalConfig.getConfigV("PAAS_MONITOR_APIACCOUNT");
		String apiKey = globalConfig.getConfigV("PAAS_MONITOR_VOICE_APIKEY");
		String appId=globalConfig.getConfigV("PAAS_MONITOR_APPID");
		String rsp = "";
		try {
			url.replaceAll("Monitor", apiAccount);
			// 时间戳
			long timestamp = System.currentTimeMillis();
			// MD5加密处理
			String ll = apiAccount+apiKey+timestamp;
			String sign = MD5Util.md5Hex(ll);
			/**
			  {
			 
			    "apiAccount": "ACC282691b568f2422dbe27201efe969a92",
			    "appId": "APPba930873768d4920b9bb0b2721154c1d",
			    "callee": "18813579883",
			    "contentType": 0,
			    "content": "欢迎使用智语平台，你有一个快件请查收",
			    "playTimes": 0,
			    "voicemailFlag": 0,
			    "dtmfFlag": 0,
			    "timeStamp": "1495878490959",
			    "sign": "376ae541a2a537ec92ebc98338f8abfc"
			 }
			**/
			Map map = new HashMap();
			map.put("apiAccount", apiAccount);
			map.put("appId", appId);
			map.put("callee",mobile);
			map.put("contentType",0);
			map.put("content",content);
			map.put("playTimes",0);
			map.put("voicemailFlag",0);
			map.put("dtmfFlag",0);
			map.put("timeStamp",String.valueOf(timestamp));
			map.put("sign",sign );
			String params=JsonUtil.objectToJson(map);
			Map<String, String> header = new HashMap<String, String>();
			rsp = HttpClientUtil.INSTANCE.httpPost(url, params, header);
			logger.info("【新平台语音系统】返回：" + rsp);
		} catch (Exception e) {
			logger.error("【新平台语音系统】异常" + e.getMessage(), e);
		}
		return rsp;
	}
}
