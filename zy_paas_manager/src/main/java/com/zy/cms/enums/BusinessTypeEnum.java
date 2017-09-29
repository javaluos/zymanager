package com.zy.cms.enums;

/**
 * 业务类型常量
 * 
 * @author allen.yuan
 * @date 2016-11-07
 *
 */
public enum BusinessTypeEnum {

	BT(-1, ""), 
	BT_1(1, "回拨电话"), 
	BT_2(2, "号码卫士"), 
	BT_3(3, "直拨电话"), 
	BT_4(4, "语音通知"), 
	BT_5(5, "语音验证码"),
	BT_6(6, "呼叫中心"),
	BT_7(7, "多方通话"),
	BT_8(8, "短信通知"),
	BT_9(9, "短信验证码"),
	BT_10(10, "录音"),
	BT_11(11, "短信营销"),
	BT_80(80, "短信(老平台)");

	// 成员变量
	private int type;
	private String name;

	// 构造方法
	private BusinessTypeEnum(int _type, String _name) {
		this.type = _type;
		this.name = _name;
	}

	// 普通方法
	public static String getName(int _type) {
		for (BusinessTypeEnum c : BusinessTypeEnum.values()) {
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

	public String getTypeStr() {
		return String.valueOf(type);
	}

	public void setType(int type) {
		this.type = type;
	}

}
