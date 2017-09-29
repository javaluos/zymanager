package com.zy.cms.service.manager;

import com.zy.cms.vo.UserMenu;

public interface UserMenuService {

	UserMenu getByUserName(String userName);

	void updateUserMenus(String userName, String menu);

}
