package com.zy.cms.mapper.master;

import com.zy.cms.vo.SmsStatusErrorCode;

import java.util.List;

public interface SmsStatusErrorCodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SmsStatusErrorCode record);

    int insertSelective(SmsStatusErrorCode record);

    SmsStatusErrorCode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SmsStatusErrorCode record);

    int updateByPrimaryKey(SmsStatusErrorCode record);

    List<SmsStatusErrorCode> selectAll();
}