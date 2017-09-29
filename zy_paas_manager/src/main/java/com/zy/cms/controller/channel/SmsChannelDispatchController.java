package com.zy.cms.controller.channel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zy.cms.common.CommonService;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.elastic.GlobalConfig;
import com.zy.cms.mapper.master.SmsChannelDispatchMapper;
import com.zy.cms.service.manager.SmsChannelBalMonitorService;
import com.zy.cms.service.master.SmsChannelBindService;
import com.zy.cms.service.master.SmsChannelService;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.channel.SmsChannel;

/**
 * 短信通道对接信息控制器
 * 
 * 
 * @author allen.yuan
 * @date 2017-07-10
 */
@Controller
@RequestMapping(value = "/smschannel")
public class SmsChannelDispatchController {

	private static final ZyLogger logger = ZyLogger.getLogger(SmsChannelDispatchController.class);

	private String theme = "【通道对接信息】";

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

	@Resource
	private SmsChannelDispatchMapper smsChannelDispatchMapper;

	private static Map<String, String> dispathCfgMap = new HashMap<String, String>(10);
	private static Map<String, String> dispathCfgExtMap = new HashMap<String, String>(5);

	static {
		dispathCfgMap.put("CHANNEL_ID", "");
		dispathCfgMap.put("CHANNEL_NAME", "");
		dispathCfgMap.put("PROTOCOL_TYPE", "");
		dispathCfgMap.put("ACCOUNT", "");
		dispathCfgMap.put("PASSWORD", "");
		dispathCfgMap.put("SMS_MT_URL", "");
		dispathCfgMap.put("SMS_MO_URL", "");
		dispathCfgMap.put("SMS_STATUS_URL", "");
		dispathCfgMap.put("USERID", "");
		dispathCfgMap.put("TASKNAME", "");
	}

	/**
	 * 查询通道配置信息
	 * 
	 * @param channelId
	 * @return
	 */
	@RequestMapping("/channeldispatch_show")
	public ModelAndView showSmsChannel(@RequestParam(value = "channelId", required = false) String channelId) {

		logger.debug(theme + "添加界面开始, channelId=" + channelId + ".");

		ModelAndView mv = new ModelAndView("/smschannel/show_channeldispatch");

		Map<String, String> cfgMap = new HashMap<String, String>();
		List<Map<String, String>> extList = new ArrayList<Map<String, String>>();

		SmsChannel vo = smsChannelService.selectByPrimaryKey(channelId);
		if (StringUtil.notEmpty(channelId)) {
			List<Map<String, String>> list = smsChannelDispatchMapper.getSmsChannelDispatchByCId(channelId);
			if (list == null || list.size() == 0) {
				
				if (vo != null) {
					cfgMap.putAll(dispathCfgExtMap);
					cfgMap.put("CHANNEL_ID", vo.getChannelId());
					cfgMap.put("CHANNEL_NAME", vo.getChannelName());
					cfgMap.put("PROTOCOL_TYPE", "0");
					mv.addObject("opType", "0"); // 新增
				}
			} else {
				cfgMap.putAll(dispathCfgExtMap);
				for (Map<String, String> map : list) {
					if (String.valueOf(map.get("PARAM_TYPE")).equals("0")) { // 默认参数
						cfgMap.put(map.get("PARAM_KEY"), map.get("PARAM_VALUE"));
					}
					if (String.valueOf(map.get("PARAM_TYPE")).equals("1")) {// 拓展参数
						Map<String, String> cfgExtMap = new HashMap<String, String>();
						cfgExtMap.put("PARAM_KEY", map.get("PARAM_KEY"));
						cfgExtMap.put("PARAM_VALUE", map.get("PARAM_VALUE"));

						extList.add(cfgExtMap);
					}
				}
				mv.addObject("opType", "1"); // 修改
			}
		}

		mv.addObject("vo", vo);
		mv.addObject("cfgMap", cfgMap);
		mv.addObject("extList", extList);
		logger.debug(theme + "添加界面结束, channelId=" + channelId + ".");

		return mv;
	}

	/**
	 * 保存通道对接配置信息
	 * 
	 * @param params
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/channeldispatch_save", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String saveSmsChannel(@RequestParam(value = "params", required = false) String params) {

		logger.debug(theme + "保存数据开始, params=" + params + ".");
		Map<String, String> mapData = null;
		if (!StringUtil.isEmpty(params)) {
			mapData = JsonUtil.parseToObject(params, Map.class);
		}

		String chanenlId = mapData.get("CHANNEL_ID");
		int result = 0;
		if (StringUtil.notEmpty(chanenlId)) {
			smsChannelDispatchMapper.delSmsChannelDispatchByCId(chanenlId); // 先删除，后添加

			Map<String, String> inMap = new HashMap<String, String>();
			inMap.put("CHANNEL_ID", chanenlId);
			for (Map.Entry<String, String> et : mapData.entrySet()) {

				inMap.put("PARAM_KEY", et.getKey().toUpperCase());
				inMap.put("PARAM_VALUE", et.getValue());
				inMap.put("PARAM_TYPE", "0"); // 默认参数
				if (!dispathCfgMap.containsKey(et.getKey())) {
					inMap.put("PARAM_TYPE", "1");// 拓展参数
				}

				smsChannelDispatchMapper.saveSmsChannelDispatch(inMap);
			}
			result = 1;
		}

		logger.debug(theme + "保存数据结束, result=" + result + ".");
		return String.valueOf(result);
	}
}
