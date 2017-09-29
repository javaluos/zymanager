package com.zy.cms.enums;

public enum SmsCategoryEnum {

	NOTICE("8", "通知"),
	VCODE("9", "验证码"),
	MARKETING("11","营销");
	
	// 成员变量
	private String type;
	private String name;

	// 构造方法
	private SmsCategoryEnum(String _type, String _name) {
		this.type = _type;
		this.name = _name;
	}

	// 普通方法
	public static String getName(String _type) {
		for (SmsCategoryEnum c : SmsCategoryEnum.values()) {
			if (c.getType().equals(_type)) {
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
