package com.zy.cms.service.master.impl;

import com.alibaba.fastjson.JSONObject;
import com.zy.cms.common.RedisConstant;
import com.zy.cms.common.RedisOperator;
import com.zy.cms.mapper.master.SmsBlackGroupBindMapper;
import com.zy.cms.mapper.master.SmsBlackListGroupMapper;
import com.zy.cms.mapper.master.SmsBlackListInfoMapper;
import com.zy.cms.service.master.SmsBlackGroupBindService;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.vo.SmsBlackGroupBind;
import com.zy.cms.vo.SmsBlackListGroup;
import com.zy.cms.vo.SmsBlackListInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by luos on 2017/9/8.
 */
@Service("smsBlackGroupBindService")
public class SmsBlackGroupBindServiceImpl implements SmsBlackGroupBindService {

    @Autowired
    private SmsBlackGroupBindMapper mapper;
    @Autowired
    private SmsBlackListInfoMapper smsBlackListInfoMapper;
    @Autowired
    private RedisOperator redisOperator;

    @Override
    public Integer queryCountByQuery(Map<String, Object> paramMap) throws SQLException {
        return mapper.selectCountByQuery(paramMap);
    }

    @Override
    public List<SmsBlackGroupBind> queryListByQuery(Map<String, Object> paramMap) throws SQLException {
        return mapper.selectListByQuery(paramMap);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer saveSmsBlackGroupBind(SmsBlackGroupBind smsBlackGroupBind) throws SQLException {
        int result = 0;
        if(null != smsBlackGroupBind){
            Date now = new Date();
            Integer id = smsBlackGroupBind.getId();
            String apiAccount = smsBlackGroupBind.getApiAccount();
            if(null == id){
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("apiAccount", apiAccount);
                List<SmsBlackGroupBind> smsBlackGroupBindList = mapper.selectListByQuery(param);
                if(null != smsBlackGroupBindList && smsBlackGroupBindList.size() > 0){
                    return -1;
                }
                smsBlackGroupBind.setCreateTime(now);
                smsBlackGroupBind.setUpdateTime(now);
                result = mapper.insert(smsBlackGroupBind);
            }else{
                smsBlackGroupBind.setUpdateTime(now);
                result = mapper.updateByPrimaryKeySelective(smsBlackGroupBind);
            }
            Integer groupId = smsBlackGroupBind.getGroupId();
            if(null != groupId){
                String redisKey = String.format(RedisConstant.ZHIYU_PAAS_SMS_BLACK_GROUP_BIND, apiAccount);
                List<SmsBlackListInfo> smsBlackList = smsBlackListInfoMapper.selectByGroupId(groupId);
                if(null == smsBlackList || smsBlackList.size() == 0){
                    redisOperator.del(redisKey);
                }else{
                    for(SmsBlackListInfo smsBlackListInfo : smsBlackList){
                        redisOperator.hset(redisKey, smsBlackListInfo.getMobile(), JsonUtil.toJsonString(smsBlackListInfo));
                    }
                }
            }
        }
        return result;
    }

    @Override
    public Integer deleteBind(Integer id) throws SQLException {
        int result = 0;
        SmsBlackGroupBind smsBlackGroupBind = mapper.selectByPrimaryKey(id);
        if(null != smsBlackGroupBind){
            result = mapper.deleteByPrimaryKey(id);
            if(result > 0){
                String apiAccount = smsBlackGroupBind.getApiAccount();
                String redisKey = String.format(RedisConstant.ZHIYU_PAAS_SMS_BLACK_GROUP_BIND, apiAccount);
                redisOperator.del(redisKey);
            }
        }
        return result;
    }

    @Override
    public SmsBlackGroupBind getSmsBlackGroupBindById(Integer id) throws SQLException {
        return mapper.selectByPrimaryKey(id);
    }
}
