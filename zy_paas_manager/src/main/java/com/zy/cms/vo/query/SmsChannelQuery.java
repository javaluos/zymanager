package com.zy.cms.vo.query;

import com.zy.cms.util.DateUtil;
import com.zy.cms.util.StringUtil;

/**
 * 通道查询器
 * 
 * @author allen.yuan
 * @date 2017-1-21
 */
public class SmsChannelQuery {

	private String channelMainCode;// 通道编号
	private String channelId;// 通道ID
	private String channelName; // 通道名称
	private Integer channelType; // 通道类型
	private Integer operateType; // 运营商类型
	private String channelProperty;// 运营商类型
	private String dtnProvince;//落地省份
	private Integer status; // 状态
	private String createStarttime;// 创建开始时间
	private String createEndtime;// 创建结束时间
	private Integer pageNum;// 页号
	private Integer pageSize;// 每页数量
	private Integer pageOffset = 0;// 分页的开始值
	private Integer pageCount;// 统计页数
	
	public String getChannelMainCode() {
		return channelMainCode == null ? "" : channelMainCode.trim();
	}

	public void setChannelMainCode(String channelMainCode) {
		this.channelMainCode = channelMainCode;
	}

	public String getChannelId() {
		return channelId == null ? "" : channelId.trim();
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getChannelName() {
		return channelName == null ? "" : channelName.trim();
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public Integer getChannelType() {
		return channelType == null ? -1 : channelType;
	}

	public void setChannelType(Integer channelType) {
		this.channelType = channelType;
	}

	public Integer getOperateType() {
		return operateType;
	}

	public void setOperateType(Integer operateType) {
		this.operateType = operateType;
	}

	public String getChannelProperty() {
		return channelProperty == null ? "" : channelProperty.trim();
	}
	
	public String getDtnProvince() {
		return dtnProvince;
	}

	public void setDtnProvince(String dtnProvince) {
		this.dtnProvince = dtnProvince;
	}

	public void setChannelProperty(String channelProperty) {
		this.channelProperty = channelProperty;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreateStarttime() {
		return createStarttime;
	}

	public void setCreateStarttime(String createStarttime) {
		this.createStarttime = createStarttime;
	}

	public String getCreateEndtime() {
		return createEndtime;
	}

	public void setCreateEndtime(String createEndtime) {
		this.createEndtime = createEndtime;
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

	public Long getCreateStarttimeL() {
		if (StringUtil.isEmpty(getCreateStarttime())) {
			return 0L;
		}
		return DateUtil.date2TimeStamp(getCreateStarttime(), DateUtil.ISO_DATE_TIME_FORMAT) / 1000;
	}

	public Long getCreateEndtimeL() {
		if (StringUtil.isEmpty(getCreateEndtime())) {
			return 0L;
		}
		return DateUtil.date2TimeStamp(getCreateEndtime(), DateUtil.ISO_DATE_TIME_FORMAT) / 1000;
	}

}
