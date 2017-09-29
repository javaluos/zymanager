package com.zy.cms.service.master.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zy.cms.common.RedisConstant;
import com.zy.cms.common.RedisOperator;
import com.zy.cms.mapper.master.SmsChannelGroupBindMapper;
import com.zy.cms.service.master.SmsChannelGroupBindService;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.vo.channel.SmsMerchantChannelGroupBind;
import com.zy.cms.vo.query.SmsChannelGroupBindQuery;

@Service("smsChannelGroupBindService")
public class SmsChannelGroupBindServiceImpl implements SmsChannelGroupBindService {

	@Autowired
	SmsChannelGroupBindMapper smsChannelGroupBindMapper;

	@Autowired
	private RedisOperator redisOperator;

	@Override
	public SmsMerchantChannelGroupBind getMerchantChannelGroupBindByApiAccount(String apiAccount) {
		return smsChannelGroupBindMapper.getMerchantChannelGroupBindByApiAccount(apiAccount);
	}

	@Transactional(rollbackFor = Exception.class)
	public int saveMerchantChannelGroupBind(SmsMerchantChannelGroupBind smsMerchantChannelGroupBind) {
		List<SmsMerchantChannelGroupBind> recordList = new ArrayList<SmsMerchantChannelGroupBind>();
		recordList.add(smsMerchantChannelGroupBind);
		Integer id = smsMerchantChannelGroupBind.getId();
		SmsMerchantChannelGroupBind oldMerchantChannelGroup = smsChannelGroupBindMapper
				.getMerchantChannelGroupBindByApiAccount(smsMerchantChannelGroupBind.getApiAccount());
		int rs = -1;
		boolean hasMerchantChannelGroup = (null != oldMerchantChannelGroup && null != oldMerchantChannelGroup.getId()
				&& 0 < oldMerchantChannelGroup.getId());
		if ((null != id && 0 < id) || hasMerchantChannelGroup) {
			if (hasMerchantChannelGroup) {
				smsMerchantChannelGroupBind.setId(oldMerchantChannelGroup.getId());
			}
			rs = smsChannelGroupBindMapper.updateMerchantGroupBindById(smsMerchantChannelGroupBind);
		} else {
			rs = smsChannelGroupBindMapper.insertMerchantChannelGroupBind(recordList);
		}
		if (rs > 0) {
			redisOperator.hset(RedisConstant.ZHIYU_PASS_SMS_ACCOUNT_CHANNELGROUP,
					smsMerchantChannelGroupBind.getApiAccount(), JsonUtil.toJsonString(smsMerchantChannelGroupBind));
		}
		return rs;
	}

	@Transactional(rollbackFor = Exception.class)
	public int deleteMerchantGroupBind(String apiAccount, Integer id) {
		Long hdel = redisOperator.hdel(RedisConstant.ZHIYU_PASS_SMS_ACCOUNT_CHANNELGROUP, apiAccount);
		int delRs = 0;
		if (hdel > 0) {
			delRs = smsChannelGroupBindMapper.deleteMerchantGroupBind(id);
		}
		return delRs;
	}

	@Override
	public List<SmsMerchantChannelGroupBind> listMerchantChannelGroupBind(SmsChannelGroupBindQuery query) {
		return smsChannelGroupBindMapper.listMerchantChannelGroupBind(query);
	}

	@Override
	public int listMerchantChannelGroupBindCount(SmsChannelGroupBindQuery query) {
		return smsChannelGroupBindMapper.listMerchantChannelGroupBindCount(query);
	}

	@Override
	public SmsMerchantChannelGroupBind getMerchantChannelGroupBindById(Integer id) {
		return smsChannelGroupBindMapper.getMerchantChannelGroupBindById(id);
	}

	@Override
	public List<String> listGroupIdByChannelId(String channelId) {
		return smsChannelGroupBindMapper.listGroupIdByChannelId(channelId);
	}

}
