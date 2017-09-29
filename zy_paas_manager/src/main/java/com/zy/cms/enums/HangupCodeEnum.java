package com.zy.cms.enums;

/**
 * 通话挂断code类型常量
 * 
 * @author allen.yuan
 * @date 2016-11-07
 *
 */
public enum HangupCodeEnum {

	HC(-1, ""), 
	HC_0(0, ""), 
	HC_1(1, "主叫挂断"), 
	HC_2(2, "被叫挂断"), 
	HC_3(3, "主叫取消"), 
	HC_4(4, "被叫无人接听"), 
	HC_5(5,"暂时无法接通"), 
	HC_8(8, "被叫拒接"), 
	HC_9(9, "空号、号码不存在"), 
	HC_10(10, "关机"), 
	HC_11(11, "停机"), 
	HC_12(12,"用户忙、正在通话中"), 
	HC_255(255, "未知原因");

	// 成员变量
	private int code;
	private String name;

	// 构造方法
	private HangupCodeEnum(int _code, String _name) {
		this.code = _code;
		this.name = _name;
	}

	// 普通方法
	public static String getName(int _code) {
		for (HangupCodeEnum c : HangupCodeEnum.values()) {
			if (c.getCode() == _code) {
				return c.name;
			}
		}
		return "";
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
