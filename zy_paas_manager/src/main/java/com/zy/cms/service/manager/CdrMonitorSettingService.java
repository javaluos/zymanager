package com.zy.cms.service.manager;

import java.util.List;

import com.zy.cms.vo.manager.CdrMonitorSetting;

public interface CdrMonitorSettingService {

	/**
	 * 保存监控指标设置信息
	 * @param measureTime
	 * @param callCountaUp
	 * @param callCountaDown
	 * @param successRateaDown
	 * @param responseRateaDown
	 * @param averageTalkTimeaDown
	 * @param averageTurnOnDelayaUp
	 * @param averageInTimeaUp
	 * @param bizId
	 * @return
	 */
	Integer saveMonitorSetting(String apiAccount,String measureTime, Integer callCountaUp, Integer callCountbUp, Integer callCountaDown,
			Integer callCountbDown, String successRateaDown, String successRatebDown, String responseRateaDown,
			String responseRatebDown, Integer averageTalkTimeaDown, Integer averageTalkTimebDown,
			Integer averageTurnOnDelayaUp, Integer averageTurnOnDelaybUp, Integer averageInTimeaUp,
			Integer averageInTimebUp, Integer bizId, int startFlag, int globalFlag)  throws Exception;
	
	/**
	 * 根据业务类型和全局标识获取监控指标设置
	 * @param bizId
	 * @param globalFlag
	 * @return
	 */
	List<CdrMonitorSetting> getCdrMonitorSetting(Integer startFlag, Integer globalFlag);
	
	/**
	 * 根据appiAccount和全局标识获取客户业务监控指标设置
	 * @param globalFlag
	 * @param apiAccount
	 * @return
	 */
	List<CdrMonitorSetting> getCdrAccountMonitorSetting (Integer startFlag,Integer globalFlag,String apiAccount);

	/**
	 * 修改监控指标设置信息
	 * @param id
	 * @param measureTime
	 * @param callCountaUp
	 * @param callCountaDown
	 * @param successRateaDown
	 * @param responseRateaDown
	 * @param averageTalkTimeaDown
	 * @param averageTurnOnDelayaUp
	 * @param averageInTimeaUp
	 * @return
	 */
	boolean editMonitorSetting(Integer id, String measureTime, Integer callCountaUp, Integer callCountbUp,
			Integer callCountaDown, Integer callCountbDown, String successRateaDown, String successRatebDown,
			String responseRateaDown, String responseRatebDown, Integer averageTalkTimeaDown,
			Integer averageTalkTimebDown, Integer averageTurnOnDelayaUp, Integer averageTurnOnDelaybUp,
			Integer averageInTimeaUp, Integer averageInTimebUp) throws Exception;

	boolean deteleMonitorSetting(Integer id);

}
