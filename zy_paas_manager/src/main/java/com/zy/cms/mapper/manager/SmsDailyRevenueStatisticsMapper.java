package com.zy.cms.mapper.manager;

import java.util.List;

import com.zy.cms.vo.SmsDailyRevenueStatistics;
import com.zy.cms.vo.manager.RevenueResult;
import com.zy.cms.vo.query.SmsDailyRevenueStatisticsQuery;

/**
 * 营收分析
 * @author JasonXu
 *
 */
public interface SmsDailyRevenueStatisticsMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(SmsDailyRevenueStatistics record);

    int insertSelective(SmsDailyRevenueStatistics record);

    SmsDailyRevenueStatistics selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SmsDailyRevenueStatistics record);

    int updateByPrimaryKey(SmsDailyRevenueStatistics record);
    
    int listAccountRevenueCount(SmsDailyRevenueStatisticsQuery query);
    
    List<SmsDailyRevenueStatistics> listAccountRevenue(SmsDailyRevenueStatisticsQuery query);
    
    int listChannelRevenueCount(SmsDailyRevenueStatisticsQuery query);
    
    List<SmsDailyRevenueStatistics> listChannelRevenue(SmsDailyRevenueStatisticsQuery query);
    
    List<SmsDailyRevenueStatistics> listSummaryRevenue(SmsDailyRevenueStatisticsQuery query);
    
    int listSummaryRevenueCount(SmsDailyRevenueStatisticsQuery query);
    
    List<SmsDailyRevenueStatistics> listRevenue(SmsDailyRevenueStatisticsQuery query);
    
    int listRevenueCount(SmsDailyRevenueStatisticsQuery query);
    
    public RevenueResult getRevenueResult(SmsDailyRevenueStatisticsQuery query);
}