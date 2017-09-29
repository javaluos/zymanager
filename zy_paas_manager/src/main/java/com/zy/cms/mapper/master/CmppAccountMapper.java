package com.zy.cms.mapper.master;

import java.util.List;

import com.zy.cms.vo.CmppAccount;


public interface CmppAccountMapper {

	public List<CmppAccount> queryCmppAccountList() throws Exception;
	
	/**
	 * 根据实体对象查询列表
	 * @param cmppAccount 实体对象
	 * @return 
	 * @throws Exception
	 */
	public List<CmppAccount> queryCmppAccountListByEntity(CmppAccount cmppAccount) throws Exception;
	
	/**
	 * 根据实体对象查询符合条件的记录数
	 * @param cmppAccount 实体对象
	 * @return 
	 * @throws Exception
	 */
	public int queryCmppAccountCountByEntity(CmppAccount cmppAccount) throws Exception;
	
	/**
	 * 根据主键ID获取对象信息
	 * @param cmppAccount 实体对象
	 * @return 
	 * @throws Exception
	 */
	public CmppAccount findCmppAccount(CmppAccount cmppAccount) throws Exception;
	
	/**
     * 新增
     * @param cmppAccount 实体对象
     * @throws Exception
     */
    public void insertCmppAccount(CmppAccount cmppAccount) throws Exception;
    
    /**
     * 修改
     * @param cmppAccount 实体对象
     * @throws Exception
     */
    public int updateCmppAccount(CmppAccount cmppAccount) throws Exception;
    
    /**
     * 删除
     * @param cmppAccount 实体对象
     * @throws Exception
     */
    public void deleteCmppAccount(CmppAccount cmppAccount) throws Exception;
    
    /**
	 * 根据apiAccount获取对象信息
	 * @param cmppAccount 实体对象
	 * @return 
	 * @throws Exception
	 */
	public CmppAccount findCmppByAccount(String  apiAccount) throws Exception;
	
	public CmppAccount findCmppByClientId(String  clientId) throws Exception;
}