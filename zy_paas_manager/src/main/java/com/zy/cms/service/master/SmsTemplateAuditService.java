package com.zy.cms.service.master;

import java.util.List;

import com.zy.cms.vo.MerchantSmsTemplateVo;
import com.zy.cms.vo.query.SmsTemplateAuditQuery;

public interface SmsTemplateAuditService {

	Integer querySmsTemplateAuthByEntity(SmsTemplateAuditQuery query);

	List<MerchantSmsTemplateVo> querySmsTemplateAuthListByEntity(SmsTemplateAuditQuery query);

	MerchantSmsTemplateVo getById(String id);

	boolean doAudit(String id, String username, String reason, String content, String flag);

	boolean deleteById(String id);

}
