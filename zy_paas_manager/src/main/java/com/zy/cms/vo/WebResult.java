package com.zy.cms.vo;

/**
 * JSON请求,消息结果对象
 * 
 * @author allen.yuan
 * @date 2016-9-20
 * 
 */
public class WebResult {

	private int code = 1; // (1:成功,-1:失败)
	private String msg = "";// 消息内容
	private Object data = null;// 数据内容

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public WebResult() {

	}

	public WebResult(int code) {
		this.code = code;
	}

	public WebResult(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public WebResult(int code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
}
