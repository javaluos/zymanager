package com.zy.cms.service.sms.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zy.cms.mapper.sms.SmsReplyMapper;
import com.zy.cms.service.sms.SmsReplyService;
import com.zy.cms.vo.SmsReply;
import com.zy.cms.vo.query.SmsReplyQuery;

@Service("smsReplyService")
public class SmsReplyServiceImpl implements SmsReplyService {

	@Autowired
	private SmsReplyMapper smsReplyMapper;

	@Override
	public int querySmsReplyCount(SmsReplyQuery query) {

		return smsReplyMapper.querySmsReplyCount(query);
	}

	@Override
	public List<SmsReply> querySmsReplys(SmsReplyQuery query) {

		return smsReplyMapper.querySmsReplys(query);
	}

}
