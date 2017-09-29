package com.zy.cms.controller.monitor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zy.cms.common.ZyLogger;
import com.zy.cms.enums.BusinessTypeEnum;
import com.zy.cms.enums.GlobalFlagEnum;
import com.zy.cms.enums.NoticeFlagEnum;
import com.zy.cms.enums.StartFlagEnum;
import com.zy.cms.service.manager.CdrMonitorNoticeSettingService;
import com.zy.cms.service.manager.CdrMonitorSettingService;
import com.zy.cms.util.DateUtil;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.vo.manager.CdrMonitorNoticeSetting;
import com.zy.cms.vo.manager.CdrMonitorSetting;

@Controller
@RequestMapping("/monitor")
public class GlobalBizMonitorAction {
	
	private static final ZyLogger logger = ZyLogger.getLogger(GlobalBizMonitorAction.class);
	
	@Autowired
	private CdrMonitorSettingService cdrMonitorSettingService;
	
	@Autowired
	private CdrMonitorNoticeSettingService cdrMonitorNoticeSettingService;
	
	@RequestMapping("/show_chart")
	public ModelAndView showChart(){
		ModelAndView mv = new ModelAndView("/monitor/show_chart");
		
		return mv;
	}
	
	@RequestMapping("/to_alarm_setting")
	public ModelAndView toAlarmSetting(){
		ModelAndView mv = new ModelAndView("/monitor/alarm_setting");
		List<CdrMonitorSetting> settings = cdrMonitorSettingService.getCdrMonitorSetting(StartFlagEnum.ENABLE.getType(), GlobalFlagEnum.GLOBAL.getType());
		List<CdrMonitorSetting> noticeSettings = new ArrayList<CdrMonitorSetting>();
		List<CdrMonitorSetting> codeSettings = new ArrayList<CdrMonitorSetting>();
		List<CdrMonitorSetting> callPhoneSettings = new ArrayList<CdrMonitorSetting>();
		List<CdrMonitorSetting> backPhoneSettings = new ArrayList<CdrMonitorSetting>();
		List<CdrMonitorSetting> guardSettings = new ArrayList<CdrMonitorSetting>();
		List<CdrMonitorSetting> smsSettings = new ArrayList<CdrMonitorSetting>();
		
		List<CdrMonitorSetting> smsNoticeSettings = new ArrayList<CdrMonitorSetting>();
		List<CdrMonitorSetting> smsCodeSettings = new ArrayList<CdrMonitorSetting>();
		List<CdrMonitorSetting> smsSellSettings = new ArrayList<CdrMonitorSetting>();
		
		for(CdrMonitorSetting setting : settings){
			if(setting.getBusinessId() == BusinessTypeEnum.BT_4.getType()){
				noticeSettings.add(setting);
			}else if(setting.getBusinessId() == BusinessTypeEnum.BT_5.getType()){
				codeSettings.add(setting);
			}else if(setting.getBusinessId() == BusinessTypeEnum.BT_3.getType()){
				callPhoneSettings.add(setting);
			}else if(setting.getBusinessId() == BusinessTypeEnum.BT_1.getType()){
				backPhoneSettings.add(setting);
			}else if(setting.getBusinessId() == BusinessTypeEnum.BT_2.getType()){
				guardSettings.add(setting);
			}else if(setting.getBusinessId() == BusinessTypeEnum.BT_80.getType()){
				smsSettings.add(setting);
			}else if(setting.getBusinessId() == BusinessTypeEnum.BT_8.getType()){
				smsNoticeSettings.add(setting);
			}else if(setting.getBusinessId() == BusinessTypeEnum.BT_9.getType()){
				smsCodeSettings.add(setting);
			}else if(setting.getBusinessId() == BusinessTypeEnum.BT_11.getType()){
				smsSellSettings.add(setting);
			}
		}
		mv.addObject("noticeSettings", noticeSettings);
		mv.addObject("codeSettings", codeSettings);
		mv.addObject("callPhoneSettings", callPhoneSettings);
		mv.addObject("backPhoneSettings", backPhoneSettings);
		mv.addObject("guardSettings", guardSettings);
		mv.addObject("smsSettings", smsSettings);
		
		mv.addObject("smsNoticeSettings", smsNoticeSettings);
		mv.addObject("smsCodeSettings", smsCodeSettings);
		mv.addObject("smsSellSettings", smsSellSettings);
		
		
		return mv;
	}
	
	@RequestMapping("/save_monitor_setting")
	@ResponseBody
	public Integer saveMonitorSetting(String measureTime, Integer callCountaUp, Integer callCountbUp, Integer callCountaDown, Integer callCountbDown, 
			String successRateaDown, String successRatebDown, String responseRateaDown, String responseRatebDown, Integer averageTalkTimeaDown, 
			Integer averageTalkTimebDown, Integer averageTurnOnDelayaUp, Integer averageTurnOnDelaybUp, Integer averageInTimeaUp,  Integer averageInTimebUp,
			Integer bizId){
//		boolean result = false;
		Integer id=null;
		try {
			id = cdrMonitorSettingService.saveMonitorSetting("0",measureTime, callCountaUp, callCountbUp, callCountaDown, callCountbDown, successRateaDown, 
					successRatebDown, responseRateaDown, responseRatebDown, averageTalkTimeaDown, averageTalkTimebDown, averageTurnOnDelayaUp, averageTurnOnDelaybUp,
					averageInTimeaUp, averageInTimebUp, bizId, StartFlagEnum.ENABLE.getType(), GlobalFlagEnum.GLOBAL.getType());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
	
	@RequestMapping("/edit_monitor_setting")
	@ResponseBody
	public boolean editMonitorSetting(Integer id, String measureTime, Integer callCountaUp, Integer callCountbUp, Integer callCountaDown, 
			Integer callCountbDown, String successRateaDown, String successRatebDown, String responseRateaDown, String responseRatebDown, 
			Integer averageTalkTimeaDown, Integer averageTalkTimebDown, Integer averageTurnOnDelayaUp, Integer averageTurnOnDelaybUp, 
			Integer averageInTimeaUp, Integer averageInTimebUp){
		boolean result = false;
		try {
			result = cdrMonitorSettingService.editMonitorSetting(id, measureTime, callCountaUp, callCountbUp, callCountaDown, callCountbDown, successRateaDown, 
					successRatebDown, responseRateaDown, responseRatebDown, averageTalkTimeaDown, averageTalkTimebDown, averageTurnOnDelayaUp, averageTurnOnDelaybUp,
					averageInTimeaUp, averageInTimebUp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("/global_notice_setting")
	public ModelAndView toGlobalSetting(){
		CdrMonitorNoticeSetting cdrMonitorNoticeSetting = cdrMonitorNoticeSettingService.getGlobalNoticeSetting();
		ModelAndView mv = new ModelAndView("/monitor/global_notice_setting");
		mv.addObject("cdrMonitorNoticeSetting", cdrMonitorNoticeSetting);
		return mv;
	}
	
	/**
	 * 保存通知设置
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save_global_setting")
	@ResponseBody
	public boolean saveNotifySetting(HttpServletRequest request,HttpServletResponse response,CdrMonitorNoticeSetting cdrMonitorNoticeSetting) throws Exception{
		boolean result = false;
		ModelAndView mv = new ModelAndView("/monitor/global_notice_setting");
		try {
			logger.info("需要保存通知设置的参数为:"+JsonUtil.objectToJson(cdrMonitorNoticeSetting));
			if(cdrMonitorNoticeSetting==null){
				logger.warn("通知设置参数为空，请重新输入");
				mv.addObject("msg", "通知设置参数为空，请重新输入");
			}
            CdrMonitorNoticeSetting cmns=cdrMonitorNoticeSettingService.getGlobalNoticeSetting();
			if(cmns!=null){
				logger.info("更新客户设置的信息");
				cmns.setNoticeEmail1(cdrMonitorNoticeSetting.getNoticeEmail1());
				cmns.setNoticeWay1(cdrMonitorNoticeSetting.getNoticeWay1());
				cmns.setNoticePhone1(cdrMonitorNoticeSetting.getNoticePhone1());
				cmns.setUpdateTime(new Date());
				result = cdrMonitorNoticeSettingService.updateByPrimaryKey(cmns) > 0;
			}else{
				logger.info("保存通知设置信息");
				cdrMonitorNoticeSetting.setApiAccount("0");
	            cdrMonitorNoticeSetting.setCreateTime(new Date());
				cdrMonitorNoticeSetting.setStartFlag(StartFlagEnum.ENABLE.getType());
				cdrMonitorNoticeSetting.setNoticeFlag(NoticeFlagEnum.NORMAL.getType());
				cdrMonitorNoticeSetting.setGlobalFlag(GlobalFlagEnum.GLOBAL.getType());
				result = cdrMonitorNoticeSettingService.insert(cdrMonitorNoticeSetting) > 0;
			}
			mv.addObject("msg", "恭喜您设置成功了");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("保存通知设置出现异常,异常是:"+e.getMessage());
			mv.addObject("msg", "通知设置出现异常");
		}
		return result;
	}
	
	/**
	 * 判断本次监控时段开始时间必须大于上次监控时段结束时间
	 * @param measureTime
	 * @param lastMeasureTime
	 * @return
	 */
	@RequestMapping("/measureTimeJudge")
	@ResponseBody
	public boolean measureTimeJudge(String measureTime, String lastMeasureTime){
		boolean result = false;
		String measureTimeArr[] = measureTime.split("~");
		String measureBeginTime = measureTimeArr[0];
		String lastMeasureTimeArr[] = lastMeasureTime.split("~");
		String lastMeasureEndTime = lastMeasureTimeArr[1];
		long measureTimeStamp = DateUtil.parseDate(measureBeginTime, DateUtil.DATE_HOUR_MM_FORMAT1).getTime();
		long lastMeasureTimeStamp = DateUtil.parseDate(lastMeasureEndTime, DateUtil.DATE_HOUR_MM_FORMAT1).getTime();
		if(lastMeasureTimeStamp > measureTimeStamp){
			return true;
		}
		return result;
	}
	
	@RequestMapping("/monitor_setting_delete")
	@ResponseBody
	public boolean monitorSettingDelete(Integer id){
		boolean result = false;
		result = cdrMonitorSettingService.deteleMonitorSetting(id);
		return result;
	}
	
	
}
