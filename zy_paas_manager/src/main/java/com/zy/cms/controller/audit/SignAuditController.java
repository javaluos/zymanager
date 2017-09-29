package com.zy.cms.controller.audit;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.zy.cms.common.CommonService;
import com.zy.cms.common.Constant;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.service.manager.AccountBindInfoService;
import com.zy.cms.service.master.MerchantAccountService;
import com.zy.cms.service.master.MerchantSmsManageService;
import com.zy.cms.util.CookieUtil;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.MerchantSmsSigner;
import com.zy.cms.vo.MerchantSmsSignerVo;
import com.zy.cms.vo.User;
import com.zy.cms.vo.query.MerchantSmsSignerQuery;
import com.zy.cms.vo.query.ResultQuery;

/**
 * 短信签名信息
 * 
 * @author allen.xu
 * @date 2016-09-21
 */
@Controller
@RequestMapping(value = "/signaudit")
public class SignAuditController {

	private static final ZyLogger logger = ZyLogger.getLogger(SignAuditController.class);

	@Resource
	private MerchantSmsManageService merchantSmsManageService;

	@Resource
	private MerchantAccountService merchantAccountService;

	@Resource
	private CommonService commonService;

	@Autowired
	private AccountBindInfoService accountBindInfoService;
	
	/**
	 * 查询短信签名审核列表UI
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/sign_list")
	public ModelAndView queryAuditList(HttpServletRequest request, HttpServletResponse response) {
		// 定义结果
		logger.info("【加载短信签名列表】");
		ModelAndView mv = new ModelAndView("/audit/sign_list");
		mv.addObject("status","2");
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
	@RequestMapping(value = "/sign_list_data", produces = "application/json")
	public ModelAndView queryAuditListData(HttpServletRequest request, HttpServletResponse response,@RequestParam("params") String params) {
		try {
			ModelAndView mv = new ModelAndView("/audit/sign_list_data");
			User us = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
			if (us == null) {
				logger.warn("用户登录超时!");
				return new ModelAndView("redirect:/login.html");
			}
			
			MerchantSmsSignerQuery query = null;
			logger.info("【(待)短信签名审核列表】页面传来参数={0}", new Object[] { params }, null);
			if (!StringUtil.isEmpty(params)) {
				query = JsonUtil.parseToObject(params, MerchantSmsSignerQuery.class);
			}
			if (query == null) {
				query = new MerchantSmsSignerQuery();
			}

			Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query.getPageNum() - 1);
			Integer pageSize = query.getPageSize() == 0 ? Constant.PAGE_SIZE_20 : query.getPageSize();
			query.setPageNum(pageNum);
			query.setPageSize(pageSize);
			query.setIsLocked(0);
			logger.info("【(待)短信签名审核列表】参数={0}", new Object[] { query }, null);
			
			Integer total = 0;
			List<MerchantSmsSigner> list = new ArrayList<MerchantSmsSigner>();
			List<String> apiAccounts=accountBindInfoService.getApiAccountByBusName(us.getUserName());
			query.setApiAccounts(apiAccounts);
			total = merchantSmsManageService.queryCountByEntity(query);
			list = merchantSmsManageService.queryListByEntity(query);
			if(list!=null&&list.size()>0){
				for(MerchantSmsSigner mss:list){
					mss.setCategory(getCategorys(mss.getCategory()));
				}
			}

			// 构建查询结果对象
			ResultQuery pgdata = new ResultQuery();
			pgdata.setPage_num(Long.valueOf(query.getPageNum() + 1));
			pgdata.setPage_size(Long.valueOf(query.getPageSize()));
			pgdata.setTotal(Long.valueOf(total));
			pgdata.setData(list);
			// 设定查询参数对象['pgdata'为JSP参数,不随意修改]
			mv.addObject("pgdata", pgdata);
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【获取(待)短信签名】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
			return null;
		}
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
}
