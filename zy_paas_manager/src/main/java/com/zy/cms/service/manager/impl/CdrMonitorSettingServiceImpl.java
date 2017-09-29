package com.zy.cms.service.manager.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zy.cms.enums.StartFlagEnum;
import com.zy.cms.mapper.manager.CdrMonitorSettingMapper;
import com.zy.cms.service.manager.CdrMonitorSettingService;
import com.zy.cms.vo.manager.CdrMonitorSetting;

@Service("cdrMonitorSettingService")
@Transactional
public class CdrMonitorSettingServiceImpl implements CdrMonitorSettingService {
	
	@Autowired
	private CdrMonitorSettingMapper mapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer saveMonitorSetting(String apiAccount,String measureTime, Integer callCountaUp, Integer callCountbUp, Integer callCountaDown,
			Integer callCountbDown, String successRateaDown, String successRatebDown, String responseRateaDown,
			String responseRatebDown, Integer averageTalkTimeaDown, Integer averageTalkTimebDown,
			Integer averageTurnOnDelayaUp, Integer averageTurnOnDelaybUp, Integer averageInTimeaUp,
			Integer averageInTimebUp, Integer bizId, int startFlag, int globalFlag) throws Exception{
		CdrMonitorSetting cdrMonitorSetting = new CdrMonitorSetting();
		cdrMonitorSetting.setApiAccount(apiAccount);
		cdrMonitorSetting.setBusinessId(bizId);
		cdrMonitorSetting.setMeasureTime(measureTime);
		if(null != callCountaUp){
			cdrMonitorSetting.setCallCountaUp(callCountaUp);
		}
		if(null != callCountbUp){
			cdrMonitorSetting.setCallCountbUp(callCountbUp);
		}
		if(null != callCountaDown){
			cdrMonitorSetting.setCallCountaDown(callCountaDown);
		}
		if(null != callCountbDown){
			cdrMonitorSetting.setCallCountbDown(callCountbDown);
		}
		if(StringUtils.isNotBlank(successRateaDown)){
			cdrMonitorSetting.setSuccessRateaDown(parseInt(successRateaDown) * 100);
		} 
		if(StringUtils.isNotBlank(successRatebDown)){
			cdrMonitorSetting.setSuccessRatebDown(parseInt(successRatebDown)* 100);
		} 
		if(StringUtils.isNotBlank(responseRateaDown)){
			cdrMonitorSetting.setResponseRateaDown(parseInt(responseRateaDown)* 100);
		} 
		if(StringUtils.isNotBlank(responseRatebDown)){
			cdrMonitorSetting.setResponseRatebDown(parseInt(responseRatebDown)* 100);
		} 
		if(null != averageTalkTimeaDown){
			cdrMonitorSetting.setAverageTalkTimeaDown(averageTalkTimeaDown);
		}
		if(null != averageTalkTimebDown){
			cdrMonitorSetting.setAverageTalkTimebDown(averageTalkTimebDown);
		}
		if(averageTurnOnDelayaUp!=null){
			cdrMonitorSetting.setAverageTurnOnDelayaUp(averageTurnOnDelayaUp);
		}
		if(null != averageTurnOnDelaybUp){
			cdrMonitorSetting.setAverageTurnOnDelaybUp(averageTurnOnDelaybUp);
		}
		if(null != averageInTimeaUp){
			cdrMonitorSetting.setAverageInTimeaUp(averageInTimeaUp);
		}
		if(null != averageInTimebUp){
			cdrMonitorSetting.setAverageInTimebUp(averageInTimebUp);
		}
		cdrMonitorSetting.setStartFlag(startFlag);
		cdrMonitorSetting.setGlobalFlag(globalFlag);
		mapper.insert(cdrMonitorSetting);
		Integer id = mapper.selectMaxId();
		return id;
	}

	@Override
	public List<CdrMonitorSetting> getCdrMonitorSetting(Integer startFlag, Integer globalFlag) {
		List<CdrMonitorSetting> cdrMonitorSettings = mapper.selectGlobalSettings(startFlag, globalFlag);
		return cdrMonitorSettings;
	}
	
	
	@Override
	public List<CdrMonitorSetting> getCdrAccountMonitorSetting(Integer startFlag,Integer globalFlag,String apiAccount) {
		List<CdrMonitorSetting> cdrMonitorSettings = mapper.getCdrAccountMonitorSetting(startFlag,globalFlag,apiAccount);
		return cdrMonitorSettings;
	}
	
	
	@Override
	public boolean editMonitorSetting(Integer id, String measureTime, Integer callCountaUp, Integer callCountbUp,
			Integer callCountaDown, Integer callCountbDown, String successRateaDown, String successRatebDown,
			String responseRateaDown, String responseRatebDown, Integer averageTalkTimeaDown,
			Integer averageTalkTimebDown, Integer averageTurnOnDelayaUp, Integer averageTurnOnDelaybUp,
			Integer averageInTimeaUp, Integer averageInTimebUp) throws Exception{
		CdrMonitorSetting cdrMonitorSetting = mapper.selectByPrimaryKey(id);
		if(null == cdrMonitorSetting){
			return false;
		}
		cdrMonitorSetting.setMeasureTime(measureTime);
		if(null != callCountaUp){
			cdrMonitorSetting.setCallCountaUp(callCountaUp);
		}else{
			cdrMonitorSetting.setCallCountaUp(-1);
		}
		if(null != callCountbUp){
			cdrMonitorSetting.setCallCountbUp(callCountbUp);
		}else{
			cdrMonitorSetting.setCallCountbUp(-1);
		}
		if(null != callCountaDown){
			cdrMonitorSetting.setCallCountaDown(callCountaDown);
		}else{
			cdrMonitorSetting.setCallCountaDown(-1);
		}
		if(null != callCountbDown){
			cdrMonitorSetting.setCallCountbDown(callCountbDown);
		}else{
			cdrMonitorSetting.setCallCountbDown(-1);
		}
		if(StringUtils.isNotBlank(successRateaDown)){
			cdrMonitorSetting.setSuccessRateaDown(parseInt(successRateaDown) * 100);
		}else{
			cdrMonitorSetting.setSuccessRateaDown(-1);
		} 
		if(StringUtils.isNotBlank(successRatebDown)){
			cdrMonitorSetting.setSuccessRatebDown(parseInt(successRatebDown)* 100);
		}else{
			cdrMonitorSetting.setSuccessRatebDown(-1);
		} 
		if(StringUtils.isNotBlank(responseRateaDown)){
			cdrMonitorSetting.setResponseRateaDown(parseInt(responseRateaDown)* 100);
		}else{
			cdrMonitorSetting.setResponseRateaDown(-1);
		} 
		if(StringUtils.isNotBlank(responseRatebDown)){
			cdrMonitorSetting.setResponseRatebDown(parseInt(responseRatebDown)* 100);
		}else{
			cdrMonitorSetting.setResponseRatebDown(-1);
		} 
		if(null != averageTalkTimeaDown){
			cdrMonitorSetting.setAverageTalkTimeaDown(averageTalkTimeaDown);
		}else{
			cdrMonitorSetting.setAverageTalkTimeaDown(-1);
		}
		if(null != averageTalkTimebDown){
			cdrMonitorSetting.setAverageTalkTimebDown(averageTalkTimebDown);
		}else{
			cdrMonitorSetting.setAverageTalkTimebDown(-1);
		}
		if(averageTurnOnDelayaUp!=null){
			cdrMonitorSetting.setAverageTurnOnDelayaUp(averageTurnOnDelayaUp);
		}else{
			cdrMonitorSetting.setAverageTurnOnDelayaUp(-1);
		}
		if(null != averageTurnOnDelaybUp){
			cdrMonitorSetting.setAverageTurnOnDelaybUp(averageTurnOnDelaybUp);
		}else{
			cdrMonitorSetting.setAverageTurnOnDelaybUp(-1);
		}
		if(null != averageInTimeaUp){
			cdrMonitorSetting.setAverageInTimeaUp(averageInTimeaUp);
		}else{
			cdrMonitorSetting.setAverageInTimeaUp(-1);
		}
		if(null != averageInTimebUp){
			cdrMonitorSetting.setAverageInTimebUp(averageInTimebUp);
		}else{
			cdrMonitorSetting.setAverageInTimebUp(-1);
		}
		return mapper.updateByPrimaryKeySelective(cdrMonitorSetting) > 0;
	}
	
	private int parseInt(String str){
		double doubleValue = Double.valueOf(str);
		return (int) doubleValue;
	}

	@Override
	public boolean deteleMonitorSetting(Integer id) {
		CdrMonitorSetting cdrMonitorSetting = mapper.selectByPrimaryKey(id);
		cdrMonitorSetting.setStartFlag(StartFlagEnum.DISABLE.getType());
		return mapper.updateByPrimaryKeySelective(cdrMonitorSetting) > 0;
	}

}
