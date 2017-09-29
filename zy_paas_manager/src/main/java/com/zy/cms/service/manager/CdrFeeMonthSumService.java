package com.zy.cms.service.manager;

import java.util.List;

import com.zy.cms.vo.WebResult;
import com.zy.cms.vo.manager.CdrFeeMonthResult;
import com.zy.cms.vo.manager.CdrFeeMonthSum;
import com.zy.cms.vo.query.CdrFeeMonthSumQuery;
import com.zy.cms.vo.query.VoiceQuery;

public interface CdrFeeMonthSumService {

	int deleteByPrimaryKey(Integer id);

    int insert(CdrFeeMonthSum record);

    int insertSelective(CdrFeeMonthSum record);

    CdrFeeMonthSum selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CdrFeeMonthSum record);

    int updateByPrimaryKey(CdrFeeMonthSum record);
    
    List<CdrFeeMonthSum> queryCdrFeeMonthByEntity(CdrFeeMonthSumQuery cdrFeeMonthSum);
    
    List<CdrFeeMonthSum> queryExportFeeMonthByEntity(CdrFeeMonthSumQuery cdrFeeMonthSum);
    
    int queryCdrFeeMonthAccount(CdrFeeMonthSumQuery cdrFeeMonthSum);
    
    List<CdrFeeMonthSum> queryCdrFeeDailyByEntity(CdrFeeMonthSumQuery cdrFeeMonthSum);
    
    int queryCdrFeeDailyAccount(CdrFeeMonthSumQuery cdrFeeMonthSum);
    
    /**
	 * 导出月结详单
	 * 
	 * @param query条件查询
	 * @realPath 文本上下文路径
	 * @webUrl 文本上下文url
	 * 
	 * @return
	 */
	public WebResult exportMonthExcel(CdrFeeMonthSumQuery query, String realPath, String webUrl);
	
	/**
	 * 导出月结详单
	 * 
	 * @param query条件查询
	 * @realPath 文本上下文路径
	 * @webUrl 文本上下文url
	 * 
	 * @return
	 */
	public WebResult exportDayExcel(CdrFeeMonthSumQuery query, String realPath, String webUrl);
	
	public CdrFeeMonthResult queryCdrMonthStatisticsCount(CdrFeeMonthSumQuery cdrFeeMonthSum);
	

}
