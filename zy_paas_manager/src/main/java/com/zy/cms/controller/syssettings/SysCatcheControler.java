package com.zy.cms.controller.syssettings;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zy.cms.common.CacheService;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.util.HttpClientUtil;

/**
 * 系统刷新缓存配置
 * 
 * @author allen.yuan
 * 
 * @date 2017-9-13
 *
 */
@Controller
@RequestMapping("/syscache")
public class SysCatcheControler {

	private static final ZyLogger logger = ZyLogger.getLogger(SysCatcheControler.class);

	@Autowired
	private CacheService cacheService;

	/**
	 * 缓存列表界面
	 * 
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/syscache_list")
	public ModelAndView querySysCacheList(HttpServletRequest request, HttpServletResponse response) {

		// 定义结果
		ModelAndView mv = new ModelAndView("/syssettings/syscache_list");
		return mv;
	}

	/**
	 * 短信接口缓存(短信系统配置, 手机号运营商, 短信敏感词, 短信IP鉴权配置 等)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/refreshSmsAPICfg", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String refreshSmsAPICfg(HttpServletRequest request, HttpServletResponse response) {

		logger.info("【缓存刷新】刷新短信接口缓存开始======== ");
		String urllist = cacheService.getConfig("REFRESH_BACKWORDS_URL");
		if (!StringUtils.isBlank(urllist)) {
			String[] urlArr = urllist.split("\\;");
			for (String url : urlArr) {
				if (!StringUtils.isBlank(url)) {
					try {
						HttpClientUtil.INSTANCE.httpPost(url, "", null);
					} catch (Exception e) {
						logger.error("【刷新短信接口缓存配置 异常】url=" + url + ",error=" + e.getMessage());
						return "-1";
					}
				}
			}
		}
		logger.info("【缓存刷新】刷新短信接口缓存结束======== ");
		return "SUCCESS";
	}

	/**
	 * 短信CMPP账号缓存 (短信CMPP2.0账号, 短信CMPP3.0账号 等)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/refreshCmppAcctInfo", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String refreshCMPPCache(HttpServletRequest request, HttpServletResponse response) {

		logger.info("【缓存刷新】刷新短信CMPP账号缓存开始======== ");
		String urllist = cacheService.getConfig("REFRESH_CMPPACCOUNT_URL");
		if (!StringUtils.isBlank(urllist)) {
			String[] urlArr = urllist.split("\\;");
			for (String url : urlArr) {
				if (!StringUtils.isBlank(url)) {
					try {
						HttpClientUtil.INSTANCE.httpPost(url, "", null);
					} catch (Exception e) {
						logger.error("【刷新 短信CMPP账号缓存 异常】url=" + url + ",error=" + e.getMessage());
						return "-1";
					}
				}
			}
		}
		logger.info("【缓存刷新】刷新 短信CMPP账号缓存结束======== ");
		return "SUCCESS";
	}

	/**
	 * 语音接口配置缓存 (语音系统配置, 语音API资源配置 等)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/refreshVoiceAPICfg", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String refreshVoiceAPICFGCache(HttpServletRequest request, HttpServletResponse response) {

		logger.info("【缓存刷新】刷新语音接口配置缓存开始======== ");
		String urllist = cacheService.getConfig("REFRESH_VOICEAPICFG_URL");
		if (!StringUtils.isBlank(urllist)) {
			String[] urlArr = urllist.split("\\;");
			for (String url : urlArr) {
				if (!StringUtils.isBlank(url)) {
					try {
						HttpClientUtil.INSTANCE.httpPost(url, "", null);
					} catch (Exception e) {
						logger.error("【刷新语音接口配置 异常】url=" + url + ",error=" + e.getMessage());
						return "-1";
					}
				}
			}
		}
		logger.info("【缓存刷新】刷新语音接口配置缓存结束======== ");
		return "SUCCESS";
	}

}
