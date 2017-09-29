package com.zy.cms.controller.channel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zy.cms.common.CommonService;
import com.zy.cms.common.Constant;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.service.manager.AccountBindInfoService;
import com.zy.cms.service.manager.SmsDailyStatService;
import com.zy.cms.service.manager.excel.ChannelSummaryExcelService;
import com.zy.cms.service.master.MerchantAccountService;
import com.zy.cms.service.master.SmsChannelService;
import com.zy.cms.util.DateUtil;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.MerchantAccount;
import com.zy.cms.vo.User;
import com.zy.cms.vo.UserCurrent;
import com.zy.cms.vo.UserInfo;
import com.zy.cms.vo.WebResult;
import com.zy.cms.vo.channel.SmsChannel;
import com.zy.cms.vo.manager.ChannelSummaryResult;
import com.zy.cms.vo.manager.SmsDailyStatistics;
import com.zy.cms.vo.query.ChannelSummaryQuery;
import com.zy.cms.vo.query.SmsChannelQuery;

/**
 * 
 * 短信通道跑量管理
 * 
 * @author JasonXu
 * @date 2017-03-23
 * 
 */
@Controller
@RequestMapping(value = "/channelSummary")
public class ChannelSummaryController {

	private static final ZyLogger logger = ZyLogger.getLogger(ChannelSummaryController.class);
	
	@Resource
	private CommonService commonService;
	
	@Resource
	private UserInfo userInfo;

	@Resource
	private MerchantAccountService merchantAccountService;
	
	@Resource
	private SmsChannelService smsChannelService;
	
	@Resource
	private SmsDailyStatService smsDailyStatService;

	@Resource
	private ChannelSummaryExcelService channelSummaryExcelService;

	@Resource
	private AccountBindInfoService accountBindInfoService;
	
	/**
	 * 
	 * 短信通道跑量管理列表
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/channel_summary_list", method = RequestMethod.GET)
	public ModelAndView summaryList(HttpServletRequest req) {

		ModelAndView mv=new ModelAndView("/smsDailyStatistics/channel_summary_list");//语音通知汇总页面
		try {
			User us = commonService.valideUser(req, Constant.USER_SESSION_UNAME);
			if (us == null) {
				logger.warn("用户登录超时!");
				return new ModelAndView("redirect:/login.html");
			}
			this.userInfo.setUserAccountInfo(mv, req);
			
			/* 下面表示后台左右菜单栏，当前的选择状态为管理中心 */
			UserCurrent current = new UserCurrent();
			current.setDataAnalysis(Constant.TRUE_STR);
			mv.addObject("current", current);
			mv.addObject("datetimeStart",DateUtil.formatDate(new Date(new Date().getTime()-24*60*60*1000),"yyyy-MM-dd"));
			mv.addObject("datetimeEnd",DateUtil.formatDate(new Date(new Date().getTime()-24*60*60*1000),"yyyy-MM-dd"));
			return mv;
		} catch (Exception e) {
			logger.info("跳转页面出现异常,异常原因是:"+e.getMessage());
			e.printStackTrace();
			return mv;
		}
	}

	
	/**
	 * 短信通道跑量加载数据
	 * @param request
	 * @param response
	 * @param params
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/channel_summary_list_data", produces = "application/json")
	public ModelAndView summaryListData(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("params") String params) {
		ModelAndView mv = new ModelAndView("/smsDailyStatistics/channel_summary_list_data");//语音通知汇总页面
		try {
			User us = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
			if (us == null) {
				logger.warn("用户登录超时!");
				return new ModelAndView("redirect:/login.html");
			}
			ChannelSummaryQuery query = null;
			if (!StringUtil.isEmpty(params)) {
				query = JsonUtil.parseToObject(params, ChannelSummaryQuery.class);
				SmsChannelQuery smsChannelQuery=null;
				
				if(!StringUtil.isEmpty(query.getChannelName())){//通道名称
				    smsChannelQuery=new SmsChannelQuery();
				    smsChannelQuery.setChannelName(query.getChannelName());
				}
				
				if(!StringUtil.isEmpty(query.getChannelMainCode())){//通道编号
					if(smsChannelQuery==null){
						smsChannelQuery=new SmsChannelQuery();
					}
					smsChannelQuery.setChannelMainCode(query.getChannelMainCode());
				}
				
				if(!StringUtil.isEmpty(query.getChannelSmsId())){//通道ID
					if(smsChannelQuery==null){
						smsChannelQuery=new SmsChannelQuery();
					}
					smsChannelQuery.setChannelId(query.getChannelSmsId());
				}
				
				if(query.getChannelType()!=-1){//通道类型
					if(smsChannelQuery==null){
						smsChannelQuery=new SmsChannelQuery();
					}
					smsChannelQuery.setChannelType(query.getChannelType());
				}
				
				List<String> channelSmsIds=smsChannelService.queryIdsByEntity(smsChannelQuery);
				if(channelSmsIds==null || channelSmsIds.size()<=0){
					return mv;
				}
				query.setChannelSmsIds(channelSmsIds);
			}
			
			Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query.getPageNum() - 1);
			Integer pageSize = query.getPageSize() == 0 ? Constant.PAGE_SIZE_20 : query.getPageSize();
			query.setPageNum(pageNum);
			query.setPageSize(pageSize);

			logger.warn("【短信通道跑量】页面传过来的参数是:"+params+" 查询条件是:");

			List<String> apiAccounts=accountBindInfoService.getApiAccountByBusName(us.getUserName());
			query.setApiAccounts(apiAccounts);
			ChannelSummaryResult cdrStatisticsResult =smsDailyStatService.getChannelSummaryResult(query);
			List<SmsDailyStatistics> list = smsDailyStatService.getChannelSummarysByQuery(query);
			Integer total = smsDailyStatService.channelTotalByQuery(query);
			
			if(list.size()>0){
				for(SmsDailyStatistics cds:list){
					SmsChannel smsChannel=smsChannelService.selectByPrimaryKey(cds.getChannelSmsId());
					if(smsChannel!=null){
						cds.setChannelMainCode(smsChannel.getChannelMainCode());
						cds.setChannelName(smsChannel.getChannelName());
						cds.setChannelType(smsChannel.getChannelType());
					}
				}
			}else{
				list=new ArrayList<SmsDailyStatistics>();
				cdrStatisticsResult=new ChannelSummaryResult();
			}
			
			// 构建查询结果对象
			cdrStatisticsResult.setTotal(Long.valueOf(total));
			cdrStatisticsResult.setPage_num(Long.valueOf(query.getPageNum() + 1));
			cdrStatisticsResult.setPage_size(Long.valueOf(query.getPageSize()));
			cdrStatisticsResult.setData(list);
			
			// 设定查询参数对象['pgdata'为JSP参数,不随意修改]
			mv.addObject("pgdata", cdrStatisticsResult);
			return mv;
		}
		catch(Exception e){
			e.printStackTrace();
			logger.info("【短信通道跑量】出现异常的原因是："+e.getMessage());
			return mv;
		}
	}
	
	
	/**
	 * 
	 * 短信通道跑量详情列表
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/detail_list", method = RequestMethod.GET)
	public ModelAndView detailList(HttpServletRequest req) {

		ModelAndView mv=new ModelAndView("/smsDailyStatistics/channel_detail_list");//语音通知汇总页面
		try {
			User us = commonService.valideUser(req, Constant.USER_SESSION_UNAME);
			if (us == null) {
				logger.warn("用户登录超时!");
				return new ModelAndView("redirect:/login.html");
			}
			this.userInfo.setUserAccountInfo(mv, req);
			
			String channelSmsId=req.getParameter("channelSmsId");
			String channelName=req.getParameter("channelName");
			String dateTime=req.getParameter("dateTime");
			channelName=new String(channelName.getBytes("iso8859-1"),"UTF-8");
			
			/* 下面表示后台左右菜单栏，当前的选择状态为管理中心 */
			UserCurrent current = new UserCurrent();
			current.setDataAnalysis(Constant.TRUE_STR);
			mv.addObject("current", current);
			mv.addObject("channelSmsId",channelSmsId);
			mv.addObject("channelName",channelName);
			mv.addObject("datetimeStart",dateTime);
			mv.addObject("datetimeEnd",dateTime);
			mv.addObject("datetimeStart",DateUtil.formatDate(dateTime));
			mv.addObject("datetimeEnd",DateUtil.formatDate(dateTime));
			return mv;
		} catch (Exception e) {
			logger.info("【短信通道跑量详情】跳转页面出现异常,异常原因是:"+e.getMessage());
			e.printStackTrace();
			return mv;
		}
	}

	
	/**
	 * 短信通道跑量详情加载数据
	 * @param request
	 * @param response
	 * @param params
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/detail_list_data", produces = "application/json")
	public ModelAndView detailListData(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("params") String params) {
		ModelAndView mv = new ModelAndView("/smsDailyStatistics/channel_detail_list_data");//语音通知汇总页面
		try {
			User us = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
			if (us == null) {
				logger.warn("用户登录超时!");
				return new ModelAndView("redirect:/login.html");
			}
			ChannelSummaryQuery query = null;
			if (!StringUtil.isEmpty(params)) {
				query = JsonUtil.parseToObject(params, ChannelSummaryQuery.class);
			}
			
			Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query.getPageNum() - 1);
			Integer pageSize = query.getPageSize() == 0 ? Constant.PAGE_SIZE_20 : query.getPageSize();
			query.setPageNum(pageNum);
			query.setPageSize(pageSize);

			logger.warn("【短信通道跑量详情】页面传过来的参数是:"+params+" 查询条件是:");

			List<String> apiAccounts=accountBindInfoService.getApiAccountByBusName(us.getUserName());
			query.setApiAccounts(apiAccounts);
			ChannelSummaryResult cdrStatisticsResult =smsDailyStatService.getChannelDetailResult(query);
			List<SmsDailyStatistics> list = smsDailyStatService.getChannelDetailByQuery(query);
			Integer total = smsDailyStatService.channelDetailTotalByQuery(query);
			
			if(list.size()>0){
				for(SmsDailyStatistics cds:list){
					MerchantAccount merchantAccount=merchantAccountService.getMerchantAccount(cds.getApiAccount());
					if(merchantAccount!=null){
						cds.setMerchantPhone(merchantAccount.getMerchantPhone());
						cds.setBusinessName(merchantAccount.getBusinessName());
					}
				}
			}else{
				list=new ArrayList<SmsDailyStatistics>();
				cdrStatisticsResult=new ChannelSummaryResult();
			}
			
			// 构建查询结果对象
			cdrStatisticsResult.setTotal(Long.valueOf(total));
			cdrStatisticsResult.setPage_num(Long.valueOf(query.getPageNum() + 1));
			cdrStatisticsResult.setPage_size(Long.valueOf(query.getPageSize()));
			cdrStatisticsResult.setData(list);
			
			// 设定查询参数对象['pgdata'为JSP参数,不随意修改]
			mv.addObject("pgdata", cdrStatisticsResult);
			return mv;
		}
		catch(Exception e){
			e.printStackTrace();
			logger.info("【短信通道跑量详情】出现异常的原因是："+e.getMessage());
			return mv;
		}
	}
	
	
	/**
	 * 短信通道跑量导出功能
	 * @param request
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/export")
	@ResponseBody
	public WebResult exportChannelSummary(HttpServletRequest request,
			@RequestParam(value = "params", required = false) String params) {

		logger.info("【短信通道跑量导出】开始  params=" + params);

		WebResult webRs = new WebResult();
		User us = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
		if (us == null) {
			logger.warn("用户登录超时!");
			webRs.setCode(-1);
			webRs.setMsg("用户登录超时!");
		}

		ChannelSummaryQuery query = null;
		if (!StringUtil.isEmpty(params)) {
			query = JsonUtil.parseToObject(params, ChannelSummaryQuery.class);
			SmsChannelQuery smsChannelQuery=null;
			
			if(!StringUtil.isEmpty(query.getChannelName())){//通道名称
			    smsChannelQuery=new SmsChannelQuery();
			    smsChannelQuery.setChannelName(query.getChannelName());
			}
			
			if(!StringUtil.isEmpty(query.getChannelMainCode())){//通道编号
				if(smsChannelQuery==null){
					smsChannelQuery=new SmsChannelQuery();
				}
				smsChannelQuery.setChannelMainCode(query.getChannelMainCode());
			}
			
			if(!StringUtil.isEmpty(query.getChannelSmsId())){//通道ID
				if(smsChannelQuery==null){
					smsChannelQuery=new SmsChannelQuery();
				}
				smsChannelQuery.setChannelId(query.getChannelSmsId());
			}
			
			if(query.getChannelType()!=-1){//通道类型
				if(smsChannelQuery==null){
					smsChannelQuery=new SmsChannelQuery();
				}
				smsChannelQuery.setChannelType(query.getChannelType());
			}
			
			List<String> channelSmsIds=smsChannelService.queryIdsByEntity(smsChannelQuery);
			if(channelSmsIds==null || channelSmsIds.size()<=0){
				return webRs;
			}
			query.setChannelSmsIds(channelSmsIds);
		}
		List<String> apiAccounts=accountBindInfoService.getApiAccountByBusName(us.getUserName());
		query.setApiAccounts(apiAccounts);
		
		Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query.getPageNum() - 1); // 分页码从0开始，所以-1
		query.setPageNum(pageNum);
		query.setPageSize(1000000);// 最大值

		// 调用导出业务
		String realPath = request.getSession().getServletContext().getRealPath("");
		webRs = channelSummaryExcelService.exportChannelExcel(query, realPath, request.getContextPath());

		logger.info("【短信通道跑量导出】结束...");

		return webRs;
	}

	/**
	 *  短信通道跑量下载功能
	 * @param downUrl
	 * @param request
	 * @param response
	 */
	@RequestMapping("/down")
	public void download(String downUrl, HttpServletRequest request, HttpServletResponse response) {

		logger.info("【短信通道跑量下载】下载开始...");
		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType("multipart/form-data");
			response.setHeader("Content-Disposition", "attachment;fileName=" + getFileName(request, downUrl));

			String path = request.getSession().getServletContext().getRealPath("") + downUrl;// 这个download目录为啥建立在classes下的
			logger.info("【短信通道跑量下载】downUrl=" + path);

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
