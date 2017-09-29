package com.zy.cms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zy.cms.common.CommonService;
import com.zy.cms.common.Constant;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.vo.User;
import com.zy.cms.vo.UserCurrent;
import com.zy.cms.vo.UserInfo;

@Controller
@RequestMapping(value = "/user")
public class AccountController {

	private static final ZyLogger logger = ZyLogger
			.getLogger(AccountController.class);
	@Resource
	private CommonService commonService;

	@Resource
	private UserInfo userInfo;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView getMerchantAccountByNm(HttpServletRequest req)
			throws Exception {
		User us = commonService.valideUser(req, Constant.USER_SESSION_UID);
		ModelAndView mv = new ModelAndView("/user/index");
		if (us == null) {
			logger.warn("找不到用户，或者用户的登陆cookie已经过期");
			return new ModelAndView("redirect:/login.html");
		} else {

			userInfo.setUserAccountInfo(mv, req);
			/* 下面表示后台左右菜单栏，当前的选择状态为管理中心 */
			UserCurrent current = new UserCurrent();
			current.setManagerCenter(Constant.TRUE_STR);
			mv.addObject("current", current);

		}
		return mv;
	}
}
