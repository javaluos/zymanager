package com.zy.cms.service.manager;

import com.zy.cms.vo.chart.ChartUI;
import com.zy.cms.vo.query.ChartQuery;

/**
 * 话单定时统计图表业务接口(客户业务)
 * 
 * @author allen.yuan
 * @date 2016-11-25
 */
public interface CdrMonitorAccountChartService {

	/**
	 * 通过UI参数查询图表数据
	 * 
	 * @param chartQuery
	 *            图表查询参数
	 * @return ChartUI
	 */
	ChartUI queryMonitorStatForAccountLine(ChartQuery chartQuery);
}
