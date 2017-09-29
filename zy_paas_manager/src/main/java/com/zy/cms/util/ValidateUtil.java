/*
 * Copyright Guoling.com All right reserved. This software is the confidential and proprietary information of
 * Guoling.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Guoling.com.
 */
package com.zy.cms.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * 类ValidateUtil.java的实现描述：TODO 类实现描述
 * @author ddp1j32 2015-6-5 下午7:56:19
 */
public class ValidateUtil {

    /**
     * 数字、字母、下划线且不能以下划线结尾、开头
     */
    public static final String   COMM_VALID         = "/^[\\@A-Za-z0-9\\!\\#\\$\\%\\^\\&\\*\\.\\~]{6,22}$/";

    /**
     * 所有手机
     */
    public static final String   ALL_MOBILE         = "^[1](([3]|[4]|[5]|[7]|[8])[0-9]{1})[0-9]{8}$";

    private static final Pattern ALL_MOBILE_PATTERN = Pattern.compile(ALL_MOBILE);
    private static final Pattern COMM_VALID_PATTERN = Pattern.compile(COMM_VALID);

    /**
     * 只校验大陆手机号是否正确
     * 
     * @param mobile
     * @throws ParamsException
     */
    public static boolean isCNMobile(String mobile) {
        Matcher matcher = ALL_MOBILE_PATTERN.matcher(mobile);
        if (!matcher.matches()) {
            return false;
        }
        return true;
    }

    /**
     * @param key 校验字符
     * @throws ParamsException
     * @throws Exception
     */
    public static boolean isNumberStringUnderLine(String key) {
        Matcher matcher = COMM_VALID_PATTERN.matcher(key);
        if (!matcher.matches()) {
            return false;
        }
        return true;
    }
}
