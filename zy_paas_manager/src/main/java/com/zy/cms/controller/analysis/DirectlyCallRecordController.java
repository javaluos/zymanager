package com.zy.cms.controller.analysis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.zy.cms.common.CommonService;
import com.zy.cms.common.Constant;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.elastic.service.EsService;
import com.zy.cms.service.manager.AccountBindInfoService;
import com.zy.cms.service.master.MerchantAccountService;
import com.zy.cms.util.DateUtil;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.MerchantAccount;
import com.zy.cms.vo.User;
import com.zy.cms.vo.UserCurrent;
import com.zy.cms.vo.UserInfo;
import com.zy.cms.vo.query.ResultQuery;
import com.zy.cms.vo.query.VoiceQuery;

/**
 * 直拨发送记录
 * @author Jason xu
 * @date 2016-11-15
 *
 */
@Controller
@RequestMapping(value = "/directlyCallRecord")
public class DirectlyCallRecordController {

	private static final ZyLogger logger = ZyLogger.getLogger(DirectlyCallRecordController.class);
	
	@Resource
	private CommonService commonService;
	
	@Resource
	private UserInfo userInfo;

	@Autowired
	private EsService esService;
	
	@Resource
	private MerchantAccountService merchantAccountService;
	
	@Autowired
	private AccountBindInfoService accountBindInfoService;

	/**
	 * 跳转到直拨发送记录列表
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/directly_call_list", method = RequestMethod.GET)
	public ModelAndView app(HttpServletRequest req) {

		ModelAndView mv = new ModelAndView("/analysis/directly_call_list");
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
		mv.addObject("datetimeStart",DateUtil.formatDate(new Date(),"yyyy-MM-dd")+" 00:00:00");
		mv.addObject("datetimeEnd",DateUtil.formatDate(new Date(),"yyyy-MM-dd")+" 23:59:59");
		
		return mv;
	}
	

    /**
     * 加载直拨发送记录列表中的数据
     * @param request
     * @param response
     * @param params
     * @return
     */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/directly_call_list_data", produces = "application/json")
	public ModelAndView getVoiceNoticeList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("params") String params) {
		try {
			User us = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
			if (us == null) {
				logger.warn("用户登录超时!");
				return new ModelAndView("redirect:/login.html");
			}
			VoiceQuery query = null;
			MerchantAccount merchantAccount=null;
			if (!StringUtil.isEmpty(params)) {
				query = JsonUtil.parseToObject(params, VoiceQuery.class);
				if(!StringUtil.isEmpty(query.getMerchantPhone())){
				    merchantAccount=merchantAccountService.getMerchantAccountByPhone(query.getMerchantPhone());
					if(merchantAccount==null){
						ModelAndView mv = new ModelAndView("/analysis/directly_call_list_data");
						return mv;
					}
				    query.setApiAccount(merchantAccount.getApiAccount());
				}
			}
			if(!StringUtil.isEmpty(query.getStarttime())){
				query.setStarttimeL(DateUtil.date2TimeStamp(query.getStarttime(), "yyyy-MM-dd HH:mm:ss") / 1000);
			}
			if(!StringUtil.isEmpty(query.getEndtime())){
				query.setEndtimeL(DateUtil.date2TimeStamp(query.getEndtime(), "yyyy-MM-dd HH:mm:ss") / 1000);
			}
			if(query.getQuerytype()==0){
			    	query.setQuerytype(-2);
			}
			 
			List<String> apiAccounts=accountBindInfoService.getApiAccountByBusName(us.getUserName());
		    List<String> apiAccountsLower=new ArrayList<String>();
		    if(apiAccounts.size()>0){
		    	for(String apiAccount:apiAccounts){
		    		apiAccountsLower.add(apiAccount.toLowerCase());
		    	}
		    }
			query.setApiAccounts(apiAccountsLower);
			
			Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query.getPageNum() - 1); // es分页码从0开始，所以-1
			Integer pageSize = query.getPageSize() == 0 ? Constant.PAGE_SIZE_20 : query.getPageSize();
			query.setPageNum(pageNum);
			query.setPageSize(pageSize);

			logger.info("页面传来的参数是："+params+" 查询直拨发送记录条件=" + JsonUtil.toJsonString(query));
			
			String result = this.esService.searchCdr(query, Constant.ES_CDR_INDEX_NAME, Constant.ES_CDR_INDEX_TYPE);

			Map rs = (Map)JSON.parse(result);
			List<Map> dataMap=(List<Map>) rs.get("data"); 
			List<Map> list = new ArrayList<Map>();
			for(Map map:dataMap){
				String apiAccount=(String) map.get("apiAccount");
				if(merchantAccount==null){
					merchantAccount=merchantAccountService.getMerchantAccount(apiAccount);
				}
				if(merchantAccount==null){
					merchantAccount=new MerchantAccount();
				}
				map.put("merchantPhone", merchantAccount.getMerchantPhone());
				map.put("businessName", merchantAccount.getBusinessName());
			    map.put("calleeInviteTime2", DateUtil.timeStamp2Date((Integer) map.get("calleeInviteTime")));
			    
				list.add(map);
			}
			
			rs.put("data", list);
			rs.put("times", rs.get("times"));
			rs.put("total_page",rs.get("total_page") );
			rs.put("total", rs.get("total"));
			rs.put("page_num", rs.get("page_num"));
			rs.put("page_size", rs.get("page_size"));
			result=JsonUtil.toJsonString(rs);
			ResultQuery cdrQuery = JsonUtil.parseToObject(result, ResultQuery.class);

			// 定义结果
			ModelAndView mv = new ModelAndView("/analysis/directly_call_list_data");
			mv.addObject("pgdata", cdrQuery);// 设定查询参数对象

			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("查询直拨发送记录出现异常,异常为{0}",new Object[]{e.getMessage()},null);
			ModelAndView mv = new ModelAndView("/analysis/directly_call_list_data");
			return mv;
		}

	}

}
