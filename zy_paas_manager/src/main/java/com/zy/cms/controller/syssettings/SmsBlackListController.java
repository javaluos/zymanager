package com.zy.cms.controller.syssettings;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zy.cms.common.CommonService;
import com.zy.cms.common.Constant;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.service.manager.excel.SmsBlackListExcelService;
import com.zy.cms.service.master.SmsBlackListService;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.SmsBlackListInfo;
import com.zy.cms.vo.User;
import com.zy.cms.vo.WebResult;
import com.zy.cms.vo.query.ResultQuery;
import com.zy.cms.vo.query.SmsBlackListQuery;

/**
 * 短信黑名单列表
 *
 * @author JasonXu
 */
@RequestMapping("/sms_black_list")
@Controller
public class SmsBlackListController {

    private static final ZyLogger logger = ZyLogger.getLogger(SmsBlackListController.class);

    @Autowired
    private CommonService commonService;

    @Autowired
    private SmsBlackListService smsBlackListService;

    @Autowired
    private SmsBlackListExcelService smsBlackListExcelService;

    /**
     * 显示黑名单列表
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/to_list/{groupId}")
    public ModelAndView toList(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer groupId) {
        // 定义结果
        ModelAndView mv = new ModelAndView("/syssettings/sms_black_list");
        mv.addObject("groupId", groupId);
        return mv;
    }

    /**
     * 加载黑名单数据
     *
     * @param request
     * @param response
     * @param params
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/list_data", produces = "application/json")
    public ModelAndView getListData(HttpServletRequest request,
                                    HttpServletResponse response, @RequestParam("params") String params) {
        ModelAndView mv = new ModelAndView("/syssettings/sms_black_list_data");
        try {
            SmsBlackListQuery query = null;
            logger.info("【短信黑名单查询列表】参数={0}", new Object[]{params}, null);
            if (!StringUtil.isEmpty(params)) {
                query = JsonUtil.parseToObject(params, SmsBlackListQuery.class);
            }
            if (query == null) {
                query = new SmsBlackListQuery();
            }

            Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query
                    .getPageNum() - 1);
            Integer pageSize = query.getPageSize() == 0 ? Constant.PAGE_SIZE_20
                    : query.getPageSize();
            query.setPageNum(pageNum);
            query.setPageSize(pageSize);

            Integer total = smsBlackListService.queryCountByEntity(query);
            List<SmsBlackListInfo> list = smsBlackListService.queryListByEntity(query);

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
            logger.error("【获取短信黑名单列表】出现错误,原因是{0}", new Object[]{e.getMessage()}, null);
            return mv;
        }
    }

    /**
     * 跳转到添加黑名单页面
     *
     * @return
     */
    @RequestMapping("/to_add/{groupId}")
    public ModelAndView toAdd(@PathVariable Integer groupId) {
        ModelAndView mv = new ModelAndView("/syssettings/sms_black_add");
        mv.addObject("groupId", groupId);
        return mv;
    }

    /**
     * 添加黑名单
     *
     * @param businessname
     * @param mobile
     * @param remark
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/do_add/{groupId}")
    @ResponseBody
    public String doAdd(@PathVariable Integer groupId, String businessname, String mobile, String remark,
                          HttpServletRequest request, HttpServletResponse response) {
        logger.info("【短信黑名单添加】businessname={0},mobile={1},remark={2}", new Object[]{businessname, mobile, remark}, null);
        int result = 0;
        User user = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
        try {
            result = smsBlackListService.addSmsBlackList(groupId, businessname, mobile, remark);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("【添加短信黑名单】出现错误,原因是{0}", new Object[]{e.getMessage()}, null);
        }
        return JsonUtil.objectToJson(result);
    }

    /**
     * 删除黑名单
     *
     * @param ids
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/do_delete/{groupId}")
    @ResponseBody
    public String doDelete(@RequestParam(value = "mobiles", required = true) String mobiles, @PathVariable Integer groupId,
                           HttpServletRequest request, HttpServletResponse response) {
        logger.info("【短信黑名单删除】mobiles={0}", new Object[]{mobiles}, null);
        int result = 0;
        try {
            result = smsBlackListService.deleteMobileFromGroup(groupId, mobiles);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("【添加短信黑名单】出现错误,原因是{0}", new Object[]{e.getMessage()}, null);
        }
        return JsonUtil.objectToJson(result);
    }


    /**
     * 短信黑名单导出功能
     *
     * @param request
     * @param params
     * @return
     */
    @RequestMapping(value = "/export")
    @ResponseBody
    public WebResult exportBlackList(HttpServletRequest request, @RequestParam(value = "params", required = false) String params) {

        logger.info("【短信黑名单导出】参数={0}", new Object[]{params}, null);
        WebResult webRs = new WebResult();
        User us = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
        if (us == null) {
            logger.warn("用户登录超时!");
            webRs.setCode(-1);
            webRs.setMsg("用户登录超时!");
        }

        SmsBlackListQuery query = null;
        if (!StringUtil.isEmpty(params)) {
            query = JsonUtil.parseToObject(params, SmsBlackListQuery.class);
        }
        if (query == null) {
            query = new SmsBlackListQuery();
        }

        Integer pageNum = (query.getPageNum() - 1) <= 0 ? 0 : (query
                .getPageNum() - 1);
        query.setPageNum(pageNum);
        query.setPageSize(1000000);// 最大值

        // 调用导出业务
        String realPath = request.getSession().getServletContext().getRealPath("");
        webRs = smsBlackListExcelService.exportBlackExcel(query, realPath, request.getContextPath());

        logger.info("【短信黑名单导出】结束...");
        return webRs;
    }

    @RequestMapping("/check_phone")
    @ResponseBody
    public String checkPhoneInBlackList(String mobiles) {
        String phones = "";
        try {
            phones = smsBlackListService.checkPhoneInWhiteList(mobiles);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("【校验黑名单号码】出现异常，原因是{0}", new Object[]{e.getMessage()}, null);
        }
        return phones;
    }

}
