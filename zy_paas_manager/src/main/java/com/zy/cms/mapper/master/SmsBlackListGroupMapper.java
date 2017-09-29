package com.zy.cms.mapper.master;

import com.zy.cms.vo.SmsBlackListGroup;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface SmsBlackListGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SmsBlackListGroup record);

    int insertSelective(SmsBlackListGroup record);

    SmsBlackListGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SmsBlackListGroup record);

    int updateByPrimaryKey(SmsBlackListGroup record);

    int selectCountByQuery(Map<String, Object> paramMap) throws SQLException;

    List<SmsBlackListGroup> selectListByQuery(Map<String, Object> paramMap) throws SQLException;
}