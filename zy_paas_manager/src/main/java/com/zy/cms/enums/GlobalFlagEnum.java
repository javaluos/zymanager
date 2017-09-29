package com.zy.cms.enums;

/**
 * 监控指标设置全局标识Enum
 * 
 * @author luos
 * @date 2016-11-23 09:55:20
 */
public enum GlobalFlagEnum {

	GLOBAL(1, "全局"), DEFAULT(0, "客户");

	// 成员变量
	private int type;
	private String name;

	// 构造方法
	private GlobalFlagEnum(int _type, String _name) {
		this.type = _type;
		this.name = _name;
	}

	// 普通方法
	public static String getName(int _type) {
		for (GlobalFlagEnum c : GlobalFlagEnum.values()) {
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
