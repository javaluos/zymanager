package com.zy.cms.service.manager.impl;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zy.cms.common.ZyLogger;
import com.zy.cms.enums.BusinessTypeEnum;
import com.zy.cms.enums.GlobalFlagEnum;
import com.zy.cms.enums.MonitorTypeEnum;
import com.zy.cms.enums.UnitEnum;
import com.zy.cms.mapper.manager.CdrMonitorChartMapper;
import com.zy.cms.mapper.manager.CdrMonitorSettingMapper;
import com.zy.cms.service.manager.CdrMonitorAccountChartService;
import com.zy.cms.util.DateUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.chart.ChartAvg;
import com.zy.cms.vo.chart.ChartPonit;
import com.zy.cms.vo.chart.ChartSeries;
import com.zy.cms.vo.chart.ChartTitle;
import com.zy.cms.vo.chart.ChartUI;
import com.zy.cms.vo.manager.CdrMonitorSetting;
import com.zy.cms.vo.manager.CdrMonitorStat;
import com.zy.cms.vo.query.ChartQuery;

/**
 * 话单定时统计图表业务接口实现类
 * 
 * @author allen.yuan
 * @date 2016-11-25
 *
 */

@Service("cdrMonitorAccountChartService")
public class CdrMonitorAccountChartServiceImpl implements CdrMonitorAccountChartService {

	private static final ZyLogger logger = ZyLogger.getLogger(CdrMonitorAccountChartServiceImpl.class);

	@Autowired
	private CdrMonitorChartMapper cdrMonitorChartMapper;

	@Autowired
	private CdrMonitorSettingMapper cdrMonitorSettingMapper;

	/**
	 * 通过UI参数查询图表数据
	 * 
	 * @param chartQuery
	 *            图表查询参数
	 * @return ChartUI
	 */
	@Override
	public ChartUI queryMonitorStatForAccountLine(ChartQuery chartQuery) {

		ChartUI charData = null;

		try {

			charData = initAccountMonitorChart(chartQuery);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【客户话单统计报表业务】折线图异常,error=" + e.getMessage());
		}
		if (charData == null) {
			charData = new ChartUI();
		}

		return charData;
	}

	/**
	 * 初始化客户监控图表数据
	 * 
	 * @param chartQuery
	 * @return
	 * @throws Exception
	 */
	private ChartUI initAccountMonitorChart(ChartQuery chartQuery) throws Exception {

		// 定义图表业务数据对象
		ChartUI charUI = new ChartUI();

		// 加载客户监控数据
		int monitorType = chartQuery.getMonitorType();// 查询子业务
		String monitorDate=chartQuery.getMonitorDate();

		List<CdrMonitorStat> aStatList = cdrMonitorChartMapper.queryMonitorStatForAccount(chartQuery);
		Map<String, CdrMonitorStat> mapDatas = transformList2Map(aStatList);// 转换数据
		aStatList = null;

		chartQuery.setMonitorDate(DateUtil.getLastDate(monitorDate));
		List<CdrMonitorStat> aStatList2 = cdrMonitorChartMapper.queryMonitorStatForAccount(chartQuery);
		Map<String, CdrMonitorStat> mapDatas2 = transformList2Map(aStatList2);// 转换数据
		aStatList2 = null;
		
		chartQuery.setMonitorDate(DateUtil.getLast2Date(monitorDate));
		List<CdrMonitorStat> aStatList3 = cdrMonitorChartMapper.queryMonitorStatForAccount(chartQuery);
		Map<String, CdrMonitorStat> mapDatas3 = transformList2Map(aStatList3);// 转换数据
		aStatList3 = null;
		chartQuery.setMonitorDate(monitorDate);
		
		int countLen = mapDatas.size() > 0 ? mapDatas.size() : 288; // (默认为5分钟间隔,长度为288)
		String[] setDataA = new String[countLen]; // A路统计数据
		String[] setDataB = new String[countLen]; // B路统计数据
		String[] setDataAPre1day = new String[countLen]; // A路前一天数据
		String[] setDataAPre2day = new String[countLen]; // A路前2天数据
		String[] setDataBPre1day = new String[countLen]; // A路前一天数据
		String[] setDataBPre2day = new String[countLen]; // A路前2天数据
		String yUnit = "";// y轴单位
		int countA = 0;
		int countB = 0;
        int countAPre1day=0;
        int countAPre2day=0;
        int countBPre1day=0;
        int countBPre2day=0;
        
		int index = 0;
		CdrMonitorStat stat = null;
		for (Map.Entry<String, CdrMonitorStat> data : mapDatas.entrySet()) {

			stat = data.getValue();
			if (monitorType == MonitorTypeEnum.MT_1.getType()) { // 并发
				setDataA[index] = stat.getCallCounta() + "";
				setDataB[index] = stat.getCallCountb() + "";
				yUnit = UnitEnum.times;// 次
				countA = countA + stat.getCallCounta();
				countB = countB + stat.getCallCountb();
			} else if (monitorType == MonitorTypeEnum.MT_2.getType()) {// 接通率
				setDataA[index] = stat.getSuccessRatea() / 100 + "";
				setDataB[index] = stat.getSuccessRateb() / 100 + "";
				yUnit = UnitEnum.percent;// %
			} else if (monitorType == MonitorTypeEnum.MT_3.getType()) {// 应答率
				setDataA[index] = stat.getResponseRatea() / 100 + "";
				setDataB[index] = stat.getResponseRateb() / 100 + "";
				yUnit = UnitEnum.percent;// %
			} else if (monitorType == MonitorTypeEnum.MT_4.getType()) {// 平均通话时延
				setDataA[index] = stat.getAverageTurnOnDelaya() + "";
				setDataB[index] = stat.getAverageTurnOnDelayb() + "";
				yUnit = UnitEnum.second;// s
			} else if (monitorType == MonitorTypeEnum.MT_5.getType()) {// 平均通话时长
				setDataA[index] = stat.getAverageTalkTimea() + "";
				setDataB[index] = stat.getAverageTalkTimeb() + "";
				yUnit = UnitEnum.second;// s
			} else if (monitorType == MonitorTypeEnum.MT_6.getType()) {// 平均接续时长
				setDataA[index] = stat.getAverageInTimea() + "";
				setDataB[index] = stat.getAverageInTimeb() + "";
				yUnit = UnitEnum.second;// s
			}else if (monitorType == MonitorTypeEnum.MT_7.getType()) {// 短信并发数
				setDataA[index] = stat.getCallCountb() + "";
//				setDataB[index] = stat.getAverageInTimeb() + "";
				countA +=stat.getCallCountb();
				yUnit = UnitEnum.times;// s
			}else if (monitorType == MonitorTypeEnum.MT_8.getType()) {// 成功率
				if(stat.getTotalCallCount()==0){
					setDataA[index] ="0";
				}else{
					setDataA[index] =100*stat.getRingCounta() / stat.getTotalCallCount()+ "";
				}
//				setDataB[index] = stat.getAverageInTimeb() + "";
				yUnit = UnitEnum.percent;// s
			}else if (monitorType == MonitorTypeEnum.MT_9.getType()) {// 失败率
				if(stat.getTotalCallCount()==0){
					setDataA[index] ="0";
				}else{
					setDataA[index] = 100*stat.getRingCountb() /stat.getTotalCallCount()+ "";
				}
//				setDataB[index] = stat.getAverageInTimeb() + "";
				yUnit = UnitEnum.percent;// s
			}else if (monitorType == MonitorTypeEnum.MT_10.getType()) {// 未知率
				if(stat.getTotalCallCount()==0){
					setDataA[index] ="0";
				}else{
					setDataA[index] =  100*stat.getResponseCounta() / stat.getTotalCallCount()  + "";
				}
//				setDataB[index] = stat.getAverageInTimeb() + "";
				yUnit = UnitEnum.percent;// s
			}else if (monitorType == MonitorTypeEnum.MT_11.getType()) {// 平均发送时长
				if(stat.getRingCounta()==0){
					setDataA[index] ="0";
				}else{
					setDataA[index] = stat.getSendTime()/stat.getRingCounta()+ "";
//					setDataA[index] = stat.getAverageTalkTimeb()/stat.getRingCounta()+ "";
				}
//				setDataB[index] = stat.getAverageInTimeb() + "";
				yUnit = UnitEnum.second;// s
			}else if (monitorType == MonitorTypeEnum.MT_12.getType()) {//平均状态报告时长
				if(stat.getRingCounta()==0){
					setDataA[index] ="0";
				}else{
					setDataA[index] = stat.getReportTime()/stat.getRingCounta()+ "";
//					setDataA[index] = stat.getAverageTalkTimea()/stat.getRingCounta()+ "";
				}
//				setDataB[index] = stat.getAverageInTimeb() + "";
				yUnit = UnitEnum.second;// s
			}
			index++;
		}

		index = 0;
		for (Map.Entry<String, CdrMonitorStat> data : mapDatas2.entrySet()) {   //前一天的数据

			stat = data.getValue();
			if (monitorType == MonitorTypeEnum.MT_1.getType()) { // 并发
				setDataAPre1day[index] = stat.getCallCounta() + "";
				setDataBPre1day[index] = stat.getCallCountb() + "";
				yUnit = UnitEnum.times;// 次
				countAPre1day = countAPre1day + stat.getCallCounta();
				countBPre1day = countBPre1day + stat.getCallCountb();
			} else if (monitorType == MonitorTypeEnum.MT_2.getType()) {// 接通率
				setDataAPre1day[index] = stat.getSuccessRatea() / 100 + "";
				setDataBPre1day[index] = stat.getSuccessRateb() / 100 + "";
				yUnit = UnitEnum.percent;// %
			} else if (monitorType == MonitorTypeEnum.MT_3.getType()) {// 应答率
				setDataAPre1day[index] = stat.getResponseRatea() / 100 + "";
				setDataBPre1day[index] = stat.getResponseRateb() / 100 + "";
				yUnit = UnitEnum.percent;// %
			} else if (monitorType == MonitorTypeEnum.MT_4.getType()) {// 平均通话时延
				setDataAPre1day[index] = stat.getAverageTurnOnDelaya() + "";
				setDataBPre1day[index] = stat.getAverageTurnOnDelayb() + "";
				yUnit = UnitEnum.second;// s
			} else if (monitorType == MonitorTypeEnum.MT_5.getType()) {// 平均通话时长
				setDataAPre1day[index] = stat.getAverageTalkTimea() + "";
				setDataBPre1day[index] = stat.getAverageTalkTimeb() + "";
				yUnit = UnitEnum.second;// s
			} else if (monitorType == MonitorTypeEnum.MT_6.getType()) {// 平均接续时长
				setDataAPre1day[index] = stat.getAverageInTimea() + "";
				setDataBPre1day[index] = stat.getAverageInTimeb() + "";
				yUnit = UnitEnum.second;// s
			}else if (monitorType == MonitorTypeEnum.MT_7.getType()) {// 短信并发数
				setDataAPre1day[index] = stat.getCallCountb() + "";
				countAPre1day +=stat.getCallCountb();
				yUnit = UnitEnum.times;// s
			}else if (monitorType == MonitorTypeEnum.MT_8.getType()) {// 成功率
				if(stat.getTotalCallCount()==0){
					setDataAPre1day[index] ="0";
				}else{
					setDataAPre1day[index] =100*stat.getRingCounta() / stat.getTotalCallCount()+ "";
				}
				yUnit = UnitEnum.percent;// s
			}else if (monitorType == MonitorTypeEnum.MT_9.getType()) {// 失败率
				if(stat.getTotalCallCount()==0){
					setDataAPre1day[index] ="0";
				}else{
					setDataAPre1day[index] = 100*stat.getRingCountb() /stat.getTotalCallCount()+ "";
				}
				yUnit = UnitEnum.percent;// s
			}else if (monitorType == MonitorTypeEnum.MT_10.getType()) {// 未知率
				if(stat.getTotalCallCount()==0){
					setDataAPre1day[index] ="0";
				}else{
					setDataAPre1day[index] =  100*stat.getResponseCounta() / stat.getTotalCallCount()  + "";
				}
				yUnit = UnitEnum.percent;// s
			}else if (monitorType == MonitorTypeEnum.MT_11.getType()) {// 平均发送时长
				if(stat.getRingCounta()==0){
					setDataAPre1day[index] ="0";
				}else{
					setDataAPre1day[index] = stat.getSendTime()/stat.getRingCounta()+ "";
				}
				yUnit = UnitEnum.second;// s
			}else if (monitorType == MonitorTypeEnum.MT_12.getType()) {//平均状态报告时长
				if(stat.getRingCounta()==0){
					setDataAPre1day[index] ="0";
				}else{
					setDataAPre1day[index] = stat.getReportTime()/stat.getRingCounta()+ "";
				}
				yUnit = UnitEnum.second;// s
			}
			index++;
		}
		
		index = 0;
		for (Map.Entry<String, CdrMonitorStat> data : mapDatas3.entrySet()) {//前两天的数据

			stat = data.getValue();
			if (monitorType == MonitorTypeEnum.MT_1.getType()) { // 并发
				setDataAPre2day[index] = stat.getCallCounta() + "";
				setDataBPre2day[index] = stat.getCallCountb() + "";
				yUnit = UnitEnum.times;// 次
				countAPre2day = countAPre2day + stat.getCallCounta();
				countBPre2day = countBPre2day + stat.getCallCountb();
			} else if (monitorType == MonitorTypeEnum.MT_2.getType()) {// 接通率
				setDataAPre2day[index] = stat.getSuccessRatea() / 100 + "";
				setDataBPre2day[index] = stat.getSuccessRateb() / 100 + "";
				yUnit = UnitEnum.percent;// %
			} else if (monitorType == MonitorTypeEnum.MT_3.getType()) {// 应答率
				setDataAPre2day[index] = stat.getResponseRatea() / 100 + "";
				setDataBPre2day[index] = stat.getResponseRateb() / 100 + "";
				yUnit = UnitEnum.percent;// %
			} else if (monitorType == MonitorTypeEnum.MT_4.getType()) {// 平均通话时延
				setDataAPre2day[index] = stat.getAverageTurnOnDelaya() + "";
				setDataBPre2day[index] = stat.getAverageTurnOnDelayb() + "";
				yUnit = UnitEnum.second;// s
			} else if (monitorType == MonitorTypeEnum.MT_5.getType()) {// 平均通话时长
				setDataAPre2day[index] = stat.getAverageTalkTimea() + "";
				setDataBPre2day[index] = stat.getAverageTalkTimeb() + "";
				yUnit = UnitEnum.second;// s
			} else if (monitorType == MonitorTypeEnum.MT_6.getType()) {// 平均接续时长
				setDataAPre2day[index] = stat.getAverageInTimea() + "";
				setDataBPre2day[index] = stat.getAverageInTimeb() + "";
				yUnit = UnitEnum.second;// s
			}else if (monitorType == MonitorTypeEnum.MT_7.getType()) {// 短信并发数
				setDataAPre2day[index] = stat.getCallCountb() + "";
				countAPre2day +=stat.getCallCountb();
				yUnit = UnitEnum.times;// s
			}else if (monitorType == MonitorTypeEnum.MT_8.getType()) {// 成功率
				if(stat.getTotalCallCount()==0){
					setDataAPre2day[index] ="0";
				}else{
					setDataAPre2day[index] =100*stat.getRingCounta() / stat.getTotalCallCount()+ "";
				}
				yUnit = UnitEnum.percent;// s
			}else if (monitorType == MonitorTypeEnum.MT_9.getType()) {// 失败率
				if(stat.getTotalCallCount()==0){
					setDataAPre2day[index] ="0";
				}else{
					setDataAPre2day[index] = 100*stat.getRingCountb() /stat.getTotalCallCount()+ "";
				}
				yUnit = UnitEnum.percent;// s
			}else if (monitorType == MonitorTypeEnum.MT_10.getType()) {// 未知率
				if(stat.getTotalCallCount()==0){
					setDataAPre2day[index] ="0";
				}else{
					setDataAPre2day[index] =  100*stat.getResponseCounta() / stat.getTotalCallCount()  + "";
				}
				yUnit = UnitEnum.percent;// s
			}else if (monitorType == MonitorTypeEnum.MT_11.getType()) {// 平均发送时长
				if(stat.getRingCounta()==0){
					setDataAPre2day[index] ="0";
				}else{
					setDataAPre2day[index] = stat.getSendTime()/stat.getRingCounta()+ "";
				}
				yUnit = UnitEnum.second;// s
			}else if (monitorType == MonitorTypeEnum.MT_12.getType()) {//平均状态报告时长
				if(stat.getRingCounta()==0){
					setDataAPre2day[index] ="0";
				}else{
					setDataAPre2day[index] = stat.getReportTime()/stat.getRingCounta()+ "";
				}
				yUnit = UnitEnum.second;// s
			}
			index++;
		}
		// 获得客户的上线&下线值
		settingActConfig(chartQuery, charUI);

		// 统计后处理
		settingUITags(chartQuery, charUI, setDataA, setDataB, countA, countB, yUnit,
				setDataAPre1day,setDataBPre1day,setDataAPre2day,setDataBPre2day,countAPre1day,countBPre1day,countAPre2day,countBPre2day,monitorDate);

		return charUI;

	}

	/**
	 * 获得客户的上线&下线值
	 * 
	 * @param chartQuery
	 * @param charUI
	 */
	private void settingActConfig(ChartQuery chartQuery, ChartUI charUI) {

		ChartAvg avg = null;
		// 获得客户监控指标上线,下线
		List<CdrMonitorSetting> aSettingList = cdrMonitorSettingMapper.queryMonitorSettingForAll(
				GlobalFlagEnum.DEFAULT.getType(), chartQuery.getApiAccount(), chartQuery.getBusinessId());

		// 设置客户监控指标下线,上线数据
		CdrMonitorSetting useSetting = null;
		if (aSettingList != null && aSettingList.size() > 0) {

			// 获得setting数据
			String businessId = chartQuery.getBusinessId();
			String currentTime = DateUtil.getDateTime();// 获得当前时间
			for (CdrMonitorSetting setting : aSettingList) {
				if (businessId.equals(String.valueOf(setting.getBusinessId()))) {
					boolean isLink = linkTimeLens(currentTime, setting.getMeasureTime());
					logger.info("【客户话单统计报表业务】折线图获得上线&下线验证={currentTime=" + currentTime + ", measureTime="
							+ setting.getMeasureTime() + ", isLink=" + isLink + "}");
					if (isLink) {
						useSetting = setting;
						break;
					}
				}

			}
		}
		if (useSetting != null) {
			avg = new ChartAvg(useSetting.getAverageInTimeaUp(), useSetting.getAverageTalkTimeaDown());
		} else {
			avg = new ChartAvg(30, 50);
		}

		// 设置上线,下线值
		charUI.getAvg().setChartAvg(avg.getMinY(), avg.getMaxY());
	}

	/**
	 * 后处理UI设置
	 */
	private void settingUITags(ChartQuery chartQuery, ChartUI charUI, String[] setDataA, String[] setDataB, int countA,
			int countB, String yUnit,String[] setDataAPre1day,String[]  setDataBPre1day,String[] setDataAPre2day,String[]  setDataBPre2day,
			int countAPre1day,int countBPre1day,int countAPre2day,int countBPre2day,String monitorDate) {

		String businessId = chartQuery.getBusinessId();// 监控业务
		int monitorType = chartQuery.getMonitorType();// 查询子业务

		boolean existALin = false;// 存在A路
		String titleName = "";
		String titleSub = "——" + chartQuery.getMonitorDate();
		if (monitorType == MonitorTypeEnum.MT_1.getType()) { // 并发数
			titleName = MonitorTypeEnum.MT_1.getName();
		} else if (monitorType == MonitorTypeEnum.MT_2.getType()) {// 接通率
			titleName = MonitorTypeEnum.MT_2.getName();
		} else if (monitorType == MonitorTypeEnum.MT_3.getType()) {// 应答率
			titleName = MonitorTypeEnum.MT_3.getName();
		} else if (monitorType == MonitorTypeEnum.MT_4.getType()) {// 平均通话时延
			titleName = MonitorTypeEnum.MT_4.getName();
		} else if (monitorType == MonitorTypeEnum.MT_5.getType()) {// 平均通话时长
			titleName = MonitorTypeEnum.MT_5.getName();
		} else if (monitorType == MonitorTypeEnum.MT_6.getType()) {// 平均接续时长
			titleName = MonitorTypeEnum.MT_6.getName();
		}else if (monitorType == MonitorTypeEnum.MT_7.getType()) {// 并发数
		    titleName = MonitorTypeEnum.MT_7.getName();
		}else if (monitorType == MonitorTypeEnum.MT_8.getType()) {// 成功率
			titleName = MonitorTypeEnum.MT_8.getName();
		}else if (monitorType == MonitorTypeEnum.MT_9.getType()) {// 失败率
			titleName = MonitorTypeEnum.MT_9.getName();
		}else if (monitorType == MonitorTypeEnum.MT_10.getType()) {// 未知率
			titleName = MonitorTypeEnum.MT_10.getName();
		}else if (monitorType == MonitorTypeEnum.MT_11.getType()) {// 平均发送时长
			titleName = MonitorTypeEnum.MT_11.getName();
		}else if (monitorType == MonitorTypeEnum.MT_12.getType()) {// 平均状态报告时长
			titleName = MonitorTypeEnum.MT_12.getName();
		}
		charUI.setTitle(new ChartTitle(titleName, titleSub));// 设置title

		// 处理A路(回拨包含A,B路) & 号码卫士 & 短信(老平台)
		if (businessId.equals(BusinessTypeEnum.BT_1.getTypeStr())
				|| businessId.equals(BusinessTypeEnum.BT_2.getTypeStr())
				|| businessId.equals(BusinessTypeEnum.BT_80.getTypeStr())
				|| businessId.equals(BusinessTypeEnum.BT_8.getTypeStr())
				|| businessId.equals(BusinessTypeEnum.BT_9.getTypeStr())
				|| businessId.equals(BusinessTypeEnum.BT_11.getTypeStr())) {

			existALin = true;// 存在A路
			String tmpName = monitorDate + " "+UnitEnum.alink_prefix + titleName;
			if(businessId.equals(BusinessTypeEnum.BT_8.getTypeStr())
					|| businessId.equals(BusinessTypeEnum.BT_9.getTypeStr())
					|| businessId.equals(BusinessTypeEnum.BT_11.getTypeStr())){
				tmpName= monitorDate+ " "+titleName;
			}

			// 设置 series
			ChartSeries seriesA = new ChartSeries();
			seriesA.setName(tmpName);
			seriesA.setName(tmpName);
			if (monitorType == MonitorTypeEnum.MT_1.getType()||monitorType == MonitorTypeEnum.MT_7.getType()) {
				tmpName = tmpName + "(" + countA + ")";
				seriesA.setName(tmpName);
			}
			seriesA.setyData(setDataA);
			charUI.getSeriesList().add(seriesA);

			// 设置 legend
			charUI.getLegends().getLegend().add(tmpName);
			
			
			/****  1天前的数据  ** **/
			String tmpName3 = DateUtil.getLastDate(monitorDate)+ " "+UnitEnum.alink_prefix + titleName;
			if(businessId.equals(BusinessTypeEnum.BT_8.getTypeStr())
					|| businessId.equals(BusinessTypeEnum.BT_9.getTypeStr())
					|| businessId.equals(BusinessTypeEnum.BT_11.getTypeStr())){
				tmpName3= DateUtil.getLastDate(monitorDate)+" "+titleName;
			}
			// 设置 series
			ChartSeries DataPre1day = new ChartSeries();
			DataPre1day.setName(tmpName3);
			if (monitorType == MonitorTypeEnum.MT_1.getType()||monitorType == MonitorTypeEnum.MT_7.getType()) {
				tmpName3 = tmpName3 + "(" + countAPre1day + ")";
				DataPre1day.setName(tmpName3);
			}
			DataPre1day.setyData(setDataAPre1day);
			charUI.getSeriesList().add(DataPre1day);

			// 设置 legend
			charUI.getLegends().getLegend().add(tmpName3);
			
			/****   2天前数据    ****/
			String tmpName2 = DateUtil.getLast2Date(monitorDate)+ " "+UnitEnum.alink_prefix +  titleName;
			if(businessId.equals(BusinessTypeEnum.BT_8.getTypeStr())
					|| businessId.equals(BusinessTypeEnum.BT_9.getTypeStr())
					|| businessId.equals(BusinessTypeEnum.BT_11.getTypeStr())){
				tmpName2= DateUtil.getLast2Date(monitorDate)+ " "+titleName;
			}
			// 设置 series
			ChartSeries DataPre2day = new ChartSeries();
			DataPre2day.setName(tmpName2);
			if (monitorType == MonitorTypeEnum.MT_1.getType()||monitorType == MonitorTypeEnum.MT_7.getType()) {
				tmpName2 = tmpName2 + "(" + countAPre2day + ")";
				DataPre2day.setName(tmpName2);
			}
			DataPre2day.setyData(setDataAPre2day);
			charUI.getSeriesList().add(DataPre2day);

			// 设置 legend
			charUI.getLegends().getLegend().add(tmpName2);
		}

		// 处理B路 || 短信(老平台)
		if (businessId.equals(BusinessTypeEnum.BT_1.getTypeStr())
				|| businessId.equals(BusinessTypeEnum.BT_2.getTypeStr())
				|| businessId.equals(BusinessTypeEnum.BT_3.getTypeStr())
				|| businessId.equals(BusinessTypeEnum.BT_4.getTypeStr())
				|| businessId.equals(BusinessTypeEnum.BT_5.getTypeStr())
				|| businessId.equals(BusinessTypeEnum.BT_80.getTypeStr())) {

			String tmpName = (!existALin) ? monitorDate+ " "+titleName : (monitorDate+ " "+UnitEnum.blink_prefix + titleName);

			// 设置 series
			ChartSeries seriesB = new ChartSeries();
			seriesB.setName(tmpName);
			if (monitorType == MonitorTypeEnum.MT_1.getType()) {
				tmpName = tmpName + "(" + countB + ")";
				seriesB.setName(tmpName);
			}
			seriesB.setyData(setDataB);
			charUI.getSeriesList().add(seriesB);

			// 设置 legend
			charUI.getLegends().getLegend().add(tmpName);
			
			/****  1天前的数据  ** **/
			String tmpName2 = (!existALin) ? DateUtil.getLastDate(monitorDate)+ " " +titleName :DateUtil.getLastDate(monitorDate)+ " "+UnitEnum.blink_prefix +   titleName;
			// 设置 series
			ChartSeries DataBPre1day = new ChartSeries();
			DataBPre1day.setName(tmpName2);
			if (monitorType == MonitorTypeEnum.MT_1.getType()||monitorType == MonitorTypeEnum.MT_7.getType()) {
				tmpName2 = tmpName2 + "(" + countBPre1day + ")";
				DataBPre1day.setName(tmpName2);
			}
			DataBPre1day.setyData(setDataBPre1day);
			charUI.getSeriesList().add(DataBPre1day);

			// 设置 legend
			charUI.getLegends().getLegend().add(tmpName2);
			
			/****   2天前数据    ****/
			String tmpName3 =(!existALin) ? DateUtil.getLast2Date(monitorDate)+ " "+ titleName :  DateUtil.getLast2Date(monitorDate) + " "+ UnitEnum.blink_prefix +titleName;
			// 设置 series
			ChartSeries DataBPre2day = new ChartSeries();
			DataBPre2day.setName(tmpName3);
			if (monitorType == MonitorTypeEnum.MT_1.getType()||monitorType == MonitorTypeEnum.MT_7.getType()) {
				tmpName3 = tmpName3 + "(" + countBPre2day + ")";
				DataBPre2day.setName(tmpName3);
			}
			DataBPre2day.setyData(setDataBPre2day);
			charUI.getSeriesList().add(DataBPre2day);

			// 设置 legend
			charUI.getLegends().getLegend().add(tmpName3);
		}

		
		// 设置左侧单位
		charUI.getAxis().setyAxisUnit(yUnit);
	}

	/**
	 * 扩展Map数据 (保证287点)
	 * 
	 * @param aStatList
	 *            查询的数据列表
	 * @return
	 */
	private Map<String, CdrMonitorStat> transformList2Map(List<CdrMonitorStat> aStatList) {

		Map<String, CdrMonitorStat> mapUtils = new ChartPonit().getDefaultMaps();
		if (aStatList != null && aStatList.size() > 0) {

			for (CdrMonitorStat stat : aStatList) {
				mapUtils.put(stat.getTimeString(), stat);
			}
		}

		return mapUtils;
	}

	/**
	 * 卡位时间段,获得上线,下线
	 * 
	 * @param currentTime
	 *            当前时间
	 * @param timeLine
	 *            时间段(如:'8:00~12:00')
	 * @return
	 */
	private boolean linkTimeLens(String currentTime, String timeLine) {

		boolean isLink = false;
		try {

			String curYMD = currentTime.split(" ")[0];// 只要年月日
			String[] timeRange = new String[2];
			if (StringUtil.notEmpty(timeLine)) {
				String[] timeArgs = timeLine.split("~");
				if (timeArgs != null && timeArgs.length == 2) {
					timeRange[0] = curYMD + " " + timeArgs[0] + ":00"; // 拼接成标准时间格式
					timeRange[1] = curYMD + " " + timeArgs[1] + ":59";
				}
			}

			if (DateUtil.formatDateLong(timeRange[0]) <= DateUtil.formatDateLong(currentTime)
					&& DateUtil.formatDateLong(currentTime) < DateUtil.formatDateLong(timeRange[1])) {
				isLink = true;
			}

		} catch (Exception e) {

			logger.error("【客户话单统计报表业务】折线图时间转换异常,error=" + e.getMessage());
		}

		return isLink;
	}

}
