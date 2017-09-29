package com.zy.cms.controller.account;

import com.zy.cms.common.CommonService;
import com.zy.cms.common.Constant;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.mapper.master.CmppAccountMapper;
import com.zy.cms.service.manager.AccountBindInfoService;
import com.zy.cms.service.manager.UserMenuService;
import com.zy.cms.service.master.AppManageService;
import com.zy.cms.service.master.MerchantAccountAuthService;
import com.zy.cms.service.master.MerchantAccountService;
import com.zy.cms.service.master.SmsBlackKeyPolicyService;
import com.zy.cms.service.master.SmsChannelPolicyService;
import com.zy.cms.service.master.VoiceMerchantAttrService;
import com.zy.cms.util.CookieUtil;
import com.zy.cms.util.DESUtil;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.*;
import com.zy.cms.vo.channel.SmsChannelPolicy;
import com.zy.cms.vo.query.AccountQuery;
import com.zy.cms.vo.query.ResultQuery;
import com.zy.cms.vo.query.SmsChannelPolicyQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 客户账号查询
 * 
 * @author allen.yuan
 * @date 2016-09-21
 */
@Controller
@RequestMapping(value = "/account")
public class MerchantAccountController {

	private static final ZyLogger logger = ZyLogger.getLogger(MerchantAccountController.class);

	@Resource
	private MerchantAccountService merchantAccountService;

	@Autowired
	private MerchantAccountAuthService merchantAccountAuthService;

	@Resource
	private CommonService commonService;

	@Autowired
	private AccountBindInfoService accountBindInfoService;

	@Resource
	private VoiceMerchantAttrService voiceMerchantAttrService;

	@Autowired
	private UserMenuService userMenuService;

	@Autowired
	private SmsChannelPolicyService smsChannelPolicyService;

	@Autowired
	private SmsBlackKeyPolicyService smsBlackKeyPolicyService;
	
	@Autowired
	private AppManageService appManageService;
	
	@Resource
	private CmppAccountMapper cmppAccountMapper;
	
	/**
	 * 查询账号信息列表UI
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/actlist")
	public ModelAndView queryActList(HttpServletRequest request, HttpServletResponse response, String params) {

		// 定义结果
		ModelAndView mv = new ModelAndView("/account/act_list");
		try {
			AccountQuery merchantAccount = new AccountQuery();
			if (StringUtils.isNotEmpty(params)) {
				merchantAccount = JsonUtil.parseToObject(params, AccountQuery.class);
				merchantAccount
						.setBusinessname(new String(merchantAccount.getBusinessname().getBytes("iso8859-1"), "UTF-8"));
			}
			mv.addObject("merchantAccount", merchantAccount);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("查询账号信息列表出现异常,异常是:" + e.getMessage());
		}
		return mv;
	}

	/**
	 * 查询账号信息列表Data
	 * 
	 * @param request
	 * @return mv JSPTable
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/actlist_data", produces = "application/json")
	public ModelAndView queryMerchantAccounts(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("params") String params) {
		// 定义结果
		ModelAndView mv = new ModelAndView("/account/act_list_data");

		try {
			User us = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
			if (us == null) {
				logger.warn("用户登录超时!");
				return new ModelAndView("redirect:/login.html");
			}
			AccountQuery query = null;

			if (!StringUtil.isEmpty(params)) {
				query = JsonUtil.parseToObject(params, AccountQuery.class);
			}
			if (query == null) {
				query = new AccountQuery();
			}

			// 设置参数
			if (!StringUtil.isEmpty(query.getRegstarttime())) {
				query.setRegstarttime(query.getRegstarttime());
			}
			if (!StringUtil.isEmpty(query.getRegendtime())) {
				query.setRegendtime(query.getRegendtime());
			}

			if (!StringUtil.isEmpty(query.getLinestarttime())) {
				query.setLinestarttime(query.getLinestarttime());
			}
			if (!StringUtil.isEmpty(query.getLineendtime())) {
				query.setLineendtime(query.getLineendtime());
			}
			Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query.getPageNum() - 1);
			Integer pageSize = query.getPageSize() == 0 ? Constant.PAGE_SIZE_20 : query.getPageSize();
			query.setPageNum(pageNum);
			query.setPageSize(pageSize);

			List<String> apiAccounts = accountBindInfoService.getApiAccountByBusName(us.getUserName());
			query.setApiAccounts(apiAccounts);
			Integer total = merchantAccountService.queryMerchantAccountCount(query);
			List<MerchantAccount> list = merchantAccountService.queryMerchantAccounts(query);

			// 构建查询结果对象
			ResultQuery pgdata = new ResultQuery();
			pgdata.setPage_num(Long.valueOf(query.getPageNum() + 1));
			pgdata.setPage_size(Long.valueOf(query.getPageSize()));
			pgdata.setTotal(Long.valueOf(total));
			pgdata.setData(list);

			// 账号是否有属性查看权限
			UserMenu userMenu = userMenuService.getByUserName(us.getUserName());
			String menus = userMenu.getMenus();
			if (StringUtils.isNotBlank(menus) && menus.contains(Constant.PROPERTY_VIEW_MENUID)) {
				mv.addObject("permission", true);
			} else {
				mv.addObject("permission", false);
			}

			// 设定查询参数对象['pgdata'为JSP参数,不随意修改]
			mv.addObject("pgdata", pgdata);
		} catch (Exception e) {
			logger.error("查询账号信息列表出现异常,异常是:" + e.getMessage());
		}
		return mv;
	}

	/**
	 * 通过 apiAccount 加载account信息
	 * 
	 * @param apiAccount
	 * @return
	 */
	@RequestMapping(value = "/getMerchantAccount/{apiAccount}", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getMerchantAccount(@PathVariable String apiAccount) {
		logger.info("【账号信息】apiAccount={0}", new Object[] { apiAccount }, null);

		String result = "";
		MerchantAccount rs = merchantAccountService.getMerchantAccount(apiAccount);
		if (null == rs) {
			result = JsonUtil.wrapjson(Constant.SUCCESS + "", Constant.NO_FIND_ACCOUNT_INFO_DESC);
		}
		result = JsonUtil.wrapjson(Constant.SUCCESS + "", rs);
		return result;
	}

	@RequestMapping("/authentication_view/{apiAccount}")
	public ModelAndView view(@PathVariable String apiAccount) {

		ModelAndView mv = null;
		MerchantAccountAuth accAuth = merchantAccountAuthService.getByApiAccount(apiAccount);
		if (null == accAuth) {
			mv = new ModelAndView("/account/blank");
		} else {
			mv = new ModelAndView("/account/authentication_view");
			mv.addObject("accAuth", accAuth);
		}
		return mv;
	}

	/**
	 * 账号属性查看
	 * 
	 * @param apiAccount
	 * @return
	 */
	@RequestMapping("/view_merchant_attr/{apiAccount}")
	public ModelAndView viewMerchantAttr(@PathVariable String apiAccount, String params) {
		String theme = "【账号属性查看】";
		logger.info(theme + "查询开始。。。");
		logger.info(theme + "查询参数APIAccount为:" + apiAccount + " params为:" + params);
		ModelAndView mv = new ModelAndView("/account/blank");
		try {
			VoiceMerchantAttr voiceMerchantAttr = new VoiceMerchantAttr();
			AccountQuery accountQuery = new AccountQuery();
			if (StringUtils.isNotEmpty(params)) {
				accountQuery = JsonUtil.parseToObject(params, AccountQuery.class);
				accountQuery.setBusinessname(new String(accountQuery.getBusinessname().getBytes("iso8859-1"), "UTF-8"));
			}

			MerchantAccount merchantAccount = merchantAccountService.getMerchantAccount(apiAccount);
			Map map = new HashMap();
			map.put("apiAccount", apiAccount);
			List<VoiceMerchantAttr> voiceMerchantAttrs = voiceMerchantAttrService.selectByQuery(map);
			if (null == merchantAccount) {
				return mv;
			}
			voiceMerchantAttr = voiceMerchantAttrService.getVoiceMerchantAttr(voiceMerchantAttrs, voiceMerchantAttr);
			SmsChannelPolicyQuery query = new SmsChannelPolicyQuery();
			query.setPageOffset(0);
			query.setPageSize(10000);
			List<SmsChannelPolicy> smsChannelPolicyList = smsChannelPolicyService.querySmsChannelPolicyByEntity(query);
            
			List<BlackKeyPolicy> blackKeyPolicyList=smsBlackKeyPolicyService.selectListAll();
			
			CmppAccount cmppAccount=cmppAccountMapper.findCmppByAccount(apiAccount);
			
			Map param=new HashMap();
			param.put("apiAccount", apiAccount);
			param.put("isDelete", "1");
			List<AppInfo> appInfos=appManageService.getAppInfos(param);
			
			mv = new ModelAndView("/account/view_merchant_attr");
			mv.addObject("merchantAccount", merchantAccount);
			mv.addObject("accountQuery", accountQuery);
			mv.addObject("voiceMerchantAttr", voiceMerchantAttr);
			mv.addObject("smsChannelPolicyList", smsChannelPolicyList);
			mv.addObject("blackKeyPolicyList", blackKeyPolicyList);
			mv.addObject("appInfos",appInfos);
			if(null!=appInfos&&appInfos.size()>0){
				mv.addObject("appInfo",appInfos.get(0));
			}
			mv.addObject("cmppAccount",cmppAccount);
		} catch (Exception e) {
			logger.error(theme + "出现异常，异常是:" + e.getMessage());
			e.printStackTrace();
		}
		logger.info(theme + "查询结束。。。");
		return mv;
	}

	/**
	 * 保存账号属性
	 * 
	 * @param apiAccount
	 * @return
	 */
	@RequestMapping(value = "/saveMerchantAttr", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String saveMerchantAttr(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("params") String params,@RequestParam("accountParams") String accountParams,@RequestParam("cmppParams") String cmppParams) {
		String theme = "【账号属性修改】";
		logger.info(theme + "开始。。。");
		logger.info(theme + "params为:" + params);
		VoiceMerchantAttr voiceMerchantAttr = null;
		MerchantAccount merchantAccount=null;
		CmppAccount cmppAccount=null;
		String result = "";
		try {
			if (!StringUtil.isEmpty(params)) {
				voiceMerchantAttr = JsonUtil.parseToObject(params, VoiceMerchantAttr.class);
			}
			/*MerchantAccount merchantAccount = merchantAccountService
					.getMerchantAccount(voiceMerchantAttr.getApiAccount());*/
			if(!StringUtil.isEmpty(accountParams)){
				merchantAccount=JsonUtil.parseToObject(accountParams, MerchantAccount.class);
			}
			if(!StringUtil.isEmpty(cmppParams)){
				cmppAccount=JsonUtil.parseToObject(cmppParams, CmppAccount.class);
			}

			//手机号验证
			MerchantAccount accountRs = merchantAccountService.getInfoByMerchantPhone(merchantAccount.getMerchantPhone());
			if (null != accountRs && !accountRs.getApiAccount().equals(merchantAccount.getApiAccount())) {
				result = JsonUtil.wrapjson(Constant.PHONE_EXIST + "",Constant.PHONE_EXIST_DESC);
				logger.info("【注册】" + result);
				return result;
			}

			
			if(StringUtils.isNotEmpty(merchantAccount.getMerchantEmail())){
				MerchantAccount accountemail = merchantAccountService.getInfoByMerchantEmail(merchantAccount.getMerchantEmail());
				if (null != accountemail && !accountemail.getApiAccount().equals(merchantAccount.getApiAccount())) {
					result = JsonUtil.wrapjson("188","该邮箱已经存在了");
					logger.info("【注册】" + result);
					return result;
				}
			}
			
			String merchantaccount = "";
			if(StringUtils.isEmpty(merchantAccount.getMerchantAccount())){
				if (!StringUtil.isEmpty(merchantAccount.getMerchantEmail())
						&& merchantAccount.getMerchantEmail().split("@").length > 1) {
					merchantaccount = merchantAccount.getMerchantEmail().split("@")[0]; // 获取邮箱的前部分
				}else{
					merchantaccount=merchantAccount.getMerchantPhone();
				}
			}else{
				merchantaccount=merchantAccount.getMerchantAccount();
			}
			MerchantAccount accountMerchant = merchantAccountService.getInfoByMerchantAccount(merchantaccount);
			if (null != accountMerchant && !accountRs.getApiAccount().equals(merchantAccount.getApiAccount())) {
				result = JsonUtil.wrapjson("189","客户账号已经存在了");
				logger.info("【注册】" + result);
				return result;
			}
			
			
			if(cmppAccount!=null){
				if(StringUtils.isNotEmpty(cmppAccount.getClientId())){
					CmppAccount account=cmppAccountMapper.findCmppByClientId(cmppAccount.getClientId());
					if(account!=null && !account.getId().equals(cmppAccount.getId())){
						result = JsonUtil.wrapjson("199","CMPP账号已经存在了");
						logger.info("【注册】" + result);
						return result;
					}
				}
			}
			
			merchantAccount.setBusinessName(voiceMerchantAttr.getBusinessName());
			merchantAccount.setAuthFlag(voiceMerchantAttr.getAuthFlag());
			List<VoiceMerchantAttr> voiceMerchantAttrs = voiceMerchantAttrService
					.getVoiceMerchantAttrs(voiceMerchantAttr);
			boolean flag = voiceMerchantAttrService.saveVoiceMerchantAttrs(voiceMerchantAttrs, merchantAccount,cmppAccount);

			if (flag) {
				result = JsonUtil.wrapjson(Constant.SUCCESS + "", "");
				return result;
			}
			result = JsonUtil.wrapjson(Constant.ERROR + "", "");
		} catch (Exception e) {
			logger.error(theme + "出现异常，异常是:" + e.getMessage());
		}
		logger.info(theme + "结束。。。");
		return result;
	}

	/**
	 * 通过 apiAccount 加载account信息
	 * 
	 * @param apiAccount
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/actlistByLike")
	public String queryAccountlistLikeName(
			@RequestParam(value = "businessName", required = false) String businessName) {
		logger.info("【账号信息】businessName={0}", new Object[] { businessName }, null);

		String result = "";
		List<MerchantAccount> rsList = merchantAccountService.queryAccountlistLikeName(businessName);
		if (null == rsList) {
			rsList = new ArrayList<MerchantAccount>();
		}
		result = JsonUtil.toJsonString(rsList);
		return result;
	}
	
	/**
	 * 跳转到添加账户页面
	 * @param request
	 * @param response
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/to_add_account")
	public ModelAndView toAddAccount(HttpServletRequest request, HttpServletResponse response, String params) {
		// 定义结果
		String theme = "【创建新账号】";
		logger.info(theme + "查询参数 params为:" + params);
		ModelAndView mv = new ModelAndView("/account/act_add_merchant");
		try {
			AccountQuery accountQuery = new AccountQuery();
			if (StringUtils.isNotEmpty(params)) {
				accountQuery = JsonUtil.parseToObject(params, AccountQuery.class);
				accountQuery.setBusinessname(new String(accountQuery.getBusinessname().getBytes("iso8859-1"), "UTF-8"));
			}
			
			SmsChannelPolicyQuery query = new SmsChannelPolicyQuery();
			query.setPageOffset(0);
			query.setPageSize(10000);
			List<SmsChannelPolicy> smsChannelPolicyList = smsChannelPolicyService.querySmsChannelPolicyByEntity(query);
			List<BlackKeyPolicy> blackKeyPolicyList=smsBlackKeyPolicyService.selectListAll();
			
			String apiAccount = "ACC" + StringUtil.getUUID();
			String apiKey = "API" + StringUtil.getUUID();
			String appId = "APP" + StringUtil.getUUID();
			
			mv.addObject("smsChannelPolicyList", smsChannelPolicyList);
			mv.addObject("blackKeyPolicyList", blackKeyPolicyList);
			mv.addObject("accountQuery", accountQuery);
			mv.addObject("apiAccount",apiAccount);
			mv.addObject("apiKey",apiKey);
			mv.addObject("appId",appId);
		} catch (Exception e) {
			logger.error(theme + "出现异常，异常是:" + e.getMessage());
			e.printStackTrace();
		}
		return mv;
	}
	
	
	/**
	 * 保存账号属性
	 * 
	 * @param apiAccount
	 * @return
	 */
	@RequestMapping(value = "/saveMerchantAccount", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String saveMerchantAccount(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("params") String params,@RequestParam("accountParams") String accountParams,@RequestParam("cmppParams") String cmppParams) {
		String theme = "【添加新账号】";
		logger.info(theme + "开始。。。");
		logger.info(theme + "params为:" + params + "   accountParams:"+accountParams +"   cmppParams:"+cmppParams);
		VoiceMerchantAttr voiceMerchantAttr = null;
		MerchantAccount merchantAccount=null;
		CmppAccount cmppAccount=null;
		String result =JsonUtil.wrapjson(Constant.ERROR + "", "");
		try {
			if (!StringUtil.isEmpty(params)) {
				voiceMerchantAttr = JsonUtil.parseToObject(params, VoiceMerchantAttr.class);
			}
			if(!StringUtil.isEmpty(accountParams)){
				merchantAccount=JsonUtil.parseToObject(accountParams, MerchantAccount.class);
			}
			if(!StringUtil.isEmpty(cmppParams)){
				cmppAccount=JsonUtil.parseToObject(cmppParams, CmppAccount.class);
			}
			
			
			// 1.手机号验证
			MerchantAccount accountRs = merchantAccountService.getInfoByMerchantPhone(merchantAccount.getMerchantPhone());
			if (null != accountRs) {
				result = JsonUtil.wrapjson(Constant.PHONE_EXIST + "",Constant.PHONE_EXIST_DESC);
				logger.info("【注册】" + result);
				return result;
			}

			
			if(StringUtils.isNotEmpty(merchantAccount.getMerchantEmail())){
				MerchantAccount accountemail = merchantAccountService.getInfoByMerchantEmail(merchantAccount.getMerchantEmail());
				if (null != accountemail) {
					result = JsonUtil.wrapjson("188","该邮箱已经存在了");
					logger.info("【注册】" + result);
					return result;
				}
			}
			
			String merchantaccount = "";
			if(StringUtils.isEmpty(merchantAccount.getMerchantAccount())){
				if (!StringUtil.isEmpty(merchantAccount.getMerchantEmail())
						&& merchantAccount.getMerchantEmail().split("@").length > 1) {
					merchantaccount = merchantAccount.getMerchantEmail().split("@")[0]; // 获取邮箱的前部分
				}else{
					merchantaccount=merchantAccount.getMerchantPhone();
				}
			}else{
				merchantaccount=merchantAccount.getMerchantAccount();
			}
			MerchantAccount accountMerchant = merchantAccountService.getInfoByMerchantAccount(merchantaccount);
			if (null != accountMerchant) {
				result = JsonUtil.wrapjson("189","客户账号已经存在了");
				logger.info("【注册】" + result);
				return result;
			}
			
			if(cmppAccount!=null){
				if(StringUtils.isNotEmpty(cmppAccount.getClientId())){
					CmppAccount account=cmppAccountMapper.findCmppByClientId(cmppAccount.getClientId());
					if(account!=null){
						result = JsonUtil.wrapjson("199","CMPP账号已经存在了");
						logger.info("【注册】" + result);
						return result;
					}
				}
			}
			
			// 2.保存客户的基本信息
			boolean saveFlag=merchantAccountService.saveRegistMerchantAccount(voiceMerchantAttr,merchantAccount,cmppAccount);	
			if(saveFlag){
				result = JsonUtil.wrapjson(Constant.SUCCESS + "", "");
			}
			
			// 3.将信息存入到cookie中
			/*Cookie unameCookie = CookieUtil.getCookie(
					Constant.USER_SESSION_UNAME,
					merchantAccount.getMerchantAccount() + "",
					Constant.Time.THIRTY_MINUTES, Constant.SLASH);*/
			Cookie uidCookie = CookieUtil.getCookie(
					Constant.USER_SESSION_UID,
					DESUtil.encrypt(merchantAccount.getMerchantAccount()
							+ "", Constant.USER_SESSION_ENCRYPT_KEY),
					Constant.Time.THIRTY_MINUTES, Constant.SLASH);
			//response.addCookie(unameCookie);
			response.addCookie(uidCookie);
			Cookie phoneCookie = CookieUtil.getCookie(
					Constant.USER_SESSION_PHONE,
					merchantAccount.getMerchantAccount() + "",
					Constant.Time.THIRTY_MINUTES, Constant.SLASH);
			response.addCookie(phoneCookie);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(theme + "出现异常，异常是:" + e.getMessage());
			result = JsonUtil.wrapjson(Constant.ERROR + "", "");
		}
		logger.info(theme + "结束。。。");
		return result;
	}

}
