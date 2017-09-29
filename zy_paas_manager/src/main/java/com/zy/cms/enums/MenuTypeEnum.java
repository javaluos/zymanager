package com.zy.cms.enums;

public enum MenuTypeEnum {
	
	TYPE0(0, "功能"),
	TYPE1(1, "菜单");
	
	// 成员变量
	private Integer type;
	private String name;

	// 构造方法
	private MenuTypeEnum(Integer _type, String _name) {
		this.type = _type;
		this.name = _name;
	}

	// 普通方法
	public static String getName(Integer _type) {
		for (MenuTypeEnum c : MenuTypeEnum.values()) {
			if (c.getType() == _type) {
				return c.name;
			}
		}
		return "";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
