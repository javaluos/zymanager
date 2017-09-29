package com.zy.cms.service.master;

/**
 * Created by luos on 2017/5/5.
 */
public interface OperateLogService {

    boolean addOperateLog(String apiAccount, int type, String userName, String channelId, String channelName);
}
