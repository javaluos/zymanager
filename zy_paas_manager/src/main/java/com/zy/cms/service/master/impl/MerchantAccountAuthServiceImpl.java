package com.zy.cms.service.master.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zy.cms.enums.AuthStatusEnum;
import com.zy.cms.mapper.master.MerchantAccountAuthMapper;
import com.zy.cms.service.master.MerchantAccountAuthService;
import com.zy.cms.vo.MerchantAccountAuth;
import com.zy.cms.vo.query.MerchantAccountAuthQuery;

@Service("MerchantAccountAuthService")
public class MerchantAccountAuthServiceImpl implements MerchantAccountAuthService {
	
	@Autowired
	private MerchantAccountAuthMapper mapper;

	@Override
	public MerchantAccountAuth getByApiAccount(String apiAccount) {
		MerchantAccountAuth result = null;
		List<MerchantAccountAuth> accAuthList = mapper.selectByApiAccount(apiAccount);
		if(null != accAuthList && accAuthList.size() > 0){
			return accAuthList.get(0);
		}
		return result;
	}
	
    public int queryMerchantAccountAuthByEntity(MerchantAccountAuthQuery voiceMerchantAccountAuth){
    	return  mapper.queryMerchantAccountAuthByEntity(voiceMerchantAccountAuth);
    }
	
	public List<MerchantAccountAuth> queryMerchantAccountAuthListByEntity(MerchantAccountAuthQuery voiceMerchantAccountAuth){
		return mapper.queryMerchantAccountAuthListByEntity(voiceMerchantAccountAuth);
	}

	@Override
	public boolean doAuthentication(String userName, MerchantAccountAuth accAuth, String flag) {
		if("0".equals(flag)){
			accAuth.setAuthStatus(AuthStatusEnum.FAILED.getType());
		}else if("1".equals(flag)){
			accAuth.setAuthStatus(AuthStatusEnum.AUTHED.getType());
		}
		accAuth.setUpdateTime(new Date());
		accAuth.setAuthResultTime(new Date());
		accAuth.setAuthUser("admin");
		return mapper.updateByPrimaryKeySelective(accAuth) > 0;
	}

	@Override
	public MerchantAccountAuth getMerchantAccountAuthById(Long id) {
		return mapper.getMerchantAccountAuthById(id);
	}

	@Override
	public void updateByPrimaryKeySelective(MerchantAccountAuth voiceMerchantAccountAuth) {
		this.mapper.updateByPrimaryKeySelective(voiceMerchantAccountAuth);
	}

	
	
}
