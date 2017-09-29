package com.zy.cms.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 日期格式转换
 * 
 * @author allen.yuan
 * @date 2016-9-7
 * 
 */
public class DateTag extends TagSupport {

	private static final long serialVersionUID = -3354015192721342312L;
	private String value;
	private String pattern;

	public void setValue(String value) {
		this.value = value;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public int doStartTag() throws JspException {

		try {
			String s="";
			if(value!=null&&!"".equals(value.trim())){
				value=value.trim();
				Long time = new Long(value);
				if (value.length() == 10) {
					time = new Long(value + "000");
				}
				if (pattern == null || pattern.trim() == "") {
					pattern = "yyyy-MM-dd HH:mm:ss";
				}
				SimpleDateFormat dateformat = new SimpleDateFormat(pattern);
				s = dateformat.format(new Date(time));
			}
			pageContext.getOut().write(s);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return super.doStartTag();
	}
}