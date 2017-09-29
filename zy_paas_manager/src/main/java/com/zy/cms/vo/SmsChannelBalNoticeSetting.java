package com.zy.cms.vo;

import java.util.Date;

public class SmsChannelBalNoticeSetting {
	
    private Integer id;

    private String noticeType;//报警方式设置(0:短信;1:邮箱,2:语音)

    private String noticeEmail;//通知邮箱,多个邮箱号用","隔开

    private String noticePhone;//通知手机号,多个手机号用","隔开

    private Date createTime;//创建时间

    private Date updateTime;//更新时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }

    public String getNoticeEmail() {
        return noticeEmail;
    }

    public void setNoticeEmail(String noticeEmail) {
        this.noticeEmail = noticeEmail == null ? null : noticeEmail.trim();
    }

    public String getNoticePhone() {
        return noticePhone;
    }

    public void setNoticePhone(String noticePhone) {
        this.noticePhone = noticePhone == null ? null : noticePhone.trim();
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