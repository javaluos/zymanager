package com.zy.cms.mapper.master;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zy.cms.vo.MerchantAccountAuth;
import com.zy.cms.vo.query.MerchantAccountAuthQuery;

public interface MerchantAccountAuthMapper {
	int deleteByPrimaryKey(Long id);

	int insert(MerchantAccountAuth record);

	int insertSelective(MerchantAccountAuth record);

	MerchantAccountAuth selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(MerchantAccountAuth record);

	int updateByPrimaryKeyWithBLOBs(MerchantAccountAuth record);

	int updateByPrimaryKey(MerchantAccountAuth record);

	int updateStatusByApiAccount(@Param("authStatus") Integer authStatus, @Param("apiAccount") String apiAccount);

	List<MerchantAccountAuth> selectByApiAccount(String apiAccount);

	int queryMerchantAccountAuthByEntity(MerchantAccountAuthQuery voiceMerchantAccountAuth);

	List<MerchantAccountAuth> queryMerchantAccountAuthListByEntity(MerchantAccountAuthQuery voiceMerchantAccountAuth);

	public MerchantAccountAuth getMerchantAccountAuthById(Long id);
}