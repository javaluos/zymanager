package com.zy.cms.controller.analysis;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.zy.cms.common.CommonService;
import com.zy.cms.common.Constant;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.service.manager.AccountBindInfoService;
import com.zy.cms.service.manager.CdrDailyStatisticsService;
import com.zy.cms.service.master.MerchantAccountService;
import com.zy.cms.util.DateUtil;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.MerchantAccount;
import com.zy.cms.vo.User;
import com.zy.cms.vo.UserCurrent;
import com.zy.cms.vo.UserInfo;
import com.zy.cms.vo.manager.CdrDailyStatistics;
import com.zy.cms.vo.manager.CdrStatisticsResult;
import com.zy.cms.vo.query.CdrDailyStatisticsQuery;
import com.zy.cms.vo.query.ResultQuery;

/**
 * 
 * 语音通知汇总  语音验证码汇总 直拨电话汇总  回拨电话汇总
 * @author jason xu
 * @date 2016-11-2
 * 
 */
@Controller
@RequestMapping(value = "/cdrDailyStatistics")
public class CdrDailyStatisticsController {

	private static final ZyLogger logger = ZyLogger.getLogger(CdrDailyStatisticsController.class);
	
	@Resource
	private CommonService commonService;
	
	@Resource
	private UserInfo userInfo;

	@Autowired
	private CdrDailyStatisticsService cdrDailyStatisticsService;
	
	@Resource
	private MerchantAccountService merchantAccountService;

	@Autowired
	private AccountBindInfoService accountBindInfoService;
	
	/**
	 * 跳转到语音通知汇总、直拨电话、语音验证码汇总列表
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/cdr_daily_statistics_list", method = RequestMethod.GET)
	public ModelAndView app(HttpServletRequest req) {

		ModelAndView mv=new ModelAndView("/cdrDailyStatistics/cdrlist");//语音通知汇总页面
		try {
			String type=req.getParameter("type");
			if("5".equals(type)){
				mv= new ModelAndView("/messageDailyStatistics/cdrlist");//语音验证码汇总页面
			}else if("3".equals(type)){
				mv= new ModelAndView("/cdrDailyStatistics/directlyCall_summary_list");//直拨电话汇总页面
			}else if("2".equals(type)){
				mv= new ModelAndView("/cdrDailyStatistics/numsecurity_summary_list");//号码卫士汇总页面
			}
			 
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
			mv.addObject("datetimeStart",DateUtil.formatDate(new Date(new Date().getTime()-24*60*60*1000),"yyyy-MM-dd"));
			mv.addObject("datetimeEnd",DateUtil.formatDate(new Date(new Date().getTime()-24*60*60*1000),"yyyy-MM-dd"));
			return mv;
		} catch (Exception e) {
			logger.info("跳转页面出现异常,异常原因是:"+e.getMessage());
			e.printStackTrace();
			return mv;
		}
	}

    /**
     * 加载语音通知汇总、直拨电话、语言验证码、号码卫士汇总列表中的数据
     * @param request
     * @param response
     * @param params
     * @return
     */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/cdr_daily_statistics_list_data", produces = "application/json")
	public ModelAndView getCdrDailyStatisticsList(HttpServletRequest request, HttpServletResponse response, 
		   @RequestParam("params") String params) {
		ModelAndView mv = new ModelAndView("/cdrDailyStatistics/cdrlist_list_data");//语音通知汇总页面
		try {
			
			User us = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
			if (us == null) {
				logger.warn("用户登录超时!");
				return new ModelAndView("redirect:/login.html");
			}
			
			CdrDailyStatisticsQuery query = null;
			if (!StringUtil.isEmpty(params)) {
				query = JsonUtil.parseToObject(params, CdrDailyStatisticsQuery.class);
				if(!StringUtil.isEmpty(query.getMerchantPhone())){
					MerchantAccount merchantAccount=merchantAccountService.getMerchantAccountByPhone(query.getMerchantPhone());
					if(merchantAccount==null){
						return mv;
					}
					query.setApiAccount(merchantAccount.getApiAccount());
				}
			}
			if(query.getQuerytype()==5){
				mv = new ModelAndView("/messageDailyStatistics/cdrlist_list_data");//语音验证码汇总页面
			}else if(query.getQuerytype()==3){
				mv= new ModelAndView("/cdrDailyStatistics/directlyCall_summary_list_data");//直拨电话汇总页面
			}else if(query.getQuerytype()==2){
				mv= new ModelAndView("/cdrDailyStatistics/numsecurity_summary_list_data");//号码卫士电话汇总页面
			}
			
			List<String> apiAccounts=accountBindInfoService.getApiAccountByBusName(us.getUserName());
			query.setApiAccounts(apiAccounts);
			
			Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query.getPageNum() - 1);
			Integer pageSize = query.getPageSize() == 0 ? Constant.PAGE_SIZE_20 : query.getPageSize();
			query.setPageNum(pageNum);
			query.setPageSize(pageSize);

			logger.warn("【语音通知汇总、直拨电话、语言验证码、号码卫士汇总列表】页面传过来的参数是:"+params+"查询条件是:"+JsonUtil.toJsonString(query));
			
			CdrStatisticsResult cdrStatisticsResult = cdrDailyStatisticsService.queryVoiceUploadCountByEntity(query);
			List<CdrDailyStatistics> list = cdrDailyStatisticsService.queryCdrDailyStatisticsListByEntity(query);

			if(list.size()>0){
				for(CdrDailyStatistics cds:list){
					MerchantAccount merchantAccount=merchantAccountService.getMerchantAccount(cds.getApi_account());
					if(merchantAccount==null){
						merchantAccount=new MerchantAccount();
					}
					cds.setMerchant_phone(merchantAccount.getMerchantPhone());
					cds.setBusiness_name(merchantAccount.getBusinessName());
					cds.setDate_time(DateUtil.formatDate(cds.getDate_time()));
				}
			}else{
				cdrStatisticsResult=new CdrStatisticsResult();
			}
			
			// 构建查询结果对象
			ResultQuery pgdata = new ResultQuery();
			pgdata.setPage_num(Long.valueOf(query.getPageNum() + 1));
			pgdata.setPage_size(Long.valueOf(query.getPageSize()));
			pgdata.setTotal(Long.valueOf(cdrStatisticsResult.getTotal_count()));
			pgdata.setData(list);
			pgdata.setSumHoldingTime(cdrStatisticsResult.getSumHoldingTime()); //通话时长(总和)
			pgdata.setSumBHoldingTimes(cdrStatisticsResult.getSumBHoldingTimes());//B路通话时长
            pgdata.setSumResponseTimes(cdrStatisticsResult.getTotal_sum_response_times());//通话总计费条数
            pgdata.setSumCallSucTimes(cdrStatisticsResult.getTotal_call_suc_times());
            pgdata.setSumCallTimes(cdrStatisticsResult.getTotal_call_times());//通话总次数
            pgdata.setSumFeeTimes(cdrStatisticsResult.getTotal_fee_time());//通话总计费时长
            pgdata.setPctCallSucs(cdrStatisticsResult.getTotal_call_suc());//平均连通率
            pgdata.setPctResponseSucs(cdrStatisticsResult.getTotal_response_suc());//平均应答率
            pgdata.setAvgAcds(cdrStatisticsResult.getTotal_avg_acd());//平均ACD
			
			// 设定查询参数对象['pgdata'为JSP参数,不随意修改]
			mv.addObject("pgdata", pgdata);
			return mv;
		}
		catch(Exception e){
			e.printStackTrace();
			logger.info("【加载语音通知汇总、直拨电话、语言验证码汇总列表】出现异常的原因是："+e.getMessage());
			return mv;
		}
	}
	
	/**
	 * 跳转到回拨电话汇总列表
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/callBack_summary_list", method = RequestMethod.GET)
	public ModelAndView callBackSummary(HttpServletRequest req) {

		ModelAndView mv=new ModelAndView("/cdrDailyStatistics/callBack_summary_list");//语音通知汇总页面
		try {
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
			mv.addObject("datetimeStart",DateUtil.formatDate(new Date(new Date().getTime()-24*60*60*1000),"yyyy-MM-dd"));
			mv.addObject("datetimeEnd",DateUtil.formatDate(new Date(new Date().getTime()-24*60*60*1000),"yyyy-MM-dd"));
			return mv;
		} catch (Exception e) {
			logger.info("跳转到【回拨电话汇总列表】页面出现异常,异常原因是:"+e.getMessage());
			e.printStackTrace();
			return mv;
		}
	}

    /**
     * 加载回拨电话汇总列表中的数据
     * @param request
     * @param response
     * @param params
     * @return
     */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/callBack_summary_list_data", produces = "application/json")
	public ModelAndView callBackSumaryListData(HttpServletRequest request, HttpServletResponse response, 
		   @RequestParam("params") String params) {
		ModelAndView mv = new ModelAndView("/cdrDailyStatistics/callBack_summary_list_data");//语音通知汇总页面
		try {
			User us = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
			if (us == null) {
				logger.warn("用户登录超时!");
				return new ModelAndView("redirect:/login.html");
			}
			
			CdrDailyStatisticsQuery query = null;
			if (!StringUtil.isEmpty(params)) {
				query = JsonUtil.parseToObject(params, CdrDailyStatisticsQuery.class);
				if(!StringUtil.isEmpty(query.getMerchantPhone())){
					MerchantAccount merchantAccount=merchantAccountService.getMerchantAccountByPhone(query.getMerchantPhone());
					if(merchantAccount==null){
						return mv;
					}
					query.setApiAccount(merchantAccount.getApiAccount());
				}
			}
			query.setQuerytype(1);
			
			Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query.getPageNum() - 1);
			Integer pageSize = query.getPageSize() == 0 ? Constant.PAGE_SIZE_20 : query.getPageSize();
			query.setPageNum(pageNum);
			query.setPageSize(pageSize);

			List<String> apiAccounts=accountBindInfoService.getApiAccountByBusName(us.getUserName());
			query.setApiAccounts(apiAccounts);
			
			logger.warn("【回拨电话汇总列表】页面传过来的参数是:"+params+"查询条件是:"+JsonUtil.toJsonString(query));
			
			CdrStatisticsResult cdrStatisticsResult = cdrDailyStatisticsService.queryCdrDailyStatisticsCountABByEntity(query);
			List<CdrDailyStatistics> list = cdrDailyStatisticsService.queryCdrDailyStatisticsListByEntity(query);

			if(list.size()>0){
				for(CdrDailyStatistics cds:list){
					MerchantAccount merchantAccount=merchantAccountService.getMerchantAccount(cds.getApi_account());
					if(merchantAccount==null){
						merchantAccount=new MerchantAccount();
					}
					cds.setMerchant_phone(merchantAccount.getMerchantPhone());
					cds.setBusiness_name(merchantAccount.getBusinessName());
					cds.setDate_time(DateUtil.formatDate(cds.getDate_time()));
				}
			}else{
				cdrStatisticsResult=new CdrStatisticsResult();
			}
			
			// 构建查询结果对象
			ResultQuery pgdata = new ResultQuery();
			pgdata.setPage_num(Long.valueOf(query.getPageNum() + 1));
			pgdata.setPage_size(Long.valueOf(query.getPageSize()));
			pgdata.setTotal(Long.valueOf(cdrStatisticsResult.getTotal_count()));
			pgdata.setData(list);
            pgdata.setSumCallTimes(cdrStatisticsResult.getTotal_call_times());//通话总次数
            pgdata.setSumFeeTimes(cdrStatisticsResult.getTotal_fee_time());//通话总计费时长
            pgdata.setSumAHoldingTimes(cdrStatisticsResult.getSumAHoldingTimes());//平均A路通话时长
            pgdata.setSumBHoldingTimes(cdrStatisticsResult.getSumBHoldingTimes()); //平均B路通话时长
            pgdata.setPctACallSucdoubles(cdrStatisticsResult.getPctACallSucdoubles()); //平均A路接通率
            pgdata.setPctBCallSucdoubles(cdrStatisticsResult.getPctBCallSucdoubles()); //平均B路接通率
            pgdata.setPctAResponseSucdouble(cdrStatisticsResult.getPctAResponseSucdouble()); //平均A路应答率
            pgdata.setPctBResponseSucdouble(cdrStatisticsResult.getPctBResponseSucdouble());//平均B路应答率
            pgdata.setAvgACalleepddTime(cdrStatisticsResult.getAvgACalleepddTime()); //平均A路接通时延
            pgdata.setAvgBCalleepddTime(cdrStatisticsResult.getAvgBCalleepddTime()); //平均B路接通时延
            pgdata.setAvgAcds(cdrStatisticsResult.getTotal_avg_acd());//平均ACD
			
			// 设定查询参数对象['pgdata'为JSP参数,不随意修改]
			mv.addObject("pgdata", pgdata);
			return mv;
		}
		catch(Exception e){
			e.printStackTrace();
			logger.info("【回拨电话汇总列表】出现异常的原因是："+e.getMessage());
			return mv;
		}
	}
}
