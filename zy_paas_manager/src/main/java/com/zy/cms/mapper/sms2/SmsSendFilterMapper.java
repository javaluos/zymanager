package com.zy.cms.mapper.sms2;

import java.util.List;

import com.zy.cms.vo.SmsSendFilter;
import com.zy.cms.vo.query.SmsSendFilterQuery;

public interface SmsSendFilterMapper {
	
    int deleteByPrimaryKey(String smsId);

    int insert(SmsSendFilter record);

    int insertSelective(SmsSendFilter record);

    SmsSendFilter selectByPrimaryKey(String smsId);

    int updateByPrimaryKeySelective(SmsSendFilter record);

    int updateByPrimaryKey(SmsSendFilter record);
    
    List<SmsSendFilter> queryListByEntity(SmsSendFilterQuery smsSendFilterQuery);
	
    int queryCountByEntity(SmsSendFilterQuery smsSendFilterQuery);
	
	List<SmsSendFilter> queryListDetailByEntity(SmsSendFilterQuery smsSendFilterQuery);
	
	List<SmsSendFilter> queryAuditPaasSms(SmsSendFilterQuery smsSendFilterQuery);
	
	int deleteBySmsIdList(SmsSendFilterQuery smsSendFilterQuery);
}