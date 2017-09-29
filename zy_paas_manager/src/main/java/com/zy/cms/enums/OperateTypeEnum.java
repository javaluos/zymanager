package com.zy.cms.enums;

/**
 * 运营商类型
 * 
 * @author allen.yuan
 * @date 2017-2-7 12:06:43
 */
public enum OperateTypeEnum {

	DF_NET(0, "三网合一"), YD_NET(1, "移动专网"), DX_NET(2, "电信专网"), LT_NET(3, "联通专网");

	// 成员变量
	private int type;
	private String name;

	// 构造方法
	private OperateTypeEnum(int _type, String _name) {
		this.type = _type;
		this.name = _name;
	}

	// 普通方法
	public static String getName(int _type) {
		for (OperateTypeEnum c : OperateTypeEnum.values()) {
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
