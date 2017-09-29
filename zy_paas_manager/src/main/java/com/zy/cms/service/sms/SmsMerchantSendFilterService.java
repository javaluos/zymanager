package com.zy.cms.service.sms;

import java.util.List;

import com.zy.cms.vo.SmsSendFilter;
import com.zy.cms.vo.query.SmsSendFilterQuery;

/**
 * 敏感词拦截短信发送记录
 */
public interface SmsMerchantSendFilterService {

	int deleteByPrimaryKey(String smsId);

    int insert(SmsSendFilter record);

    int insertSelective(SmsSendFilter record);

    SmsSendFilter selectByPrimaryKey(String smsId);

    int updateByPrimaryKeySelective(SmsSendFilter record);

    int updateByPrimaryKey(SmsSendFilter record);
    
	public List<SmsSendFilter> queryListByEntity(SmsSendFilterQuery smsSendFilterQuery) throws Exception;
	
	public List<SmsSendFilter> queryAuditPaasSms(SmsSendFilterQuery smsSendFilterQuery) throws Exception;
	
	public int queryCountByEntity(SmsSendFilterQuery smsSendFilterQuery) throws Exception;
	
	public List<SmsSendFilter> queryListDetailByEntity(SmsSendFilterQuery smsSendFilterQuery) throws Exception;
	
	public int deleteBySmsIdList(SmsSendFilterQuery smsSendFilterQuery) throws Exception;
}
