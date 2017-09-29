package com.zy.cms.service.master.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zy.cms.common.RedisConstant;
import com.zy.cms.common.RedisOperator;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.mapper.master.SmsChannelGroupBindMapper;
import com.zy.cms.mapper.master.SmsChannelGroupMapper;
import com.zy.cms.service.master.SmsChannelGroupService;
import com.zy.cms.service.master.SmsChannelPolicyRuleService;
import com.zy.cms.util.GenerateCodeUtil;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.channel.SmsChannelGroup;
import com.zy.cms.vo.channel.SmsChannelGroupBind;
import com.zy.cms.vo.channel.SmsChannelGroupResult;
import com.zy.cms.vo.query.SmsChannelGroupQuery;

@Service("smsChannelGroupService")
public class SmsChannelGroupServiceImpl implements SmsChannelGroupService {

	private static final ZyLogger logger = ZyLogger.getLogger(SmsChannelGroupServiceImpl.class);

	@Autowired
	SmsChannelGroupMapper smsChannelGroupMapper;

	@Autowired
	SmsChannelGroupBindMapper smsChannelGroupBindMapper;

	@Autowired
	SmsChannelPolicyRuleService smsChannelPolicyRuleService;

	@Autowired
	private RedisOperator redisOperator;

	public static final String SMS_CHANNEL_GROUP_ID_PREFIX = "cgp";

	@Transactional(rollbackFor = Exception.class)
	public String save(SmsChannelGroup scg) {
		int rs = -1;
		try {
			// 校验通道组是否重复
			int count = smsChannelGroupMapper.selectGroupNameCount(scg.getGroupName());
			if (count > 0 && StringUtil.isEmpty(scg.getId())) {
				return SmsChannelGroupResult.CHANNEL_GROUP_NAME_REPECT.getErrorCode();
			}

			String groupId = null;
			boolean isUpdate = false;
			// 插入操作
			if (StringUtil.isEmpty(scg.getId())) {
				groupId = SMS_CHANNEL_GROUP_ID_PREFIX + GenerateCodeUtil.generateCharAndNumr(13);
				scg.setId(groupId);
			}
			// 修改操作
			else {
				groupId = scg.getId();
				isUpdate = true;
			}
			List<SmsChannelGroupBind> bindList = scg.getSmsChannelGroupBindList();
			for (SmsChannelGroupBind bind : bindList) {
				bind.setChannelGroupId(groupId);
			}
			// 存入redis
			String redisGroupKey = RedisConstant.ZHIYU_PASS_SMS_CHANNELGROUP_INFO;
			String redisBindKey = String.format(RedisConstant.ZHIYU_PASS_SMS_CHANNELGROUP_RELATIN, groupId);
			if (isUpdate) {
				// 删除旧的group下的绑定以及redis
				deleteGroupBind(groupId, redisBindKey);
				// 如果是修改操作 删除通道组绑定老数据后 修改通道组
				rs = smsChannelGroupMapper.updateByPrimaryKeySelective(scg);
			} else {
				// 如果是新增操作 直接插入数据
				rs = smsChannelGroupMapper.insert(scg);
			}
			int listRs = smsChannelGroupBindMapper.insertChannelGroupBindList(bindList);
			if (listRs > 0 && rs > 0) {
				for (SmsChannelGroupBind bind : bindList) {
					// 通道组绑定通道放入redis
					redisOperator.hset(redisBindKey, bind.getChannelId(), JsonUtil.toJsonString(bind));
				}
				// 通道组放入redis 不需要通道组绑定通道的集合
				scg.getSmsChannelGroupBindList().clear();
				redisOperator.hset(redisGroupKey, groupId, JsonUtil.toJsonString(scg));
			}
			if (rs > 0) {
				return SmsChannelGroupResult.ADD_CHANNEL_GROUP_SUCCESS.getErrorCode();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
		return SmsChannelGroupResult.ADD_CHANNEL_GROUP_FAILED.getErrorCode();
	}

	@Transactional(rollbackFor = Exception.class)
	public int deleteSmsChannelGroupAndBind(String groupId) {
		try {
			boolean isUsedByRule = smsChannelPolicyRuleService.queryListByGroupId(groupId);
			boolean isUsedByAccount = smsChannelGroupBindMapper.countGroupBindByGroupId(groupId) > 0;
			if (!isUsedByRule && !isUsedByAccount) {
				String redisBindKey = String.format(RedisConstant.ZHIYU_PASS_SMS_CHANNELGROUP_RELATIN, groupId);
				// 删除旧的group下的绑定以及redis
				deleteGroupBind(groupId, redisBindKey);
				int deleteByPrimaryKey = smsChannelGroupMapper.deleteByPrimaryKey(groupId);
				if (deleteByPrimaryKey > 0) {
					String redisGroupKey = RedisConstant.ZHIYU_PASS_SMS_CHANNELGROUP_INFO;
					redisOperator.hdel(redisGroupKey, groupId);
				}
				return deleteByPrimaryKey;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
		return 0;
	}

	@Override
	public List<SmsChannelGroup> listSmsChannelGroup(SmsChannelGroupQuery query) {
		// TODO Auto-generated method stub
		return smsChannelGroupMapper.listSmsChannelGroup(query);
	}

	@Override
	public List<Map<String, String>> getSmsChannelSelect() {
		// TODO Auto-generated method stub
		return smsChannelGroupMapper.getSmsChannelSelect();
	}

	@Override
	public int listSmsChannelGroupCount(SmsChannelGroupQuery query) {
		// TODO Auto-generated method stub
		return smsChannelGroupMapper.listSmsChannelGroupCount(query);
	}

	@Override
	public List<Map<String, String>> getSmsChannelGroupSelect() {
		// TODO Auto-generated method stub
		return smsChannelGroupMapper.getSmsChannelGroupSelect();
	}

	@Override
	public SmsChannelGroup selectSmsChannelGroupById(String id) {
		// TODO Auto-generated method stub
		return smsChannelGroupMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<SmsChannelGroup> listSmsChannelGroupNoPage() {
		// TODO Auto-generated method stub
		return smsChannelGroupMapper.listSmsChannelGroupNoPage();
	}

	private void deleteGroupBind(String groupId, String redisBindKey) {
		SmsChannelGroup oldGroup = smsChannelGroupMapper.selectByPrimaryKey(groupId);
		List<SmsChannelGroupBind> oldBind = oldGroup.getSmsChannelGroupBindList();
		String[] bindChannelIds = new String[oldBind.size()];
		List<Integer> ids = new ArrayList<Integer>();
		for (int i = 0; i < oldBind.size(); i++) {
			bindChannelIds[i] = oldBind.get(i).getChannelId();
			ids.add(oldBind.get(i).getId());
		}
		redisOperator.hdels(redisBindKey, bindChannelIds);
		smsChannelGroupBindMapper.deleteByIds(ids);
	}
}
