package com.zy.cms.enums;

public enum SmsSendStatusEnum {

	SUCCESS(0, "发送成功"),
	FAIL(1,"发送失败"),
	UNKNOWN(2, "状态未知"),
	SUBMIT_FAIL(3, "提交失败");
	
	// 成员变量
	private int type;
	private String name;

	// 构造方法
	private SmsSendStatusEnum(int _type, String _name) {
		this.type = _type;
		this.name = _name;
	}

	// 普通方法
	public static String getName(int _type) {
		for (SmsSendStatusEnum c : SmsSendStatusEnum.values()) {
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
