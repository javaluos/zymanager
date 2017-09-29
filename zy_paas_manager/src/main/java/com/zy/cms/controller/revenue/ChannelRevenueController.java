package com.zy.cms.controller.revenue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.zy.cms.service.manager.AccountBindInfoService;
import com.zy.cms.service.manager.SmsDailyRevenueStatisticsService;
import com.zy.cms.service.manager.excel.RevenueExcelService;
import com.zy.cms.service.master.MerchantAccountService;
import com.zy.cms.service.master.SmsChannelService;
import com.zy.cms.util.DateUtil;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.MerchantAccount;
import com.zy.cms.vo.SmsDailyRevenueStatistics;
import com.zy.cms.vo.User;
import com.zy.cms.vo.WebResult;
import com.zy.cms.vo.channel.SmsChannel;
import com.zy.cms.vo.manager.RevenueResult;
import com.zy.cms.vo.query.ResultQuery;
import com.zy.cms.vo.query.SmsChannelQuery;
import com.zy.cms.vo.query.SmsDailyRevenueStatisticsQuery;

/**
 * 通道营收分析
 * @author JasonXu
 *
 */
@Controller
@RequestMapping("/channel_revenue")
public class ChannelRevenueController {

	private static final ZyLogger logger = ZyLogger.getLogger(AccountRevenueController.class);

	@Autowired
	SmsDailyRevenueStatisticsService smsDailyRevenueStatisticsService;
	
	@Autowired
	MerchantAccountService merchantAccountService;
	
	@Resource
	private AccountBindInfoService accountBindInfoService;

	@Resource
	private SmsChannelService smsChannelService;
	
	@Resource
	private CommonService commonService;

	@Resource
	private RevenueExcelService revenueExcelService;
	
	@RequestMapping("/to_list")
	public ModelAndView toList() {
		ModelAndView mv = new ModelAndView("/revenue/channel_revenue_list");
		mv.addObject("datetimeStart",DateUtil.formatDate(new Date(new Date().getTime()-24*60*60*1000),"yyyy-MM-dd"));
		mv.addObject("datetimeEnd",DateUtil.formatDate(new Date(new Date().getTime()-24*60*60*1000),"yyyy-MM-dd"));
		return mv;
	}
	
	
	@RequestMapping("/list_data")
	public ModelAndView getChannelGroupData(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("params") String params) {
		ModelAndView mv = new ModelAndView("/revenue/channel_revenue_list_data");
		try {
			User us = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
			if (us == null) {
				logger.warn("用户登录超时!");
				return new ModelAndView("redirect:/login.html");
			}
			
			SmsDailyRevenueStatisticsQuery query = null;
			logger.info("【通道营收分析查询列表】参数={0}", new Object[] { params }, null);
			if (!StringUtil.isEmpty(params)) {
				query = JsonUtil.parseToObject(params, SmsDailyRevenueStatisticsQuery.class);
			}
			if (query == null) {
				query = new SmsDailyRevenueStatisticsQuery();
			}
			
			Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query.getPageNum() - 1);
			Integer pageSize = query.getPageSize() == 0 ? Constant.PAGE_SIZE_20 : query.getPageSize();
			query.setPageNum(pageNum);
			query.setPageSize(pageSize);
			
			List<String> channelIds = new ArrayList<String>();
			if (!StringUtil.isEmpty(query.getChannelName())) {
				SmsChannelQuery scquery=new SmsChannelQuery();
				scquery.setChannelName(query.getChannelName().trim());
				List<String> ids=smsChannelService.queryIdsByEntity(scquery);
				if (ids != null && ids.size()>0) {
					channelIds.addAll(ids);
				} else {
					channelIds.add(query.getChannelName().trim());// 错误的，查询无结果
				}
			} 
			if (!StringUtil.isEmpty(query.getChannelMainCode())) {
				SmsChannelQuery scquery=new SmsChannelQuery();
				scquery.setChannelMainCode(query.getChannelMainCode().trim());
				List<String> ids=smsChannelService.queryIdsByEntity(scquery);
				if (ids != null && ids.size()>0) {
					channelIds.addAll(ids);
				} else {
					channelIds.add(query.getChannelMainCode().trim());// 错误的，查询无结果
				}
			} 
			query.setChannelIds(channelIds);
			
			Integer total = smsDailyRevenueStatisticsService.listChannelRevenueCount(query);
			List<SmsDailyRevenueStatistics> list = smsDailyRevenueStatisticsService.listChannelRevenue(query);
			RevenueResult revenueResult =smsDailyRevenueStatisticsService.getRevenueResult(query);
			
			String[] apis = toChannelIdArrys(list);
			Map<String, SmsChannel> smsChannelMaps = smsChannelService.queryChannelListByApis(apis);
			
			// 构建查询结果对象
			if(revenueResult==null){
				revenueResult=new RevenueResult();
			}
			revenueResult.setPage_num(Long.valueOf(query.getPageNum() + 1));
			revenueResult.setPage_size(Long.valueOf(query.getPageSize()));
			revenueResult.setTotal(Long.valueOf(total));
			revenueResult.setData(list);
			
			mv.addObject("pgdata", revenueResult);
			mv.addObject("smsChannelMaps", smsChannelMaps);
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【获取通道营收分析列表】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
			return mv;
		}
	}
	
	
	/**
	 * 客户营收分析导出功能
	 * @param request
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/export")
	@ResponseBody
	public WebResult exportChannelRevenue(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("params") String params) {

		logger.info("【通道营收分析导出】开始  params=" + params);

		WebResult webRs = new WebResult();
		User us = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
		if (us == null) {
			logger.warn("用户登录超时!");
			webRs.setCode(-1);
			webRs.setMsg("用户登录超时!");
		}
		
		SmsDailyRevenueStatisticsQuery query = null;
		logger.info("【通道营收分析查询列表】参数={0}", new Object[] { params }, null);
		if (!StringUtil.isEmpty(params)) {
			query = JsonUtil.parseToObject(params, SmsDailyRevenueStatisticsQuery.class);
		}
		if (query == null) {
			query = new SmsDailyRevenueStatisticsQuery();
		}
		
		Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query.getPageNum() - 1);
		query.setPageNum(pageNum);
		query.setPageSize(1000000);
		
		List<String> channelIds = new ArrayList<String>();
		if (!StringUtil.isEmpty(query.getChannelName())) {
			SmsChannelQuery scquery=new SmsChannelQuery();
			scquery.setChannelName(query.getChannelName().trim());
			List<String> ids=smsChannelService.queryIdsByEntity(scquery);
			if (ids != null && ids.size()>0) {
				channelIds.addAll(ids);
			} else {
				channelIds.add(query.getChannelName().trim());// 错误的，查询无结果
			}
		} 
		if (!StringUtil.isEmpty(query.getChannelMainCode())) {
			SmsChannelQuery scquery=new SmsChannelQuery();
			scquery.setChannelMainCode(query.getChannelMainCode().trim());
			List<String> ids=smsChannelService.queryIdsByEntity(scquery);
			if (ids != null && ids.size()>0) {
				channelIds.addAll(ids);
			} else {
				channelIds.add(query.getChannelMainCode().trim());// 错误的，查询无结果
			}
		} 
		query.setChannelIds(channelIds);
		
		// 调用导出业务
		String realPath = request.getSession().getServletContext().getRealPath("");
		webRs = revenueExcelService.exportChannelRevenueExcel(query, realPath, request.getContextPath());

		logger.info("【通道营收分析导出】结束...");

		return webRs;
	}
	

	@RequestMapping("/revenue_channel_attr_list")
	public ModelAndView revenueAttrList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("/revenue/channel_revenue_view");
		
		try {
			String channelId=request.getParameter("channelId");
			String smsCategory=request.getParameter("smsCategory");
			String dateTime=request.getParameter("dateTime");
			
			SmsChannel smsChannel=smsChannelService.selectByPrimaryKey(channelId);
			mv.addObject("smsChannel", smsChannel);
			mv.addObject("smsCategory",smsCategory);
			mv.addObject("dateTime",DateUtil.parseDateFromYYMMdd(dateTime));
		} catch (Exception e) {
			logger.error("【获取通道营收分析列表】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
		}
		
		return mv;
	}
	
	
	@RequestMapping("/revenue_channel_attr_data")
	public ModelAndView revenueAttrListData(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("params") String params) {
		ModelAndView mv = new ModelAndView("/revenue/channel_revenue_view_data");
		try {
			User us = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
			if (us == null) {
				logger.warn("用户登录超时!");
				return new ModelAndView("redirect:/login.html");
			}
			
			SmsDailyRevenueStatisticsQuery query = null;
			logger.info("【通道组查询列表】参数={0}", new Object[] { params }, null);
			if (!StringUtil.isEmpty(params)) {
				query = JsonUtil.parseToObject(params, SmsDailyRevenueStatisticsQuery.class);
			}
			if (query == null) {
				query = new SmsDailyRevenueStatisticsQuery();
			}
			
			Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query.getPageNum() - 1);
			Integer pageSize = query.getPageSize() == 0 ? Constant.PAGE_SIZE_20 : query.getPageSize();
			query.setPageNum(pageNum);
			query.setPageSize(pageSize);
			
			Integer total = smsDailyRevenueStatisticsService.listRevenueCount(query);
			List<SmsDailyRevenueStatistics> list = smsDailyRevenueStatisticsService.listRevenue(query);
			
			String[] apis = toApiArrys(list);
			Map<String, MerchantAccount> accountMaps =merchantAccountService.queryMerchantAccountListByApis(apis);
			
			// 构建查询结果对象
			ResultQuery<SmsDailyRevenueStatistics> pgdata = new ResultQuery<SmsDailyRevenueStatistics>();
			pgdata.setPage_num(Long.valueOf(query.getPageNum() + 1));
			pgdata.setPage_size(Long.valueOf(query.getPageSize()));
			pgdata.setTotal(Long.valueOf(total));
			pgdata.setData(list);
			mv.addObject("pgdata", pgdata);
			mv.addObject("accountMaps", accountMaps);
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【获取通道营收分析列表】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
			return mv;
		}
	}
	
	/**
	 * 获得apiAccount数组查询
	 * 
	 * @param list
	 * @return
	 */
	private String[] toApiArrys(List<SmsDailyRevenueStatistics> list) {

		String[] apiArrays = {};
		if (list == null || list.size() == 0) {
			return apiArrays;
		}
		apiArrays = new String[list.size()];
		Set<String> oSet = new HashSet<String>();
		for (SmsDailyRevenueStatistics vo : list) {
			oSet.add(vo.getApiAccount());
		}

		apiArrays = oSet.toArray(new String[] {});
		return apiArrays;
	}
	
	/**
	 * 获得channelId数组查询
	 * 
	 * @param list
	 * @return
	 */
	private String[] toChannelIdArrys(List<SmsDailyRevenueStatistics> list) {

		String[] apiArrays = {};
		if (list == null || list.size() == 0) {
			return apiArrays;
		}
		apiArrays = new String[list.size()];
		Set<String> oSet = new HashSet<String>();
		for (SmsDailyRevenueStatistics vo : list) {
			oSet.add(vo.getChannelId());
		}

		apiArrays = oSet.toArray(new String[] {});
		return apiArrays;
	}
}
