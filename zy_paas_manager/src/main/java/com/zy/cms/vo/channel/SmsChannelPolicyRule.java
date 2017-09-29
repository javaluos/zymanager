package com.zy.cms.vo.channel;

import java.util.Date;

/**
 * 分流策略表
 * @author JasonXu
 *
 */
public class SmsChannelPolicyRule {
	
    private String id;

    private String policyId;//策略id

    private String keyword;//关键字

    private String mobiles;//号段

    private Integer groupType;//1表示通道组 2表示通道

    private String groupYD;//移动通道组ID

    private String groupLT;//联通通道组ID

    private String groupDX;//电信通道组ID

    private Integer ruleIndex;//策略序号

    private Date createTime;

    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? "" : keyword.trim();
    }

    public String getMobiles() {
        return mobiles;
    }

    public void setMobiles(String mobiles) {
        this.mobiles = mobiles == null ? "" : mobiles.trim();
    }

    public Integer getGroupType() {
        return groupType;
    }

    public void setGroupType(Integer groupType) {
        this.groupType = groupType;
    }

    public String getPolicyId() {
		return policyId;
	}

	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}

	public String getGroupYD() {
		this.groupYD = (groupYD == null||groupYD.equals("-1")) ? "" : groupYD.trim();
		return groupYD;
	}

	public void setGroupYD(String groupYD) {
		this.groupYD = (groupYD == null||groupYD.equals("-1")) ? "" : groupYD.trim();
	}

	public String getGroupLT() {
		this.groupLT = (groupLT == null||groupLT.equals("-1")) ? "" : groupLT.trim();
		return groupLT;
	}

	public void setGroupLT(String groupLT) {
		this.groupLT = (groupLT == null||groupLT.equals("-1")) ? "" : groupLT.trim();
	}

	public String getGroupDX() {
		this.groupDX = (groupDX == null||groupDX.equals("-1")) ? "" : groupDX.trim();
		return groupDX;
	}

	public void setGroupDX(String groupDX) {
		this.groupDX = (groupDX == null||groupDX.equals("-1")) ? "" : groupDX.trim();
	}

	public Integer getRuleIndex() {
		return ruleIndex;
	}

	public void setRuleIndex(Integer ruleIndex) {
		this.ruleIndex = ruleIndex;
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