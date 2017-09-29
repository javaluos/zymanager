package com.zy.cms.mapper.manager;

import java.util.List;

import com.zy.cms.vo.VoiceMerchantBalanceMonitor;


public interface VoiceMerchantBalanceMonitorMapper {

	/**
	 * 查询列表
	 * @param page 分页信息对象
	 * @return 
	 * @throws Exception
	 */
	public List<VoiceMerchantBalanceMonitor> queryVoiceMerchantBalanceMonitorList(VoiceMerchantBalanceMonitor voiceMerchantBalanceMonitor) throws Exception;
	
	/**
	 * 根据实体对象查询列表
	 * @param voiceMerchantBalanceMonitor 实体对象
	 * @return 
	 * @throws Exception
	 */
	public List<VoiceMerchantBalanceMonitor> queryVoiceMerchantBalanceMonitorListByEntity(VoiceMerchantBalanceMonitor voiceMerchantBalanceMonitor) throws Exception;
	
	
	/**
	 * 根据实体对象查询符合条件的记录数
	 * @param voiceMerchantBalanceMonitor 实体对象
	 * @return 
	 * @throws Exception
	 */
	public int queryVoiceMerchantBalanceMonitorCountByEntity(VoiceMerchantBalanceMonitor voiceMerchantBalanceMonitor) throws Exception;
	
	/**
	 * 根据主键ID获取对象信息
	 * @param voiceMerchantBalanceMonitor 实体对象
	 * @return 
	 * @throws Exception
	 */
	public VoiceMerchantBalanceMonitor findVoiceMerchantBalanceMonitor(VoiceMerchantBalanceMonitor voiceMerchantBalanceMonitor) throws Exception;
	
	
	public VoiceMerchantBalanceMonitor findVoiceMerchantBalanceMonitorSelf(VoiceMerchantBalanceMonitor voiceMerchantBalanceMonitor) throws Exception;
	
	/**
     * 新增
     * @param voiceMerchantBalanceMonitor 实体对象
     * @throws Exception
     */
    public void insertVoiceMerchantBalanceMonitor(VoiceMerchantBalanceMonitor voiceMerchantBalanceMonitor) throws Exception;
    
    /**
     * 修改
     * @param voiceMerchantBalanceMonitor 实体对象
     * @throws Exception
     */
    public void updateVoiceMerchantBalanceMonitor(VoiceMerchantBalanceMonitor voiceMerchantBalanceMonitor) throws Exception;
    
    /**
     * 删除
     * @param voiceMerchantBalanceMonitor 实体对象
     * @throws Exception
     */
    public void deleteVoiceMerchantBalanceMonitor(VoiceMerchantBalanceMonitor voiceMerchantBalanceMonitor) throws Exception;

	public void updateVoiceMerchantBalanceMonitorStatu(VoiceMerchantBalanceMonitor vbm)throws Exception;
	
	public void updateNeedNoticeBalanceMonitor() throws Exception;
			
        
}