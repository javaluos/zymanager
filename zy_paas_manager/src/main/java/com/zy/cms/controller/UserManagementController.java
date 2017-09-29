package com.zy.cms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zy.cms.common.CommonService;
import com.zy.cms.common.Constant;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.enums.StartFlagEnum;
import com.zy.cms.service.manager.DepartmentService;
import com.zy.cms.service.manager.MenuService;
import com.zy.cms.service.manager.UserMenuService;
import com.zy.cms.service.manager.UserService;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.MD5Util;
import com.zy.cms.util.MenuRoot;
import com.zy.cms.util.MenuUtils;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.Department;
import com.zy.cms.vo.Menu;
import com.zy.cms.vo.User;
import com.zy.cms.vo.UserMenu;
import com.zy.cms.vo.query.ResultQuery;
import com.zy.cms.vo.query.UserQuery;

@Controller
@RequestMapping(value = "/user")
public class UserManagementController {

	private static final ZyLogger logger = ZyLogger.getLogger(UserManagementController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private MenuService menuService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private UserMenuService userMenuService;

	@RequestMapping("/user_list")
	public ModelAndView getUserList() {
		List<User> userList = userService.findAll();
		ModelAndView mv = new ModelAndView("/usermanagement/user_list");
		mv.addObject("userList", userList);
		return mv;
	}

	/**
	 * 查询用户信息列表Data
	 * 
	 * @param request
	 * @return mv JSPTable
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/userlist_data", produces = "application/json")
	public ModelAndView queryMerchantAccounts(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("params") String params) {

		logger.info("【用户管理查询】params=" + params);

		// 定义结果
		ModelAndView mv = new ModelAndView("/usermanagement/user_list_data");
		try {

			UserQuery query = null;
			if (!StringUtil.isEmpty(params)) {
				query = JsonUtil.parseToObject(params, UserQuery.class);
			}
			if (query == null) {
				query = new UserQuery();
			}

			// 设置参数
			if (!StringUtil.isEmpty(query.getUsername())) {
				query.setUsername(query.getUsername().trim());
			} else {
				query.setUsername("");
			}
			query.setState(StartFlagEnum.ENABLE.getType());
			Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query.getPageNum() - 1);
			Integer pageSize = query.getPageSize() == 0 ? Constant.PAGE_SIZE_20 : query.getPageSize();
			query.setPageNum(pageNum);
			query.setPageSize(pageSize);

			int total = userService.queryUserCount(query);
			List<User> list = userService.queryUsers(query);
			for (User user : list) {
				Integer deptNo = user.getDeptNo();
				if (null != deptNo) {
					Department department = departmentService.getById(user.getDeptNo());
					if (department != null && department.getName() != null) {
					  user.setDepartment(department.getName());
					}
				}
			}

			// 构建查询结果对象
			ResultQuery pgdata = new ResultQuery();
			pgdata.setPage_num(Long.valueOf(query.getPageNum() + 1));
			pgdata.setPage_size(Long.valueOf(query.getPageSize()));
			pgdata.setTotal(Long.valueOf(total));
			pgdata.setData(list);

			// 设定查询参数对象['pgdata'为JSP参数,不随意修改]
			mv.addObject("pgdata", pgdata);
		} catch (Exception e) {
			logger.error("【用户管理查询】error=" + e.getLocalizedMessage());
		}
		return mv;
	}

	@RequestMapping("/to_user_add")
	public ModelAndView toUserAdd() {
		ModelAndView mv = new ModelAndView("/usermanagement/user_add");
		List<Menu> menuList = menuService.getAllEnableMenus((short) StartFlagEnum.ENABLE.getType());
		List<MenuRoot> menuRootList = MenuUtils.initMUList(menuList);
		mv.addObject("menuRootList", menuRootList);
		List<Department> departmentList = departmentService.getAllDepartment();
		mv.addObject("departmentList", departmentList);
		return mv;
	}

	@RequestMapping("check_username_exist")
	@ResponseBody
	public boolean checkUsernameExist(String userName) {
		boolean result = false;
		User user = userService.findUserByName(userName);
		if (null != user) {
			result = true;
		}
		return result;
	}

	@RequestMapping("/do_user_add")
	public ModelAndView doUserAdd(Model model, String userName, String fullname, String phone, Integer department,
			String menu, String deptName) {
		boolean result = false;
		ModelAndView mv = new ModelAndView("forward:/user/user_list");
		result = userService.saveUserInfo(userName, fullname, phone, department, menu, deptName);
		if (result) {
			return mv;
		}
		model.addAttribute("msg", "新增账号信息失败.");
		return new ModelAndView("/user/to_user_add");
	}

	@RequestMapping("/user_delete/{userId}")
	public ModelAndView userDelete(@PathVariable("userId") Integer userId) {
		ModelAndView mv = new ModelAndView("forward:/user/user_list");
		try {
			userService.deleteByUserId(userId);
		} catch (Exception e) {
			logger.error("删除用户error:" + e.getMessage());
			e.printStackTrace();
		}
		return mv;
	}

	@RequestMapping("/check_user_pwd")
	@ResponseBody
	public boolean checkUserPwd(HttpServletRequest request, String pwd) {
		boolean result = false;
		User user = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
		String password = user.getPassword();
		if (StringUtils.isNotBlank(pwd) && MD5Util.md5Hex(pwd).equals(password)) {
			result = true;
		}
		return result;
	}

	@RequestMapping("/init_pwd/{userId}")
	@ResponseBody
	public ModelAndView initPwd(@PathVariable("userId") Integer userId) {
		ModelAndView mv = new ModelAndView("forward:/user/user_list");
		try {
			userService.initPwd(userId);
		} catch (Exception e) {
			logger.error("初始化密码error:" + e.getMessage());
			e.printStackTrace();
		}
		return mv;
	}

	@RequestMapping("/update_pwd")
	@ResponseBody
	public boolean updatePwd(HttpServletRequest request, String newpwd) {
		boolean result = false;
		User user = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
		result = userService.updatePwd(user.getUserId(), newpwd);
		return result;
	}

	@RequestMapping("/to_user_update/{userId}")
	public ModelAndView toUserUpdate(@PathVariable("userId") Integer userId) {
		ModelAndView mv = new ModelAndView("/usermanagement/user_update");
		User user = userService.findUserByUserId(userId);
		mv.addObject("user", user);
		Department department = departmentService.getById(user.getDeptNo());
		mv.addObject("department", department);
		List<Menu> menuList = menuService.getAllEnableMenus((short) StartFlagEnum.ENABLE.getType());
		List<MenuRoot> menuRootList = MenuUtils.initMUList(menuList);

		UserMenu userMenu = userMenuService.getByUserName(user.getUserName());
		boolean containtFlag = false;
		for (MenuRoot rt : menuRootList) {
			String menus = userMenu.getMenus();
			if (StringUtils.isNotBlank(menus)) {
				containtFlag = userMenu.getMenus().contains(rt.getMenuid());
				rt.setIsChecked(containtFlag ? 1 : 0);
				for (MenuRoot rc : rt.getChildList()) {
					containtFlag = menus.contains(rc.getMenuid());
					rc.setIsChecked(containtFlag ? 1 : 0);
				}
			}
		}
		mv.addObject("menuRootList", menuRootList);
		return mv;
	}

	@RequestMapping("/do_user_update")
	public ModelAndView UserUpdate(String userName, String menu) {
		ModelAndView mv = new ModelAndView("forward:/user/user_list");
		try {
			userMenuService.updateUserMenus(userName, menu);
		} catch (Exception e) {
			logger.error("初始化密码error:" + e.getMessage());
			e.printStackTrace();
		}
		return mv;
	}

}
