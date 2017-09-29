package com.zy.cms.controller.monitor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zy.cms.service.manager.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.zy.cms.common.CommonService;
import com.zy.cms.common.Constant;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.enums.BusinessTypeEnum;
import com.zy.cms.enums.GlobalFlagEnum;
import com.zy.cms.enums.MonitorStatusEnmu;
import com.zy.cms.enums.NoticeFlagEnum;
import com.zy.cms.enums.StartFlagEnum;
import com.zy.cms.service.master.MerchantAccountService;
import com.zy.cms.service.master.VoiceUploadService;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.MerchantAccount;
import com.zy.cms.vo.User;
import com.zy.cms.vo.UserMenu;
import com.zy.cms.vo.manager.CdrMonitorNoticeSetting;
import com.zy.cms.vo.manager.CdrMonitorSetting;
import com.zy.cms.vo.manager.CdrMonitorUser;
import com.zy.cms.vo.query.CdrMonitorUserQuery;
import com.zy.cms.vo.query.ResultQuery;

/**
 * 客户业务监控
 * @author xuyipeng
 *
 */
@Controller
@RequestMapping("/monitor")
public class AccountBizMonitorController {
	
	private static final ZyLogger logger = ZyLogger
			.getLogger(AccountBizMonitorController.class);
	
	@Resource
	private CommonService commonService;
	
	@Autowired
	private CdrMonitorUserService cdrMonitorUserService;
	
	@Resource
	private VoiceUploadService voiceUploadService;

	@Resource
	private MerchantAccountService merchantAccountService;
	
	@Resource
	private CdrMonitorNoticeSettingService cdrMonitorNoticeSettingService;
	
	@Autowired
	private CdrMonitorSettingService cdrMonitorSettingService;
	
	@Autowired
	private UserMenuService userMenuService;

	@Autowired
	private AccountBindInfoService accountBindInfoService;
	
	/**
	 * 跳转到业务监控列表
	 * @return
	 */
	@RequestMapping("/monitor_list")
	public ModelAndView monitorList(HttpServletRequest request, HttpServletResponse response){
		User user = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
		ModelAndView mv = new ModelAndView("/monitor/monitor_list");
		logger.info("跳转到业务监控列表");
		//是否有设置监控权限
		UserMenu userMenu = userMenuService.getByUserName(user.getUserName());
		String menus = userMenu.getMenus();
		if(StringUtils.isNotBlank(menus) && menus.contains(Constant.ACCOUNT_SETTING_MENUID)){
			mv.addObject("permission", true);
		}else{
			mv.addObject("permission", false);
		}
		return mv;
	}
	
	
	/**
	 * 加载业务监控列表数据
	 * @param request
	 * @param response
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/monitor_list_data", produces = "application/json")
	public ModelAndView queryAuditListData(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("params") String params)  {
		User user = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
		ModelAndView mv = new ModelAndView("/monitor/monitor_list_data");
		try {
			CdrMonitorUserQuery query = null;
			logger.info("【 客户业务监控列表】参数={0}", new Object[] { params }, null);
			if (!StringUtil.isEmpty(params)) {
				query = JsonUtil.parseToObject(params, CdrMonitorUserQuery.class);
			}
			if (query == null) {
				query = new CdrMonitorUserQuery();
			}

			Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query
					.getPageNum() - 1);
			Integer pageSize = query.getPageSize() == 0 ? Constant.PAGE_SIZE_20
					: query.getPageSize();
			query.setPageNum(pageNum);
			query.setPageSize(pageSize);

			List<String> apiAccounts = accountBindInfoService.getApiAccountByBusName(user.getUserName());
			query.setApiAccounts(apiAccounts);

			Integer total = cdrMonitorUserService.queryMonitorUserCountByEntity(query);
			List<CdrMonitorUser> list = cdrMonitorUserService.queryMonitorUserByEntity(query);
			
			// 构建查询结果对象
			@SuppressWarnings("rawtypes")
			ResultQuery pgdata = new ResultQuery();
			pgdata.setPage_num(Long.valueOf(query.getPageNum() + 1));
			pgdata.setPage_size(Long.valueOf(query.getPageSize()));
			pgdata.setTotal(Long.valueOf(total));
			pgdata.setData(list);
			//是否有设置监控权限
			UserMenu userMenu = userMenuService.getByUserName(user.getUserName());
			String menus = userMenu.getMenus();
			if(StringUtils.isNotBlank(menus) && menus.contains(Constant.ACCOUNT_SETTING_MENUID)){
				mv.addObject("permission", true);
			}else{
				mv.addObject("permission", false);
			}
			// 设定查询参数对象['pgdata'为JSP参数,不随意修改]
			mv.addObject("pgdata", pgdata);
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【获取(待)审核列表】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
			return mv;
		}
	}
	
	/**
	 * 跳转到添加业务监控页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/monitor_user_add")
    public ModelAndView addAccountMonitor(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = new ModelAndView("/monitor/monitor_user_add");
		logger.info("跳转到客户信息业务监控");
		return mv;
    }
	
	/**
	 * 将客户加入到业务监控表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/save_accountmonitor", produces = "text/plain;charset=UTF-8")
	@ResponseBody
    public String saveAccountMonitor(HttpServletRequest request,HttpServletResponse response){
		logger.info("保存客户信息业务监控");
		String result=null;
		try {
			String apiAccount=request.getParameter("apiAccount");
			String businessName=request.getParameter("businessName");
			String merchantPhone=request.getParameter("merchantPhone");
			CdrMonitorUser cdrMonitorUser=new CdrMonitorUser();
			cdrMonitorUser.setApiAccount(apiAccount);
			cdrMonitorUser.setCreateTime(new Date());
			cdrMonitorUser.setBusinessName(businessName);
			cdrMonitorUser.setMerchantPhone(merchantPhone);
			cdrMonitorUser.setMonitorStatus(MonitorStatusEnmu.STOP.getType());
			cdrMonitorUser.setNoticeTimes(0);
			cdrMonitorUserService.insert(cdrMonitorUser);
			result = JsonUtil.wrapjson(Constant.SUCCESS + "", cdrMonitorUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
    }
	
	/**
	 * 跳转到通知设置界面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/notification_setting")
	public ModelAndView notificationSetting(HttpServletRequest request,HttpServletResponse response) throws Exception{
		ModelAndView mv = new ModelAndView("/monitor/monitor_notification_setting");
		try {
			User user = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
			String id=request.getParameter("id");
			CdrMonitorUser cdrMonitorUser=cdrMonitorUserService.selectByPrimaryKey(Integer.parseInt(id));
			String apiAccount=cdrMonitorUser.getApiAccount();
			mv.addObject("apiAccount", apiAccount);
			CdrMonitorNoticeSetting cdrMonitorNoticeSetting=cdrMonitorNoticeSettingService.selectByApiaccount(apiAccount);
			if(cdrMonitorNoticeSetting!=null){
				mv.addObject("cdrMonitorNoticeSetting", cdrMonitorNoticeSetting);
			}
			//是否有设置监控权限
			UserMenu userMenu = userMenuService.getByUserName(user.getUserName());
			String menus = userMenu.getMenus();
			if(StringUtils.isNotBlank(menus) && menus.contains(Constant.ACCOUNT_SETTING_MENUID)){
				mv.addObject("permission", true);
			}else{
				mv.addObject("permission", false);
			}
			logger.info("跳转到通知设置");
		} catch (Exception e) {
			logger.error("跳转到通知设置出现异常,异常是:"+e.getMessage());
			e.printStackTrace();
		}
		return mv;
	}
	
	/**
	 * 保存通知设置
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/save_notifiy_setting", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String saveNotifySetting(HttpServletRequest request,HttpServletResponse response,CdrMonitorNoticeSetting cdrMonitorNoticeSetting) throws Exception{
		    ModelAndView mv = new ModelAndView("/monitor/monitor_notification_setting");
		    String result="";
		try {
			logger.info("需要保存通知设置的参数为:"+JsonUtil.objectToJson(cdrMonitorNoticeSetting));
			if(cdrMonitorNoticeSetting==null){
				logger.warn("通知设置参数为空，请重新输入");
				mv.addObject("msg", "通知设置参数为空，请重新输入");
				return result;
			}
            if(StringUtil.isEmpty(cdrMonitorNoticeSetting.getApiAccount())){
            	logger.warn("通知设置apiAccount为空，请重新输入");
            	mv.addObject("msg", "通知设置apiAccount为空，请重新输入");
				return result;
            }
            CdrMonitorNoticeSetting cmns=cdrMonitorNoticeSettingService.selectByApiaccount(cdrMonitorNoticeSetting.getApiAccount());
			if(cmns!=null){
				logger.info("更新客户设置的信息");
				cmns.setNoticeEmail1(cdrMonitorNoticeSetting.getNoticeEmail1());
				cmns.setNoticeWay1(cdrMonitorNoticeSetting.getNoticeWay1());
				cmns.setNoticePhone1(cdrMonitorNoticeSetting.getNoticePhone1());
				cmns.setUpdateTime(new Date());
				cdrMonitorNoticeSettingService.updateByPrimaryKey(cmns);
			}else{
				logger.info("保存通知设置信息");
	            cdrMonitorNoticeSetting.setCreateTime(new Date());
				cdrMonitorNoticeSetting.setStartFlag(StartFlagEnum.ENABLE.getType());
				cdrMonitorNoticeSetting.setNoticeFlag(NoticeFlagEnum.NORMAL.getType());
				cdrMonitorNoticeSetting.setGlobalFlag(GlobalFlagEnum.DEFAULT.getType());
				cdrMonitorNoticeSettingService.insert(cdrMonitorNoticeSetting);
			}
			mv.addObject("msg", "恭喜您设置成功了");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("保存通知设置出现异常,异常是:"+e.getMessage());
			mv.addObject("msg", "通知设置出现异常");
		}
		result = JsonUtil.wrapjson(Constant.SUCCESS + "", cdrMonitorNoticeSetting);
		return result;
	}
	

	/**
	 * 删除监控人物
	 * @return
	 */
	@RequestMapping(value = "/delete_monitor")
	public ModelAndView deleteMonitor(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = new ModelAndView("/monitor/monitor_list");
		try {
			User user = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
			String id=request.getParameter("id");
			cdrMonitorUserService.deleteByPrimaryKey(Integer.parseInt(id));
			logger.info("删除监控");
			//是否有设置监控权限
			UserMenu userMenu = userMenuService.getByUserName(user.getUserName());
			String menus = userMenu.getMenus();
			if(StringUtils.isNotBlank(menus) && menus.contains(Constant.ACCOUNT_SETTING_MENUID)){
				mv.addObject("permission", true);
			}else{
				mv.addObject("permission", false);
			}
		} catch (Exception e) {
			logger.error("删除监控出现异常,异常是:"+e.getMessage());
			e.printStackTrace();
		}
		return mv;
	}
	
	/**
	 * 开启监控
	 * @return
	 */
	@RequestMapping(value = "/start_monitor")
	public ModelAndView startMonitor(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = new ModelAndView("/monitor/monitor_list");
		try {
			User user = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
			String id=request.getParameter("id");
			CdrMonitorUser cdrMonitorUser=cdrMonitorUserService.selectByPrimaryKey(Integer.parseInt(id));
			cdrMonitorUser.setMonitorStatus(1);
			cdrMonitorUserService.updateByPrimaryKey(cdrMonitorUser);
			//是否有设置监控权限
			UserMenu userMenu = userMenuService.getByUserName(user.getUserName());
			String menus = userMenu.getMenus();
			if(StringUtils.isNotBlank(menus) && menus.contains(Constant.ACCOUNT_SETTING_MENUID)){
				mv.addObject("permission", true);
			}else{
				mv.addObject("permission", false);
			}
			logger.info("开启监控");
		} catch (Exception e) {
			logger.error("开启监控出现异常,异常是:"+e.getMessage());
			e.printStackTrace();
		}
		return mv;
	}
	

	/**
	 * 暂停监控
	 * @return
	 */
	@RequestMapping(value = "/stop_monitor")
	public ModelAndView stopMonitor(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = new ModelAndView("/monitor/monitor_list");
		try {
			User user = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
			String id=request.getParameter("id");
			CdrMonitorUser cdrMonitorUser=cdrMonitorUserService.selectByPrimaryKey(Integer.parseInt(id));
			cdrMonitorUser.setMonitorStatus(0);
			cdrMonitorUserService.updateByPrimaryKey(cdrMonitorUser);
			//是否有设置监控权限
			UserMenu userMenu = userMenuService.getByUserName(user.getUserName());
			String menus = userMenu.getMenus();
			if(StringUtils.isNotBlank(menus) && menus.contains(Constant.ACCOUNT_SETTING_MENUID)){
				mv.addObject("permission", true);
			}else{
				mv.addObject("permission", false);
			}
			logger.info("暂停监控");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("暂停监控出现异常,异常是:"+e.getMessage());
		}
		return mv;
	}
	
	/**
	 * 根据手机号码获取账号信息
	 * @return
	 */
	@RequestMapping(value = "/get_merchantaccounts", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getMerchantAccount(HttpServletRequest request,HttpServletResponse response) {
		String result = "";
        try {
			String merchantPhone=request.getParameter("merchantPhone");
			logger.info("【根据手机号码获取账号信息】手机号码为:"+merchantPhone);
			MerchantAccount merchantAccount=null;
			if(!StringUtil.isEmpty(merchantPhone)){
				merchantAccount= merchantAccountService.getMerchantAccountByPhone(merchantPhone.trim());
			}
			if (null == merchantAccount) {
				result = JsonUtil.wrapjson(Constant.WARN + "", Constant.NO_FIND_ACCOUNT_INFO_DESC);
				return result;
			}
			CdrMonitorUser cdrMonitorUser=cdrMonitorUserService.findMonitorUserByAccount(merchantAccount.getApiAccount());
			if(cdrMonitorUser!=null){
				result = JsonUtil.wrapjson(Constant.WARN + "", Constant.CDR_USER_MONITOR_ACCOUNT_INFO_DESC);
				return result;
			}
			result = JsonUtil.wrapjson(Constant.SUCCESS + "", merchantAccount);
			
			return result;
		} catch (Exception e) {
			logger.info("【根据手机号码获取账号信息】出现异常,异常为:"+e.getMessage());
			result = JsonUtil.wrapjson(Constant.ERROR + "", Constant.OTHER_ERROR_DESC);
			e.printStackTrace();
		}
        return result;
	}
	
	/**
	 * 跳转到指标设置页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/index_setting")
	public ModelAndView indexSetting(HttpServletRequest request,HttpServletResponse response) throws Exception{
		ModelAndView mv = new ModelAndView("/monitor/monitor_index_setting");
		logger.info("跳转到指标设置");
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=UTF-8");//防止数据传递乱码
			String businessName = new String(request.getParameter("businessName").getBytes("iso-8859-1"), "utf-8");
			String merchantPhone=request.getParameter("merchantPhone");
			String apiAccount=request.getParameter("apiAccount");
			List<CdrMonitorSetting> settings = cdrMonitorSettingService.getCdrAccountMonitorSetting(StartFlagEnum.ENABLE.getType(),GlobalFlagEnum.DEFAULT.getType(),apiAccount);
			List<CdrMonitorSetting> noticeSettings = new ArrayList<CdrMonitorSetting>();
			List<CdrMonitorSetting> codeSettings = new ArrayList<CdrMonitorSetting>();;
			List<CdrMonitorSetting> callPhoneSettings = new ArrayList<CdrMonitorSetting>();;
			List<CdrMonitorSetting> backPhoneSettings = new ArrayList<CdrMonitorSetting>();;
			List<CdrMonitorSetting> guardSettings = new ArrayList<CdrMonitorSetting>();
			
			List<CdrMonitorSetting> smsNoticeSettings = new ArrayList<CdrMonitorSetting>();
			List<CdrMonitorSetting> smsCodeSettings = new ArrayList<CdrMonitorSetting>();
			List<CdrMonitorSetting> smsSellSettings = new ArrayList<CdrMonitorSetting>();
			if(settings.size()>0){
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
					}else if(setting.getBusinessId() == BusinessTypeEnum.BT_8.getType()){
						smsNoticeSettings.add(setting);
					}else if(setting.getBusinessId() == BusinessTypeEnum.BT_9.getType()){
						smsCodeSettings.add(setting);
					}else if(setting.getBusinessId() == BusinessTypeEnum.BT_11.getType()){
						smsSellSettings.add(setting);
					}
				}
			}
			mv.addObject("noticeSettings", noticeSettings);
			mv.addObject("codeSettings", codeSettings);
			mv.addObject("callPhoneSettings", callPhoneSettings);
			mv.addObject("backPhoneSettings", backPhoneSettings);
			mv.addObject("guardSettings", guardSettings);
			
			mv.addObject("smsNoticeSettings", smsNoticeSettings);
			mv.addObject("smsCodeSettings", smsCodeSettings);
			mv.addObject("smsSellSettings", smsSellSettings);
			
			mv.addObject("businessName", businessName);
			mv.addObject("merchantPhone", merchantPhone);
			mv.addObject("apiAccount",apiAccount);
		} catch (Exception e) {
			logger.error("跳转到指标设置出现异常，异常是:"+e.getMessage());
			e.printStackTrace();
		}
		return mv;
	}
	
	/**
	 * 保存指标设置信息
	 * @return
	 */
	@RequestMapping("/save_index_setting")
	@ResponseBody
	public Integer saveMonitorSetting(String apiAccount,String measureTime, Integer callCountaUp, Integer callCountbUp, Integer callCountaDown, Integer callCountbDown, 
			String successRateaDown, String successRatebDown, String responseRateaDown, String responseRatebDown, Integer averageTalkTimeaDown, 
			Integer averageTalkTimebDown, Integer averageTurnOnDelayaUp, Integer averageTurnOnDelaybUp, Integer averageInTimeaUp,  Integer averageInTimebUp,
			Integer bizId){
		Integer id=null;
		try {
			id = cdrMonitorSettingService.saveMonitorSetting(apiAccount,measureTime, callCountaUp, callCountbUp, callCountaDown, callCountbDown, successRateaDown, 
					successRatebDown, responseRateaDown, responseRatebDown, averageTalkTimeaDown, averageTalkTimebDown, averageTurnOnDelayaUp, averageTurnOnDelaybUp,
					averageInTimeaUp, averageInTimebUp, bizId, StartFlagEnum.ENABLE.getType(), GlobalFlagEnum.DEFAULT.getType());
		} catch (Exception e) {
			logger.error("[客户业务监控]保存指标设置信息出现异常:"+e.getMessage());
			e.printStackTrace();
		}
		return id;
	}
	
	/**
	 * 编辑指标设置信息
	 * @return
	 */
	@RequestMapping("/edit_index_setting")
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
			logger.error("[客户业务监控]编辑指标设置信息出现异常:"+e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
}
