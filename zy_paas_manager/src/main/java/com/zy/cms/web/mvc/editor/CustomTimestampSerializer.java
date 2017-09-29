package com.zy.cms.web.mvc.editor;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Timestamp类型参数JSON输出(通过注解方式).
 * 
 * @author fenglb
 * @date 2014-2-25 上午10:34:59
 */
public class CustomTimestampSerializer extends JsonSerializer<Timestamp> {

	public static String FORMATTER = "yyyy-MM-dd HH:mm:ss";

	@Override
	public void serialize(Timestamp value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		SimpleDateFormat formatter = new SimpleDateFormat(FORMATTER);
		String formattedDate = formatter.format(value);
		jgen.writeString(formattedDate);
	}
}
