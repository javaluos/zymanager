package com.zy.cms.mapper.master;

import java.util.List;

import com.zy.cms.vo.channel.SmsChannelPolicyRule;
import com.zy.cms.vo.query.SmsChannelPolicyQuery;

/**
 * 分流策略
 * @author JasonXu
 *
 */
public interface SmsChannelPolicyRuleMapper {
	
    int deleteByPrimaryKey(String id) throws Exception;

    int insert(SmsChannelPolicyRule record) throws Exception;

    int insertSelective(SmsChannelPolicyRule record) throws Exception;

    SmsChannelPolicyRule selectByPrimaryKey(String id) throws Exception;

    int updateByPrimaryKeySelective(SmsChannelPolicyRule record) throws Exception;

    int updateByPrimaryKey(SmsChannelPolicyRule record) throws Exception;
    
    public List<SmsChannelPolicyRule> queryShuntingStrategyByEntity(SmsChannelPolicyRule smsChannelPolicyRule) throws Exception;
	
	public int queryShuntingStrategyCount(SmsChannelPolicyRule smsChannelPolicyRule) throws Exception;
	
	public int deleteByPolicyId(String policyId) throws Exception;
	
	public List<SmsChannelPolicyRule> queryListByGroupId(String groupId) throws Exception;
	
	
}