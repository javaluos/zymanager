package com.zy.cms.service.master;

import java.util.List;

import com.zy.cms.vo.CdrPushAddr;

public interface CdrPushAddrService {

	/**
	 * 查询列表
	 * 
	 * @return @
	 */
	public List<CdrPushAddr> queryCdrPushAddrList(CdrPushAddr cdrPushAddr);

	/**
	 * 新增
	 * 
	 * @param cdrPushAddr
	 *            实体对象 @
	 */
	public void insertCdrPushAddr(CdrPushAddr cdrPushAddr);

	/**
	 * 新增(批量)
	 * 
	 * @param list
	 *            实体对象列表 @
	 */
	public void insertCdrPushAddrBatch(List<CdrPushAddr> list);

	/**
	 * 删除
	 * 
	 * @param cdrPushAddr
	 *            实体对象 @
	 */
	public void deleteCdrPushAddr(CdrPushAddr cdrPushAddr);
}
