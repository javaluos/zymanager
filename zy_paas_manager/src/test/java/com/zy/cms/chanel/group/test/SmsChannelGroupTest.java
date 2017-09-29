package com.zy.cms.chanel.group.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zy.cms.common.RedisConstant;
import com.zy.cms.common.RedisOperator;
import com.zy.cms.mapper.master.SmsChannelGroupBindMapper;
import com.zy.cms.mapper.master.SmsChannelGroupMapper;
import com.zy.cms.service.master.SmsChannelGroupService;
import com.zy.cms.service.master.SmsChannelPolicyRuleService;
import com.zy.cms.util.GenerateCodeUtil;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.vo.channel.SmsChannelGroup;
import com.zy.cms.vo.channel.SmsChannelGroupBind;
import com.zy.cms.vo.channel.SmsMerchantChannelGroupBind;
import com.zy.cms.vo.query.SmsChannelGroupBindQuery;
import com.zy.cms.vo.query.SmsChannelGroupQuery;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-application-context.xml" })
public class SmsChannelGroupTest {

	@Resource
	SmsChannelGroupMapper smsChannelGroupMapper;

	@Resource
	SmsChannelGroupBindMapper smsChannelGroupBindMapper;

	@Resource
	SmsChannelGroupService smsChannelGroupService;

	@Resource
	SmsChannelPolicyRuleService smsChannelPolicyRuleService;

	@Autowired
	private RedisOperator redisOperator;

	@Test
	public void testGroupInsertAndSelect() {
		try {
			List<Map<String, String>> smsChannelSelect = smsChannelGroupMapper.getSmsChannelSelect();
			assertTrue(smsChannelSelect.size() > 0);
			/*
			 * for (Map<String, String> map : smsChannelSelect) { for
			 * (Entry<String, String> s : map.entrySet()) {
			 * System.out.println(s.getKey() + "------------" + s.getValue()); }
			 * }
			 */
			List<SmsChannelGroupBind> smsChannelGroupBindList = new ArrayList<SmsChannelGroupBind>();
			// String groupId = "cgp" +
			// GenerateCodeUtil.generateCharAndNumr(13);
			String groupName = "group_name" + GenerateCodeUtil.generateCharAndNumr(5);
			smsChannelGroupBindList.add(new SmsChannelGroupBind(null, "BJ-CT-CHANNEL-DX", 100, 1000, "remark", 1));
			SmsChannelGroup smsChannelGroup = new SmsChannelGroup(null, groupName, 1, "remark", 1,
					smsChannelGroupBindList);
			smsChannelGroupService.save(smsChannelGroup);
			SmsChannelGroupQuery query = new SmsChannelGroupQuery();
			// query.setPageOffset(0);
			// query.setPageSize(20);
			List<SmsChannelGroup> listSmsChannelGroup = smsChannelGroupService.listSmsChannelGroup(query);
			assertTrue(listSmsChannelGroup.size() > 0);
			for (SmsChannelGroup group : listSmsChannelGroup) {
				assertTrue(group.getSmsChannelGroupBindList().size() > 0);
				for (SmsChannelGroupBind bind : group.getSmsChannelGroupBindList()) {
					System.out.println(bind.getChannelScore());
				}
			}
			smsChannelGroupService.listSmsChannelGroupCount(query);
			SmsChannelGroupQuery queryChannelName = new SmsChannelGroupQuery();
			queryChannelName.setPageOffset(0);
			queryChannelName.setPageSize(20);
			queryChannelName.setChannelGroupName("通道组名称");
			int listSmsChannelGroupCount = smsChannelGroupService.listSmsChannelGroupCount(queryChannelName);
			System.out.println(listSmsChannelGroupCount);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}

	@Test
	public void testGroupBindInsertAndSelect() {
		try {
			List<SmsMerchantChannelGroupBind> recordList = new ArrayList<SmsMerchantChannelGroupBind>();
			String apiAccount1 = "ACC7c2f781d89034a198958be4273a7ba2a";
			String apiAccount2 = "ACC19a660c5b79947768bda869f3128a868";
			// String apiAccount, String groupYd, String groupLt, String groupDx
			recordList.add(new SmsMerchantChannelGroupBind(apiAccount1, "12", "34", "45"));
			recordList.add(new SmsMerchantChannelGroupBind(apiAccount2, "cgp9Hr14ZVq7r6Mp", "cgpFM1HIs55pM154",
					"cgpm1u4537336L4z"));
			// smsChannelGroupBindMapper.insertMerchantChannelGroupBind(recordList);
			SmsMerchantChannelGroupBind merchantChannelGroupBindByApiAccount = smsChannelGroupBindMapper
					.getMerchantChannelGroupBindByApiAccount(apiAccount1);
			System.out.println(merchantChannelGroupBindByApiAccount.toString());
			SmsChannelGroupBindQuery smsChannelGroupBindQuery = new SmsChannelGroupBindQuery();
			smsChannelGroupBindQuery.setPageOffset(0);
			smsChannelGroupBindQuery.setPageSize(20);
			smsChannelGroupBindQuery.setBusinessName("进");
			smsChannelGroupBindMapper.listMerchantChannelGroupBindCount(smsChannelGroupBindQuery);
			List<SmsMerchantChannelGroupBind> listMerchantChannelGroupBind = smsChannelGroupBindMapper
					.listMerchantChannelGroupBind(smsChannelGroupBindQuery);
			for (SmsMerchantChannelGroupBind bind : listMerchantChannelGroupBind) {
				System.out.println(bind.toString());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}

	@Test
	public void testList() {
		try {
			List<SmsChannelGroup> listSmsChannelGroupNoPage = smsChannelGroupService.listSmsChannelGroupNoPage();
			for (SmsChannelGroup smsChannelGroup : listSmsChannelGroupNoPage) {
				System.out.println(smsChannelGroup.toString());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}

	@Test
	public void testRedis() {
		try {
			List<SmsChannelGroupBind> smsChannelGroupBindList = new ArrayList<SmsChannelGroupBind>();
			String groupId = "cgpde7p7s9lwD84m";
			String groupName = "group_name" + GenerateCodeUtil.generateCharAndNumr(5);
			smsChannelGroupBindList.add(new SmsChannelGroupBind(null, "BJ-CT-CHANNEL-DX", 9527, 9528, "remark", 1));
			SmsChannelGroup smsChannelGroup = new SmsChannelGroup(groupId, groupName, 1, "remark", 1,
					smsChannelGroupBindList);
			smsChannelGroupService.save(smsChannelGroup);
			String redisKey = String.format(RedisConstant.ZHIYU_PASS_SMS_CHANNELGROUP_RELATIN, groupId);
			for (SmsChannelGroupBind bind : smsChannelGroupBindList) {
				bind.setChannelGroupId(groupId);
				redisOperator.hset(redisKey, redisKey, JsonUtil.toJsonString(bind));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}

	@Test
	public void testDelete() {
		try {
			String groupId = "cgp00J1b00QI7o1t";
			smsChannelGroupService.deleteSmsChannelGroupAndBind(groupId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}

}
