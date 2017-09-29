package com.zy.cms.enums;

/**
 * 认证状态Enum
 * @author luos
 * @date 2016-11-8 14:58:39
 */
public enum AuthStatusEnum {
	
	AUTHED(1, "已认证"),
	TOAUTH(2, "待认证"),
	AUTHING(3,"认证中"),
	FAILED(4,"认证失败"),
	CANCEL(5,"取消认证");
	
	// 成员变量
	private int type;
	private String name;

	// 构造方法
	private AuthStatusEnum(int _type, String _name) {
		this.type = _type;
		this.name = _name;
	}

	// 普通方法
	public static String getName(int _type) {
		for (AuthStatusEnum c : AuthStatusEnum.values()) {
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
