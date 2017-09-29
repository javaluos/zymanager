package com.zy.cms.mapper.sms;

import com.zy.cms.vo.manager.FailDetailVO;
import com.zy.cms.vo.manager.SmsDailyStatistics;
import com.zy.cms.vo.query.SmsSendQuery;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by luos on 2017/5/18.
 */
public interface SmsStatisticsMapper {

    Integer selectTodayTotalByQuery(SmsSendQuery query) throws SQLException;

    List<SmsDailyStatistics> selectTodayListByQuery(SmsSendQuery query) throws SQLException;

    SmsDailyStatistics statTodaySmsDailyTotal(SmsSendQuery query) throws SQLException;

    Integer selectTodayViewTotalByQuery(SmsSendQuery query) throws SQLException;

    List<SmsDailyStatistics> selectTodayViewListByQuery(SmsSendQuery query) throws SQLException;

    List<FailDetailVO> querySmsFailDetailByQuery(SmsSendQuery query) throws SQLException;

    List<FailDetailVO> querySmsViewFailDetailByQuery(SmsSendQuery query) throws SQLException;
}
