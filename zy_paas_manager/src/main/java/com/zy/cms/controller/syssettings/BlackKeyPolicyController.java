package com.zy.cms.controller.syssettings;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zy.cms.common.Constant;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.mapper.master.BlackKeyListInfoMapper;
import com.zy.cms.service.master.SmsBlackKeyPolicyService;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.BlackKeyPolicy;
import com.zy.cms.vo.query.ResultQuery;

@RequestMapping("/black_key_policy")
@Controller
public class BlackKeyPolicyController {

	private static final ZyLogger logger = ZyLogger.getLogger(BlackKeyPolicyController.class);

	@Autowired
	private SmsBlackKeyPolicyService smsBlackKeyPolicyService;
	
	@Autowired
	private BlackKeyListInfoMapper blackKeyListInfoMapper;
	
	@RequestMapping("/to_list")
	public ModelAndView toList(HttpServletRequest request, HttpServletResponse response) {
		// 定义结果
		ModelAndView mv = new ModelAndView("/syssettings/black_key_policy_list");
		return mv;
	}

	@RequestMapping("/toadd")
	public ModelAndView toadd(HttpServletRequest request, HttpServletResponse response) {
		// 定义结果
		ModelAndView mv = new ModelAndView("/syssettings/black_key_policy_list_add");
		return mv;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/list_data", produces = "application/json")
	public ModelAndView getListData(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("params") String params) {
		ModelAndView mv = new ModelAndView("/syssettings/black_key_policy_list_data");
		try {
			Map<String, Object> Parammap = new HashMap<String, Object>();
			logger.info("【敏感词查询列表】参数={0}", new Object[] { params }, null);
			if (!StringUtil.isEmpty(params)) {
				Parammap = JsonUtil.parseToObject(params, Map.class);
			}

			Integer pageNum = (Integer.parseInt(Parammap.get("pageNum") + "") - 1) <= 0 ? 0
					: (Integer.parseInt(Parammap.get("pageNum") + "") - 1);
			Integer pageSize = Constant.PAGE_SIZE_20;

			Parammap.put("pageNum", pageNum);
			Parammap.put("pageSize", pageSize);
			Parammap.put("pageOffset", pageNum * pageSize);
			Parammap.put("black_key", (Parammap.get("black_key") + "").trim());
			Parammap.put("industry", (Parammap.get("industry") + "").trim());

			Integer total = smsBlackKeyPolicyService.queryCountByQuery(Parammap);
			List<BlackKeyPolicy> list = smsBlackKeyPolicyService.selectListByQuery(Parammap);

			// 构建查询结果对象
			ResultQuery pgdata = new ResultQuery();
			pgdata.setPage_num(Long.valueOf(Integer.parseInt(Parammap.get("pageNum") + "") + 1));
			pgdata.setPage_size(Long.valueOf(Integer.parseInt(Parammap.get("pageSize") + "")));
			pgdata.setTotal(Long.valueOf(total));
			pgdata.setData(list);
			mv.addObject("pgdata", pgdata);
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【获取拦截策略列表】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
			return mv;
		}

	}

	/**
	 * 保存拦截策略信息
	 * 
	 * @param apiAccount
	 * @return
	 */
	@RequestMapping(value = "/to_save", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String blackkeySave(@RequestParam(value = "params", required = false) String params) {
		int result = 0;
		try {
			@SuppressWarnings("unchecked")
			Map<String, String> map = JsonUtil.parseToObject(params, Map.class);
			String policyName = map.get("policyName");
			String remark = map.get("remark");
			
			BlackKeyPolicy info = smsBlackKeyPolicyService.getInfoByPolicyName(policyName);
			if(info!=null){
				return String.valueOf(2);
			}
			
			BlackKeyPolicy blackKeyPolicy=new BlackKeyPolicy();
			blackKeyPolicy.setPolicyName(policyName);
			blackKeyPolicy.setRemark(remark);
			result=smsBlackKeyPolicyService.insertSelective(blackKeyPolicy);
			
		} catch (Exception e) {
			logger.error("【拦截策略添加】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
		}

		return String.valueOf(result);
	}

	@RequestMapping("/blackkey_policy_exist")
	@ResponseBody
	public boolean checkBlackkeyExist(String black_key) {
		boolean result = false;
		try {
			BlackKeyPolicy info = smsBlackKeyPolicyService.getInfoByPolicyName(black_key);
			if (null != info) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【敏感词是否已经存在】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
		}
		return result;
	}

	@RequestMapping("/do_delete/{id}")
	@ResponseBody
	public ModelAndView doDelete(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView("forward:/black_key_policy/to_list");
		try {
			smsBlackKeyPolicyService.deleteByPrimaryKey(id);
			blackKeyListInfoMapper.deleteByPolicyId(id);
		} catch (Exception e) {
			logger.error("删除敏感词error:" + e.getMessage());
			e.printStackTrace();
		}
		return mv;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public String update(HttpServletRequest req) {
		int result;
		try {
			String id = req.getParameter("id");
			String policyName = req.getParameter("policyName");
			String remark = req.getParameter("remark");
			
			BlackKeyPolicy blackKeyPolicy = smsBlackKeyPolicyService.getInfoByPolicyName(policyName);
			if(blackKeyPolicy!=null){
				if(!blackKeyPolicy.getId().toString().equals(id)){
					return String.valueOf(2);
				}
			}
			
			BlackKeyPolicy info = new BlackKeyPolicy();
			info.setPolicyName(policyName);
			info.setRemark(remark);
			info.setUpdateTime(new Date());
			info.setId(Integer.parseInt(id));
			smsBlackKeyPolicyService.updateByPrimaryKey(info);
			result = 1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("修改敏感词error:" + e.getMessage());
			result =  3;
		}
		return JsonUtil.objectToJson(result);
	}

	@RequestMapping("/to_update/{id}")
	@ResponseBody
	public ModelAndView doUpdate(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView("/syssettings/black_key_policy_list_update");
		BlackKeyPolicy info=new BlackKeyPolicy();
		try {
			info = smsBlackKeyPolicyService.getInfoById(id);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		mv.addObject("obj", info);
		return mv;
	}

}
