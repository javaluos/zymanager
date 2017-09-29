package com.zy.cms.controller.mbag;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zy.cms.common.CommonService;
import com.zy.cms.common.Constant;
import com.zy.cms.common.RedisConstant;
import com.zy.cms.common.RedisOperator;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.service.manager.AccountBindInfoService;
import com.zy.cms.service.manager.UserMenuService;
import com.zy.cms.service.master.MerchantAccountBalanceService;
import com.zy.cms.service.master.MerchantAccountService;
import com.zy.cms.util.GenerateCodeUtil;
import com.zy.cms.util.HttpClientUtil;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.MD5Util;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.MerchantAccount;
import com.zy.cms.vo.ResultVo;
import com.zy.cms.vo.User;
import com.zy.cms.vo.UserMenu;
import com.zy.cms.vo.VoiceMerchantAccountBalance;
import com.zy.cms.vo.query.AccountBalanceQuery;
import com.zy.cms.vo.query.ResultQuery;

/**
 * 账户余额查询
 * 
 * @author allen.yuan
 * @date 2016-10-09
 */
@Controller
@RequestMapping(value = "/moneybag")
public class AccountBalanceController {

	private static final ZyLogger logger = ZyLogger.getLogger(AccountBalanceController.class);

	@Resource
	private MerchantAccountService merchantAccountService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private UserMenuService userMenuService;

	@Autowired
	private MerchantAccountBalanceService merchantAccountBalanceService;

	@Autowired
	private RedisOperator redisOperator;

	@Autowired
	private AccountBindInfoService accountBindInfoService;
	
	@Value("${DEFAULT_APIACCOUNT}")
	private String defaultApiAccount;
	@Value("${DEFAULT_APIKEY}")
	private String defaultApikey;
	@Value("${DEFAULT_APPID}")
	private String defaultAppId;
	@Value("${DEFAULT_URL}")
	private String defaultUrl;
	@Value("${DEFAULT_MATCHURL}")
	private String defaultMatchUrl;
	@Value("${DEFAULT_SIGNID}")
	private String defaultSignId;

	/**
	 * 查询账号余额信息列表UI
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/actbalancelist")
	public ModelAndView queryActBalanceBList(HttpServletRequest request, HttpServletResponse response) {

		// 定义结果
		ModelAndView mv = new ModelAndView("/mbag/actbalance_list");
		return mv;
	}

	/**
	 * 查询账号余额信息列表Data
	 * 
	 * @param request
	 * @return mv JSPTable
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/actbalancelist_data", produces = "application/json")
	public ModelAndView queryActBalanceData(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("params") String params) {
		User user = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
		if (user == null) {
			logger.warn("用户登录超时!");
			return new ModelAndView("redirect:/login.html");
		}
		// 定义结果
		ModelAndView mv = new ModelAndView("/mbag/actbalance_list_data");

		try {
			AccountBalanceQuery query = null;
			if (!StringUtil.isEmpty(params)) {
				query = JsonUtil.parseToObject(params, AccountBalanceQuery.class);
			}
			if (query == null) {
				query = new AccountBalanceQuery();
			}

			Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query.getPageNum() - 1);
			Integer pageSize = query.getPageSize() == 0 ? Constant.PAGE_SIZE_20 : query.getPageSize();
			query.setPageNum(pageNum);
			query.setPageSize(pageSize);

			List<String> apiAccounts = accountBindInfoService.getApiAccountByBusName(user.getUserName());
			query.setApiAccounts(apiAccounts);
			Integer total = merchantAccountService.queryMerchantAcctBalanceCount(query);
			List<MerchantAccount> list = null;

			list = merchantAccountService.queryMerchantAcctBalances(query);

			// 构建查询结果对象
			ResultQuery pgdata = new ResultQuery();
			pgdata.setPage_num(Long.valueOf(query.getPageNum() + 1));
			pgdata.setPage_size(Long.valueOf(query.getPageSize()));
			pgdata.setTotal(Long.valueOf(total));
			pgdata.setData(list);

			// 账号是否有充值权限
			UserMenu userMenu = userMenuService.getByUserName(user.getUserName());
			String menus = userMenu.getMenus();
			if (StringUtils.isNotBlank(menus) && menus.contains(Constant.RECHARGE_MENUID)) {
				mv.addObject("permission", true);
			} else {
				mv.addObject("permission", false);
			}
			// 设定查询参数对象['pgdata'为JSP参数,不随意修改]
			mv.addObject("pgdata", pgdata);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("【余额查询出现异常】", e);
		}
		return mv;
	}

	/**
	 * 账户余额修改页面跳转
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/actchargeindex")
	public ModelAndView actchargeindex(HttpServletRequest request, HttpServletResponse response, String apiAccount) {
		User user = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
		// 定义结果
		MerchantAccount mAccount = merchantAccountService.getMerchantAccount(apiAccount);

		ModelAndView mv = new ModelAndView("/mbag/balanceupdate_index");
		mv.addObject("obj", mAccount);
		mv.addObject("user", user);

		return mv;
	}

	/**
	 * 检查用户是否有充值权限
	 * 
	 * @param request
	 * 
	 */
	@RequestMapping("/check_submit")
	@ResponseBody
	public Object checkSubmit(HttpServletRequest request, String apiAccount, String updateFee) {
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("result", "0");
		// boolean result = false;
		User user = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
		UserMenu userMenu = userMenuService.getByUserName(user.getUserName());
		String menus = userMenu.getMenus();
		if (StringUtil.isEmpty(menus) || !menus.contains(Constant.RECHARGE_MENUID)) {
			resultMap.put("result", "1");
			resultMap.put("menumsg", "当前用户没有充值权限.");
		}
		// 检查余额是否扣款时余额是否充足
		if (!StringUtil.isEmpty(updateFee)) {
			long money = (long) (Double.valueOf(updateFee) * 10000);
			if (money < 0) {
				VoiceMerchantAccountBalance accBalance = merchantAccountBalanceService
						.getMerchantAccountBalance(apiAccount);
				long balance = accBalance.getBalance() + money;
				if (balance < 0) {
					resultMap.put("result", "1");
					resultMap.put("balancemsg", "余额不足.");
				}
			}
		} else {
			resultMap.put("result", "1");
			resultMap.put("balancemsg", "请输入修改金额.");
		}
		return JsonUtil.objectToJson(resultMap);
	}

	@RequestMapping(value = "/get_vcode", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String getVcode(String mobile) {
		boolean flag = StringUtil.validZH_CNMobile(mobile);
		ResultVo vo = new ResultVo();
		if (flag) {

			String smscode = GenerateCodeUtil.generateNumCode(4); // 随机生成的验证码
			String content = "【智语科技】您好，您的验证码是：" + smscode; // 发送内容

			String smsSendResult = sendSmsByMatchTemplate(content, mobile, smscode, "", "9");

			logger.info("【修改余额短信验证码返回】," + smsSendResult + "");
			Map smsMap = JsonUtil.parseToObject(smsSendResult, Map.class);
			if (null != smsMap && smsMap.get("status") != null && (smsMap.get("status").equals("0"))) {
				vo.setReason("");
				vo.setResult("SUCCESS");

				String key = String.format(RedisConstant.BALANCE_VCODE_KEY, mobile);
				redisOperator.setex(key,Constant.VERIFY_CODE_IN_REDIS_MINUTES,smscode);
			} else {
				vo.setReason(smsMap.get("reason").toString());
				vo.setResult("FAIL");
			}

		} else {
			vo.setReason("手机号不正确");
			vo.setResult("FAIL");
		}
		return JsonUtil.toJsonString(vo);
	}

	@RequestMapping(value = "/check_vcode")
	@ResponseBody
	public String checkVcode(String mobile, String vcode) {
		boolean result = false;
		// 获取redis中短信
		String key = String.format(RedisConstant.BALANCE_VCODE_KEY, mobile);
		String redisVcode = redisOperator.get(key);
		if (!StringUtil.isEmpty(redisVcode) && redisVcode.equals(vcode)) {
			result = true;
		}
		return JsonUtil.objectToJson(result);
	}
	
	/**
	 * 新平台根据短信内容发送短信
	 *
	 * @param apiAccount
	 * @param appId
	 * @param timeStamp
	 * @param _sign
	 * @param content
	 * @param mobile
	 * @param code
	 * @param url
	 *            请求短信接口地址
	 * @param redisKey
	 *            将数据存放到redis中
	 * @param time
	 * @return
	 */
	private String sendSmsByMatchTemplate(String content, String mobile, String code, String redisKey, String smsType) {
		String timeStamp = System.currentTimeMillis() + "";
		String sign = MD5Util.md5Hex(defaultApiAccount + defaultApikey + timeStamp);// MD5签名加密
		if (StringUtil.isEmpty(defaultApiAccount) || StringUtil.isEmpty(defaultAppId) || StringUtil.isEmpty(timeStamp)
				|| StringUtil.isEmpty(sign) || StringUtil.isEmpty(content) || StringUtil.isEmpty(mobile)
				|| StringUtil.isEmpty(defaultMatchUrl)) {
			logger.info("【短信系统】获取到的参数为空");
			return "";
		}
		Map<String, String> params = new HashMap<String, String>();
		params.put("apiAccount", defaultApiAccount);
		params.put("appId", defaultAppId);
		params.put("timeStamp", timeStamp);
		params.put("sign", sign);
		params.put("content", content);
		params.put("mobile", mobile);
		params.put("userData", "");
		params.put("statusPushAddr", "");
		params.put("smsType", smsType);

		String httpResult = "";
		try {
			logger.info("【短信系统】URL=" + defaultMatchUrl + "  params=" + params);

			httpResult = HttpClientUtil.INSTANCE.httpPost(defaultMatchUrl, JsonUtil.toJsonString(params), null);

			if (StringUtil.isEmpty(httpResult)) {
				logger.error("【短信系统】响应为空");
			}
			logger.info("【短信系统】httpResult=" + httpResult + ",code=" + code);
			return httpResult;
			
		} catch (Exception e) {
			logger.error("【短信系统】异常" + e.getMessage(), e);
		}
		return httpResult;
	}

}
