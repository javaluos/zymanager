package com.zy.cms.mapper.master;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.zy.cms.vo.MerchantAccount;
import com.zy.cms.vo.query.AccountBalanceQuery;
import com.zy.cms.vo.query.AccountQuery;

public interface VoiceMerchantAccountMapper {

	/**
	 * 查找单个账户
	 * 
	 * @param record
	 * @return
	 */
	public MerchantAccount getMerchantAccount(String apiAccount);

	/**
	 * 获取总数<分页>
	 * 
	 * @param params
	 *            可为 merchantPhone merchantEmail startTime endTime pageNum
	 *            pageSize
	 * @return
	 */
	public List<MerchantAccount> getMerchantAccounts(AccountQuery query);

	/**
	 * 获取总数
	 * 
	 * @param params
	 *            可为 merchantPhone merchantEmail startTime endTime pageNum
	 *            pageSize
	 * @return
	 */
	public int getMerchantAccountCount(AccountQuery query);

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

	/**
	 * 通过手机号码查找账户信息
	 * 
	 * @param merchantPhone
	 * @return
	 */
	public MerchantAccount getMerchantAccountByPhone(String merchantPhone);

	int updateByPrimaryKeySelective(MerchantAccount record);

	public MerchantAccount selectByApiAccount(String apiAccount);

	List<MerchantAccount> getMerchantAccountByCondition(@Param("businessName") String businessName,
			@Param("merchantPhone") String merchantPhone) throws SQLException;

	List<MerchantAccount> getAllMerchantAccount();

	public List<MerchantAccount> queryMerchantAccountListByApis(String[] apis);

	/**
	 * 通过名称模糊匹配
	 * 
	 * @param businessName
	 * @return
	 */
	public List<MerchantAccount> queryAccountlistLikeName(String businessName);
	
	/**
	 * 通过名称和账号模糊匹配
	 * 
	 * @param name
	 * @return
	 */
	public List<String> querylistByNameOrPhone(@Param("name") String name,@Param("phone") String phone);
	
	public MerchantAccount getInfoByMerchantAccount(String merchantAccount);
	
	public MerchantAccount getInfoByMerchantPhone(String merchantPhone);
	
	/**
	 * 添加账户
	 * 
	 * @param record
	 * @return
	 */
	int insert(Map record);

	public MerchantAccount getInfoByMerchantEmail(String email);
}