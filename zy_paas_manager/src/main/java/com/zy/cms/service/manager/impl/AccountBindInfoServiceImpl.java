package com.zy.cms.service.manager.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zy.cms.mapper.manager.AccountBindInfoMapper;
import com.zy.cms.service.manager.AccountBindInfoService;
import com.zy.cms.service.manager.UserService;
import com.zy.cms.service.master.MerchantAccountService;
import com.zy.cms.vo.AccountBindInfo;
import com.zy.cms.vo.MerchantAccount;
import com.zy.cms.vo.User;
import com.zy.cms.vo.query.AccountBindQuery;

@Service("accountBindInfoService")
public class AccountBindInfoServiceImpl implements AccountBindInfoService {
	
	@Autowired
	private AccountBindInfoMapper mapper;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MerchantAccountService merchantAccountService;

	@Override
	public Integer getCountByQuery(AccountBindQuery query) throws SQLException {
		Integer result = mapper.selectCountByQuery(query);
		if(null == result){
			return 0;
		}
		return result;
	}

	@Override
	public List<AccountBindInfo> getListByQuery(AccountBindQuery query) throws SQLException {
		return mapper.selectListByQuery(query);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean doAccountbind(String username, String accounts) throws SQLException{
		boolean result = false;
		if(StringUtils.isBlank(username) || StringUtils.isBlank(accounts)){
			return false;
		}
		User user = userService.findUserByName(username);
		if(null == user){
			return false;
		}
		result = mapper.deleteByPrimaryUserName(username) >= 0;
		if(result == false){
			return false;
		}
		accounts = accounts.substring(0, accounts.length() - 1);
		String[] accountArrays = accounts.split(",");
		for(String account : accountArrays){
			MerchantAccount merchantAccount = merchantAccountService.getMerchantAccount(account);
			if(null != merchantAccount){
				AccountBindInfo accountBindInfo = new AccountBindInfo();
				accountBindInfo.setUserName(username);
				accountBindInfo.setApiAccount(merchantAccount.getApiAccount());
				accountBindInfo.setFullname(user.getFullname());
				accountBindInfo.setDepartment(user.getDepartment());
				accountBindInfo.setDeptNo(user.getDeptNo());
				accountBindInfo.setBusinessName(merchantAccount.getBusinessName());
				accountBindInfo.setMerchantPhone(merchantAccount.getMerchantPhone());
				accountBindInfo.setMerchantType(merchantAccount.getMerchantType());
				accountBindInfo.setCreateTime(new Date());
				accountBindInfo.setUpdateTime(new Date());
				result = mapper.insert(accountBindInfo) > 0;
			}
		}
		return result;
	}

	@Override
	public int getAccBindCountByQuery(AccountBindQuery query) throws SQLException {
		return mapper.selectAccBindCountByQuery(query);
	}

	@Override
	public List<AccountBindInfo> getAccBindListByQuery(AccountBindQuery query) throws SQLException {
		return mapper.selectAccBindListByQuery(query);
	}

	@Override
	public List<String> getApiAccountByBusName(String businessName) {
		return mapper.getApiAccountByBusName(businessName);
	}

	@Override
	public boolean checkBind(String username, String apiAccount) throws SQLException{
		boolean result = false;
		List<AccountBindInfo> accBindList = mapper.selectByUsernameApiAccount(username, apiAccount);
		if(null == accBindList || accBindList.size() == 0){
			result = true;
		}
		return result;
	}

	@Override
	public boolean deleteByUsername(String username) throws SQLException {
		return mapper.deleteByPrimaryUserName(username) > 0;
	}

	@Override
	public boolean addAccountbind(String username, String accounts) throws SQLException {
		boolean result = false;
		if(StringUtils.isBlank(username) || StringUtils.isBlank(accounts)){
			return false;
		}
		User user = userService.findUserByName(username);
		if(null == user){
			return false;
		}
		accounts = accounts.substring(0, accounts.length() - 1);
		String[] accountArrays = accounts.split(",");
		for(String account : accountArrays){
			List<AccountBindInfo> accBindInfo = mapper.selectByUsernameApiAccount(username, account);
			if(null != accBindInfo && accBindInfo.size() > 0){
				return false;
			}
		}
		for(String account : accountArrays){
			MerchantAccount merchantAccount = merchantAccountService.getMerchantAccount(account);
			if(null != merchantAccount){
				AccountBindInfo accountBindInfo = new AccountBindInfo();
				accountBindInfo.setUserName(username);
				accountBindInfo.setApiAccount(merchantAccount.getApiAccount());
				accountBindInfo.setFullname(user.getFullname());
				accountBindInfo.setDepartment(user.getDepartment());
				accountBindInfo.setDeptNo(user.getDeptNo());
				accountBindInfo.setBusinessName(merchantAccount.getBusinessName());
				accountBindInfo.setMerchantPhone(merchantAccount.getMerchantPhone());
				accountBindInfo.setMerchantType(merchantAccount.getMerchantType());
				accountBindInfo.setCreateTime(new Date());
				accountBindInfo.setUpdateTime(new Date());
				result = mapper.insert(accountBindInfo) > 0;
			}
		}
		return result;
	}

	@Override
	public List<AccountBindInfo> getByUserName(String username) throws SQLException {
		return mapper.selectByUserName(username);
	}

}
