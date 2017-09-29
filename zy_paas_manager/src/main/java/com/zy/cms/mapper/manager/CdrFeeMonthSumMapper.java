package com.zy.cms.mapper.manager;

import java.util.List;

import com.zy.cms.vo.manager.CdrFeeMonthResult;
import com.zy.cms.vo.manager.CdrFeeMonthSum;
import com.zy.cms.vo.query.CdrFeeMonthSumQuery;

public interface CdrFeeMonthSumMapper {
    
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
     
    List<CdrFeeMonthSum> queryExportFeeDailyByEntity(CdrFeeMonthSumQuery cdrFeeMonthSum);
    
    int queryCdrFeeDailyAccount(CdrFeeMonthSumQuery cdrFeeMonthSum);
    
    CdrFeeMonthResult queryCdrMonthStatisticsCount(CdrFeeMonthSumQuery cdrFeeMonthSum);
}