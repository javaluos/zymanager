package com.zy.cms.controller.analysis;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zy.cms.common.CommonService;
import com.zy.cms.common.Constant;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.service.manager.AccountBindInfoService;
import com.zy.cms.service.master.MerchantAccountService;
import com.zy.cms.service.sms.SmsReplyService;
import com.zy.cms.util.DateUtil;
import com.zy.cms.util.HttpClientUtil;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.MerchantAccount;
import com.zy.cms.vo.SmsReply;
import com.zy.cms.vo.User;
import com.zy.cms.vo.query.ResultQuery;
import com.zy.cms.vo.query.SmsReplyQuery;

/**
 * 短信上行查询记录
 * 
 * @author allen.yuan
 * @date 2017-6-7 10:15:10
 *
 */
@RequestMapping("/sms_reply")
@Controller
public class SmsReplyRecordController {

	private static final ZyLogger logger = ZyLogger.getLogger(SmsReplyRecordController.class);

	@Value("${MO_REPUSH_URL}")
	private String moRePushURL;

	@Resource
	private CommonService commonService;

	@Resource
	private AccountBindInfoService accountBindInfoService;

	@Resource
	private MerchantAccountService merchantAccountService;

	@Autowired
	private SmsReplyService smsReplyService;

	@RequestMapping("/to_list")
	public ModelAndView toList() {

		ModelAndView mv = new ModelAndView("/analysis/sms_reply_list");
		String date = DateUtil.getDateYMD();
		mv.addObject("starttime", date + " 00:00:00");
		mv.addObject("endtime", date + " 23:59:59");
		return mv;
	}

	@RequestMapping(value = "/list_data", produces = "application/json")
	public ModelAndView getSmsListData(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("params") String params) {

		ModelAndView mv = new ModelAndView("/analysis/sms_reply_list_data");
		try {
			User us = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
			if (us == null) {
				logger.warn("用户登录超时!");
				return new ModelAndView("redirect:/login.html");
			}

			SmsReplyQuery query = null;
			logger.info("【短信发送查询列表】参数={0}", new Object[] { params }, null);
			if (!StringUtil.isEmpty(params)) {
				query = JsonUtil.parseToObject(params, SmsReplyQuery.class);
			}
			if (query == null) {
				query = new SmsReplyQuery();
			}

			Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query.getPageNum() - 1);
			Integer pageSize = query.getPageSize() == 0 ? Constant.PAGE_SIZE_20 : query.getPageSize();
			query.setPageNum(pageNum);
			query.setPageSize(pageSize);

			// 查询当前用户绑定的列表
			List<String> apiAccounts = accountBindInfoService.getApiAccountByBusName(us.getUserName());
			query.setApiAccounts(apiAccounts);

			// 按账号条件查询
			String merchantPhone = query.getMerchantPhone();
			if (!StringUtil.isEmpty(merchantPhone)) {
				// 按账号查询
				MerchantAccount merchantAccount = merchantAccountService.getMerchantAccountByPhone(merchantPhone);
				if (merchantAccount != null) {
					query.setApiAccount(merchantAccount.getApiAccount());
				}
			}

			Integer total = smsReplyService.querySmsReplyCount(query);
			List<SmsReply> list = smsReplyService.querySmsReplys(query);

			String[] apis = toApiArrys(list);
			Map<String, MerchantAccount> accountMaps = merchantAccountService.queryMerchantAccountListByApis(apis);

			// 构建查询结果对象
			ResultQuery pgdata = new ResultQuery();
			pgdata.setPage_num(Long.valueOf(query.getPageNum() + 1));
			pgdata.setPage_size(Long.valueOf(query.getPageSize()));
			pgdata.setData(list);
			pgdata.setTotal(Long.valueOf(total));
			mv.addObject("pgdata", pgdata);
			mv.addObject("accountMaps", accountMaps);

			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【获取短信上行列表】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
			return mv;
		}

	}

	/**
	 * 获得数组查询
	 * 
	 * @param list
	 * @return
	 */
	private String[] toApiArrys(List<SmsReply> list) {

		String[] apiArrays = {};
		if (list == null || list.size() == 0) {
			return apiArrays;
		}
		apiArrays = new String[list.size()];
		Set<String> oSet = new HashSet<String>();
		for (SmsReply vo : list) {
			oSet.add(vo.getApiAccount());
		}

		apiArrays = oSet.toArray(new String[] {});
		return apiArrays;
	}

	@RequestMapping(value = "/list_repushMo", produces = "application/json")
	@ResponseBody
	public String rePushMoData(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("moId") String moId) {

		logger.info("【短信上行手工重推】moId=" + moId + ", moRePushURL=" + moRePushURL);
		String rs = "";
		Map<String, String> map = new HashMap<String, String>();
		map.put("status", "0");
		map.put("desc", "成功");
		rs = JsonUtil.toJsonString(map);
		try {

			Map<String, String> p = new HashMap<String, String>();
			p.put("moId", moId);
			String params = JsonUtil.objectToJson(p);
			Map<String, String> header = new HashMap<String, String>();

			rs = HttpClientUtil.INSTANCE.httpPost(moRePushURL, params, header);

		} catch (Exception e) {
			logger.info("【短信上行手工重推】异常，error=" + e.getMessage());
		}

		if (StringUtil.isEmpty(rs)) {
			rs = JsonUtil.toJsonString(map);
		}
		logger.info("【短信上行手工重推】结束===");
		return rs;

	}
}
