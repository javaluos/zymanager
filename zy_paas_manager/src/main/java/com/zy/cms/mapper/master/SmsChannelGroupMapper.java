package com.zy.cms.mapper.master;

import java.util.List;
import java.util.Map;

import com.zy.cms.vo.channel.SmsChannelGroup;
import com.zy.cms.vo.query.SmsChannelGroupQuery;

public interface SmsChannelGroupMapper {
	int deleteByPrimaryKey(String id);

	int insert(SmsChannelGroup record);

	List<SmsChannelGroup> listSmsChannelGroup(SmsChannelGroupQuery query);

	int listSmsChannelGroupCount(SmsChannelGroupQuery query);

	List<SmsChannelGroup> listSmsChannelGroupNoPage();

	int insertSelective(SmsChannelGroup record);

	List<Map<String, String>> getSmsChannelSelect();

	List<Map<String, String>> getSmsChannelGroupSelect();

	SmsChannelGroup selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(SmsChannelGroup record);

	int updateByPrimaryKey(SmsChannelGroup record);

	int selectGroupNameCount(String groupName);

}