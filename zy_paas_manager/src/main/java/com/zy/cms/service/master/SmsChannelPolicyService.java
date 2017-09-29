package com.zy.cms.service.master;

import java.util.List;

import com.zy.cms.vo.channel.SmsChannelPolicy;
import com.zy.cms.vo.query.SmsChannelPolicyQuery;

/**
 * 
 * @author JasonXU
 * @date 2017-07-06
 */
public interface SmsChannelPolicyService {
	
	public int deleteByPrimaryKey(String id);

	public int insert(SmsChannelPolicy record);

	public int insertSelective(SmsChannelPolicy record);

	public SmsChannelPolicy selectByPrimaryKey(String id);

	public int updateByPrimaryKeySelective(SmsChannelPolicy record);

	public int updateByPrimaryKey(SmsChannelPolicy record);
	
	public List<SmsChannelPolicy> querySmsChannelPolicyByEntity(SmsChannelPolicyQuery smsChannelPolicyQuery) throws Exception;
	
	public int querySmsChannelPolicyCount(SmsChannelPolicyQuery smsChannelPolicyQueryO) throws Exception;
	
	public int saveSmsChannelPolicy(String params) throws Exception;
	
	public int updateSmsChannelPolicy(String params) throws Exception;
	
	public String deleteSmsChannelPolicy(String id) throws Exception;
}
