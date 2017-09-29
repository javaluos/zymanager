package com.zy.cms.common;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.zy.cms.service.manager.UserService;
import com.zy.cms.service.master.MerchantAccountService;
import com.zy.cms.util.CookieUtil;
import com.zy.cms.util.DESUtil;
import com.zy.cms.util.HttpClientUtil;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.MD5Util;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.MerchantAccount;
import com.zy.cms.vo.User;

/**
 * 公共服务
 * 
 * @author hmj
 */
@Component
public class CommonService {

	private static final ZyLogger logger = ZyLogger
			.getLogger(CommonService.class);
	@Resource
	private CacheService cacheService;
	@Resource
	private RedisOperator redisOperator;
	@Resource
	private UserService userService;

	/**
	 * 发短信
	 * 
	 * @param mobile
	 *            手机号
	 * @param extend
	 *            扩展字段
	 * @param code
	 *            验证码
	 * @param keyTemplate
	 *            在rediskey
	 * @param time
	 *            时间
	 * @param merchantAccount
	 *            此参数主要是短信发送失败时向系统发消息使用
	 * @return
	 */
	public String sendRegSms(String mobile, String extend, String code,
			String keyTemplate, Integer time) {
		String appkey = cacheService.getConfig("SYS_SMS_APPKEY");
		String token = cacheService.getConfig("SYS_SMS_TOKEN");
		String systemplate = cacheService.getConfig("SYS_SMS_TEMPLATE");
		String templateId = "";
		Map systemplateMap = JsonUtil.parseToObject(systemplate, Map.class);
		templateId = systemplateMap.get("SYS_SMS_TEMPLATE_ID") + "";
		return sendRegSms(appkey, token, templateId, mobile, extend, code,
				keyTemplate, time);
	}
	
	

	/**
	 * 发短信
	 * 
	 * @param token
	 *            token
	 * @param templateId
	 *            短信末班
	 * @param mobile
	 *            手机号
	 * @param extend
	 *            扩展字段
	 * @param code
	 *            验证码
	 * @param keyTemplate
	 *            在rediskey
	 * @param time
	 *            时间
	 * @param merchantAccount
	 *            此参数主要是短信发送失败时向系统发消息使用
	 * @return
	 */
	public String sendRegSms(String appkey, String token, String templateId,
			String mobile, String extend, String code, String keyTemplate,
			Integer time) {
		int status = -1;
		boolean result = false;
		if (StringUtil.isEmpty(appkey)) {
			return "";
		}
		if (StringUtil.isEmpty(token)) {
			return "";
		}
		if (StringUtil.isEmpty(templateId)) {
			return "";
		}
		if (StringUtil.isEmpty(mobile)) {
			return "";
		}

		long timestap = System.currentTimeMillis();
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("mobile", mobile);
		map.put("param", code);
		map.put("extend", extend);
		params.put("data", JsonUtil.objectToJson(map));
		String ZyUrl = cacheService.getConfig("SEND_SMS_URL") + "single/";
		if (StringUtil.isEmpty(extend)) {
			extend = Constant.ENPTY_STRING;
		}
		if (StringUtil.isEmpty(code)) {
			code = Constant.ENPTY_STRING;
		}
		String signKey = new StringBuilder(appkey).append(token)
				.append(templateId).append(mobile).append(code).append(extend)
				.append(timestap).toString();
		logger.info("signKey=" + signKey);
		String sign = MD5Util.md5Hex(signKey);
		String url = ZyUrl + appkey + "/" + token + "/" + templateId
				+ "?timestamp=" + timestap + "&sign=" + sign;
		String httpResult = "";
		try {
			logger.info("【短信系统】URL=" + url + "  params=" + params);
			httpResult = HttpClientUtil.INSTANCE.httpPost(url, params, null);

			if (StringUtil.isEmpty(httpResult)) {
				logger.error("【短信系统】响应为空");
			}
			logger.info("【短信系统】httpResult=" + httpResult + ",code=" + code);
			Map rs = JsonUtil.parseToObject(httpResult, Map.class);
			if (rs.get("result").toString().equals("SUCCESS")) {
				if (!StringUtil.isEmpty(keyTemplate)) {
					String key = String.format(keyTemplate, mobile);
					redisOperator.setex(key, time != null ? time.intValue()
							: Constant.VERIFY_CODE_IN_REDIS_MINUTES, code);
				}
				result = true;
			}
			return httpResult;
		} catch (Exception e) {
			logger.error("【短信系统】异常" + e.getMessage(), e);
		}
		return httpResult;
	}

	/**
	 * 验证code
	 * 
	 * @param phone
	 *            手机号
	 * @param code
	 *            code
	 * @param keyTemplate
	 *            key末班
	 * @return
	 */
	public boolean valideCode(String phone, String code, String keyTemplate) {
		String key = String.format(keyTemplate, phone);
		String redisCode = redisOperator.get(key);
		boolean flag = false;
		logger.info("【验证code】code={0},redisCode={1}", new Object[] { code,
				redisCode }, null);
		if (StringUtil.isEmpty(redisCode)) {
			return flag;
		}
		if (redisCode.toUpperCase().equals(code.toUpperCase())) {
			flag = true;
			redisOperator.del(key);// 删除该key
		}
		logger.info("【验证code】移除key={0}成功,flag={1}", new Object[] { key, flag },
				null);
		return flag;
	}

	/**
	 * 验证用户有效性
	 * 
	 * @param req
	 * @param key
	 * @return
	 */
	public User valideUser(HttpServletRequest req, String key) {

		// 从cookie里面获取username
		Cookie userNameCk = CookieUtil.getCookieByCookieName(req, key);
		if (null == userNameCk || StringUtil.isEmpty(userNameCk.getValue())) {
			return null;
		}
		String username = userNameCk.getValue();
		logger.info("cookile name:" + username);
		User user = userService.findUserByName(username);

		return user;
	}
	
	 /**
     * 将比率转换成百分比(保留两位小数)
     * @param rate
     * @return
     */
    public String doubleToRate(Double rate) {
        BigDecimal decimal = new BigDecimal(rate * 100);
        Double rateDouble = decimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return rateDouble + "%";
    }
	
    
    /**
	 * 验证敏感词 
	 * @param params
	 * @throws SmsException
	 */
	public String valideBlackKey(String params){
		if (StringUtil.isEmpty(params)) {
			return "";
		}
		long beginTime = System.currentTimeMillis();
		Set<String> keyResult = BlackKeyFilter.validateFilterBlackKey(params, BlackKeyFilter.minMatchTYpe);
		long endTime = System.currentTimeMillis();
		logger.info("【验证param】关键字过滤花费时长:" + (endTime - beginTime) + "ms");
		if (keyResult.size() > 0) {
			String message = keyResult.toString();
			logger.error("【验证param】包含敏感词:(" + message + ")");
			return message;
		}
		return "";
	}
	
	public static void main(String[] args) {
		
		String sign = MD5Util.md5Hex("zykj@us2016");
		System.out.println(sign);
	}
}
