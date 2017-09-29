package com.zy.cms.controller.syssettings;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zy.cms.common.CommonService;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.service.manager.CdrMonitorNoticeLogService;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.vo.query.CdrMonitorNoticeLogQuery;

/**
 * 监控告警日志
 * 
 * @author JasonXu
 *
 */
@RequestMapping("/cdr_monitor_notice_log")
@Scope(value = "prototype")
@Controller
public class CdrMonitorNoticeLog2Controller {

	private static final ZyLogger logger = ZyLogger.getLogger(CdrMonitorNoticeLog2Controller.class);

	@Autowired
	private CdrMonitorNoticeLogService cdrMonitorNoticeLogService;

	@Resource
	private CommonService commonService;

	/**
	 * 获取未处理的告警日志
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/get_log_count")
	@ResponseBody
	public String getLogCount(HttpServletRequest request) {
		int result = 0;
		try {
			/*
			 * User us = commonService.valideUser(request,
			 * Constant.USER_SESSION_UNAME); if (us == null) {
			 * logger.warn("用户登录超时!"); return JsonUtil.objectToJson(result); }
			 */

			CdrMonitorNoticeLogQuery cmnlq = new CdrMonitorNoticeLogQuery();
			cmnlq.setIsDeal("1");
			result = cdrMonitorNoticeLogService.queryCdrMonitorLogCount(cmnlq);

		} catch (Exception e) {
			logger.info("【告警日志获取账号列表】处理告警日志出现异常,异常是:" + e.getMessage());
		}
		return JsonUtil.objectToJson(result);
	}
}
