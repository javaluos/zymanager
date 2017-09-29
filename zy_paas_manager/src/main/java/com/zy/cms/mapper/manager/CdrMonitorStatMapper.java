package com.zy.cms.mapper.manager;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zy.cms.vo.manager.CdrMonitorStat;

public interface CdrMonitorStatMapper {

	/**
	 * 查询列表
	 * @param page 分页信息对象
	 * @return 
	 * @throws Exception
	 */
	public List<CdrMonitorStat> queryCdrMonitorStatList() throws Exception;
	
	/**
	 * 根据实体对象查询列表
	 * @param cdrMonitorStat 实体对象
	 * @return 
	 * @throws Exception
	 */
	public List<CdrMonitorStat> queryCdrMonitorStatListByEntity(CdrMonitorStat cdrMonitorStat) throws Exception;
	
	/**
	 * 根据实体对象查询符合条件的记录数
	 * @param cdrMonitorStat 实体对象
	 * @return 
	 * @throws Exception
	 */
	public int queryCdrMonitorStatCountByEntity(CdrMonitorStat cdrMonitorStat) throws Exception;
	
	/**
	 * 根据主键ID获取对象信息
	 * @param cdrMonitorStat 实体对象
	 * @return 
	 * @throws Exception
	 */
	public CdrMonitorStat findCdrMonitorStat(CdrMonitorStat cdrMonitorStat) throws Exception;
	
	/**
     * 新增
     * @param cdrMonitorStat 实体对象
     * @throws Exception
     */
    public void insertCdrMonitorStat(CdrMonitorStat cdrMonitorStat) throws Exception;
    
    /**
     * 修改
     * @param cdrMonitorStat 实体对象
     * @throws Exception
     */
    public void updateCdrMonitorStat(CdrMonitorStat cdrMonitorStat) throws Exception;
    
    /**
     * 删除
     * @param cdrMonitorStat 实体对象
     * @throws Exception
     */
    public void deleteCdrMonitorStat(CdrMonitorStat cdrMonitorStat) throws Exception;
    
    
    //批量插入
    public	Integer batchInsert(List<Map> params);

    /**
     * 获取时段内的实时监控记录
     * @param stime
     * @param etime
     * @return
     */
    public List<CdrMonitorStat> getAllStatInPeriod(@Param("stime")Date stime,@Param("etime")Date etime);
    
}