package com.zy.cms.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.TypeReference;

public class RequstUtil {
	 
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 对象反序列化
	 * 
	 * @param <T>
	 * @param is
	 * @param ref
	 * @param dataType
	 * @return
	 * @throws Exception
	 */
	public static <T extends Object> T jsonString2Bean(InputStream is,
			TypeReference<T> ref) throws Exception {
		String inputstr = readHttpRequest(is);
		return parseToObject(inputstr, ref);
	}

	public static String getRequestStr(InputStream is) throws Exception {
		String inputstr = readHttpRequest(is);
		inputstr = URLDecoder.decode(inputstr, "UTF-8");
		return inputstr;
	}

	/**
	 * Request string to Object
	 * 
	 * @param <T>
	 * @param is
	 * @param ref
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public static <T extends Object> T parseToObject(String jsonstr,
			TypeReference<T> ref) throws Exception {
		try {
			if (jsonstr == null || "".equals(jsonstr.trim())) {
				return null;
			}
			jsonstr = jsonstr.trim();
			return JsonUtil.parseToObject(jsonstr, ref);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 读取HTTP消息
	 * 
	 * @param is
	 * @return
	 * @throws Exception
	 */
	public static String readHttpRequest(InputStream is) throws Exception {
		if (is == null) {
			return "";
		}
		ByteArrayOutputStream bos = null;
		try {
			bos = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int r;
			while ((r = is.read(buf, 0, buf.length)) != -1) {
				bos.write(buf, 0, r);
			}
			
			String str = new String(bos.toByteArray(), "UTF-8");
			return str;
		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (bos != null) {
					bos.close();
				}
			} catch (IOException e) {
				// TODO
			}
		}
	}

	/**
	 * 文件流转字符串
	 * 
	 * @param is
	 *            文本流
	 * @return
	 */
	public static String convertStreamToString(InputStream is) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;

		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}
