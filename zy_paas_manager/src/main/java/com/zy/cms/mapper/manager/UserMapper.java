package com.zy.cms.mapper.manager;

import java.util.List;
import java.util.Map;

import com.zy.cms.vo.User;
import com.zy.cms.vo.query.UserQuery;

public interface UserMapper {

	List<User> findAll();

	User findUserForLogin(Map<String,String> map);

	int updateUserForLogin(User user);

	int insertUser(User user);

	int selectUserCount(UserQuery query);

	List<User> selectUsers(UserQuery query);
	
	int updateByPrimaryKeySelective(User user);

	User selectByUserId(Integer userId);
}