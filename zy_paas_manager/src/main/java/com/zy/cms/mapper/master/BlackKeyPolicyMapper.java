package com.zy.cms.mapper.master;

import java.util.List;
import java.util.Map;
import com.zy.cms.vo.BlackKeyListInfo;
import com.zy.cms.vo.BlackKeyPolicy;

public interface BlackKeyPolicyMapper {
	
	int deleteByPrimaryKey(Integer id);
	
	int insertSelective(BlackKeyPolicy record);
	
	int updateByPrimaryKey(BlackKeyPolicy record);
	
	BlackKeyPolicy getInfoByPolicyName(String policyName);
	
	List<BlackKeyPolicy> selectListByQuery(Map params);
	
	int queryCountByQuery(Map params);
	
	public List<BlackKeyPolicy> selectListAll() throws Exception;
	
	BlackKeyPolicy getInfoById(Integer id);
}