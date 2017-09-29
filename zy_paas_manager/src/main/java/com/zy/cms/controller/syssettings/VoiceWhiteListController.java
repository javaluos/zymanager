package com.zy.cms.controller.syssettings;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zy.cms.common.CommonService;
import com.zy.cms.common.Constant;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.service.master.MerchantAccountService;
import com.zy.cms.service.master.VoiceWhiteListService;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.MerchantAccount;
import com.zy.cms.vo.User;
import com.zy.cms.vo.VoiceWhiteListVo;
import com.zy.cms.vo.query.AccountQuery;
import com.zy.cms.vo.query.ResultQuery;
import com.zy.cms.vo.query.VoiceWhiteListQuery;

@RequestMapping("/voice_white_list")
@Controller
public class VoiceWhiteListController {
	
	private static final ZyLogger logger = ZyLogger.getLogger(VoiceWhiteListController.class);
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private MerchantAccountService merchantAccountService;
	
	@Autowired
	private VoiceWhiteListService voiceWhiteListService;
	
	@RequestMapping("/to_list")
	public ModelAndView toList(HttpServletRequest request, HttpServletResponse response){
		// 定义结果
		ModelAndView mv = new ModelAndView("/syssettings/voice_white_list");
		return mv;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/list_data", produces = "application/json")
	public ModelAndView getListData(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("params") String params){
		ModelAndView mv = new ModelAndView("/syssettings/voice_white_list_data");
		try {
			 	VoiceWhiteListQuery query = null;
				logger.info("【语音白名单查询列表】参数={0}", new Object[] { params }, null);
				if (!StringUtil.isEmpty(params)) {
					query = JsonUtil.parseToObject(params, VoiceWhiteListQuery.class);
				}
				if (query == null) {
					query = new VoiceWhiteListQuery();
				}

				Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query
						.getPageNum() - 1);
				Integer pageSize = query.getPageSize() == 0 ? Constant.PAGE_SIZE_20
						: query.getPageSize();
				query.setPageNum(pageNum);
				query.setPageSize(pageSize);
				
				Integer total = voiceWhiteListService.queryCountByEntity(query);
				List<VoiceWhiteListVo> list = voiceWhiteListService.queryListByEntity(query);
				
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
				logger.error("【获取语音白名单列表】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
				return mv;
			}
		
	}
	
	@RequestMapping("/to_add")
	public ModelAndView toAdd(){
		ModelAndView mv = new ModelAndView("/syssettings/voice_white_list_add");
		return mv;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/account_list_data")
	public ModelAndView getAccountListData(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("params") String params){
		ModelAndView mv = new ModelAndView("/syssettings/account_list_data");
		try {
				AccountQuery query = null;
				logger.info("【账号查询列表】参数={0}", new Object[] { params }, null);
				if (!StringUtil.isEmpty(params)) {
					query = JsonUtil.parseToObject(params, AccountQuery.class);
				}
				if (query == null) {
					query = new AccountQuery();
				}

				Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query
						.getPageNum() - 1);
				Integer pageSize = query.getPageSize() == 0 ? Constant.PAGE_SIZE_20
						: query.getPageSize();
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
	
	@RequestMapping("/account_exist")
	@ResponseBody
	public boolean checkAccountExist(String apiAccount){
		boolean result = false;
		try {
			result = voiceWhiteListService.getByApiAccount(apiAccount);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【校验账号是否已添加】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
		}
		return result;
	}
	
	@RequestMapping("/do_add")
	@ResponseBody
	public String doAdd(String apiAccount, HttpServletRequest request, HttpServletResponse response){
		boolean result = false;
		User user = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
		try {
			result = voiceWhiteListService.add(apiAccount, user.getUserName());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【添加语音白名单】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
		}
		return JsonUtil.objectToJson(result);
	}
	
	@RequestMapping("/do_delete/{id}")
	@ResponseBody
	public ModelAndView doDelete(@PathVariable Integer id){
		ModelAndView mv = new ModelAndView("forward:/voice_white_list/to_list");
		try {
			voiceWhiteListService.delete(id);
		} catch (Exception e) {
			logger.error("删除语音白名单error:" + e.getMessage());
			e.printStackTrace();
		}
		return mv;
	}
	
}
