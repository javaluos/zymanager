package com.zy.cms.vo.chart;

import java.util.ArrayList;
import java.util.List;

/**
 * 图表UI--legend实体类
 * 
 * @author allen.yuan
 * @date 2016-11-23
 *
 */
public class ChartLegend {

	// 例如：['A路接通率','B路接通率']
	private List<String> legend;

	public ChartLegend() {

	}

	public List<String> getLegend() {
		if (legend == null) {
			legend = new ArrayList<String>();
		}
		return legend;
	}

	public void setLegend(List<String> legend) {
		this.legend = legend;
	}

}
