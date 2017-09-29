package com.zy.cms.controller.mbag;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.zy.cms.service.manager.CdrFeeMonthSumService;
import com.zy.cms.service.master.MerchantAccountService;
import com.zy.cms.util.DateUtil;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.MerchantAccount;
import com.zy.cms.vo.User;
import com.zy.cms.vo.WebResult;
import com.zy.cms.vo.manager.CdrFeeMonthResult;
import com.zy.cms.vo.manager.CdrFeeMonthSum;
import com.zy.cms.vo.query.CdrFeeMonthSumQuery;
import com.zy.cms.vo.query.ResultQuery;

/**
 * 月结账单
 * 
 * @author xu
 * @date 2016-10-09
 */
@Controller
@RequestMapping(value = "/cdrfeemonth")
public class CdrFeeMonthSumController {

	private static final ZyLogger logger = ZyLogger.getLogger(CdrFeeMonthSumController.class);

	@Resource
	private MerchantAccountService merchantAccountService;

	@Resource
	private CdrFeeMonthSumService cdrFeeMonthSumService;

	@Resource
	private CommonService commonService;
	
	@Autowired
	private AccountBindInfoService accountBindInfoService;

	/**
	 * 查询月结账单列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/cdrfeemonth_list")
	public ModelAndView cdrFeeMonthList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("/mbag/cdrfeemonth_list");
		mv.addObject("dateTime", DateUtil.parseDateFromYYMM(DateUtil.getLastMonthDate()));
		return mv;
	}

	/**
	 * 加载月结账单列表
	 * 
	 * @param request
	 * @return mv JSPTable
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/cdrfeemonth_list_data", produces = "application/json")
	public ModelAndView cdrFeeMonthListData(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("params") String params) {
		// 定义结果
		ModelAndView mv = new ModelAndView("/mbag/cdrfeemonth_list_data");
		ResultQuery pgdata = new ResultQuery();
		try {
			User user = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
			if (user == null) {
				logger.warn("用户登录超时!");
				return new ModelAndView("redirect:/login.html");
			}
			
			CdrFeeMonthSumQuery query = null;
			if (!StringUtil.isEmpty(params)) {
				query = JsonUtil.parseToObject(params, CdrFeeMonthSumQuery.class);
			}
			if (query == null) {
				query = new CdrFeeMonthSumQuery();
			}
			if (StringUtil.isEmpty(query.getDateTime())) {
				String tablesuffix = DateUtil.getLastMonthDate();
				query.setTablesuffix(tablesuffix);
			} else {
				query.setTablesuffix(DateUtil.parseDateToYYMM(query.getDateTime()));
			}
			logger.info("【月结账单】页面传来的参数为:" + JsonUtil.toJsonString(query));

			Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query.getPageNum() - 1);
			Integer pageSize = query.getPageSize() == 0 ? Constant.PAGE_SIZE_20 : query.getPageSize();
			query.setPageNum(pageNum);
			query.setPageSize(pageSize);

			List<String> apiAccounts=accountBindInfoService.getApiAccountByBusName(user.getUserName());
			query.setApiAccounts(apiAccounts);
			
			Integer total = cdrFeeMonthSumService.queryCdrFeeMonthAccount(query);
			List<CdrFeeMonthSum> list = cdrFeeMonthSumService.queryCdrFeeMonthByEntity(query);
			List<CdrFeeMonthSum> dataList=new ArrayList<CdrFeeMonthSum>();
			CdrFeeMonthResult cdrFeeMonthSum = cdrFeeMonthSumService.queryCdrMonthStatisticsCount(query);
			if (null != list && list.size() > 0) {
				pgdata.setDateTime(DateUtil.parseDateFromYY(list.get(0).getDateTime()));
				for (CdrFeeMonthSum cfms : list) {
					cfms.setSumFeeBsd1(calcMul(cfms.getSumFeeBs1()));
					cfms.setSumFeeBsd2(calcMul(cfms.getSumFeeBs2()));
					cfms.setSumFeeBsd3(calcMul(cfms.getSumFeeBs3()));
					cfms.setSumFeeBsd4(calcMul(cfms.getSumFeeBs4()));
					cfms.setSumFeeBsd5(calcMul(cfms.getSumFeeBs5()));
					cfms.setSumFeeBsd6(calcMul(cfms.getSumFeeBs6()));
					cfms.setSumFeeBsd7(calcMul(cfms.getSumFeeBs7()));
					cfms.setSumFeeBsd8(calcMul(cfms.getSumFeeBs8()));
					cfms.setSumFeeBsd9(calcMul(cfms.getSumFeeBs9()));
					cfms.setSumFeeBsd10(calcMul(cfms.getSumFeeBs10()));
					cfms.setSumFeeBsd11(calcMul(cfms.getSumFeeBs11()));
					cfms.setFeeRated1(calcMul(cfms.getFeeRate1()));
					cfms.setFeeRated2(calcMul(cfms.getFeeRate2()));
					cfms.setFeeRated3(calcMul(cfms.getFeeRate3()));
					cfms.setFeeRated4(calcMul(cfms.getFeeRate4()));
					cfms.setFeeRated5(calcMul(cfms.getFeeRate5()));
					cfms.setFeeRated6(calcMul(cfms.getFeeRate6()));
					cfms.setFeeRated7(calcMul(cfms.getFeeRate7()));
					cfms.setFeeRated8(calcMul(cfms.getFeeRate8()));
					cfms.setFeeRated9(calcMul(cfms.getFeeRate9()));
					cfms.setFeeRated10(calcMul(cfms.getFeeRate10()));
					cfms.setFeeRated11(calcMul(cfms.getFeeRate11()));
					Double sumFeed=calcMul(cfms.getSumFeeBs1() + cfms.getSumFeeBs2() + cfms.getSumFeeBs3()
											+ cfms.getSumFeeBs4() + cfms.getSumFeeBs5() + cfms.getSumFeeBs6() + cfms.getSumFeeBs7()
											+ cfms.getSumFeeBs8() + cfms.getSumFeeBs9() + cfms.getSumFeeBs10() + cfms.getSumFeeBs11());
					if(sumFeed!=0){
						cfms.setSumFeed(sumFeed);
						dataList.add(cfms);
					}
				}
				// 构建查询结果对象
				pgdata.setPage_num(Long.valueOf(query.getPageNum() + 1));
				pgdata.setPage_size(Long.valueOf(query.getPageSize()));
				pgdata.setTotal(Long.valueOf(total));
				pgdata.setData(dataList);

				pgdata.setSumFeeBs1(calcMul(cdrFeeMonthSum.getSumFeeBs1()));
				pgdata.setSumFeeBs2(calcMul(cdrFeeMonthSum.getSumFeeBs2()));
				pgdata.setSumFeeBs3(calcMul(cdrFeeMonthSum.getSumFeeBs3()));
				pgdata.setSumFeeBs4(calcMul(cdrFeeMonthSum.getSumFeeBs4()));
				pgdata.setSumFeeBs5(calcMul(cdrFeeMonthSum.getSumFeeBs5()));
				pgdata.setSumFeeBs6(calcMul(cdrFeeMonthSum.getSumFeeBs6()));
				pgdata.setSumFeeBs7(calcMul(cdrFeeMonthSum.getSumFeeBs7()));
				pgdata.setSumFeeBs8(calcMul(cdrFeeMonthSum.getSumFeeBs8()));
				pgdata.setSumFeeBs9(calcMul(cdrFeeMonthSum.getSumFeeBs9()));
				pgdata.setSumFeeBs10(calcMul(cdrFeeMonthSum.getSumFeeBs10()));
				pgdata.setSumFeeBs11(calcMul(cdrFeeMonthSum.getSumFeeBs11()));
				pgdata.setSumFeed(calcMul(cdrFeeMonthSum.getSumFeeBs1() + cdrFeeMonthSum.getSumFeeBs2()
						+ cdrFeeMonthSum.getSumFeeBs3() + cdrFeeMonthSum.getSumFeeBs4() + cdrFeeMonthSum.getSumFeeBs5()
						+ cdrFeeMonthSum.getSumFeeBs6() + cdrFeeMonthSum.getSumFeeBs7() + cdrFeeMonthSum.getSumFeeBs8()
						+ cdrFeeMonthSum.getSumFeeBs9() + cdrFeeMonthSum.getSumFeeBs10()
						+ cdrFeeMonthSum.getSumFeeBs11()));

				// 设定查询参数对象['pgdata'为JSP参数,不随意修改]
				mv.addObject("pgdata", pgdata);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【月结账单】加载出现异常,异常是:" + e.getMessage());
			pgdata.setPage_num(0L);
			pgdata.setPage_size(20L);
			pgdata.setTotal(0L);
			pgdata.setData(null);
			mv.addObject("pgdata", pgdata);
		}
		return mv;
	}

	/**
	 * 查询日结账单列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/cdrfeedaily_list")
	public ModelAndView cdrFeeDailyList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("/mbag/cdrfeedaily_list");
		try {
			String apiAccount = request.getParameter("apiAccount");
			String dateTime = request.getParameter("dateTime");
			MerchantAccount merchantAccount = merchantAccountService.getMerchantAccount(apiAccount);
			mv.addObject("merchantAccount", merchantAccount);
			mv.addObject("apiAccount", apiAccount);
			dateTime = DateUtil.parseDateFromYYMM(dateTime);
			mv.addObject("dateTime", dateTime);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【查询日结账单列表】出现异常,异常是:" + e.getMessage());
		}
		return mv;
	}

	/**
	 * 加载日结账单列表
	 * 
	 * @param request
	 * @return mv JSPTable
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/cdrfeedaily_list_data", produces = "application/json")
	public ModelAndView cdrFeeDailyListData(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("params") String params) {
		// 定义结果
		ModelAndView mv = new ModelAndView("/mbag/cdrfeedaily_list_data");
		ResultQuery pgdata = new ResultQuery();
		try {
			CdrFeeMonthSumQuery query = null;
			if (!StringUtil.isEmpty(params)) {
				query = JsonUtil.parseToObject(params, CdrFeeMonthSumQuery.class);
			}
			if (query == null) {
				query = new CdrFeeMonthSumQuery();
			}
			if (StringUtil.isEmpty(query.getDateTime())) {
				String tablesuffix = DateUtil.getLastMonthDate();
				query.setTablesuffix(tablesuffix);
			} else {
				String tablesuffix = DateUtil.parseDateToYYMM(query.getDateTime());
				query.setTablesuffix(tablesuffix);
				query.setDateTimeStart(tablesuffix + "01");
				query.setDateTimeEnd(tablesuffix + "31");
			}

			logger.info("【日结账单】页面传来的参数为:" + JsonUtil.toJsonString(query));

			Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query.getPageNum() - 1);
			Integer pageSize = query.getPageSize() == 0 ? Constant.PAGE_SIZE_20 : query.getPageSize();
			query.setPageNum(pageNum);
			query.setPageSize(pageSize);

			Integer total = cdrFeeMonthSumService.queryCdrFeeDailyAccount(query);
			List<CdrFeeMonthSum> list = cdrFeeMonthSumService.queryCdrFeeDailyByEntity(query);

			// 构建查询结果对象
			pgdata.setPage_num(Long.valueOf(query.getPageNum() + 1));
			pgdata.setPage_size(Long.valueOf(query.getPageSize()));
			pgdata.setTotal(Long.valueOf(total));
			if (null != list && list.size() > 0) {
				for (CdrFeeMonthSum cfms : list) {
					cfms.setDateTime(DateUtil.parseDateFromYYMMdd(cfms.getDateTime()));
					cfms.setSumFeeBsd1(calcMul(cfms.getSumFeeBs1()));
					cfms.setSumFeeBsd2(calcMul(cfms.getSumFeeBs2()));
					cfms.setSumFeeBsd3(calcMul(cfms.getSumFeeBs3()));
					cfms.setSumFeeBsd4(calcMul(cfms.getSumFeeBs4()));
					cfms.setSumFeeBsd5(calcMul(cfms.getSumFeeBs5()));
					cfms.setSumFeeBsd6(calcMul(cfms.getSumFeeBs6()));
					cfms.setSumFeeBsd7(calcMul(cfms.getSumFeeBs7()));
					cfms.setSumFeeBsd8(calcMul(cfms.getSumFeeBs8()));
					cfms.setSumFeeBsd9(calcMul(cfms.getSumFeeBs9()));
					cfms.setSumFeeBsd10(calcMul(cfms.getSumFeeBs10()));
					cfms.setSumFeeBsd11(calcMul(cfms.getSumFeeBs11()));
					cfms.setFeeRated1(calcMul(cfms.getFeeRate1()));
					cfms.setFeeRated2(calcMul(cfms.getFeeRate2()));
					cfms.setFeeRated3(calcMul(cfms.getFeeRate3()));
					cfms.setFeeRated4(calcMul(cfms.getFeeRate4()));
					cfms.setFeeRated5(calcMul(cfms.getFeeRate5()));
					cfms.setFeeRated6(calcMul(cfms.getFeeRate6()));
					cfms.setFeeRated7(calcMul(cfms.getFeeRate7()));
					cfms.setFeeRated8(calcMul(cfms.getFeeRate8()));
					cfms.setFeeRated9(calcMul(cfms.getFeeRate9()));
					cfms.setFeeRated10(calcMul(cfms.getFeeRate10()));
					cfms.setFeeRated11(calcMul(cfms.getFeeRate11()));
					cfms.setSumFeed(calcMul(cfms.getSumFeeBs1() + cfms.getSumFeeBs2() + cfms.getSumFeeBs3()
							+ cfms.getSumFeeBs4() + cfms.getSumFeeBs5() + cfms.getSumFeeBs6() + cfms.getSumFeeBs7()
							+ cfms.getSumFeeBs8() + cfms.getSumFeeBs9() + cfms.getSumFeeBs10() + cfms.getSumFeeBs11()));
				}
			}
			pgdata.setData(list);
			// 设定查询参数对象['pgdata'为JSP参数,不随意修改]
			mv.addObject("pgdata", pgdata);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【日结账单】加载出现异常,异常是:" + e.getMessage());
			pgdata.setPage_num(0L);
			pgdata.setPage_size(20L);
			pgdata.setTotal(0L);
			pgdata.setData(null);
			mv.addObject("pgdata", pgdata);
		}
		return mv;
	}

	/**
	 * 月结账单报表导出
	 * 
	 * @param request
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/monthexport")
	@ResponseBody
	public WebResult monthexport(HttpServletRequest request,
			@RequestParam(value = "params", required = false) String params) {

		logger.info("【月结账单导出】开始  params=" + params);
		WebResult webRs = new WebResult();
		try {
			User us = commonService.valideUser(request, Constant.USER_SESSION_UID);
			if (us != null) {
				logger.warn("用户登录超时!");
				webRs.setCode(-1);
				webRs.setMsg("用户登录超时!");
			}
			CdrFeeMonthSumQuery query = null;
			if (!StringUtil.isEmpty(params)) {
				query = JsonUtil.parseToObject(params, CdrFeeMonthSumQuery.class);
			}
			if (StringUtil.isEmpty(query.getDateTime())) {
				String tablesuffix = DateUtil.getLastMonthDate();
				query.setTablesuffix(tablesuffix);
			} else {
				query.setTablesuffix(DateUtil.parseDateToYYMM(query.getDateTime()));
			}

			Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query.getPageNum() - 1); // es分页码从0开始，所以-1
			query.setPageNum(pageNum);
			query.setPageSize(1000000);// 最大值
			// 调用导出业务
			String realPath = request.getSession().getServletContext().getRealPath("");
			webRs = cdrFeeMonthSumService.exportMonthExcel(query, realPath, request.getContextPath());
		} catch (Exception e) {
			logger.info("【月结账单下载】出现异常,异常是" + e.getMessage());
			e.printStackTrace();
		}

		logger.info("【月结账单导出】结束...");
		return webRs;
	}

	/**
	 * 日结账单报表导出
	 * 
	 * @param request
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/dayexport")
	@ResponseBody
	public WebResult dayexport(HttpServletRequest request,
			@RequestParam(value = "params", required = false) String params) {

		logger.info("【月结账单导出】开始  params=" + params);
		WebResult webRs = new WebResult();
		try {
			User us = commonService.valideUser(request, Constant.USER_SESSION_UID);
			if (us != null) {
				logger.warn("用户登录超时!");
				webRs.setCode(-1);
				webRs.setMsg("用户登录超时!");
			}
			CdrFeeMonthSumQuery query = null;
			if (!StringUtil.isEmpty(params)) {
				query = JsonUtil.parseToObject(params, CdrFeeMonthSumQuery.class);
			}
			if (StringUtil.isEmpty(query.getDateTime())) {
				String tablesuffix = DateUtil.getLastMonthDate();
				query.setTablesuffix(tablesuffix);
			} else {
				query.setTablesuffix(DateUtil.parseDateToYYMM(query.getDateTime()));
			}

			Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query.getPageNum() - 1); // es分页码从0开始，所以-1
			query.setPageNum(pageNum);
			query.setPageSize(1000000);// 最大值
			// 调用导出业务
			String realPath = request.getSession().getServletContext().getRealPath("");
			webRs = cdrFeeMonthSumService.exportDayExcel(query, realPath, request.getContextPath());
		} catch (Exception e) {
			logger.info("【月结账单下载】出现异常,异常是" + e.getMessage());
			e.printStackTrace();
		}

		logger.info("【月结账单导出】结束...");
		return webRs;
	}

	public static double calcMul(int d1) {
		BigDecimal b1 = new BigDecimal(Double.toString(d1));
		BigDecimal b3 = new BigDecimal(10000);
		return b1.divide(b3).doubleValue();
	}
}
