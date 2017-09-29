package com.zy.cms.service.manager.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zy.cms.enums.StartFlagEnum;
import com.zy.cms.mapper.manager.UserMapper;
import com.zy.cms.mapper.manager.UserMenuMapper;
import com.zy.cms.service.manager.UserService;
import com.zy.cms.util.MD5Util;
import com.zy.cms.vo.User;
import com.zy.cms.vo.UserMenu;
import com.zy.cms.vo.query.UserQuery;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserMenuMapper userMenuMapper;

	@Override
	public List<User> findAll() {
		 
		return userMapper.findAll();
	}

	@Override
	public User findUserByName(String userName) {
		 
		Map<String,String> maps = new HashMap<String,String>();
		maps.put("userName", userName);
		return userMapper.findUserForLogin(maps);
	}

	@Override
	public int updateUserForLogin(User user) {
		 
		return userMapper.updateUserForLogin(user);
	}

	@Override
	public int insertUser(User user) {
		 
		return userMapper.insertUser(user);
	}

	@Override
	public int queryUserCount(UserQuery query) {
		return userMapper.selectUserCount(query);
	}

	@Override
	public List<User> queryUsers(UserQuery query) {
		return userMapper.selectUsers(query);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean saveUserInfo(String userName, String fullname, String phone, Integer department, String menu, String deptName) {
		boolean result = false;
		User user = new User();
		user.setUserName(userName);
		user.setPassword(MD5Util.md5Hex("123456"));
		user.setFullname(fullname);
//		user.setDepartment(deptName);
		user.setPhone(phone);
		user.setLeval(1);
		user.setState(StartFlagEnum.ENABLE.getType());
		user.setDeptNo(department);
		Date now = new Date();
		user.setCreateTime(now);
		result = userMapper.insertUser(user) > 0;
		
		UserMenu userMenu = new UserMenu();
		userMenu.setUserName(userName);
		userMenu.setMenus(menu);
		userMenu.setCreateTime(now);
		userMenu.setUpdateTime(now);
		result = userMenuMapper.insert(userMenu) > 0;
		
		return result;
		
	}

	@Override
	public void deleteByUserId(Integer userId) {
		User user = userMapper.selectByUserId(userId);
		user.setState(StartFlagEnum.DISABLE.getType());
		userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public boolean initPwd(Integer userId) {
		User user = userMapper.selectByUserId(userId);
		user.setPassword(MD5Util.md5Hex("123456"));
		return userMapper.updateByPrimaryKeySelective(user) > 0;
	}

	@Override
	public boolean updatePwd(Integer userId, String newpwd) {
		User user = userMapper.selectByUserId(userId);
		user.setPassword(MD5Util.md5Hex(newpwd));
		return userMapper.updateByPrimaryKeySelective(user) > 0;
	}

	@Override
	public User findUserByUserId(Integer userId) {
		return  userMapper.selectByUserId(userId);
	}

}
