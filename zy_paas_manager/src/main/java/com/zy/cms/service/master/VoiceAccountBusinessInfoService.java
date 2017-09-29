/*
 * Copyright Guoling.com All right reserved. This software is the confidential and proprietary information of
 * Guoling.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Guoling.com.
 */
package com.zy.cms.service.master;

import java.util.List;
import java.util.Map;

import com.zy.cms.vo.VoiceAccountBusinessInfo;
import com.zy.cms.vo.query.VoiceAccountBusinessInfoQuery;

/**
 * 类VoiceAccountBusinessInfoService.java的实现描述：TODO 类实现描述
 * @author ddp1j32 2015-6-11 下午2:38:01
 */
public interface VoiceAccountBusinessInfoService {

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
	
    /*//**
	 * 根据apiAccount和业务id查询资费数据
	 * @param voiceAccountBusinessInfoQuery
	 * @return
	 * @throws Exception
	 *//*
	public VoiceAccountBusinessInfo queryVoiceAccountBusinessInfoByAccountID(Map accountId) throws Exception;*/
	
	/**
	 * 根据apiAccount查询资费数据
	 * @param voiceAccountBusinessInfoQuery
	 * @return
	 * @throws Exception
	 */
	public List<VoiceAccountBusinessInfo> queryVoiceAccountBusinessInfoByAccount(String apiAccount) throws Exception;
	
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
	 * 批量更新应该所对应的资费
	 * @return
	 * @throws Exception
	 */
	public void batchUpdate(List<VoiceAccountBusinessInfo> voiceAccountBusinessInfos,VoiceAccountBusinessInfoQuery voiceAccountBusinessInfoQuery) throws Exception;
}
