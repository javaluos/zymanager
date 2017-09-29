package com.zy.cms.mapper.master;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zy.cms.vo.VoiceAccountBusinessInfo;
import com.zy.cms.vo.query.VoiceAccountBusinessInfoQuery;

public interface VoiceAccountBusinessInfoMapper {
   
	/**
	 * 将资费插入到表中
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public int insert(VoiceAccountBusinessInfo record) throws Exception;
	
	/**
	 * 更新资费表中的数据
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public int update(VoiceAccountBusinessInfo record) throws Exception;
    
	/**
	 * 批量将资费插入到表中
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public int batchInsert(Map record) throws Exception;
	
	/**
	 * 根据实体对象查询资费数据
	 * @param voiceAccountBusinessInfoQuery
	 * @return
	 * @throws Exception
	 */
	public List<VoiceAccountBusinessInfo> queryVoiceAccountBusinessInfoByEntity(VoiceAccountBusinessInfoQuery voiceAccountBusinessInfoQuery) throws Exception;
	
	/**
	 * 根据实体对象查询资费总记录数
	 * @param voiceAccountBusinessInfoQuery
	 * @return
	 * @throws Exception
	 */
	public int queryVoiceAccountBusinessInfoCountByEntity(VoiceAccountBusinessInfoQuery voiceAccountBusinessInfoQuery) throws Exception;
	
	/**
	 * 根据实体对象查找资费
	 * @param voiceAccountBusinessInfoQuery
	 * @return
	 * @throws Exception
	 */
	public VoiceAccountBusinessInfo findVoiceAccountBusinessInfo(VoiceAccountBusinessInfoQuery voiceAccountBusinessInfoQuery) throws Exception;
    
	/**
	 * 查找默认的资费配置
	 * @return
	 * @throws Exception
	 */
	public VoiceAccountBusinessInfo findVoiceBusinessInfo() throws Exception;
	
	/**
	 * 根据apiAccount和业务id查询资费数据
	 * @param voiceAccountBusinessInfoQuery
	 * @return
	 * @throws Exception
	 */
	public VoiceAccountBusinessInfo queryVoiceAccountBusinessInfoByAccountID(Map accountId) throws Exception;
	
	/**
	 * 根据apiAccount查询资费数据
	 * @param voiceAccountBusinessInfoQuery
	 * @return
	 * @throws Exception
	 */
	public List<VoiceAccountBusinessInfo> queryVoiceAccountBusinessInfoByAccount(String apiAccount) throws Exception;
	
	/**
	 * 批量更新资费数据
	 * @param record
	 */
	public void batchUpdate(List<VoiceAccountBusinessInfo> record);
}