package com.zy.cms.controller.audit;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.zy.cms.common.CacheService;
import com.zy.cms.common.CommonService;
import com.zy.cms.common.Constant;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.service.manager.AccountBindInfoService;
import com.zy.cms.service.master.MerchantAccountService;
import com.zy.cms.service.master.VoiceUploadService;
import com.zy.cms.util.CookieUtil;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.util.UploadUtil;
import com.zy.cms.vo.MerchantAccount;
import com.zy.cms.vo.User;
import com.zy.cms.vo.VoiceUpload;
import com.zy.cms.vo.query.ResultQuery;
import com.zy.cms.vo.query.VoiceUploadQuery;

/**
 * 语音审核信息
 * 
 * @author allen.xu
 * @date 2016-09-21
 */
@Controller
@RequestMapping(value = "/audit")
public class VoiceAuditController {

	private static final ZyLogger logger = ZyLogger.getLogger(VoiceAuditController.class);

	@Resource
	private VoiceUploadService voiceUploadService;

	@Resource
	private MerchantAccountService merchantAccountService;

	@Resource
	private CommonService commonService;

	@Autowired
	private CacheService cacheService;

	@Autowired
	private AccountBindInfoService accountBindInfoService;
	
	/**
	 * 查询语音审核列表UI
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/audit_list")
	public ModelAndView queryAuditList(HttpServletRequest request, HttpServletResponse response) {
		// 定义结果
		ModelAndView mv = new ModelAndView("/audit/audit_list");
		// mv.addObject("authStatus", request.getParameter("authStatus"));
		return mv;
	}

	/**
	 * 加载语音审核列表数据
	 * 
	 * @param request
	 * @param response
	 * @param params
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/audit_list_data", produces = "application/json")
	public ModelAndView queryAuditListData(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("params") String params) {
		try {
			ModelAndView mv = new ModelAndView("/audit/audit_list_data");
			User us = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
			if (us == null) {
				logger.warn("用户登录超时!");
				return new ModelAndView("redirect:/login.html");
			}
			
			VoiceUploadQuery query = null;
			logger.info("【(待)审核列表】参数={0}", new Object[] { params }, null);
			if (!StringUtil.isEmpty(params)) {
				query = JsonUtil.parseToObject(params, VoiceUploadQuery.class);
			}
			if (query == null) {
				query = new VoiceUploadQuery();
			}

			Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query.getPageNum() - 1);
			Integer pageSize = query.getPageSize() == 0 ? Constant.PAGE_SIZE_20 : query.getPageSize();
			query.setPageNum(pageNum);
			query.setPageSize(pageSize);

			// 按账号查询
			boolean queryflag = true;
			MerchantAccount ma = null;
			if (StringUtil.notEmpty(query.getMerchantPhone())) {
				ma = merchantAccountService.getMerchantAccountByPhone(query.getMerchantPhone());
				if (ma != null && StringUtil.notEmpty(ma.getApiAccount())) {
					query.setApiAccount(ma.getApiAccount());
				} else {
					queryflag = false;
				}
			}

			Integer total = 0;
			List<VoiceUpload> list = new ArrayList<VoiceUpload>();
			if (queryflag) {
				List<String> apiAccounts=accountBindInfoService.getApiAccountByBusName(us.getUserName());
				query.setApiAccounts(apiAccounts);
				total = voiceUploadService.queryVoiceUploadCountByEntity(query);
				list = voiceUploadService.queryVoiceUploadListByEntity(query);
			}

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
			e.printStackTrace();
			logger.error("【获取(待)审核列表】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
			return null;
		}
	}

	/**
	 * 跳转到审核页面
	 * 
	 * @param voiceUploadQuery
	 * @param req
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/preAudit")
	public ModelAndView preAudit(VoiceUploadQuery voiceUploadQuery, HttpServletRequest req, Model model)
			throws Exception {
		logger.info("跳转到【待审核列表详情】apiAccount={0},ID={1}",
				new Object[] { voiceUploadQuery.getApiAccount(), voiceUploadQuery.getId() }, null);
		ModelAndView mv = new ModelAndView("/audit/audit_detail_pre");
		try {
			VoiceUpload voiceUpload = voiceUploadService.findVoiceUpload(voiceUploadQuery);
			mv.addObject("voiceUpload", voiceUpload);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("跳转到【待审核列表详情】异常是" + e.getMessage());
		}
		return mv;
	}

	/**
	 * 跳转到详情页面
	 * 
	 * @param voiceUploadQuery
	 * @param req
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/auditDetail")
	public ModelAndView auditDetail(VoiceUploadQuery voiceUploadQuery, HttpServletRequest req, Model model)
			throws Exception {
		logger.info("跳转到【审核列表详情】apiAccount={0},ID={1}",
				new Object[] { voiceUploadQuery.getApiAccount(), voiceUploadQuery.getId() }, null);
		ModelAndView mv = new ModelAndView("/audit/audit_detail");
		try {
			VoiceUpload voiceUpload = voiceUploadService.findVoiceUpload(voiceUploadQuery);
			mv.addObject("voiceUpload", voiceUpload);
		} catch (Exception e) {
			logger.error("跳转到【审核列表详情】出现异常,异常是:" + e.getMessage());
			e.printStackTrace();
		}
		return mv;
	}

	/**
	 * 审核
	 * 
	 * @return
	 */
	@RequestMapping(value = "/audit")
	public String audit(VoiceUploadQuery voiceUploadQuery, HttpServletRequest req, Model model) {
		try {
			Map<String, String> cookies = CookieUtil.getCookies(req);
			String username = cookies.get(Constant.USER_SESSION_UNAME);
			if (username == null) {
				logger.warn("找不到用户，或者用户的登陆cookie已经过期");
				return "redirect:/login.html";
			}
			logger.info("【语音审核】审核信息是:" + JsonUtil.toJsonString(voiceUploadQuery));
			VoiceUpload voiceUpload = voiceUploadService.findVoiceUpload(voiceUploadQuery);

			if (2 == voiceUploadQuery.getVoiceFileType()) {
				MultipartHttpServletRequest mulReq = (MultipartHttpServletRequest) req;
				MultipartFile file = mulReq.getFile("file");
				String fileName = file.getOriginalFilename();
				long size = file.getSize();

				InputStream in = file.getInputStream();
				// 上传到文件系统
				Map<String, Object> map = UploadUtil.upload(in, "", fileName, size);
				if (null == map.get("FILE_ID") || "".equals(map.get("FILE_ID").toString().trim())) {
					logger.error("【语音审核】voiceUpload 上传fastDFS 失败");
					model.addAttribute("msg", "上传语音文件失败.");
					return "redirect:/audit/audit.html";
				}
				voiceUpload.setDownloadUrl(cacheService.getConfig("FASTDFS_URL_PORT") + map.get("FILE_ID"));
			} else {
				voiceUpload.setDownloadUrl(voiceUpload.getDownloadUrlS());
			}

			voiceUpload.setAuthStatus(voiceUploadQuery.getAuthStatus());
			voiceUpload.setAuthDesc(voiceUploadQuery.getAuthDesc());
			voiceUpload.setAuthResultTime(new Date());
			voiceUpload.setAuthUser(username);
			voiceUpload.setUpdateTime(voiceUpload.getAuthResultTime());
			voiceUploadService.updateVoiceUpload(voiceUpload);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【语音审核】失败,异常是{0}", new Object[] { e.getMessage() }, null);
		}
		return "redirect:/audit/audit_list.html";
	}

}
