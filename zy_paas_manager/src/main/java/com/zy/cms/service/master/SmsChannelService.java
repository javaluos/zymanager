package com.zy.cms.service.master;

import java.util.List;
import java.util.Map;

import com.zy.cms.vo.MerchantAccount;
import com.zy.cms.vo.channel.SmsChannel;
import com.zy.cms.vo.query.SmsChannelQuery;

public interface SmsChannelService {

	Integer querySmsChannelCountByEntity(SmsChannelQuery query);

	List<SmsChannel> querySmsChannelListByEntity(SmsChannelQuery query);

	SmsChannel selectByPrimaryKey(String channelId);

	SmsChannel selectByChannelMainCode(String channelMainCode);

	int deleteByPrimaryKey(String channelId);

	int insertSelective(SmsChannel channel);

	int updateByPrimaryKeySelective(SmsChannel channel);

	int saveSmsChannel(SmsChannel channel);
	
	boolean delSmsChannel(String channelId);

	List<String> getProvinceList();
	
	List<String> queryIdsByEntity(SmsChannelQuery query);
	
	public Map<String, SmsChannel> queryChannelListByApis(String[] apis);

}
