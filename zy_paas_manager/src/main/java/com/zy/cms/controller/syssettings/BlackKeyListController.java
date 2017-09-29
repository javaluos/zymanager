package com.zy.cms.controller.syssettings;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
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
import com.zy.cms.service.syssettings.BlackKeyListService;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.BlackKeyListInfo;
import com.zy.cms.vo.WebResult;
import com.zy.cms.vo.query.ResultQuery;

@RequestMapping("/black_key_list")
@Controller
public class BlackKeyListController {

	private static final ZyLogger logger = ZyLogger.getLogger(BlackKeyListController.class);

	@Autowired
	private BlackKeyListService blackKeyListService;
	@Autowired
	private BlackKeyListInfoMapper blackKeyListInfoMapper;

	@RequestMapping("/to_list")
	public ModelAndView toList(HttpServletRequest request, HttpServletResponse response,String policyId,String policyName) {
		// 定义结果
		ModelAndView mv = new ModelAndView("/syssettings/black_key_list");
		mv.addObject("policyId",policyId);
		try {
			mv.addObject("policyName", new String(policyName.getBytes("iso8859-1"),"utf-8"));
		} catch (UnsupportedEncodingException e) {
			logger.info(e.getMessage());
		}
		return mv;
	}

	@RequestMapping("/toadd")
	public ModelAndView toadd(HttpServletRequest request, HttpServletResponse response,String policyId,String policyName) {
		// 定义结果
		ModelAndView mv = new ModelAndView("/syssettings/black_key_list_add");
		mv.addObject("policyId",policyId);
		try {
			mv.addObject("policyName", new String(policyName.getBytes("iso8859-1"),"utf-8"));
		} catch (UnsupportedEncodingException e) {
			logger.info(e.getMessage());
		}
		return mv;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/list_data", produces = "application/json")
	public ModelAndView getListData(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("params") String params) {
		ModelAndView mv = new ModelAndView("/syssettings/black_key_list_data");
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

			Integer total = blackKeyListInfoMapper.queryCountByQuery(Parammap);
			List<BlackKeyListInfo> list = blackKeyListInfoMapper.selectListByQuery(Parammap);

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
			logger.error("【获取敏感词列表】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
			return mv;
		}

	}

	/**
	 * 保存敏感词信息
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
			String industry = map.get("industry");
			String policyId=map.get("policyId");
			String remark = map.get("remark");
			String black_keys = map.get("black_keys");
			String[] black_key = black_keys.split(",");

			if (null != black_key && black_key.length > 0) {
				for (int i = 0; i < black_key.length; i++) {
					BlackKeyListInfo info = new BlackKeyListInfo();
					info.setIndustry(industry);
					info.setBlack_key(black_key[i]);
					if(StringUtils.isNotEmpty(policyId)){
						info.setPolicyId(Integer.parseInt(policyId));
					}
					info.setRemark(remark);
					BlackKeyListInfo entity = blackKeyListInfoMapper.getInfoByblack_key(black_key[i],policyId);
					if (entity == null) {
						blackKeyListInfoMapper.insertSelective(info);
					}
				}
				result = 1;
			}
		} catch (Exception e) {
			logger.error("【敏感词添加】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
		}

		return String.valueOf(result);
	}

	@RequestMapping("/blackkey_exist")
	@ResponseBody
	public boolean checkBlackkeyExist(String black_key,String policyId) {
		boolean result = false;
		try {
			BlackKeyListInfo info = blackKeyListInfoMapper.getInfoByblack_key(black_key,policyId);
			if (null != info) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【敏感词是否已经存在】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
		}
		return result;
	}

	@RequestMapping("/do_delete/{id}/{policyId}")
	@ResponseBody
	public ModelAndView doDelete(@PathVariable Integer id,@PathVariable String policyId,String policyName) {
		ModelAndView mv = new ModelAndView("redirect:/black_key_list/to_list?policyId="+policyId+"&policyName="+policyName);
		try {
			blackKeyListInfoMapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			logger.error("删除敏感词error:" + e.getMessage());
			e.printStackTrace();
		}
		return mv;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public String update(HttpServletRequest req) {
		boolean result = false;
		try {
			String id = req.getParameter("id");
			String industry = req.getParameter("industry");
			String black_key = req.getParameter("black_key");
			String remark = req.getParameter("remark");
			BlackKeyListInfo info = new BlackKeyListInfo();
			info.setBlack_key(black_key);
			info.setIndustry(industry);
			info.setRemark(remark);
			info.setUpdateTime(new Date());
			info.setId(Integer.parseInt(id));
			blackKeyListInfoMapper.updateByPrimaryKey(info);
			result = true;
		} catch (Exception e) {
			logger.error("修改敏感词error:" + e.getMessage());
			result = false;
		}
		return JsonUtil.objectToJson(result);
	}

	@RequestMapping("/to_update/{id}")
	@ResponseBody
	public ModelAndView doUpdate(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView("/syssettings/black_key_list_update");
		BlackKeyListInfo info = blackKeyListInfoMapper.getInfoByid(id);
		mv.addObject("obj", info);
		return mv;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/export")
	@ResponseBody
	public WebResult doexport(HttpServletRequest request,
			@RequestParam(value = "params", required = false) String params) {
		logger.info("【 敏感词记录导出】开始  params=" + params);
		WebResult webRs = new WebResult();
		try {
			Map<String, Object> Parammap = new HashMap<String, Object>();
			logger.info("【敏感词查询列表】参数={0}", new Object[] { params }, null);
			if (!StringUtil.isEmpty(params)) {
				Parammap = JsonUtil.parseToObject(params, Map.class);
			}
			Parammap.put("pageSize", 1000000);
			Parammap.put("pageOffset", 0);
			// 调用导出业务
			String realPath = request.getSession().getServletContext().getRealPath("");
			webRs = blackKeyListService.export(Parammap, realPath, request.getContextPath(), "xlsx");
		} catch (Exception e) {
			logger.info("【敏感词记录下载】出现异常,异常是" + e.getMessage());
			e.printStackTrace();
		}

		logger.info("【敏感词记录导出】结束...");
		return webRs;

	}

}
