package com.zy.cms.mapper.manager;

import java.util.List;

import com.zy.cms.vo.manager.CdrMonitorStat;
import com.zy.cms.vo.query.ChartQuery;

/**
 * 话单监控图表业务
 * 
 * @author allen.yuan
 * 
 * @date 2016-11-25
 */
public interface CdrMonitorChartMapper {

	/**
	 * 查询全局的统计数据
	 * 
	 * @param charQuery
	 * @return
	 * @throws Exception
	 */
	List<CdrMonitorStat> queryMonitorStatForGlobal(ChartQuery charQuery) throws Exception;

	/**
	 * 查询客户级统计数据
	 * 
	 * @param charQuery
	 * @return
	 * @throws Exception
	 */
	List<CdrMonitorStat> queryMonitorStatForAccount(ChartQuery charQuery) throws Exception;

	List<CdrMonitorStat> queryMonitorStatForChannel(ChartQuery chartQuery) throws Exception;

    List<CdrMonitorStat> queryMainMonitorStaForChannel(ChartQuery chartQuery) throws Exception;
}