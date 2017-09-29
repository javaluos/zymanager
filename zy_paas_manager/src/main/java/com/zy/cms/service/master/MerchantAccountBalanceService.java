package com.zy.cms.service.master;

import java.util.List;

import com.zy.cms.vo.BalanceUpdateRecord;
import com.zy.cms.vo.VoiceMerchantAccountBalance;
import com.zy.cms.vo.query.AccBalUpdateRecordQuery;

public interface MerchantAccountBalanceService {
	/**
	 * 
	 * @param account
	 * @param type
	 *            1 邮箱，2 手机号码
	 * @return
	 */
	public VoiceMerchantAccountBalance getMerchantAccountBalance(String apiAccount);

	public Integer queryMchAcctBalUpdateCount(AccBalUpdateRecordQuery query);

	public List<BalanceUpdateRecord> queryMchAcctBalUpdateRecords(AccBalUpdateRecordQuery query);

	/**
	 * 添加账号余额修改记录
	 * @param apiAccount
	 * @param merchantAccount
	 * @param businessName
	 * @param updateFee
	 * @param comment
	 * @return
	 */
	public boolean addAccountBalanceUpdateRecord(String apiAccount, String merchantAccount, String businessName,
			String updateFee, String operator, String comment);

}
