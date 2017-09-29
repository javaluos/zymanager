package com.zy.cms.util;

import org.apache.commons.lang3.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by luos on 2017/8/1.
 */
public class SmsContentUtil {

    public static Map<String, String> getNumbers(String intputStr) {
        Map<String, String> numberMap = new LinkedHashMap<String, String>();

        int numberlength = 0;
        int nowlength = 0;
        StringBuffer nowNumberStr = null;

        for (int i = 0; i < intputStr.length(); i++) {
            int index = intputStr.charAt(i);
            if (intputStr.charAt(i) >= 48 && intputStr.charAt(i) <= 57) {
                if (nowlength == 0) {
                    nowNumberStr = new StringBuffer(String.valueOf(intputStr.charAt(i)));
                    nowlength++;
                } else {
                    nowNumberStr.append(intputStr.charAt(i));
                    nowlength++;
                }
            } else if ((intputStr.charAt(i) >= 65 && intputStr.charAt(i) <= 90)
                    || (intputStr.charAt(i) >= 97 && intputStr.charAt(i) <= 122)) {
                if (nowlength == 0) {
                    nowNumberStr = new StringBuffer(String.valueOf(intputStr.charAt(i)));
                    nowlength++;
                } else {
                    nowNumberStr.append(intputStr.charAt(i));
                    nowlength++;
                }
            } else {
                if (null != nowNumberStr && nowNumberStr.length() > 1){
                    numberMap.put(nowNumberStr.toString() + "", nowNumberStr.toString());
                    nowlength = 0;
                    nowNumberStr = null;
                } else {
                    nowlength = 0;
                    nowNumberStr = null;
                }

            }

        }
        if (StringUtils.isNotBlank(nowNumberStr)) {
            numberMap.put(nowNumberStr.toString() + "",nowNumberStr.toString());
        }
        return numberMap;
    }

    public static String replaceAllNumber(Map<String, String> numberMap, String smsContent) {
        for (Map.Entry<String, String> entry : numberMap.entrySet()) {
            if (smsContent.contains(entry.getValue())) {
                smsContent = smsContent.replace(entry.getValue(), "$");
            }
        }
        return smsContent;
    }

    public static void main (String args[]) {
        String smsContent = "每月500M全国流量包月包(30元/月),祝您生活愉快,详询：4008232468【流量掌厅】";
//        smsContent = smsContent.replace(smsContent.substring(smsContent.indexOf("$"), smsContent.indexOf("$") + 1), "500M");
//        System.out.println(smsContent);
//        System.out.println(smsContent.replace(smsContent.substring(smsContent.indexOf(3)) , "$"));
        Map<String, String> numberMap = getNumbers(smsContent);
        smsContent = replaceAllNumber(numberMap, smsContent);
        System.out.println(smsContent);
        smsContent = StringUtil.replaceSpecStr(smsContent).toLowerCase();
        for (int i=0;i<smsContent.length();){
            String index = smsContent.charAt(i) + "";
            if (null != numberMap && numberMap.size() > 0){
                for (Map.Entry<String, String> entry : numberMap.entrySet()) {
                    if (("$").equals(index)){
                        System.out.print(entry.getValue() + " ");
                        String before = smsContent.substring(0, i);
                        String back = smsContent.substring((i + 1), smsContent.length());
                        smsContent = before + entry.getValue() + back;
                        i += entry.getValue().length();
                        numberMap.remove(entry.getKey());
                        break;
                    } else {
                        System.out.print(index + " ");
                        i++;
                        break;
                    }
                }
            } else {
                System.out.print(index + " ");
                i++;
            }

        }
    }
}
