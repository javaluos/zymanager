package com.zy.cms.elastic;


import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.zy.cms.mapper.master.VoiceMerchantAccountMapper;
import com.zy.cms.vo.MerchantAccount;
import com.zy.cms.vo.query.AccountQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 公共服务
 *
 * @author hmj
 */
@Component
public class GlobalConfig {
    private ConcurrentMap<String, String> map = new ConcurrentHashMap<String, String>();
    private ConcurrentMap<String, MerchantAccount> merchantAccountMap = new ConcurrentHashMap<String, MerchantAccount>();
    private List<Map> list = new ArrayList<Map>();
    @Resource
    private Properties elasticsearch;
    @Autowired
    private VoiceMerchantAccountMapper voiceMerchantAccountMapper;

    @SuppressWarnings("unused")
    @PostConstruct
    public void init() {
        Enumeration enu = elasticsearch.propertyNames();
        while (enu.hasMoreElements()) {
            String key = (String) enu.nextElement();
            map.put(key, elasticsearch.get(key) + "");
        }
        List<MerchantAccount> accountList = voiceMerchantAccountMapper.getAllMerchantAccount();
        for(MerchantAccount merchantAccount : accountList){
            merchantAccountMap.put(merchantAccount.getApiAccount(), merchantAccount);
        }
    }

    public void reload() {
        init();
    }

    public String getConfigV(String key) {
        return map.get(key);
    }

    public List<Map> getSmsChannels() {
        return list;
    }

    public MerchantAccount getByApiAccount(String apiAccount) {
        MerchantAccount merchantAccount = merchantAccountMap.get(apiAccount);
        return merchantAccount;
    }
}