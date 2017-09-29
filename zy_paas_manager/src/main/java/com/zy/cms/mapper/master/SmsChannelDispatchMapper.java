package com.zy.cms.mapper.master;

import java.util.List;
import java.util.Map;

public interface SmsChannelDispatchMapper {

	List<Map<String, String>> getSmsChannelDispatchByCId(String channelId);

	void delSmsChannelDispatchByCId(String channelId);

	void saveSmsChannelDispatch(Map<String, String> map);
}