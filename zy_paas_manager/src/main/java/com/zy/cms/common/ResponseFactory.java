package com.zy.cms.common;

/**
 * 常用Response工厂.
 * 
 * @author qingwu
 * @date 2014-3-20 下午7:13:33
 */
public class ResponseFactory {
	/**
	 * 获取默认操作成功Response.
	 * 
	 * @return
	 * @author qingwu
	 * @date 2012-9-29 下午02:30:07
	 */
	public static <T> Response<T> getDefaultSuccessResponse() {
		Response<T> response = new Response<T>();
		response.setResult(Response.RESULT_SUCCESS);
		return response;
	}

	public static <T> Response<T> getDefaultSuccessResponse(String msg) {
		Response<T> response = getDefaultSuccessResponse();
		response.setMessage(msg);
		return response;
	}

	/**
	 * 获取默认操作失败Response.
	 * 
	 * @return
	 * @author qingwu
	 * @date 2012-11-14 下午04:37:39
	 */
	public static <T> Response<T> getDefaultFailureResponse() {
		Response<T> response = new Response<T>();
		response.setResult(Response.RESULT_FAILURE);
		return response;
	}

	public static <T> Response<T> getDefaultFailureResponse(String msg) {
		Response<T> response = getDefaultFailureResponse();
		response.setMessage(msg);
		return response;
	}

	/**
	 * 获取默认输入操作失败Response.
	 * 
	 * @return
	 */
	public static <T> Response<T> getDefaultInputFailureResponse() {
		Response<T> response = new Response<T>();
		response.setResult(Response.RESULT_INPUT);
		return response;
	}

	public static <T> Response<T> getDefaultInputFailureResponse(String msg) {
		Response<T> response = getDefaultInputFailureResponse();
		response.setMessage(msg);
		return response;
	}
}
