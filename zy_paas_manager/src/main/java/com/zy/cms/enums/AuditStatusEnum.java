package com.zy.cms.enums;

/**
 * 审核状态Enum
 * @author luos
 * @date 2017-1-5 12:06:43
 */
public enum AuditStatusEnum {

	CHECKED(1, "审核通过"),
	TOCHECK(2, "待认证"),
	CHECKING(3,"审核中"),
	FAILED(4,"认证未通过"),
	CANCEL(5,"取消审核");
	
	// 成员变量
	private int type;
	private String name;

	// 构造方法
	private AuditStatusEnum(int _type, String _name) {
		this.type = _type;
		this.name = _name;
	}

	// 普通方法
	public static String getName(int _type) {
		for (AuditStatusEnum c : AuditStatusEnum.values()) {
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
