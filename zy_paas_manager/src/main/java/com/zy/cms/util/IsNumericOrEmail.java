package com.zy.cms.util;

import java.util.regex.Pattern;

/**
 * 
 * @Description: (判断是否是数字)
 * @author hmj
 * @date 2015-6-01
 * 
 */
public class IsNumericOrEmail {

	/**
	 * @Title: isNumeric
	 * @Description: (判断是否是数字)
	 * @param：@param str
	 * @param：@return
	 * @return boolean 返回类型
	 * @throws
	 */
	public static boolean isNumeric(String str) {
		if(StringUtil.isEmpty(str)){//如果是空，则直接返回false
			return false;
		}
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * 
	 * @Title: isPhone
	 * @Description:(非严格的判断是否是手机)
	 * @param：@param str
	 * @param：@return
	 * @return boolean 返回类型
	 * @throws
	 */
	public static boolean isPhone(String str) {
		Pattern pattern = Pattern.compile("^[1](([3]|[4]|[5]|[7]|[8])[0-9]{1})[0-9]{8}$");
		return pattern.matcher(str).matches();
	}
	
	/**
	 * 
	 * @Title: isEmail
	 * @Description: (判断是否是邮箱)
	 * @param：@param str
	 * @param：@return
	 * @return boolean 返回类型
	 * @throws
	 */
	public static boolean isEmail(String str) {
		Pattern pattern = Pattern
				.compile("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\\.([a-zA-Z0-9_-])+)+$");
		return pattern.matcher(str).matches();
	}

}
