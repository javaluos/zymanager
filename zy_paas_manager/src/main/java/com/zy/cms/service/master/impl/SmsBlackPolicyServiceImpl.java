package com.zy.cms.service.master.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zy.cms.common.RedisOperator;
import com.zy.cms.mapper.master.BlackKeyPolicyMapper;
import com.zy.cms.service.master.SmsBlackKeyPolicyService;
import com.zy.cms.vo.BlackKeyListInfo;
import com.zy.cms.vo.BlackKeyPolicy;

/**
 * 
 * @author JasonXu
 *
 */
@Service("smsBlackKeyPolicyService")
public class SmsBlackPolicyServiceImpl implements SmsBlackKeyPolicyService {
	
	@Autowired
	private BlackKeyPolicyMapper blackKeyPolicyMapper;

	@Autowired
	private RedisOperator redisOperator;

	@Override
	public int deleteByPrimaryKey(Integer id) throws Exception {
		return blackKeyPolicyMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(BlackKeyPolicy record) throws Exception {
		return blackKeyPolicyMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKey(BlackKeyPolicy record) throws Exception {
		return blackKeyPolicyMapper.updateByPrimaryKey(record);
	}

	@Override
	public BlackKeyPolicy getInfoByPolicyName(String policyName) throws Exception {
		return blackKeyPolicyMapper.getInfoByPolicyName(policyName);
	}

	@Override
	public List<BlackKeyPolicy> selectListByQuery(Map params) throws Exception {
		return blackKeyPolicyMapper.selectListByQuery(params);
	}

	@Override
	public int queryCountByQuery(Map params) throws Exception {
		return blackKeyPolicyMapper.queryCountByQuery(params);
	}
	
	@Override
	public List<BlackKeyPolicy> selectListAll() throws Exception {
		return blackKeyPolicyMapper.selectListAll();
	}

	@Override
	public BlackKeyPolicy getInfoById(Integer id) throws Exception {
		return blackKeyPolicyMapper.getInfoById(id);
	}

}
