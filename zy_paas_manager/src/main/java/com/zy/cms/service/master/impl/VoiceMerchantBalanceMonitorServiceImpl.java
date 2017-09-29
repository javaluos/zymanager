package com.zy.cms.service.master.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zy.cms.mapper.manager.VoiceMerchantBalanceMonitorMapper;
import com.zy.cms.service.master.VoiceMerchantBalanceMonitorService;
import com.zy.cms.vo.VoiceMerchantBalanceMonitor;


@Service("voiceMerchantBalanceMonitorService")
public class VoiceMerchantBalanceMonitorServiceImpl implements VoiceMerchantBalanceMonitorService {

	/**
	 * VoiceMerchantBalanceMonitor数据库访问接口
	 */
	@Autowired
	private VoiceMerchantBalanceMonitorMapper voiceMerchantBalanceMonitorDao;
	
	/**
	 * 查询列表
	 * @param page 分页信息对象
	 * @return 
	 * @throws Exception
	 */
	public List<VoiceMerchantBalanceMonitor> queryVoiceMerchantBalanceMonitorList(VoiceMerchantBalanceMonitor voiceMerchantBalanceMonitor) throws Exception {
		return voiceMerchantBalanceMonitorDao.queryVoiceMerchantBalanceMonitorList(voiceMerchantBalanceMonitor);
	}
	
	/**
	 * 根据实体对象查询列表
	 * @param voiceMerchantBalanceMonitor 实体对象
	 * @return 
	 * @throws Exception
	 */
	public List<VoiceMerchantBalanceMonitor> queryVoiceMerchantBalanceMonitorListByEntity(VoiceMerchantBalanceMonitor voiceMerchantBalanceMonitor) throws Exception {
		return voiceMerchantBalanceMonitorDao.queryVoiceMerchantBalanceMonitorListByEntity(voiceMerchantBalanceMonitor);
	}
	
	/**
	 * 根据实体对象查询符合条件的记录数
	 * @param voiceMerchantBalanceMonitor 实体对象
	 * @return 
	 * @throws Exception
	 */
	public int queryVoiceMerchantBalanceMonitorCountByEntity(VoiceMerchantBalanceMonitor voiceMerchantBalanceMonitor) throws Exception {
		return voiceMerchantBalanceMonitorDao.queryVoiceMerchantBalanceMonitorCountByEntity(voiceMerchantBalanceMonitor);
	}
	
	/**
	 * 根据主键ID获取对象信息
	 * @param voiceMerchantBalanceMonitor 实体对象
	 * @return 
	 * @throws Exception
	 */
	public VoiceMerchantBalanceMonitor findVoiceMerchantBalanceMonitor(VoiceMerchantBalanceMonitor voiceMerchantBalanceMonitor) throws Exception {
		return voiceMerchantBalanceMonitorDao.findVoiceMerchantBalanceMonitor(voiceMerchantBalanceMonitor);
	}
	
	public VoiceMerchantBalanceMonitor findVoiceMerchantBalanceMonitorSelf(VoiceMerchantBalanceMonitor voiceMerchantBalanceMonitor) throws Exception {
		return voiceMerchantBalanceMonitorDao.findVoiceMerchantBalanceMonitorSelf(voiceMerchantBalanceMonitor);
	}
	
	/**
     * 新增
     * @param voiceMerchantBalanceMonitor 实体对象
     * @throws Exception
     */
    public void insertVoiceMerchantBalanceMonitor(VoiceMerchantBalanceMonitor voiceMerchantBalanceMonitor) throws Exception {
    	voiceMerchantBalanceMonitorDao.insertVoiceMerchantBalanceMonitor(voiceMerchantBalanceMonitor);
    }
    
    /**
     * 修改
     * @param voiceMerchantBalanceMonitor 实体对象
     * @throws Exception
     */
    public void updateVoiceMerchantBalanceMonitor(VoiceMerchantBalanceMonitor voiceMerchantBalanceMonitor) throws Exception {
    	voiceMerchantBalanceMonitorDao.updateVoiceMerchantBalanceMonitor(voiceMerchantBalanceMonitor);
    }
    
    /**
     * 删除
     * @param voiceMerchantBalanceMonitor 实体对象
     * @throws Exception
     */
    public void deleteVoiceMerchantBalanceMonitor(VoiceMerchantBalanceMonitor voiceMerchantBalanceMonitor) throws Exception {
    	voiceMerchantBalanceMonitorDao.deleteVoiceMerchantBalanceMonitor(voiceMerchantBalanceMonitor);
    }
}
