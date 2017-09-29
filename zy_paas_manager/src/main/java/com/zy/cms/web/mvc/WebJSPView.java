package com.zy.cms.web.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.view.JstlView;

/**
 * JSTL统一渲染输出.
 * 
 * @author fenglb
 * @date 2016-8-20 下午5:32:18
 */
public class WebJSPView extends JstlView {

	@Override
	protected void exposeHelpers(HttpServletRequest request) throws Exception {
		
		request.setAttribute("ctx", request.getContextPath());
		super.exposeHelpers(request);
	}


}
