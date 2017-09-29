package com.zy.cms.service.master.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zy.cms.mapper.master.VoiceUploadMapper;
import com.zy.cms.service.master.VoiceUploadService;
import com.zy.cms.vo.VoiceUpload;
import com.zy.cms.vo.query.VoiceUploadQuery;


@Service("voiceUploadService")
public class VoiceUploadServiceImpl implements VoiceUploadService {

	/**
	 * VoiceUpload数据库访问接口
	 */
	@Autowired
	private VoiceUploadMapper voiceUploadMapper;
	
	/**
	 * 查询列表
	 * @param page 分页信息对象
	 * @return 
	 * @throws Exception
	 */
	public List<VoiceUpload> queryVoiceUploadList(Map map) throws Exception {
		return voiceUploadMapper.queryVoiceUploadList(map);
	}
	
	/**
	 * 根据实体对象查询列表
	 * @param voiceUpload 实体对象
	 * @return 
	 * @throws Exception
	 */
	public List<VoiceUpload> queryVoiceUploadListByEntity(VoiceUploadQuery voiceUpload) throws Exception {
	    return voiceUploadMapper.queryVoiceUploadListByEntity(voiceUpload);
	}
	
	/**
	 * 根据实体对象查询符合条件的记录数
	 * @param voiceUpload 实体对象
	 * @return 
	 * @throws Exception
	 */
	public int queryVoiceUploadCountByEntity(VoiceUploadQuery voiceUpload) throws Exception {
		return voiceUploadMapper.queryVoiceUploadCountByEntity(voiceUpload);
	}
	
	/**
	 * 根据主键ID获取对象信息
	 * @param voiceUpload 实体对象
	 * @return 
	 * @throws Exception
	 */
	public VoiceUpload findVoiceUpload(VoiceUploadQuery voiceUpload) throws Exception {
		
        if(voiceUpload!=null && voiceUpload.getApiAccount()!=null){
        	String tableName=getTableSuffix(voiceUpload.getApiAccount());
        	voiceUpload.setTableName(tableName);
        }
		return voiceUploadMapper.findVoiceUpload(voiceUpload);
	}
	
	/**
     * 新增
     * @param voiceUpload 实体对象
     * @throws Exception
     */
    public void insertVoiceUpload(VoiceUploadQuery voiceUpload) throws Exception {
    	voiceUploadMapper.insertVoiceUpload(voiceUpload);
    }
    
    /**
     * 修改
     * @param voiceUpload 实体对象
     * @throws Exception
     */
    public void updateVoiceUpload(VoiceUpload voiceUpload) throws Exception {
    	setTableName(voiceUpload);
    	voiceUploadMapper.updateVoiceUpload(voiceUpload);
    }
    
    /**
     * 删除
     * @param voiceUpload 实体对象
     * @throws Exception
     */
    public void deleteVoiceUpload(VoiceUploadQuery voiceUpload) throws Exception {
    	 if(voiceUpload!=null && voiceUpload.getApiAccount()!=null){
         	String tableName=getTableSuffix(voiceUpload.getApiAccount());
         	voiceUpload.setTableName(tableName);
         }
    	voiceUploadMapper.deleteVoiceUpload(voiceUpload);
    }

	private void setTableName(VoiceUpload voiceUpload) {
		String tableName=getTableSuffix(voiceUpload.getApiAccount());
    	voiceUpload.setTableName(tableName);
	}
    /**
     * 20 张表
     * @return 表后缀
     */
    private String getTableSuffix(String apiAccount){
    	if(null==apiAccount||"".equals(apiAccount)){
    		return null;
    	}else{
    		int value=Math.abs(apiAccount.hashCode());
    		value=(value%tableSheets==0?tableSheets:(value%tableSheets));
    		return ""+value;
    	}
    }
    private static int tableSheets=20;
}
