package com.zy.cms.controller.chart;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zy.cms.common.CommonService;
import com.zy.cms.common.Constant;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.enums.GlobalFlagEnum;
import com.zy.cms.service.manager.CdrMonitorAccountChartService;
import com.zy.cms.service.manager.UserMenuService;
import com.zy.cms.service.master.MerchantAccountService;
import com.zy.cms.util.DateUtil;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.MerchantAccount;
import com.zy.cms.vo.User;
import com.zy.cms.vo.UserMenu;
import com.zy.cms.vo.chart.ChartUI;
import com.zy.cms.vo.query.ChartQuery;

/**
 * 客户监控图表数据业务控制器
 * 
 * @author allen.yuan
 * @date 2016-11-23
 *
 */
@Controller
@RequestMapping("/chart")
public class MonitorAccountChartController extends ChartBaseController {

	private static final ZyLogger logger = ZyLogger.getLogger(MonitorAccountChartController.class);

	@Autowired
	protected MerchantAccountService accountService;

	@Autowired
	protected CdrMonitorAccountChartService cdrMonitorAccountChartService;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private UserMenuService userMenuService;

	@RequestMapping("/account_chartline")
	public ModelAndView showAccountChartLine(HttpServletRequest request, String apiAccount) {
		User user = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
		ModelAndView model = new ModelAndView("/chart/account_chartline");
		MerchantAccount mAccount = accountService.getMerchantAccount(apiAccount);
		model.addObject("mAccount", mAccount);// 账号信息
		model.addObject("btList", initBtList(GlobalFlagEnum.DEFAULT.getType()));// 业务列表
		model.addObject("mtList", initMtList());// 监控列表
		model.addObject("currentDate", DateUtil.getDateYMD());// 当前时间
		
		//是否有指标设置权限
		UserMenu userMenu = userMenuService.getByUserName(user.getUserName());
		String menus = userMenu.getMenus();
		if(StringUtils.isNotBlank(menus) && menus.contains(Constant.ACCOUNT_SETTING_MENUID)){
			model.addObject("permission", true);
		}else{
			model.addObject("permission", false);
		}
		return model;
	}

	/**
	 * 客户业务-定时加载图表数据
	 * 
	 * @param request
	 * @param params
	 *            参数对象
	 * @return
	 */
	@RequestMapping("/queryAccountChartData")
	@ResponseBody
	public ChartUI queryAccountChartData(HttpServletRequest request, @RequestParam("params") String params) {

		logger.info("【客户业务-折线图】params=" + params);

		ChartQuery chartQuery = null;
		if (!StringUtil.isEmpty(params)) {
			chartQuery = JsonUtil.parseToObject(params, ChartQuery.class);
		}
		if (chartQuery == null) {
			chartQuery = new ChartQuery();
		}

		ChartUI chartUI = cdrMonitorAccountChartService.queryMonitorStatForAccountLine(chartQuery);
		//logger.info("【客户业务-折线图】data=" + JsonUtil.toJsonString(chartUI));
		return chartUI;
	}
}
