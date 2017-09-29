package com.zy.cms.enums;

/**
 * 操作日志类型enum
 * Created by luos on 2017/5/5.
 */
public enum OperateLogTypeEnum {

    ADD_CHNBIND(1, "添加绑定通道"),
    DEL_CHNBIND(2, "删除绑定通道"),
    OTHER(99, "其它");

    // 成员变量
    private int type;
    private String name;

    // 构造方法
    private OperateLogTypeEnum(int _type, String _name) {
        this.type = _type;
        this.name = _name;
    }

    // 普通方法
    public static String getName(int _type) {
        for (OperateLogTypeEnum c : OperateLogTypeEnum.values()) {
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
