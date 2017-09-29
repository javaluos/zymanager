package com.zy.cms.controller.analysis;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zy.cms.common.CommonService;
import com.zy.cms.common.Constant;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.enums.SmsCategoryEnum;
import com.zy.cms.service.manager.AccountBindInfoService;
import com.zy.cms.service.manager.SmsDailyStatService;
import com.zy.cms.service.manager.UserMenuService;
import com.zy.cms.service.master.MerchantAccountService;
import com.zy.cms.service.master.SmsChannelService;
import com.zy.cms.util.DateUtil;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.MerchantAccount;
import com.zy.cms.vo.User;
import com.zy.cms.vo.UserMenu;
import com.zy.cms.vo.WebResult;
import com.zy.cms.vo.channel.SmsChannel;
import com.zy.cms.vo.manager.FailDetailVO;
import com.zy.cms.vo.manager.SmsDailyStatistics;
import com.zy.cms.vo.query.ResultQuery;
import com.zy.cms.vo.query.SmsSendQuery;

/**
 * 短信发送汇总 Controller Created by luos on 2017/3/22.
 */
@Controller
@RequestMapping("/sms_send_stat")
public class SmsSendStatisticsController {

	private static final ZyLogger logger = ZyLogger.getLogger(SmsSendStatisticsController.class);

	@Autowired
	private CommonService commonService;

	@Autowired
	private SmsDailyStatService smsDailyStatService;

	@Autowired
	private MerchantAccountService merchantAccountService;

	@Autowired
	private AccountBindInfoService accountBindInfoService;

	@Autowired
	private UserMenuService userMenuService;

	@Autowired
	private SmsChannelService smsChannelService;

	@RequestMapping(value = "/to_list", produces = "text/html;charset=UTF-8")
	public ModelAndView toList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("/analysis/sms_send_stat_list");
		try {
			mv.addObject("categoryList", SmsCategoryEnum.values());
			String params = request.getParameter("params");
			JSONObject jsonObject = JSON.parseObject(params);
			if (null != jsonObject) {
				String sTime = jsonObject.getString("sTime");
				String eTime = jsonObject.getString("eTime");
				if (StringUtils.isBlank(sTime)) {
					mv.addObject("datetimeStart", DateUtil.getDateYMD() + " 00:00:00");
				} else {
					mv.addObject("datetimeStart", sTime);
				}
				if (StringUtils.isBlank(eTime)) {
					mv.addObject("datetimeEnd", DateUtil.getDateYMD() + " 23:59:59");
				} else {
					mv.addObject("datetimeEnd", eTime);
				}
				mv.addObject("merchantPhone", jsonObject.getString("merchantPhone"));
				String businessName = jsonObject.getString("businessName");
				mv.addObject("businessName", new String(businessName.getBytes("iso-8859-1"), "utf-8"));
				mv.addObject("smsCategory", jsonObject.getInteger("smsCategory"));
			} else {
				mv.addObject("datetimeStart", DateUtil.getDateYMD() + " 00:00:00");
				mv.addObject("datetimeEnd", DateUtil.getDateYMD() + " 23:59:59");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【短信发送汇总查询列表】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
		}
		mv.addObject("today", DateUtil.getDateYMD());
		return mv;
	}

	@RequestMapping(value = "/list_data", produces = "application/json")
	public ModelAndView getSmsListData(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("params") String params) {
		ModelAndView mv = new ModelAndView("/analysis/sms_send_stat_list_data");
		try {
			User user = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
			if (user == null) {
				logger.warn("用户登录超时!");
				return new ModelAndView("redirect:/login.html");
			}

			SmsSendQuery query = null;
			logger.info("【短信发送汇总查询列表】参数={0}", new Object[] { params }, null);
			if (!StringUtil.isEmpty(params)) {
				query = JsonUtil.parseToObject(params, SmsSendQuery.class);
			}
			if (query == null) {
				query = new SmsSendQuery();
			}

			Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query.getPageNum() - 1);
			Integer pageSize = query.getPageSize() == 0 ? Constant.PAGE_SIZE_20 : query.getPageSize();
			query.setPageNum(pageNum);
			query.setPageSize(pageSize);

			List<String> apiAccounts = accountBindInfoService.getApiAccountByBusName(user.getUserName());
			query.setApiAccounts(apiAccounts);

			Date now = new Date();
			String todayDateStr = DateUtil.formatDate(now, DateUtil.NUMBER_DATE_FORMAT);
			String today = DateUtil.formatDate(now, DateUtil.ISO_DATE_FORMAT);
			String result = "";
			Integer total = 0;
			List<SmsDailyStatistics> list = null;
			SmsDailyStatistics smsDailyStatistics = null;
			long startTime = System.currentTimeMillis();
			String sTime = query.getStarttime();
			String eTime = query.getEndtime();
			mv.addObject("sTime", sTime);
			mv.addObject("eTime", eTime);
			mv.addObject("merchantPhone", query.getMerchantPhone());
			mv.addObject("businessName", query.getBusinessName());
			mv.addObject("smsCategory", query.getCategory());
			if (StringUtils.isNotBlank(sTime) && StringUtils.isNotBlank(eTime) && today.equals(sTime.substring(0, 10))
					&& today.equals(sTime.substring(0, 10))) {
				query.setTableName(todayDateStr);
				String statDateTime = smsDailyStatService.getLatestStatDateTime();
				query.setStatTime(statDateTime);
				total = smsDailyStatService.getTodayTotalByQuery(query);
				if (total == 0) {
					statDateTime = getLastFiveMinutes(statDateTime);
					query.setStatTime(statDateTime);
					total = smsDailyStatService.getTodayTotalByQuery(query);
					list = smsDailyStatService.getTodayListByQuery(query);
				} else {
					list = smsDailyStatService.getTodayListByQuery(query);
				}
				smsDailyStatistics = smsDailyStatService.statTodaySmsDailyTotal(query);
				mv.addObject("dateStr", DateUtil.formatDate(todayDateStr));
				mv.addObject("dateFormat", todayDateStr);
			} else {
				if (StringUtils.isNotBlank(sTime)) {
					sTime = DateUtil.transformDateFormat(sTime.substring(0, 10), DateUtil.ISO_DATE_FORMAT,
							DateUtil.NUMBER_DATE_FORMAT);
					query.setStarttime(sTime);
				}
				if (StringUtils.isNotBlank(query.getEndtime())) {
					eTime = DateUtil.transformDateFormat(eTime.substring(0, 10), DateUtil.ISO_DATE_FORMAT,
							DateUtil.NUMBER_DATE_FORMAT);
					query.setEndtime(eTime);
				}
				total = smsDailyStatService.getTotalByQuery(query);
				list = smsDailyStatService.getListByQuery(query);
				smsDailyStatistics = smsDailyStatService.statSmsDailyTotal(query);
			}
			long endTime = System.currentTimeMillis();
			logger.info("本次查询执行时间" + (endTime - startTime) + "ms");
			mv.addObject("smsDailyStatistics", smsDailyStatistics);

			// 构建查询结果对象
			ResultQuery pgdata = new ResultQuery();
			pgdata.setPage_num(Long.valueOf(query.getPageNum() + 1));
			pgdata.setPage_size(Long.valueOf(query.getPageSize()));
			pgdata.setTotal(Long.valueOf(total));
			pgdata.setData(list);

			// 账号是否有属性查看权限
			UserMenu userMenu = userMenuService.getByUserName(user.getUserName());
			String menus = userMenu.getMenus();
			if (StringUtils.isNotBlank(menus) && menus.contains(Constant.SMS_STATISTICS_MENUID)) {
				mv.addObject("permission", true);
			} else {
				mv.addObject("permission", false);
			}
			mv.addObject("pgdata", pgdata);
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【查询短信发送汇总】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
			return mv;
		}
	}

	@RequestMapping(value = "/to_view", produces = "text/html;charset=UTF-8")
	public ModelAndView toView(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("/analysis/sms_send_stat_view");
		try {
			String params = request.getParameter("params");
			params = new String(params.getBytes("iso-8859-1"), "utf-8");
			JSONObject jsonObject = JSON.parseObject(params);
			if (null != jsonObject) {
				String apiAccount = jsonObject.getString("apiAccount");
				MerchantAccount merchantAccount = merchantAccountService.getMerchantAccount(apiAccount);
				mv.addObject("businessName", merchantAccount.getBusinessName());
				mv.addObject("merchantPhone", merchantAccount.getMerchantPhone());
				mv.addObject("apiAccount", apiAccount);
				mv.addObject("smsCategory", jsonObject.getInteger("smsCategory"));
				String dateTime = jsonObject.getString("dateTime");
				dateTime = DateUtil.formatDate(dateTime);
				mv.addObject("dateTime", dateTime);
				mv.addObject("sTime", jsonObject.getString("sTime"));
				mv.addObject("eTime", jsonObject.getString("eTime"));
				mv.addObject("queryPhone", jsonObject.getString("merchantPhone"));
				mv.addObject("queryName", jsonObject.getString("businessName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【查询短信发送汇总详情】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
			return mv;
		}
		return mv;
	}

	@RequestMapping(value = "/view_list_data", produces = "application/json")
	public ModelAndView getSmsViewListData(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("params") String params) {
		ModelAndView mv = new ModelAndView("/analysis/sms_send_stat_view_data");
		try {
			User user = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
			if (user == null) {
				logger.warn("用户登录超时!");
				return new ModelAndView("redirect:/login.html");
			}

			SmsSendQuery query = null;
			logger.info("【短信发送汇总详情查询列表】参数={0}", new Object[] { params }, null);
			if (!StringUtil.isEmpty(params)) {
				query = JsonUtil.parseToObject(params, SmsSendQuery.class);
			}
			if (query == null) {
				query = new SmsSendQuery();
			}

			Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query.getPageNum() - 1);
			Integer pageSize = query.getPageSize() == 0 ? Constant.PAGE_SIZE_20 : query.getPageSize();
			query.setPageNum(pageNum);
			query.setPageSize(pageSize);

			if (StringUtils.isNotBlank(query.getStarttime())) {
				String startTime = DateUtil.transformDateFormat(query.getStarttime(), DateUtil.ISO_DATE_FORMAT,
						DateUtil.NUMBER_DATE_FORMAT);
				query.setStarttime(startTime);
			}
			if (StringUtils.isNotBlank(query.getEndtime())) {
				String endTime = DateUtil.transformDateFormat(query.getEndtime(), DateUtil.ISO_DATE_FORMAT,
						DateUtil.NUMBER_DATE_FORMAT);
				query.setEndtime(endTime);
			}

			List<String> apiAccounts = accountBindInfoService.getApiAccountByBusName(user.getUserName());
			query.setApiAccounts(apiAccounts);

			String todayDateStr = DateUtil.formatDate(new Date(), DateUtil.NUMBER_DATE_FORMAT);
			String result = "";
			Integer total = 0;
			List<SmsDailyStatistics> list = null;
			long startTime = System.currentTimeMillis();
			if (todayDateStr.equals(query.getStarttime()) && todayDateStr.equals(query.getEndtime())) {
				query.setTableName(todayDateStr);
				String statDateTime = DateUtil.parseBeforeMins(-5);// 当前时间向前减5分钟
				query.setStatTime(statDateTime);
				total = smsDailyStatService.getTodayViewTotalByQuery(query);
				list = smsDailyStatService.getTodayViewListByQuery(query);
				mv.addObject("dateStr", DateUtil.formatDate(todayDateStr));
				mv.addObject("dateFormat", todayDateStr);
			} else {
				total = smsDailyStatService.getViewTotalByQuery(query);
				list = smsDailyStatService.getViewListByQuery(query);
			}
			long endTime = System.currentTimeMillis();
			logger.info("本次查询执行时间" + (endTime - startTime) + "ms");

			// 构建查询结果对象
			ResultQuery pgdata = new ResultQuery();
			pgdata.setPage_num(Long.valueOf(query.getPageNum() + 1));
			pgdata.setPage_size(Long.valueOf(query.getPageSize()));
			pgdata.setTotal(Long.valueOf(total));
			pgdata.setData(list);
			mv.addObject("pgdata", pgdata);
			mv.addObject("smsCategory", query.getCategory());
			mv.addObject("sTime", query.getsTime());
			mv.addObject("eTime", query.geteTime());
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【查询短信发送汇总详情】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
			return mv;
		}
	}

	@RequestMapping("/export")
	@ResponseBody
	public WebResult doexport(HttpServletRequest request,
			@RequestParam(value = "params", required = false) String params) {
		logger.info("【 短信发送记录汇总导出】开始  params=" + params);
		WebResult webRs = new WebResult();
		try {
			User user = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
			if (user == null) {
				logger.warn("用户登录超时!");
				webRs.setCode(-1);
				webRs.setMsg("用户登录超时!");
			}
			SmsSendQuery query = null;
			if (!StringUtil.isEmpty(params)) {
				query = JsonUtil.parseToObject(params, SmsSendQuery.class);
			}

			Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query.getPageNum() - 1); // es分页码从0开始，所以-1
			query.setPageNum(pageNum);
			query.setPageSize(1000000);// 最大值
			String starttime = query.getStarttime();
			String endtime = query.getEndtime();
			if (StringUtils.isNotBlank(starttime) && starttime.length() >= 10) {
				String startTime = DateUtil.transformDateFormat(starttime.substring(0, 10), DateUtil.ISO_DATE_FORMAT,
						DateUtil.NUMBER_DATE_FORMAT);
				query.setStarttime(startTime);
			}
			if (StringUtils.isNotBlank(endtime) && endtime.length() >= 10) {
				String endTime = DateUtil.transformDateFormat(endtime.substring(0, 10), DateUtil.ISO_DATE_FORMAT,
						DateUtil.NUMBER_DATE_FORMAT);
				query.setEndtime(endTime);
			}
			List<String> apiAccounts = accountBindInfoService.getApiAccountByBusName(user.getUserName());
			query.setApiAccounts(apiAccounts);

			// 调用导出业务
			String realPath = request.getSession().getServletContext().getRealPath("");
			webRs = smsDailyStatService.exportExcel(query, realPath, request.getContextPath());
		} catch (Exception e) {
			logger.info("【短信发送记录汇总下载】出现异常,异常是" + e.getMessage());
			e.printStackTrace();
		}

		logger.info("【短信发送记录汇总导出】结束...");
		return webRs;
	}

	@RequestMapping(value = "/show_fail_detail", produces = "text/html;charset=UTF-8")
	public ModelAndView toFailDetail(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("/analysis/show_fail_detail");
		String params = request.getParameter("params");
		JSONObject jsonObject = JSON.parseObject(params);
		String apiAccount = jsonObject.getString("apiAccount");
		mv.addObject("apiAccount", apiAccount);
		mv.addObject("smsCategory", jsonObject.getInteger("smsCategory"));
		MerchantAccount merchantAccount = merchantAccountService.getMerchantAccount(apiAccount);
		String dateTime = jsonObject.getString("dateTime");
		mv.addObject("dateTime", dateTime);
		mv.addObject("merchantPhone", merchantAccount.getMerchantPhone());
		mv.addObject("businessName", merchantAccount.getBusinessName());
		String dateStr = DateUtil.parseDateFromYYMMdd(dateTime);
		mv.addObject("startTime", dateStr + " 00:00:00");
		mv.addObject("endTime", dateStr + " 23:59:59");
		mv.addObject("sTime", jsonObject.getString("sTime"));
		mv.addObject("eTime", jsonObject.getString("eTime"));
		return mv;
	}

	@RequestMapping("/fail_analysis")
	public ModelAndView failAnalysis(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("params") String params) {
		ModelAndView mv = new ModelAndView("/analysis/show_fail_detail_data");

		User user = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
		if (user == null) {
			logger.warn("用户登录超时!");
			return new ModelAndView("redirect:/login.html");
		}
		try {
			SmsSendQuery query = null;
			logger.info("【短信发送汇总查询列表】参数={0}", new Object[] { params }, null);
			if (!StringUtil.isEmpty(params)) {
				query = JsonUtil.parseToObject(params, SmsSendQuery.class);
			}
			if (query == null) {
				query = new SmsSendQuery();
			}
			List<FailDetailVO> list = smsDailyStatService.getSmsFailDetailByQuery(query);
			ResultQuery pgdata = new ResultQuery();
			pgdata.setPage_num(Long.valueOf(query.getPageNum() + 1));
			pgdata.setPage_size(Long.valueOf(query.getPageSize()));
			mv.addObject("pgdata", pgdata);
			mv.addObject("size", list.size());
			if (null != list && list.size() > 1) {
				mv.addObject("total", list.get(0).getTotal());
				mv.addObject("groupCount", list.get(0).getGroupCount());
				mv.addObject("percentage", list.get(0).getPercentage());
				mv.addObject("receiveStatusDesc", list.get(0).getReceiveStatusDesc());
				mv.addObject("statusDescCN", list.get(0).getStatusDescCN());
				list.remove(0);
			}
			pgdata.setData(list);
			pgdata.setTotal((long) list.size());
			mv.addObject("businessName", query.getBusinessName());
			mv.addObject("merchantPhone", query.getMerchantPhone());
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【失败原因分析】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
			return mv;
		}
	}

	@RequestMapping("/fail_analysis_export")
	@ResponseBody
	public WebResult failAnalysisExport(HttpServletRequest request,
			@RequestParam(value = "params", required = false) String params) {
		logger.info("【 客户失败原因分析导出】开始  params=" + params);
		WebResult webRs = new WebResult();
		try {
			User user = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
			if (user == null) {
				logger.warn("用户登录超时!");
				webRs.setCode(-1);
				webRs.setMsg("用户登录超时!");
			}
			SmsSendQuery query = null;
			if (!StringUtil.isEmpty(params)) {
				query = JsonUtil.parseToObject(params, SmsSendQuery.class);
			}

			Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query.getPageNum() - 1); // es分页码从0开始，所以-1
			query.setPageNum(pageNum);
			query.setPageSize(1000000);// 最大值
			List<String> apiAccounts = accountBindInfoService.getApiAccountByBusName(user.getUserName());
			query.setApiAccounts(apiAccounts);

			// 调用导出业务
			String realPath = request.getSession().getServletContext().getRealPath("");
			webRs = smsDailyStatService.exportFailAnalysis(query, realPath, request.getContextPath());
		} catch (Exception e) {
			logger.info("【客户失败原因分析导出】出现异常,异常是" + e.getMessage());
			e.printStackTrace();
		}

		logger.info("【客户失败原因分析导出】结束...");
		return webRs;
	}

	@RequestMapping("/fail_detail_view")
	public ModelAndView viewFailDetail(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("/analysis/view_fail_detail");
		String params = request.getParameter("params");
		JSONObject jsonObject = JSON.parseObject(params);
		String apiAccount = jsonObject.getString("apiAccount");
		String channelId = jsonObject.getString("channelId");
		mv.addObject("apiAccount", apiAccount);
		mv.addObject("channelId", channelId);
		SmsChannel smsChannel = smsChannelService.selectByPrimaryKey(channelId);
		if (null != smsChannel) {
			mv.addObject("channelName", smsChannel.getChannelName());
		} else {
			mv.addObject("channelName", "");
		}
		MerchantAccount merchantAccount = merchantAccountService.getMerchantAccount(apiAccount);
		String dateTime = jsonObject.getString("dateTime");
		mv.addObject("dateTime", dateTime);
		if (null != merchantAccount) {
			mv.addObject("merchantPhone", merchantAccount.getMerchantPhone());
			mv.addObject("businessName", merchantAccount.getBusinessName());
		} else {
			mv.addObject("merchantPhone", "");
			mv.addObject("businessName", "");
		}
		mv.addObject("smsCategory", jsonObject.getInteger("smsCategory"));
		String dateStr = DateUtil.parseDateFromYYMMdd(dateTime);
		mv.addObject("startTime", dateStr + " 00:00:00");
		mv.addObject("endTime", dateStr + " 23:59:59");
		mv.addObject("sTime", jsonObject.getString("sTime"));
		mv.addObject("eTime", jsonObject.getString("eTime"));
		return mv;

	}

	@RequestMapping("/view_fail_analysis")
	public ModelAndView viewFailAnalysis(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("params") String params) {
		ModelAndView mv = new ModelAndView("/analysis/view_fail_detail_data");

		User user = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
		if (user == null) {
			logger.warn("用户登录超时!");
			return new ModelAndView("redirect:/login.html");
		}
		try {
			SmsSendQuery query = null;
			logger.info("【短信发送汇总查询列表】参数={0}", new Object[] { params }, null);
			if (!StringUtil.isEmpty(params)) {
				query = JsonUtil.parseToObject(params, SmsSendQuery.class);
			}
			if (query == null) {
				query = new SmsSendQuery();
			}
			List<FailDetailVO> list = smsDailyStatService.getSmsViewFailDetailByQuery(query);
			ResultQuery pgdata = new ResultQuery();
			pgdata.setPage_num(Long.valueOf(query.getPageNum() + 1));
			pgdata.setPage_size(Long.valueOf(query.getPageSize()));
			mv.addObject("pgdata", pgdata);
			mv.addObject("size", list.size());
			List<FailDetailVO> newList = new ArrayList<FailDetailVO>();
			if (null != list && list.size() > 1) {
				mv.addObject("total", list.get(0).getTotal());
				mv.addObject("groupCount", list.get(0).getGroupCount());
				mv.addObject("percentage", list.get(0).getPercentage());
				mv.addObject("receiveStatusDesc", list.get(0).getReceiveStatusDesc());
				mv.addObject("statusDescCN", list.get(0).getStatusDescCN());
				list.remove(0);
			}
			pgdata.setData(list);
			pgdata.setTotal((long) list.size());
			mv.addObject("channelName", query.getChannelName());
			mv.addObject("channelId", query.getChannelId());
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【失败原因分析】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
			return mv;
		}
	}

	@RequestMapping("/channel_analysis_export")
	@ResponseBody
	public WebResult channelAnalysisExport(HttpServletRequest request,
			@RequestParam(value = "params", required = false) String params) {
		logger.info("【 客户失败原因分析导出】开始  params=" + params);
		WebResult webRs = new WebResult();
		try {
			User user = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
			if (user == null) {
				logger.warn("用户登录超时!");
				webRs.setCode(-1);
				webRs.setMsg("用户登录超时!");
			}
			SmsSendQuery query = null;
			if (!StringUtil.isEmpty(params)) {
				query = JsonUtil.parseToObject(params, SmsSendQuery.class);
			}

			Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query.getPageNum() - 1); // es分页码从0开始，所以-1
			query.setPageNum(pageNum);
			query.setPageSize(1000000);// 最大值
			List<String> apiAccounts = accountBindInfoService.getApiAccountByBusName(user.getUserName());
			query.setApiAccounts(apiAccounts);

			// 调用导出业务
			String realPath = request.getSession().getServletContext().getRealPath("");
			webRs = smsDailyStatService.exportChannelFailAnalysis(query, realPath, request.getContextPath());
		} catch (Exception e) {
			logger.info("【客户失败原因分析导出】出现异常,异常是" + e.getMessage());
			e.printStackTrace();
		}

		logger.info("【客户失败原因分析导出】结束...");
		return webRs;
	}

	private String getLastFiveMinutes(String dateTime) {
		Calendar c = Calendar.getInstance();
		Date date = DateUtil.parseDate(dateTime, DateUtil.ISO_DATE_TIME_FORMAT);
		long returnTime = date.getTime() - 300000;
		return DateUtil.getDateTime(new Date(returnTime));
	}

}
