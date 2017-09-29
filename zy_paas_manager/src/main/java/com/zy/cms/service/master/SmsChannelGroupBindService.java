package com.zy.cms.service.master;

import java.util.List;

import com.zy.cms.vo.channel.SmsMerchantChannelGroupBind;
import com.zy.cms.vo.query.SmsChannelGroupBindQuery;

public interface SmsChannelGroupBindService {

	int saveMerchantChannelGroupBind(SmsMerchantChannelGroupBind bind);

	SmsMerchantChannelGroupBind getMerchantChannelGroupBindByApiAccount(String apiAccount);

	SmsMerchantChannelGroupBind getMerchantChannelGroupBindById(Integer id);

	List<SmsMerchantChannelGroupBind> listMerchantChannelGroupBind(SmsChannelGroupBindQuery query);

	int deleteMerchantGroupBind(String apiAccount, Integer id);

	int listMerchantChannelGroupBindCount(SmsChannelGroupBindQuery query);
	
	List<String> listGroupIdByChannelId(String channelId);
}
