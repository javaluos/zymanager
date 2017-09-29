/*
 * Copyright Guoling.com All right reserved. This software is the confidential and proprietary information of
 * Guoling.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Guoling.com.
 */
package com.zy.cms.vo;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.zy.cms.common.Constant;
import com.zy.cms.util.CookieUtil;
import com.zy.cms.util.StringUtil;

/**
 * 类UserInfo.java的实现描述：用户信息封装处理类
 * 
 * @author ddp1j32 2015-6-6 上午8:50:54
 */
@Component
public class UserInfo {

	/**
	 * 增加输出变量username以及messages到freemarker模板
	 * 
	 * @param view
	 * @param request
	 */
	public void setUserInfo(ModelAndView view, HttpServletRequest request) {
		Map<String, String> cookies = CookieUtil.getCookies(request);
		String merchantAccount = cookies.get(Constant.USER_SESSION_UID);
		if (!StringUtil.isEmpty(merchantAccount)) {// 用户的账户cookie没有失效时，才从cookie中获取用户的名称
			String username = cookies.get(Constant.USER_SESSION_UNAME);
			// 输出用户的名称
			view.addObject("username", username);
		}
	}

	/**
	 * 增加输出变量username、messages及用户余额到freemarker模板
	 * 
	 * @param view
	 * @param request
	 */
	public void setUserAccountInfo(ModelAndView view, HttpServletRequest request) {

		setUserInfo(view, request);
	}
}
