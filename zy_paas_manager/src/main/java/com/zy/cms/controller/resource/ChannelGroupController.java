package com.zy.cms.controller.resource;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.TypeReference;
import com.zy.cms.common.Constant;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.service.master.MerchantAccountService;
import com.zy.cms.service.master.SmsChannelGroupBindService;
import com.zy.cms.service.master.SmsChannelGroupService;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.MerchantAccount;
import com.zy.cms.vo.channel.SmsChannelGroup;
import com.zy.cms.vo.channel.SmsMerchantChannelGroupBind;
import com.zy.cms.vo.query.AccountQuery;
import com.zy.cms.vo.query.ResultQuery;
import com.zy.cms.vo.query.SmsChannelGroupBindQuery;
import com.zy.cms.vo.query.SmsChannelGroupQuery;

/**
 * 短信通道组管理Controller Created by luos on 2017/7/4.
 */
@Controller
@RequestMapping("/channel_group")
public class ChannelGroupController {

	private static final ZyLogger logger = ZyLogger.getLogger(ChannelGroupController.class);

	@Autowired
	SmsChannelGroupService smsChannelGroupService;

	@Autowired
	SmsChannelGroupBindService smsChannelGroupBindService;

	@Autowired
	private MerchantAccountService merchantAccountService;

	@RequestMapping("/to_list")
	public ModelAndView toList() {
		ModelAndView mv = new ModelAndView("/channelgroup/channel_group_list");
		return mv;
	}

	@RequestMapping("/list_data")
	public ModelAndView getChannelGroupData(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("params") String params) {
		ModelAndView mv = new ModelAndView("/channelgroup/channel_group_list_data");
		try {
			SmsChannelGroupQuery query = null;
			logger.info("【通道组查询列表】参数={0}", new Object[] { params }, null);
			if (!StringUtil.isEmpty(params)) {
				query = JsonUtil.parseToObject(params, SmsChannelGroupQuery.class);
			}
			if (query == null) {
				query = new SmsChannelGroupQuery();
			}
			
			if(StringUtils.isNotEmpty(query.getChannelId())){
				List<String> groupIds=smsChannelGroupBindService.listGroupIdByChannelId(query.getChannelId());
				if(groupIds!=null){
					if(groupIds.size()>0){
						query.setChannelGroupIds(groupIds);
					}else{
						groupIds=new ArrayList<String>();
						groupIds.add("12345678909");
						query.setChannelGroupIds(groupIds);
					}
				}else{
					groupIds=new ArrayList<String>();
					groupIds.add("12345678909");
					query.setChannelGroupIds(groupIds);
				}
			}
			
			
			Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query.getPageNum() - 1);
			Integer pageSize = query.getPageSize() == 0 ? Constant.PAGE_SIZE_20 : query.getPageSize();
			query.setPageNum(pageNum);
			query.setPageSize(pageSize);
			Integer total = smsChannelGroupService.listSmsChannelGroupCount(query);
			List<SmsChannelGroup> list = smsChannelGroupService.listSmsChannelGroup(query);
			// 构建查询结果对象
			ResultQuery<SmsChannelGroup> pgdata = new ResultQuery<SmsChannelGroup>();
			pgdata.setPage_num(Long.valueOf(query.getPageNum() + 1));
			pgdata.setPage_size(Long.valueOf(query.getPageSize()));
			pgdata.setTotal(Long.valueOf(total));
			pgdata.setData(list);
			mv.addObject("pgdata", pgdata);
			List<Map<String, String>> smsChannelSelect = smsChannelGroupService.getSmsChannelSelect();
			Map<String, String> smsChannelMap = new HashMap<String, String>();
			for (Map<String, String> map : smsChannelSelect) {
				smsChannelMap.put(map.get("CHANNEL_ID"), map.get("CHANNEL_NAME"));
			}
			mv.addObject("smsChannelMap", smsChannelMap);
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【获取通道绑定列表】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
			return mv;
		}
	}

	@RequestMapping("/to_add")
	public ModelAndView toAdd(@RequestParam(value = "groupId", required = false) String id) {
		ModelAndView mv = new ModelAndView("/channelgroup/channel_group_add");
		if (StringUtil.notEmpty(id)) {
			SmsChannelGroup smsChannelGroup = smsChannelGroupService.selectSmsChannelGroupById(id);
			mv.addObject("smsChannelGroup", smsChannelGroup);
		}
		List<Map<String, String>> smsChannelSelect = smsChannelGroupService.getSmsChannelSelect();
		mv.addObject("channelSelect", smsChannelSelect);
		return mv;
	}

	@RequestMapping(value = "/save_group_channel", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String saveGroupChannel(HttpServletRequest request, HttpServletResponse response, @RequestBody String data) {
		try {
			SmsChannelGroup smsChannelGroup = JsonUtil.parseToObject(data, new TypeReference<SmsChannelGroup>() {
			});
			logger.info("---save a group channel:-smsChannelGroupID-----" + smsChannelGroup.getId());
			// 通道类型(0:标准通道组;1:自定义通道组)
			smsChannelGroup.setGroupType(1);
			// 状态(0:不可用 ;1:正常)
			smsChannelGroup.setStatus(1);
			String rs = smsChannelGroupService.save(smsChannelGroup);
			return rs;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("【获取通道绑定列表】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
			return "error";
		}
	}

	@RequestMapping("/to_acc_list")
	public ModelAndView toAccList(String businessName, String merchantPhone) {
		ModelAndView mv = new ModelAndView("/channelgroup/account_list");
		try {
			mv.addObject("businessName", new String(businessName.getBytes("ISO-8859-1"), "UTF-8"));
			mv.addObject("merchantPhone", new String(merchantPhone.getBytes("ISO-8859-1"), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			logger.error("【获取账号列表】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
		}
		return mv;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/acc_list_data")
	public ModelAndView getAccListData(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("params") String params) {
		ModelAndView mv = new ModelAndView("/channelgroup/account_list_data");
		try {
			AccountQuery query = null;
			logger.info("【通道绑定查询列表】参数={0}", new Object[] { params }, null);
			if (!StringUtil.isEmpty(params)) {
				query = JsonUtil.parseToObject(params, AccountQuery.class);
			}
			if (query == null) {
				query = new AccountQuery();
			}

			Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query.getPageNum() - 1);
			Integer pageSize = query.getPageSize() == 0 ? Constant.PAGE_SIZE_20 : query.getPageSize();
			query.setPageNum(pageNum);
			query.setPageSize(pageSize);
			Integer total = merchantAccountService.queryMerchantAccountCount(query);
			List<MerchantAccount> list = merchantAccountService.queryMerchantAccounts(query);
			// 构建查询结果对象
			ResultQuery pgdata = new ResultQuery();
			pgdata.setPage_num(Long.valueOf(query.getPageNum() + 1));
			pgdata.setPage_size(Long.valueOf(query.getPageSize()));
			pgdata.setTotal(Long.valueOf(total));
			pgdata.setData(list);
			mv.addObject("pgdata", pgdata);
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【获取账号列表】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
			return mv;
		}
	}

	@RequestMapping("/get_bind_channel_group")
	@ResponseBody
	public String getBindChannel(String apiAccount) {
		SmsMerchantChannelGroupBind merchantChannelGroupBindByApiAccount = smsChannelGroupBindService
				.getMerchantChannelGroupBindByApiAccount(apiAccount);
		if (merchantChannelGroupBindByApiAccount != null) {
			return JsonUtil.objectToJson(merchantChannelGroupBindByApiAccount);
		}
		return "";
	}

	@RequestMapping("/bind_list")
	public ModelAndView toGroupBindList() {
		ModelAndView mv = new ModelAndView("/channelgroup/channel_group_bind_list");
		return mv;
	}

	@RequestMapping("/bind_list_data")
	public ModelAndView getChannelGroupBindData(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("params") String params) {
		ModelAndView mv = new ModelAndView("/channelgroup/channel_group_bind_list_data");
		try {
			SmsChannelGroupBindQuery query = null;
			logger.info("【通道组查询列表】参数={0}", new Object[] { params }, null);
			if (!StringUtil.isEmpty(params)) {
				query = JsonUtil.parseToObject(params, SmsChannelGroupBindQuery.class);
			}
			if (query == null) {
				query = new SmsChannelGroupBindQuery();
			}
			Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query.getPageNum() - 1);
			Integer pageSize = query.getPageSize() == 0 ? Constant.PAGE_SIZE_20 : query.getPageSize();
			query.setPageNum(pageNum);
			query.setPageSize(pageSize);

			Integer total = smsChannelGroupBindService.listMerchantChannelGroupBindCount(query);
			List<SmsMerchantChannelGroupBind> list = smsChannelGroupBindService.listMerchantChannelGroupBind(query);
			// 构建查询结果对象
			ResultQuery<SmsMerchantChannelGroupBind> pgdata = new ResultQuery<SmsMerchantChannelGroupBind>();
			pgdata.setPage_num(Long.valueOf(query.getPageNum() + 1));
			pgdata.setPage_size(Long.valueOf(query.getPageSize()));
			pgdata.setTotal(Long.valueOf(total));
			pgdata.setData(list);
			mv.addObject("pgdata", pgdata);
			List<Map<String, String>> smsChannelSelect = smsChannelGroupService.getSmsChannelGroupSelect();
			Map<String, String> smsChannelGroupMap = new HashMap<String, String>();
			for (Map<String, String> map : smsChannelSelect) {
				smsChannelGroupMap.put(map.get("ID"), map.get("GROUP_NAME"));
			}
			mv.addObject("smsChannelMap", smsChannelGroupMap);
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【获取通道绑定列表】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
			return mv;
		}
	}

	@RequestMapping("/bind_add")
	public ModelAndView toGroupBindAdd(@RequestParam(value = "bindId", required = false) int bindId) {
		ModelAndView mv = new ModelAndView("/channelgroup/group_bind_add");
		if (bindId > 0) {
			SmsMerchantChannelGroupBind merchantChannelGroupBind = smsChannelGroupBindService
					.getMerchantChannelGroupBindById(bindId);
			mv.addObject("merchantChannelGroupBind", merchantChannelGroupBind);
		}
		List<Map<String, String>> smsChannelGroupSelect = smsChannelGroupService.getSmsChannelGroupSelect();
		mv.addObject("channelGroupSelect", smsChannelGroupSelect);
		return mv;
	}

	@RequestMapping(value = "/save_bind_group_channel")
	@ResponseBody
	public String saveBindGroupChannel(SmsMerchantChannelGroupBind bind) {
		try {
			logger.debug("save bind group channel: " + bind.toString());
			smsChannelGroupBindService.saveMerchantChannelGroupBind(bind);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("【获取通道绑定列表】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
			return "false";
		}
		return "true";
	}

	@RequestMapping("/delete")
	@ResponseBody
	public String delete(@RequestParam(value = "groupId", required = true) String id) {
		try {
			int rs = smsChannelGroupService.deleteSmsChannelGroupAndBind(id);
			if (rs > 0) {
				return "success";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("【获取通道绑定列表】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
			return "fail";
		}
		return "fail";
	}

	@RequestMapping("/deleteMerchantGroupBind")
	@ResponseBody
	public String deleteMerchantGroupById(@RequestParam(value = "apiAccount", required = true) String apiAccount,
			@RequestParam(value = "bindId", required = true) int bindId) {
		try {
			int rs = smsChannelGroupBindService.deleteMerchantGroupBind(apiAccount, bindId);
			if (rs > 0) {
				return "success";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("【获取通道绑定列表】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
			return "error";
		}
		return "fail";
	}
}
