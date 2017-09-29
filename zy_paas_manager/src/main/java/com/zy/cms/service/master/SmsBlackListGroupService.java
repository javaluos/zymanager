package com.zy.cms.service.master;

import com.zy.cms.vo.SmsBlackListGroup;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 黑名单组Service
 * Created by luos on 2017/9/7.
 */
public interface SmsBlackListGroupService {
    Integer queryCountByQuery(Map<String, Object> paramMap) throws SQLException;

    List<SmsBlackListGroup> queryListByQuery(Map<String, Object> paramMap) throws SQLException;

    int saveSmsBlackGroup(String params);

    SmsBlackListGroup getById(Integer id);

    int editSmsBlackGroup(Integer id, String params);

    int deleteSmsBlackGroup(Integer id) throws SQLException;
}
