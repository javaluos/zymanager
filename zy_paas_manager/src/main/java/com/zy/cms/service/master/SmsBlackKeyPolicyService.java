package com.zy.cms.service.master;

import java.util.List;
import java.util.Map;
import com.zy.cms.vo.BlackKeyListInfo;
import com.zy.cms.vo.BlackKeyPolicy;

/**
 * 短信黑名单列表
 * @author JasonXu
 *
 */
public interface SmsBlackKeyPolicyService {

    int deleteByPrimaryKey(Integer id) throws Exception;
	
	int insertSelective(BlackKeyPolicy record) throws Exception;
	
	int updateByPrimaryKey(BlackKeyPolicy record) throws Exception;
	
	BlackKeyPolicy getInfoByPolicyName(String policyName) throws Exception;
	
	List<BlackKeyPolicy> selectListByQuery(Map params) throws Exception;
	
	int queryCountByQuery(Map params) throws Exception;
	
	public List<BlackKeyPolicy> selectListAll() throws Exception;
	
	public BlackKeyPolicy getInfoById(Integer id) throws Exception;
	    
}
