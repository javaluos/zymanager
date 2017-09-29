package com.zy.cms.vo;

import java.util.Date;

/**
 * 拦截策略
 * @author JasonXu
 *
 */
public class BlackKeyPolicy {
	
    private Integer id;//策略ID

    private String policyName;//拦截策略名称

    private String remark;//备注

    private Date createTime;//创建时间

    private Date updateTime;//更新时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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