package com.zy.cms.util;

import java.math.BigDecimal;

/**
 * 数据类型.
 * 
 * @author fenglb
 * @date 2012-7-28 下午02:42:26
 */
public class ValueUtil {
	/**
	 * 值对象 --> String.
	 * 
	 * @param value
	 * @return
	 * @author fenglb
	 * @date 2012-7-28 下午03:15:11
	 */
	public static String getString(Object value) {
		String result = "";
		if (value != null) {
			String sValue = value.toString().trim();
			if (value instanceof Number) {
				if (value instanceof Double || value instanceof BigDecimal) {
					if (!"Infinity".equals(sValue) && !"NaN".equals(sValue)) {
						result = StringUtil.toNuSicen(value);
					} else {
						result = "0";
					}
				} else {
					result = sValue;
				}
			} else {
				result = sValue;
			}
		}
		return result.trim();
	}

	/**
	 * 值对象 --> long.
	 * 
	 * @param value
	 * @return
	 * @author fenglb
	 * @date 2012-7-28 下午03:27:21
	 */
	public static long getLong(Object value) {
		try {
			return Long.parseLong(getString(value));
		} catch (Exception e) {
			return 0L;
		}
	}

	/**
	 * 值对象 --> double.
	 * 
	 * @param value
	 * @return
	 * @author fenglb
	 * @date 2012-7-28 下午03:29:25
	 */
	public static double getDouble(Object value) {
		try {
			return Double.parseDouble(getString(value));
		} catch (Exception e) {
			return 0.0;
		}
	}

	/**
	 * 值对象 --> int.
	 * 
	 * @param value
	 * @return
	 * @author fenglb
	 * @date 2012-7-28 下午03:29:35
	 */
	public static int getInt(Object value) {
		try {
			return Integer.parseInt(getString(value));
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 值对象 --> boolean.
	 * 
	 * @param value
	 * @return
	 * @author fenglb
	 * @date 2012-10-12 上午09:00:16
	 */
	public static boolean getBoolean(Object value) {
		try {
			String v = getString(value);
			if ("1".equals(v)) {
				return true;
			} else if ("0".equals(v)) {
				return false;
			} else if ("Y".equals(v)) {
				return true;
			} else if ("N".equals(v)) {
				return false;
			} else {
				return Boolean.parseBoolean(v);
			}
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 获得四舍五入的数字结果(精确到2位小数点).
	 * 
	 * @param num
	 *            数字
	 * @return 四舍五入的数字结果
	 * @author fenglb
	 * @date 2013-10-24 下午5:16:32
	 */
	public static double getRoundNum(double num) {
		BigDecimal b = new BigDecimal(num);
		double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}
	
	/**
	 * 为了防止精度丢失而计算乘法
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static int calcMul(double d1,int d2,int d3){  
		BigDecimal b1=new BigDecimal(Double.toString(d1));  
        BigDecimal b2=new BigDecimal(Integer.toString(d2));  
        BigDecimal b3;
        switch (d3) {
		  case 0:
			  b3 =new BigDecimal(Integer.toString(10));
			  break;
		  case 1:
			  b3 =new BigDecimal(Integer.toString(2));
			  break;
		  default:
			  b3 =new BigDecimal(Integer.toString(1));
			  break;
		}
        return b1.multiply(b2).divide(b3).intValue();  
          
    } 
	
	public static int calcMul(double d1,int d2){  
		BigDecimal b1=new BigDecimal(Double.toString(d1));  
        BigDecimal b2=new BigDecimal(Integer.toString(d2)); 
        return b1.multiply(b2).intValue();  
          
    } 
}
