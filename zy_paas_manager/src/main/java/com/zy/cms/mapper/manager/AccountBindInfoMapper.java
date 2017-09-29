package com.zy.cms.mapper.manager;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zy.cms.vo.AccountBindInfo;
import com.zy.cms.vo.query.AccountBindQuery;

public interface AccountBindInfoMapper {
    int deleteByPrimaryKey(Integer id) throws SQLException;

    int insert(AccountBindInfo record) throws SQLException;

    int insertSelective(AccountBindInfo record) throws SQLException;

    AccountBindInfo selectByPrimaryKey(Integer id) throws SQLException;

    int updateByPrimaryKeySelective(AccountBindInfo record) throws SQLException;

    int updateByPrimaryKey(AccountBindInfo record) throws SQLException;

    Integer selectCountByQuery(AccountBindQuery query) throws SQLException;

	List<AccountBindInfo> selectListByQuery(AccountBindQuery query) throws SQLException;

	int selectAccBindCountByQuery(AccountBindQuery query) throws SQLException;

	List<AccountBindInfo> selectAccBindListByQuery(AccountBindQuery query) throws SQLException;

	int deleteByPrimaryUserName(String username) throws SQLException;
	
	List<String> getApiAccountByBusName(String businessName);

	List<AccountBindInfo> selectByUsernameApiAccount(@Param("username") String username, @Param("apiAccount") String apiAccount);

	List<AccountBindInfo> selectByUserName(String username) throws SQLException;
}