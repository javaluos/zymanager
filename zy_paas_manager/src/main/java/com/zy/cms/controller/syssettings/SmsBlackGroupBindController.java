package com.zy.cms.controller.syssettings;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.zy.cms.service.master.MerchantAccountService;
import com.zy.cms.service.master.SmsBlackGroupBindService;
import com.zy.cms.service.master.SmsBlackListGroupService;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.MerchantAccount;
import com.zy.cms.vo.SmsBlackGroupBind;
import com.zy.cms.vo.SmsBlackListGroup;
import com.zy.cms.vo.User;
import com.zy.cms.vo.query.ResultQuery;

/**
 * 短信黑名单组绑定Controller
 * Created by luos on 2017/9/6.
 */
@Controller
@RequestMapping("/black_group_bind")
public class SmsBlackGroupBindController {

    private static final ZyLogger logger = ZyLogger.getLogger(SmsBlackGroupBindController.class);

    @Autowired
    private SmsBlackListGroupService smsBlackListGroupService;
    @Autowired
    private SmsBlackGroupBindService smsBlackGroupBindService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private MerchantAccountService merchantAccountService;

    @RequestMapping("/to_list")
    public ModelAndView toList() {
        ModelAndView mv = new ModelAndView("/blackgroup/bind_list");

        return mv;
    }

    @RequestMapping("/list_data")
    @ResponseBody
    public ModelAndView getListData(HttpServletRequest request,
                                    HttpServletResponse response, @RequestParam("params") String params) {
        ModelAndView mv = new ModelAndView("/blackgroup/bind_list_data");
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            logger.info("【短信黑名单组绑定查询列表】参数={0}", new Object[]{params}, null);
            if (!StringUtil.isEmpty(params)) {
                paramMap = JsonUtil.parseToObject(params, Map.class);
            }

            Integer pageNum = (Integer.parseInt(paramMap.get("pageNum") + "") - 1) <= 0 ? 0
                    : (Integer.parseInt(paramMap.get("pageNum") + "") - 1);
            Integer pageSize = Constant.PAGE_SIZE_20;

            paramMap.put("pageNum", pageNum);
            paramMap.put("pageSize", pageSize);
            paramMap.put("pageOffset", pageNum * pageSize);
            paramMap.put("groupName", (paramMap.get("groupName") + "").trim());
            paramMap.put("merchantPhone", (paramMap.get("merchantPhone") + "").trim());

            Integer total = smsBlackGroupBindService.queryCountByQuery(paramMap);
            List<SmsBlackGroupBind> list = smsBlackGroupBindService.queryListByQuery(paramMap);

            // 构建查询结果对象
            ResultQuery pgdata = new ResultQuery();
            pgdata.setPage_num(Long.valueOf(Integer.parseInt(paramMap.get("pageNum") + "") + 1));
            pgdata.setPage_size(Long.valueOf(Integer.parseInt(paramMap.get("pageSize") + "")));
            pgdata.setTotal(Long.valueOf(total));
            pgdata.setData(list);
            mv.addObject("pgdata", pgdata);
            return mv;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("【获取短信黑名单组绑定列表】出现错误,原因是{0}", new Object[]{e.getMessage()}, null);
            return mv;
        }
    }

    @RequestMapping("/to_add")
    public ModelAndView toAdd(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/blackgroup/black_group_bind");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        try{
            List<SmsBlackListGroup> smsBlackListGroupList = smsBlackListGroupService.queryListByQuery(paramMap);
            mv.addObject("smsBlackListGroupList", smsBlackListGroupList);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("【获取短信黑名单组列表】出现错误,原因是{0}", new Object[]{e.getMessage()}, null);
        }
        return mv;
    }

    @RequestMapping("/to_edit/{id}")
    public ModelAndView toEdit(HttpServletRequest request, @PathVariable Integer id) {
        ModelAndView mv = new ModelAndView("/blackgroup/black_group_bind");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        try{
            List<SmsBlackListGroup> smsBlackListGroupList = smsBlackListGroupService.queryListByQuery(paramMap);
            mv.addObject("smsBlackListGroupList", smsBlackListGroupList);
            SmsBlackGroupBind smsBlackGroupBind = smsBlackGroupBindService.getSmsBlackGroupBindById(id);
            mv.addObject("smsBlackGroupBind", smsBlackGroupBind);
            MerchantAccount merchantAccount = merchantAccountService.getMerchantAccount(smsBlackGroupBind.getApiAccount());
            mv.addObject("merchantAccount", merchantAccount);
            mv.addObject("operate", "edit");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("【获取短信黑名单组列表】出现错误,原因是{0}", new Object[]{e.getMessage()}, null);
        }
        return mv;
    }

    @RequestMapping("/do_bind")
    @ResponseBody
    public String doAdd(SmsBlackGroupBind smsBlackGroupBind ,HttpServletRequest request, HttpServletResponse response) {
        logger.info("【短信黑名单组绑定】保存黑名单组开始...params=" + JsonUtil.toJsonString(smsBlackGroupBind));
        int result = 0;
        try {
            User us = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
            if (us == null) {
                logger.warn("用户登录超时!");
                return JsonUtil.objectToJson(result);
            }
            result = smsBlackGroupBindService.saveSmsBlackGroupBind(smsBlackGroupBind);

        } catch (Exception e) {
            logger.info("【短信黑名单组绑定】添加短信黑名单组绑定出现异常,异常是:" + e.getMessage());
        }
        return JsonUtil.objectToJson(result);
    }

    @RequestMapping("/del_bind/{id}")
    @ResponseBody
    public String delBind(HttpServletRequest request, @PathVariable Integer id) {
        int result = 0;
        try {
            User us = commonService.valideUser(request, Constant.USER_SESSION_UNAME);
            if (us == null) {
                logger.warn("用户登录超时!");
                return JsonUtil.objectToJson(result);
            }
            result = smsBlackGroupBindService.deleteBind(id);
        } catch (Exception e) {
            logger.info("【短信黑名单组列表】修改短信黑名单组出现异常,异常是:" + e.getMessage());
        }
        return JsonUtil.objectToJson(result);
    }

}
