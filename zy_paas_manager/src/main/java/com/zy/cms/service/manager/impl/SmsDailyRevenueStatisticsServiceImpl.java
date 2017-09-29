package com.zy.cms.service.manager.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.mapper.manager.SmsDailyRevenueStatisticsMapper;
import com.zy.cms.service.manager.SmsDailyRevenueStatisticsService;
import com.zy.cms.vo.SmsDailyRevenueStatistics;
import com.zy.cms.vo.manager.RevenueResult;
import com.zy.cms.vo.query.SmsDailyRevenueStatisticsQuery;

/**
 * 营收分析
 * @author JasonXu
 *
 */
@Service("smsDailyRevenueStatisticsService")
@Transactional
public class SmsDailyRevenueStatisticsServiceImpl implements SmsDailyRevenueStatisticsService {
	
	private static final ZyLogger logger = ZyLogger.getLogger(SmsDailyRevenueStatisticsServiceImpl.class);

	@Autowired
	private SmsDailyRevenueStatisticsMapper smsDailyRevenueStatisticsMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return smsDailyRevenueStatisticsMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(SmsDailyRevenueStatistics record) {
		return smsDailyRevenueStatisticsMapper.insert(record);
	}

	@Override
	public int insertSelective(SmsDailyRevenueStatistics record) {
		return smsDailyRevenueStatisticsMapper.insertSelective(record);
	}

	@Override
	public SmsDailyRevenueStatistics selectByPrimaryKey(Long id) {
		return smsDailyRevenueStatisticsMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SmsDailyRevenueStatistics record) {
		return smsDailyRevenueStatisticsMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SmsDailyRevenueStatistics record) {
		return smsDailyRevenueStatisticsMapper.updateByPrimaryKey(record);
	}

	@Override
	public int listAccountRevenueCount(SmsDailyRevenueStatisticsQuery query) {
		return smsDailyRevenueStatisticsMapper.listAccountRevenueCount(query);
	}

	@Override
	public List<SmsDailyRevenueStatistics> listAccountRevenue(SmsDailyRevenueStatisticsQuery query) {
		return smsDailyRevenueStatisticsMapper.listAccountRevenue(query);
	}

	@Override
	public int listChannelRevenueCount(SmsDailyRevenueStatisticsQuery query) {
		return smsDailyRevenueStatisticsMapper.listChannelRevenueCount(query);
	}

	@Override
	public List<SmsDailyRevenueStatistics> listChannelRevenue(SmsDailyRevenueStatisticsQuery query) {
		return smsDailyRevenueStatisticsMapper.listChannelRevenue(query);
	}

	@Override
	public List<SmsDailyRevenueStatistics> listSummaryRevenue(SmsDailyRevenueStatisticsQuery query) {
		return smsDailyRevenueStatisticsMapper.listSummaryRevenue(query);
	}

	@Override
	public int listSummaryRevenueCount(SmsDailyRevenueStatisticsQuery query) {
		return smsDailyRevenueStatisticsMapper.listSummaryRevenueCount(query);
	}

	@Override
	public List<SmsDailyRevenueStatistics> listRevenue(SmsDailyRevenueStatisticsQuery query) {
		return smsDailyRevenueStatisticsMapper.listRevenue(query);
	}

	@Override
	public int listRevenueCount(SmsDailyRevenueStatisticsQuery query) {
		return smsDailyRevenueStatisticsMapper.listRevenueCount(query);
	}

	@Override
	public RevenueResult getRevenueResult(SmsDailyRevenueStatisticsQuery query) {
		return smsDailyRevenueStatisticsMapper.getRevenueResult(query);
	}

	
}
