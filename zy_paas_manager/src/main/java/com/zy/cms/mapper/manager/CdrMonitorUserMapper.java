package com.zy.cms.mapper.manager;

import java.util.List;

import com.zy.cms.vo.manager.CdrMonitorUser;
import com.zy.cms.vo.query.CdrMonitorUserQuery;

public interface CdrMonitorUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CdrMonitorUser record);

    int insertSelective(CdrMonitorUser record);

    CdrMonitorUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CdrMonitorUser record);

    int updateByPrimaryKey(CdrMonitorUser record);
    
    /**
   	 * 根据实体对象查询列表
   	 * @param voiceUpload 实体对象
   	 * @return 
   	 * @throws Exception
   	 */
   	public List<CdrMonitorUser> queryMonitorUserByEntity(CdrMonitorUserQuery cdrMonitorUserQuery) throws Exception;
   	
   	
   	/**
   	 * 根据实体对象查询符合条件的记录数
   	 * @param voiceUpload 实体对象
   	 * @return 
   	 * @throws Exception
   	 */
   	public int queryMonitorUserCountByEntity(CdrMonitorUserQuery cdrMonitorUserQuery) throws Exception;
   	
   	public CdrMonitorUser findMonitorUserByAccount(String apiAccount) throws Exception;
}