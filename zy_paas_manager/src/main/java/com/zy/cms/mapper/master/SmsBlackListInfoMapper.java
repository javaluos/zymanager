package com.zy.cms.mapper.master;

import java.sql.SQLException;
import java.util.List;

import com.zy.cms.vo.SmsBlackListInfo;
import com.zy.cms.vo.query.SmsBlackListQuery;
import org.apache.ibatis.annotations.Param;

/**
 * 短信黑名单
 * @author JasonXu
 *
 */
public interface SmsBlackListInfoMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(SmsBlackListInfo record);

    int insertSelective(SmsBlackListInfo record);

    SmsBlackListInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SmsBlackListInfo record);

    int updateByPrimaryKey(SmsBlackListInfo record);
    
    Integer queryCountByEntity(SmsBlackListQuery query) throws Exception;

   	List<SmsBlackListInfo> queryListByEntity(SmsBlackListQuery query) throws Exception;
   	
   	SmsBlackListInfo querySmsBlackList(@Param("mobile") String mobile, @Param("groupId") Integer groupId) throws Exception;
   	
   	int deleteByMobile(String mobile) throws Exception;

    List<SmsBlackListInfo> selectByGroupId(Integer groupId) throws SQLException;
}