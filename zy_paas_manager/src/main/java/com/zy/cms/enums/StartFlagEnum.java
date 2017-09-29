package com.zy.cms.enums;

/**
 * 监控指标设置启用标识Enum
 * @author luos
 * @date 2016-11-23 09:55:02
 */
public enum StartFlagEnum {
	
	ENABLE(1, "启用"),
	DISABLE(0, "禁用");
	
	// 成员变量
	private int type;
	private String name;

	// 构造方法
	private StartFlagEnum(int _type, String _name) {
		this.type = _type;
		this.name = _name;
	}

	// 普通方法
	public static String getName(int _type) {
		for (StartFlagEnum c : StartFlagEnum.values()) {
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
