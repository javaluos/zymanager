package com.zy.cms.controller.analysis;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.zy.cms.common.CommonService;
import com.zy.cms.common.Constant;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.elastic.service.EsService;
import com.zy.cms.service.manager.AccountBindInfoService;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.StringUtil;
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
@RequestMapping(value = "/analysis")
public class AnalysisCdrController {

	private static final ZyLogger logger = ZyLogger.getLogger(AnalysisCdrController.class);
	
	@Resource
	private CommonService commonService;
	@Resource
	private UserInfo userInfo;

	@Autowired
	private EsService esService;
	
	@Autowired
	private AccountBindInfoService accountBindInfoService;


	@RequestMapping(value = "/cdrlist", method = RequestMethod.GET)
	public ModelAndView app(HttpServletRequest req) {

		ModelAndView mv = new ModelAndView("/analysis/cdrlist");
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
	@RequestMapping(value = "/tbs_voicenotice", produces = "application/json")
	public ModelAndView getVoiceNoticeList(HttpServletRequest request, HttpServletResponse response,
		   @RequestParam("params") String params) {

		User us = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
		if (us == null) {
			logger.warn("用户登录超时!");
			return new ModelAndView("redirect:/login.html");
		}
		
		VoiceQuery query = null;
		if (!StringUtil.isEmpty(params)) {
			query = JsonUtil.parseToObject(params, VoiceQuery.class);
		}
		if (query == null) {
			query = new VoiceQuery();
		}

		// 设置参数
		if (!StringUtil.isEmpty(query.getStarttime())) {

			if (query.getStarttime().trim().length() == 10) {
				query.setStarttime(query.getStarttime() + Constant.Time.START_HHMMSS);
			}
		}
		if (!StringUtil.isEmpty(query.getEndtime())) {
			if (query.getEndtime().trim().length() == 10) {
				query.setEndtime(query.getEndtime() + Constant.Time.END_HHMMSS);
			}
		}
		Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query.getPageNum() - 1); // es分页码从0开始，所以-1
		Integer pageSize = query.getPageSize() == 0 ? Constant.PAGE_SIZE_20 : query.getPageSize();
		query.setPageNum(pageNum);
		query.setPageSize(pageSize);
		query.setApiAccount("");

		List<String> apiAccounts=accountBindInfoService.getApiAccountByBusName(us.getUserName());
	    List<String> apiAccountsLower=new ArrayList<String>();
	    if(apiAccounts.size()>0){
	    	for(String apiAccount:apiAccounts){
	    		apiAccountsLower.add(apiAccount.toLowerCase());
	    	}
	    }
		query.setApiAccounts(apiAccountsLower);
		
		logger.info("查询语音通知条件=" + JsonUtil.toJsonString(query));
		String result = this.esService.searchCdr(query, Constant.ES_CDR_INDEX_NAME, Constant.ES_CDR_INDEX_TYPE);

		ResultQuery cdrQuery = JsonUtil.parseToObject(result, ResultQuery.class);

		// 定义结果
		ModelAndView mv = new ModelAndView("/analysis/tbs_voicenotice");
		if (query.getQuerytype() == 5) {// 语音验证码
			mv = new ModelAndView("/analysis/tbs_voicecode");
		}
		mv.addObject("pgdata", cdrQuery);// 设定查询参数对象

		return mv;

	}

	/**
	 * 查询语音验证码列表
	 * 
	 * @param request
	 * @param response
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/tbs_voicecode", produces = "text/html;charset=UTF-8")
	public ModelAndView getVoiceCodeList(HttpServletRequest request, HttpServletResponse response, VoiceQuery query) {

		ModelAndView mv = new ModelAndView("/analysis/tbs_voicecode");
		return mv;

	}

}
