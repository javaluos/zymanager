package com.zy.cms.service.master.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zy.cms.common.RedisConstant;
import com.zy.cms.mapper.master.SmsBlackGroupBindMapper;
import com.zy.cms.mapper.master.SmsBlackListGroupMapper;
import com.zy.cms.service.master.SmsBlackListGroupService;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.SmsBlackGroupBind;
import com.zy.cms.vo.SmsBlackListGroup;
import com.zy.cms.vo.channel.SmsChannelPolicy;
import com.zy.cms.vo.channel.SmsChannelPolicyRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by luos on 2017/9/7.
 */
@Service("smsBlackListGroupService")
public class SmsBlackListGroupServiceImpl implements SmsBlackListGroupService {

    @Autowired
    private SmsBlackListGroupMapper mapper;

    @Autowired
    private SmsBlackGroupBindMapper smsBlackGroupBindMapper;

    @Override
    public Integer queryCountByQuery(Map<String, Object> paramMap) throws SQLException {
        return mapper.selectCountByQuery(paramMap);
    }

    @Override
    public List<SmsBlackListGroup> queryListByQuery(Map<String, Object> paramMap) throws SQLException {
        return mapper.selectListByQuery(paramMap);
    }

    @Override
    public int saveSmsBlackGroup(String params) {
        int result = 0;
        try {
            JSONObject jsonObject = JSONObject.parseObject(params);
            String groupName = "";
            String remark = "";
            SmsChannelPolicyRule scpr = new SmsChannelPolicyRule();
            if(null != jsonObject){
                groupName = jsonObject.getString("groupName");
                remark = jsonObject.getString("remark");
            }
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("groupName", groupName);
            List<SmsBlackListGroup> smsBlackListGroupList = mapper.selectListByQuery(paramMap);
            if (smsBlackListGroupList.size() > 0) {
                return -1;
            }
            SmsBlackListGroup smsBlackListGroup = new SmsBlackListGroup();
            smsBlackListGroup.setGroupName(groupName);
            smsBlackListGroup.setRemark(remark);
            Date now = new Date();
            smsBlackListGroup.setCreateTime(now);
            smsBlackListGroup.setUpdateTime(now);
            result = mapper.insert(smsBlackListGroup);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public SmsBlackListGroup getById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public int editSmsBlackGroup(Integer id, String params) {
        int result = 0;
        try {
            JSONObject jsonObject = JSONObject.parseObject(params);
            String oriGroupName = "";
            String groupName = "";
            String remark = "";
            SmsChannelPolicyRule scpr = new SmsChannelPolicyRule();
            if(null != jsonObject){
                oriGroupName = jsonObject.getString("oriGroupName");
                groupName = jsonObject.getString("groupName");
                remark = jsonObject.getString("remark");
            }
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("groupName", groupName);
            if(!oriGroupName.equals(groupName)){
                List<SmsBlackListGroup> smsBlackListGroupList = mapper.selectListByQuery(paramMap);
                if (smsBlackListGroupList.size() > 0) {
                    return -1;
                }
            }
            SmsBlackListGroup smsBlackListGroup = mapper.selectByPrimaryKey(id);
            smsBlackListGroup.setGroupName(groupName);
            smsBlackListGroup.setRemark(remark);
            Date now = new Date();
            smsBlackListGroup.setUpdateTime(now);
            result = mapper.updateByPrimaryKeySelective(smsBlackListGroup);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int deleteSmsBlackGroup(Integer id) throws SQLException {
        int result = 0;
        //是否有绑定
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("groupId", id);
        List<SmsBlackGroupBind> bindList = smsBlackGroupBindMapper.selectListByQuery(paramMap);
        if(null != bindList && bindList.size() > 0){
            return -1;
        }
        return mapper.deleteByPrimaryKey(id);
    }
}
