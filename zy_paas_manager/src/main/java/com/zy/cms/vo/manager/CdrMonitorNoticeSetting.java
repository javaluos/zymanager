package com.zy.cms.vo.manager;

import java.util.Date;

public class CdrMonitorNoticeSetting {
    private Integer id;

    private String apiAccount;

    private String noticeTimeRange1;

    private String noticeWay1;

    private String noticeEmail1;

    private String noticePhone1;

    private String noticeTimeRange2;

    private String noticeWay2;

    private String noticeEmail2;

    private String noticePhone2;

    private String noticeTimeRange3;

    private String noticeWay3;

    private String noticeEmail3;

    private String noticePhone3;

    private Integer globalFlag;

    private Date createTime;

    private Integer startFlag;

    private Integer noticeFlag;

    private Date updateTime;

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
        this.apiAccount = apiAccount == null ? null : apiAccount.trim();
    }

    public String getNoticeTimeRange1() {
        return noticeTimeRange1;
    }

    public void setNoticeTimeRange1(String noticeTimeRange1) {
        this.noticeTimeRange1 = noticeTimeRange1 == null ? null : noticeTimeRange1.trim();
    }

    public String getNoticeWay1() {
        return noticeWay1;
    }

    public void setNoticeWay1(String noticeWay1) {
        this.noticeWay1 = noticeWay1 == null ? null : noticeWay1.trim();
    }

    public String getNoticeEmail1() {
        return noticeEmail1;
    }

    public void setNoticeEmail1(String noticeEmail1) {
        this.noticeEmail1 = noticeEmail1 == null ? null : noticeEmail1.trim();
    }

    public String getNoticePhone1() {
        return noticePhone1;
    }

    public void setNoticePhone1(String noticePhone1) {
        this.noticePhone1 = noticePhone1 == null ? null : noticePhone1.trim();
    }

    public String getNoticeTimeRange2() {
        return noticeTimeRange2;
    }

    public void setNoticeTimeRange2(String noticeTimeRange2) {
        this.noticeTimeRange2 = noticeTimeRange2 == null ? null : noticeTimeRange2.trim();
    }

    public String getNoticeWay2() {
        return noticeWay2;
    }

    public void setNoticeWay2(String noticeWay2) {
        this.noticeWay2 = noticeWay2 == null ? null : noticeWay2.trim();
    }

    public String getNoticeEmail2() {
        return noticeEmail2;
    }

    public void setNoticeEmail2(String noticeEmail2) {
        this.noticeEmail2 = noticeEmail2 == null ? null : noticeEmail2.trim();
    }

    public String getNoticePhone2() {
        return noticePhone2;
    }

    public void setNoticePhone2(String noticePhone2) {
        this.noticePhone2 = noticePhone2 == null ? null : noticePhone2.trim();
    }

    public String getNoticeTimeRange3() {
        return noticeTimeRange3;
    }

    public void setNoticeTimeRange3(String noticeTimeRange3) {
        this.noticeTimeRange3 = noticeTimeRange3 == null ? null : noticeTimeRange3.trim();
    }

    public String getNoticeWay3() {
        return noticeWay3;
    }

    public void setNoticeWay3(String noticeWay3) {
        this.noticeWay3 = noticeWay3 == null ? null : noticeWay3.trim();
    }

    public String getNoticeEmail3() {
        return noticeEmail3;
    }

    public void setNoticeEmail3(String noticeEmail3) {
        this.noticeEmail3 = noticeEmail3 == null ? null : noticeEmail3.trim();
    }

    public String getNoticePhone3() {
        return noticePhone3;
    }

    public void setNoticePhone3(String noticePhone3) {
        this.noticePhone3 = noticePhone3 == null ? null : noticePhone3.trim();
    }

    public Integer getGlobalFlag() {
        return globalFlag;
    }

    public void setGlobalFlag(Integer globalFlag) {
        this.globalFlag = globalFlag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStartFlag() {
        return startFlag;
    }

    public void setStartFlag(Integer startFlag) {
        this.startFlag = startFlag;
    }

    public Integer getNoticeFlag() {
        return noticeFlag;
    }

    public void setNoticeFlag(Integer noticeFlag) {
        this.noticeFlag = noticeFlag;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}