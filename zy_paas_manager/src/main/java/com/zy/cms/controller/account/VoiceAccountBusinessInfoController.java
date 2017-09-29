package com.zy.cms.controller.account;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.zy.cms.common.CommonService;
import com.zy.cms.common.Constant;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.service.manager.AccountBindInfoService;
import com.zy.cms.service.manager.UserMenuService;
import com.zy.cms.service.master.MerchantAccountService;
import com.zy.cms.service.master.VoiceAccountBusinessInfoService;
import com.zy.cms.util.CookieUtil;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.MerchantAccount;
import com.zy.cms.vo.User;
import com.zy.cms.vo.UserCurrent;
import com.zy.cms.vo.UserInfo;
import com.zy.cms.vo.UserMenu;
import com.zy.cms.vo.VoiceAccountBusinessInfo;
import com.zy.cms.vo.query.ResultQuery;
import com.zy.cms.vo.query.VoiceAccountBusinessInfoQuery;

/**
 * paas平台统计分析控制器
 * 
 * @author allen.yuan
 * @date 2016-9-6
 * 
 */
@Controller
@RequestMapping(value = "/voiceAccountBusinessInfo")
public class VoiceAccountBusinessInfoController {

	private static final ZyLogger logger = ZyLogger.getLogger(VoiceAccountBusinessInfoController.class);
	
	@Resource
	private CommonService commonService;
	
	@Resource
	private UserInfo userInfo;

	@Resource
	private MerchantAccountService merchantAccountService;
	
	@Autowired
	private VoiceAccountBusinessInfoService voiceAccountBusinessInfoService;
	
	@Autowired
	private UserMenuService userMenuService;
	
	@Autowired
	private AccountBindInfoService accountBindInfoService;

	/**
	 * 跳转到资费查询列表
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/voice_account_businessInfo_list", method = RequestMethod.GET)
	public ModelAndView app(HttpServletRequest req) {

		ModelAndView mv = new ModelAndView("/account/voice_account_businessInfo_list");
		User us = commonService.valideUser(req, Constant.USER_SESSION_UNAME);
		if (us == null) {
			logger.warn("用户登录超时!");
			return new ModelAndView("redirect:/login.html");
		}
		this.userInfo.setUserAccountInfo(mv, req);

		/* 下面表示后台左右菜单栏，当前的选择状态为管理中心 */
		UserCurrent current = new UserCurrent();
		current.setDataAnalysis(Constant.TRUE_STR);
		mv.addObject("current", current);
		
		return mv;
	}

	/**
	 * 加载资费列表中的数据
	 * @param request
	 * @param response
	 * @param params
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/voice_account_businessInfo_list_data", produces = "application/json")
	public ModelAndView getVoiceNoticeList(HttpServletRequest request, HttpServletResponse response, 
		   @RequestParam("params") String params) {
		User us = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
		ModelAndView mv = new ModelAndView("/account/voice_account_businessInfo_list_data");
		try {
			if (us == null) {
				logger.warn("用户登录超时!");
				return new ModelAndView("redirect:/login.html");
			}
			
			VoiceAccountBusinessInfoQuery query = null;
			if (!StringUtil.isEmpty(params)) {
				query = JsonUtil.parseToObject(params, VoiceAccountBusinessInfoQuery.class);
				if(!StringUtil.isEmpty(query.getMerchantPhone())){
					MerchantAccount merchantAccount=merchantAccountService.getMerchantAccountByPhone(query.getMerchantPhone());
					if(merchantAccount==null){
						return mv;
					}
					query.setApiAccount(merchantAccount.getApiAccount());
				}
			}
			Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query.getPageNum() - 1);
			Integer pageSize = query.getPageSize() == 0 ? Constant.PAGE_SIZE_20 : query.getPageSize();
			query.setPageNum(pageNum);
			query.setPageSize(pageSize);

			logger.info("【资费列表】查询条件是："+JsonUtil.toJsonString(query));
			
			List<String> apiAccounts=accountBindInfoService.getApiAccountByBusName(us.getUserName());
			query.setApiAccounts(apiAccounts);
			int totalCount = voiceAccountBusinessInfoService.queryVoiceAccountBusinessInfoCountByEntity(query);
			List<VoiceAccountBusinessInfo> list = voiceAccountBusinessInfoService.queryVoiceAccountBusinessInfoByEntity(query);
			if(list.size()>0){
				for(VoiceAccountBusinessInfo vabi:list){
					vabi.setCallBacks(calcMul3(vabi.getCallBack(),vabi.getCallBackRule2()));//1回拨电话
					vabi.setNumberGuards(calcMul3(vabi.getNumberGuard(),vabi.getNumberGuardRule2()));//2号码卫士
					vabi.setDirectDialTelephones(calcMul3(vabi.getDirectDialTelephone(),vabi.getDirectDialTelephoneRule2()));//3直拨电话
					vabi.setVoiceNotifications(calcMul3(vabi.getVoiceNotification(),vabi.getVoiceNotificationRule2()));//4语音通知
					vabi.setVoiceVerificationCodes(calcMul3(vabi.getVoiceVerificationCode(),vabi.getVoiceVerificationCodeRule2()));//5语音验证码
					vabi.setCallCenters(calcMul3(vabi.getCallCenter(),vabi.getCallCenterRule2()));//6呼叫中心
					vabi.setMultiTalks(calcMul3(vabi.getMultiTalk(),vabi.getMultiTalkRule2())); //7多方通话
					vabi.setSmsNotifications(calcMul2(vabi.getSmsNotification()));//8短信验通知
					vabi.setSmsVeriicationCodes(calcMul2(vabi.getSmsVeriicationCode())); //9短信验证码
					vabi.setSoundRecordings(calcMul2(vabi.getSoundRecording()));//10录音
					vabi.setSmsMarkets(calcMul2(vabi.getSmsMarket()));// 11短信营销
				}
			}

			// 构建查询结果对象
			ResultQuery pgdata = new ResultQuery();
			pgdata.setPage_num(Long.valueOf(query.getPageNum() + 1));
			pgdata.setPage_size(Long.valueOf(query.getPageSize()));
			pgdata.setTotal(Long.valueOf(totalCount));
			pgdata.setData(list);
			
			//账号是否有修改权限
			UserMenu userMenu = userMenuService.getByUserName(us.getUserName());
			String menus = userMenu.getMenus();
			if(StringUtils.isNotBlank(menus) && menus.contains(Constant.MONEY_UPDATE_MENUID)){
				mv.addObject("permission", true);
			}else{
				mv.addObject("permission", false);
			}
			// 设定查询参数对象['pgdata'为JSP参数,不随意修改]
			mv.addObject("pgdata", pgdata);
			return mv;
		}
		catch(Exception e){
			e.printStackTrace();
			logger.error("【资费列表】出现异常的原因是："+e.getMessage());
			return mv;
		}
	}
	
	/**
	 * 跳转到修改页面
	 * @param voiceAccountBusinessInfoQuery
	 * @param req
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/preUpdate")
	public ModelAndView preUpdate(VoiceAccountBusinessInfoQuery voiceAccountBusinessInfoQuery,HttpServletRequest req,
			Model model) throws Exception{
		ModelAndView mv = new ModelAndView("/account/voice_account_businessInfo_detail");
		try {
			logger.info("【资费查询跳转到修改页面】apiAccount={0}", new Object[] { voiceAccountBusinessInfoQuery.getApiAccount()}, null);
			VoiceAccountBusinessInfo voiceAccountBusinessInfo=voiceAccountBusinessInfoService.findVoiceAccountBusinessInfo(voiceAccountBusinessInfoQuery);
			if(voiceAccountBusinessInfo!=null){
				voiceAccountBusinessInfo.setCallBacks(calcMul(voiceAccountBusinessInfo.getCallBack(),voiceAccountBusinessInfo.getCallBackRule2()));//1回拨电话
				voiceAccountBusinessInfo.setNumberGuards(calcMul(voiceAccountBusinessInfo.getNumberGuard(),voiceAccountBusinessInfo.getNumberGuardRule2()));//2号码卫士
				voiceAccountBusinessInfo.setDirectDialTelephones(calcMul(voiceAccountBusinessInfo.getDirectDialTelephone(),voiceAccountBusinessInfo.getDirectDialTelephoneRule2()));//3直拨电话
				voiceAccountBusinessInfo.setVoiceNotifications(calcMul(voiceAccountBusinessInfo.getVoiceNotification(),voiceAccountBusinessInfo.getVoiceNotificationRule2()));//4语音通知
				voiceAccountBusinessInfo.setVoiceVerificationCodes(calcMul(voiceAccountBusinessInfo.getVoiceVerificationCode(),voiceAccountBusinessInfo.getVoiceVerificationCodeRule2()));//5语音验证码
				voiceAccountBusinessInfo.setCallCenters(calcMul(voiceAccountBusinessInfo.getCallCenter(),voiceAccountBusinessInfo.getCallCenterRule2()));//6呼叫中心
				voiceAccountBusinessInfo.setMultiTalks(calcMul(voiceAccountBusinessInfo.getMultiTalk(),voiceAccountBusinessInfo.getMultiTalkRule2())); //7多方通话
				voiceAccountBusinessInfo.setSmsNotifications(calcMul2(voiceAccountBusinessInfo.getSmsNotification()));//8短信验通知
				voiceAccountBusinessInfo.setSmsVeriicationCodes(calcMul2(voiceAccountBusinessInfo.getSmsVeriicationCode())); //9短信验证码
				voiceAccountBusinessInfo.setSoundRecordings(calcMul2(voiceAccountBusinessInfo.getSoundRecording()));//10录音
				voiceAccountBusinessInfo.setSmsMarkets(calcMul2(voiceAccountBusinessInfo.getSmsMarket()));//11 短信营销
			}
			VoiceAccountBusinessInfo voiceAccountBusinessInfoDefault=voiceAccountBusinessInfoService.findVoiceBusinessInfo();
			mv.addObject("voiceAccountBusinessInfo", voiceAccountBusinessInfo);
			mv.addObject("voiceAccountBusinessInfoDefault",voiceAccountBusinessInfoDefault);
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("【资费查询跳转到修改页面】出现异常,异常是:"+e.getMessage());
			return mv;
		}
	}
	
	/**
	 * 更新资费数据
	 * @return
	 */
	@RequestMapping(value = "/update")
	@Transactional
	public String update(VoiceAccountBusinessInfoQuery voiceAccountBusinessInfoQuery,HttpServletRequest req,
			Model model){
		try {
			Map<String, String> cookies = CookieUtil.getCookies(req);
			String username = cookies.get(Constant.USER_SESSION_UNAME);
			if (username == null) {
				logger.warn("找不到用户，或者用户的登陆cookie已经过期");
				return "redirect:/login.html";
			}
			logger.info("【更新资费数据】参数是:{0}", new Object[] {JsonUtil.objectToJson(voiceAccountBusinessInfoQuery).toString()}, null);
			List<VoiceAccountBusinessInfo> voiceAccountBusinessInfos=voiceAccountBusinessInfoService.queryVoiceAccountBusinessInfoByAccount(voiceAccountBusinessInfoQuery.getApiAccount());
			if(voiceAccountBusinessInfos.size()>0){
				voiceAccountBusinessInfoService.batchUpdate(voiceAccountBusinessInfos,voiceAccountBusinessInfoQuery);
		
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("更新资费数据失败,异常是{0}", new Object[] { e.getMessage()}, null);
		}
		return "redirect:/voiceAccountBusinessInfo/voice_account_businessInfo_list";
	}

	public static double calcMul(int d1,int d2){  
		BigDecimal b1=new BigDecimal(Double.toString(d1)); 
		BigDecimal b3=new BigDecimal(10000);
        BigDecimal b2=new BigDecimal(Integer.toString(d2));  
        return b1.multiply(b2).divide(b3).doubleValue();  
          
    } 
	
	public static double calcMul3(int d1,int d2){  
		BigDecimal b1=new BigDecimal(Double.toString(d1)); 
		BigDecimal b3=new BigDecimal(10000);
        BigDecimal b2=new BigDecimal(Integer.toString(d2));  
        return b1.multiply(b2).divide(b3).doubleValue();  
          
    } 
	
	public static double calcMul2(int d1){  
		BigDecimal b1=new BigDecimal(Double.toString(d1)); 
		BigDecimal b3=new BigDecimal(10000);
        return b1.divide(b3).doubleValue();  
          
    } 
	
}
