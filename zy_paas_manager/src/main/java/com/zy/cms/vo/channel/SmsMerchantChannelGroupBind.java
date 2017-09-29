package com.zy.cms.vo.channel;

public class SmsMerchantChannelGroupBind {

	// 账户和通道组绑定信息
	private Integer id;
	private String apiAccount;
	private String groupYd;
	private String groupLt;
	private String groupDx;
	private String createTime;
	private String updateTime;
	// 账户信息
	private String merchantAccount;
	private String merchantPhone;
	private String businessName;

	public SmsMerchantChannelGroupBind() {
	}

	public SmsMerchantChannelGroupBind(String apiAccount, String groupYd, String groupLt, String groupDx) {
		this.apiAccount = apiAccount;
		this.groupYd = groupYd;
		this.groupLt = groupLt;
		this.groupDx = groupDx;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getApiAccount() {
		return apiAccount;
	}

	public void setApiAccount(String apiAccount) {
		this.apiAccount = apiAccount;
	}

	public String getGroupYd() {
		return groupYd;
	}

	public void setGroupYd(String groupYd) {
		this.groupYd = groupYd;
	}

	public String getGroupLt() {
		return groupLt;
	}

	public void setGroupLt(String groupLt) {
		this.groupLt = groupLt;
	}

	public String getGroupDx() {
		return groupDx; 
	}

	public void setGroupDx(String groupDx) {
		this.groupDx = groupDx;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getMerchantAccount() {
		return merchantAccount;
	}

	public void setMerchantAccount(String merchantAccount) {
		this.merchantAccount = merchantAccount;
	}

	public String getMerchantPhone() {
		return merchantPhone;
	}

	public void setMerchantPhone(String merchantPhone) {
		this.merchantPhone = merchantPhone;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	@Override
	public String toString() {
		return "SmsMerchantChannelGroupBind [id=" + id + ", apiAccount=" + apiAccount + ", groupYd=" + groupYd
				+ ", groupLt=" + groupLt + ", groupDx=" + groupDx + ", createTime=" + createTime + ", updateTime="
				+ updateTime + ", merchantAccount=" + merchantAccount + ", merchantPhone=" + merchantPhone
				+ ", businessName=" + businessName + "]";
	}

}
