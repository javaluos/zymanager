package com.zy.cms.service.manager;

import com.zy.cms.vo.WebResult;
import com.zy.cms.vo.query.SmsSendQuery;

/**
 * Created by luos on 2017/5/22.
 */
public interface SmsSendRecordService {

    WebResult exportExcel(SmsSendQuery query, String realPath, String contextPath);
}
