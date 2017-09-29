package com.zy.cms.service.manager.excel;

import com.zy.cms.vo.WebResult;
import com.zy.cms.vo.query.ChannelSummaryQuery;
import com.zy.cms.vo.query.SmsDailyRevenueStatisticsQuery;

/**
 * 营收分析Excel 业务接口
 * 
 * @author allen.yuan
 * @date 2016-11-5
 *
 */
public interface RevenueExcelService {

	/**
	 * 导出客户分析
	 * 
	 * @param query 条件查询
	 * @realPath 文本上下文路径
	 * @webUrl   文本上下文url
	 * 
	 * @return
	 */
	public WebResult exportAccountRevenueExcel(SmsDailyRevenueStatisticsQuery query, String realPath, String webUrl);
	
	/**
	 * 导出通道分析
	 * 
	 * @param query 条件查询
	 * @realPath 文本上下文路径
	 * @webUrl   文本上下文url
	 * 
	 * @return
	 */
	public WebResult exportChannelRevenueExcel(SmsDailyRevenueStatisticsQuery query, String realPath, String webUrl);
	
	/**
	 * 导出汇总分析
	 * 
	 * @param query 条件查询
	 * @realPath 文本上下文路径
	 * @webUrl   文本上下文url
	 * 
	 * @return
	 */
	public WebResult exportRevenueExcel(SmsDailyRevenueStatisticsQuery query, String realPath, String webUrl);
}
