package com.zy.cms.service.sms;

import java.util.List;

import com.zy.cms.vo.SmsReply;
import com.zy.cms.vo.query.SmsReplyQuery;

/**
 * 

 */
public interface SmsReplyService {

	/**
	 * 获取总数
	 * 
	 * @param params
	 *            可为 merchantPhone startTime endTime pageNum
	 * 
	 * @return
	 */
	public int querySmsReplyCount(SmsReplyQuery query);

	/**
	 * 获取分页列表<分页>
	 * 
	 * @param params
	 *            可为 merchantPhone startTime endTime pageNum pageSize
	 * @return
	 */
	public List<SmsReply> querySmsReplys(SmsReplyQuery query);
}
