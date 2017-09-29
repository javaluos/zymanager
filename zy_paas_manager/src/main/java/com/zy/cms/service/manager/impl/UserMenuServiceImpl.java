package com.zy.cms.service.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zy.cms.mapper.manager.UserMenuMapper;
import com.zy.cms.service.manager.UserMenuService;
import com.zy.cms.vo.UserMenu;

@Service("userMenuService")
public class UserMenuServiceImpl implements UserMenuService {
	
	@Autowired
	private UserMenuMapper mapper;

	@Override
	public UserMenu getByUserName(String userName) {
		UserMenu result = null;
		List<UserMenu> userMenuList = mapper.selectByUserName(userName);
		if(null != userMenuList && userMenuList.size()> 0){
			return userMenuList.get(0);
		}
		return result;
	}

	@Override
	public void updateUserMenus(String userName, String menu) {
		UserMenu userMenu = null;
		List<UserMenu> userMenuList = mapper.selectByUserName(userName);
		if(null != userMenuList && userMenuList.size()> 0){
			userMenu = userMenuList.get(0);
		}
		if(null != userMenu){
			userMenu.setMenus(menu);
			mapper.updateByPrimaryKeySelective(userMenu);
		}
	}

}
