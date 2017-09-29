package com.zy.cms.service.master;

import java.util.List;
import java.util.Map;

import com.zy.cms.vo.channel.SmsChannelGroup;
import com.zy.cms.vo.query.SmsChannelGroupQuery;

public interface SmsChannelGroupService {

	String save(SmsChannelGroup scg);

	List<SmsChannelGroup> listSmsChannelGroup(SmsChannelGroupQuery query);

	int listSmsChannelGroupCount(SmsChannelGroupQuery query);
	
	List<SmsChannelGroup> listSmsChannelGroupNoPage();

	List<Map<String, String>> getSmsChannelSelect();

	List<Map<String, String>> getSmsChannelGroupSelect();
	
	SmsChannelGroup selectSmsChannelGroupById(String id);
	
	int deleteSmsChannelGroupAndBind(String id);

}
