package com.zy.cms.enums;

/**
 * 通道属性
 * 
 * @author allen.yuan
 * @date 2017-2-7 12:06:43
 */
public enum ChannelPropertyEnum {

	All("0", ""), YD("10", "移动"), DX("20", "电信"), LT("30", "联通");

	// 成员变量
	private String value;
	private String name;

	// 构造方法
	private ChannelPropertyEnum(String _value, String _name) {
		this.value = _value;
		this.name = _name;
	}

	// 普通方法
	public static String getName(int _value) {
		for (ChannelPropertyEnum c : ChannelPropertyEnum.values()) {
			if (c.getValue().equals(_value)) {
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

	public String getValue() {
		return value;
	}

	public void setType(String value) {
		this.value = value;
	}
}
