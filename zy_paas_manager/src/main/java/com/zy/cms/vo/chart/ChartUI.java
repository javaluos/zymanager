package com.zy.cms.vo.chart;

import java.util.LinkedList;
import java.util.List;

/**
 * 图表UI视图类
 * 
 * @author allen.yuan
 * @date 2016-11-23
 *
 */
public class ChartUI {

	private ChartTitle title;// 标题
	private ChartLegend legends;// 图表
	private ChartAxis axis; // x,y轴数据
	private ChartAvg avg;// y轴下线值,上线值
	private List<ChartSeries> seriesList;// 数据

	public ChartUI() {
		this.initUI();
	}

	public void initUI() {
		title = new ChartTitle();
		legends = new ChartLegend();
		axis = new ChartAxis();
		avg = new ChartAvg();
		seriesList = new LinkedList<ChartSeries>();
	}

	public ChartTitle getTitle() {
		if (title == null) {
			title = new ChartTitle();
		}
		return title;
	}

	public void setTitle(ChartTitle title) {
		this.title = title;
	}

	public ChartLegend getLegends() {
		if (legends == null) {
			legends = new ChartLegend();
		}
		return legends;
	}

	public void setLegends(ChartLegend legends) {
		this.legends = legends;
	}

	public ChartAxis getAxis() {
		if (axis == null) {
			axis = new ChartAxis();
		}
		return axis;
	}

	public void setAxis(ChartAxis axis) {
		this.axis = axis;
	}

	public ChartAvg getAvg() {
		if (avg == null) {
			avg = new ChartAvg();
		}
		return avg;
	}

	public void setAvg(ChartAvg avg) {
		this.avg = avg;
	}

	public List<ChartSeries> getSeriesList() {
		if (seriesList == null) {
			seriesList = new LinkedList<ChartSeries>();
		}
		return seriesList;
	}

	public void setSeriesList(List<ChartSeries> seriesList) {
		this.seriesList = seriesList;
	}

}
