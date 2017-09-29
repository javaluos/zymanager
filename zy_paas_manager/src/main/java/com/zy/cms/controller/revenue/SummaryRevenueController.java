package com.zy.cms.controller.revenue;

import java.util.Date;
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
import com.zy.cms.service.manager.AccountBindInfoService;
import com.zy.cms.service.manager.SmsDailyRevenueStatisticsService;
import com.zy.cms.service.manager.excel.RevenueExcelService;
import com.zy.cms.service.master.MerchantAccountService;
import com.zy.cms.util.DateUtil;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.SmsDailyRevenueStatistics;
import com.zy.cms.vo.User;
import com.zy.cms.vo.WebResult;
import com.zy.cms.vo.manager.RevenueResult;
import com.zy.cms.vo.query.SmsDailyRevenueStatisticsQuery;

/**
 * 营收汇总
 * @author JasonXu
 *
 */
@Controller
@RequestMapping("/summary_revenue")
public class SummaryRevenueController {

	private static final ZyLogger logger = ZyLogger.getLogger(AccountRevenueController.class);

	@Autowired
	SmsDailyRevenueStatisticsService smsDailyRevenueStatisticsService;
	
	@Autowired
	MerchantAccountService merchantAccountService;
	
	@Resource
	private AccountBindInfoService accountBindInfoService;

	@Resource
	private CommonService commonService;
	
	@Resource
	private RevenueExcelService revenueExcelService;

	@RequestMapping("/to_list")
	public ModelAndView toList() {
		ModelAndView mv = new ModelAndView("/revenue/summary_revenue_list");
		mv.addObject("datetimeStart",DateUtil.formatDate(new Date(new Date().getTime()-24*60*60*1000),"yyyy-MM-dd"));
		mv.addObject("datetimeEnd",DateUtil.formatDate(new Date(new Date().getTime()-24*60*60*1000),"yyyy-MM-dd"));
		return mv;
	}
	
	
	@RequestMapping("/list_data")
	public ModelAndView getChannelGroupData(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("params") String params) {
		ModelAndView mv = new ModelAndView("/revenue/summary_revenue_list_data");
		try {
			User us = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
			if (us == null) {
				logger.warn("用户登录超时!");
				return new ModelAndView("redirect:/login.html");
			}
			
			SmsDailyRevenueStatisticsQuery query = null;
			logger.info("【营收汇总列表】参数={0}", new Object[] { params }, null);
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
			
			Integer total = smsDailyRevenueStatisticsService.listSummaryRevenueCount(query);
			List<SmsDailyRevenueStatistics> list = smsDailyRevenueStatisticsService.listSummaryRevenue(query);
			RevenueResult revenueResult =smsDailyRevenueStatisticsService.getRevenueResult(query);
			
			// 构建查询结果对象
			if(revenueResult==null){
				revenueResult=new RevenueResult();
			}
			revenueResult.setPage_num(Long.valueOf(query.getPageNum() + 1));
			revenueResult.setPage_size(Long.valueOf(query.getPageSize()));
			revenueResult.setTotal(Long.valueOf(total));
			revenueResult.setData(list);
			
			mv.addObject("pgdata", revenueResult);
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【获取营收汇总列表】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
			return mv;
		}
	}
	
	
	/**
	 * 客户汇总导出功能
	 * @param request
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/export")
	@ResponseBody
	public WebResult exportRevenue(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("params") String params) {

		logger.info("【营收汇总导出】开始  params=" + params);

		WebResult webRs = new WebResult();
		User us = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
		if (us == null) {
			logger.warn("用户登录超时!");
			webRs.setCode(-1);
			webRs.setMsg("用户登录超时!");
		}
		
		SmsDailyRevenueStatisticsQuery query = null;
		logger.info("【营收汇总】参数={0}", new Object[] { params }, null);
		if (!StringUtil.isEmpty(params)) {
			query = JsonUtil.parseToObject(params, SmsDailyRevenueStatisticsQuery.class);
		}
		if (query == null) {
			query = new SmsDailyRevenueStatisticsQuery();
		}
		
		Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query.getPageNum() - 1);
		query.setPageNum(pageNum);
		query.setPageSize(1000000);
		
		// 调用导出业务
		String realPath = request.getSession().getServletContext().getRealPath("");
		webRs = revenueExcelService.exportRevenueExcel(query, realPath, request.getContextPath());

		logger.info("【通道营收分析导出】结束...");

		return webRs;
	}
	
}
