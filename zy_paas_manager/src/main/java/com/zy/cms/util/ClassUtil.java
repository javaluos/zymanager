package com.zy.cms.util;

import java.util.Date;

/**
 * 类工具.
 * 
 * @author fenglb
 * @date 2014-3-20 下午7:14:24
 */
public class ClassUtil {

    /**
     * 强行值类型转换.
     * 
     * @param value
     * @param type
     * @return
     * @author yangz
     * @date 2013-2-8 下午5:26:26
     */
    public static Object castValue(Object value, Class<?> type) {
        if (int.class.isAssignableFrom(type) || Integer.class.isAssignableFrom(type)) {
            return ValueUtil.getInt(value);
        } else if (long.class.isAssignableFrom(type) || Long.class.isAssignableFrom(type)) {
            return ValueUtil.getLong(value);
        } else if (double.class.isAssignableFrom(type) || Double.class.isAssignableFrom(type)) {
            return ValueUtil.getDouble(value);
        } else if (boolean.class.isAssignableFrom(type) || Boolean.class.isAssignableFrom(type)) {
            return ValueUtil.getBoolean(value);
        } else if (String.class.isAssignableFrom(type)) {
            return ValueUtil.getString(value);
        } else if (Date.class.isAssignableFrom(type)) {
            return value instanceof Date ? value : DateUtil.parseDate(ValueUtil.getString(value), "yyyy-MM-dd HH:mm:ss");
        } else if (char.class.isAssignableFrom(type) || Character.class.isAssignableFrom(type)) {
            return (char) ValueUtil.getInt(value);
        } else {
            throw new RuntimeException("unknow value type:" + type);
        }
    }

}
