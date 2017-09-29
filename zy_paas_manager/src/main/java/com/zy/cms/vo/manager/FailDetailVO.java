package com.zy.cms.vo.manager;

/**
 * 发送失败分析实体
 * Created by luos on 2017/6/14.
 */
public class FailDetailVO {

    private String receiveStatusDesc; //失败状态描述

    private Integer total; //失败总数

    private Integer groupCount; //各类型数量

    private Double percentage; //所占百分比

    private String statusDescCN; //失败状态中文描述

    public String getReceiveStatusDesc() {
        return receiveStatusDesc;
    }

    public void setReceiveStatusDesc(String receiveStatusDesc) {
        this.receiveStatusDesc = receiveStatusDesc;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getGroupCount() {
        return groupCount;
    }

    public void setGroupCount(Integer groupCount) {
        this.groupCount = groupCount;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public String getStatusDescCN() {
        return statusDescCN;
    }

    public void setStatusDescCN(String statusDescCN) {
        this.statusDescCN = statusDescCN;
    }
}
