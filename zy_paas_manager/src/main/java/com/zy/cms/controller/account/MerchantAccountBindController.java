package com.zy.cms.controller.account;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zy.cms.common.Constant;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.enums.StartFlagEnum;
import com.zy.cms.service.manager.AccountBindInfoService;
import com.zy.cms.service.manager.DepartmentService;
import com.zy.cms.service.manager.UserService;
import com.zy.cms.service.master.MerchantAccountService;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.AccountBindInfo;
import com.zy.cms.vo.Department;
import com.zy.cms.vo.MerchantAccount;
import com.zy.cms.vo.User;
import com.zy.cms.vo.query.AccountBindQuery;
import com.zy.cms.vo.query.AccountQuery;
import com.zy.cms.vo.query.ResultQuery;
import com.zy.cms.vo.query.UserQuery;

@Controller
@RequestMapping("/account_bind")
public class MerchantAccountBindController {
	
	private static final ZyLogger logger = ZyLogger.getLogger(MerchantAccountBindController.class);
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private AccountBindInfoService accountBindInfoService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MerchantAccountService merchantAccountService;

	@RequestMapping("/to_list")
	public ModelAndView toList(){
		ModelAndView mv = new ModelAndView("/accountbind/account_bind_list");
		List<Department> deptList = departmentService.getAllDepartment();
		mv.addObject("deptList", deptList);
		return mv;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/list_data")
	public ModelAndView getAccountBindListData(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("params") String params){
		ModelAndView mv = new ModelAndView("/accountbind/account_bind_list_data");
		try {
			AccountBindQuery query = null;
			logger.info("【客户绑定查询列表】参数={0}", new Object[] { params }, null);
			if (!StringUtil.isEmpty(params)) {
				query = JsonUtil.parseToObject(params, AccountBindQuery.class);
			}
			if (query == null) {
				query = new AccountBindQuery();
			}

			Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query
					.getPageNum() - 1);
			Integer pageSize = query.getPageSize() == 0 ? Constant.PAGE_SIZE_20
					: query.getPageSize();
			query.setPageNum(pageNum);
			query.setPageSize(pageSize);
			
			Integer total = accountBindInfoService.getCountByQuery(query);
			List<AccountBindInfo> list = accountBindInfoService.getListByQuery(query);
			for(AccountBindInfo accountBindInfo : list){
				String department = accountBindInfo.getDepartment();
				if(StringUtils.isBlank(department) && null != accountBindInfo.getDeptNo()){
					Department departmentInfo = departmentService.getById(accountBindInfo.getDeptNo());
					if(null != departmentInfo){
						accountBindInfo.setDepartment(departmentInfo.getName());
					}
				}
			}
			
			// 构建查询结果对象
			ResultQuery pgdata = new ResultQuery();
			pgdata.setPage_num(Long.valueOf(query.getPageNum() + 1));
			pgdata.setPage_size(Long.valueOf(query.getPageSize()));
			pgdata.setTotal(Long.valueOf(total));
			pgdata.setData(list);
			mv.addObject("pgdata", pgdata);
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【获取通道绑定列表】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
			return mv;
		}
		
	}
	
	@RequestMapping("/to_bind")
	public ModelAndView toBind(){
		ModelAndView mv = new ModelAndView("/accountbind/add_account_bind");
		return mv;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/user_list_data")
	public ModelAndView userListData(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("params") String params){
		ModelAndView mv = new ModelAndView("/accountbind/user_list_data");
		try {
			UserQuery query = null;
			logger.info("【客户绑定查询列表】参数={0}", new Object[] { params }, null);
			if (!StringUtil.isEmpty(params)) {
				query = JsonUtil.parseToObject(params, UserQuery.class);
			}
			if (query == null) {
				query = new UserQuery();
			}
			
			Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query
					.getPageNum() - 1);
			Integer pageSize = query.getPageSize() == 0 ? Constant.PAGE_SIZE_20
					: query.getPageSize();
			query.setPageNum(pageNum);
			query.setPageSize(pageSize);
			query.setState(StartFlagEnum.ENABLE.getType());
			
			List<User> list = userService.queryUsers(query);
			for(User user : list){
				String department = user.getDepartment();
				if(StringUtils.isBlank(department) && null != user.getDeptNo()){
					Department departmentInfo = departmentService.getById(user.getDeptNo());
					if(null != departmentInfo){
						user.setDepartment(departmentInfo.getName());
					}
				}
			}
			
			// 构建查询结果对象
			ResultQuery pgdata = new ResultQuery();
			pgdata.setPage_num(Long.valueOf(query.getPageNum() + 1));
			pgdata.setPage_size(Long.valueOf(query.getPageSize()));
			pgdata.setTotal(Long.valueOf(list.size()));
			pgdata.setData(list);
			mv.addObject("pgdata", pgdata);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【获取用户列表】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
		}
		return mv;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/acc_list_data")
	public ModelAndView accListData(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("params") String params){
		ModelAndView mv = new ModelAndView("/accountbind/account_list_data");
		try {
			
			AccountQuery query = null;
			logger.info("【客户绑定查询列表】参数={0}", new Object[] { params }, null);
			if (!StringUtil.isEmpty(params)) {
				query = JsonUtil.parseToObject(params, AccountQuery.class);
			}
			if (query == null) {
				query = new AccountQuery();
			}

			Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query
					.getPageNum() - 1);
			Integer pageSize = query.getPageSize() == 0 ? Constant.PAGE_SIZE_5
					: query.getPageSize();
			query.setPageNum(pageNum);
			query.setPageSize(pageSize);
			query.setIsLocked(0);
			
			Integer total = merchantAccountService.queryMerchantAccountCount(query);
			List<MerchantAccount> list = merchantAccountService.queryMerchantAccounts(query);
			
			// 构建查询结果对象
			ResultQuery pgdata = new ResultQuery();
			pgdata.setPage_num(Long.valueOf(query.getPageNum() + 1));
			pgdata.setPage_size(Long.valueOf(query.getPageSize()));
			pgdata.setTotal(Long.valueOf(total));
			pgdata.setData(list);
			mv.addObject("pgdata", pgdata);
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【获取通道绑定列表】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
			return mv;
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/add_account_bind")
	public ModelAndView getAccListData(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("params") String params){
		ModelAndView mv = new ModelAndView("/accountbind/add_account_list");
		try {
			
			AccountQuery query = null;
			logger.info("【客户绑定查询列表】参数={0}", new Object[] { params }, null);
			if (!StringUtil.isEmpty(params)) {
				query = JsonUtil.parseToObject(params, AccountQuery.class);
			}
			if (query == null) {
				query = new AccountQuery();
			}
			
			Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query
					.getPageNum() - 1);
			Integer pageSize = query.getPageSize() == 0 ? Constant.PAGE_SIZE_5
					: query.getPageSize();
			query.setPageNum(pageNum);
			query.setPageSize(pageSize);
			query.setIsLocked(0);
			
			Integer total = merchantAccountService.queryMerchantAccountCount(query);
			List<MerchantAccount> list = merchantAccountService.queryMerchantAccounts(query);
			
			// 构建查询结果对象
			ResultQuery pgdata = new ResultQuery();
			pgdata.setPage_num(Long.valueOf(query.getPageNum() + 1));
			pgdata.setPage_size(Long.valueOf(query.getPageSize()));
			pgdata.setTotal(Long.valueOf(total));
			pgdata.setData(list);
			mv.addObject("pgdata", pgdata);
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【获取通道绑定列表】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
			return mv;
		}
	}
	
	@RequestMapping("/do_bind")
	@ResponseBody
	public boolean doBind(String username, String accounts){
		boolean result = false;
		try {
			result = accountBindInfoService.doAccountbind(username, accounts);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("【账号绑定】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
		}
		return result;
	}
	
	@RequestMapping("/to_modify_bind/{username}")
	public ModelAndView toBindDataList(@PathVariable String username){
		ModelAndView mv = new ModelAndView("/accountbind/modify_account_bind");
		mv.addObject("username", username);
		return mv;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/get_bindacc_list")
	public ModelAndView bindeAccDataList(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("params") String params){
		ModelAndView mv = new ModelAndView("/accountbind/bind_account_list");
		try {
			AccountBindQuery query = null;
			logger.info("【客户绑定查询列表】参数={0}", new Object[] { params }, null);
			if (!StringUtil.isEmpty(params)) {
				query = JsonUtil.parseToObject(params, AccountBindQuery.class);
			}
			if (query == null) {
				query = new AccountBindQuery();
			}
			
			Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query
					.getPageNum() - 1);
			Integer pageSize = query.getPageSize() == 0 ? Constant.PAGE_SIZE_5
					: query.getPageSize();
			query.setPageNum(pageNum);
			query.setPageSize(pageSize);
			
//			int total = accountBindInfoService.getAccBindCountByQuery(query);
			List<AccountBindInfo> list = accountBindInfoService.getAccBindListByQuery(query);
			
			// 构建查询结果对象
			ResultQuery pgdata = new ResultQuery();
//			pgdata.setPage_num(Long.valueOf(query.getPageNum() + 1));
//			pgdata.setPage_size(Long.valueOf(query.getPageSize()));
//			pgdata.setTotal(Long.valueOf(total));
			pgdata.setData(list);
			mv.addObject("pgdata", pgdata);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("【账号绑定】查询账号绑定出错,原因是{0}", new Object[] { e.getMessage() }, null);
		} 
		return mv;
	}
	
	@RequestMapping("/check_bind")
	@ResponseBody
	public boolean checkBind(String username, String apiAccount){
		boolean result = false;
		try{
			result = accountBindInfoService.checkBind(username, apiAccount);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("【账号绑定】查询账号是否已绑定出错,原因是{0}", new Object[] { e.getMessage() }, null);
		}
		return result;
	}
	
	@RequestMapping("/accountbind_del/{username}")
	public ModelAndView doAccBindDel(@PathVariable String username){
		ModelAndView mv = new ModelAndView("forward:/account_bind/to_list");
		try{
			accountBindInfoService.deleteByUsername(username);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("【账号绑定】查询账号是否已绑定出错,原因是{0}", new Object[] { e.getMessage() }, null);
		}
		return mv;
	}
	
	@RequestMapping("/getuser_accbind")
	@ResponseBody
	public String getUserAccBind(String username){
		List<AccountBindInfo> list = null;
		try{
			list = accountBindInfoService.getByUserName(username);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("【账号绑定】查询账号绑定用户出错,原因是{0}", new Object[] { e.getMessage() }, null);
		}
		return JsonUtil.objectToJson(list);
	}
	
}
