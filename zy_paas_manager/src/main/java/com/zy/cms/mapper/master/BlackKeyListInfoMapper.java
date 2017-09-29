package com.zy.cms.mapper.master;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zy.cms.vo.BlackKeyListInfo;

public interface BlackKeyListInfoMapper {

	int deleteByPrimaryKey(Integer id);
	int insertSelective(BlackKeyListInfo record);
	int updateByPrimaryKey(BlackKeyListInfo record);
	BlackKeyListInfo getInfoByid(Integer id);
	BlackKeyListInfo getInfoByblack_key(@Param("black_key") String black_key,@Param("policyId") String policyId);
	List<BlackKeyListInfo> selectListByQuery(Map params);
	int queryCountByQuery(Map params);
	int deleteByPolicyId(int policyId) throws Exception;

}
