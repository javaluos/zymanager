package com.zy.cms.controller.monitor;

import com.zy.cms.common.CommonService;
import com.zy.cms.common.Constant;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.service.manager.SmsChannelBalMonitorService;
import com.zy.cms.service.manager.SmsChannelBalNoticeService;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.SmsChannelBalNoticeSetting;
import com.zy.cms.vo.User;
import com.zy.cms.vo.channel.SmsChannel;
import com.zy.cms.vo.query.ResultQuery;
import com.zy.cms.vo.query.SmsChannelQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by luos on 2017/4/12.
 */
@RequestMapping("/channel_balance")
@Controller
public class SmsChannelBalanceMonitorAction {

    private static final ZyLogger logger = ZyLogger.getLogger(SmsChannelBalanceMonitorAction.class);

    @Resource
    private CommonService commonService;

    @Autowired
    private SmsChannelBalMonitorService smsChannelBalMonitorService;

    @Autowired
    private SmsChannelBalNoticeService smsChannelBalNoticeService;

    @RequestMapping("/to_list")
    public ModelAndView toList(){
        ModelAndView mv = new ModelAndView("/monitor/chnbal_monitor_list");
        return mv;
    }

    @RequestMapping(value = "/chnbal_monitor_list_data")
    public ModelAndView queryListData(HttpServletRequest request, HttpServletResponse response,
                                      @RequestParam("params") String params)  {
        User user = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
        ModelAndView mv = new ModelAndView("/monitor/chnbal_monitor_list_data");
        try {
            SmsChannelQuery query = null;
            logger.info("【 通道余额监控列表】参数={0}", new Object[] { params }, null);
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

            Integer total = smsChannelBalMonitorService.queryCountByEntity(query);
            List<SmsChannel> list = smsChannelBalMonitorService.queryListByEntity(query);

            // 构建查询结果对象
            ResultQuery pgdata = new ResultQuery();
            pgdata.setPage_num(Long.valueOf(query.getPageNum() + 1));
            pgdata.setPage_size(Long.valueOf(query.getPageSize()));
            pgdata.setTotal(Long.valueOf(total));
            pgdata.setData(list);
            // 设定查询参数对象['pgdata'为JSP参数,不随意修改]
            mv.addObject("pgdata", pgdata);
            return mv;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("【获取通道余额监控列表】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
            return mv;
        }

    }

    @RequestMapping("/to_setting")
    public ModelAndView toSetting(){
        ModelAndView mv = new ModelAndView("/monitor/chnbal_monitor_setting");
        try{
            SmsChannelBalNoticeSetting setting = smsChannelBalNoticeService.getSetting();
            mv.addObject("setting", setting);
            return mv;
        }catch (Exception e){
            e.printStackTrace();
            logger.error("【获取通道余额告警设置】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
            return mv;
        }
    }

    @RequestMapping("/save_setting")
    @ResponseBody
    public boolean saveSetting(String noticeType, String noticePhone, String noticeEmail){
        boolean result = false;
        try{
            result = smsChannelBalNoticeService.saveSetting(noticeType, noticePhone, noticeEmail);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("【保存通道余额告警设置】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
        }
        return result;
    }

    @RequestMapping("/delete_monitor")
    @ResponseBody
    public boolean deleteMonitor(String channelId){
        boolean result = false;
        try {
            result = smsChannelBalMonitorService.deleteMonitor(channelId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("【删除通道余额监控】出现错误,原因是{0}", new Object[] { e.getMessage() }, null);
        }
        return result;
    }

}
