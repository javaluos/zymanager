package com.zy.cms.controller.syssettings;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zy.cms.common.CommonService;
import com.zy.cms.common.Constant;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.service.manager.CdrMonitorNoticeLogService;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.User;
import com.zy.cms.vo.manager.CdrMonitorNoticeLog;
import com.zy.cms.vo.query.CdrMonitorNoticeLogQuery;
import com.zy.cms.vo.query.ResultQuery;

/**
 * 监控告警日志
 * @author JasonXu
 *
 */
@RequestMapping("/cdr_monitor_notice_log")
@Controller
public class CdrMonitorNoticeLogController {
	
	private static final ZyLogger logger = ZyLogger.getLogger(CdrMonitorNoticeLogController.class);
	
	@Autowired
	private CdrMonitorNoticeLogService cdrMonitorNoticeLogService;
	
	@Resource
	private CommonService commonService;

	
	@RequestMapping("/to_list")
	public ModelAndView toList(HttpServletRequest request, HttpServletResponse response){
		// 定义结果
		ModelAndView mv = new ModelAndView("/syssettings/cdr_monitor_log_list");
		return mv;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/list_data")
	public ModelAndView getAccountListData(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("params") String params){
		ModelAndView mv = new ModelAndView("/syssettings/cdr_monitor_log_list_data");
		try {
			    CdrMonitorNoticeLogQuery query = null;
				logger.info("【告警日志查询列表】参数={0}", new Object[] { params }, null);
				if (!StringUtil.isEmpty(params)) {
					query = JsonUtil.parseToObject(params, CdrMonitorNoticeLogQuery.class);
				}
				if (query == null) {
					query = new CdrMonitorNoticeLogQuery();
				}

				Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query
						.getPageNum() - 1);
				Integer pageSize = query.getPageSize() == 0 ? Constant.PAGE_SIZE_20
						: query.getPageSize();
				query.setPageNum(pageNum);
				query.setPageSize(pageSize);
				
				Integer total = cdrMonitorNoticeLogService.queryCdrMonitorLogCount(query);
				List<CdrMonitorNoticeLog> list = cdrMonitorNoticeLogService.queryCdrMonitorLogByEntity(query);
				
				// 构建查询结果对象
				ResultQuery pgdata = new ResultQuery();
				pgdata.setPage_num(Long.valueOf(query.getPageNum() + 1));
				pgdata.setPage_size(Long.valueOf(query.getPageSize()));
				pgdata.setTotal(Long.valueOf(total));
				pgdata.setData(list);
				mv.addObject("pgdata", pgdata);
				return mv;
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("【告警日志获取账号列表】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
				return mv;
			}
	}

	@RequestMapping("/do_update")
	@ResponseBody
	public String updateLog(HttpServletRequest request,String id){
		logger.info("【告警日志获取账号列表】处理告警日志开始...id="+id);
		int result = 0;
		try {
			User us = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
			if (us == null) {
				logger.warn("用户登录超时!");
				return JsonUtil.objectToJson(result);
			}
			
			if(!StringUtil.isEmpty(id)){
				result=cdrMonitorNoticeLogService.updateLog(id,us.getUserName());
			}
		} catch (Exception e) {
			logger.info("【告警日志获取账号列表】处理告警日志出现异常,异常是:"+e.getMessage());
		}
		return JsonUtil.objectToJson(result);
	}

	
	/**
	 * 一键处理
	 * @param request
	 * @return
	 */
	@RequestMapping("/deal_all")
	@ResponseBody
	public String dealAll(HttpServletRequest request){
		int result = 0;
		try {
			User us = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
			if (us == null) {
				logger.warn("用户登录超时!");
				return JsonUtil.objectToJson(result);
			}
			
			result=cdrMonitorNoticeLogService.updateAll(us.getUserName());
			
		} catch (Exception e) {
			logger.info("【告警日志获取账号列表】处理告警日志出现异常,异常是:"+e.getMessage());
		}
		return JsonUtil.objectToJson(result);
	}
}
