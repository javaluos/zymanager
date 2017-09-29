package com.zy.cms.util;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JsonUtil {

    /**
     * 对象转换成json字符串
     * 
     * @date：2015-5-29
     * @param obj
     * @return
     */
    public static String objectToJson(Object obj) {
        String result = JSON.toJSONString(obj);
        return result;
    }

    /**
     * @Description：通过fastjson生成json格式字符串
     * @date：2015-5-29
     * @param t
     * @return
     */
    public static <T extends Object> String toJsonString(T t) {
        StringBuilder builder = new StringBuilder("");
        builder.append(JSON.toJSONStringWithDateFormat(t, "yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteDateUseDateFormat, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullStringAsEmpty,
                                                       SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteNullListAsEmpty));
        return builder.append("").toString();
    }
    /**
     * @Description：通过fastjson生成json格式字符串，不转换成序列化的字段
     * @date：2015-5-29
     * @param t
     * @return
     */
    public static <T extends Object> String toJsonStringNoSeria(T t) {
    	StringBuilder builder = new StringBuilder("");
    	builder.append(JSON.toJSONStringWithDateFormat(t, "yyyy-MM-dd HH:mm:ss"));
    	return builder.append("").toString();
    }

    /**
     * @Description：通过fastjson生成json格式字符串
     * @date：2015-5-29
     * @param t
     * @return
     */
    public static <T extends Object> String toJsonStringNoFormat(T t) {
        StringBuilder builder = new StringBuilder("");
        builder.append(JSON.toJSONString(t));
        return builder.append("").toString();
    }

    /**
     * @Description：将json格式的字符串转换成制定的对象
     * @date：2015-5-29
     * @param jsonstr
     * @param ref
     * @return
     * @throws Exception
     */
    public static <T extends Object> T parseToObject(String jsonstr, TypeReference<T> ref) {
        if (jsonstr == null || "".equals(jsonstr.trim())) {
            return null;
        }
        jsonstr = jsonstr.trim();
        return JSONObject.parseObject(jsonstr, ref);
    }

    /**
     * @Description：将json格式的字符串转换成制定的对象
     * @date：2015-5-29
     * @param jsonstr
     * @param clz
     * @return
     * @throws Exception
     */
    public static <T extends Object> T parseToObject(String jsonstr, Class<T> clz) {
        if (jsonstr == null || "".equals(jsonstr.trim())) {
            return null;
        }
        jsonstr = jsonstr.trim();
        return JSONObject.parseObject(jsonstr, clz);
    }

    public static String wrapjson(String code, Object msg) {
        Map map = new HashMap();
        map.put("code", code);
        map.put("result", msg);
        return JsonUtil.toJsonString(map);
    }
}
