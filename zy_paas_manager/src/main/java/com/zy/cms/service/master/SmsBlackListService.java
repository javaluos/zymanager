package com.zy.cms.service.master;

import java.util.List;
import com.zy.cms.vo.SmsBlackListInfo;
import com.zy.cms.vo.query.SmsBlackListQuery;

/**
 * 短信黑名单列表
 * @author JasonXu
 *
 */
public interface SmsBlackListService {

	int deleteByPrimaryKey(Integer id) throws Exception;

    int insert(SmsBlackListInfo record) throws Exception;

    int insertSelective(SmsBlackListInfo record) throws Exception;

    SmsBlackListInfo selectByPrimaryKey(Integer id) throws Exception;

    int updateByPrimaryKeySelective(SmsBlackListInfo record) throws Exception;

    int updateByPrimaryKey(SmsBlackListInfo record) throws Exception;
    
    SmsBlackListInfo querySmsBlackList(String mobile, Integer groupId) throws Exception;
    
    Integer queryCountByEntity(SmsBlackListQuery query) throws Exception;

	List<SmsBlackListInfo> queryListByEntity(SmsBlackListQuery query) throws Exception;
	
	int deleteByMobile(String mobile) throws Exception;

    String checkPhoneInWhiteList(String mobiles) throws Exception;

    int addSmsBlackList(Integer groupId, String businessname, String mobile, String remark) throws Exception;

    int deleteMobileFromGroup(Integer groupId, String mobiles) throws Exception;
}
