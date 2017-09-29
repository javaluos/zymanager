package com.zy.cms.mapper.master;

import java.util.List;
import java.util.Map;

import com.zy.cms.vo.VoiceMerchantAttr;

/**
 * 账号属性
 * @author JasonXu
 *
 */
public interface VoiceMerchantAttrMapper {
	
    int deleteByPrimaryKey(Long id) throws Exception;

    int insert(VoiceMerchantAttr record) throws Exception;

    int insertSelective(VoiceMerchantAttr record) throws Exception;

    VoiceMerchantAttr selectByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(VoiceMerchantAttr record) throws Exception;

    int updateByPrimaryKey(VoiceMerchantAttr record) throws Exception;
    
    List<VoiceMerchantAttr> selectByQuery(Map map) throws Exception;
    
    int batchInsert(List<VoiceMerchantAttr> list) throws Exception;
    
    void batchDelete(String apiAccount) throws Exception;
}