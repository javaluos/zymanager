package com.zy.cms.service.master.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zy.cms.common.ZyLogger;
import com.zy.cms.mapper.master.SmsChannelPolicyRuleMapper;
import com.zy.cms.service.master.SmsChannelPolicyRuleService;
import com.zy.cms.vo.channel.SmsChannelPolicyRule;


@Service("smsChannelPolicyRuleService")
public class SmsChannelPolicyRuleServiceImpl implements  SmsChannelPolicyRuleService{

	private static final ZyLogger logger = ZyLogger.getLogger(SmsChannelPolicyRuleServiceImpl.class);
	
	@Autowired
	private SmsChannelPolicyRuleMapper smsShuntingStrategyMapper;

	@Override
	public int deleteByPrimaryKey(String id) throws Exception{
		return smsShuntingStrategyMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(SmsChannelPolicyRule record) throws Exception{
		return smsShuntingStrategyMapper.insert(record);
	}

	@Override
	public int insertSelective(SmsChannelPolicyRule record) throws Exception{
		return smsShuntingStrategyMapper.insertSelective(record);
	}

	@Override
	public SmsChannelPolicyRule selectByPrimaryKey(String id) throws Exception{
		return smsShuntingStrategyMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SmsChannelPolicyRule record) throws Exception{
		return smsShuntingStrategyMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SmsChannelPolicyRule record) throws Exception{
		return smsShuntingStrategyMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<SmsChannelPolicyRule> querySmsChannelPolicyRuleByEntity(SmsChannelPolicyRule smsChannelPolicyRule)
			throws Exception {
		return smsShuntingStrategyMapper.queryShuntingStrategyByEntity(smsChannelPolicyRule);
	}

	@Override
	public int querySmsChannelPolicyRuleCount(SmsChannelPolicyRule smsChannelPolicyRule) throws Exception {
		return smsShuntingStrategyMapper.queryShuntingStrategyCount(smsChannelPolicyRule);
	}

	@Override
	public int deleteByPolicyId(String policyId) throws Exception {
		return smsShuntingStrategyMapper.deleteByPolicyId(policyId);
	}

	@Override
	public boolean queryListByGroupId(String groupId) throws Exception {
		List<SmsChannelPolicyRule> list=smsShuntingStrategyMapper.queryListByGroupId(groupId);
		if(list.size()>0){
			return true;
		}else{
			return false;
		}
	}


}
