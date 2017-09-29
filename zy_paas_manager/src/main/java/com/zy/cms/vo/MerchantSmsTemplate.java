package com.zy.cms.vo;

import java.util.Date;

public class MerchantSmsTemplate {
    private String id;

    private String apiAccount;

    private String name;

    private String content;

    private String category; //模板分类:(8:通知;9:验证码;11:营销)

    private Integer status;

    private String reason;

    private String authUser;

    private Date authSubmitTime;

    private Date authResultTime;

    private Integer isNotice;

    private String noticePhone;

    private Integer isLocked;

    private Date createTime;

    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getApiAccount() {
        return apiAccount;
    }

    public void setApiAccount(String apiAccount) {
        this.apiAccount = apiAccount == null ? null : apiAccount.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
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
        this.reason = reason == null ? null : reason.trim();
    }

    public String getAuthUser() {
        return authUser;
    }

    public void setAuthUser(String authUser) {
        this.authUser = authUser == null ? null : authUser.trim();
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

    public Integer getIsNotice() {
        return isNotice;
    }

    public void setIsNotice(Integer isNotice) {
        this.isNotice = isNotice;
    }

    public String getNoticePhone() {
        return noticePhone;
    }

    public void setNoticePhone(String noticePhone) {
        this.noticePhone = noticePhone == null ? null : noticePhone.trim();
    }

    public Integer getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(Integer isLocked) {
        this.isLocked = isLocked;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}