package com.zy.cms.controller.analysis;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zy.cms.common.CommonService;
import com.zy.cms.common.Constant;
import com.zy.cms.common.RedisConstant;
import com.zy.cms.common.RedisOperator;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.elastic.service.EsService;
import com.zy.cms.enums.SmsCategoryEnum;
import com.zy.cms.enums.SmsSendStatusEnum;
import com.zy.cms.mapper.master.MobileOperatorMapper;
import com.zy.cms.service.manager.AccountBindInfoService;
import com.zy.cms.service.manager.SmsSendRecordService;
import com.zy.cms.util.DateUtil;
import com.zy.cms.util.HttpClientUtil;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.util.ValueUtil;
import com.zy.cms.vo.User;
import com.zy.cms.vo.WebResult;
import com.zy.cms.vo.query.ResultQuery;
import com.zy.cms.vo.query.SmsSendQuery;

/**
 * 短信发送记录Controller
 * 
 * @author luos
 * @date 2017-2-23 10:15:10
 *
 */
@RequestMapping("/sms_send")
@Controller
public class SmsSendRecordController {

	private static final ZyLogger logger = ZyLogger.getLogger(SmsSendRecordController.class);

	@Autowired
	private EsService esService;

	@Resource
	private CommonService commonService;

	@Resource
	private AccountBindInfoService accountBindInfoService;

	@Autowired
	private SmsSendRecordService smsSendRecordService;

	@Autowired
	private MobileOperatorMapper mobileOperatorMapper;

	@Value("${PUSH_STATUS_URL}")
	private String pushStatusUrl;

	private static ExecutorService clientThread = Executors.newFixedThreadPool(15);// 接收消息推送线程

	@Resource
	private RedisOperator redisOperator;

	private static boolean stopFlag;

	@RequestMapping("/to_list")
	public ModelAndView toList() {
		ModelAndView mv = new ModelAndView("/analysis/sms_send_list");

		List<String> mbcList = mobileOperatorMapper.selectAllMobileCity();
		mv.addObject("datetimeStart", DateUtil.getDateYMD() + " 00:00:00");
		mv.addObject("datetimeEnd", DateUtil.getDateYMD() + " 23:59:59");
		mv.addObject("categoryList", SmsCategoryEnum.values());
		mv.addObject("statusList", SmsSendStatusEnum.values());
		mv.addObject("mbcList", mbcList);

		return mv;
	}

	@RequestMapping("/to_listStat")
	public ModelAndView toListStat() {
		ModelAndView mv = new ModelAndView("/analysis/sms_send_list_stat");

		mv.addObject("datetimeStart", DateUtil.getDateYMD() + " 00:00:00");
		mv.addObject("datetimeEnd", DateUtil.getDateYMD() + " 23:59:59");
		mv.addObject("categoryList", SmsCategoryEnum.values());
		mv.addObject("statusList", SmsSendStatusEnum.values());
		return mv;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/list_data", produces = "application/json")
	public ModelAndView getSmsListData(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("params") String params) {
		ModelAndView mv = new ModelAndView("/analysis/sms_send_list_data");
		try {
			User us = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
			if (us == null) {
				logger.warn("用户登录超时!");
				return new ModelAndView("redirect:/login.html");
			}

			SmsSendQuery query = null;
			logger.info("【短信发送查询列表】参数={0}", new Object[] { params }, null);
			if (!StringUtil.isEmpty(params)) {
				query = JsonUtil.parseToObject(params, SmsSendQuery.class);
			}
			if (query == null) {
				query = new SmsSendQuery();
			}

			Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query.getPageNum() - 1);
			Integer pageSize = query.getPageSize() == 0 ? Constant.PAGE_SIZE_20 : query.getPageSize();
			query.setPageNum(pageNum);
			query.setPageSize(pageSize);

			List<String> apiAccounts = accountBindInfoService.getApiAccountByBusName(us.getUserName());
			List<String> apiAccountsLower = new ArrayList<String>();
			if (apiAccounts.size() > 0) {
				for (String apiAccount : apiAccounts) {
					apiAccountsLower.add(apiAccount.toLowerCase());
				}
			}
			query.setApiAccounts(apiAccountsLower);

			String result = esService.searchSmsSend(query, Constant.ES_SMS_SEND_INDEX_NAME,
					Constant.ES_SMS_SEND_INDEX_TYPE);
			JSONObject json = JSONObject.parseObject(result);
			List list = new ArrayList<>();
			if (null != json) {
				JSONArray jsonArray = json.getJSONArray("data");
				if (null != jsonArray && jsonArray.size() > 0) {
					for (int i = 0; i < jsonArray.size(); i++) {
						JSONObject dataObj = jsonArray.getJSONObject(i);
						list.add(dataObj);
					}
				}
			}
			// 构建查询结果对象
			ResultQuery pgdata = new ResultQuery();
			pgdata.setPage_num(Long.valueOf(query.getPageNum() + 1));
			pgdata.setPage_size(Long.valueOf(query.getPageSize()));
			pgdata.setTotal(json.getLong("total"));
			pgdata.setData(list);
			mv.addObject("pgdata", pgdata);
			mv.addObject("total_fee_num", json.getLong("total_fee_num"));

			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【短信发送查询列表】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
			return mv;
		}

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/list_datastat", produces = "application/json")
	public ModelAndView getSmsStatListData(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("params") String params) {
		ModelAndView mv = new ModelAndView("/analysis/sms_send_list_data_stat");
		try {
			User us = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
			if (us == null) {
				logger.warn("用户登录超时!");
				return new ModelAndView("redirect:/login.html");
			}

			SmsSendQuery query = null;
			logger.info("【短信发送查询列表】参数={0}", new Object[] { params }, null);
			if (!StringUtil.isEmpty(params)) {
				query = JsonUtil.parseToObject(params, SmsSendQuery.class);
			}
			if (query == null) {
				query = new SmsSendQuery();
			}

			Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query.getPageNum() - 1);
			Integer pageSize = query.getPageSize() == 0 ? Constant.PAGE_SIZE_20 : query.getPageSize();
			query.setPageNum(pageNum);
			query.setPageSize(pageSize);

			List<String> apiAccounts = accountBindInfoService.getApiAccountByBusName(us.getUserName());
			List<String> apiAccountsLower = new ArrayList<String>();
			if (apiAccounts.size() > 0) {
				for (String apiAccount : apiAccounts) {
					apiAccountsLower.add(apiAccount.toLowerCase());
				}
			}
			query.setApiAccounts(apiAccountsLower);

			String result = esService.searchSmsSend(query, Constant.ES_SMS_SEND_INDEX_NAME,
					Constant.ES_SMS_SEND_INDEX_TYPE);
			JSONObject json = JSONObject.parseObject(result);
			List list = new ArrayList<>();
			if (null != json) {
				JSONArray jsonArray = json.getJSONArray("data");
				if (null != jsonArray && jsonArray.size() > 0) {
					for (int i = 0; i < jsonArray.size(); i++) {
						JSONObject dataObj = jsonArray.getJSONObject(i);
						list.add(dataObj);
					}
				}
			}
			// 构建查询结果对象
			ResultQuery pgdata = new ResultQuery();
			pgdata.setPage_num(Long.valueOf(query.getPageNum() + 1));
			pgdata.setPage_size(Long.valueOf(query.getPageSize()));
			pgdata.setTotal(json.getLong("total"));
			pgdata.setData(list);
			mv.addObject("pgdata", pgdata);
			mv.addObject("total_fee_num", json.getLong("total_fee_num"));

			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【获取账号列表】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
			return mv;
		}

	}

	@RequestMapping(value = "/rePushStat", produces = "application/json")
	@ResponseBody
	public String rePushStat(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("params") String params) {
		try {
			User us = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
			if (us == null) {
				logger.warn("用户登录超时!");
				return "1"; // new ModelAndView("redirect:/login.html");
			}
			SmsSendQuery query = null;
			logger.info("【短信发送查询列表】参数={0}", new Object[] { params }, null);
			if (!StringUtil.isEmpty(params)) {
				query = JsonUtil.parseToObject(params, SmsSendQuery.class);
			}
			if (query == null) {
				query = new SmsSendQuery();
			}

			Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query.getPageNum() - 1);
			Integer pageSize = query.getPageSize() == 0 ? Constant.PAGE_SIZE_20 : query.getPageSize();
			pageSize = 500;// 1000
			query.setPageNum(pageNum);
			query.setPageSize(pageSize);

			List<String> apiAccounts = accountBindInfoService.getApiAccountByBusName(us.getUserName());
			List<String> apiAccountsLower = new ArrayList<String>();
			if (apiAccounts.size() > 0) {
				for (String apiAccount : apiAccounts) {
					apiAccountsLower.add(apiAccount.toLowerCase());
				}
			}
			query.setApiAccounts(apiAccountsLower);
			stopFlag = false;
			long total = 0;
			if (!stopFlag) {
				JSONObject json = searchLeftResult(query);
				total = json.getLong("total");
				long cyc = total / pageSize + (total % pageSize == 0 ? 0 : 1);
				pageNum++;
				while (pageNum < cyc) {
					if (stopFlag) {
						break;
					}
					query.setPageNum(pageNum);
					searchLeftResult(query);
					pageNum++;
					Thread.sleep(50);
				}
			}
			if (total == 0) {
				return "0";
			}
			if (stopFlag) {
				return "4";
			}
			return "2";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【获取账号列表】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
			return "3";
		}

	}

	@RequestMapping(value = "/stopPushStat", produces = "application/json")
	@ResponseBody
	public String stopPushStat() {
		stopFlag = true;
		return stopFlag + "";
	}

	private JSONObject searchLeftResult(SmsSendQuery query) {
		String result = esService.searchRepushSmsSend(query, Constant.ES_SMS_SEND_INDEX_NAME,
				Constant.ES_SMS_SEND_INDEX_TYPE);
		JSONObject json = JSONObject.parseObject(result);
		// List list = new ArrayList<>();
		if (null != json) {
			JSONArray jsonArray = json.getJSONArray("data");
			if (null != jsonArray && jsonArray.size() > 0) {
				for (int i = 0; i < jsonArray.size(); i++) {
					JSONObject dataObj = jsonArray.getJSONObject(i);
					pushStatus(dataObj);
					// list.add(dataObj);
				}
			}
		}
		return json;
	}

	/**
	 * 补推状态报告
	 *
	 * @param smsPv
	 */
	public void pushStatus(final JSONObject dataObj) {

		// 推送状态报告
		clientThread.execute(new Runnable() {
			@Override
			public void run() {

				try {
					if (stopFlag) {
						return;
					}
					String receiveStatus = dataObj.getString("RECEIVE_STATUS");
					String sendResult = dataObj.getString("SEND_RESULT");
					if ("2".equals(receiveStatus) && "0".equals(sendResult)) {
						return;
					}
					// 够造组装报告数据
					Map<String, String> statusMap = new HashMap<String, String>();
					statusMap.put("apiAccount", dataObj.getString("API_ACCOUNT"));
					statusMap.put("appId", dataObj.getString("APP_ID"));
					statusMap.put("smsId", dataObj.getString("SMS_ID"));
					statusMap.put("mobile", dataObj.getString("RECEIVE_MOBILE"));
					statusMap.put("smsCateGory", dataObj.getString("SMS_CATEGORY"));
					statusMap.put("userData", dataObj.getString("USER_DATA"));
					statusMap.put("fee", "" + ValueUtil.getDouble(dataObj.getString("SMS_FEE")));// SMS_FEE
																									// "0"//10000
					statusMap.put("status", receiveStatus);// RECEIVE_STATUS
															// 运营商返回状态(0:成功(收到短信);1:返回失败;2:未知(运营商未返回)

					if ("1".equals(sendResult)) {// 提交失败
						statusMap.put("receiveChannelStatusDesc", "UNDELIV");// "-101"
						statusMap.put("receiveStatusDescChs", "提交失败");// dataObj.getString("TRACE_LOG")
					} else {
						statusMap.put("receiveChannelStatusDesc", dataObj.getString("RECEIVE_STATUS_DESC"));// "-101"
						statusMap.put("receiveStatusDescChs", resetStatusDescChs(dataObj.getString("RECEIVE_STATUS"),
								dataObj.getString("RECEIVE_STATUS_DESC")));// 审核不通过
					}
					long createTime = DateUtil.date2TimeStamp(dataObj.getString("CREATE_TIME"), "yyyy-MM-dd HH:mm:ss");
					statusMap.put("createTime", createTime + "");// smsPv.getTimerId()
					String smsTime = DateUtil.getDateTime();
					if (0 != dataObj.getLongValue("RECEIVE_TIME")) {
						smsTime = DateUtil.formatDate(new Date(dataObj.getLongValue("RECEIVE_TIME")),
								DateUtil.ISO_DATE_TIME_FORMAT);
					}
					statusMap.put("smsTime", smsTime);// TODO
														// DateUtil.getDateTime()
					statusMap.put("statusPushAddr", (null == dataObj.getString("STATUS_PUSH_ADDR")) ? ""
							: dataObj.getString("STATUS_PUSH_ADDR"));
					statusMap.put("protocolType", dataObj.getString("PROTOCOL_TYPE"));
					statusMap.put("uId", (null == dataObj.getString("UID")) ? "" : dataObj.getString("UID"));

					String data = JsonUtil.toJsonString(statusMap);
					String result = HttpClientUtil.INSTANCE.httpPost(pushStatusUrl, data, null);
					logger.info("【短信状态报告es记录重推】,组装报告数据,pushResult=" + result);

				} catch (Exception e) {
					logger.error("【短信状态报告es记录重推】,组装报告数据,异常error=" + e.getMessage());
				}
			}
		});
	}

	/**
	 * 状态发送结果描述
	 * 
	 * @param code
	 * @return
	 */
	public String resetStatusDescChs(String stat, String desc) {

		if (("0").equals(stat)) {
			return "成功";
		} else {
			String descChs = redisOperator.hget(RedisConstant.ZHIYU_PAAS_SMS_STATUS_ERRORCODE_LIST, desc);
			if (descChs != null) {
				return descChs;
			}
		}
		return "";
	}

	@RequestMapping("/export")
	@ResponseBody
	public WebResult doexport(HttpServletRequest request,
			@RequestParam(value = "params", required = false) String params) {
		logger.info("【 短信发送记录导出】开始  params=" + params);
		WebResult webRs = new WebResult();
		try {
			User user = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
			if (user == null) {
				logger.warn("用户登录超时!");
				webRs.setCode(-1);
				webRs.setMsg("用户登录超时!");
			}
			SmsSendQuery query = null;
			if (!StringUtil.isEmpty(params)) {
				query = JsonUtil.parseToObject(params, SmsSendQuery.class);
			}

			Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query.getPageNum() - 1); // es分页码从0开始，所以-1
			query.setPageNum(pageNum);
			query.setPageSize(1000); // 按分片(10分片，每次查询1000*10)
			query.setPageCount(300000);// 最大导出30万
			List<String> apiAccounts = accountBindInfoService.getApiAccountByBusName(user.getUserName());
			query.setApiAccounts(apiAccounts);

			// 调用导出业务
			String realPath = request.getSession().getServletContext().getRealPath("");
			webRs = smsSendRecordService.exportExcel(query, realPath, request.getContextPath());
		} catch (Exception e) {
			logger.info("【短信发送记录汇总下载】出现异常,异常是" + e.getMessage());
			e.printStackTrace();
		}

		logger.info("【短信发送记录汇总导出】结束...");
		return webRs;

	}
}
