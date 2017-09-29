package com.zy.cms.enums;

/**
 * 账号认证状态Enum
 * @author luos
 * @date 2016-11-8 14:59:10
 */
public enum AuthFlagEnum {
	
	UNAUTH(-1, "未认证"),
	AUTHED(1, "已认证");

	// 成员变量
	private int type;
	private String name;

	// 构造方法
	private AuthFlagEnum(int _type, String _name) {
		this.type = _type;
		this.name = _name;
	}

	// 普通方法
	public static String getName(int _type) {
		for (AuthFlagEnum c : AuthFlagEnum.values()) {
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
