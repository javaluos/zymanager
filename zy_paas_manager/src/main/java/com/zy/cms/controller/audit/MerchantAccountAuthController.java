package com.zy.cms.controller.audit;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.zy.cms.common.CommonService;
import com.zy.cms.common.Constant;
import com.zy.cms.common.RedisConstant;
import com.zy.cms.common.RedisOperator;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.service.manager.AccountBindInfoService;
import com.zy.cms.service.master.MerchantAccountAuthService;
import com.zy.cms.service.master.MerchantAccountService;
import com.zy.cms.service.master.VoiceUploadService;
import com.zy.cms.util.CookieUtil;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.MerchantAccount;
import com.zy.cms.vo.MerchantAccountAuth;
import com.zy.cms.vo.User;
import com.zy.cms.vo.query.MerchantAccountAuthQuery;
import com.zy.cms.vo.query.ResultQuery;

/**
 * 用户认证
 * 
 * @author allen.xu
 * @date 2016-09-21
 */
@Controller
@RequestMapping(value = "/merchantAccountAuth")
public class MerchantAccountAuthController {

	private static final ZyLogger logger = ZyLogger.getLogger(MerchantAccountAuthController.class);

	@Resource
	private MerchantAccountAuthService merchantAccountAuthService;

	@Resource
	private RedisOperator redisOperator;
	
	@Resource
	private MerchantAccountService merchantAccountService;
	
	@Resource
	private VoiceUploadService voiceUploadService;
	
	@Resource
	private CommonService commonService;
	
	@Autowired
	private AccountBindInfoService accountBindInfoService;
	
	/**
	 * 查询认证信息列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/audit_list")
	public ModelAndView queryAuditList(HttpServletRequest request,
			HttpServletResponse response) {
		// 定义结果
		ModelAndView mv = new ModelAndView("/merchantAccountAuth/audit_list");
		return mv;
	}

	/**
	 * 查询账号信息列表Data
	 * 
	 * @param request
	 * @return mv JSPTable
	 * @throws Exception 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/audit_list_data", produces = "application/json")
	public ModelAndView queryAuditListData(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("params") String params)  {
		try {
			ModelAndView mv = new ModelAndView("/merchantAccountAuth/audit_list_data");
			User us = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
			if (us == null) {
				logger.warn("用户登录超时!");
				return new ModelAndView("redirect:/login.html");
			}
			
			MerchantAccountAuthQuery query = null;
			logger.info("【(待)认证列表】参数={0}", new Object[] { params }, null);
			if (!StringUtil.isEmpty(params)) {
				query = JsonUtil.parseToObject(params, MerchantAccountAuthQuery.class);
			}
			if (query == null) {
				query = new MerchantAccountAuthQuery();
			}

			Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query
					.getPageNum() - 1);
			Integer pageSize = query.getPageSize() == 0 ? Constant.PAGE_SIZE_20
					: query.getPageSize();
			query.setPageNum(pageNum);
			query.setPageSize(pageSize);
			
			List<String> apiAccounts=accountBindInfoService.getApiAccountByBusName(us.getUserName());
			query.setApiAccounts(apiAccounts);
			Integer total = merchantAccountAuthService.queryMerchantAccountAuthByEntity(query);
			List<MerchantAccountAuth> list = merchantAccountAuthService.queryMerchantAccountAuthListByEntity(query);
			
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
			logger.error("【获取(待)认证列表】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
			return null;
		}

	}
	

	/**
	 * 跳转到认证详情页面
	 * @param voiceUploadQuery
	 * @param req
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/auditDetail")
	public ModelAndView auditDetail(MerchantAccountAuth voiceMerchantAccountAuth,HttpServletRequest req,
			Model model) throws Exception{
		try {
			logger.info("【用户认证列表详情】ID={0}", new Object[] {voiceMerchantAccountAuth.getId() }, null);
			ModelAndView mv = new ModelAndView("/merchantAccountAuth/audit_personal_detail");
			MerchantAccountAuth merchantAccountAuth=merchantAccountAuthService.getMerchantAccountAuthById(voiceMerchantAccountAuth.getId());
			if(merchantAccountAuth!=null){
				if(merchantAccountAuth.getMerchantType()==2){  //企业信息认证
					mv = new ModelAndView("/merchantAccountAuth/audit_company_detail");
				}
			}
			mv.addObject("voiceMerchantAccountAuth", merchantAccountAuth);
			return mv;
		} catch (Exception e) {
			logger.error("用户认证失败,异常是{0}", new Object[] { e.getMessage()}, null);
		}
		return new ModelAndView("/merchantAccountAuth/audit_list.html");
	}
	
	/**
	 * 认证审核
	 * @return
	 */
	@RequestMapping(value = "/audit")
	public String audit(MerchantAccountAuth voiceMerchantAccountAuth,HttpServletRequest req,
			Model model){
		try {
			Map<String, String> cookies = CookieUtil.getCookies(req);
			String username = cookies.get(Constant.USER_SESSION_UNAME);
			if (username == null) {
				logger.warn("找不到用户，或者用户的登陆cookie已经过期");
				return "redirect:/login.html";
			}
			
			logger.info("认证审核参数,审核人是{0},审核时间是:{1},审核描述是：{2},审核状态是 :{3}", new Object[] {username,new Date(),voiceMerchantAccountAuth.getAuthDesc(),voiceMerchantAccountAuth.getAuthStatus()}, null);
			MerchantAccountAuth accountAuth=merchantAccountAuthService.getMerchantAccountAuthById(voiceMerchantAccountAuth.getId());
			accountAuth.setAuthStatus(voiceMerchantAccountAuth.getAuthStatus());
			accountAuth.setAuthDesc(voiceMerchantAccountAuth.getAuthDesc());
			accountAuth.setAuthResultTime(new Date());
			accountAuth.setAuthUser(username);
			accountAuth.setUpdateTime(voiceMerchantAccountAuth.getAuthResultTime());
			merchantAccountAuthService.updateByPrimaryKeySelective(accountAuth);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("语音审核失败,异常是{0}", new Object[] { e.getMessage()}, null);
		}
		return "redirect:/merchantAccountAuth/audit_list.html";
	}
	
	@RequestMapping("/to_authentication")
	public ModelAndView toAuthentication(HttpServletRequest request, HttpServletResponse response, String apiAccount){
		ModelAndView mv = new ModelAndView("/merchantAccountAuth/do_authentication");
		MerchantAccountAuth accAuth = merchantAccountAuthService.getByApiAccount(apiAccount);
		mv.addObject("accAuth", accAuth);
		return mv;
	}
	
	@RequestMapping("/do_authentication")
	public ModelAndView doAuthentication(HttpServletRequest request, HttpServletResponse response){
		try {
			boolean result = false;
			/*User user = commonService.valideUser(request, Constant.USER_SESSION_UID);
			if (user == null) {
				logger.warn("用户登录超时!");
				return new ModelAndView("redirect:/login.html");
			}*/
			String apiAccount = request.getParameter("apiAccount");
			MerchantAccountAuth accAuth = merchantAccountAuthService.getByApiAccount(apiAccount);
			MerchantAccount mAccount = merchantAccountService.getMerchantAccount(apiAccount);
			String flag = request.getParameter("flag");
			String authDesc = request.getParameter("authDesc");
			accAuth.setAuthDesc(authDesc);
			if(null != accAuth){
				result = merchantAccountAuthService.doAuthentication("", accAuth, flag);
			}
			if(("1").equals(flag)){
				result = merchantAccountService.updateAccountInfo(accAuth, mAccount, flag);
				String account_key = String.format(RedisConstant.ACCOUNTKEY_PREFIX, apiAccount);
				logger.info("【信息认证成功,清除redis缓存信息】 清除的apiAccount是{0},清除的是account_key是{1} ",new Object[]{apiAccount,account_key},null);
				redisOperator.del(account_key);
			}
			return new ModelAndView("redirect:/merchantAccountAuth/audit_list");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("【信息审核失败】 异常是:{0}",new Object[]{e.getMessage()},null);
			return new ModelAndView("redirect:/merchantAccountAuth/audit_list");
		}
	}
	
	@RequestMapping("/authentication_view/{apiAccount}")
	public ModelAndView view(@PathVariable String apiAccount){
		ModelAndView mv = new ModelAndView("/merchantAccountAuth/authentication_view");
		MerchantAccountAuth accAuth = merchantAccountAuthService.getByApiAccount(apiAccount);
		mv.addObject("accAuth", accAuth);
		return mv;
	}

}
