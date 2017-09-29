package com.zy.cms.service.manager;

import java.util.List;

import com.zy.cms.vo.Menu;

public interface MenuService {

	List<Menu> getUserMenus(List<String> ids, Integer type);

	List<Menu> getAllEnableMenus(Short status);

}
