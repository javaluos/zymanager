package com.zy.cms.enums;

/**
 * 通话状态类型常量
 * 
 * @author allen.yuan
 * @date 2016-11-07
 *
 */
public enum StateTypeEnum {

	ST(-1, ""), 
	ST_0(0, "正常通话"), 
	ST_1(1, "被叫未接听"), 
	ST_2(2, "被叫拒接"), 
	ST_3(3, "外呼失败");

	// 成员变量
	private int state;
	private String name;

	// 构造方法
	private StateTypeEnum(int _state, String _name) {
		this.state = _state;
		this.name = _name;
	}

	// 普通方法
	public static String getName(int _state) {
		for (StateTypeEnum c : StateTypeEnum.values()) {
			if (c.getState() == _state) {
				return c.name;
			}
		}
		return "";
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
