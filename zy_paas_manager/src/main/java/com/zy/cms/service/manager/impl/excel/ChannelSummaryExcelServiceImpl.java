package com.zy.cms.service.manager.impl.excel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zy.cms.common.CommonService;
import com.zy.cms.common.Constant;
import com.zy.cms.common.ExcelPOIUtil;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.elastic.service.EsCdrExportService;
import com.zy.cms.enums.BusinessTypeEnum;
import com.zy.cms.enums.HangupCodeEnum;
import com.zy.cms.enums.StateTypeEnum;
import com.zy.cms.service.manager.SmsDailyStatService;
import com.zy.cms.service.manager.excel.CdrDetailExcelService;
import com.zy.cms.service.manager.excel.ChannelSummaryExcelService;
import com.zy.cms.service.master.MerchantAccountService;
import com.zy.cms.service.master.SmsChannelService;
import com.zy.cms.util.DateUtil;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.Cdr;
import com.zy.cms.vo.MerchantAccount;
import com.zy.cms.vo.WebResult;
import com.zy.cms.vo.channel.SmsChannel;
import com.zy.cms.vo.manager.ChannelSummaryResult;
import com.zy.cms.vo.manager.SmsDailyStatistics;
import com.zy.cms.vo.query.ChannelSummaryQuery;
import com.zy.cms.vo.query.VoiceQuery;

/**
 * 导出短信通道跑量业务
 * 
 * @author JasonXu
 * @date 2017-03-24
 *
 */
@Service("channelSummaryExcelService")
public class ChannelSummaryExcelServiceImpl implements ChannelSummaryExcelService {

	private static final ZyLogger logger = ZyLogger.getLogger(ChannelSummaryExcelServiceImpl.class);

	@Resource
	private  CommonService commonService;
	
	@Resource
	private SmsDailyStatService smsDailyStatService;
	
	@Resource
	private SmsChannelService smsChannelService;

	private String export_Tmp_Path ="/tmp";

	/**
	 * 导出短信通道跑量
	 */
	@Override
	public WebResult exportChannelExcel(ChannelSummaryQuery query, String realPath, String webUrl) {

		// 定义操作结果
		WebResult webRs = new WebResult();

		try {

			String sheetName = "短信通道跑量"; // sheet名称
			String fileName = export_Tmp_Path + "/cdr/channelSummary_" + DateUtil.getVDateTime() + ".xlsx"; // 输出文件路径
			String savePath = (realPath + fileName).replace("\\", "/");
			String downUrl = webUrl + fileName;
			webRs.setData(fileName.replace("\\", "/"));
			logger.info("【短信通道跑量导出】params={savePath:" + savePath + ",downUrl=" + downUrl + "}");

			// 获得excel头,内容
			List<Map<String, Object>> headInfoList = initExcelHead();
			List<Map<String, Object>> dataList = initExcelBody(query);
			if (dataList.size() > 200000) {
				logger.info("【短信通道跑量导出】导出失败，数据超过20万行.");
				webRs.setCode(-1);
				webRs.setMsg("导出失败，数据超过20万行");
				return webRs;
			}

			// 执行创建excel业务,填充数据
			ExcelPOIUtil.exportExcel2FilePath(savePath, sheetName, headInfoList, dataList);

		} catch (Exception e) {

			logger.error("【短信通道跑量导出】失败 error=" + e.getMessage());
			webRs.setCode(-1);
			webRs.setMsg("导出数据失败.");
		}

		logger.info("【短信通道跑量导出】result=" + JsonUtil.toJsonString(webRs));
		return webRs;
	}

	/**
	 * 构建短信通道跑量导出表头
	 * 
	 * @return 返回表头列表
	 */
	private List<Map<String, Object>> initExcelHead() {

		List<Map<String, Object>> headInfoList = new ArrayList<Map<String, Object>>();
		Map<String, Object> itemMap = new HashMap<String, Object>();
		
		itemMap.put("title", "日期");
		itemMap.put("columnWidth", 50);
		itemMap.put("dataKey", "XH1");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "通道编号");
		itemMap.put("columnWidth", 35);
		itemMap.put("dataKey", "XH2");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "通道名称");
		itemMap.put("columnWidth", 45);
		itemMap.put("dataKey", "XH3");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "通道ID");
		itemMap.put("columnWidth", 35);
		itemMap.put("dataKey", "XH4");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "类型");
		itemMap.put("columnWidth", 35);
		itemMap.put("dataKey", "XH5");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "发送条数");
		itemMap.put("columnWidth", 35);
		itemMap.put("dataKey", "XH6");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "成功条数");
		itemMap.put("columnWidth", 25);
		itemMap.put("dataKey", "XH7");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "成功率");
		itemMap.put("columnWidth", 40);
		itemMap.put("dataKey", "XH8");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "计费条数");
		itemMap.put("columnWidth", 25);
		itemMap.put("dataKey", "XH9");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "失败条数");
		itemMap.put("columnWidth", 25);
		itemMap.put("dataKey", "XH10");
		headInfoList.add(itemMap);
		
		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "失败率");
		itemMap.put("columnWidth", 25);
		itemMap.put("dataKey", "XH11");
		headInfoList.add(itemMap);
		
		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "未知状态数");
		itemMap.put("columnWidth", 25);
		itemMap.put("dataKey", "XH12");
		headInfoList.add(itemMap);
		
		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "未知状态比例");
		itemMap.put("columnWidth", 25);
		itemMap.put("dataKey", "XH13");
		headInfoList.add(itemMap);
		
		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "平均发送时长");
		itemMap.put("columnWidth", 25);
		itemMap.put("dataKey", "XH14");
		headInfoList.add(itemMap);
		
		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "平均状态报告接收时长");
		itemMap.put("columnWidth", 45);
		itemMap.put("dataKey", "XH15");
		headInfoList.add(itemMap);
		
		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "10秒内到达率");
		itemMap.put("columnWidth", 32);
		itemMap.put("dataKey", "XH16");
		headInfoList.add(itemMap);
		
		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "50秒内到达率");
		itemMap.put("columnWidth", 32);
		itemMap.put("dataKey", "XH17");
		headInfoList.add(itemMap);
		
		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "超过50秒到达率");
		itemMap.put("columnWidth", 32);
		itemMap.put("dataKey", "XH18");
		headInfoList.add(itemMap);
		
		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "回执24小时到达率");
		itemMap.put("columnWidth", 40);
		itemMap.put("dataKey", "XH19");
		headInfoList.add(itemMap);
		
		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "回执48小时到达率");
		itemMap.put("columnWidth", 40);
		itemMap.put("dataKey", "XH20");
		headInfoList.add(itemMap);
		
		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "回执72小时到达率");
		itemMap.put("columnWidth", 40);
		itemMap.put("dataKey", "XH21");
		headInfoList.add(itemMap);
	
		return headInfoList;
	}

	/**
	 * 构建短信通道跑量导出数据列表
	 * 
	 * @query 导出业务查询条件
	 * 
	 * @return
	 */
	private List<Map<String, Object>> initExcelBody(ChannelSummaryQuery query) {

		logger.info("【短信通道跑量导出】查询参数 query=" + JsonUtil.toJsonString(query));
		// 定义填充数据列表
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		
		try {
			ChannelSummaryResult cdrStatisticsResult =smsDailyStatService.getChannelSummaryResult(query);
			List<SmsDailyStatistics> list = smsDailyStatService.getChannelSummarysByQuery(query);
			if (list != null && list.size() > 0) {
				Map<String, Object> dataItem = null;
				for (SmsDailyStatistics smsDailyStatistics : list) {
					dataItem = new HashMap<String, Object>();
					
					SmsChannel smsChannel=smsChannelService.selectByPrimaryKey(smsDailyStatistics.getChannelSmsId());
					if(smsChannel!=null){
						smsDailyStatistics.setChannelMainCode(smsChannel.getChannelMainCode());
						smsDailyStatistics.setChannelName(smsChannel.getChannelName());
						smsDailyStatistics.setChannelType(smsChannel.getChannelType());
					}
					
					dataItem.put("XH1",smsDailyStatistics.getDateTime() );//日期
					dataItem.put("XH2",smsDailyStatistics.getChannelMainCode());//通道编号
					
					String channelType="";
					if(smsDailyStatistics.getChannelType().equals("1")){
						channelType="通知";
					}else if(smsDailyStatistics.getChannelType().equals("2")){
						channelType="验证码";
					}else if(smsDailyStatistics.getChannelType().equals("3")){
						channelType="营销";
					}else if(smsDailyStatistics.getChannelType().equals("4")){
						channelType="通知、验证码";
					}else{
						channelType="通知、验证码、营销";
					}
					
					dataItem.put("XH3",smsDailyStatistics.getChannelName() );//通道名称
					dataItem.put("XH4",smsDailyStatistics.getChannelSmsId() );//通道ID
					dataItem.put("XH5",channelType );//通道类型
					dataItem.put("XH6",smsDailyStatistics.getSendCount() );//发送条数
					dataItem.put("XH7",smsDailyStatistics.getSuccessCount() );//成功条数
					dataItem.put("XH8",commonService.doubleToRate(smsDailyStatistics.getSuccessSendRate()));//成功率
					dataItem.put("XH9",smsDailyStatistics.getFeeCount() );//计费条数
					dataItem.put("XH10",smsDailyStatistics.getFailedCount());//失败条数
					dataItem.put("XH11",commonService.doubleToRate(smsDailyStatistics.getFailSendRate()));//失败率
					dataItem.put("XH12",smsDailyStatistics.getNoreportCount() );//未知状态数
					dataItem.put("XH13",commonService.doubleToRate(smsDailyStatistics.getNoreportRate()));//未知状态比例
					dataItem.put("XH14",smsDailyStatistics.getAvgSendTime() );//平均发送时长
					dataItem.put("XH15",smsDailyStatistics.getAvgStatusTime() );//平均状态报告接收时长
					dataItem.put("XH16",commonService.doubleToRate(smsDailyStatistics.getSdUs10sCountRate()));//10秒内到达率
					dataItem.put("XH17",commonService.doubleToRate(smsDailyStatistics.getSdUs50sCountRate()));//50秒内到达率
					dataItem.put("XH18",commonService.doubleToRate(smsDailyStatistics.getSdUsgt50sCountRate()));//超过50秒到达率
					dataItem.put("XH19",commonService.doubleToRate(smsDailyStatistics.getStBk24hCountRate()));//回执24小时到达率
					dataItem.put("XH20",commonService.doubleToRate(smsDailyStatistics.getStBk48hCountRate()));//回执48小时到达率
					dataItem.put("XH21",commonService.doubleToRate(smsDailyStatistics.getStBk72hCountRate()));//回执72小时到达率
					dataList.add(dataItem);
				}
			}
			
			if(cdrStatisticsResult!=null){
				Map<String, Object> dataItem = new HashMap<String, Object>();
				dataItem.put("XH1","汇总");//日期
				dataItem.put("XH2","");//通道编号
				dataItem.put("XH3","");//通道名称
				dataItem.put("XH4","");//通道ID
				dataItem.put("XH5","");//通道类型
				dataItem.put("XH6",cdrStatisticsResult.getSendCounts() );//发送条数
				dataItem.put("XH7",cdrStatisticsResult.getSuccessCounts() );//成功条数
				dataItem.put("XH8",commonService.doubleToRate(cdrStatisticsResult.getSuccessSendRates()) );//成功率
				dataItem.put("XH9",cdrStatisticsResult.getFeeCounts() );//计费条数
				dataItem.put("XH10",cdrStatisticsResult.getFailedCounts());//失败条数
				dataItem.put("XH11",commonService.doubleToRate(cdrStatisticsResult.getFailSendRates()) );//失败率
				dataItem.put("XH12",cdrStatisticsResult.getNoreportCounts() );//未知状态数
				dataItem.put("XH13",commonService.doubleToRate(cdrStatisticsResult.getNoreportRates()) );//未知状态比例
				dataItem.put("XH14",cdrStatisticsResult.getAvgSendTimes() );//平均发送时长
				dataItem.put("XH15",cdrStatisticsResult.getAvgStatusTimes() );//平均状态报告接收时长
				dataItem.put("XH16",commonService.doubleToRate(cdrStatisticsResult.getSdUs10sCountRates()) );//10秒内到达率
				dataItem.put("XH17",commonService.doubleToRate(cdrStatisticsResult.getSdUs50sCountRates()) );//50秒内到达率
				dataItem.put("XH18",commonService.doubleToRate(cdrStatisticsResult.getSdUsgt50sCountRates()) );//超过50秒到达率
				dataItem.put("XH19",commonService.doubleToRate(cdrStatisticsResult.getStBk24hCountRates() ));//回执24小时到达率
				dataItem.put("XH20",commonService.doubleToRate(cdrStatisticsResult.getStBk48hCountRates()));//回执48小时到达率
				dataItem.put("XH21",commonService.doubleToRate(cdrStatisticsResult.getStBk72hCountRates()));//回执72小时到达率
				dataList.add(dataItem);
			}
		} catch (SQLException e) {
			logger.info("【短信通道跑量导出】出现异常,异常是:" + e.getMessage());
		}
		return dataList;
	}
}
