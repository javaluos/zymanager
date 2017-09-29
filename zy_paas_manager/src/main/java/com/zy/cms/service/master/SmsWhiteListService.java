package com.zy.cms.service.master;

import com.zy.cms.vo.SmsBlackListInfo;
import com.zy.cms.vo.SmsWhiteListInfo;
import com.zy.cms.vo.query.SmsBlackListQuery;

import java.util.List;

/**
 * 短信白名单列表
 * @author JasonXu
 *
 */
public interface SmsWhiteListService {

	int deleteByPrimaryKey(Integer id) throws Exception;

    int insert(SmsWhiteListInfo record) throws Exception;

    int insertSelective(SmsWhiteListInfo record) throws Exception;

    SmsWhiteListInfo selectByPrimaryKey(Integer id) throws Exception;

    int updateByPrimaryKeySelective(SmsWhiteListInfo record) throws Exception;

    int updateByPrimaryKey(SmsWhiteListInfo record) throws Exception;

    SmsWhiteListInfo querySmsWhiteList(String mobile) throws Exception;
    
    Integer queryCountByEntity(SmsBlackListQuery query) throws Exception;

	List<SmsWhiteListInfo> queryListByEntity(SmsBlackListQuery query) throws Exception;
	
	int deleteByMobile(String mobile) throws Exception;

    int addSmsWhiteListInfo(String businessname, String mobile, String remark) throws Exception;

    String checkPhoneInBlackList(String mobiles) throws Exception;
}
