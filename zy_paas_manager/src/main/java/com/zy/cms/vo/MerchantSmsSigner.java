package com.zy.cms.vo;

import java.util.Date;

public class MerchantSmsSigner {
	
    private String id;//主键随机生成16位的大小写字母的混合,且保持唯一性

    private String content;//短信签名内容

    private String apiAccount;//API_ACCOUNT
    
    private String category;//签名分类:(8:通知;9:验证码;11:营销)

    private Integer status;//审核状态(1:审核通过,2:待审核,3:审核中,4:审核失败,5:取消审核)

    private String reason;//状态变化的原因,如审核失败的原因

    private Integer isLocked;//是否锁定:0表示锁定;1正常
    
    private String authUser;//审核人
    
    private Date authSubmitTime;//审核提交时间
    
    private Date authResultTime;//审核结束时间

    private Date updateTime;//更新时间

    private Date createTime;//创建时间

    private Short isNotice;//是否通知:0不通知;1通知

    private String noticePhone;//通知的手机号
    
    private MerchantAccount merchantAccount;//账号

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getApiAccount() {
		return apiAccount;
	}

	public void setApiAccount(String apiAccount) {
		this.apiAccount = apiAccount;
	}

	public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? "" : reason.trim();
    }

    public Integer getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(Integer isLocked) {
        this.isLocked = isLocked;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Short getIsNotice() {
        return isNotice;
    }

    public void setIsNotice(Short isNotice) {
        this.isNotice = isNotice;
    }

    public String getNoticePhone() {
        return noticePhone;
    }

    public void setNoticePhone(String noticePhone) {
        this.noticePhone = noticePhone == null ? null : noticePhone.trim();
    }

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getAuthUser() {
		return authUser;
	}

	public void setAuthUser(String authUser) {
		this.authUser = authUser;
	}

	public Date getAuthSubmitTime() {
		return authSubmitTime;
	}

	public void setAuthSubmitTime(Date authSubmitTime) {
		this.authSubmitTime = authSubmitTime;
	}

	public Date getAuthResultTime() {
		return authResultTime;
	}

	public void setAuthResultTime(Date authResultTime) {
		this.authResultTime = authResultTime;
	}
	
	public MerchantAccount getMerchantAccount() {
		return merchantAccount;
	}

	public void setMerchantAccount(MerchantAccount merchantAccount) {
		this.merchantAccount = merchantAccount;
	}

	@Override
	public String toString() {
		return "MerchantSmsSigner [id=" + id + ", content=" + content
				+ ", apiAccount=" + apiAccount + ", status=" + status
				+ ", reason=" + reason + ", isLocked=" + isLocked
				+ ", updateTime=" + updateTime + ", createTime=" + createTime
				+ ", isNotice=" + isNotice + ", noticePhone=" + noticePhone
				+ "]";
	}
}