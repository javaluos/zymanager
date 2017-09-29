package com.zy.cms.mapper.master;

import java.util.List;
import java.util.Map;

import com.zy.cms.vo.MerchantAccount;

public interface MerchantAccountMapper {

	int deleteByPrimaryKey(String merchantAccount);

	int insert(MerchantAccount record);

	int insertSelective(MerchantAccount record);

	MerchantAccount selectByPrimaryKey(String merchantAccount);

	int updateByPrimaryKeySelective(MerchantAccount record);

	int updateByPrimaryKey(MerchantAccount record);

	int updateFeeExpireTimeByPrimaryKey(MerchantAccount record);

	int smsCutPayByAccountNm(String merchantAccount);

	MerchantAccount getAccount(Map param);

	/**
	 * 通过appKey查询账户信息
	 * 
	 * @param appKey
	 * @return
	 */
	MerchantAccount selectByAppKey(String appKey);

	List<MerchantAccount> selectExpireAccount(Map param);

	List<MerchantAccount> selectALLAccount();
}
