package com.zy.cms.enums;

/**
 * 短信监控类型Enum
 * Created by luos on 2017/6/8.
 */
public enum SmsMonitorTypeEnum {

    SMT_1(1, "并发数"),
    SMT_2(2, "成功率"),
    SMT_3(3, "失败率"),
    SMT_4(4, "未知率"),
    SMT_5(5, "平均发送时长"),
    SMT_6(6, "平均状态报告时长");

    // 成员变量
    private int type;
    private String name;

    // 构造方法
    private SmsMonitorTypeEnum(int _type, String _name) {
        this.type = _type;
        this.name = _name;
    }

    // 普通方法
    public static String getName(int _type) {
        for (SmsMonitorTypeEnum c : SmsMonitorTypeEnum.values()) {
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
