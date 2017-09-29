package com.zy.cms.vo.query;

import java.util.Date;
import java.util.List;

public class MerchantSmsSignerQuery {
	
    private String id;//主键随机生成16位的大小写字母的混合,且保持唯一性

    private String content;//短信签名内容

    private String apiAccount;//API_ACCOUNT
    
    private String category;//签名分类:0通知类;1验证码类;2营销类

    private Integer status;//审核状态(1:审核通过,2:待审核,3:审核中,4:审核失败,5:取消审核)

    private String reason;//状态变化的原因,如审核失败的原因

    private Integer isLocked;//是否锁定:0表示锁定;1正常

    private Date updateTime;//更新时间

    private Date createTime;//创建时间

    private Short isNotice;//是否通知:0不通知;1通知

    private String noticePhone;//通知的手机号
    
    private String authUser;//审核人
    
    private String authSubmitTimeStart;//审核提交时间开始
    
    private String authSubmitTimeEnd;//审核提交时间结束
    
    private String authResultTimeStart;//审核结束时间开始
    
    private String authResultTimeEnd;//审核结束时间结束
    
    private Integer pageNum;// 页号
	
	private Integer pageSize;// 每页数量
	
	private Integer pageOffset = 0;// 分页的开始值
	
	private Integer pageCount;// 统计页数
	
	private String merchantPhone;//手机号码
	
	private String merchantAccount;//客户名称
	
	private List apiAccounts;//账户绑定的客户

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
    	if(status == null){
    		status= 0;
    	}
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
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
	
	public Integer getPageNum() {
		if (pageNum == null) {
			pageNum = 0;
		}
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		if (pageSize == null) {
			pageSize = 0;
		}
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageCount() {
		if (pageCount == null) {
			pageCount = 0;
		}
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	
	public Integer getPageOffset() {
		pageOffset = getPageNum() * getPageSize();
		return pageOffset;
	}

	public void setPageOffset(Integer pageOffset) {
		this.pageOffset = pageOffset;
	}

	
	public String getAuthUser() {
		return authUser;
	}

	public void setAuthUser(String authUser) {
		this.authUser = authUser;
	}
 
	public String getAuthSubmitTimeStart() {
		return authSubmitTimeStart;
	}

	public void setAuthSubmitTimeStart(String authSubmitTimeStart) {
		this.authSubmitTimeStart = authSubmitTimeStart;
	}

	public String getAuthSubmitTimeEnd() {
		return authSubmitTimeEnd;
	}

	public void setAuthSubmitTimeEnd(String authSubmitTimeEnd) {
		this.authSubmitTimeEnd = authSubmitTimeEnd;
	}

	public String getAuthResultTimeStart() {
		return authResultTimeStart;
	}

	public void setAuthResultTimeStart(String authResultTimeStart) {
		this.authResultTimeStart = authResultTimeStart;
	}

	public String getAuthResultTimeEnd() {
		return authResultTimeEnd;
	}

	public void setAuthResultTimeEnd(String authResultTimeEnd) {
		this.authResultTimeEnd = authResultTimeEnd;
	}
	
	public String getMerchantPhone() {
		return merchantPhone;
	}

	public void setMerchantPhone(String merchantPhone) {
		this.merchantPhone = merchantPhone;
	}
	
	public String getMerchantAccount() {
		return merchantAccount;
	}

	public void setMerchantAccount(String merchantAccount) {
		this.merchantAccount = merchantAccount == null ? null : merchantAccount.trim();
	}

	public List getApiAccounts() {
		return apiAccounts;
	}

	public void setApiAccounts(List apiAccounts) {
		this.apiAccounts = apiAccounts;
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