package com.zy.cms.controller.audit;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zy.cms.common.CommonService;
import com.zy.cms.common.Constant;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.service.master.MerchantAccountService;
import com.zy.cms.service.master.MerchantSmsManageService;
import com.zy.cms.service.sms.SmsMerchantSendFilterService;
import com.zy.cms.util.CookieUtil;
import com.zy.cms.util.DateUtil;
import com.zy.cms.util.HttpClientUtil;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.MerchantAccount;
import com.zy.cms.vo.MerchantSmsSigner;
import com.zy.cms.vo.MerchantSmsSignerVo;
import com.zy.cms.vo.SmsSendFilter;
import com.zy.cms.vo.User;
import com.zy.cms.vo.query.MerchantSmsSignerQuery;
import com.zy.cms.vo.query.ResultQuery;
import com.zy.cms.vo.query.SmsSendFilterQuery;
import com.zy.cms.vo.query.SubSmsSendFilterQuery;

/**
 * 短信拦截后待人工审核
 * @author JasonXu
 *
 */
@Controller
@RequestMapping(value = "/sms_send_filter")
public class SmsSendFilterController {

	private static final ZyLogger logger = ZyLogger.getLogger(SmsSendFilterController.class);

	@Resource
	private MerchantSmsManageService merchantSmsManageService;

	@Resource
	private MerchantAccountService merchantAccountService;

	@Resource
	private CommonService commonService;
	
	@Resource
	private SmsMerchantSendFilterService smsMerchantSendFilterService;
	
	private static ExecutorService clientThread = Executors.newFixedThreadPool(15);// 接收消息推送线程
	
	@Value("${sendToRmiVoiceProxyURL}")
	private String sendToRmiVoiceProxyURL;
	
	@Value("${PUSH_STATUS_URL}")
	private String pushStatusUrl;
	
	@Value("${LOG_TOES_URL}")
	private String logToesUrl;
		
	/**
	 * 查询短信签名审核列表UI
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/to_list")
	public ModelAndView queryAuditList(HttpServletRequest request, HttpServletResponse response) {
		// 定义结果
		logger.info("【加载短信签名列表】");
		ModelAndView mv = new ModelAndView("/audit/send_filter_list");
		return mv;
	}

	/**
	 * 加载短信签名列表数据
	 * 
	 * @param request
	 * @param response
	 * @param params
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/list_data", produces = "application/json")
	public ModelAndView queryAuditListData(HttpServletRequest request, HttpServletResponse response,@RequestParam("params") String params) {
		try {
			ModelAndView mv = new ModelAndView("/audit/send_filter_list_data");
			User us = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
			if (us == null) {
				logger.warn("用户登录超时!");
				return new ModelAndView("redirect:/login.html");
			}
			
			SmsSendFilterQuery query = null;
			logger.info("【(待)短信签名审核列表】页面传来参数={0}", new Object[] { params }, null);
			if (!StringUtil.isEmpty(params)) {
				query = JsonUtil.parseToObject(params, SmsSendFilterQuery.class);
			}
			if (query == null) {
				query = new SmsSendFilterQuery();
			}

			Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query.getPageNum() - 1);
			Integer pageSize = query.getPageSize() == 0 ? Constant.PAGE_SIZE_20 : query.getPageSize();
			query.setPageNum(pageNum);
			query.setPageSize(pageSize);
			
			if(StringUtils.isNotEmpty(query.getBusinessName()) || StringUtils.isNotEmpty(query.getMerchantPhone())){
				List<String> apiAccounts=merchantAccountService.querylistByNameOrPhone(query.getBusinessName(), query.getMerchantPhone());
				if(apiAccounts!=null && apiAccounts.size()>0){
					query.setApiAccounts(apiAccounts);
				}else{
					query.setApiAccount("123");
				}
			}
			logger.info("【(待)短信签名审核列表】参数={0}", new Object[] { query }, null);
			
			
			Integer total = smsMerchantSendFilterService.queryCountByEntity(query);
			List<SmsSendFilter> list = smsMerchantSendFilterService.queryListByEntity(query);
			
			String[] apis = toApiArrys(list);
			Map<String, MerchantAccount> accountMaps = merchantAccountService.queryMerchantAccountListByApis(apis);
			
			// 构建查询结果对象
			ResultQuery pgdata = new ResultQuery();
			pgdata.setPage_num(Long.valueOf(query.getPageNum() + 1));
			pgdata.setPage_size(Long.valueOf(query.getPageSize()));
			pgdata.setTotal(Long.valueOf(total));
			pgdata.setData(list);
			// 设定查询参数对象['pgdata'为JSP参数,不随意修改]
			mv.addObject("pgdata", pgdata);
			mv.addObject("accountMaps", accountMaps);
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【获取(待)短信签名】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
			return null;
		}
	}
	
	
	
	/**
	 * 查询短信签名审核列表UI
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/to_list_detail")
	public ModelAndView queryAuditListDetail(HttpServletRequest request, HttpServletResponse response,String apiAccount,String smsContent,String blackContent) {
		// 定义结果
		logger.info("【加载短信签名列表】");
		ModelAndView mv = new ModelAndView("/audit/send_filter_detail_list");
		mv.addObject("apiAccount", apiAccount);
		try {
			if(StringUtils.isNotEmpty(smsContent)){
				mv.addObject("smsContent", new String(smsContent.getBytes("iso8859-1"),"utf-8"));
			}
			if(StringUtils.isNotEmpty(blackContent)){
				mv.addObject("blackContent", new String(blackContent.getBytes("iso8859-1"),"utf-8"));
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return mv;
	}

	
	
	/**
	 * 加载短信签名列表数据
	 * 
	 * @param request
	 * @param response
	 * @param params
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/list_data_detail", produces = "application/json")
	public ModelAndView queryAuditListDataDetail(HttpServletRequest request, HttpServletResponse response,@RequestParam("params") String params) {
		try {
			ModelAndView mv = new ModelAndView("/audit/send_filter_list_detail_data");
			User us = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
			if (us == null) {
				logger.warn("用户登录超时!");
				return new ModelAndView("redirect:/login.html");
			}
			
			SmsSendFilterQuery query = null;
			logger.info("【(待)短信签名审核列表】页面传来参数={0}", new Object[] { params }, null);
			if (!StringUtil.isEmpty(params)) {
				query = JsonUtil.parseToObject(params, SmsSendFilterQuery.class);
			}
			if (query == null) {
				query = new SmsSendFilterQuery();
			}

			Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query.getPageNum() - 1);
			Integer pageSize = query.getPageSize() == 0 ? Constant.PAGE_SIZE_20 : query.getPageSize();
			query.setPageNum(pageNum);
			query.setPageSize(pageSize);
			logger.info("【(待)短信签名审核列表】参数={0}", new Object[] { query }, null);
			
			
			Integer total = smsMerchantSendFilterService.queryCountByEntity(query);
			List<SmsSendFilter> list = smsMerchantSendFilterService.queryListDetailByEntity(query);
			
			String[] apis = toApiArrys(list);
			Map<String, MerchantAccount> accountMaps = merchantAccountService.queryMerchantAccountListByApis(apis);
			
			// 构建查询结果对象
			ResultQuery pgdata = new ResultQuery();
			pgdata.setPage_num(Long.valueOf(query.getPageNum() + 1));
			pgdata.setPage_size(Long.valueOf(query.getPageSize()));
			pgdata.setTotal(Long.valueOf(total));
			pgdata.setData(list);
			// 设定查询参数对象['pgdata'为JSP参数,不随意修改]
			mv.addObject("pgdata", pgdata);
			mv.addObject("accountMaps", accountMaps);
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【获取(待)短信签名】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
			return null;
		}
	}
	/**
	 * 审核通过
	 * @param request
	 * @param response
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/auditPaas", produces = "application/json")
	@ResponseBody
	public String auditPaas(HttpServletRequest request, HttpServletResponse response,@RequestParam("params") String params) {
		try {
			User us = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
			if (us == null) {
				logger.warn("用户登录超时!");
				return "1";
			}
			
			SmsSendFilterQuery query = null;
			logger.info("【短信人工审核通过】页面传来参数={0}", new Object[] { params }, null);
			if (!StringUtil.isEmpty(params)) {
				query = JsonUtil.parseToObject(params, SmsSendFilterQuery.class);
			}
			if (query == null) {
				query = new SmsSendFilterQuery();
			}
			List<SubSmsSendFilterQuery> subSmsSendFilterQueryList=query.getList();
			if(null!=subSmsSendFilterQueryList&&subSmsSendFilterQueryList.size()>0){
				for(SubSmsSendFilterQuery sub:subSmsSendFilterQueryList){
					if(null!=sub){
						SmsSendFilterQuery queryDB = new SmsSendFilterQuery();
						queryDB.setApiAccount(sub.getApiAccount());
						queryDB.setSmsContent(sub.getSmsContent());
						List<SmsSendFilter> list = smsMerchantSendFilterService.queryAuditPaasSms(queryDB);
						List<String> smsIdList=new ArrayList<String>();
						if(null!=list&&list.size()>0){
							for(final SmsSendFilter s:list){
								smsIdList.add(s.getSmsId());
								clientThread.execute(new Runnable() {
									@Override
									public void run() {
										try {
											String  data=JsonUtil.objectToJson(s);
											HttpClientUtil.INSTANCE.httpPost(sendToRmiVoiceProxyURL, data, null);
										} catch (Exception e) {
											e.printStackTrace();
											//
											logger.error("【短信人工审核通过】发送RMI,异常error=" + e.getMessage());
										}
									}
								});
							}
							//delete from db
							SmsSendFilterQuery	delete = new SmsSendFilterQuery();
							delete.setSmsIdList(smsIdList);
							smsMerchantSendFilterService.deleteBySmsIdList(delete);
						}
					}
				}
			}
			
		
			
			return "2";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【短信人工审核通过】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
			return null;
		}
	}
	
	/**
	 * 审核通过
	 * @param request
	 * @param response
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/auditNotPaas", produces = "application/json")
	@ResponseBody
	public String auditNotPaas(HttpServletRequest request, HttpServletResponse response,@RequestParam("params") String params) {
		try {
			User us = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
			if (us == null) {
				logger.warn("用户登录超时!");
				return "1";
			}
			
			SmsSendFilterQuery query = null;
			logger.info("【短信人工审核拒绝】页面传来参数={0}", new Object[] { params }, null);
			if (!StringUtil.isEmpty(params)) {
				query = JsonUtil.parseToObject(params, SmsSendFilterQuery.class);
			}
			if (query == null) {
				query = new SmsSendFilterQuery();
			}
			
			
			List<SubSmsSendFilterQuery> subSmsSendFilterQueryList=query.getList();
			if(null!=subSmsSendFilterQueryList&&subSmsSendFilterQueryList.size()>0){
				for(SubSmsSendFilterQuery sub:subSmsSendFilterQueryList){
					if(null!=sub){
						SmsSendFilterQuery queryDB = new SmsSendFilterQuery();
						queryDB.setApiAccount(sub.getApiAccount());
						queryDB.setSmsContent(sub.getSmsContent());
						List<SmsSendFilter> list = smsMerchantSendFilterService.queryAuditPaasSms(queryDB);
						List<String> smsIdList=new ArrayList<String>();
						if(null!=list&&list.size()>0){
							for(final SmsSendFilter s:list){
								smsIdList.add(s.getSmsId());
								logToes(s);//写入es
								pushStatus(s);//推状态报告给客户
							}
							//delete from db
							SmsSendFilterQuery	delete = new SmsSendFilterQuery();
							delete.setSmsIdList(smsIdList);
							smsMerchantSendFilterService.deleteBySmsIdList(delete);
						}
						
					}
				}
			}
			
		
			
			return "2";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【短信人工审核拒绝】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
			return null;
		}
	}
	
    private void logToes(final SmsSendFilter s) {
    	clientThread.execute(new Runnable() {
			@Override
			public void run() {
				try {
					String  data=JsonUtil.objectToJson(s);
					HttpClientUtil.INSTANCE.httpPost(logToesUrl, data, null);
				} catch (Exception e) {
					 logger.error("【短信人工审核拒绝】写入es,异常error=" + e.getMessage());
				}
			}
		});
	}
    
   
	/**
     * 补推状态报告
     *
     * @param smsPv
     */
    public void pushStatus(final SmsSendFilter smsPv) {

        // 推送状态报告
        clientThread.execute(new Runnable() {
            @Override
            public void run() {

                try {
        			
                    // 够造组装报告数据
                    Map<String, String> statusMap = new HashMap<String, String>();
                    statusMap.put("apiAccount", smsPv.getApiAccount());
                    statusMap.put("appId", smsPv.getAppId());
                    statusMap.put("smsId", smsPv.getSmsId());
                    statusMap.put("mobile", smsPv.getReceiveMobile());     
                    statusMap.put("smsCateGory", smsPv.getSmsCategory());
                    statusMap.put("userData", smsPv.getUserData());
                    statusMap.put("fee", "0");
                    statusMap.put("status", "1");// 失败
                    statusMap.put("receiveChannelStatusDesc", "-101");
                    statusMap.put("receiveStatusDescChs", "审核不通过");
                    statusMap.put("createTime", smsPv.getCreateTime().getTime()+"");//smsPv.getTimerId()
                    statusMap.put("smsTime", DateUtil.getDateTime());
                    statusMap.put("statusPushAddr", smsPv.getStatusPushAddr());
                    statusMap.put("protocolType", smsPv.getProtocolType() + "");
                    statusMap.put("uId", smsPv.getuId() + "");
                  

                    String data = JsonUtil.toJsonString(statusMap);
                    String result =HttpClientUtil.INSTANCE.httpPost(pushStatusUrl, data, null);

                    logger.info("【短信人工审核】,组装报告数据,pushResult=" + result);
                } catch (Exception e) {	
                    logger.error("【短信人工审核】,组装报告数据,异常error=" + e.getMessage());
                }
            }
        });
    }
    
   

	
	/**
	 * 跳转到短信签名页面
	 * 
	 * @param voiceUploadQuery
	 * @param req
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/preAudit")
	public ModelAndView preAudit(MerchantSmsSignerQuery merchantSmsSignerQuery, HttpServletRequest req, Model model)
			throws Exception {
		logger.info("跳转到【短信签名待审核列表详情】apiAccount={0},ID={1}",
				new Object[] { merchantSmsSignerQuery.getApiAccount(), merchantSmsSignerQuery.getId() }, null);
		ModelAndView mv = new ModelAndView("/audit/sign_detail_pre");
		try {
			MerchantSmsSigner merchantSmsSigner = merchantSmsManageService.findMerchantSmsSigner(merchantSmsSignerQuery);
			if(merchantSmsSigner!=null){
				merchantSmsSigner.setCategory(getCategorys(merchantSmsSigner.getCategory()));
			}
			mv.addObject("merchantSmsSigner", merchantSmsSigner);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("跳转到【短信签名待审核列表详情】异常是" + e.getMessage());
		}
		return mv;
	}

	
	/**
	 * 短信签名审核
	 * 
	 * @return
	 */
	@RequestMapping(value = "/audit")
	public String audit(MerchantSmsSignerQuery merchantSmsSignerQuery, HttpServletRequest req, Model model) {
		try {
			Map<String, String> cookies = CookieUtil.getCookies(req);
			String username = cookies.get(Constant.USER_SESSION_UNAME);
			if (username == null) {
				logger.warn("找不到用户，或者用户的登陆cookie已经过期");
				return "redirect:/login.html";
			}
			logger.info("【短信签名审核】审核信息是:" + JsonUtil.toJsonString(merchantSmsSignerQuery));
			MerchantSmsSignerVo merchantSmsSigner = merchantSmsManageService.findMerchantSmsSignerVo(merchantSmsSignerQuery);

			merchantSmsSigner.setStatus(merchantSmsSignerQuery.getStatus());
			merchantSmsSigner.setReason(merchantSmsSignerQuery.getReason());
			merchantSmsSigner.setAuthResultTime(new Date());
			merchantSmsSigner.setAuthUser(username);
			merchantSmsSigner.setUpdateTime(merchantSmsSigner.getAuthResultTime());
			merchantSmsSigner.setApiAccount(merchantSmsSigner.getApiAccount());
			merchantSmsManageService.update(merchantSmsSigner);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【短信签名审核】失败,异常是{0}", new Object[] { e.getMessage() }, null);
		}
		return "redirect:/audit/sign_list.html";
	}

	
	
	public String getCategorys(String category){
    	StringBuffer result=new StringBuffer();
    	if(StringUtils.isNotEmpty(category)){
    		String[] categorys=category.split(",");
    		if(categorys.length>0){
    			for(int i=0;i<categorys.length;i++){
    				String cg=categorys[i];
    				if(cg.equals("8")){
    					if(i==0){
    						result.append("通知");
    					}
    					else{
    						result.append("、通知");
    					}
    				}
    				else if(cg.equals("9")){
    					if(i==0){
    						result.append("验证码");
    					}
    					else{
    						result.append("、验证码");
    					}
    				}else{
    					// 11:营销短信
    					if(i==0){
    						result.append("营销");
    					}else{
    						result.append("、营销");
    					}
    				}
    			}
    			return result.toString();
    		}
    		return category;
    	}
    	return category;
    }
	
	
	
	/**
	 * 获得数组查询
	 * 
	 * @param list
	 * @return
	 */
	private String[] toApiArrys(List<SmsSendFilter> list) {

		String[] apiArrays = {};
		if (list == null || list.size() == 0) {
			return apiArrays;
		}
		apiArrays = new String[list.size()];
		Set<String> oSet = new HashSet<String>();
		for (SmsSendFilter vo : list) {
			oSet.add(vo.getApiAccount());
		}

		apiArrays = oSet.toArray(new String[] {});
		return apiArrays;
	}
	
}
