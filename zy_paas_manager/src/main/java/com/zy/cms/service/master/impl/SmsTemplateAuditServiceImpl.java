package com.zy.cms.service.master.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zy.cms.common.RedisConstant;
import com.zy.cms.common.RedisOperator;
import com.zy.cms.enums.AuditStatusEnum;
import com.zy.cms.mapper.master.MerchantSmsTemplateMapper;
import com.zy.cms.service.master.SmsTemplateAuditService;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.MerchantSmsTemplate;
import com.zy.cms.vo.MerchantSmsTemplateVo;
import com.zy.cms.vo.query.SmsTemplateAuditQuery;

@Service("smsTemplateAuditService")
@Transactional
public class SmsTemplateAuditServiceImpl implements SmsTemplateAuditService {

	@Autowired
	private MerchantSmsTemplateMapper mapper;
	@Autowired
	private RedisOperator redis;

	@Override
	public Integer querySmsTemplateAuthByEntity(SmsTemplateAuditQuery query) {
		return mapper.selectSmsTemplateCount(query);
	}

	@Override
	public List<MerchantSmsTemplateVo> querySmsTemplateAuthListByEntity(SmsTemplateAuditQuery query) {
		return mapper.selectSmsTemplateList(query);
	}

	@Override
	public MerchantSmsTemplateVo getById(String id) {
		return mapper.selectSmsTemplateVOById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean doAudit(String id, String username, String reason, String content, String flag) {

		boolean result = false;
		MerchantSmsTemplate merchantSmsTemplate = mapper.selectByPrimaryKey(id);
		merchantSmsTemplate.setAuthUser(username);
		Date now = new Date();
		merchantSmsTemplate.setAuthResultTime(now);
		merchantSmsTemplate.setUpdateTime(now);
		if ("0".equals(flag)) {
			merchantSmsTemplate.setReason(reason);
			merchantSmsTemplate.setStatus(AuditStatusEnum.FAILED.getType());
		} else if ("1".equals(flag)) {
			merchantSmsTemplate.setStatus(AuditStatusEnum.CHECKED.getType());
			if (!StringUtil.isEmpty(content)) {
				merchantSmsTemplate.setContent(content);
			}
		}
		result = mapper.updateByPrimaryKeySelective(merchantSmsTemplate) > 0;

		String redisKey = String.format(RedisConstant.ZHIYU_PAAS_SMS_TEMPLATE_ID, merchantSmsTemplate.getApiAccount());
		if (result && flag.equals("1")) {
			// 修改redis
			redis.hset(redisKey, merchantSmsTemplate.getId(), JsonUtil.objectToJson(merchantSmsTemplate));
		}
		if (result && !flag.equals("1")) {
			// 删除redis
			redis.hdel(redisKey, merchantSmsTemplate.getId());
		}
		return result;
	}

	@Override
	public boolean deleteById(String id) {

		MerchantSmsTemplate merchantSmsTemplate = mapper.selectByPrimaryKey(id);
		merchantSmsTemplate.setIsLocked(1);
		return mapper.updateByPrimaryKeySelective(merchantSmsTemplate) > 0;
	}

}
