package com.zy.cms.controller.chart;

import java.util.ArrayList;
import java.util.List;

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
import com.zy.cms.enums.BusinessTypeEnum;
import com.zy.cms.enums.GlobalFlagEnum;
import com.zy.cms.enums.MonitorTypeEnum;
import com.zy.cms.service.manager.CdrMonitorGlobalChartService;
import com.zy.cms.service.manager.UserMenuService;
import com.zy.cms.util.DateUtil;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.EnumUtil;
import com.zy.cms.vo.User;
import com.zy.cms.vo.UserMenu;
import com.zy.cms.vo.chart.ChartUI;
import com.zy.cms.vo.query.ChartQuery;

/**
 * 全局监控图表数据业务控制器
 * 
 * @author allen.yuan
 * @date 2016-11-23
 *
 */
@Controller
@RequestMapping("/chart")
public class MonitorGlobalChartController extends ChartBaseController {

	private static final ZyLogger logger = ZyLogger.getLogger(MonitorGlobalChartController.class);

	@Autowired
	protected CdrMonitorGlobalChartService cdrMonitorGlobalChartService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private UserMenuService userMenuService;

	@RequestMapping("/global_chartline")
	public ModelAndView showGlobalChartLine(HttpServletRequest request, String apiAccount) {
		User user = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
		ModelAndView model = new ModelAndView("/chart/global_chartline");
		model.addObject("apiAccount", "0");
		model.addObject("btList", initBtList(GlobalFlagEnum.GLOBAL.getType()));// 业务列表
		model.addObject("mtList", initMtList());// 监控列表
		model.addObject("currentDate", DateUtil.getDateYMD());// 当前时间

		// 是否有指标设置权限
		UserMenu userMenu = userMenuService.getByUserName(user.getUserName());
		String menus = userMenu.getMenus();
		if (StringUtils.isNotBlank(menus) && menus.contains(Constant.GLOBAL_SETTING_MENUID)) {
			model.addObject("permission", true);
		} else {
			model.addObject("permission", false);
		}
		return model;
	}

	/**
	 * 全局业务-定时加载图表数据
	 * 
	 * @param request
	 * @param params
	 *            参数对象
	 * @return
	 */
	@RequestMapping("/queryGlobalChartData")
	@ResponseBody
	public ChartUI queryGlobalChartData(HttpServletRequest request, @RequestParam("params") String params) {

		logger.info("【全局业务-折线图】params=" + params);
		ChartQuery chartQuery = null;
		if (!StringUtil.isEmpty(params)) {
			chartQuery = JsonUtil.parseToObject(params, ChartQuery.class);
		}
		if (chartQuery == null) {
			chartQuery = new ChartQuery();
		}

		ChartUI chartUI = cdrMonitorGlobalChartService.queryMonitorStatForGlobalLine(chartQuery);
		// logger.info("【全局业务-折线图】data=" + JsonUtil.toJsonString(chartUI));
		return chartUI;
	}

	/**
	 * 通过业务类型动态获得监控类型
	 * 
	 * @param request
	 * @param businessId
	 *            业务类型
	 * @return
	 */
	@RequestMapping("/queryMonitorByBs")
	@ResponseBody
	public List<EnumUtil> queryMonitorByBs(HttpServletRequest request, @RequestParam("businessId") String businessId) {

		logger.info("【全局业务-折线图】businessId=" + businessId);
		String bsId = StringUtil.isEmpty(businessId) ? "0" : businessId;

		List<EnumUtil> mtList = new ArrayList<EnumUtil>();
		if (BusinessTypeEnum.BT_8.getType() == Integer.valueOf(bsId)
				|| BusinessTypeEnum.BT_9.getType() == Integer.valueOf(bsId)
				|| BusinessTypeEnum.BT_11.getType() == Integer.valueOf(bsId)
				|| BusinessTypeEnum.BT_80.getType() == Integer.valueOf(bsId)) {

			mtList.add(new EnumUtil(MonitorTypeEnum.MT_1.getType(), MonitorTypeEnum.MT_1.getName()));// 短信业务只要并发次数监控
		} else {
			mtList = initMtList(); // 监控所有业务
		}

		return mtList;
	}
}
