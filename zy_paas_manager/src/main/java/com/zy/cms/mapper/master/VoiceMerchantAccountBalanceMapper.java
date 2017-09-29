package com.zy.cms.mapper.master;

import java.util.Date;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zy.cms.vo.VoiceMerchantAccountBalance;

public interface VoiceMerchantAccountBalanceMapper {

	int insert(VoiceMerchantAccountBalance record);

	int update(VoiceMerchantAccountBalance record);

	VoiceMerchantAccountBalance getVoiceMerchantAccountBalance(Map record);

	int updateBalance(@Param("apiAccount") String apiAccount, @Param("updateMoney") long updateMoney, @Param("updateTime") Date updateTime);
}