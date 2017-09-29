package com.zy.cms.enums;

/**
 * 监控类型常量
 * 
 * @author allen.yuan
 * @date 2016-11-07
 *
 */
public enum MonitorTypeEnum {
 
	MT(-1, ""), 
	MT_1(1, "并发数"), 
	MT_2(2, "接通率"), 
	MT_3(3, "应答率"), 
	MT_4(4, "平均接通时延"), 
	MT_5(5, "平均通话时长"),
	MT_6(6, "平均接续时长"),
	
	MT_7(7, "短信并发数"),
	MT_8(8, "成功率"),
	MT_9(9, "失败率"),
	MT_10(10, "未知率"),
	MT_11(11, "平均发送时长"),
	MT_12(12, "平均状态报告时长");
	

	// 成员变量
	private int type;
	private String name;

	// 构造方法
	private MonitorTypeEnum(int _type, String _name) {
		this.type = _type;
		this.name = _name;
	}

	// 普通方法
	public static String getName(int _type) {
		for (MonitorTypeEnum c : MonitorTypeEnum.values()) {
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
