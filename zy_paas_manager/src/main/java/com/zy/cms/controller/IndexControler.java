package com.zy.cms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zy.cms.common.ZyLogger;

@Controller
public class IndexControler {

	private static final ZyLogger logger = ZyLogger.getLogger(IndexControler.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("redirect:/login.html");
	}

	@RequestMapping(value = "/version", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String versionInfo(HttpServletRequest req, HttpServletResponse response) {
		String result = "【zy-paas-mangaer】当前版本：" + "zy-paas-mangaer-v1.01.05";
		logger.info(result);
		return result;
	}

}
