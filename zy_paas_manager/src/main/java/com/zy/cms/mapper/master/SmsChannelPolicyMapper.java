package com.zy.cms.mapper.master;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.zy.cms.vo.channel.SmsChannelPolicy;
import com.zy.cms.vo.query.SmsChannelPolicyQuery;

public interface SmsChannelPolicyMapper {
	
    int deleteByPrimaryKey(String id);

    int insert(SmsChannelPolicy record);

    int insertSelective(SmsChannelPolicy record);

    SmsChannelPolicy selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SmsChannelPolicy record);

    int updateByPrimaryKey(SmsChannelPolicy record);
    
    public List<SmsChannelPolicy> querySmsChannelPolicyByEntity(SmsChannelPolicyQuery smsShuntingStrategyQuery) throws Exception;
	
	public int querySmsChannelPolicyCount(SmsChannelPolicyQuery smsShuntingStrategyQuery) throws Exception;
	
	public List<SmsChannelPolicy> selectSmsChannelByPolicyName(@RequestParam(value="policyName") String policyName) throws Exception;
}