package com.zy.cms.controller.monitor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.zy.cms.common.CommonService;
import com.zy.cms.common.Constant;
import com.zy.cms.service.manager.AccountBindInfoService;
import com.zy.cms.vo.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zy.cms.service.master.VoiceMerchantBalanceMonitorService;
import com.zy.cms.vo.VoiceMerchantBalanceMonitor;

@Controller
//@Scope("prototype")
@RequestMapping(value = "/monitor")
public class VoiceMerchantBalanceMonitorAction{

	private static final Logger log = Logger.getLogger(VoiceMerchantBalanceMonitorAction.class);

	@Resource
	private CommonService commonService;

	@Autowired
	private VoiceMerchantBalanceMonitorService voiceMerchantBalanceMonitorService;

	@Autowired
	private AccountBindInfoService accountBindInfoService;
	
	private List<VoiceMerchantBalanceMonitor> voiceMerchantBalanceMonitorList;
	
	
	public List<VoiceMerchantBalanceMonitor> getVoiceMerchantBalanceMonitorList() {
		return voiceMerchantBalanceMonitorList;
	}
	
	public void setVoiceMerchantBalanceMonitorList(List<VoiceMerchantBalanceMonitor> voiceMerchantBalanceMonitorList) {
		this.voiceMerchantBalanceMonitorList = voiceMerchantBalanceMonitorList;
	}
	
	/**
     * 分页列表查询
     * @return
     */
	@RequestMapping(value = "/list",method={RequestMethod.POST, RequestMethod.GET})
    public ModelAndView list(VoiceMerchantBalanceMonitor voiceMerchantBalanceMonitor,HttpServletRequest req) {
		User user = commonService.valideUser(req, Constant.USER_SESSION_UNAME);
		if(StringUtils.isBlank(voiceMerchantBalanceMonitor.getMerchantPhone())){
			voiceMerchantBalanceMonitor.setMerchantPhone(null);
		}
		List<String> apiAccounts = accountBindInfoService.getApiAccountByBusName(user.getUserName());
		voiceMerchantBalanceMonitor.setApiAccounts(apiAccounts);
		ModelAndView mv = new ModelAndView("/monitor/voiceMerchantBalanceMonitor_list");
        	try {
				voiceMerchantBalanceMonitorList = voiceMerchantBalanceMonitorService.queryVoiceMerchantBalanceMonitorList(voiceMerchantBalanceMonitor);
				int currentPage = 1;
				int total = 0;
				int totalPage = 0;
				int pageSize = 15;
				try {
					if(null!=req.getParameter("cp")){
						currentPage = Integer.parseInt(req.getParameter("cp")); // 当前页
					}
				} catch (Exception e) {
				}

				List<VoiceMerchantBalanceMonitor> showList = null;
				if (null != voiceMerchantBalanceMonitorList && voiceMerchantBalanceMonitorList.size() > 0) {
					total = voiceMerchantBalanceMonitorList.size();
					totalPage = (total % pageSize == 0) ? (total / pageSize)
							: (total / pageSize) + 1;
					showList = voiceMerchantBalanceMonitorList.subList((currentPage - 1) * pageSize,
							currentPage * pageSize > voiceMerchantBalanceMonitorList.size() ? voiceMerchantBalanceMonitorList.size()
									: currentPage * pageSize);
				}
				
				mv.addObject("totalCount", total);
				mv.addObject("currentPage", currentPage);
				mv.addObject("totalPage", totalPage);
				mv.addObject("pageSize", pageSize);
				mv.addObject("list", showList);
				mv.addObject("voiceMerchantBalanceMonitor", voiceMerchantBalanceMonitor);
				mv.addObject("sid", req.getParameter("sid"));
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
    		return mv;
    	
    }
    
    
    
    /**
     * 修改初始化，进入修改页面  
     * @return
     */
    @RequestMapping(value = "/preUpdate")
    public ModelAndView preUpdate(VoiceMerchantBalanceMonitor voiceMerchantBalanceMonitor ,String sid) {
			// 获取待修改的数据信息
			ModelAndView mv=null;
			try {
				voiceMerchantBalanceMonitor = voiceMerchantBalanceMonitorService.findVoiceMerchantBalanceMonitor(voiceMerchantBalanceMonitor);
				mv = new ModelAndView("/monitor/voiceMerchantBalanceMonitor_update");
				mv.addObject("voiceMerchantBalanceMonitor", voiceMerchantBalanceMonitor);
				mv.addObject("sid", sid);
			} catch (Exception e) {
				e.printStackTrace();
			}
    		return mv;
    }
    
    /**
     * 修改保存  
     * @return
     */
    @RequestMapping(value = "/update")
    public String update(VoiceMerchantBalanceMonitor voiceMerchantBalanceMonitor,String sid) {
    	try {
    		if(!StringUtils.isBlank(voiceMerchantBalanceMonitor.getNoticeTimeRangeStart1())&&!StringUtils.isBlank(voiceMerchantBalanceMonitor.getNoticeTimeRangeEnd1())){
    			voiceMerchantBalanceMonitor.setNoticeTimeRange1(voiceMerchantBalanceMonitor.getNoticeTimeRangeStart1().trim()+"-"+voiceMerchantBalanceMonitor.getNoticeTimeRangeEnd1().trim());
    		}
    		if(!StringUtils.isBlank(voiceMerchantBalanceMonitor.getNoticeTimeRangeStart2())&&!StringUtils.isBlank(voiceMerchantBalanceMonitor.getNoticeTimeRangeEnd2())){
    			voiceMerchantBalanceMonitor.setNoticeTimeRange2(voiceMerchantBalanceMonitor.getNoticeTimeRangeStart2().trim()+"-"+voiceMerchantBalanceMonitor.getNoticeTimeRangeEnd2().trim());
    		}
    		if(!StringUtils.isBlank(voiceMerchantBalanceMonitor.getNoticeTimeRangeStart3())&&!StringUtils.isBlank(voiceMerchantBalanceMonitor.getNoticeTimeRangeEnd3())){
    			voiceMerchantBalanceMonitor.setNoticeTimeRange3(voiceMerchantBalanceMonitor.getNoticeTimeRangeStart3().trim()+"-"+voiceMerchantBalanceMonitor.getNoticeTimeRangeEnd3().trim());
    		}
    		int monitorMinBalance= new   BigDecimal(voiceMerchantBalanceMonitor.getMonitorMinBalanceYuan()==null?"1":voiceMerchantBalanceMonitor.getMonitorMinBalanceYuan()).multiply(new  BigDecimal(10000)).intValue();
    		voiceMerchantBalanceMonitor.setMonitorMinBalance(monitorMinBalance);
    		voiceMerchantBalanceMonitor.setUpdateTime(new Date());
			if(null!=voiceMerchantBalanceMonitor.getId()){
				//update 
				voiceMerchantBalanceMonitor.setNoticeFlag(0);
				voiceMerchantBalanceMonitor.setNoticeTenthFlag(0);
				voiceMerchantBalanceMonitorService.updateVoiceMerchantBalanceMonitor(voiceMerchantBalanceMonitor);
			}else{
				//insert
				VoiceMerchantBalanceMonitor vbm=new VoiceMerchantBalanceMonitor();
				vbm.setApiAccount(voiceMerchantBalanceMonitor.getApiAccount());
				vbm=voiceMerchantBalanceMonitorService.findVoiceMerchantBalanceMonitorSelf(vbm);
				if(null==vbm){
					voiceMerchantBalanceMonitor.setNoticeFlag(0);
					voiceMerchantBalanceMonitor.setNoticeTenthFlag(0);
					voiceMerchantBalanceMonitorService.insertVoiceMerchantBalanceMonitor(voiceMerchantBalanceMonitor);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return "redirect:/monitor/list?sid="+sid;
    }
}