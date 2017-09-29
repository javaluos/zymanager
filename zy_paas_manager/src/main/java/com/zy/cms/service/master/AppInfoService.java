/*
 * Copyright Guoling.com All right reserved. This software is the confidential and proprietary information of
 * Guoling.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Guoling.com.
 */
package com.zy.cms.service.master;

import com.zy.cms.vo.AppInfo;

/*
 * 类AppInfoService.java的实现描述：TODO 类实现描述
 * @author ddp1j32 2015-6-11 下午2:38:01
 */
public interface AppInfoService {

    /**
     * 根据token获取应用的信息
     * 
     * @param token
     * @return
     */
    public AppInfo getAppInfoByToken(String token);
}
