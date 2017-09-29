package com.zy.cms.web.mvc;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zy.cms.vo.UserInfo;

/**
 * spring mvc 默认地址匹配.
 * 
 * @author fenglb
 * @date 2013-6-1 下午4:35:02
 */
@Controller
public class WildcardMVCController {

    @Resource
    private UserInfo userInfo;

    /**
     * 默认匹配所有地址.
     * 
     * @return
     * @author fenglb
     * @date 2013-4-15 下午2:16:00
     */
    @RequestMapping("/**/*.htm")
    public ModelAndView htmMapping(HttpServletRequest request, HttpServletResponse response) {
        return getHtmlView(request, ".htm");
    }

    /**
     * 默认匹配所有地址.
     * 
     * @return
     * @author fenglb
     * @date 2013-4-15 下午2:16:00
     */
    @RequestMapping("/**/*.html")
    public ModelAndView htmlMapping(HttpServletRequest request, HttpServletResponse response) {
        return getHtmlView(request, ".html");
    }

    /**
     * 获取html页面的模板
     * 
     * @param request
     * @param htmlSuffix
     * @return
     */
    public ModelAndView getHtmlView(HttpServletRequest request, String htmlSuffix) {
        String url = request.getServletPath();
        ModelAndView view = new ModelAndView();
        view.setViewName(getViewName(url, htmlSuffix));
        userInfo.setUserInfo(view, request);
        return view;
    }

    /**
     * 获得视图路径.
     * 
     * @param requestURI
     * @param suffix
     * @return
     * @author fenglb
     * @date 2014-2-10 下午3:36:55
     */
    public String getViewName(String requestURI, String suffix) {
        String viewName = requestURI;
        viewName = viewName.substring(0, viewName.length() - suffix.length());
        return viewName;
    }

}
