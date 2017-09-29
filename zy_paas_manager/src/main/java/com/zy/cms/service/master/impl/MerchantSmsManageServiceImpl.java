package com.zy.cms.service.master.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zy.cms.common.RedisConstant;
import com.zy.cms.common.RedisOperator;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.mapper.master.MerchantSmsSignerMapper;
import com.zy.cms.service.master.MerchantSmsManageService;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.vo.MerchantSmsSigner;
import com.zy.cms.vo.MerchantSmsSignerVo;
import com.zy.cms.vo.VoiceUpload;
import com.zy.cms.vo.query.MerchantSmsSignerQuery;
import com.zy.cms.vo.query.VoiceUploadQuery;

@Service("merchantSmsManageService")
public class MerchantSmsManageServiceImpl implements MerchantSmsManageService {

	private static final ZyLogger logger = ZyLogger.getLogger(MerchantSmsManageServiceImpl.class);
	@Resource
	private MerchantSmsSignerMapper merchantSmsSignerMapper;

	@Resource
	private RedisOperator redis;

	@Override
	public Integer saveMerchantSmsSigner(MerchantSmsSigner param) throws Exception {

		return 0;
	}

	/**
	 * 通过签名Id和账号锁定签名
	 * 
	 * @param signerId
	 *            签名ID
	 * @param merchantAccount
	 *            账号
	 * @throws BusinessException
	 */
	@Override
	@Transactional
	public void lockMerchantSmsSigner(MerchantSmsSignerVo merchantSmsSigner) {

	}

	@Override
	@Transactional
	public void update(MerchantSmsSignerVo merchantSmsSigner) {

		// 预留短信通知接口
		int count = merchantSmsSignerMapper.updateByPrimaryKey(merchantSmsSigner);
		String redisKey= String.format(RedisConstant.ZHIYU_PAAS_SMS_SIGNER_KEY, merchantSmsSigner.getApiAccount());
		if (count > 0 && merchantSmsSigner.getStatus() == 1) {
			redis.hset(redisKey, merchantSmsSigner.getId(),
					JsonUtil.objectToJson(merchantSmsSigner));
		} 
		
		if (count > 0 && merchantSmsSigner.getStatus() != 1) {
			redis.hdel(redisKey, merchantSmsSigner.getId());//删除redis
		} 		 
	}

	@Override
	@Transactional(readOnly = true)
	public List<MerchantSmsSigner> getSmsSignerByKey(Map params) {
		logger.info("【查询短信签名】params={0}", new Object[] { params }, null);
		List<MerchantSmsSigner> list = merchantSmsSignerMapper.getMerchantSmsSigners(params);
		return list;
	}

	@Override
	public Integer getCount(Map params) {
		logger.info("【查询短信签名】params={0},account={1}", new Object[] { params }, null);
		return this.merchantSmsSignerMapper.getCounts(params);
	}

	@Override
	public MerchantSmsSigner getMerchantSmsSigner(Map param) {
		return merchantSmsSignerMapper.getMerchantSmsSigner(param);
	}

	@Override
	public List<MerchantSmsSigner> getMerchantSmsSigners(MerchantSmsSigner merchantSmsSigner) throws Exception {
		return null;
	}

	/**
	 * 根据实体对象查询列表
	 * 
	 * @param voiceUpload
	 *            实体对象
	 * @return
	 * @throws Exception
	 */
	public List<MerchantSmsSigner> queryListByEntity(MerchantSmsSignerQuery merchantSmsSignerQuery) throws Exception {
		return merchantSmsSignerMapper.queryListByEntity(merchantSmsSignerQuery);
	}

	/**
	 * 根据实体对象查询符合条件的记录数
	 * 
	 * @param voiceUpload
	 *            实体对象
	 * @return
	 * @throws Exception
	 */
	public int queryCountByEntity(MerchantSmsSignerQuery merchantSmsSignerQuery) throws Exception {
		return merchantSmsSignerMapper.queryCountByEntity(merchantSmsSignerQuery);
	}

	/**
	 * 根据主键ID获取对象信息
	 * 
	 * @param voiceUpload
	 *            实体对象
	 * @return
	 * @throws Exception
	 */
	public MerchantSmsSigner findMerchantSmsSigner(MerchantSmsSignerQuery merchantSmsSignerQuery) throws Exception {
		return merchantSmsSignerMapper.findMerchantSmsSigner(merchantSmsSignerQuery);
	}

	/**
	 * 根据主键ID获取对象信息
	 * 
	 * @param voiceUpload
	 *            实体对象
	 * @return
	 * @throws Exception
	 */
	public MerchantSmsSignerVo findMerchantSmsSignerVo(MerchantSmsSignerQuery merchantSmsSignerQuery) throws Exception {
		return merchantSmsSignerMapper.findMerchantSmsSignerVo(merchantSmsSignerQuery);
	}
}
