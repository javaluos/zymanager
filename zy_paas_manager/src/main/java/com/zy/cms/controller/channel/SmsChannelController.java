package com.zy.cms.controller.channel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zy.cms.common.CommonService;
import com.zy.cms.common.Constant;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.elastic.GlobalConfig;
import com.zy.cms.service.manager.SmsChannelBalMonitorService;
import com.zy.cms.service.master.SmsChannelBindService;
import com.zy.cms.service.master.SmsChannelService;
import com.zy.cms.util.HttpClientUtil;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.SmsChannelBalMonitorSetting;
import com.zy.cms.vo.User;
import com.zy.cms.vo.channel.SmsChannel;
import com.zy.cms.vo.query.ResultQuery;
import com.zy.cms.vo.query.SmsChannelQuery;

/**
 * 短信通道信息控制器
 * 
 * 
 * @author allen.yuan
 * @date 2017-01-21
 */
@Controller
@RequestMapping(value = "/smschannel")
public class SmsChannelController {

	private static final ZyLogger logger = ZyLogger.getLogger(SmsChannelController.class);

	private String theme = "【通道信息】";

	@Resource
	private SmsChannelService smsChannelService;
	
	@Resource
	private SmsChannelBalMonitorService smsChannelBalMonitorService;

	@Resource
	private CommonService commonService;
	
	@Resource
	private SmsChannelBindService smsChannelBindService;
	
	@Resource
	private GlobalConfig globalConfig;
	
	/**
	 * 查询通道信息列表UI
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/smschannel_list")
	public ModelAndView queryActList(HttpServletRequest request, HttpServletResponse response) {

		logger.debug(theme + "查询界面开始");
		// 定义结果
		ModelAndView mv = new ModelAndView("/smschannel/smschannel_list");
		return mv;
	}

	/**
	 * 查询账号信息列表Data
	 * 
	 * @param request
	 * @return mv JSPTable
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/smschannel_data", produces = "application/json")
	public ModelAndView queryMerchantAccounts(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("params") String params) {

		logger.debug(theme + "加载数据业务开始, params=" + params + ".");
		// 定义结果
		ModelAndView mv = new ModelAndView("/smschannel/smschannel_list_data");

		SmsChannelQuery query = null;
		if (!StringUtil.isEmpty(params)) {
			query = JsonUtil.parseToObject(params, SmsChannelQuery.class);
		}
		if (query == null) {
			query = new SmsChannelQuery();
		}

		Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query.getPageNum() - 1);
		Integer pageSize = query.getPageSize() == 0 ? Constant.PAGE_SIZE_20 : query.getPageSize();
		query.setPageNum(pageNum);
		query.setPageSize(pageSize);

		Integer total = smsChannelService.querySmsChannelCountByEntity(query);
		List<SmsChannel> list = smsChannelService.querySmsChannelListByEntity(query);

		// 构建查询结果对象
		ResultQuery pgdata = new ResultQuery();
		pgdata.setPage_num(Long.valueOf(query.getPageNum() + 1));
		pgdata.setPage_size(Long.valueOf(query.getPageSize()));
		pgdata.setTotal(Long.valueOf(total));
		pgdata.setData(list);

		// 设定查询参数对象['pgdata'为JSP参数,不随意修改]
		mv.addObject("pgdata", pgdata);

		logger.debug(theme + "加载数据业务结束, params=" + params + ".");
		return mv;
	}

	@RequestMapping("/smschannel_show")
	public ModelAndView showSmsChannel(@RequestParam(value = "channelId", required = false) String channelId) {

		logger.debug(theme + "添加界面开始, channelId=" + channelId + ".");

		ModelAndView mv = new ModelAndView("/smschannel/smschannel_show");
		if (StringUtil.notEmpty(channelId)) {
			SmsChannel vo = smsChannelService.selectByPrimaryKey(channelId);
			mv.addObject("obj", vo);
		}
		if (StringUtil.isEmpty(channelId)) {
			mv.addObject("opType", "0"); // 新增
		} else {
			mv.addObject("opType", "1"); // 修改
		}
		List<String> provinceList = smsChannelService.getProvinceList();
		mv.addObject("provinceList", provinceList);
		logger.debug(theme + "添加界面结束, channelId=" + channelId + ".");

		return mv;
	}

	/**
	 * 保存通道信息
	 * 
	 * @param apiAccount
	 * @return
	 */
	@RequestMapping(value = "/smschannel_save", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String saveSmsChannel(@RequestParam(value = "params", required = false) String params) {

		logger.debug(theme + "保存数据开始, params=" + params + ".");

		SmsChannel channel = null;
		if (!StringUtil.isEmpty(params)) {
			channel = JsonUtil.parseToObject(params, SmsChannel.class);
		}

		int result = 0;
		if (channel != null && StringUtil.notEmpty(channel.getChannelMainCode())) {
			result = smsChannelService.saveSmsChannel(channel);
		}

		logger.debug(theme + "保存数据结束, result=" + result + ".");
		return String.valueOf(result);
	}

	@RequestMapping("/querySmsChannelBySmsChMainCode")
	@ResponseBody
	public SmsChannel querySmsChannelBySmsChMainCode(
			@RequestParam(value = "channelMainCode", required = false) String channelMainCode) {

		logger.debug(theme + "通过通道ID查询通道信息开始, channelMainCode=" + channelMainCode + ".");
		SmsChannel vo = smsChannelService.selectByChannelMainCode(channelMainCode);

		logger.debug(theme + "通过通道ID查询通道信息结束, channelVO=" + JsonUtil.objectToJson(vo) + ".");
		return vo;
	}

	@RequestMapping("/smschannel_del")
	@ResponseBody
	public boolean delSmsChannel(@RequestParam(value = "channelId", required = false) String channelId) {
		boolean result = false;
		logger.info(theme + "删除通道开始, channelId=" + channelId + ".");

		result = smsChannelService.delSmsChannel(channelId);
		logger.info(theme + "删除通道结束, channelId=" + channelId + ".");

		return result;
	}
	
	
	/**
	 * 将通道加入到余额监控列表中
	 * 
	 * @param apiAccount
	 * @return
	 */
	@RequestMapping(value = "/save_channel_balance", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public Object saveSmsChannelBalance(HttpServletRequest request,
			@RequestParam(value = "channelId", required = false)      String channelId,
			@RequestParam(value = "channelBalance", required = false) String channelBalance,
			@RequestParam(value = "balanceUnit", required = false) String balanceUnit) {

		User user = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
		logger.debug("保存余额监控开始, channelId=" + channelId + " channelBalance="+channelBalance+ " balanceUnit="+balanceUnit);
		try {
			if (null == user){
				logger.warn("找不到用户，或者用户的登陆cookie已经过期");
				return new ModelAndView("redirect:/login.html");
			}
			if (StringUtil.isEmpty(channelId)||StringUtil.isEmpty(channelBalance)||StringUtil.isEmpty(balanceUnit)) {
				return "false";
			}
			SmsChannel smsChannel=smsChannelService.selectByPrimaryKey(channelId);
			smsChannel.setBalanceMonitorFlag(1);
			int flags=smsChannelService.updateByPrimaryKeySelective(smsChannel);
			
			if(flags>0){
				SmsChannelBalMonitorSetting smsChannelBalMonitorSetting=new SmsChannelBalMonitorSetting();
				smsChannelBalMonitorSetting.setChannelId(channelId);
				if("0".equals(balanceUnit)){
					smsChannelBalMonitorSetting.setMonitorBalance(Integer.parseInt(channelBalance) * 1000);
				}else if("1".equals(balanceUnit)){
					smsChannelBalMonitorSetting.setMonitorBalance(Integer.parseInt(channelBalance));
				}
				smsChannelBalMonitorSetting.setBalanceUnit(Integer.parseInt(balanceUnit));
				int flag=smsChannelBalMonitorService.insert(smsChannelBalMonitorSetting);
				if(flag>0){
					return "true";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.debug("保存加入余额监控结束 ");
		return "false";
	}
	
	/**
	 * 将通道从余额监控列表中删除
	 * 
	 * @param apiAccount
	 * @return
	 */
	@RequestMapping(value = "/remove_channel_balance", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String removeSmsChannelBalance(HttpServletRequest request,
			@RequestParam(value = "channelId", required = false)  String channelId) {

		logger.debug("删除余额监控开始, channelId=" + channelId );
		try {
			if (StringUtil.isEmpty(channelId)) {
				return "false";
			}
			boolean result = smsChannelBalMonitorService.deleteMonitor(channelId);
			return result + "";
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.debug("删除余额监控结束 ");
		return "false";
	}
	
	/**
	 * 短信通道测试
	 * 
	 * @param apiAccount
	 * @return
	 */
	@RequestMapping(value = "/test_channel", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public Object testSmsChannel(HttpServletRequest request,
			@RequestParam(value = "channelId", required = false)   String channelId,
			@RequestParam(value = "smsContent", required = false)   String smsContent,
			@RequestParam(value = "mobile", required = false)       String mobile,
			@RequestParam(value = "extNumber", required = false)    String extNumber,
			@RequestParam(value = "smsType", required = false)      String smsType) {

		User user = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
		logger.debug("短信通道测试开始, smsContent=" + smsContent + " mobile="+mobile+ " exnumber="+extNumber+ " smsType="+smsType);
		try {
			if (null == user){
				logger.warn("找不到用户，或者用户的登陆cookie已经过期");
				return new ModelAndView("redirect:/login.html");
			}
			if (StringUtil.isEmpty(smsContent)||StringUtil.isEmpty(mobile)||StringUtil.isEmpty(smsType)) {
				return "false";
			}
			String apiAccount =globalConfig.getConfigV("SMS_CHANNEL_TEST_APIACCOUNT");
			String channels = channelId + "_30_1000,";
			boolean result=smsChannelBindService.dobindChannel(apiAccount, channels, user.getUserName());
			String rsp="";
			if(result){
				rsp=sendSms(mobile, smsContent, extNumber, smsType);
			}
            return rsp;			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("短信通道测试出现异常，异常是:"+e.getMessage());
		}
		logger.debug("短信通道测试结束 ");
		return "false";
	}
	
	private String  sendSms(String mobile, String content,String extNumber,String smsType) {
		String url=globalConfig.getConfigV("SMS_CHANNEL_TEST_URL");
		String apiAccount =globalConfig.getConfigV("SMS_CHANNEL_TEST_APIACCOUNT");
		String appId =globalConfig.getConfigV("SMS_CHANNEL_TEST_APPID");
		String sign =globalConfig.getConfigV("SMS_CHANNEL_TEST_SIGN");
		String timestamp =globalConfig.getConfigV("SMS_CHANNEL_TEST_TIMESTAMP");
		String rsp = "";
		try {
			// 时间戳
			// MD5加密处理
			Map<String, String> map = new HashMap<String, String>();
			map.put("apiAccount", apiAccount);
			map.put("appId", appId);
			map.put("sign", sign);
			map.put("timeStamp", timestamp);
			map.put("smsType", smsType);
			map.put("mobile", mobile);
			map.put("content", content);
			map.put("userData", "");
			map.put("statusPushAddr", "");
			map.put("extNumber", extNumber);
			String str=JsonUtil.objectToJson(map);
			
			rsp = HttpClientUtil.INSTANCE.httpPost(url,str,null);
			logger.info("【短信系统】返回："+rsp);
		} catch (Exception e) {
			logger.error("【短信系统】异常" + e.getMessage(), e);
		}
		return rsp;
	}
}
