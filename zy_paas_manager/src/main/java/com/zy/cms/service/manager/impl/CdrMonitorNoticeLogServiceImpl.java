package com.zy.cms.service.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zy.cms.common.ZyLogger;
import com.zy.cms.mapper.manager.CdrDailyStatisticsMapper;
import com.zy.cms.mapper.manager.CdrMonitorNoticeLogMapper;
import com.zy.cms.service.manager.CdrDailyStatisticsService;
import com.zy.cms.service.manager.CdrMonitorNoticeLogService;
import com.zy.cms.vo.manager.CdrDailyStatistics;
import com.zy.cms.vo.manager.CdrMonitorNoticeLog;
import com.zy.cms.vo.manager.CdrStatisticsResult;
import com.zy.cms.vo.query.CdrDailyStatisticsQuery;
import com.zy.cms.vo.query.CdrMonitorNoticeLogQuery;

@Service("cdrMonitorNoticeLogService")
@Transactional
public class CdrMonitorNoticeLogServiceImpl implements CdrMonitorNoticeLogService {
	
	private static final ZyLogger logger = ZyLogger.getLogger(CdrMonitorNoticeLogServiceImpl.class);

	@Autowired
	private CdrMonitorNoticeLogMapper cdrMonitorNoticeLogMapper;

	@Override
	public List<CdrMonitorNoticeLog> queryCdrMonitorLogByEntity(CdrMonitorNoticeLogQuery cdrMonitorNoticeLogQuery)
			throws Exception {
		return cdrMonitorNoticeLogMapper.queryCdrMonitorLogByEntity(cdrMonitorNoticeLogQuery);
	}

	@Override
	public int queryCdrMonitorLogCount(CdrMonitorNoticeLogQuery cdrMonitorNoticeLogQuery) throws Exception {
		return cdrMonitorNoticeLogMapper.queryCdrMonitorLogCount(cdrMonitorNoticeLogQuery);
	}

	@Override
	public int updateLog(String id,String dealUser) throws Exception {
		return cdrMonitorNoticeLogMapper.updateLog(id,dealUser);
	}

	@Override
	public int updateAll(String dealUser) throws Exception {
		return cdrMonitorNoticeLogMapper.updateAll(dealUser);
	}

	
}
