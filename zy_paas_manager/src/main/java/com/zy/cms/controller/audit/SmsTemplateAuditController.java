package com.zy.cms.controller.audit;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.zy.cms.common.CommonService;
import com.zy.cms.common.Constant;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.service.manager.AccountBindInfoService;
import com.zy.cms.service.master.SmsTemplateAuditService;
import com.zy.cms.util.CookieUtil;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.MerchantSmsTemplateVo;
import com.zy.cms.vo.User;
import com.zy.cms.vo.query.ResultQuery;
import com.zy.cms.vo.query.SmsTemplateAuditQuery;

/**
 * 模板审核Controller 
 * @author luos
 * @date 2017-1-5 18:15:11
 *
 */
@Controller
@RequestMapping("/smstemplate")
public class SmsTemplateAuditController {
	
	private static final ZyLogger logger = ZyLogger.getLogger(SmsTemplateAuditController.class);
	
	@Autowired
	private SmsTemplateAuditService smsTemplateAuditService;
	
	@Autowired
	private AccountBindInfoService accountBindInfoService;
	
	@Resource
	private CommonService commonService;
	
	@RequestMapping("/list")
	public ModelAndView getList(){
		ModelAndView mv = new ModelAndView("/smstemplate/audit_list");
		return mv;
	}
	
	@RequestMapping(value = "/audit_list_data", produces = "application/json")
	public ModelAndView queryAuditListData(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("params") String params)  {
		try {
			ModelAndView mv = new ModelAndView("/smstemplate/audit_list_data");
			User us = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
			if (us == null) {
				logger.warn("用户登录超时!");
				return new ModelAndView("redirect:/login.html");
			}
			
			SmsTemplateAuditQuery query = null;
			logger.info("【(待)审核列表】参数={0}", new Object[] { params }, null);
			if (!StringUtil.isEmpty(params)) {
				query = JsonUtil.parseToObject(params, SmsTemplateAuditQuery.class);
			}
			if (query == null) {
				query = new SmsTemplateAuditQuery();
			}

			Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query
					.getPageNum() - 1);
			Integer pageSize = query.getPageSize() == 0 ? Constant.PAGE_SIZE_20
					: query.getPageSize();
			query.setPageNum(pageNum);
			query.setPageSize(pageSize);
			String businessName = query.getBusinessName();
			if(StringUtils.isNotBlank(businessName)){
				query.setBusinessName(businessName.trim());
			}
			String merchantPhone = query.getMerchantPhone();
			if(StringUtils.isNotBlank(merchantPhone)){
				query.setMerchantPhone(merchantPhone.trim());
			}
			String authUser = query.getAuthUser();
			if(StringUtils.isNotBlank(authUser)){
				query.setAuthUser(authUser.trim());
			}
			
			List<String> apiAccounts=accountBindInfoService.getApiAccountByBusName(us.getUserName());
			query.setApiAccounts(apiAccounts);
			Integer total = smsTemplateAuditService.querySmsTemplateAuthByEntity(query);
			List<MerchantSmsTemplateVo> list = smsTemplateAuditService.querySmsTemplateAuthListByEntity(query);
			
			// 构建查询结果对象
			ResultQuery pgdata = new ResultQuery();
			pgdata.setPage_num(Long.valueOf(query.getPageNum() + 1));
			pgdata.setPage_size(Long.valueOf(query.getPageSize()));
			pgdata.setTotal(Long.valueOf(total));
			pgdata.setData(list);

			// 设定查询参数对象['pgdata'为JSP参数,不随意修改]
			mv.addObject("pgdata", pgdata);
			return mv;
		} catch (Exception e) {
			logger.error("【获取(待)审核列表】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
			return null;
		}

	}
	
	@RequestMapping("/to_audit/{id}")
	public ModelAndView toAudit(HttpServletRequest request, @PathVariable String id){
		ModelAndView mv = new ModelAndView("/smstemplate/audit_sms_template");
		MerchantSmsTemplateVo mchSmsTemplateVO = smsTemplateAuditService.getById(id);
		String blackKey=commonService.valideBlackKey(mchSmsTemplateVO.getContent());
		mv.addObject("blackKey", blackKey);
		mv.addObject("mchSmsTemplate", mchSmsTemplateVO);
		return mv;
	}
	
	@RequestMapping("/do_audit")
	public ModelAndView doAudit(HttpServletRequest request, String id, String reason, String content, String flag){
		ModelAndView mv = null;
		try {
			Map<String, String> cookies = CookieUtil.getCookies(request);
			String username = cookies.get(Constant.USER_SESSION_UNAME);
			if (username == null) {
				logger.warn("找不到用户，或者用户的登陆cookie已经过期");
				mv = new ModelAndView("redirect:/login.html");
			}
			smsTemplateAuditService.doAudit(id, username, reason, content, flag);
			mv = new ModelAndView("redirect:/smstemplate/list");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("模板审核失败,异常是{0}", new Object[] { e.getMessage()}, null);
			mv = new ModelAndView("redirect:/smstemplate/list");
		}
		return mv;
	}
	
	@RequestMapping("/delete/{id}")
	public ModelAndView delete(HttpServletRequest request, @PathVariable String id){
		ModelAndView mv = new ModelAndView("forward:/smstemplate/list");
		try {
			smsTemplateAuditService.deleteById(id);
		} catch (Exception e) {
			logger.error("删除模板error:" + e.getMessage());
			e.printStackTrace();
		}
		return mv;
	}

}
