package com.zy.cms.vo.chart;

/**
 * 图表UI--title实体类
 * 
 * @author allen.yuan
 * @date 2016-11-23
 *
 */
public class ChartTitle {

	private String text = "";
	private String subtext = "";

	public ChartTitle() {

	}

	public ChartTitle(String text, String subtext) {
		this.text = text;
		this.subtext = subtext;
	}

	public void setChartTitle(String text, String subtext) {
		this.text = text;
		this.subtext = subtext;
	}

	public String getText() {
		return text == null ? "" : text.trim();
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getSubtext() {
		return subtext == null ? "" : subtext.trim();
	}

	public void setSubtext(String subtext) {
		this.subtext = subtext;
	}

}
