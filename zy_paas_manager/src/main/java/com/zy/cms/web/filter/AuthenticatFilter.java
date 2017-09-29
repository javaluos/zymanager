package com.zy.cms.web.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zy.cms.common.Constant;
import com.zy.cms.util.CookieUtil;
import com.zy.cms.util.StringUtil;

/**
 * 权限过滤
 * 
 * @author fenglb
 */
public class AuthenticatFilter implements Filter {

	/** 会话过期或未登录 **/
	private RequestDispatcher expire;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		ServletContext ctx = filterConfig.getServletContext();
		expire = ctx.getRequestDispatcher("/login.html");
	}

	/**
	 * 登录验证
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		Map<String, String> cookies = CookieUtil.getCookies(req);
		String contentPath = req.getRequestURI();
		boolean login = Boolean.FALSE;
		String uName = cookies.get(Constant.USER_SESSION_UID);
		if (!StringUtil.isEmpty(uName)) {
			login = Boolean.TRUE;
			CookieUtil.getCookie(Constant.USER_SESSION_UID, uName, Constant.Time.THIRTY_MINUTES, Constant.SLASH);
		}
		if (login) {
			chain.doFilter(request, response);
		} else {
			String param = getParamString(req);
			if (!StringUtil.isEmpty(param)) {
				contentPath = contentPath + "?" + param;
			}
			writeRefer2Cookie(contentPath, response);
			expire.forward(request, response);
		}
	}

	/**
	 * 获取URL请求参数
	 * 
	 * @param req
	 * @return
	 */
	private String getParamString(HttpServletRequest req) {
		StringBuilder paramStr = new StringBuilder();
		Map<String, String[]> params = req.getParameterMap();
		if (params != null && params.size() > 0) {
			for (Map.Entry<String, String[]> param : params.entrySet()) {
				paramStr.append(param.getKey().toString()).append(Constant.EQUAL_MARK).append(param.getValue()[0])
						.append(Constant.AND);
			}
		}
		if (paramStr.length() > 0) {
			paramStr.deleteCharAt(paramStr.length() - 1);
		}
		return paramStr.toString();
	}

	/**
	 * 将未登陆的来源URL写到COOKIE中，有效期1个小时
	 * 
	 * @param url
	 * @param response
	 */
	private void writeRefer2Cookie(String url, ServletResponse response) {
		Cookie cookie = new Cookie(Constant.NOT_LOGIN_FROM_URL, url);
		// 一小时有效
		cookie.setMaxAge(60 * 60);
		cookie.setPath("/");
		((HttpServletResponse) response).addCookie(cookie);
	}

	@Override
	public void destroy() {
		expire = null;
	}
}
