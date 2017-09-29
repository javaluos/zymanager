package com.zy.cms.service.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zy.cms.common.ZyLogger;
import com.zy.cms.mapper.manager.CdrMonitorUserMapper;
import com.zy.cms.service.manager.CdrMonitorUserService;
import com.zy.cms.vo.manager.CdrMonitorUser;
import com.zy.cms.vo.query.CdrMonitorUserQuery;

@Service("cdrMonitorUserService")
@Transactional
public class CdrMonitorUserServiceImpl implements CdrMonitorUserService {
	
	private static final ZyLogger logger = ZyLogger.getLogger(CdrMonitorUserServiceImpl.class);
	
	@Autowired
	private CdrMonitorUserMapper cdrMonitorUserMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return cdrMonitorUserMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(CdrMonitorUser record) {
		// TODO Auto-generated method stub
		return cdrMonitorUserMapper.insert(record);
	}

	@Override
	public int insertSelective(CdrMonitorUser record) {
		// TODO Auto-generated method stub
		return cdrMonitorUserMapper.insertSelective(record);
	}

	@Override
	public CdrMonitorUser selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return cdrMonitorUserMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(CdrMonitorUser record) {
		// TODO Auto-generated method stub
		return cdrMonitorUserMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(CdrMonitorUser record) {
		// TODO Auto-generated method stub
		return cdrMonitorUserMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<CdrMonitorUser> queryMonitorUserByEntity(CdrMonitorUserQuery cdrMonitorUserQuery) throws Exception {
		// TODO Auto-generated method stub
		return cdrMonitorUserMapper.queryMonitorUserByEntity(cdrMonitorUserQuery);
	}

	@Override
	public int queryMonitorUserCountByEntity(CdrMonitorUserQuery cdrMonitorUserQuery) throws Exception {
		// TODO Auto-generated method stub
		return cdrMonitorUserMapper.queryMonitorUserCountByEntity(cdrMonitorUserQuery);
	}

	@Override
	public CdrMonitorUser findMonitorUserByAccount(String apiAccount) throws Exception {
		// TODO Auto-generated method stub
		return cdrMonitorUserMapper.findMonitorUserByAccount(apiAccount);
	}

	
}
