package com.zy.cms.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zy.cms.common.Constant;
import com.zy.cms.common.RedisConstant;
import com.zy.cms.common.RedisOperator;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.util.CookieUtil;
import com.zy.cms.util.DESUtil;
import com.zy.cms.util.GenerateCodeUtil;
import com.zy.cms.util.StringUtil;

/**
 * 验证码
 * 
 * @author hmj
 * @date 2015-06-01
 */
@Controller
@RequestMapping("/action")
public class CodeController {

	private static final ZyLogger logger = ZyLogger
			.getLogger(CodeController.class);
	@Resource
	private RedisOperator redisOperator;
	
	/**
     * 生成验证码
     * 
     * @param codeModle 验证码所述模块
     * @param width 宽度
     * @param height 高度
     * @param req
     * @param resp
     * @throws IOException
     */
    @RequestMapping("/code/getCode")
    public void getCode(String codeModle, String width, String height, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info("【验证码】请求参数codeModle={0},width={1},height={2}", new Object[] { codeModle, width, height }, null);
        String codeType = "";
        String redisKeyTemplate = "";
        if (!StringUtil.isEmpty(codeModle)) {
            if (codeModle.equals("reg")) {
                codeType = Constant.REG_CODE_KEY;
                redisKeyTemplate = RedisConstant.REG_CODE_KEY;
            } else if (codeModle.equals("changePwd")) {
                codeType = Constant.PWD_CODE_KEY;
                redisKeyTemplate = RedisConstant.PWD_CODE_KEY;
            } else if (codeModle.equals("freeSmsExp")) {
                codeType = Constant.PRO_FREE_CODE_KEY;
                redisKeyTemplate = RedisConstant.PRO_FREE_CODE_KEY;
            } else if (codeModle.equals("findpwd")) {
                codeType = Constant.FINDPWD_CODE_KEY;
                redisKeyTemplate = RedisConstant.FINDPWD_CODE_KEY;
            }
        }
        Integer wi = null;
        Integer hei = null;
        if (!StringUtil.isEmpty(width)) {
            wi = Integer.parseInt(width);
        }
        if (!StringUtil.isEmpty(height)) {
            hei = Integer.parseInt(height);
        }
        Cookie ck = CookieUtil.getCookieByCookieName(req, codeType);
        if (ck != null) {
            String key = ck.getValue();
            key = DESUtil.decrypt(key, Constant.USER_SESSION_ENCRYPT_KEY);
            key = String.format(redisKeyTemplate, key);
            redisOperator.del(key);// 清除redis值
        }

        StringBuffer randomCode = new StringBuffer();
        BufferedImage buffImg = GenerateCodeUtil.getCode(randomCode, wi, hei);
        logger.info("【验证码】" + codeType + "-randomCode=" + randomCode);
        String newKey = StringUtil.getUUID();
        String regCodeKey = String.format(redisKeyTemplate, newKey);

        redisOperator.setex(regCodeKey, Constant.VERIFY_CODE_IN_REDIS_MINUTES, randomCode.toString());

        Cookie cookie = CookieUtil.getCookie(codeType, DESUtil.encrypt(newKey, Constant.USER_SESSION_ENCRYPT_KEY), 5 * 60, "/");
        resp.addCookie(cookie);

        // 禁止图像缓存。
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);

        resp.setContentType("image/jpeg");

        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos = resp.getOutputStream();
        ImageIO.write(buffImg, "jpeg", sos);
        sos.close();
    }
}
