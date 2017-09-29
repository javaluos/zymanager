package com.zy.cms.vo.query;

import com.zy.cms.util.DateUtil;
import com.zy.cms.util.StringUtil;

/**
 * 图表业务查询参数
 * 
 * @author allen.yuan
 * @date 2016-11-23
 */
public class ChartQuery {

	private Integer accountType = 0;// 账号类型（0：用户及；1：全局）
	private String apiAccount; // 账号
	private String channelMainCode;
	private String channelId; //通道Id
	private String businessId;// 业务Id
	private Integer monitorType;// 监控类型
	private String monitorDate;// 监控时间
	private String tableDate; // 天表时间
	

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public String getApiAccount() {
		return apiAccount == null ? "" : apiAccount.trim();
	}

	public void setApiAccount(String apiAccount) {
		this.apiAccount = apiAccount;
	}

	public String getChannelMainCode() {
		return channelMainCode;
	}

	public void setChannelMainCode(String channelMainCode) {
		this.channelMainCode = channelMainCode;
	}

	public String getChannelId() {
		return channelId == null ? "" : channelId.trim();
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public Integer getMonitorType() {
		return monitorType;
	}

	public void setMonitorType(Integer monitorType) {
		this.monitorType = monitorType;
	}

	public String getMonitorDate() {
		return monitorDate == null ? "" : monitorDate.trim();
	}

	public void setMonitorDate(String monitorDate) {
		this.monitorDate = monitorDate;
	}

	public String getTableDate() {
		if (StringUtil.isEmpty(monitorDate)) {
			tableDate = DateUtil.getDate();
		} else if (monitorDate.length() == 10) {
			tableDate = monitorDate.replaceAll("-", "");
		} else {
			tableDate = DateUtil.getDate();
		}

		return tableDate;
	}

	public void setTableDate(String tableDate) {
		this.tableDate = tableDate;
	}
	
	
	
}
