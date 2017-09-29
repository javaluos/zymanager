package com.zy.cms.service.master;

import java.util.List;
import com.zy.cms.vo.channel.SmsChannelPolicyRule;
import com.zy.cms.vo.query.SmsChannelPolicyQuery;

/**
 * 
 * @author JasonXU
 * @date 2017-07-06
 */
public interface SmsChannelPolicyRuleService {
	
	public int deleteByPrimaryKey(String id) throws Exception;

	public int insert(SmsChannelPolicyRule record) throws Exception;

    public int insertSelective(SmsChannelPolicyRule record) throws Exception;

    public SmsChannelPolicyRule selectByPrimaryKey(String id) throws Exception;

    public int updateByPrimaryKeySelective(SmsChannelPolicyRule record) throws Exception;

    public int updateByPrimaryKey(SmsChannelPolicyRule record) throws Exception;
    
	public List<SmsChannelPolicyRule> querySmsChannelPolicyRuleByEntity(SmsChannelPolicyRule smsChannelPolicyRule) throws Exception;
	
	public int querySmsChannelPolicyRuleCount(SmsChannelPolicyRule smsChannelPolicyRule) throws Exception;
	
	public int deleteByPolicyId(String policyId) throws Exception;
	
	public boolean queryListByGroupId(String groupId) throws Exception;
}
