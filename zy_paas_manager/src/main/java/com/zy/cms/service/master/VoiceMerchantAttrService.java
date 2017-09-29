package com.zy.cms.service.master;

import java.util.List;
import java.util.Map;

import com.zy.cms.vo.CmppAccount;
import com.zy.cms.vo.MerchantAccount;
import com.zy.cms.vo.VoiceMerchantAttr;

/**
 * 账号属性
 * @author JasonXu
 *
 */
public interface VoiceMerchantAttrService {
	
	int deleteByPrimaryKey(Long id) throws Exception;

    int insert(VoiceMerchantAttr record) throws Exception;
    
    int batchInsert(List<VoiceMerchantAttr> voiceMerchantAttrs) throws Exception;

    int insertSelective(VoiceMerchantAttr record) throws Exception;

    VoiceMerchantAttr selectByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(VoiceMerchantAttr record) throws Exception;

    int updateByPrimaryKey(VoiceMerchantAttr record) throws Exception;
    
    List<VoiceMerchantAttr> selectByQuery(Map map) throws Exception;
    
    List<VoiceMerchantAttr> getVoiceMerchantAttrs(VoiceMerchantAttr voiceMerchantAttr) throws Exception;
    
    VoiceMerchantAttr getVoiceMerchantAttr(List<VoiceMerchantAttr> voiceMerchantAttrs,VoiceMerchantAttr voiceMerchantAttr) throws Exception;
    
    /**
     * 保存账号属性和更新账户信息
     * @param voiceMerchantAttrs
     * @param merchantAccount
     * @return
     * @throws Exception
     */
    boolean saveVoiceMerchantAttrs(List<VoiceMerchantAttr> voiceMerchantAttrs,MerchantAccount merchantAccount,CmppAccount cmppAccount) throws Exception;
    
    void batchDelete(String apiAccount) throws Exception;
}
