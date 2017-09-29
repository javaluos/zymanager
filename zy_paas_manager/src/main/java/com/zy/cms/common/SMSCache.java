package com.zy.cms.common;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.zy.cms.mapper.master.BlackKeyMapper;
import com.zy.cms.mapper.master.MobileOperatorMapper;
import com.zy.cms.vo.MobileOperator;

@Component
public class SMSCache {
	
	private static final ReadWriteLock rwl = new ReentrantReadWriteLock();
	
	public Set<String> bls = new HashSet<String>();

	private Map<String, MobileOperator> mobileOperatorMap = new ConcurrentHashMap<String, MobileOperator>();
	
	@Autowired
	private BlackKeyMapper blackKeyMapper;
	
	@Autowired
	private MobileOperatorMapper  mobileOperatorMapper;
	
	@PostConstruct
	private void init() {
		//加载黑词
		loadBlackKey();
		//手机号运营商
		loadMobileOperator();
	}

    /**
     * 黑词初始化
     */
    public void loadBlackKey() {
    	List<String> list=blackKeyMapper.getAllblackkey();      //重数据库加载黑词
    	rwl.writeLock().lock();
    	try{
    		bls.clear();
    		if (list != null && list.size() > 0) {
        		for(String data:list){
        			bls.add(data.trim());
        		}
        	}
        	BlackKeyFilter.initBlackStore(bls);
    	}finally {
			rwl.writeLock().unlock();
		}
	}
    
    /**
     * 加载手机号段所属的运营商关系到缓存中
     * 
     * @param type
     * @return
     */
    public void loadMobileOperator() {
        List<MobileOperator> list = mobileOperatorMapper.selectAll();
        if (list != null && list.size() > 0) {
            for (MobileOperator operator : list) {
                mobileOperatorMap.put(operator.getNumberPrefix(), operator);
            }
        }
    }
    
    /**
     * 根据号码的前缀，获取所属运营商
     * 
     * @param numberPrefix
     * @return
     */
    public MobileOperator getMobileOperator(String numberPrefix) {
        return mobileOperatorMap.get(numberPrefix);
    }
    
    public void reload(){
    	init();
    }
}
