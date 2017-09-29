package com.zy.cms.vo;

import java.io.Serializable;

/**
 *JSON请求返回结果 
 *
 */
public class ResultVo implements Serializable{

	/**
	 * SUCCESS FAIL
	 */
	private static final long serialVersionUID = 4375879955695946374L;
	private String reason;
	private String result;
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}

}
