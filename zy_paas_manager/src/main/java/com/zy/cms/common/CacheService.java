package com.zy.cms.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.zy.cms.mapper.master.VoiceBusinessInfoMapper;
import com.zy.cms.mapper.master.VoicePackageMapper;
import com.zy.cms.mapper.master.VoiceParamMapper;

/**
 * 系统缓存
 * 
 * @author hmj
 */
@Component
public class CacheService {
	private static final ZyLogger logger = ZyLogger
			.getLogger(CacheService.class);
	private Map<String, String> configMap = new ConcurrentHashMap<String, String>();
	private List<Map> packageMaps = new ArrayList<Map>();
	private List<Map> vosbusness = new ArrayList<Map>();
	
	@Resource
	private VoiceParamMapper voiceParamMapper;
	@Resource
	private VoiceBusinessInfoMapper voiceBusinessInfoMapper;
	
	@Resource
	private VoicePackageMapper voicePackageMapper;

	private static final ReadWriteLock rwl = new ReentrantReadWriteLock();

	@SuppressWarnings("unused")
	@PostConstruct
	private void init() {
		logger.info("【加载缓存】开始加载缓存............");
		List<Map> list = voiceParamMapper.getAllParam(null);
		for (Map param : list) {
			configMap.put(param.get("PARAM_NAME") + "",
					param.get("PARAM_VALUE") + "");
		}

		rwl.writeLock().lock();
		try {
			List<Map> listSmsCombos = voicePackageMapper.getAllPackages(null);
			packageMaps.clear();
			packageMaps.addAll(listSmsCombos);
			List<Map> listMactivitys = voiceBusinessInfoMapper
					.getAllBusiness(null);
			vosbusness.clear();
			vosbusness.addAll(listMactivitys);
		} finally {
			rwl.writeLock().unlock();
		}
		logger.info("【加载缓存】加载缓存结束............");
	}

	/**
	 * 根据key
	 * 
	 * @param key
	 * @return
	 */
	public String getConfig(String key) {
		return configMap.get(key);
	}

	/**
	 * 获取所有套餐
	 * 
	 * @return
	 */
	public List<Map> getVoicePackages() {
		return packageMaps;
	}

	/**
	 * 获取所有业务
	 * 
	 * @return
	 */
	public List<Map> getVoiceBusness() {
		return vosbusness;
	}

	/**
	 * 重新加载缓存
	 */
	public void reloadCache() {
		init();
	}
}
