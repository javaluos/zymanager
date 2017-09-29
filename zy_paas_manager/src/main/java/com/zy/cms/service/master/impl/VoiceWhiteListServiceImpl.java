package com.zy.cms.service.master.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zy.cms.common.RedisConstant;
import com.zy.cms.common.RedisOperator;
import com.zy.cms.mapper.master.VoiceWhiteListInfoMapper;
import com.zy.cms.service.master.VoiceWhiteListService;
import com.zy.cms.vo.VoiceWhiteListInfo;
import com.zy.cms.vo.VoiceWhiteListVo;
import com.zy.cms.vo.query.VoiceWhiteListQuery;

@Service("voiceWhiteListService")
public class VoiceWhiteListServiceImpl implements VoiceWhiteListService {
	
	@Autowired
	private RedisOperator redisOperator;
	
	@Autowired
	private VoiceWhiteListInfoMapper mapper;

	@Override
	public Integer queryCountByEntity(VoiceWhiteListQuery query) throws Exception{
		return mapper.selectCountByEntity(query);
	}

	@Override
	public List<VoiceWhiteListVo> queryListByEntity(VoiceWhiteListQuery query) throws Exception{
		return mapper.selectListByEntity(query);
	}

	@Override
	public boolean add(String apiAccount, String userName) throws Exception{
		boolean result = false;
		VoiceWhiteListInfo voiceWhiteListInfo = new VoiceWhiteListInfo();
		voiceWhiteListInfo.setApiAccount(apiAccount);
		voiceWhiteListInfo.setOperator(userName);
		Date now = new Date();
		voiceWhiteListInfo.setAddTime(now);
		voiceWhiteListInfo.setCreateTime(now);
		voiceWhiteListInfo.setUpdateTime(now);
		voiceWhiteListInfo.setRemark("");
		result =  mapper.insert(voiceWhiteListInfo) > 0;
		//写入redis
		if(result){
			String redisKey = String.format(RedisConstant.VOICE_WHITE_LIST_ACCOUNT, apiAccount);
			redisOperator.set(redisKey, apiAccount);
		}
		return result;
	}

	@Override
	public boolean getByApiAccount(String apiAccount) throws Exception{
		boolean result = false;
		VoiceWhiteListInfo voiceWhiteListInfo = mapper.selectByApiAccount(apiAccount);
		if(null != voiceWhiteListInfo) {
			result = true; 
		}
		return result;
	}

	@Override
	public boolean delete(Integer id) throws Exception {
		boolean result = false;
		VoiceWhiteListInfo voiceWhiteListInfo = mapper.selectByPrimaryKey(id);
		result = mapper.deleteByPrimaryKey(id) > 0;
		if(result){
			String redisKey = String.format(RedisConstant.VOICE_WHITE_LIST_ACCOUNT, voiceWhiteListInfo.getApiAccount());
			redisOperator.del(redisKey);
		}
		return result;
	}

}
