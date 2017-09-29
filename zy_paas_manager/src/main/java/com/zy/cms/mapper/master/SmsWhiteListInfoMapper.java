package com.zy.cms.mapper.master;

import com.zy.cms.vo.SmsWhiteListInfo;
import com.zy.cms.vo.query.SmsBlackListQuery;

import java.util.List;

public interface SmsWhiteListInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SmsWhiteListInfo record);

    int insertSelective(SmsWhiteListInfo record);

    SmsWhiteListInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SmsWhiteListInfo record);

    int updateByPrimaryKey(SmsWhiteListInfo record);

    Integer queryCountByEntity(SmsBlackListQuery query) throws Exception;

    List<SmsWhiteListInfo> queryListByEntity(SmsBlackListQuery query) throws Exception;

    SmsWhiteListInfo querySmsWhiteList(String mobile) throws Exception;

    int deleteByMobile(String mobile) throws Exception;
}