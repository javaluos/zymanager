package com.zy.cms.controller.channel;

import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zy.cms.entity.SmsChannelMonitor;
import com.zy.cms.entity.SmsChannelMonitorNoticeSetting;
import com.zy.cms.enums.SmsMonitorTypeEnum;
import com.zy.cms.service.manager.SmsChannelMonitorNoticeSettingService;
import com.zy.cms.service.manager.SmsChannelMonitorService;
import com.zy.cms.util.DateUtil;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.chart.ChartUI;
import com.zy.cms.vo.query.ChartQuery;

@Controller
@Scope("prototype")
@RequestMapping(value = "/channel_monitor")
public class SmsChannelMonitorAction{

	private static final Logger logger = Logger.getLogger(SmsChannelMonitorAction.class);
	
	@Autowired
	private SmsChannelMonitorService smsChannelMonitorService;
	@Autowired
	private SmsChannelMonitorNoticeSettingService smsChannelMonitorNoticeSettingService;
	
	
	/**
     * 分页列表查询
     * @return
     */
	@RequestMapping(value = "/list",method={RequestMethod.POST, RequestMethod.GET})
    public ModelAndView list(SmsChannelMonitor smsChannelMonitor,HttpServletRequest req) {
		
		ModelAndView mv = new ModelAndView("/monitor/channel_monitor_list");
        	try {
        		String preChannelId=smsChannelMonitor.getPreChannelId();
			  	String preChannelName=smsChannelMonitor.getPreChannelName();
			  	String preStartFlag=smsChannelMonitor.getPreStartFlag()+"";
				boolean flag=false;
			  	if(!StringUtil.isEmpty(preChannelId)){
			  		smsChannelMonitor.setChannelId(preChannelId);
			  		flag=true;
			  	}
				boolean f=false;
			  	if(!StringUtil.isEmpty(preChannelName)){
			  		preChannelName=URLDecoder.decode(preChannelName,"utf-8");
			  		smsChannelMonitor.setChannelName(preChannelName);
			  		f=true;
			  	}
			  	if(!StringUtil.isEmpty(preStartFlag)){
			  		smsChannelMonitor.setStartFlag(Integer.parseInt(preStartFlag));
			  		f=true;
			  	}
        		if(f&&!flag){
        			smsChannelMonitor.setChannelId(preChannelId);
        		}
			  	
			    String param=req.getParameter("paramMap");
			    if(StringUtils.isNotEmpty(param)){
			    	param=new String(param.getBytes("ISO-8859-1"), "UTF-8");
			    	param=param.replaceAll("startFlagParam", "\"startFlagParam\"").
			    			replaceAll("channelIdParam", "\"channelIdParam\"").
			    			replaceAll("channelNameParam", "\"channelNameParam\"").
			    			replaceAll("=",":\"").replaceAll(",","\",").replaceAll("}","\"}");
			    	 Map<String,String> paramMap=JsonUtil.parseToObject(param, Map.class);
				     if(paramMap!=null){
				    	String channelNameParam=paramMap.get("channelNameParam");
				    	if(StringUtils.isNotEmpty(channelNameParam)){
				    		smsChannelMonitor.setChannelName(channelNameParam);
				    	}
						String channelIdParam=paramMap.get("channelIdParam");
						if(StringUtils.isNotEmpty(channelIdParam)){
							smsChannelMonitor.setChannelId(channelIdParam);
				    	}
						String startFlagParam=paramMap.get("startFlagParam");
						if(StringUtils.isNotEmpty(startFlagParam)){
							smsChannelMonitor.setStartFlag(Integer.parseInt(startFlagParam));
				    	}
				    }
			    }
			  	
        		List<SmsChannelMonitor> list = smsChannelMonitorService.querySmsChannelMonitorList(smsChannelMonitor);
				int currentPage = 1;
				int total = 0;
				int totalPage = 0;
				int pageSize = 20;
				try {
					if(null!=req.getParameter("cp")){
						currentPage = Integer.parseInt(req.getParameter("cp")); // 当前页
					}
				} catch (Exception e) {
					currentPage=1;
				}

				List<SmsChannelMonitor> showList = null;
				if (null != list && list.size() > 0) {
					total = list.size();
					totalPage = (total % pageSize == 0) ? (total / pageSize)
							: (total / pageSize) + 1;
					if(currentPage>totalPage){
						currentPage=totalPage;
					}
					if(currentPage<=0){
						currentPage=1;
					}
					
					showList = list.subList((currentPage - 1) * pageSize,
							currentPage * pageSize > list.size() ? list.size()
									: currentPage * pageSize);
				}
				
				mv.addObject("totalCount", total);
				mv.addObject("currentPage", currentPage);
				mv.addObject("totalPage", totalPage);
				mv.addObject("pageSize", pageSize);
				mv.addObject("list", showList);
				mv.addObject("smsChannelMonitor", smsChannelMonitor);
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
    		return mv;
    	
    }
    
    
    /**
     * 修改初始化，进入修改页面  
     * @return
     */
    @RequestMapping(value = "/preUpdate")
    public ModelAndView preUpdate(SmsChannelMonitor smsChannelMonitor) {
			// 获取待修改的数据信息
			ModelAndView mv=null;
			try {
				
				String preChannelId=smsChannelMonitor.getPreChannelId();
			  	String preChannelName=smsChannelMonitor.getPreChannelName();
			  	if(!StringUtil.isEmpty(preChannelName)){
			  		preChannelName=URLDecoder.decode(preChannelName,"utf-8");
			  	}
			  	String preStartFlag=smsChannelMonitor.getPreStartFlag()+"";
				smsChannelMonitor = smsChannelMonitorService.findSmsChannelMonitor(smsChannelMonitor);
				mv = new ModelAndView("/monitor/channel_monitor_update");
				mv.addObject("smsChannelMonitor", smsChannelMonitor);
				mv.addObject("preChannelId", preChannelId);
				mv.addObject("preChannelName", preChannelName);
				mv.addObject("preStartFlag", preStartFlag);
			} catch (Exception e) {
				e.printStackTrace();
				return mv;
			}
    		return mv;
    }
    
    /**
     * 修改保存  
     * @return
     * 'flag': 1,'channelId': channelId,'noticeTimeRange1': noticeTimeRange1, 'successRateDown1':successRateDown1,'failtRateUp1':failtRateUp1,'unknownRateUp1':unknownRateUp1,'averageSendUp1':averageSendUp1,'averageReveiveUp1':averageReveiveUp1
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public String update(SmsChannelMonitor smsChannelMonitor) {
    	try {
			if(null!=smsChannelMonitor.getId()){
				//update 
				String flag=smsChannelMonitor.getFlag();
				SmsChannelMonitor smsChannelMonitorDb=smsChannelMonitorService.findSmsChannelMonitorById(smsChannelMonitor);
    			if(null!=smsChannelMonitorDb){
    				if("1".equals(flag)){
    					smsChannelMonitorDb.setNoticeTimeRange1(smsChannelMonitor.getNoticeTimeRange1());
    					smsChannelMonitorDb.setSuccessRateDown1(smsChannelMonitor.getSuccessRateDown1());
    					smsChannelMonitorDb.setFailtRateUp1(smsChannelMonitor.getFailtRateUp1());
    					smsChannelMonitorDb.setUnknownRateUp1(smsChannelMonitor.getUnknownRateUp1());
    					smsChannelMonitorDb.setAverageSendUp1(smsChannelMonitor.getAverageSendUp1());
    					smsChannelMonitorDb.setAverageReveiveUp1(smsChannelMonitor.getAverageReveiveUp1());
    					smsChannelMonitorDb.setSendCount1(smsChannelMonitor.getSendCount1());
    					
    				}else if("2".equals(flag)){
    					smsChannelMonitorDb.setNoticeTimeRange2(smsChannelMonitor.getNoticeTimeRange2());
    					smsChannelMonitorDb.setSuccessRateDown2(smsChannelMonitor.getSuccessRateDown2());
    					smsChannelMonitorDb.setFailtRateUp2(smsChannelMonitor.getFailtRateUp2());
    					smsChannelMonitorDb.setUnknownRateUp2(smsChannelMonitor.getUnknownRateUp2());
    					smsChannelMonitorDb.setAverageSendUp2(smsChannelMonitor.getAverageSendUp2());
    					smsChannelMonitorDb.setAverageReveiveUp2(smsChannelMonitor.getAverageReveiveUp2());
    					smsChannelMonitorDb.setSendCount2(smsChannelMonitor.getSendCount2());
    				}else if("3".equals(flag)){
    					smsChannelMonitorDb.setNoticeTimeRange3(smsChannelMonitor.getNoticeTimeRange3());
    					smsChannelMonitorDb.setSuccessRateDown3(smsChannelMonitor.getSuccessRateDown3());
    					smsChannelMonitorDb.setFailtRateUp3(smsChannelMonitor.getFailtRateUp3());
    					smsChannelMonitorDb.setUnknownRateUp3(smsChannelMonitor.getUnknownRateUp3());
    					smsChannelMonitorDb.setAverageSendUp3(smsChannelMonitor.getAverageSendUp3());
    					smsChannelMonitorDb.setAverageReveiveUp3(smsChannelMonitor.getAverageReveiveUp3());
    					smsChannelMonitorDb.setSendCount3(smsChannelMonitor.getSendCount3());
    				}
    				if(StringUtils.isBlank(smsChannelMonitorDb.getNoticeTimeRange1())&&
    						StringUtils.isBlank(smsChannelMonitorDb.getNoticeTimeRange2())&&
    						StringUtils.isBlank(smsChannelMonitorDb.getNoticeTimeRange3())){
    					smsChannelMonitorDb.setStartFlag(2);
    				}
    				
    				smsChannelMonitorDb.setUpdateTime(new Date());
    				smsChannelMonitorService.delSmsChannelMonitor(smsChannelMonitorDb);
    			}
			}else{
				//insert
				smsChannelMonitor.setStartFlag(2);//开启 1，关闭2
				smsChannelMonitorService.insertSmsChannelMonitor(smsChannelMonitor);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "0";
		}
    	
    	return "1";
    }
    @RequestMapping(value = "/monitorFlagChange")
    @ResponseBody
    public String monitorFlagChange(SmsChannelMonitor smsChannelMonitor) {
    	try {
    		if(null!=smsChannelMonitor.getId()&&null!=smsChannelMonitor.getStartFlag()){
    			//update 
    			smsChannelMonitor.setUpdateTime(new Date());
    			smsChannelMonitorService.monitorFlagChange(smsChannelMonitor);
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    		return "0";
    	}
    	
    	return "1";
    }
    @RequestMapping(value = "/del")
    @ResponseBody
    public String del(SmsChannelMonitor smsChannelMonitor) {
    	try {
    		if(null!=smsChannelMonitor.getId()){
    			String flag=smsChannelMonitor.getFlag();
    			smsChannelMonitor=smsChannelMonitorService.findSmsChannelMonitorById(smsChannelMonitor);
    			if("1".equals(flag)){
    				smsChannelMonitor.setNoticeTimeRange1("");
    				smsChannelMonitor.setSuccessRateDown1(null);
    				smsChannelMonitor.setFailtRateUp1(null);
    				smsChannelMonitor.setUnknownRateUp1(null);
    				smsChannelMonitor.setAverageSendUp1(null);
    				smsChannelMonitor.setAverageReveiveUp1(null);
    				smsChannelMonitor.setSendCount1(null);
    				
    			}else if("2".equals(flag)){
    				smsChannelMonitor.setNoticeTimeRange2("");
    				smsChannelMonitor.setSuccessRateDown2(null);
    				smsChannelMonitor.setFailtRateUp2(null);
    				smsChannelMonitor.setUnknownRateUp2(null);
    				smsChannelMonitor.setAverageSendUp2(null);
    				smsChannelMonitor.setAverageReveiveUp2(null);
    				smsChannelMonitor.setSendCount2(null);
    			}else if("3".equals(flag)){
    				smsChannelMonitor.setNoticeTimeRange3("");
    				smsChannelMonitor.setSuccessRateDown3(null);
    				smsChannelMonitor.setFailtRateUp3(null);
    				smsChannelMonitor.setUnknownRateUp3(null);
    				smsChannelMonitor.setAverageSendUp3(null);
    				smsChannelMonitor.setAverageReveiveUp3(null);
    				smsChannelMonitor.setSendCount3(null);
    			}
    			if(StringUtils.isBlank(smsChannelMonitor.getNoticeTimeRange1())&&
    					StringUtils.isBlank(smsChannelMonitor.getNoticeTimeRange2())&&
    					StringUtils.isBlank(smsChannelMonitor.getNoticeTimeRange3())){
    				smsChannelMonitor.setStartFlag(2);
    			}
    			smsChannelMonitorService.delSmsChannelMonitor(smsChannelMonitor);
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    		return "0";
    	}
    	
    	return "1";
    }
    
    @RequestMapping("/to_setting")
    public ModelAndView toSetting(){
        ModelAndView mv = new ModelAndView("/monitor/channel_monitor_setting");
        try{
        	SmsChannelMonitorNoticeSetting setting = smsChannelMonitorNoticeSettingService.getSetting();
            mv.addObject("setting", setting);
            return mv;
        }catch (Exception e){
            e.printStackTrace();
//            logger.error("【获取通道余额告警设置】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
            return mv;
        }
    }

    @RequestMapping("/save_setting")
    public boolean saveSetting(String noticeType, String noticePhone, String noticeEmail){
        boolean result = false;
        try{
            result = smsChannelMonitorNoticeSettingService.saveSetting(noticeType, noticePhone, noticeEmail);
        }catch (Exception e){
            e.printStackTrace();
//            logger.error("【保存通道余额告警设置】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
        }
        return result;
    }

	@RequestMapping(value = "/main_channel_chartline", produces = "text/html;charset=UTF-8")
	public ModelAndView toMainChannelChartLine(@RequestParam("channelMainCode") String channelMainCode,@RequestParam("param") String param) {
		ModelAndView mv = new ModelAndView("/monitor/main_channel_chartline");
		try {
			mv.addObject("channelMainCode", channelMainCode);
			mv.addObject("monitorTypeList", SmsMonitorTypeEnum.values());
			mv.addObject("currentDate", DateUtil.getDateYMD());// 当前时间
			param=new String(param.getBytes("ISO-8859-1"), "UTF-8");
			Map paramMap=JsonUtil.parseToObject(param, Map.class);
			mv.addObject("paramMap", paramMap);
			
		} catch (Exception e) {
			logger.info("通道跑量查看曲线图出现异常，异常是："+e.getMessage());
		}
		return mv;
	}

	@RequestMapping(value = "/to_channel_chartline", produces = "text/html;charset=UTF-8")
	public ModelAndView toChannelChartLine(@RequestParam("channelId") String channelId, @RequestParam("channelName") String channelName,@RequestParam("param") String param) {
		ModelAndView mv = new ModelAndView("/monitor/channel_chartline");
		mv.addObject("channelId", channelId);
		try{
			mv.addObject("channelName", StringUtils.isBlank(channelName) ? "" : new String(channelName.getBytes("ISO-8859-1"), "UTF-8"));
			param=new String(param.getBytes("ISO-8859-1"), "UTF-8");
		} catch (Exception e){
			e.printStackTrace();
            logger.error("【通道跑量监控折线图】出现错误,原因是:" + e.getMessage());
		}
		mv.addObject("monitorTypeList", SmsMonitorTypeEnum.values());
		mv.addObject("currentDate", DateUtil.getDateYMD());// 当前时间
		Map paramMap=JsonUtil.parseToObject(param, Map.class);
		mv.addObject("paramMap", paramMap);
		return mv;
	}

	/**
	 * 通道跑量-定时加载图表数据
	 *
	 * @param request
	 * @param params
	 *            参数对象
	 * @return
	 */
	@RequestMapping("/channel_monitor_chart")
	@ResponseBody
	public ChartUI queryAccountChartData(HttpServletRequest request, @RequestParam("params") String params) {

		logger.info("【通道跑量监控-折线图】params=" + params);

		ChartQuery chartQuery = null;
		if (!StringUtil.isEmpty(params)) {
			chartQuery = JsonUtil.parseToObject(params, ChartQuery.class);
		}
		if (chartQuery == null) {
			chartQuery = new ChartQuery();
		}
		ChartUI chartUI = null;
		try	{
			chartUI = smsChannelMonitorService.queryMonitorChannelChartLine(chartQuery);
		}catch (Exception e){
			e.printStackTrace();
			logger.error("【通道跑量监控-折线图】出现错误,原因是" + e.getMessage());
		}
		return chartUI;
	}
    
}