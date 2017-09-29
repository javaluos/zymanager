package com.zy.cms.service.sms.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zy.cms.mapper.sms2.SmsSendFilterMapper;
import com.zy.cms.service.sms.SmsMerchantSendFilterService;
import com.zy.cms.vo.SmsSendFilter;
import com.zy.cms.vo.query.SmsSendFilterQuery;

@Service("smsMerchantSendFilterService")
public class SmsMerchantSendFilterServiceImpl implements SmsMerchantSendFilterService {

	@Autowired
	private SmsSendFilterMapper smsSendFilterMapper;

	@Override
	public int deleteByPrimaryKey(String smsId) {
		return smsSendFilterMapper.deleteByPrimaryKey(smsId);
	}

	@Override
	public int insert(SmsSendFilter record) {
		return smsSendFilterMapper.insert(record);
	}

	@Override
	public int insertSelective(SmsSendFilter record) {
		return smsSendFilterMapper.insertSelective(record);
	}

	@Override
	public SmsSendFilter selectByPrimaryKey(String smsId) {
		return smsSendFilterMapper.selectByPrimaryKey(smsId);
	}

	@Override
	public int updateByPrimaryKeySelective(SmsSendFilter record) {
		return smsSendFilterMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SmsSendFilter record) {
		return smsSendFilterMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<SmsSendFilter> queryListByEntity(SmsSendFilterQuery smsSendFilterQuery) throws Exception {
		return smsSendFilterMapper.queryListByEntity(smsSendFilterQuery);
	}

	@Override
	public int queryCountByEntity(SmsSendFilterQuery smsSendFilterQuery) throws Exception {
		return smsSendFilterMapper.queryCountByEntity(smsSendFilterQuery);
	}
	
	
	@Override
	public List<SmsSendFilter> queryAuditPaasSms(SmsSendFilterQuery smsSendFilterQuery) throws Exception {
		return smsSendFilterMapper.queryAuditPaasSms(smsSendFilterQuery);
	}
	
	@Override
	public int deleteBySmsIdList(SmsSendFilterQuery smsSendFilterQuery) throws Exception {
		return smsSendFilterMapper.deleteBySmsIdList(smsSendFilterQuery);
	}
	
	@Override
	public List<SmsSendFilter> queryListDetailByEntity(SmsSendFilterQuery smsSendFilterQuery) throws Exception {
		return smsSendFilterMapper.queryListDetailByEntity(smsSendFilterQuery);
	}



}
