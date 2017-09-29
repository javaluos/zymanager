package com.zy.cms.service.manager;

import java.sql.SQLException;
import java.util.List;

import com.zy.cms.vo.AccountBindInfo;
import com.zy.cms.vo.query.AccountBindQuery;

public interface AccountBindInfoService {

	Integer getCountByQuery(AccountBindQuery query) throws SQLException;

	List<AccountBindInfo> getListByQuery(AccountBindQuery query) throws SQLException;
	
	List<String> getApiAccountByBusName(String businessName) ;

	boolean doAccountbind(String username, String accounts) throws SQLException;

	int getAccBindCountByQuery(AccountBindQuery query) throws SQLException;

	List<AccountBindInfo> getAccBindListByQuery(AccountBindQuery query) throws SQLException;

	boolean checkBind(String username, String apiAccount) throws SQLException;

	boolean deleteByUsername(String username) throws SQLException;

	boolean addAccountbind(String username, String accounts) throws SQLException;

	List<AccountBindInfo> getByUserName(String username) throws SQLException;

}
