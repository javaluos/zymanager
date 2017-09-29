package com.zy.cms.vo.chart;

/**
 * 图表UI--axis实体类
 * 
 * @author allen.yuan
 * @date 2016-11-23
 *
 */
public class ChartSeries {

	private String name = "";
	private Integer count = 0;
	private String[] data = {};
	private String[] markdata = {};

	public String[] getData() {
		if (data == null) {
			data = new String[0];
		}
		return data;
	}

	public void setyData(String[] data) {
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getMarkdata() {
		if (markdata == null) {
			markdata = new String[0];
		}
		return markdata;
	}

	public void setMarkdata(String[] markdata) {
		this.markdata = markdata;
	}

	public void setData(String[] data) {
		this.data = data;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}
