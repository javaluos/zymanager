package com.zy.cms.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.ListUtils;

import com.zy.cms.vo.Menu;

/**
 * 树形菜单助手类
 *
 * @author allen
 * @date 2015-6-18
 */
public class MenuUtils {

    /**
     * 构建菜单树列表
     *
     * @param allList 所有数据列表
     * @return rsList 整理后的树形列表
     */
    public static List<MenuRoot> initMUList(List<Menu> allList) {

        // 定义结果集
        List<MenuRoot> rsList = new ArrayList<MenuRoot>();

        if (null == allList || allList.size() == 0) {
            return rsList;
        }

        MenuRoot parent = null;
        for (Menu menu : allList) {

            if (StringUtil.isEmpty(menu.getParent()) || menu.getParent().equals("0")) {

                parent = new MenuRoot();
                parent.setId(menu.getId());
                parent.setMenuid(menu.getModuleId());
                parent.setMenu_parent_id(menu.getParent());
                parent.setText("");
                parent.setName(menu.getName());
                parent.setIco(menu.getIcon());
                parent.setHref(menu.getUrl());
                parent.setLevel((int)menu.getLevel());

                rsList.add(parent);

                buildChildren(allList, parent);
            }

        }

        return rsList;
    }

    /**
     * 构建树形列表
     *
     * @param allList 查询的所有列表
     * @param parent  父节点对象
     */
    private static void buildChildren(List<Menu> allList, MenuRoot parent) {

        List<Menu> clsList = getChildren(allList, parent);

        if (!clsList.isEmpty()) {

            for (Menu mu : clsList) {

                MenuRoot children = new MenuRoot();
                children.setId(mu.getId());
                children.setMenuid(mu.getModuleId());
                children.setMenu_parent_id(mu.getParent());
                children.setText("");
                children.setName(mu.getName());
                children.setIco(mu.getIcon());
                children.setHref(mu.getUrl());
                children.setLevel((int)mu.getLevel());

                parent.getChildList().add(children);

                buildChildren(allList, children);
            }

        }
    }

    /**
     * 整理子节点
     *
     * @param allList 查询的所有列表
     * @param parent  父节点对象
     * @return 子节点列表
     */
    private static List<Menu> getChildren(List<Menu> allList, MenuRoot parent) {

        List<Menu> children = new ArrayList<Menu>();

        String menuid = parent.getMenuid();// 父节点ID
        for (Menu childNode : allList) {

            if (menuid.equals(childNode.getParent())) {

                children.add(childNode);
            }
        }

        return children;
    }
}
