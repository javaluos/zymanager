package com.zy.cms.mapper.smsmanager;

import com.zy.cms.vo.manager.ChannelSummaryResult;
import com.zy.cms.vo.manager.SmsDailyStatistics;
import com.zy.cms.vo.query.ChannelSummaryQuery;
import com.zy.cms.vo.query.SmsSendQuery;

import java.sql.SQLException;
import java.util.List;

public interface SmsDailyStatisticsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SmsDailyStatistics record);

    int insertSelective(SmsDailyStatistics record);

    SmsDailyStatistics selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SmsDailyStatistics record);

    int updateByPrimaryKey(SmsDailyStatistics record);

    int selectTotalByQuery(SmsSendQuery query) throws SQLException;

    List<SmsDailyStatistics> selectListByQuery(SmsSendQuery query) throws SQLException;

    SmsDailyStatistics statSmsDailyTotal(SmsSendQuery query) throws SQLException;

    int selectViewTotalByQuery(SmsSendQuery query) throws SQLException;

    List<SmsDailyStatistics> selectViewListByQuery(SmsSendQuery query) throws SQLException;
    
    List<SmsDailyStatistics> getChannelSummarysByQuery(ChannelSummaryQuery query) throws SQLException;
    
    ChannelSummaryResult<SmsDailyStatistics> getChannelSummaryResult(ChannelSummaryQuery query) throws SQLException;
    
    /**
     * 短信通道跑量详情
     * @param query
     * @return
     * @throws SQLException
     */
    List<SmsDailyStatistics> getChannelDetailByQuery(ChannelSummaryQuery query) throws SQLException;
    
    /**
     * 短信通道跑量详情汇总
     * @param query
     * @return
     * @throws SQLException
     */
    ChannelSummaryResult<SmsDailyStatistics> getChannelDetailResult(ChannelSummaryQuery query) throws SQLException;
    
    int channelTotalByQuery(ChannelSummaryQuery query) throws SQLException;
    
    int channelDetailTotalByQuery(ChannelSummaryQuery query) throws SQLException;

}