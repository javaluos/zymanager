package com.zy.cms.controller.syssettings;

import com.zy.cms.common.CommonService;
import com.zy.cms.common.Constant;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.service.manager.excel.SmsWhiteListExcelService;
import com.zy.cms.service.master.SmsWhiteListService;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.SmsWhiteListInfo;
import com.zy.cms.vo.User;
import com.zy.cms.vo.WebResult;
import com.zy.cms.vo.query.ResultQuery;
import com.zy.cms.vo.query.SmsBlackListQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 短信白名单列表
 *
 * @author JasonXu
 */
@RequestMapping("/sms_white_list")
@Controller
public class SmsWhiteListController {

    private static final ZyLogger logger = ZyLogger.getLogger(SmsWhiteListController.class);

    @Autowired
    private CommonService commonService;

    @Autowired
    private SmsWhiteListService smsWhiteListService;

    @Autowired
    private SmsWhiteListExcelService smsWhiteListExcelService;

    /**
     * 显示白名单列表
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/to_list")
    public ModelAndView toList(HttpServletRequest request, HttpServletResponse response) {
        // 定义结果
        ModelAndView mv = new ModelAndView("/syssettings/sms_white_list");
        return mv;
    }

    /**
     * 加载白名单数据
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
        ModelAndView mv = new ModelAndView("/syssettings/sms_white_list_data");
        try {
            SmsBlackListQuery query = null;
            logger.info("【短信白名单查询列表】参数={0}", new Object[]{params}, null);
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

            Integer total = smsWhiteListService.queryCountByEntity(query);
            List<SmsWhiteListInfo> list = smsWhiteListService.queryListByEntity(query);

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
            logger.error("【获取短信白名单列表】出现错误,原因是{0}", new Object[]{e.getMessage()}, null);
            return mv;
        }
    }

    /**
     * 跳转到添加白名单页面
     *
     * @return
     */
    @RequestMapping("/to_add")
    public ModelAndView toAdd() {
        ModelAndView mv = new ModelAndView("/syssettings/sms_white_add");
        return mv;
    }

    /**
     * 添加白名单
     *
     * @param businessname
     * @param mobile
     * @param remark
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/do_add")
    @ResponseBody
    public String doAdd(String businessname, String mobile, String remark, HttpServletRequest request, HttpServletResponse response) {
        logger.info("【短信白名单添加】businessname={0},mobile={1},remark={2}", new Object[]{businessname, mobile, remark}, null);
        int result = 0;
        User user = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
        try {
            result = smsWhiteListService.addSmsWhiteListInfo(businessname, mobile, remark);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("【添加短信白名单】出现错误,原因是{0}", new Object[]{e.getMessage()}, null);
        }
        return JsonUtil.objectToJson(result);
    }

    /**
     * 删除白名单
     *
     * @param ids
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/do_delete")
    @ResponseBody
    public String doDelete(@RequestParam(value = "mobiles", required = true) String mobiles, HttpServletRequest request, HttpServletResponse response) {
        logger.info("【短信白名单删除】mobiles={0}", new Object[]{mobiles}, null);
        int result = 0;
        try {
            if (StringUtils.isNotEmpty(mobiles)) {
                String mobileArray[] = mobiles.split(",");
                if (mobileArray.length > 0) {
                    for (String mobile : mobileArray) {
                        int success = smsWhiteListService.deleteByMobile(mobile);
                        result = result + success;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("【添加短信白名单】出现错误,原因是{0}", new Object[]{e.getMessage()}, null);
        }
        return JsonUtil.objectToJson(result);
    }

    /**
     * 短信白名单导出功能
     *
     * @param request
     * @param params
     * @return
     */
    @RequestMapping(value = "/export")
    @ResponseBody
    public WebResult exportWhiteList(HttpServletRequest request, @RequestParam(value = "params", required = false) String params) {
        logger.info("【短信白名单导出】参数={0}", new Object[]{params}, null);
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
        webRs = smsWhiteListExcelService.exportWhiteExcel(query, realPath, request.getContextPath());

        logger.info("【短信白名单导出】结束...");
        return webRs;
    }

    @RequestMapping("/check_phone")
    @ResponseBody
    public String checkPhoneInBlackList(String mobiles){
        String phones = "";
        try {
            phones = smsWhiteListService.checkPhoneInBlackList(mobiles);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("【校验白名单号码】出现异常，原因是{0}", new Object[]{e.getMessage()}, null);
        }
        return phones;
    }

}
