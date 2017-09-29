package com.zy.cms.web.mvc.editor;

import java.beans.PropertyEditorSupport;

import com.zy.cms.util.ObjectUtil;

/**
 * 字符串注入过滤器<br>
 * 
 * @author fenglb
 * @date 2013-3-30 下午3:14:08
 */
public class StringEditor extends PropertyEditorSupport {

    public void setAsText(String text) throws IllegalArgumentException {
        if (!ObjectUtil.isEmpty(text)) {
            setValue(text.toString().trim());
        } else {
            setValue(text);
        }
    }

    public String getAsText() {
        Object value = getValue();
        return ObjectUtil.isEmpty(value) ? "" : value.toString().trim();
    }
}
