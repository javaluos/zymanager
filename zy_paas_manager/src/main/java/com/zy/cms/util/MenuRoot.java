package com.zy.cms.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单属性实体类
 *
 * @author Allen
 * @date 2015-5-25
 */
public class MenuRoot implements Serializable {

    private static final long serialVersionUID = -6605322124856912639L;

    private List<MenuRoot> childList = new ArrayList<MenuRoot>();

    private Integer id = 0;
    
    private String menuid = "";

    private String menu_parent_id = "";

    private String name = "";

    private String text = "";

    private String href = "";

    private String ico = "";

    private Integer level = 0;

    private Integer status = 1;// 菜单显示有效状态
    
    private int isChecked = 0 ;//0:未选中,1:已选中

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMenuid() {
        return menuid;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }

    public String getMenu_parent_id() {
        return menu_parent_id;
    }

    public void setMenu_parent_id(String menu_parent_id) {
        this.menu_parent_id = menu_parent_id;
    }

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public List<MenuRoot> getChildList() {
        return childList;
    }

    public void setChildList(List<MenuRoot> childList) {
        this.childList = childList;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

	public int getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(int isChecked) {
		this.isChecked = isChecked;
	}
    
}
