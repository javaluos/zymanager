package com.zy.cms.service.master.impl;

import com.zy.cms.enums.OperateLogTypeEnum;
import com.zy.cms.mapper.master.OperateLogMapper;
import com.zy.cms.mapper.master.VoiceMerchantAccountMapper;
import com.zy.cms.service.master.OperateLogService;
import com.zy.cms.vo.MerchantAccount;
import com.zy.cms.vo.OperateLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by luos on 2017/5/5.
 */
@Service("operateLogService")
public class OperateLogServiceImpl implements OperateLogService {

    @Autowired
    private OperateLogMapper mapper;

    @Autowired
    private VoiceMerchantAccountMapper voiceMerchantAccountMapper;

    @Override
    public boolean addOperateLog(String apiAccount, int type, String userName, String channelId, String channelName) {
        OperateLog operateLog = new OperateLog();
        operateLog.setApiAccount(apiAccount);
        operateLog.setOperateType(type);
        MerchantAccount merchantAccount = voiceMerchantAccountMapper.selectByApiAccount(apiAccount);
        String merchantPhone = merchantAccount.getMerchantPhone();
        String businessName = merchantAccount.getBusinessName();
        String operate = "";
        String state = "";
        if (type == OperateLogTypeEnum.ADD_CHNBIND.getType()) {
            operate = "添加";
            state = "绑定";
        } else if (type == OperateLogTypeEnum.DEL_CHNBIND.getType()) {
            operate = "删除";
            state = "已绑定的";
        }
        operateLog.setOperateDesc(operate + "客户【" + merchantPhone + " " + businessName + "】"
                + state + "通道【" + channelId + " " + channelName + "】");
        operateLog.setRemark("");
        operateLog.setCreateTime(new Date());
        operateLog.setCreateUser(userName);
        return mapper.insert(operateLog) > 0;
    }
}
