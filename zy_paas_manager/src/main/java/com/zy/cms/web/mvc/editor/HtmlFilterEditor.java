package com.zy.cms.web.mvc.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.web.util.HtmlUtils;

import com.zy.cms.util.ClassUtil;
import com.zy.cms.util.ObjectUtil;

/**
 * html 字符过滤, 防止脚本注入.
 * 
 * @author fenglb
 * @date 2013-3-30 下午3:14:08
 */

public class HtmlFilterEditor extends PropertyEditorSupport {

    public void setAsText(String text) throws IllegalArgumentException {
        if (!ObjectUtil.isEmpty(text)) {
            setValue(ClassUtil.castValue(HtmlUtils.htmlEscape(text), getValue().getClass()));
        } else {
            setValue(null);
        }
    }

    public String getAsText() {
        Object value = getValue();
        return ObjectUtil.isEmpty(value) ? "" : value.toString().trim();
    }
}
