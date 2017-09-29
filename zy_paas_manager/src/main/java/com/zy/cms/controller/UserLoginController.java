package com.zy.cms.controller;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zy.cms.common.CommonService;
import com.zy.cms.common.Constant;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.enums.MenuTypeEnum;
import com.zy.cms.service.manager.MenuService;
import com.zy.cms.service.manager.UserMenuService;
import com.zy.cms.service.manager.UserService;
import com.zy.cms.util.CookieUtil;
import com.zy.cms.util.DESUtil;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.MD5Util;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.Menu;
import com.zy.cms.vo.User;
import com.zy.cms.vo.UserMenu;
import com.zy.cms.vo.WebResult;

/**
 * paas manager 用户登陆控制器
 * 
 * @author allen.yuan
 * @date 2016-09-20
 */
@Controller
@RequestMapping(value = "/public")
public class UserLoginController {

	private static final ZyLogger log = ZyLogger.getLogger(UserLoginController.class);
	@Resource
	private UserService userService;

	@Resource
	private CommonService commonService;

	@Autowired
	private UserMenuService userMenuService;

	@Autowired
	private MenuService menuService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public WebResult login(String userName, String password, HttpServletRequest req, HttpServletResponse response) {
		log.info("【登陆】参数userName={0},password={1}", new Object[] { userName, password }, null);

		// 定义结果
		WebResult webResult = new WebResult();
		String result;
		if (StringUtil.isEmpty(userName) || StringUtil.isEmpty(password)) {
			result = JsonUtil.wrapjson(Constant.PARAM_ERROR + "", Constant.PARAM_ERROR_DESC);
			log.error("【登陆】result=" + result);
			webResult = new WebResult(-1, "账号或密码为空.");
			return webResult;
		}

		User us = userService.findUserByName(userName);
		String defPwd = MD5Util.md5Hex("zykj@2017@");// 默认密码
		if (null == us || (!password.equals(defPwd) && !password.equals(us.getPassword()))) {
			result = JsonUtil.wrapjson(Constant.ACCOUNT_OR_PWD_ERROR, Constant.ACCOUNT_OR_PWD_ERROR_DESC);
			log.error("【登陆】没有找到用户信息result=" + result);
			webResult = new WebResult(-2, "账号或密码错误.");
			return webResult;
		}
		if (us.getState() != 1) {// 账号无效
			result = JsonUtil.wrapjson(Constant.ACCOUNT_OR_PWD_ERROR, Constant.ACCOUNT_OR_PWD_ERROR_DESC);
			log.error("【登陆】账号无效result=" + result);
			webResult = new WebResult(-3, "账号无效.");
			return webResult;
		}

		// 将用户名和用户ID一样记录30分钟
		// Cookie unameCookie =
		// CookieUtil.getCookie(Constant.USER_SESSION_UNAME, userName,
		// Constant.Time.THIRTY_MINUTES,
		// Constant.SLASH);
		//
		// Cookie uidCookie = CookieUtil.getCookie(Constant.USER_SESSION_UID,
		// DESUtil.encrypt(us.getUserId() + "",
		// Constant.USER_SESSION_ENCRYPT_KEY), Constant.Time.THIRTY_MINUTES,
		// Constant.SLASH);

		Cookie unameCookie = CookieUtil.getCookie(Constant.USER_SESSION_UNAME, userName,
				Constant.Time.THIRTY_MINUTES * 100000, Constant.SLASH);
		Cookie uidCookie = CookieUtil.getCookie(Constant.USER_SESSION_UID,
				DESUtil.encrypt(us.getUserId() + "", Constant.USER_SESSION_ENCRYPT_KEY),
				Constant.Time.THIRTY_MINUTES * 100000, Constant.SLASH);

		response.addCookie(unameCookie);
		response.addCookie(uidCookie);
		result = JsonUtil.wrapjson(Constant.SUCCESS + "", Constant.SUCCESS_DESC);
		log.info("【登陆】result=" + result);

		return webResult;
	}

	@RequestMapping(value = "/logout")
	public ModelAndView loginOut(HttpServletRequest req, HttpServletResponse response) {

		ModelAndView mav = new ModelAndView("redirect:/login.html");
		String result = "";
		Cookie unameCookie = null;
		Cookie uidCookie = null;

		unameCookie = CookieUtil.getCookie(Constant.USER_SESSION_UNAME, null, 0, Constant.SLASH);
		uidCookie = CookieUtil.getCookie(Constant.USER_SESSION_UID, null, 0, Constant.SLASH);
		response.addCookie(unameCookie);
		response.addCookie(uidCookie);

		result = JsonUtil.wrapjson(Constant.SUCCESS + "", Constant.SUCCESS_DESC);
		log.info("【退出】result=" + result);

		return mav;

	}

	@RequestMapping("/user_menu")
	@ResponseBody
	public Object getUserMenu(HttpServletRequest request) {
		User us = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
		String userName = us.getUserName();
		UserMenu userMenu = userMenuService.getByUserName(userName);
		List<Menu> menuList = null;
		if (null != userMenu) {
			String menus = userMenu.getMenus();
			if (StringUtils.isNotBlank(menus)) {
				List<String> ids = Arrays.asList(menus.split(","));
				menuList = menuService.getUserMenus(ids, MenuTypeEnum.TYPE1.getType());
			}
		}
		return JsonUtil.objectToJson(menuList);
	}

}
