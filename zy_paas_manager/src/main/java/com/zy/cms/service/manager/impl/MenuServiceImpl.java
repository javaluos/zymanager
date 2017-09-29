package com.zy.cms.service.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zy.cms.mapper.manager.MenuMapper;
import com.zy.cms.service.manager.MenuService;
import com.zy.cms.vo.Menu;

@Service("menuService")
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	private MenuMapper mapper;

	@Override
	public List<Menu> getUserMenus(List<String> ids, Integer type) {
		List<Menu> menuList = mapper.selectUserMenus(ids, type);
		return menuList;
	}

	@Override
	public List<Menu> getAllEnableMenus(Short status) {
		return mapper.selectAllEnableMenus(status);
	}

}
