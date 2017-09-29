package com.zy.cms.controller.analysis;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.zy.cms.common.CommonService;
import com.zy.cms.common.Constant;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.elastic.service.EsService;
import com.zy.cms.mapper.cdrs.CdrsMapper;
import com.zy.cms.mapper.master.VoiceUploadMapper;
import com.zy.cms.service.manager.AccountBindInfoService;
import com.zy.cms.service.master.MerchantAccountService;
import com.zy.cms.util.DateUtil;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.MerchantAccount;
import com.zy.cms.vo.User;
import com.zy.cms.vo.UserCurrent;
import com.zy.cms.vo.UserInfo;
import com.zy.cms.vo.query.ResultQuery;
import com.zy.cms.vo.query.VoiceQuery;

/**
 * paas平台统计分析控制器
 * 
 * @author allen.yuan
 * @date 2016-9-6
 * 
 */
@Controller
@RequestMapping(value = "/voiceRecord")
public class VoiceRecordController {

	private static final ZyLogger logger = ZyLogger.getLogger(VoiceRecordController.class);
	
	@Resource
	private CommonService commonService;
	
	@Resource
	private UserInfo userInfo;

	@Autowired
	private EsService esService;
	
	@Resource
	private MerchantAccountService merchantAccountService;
	
	@Resource
	private CdrsMapper cdrsMapper;
	
	@Resource
	private VoiceUploadMapper voiceUploadMapper;
	
	@Autowired
	private AccountBindInfoService accountBindInfoService;

	@RequestMapping(value = "/voice_record_list", method = RequestMethod.GET)
	public ModelAndView app(HttpServletRequest req) {

		ModelAndView mv = new ModelAndView("/voiceRecord/voice_list");
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
		mv.addObject("datetimeStart",DateUtil.formatDate(new Date(),"yyyy-MM-dd")+" 00:00:00");
		mv.addObject("datetimeEnd",DateUtil.formatDate(new Date(),"yyyy-MM-dd")+" 23:59:59");
		
		return mv;
	}

	/**
	 * 查询语音通知列表,语音验证码
	 * 
	 * @param request
	 * @param response
	 * @param params
	 *            包含(VoiceQuery参数对象的Json数据)
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/voice_record_list_data", produces = "application/json")
	public ModelAndView getVoiceNoticeList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("params") String params) {
		try {
			User us = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
			if (us == null) {
				logger.warn("用户登录超时!");
				return new ModelAndView("redirect:/login.html");
			}
			VoiceQuery query = null;
			MerchantAccount merchantAccount=null;
			if (!StringUtil.isEmpty(params)) {
				query = JsonUtil.parseToObject(params, VoiceQuery.class);
				if(!StringUtil.isEmpty(query.getMerchantPhone())){
				    merchantAccount=merchantAccountService.getMerchantAccountByPhone(query.getMerchantPhone());
					if(merchantAccount==null){
						ModelAndView mv = new ModelAndView("/voiceRecord/voice_list_data");
						return mv;
					}
				    query.setApiAccount(merchantAccount.getApiAccount());
				}
			}
			if(!StringUtil.isEmpty(query.getStarttime())){
				query.setStarttimeL(DateUtil.date2TimeStamp(query.getStarttime(), "yyyy-MM-dd HH:mm:ss") / 1000);
			}
			if(!StringUtil.isEmpty(query.getEndtime())){
				query.setEndtimeL(DateUtil.date2TimeStamp(query.getEndtime(), "yyyy-MM-dd HH:mm:ss") / 1000);
			}
			if(query.getQuerytype()==0){
			    query.setQuerytype(-1);
			}
			
			List<String> apiAccounts=accountBindInfoService.getApiAccountByBusName(us.getUserName());
		    List<String> apiAccountsLower=new ArrayList<String>();
		    if(apiAccounts.size()>0){
		    	for(String apiAccount:apiAccounts){
		    		apiAccountsLower.add(apiAccount.toLowerCase());
		    	}
		    }
			query.setApiAccounts(apiAccountsLower);
			
			Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query.getPageNum() - 1); // es分页码从0开始，所以-1
			Integer pageSize = query.getPageSize() == 0 ? Constant.PAGE_SIZE_20 : query.getPageSize();
			query.setPageNum(pageNum);
			query.setPageSize(pageSize);

			logger.info("页面传来的参数是："+params+" 查询语音通知条件=" + JsonUtil.toJsonString(query));
			
			String result = this.esService.searchCdr(query, Constant.ES_CDR_INDEX_NAME, Constant.ES_CDR_INDEX_TYPE);

			Map rs = (Map)JSON.parse(result);
			List<Map> dataMap=(List<Map>) rs.get("data"); 
			List<Map> list = new ArrayList<Map>();
			for(Map map:dataMap){
				String apiAccount=(String) map.get("apiAccount");
				if(merchantAccount==null){
					merchantAccount=merchantAccountService.getMerchantAccount(apiAccount);
				}
				if(merchantAccount==null){
					merchantAccount=new MerchantAccount();
				}
				map.put("merchantPhone", merchantAccount.getMerchantPhone());
				map.put("businessName", merchantAccount.getBusinessName());
			    map.put("calleeInviteTime2", DateUtil.timeStamp2Date((Integer) map.get("calleeInviteTime")));
			    
				list.add(map);
			}
			
			rs.put("data", list);
			rs.put("times", rs.get("times"));
			rs.put("total_page",rs.get("total_page") );
			rs.put("total", rs.get("total"));
			rs.put("page_num", rs.get("page_num"));
			rs.put("page_size", rs.get("page_size"));
			result=JsonUtil.toJsonString(rs);
			ResultQuery cdrQuery = JsonUtil.parseToObject(result, ResultQuery.class);

			// 定义结果
			ModelAndView mv = new ModelAndView("/voiceRecord/voice_list_data");
			mv.addObject("pgdata", cdrQuery);// 设定查询参数对象

			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("查询语音通知结果出现异常,异常为{0}",new Object[]{e.getMessage()},null);
			ModelAndView mv = new ModelAndView("/voiceRecord/voice_list_data");
			return mv;
		}

	}
	
	
	
	@RequestMapping(value = "/voice_record_content")
	public ModelAndView getVoiceNoticeContent(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("callId") String callId) {
		try {
			User us = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
			if (us == null) {
				logger.warn("用户登录超时!");
				return new ModelAndView("redirect:/login.html");
			}
			
			Map map=new HashMap();
			map.put("TABLE_NAME", DateUtil.getDateStr(new Date(Long.parseLong(callId.substring(0, 13)))));
			map.put("callId", callId);
			Map<String,Object> result=cdrsMapper.getCdrsDetail(map);
			Map<String,Object>resultMap=new HashMap<String,Object>();
			if(result!=null){
				String content="";        //保存文本内容
				String url="";            //保存语音文件下载地址，用逗号分割多个地址
				String[] fileId={}; 
				String verify_code="";     //验证码
				Map<String,String> dataMap= new HashMap<String,String>();
				//文本
				if((result.get("CONTENT_TYPE")+"").equals("0")){
					if(result.get("CONTENT")!=null){
						content=result.get("CONTENT")+"";
					}
				}
				//录音文件
				if((result.get("CONTENT_TYPE")+"").equals("1")){
					String tableName=getTableSuffix(result.get("API_ACCOUNT")+"");
					if(result.get("CONTENT")!=null){
						fileId=(result.get("CONTENT")+"").split(",");
					}
					
					for(int i=0;i<fileId.length;i++){
						Map paramap=new HashMap();
						paramap.put("TABLE_NAME", tableName);
						paramap.put("FILE_ID", fileId[i]);
						
						Map<String,String> re=voiceUploadMapper.selectUrlByFileId(paramap);
						if(re!=null){
							url= re.get("DOWNLOAD_URL");
						}
						dataMap.put( fileId[i], url);
					}
				}
				if(result.get("VERIFY_CODE")!=null){
					verify_code=result.get("VERIFY_CODE")+"";
				}
				resultMap.put("content", content);
				resultMap.put("verify_code", verify_code);
				if(dataMap!=null){
					resultMap.put("dataMap", dataMap);
				}
				
			}else{
				//没有找到callId对应的文本或者语音
				resultMap.put("content", "");
				resultMap.put("dataMap", "");
				resultMap.put("verify_code", "");
			}
			
			// 定义结果
			ModelAndView mv = new ModelAndView("/voiceRecord/voice_list_content");
			mv.addObject("pgdata", resultMap);// 设定查询参数对象

			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("查询语音通知结果出现异常,异常为{0}",new Object[]{e.getMessage()},null);
			ModelAndView mv = new ModelAndView("/voiceRecord/voice_list_content");
			return mv;
		}

	}
	
	private String getTableSuffix(String apiAccount){
    	if(null==apiAccount||"".equals(apiAccount)){
    		return null;
    	}else{
    		int value=Math.abs(apiAccount.hashCode());
    		value=(value%tableSheets==0?tableSheets:(value%tableSheets));
    		return ""+value;
    	}
    }
    private static int tableSheets=20;


}
