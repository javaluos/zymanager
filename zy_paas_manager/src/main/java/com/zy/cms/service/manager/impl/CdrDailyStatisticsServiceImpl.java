package com.zy.cms.service.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zy.cms.common.ZyLogger;
import com.zy.cms.mapper.manager.CdrDailyStatisticsMapper;
import com.zy.cms.service.manager.CdrDailyStatisticsService;
import com.zy.cms.vo.manager.CdrDailyStatistics;
import com.zy.cms.vo.manager.CdrStatisticsResult;
import com.zy.cms.vo.query.CdrDailyStatisticsQuery;

@Service("cdrDailyStatisticsService")
@Transactional
public class CdrDailyStatisticsServiceImpl implements CdrDailyStatisticsService {
	private static final ZyLogger logger = ZyLogger.getLogger(CdrDailyStatisticsServiceImpl.class);

	@Autowired
	private CdrDailyStatisticsMapper cdrDailyStatisticsMapper;

	@Override
	public List<CdrDailyStatistics> queryCdrDailyStatisticsListByEntity(CdrDailyStatisticsQuery cdrDailyStatisticsQuery)
			throws Exception {
		// TODO Auto-generated method stub
		return cdrDailyStatisticsMapper.queryCdrDailyStatisticsListByEntity(cdrDailyStatisticsQuery);
	}

	@Override
	public CdrStatisticsResult queryVoiceUploadCountByEntity(CdrDailyStatisticsQuery cdrDailyStatisticsQuery)
			throws Exception {
		// TODO Auto-generated method stub
		return cdrDailyStatisticsMapper.queryCdrDailyStatisticsCountByEntity(cdrDailyStatisticsQuery);
	}

	@Override
	public CdrDailyStatistics findVoiceUpload(CdrDailyStatisticsQuery cdrDailyStatisticsQuery) throws Exception {
		// TODO Auto-generated method stub
		return cdrDailyStatisticsMapper.findCdrDailyStatistics(cdrDailyStatisticsQuery);
	}

	@Override
	public CdrStatisticsResult queryCdrDailyStatisticsCountABByEntity(CdrDailyStatisticsQuery cdrDailyStatisticsQuery)
			throws Exception {
		return cdrDailyStatisticsMapper.queryCdrDailyStatisticsCountABByEntity(cdrDailyStatisticsQuery);
	}

}
