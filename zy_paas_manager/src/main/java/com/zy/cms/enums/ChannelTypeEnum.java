package com.zy.cms.enums;

/**
 * 通道类型enum
 * @author luos
 * @date 2017-3-15 15:14:52
 *
 */
public enum ChannelTypeEnum {
	TYPE_1(1, "通知"), 
	TYPE_2(2, "验证码"), 
	TYPE_3(3, "营销"), 
	TYPE_4(4, "验证码、通知"), 
	TYPE_5(5, "验证码、通知、营销");

	// 成员变量
	private int type;
	private String name;

	// 构造方法
	private ChannelTypeEnum(int _type, String _name) {
		this.type = _type;
		this.name = _name;
	}

	// 普通方法
	public static String getName(int _type) {
		for (ChannelTypeEnum c : ChannelTypeEnum.values()) {
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

