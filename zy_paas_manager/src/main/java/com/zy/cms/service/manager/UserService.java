package com.zy.cms.service.manager;

import java.util.List;

import com.zy.cms.vo.User;
import com.zy.cms.vo.query.UserQuery;

public interface UserService {

	List<User> findAll();

	User findUserByName(String userName);
	
	User findUserByUserId(Integer userId);

	int updateUserForLogin(User user);

	int insertUser(User user);

	int queryUserCount(UserQuery query);

	List<User> queryUsers(UserQuery query);

	boolean saveUserInfo(String userName, String fullname, String phone, Integer department, String menu, String deptName);

	void deleteByUserId(Integer userId);

	boolean initPwd(Integer userId);

	boolean updatePwd(Integer userId, String newpwd);
}
