package com.zy.cms.service.manager;
import java.util.List;
import com.zy.cms.vo.manager.CdrDailyStatistics;
import com.zy.cms.vo.manager.CdrStatisticsResult;
import com.zy.cms.vo.query.CdrDailyStatisticsQuery;

public interface CdrDailyStatisticsService {
   
	/**
	 * 根据实体对象查询列表
	 * @param voiceUpload 实体对象
	 * @return 
	 * @throws Exception
	 */
	public List<CdrDailyStatistics> queryCdrDailyStatisticsListByEntity(CdrDailyStatisticsQuery cdrDailyStatisticsQuery) throws Exception;
	
	/**
	 * 根据实体对象查询符合条件的记录数
	 * @param voiceUpload 实体对象
	 * @return 
	 * @throws Exception
	 */
	public CdrStatisticsResult queryVoiceUploadCountByEntity(CdrDailyStatisticsQuery cdrDailyStatisticsQuery) throws Exception;
	
	/**
	 * 根据主键ID获取对象信息
	 * @param voiceUpload 实体对象
	 * @return 
	 * @throws Exception
	 */
	public CdrDailyStatistics findVoiceUpload(CdrDailyStatisticsQuery cdrDailyStatisticsQuery) throws Exception;
	
	/**
	 * 根据实体对象查询符合条件的回拨记录数
	 * @param voiceUpload 实体对象
	 * @return 
	 * @throws Exception
	 */
	public CdrStatisticsResult queryCdrDailyStatisticsCountABByEntity(CdrDailyStatisticsQuery cdrDailyStatisticsQuery) throws Exception;
	
}
