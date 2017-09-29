package com.zy.cms.common;

import org.apache.http.Header;

/**
 * Http请求的结果
 * 
 * @author ddp1j32
 * @date 2015-2-5
 */
public class HttpResult {

    /**
     * Http请求响应码，如200,400等
     */
    private int      code;
    /**
     * Http请求响应的内容
     */
    private String   content;
    /**
     * 当前请求处理是否成功
     */
    private boolean  result;
    /**
     * Http response header
     */
    private Header[] responseHeader;

    public HttpResult(){

    }

    public HttpResult(int code, String content){
        this.code = code;
        this.content = content;
    }

    public HttpResult(int code, String content, boolean result){
        this.code = code;
        this.content = content;
        this.result = result;
    }

    public HttpResult(int code, String content, Header[] responseHeader){
        this.code = code;
        this.content = content;
        this.responseHeader = responseHeader;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public Header[] getResponseHeader() {
        return responseHeader;
    }

    public void setResponseHeader(Header[] responseHeader) {
        this.responseHeader = responseHeader;
    }

    public String toString() {
        return new StringBuilder("code:").append(code).append(",result:").append(result).append(",content:").append(content).append(",responseHeader:").append(responseHeader).toString();
    }
}
