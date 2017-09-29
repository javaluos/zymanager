package com.zy.cms.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class StringUtil {

    public static final String   ALL_MOBILE         = "^[1](([3]|[4]|[5]|[7]|[8])[0-9]{1})[0-9]{8}$";
    public static final String   PARAM_VALIDE         = "\\#[^\\#]+\\#";
    private static final Pattern ALL_MOBILE_PATTERN = Pattern.compile(ALL_MOBILE);
    
    private static final String CHINESE_TEXT = "[\u4E00-\u9FA5]{2,20}";
    private static final Pattern CHINESE_TEXT_PATTERN = Pattern.compile(CHINESE_TEXT);
    
    private static final String CHINESE_LEFT_NUM_TEXT = "^[\u4E00-\u9FA5a-zA-Z0-9]{2,20}$";
    private static final Pattern CHINESE_LEFT_NUM_TEXT_PATTERN = Pattern.compile(CHINESE_LEFT_NUM_TEXT);
    public static final String   CODE_VILIDE         = "^[0-9]{4,10}$";
    /**
     * 返回字符串的值，如果字符串为空则返回默认值
     * 
     * @param src 字符串
     * @param defaultValue 默认值
     * @return
     */
    public static final String nvl(String src, String defaultValue) {
        if (src != null && src.length() > 0) {
            return src;
        } else {
            return defaultValue;
        }
    }

    public static final String nvl(String src) {
        return nvl(src, "");
    }

    /**
     * 得到当前日期/时间字符串
     * 
     * @return 返回日期/时间字符串
     */
    public static String getNowDateTimeString(String type) {
        Date date = new Date();
        SimpleDateFormat formattxt = new SimpleDateFormat(type);
        return formattxt.format(date);
    }

    /**
     * 判断给入的字符串是否为空,null、""、" "都表示空字符串
     * 
     * @param str 待判定的字符串
     * @return 空符串返回true，否则返回false
     */
    public static boolean isEmpty(String str) {
        if (null == str || "".equals(str.trim()) || "null".equals(str.trim())) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 非空
     * @param str
     * @return
     */
    public static boolean notEmpty(String str) {
        return !isEmpty(str);
    }
    
    /**
     * 生成签名
     * 
     * @return
     */
    public static String generateAppKey() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "").trim();
        return uuid;
    }

    public static boolean validZH_CNMobile(String mobile) {
        Matcher matcher = ALL_MOBILE_PATTERN.matcher(mobile);
        if (!matcher.matches()) {
            return false;
        }
        return true;
    }
    
    public static boolean validChineseText(String text) {
        Matcher matcher = CHINESE_TEXT_PATTERN.matcher(text);
        if (!matcher.matches()) {
            return false;
        }
        return true;
    }
    
    /**
     * 校验汉字、字母、数字的组合
     * @param key
     * @return
     */
    public static boolean validChineseLfN(String key){
    	Matcher matcher = CHINESE_LEFT_NUM_TEXT_PATTERN.matcher(key);
        if (!matcher.matches()) {
            return false;
        }
        return true;
    }
    

    /**
     * 四舍五入并去掉科学计数法, 默认小数点2位.
     * 
     * @param value String, double, Double, BigDecimal
     * @return
     * @author fenglb
     * @date 2012-7-28 下午03:44:05
     */
    public static String toNuSicen(Object value) {
        return toNuSicen(value, 2);
    }

    /**
     * 四舍五入并去掉科学计数法.
     * 
     * @param value String, double, Double, BigDecimal
     * @param precision 保留几位小数
     * @return
     * @author fenglb
     * @date 2012-7-28 下午03:47:25
     */
    public static String toNuSicen(Object value, int precision) {
        Object result = "";
        DecimalFormat df = new DecimalFormat();
        df.setMinimumFractionDigits(precision);
        df.setMaximumFractionDigits(precision);
        df.setGroupingUsed(false);
        if (ObjectUtil.isEmpty(value)) {
            return df.format(0);
        } else if (value instanceof BigDecimal) {
            result = value;
        } else if (value instanceof String) {
            result = new BigDecimal(String.valueOf(value));
        } else if (value instanceof Number) {
            result = Double.parseDouble(value.toString());
        } else {
            throw new IllegalArgumentException(value + "need extends Number or String");
        }
        return df.format(result);
    }

    /**
     * 生成uuid
     * 
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 字符编码转换
     * 
     * @param source
     * @param srcEncode
     * @param destEncode
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String ConverterStringCode(String source, String srcEncode, String destEncode) throws UnsupportedEncodingException {
        return new String(source.getBytes(srcEncode), destEncode);
    }
    
    public static List<String> getParams(String str){
    	 Pattern p = Pattern.compile(PARAM_VALIDE);
    	 List<String> strs = new ArrayList<String>();
		 Matcher m = p.matcher(str);
		 while (m.find()) {
			 strs.add(m.group());           
		 }
		 return strs;
    }

    /**
     * 正则替换所有特殊字符
     * @param orgStr
     * @return
     */
    public static String replaceSpecStr(String orgStr){
        if (null!=orgStr&&!"".equals(orgStr.trim())) {
            String regEx="[\\s~·`!！@#￥%^……&*（()）\\-——\\-_=+【\\[\\]】｛{}｝\\|、\\\\；;：:‘'“”\"，,《<。.》>、/？?]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(orgStr);
            return m.replaceAll("");
        }
        return null;
    }
    
    public static void main (String [] args){
    	String va="你的验证码为：[\u4E00-\u9FA5a-zA-Z0-9]{1,20}$";
    	String  b="你的验证码为：205698d请注意查收请注意查收请注意查收请注意查收请注意查收请注意查收";

    	String CONTENT_VALID = va;
    	Pattern CONTENT_VALID_PATTERN = Pattern.compile( CONTENT_VALID );
    	Matcher matcher = CONTENT_VALID_PATTERN.matcher(b);
    	System.out.println(matcher.matches());
    }
}
