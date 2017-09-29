package com.zy.cms.service.master.impl;

import com.zy.cms.common.RedisConstant;
import com.zy.cms.common.RedisOperator;
import com.zy.cms.common.SMSCache;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.mapper.master.SmsBlackListInfoMapper;
import com.zy.cms.mapper.master.SmsWhiteListInfoMapper;
import com.zy.cms.service.master.SmsWhiteListService;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.vo.MobileOperator;
import com.zy.cms.vo.SmsBlackListInfo;
import com.zy.cms.vo.SmsWhiteListInfo;
import com.zy.cms.vo.query.SmsBlackListQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 短信白名单列表
 *
 * @author JasonXu
 */
@Service("smsWhiteListService")
public class SmsWhieteListServiceImpl implements SmsWhiteListService {

    private static final ZyLogger logger = ZyLogger.getLogger(SmsWhieteListServiceImpl.class);

    public static final String ALL_MOBILE = "^[1](([3]|[4]|[5]|[7]|[8])[0-9]{1})[0-9]{8}$";

    private static final Pattern ALL_MOBILE_PATTERN = Pattern.compile(ALL_MOBILE);

    @Autowired
    private SmsWhiteListInfoMapper smsWhiteListInfoMapper;

    @Autowired
    private RedisOperator redisOperator;

    @Autowired
    private SMSCache sMSCache;

    @Autowired
    private SmsBlackListInfoMapper smsBlackListInfoMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) throws Exception {
        return smsWhiteListInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SmsWhiteListInfo record) throws Exception {
        Long count = redisOperator.hset(RedisConstant.ZHIYU_PAAS_SMS_WHITE_LIST, record.getMobile(), JsonUtil.objectToJson(record));
        return smsWhiteListInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(SmsWhiteListInfo record) throws Exception {
        Long count = redisOperator.hset(RedisConstant.ZHIYU_PAAS_SMS_WHITE_LIST, record.getMobile(), JsonUtil.objectToJson(record));
        return smsWhiteListInfoMapper.insertSelective(record);
    }

    @Override
    public SmsWhiteListInfo selectByPrimaryKey(Integer id) throws Exception {
        return smsWhiteListInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SmsWhiteListInfo record) throws Exception {
        return smsWhiteListInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKey(SmsWhiteListInfo record) throws Exception {
        return smsWhiteListInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public Integer queryCountByEntity(SmsBlackListQuery query) throws Exception {
        return smsWhiteListInfoMapper.queryCountByEntity(query);
    }

    @Override
    public List<SmsWhiteListInfo> queryListByEntity(SmsBlackListQuery query) throws Exception {
        return smsWhiteListInfoMapper.queryListByEntity(query);
    }

    @Override
    public SmsWhiteListInfo querySmsWhiteList(String mobile) throws Exception {
        return smsWhiteListInfoMapper.querySmsWhiteList(mobile);
    }

    @Override
    public int deleteByMobile(String mobile) throws Exception {
        redisOperator.hdel(RedisConstant.ZHIYU_PAAS_SMS_WHITE_LIST, mobile);
        return smsWhiteListInfoMapper.deleteByMobile(mobile);
    }

    @Override
    public int addSmsWhiteListInfo(String businessname, String mobile, String remark) throws Exception {
        int result = 0;
        if (StringUtils.isNotEmpty(mobile)) {
            String mobiles[] = mobile.split(",");
            Date now = new Date();
            if (mobiles.length > 0) {
                for (String mo : mobiles) {
                    if (StringUtils.isNotEmpty(mo)) {
                        boolean flag = validZH_CNMobile(mo);//校验是否是手机号码
                        if (flag) {
                            SmsWhiteListInfo sbli = querySmsWhiteList(mo);
                            int success = 0;
                            if (sbli != null) {
                                sbli.setBusinessName(businessname);
                                sbli.setRemark(remark);
                                sbli.setUpdateTime(now);
                                success = updateByPrimaryKeySelective(sbli);
                            } else {
                                SmsWhiteListInfo smsWhiteListInfo = new SmsWhiteListInfo();
                                smsWhiteListInfo.setBusinessName(businessname);
                                smsWhiteListInfo.setMobile(mo);
                                smsWhiteListInfo.setRemark(remark);
                                smsWhiteListInfo.setCreateTime(now);
                                smsWhiteListInfo.setUpdateTime(now);
                                success = insert(smsWhiteListInfo);
                            }
                            result = result + success;
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * 校验添加的白名单号码是否在黑名单列表中
     * @param mobiles
     * @return
     */
    @Override
    public String checkPhoneInBlackList(String mobiles) throws Exception {
        String phones = "";
        StringBuffer sb = new StringBuffer();
        if(StringUtils.isNotBlank(mobiles)){
            String mobileArr[] = mobiles.split(",");
            if (mobileArr.length > 0) {
                for (String mobile : mobileArr) {
                    SmsBlackListInfo smsBlackListInfo = smsBlackListInfoMapper.querySmsBlackList(mobile, null);
                    if(null != smsBlackListInfo){
                        sb.append(mobile + ",");
                    }
                }
            }

        }
        if(sb.length() > 0){
            phones = sb.toString().substring(0, sb.length() - 1);
        }
        return phones;
    }

    /**
     * 号码格式校验+是否是白名单
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
        MobileOperator mobileOperator = sMSCache.getMobileOperator(phoneNumberPrefix);
        if (mobileOperator == null) {
            phoneNumberPrefix = mobile.substring(0, 4);
            mobileOperator = sMSCache.getMobileOperator(phoneNumberPrefix);
        }
        if (mobileOperator == null) {
            logger.error("接收短信的手机用户，找不到相应的运营，手机号为[" + mobile + "]当前手机号段没有录入数据库!");
            return false;
        }
        return true;
    }

}
