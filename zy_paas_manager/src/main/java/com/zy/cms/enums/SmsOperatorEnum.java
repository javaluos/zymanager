package com.zy.cms.enums;

/**
 * Created by luos on 2017/5/22.
 */
public enum SmsOperatorEnum {

    YD("YD", "移动"),
    LT("LT", "联通"),
    DX("DX","电信");

    // 成员变量
    private String type;
    private String name;

    // 构造方法
    private SmsOperatorEnum(String _type, String _name) {
        this.type = _type;
        this.name = _name;
    }

    // 普通方法
    public static String getName(String _type) {
        for (SmsOperatorEnum c : SmsOperatorEnum.values()) {
            if (c.getType().equals(_type)) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
