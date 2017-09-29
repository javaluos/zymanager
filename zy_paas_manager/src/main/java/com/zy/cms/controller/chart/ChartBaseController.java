package com.zy.cms.controller.chart;

import java.util.ArrayList;
import java.util.List;

import com.zy.cms.enums.BusinessTypeEnum;
import com.zy.cms.enums.GlobalFlagEnum;
import com.zy.cms.enums.MonitorTypeEnum;
import com.zy.cms.vo.EnumUtil;

/**
 * 报表业务,公共数据父类
 * 
 * @author allen.yuan
 * @date 2016-11-29
 *
 */
public abstract class ChartBaseController {

	/**
	 * 初始化业务类型下拉列表
	 * 
	 * @return
	 */
	protected List<EnumUtil> initBtList(int bsType) {

		// 加载业务列表
		List<EnumUtil> btList = new ArrayList<EnumUtil>();

		btList.add(new EnumUtil(BusinessTypeEnum.BT_4.getType(), BusinessTypeEnum.BT_4.getName()));
		btList.add(new EnumUtil(BusinessTypeEnum.BT_5.getType(), BusinessTypeEnum.BT_5.getName()));
		btList.add(new EnumUtil(BusinessTypeEnum.BT_3.getType(), BusinessTypeEnum.BT_3.getName()));
		btList.add(new EnumUtil(BusinessTypeEnum.BT_1.getType(), BusinessTypeEnum.BT_1.getName()));
		btList.add(new EnumUtil(BusinessTypeEnum.BT_2.getType(), BusinessTypeEnum.BT_2.getName()));
		if(bsType==GlobalFlagEnum.DEFAULT.getType()){
			btList.add(new EnumUtil(BusinessTypeEnum.BT_8.getType(), BusinessTypeEnum.BT_8.getName()));
			btList.add(new EnumUtil(BusinessTypeEnum.BT_9.getType(), BusinessTypeEnum.BT_9.getName()));
			btList.add(new EnumUtil(BusinessTypeEnum.BT_11.getType(),BusinessTypeEnum.BT_11.getName()));
		}

		if (bsType == GlobalFlagEnum.GLOBAL.getType()) { // 全局业务增加短信监控
			btList.add(new EnumUtil(BusinessTypeEnum.BT_8.getType(), BusinessTypeEnum.BT_8.getName()));
			btList.add(new EnumUtil(BusinessTypeEnum.BT_9.getType(), BusinessTypeEnum.BT_9.getName()));
			btList.add(new EnumUtil(BusinessTypeEnum.BT_11.getType(),BusinessTypeEnum.BT_11.getName()));
			btList.add(new EnumUtil(BusinessTypeEnum.BT_80.getType(), BusinessTypeEnum.BT_80.getName()));
		}

		return btList;
	}

	/**
	 * 初始化监控类型下拉列表
	 * 
	 * @return
	 */
	protected List<EnumUtil> initMtList() {

		// 加载监控列表
		List<EnumUtil> mtList = new ArrayList<EnumUtil>();
		mtList.add(new EnumUtil(MonitorTypeEnum.MT_1.getType(), MonitorTypeEnum.MT_1.getName()));
		mtList.add(new EnumUtil(MonitorTypeEnum.MT_2.getType(), MonitorTypeEnum.MT_2.getName()));
		mtList.add(new EnumUtil(MonitorTypeEnum.MT_3.getType(), MonitorTypeEnum.MT_3.getName()));
		mtList.add(new EnumUtil(MonitorTypeEnum.MT_4.getType(), MonitorTypeEnum.MT_4.getName()));
		mtList.add(new EnumUtil(MonitorTypeEnum.MT_5.getType(), MonitorTypeEnum.MT_5.getName()));
		mtList.add(new EnumUtil(MonitorTypeEnum.MT_6.getType(), MonitorTypeEnum.MT_6.getName()));
		
		mtList.add(new EnumUtil(MonitorTypeEnum.MT_7.getType(), MonitorTypeEnum.MT_7.getName()));
		mtList.add(new EnumUtil(MonitorTypeEnum.MT_8.getType(), MonitorTypeEnum.MT_8.getName()));
		mtList.add(new EnumUtil(MonitorTypeEnum.MT_9.getType(), MonitorTypeEnum.MT_9.getName()));
		mtList.add(new EnumUtil(MonitorTypeEnum.MT_10.getType(), MonitorTypeEnum.MT_10.getName()));
		mtList.add(new EnumUtil(MonitorTypeEnum.MT_11.getType(), MonitorTypeEnum.MT_11.getName()));

		return mtList;
	}

}
