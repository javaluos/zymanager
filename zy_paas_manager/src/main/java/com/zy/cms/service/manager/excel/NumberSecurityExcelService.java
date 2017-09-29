package com.zy.cms.service.manager.excel;

import com.zy.cms.vo.WebResult;
import com.zy.cms.vo.query.CdrDailyStatisticsQuery;
import com.zy.cms.vo.query.VoiceQuery;

/**
 * 号码卫士发送记录话单Excel 业务接口
 * 
 * @author allen.yuan
 * @date 2016-11-5
 *
 */
public interface NumberSecurityExcelService {

	/**
	 * 导出号码卫士发送记录详单
	 * 
	 * @param query
	 *            条件查询
	 * @realPath 文本上下文路径
	 * @webUrl 文本上下文url
	 * 
	 * @return
	 */
	public WebResult exportVoiceExcel(VoiceQuery query, String realPath, String webUrl);

	public WebResult exportSummaryExcel(CdrDailyStatisticsQuery query, String realPath, String contextPath);
}
