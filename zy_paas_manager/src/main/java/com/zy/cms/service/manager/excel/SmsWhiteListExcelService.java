package com.zy.cms.service.manager.excel;

import com.zy.cms.vo.WebResult;
import com.zy.cms.vo.query.SmsBlackListQuery;

/**
 * 短信白名单 Excel业务接口
 * 
 * @author allen.yuan
 * @date 2016-11-5
 *
 */
public interface SmsWhiteListExcelService {

	/**
	 * 导出短信白名单列表
	 * 
	 * @param query 条件查询
	 * @realPath 文本上下文路径
	 * @webUrl   文本上下文url
	 * 
	 * @return
	 */
	public WebResult exportWhiteExcel(SmsBlackListQuery query, String realPath, String webUrl);
}
