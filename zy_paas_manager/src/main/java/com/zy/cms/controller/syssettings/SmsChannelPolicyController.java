package com.zy.cms.controller.syssettings;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.zy.cms.common.CommonService;
import com.zy.cms.common.Constant;
import com.zy.cms.common.RedisOperator;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.mapper.master.MobileOperatorMapper;
import com.zy.cms.service.master.SmsChannelGroupService;
import com.zy.cms.service.master.SmsChannelPolicyRuleService;
import com.zy.cms.service.master.SmsChannelPolicyService;
import com.zy.cms.service.master.VoiceMerchantAttrService;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.MobileOperator;
import com.zy.cms.vo.User;
import com.zy.cms.vo.channel.SmsChannelGroup;
import com.zy.cms.vo.channel.SmsChannelPolicy;
import com.zy.cms.vo.channel.SmsChannelPolicyRule;
import com.zy.cms.vo.query.ResultQuery;
import com.zy.cms.vo.query.SmsChannelPolicyQuery;

/**
 * 分流策略设置
 * @author JasonXu
 *
 */
@RequestMapping("/sms_channel_policy")
@Controller
public class SmsChannelPolicyController {
	
	private static final ZyLogger logger = ZyLogger.getLogger(SmsChannelPolicyController.class);
	
	@Autowired
	private SmsChannelPolicyService smsChannelPolicyService;
	
	@Autowired
	private SmsChannelPolicyRuleService smsChannelPolicyRuleService;
	
	@Autowired
	private SmsChannelGroupService smsChannelGroupService;
	
	@Resource
	private CommonService commonService;
	
	@Resource
	private MobileOperatorMapper mobileOperatorMapper;
	
	@Resource
	private RedisOperator redisOperator;
	
	@Resource
	private VoiceMerchantAttrService voiceMerchantAttrService;

	/**
	 * 跳转到查询分流列表页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/to_list")
	public ModelAndView toList(HttpServletRequest request, HttpServletResponse response){
		// 定义结果
		ModelAndView mv = new ModelAndView("/syssettings/channel_policy_list");
		return mv;
	}
	
	/**
	 * 加载分流列表数据
	 * @param request
	 * @param response
	 * @param params
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/list_data")
	public ModelAndView getAccountListData(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("params") String params){
		ModelAndView mv = new ModelAndView("/syssettings/channel_policy_list_data");
		try {
			    SmsChannelPolicyQuery query = null;
				logger.info("【分流策略设置列表】参数={0}", new Object[] { params }, null);
				if (!StringUtil.isEmpty(params)) {
					query = JsonUtil.parseToObject(params, SmsChannelPolicyQuery.class);
				}
				if (query == null) {
					query = new SmsChannelPolicyQuery();
				}

				Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query
						.getPageNum() - 1);
				Integer pageSize = query.getPageSize() == 0 ? Constant.PAGE_SIZE_20
						: query.getPageSize();
				query.setPageNum(pageNum);
				query.setPageSize(pageSize);
				
				Integer total = smsChannelPolicyService.querySmsChannelPolicyCount(query);
				List<SmsChannelPolicy> list = smsChannelPolicyService.querySmsChannelPolicyByEntity(query);
				
				SmsChannelPolicyRule ruleQuery=new SmsChannelPolicyRule();			
				List<SmsChannelPolicyRule> ruleList=smsChannelPolicyRuleService.querySmsChannelPolicyRuleByEntity(ruleQuery);
				
				logger.info("【分流策略设置列表】查询到的数据={0}", new Object[] { JsonUtil.toJsonString(list) }, null);
				
				List<SmsChannelGroup> grouplist=smsChannelGroupService.listSmsChannelGroupNoPage();
				if(ruleList.size()>0){
					for(SmsChannelPolicyRule scpr:ruleList){
						for(SmsChannelGroup scg:grouplist){
							if(scpr.getGroupYD().equals(scg.getId())){
								scpr.setGroupYD(scg.getGroupName());
							}
							if(scpr.getGroupLT().equals(scg.getId())){
								scpr.setGroupLT(scg.getGroupName());
							}
							if(scpr.getGroupDX().equals(scg.getId())){
								scpr.setGroupDX(scg.getGroupName());
							}
						}
					}
				}
				
				// 构建查询结果对象
				ResultQuery pgdata = new ResultQuery();
				pgdata.setPage_num(Long.valueOf(query.getPageNum() + 1));
				pgdata.setPage_size(Long.valueOf(query.getPageSize()));
				pgdata.setTotal(Long.valueOf(total));
				pgdata.setData(list);
				mv.addObject("pgdata", pgdata);
				mv.addObject("ruleList",ruleList);
				return mv;
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("【分流策略设置列表】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
				return mv;
			}
	}
	
	/**
	 * 跳转到添加分流列表页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/add")
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response){
		// 定义结果
		ModelAndView mv = new ModelAndView("/syssettings/channel_policy_add");
		try {
			List<SmsChannelGroup> grouplist=smsChannelGroupService.listSmsChannelGroupNoPage();
			List<MobileOperator>  mobileAll=mobileOperatorMapper.selectAll();
			
			StringBuffer mobileYDs=new StringBuffer();
			StringBuffer mobileLTs=new StringBuffer();
			StringBuffer mobileDXs=new StringBuffer();
			if(mobileAll.size()>0){
				for(MobileOperator mobileYD:mobileAll){
					if(mobileYD.getOperatorCode().equals("YD")){
						mobileYDs.append(mobileYD.getNumberPrefix()+"*,");
					}else if(mobileYD.getOperatorCode().equals("LT")){
						mobileLTs.append(mobileYD.getNumberPrefix()+"*,");
					}else{
						mobileDXs.append(mobileYD.getNumberPrefix()+"*,");
					}
				}
			}
			
			String mobileYD=mobileYDs.substring(0, mobileYDs.length()-1);
			String mobileLT=mobileLTs.substring(0, mobileLTs.length()-1);
			String mobileDX=mobileDXs.substring(0, mobileDXs.length()-1);
			
			mv.addObject("grouplist", grouplist);
			mv.addObject("mobileYD",mobileYD);
			mv.addObject("mobileLT",mobileLT);
			mv.addObject("mobileDX",mobileDX);
		} catch (Exception e) {
			logger.error("【分流策略设置列表】出现异常...异常是:"+e.getMessage());
		}
		return mv;
	}
	
	/**
	 * 保存分流列表
	 * @param request
	 * @param response
	 * @param params
	 * @return
	 */
	@RequestMapping("/do_save")
	@ResponseBody
	public String save(HttpServletRequest request, HttpServletResponse response,@RequestParam("params") String params){
		logger.info("【分流策略设置列表】保存分流策略开始...params="+params);
		int result = 0;
		try {
			User us = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
			if (us == null) {
				logger.warn("用户登录超时!");
				return JsonUtil.objectToJson(result);
			}
			
			if (!StringUtil.isEmpty(params)) {
			    result=smsChannelPolicyService.saveSmsChannelPolicy(params);
			}
		} catch (Exception e) {
			logger.info("【分流策略设置列表】处理告警日志出现异常,异常是:"+e.getMessage());
		}
		return JsonUtil.objectToJson(result);
	}
	
	
	/**
	 * 编辑分流列表
	 * @param request
	 * @param response
	 * @param params
	 * @return
	 */
	@RequestMapping("/do_edit")
	@ResponseBody
	public String doEdit(HttpServletRequest request, HttpServletResponse response,@RequestParam("params") String params){
		logger.info("【分流策略设置列表】编辑分流策略开始...params="+params);
		int result = 0;
		try {
			User us = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
			if (us == null) {
				logger.warn("用户登录超时!");
				return JsonUtil.objectToJson(result);
			}
			
			if (!StringUtil.isEmpty(params)) {
				result=smsChannelPolicyService.updateSmsChannelPolicy(params);
			}
			
		} catch (Exception e) {
			logger.info("【分流策略设置列表】出现异常,异常是:"+e.getMessage());
		}
		return JsonUtil.objectToJson(result);
	}
	
	
	/**
	 * 删除分流列表
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping("/do_delete")
	@ResponseBody
	public String delete(HttpServletRequest request, HttpServletResponse response,@RequestParam("id") String id){
		logger.info("【分流策略设置列表】删除分流策略开始...id="+id);
		String result = "处理失败";
		try {
			User us = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
			if (us == null) {
				logger.warn("用户登录超时!");
				return JsonUtil.objectToJson(result);
			}
			
			if (!StringUtil.isEmpty(id)) {
				result=smsChannelPolicyService.deleteSmsChannelPolicy(id);
			}
			
		} catch (Exception e) {
			logger.info("【分流策略设置列表】删除分流策略开始出现异常,异常是:"+e.getMessage());
		}
		return JsonUtil.toJsonString(result);
	}
	
	/**
	 * 跳转到添加分流列表页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/to_edit")
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response,@RequestParam("id") String id){
		// 定义结果
		logger.info("【分流策略设置列表】编辑分流策略开始...id="+id);
		ModelAndView mv = new ModelAndView("/syssettings/channel_policy_add");
		try {
			List<SmsChannelGroup> grouplist=smsChannelGroupService.listSmsChannelGroupNoPage();
			List<MobileOperator>  mobileAll=mobileOperatorMapper.selectAll();
			
			SmsChannelPolicy smsChannelPolicy=smsChannelPolicyService.selectByPrimaryKey(id);
			SmsChannelPolicyRule smsChannelPolicyRule=new SmsChannelPolicyRule();
			smsChannelPolicyRule.setPolicyId(id);
			List<SmsChannelPolicyRule> listRule=smsChannelPolicyRuleService.querySmsChannelPolicyRuleByEntity(smsChannelPolicyRule);
			
			StringBuffer mobileYDs=new StringBuffer();
			StringBuffer mobileLTs=new StringBuffer();
			StringBuffer mobileDXs=new StringBuffer();
			if(mobileAll.size()>0){
				for(MobileOperator mobileYD:mobileAll){
					if(mobileYD.getOperatorCode().equals("YD")){
						mobileYDs.append(mobileYD.getNumberPrefix()+"*,");
					}else if(mobileYD.getOperatorCode().equals("LT")){
						mobileLTs.append(mobileYD.getNumberPrefix()+"*,");
					}else{
						mobileDXs.append(mobileYD.getNumberPrefix()+"*,");
					}
				}
			}
			
			String mobileYD=mobileYDs.substring(0, mobileYDs.length()-1);
			String mobileLT=mobileLTs.substring(0, mobileLTs.length()-1);
			String mobileDX=mobileDXs.substring(0, mobileDXs.length()-1);
			
			mv.addObject("grouplist", grouplist);
			mv.addObject("mobileYD",mobileYD);
			mv.addObject("mobileLT",mobileLT);
			mv.addObject("mobileDX",mobileDX);
			mv.addObject("policyName", smsChannelPolicy.getPolicyName());
			mv.addObject("policyId", smsChannelPolicy.getId());
			mv.addObject("listRule", listRule);
			
		} catch (Exception e) {
			logger.error("【分流策略设置列表】出现异常...异常是:"+e.getMessage());
		}
		return mv;
	}
	
}
