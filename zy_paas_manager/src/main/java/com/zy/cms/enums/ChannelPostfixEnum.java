package com.zy.cms.enums;

/**
 * 通道后缀
 * 
 * @author allen.yuan
 * @date 2017-2-7 12:06:43
 */
public enum ChannelPostfixEnum {

	All(0, ""), YD(1, "YD"), DX(2, "DX"), LT(3, "LT");

	// 成员变量
	private int type;
	private String name;

	// 构造方法
	private ChannelPostfixEnum(int _type, String _name) {
		this.type = _type;
		this.name = _name;
	}

	// 普通方法
	public static String getName(int _type) {
		for (ChannelPostfixEnum c : ChannelPostfixEnum.values()) {
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
