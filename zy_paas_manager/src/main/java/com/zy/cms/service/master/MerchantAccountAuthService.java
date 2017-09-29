package com.zy.cms.service.master;

import java.util.List;

import com.zy.cms.vo.MerchantAccountAuth;
import com.zy.cms.vo.query.MerchantAccountAuthQuery;


public interface MerchantAccountAuthService {

	public MerchantAccountAuth getByApiAccount(String apiAccount);
	
	public int queryMerchantAccountAuthByEntity(MerchantAccountAuthQuery voiceMerchantAccountAuth);
	
	public List<MerchantAccountAuth> queryMerchantAccountAuthListByEntity(MerchantAccountAuthQuery voiceMerchantAccountAuth);

	public boolean doAuthentication(String userName, MerchantAccountAuth accAuth, String flag);

	public MerchantAccountAuth getMerchantAccountAuthById(Long id);
	
	public void updateByPrimaryKeySelective(MerchantAccountAuth voiceMerchantAccountAuth);
}
