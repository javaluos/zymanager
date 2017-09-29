package com.zy.cms.vo.manager;

import java.util.Date;

/**
 * 月结账单费用
 * 
 * @author xuyipeng
 *
 */
public class CdrFeeMonthSum {

	private String businessName;// 客户名称

	private String merchantPhone;// 客户账号

	private Integer id;

	private String dateTime;// 统计时间(yyyy-MM)

	private String apiAccount;// 平台账户APIACOUNT

	private Integer sumFeeBs1;// 回拨费用

	private Integer sumFeeBs2;// 号码卫士费用

	private Integer sumFeeBs3;// SDK拨打费用

	private Integer sumFeeBs4;// 语音通知费用

	private Integer sumFeeBs5;// 语音验证码费用

	private Integer sumFeeBs6;// 呼叫中心费用

	private Integer sumFeeBs7;// 多方通话费用

	private Integer sumFeeBs8;

	private Integer sumFeeBs9;

	private Integer sumFeeBs10;

	private Integer sumFeeBs11;

	private Integer feeRate1;// 回拨单价

	private Integer feeRate2;// 号码卫士单价

	private Integer feeRate3;// SDK拨打单价

	private Integer feeRate4;// 语音通知单价

	private Integer feeRate5;// 语音验证码单价

	private Integer feeRate6;// 呼叫中心单价

	private Integer feeRate7;// 多方通话单价

	private Integer feeRate8;

	private Integer feeRate9;

	private Integer feeRate10;

	private Integer feeRate11;

	private Double sumFeeBsd1;// 回拨费用

	private Double sumFeeBsd2;// 号码卫士费用

	private Double sumFeeBsd3;// SDK拨打费用

	private Double sumFeeBsd4;// 语音通知费用

	private Double sumFeeBsd5;// 语音验证码费用

	private Double sumFeeBsd6;// 呼叫中心费用

	private Double sumFeeBsd7;// 多方通话费用

	private Double sumFeeBsd8;

	private Double sumFeeBsd9;

	private Double sumFeeBsd10;

	private Double sumFeeBsd11;

	private Double feeRated1;// 回拨单价

	private Double feeRated2;// 号码卫士单价

	private Double feeRated3;// SDK拨打单价

	private Double feeRated4;// 语音通知单价

	private Double feeRated5;// 语音验证码单价

	private Double feeRated6;// 呼叫中心单价

	private Double feeRated7;// 多方通话单价

	private Double feeRated8;

	private Double feeRated9;

	private Double feeRated10;

	private Double feeRated11;

	private Integer feeCount1;// 回拨计费条数

	private Integer feeCount2;// 号码卫士计费条数

	private Integer feeCount3;// SDK拨打计费条数

	private Integer feeCount4;// 语音通知计费条数

	private Integer feeCount5;// 语音验证码计费条数

	private Integer feeCount6;// 呼叫中心计费条数

	private Integer feeCount7;// 多方通话计费条数

	private Integer feeCount8;

	private Integer feeCount9;

	private Integer feeCount10;

	private Integer feeCount11;

	private Integer feeTime1;// 回拨计费时长

	private Integer feeTime2;// 号码卫士计费时长

	private Integer feeTime3;// SDK拨打计费时长

	private Integer feeTime4;// 语音通知计费时长

	private Integer feeTime5;// 语音验证码计费时长

	private Integer feeTime6;// 呼叫中心计费时长

	private Integer feeTime7;// 多方通话计费时长

	private Integer feeTime8;

	private Integer feeTime9;

	private Integer feeTime10;

	private Integer feeTime11;

	private String tablesuffix;

	private Integer sumFee;// 业务总费用(每月)

	private Double sumFeed;// 业务总费用(每月)

	private Date createTime;// 创建时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime == null ? null : dateTime.trim();
	}

	public String getApiAccount() {
		return apiAccount;
	}

	public void setApiAccount(String apiAccount) {
		this.apiAccount = apiAccount == null ? null : apiAccount.trim();
	}

	public Integer getSumFeeBs1() {
		return sumFeeBs1;
	}

	public void setSumFeeBs1(Integer sumFeeBs1) {
		this.sumFeeBs1 = sumFeeBs1;
	}

	public Integer getSumFeeBs2() {
		return sumFeeBs2;
	}

	public void setSumFeeBs2(Integer sumFeeBs2) {
		this.sumFeeBs2 = sumFeeBs2;
	}

	public Integer getSumFeeBs3() {
		return sumFeeBs3;
	}

	public void setSumFeeBs3(Integer sumFeeBs3) {
		this.sumFeeBs3 = sumFeeBs3;
	}

	public Integer getSumFeeBs4() {
		return sumFeeBs4;
	}

	public void setSumFeeBs4(Integer sumFeeBs4) {
		this.sumFeeBs4 = sumFeeBs4;
	}

	public Integer getSumFeeBs5() {
		return sumFeeBs5;
	}

	public void setSumFeeBs5(Integer sumFeeBs5) {
		this.sumFeeBs5 = sumFeeBs5;
	}

	public Integer getSumFeeBs6() {
		return sumFeeBs6;
	}

	public void setSumFeeBs6(Integer sumFeeBs6) {
		this.sumFeeBs6 = sumFeeBs6;
	}

	public Integer getSumFeeBs7() {
		return sumFeeBs7;
	}

	public void setSumFeeBs7(Integer sumFeeBs7) {
		this.sumFeeBs7 = sumFeeBs7;
	}

	public Integer getSumFeeBs8() {
		return sumFeeBs8;
	}

	public void setSumFeeBs8(Integer sumFeeBs8) {
		this.sumFeeBs8 = sumFeeBs8;
	}

	public Integer getSumFeeBs9() {
		return sumFeeBs9;
	}

	public void setSumFeeBs9(Integer sumFeeBs9) {
		this.sumFeeBs9 = sumFeeBs9;
	}

	public Integer getSumFeeBs10() {
		return sumFeeBs10;
	}

	public void setSumFeeBs10(Integer sumFeeBs10) {
		this.sumFeeBs10 = sumFeeBs10;
	}

	public Integer getFeeRate1() {
		if (feeRate1 == null) {
			feeRate1 = 0;
		}
		return feeRate1;
	}

	public void setFeeRate1(Integer feeRate1) {
		this.feeRate1 = feeRate1;
	}

	public Integer getFeeRate2() {
		if (feeRate2 == null) {
			feeRate2 = 0;
		}
		return feeRate2;
	}

	public void setFeeRate2(Integer feeRate2) {
		this.feeRate2 = feeRate2;
	}

	public Integer getFeeRate3() {
		if (feeRate3 == null) {
			feeRate3 = 0;
		}
		return feeRate3;
	}

	public void setFeeRate3(Integer feeRate3) {
		this.feeRate3 = feeRate3;
	}

	public Integer getFeeRate4() {
		if (feeRate4 == null) {
			feeRate4 = 0;
		}
		return feeRate4;
	}

	public void setFeeRate4(Integer feeRate4) {
		this.feeRate4 = feeRate4;
	}

	public Integer getFeeRate5() {
		if (feeRate5 == null) {
			feeRate5 = 0;
		}
		return feeRate5;
	}

	public void setFeeRate5(Integer feeRate5) {
		this.feeRate5 = feeRate5;
	}

	public Integer getFeeRate6() {
		if (feeRate6 == null) {
			feeRate6 = 0;
		}
		return feeRate6;
	}

	public void setFeeRate6(Integer feeRate6) {
		this.feeRate6 = feeRate6;
	}

	public Integer getFeeRate7() {
		if (feeRate7 == null) {
			feeRate7 = 0;
		}
		return feeRate7;
	}

	public void setFeeRate7(Integer feeRate7) {
		this.feeRate7 = feeRate7;
	}

	public Integer getFeeRate8() {
		if (feeRate8 == null) {
			feeRate8 = 0;
		}
		return feeRate8;
	}

	public void setFeeRate8(Integer feeRate8) {
		this.feeRate8 = feeRate8;
	}

	public Integer getFeeRate9() {
		if (feeRate9 == null) {
			feeRate9 = 0;
		}
		return feeRate9;
	}

	public void setFeeRate9(Integer feeRate9) {
		this.feeRate9 = feeRate9;
	}

	public Integer getFeeRate10() {
		if (feeRate10 == null) {
			feeRate10 = 0;
		}
		return feeRate10;
	}

	public void setFeeRate10(Integer feeRate10) {
		this.feeRate10 = feeRate10;
	}

	public Integer getFeeCount1() {
		return feeCount1;
	}

	public void setFeeCount1(Integer feeCount1) {
		this.feeCount1 = feeCount1;
	}

	public Integer getFeeCount2() {
		return feeCount2;
	}

	public void setFeeCount2(Integer feeCount2) {
		this.feeCount2 = feeCount2;
	}

	public Integer getFeeCount3() {
		return feeCount3;
	}

	public void setFeeCount3(Integer feeCount3) {
		this.feeCount3 = feeCount3;
	}

	public Integer getFeeCount4() {
		return feeCount4;
	}

	public void setFeeCount4(Integer feeCount4) {
		this.feeCount4 = feeCount4;
	}

	public Integer getFeeCount5() {
		return feeCount5;
	}

	public void setFeeCount5(Integer feeCount5) {
		this.feeCount5 = feeCount5;
	}

	public Integer getFeeCount6() {
		return feeCount6;
	}

	public void setFeeCount6(Integer feeCount6) {
		this.feeCount6 = feeCount6;
	}

	public Integer getFeeCount7() {
		return feeCount7;
	}

	public void setFeeCount7(Integer feeCount7) {
		this.feeCount7 = feeCount7;
	}

	public Integer getFeeCount8() {
		return feeCount8;
	}

	public void setFeeCount8(Integer feeCount8) {
		this.feeCount8 = feeCount8;
	}

	public Integer getFeeCount9() {
		return feeCount9;
	}

	public void setFeeCount9(Integer feeCount9) {
		this.feeCount9 = feeCount9;
	}

	public Integer getFeeCount10() {
		return feeCount10;
	}

	public void setFeeCount10(Integer feeCount10) {
		this.feeCount10 = feeCount10;
	}

	public Integer getFeeTime1() {
		return feeTime1;
	}

	public void setFeeTime1(Integer feeTime1) {
		this.feeTime1 = feeTime1;
	}

	public Integer getFeeTime2() {
		return feeTime2;
	}

	public void setFeeTime2(Integer feeTime2) {
		this.feeTime2 = feeTime2;
	}

	public Integer getFeeTime3() {
		return feeTime3;
	}

	public void setFeeTime3(Integer feeTime3) {
		this.feeTime3 = feeTime3;
	}

	public Integer getFeeTime4() {
		return feeTime4;
	}

	public void setFeeTime4(Integer feeTime4) {
		this.feeTime4 = feeTime4;
	}

	public Integer getFeeTime5() {
		return feeTime5;
	}

	public void setFeeTime5(Integer feeTime5) {
		this.feeTime5 = feeTime5;
	}

	public Integer getFeeTime6() {
		return feeTime6;
	}

	public void setFeeTime6(Integer feeTime6) {
		this.feeTime6 = feeTime6;
	}

	public Integer getFeeTime7() {
		return feeTime7;
	}

	public void setFeeTime7(Integer feeTime7) {
		this.feeTime7 = feeTime7;
	}

	public Integer getFeeTime8() {
		return feeTime8;
	}

	public void setFeeTime8(Integer feeTime8) {
		this.feeTime8 = feeTime8;
	}

	public Integer getFeeTime9() {
		return feeTime9;
	}

	public void setFeeTime9(Integer feeTime9) {
		this.feeTime9 = feeTime9;
	}

	public Integer getFeeTime10() {
		return feeTime10;
	}

	public void setFeeTime10(Integer feeTime10) {
		this.feeTime10 = feeTime10;
	}

	public Integer getSumFee() {
		return sumFee;
	}

	public void setSumFee(Integer sumFee) {
		this.sumFee = sumFee;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getTablesuffix() {
		return tablesuffix;
	}

	public void setTablesuffix(String tablesuffix) {
		this.tablesuffix = tablesuffix;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getMerchantPhone() {
		return merchantPhone;
	}

	public void setMerchantPhone(String merchantPhone) {
		this.merchantPhone = merchantPhone;
	}

	public Double getSumFeeBsd1() {
		return sumFeeBsd1;
	}

	public void setSumFeeBsd1(Double sumFeeBsd1) {
		this.sumFeeBsd1 = sumFeeBsd1;
	}

	public Double getSumFeeBsd2() {
		return sumFeeBsd2;
	}

	public void setSumFeeBsd2(Double sumFeeBsd2) {
		this.sumFeeBsd2 = sumFeeBsd2;
	}

	public Double getSumFeeBsd3() {
		return sumFeeBsd3;
	}

	public void setSumFeeBsd3(Double sumFeeBsd3) {
		this.sumFeeBsd3 = sumFeeBsd3;
	}

	public Double getSumFeeBsd4() {
		return sumFeeBsd4;
	}

	public void setSumFeeBsd4(Double sumFeeBsd4) {
		this.sumFeeBsd4 = sumFeeBsd4;
	}

	public Double getSumFeeBsd5() {
		return sumFeeBsd5;
	}

	public void setSumFeeBsd5(Double sumFeeBsd5) {
		this.sumFeeBsd5 = sumFeeBsd5;
	}

	public Double getSumFeeBsd6() {
		return sumFeeBsd6;
	}

	public void setSumFeeBsd6(Double sumFeeBsd6) {
		this.sumFeeBsd6 = sumFeeBsd6;
	}

	public Double getSumFeeBsd7() {
		return sumFeeBsd7;
	}

	public void setSumFeeBsd7(Double sumFeeBsd7) {
		this.sumFeeBsd7 = sumFeeBsd7;
	}

	public Double getSumFeeBsd8() {
		return sumFeeBsd8;
	}

	public void setSumFeeBsd8(Double sumFeeBsd8) {
		this.sumFeeBsd8 = sumFeeBsd8;
	}

	public Double getSumFeeBsd9() {
		return sumFeeBsd9;
	}

	public void setSumFeeBsd9(Double sumFeeBsd9) {
		this.sumFeeBsd9 = sumFeeBsd9;
	}

	public Double getSumFeeBsd10() {
		return sumFeeBsd10;
	}

	public void setSumFeeBsd10(Double sumFeeBsd10) {
		this.sumFeeBsd10 = sumFeeBsd10;
	}

	public Double getFeeRated1() {
		return feeRated1;
	}

	public void setFeeRated1(Double feeRated1) {
		this.feeRated1 = feeRated1;
	}

	public Double getFeeRated2() {
		return feeRated2;
	}

	public void setFeeRated2(Double feeRated2) {
		this.feeRated2 = feeRated2;
	}

	public Double getFeeRated3() {
		return feeRated3;
	}

	public void setFeeRated3(Double feeRated3) {
		this.feeRated3 = feeRated3;
	}

	public Double getFeeRated4() {
		return feeRated4;
	}

	public void setFeeRated4(Double feeRated4) {
		this.feeRated4 = feeRated4;
	}

	public Double getFeeRated5() {
		return feeRated5;
	}

	public void setFeeRated5(Double feeRated5) {
		this.feeRated5 = feeRated5;
	}

	public Double getFeeRated6() {
		return feeRated6;
	}

	public void setFeeRated6(Double feeRated6) {
		this.feeRated6 = feeRated6;
	}

	public Double getFeeRated7() {
		return feeRated7;
	}

	public void setFeeRated7(Double feeRated7) {
		this.feeRated7 = feeRated7;
	}

	public Double getFeeRated8() {
		return feeRated8;
	}

	public void setFeeRated8(Double feeRated8) {
		this.feeRated8 = feeRated8;
	}

	public Double getFeeRated9() {
		return feeRated9;
	}

	public void setFeeRated9(Double feeRated9) {
		this.feeRated9 = feeRated9;
	}

	public Double getFeeRated10() {
		return feeRated10;
	}

	public void setFeeRated10(Double feeRated10) {
		this.feeRated10 = feeRated10;
	}

	public Double getSumFeed() {
		return sumFeed;
	}

	public void setSumFeed(Double sumFeed) {
		this.sumFeed = sumFeed;
	}

	public Integer getSumFeeBs11() {
		return sumFeeBs11;
	}

	public void setSumFeeBs11(Integer sumFeeBs11) {
		this.sumFeeBs11 = sumFeeBs11;
	}

	public Integer getFeeRate11() {
		if (feeRate11 == null) {
			feeRate11 = 0;
		}
		return feeRate11;
	}

	public void setFeeRate11(Integer feeRate11) {
		this.feeRate11 = feeRate11;
	}

	public Double getSumFeeBsd11() {
		return sumFeeBsd11;
	}

	public void setSumFeeBsd11(Double sumFeeBsd11) {
		this.sumFeeBsd11 = sumFeeBsd11;
	}

	public Double getFeeRated11() {
		return feeRated11;
	}

	public void setFeeRated11(Double feeRated11) {
		this.feeRated11 = feeRated11;
	}

	public Integer getFeeCount11() {
		return feeCount11;
	}

	public void setFeeCount11(Integer feeCount11) {
		this.feeCount11 = feeCount11;
	}

	public Integer getFeeTime11() {
		return feeTime11;
	}

	public void setFeeTime11(Integer feeTime11) {
		this.feeTime11 = feeTime11;
	}

}