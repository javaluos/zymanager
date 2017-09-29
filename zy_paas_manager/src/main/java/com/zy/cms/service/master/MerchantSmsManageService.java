package com.zy.cms.service.master;

import java.util.List;
import java.util.Map;
import com.zy.cms.vo.MerchantSmsSigner;
import com.zy.cms.vo.MerchantSmsSignerVo;
import com.zy.cms.vo.VoiceUpload;
import com.zy.cms.vo.query.MerchantSmsSignerQuery;


/**
 * 短信签名
 * 
 * @author jasonxu
 * @date 2015-6-03
 */
public interface MerchantSmsManageService {
	
	/**
	 * 短信签名保存
	 * 
	 * @param param
	 *            应用信息
	 * @return
	 */
	public Integer saveMerchantSmsSigner(MerchantSmsSigner param) throws Exception;

	/**
	 * 锁定短信签名
	 * 
	 * @param merchantSmsSigner
	 */
	public void lockMerchantSmsSigner(MerchantSmsSignerVo merchantSmsSigner);

	
	/**
	 * 获取所有公有模板
	 * 
	 * @return
	 *//*
	public List<MerchantSmsTemplate> getPublicSmsTemplate();

	
	*//**
	 * 获取所有模板
	 * 
	 * @return
	 *//*
	public List<MerchantSmsTemplate> getALLSmsTemplate();*/

	/**
	 * 编辑签名
	 * 
	 * @param sn
	 */
	public void update(MerchantSmsSignerVo sn);

	/**
	 * 通过账号和检索内容查询签名信息
	 * 
	 * @param content
	 *            检索内容
	 * @param account
	 *            账号
	 * @return
	 */
	public List<MerchantSmsSigner> getSmsSignerByKey(Map params);

	public Integer getCount(Map params);

	public MerchantSmsSigner getMerchantSmsSigner(Map param);

//	public MerchantSmsTemplate getTemplateById(String id);
	
	public List<MerchantSmsSigner> getMerchantSmsSigners(MerchantSmsSigner merchantSmsSigner)  throws Exception;
	
	public List<MerchantSmsSigner> queryListByEntity(MerchantSmsSignerQuery merchantSmsSignerQuery) throws Exception;
	
	public int queryCountByEntity(MerchantSmsSignerQuery merchantSmsSignerQuery) throws Exception;
	
	public MerchantSmsSigner findMerchantSmsSigner(MerchantSmsSignerQuery merchantSmsSignerQuery) throws Exception;
	
	public MerchantSmsSignerVo findMerchantSmsSignerVo(MerchantSmsSignerQuery merchantSmsSignerQuery) throws Exception;
	
}
