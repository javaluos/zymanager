package com.zy.cms.mapper.manager;

import java.sql.SQLException;
import java.util.List;

import com.zy.cms.vo.manager.SmsDailyStatistics;
import com.zy.cms.vo.query.SmsSendQuery;

/**
 * Created by luos on 2017/5/18.
 */
public interface SmsStatisticsDayMapper {

	Integer selectTodayTotalByQuery(SmsSendQuery query) throws SQLException;

	List<SmsDailyStatistics> selectTodayListByQuery(SmsSendQuery query) throws SQLException;

	SmsDailyStatistics statTodaySmsDailyTotal(SmsSendQuery query) throws SQLException;

	Integer selectTodayViewTotalByQuery(SmsSendQuery query) throws SQLException;

	List<SmsDailyStatistics> selectTodayViewListByQuery(SmsSendQuery query) throws SQLException;

}
