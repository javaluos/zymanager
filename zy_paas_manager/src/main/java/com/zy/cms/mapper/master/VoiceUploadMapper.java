package com.zy.cms.mapper.master;

import java.util.List;
import java.util.Map;
import com.zy.cms.vo.VoiceUpload;
import com.zy.cms.vo.query.VoiceUploadQuery;


public interface VoiceUploadMapper {

	/**
	 * 查询列表
	 * @param page 分页信息对象
	 * @return 
	 * @throws Exception
	 */
	public List<VoiceUpload> queryVoiceUploadList(Map map) throws Exception;
	
	/**
	 * 根据实体对象查询列表
	 * @param voiceUpload 实体对象
	 * @return 
	 * @throws Exception
	 */
	public List<VoiceUpload> queryVoiceUploadListByEntity(VoiceUploadQuery voiceUpload) throws Exception;
	
	/**
	 * 根据实体对象查询符合条件的记录数
	 * @param voiceUpload 实体对象
	 * @return 
	 * @throws Exception
	 */
	public int queryVoiceUploadCountByEntity(VoiceUploadQuery voiceUpload) throws Exception;
	
	/**
	 * 根据主键ID获取对象信息
	 * @param voiceUpload 实体对象
	 * @return 
	 * @throws Exception
	 */
	public VoiceUpload findVoiceUpload(VoiceUploadQuery voiceUpload) throws Exception;
	
	/**
     * 新增
     * @param voiceUpload 实体对象
     * @throws Exception
     */
    public void insertVoiceUpload(VoiceUploadQuery voiceUpload) throws Exception;
    
    /**
     * 修改
     * @param voiceUpload 实体对象
     * @throws Exception
     */
    public void updateVoiceUpload(VoiceUpload voiceUpload) throws Exception;
    
    /**
     * 删除
     * @param voiceUpload 实体对象
     * @throws Exception
     */
    public void deleteVoiceUpload(VoiceUploadQuery voiceUpload) throws Exception;
    
    Map<String,String> selectUrlByFileId(Map<String,String> map);
        
}