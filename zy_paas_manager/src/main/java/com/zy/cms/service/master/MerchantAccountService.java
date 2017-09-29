package com.zy.cms.service.master;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.zy.cms.vo.CmppAccount;
import com.zy.cms.vo.MerchantAccount;
import com.zy.cms.vo.MerchantAccountAuth;
import com.zy.cms.vo.VoiceMerchantAttr;
import com.zy.cms.vo.query.AccountBalanceQuery;
import com.zy.cms.vo.query.AccountQuery;

public interface MerchantAccountService {

	/**
	 * 
	 * @param apiAccount
	 * @return
	 */
	public MerchantAccount getMerchantAccount(String apiAccount);

	/**
	 * 通过手机号码查找账户信息
	 * 
	 * @param merchantPhone
	 * @return
	 */
	public MerchantAccount getMerchantAccountByPhone(String merchantPhone);

	/**
	 * 获取总数
	 * 
	 * @param params
	 *            可为 merchantPhone merchantEmail startTime endTime pageNum
	 * 
	 * @return
	 */
	public int queryMerchantAccountCount(AccountQuery query);

	/**
	 * 获取分页列表<分页>
	 * 
	 * @param params
	 *            可为 merchantPhone merchantEmail startTime endTime pageNum
	 *            pageSize
	 * @return
	 */
	public List<MerchantAccount> queryMerchantAccounts(AccountQuery query);

	/**
	 * 获取总数
	 * 
	 * @param params
	 *            可为 merchantPhone merchantEmail BALANCE
	 * @return
	 */
	public int queryMerchantAcctBalanceCount(AccountBalanceQuery query);

	/**
	 * 获取分页列表<分页>
	 * 
	 * @param params
	 *            可为 merchantPhone merchantEmail BALANCE pageNum pageSize
	 * @return
	 */
	public List<MerchantAccount> queryMerchantAcctBalances(AccountBalanceQuery query) throws SQLException;

	public boolean updateAccountInfo(MerchantAccountAuth accAuth, MerchantAccount mAccount, String flag);

	/**
	 * 通过APIAccount 查列表
	 * 
	 * @param params
	 *            apis
	 * 
	 * @return
	 */
	public Map<String, MerchantAccount> queryMerchantAccountListByApis(String[] apis);

	/**
	 * 通过名称模糊匹配
	 * 
	 * @param name
	 * @return
	 */
	public List<MerchantAccount> queryAccountlistLikeName(String name);
	
	/**
	 * 通过名称和账号模糊匹配
	 * 
	 * @param name
	 * @return
	 */
	public List<String> querylistByNameOrPhone(String name,String phone);
	
	public MerchantAccount getInfoByMerchantAccount(String merchantAccount);
	
	public MerchantAccount getInfoByMerchantPhone(String merchantPhone);
	
	public MerchantAccount getInfoByMerchantEmail(String email);
	
	/**
	 * 保存注册账号信息 要加入事务
	 * 
	 * @param merchantAccount
	 * @param merchantPhone
	 * @param merchantPwd
	 * @param merchantNm
	 *            企业名
	 * @param channelId
	 */
	public boolean saveRegistMerchantAccount(VoiceMerchantAttr voiceMerchantAttr,MerchantAccount merchantAccount,CmppAccount cmppAccount) throws Exception;

}
