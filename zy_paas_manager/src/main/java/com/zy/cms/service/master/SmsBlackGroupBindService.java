package com.zy.cms.service.master;

import com.zy.cms.vo.SmsBlackGroupBind;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 黑名单组Service
 * Created by luos on 2017/9/8.
 */
public interface SmsBlackGroupBindService {

    Integer queryCountByQuery(Map<String, Object> paramMap) throws SQLException;

    List<SmsBlackGroupBind> queryListByQuery(Map<String, Object> paramMap) throws SQLException;

    Integer saveSmsBlackGroupBind(SmsBlackGroupBind smsBlackGroupBind) throws SQLException;

    Integer deleteBind(Integer id) throws SQLException;

    SmsBlackGroupBind getSmsBlackGroupBindById(Integer id) throws SQLException;
}
