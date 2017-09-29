package com.zy.cms.mapper.manager;

import java.util.List;

import com.zy.cms.vo.UserMenu;

public interface UserMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserMenu record);

    int insertSelective(UserMenu record);

    UserMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserMenu record);

    int updateByPrimaryKey(UserMenu record);

	List<UserMenu> selectByUserId(Integer userId);

	List<UserMenu> selectByUserName(String userName);
}