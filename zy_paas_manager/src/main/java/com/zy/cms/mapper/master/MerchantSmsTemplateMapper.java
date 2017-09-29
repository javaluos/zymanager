package com.zy.cms.mapper.master;

import java.util.List;

import com.zy.cms.vo.MerchantSmsTemplate;
import com.zy.cms.vo.MerchantSmsTemplateVo;
import com.zy.cms.vo.query.SmsTemplateAuditQuery;

public interface MerchantSmsTemplateMapper {
    int deleteByPrimaryKey(String id);

    int insert(MerchantSmsTemplate record);

    int insertSelective(MerchantSmsTemplate record);

    MerchantSmsTemplate selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MerchantSmsTemplate record);

    int updateByPrimaryKey(MerchantSmsTemplate record);

	Integer selectSmsTemplateCount(SmsTemplateAuditQuery query);

	List<MerchantSmsTemplateVo> selectSmsTemplateList(SmsTemplateAuditQuery query);

	MerchantSmsTemplateVo selectSmsTemplateVOById(String id);
}