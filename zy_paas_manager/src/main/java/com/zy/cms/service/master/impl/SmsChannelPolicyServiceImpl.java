package com.zy.cms.service.master.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zy.cms.common.RedisConstant;
import com.zy.cms.common.RedisOperator;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.mapper.master.SmsChannelPolicyMapper;
import com.zy.cms.service.master.SmsChannelPolicyRuleService;
import com.zy.cms.service.master.SmsChannelPolicyService;
import com.zy.cms.service.master.VoiceMerchantAttrService;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.VoiceMerchantAttr;
import com.zy.cms.vo.channel.SmsChannelPolicy;
import com.zy.cms.vo.channel.SmsChannelPolicyRule;
import com.zy.cms.vo.query.SmsChannelPolicyQuery;


@Service("smsChannelPolicyService")
public class SmsChannelPolicyServiceImpl implements  SmsChannelPolicyService{

	private static final ZyLogger logger = ZyLogger.getLogger(SmsChannelPolicyServiceImpl.class);
	
	@Autowired
	private SmsChannelPolicyMapper smsChannelPolicyMapper;
	
	@Autowired
	private SmsChannelPolicyRuleService smsChannelPolicyRuleService;
	
	@Resource
	private RedisOperator redisOperator;

	@Resource
	private VoiceMerchantAttrService voiceMerchantAttrService;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return smsChannelPolicyMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(SmsChannelPolicy record) {
		return smsChannelPolicyMapper.insert(record);
	}

	@Override
	public int insertSelective(SmsChannelPolicy record) {
		return smsChannelPolicyMapper.insertSelective(record);
	}

	@Override
	public SmsChannelPolicy selectByPrimaryKey(String id) {
		return smsChannelPolicyMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SmsChannelPolicy record) {
		return smsChannelPolicyMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKey(SmsChannelPolicy record) {
		return smsChannelPolicyMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<SmsChannelPolicy> querySmsChannelPolicyByEntity(SmsChannelPolicyQuery smsChannelPolicyQuery)
			throws Exception {
		return smsChannelPolicyMapper.querySmsChannelPolicyByEntity(smsChannelPolicyQuery);
	}

	@Override
	public int querySmsChannelPolicyCount(SmsChannelPolicyQuery smsChannelPolicyQuery) throws Exception {
		return smsChannelPolicyMapper.querySmsChannelPolicyCount(smsChannelPolicyQuery);
	}

	@Override
	@Transactional
	public int saveSmsChannelPolicy(String params) throws Exception {
		int result=0;
		try {
			JSONArray jsonArray = JSONArray.parseArray(params);
			String id=StringUtil.getUUID();
			String policyName="";
			String redisconstant=String.format(RedisConstant.ZHIYU_PASS_SMS_CHANNEL_POLICY_RULE, id);
			for(int i=0;i<jsonArray.size();i++){
				SmsChannelPolicyRule scpr=new SmsChannelPolicyRule();
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				policyName=jsonObject.getString("policyName");
				
				List<SmsChannelPolicy> policys=smsChannelPolicyMapper.selectSmsChannelByPolicyName(policyName);
				if(policys.size()>0){
					return -1;
				}
				
				String ruleId=StringUtil.getUUID();
				scpr.setId(ruleId);
				scpr.setKeyword(jsonObject.getString("keyword"));
				scpr.setMobiles(jsonObject.getString("mobiles"));
				scpr.setGroupType(1);
				scpr.setGroupYD(jsonObject.getString("groupYD"));
				scpr.setGroupLT(jsonObject.getString("groupLT"));
				scpr.setGroupDX(jsonObject.getString("groupDX"));
				scpr.setRuleIndex(Integer.parseInt(jsonObject.getString("policyIndex")));
				scpr.setPolicyId(id);
				int flag=smsChannelPolicyRuleService.insert(scpr);
				if(flag>0){
					 redisOperator.hset(redisconstant, ruleId, JsonUtil.toJsonString(scpr));
				}
				result+=flag;
				SmsChannelPolicy smsChannelPolicy=new SmsChannelPolicy();
			    smsChannelPolicy.setId(id);
			    smsChannelPolicy.setPolicyName(policyName);
			    insert(smsChannelPolicy);
			}
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	@Override
	@Transactional
	public int updateSmsChannelPolicy(String params) throws Exception {
		int result=0;
		try {
			JSONArray jsonArray = JSONArray.parseArray(params);
			JSONObject jsonObject = jsonArray.getJSONObject(0);
			String policyId=jsonObject.getString("policyId");
			String policyName=jsonObject.getString("policyName");
			
			List<SmsChannelPolicy> policys=smsChannelPolicyMapper.selectSmsChannelByPolicyName(policyName);
			if(policys.size()>0){
				if(policys.size()==1){
					SmsChannelPolicy scp=policys.get(0);
					if(!policyId.equals(scp.getId())){
						return -1;
					}
				}else{
					return -1;
				}
			}
			
			String redisconstant=String.format(RedisConstant.ZHIYU_PASS_SMS_CHANNEL_POLICY_RULE, policyId);
			
			SmsChannelPolicyRule smsChannelPolicyRule=new SmsChannelPolicyRule();
			smsChannelPolicyRule.setPolicyId(policyId);
			List<SmsChannelPolicyRule> lists=smsChannelPolicyRuleService.querySmsChannelPolicyRuleByEntity(smsChannelPolicyRule);
			if(lists.size()>jsonArray.size()){//需要删除某个分流规则
				for(int i=0;i<jsonArray.size();i++){
					SmsChannelPolicyRule scpr=lists.get(i);
					jsonObject = jsonArray.getJSONObject(i);
					scpr.setKeyword(jsonObject.getString("keyword"));
					scpr.setMobiles(jsonObject.getString("mobiles"));
					scpr.setGroupType(1);
					scpr.setGroupYD(jsonObject.getString("groupYD"));
					scpr.setGroupLT(jsonObject.getString("groupLT"));
					scpr.setGroupDX(jsonObject.getString("groupDX"));
					scpr.setRuleIndex(Integer.parseInt(jsonObject.getString("policyIndex")));
					int flag=smsChannelPolicyRuleService.updateByPrimaryKey(scpr);
					if(flag>0){
						 redisOperator.hset(redisconstant, scpr.getId(), JsonUtil.toJsonString(scpr));
					}
					result+=flag;
				}
				for(int i=jsonArray.size();i<lists.size();i++){
					SmsChannelPolicyRule scpr=lists.get(i);
					smsChannelPolicyRuleService.deleteByPrimaryKey(scpr.getId());
					redisOperator.hdel(redisconstant, scpr.getId());
				}
			}else if(lists.size()<jsonArray.size()){//需要增加某个分流规则
				for(int i=0;i<lists.size();i++){
					SmsChannelPolicyRule scpr=lists.get(i);
					jsonObject = jsonArray.getJSONObject(i);
					scpr.setKeyword(jsonObject.getString("keyword"));
					scpr.setMobiles(jsonObject.getString("mobiles"));
					scpr.setGroupType(1);
					scpr.setGroupYD(jsonObject.getString("groupYD"));
					scpr.setGroupLT(jsonObject.getString("groupLT"));
					scpr.setGroupDX(jsonObject.getString("groupDX"));
					scpr.setRuleIndex(Integer.parseInt(jsonObject.getString("policyIndex")));
					int flag=smsChannelPolicyRuleService.updateByPrimaryKey(scpr);
					if(flag>0){
						 redisOperator.hset(redisconstant, scpr.getId(), JsonUtil.toJsonString(scpr));
					}
					result+=flag;
				}
				for(int i=lists.size();i<jsonArray.size();i++){
					String ruleId=StringUtil.getUUID();
					SmsChannelPolicyRule scpr=new SmsChannelPolicyRule();
					jsonObject = jsonArray.getJSONObject(i);
					scpr.setId(ruleId);
					scpr.setKeyword(jsonObject.getString("keyword"));
					scpr.setMobiles(jsonObject.getString("mobiles"));
					scpr.setGroupType(1);
					scpr.setGroupYD(jsonObject.getString("groupYD"));
					scpr.setGroupLT(jsonObject.getString("groupLT"));
					scpr.setGroupDX(jsonObject.getString("groupDX"));
					scpr.setRuleIndex(Integer.parseInt(jsonObject.getString("policyIndex")));
					scpr.setPolicyId(policyId);
					int flag=smsChannelPolicyRuleService.insert(scpr);
					if(flag>0){
						 redisOperator.hset(redisconstant, ruleId, JsonUtil.toJsonString(scpr));
					}
					result+=flag;
				}
			}else{//只是编辑某个分流规则
				for(int i=0;i<lists.size();i++){
					SmsChannelPolicyRule scpr=lists.get(i);
					jsonObject = jsonArray.getJSONObject(i);
					scpr.setKeyword(jsonObject.getString("keyword"));
					scpr.setMobiles(jsonObject.getString("mobiles"));
					scpr.setGroupType(1);
					scpr.setGroupYD(jsonObject.getString("groupYD"));
					scpr.setGroupLT(jsonObject.getString("groupLT"));
					scpr.setGroupDX(jsonObject.getString("groupDX"));
					scpr.setRuleIndex(Integer.parseInt(jsonObject.getString("policyIndex")));
					int flag=smsChannelPolicyRuleService.updateByPrimaryKey(scpr);
					if(flag>0){
						 redisOperator.hset(redisconstant, scpr.getId(), JsonUtil.toJsonString(scpr));
					}
					result+=flag;
				}
			}
		   SmsChannelPolicy smsChannelPolicy=new SmsChannelPolicy();
		   smsChannelPolicy.setId(policyId);
		   smsChannelPolicy.setPolicyName(policyName);
		   updateByPrimaryKey(smsChannelPolicy);
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	@Override
	@Transactional
	public String deleteSmsChannelPolicy(String id) throws Exception {
		String result="";
		try {
			Map map=new HashMap();
			map.put("attrName", "CHANNEL_POLICY");
			map.put("attrValue", id);
			List<VoiceMerchantAttr> lists=voiceMerchantAttrService.selectByQuery(map);
			
			if(lists.size()>0){
				return "已存在客户使用该策略,暂不能删除!";
			}
			
			int flag=deleteByPrimaryKey(id);
			if(flag>0){
				SmsChannelPolicyRule smsChannelPolicyRule=new SmsChannelPolicyRule();
				smsChannelPolicyRule.setPolicyId(id);
				List<SmsChannelPolicyRule> scprs=smsChannelPolicyRuleService.querySmsChannelPolicyRuleByEntity(smsChannelPolicyRule);
				flag=smsChannelPolicyRuleService.deleteByPolicyId(id);
				if(flag>0){
					for(SmsChannelPolicyRule scpr:scprs){
						String redisconstant=String.format(RedisConstant.ZHIYU_PASS_SMS_CHANNEL_POLICY_RULE, id);
						redisOperator.hdel(redisconstant,scpr.getId());
					}
					return "处理成功";
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

}
