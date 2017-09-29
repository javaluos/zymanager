package com.zy.cms.vo.chart;

/**
 * 图表UI--Avg实体类
 * 
 * @author allen.yuan
 * @date 2016-11-23
 *
 */
public class ChartAvg {

	private int minY = 0;// 下线
	private int maxY = 0;// 上线

	public ChartAvg() {

	}

	public ChartAvg(int minY, int maxY) {

		this.minY = minY;
		this.maxY = maxY;
	}

	public void setChartAvg(int minY, int maxY) {
		this.minY = minY;
		this.maxY = maxY;
	}

	public int getMinY() {
		return minY;
	}

	public void setMinY(int minY) {
		this.minY = minY;
	}

	public int getMaxY() {
		return maxY;
	}

	public void setMaxY(int maxY) {
		this.maxY = maxY;
	}

}
