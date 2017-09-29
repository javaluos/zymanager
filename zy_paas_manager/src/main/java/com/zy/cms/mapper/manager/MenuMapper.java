package com.zy.cms.mapper.manager;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zy.cms.vo.Menu;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

	List<Menu> selectUserMenus(@Param("ids") List<String> ids, @Param("type") Integer type);

	List<Menu> selectAllEnableMenus(Short status);
}