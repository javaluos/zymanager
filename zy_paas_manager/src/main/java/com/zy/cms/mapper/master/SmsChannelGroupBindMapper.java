package com.zy.cms.mapper.master;

import java.util.List;

import com.zy.cms.vo.channel.SmsChannelGroupBind;
import com.zy.cms.vo.channel.SmsMerchantChannelGroupBind;
import com.zy.cms.vo.query.SmsChannelGroupBindQuery;

public interface SmsChannelGroupBindMapper {
	int deleteByPrimaryKey(Integer id);

	int deleteByIds(List<Integer> ids);
	
	int deleteMerchantGroupBind(Integer id);

	int insert(SmsChannelGroupBind record);

	int insertChannelGroupBindList(List<SmsChannelGroupBind> recordList);

	int insertMerchantChannelGroupBind(List<SmsMerchantChannelGroupBind> recordList);

	SmsMerchantChannelGroupBind getMerchantChannelGroupBindByApiAccount(String apiAccount);
	
	SmsMerchantChannelGroupBind getMerchantChannelGroupBindById(Integer id);

	List<SmsMerchantChannelGroupBind> listMerchantChannelGroupBind(SmsChannelGroupBindQuery query);

	int listMerchantChannelGroupBindCount(SmsChannelGroupBindQuery query);

	SmsChannelGroupBind selectByPrimaryKey(Integer id);
	
	int updateMerchantGroupBindById(SmsMerchantChannelGroupBind bind);

	int updateByPrimaryKeySelective(SmsChannelGroupBind record);

	int updateByPrimaryKey(SmsChannelGroupBind record);

	Integer countGroupBindByGroupId(String channelGroupId);
	
	List<String> listGroupIdByChannelId(String channelId);
}