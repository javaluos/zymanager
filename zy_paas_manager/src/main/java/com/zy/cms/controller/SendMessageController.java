package com.zy.cms.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.util.HttpClientUtil;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.MD5Util;

/**
 * 发送短信接口
 * @author d20zh32
 *
 */
@Controller
@RequestMapping(value = "")
public class SendMessageController {

	private static final ZyLogger logger = ZyLogger.getLogger(SendMessageController.class);

	@RequestMapping(value = "/sendMessage", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String sendMessage(String content, String mobile,HttpServletRequest req, HttpServletResponse response) {
		Map<String,String> resultMap=new HashMap<String,String>();
		try {
		    content=new String((content).getBytes("iso-8859-1"),"utf-8");
		    logger.info("【运维发送短信】发送号码为:" + mobile+" 内容为:"+content);
		    
			if((StringUtils.isBlank(content)||StringUtils.isBlank(mobile))&&(StringUtils.isEmpty(content)||StringUtils.isEmpty(mobile))){
				resultMap.put("result", "-1");
				resultMap.put("reason", "短信发送内容/接收手机号码不能为空！");
				return JsonUtil.toJsonString(resultMap);
			}
			content=content+"【智语科技】";
			String result=sendNoticeSms(mobile,content);
			return result;
		} catch (Exception e) {
			logger.error("【运维发送短信】发送短信出现异常,异常为:" + e.getMessage());
			resultMap.put("result", "-1");
			resultMap.put("reason", "短信发送出现异常！");
			return  JsonUtil.toJsonString(resultMap);
		}
	}
	
	/**
	 * 给用户发送短信
	 * @param mobile  手机号码
	 * @param content 发送的内容
	 * @return
	 */
	public String sendNoticeSms(String mobile, String content) {
		String url = "http://sms.zhiyan.net/sms/smscc/single/";
		String appkey = "3d590e16b27741c5b60204a55ca247ef";
		String rsp = "";
		try {
			// 时间戳
			long timestamp = System.currentTimeMillis();
			String uid = UUID.randomUUID().toString().replaceAll("-", "");
			// MD5加密处理
			String ll = appkey + mobile + uid + content + timestamp;
			String sign = MD5Util.md5Hex(ll);
			url = url + appkey + "?timestamp=" + timestamp + "&sign=" + sign;
			Map<String, String> params = new HashMap<String, String>();
			Map<String, String> map = new HashMap<String, String>();
			map.put("mobile", mobile);
			map.put("content", content);
			map.put("uid", uid);
			map.put("extend", "");
			params.put("data", JsonUtil.objectToJson(map));
			Map<String, String> header = new HashMap<String, String>();
			rsp = HttpClientUtil.INSTANCE.httpPost(url, params, header);
			logger.info("【运维发送短信,短信系统】返回：" + rsp);
		} catch (Exception e) {
			logger.error("【运维发送短信,短信系统】异常" + e.getMessage(), e);
		}
		return rsp;
	}
}
