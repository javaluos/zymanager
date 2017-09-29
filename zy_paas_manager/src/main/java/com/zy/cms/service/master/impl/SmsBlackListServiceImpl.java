package com.zy.cms.service.master.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zy.cms.common.SMSCache;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.mapper.master.SmsBlackGroupBindMapper;
import com.zy.cms.mapper.master.SmsWhiteListInfoMapper;
import com.zy.cms.vo.MobileOperator;
import com.zy.cms.vo.SmsBlackGroupBind;
import com.zy.cms.vo.SmsWhiteListInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zy.cms.common.RedisConstant;
import com.zy.cms.common.RedisOperator;
import com.zy.cms.mapper.master.SmsBlackListInfoMapper;
import com.zy.cms.service.master.SmsBlackListService;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.vo.SmsBlackListInfo;
import com.zy.cms.vo.query.SmsBlackListQuery;
import org.springframework.transaction.annotation.Transactional;

/**
 * 短信黑名单列表
 *
 * @author JasonXu
 */
@Service("smsBlackListService")
public class SmsBlackListServiceImpl implements SmsBlackListService {

    private static final ZyLogger logger = ZyLogger.getLogger(SmsBlackListServiceImpl.class);

    @Autowired
    private SmsBlackListInfoMapper smsBlackListInfoMapper;

    @Autowired
    private SmsWhiteListInfoMapper smsWhiteListInfoMapper;

    @Autowired
    private SmsBlackGroupBindMapper smsBlackGroupBindMapper;

    @Autowired
    private SMSCache smsCache;

    @Autowired
    private RedisOperator redisOperator;

    public static final String ALL_MOBILE = "^[1](([3]|[4]|[5]|[7]|[8])[0-9]{1})[0-9]{8}$";

    private static final Pattern ALL_MOBILE_PATTERN = Pattern.compile(ALL_MOBILE);

    @Override
    public int deleteByPrimaryKey(Integer id) throws Exception {
        return smsBlackListInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SmsBlackListInfo record) throws Exception {
//        Long count = redisOperator.hset(RedisConstant.ZHIYU_PAAS_SMS_BLACK_LIST, record.getMobile(), JsonUtil.objectToJson(record));
        return smsBlackListInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(SmsBlackListInfo record) throws Exception {
        @SuppressWarnings("unused")
        Long count = redisOperator.hset(RedisConstant.ZHIYU_PAAS_SMS_BLACK_LIST, record.getMobile(), JsonUtil.objectToJson(record));
        return smsBlackListInfoMapper.insertSelective(record);
    }

    @Override
    public SmsBlackListInfo selectByPrimaryKey(Integer id) throws Exception {
        return smsBlackListInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SmsBlackListInfo record) throws Exception {
        return smsBlackListInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKey(SmsBlackListInfo record) throws Exception {
        return smsBlackListInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public Integer queryCountByEntity(SmsBlackListQuery query) throws Exception {
        return smsBlackListInfoMapper.queryCountByEntity(query);
    }

    @Override
    public List<SmsBlackListInfo> queryListByEntity(SmsBlackListQuery query) throws Exception {
        return smsBlackListInfoMapper.queryListByEntity(query);
    }

    @Override
    public SmsBlackListInfo querySmsBlackList(String mobile, Integer groupId) throws Exception {
        return smsBlackListInfoMapper.querySmsBlackList(mobile, groupId);
    }

    @Override
    public int deleteByMobile(String mobile) throws Exception {
        redisOperator.hdel(RedisConstant.ZHIYU_PAAS_SMS_BLACK_LIST, mobile);
        return smsBlackListInfoMapper.deleteByMobile(mobile);
    }

    /**
     * 校验添加的黑名单号码是否在白名单列表中
     *
     * @param mobiles
     * @return
     */
    @Override
    public String checkPhoneInWhiteList(String mobiles) throws Exception {
        String phones = "";
        StringBuffer sb = new StringBuffer();
        if (StringUtils.isNotBlank(mobiles)) {
            String mobileArr[] = mobiles.split(",");
            if (mobileArr.length > 0) {
                for (String mobile : mobileArr) {
                    SmsWhiteListInfo smsWhiteListInfo = smsWhiteListInfoMapper.querySmsWhiteList(mobile);
                    if (null != smsWhiteListInfo) {
                        sb.append(mobile + ",");
                    }
                }
            }

        }
        if (sb.length() > 0) {
            phones = sb.toString().substring(0, sb.length() - 1);
        }
        return phones;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addSmsBlackList(Integer groupId, String businessname, String mobile, String remark) throws Exception {
        int result = 0;
        if (StringUtils.isNotEmpty(mobile)) {
            String mobiles[] = mobile.split(",");
            Date now = new Date();
            if (mobiles.length > 0) {
                for (String mo : mobiles) {
                    if (StringUtils.isNotEmpty(mo)) {
                        boolean flag = validZH_CNMobile(mo.trim());//校验是否是手机号码
                        if (flag) {
                            SmsBlackListInfo sbli = querySmsBlackList(mo, groupId);
                            int success = 0;
                            if (sbli != null) {
                                sbli.setBusinessName(businessname);
                                sbli.setRemark(remark);
                                sbli.setUpdateTime(now);
                                success = updateByPrimaryKeySelective(sbli);
                            } else {
                                SmsBlackListInfo smsBlackListInfo = new SmsBlackListInfo();
                                smsBlackListInfo.setBusinessName(businessname);
                                smsBlackListInfo.setMobile(mo);
                                smsBlackListInfo.setRemark(remark);
                                smsBlackListInfo.setCreateTime(now);
                                smsBlackListInfo.setUpdateTime(now);
                                smsBlackListInfo.setGroupId(groupId);
                                success = insert(smsBlackListInfo);
                                Map<String, Object> paramMap = new HashMap<String, Object>();
                                paramMap.put("groupId", groupId);
                                List<SmsBlackGroupBind> smsBlackGroupBinds = smsBlackGroupBindMapper.selectListByQuery(paramMap);
                                for(SmsBlackGroupBind smsBlackGroupBind : smsBlackGroupBinds){
                                    String apiAcount = "";
                                    if("0".equals(String.valueOf(groupId))){
                                        apiAcount = "0"; //全局黑名单组
                                    }else{
                                        apiAcount = smsBlackGroupBind.getApiAccount();
                                    }
                                    String redisKey = String.format(RedisConstant.ZHIYU_PAAS_SMS_BLACK_GROUP_BIND, apiAcount);
                                    redisOperator.hset(redisKey, mo, JsonUtil.toJsonString(smsBlackListInfo));
                                }
                            }
                            result = result + success;
                        }
                    }
                }
            }
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteMobileFromGroup(Integer groupId, String mobiles) throws Exception {
        int result = 0;
        int count = 0;
        if (StringUtils.isNotEmpty(mobiles)) {
            String mobileArray[] = mobiles.split(",");
            if (mobileArray.length > 0) {
                for (String mobile : mobileArray) {
                    int success = smsBlackListInfoMapper.deleteByMobile(mobile);
                    if(success > 0){
                        count ++;
                    }
                    Map<String, Object> paramMap = new HashMap<String, Object>();
                    paramMap.put("groupId", groupId);
                    List<SmsBlackGroupBind> smsBlackGroupBinds = smsBlackGroupBindMapper.selectListByQuery(paramMap);
                    for(SmsBlackGroupBind smsBlackGroupBind : smsBlackGroupBinds){
                        String apiAcount = "";
                        if("0".equals(String.valueOf(groupId))){
                            apiAcount = "0"; //全局黑名单组
                        }else{
                            apiAcount = smsBlackGroupBind.getApiAccount();
                        }
                        String redisKey = String.format(RedisConstant.ZHIYU_PAAS_SMS_BLACK_GROUP_BIND, apiAcount);
                        redisOperator.hdel(redisKey, mobile);
                    }
                }
            }
        }
        return count;
    }

    /**
     * 号码格式校验+是否是黑名单
     *
     * @param mobile
     * @throws ParamsException
     */
    private boolean validZH_CNMobile(String mobile) {
        Matcher matcher = ALL_MOBILE_PATTERN.matcher(mobile);
        if (!matcher.matches()) {
            logger.error("【手机号验证】手机号[" + mobile + "]不是大陆号码：");
            return false;
        }
        String phoneNumberPrefix = mobile.substring(0, 3);
        MobileOperator mobileOperator = smsCache.getMobileOperator(phoneNumberPrefix);
        if (mobileOperator == null) {
            phoneNumberPrefix = mobile.substring(0, 4);
            mobileOperator = smsCache.getMobileOperator(phoneNumberPrefix);
        }
        if (mobileOperator == null) {
            logger.error("接收短信的手机用户，找不到相应的运营，手机号为[" + mobile + "]当前手机号段没有录入数据库!");
            return false;
        }
        return true;
    }


}
