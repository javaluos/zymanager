package com.zy.cms.service.manager.excel;

import com.zy.cms.vo.WebResult;
import com.zy.cms.vo.query.ChannelSummaryQuery;

/**
 * 短信通道跑量Excel 业务接口
 * 
 * @author allen.yuan
 * @date 2016-11-5
 *
 */
public interface ChannelSummaryExcelService {

	/**
	 * 导出短信通道跑量汇总
	 * 
	 * @param query 条件查询
	 * @realPath 文本上下文路径
	 * @webUrl   文本上下文url
	 * 
	 * @return
	 */
	public WebResult exportChannelExcel(ChannelSummaryQuery query, String realPath, String webUrl);
}
