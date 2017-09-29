package com.zy.cms.chanel.group.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.TypeReference;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.vo.channel.SmsChannelGroup;
import com.zy.cms.vo.channel.SmsChannelGroupBind;

public class SmsChannelGroupNoContextTest {

	@Test
	public void testJson() {
		SmsChannelGroup smsChannelGroup = null;
		try {
			String groupJson = "{\"groupName\":\" 通道组名称：11\",\"smsChannelGroupBindList\":[{\"channelId\":\"United States\","
					+ "\"channelScore\":\"1\",\"thresholdValue\":\"11\"}]}";
			smsChannelGroup = JsonUtil.parseToObject(groupJson, new TypeReference<SmsChannelGroup>() {
			});
			assertNotNull(smsChannelGroup);
			System.out.println(smsChannelGroup.toString());
			List<SmsChannelGroupBind> smsChannelGroupBindList = smsChannelGroup.getSmsChannelGroupBindList();
			assertTrue(smsChannelGroupBindList.size() > 0);
			for (SmsChannelGroupBind bind : smsChannelGroupBindList) {
				System.out.println(bind.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testClear() {
		List<String> list = new ArrayList<String>();
		list.add("11");
		list.add("22");
		list.clear();
		assertTrue(list.size() == 0);
	}

}
