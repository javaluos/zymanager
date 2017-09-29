package com.zy.cms.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zy.cms.common.ZyLogger;

/**
 * Jackson2工具类.
 * 
 * @author qingwu
 * @date 2012-10-10 上午09:51:50
 */
public class Jackson2Util {

    /** 日志处理器 . */
    private static ZyLogger logger = ZyLogger.getLogger(Jackson2Util.class);

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    }

    /**
     * 采用JSON格式输出结果对象.
     * 
     * @param result 结果对象
     * @throws IOException
     */
    public static void writeJson(HttpServletResponse response, Object result) throws IOException {
        PrintWriter writer = null;
        response.setContentType("text/plain; charset=UTF-8");
        try {
            writer = response.getWriter();
            String json = toJson(result);
            writer.println(json);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    /**
     * 将对象转化为Json格式字符串.
     * 
     * @param obj
     * @return
     * @throws JsonProcessingException
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonGenerationException
     */
    public static String toJson(Object obj) throws JsonProcessingException {
        return mapper.writeValueAsString(obj);
    }

    /**
     * 将Json格式字符串转化为对象.
     * 
     * @param <T>
     * @param json
     * @param requiredType
     * @return
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonParseException
     */
    public static <T> T toObject(String json, Class<T> requiredType) throws JsonParseException, JsonMappingException, IOException {
        return mapper.readValue(json, requiredType);
    }

    /**
     * 将Json格式字符串转化为对象.
     * 
     * @param <T>
     * @param json
     * @param requiredType
     * @return
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonParseException
     */
    public static <T> T toObject(String json, TypeReference<T> type) throws JsonParseException, JsonMappingException, IOException {
        return mapper.readValue(json, type);
    }

}
