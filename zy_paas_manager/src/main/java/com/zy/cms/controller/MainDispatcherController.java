package com.zy.cms.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.zy.cms.common.CommonService;
import com.zy.cms.common.Constant;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.service.manager.CdrMonitorNoticeLogService;
import com.zy.cms.util.RequstUtil;
import com.zy.cms.vo.User;
import com.zy.cms.vo.query.CdrMonitorNoticeLogQuery;

/**
 * paas manager main控制器
 * 
 * @author allen.yuan
 * @date 2016-09-20
 */
@Controller
@SessionAttributes("user")
@RequestMapping(value = "/main")
public class MainDispatcherController {

	private static final ZyLogger log = ZyLogger.getLogger(MainDispatcherController.class);

	@Resource
	private CommonService commonService;
	
	@Autowired
	private CdrMonitorNoticeLogService cdrMonitorNoticeLogService;

	@RequestMapping(value = "/index")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response, ModelMap model) {

		ModelAndView mav = new ModelAndView("/main");
		
		try {
			User us = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
			if (us == null) {
				log.warn("找不到用户，或者用户的登陆cookie已经过期。");
				return new ModelAndView("redirect:/login.html");
			}
			us.setLastLoginIp(RequstUtil.getIpAddr(request));// 设置登录IP
			us.setLastLoginTime(new Date());// 登录时间
			model.addAttribute("user", us);
			
			/**监控告警未处理数目**/
			CdrMonitorNoticeLogQuery cmnlq=new CdrMonitorNoticeLogQuery();
			cmnlq.setIsDeal("1");
			int totalCount=cdrMonitorNoticeLogService.queryCdrMonitorLogCount(cmnlq);
			model.addAttribute("totalCount", totalCount);
		} catch (Exception e) {
			log.error("跳到主页面出现异常,异常是:"+e.getMessage());
		}
		
		return mav;
	}

	/**
	 * 跳转到默认界面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/default")
	public ModelAndView showWelcome(HttpServletRequest request, HttpServletResponse response, ModelMap model) {

		ModelAndView mav = new ModelAndView("/main/default");
		return mav;
	}

}
