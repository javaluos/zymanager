package com.zy.cms.service.manager;

import java.sql.SQLException;
import java.util.List;

import com.zy.cms.vo.MerchantAccount;
import com.zy.cms.vo.WebResult;
import com.zy.cms.vo.manager.ChannelSummaryResult;
import com.zy.cms.vo.manager.FailDetailVO;
import com.zy.cms.vo.manager.SmsDailyStatistics;
import com.zy.cms.vo.query.ChannelSummaryQuery;
import com.zy.cms.vo.query.SmsSendQuery;

/**
 * Created by luos on 2017/3/23.
 */
public interface SmsDailyStatService {

    Integer getTotalByQuery(SmsSendQuery query) throws SQLException;

    List<SmsDailyStatistics> getListByQuery(SmsSendQuery query) throws SQLException;

    SmsDailyStatistics statSmsDailyTotal(SmsSendQuery query) throws SQLException;

    Integer getViewTotalByQuery(SmsSendQuery query) throws SQLException;

    List<SmsDailyStatistics> getViewListByQuery(SmsSendQuery query) throws SQLException;
    
    /**
     * 短信通道跑量
     * @param query
     * @return
     * @throws SQLException
     */
    List<SmsDailyStatistics> getChannelSummarysByQuery(ChannelSummaryQuery query) throws SQLException;
    
    /**
     * 短信通道跑量汇总
     * @param query
     * @return
     * @throws SQLException
     */
    ChannelSummaryResult<SmsDailyStatistics> getChannelSummaryResult(ChannelSummaryQuery query) throws SQLException;

    public WebResult exportExcel(SmsSendQuery query, String realPath, String webUrl);
    
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

    Integer getTodayTotalByQuery(SmsSendQuery query) throws SQLException;

    List<SmsDailyStatistics> getTodayListByQuery(SmsSendQuery query) throws SQLException;

    SmsDailyStatistics statTodaySmsDailyTotal(SmsSendQuery query) throws SQLException;

    Integer getTodayViewTotalByQuery(SmsSendQuery query) throws SQLException;

    List<SmsDailyStatistics> getTodayViewListByQuery(SmsSendQuery query) throws SQLException;

    List<FailDetailVO> getSmsFailDetailByQuery(SmsSendQuery query) throws SQLException;

    List<FailDetailVO> getSmsViewFailDetailByQuery(SmsSendQuery query) throws SQLException;

    WebResult exportFailAnalysis(SmsSendQuery query, String realPath, String contextPath) throws SQLException;

    WebResult exportChannelFailAnalysis(SmsSendQuery query, String realPath, String contextPath) throws SQLException;

    String getLatestStatDateTime();
}
