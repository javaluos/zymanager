package com.zy.cms.mapper.master;

import com.zy.cms.vo.SmsBlackGroupBind;
import com.zy.cms.vo.SmsBlackListGroup;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface SmsBlackGroupBindMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SmsBlackGroupBind record);

    int insertSelective(SmsBlackGroupBind record);

    SmsBlackGroupBind selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SmsBlackGroupBind record);

    int updateByPrimaryKey(SmsBlackGroupBind record);

    Integer selectCountByQuery(Map<String, Object> paramMap) throws SQLException;

    List<SmsBlackGroupBind> selectListByQuery(Map<String, Object> paramMap) throws SQLException;
}