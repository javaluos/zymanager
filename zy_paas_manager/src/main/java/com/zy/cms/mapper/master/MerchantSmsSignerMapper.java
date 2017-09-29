package com.zy.cms.mapper.master;

import java.util.List;
import java.util.Map;
import com.zy.cms.vo.MerchantSmsSigner;
import com.zy.cms.vo.MerchantSmsSignerVo;
import com.zy.cms.vo.query.MerchantSmsSignerQuery;


public interface MerchantSmsSignerMapper {
	
	int deleteByPrimaryKey(Integer id);

	Integer insert(MerchantSmsSigner record);

	int insertSelective(MerchantSmsSigner record);

	MerchantSmsSigner selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(MerchantSmsSigner record);

	int updateByPrimaryKey(MerchantSmsSignerVo record);

	List<MerchantSmsSigner> getMerchantSmsSigners(Map params);

	Integer getCounts(Map params);

	MerchantSmsSigner getMerchantSmsSigner(Map params);

	List<MerchantSmsSigner> getCheckFailMerchantSmsSigners();
	
	List<MerchantSmsSigner> getMerchantSmsSigners(MerchantSmsSigner merchantSmsSigner);
	
    List<MerchantSmsSigner> queryListByEntity(MerchantSmsSignerQuery merchantSmsSignerQuery) throws Exception;
	
	int queryCountByEntity(MerchantSmsSignerQuery merchantSmsSignerQuery) throws Exception;
	
	MerchantSmsSigner findMerchantSmsSigner(MerchantSmsSignerQuery merchantSmsSignerQuery) throws Exception;
	
	MerchantSmsSignerVo findMerchantSmsSignerVo(MerchantSmsSignerQuery merchantSmsSignerQuery) throws Exception;
	
}