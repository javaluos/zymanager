package com.zy.cms.vo.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;


/**
 * 短信发送查询对象
 * @author luos
 * @date 2017-2-23 11:41:18
 *
 */
public class SmsSendFilterQuery {
	
	 private String smsId;

    private String apiAccount;
    
    private String businessName;
    
    private String merchantPhone;

    private String appId;

    private String smsCategory;

    private String smsSignerId;

    private String smsTemplateId;

    private String receiveMobile;

    private String smsContent;

    private Integer smsNums;

    private Integer smsFee;

    private Integer smsType;

    private Integer resource;

    private Integer protocolType;

    private String province;

    private String city;

    private String carriers;

    private String blackContent;

    private Integer status;

    private Date createTime;
    
    private String createTimeStart;
    
    private String createTimeEnd;

    private Date updateTime;
    
    private String orderRule;//排序规则 0 按条数倒叙 1 按条数升序
    
    private String blackContentDetail;
    
    private String smsContentDetail;

	private Integer pageNum;// 页号
	
	private Integer pageSize;// 每页数量
	
	private Integer pageOffset = 0;// 分页的开始值
	
	private Integer pageCount;// 统计页数
	
	private List apiAccounts;
	
	private List<String> smsIdList;// 短信ID集
	
	private List<SubSmsSendFilterQuery> list=new ArrayList<SubSmsSendFilterQuery>();

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

	public Integer getPageOffset() {
		pageOffset = getPageNum() * getPageSize();
		return pageOffset;
	}

	public void setPageOffset(Integer pageOffset) {
		this.pageOffset = pageOffset;
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
	

	public List<String> getSmsIdList() {
		return smsIdList;
	}

	public void setSmsIdList(List<String> smsIdList) {
		this.smsIdList = smsIdList;
	}

	public String getApiAccount() {
		return apiAccount;
	}

	public void setApiAccount(String apiAccount) {
		this.apiAccount = apiAccount;
	}

	public String getSmsContent() {
		return smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

	public String getBlackContent() {
		return blackContent;
	}

	public void setBlackContent(String blackContent) {
		this.blackContent = blackContent;
	}

	public String getSmsId() {
		return smsId;
	}

	public void setSmsId(String smsId) {
		this.smsId = smsId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getSmsCategory() {
		if(StringUtils.isNotEmpty(smsCategory)){
			if(smsCategory.equals("0")){
				smsCategory="";
			}
		}
		return smsCategory;
	}

	public void setSmsCategory(String smsCategory) {
		this.smsCategory = smsCategory;
	}

	public String getSmsSignerId() {
		return smsSignerId;
	}

	public void setSmsSignerId(String smsSignerId) {
		this.smsSignerId = smsSignerId;
	}

	public String getSmsTemplateId() {
		return smsTemplateId;
	}

	public void setSmsTemplateId(String smsTemplateId) {
		this.smsTemplateId = smsTemplateId;
	}

	public String getReceiveMobile() {
		return receiveMobile;
	}

	public void setReceiveMobile(String receiveMobile) {
		this.receiveMobile = receiveMobile;
	}

	public Integer getSmsNums() {
		return smsNums;
	}

	public void setSmsNums(Integer smsNums) {
		this.smsNums = smsNums;
	}

	public Integer getSmsFee() {
		return smsFee;
	}

	public void setSmsFee(Integer smsFee) {
		this.smsFee = smsFee;
	}

	public Integer getSmsType() {
		return smsType;
	}

	public void setSmsType(Integer smsType) {
		this.smsType = smsType;
	}

	public Integer getResource() {
		return resource;
	}

	public void setResource(Integer resource) {
		this.resource = resource;
	}

	public Integer getProtocolType() {
		return protocolType;
	}

	public void setProtocolType(Integer protocolType) {
		this.protocolType = protocolType;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCarriers() {
		return carriers;
	}

	public void setCarriers(String carriers) {
		this.carriers = carriers;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public String getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(String createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public String getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public String getBusinessName() {
		return businessName == null ? "" : businessName.trim();
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getMerchantPhone() {
		return merchantPhone == null ? "" : merchantPhone.trim();
	}

	public void setMerchantPhone(String merchantPhone) {
		this.merchantPhone = merchantPhone;
	}

	public String getOrderRule() {
		return orderRule;
	}

	public void setOrderRule(String orderRule) {
		this.orderRule = orderRule;
	}

	public List getApiAccounts() {
		return apiAccounts;
	}

	public void setApiAccounts(List apiAccounts) {
		this.apiAccounts = apiAccounts;
	}

	public String getBlackContentDetail() {
		return blackContentDetail;
	}

	public void setBlackContentDetail(String blackContentDetail) {
		this.blackContentDetail = blackContentDetail;
	}

	public String getSmsContentDetail() {
		return smsContentDetail;
	}

	public void setSmsContentDetail(String smsContentDetail) {
		this.smsContentDetail = smsContentDetail;
	}

	public List<SubSmsSendFilterQuery> getList() {
		return list;
	}

	public void setList(List<SubSmsSendFilterQuery> list) {
		this.list = list;
	}
	
	
}
