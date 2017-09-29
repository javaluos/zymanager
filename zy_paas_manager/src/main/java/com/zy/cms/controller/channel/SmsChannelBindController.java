package com.zy.cms.controller.channel;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zy.cms.common.CommonService;
import com.zy.cms.common.Constant;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.enums.ChannelPropertyEnum;
import com.zy.cms.enums.ChannelTypeEnum;
import com.zy.cms.service.master.MerchantAccountService;
import com.zy.cms.service.master.SmsChannelBindService;
import com.zy.cms.service.master.SmsChannelService;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.MerchantAccount;
import com.zy.cms.vo.User;
import com.zy.cms.vo.channel.SmsChannel;
import com.zy.cms.vo.channel.SmsChannelBandVo;
import com.zy.cms.vo.query.AccountQuery;
import com.zy.cms.vo.query.ResultQuery;
import com.zy.cms.vo.query.SmsChannelBindQuery;
import com.zy.cms.vo.query.SmsChannelQuery;

/**
 * 短信账号通道绑定Controller
 * @author luos
 * @date 
 *
 */
@Controller
@RequestMapping("/channel_bind")
public class SmsChannelBindController {
	
	private static final ZyLogger logger = ZyLogger.getLogger(SmsChannelController.class);
	
	@Autowired
	private SmsChannelBindService smsChannelBindService;
	
	@Autowired
	private MerchantAccountService merchantAccountService;
	
	@Autowired
	private SmsChannelService smsChannelService;

	@Autowired
	private CommonService commonService;
	
	@RequestMapping("/to_list")
	public ModelAndView toList(){
		ModelAndView mv = new ModelAndView("/smschannel/channel_bind_list");
		mv.addObject("channelPropertyList" , ChannelPropertyEnum.values());
		return mv;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/list_data")
	public ModelAndView getChannelBindListData(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("params") String params){
		ModelAndView mv = new ModelAndView("/smschannel/channel_bind_list_data");
		try {
				SmsChannelBindQuery query = null;
				logger.info("【通道绑定查询列表】参数={0}", new Object[] { params }, null);
				if (!StringUtil.isEmpty(params)) {
					query = JsonUtil.parseToObject(params, SmsChannelBindQuery.class);
				}
				if (query == null) {
					query = new SmsChannelBindQuery();
				}

				Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query
						.getPageNum() - 1);
				Integer pageSize = query.getPageSize() == 0 ? Constant.PAGE_SIZE_20
						: query.getPageSize();
				query.setPageNum(pageNum);
				query.setPageSize(pageSize);
				
				Integer total = smsChannelBindService.getCountByQuery(query);
				List<SmsChannelBandVo> list = smsChannelBindService.getListByQuery(query);
				
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
	
	@RequestMapping("/score_modify")
	@ResponseBody
	public boolean scoreModify(Integer id, Integer score, Integer thresholdValue){
		boolean result = false;
		try {
			result = smsChannelBindService.modifyScore(id, score, thresholdValue);
		} catch (Exception e) {
			logger.error("【修改通道评分】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("/del_bind")
	@ResponseBody
	public Object delBind(HttpServletRequest request, Integer id){
		boolean result = false;
		try {
			User user = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
			result = smsChannelBindService.deleteChannelBind(id, user.getUserName());
		} catch (Exception e) {
			logger.error("【删除通道绑定】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("/to_bind")
	public ModelAndView toBind(){
		ModelAndView mv = new ModelAndView("/smschannel/add_channel_bind");
		mv.addObject("channelTypeList", ChannelTypeEnum.values());
		List<String> provinceList = smsChannelService.getProvinceList();
		mv.addObject("provinceList", provinceList);
		mv.addObject("channelPropertyList" , ChannelPropertyEnum.values());
		return mv;
	}
	
	@RequestMapping("/to_acc_list")
	public ModelAndView toAccList(String businessname, String merchantphone){
		ModelAndView mv = new ModelAndView("/smschannel/show_account_list");
		try {
			mv.addObject("businessname", new String(businessname.getBytes("ISO-8859-1"),"UTF-8"));
			mv.addObject("merchantphone", new String(merchantphone.getBytes("ISO-8859-1"),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			logger.error("【获取账号列表】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
		}
		return mv;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/acc_list_data")
	public ModelAndView getAccListData(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("params") String params){
		ModelAndView mv = new ModelAndView("/smschannel/account_list_data");
		try {
				AccountQuery query = null;
				logger.info("【通道绑定查询列表】参数={0}", new Object[] { params }, null);
				if (!StringUtil.isEmpty(params)) {
					query = JsonUtil.parseToObject(params, AccountQuery.class);
				}
				if (query == null) {
					query = new AccountQuery();
				}

				Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query
						.getPageNum() - 1);
				Integer pageSize = query.getPageSize() == 0 ? Constant.PAGE_SIZE_20
						: query.getPageSize();
				query.setPageNum(pageNum);
				query.setPageSize(pageSize);
				
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
				logger.error("【获取账号列表】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
				return mv;
			}
	}
	
	@RequestMapping("/to_channel_list")
	public ModelAndView toChannelListData(String channelname, String channelid, String channeltype, String dtnprovince,String channelProperty){
		ModelAndView mv = new ModelAndView("/smschannel/show_channel_list");
		try {
			mv.addObject("channelname", new String(channelname.getBytes("ISO-8859-1"),"UTF-8"));
			mv.addObject("channelid", new String(channelid.getBytes("ISO-8859-1"),"UTF-8"));
			mv.addObject("channeltype", channeltype);
			mv.addObject("channelproperty", channelProperty);
			mv.addObject("dtnprovince", new String(dtnprovince.getBytes("ISO-8859-1"),"UTF-8"));
			mv.addObject("channelTypeList", ChannelTypeEnum.values());
			List<String> provinceList = smsChannelService.getProvinceList();
			mv.addObject("provinceList", provinceList);
			mv.addObject("channelPropertyList" , ChannelPropertyEnum.values());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			logger.error("【获取通道列表】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
		}
		return mv;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/channel_list_data")
	public ModelAndView getChannelListData(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("params") String params){
		ModelAndView mv = new ModelAndView("/smschannel/bind_channel_list");
		try {
			SmsChannelQuery query = null;
			logger.info("【通道绑定查询列表】参数={0}", new Object[] { params }, null);
			if (!StringUtil.isEmpty(params)) {
				query = JsonUtil.parseToObject(params, SmsChannelQuery.class);
			}
			if (query == null) {
				query = new SmsChannelQuery();
			}
			Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query
					.getPageNum() - 1);
			Integer pageSize = query.getPageSize() == 0 ? Constant.PAGE_SIZE_20
					: query.getPageSize();
			query.setPageNum(pageNum);
			query.setPageSize(pageSize);
			
			query.setStatus(1);
			Integer total = smsChannelService.querySmsChannelCountByEntity(query);
			List<SmsChannel> list = smsChannelService.querySmsChannelListByEntity(query);
			
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
			logger.error("【获取通道列表】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
			return mv;
		}
	}
	
	@RequestMapping("/get_bind_channel")
	@ResponseBody
	public String getBindChannel(String apiAccount){
		List<SmsChannelBandVo> smsChannelBindList = smsChannelBindService.getByApiAccount(apiAccount);
		return JsonUtil.objectToJson(smsChannelBindList);
	}
	
	@RequestMapping("/do_bind_channel")
	@ResponseBody
	public boolean doBindChannel(HttpServletRequest request, String apiAccount, String channels){
		boolean result = false;
		try {
			User user = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
			result = smsChannelBindService.dobindChannel(apiAccount, channels, user.getUserName());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【通道绑定】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
		}
		return result;
	}
	
	@RequestMapping("/clear_send_total")
	@ResponseBody
	public Object clearSendTotal(String apiAccount, String channelId){
		String result = "";
		try{
			result = smsChannelBindService.clearSendTotal(apiAccount, channelId);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("【通道发送清零】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
		}
		return JsonUtil.objectToJson(result);
	}
}
