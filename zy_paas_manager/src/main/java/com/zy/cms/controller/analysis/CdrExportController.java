package com.zy.cms.controller.analysis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zy.cms.common.CommonService;
import com.zy.cms.common.Constant;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.service.manager.AccountBindInfoService;
import com.zy.cms.service.manager.excel.CdrDetailExcelService;
import com.zy.cms.service.master.MerchantAccountService;
import com.zy.cms.util.DateUtil;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.MerchantAccount;
import com.zy.cms.vo.User;
import com.zy.cms.vo.WebResult;
import com.zy.cms.vo.query.VoiceQuery;

/**
 * 话单导出Excel业务控制层
 * 
 * @author allen.yuan
 * @date 2016-11-5
 *
 */
@Controller
@RequestMapping(value = "/export")
public class CdrExportController {

	private static final ZyLogger logger = ZyLogger.getLogger(CdrExportController.class);

	@Resource
	private CommonService commonService;

	@Autowired
	private MerchantAccountService merchantAccountService;

	@Autowired
	private CdrDetailExcelService cdrDetailExcelService;
	
	@Autowired
	private AccountBindInfoService accountBindInfoService;

	@RequestMapping(value = "/voicefs")
	@ResponseBody
	public WebResult exportVoiceRcord(HttpServletRequest request,
			@RequestParam(value = "params", required = false) String params) {

		logger.info("【语音话单导出】开始  params=" + params);

		WebResult webRs = new WebResult();
		User us = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
		if (us == null) {
			logger.warn("用户登录超时!");
			webRs.setCode(-1);
			webRs.setMsg("用户登录超时!");
		}

		VoiceQuery query = null;
		if (!StringUtil.isEmpty(params)) {
			query = JsonUtil.parseToObject(params, VoiceQuery.class);
		}

		MerchantAccount merchantAccount = merchantAccountService.getMerchantAccountByPhone(query.getMerchantPhone());
		if (merchantAccount != null) {
			query.setApiAccount(merchantAccount.getApiAccount());
		}
		if (!StringUtil.isEmpty(query.getStarttime())) {
			query.setStarttimeL(DateUtil.date2TimeStamp(query.getStarttime(), "yyyy-MM-dd HH:mm:ss") / 1000);
		}
		if (!StringUtil.isEmpty(query.getEndtime())) {
			query.setEndtimeL(DateUtil.date2TimeStamp(query.getEndtime(), "yyyy-MM-dd HH:mm:ss") / 1000);
		}

		Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query.getPageNum() - 1); // es分页码从0开始，所以-1
		query.setPageNum(pageNum);
		query.setPageSize(1000000);// 最大值

		// 调用导出业务
		String realPath = request.getSession().getServletContext().getRealPath("");
		webRs = cdrDetailExcelService.exportVoiceExcel(query, realPath, request.getContextPath());

		logger.info("【语音话单导出】结束...");

		return webRs;
	}

	@RequestMapping("/down")
	public void download(String downUrl, HttpServletRequest request, HttpServletResponse response) {

		logger.info("【语音话单下载】下载开始...");
		try {

			response.setCharacterEncoding("utf-8");
			response.setContentType("multipart/form-data");
			response.setHeader("Content-Disposition", "attachment;fileName=" + getFileName(request, downUrl));

			String path = request.getSession().getServletContext().getRealPath("") + downUrl;// 这个download目录为啥建立在classes下的
			logger.info("【语音话单下载】downUrl=" + path);

			InputStream inputStream = new FileInputStream(new File(path));
			OutputStream os = response.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}

			// 这里主要关闭。
			os.close();

			inputStream.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		logger.info("【语音话单下载】下载结束...");
	}

	private String getFileName(HttpServletRequest request, String filePath) throws UnsupportedEncodingException {

		String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
		String Agent = request.getHeader("User-Agent");
		if (null != Agent) {
			Agent = Agent.toLowerCase();
			if (Agent.indexOf("firefox") != -1) {
				fileName = new String(fileName.getBytes(), "iso8859-1");
			} else if (Agent.indexOf("msie") != -1) {
				fileName = URLEncoder.encode(fileName, "UTF-8");
			} else {
				fileName = URLEncoder.encode(fileName, "UTF-8");
			}
		}
		return fileName;
	}
}
