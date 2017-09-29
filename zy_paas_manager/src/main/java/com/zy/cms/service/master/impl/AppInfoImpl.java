/*
 * Copyright Guoling.com All right reserved. This software is the confidential and proprietary information of
 * Guoling.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Guoling.com.
 */
package com.zy.cms.service.master.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zy.cms.common.Constant;
import com.zy.cms.common.RedisConstant;
import com.zy.cms.common.RedisOperator;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.mapper.master.AppInfoMapper;
import com.zy.cms.service.master.AppInfoService;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.AppInfo;

/*
 * 类AppInfoBiz.java的实现描述：TODO 类实现描述
 * @author ddp1j32 2015-6-11 下午2:39:21
 */
@Service("appInfoService")
public class AppInfoImpl implements AppInfoService {

	private static final ZyLogger logger = ZyLogger.getLogger(AppInfoImpl.class);
	@Autowired
	private AppInfoMapper appInfoMapper;

	@Autowired
	private RedisOperator redis;

	@Override
	public AppInfo getAppInfoByToken(String token) {
		return null;
	}

}
