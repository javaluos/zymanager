package com.zy.cms.service.master.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.TypeReference;
import com.zy.cms.common.CacheService;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.mapper.master.AppInfoMapper;
import com.zy.cms.mapper.master.MerchantSmsSignerMapper;
import com.zy.cms.mapper.master.MerchantSmsTemplateMapper;
import com.zy.cms.service.master.AppManageService;
import com.zy.cms.vo.AppInfo;
import com.zy.cms.vo.CdrPushAddr;
import com.zy.cms.vo.IndustryInfo;
import com.zy.cms.vo.MerchantSmsSigner;

@Service("appManageService")
public class AppManageServiceImpl implements AppManageService {
	private static final ZyLogger logger = ZyLogger
			.getLogger(AppManageServiceImpl.class);
	private TypeReference<List<Map>> ref = new TypeReference<List<Map>>() {
	};
	@Resource
	private AppInfoMapper appInfoMapper;
	@Resource
	private MerchantSmsSignerMapper merchantSmsSignerMapper;
	@Resource
	private CacheService cacheService;
	@Resource
	private MerchantSmsTemplateMapper merchantSmsTemplateMapper;
	

	@Override
	public List<AppInfo> getAppInfos(Map params) {
		logger.info("【应用列表】params={0}", new Object[] { params }, null);
		long start = System.currentTimeMillis();
		if (null == params || params.size() <= 0) {
			logger.error("【应用列表】params为空");
			return null;
		}
		logger.info("【应用列表】耗时={0}", new Object[] { System.currentTimeMillis()
				- start }, null);
		List<AppInfo> list = appInfoMapper.getAppInfos(params);
		if (list != null && list.size() > 0) {
			for (AppInfo app : list) {
				if (app.getId() == 1) {
					app.setAppName("测试");
					break;
				}
			}
		}
		return list;
	}

	@Override
	public Integer saveAppInfo(AppInfo param) {
		logger.info("【创建应用】params={0}", new Object[] { param }, null);
		long start = System.currentTimeMillis();
		if (null == param) {
			logger.error("【创建应用】params为空");
			return null;
		}
		logger.info("【创建应用】耗时={0}", new Object[] { System.currentTimeMillis()
				- start }, null);

		CdrPushAddr cdrPushAddr = new CdrPushAddr();
		cdrPushAddr.setApiAccount(param.getApiAccount());
		cdrPushAddr.setAppId(param.getAppId());
		try {
			// 删除话单推送地址
			/*cdrPushAddrMapper.deleteCdrPushAddr(cdrPushAddr);
			List<CdrPushAddr> addrlist = param.getAddrList();
			if (addrlist != null && addrlist.size() > 0) {
				cdrPushAddrMapper.insertCdrPushAddrBatch(addrlist);

			}*/
			
			return appInfoMapper.insert(param);
		} catch (Exception e) {
			logger.error("【保存应用】异常信息" + e.getMessage());
		}
		return 0;

	}

	@Override
	public Integer updateAppInfo(AppInfo param) {
		logger.info("【更新应用】params={0}", new Object[] { param }, null);
		long start = System.currentTimeMillis();
		if (null == param) {
			logger.error("【更新应用】params为空");
			return 0;
		}
		logger.info("【更新应用】耗时={0}", new Object[] { System.currentTimeMillis()
				- start }, null);
		
		CdrPushAddr cdrPushAddr = new CdrPushAddr();
		cdrPushAddr.setApiAccount(param.getApiAccount());
		cdrPushAddr.setAppId(param.getAppId());
		try {
			// 删除话单推送地址
			/*cdrPushAddrMapper.deleteCdrPushAddr(cdrPushAddr);
			List<CdrPushAddr> addrlist = param.getAddrList();
			if (addrlist != null && addrlist.size() > 0) {
				cdrPushAddrMapper.insertCdrPushAddrBatch(addrlist);

			}*/
			
			return appInfoMapper.updateByPrimaryKeySelective(param);
		} catch (Exception e) {
			logger.error("【更新应用】异常信息" + e.getMessage());
		}
		
		return 0;
	}

	@Override
	public int getAppInfoCount(Map params) {
		logger.info("【应用列表总数】params={0}", new Object[] { params }, null);
		long start = System.currentTimeMillis();
		if (null == params || params.size() <= 0) {
			logger.error("【应用列表总数】params为空");
			return 0;
		}
		int count = appInfoMapper.getCounts(params);
		logger.info("【应用列表总数】耗时={0}", new Object[] { System.currentTimeMillis()
				- start }, null);
		return count;
	}

	@Override
	public int deleteApp(Map param) {
		logger.info("【删除应用】param={0}", new Object[] { param }, null);
		long start = System.currentTimeMillis();
		if (null == param || param.size() <= 0) {
			logger.error("【删除应用】params为空");
			return 0;
		}
		int rs = appInfoMapper.delete(param);
		logger.info("【删除应用】耗时={0}", new Object[] { System.currentTimeMillis()
				- start }, null);
		return rs;
	}

	@Override
	public Map getAppDetail(Integer id) {
		logger.info("【应用详情】id={0}", new Object[] { id }, null);
		long start = System.currentTimeMillis();
		if (null == id) {
			logger.error("【应用详情】id为空");
			return null;
		}
		// 获取appinfo
		Map param = new HashMap();
		param.put("id", id);
		AppInfo appInfo = appInfoMapper.getAppInfo(param);
		if (null == appInfo) {
			return null;
		}

		Map rs = new HashMap();
		rs.put("appInfo", appInfo);
		try {

			CdrPushAddr cdrPushAddr = new CdrPushAddr();
			cdrPushAddr.setApiAccount(appInfo.getApiAccount());
			cdrPushAddr.setAppId(appInfo.getAppId());

			/*List<CdrPushAddr> addrs= cdrPushAddrMapper.queryCdrPushAddrList(cdrPushAddr);
			appInfo.setAddrList(addrs);*/
			
		} catch (Exception e) {
			logger.error("【应用详情】异常，" + e.getMessage(), e);
			return null;
		}

		logger.info("【应用详情】耗时={0}", new Object[] { System.currentTimeMillis()
				- start }, null);
		return rs;
	}


	@Override
	public String getSignerNmById(Integer id) {
		MerchantSmsSigner signer = this.merchantSmsSignerMapper
				.selectByPrimaryKey(id);
		return signer.getContent();
	}


	@Override
	public AppInfo getAppInfoById(Integer id) {
		
		AppInfo appInfo=appInfoMapper.selectByPrimaryKey(id);
		
		if(appInfo ==null){
			return  appInfo;
		}
		try {

			CdrPushAddr cdrPushAddr = new CdrPushAddr();
			cdrPushAddr.setApiAccount(appInfo.getApiAccount());
			cdrPushAddr.setAppId(appInfo.getAppId());

			/*List<CdrPushAddr> addrs= cdrPushAddrMapper.queryCdrPushAddrList(cdrPushAddr);
			appInfo.setAddrList(addrs);*/
			
		} catch (Exception e) {
			logger.error("【应用详情】异常，" + e.getMessage(), e);
			return null;
		}
		return appInfo;
	}

	@Override
	public List<AppInfo> getAppInfoByMap(Map<String, Object> map) {
		return this.appInfoMapper.getAppInfos(map);
	}

	@Override
	public int getAppInfoExitCount(Map param) {
		if (null == param) {
			logger.error("【创建应用】params为空");
			return 0;
		}

		return appInfoMapper.getAppInfoExitCount(param);
	}

	@Override
	public String getIndustryNmById(Integer id) {
		return null;
	}

	@Override
	public List<IndustryInfo> getAllIndustryInfo() {
		return null;
	}

}
