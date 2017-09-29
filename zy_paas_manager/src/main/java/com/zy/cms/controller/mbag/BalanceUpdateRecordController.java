package com.zy.cms.controller.mbag;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.zy.cms.common.CommonService;
import com.zy.cms.common.Constant;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.service.manager.AccountBindInfoService;
import com.zy.cms.service.master.MerchantAccountBalanceService;
import com.zy.cms.service.master.MerchantAccountService;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.BalanceUpdateRecord;
import com.zy.cms.vo.MerchantAccount;
import com.zy.cms.vo.User;
import com.zy.cms.vo.query.AccBalUpdateRecordQuery;
import com.zy.cms.vo.query.ResultQuery;

@Controller
@RequestMapping("/moneybag")
public class BalanceUpdateRecordController {
	
	private static final ZyLogger logger = ZyLogger.getLogger(BalanceUpdateRecordController.class);

	@Resource
	private MerchantAccountService merchantAccountService;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private MerchantAccountBalanceService merchantAccountBalanceService;
	
	@Autowired
	private AccountBindInfoService accountBindInfoService;
	
	/**
	 * 查询账号余额充值记录列表UI
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/actbalance/updatelog_list")
	public ModelAndView queryActBalanceBList(HttpServletRequest request, HttpServletResponse response) {
		// 定义结果
		ModelAndView mv = new ModelAndView("/mbag/actbal_update_list");
		String apiAccount = request.getParameter("apiAccount");
		if(!StringUtil.isEmpty(apiAccount)){
			MerchantAccount merchantAccount = merchantAccountService.getMerchantAccount(apiAccount);
			mv.addObject("merchantAccount", merchantAccount.getMerchantPhone());
		}
		return mv;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/actbalance/updatelog_list_data", produces = "application/json")
	public ModelAndView queryActBalanceData(HttpServletRequest request, HttpServletResponse response,
		   @RequestParam("params") String params) {
		// 定义结果
		ModelAndView mv = new ModelAndView("/mbag/actbal_update_data");
		User us = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
		if (us == null) {
			logger.warn("用户登录超时!");
			return new ModelAndView("redirect:/login.html");
		}
		
		AccBalUpdateRecordQuery query = null;
		if (!StringUtil.isEmpty(params)) {
			query = JsonUtil.parseToObject(params, AccBalUpdateRecordQuery.class);
		}
		if (query == null) {
			query = new AccBalUpdateRecordQuery();
		}
		String merchantAccount = query.getMerchantAccount();
		if(!StringUtil.isEmpty(merchantAccount)){
			query.setMerchantAccount(merchantAccount.trim());
		}
		String operator = query.getOperator();
		if(!StringUtil.isEmpty(operator)){
			query.setOperator(operator.trim());
		}
		Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query.getPageNum() - 1);
		Integer pageSize = query.getPageSize() == 0 ? Constant.PAGE_SIZE_20 : query.getPageSize();
		query.setPageNum(pageNum);
		query.setPageSize(pageSize);

		List<String> apiAccounts=accountBindInfoService.getApiAccountByBusName(us.getUserName());
		query.setApiAccounts(apiAccounts);
		Integer total = merchantAccountBalanceService.queryMchAcctBalUpdateCount(query);
		List<BalanceUpdateRecord> list = merchantAccountBalanceService.queryMchAcctBalUpdateRecords(query);

		// 构建查询结果对象
		ResultQuery pgdata = new ResultQuery();
		pgdata.setPage_num(Long.valueOf(query.getPageNum() + 1));
		pgdata.setPage_size(Long.valueOf(query.getPageSize()));
		pgdata.setTotal(Long.valueOf(total));
		pgdata.setData(list);
		
		// 设定查询参数对象['pgdata'为JSP参数,不随意修改]
		mv.addObject("pgdata", pgdata);
		return mv;
	}
	
	@RequestMapping("/do_balance_update")
	public ModelAndView doBalanceUpdate(HttpServletRequest request, String apiAccount, String merchantAccount,
			String businessName, String updateFee, String comment){
		boolean result = false;
		User user = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
		ModelAndView mv = null;
		result = merchantAccountBalanceService.addAccountBalanceUpdateRecord(apiAccount, merchantAccount, businessName, 
				updateFee, user.getUserName(), comment);
		if(result){
			mv = new ModelAndView("/mbag/actbalance_list");
			if(!StringUtil.isEmpty(apiAccount)){
				MerchantAccount mchAccount = merchantAccountService.getMerchantAccount(apiAccount);
				mv.addObject("merchantAccount", mchAccount.getMerchantPhone());
			}
		}else{
			mv = new ModelAndView("/moneybag/actchargeindex?apiAccount="+apiAccount+"");
		}
		return mv;
	}
	
}
