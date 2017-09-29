package com.zy.cms.service.manager.impl.excel;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
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
import com.zy.cms.service.manager.SmsDailyRevenueStatisticsService;
import com.zy.cms.service.manager.SmsDailyStatService;
import com.zy.cms.service.manager.excel.CdrDetailExcelService;
import com.zy.cms.service.manager.excel.ChannelSummaryExcelService;
import com.zy.cms.service.manager.excel.RevenueExcelService;
import com.zy.cms.service.master.MerchantAccountService;
import com.zy.cms.service.master.SmsChannelService;
import com.zy.cms.util.DateUtil;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.Cdr;
import com.zy.cms.vo.MerchantAccount;
import com.zy.cms.vo.SmsDailyRevenueStatistics;
import com.zy.cms.vo.WebResult;
import com.zy.cms.vo.channel.SmsChannel;
import com.zy.cms.vo.manager.ChannelSummaryResult;
import com.zy.cms.vo.manager.RevenueResult;
import com.zy.cms.vo.manager.SmsDailyStatistics;
import com.zy.cms.vo.query.ChannelSummaryQuery;
import com.zy.cms.vo.query.SmsDailyRevenueStatisticsQuery;
import com.zy.cms.vo.query.VoiceQuery;

/**
 * 导出短信通道跑量业务
 * 
 * @author JasonXu
 * @date 2017-03-24
 *
 */
@Service("revenueExcelService")
public class RevenueExcelServiceImpl implements RevenueExcelService {

	private static final ZyLogger logger = ZyLogger.getLogger(RevenueExcelServiceImpl.class);

	@Resource
	private  CommonService commonService;
	
	@Resource
	private SmsDailyStatService smsDailyStatService;
	
	@Resource
	SmsDailyRevenueStatisticsService smsDailyRevenueStatisticsService;
	

	@Autowired
	MerchantAccountService merchantAccountService;
	
	@Resource
	private SmsChannelService smsChannelService;

	private String export_Tmp_Path ="/tmp";

	/**
	 * 导出短信通道跑量
	 */
	@Override
	public WebResult exportAccountRevenueExcel(SmsDailyRevenueStatisticsQuery query, String realPath, String webUrl) {

		// 定义操作结果
		WebResult webRs = new WebResult();

		try {

			String sheetName = "客户营收分析"; // sheet名称
			String fileName = export_Tmp_Path + "/cdr/accountRevenue_" + DateUtil.getVDateTime() + ".xlsx"; // 输出文件路径
			String savePath = (realPath + fileName).replace("\\", "/");
			String downUrl = webUrl + fileName;
			webRs.setData(fileName.replace("\\", "/"));
			logger.info("【客户营收分析导出】params={savePath:" + savePath + ",downUrl=" + downUrl + "}");

			// 获得excel头,内容
			List<Map<String, Object>> headInfoList = initAccountExcelHead();
			List<Map<String, Object>> dataList = initAccountExcelBody(query);
			if (dataList.size() > 200000) {
				logger.info("【客户营收分析导出】导出失败，数据超过20万行.");
				webRs.setCode(-1);
				webRs.setMsg("导出失败，数据超过20万行");
				return webRs;
			}

			// 执行创建excel业务,填充数据
			ExcelPOIUtil.exportExcel2FilePath(savePath, sheetName, headInfoList, dataList);

		} catch (Exception e) {

			logger.error("【客户营收分析导出】失败 error=" + e.getMessage());
			webRs.setCode(-1);
			webRs.setMsg("导出数据失败.");
		}

		logger.info("【客户营收分析导出】result=" + JsonUtil.toJsonString(webRs));
		return webRs;
	}

	
	@Override
	public WebResult exportChannelRevenueExcel(SmsDailyRevenueStatisticsQuery query, String realPath, String webUrl) {
		// 定义操作结果
		WebResult webRs = new WebResult();

		try {

			String sheetName = "通道营收分析"; // sheet名称
			String fileName = export_Tmp_Path + "/cdr/channelRevenue_" + DateUtil.getVDateTime() + ".xlsx"; // 输出文件路径
			String savePath = (realPath + fileName).replace("\\", "/");
			String downUrl = webUrl + fileName;
			webRs.setData(fileName.replace("\\", "/"));
			logger.info("【通道营收分析导出】params={savePath:" + savePath + ",downUrl=" + downUrl + "}");

			// 获得excel头,内容
			List<Map<String, Object>> headInfoList = initChannelExcelHead();
			List<Map<String, Object>> dataList = initChannelExcelBody(query);
			if (dataList.size() > 200000) {
				logger.info("【通道营收分析导出】导出失败，数据超过20万行.");
				webRs.setCode(-1);
				webRs.setMsg("导出失败，数据超过20万行");
				return webRs;
			}

			// 执行创建excel业务,填充数据
			ExcelPOIUtil.exportExcel2FilePath(savePath, sheetName, headInfoList, dataList);

		} catch (Exception e) {

			logger.error("【通道营收分析导出】失败 error=" + e.getMessage());
			webRs.setCode(-1);
			webRs.setMsg("导出数据失败.");
		}

		logger.info("【通道营收分析导出】result=" + JsonUtil.toJsonString(webRs));
		return webRs;
	}
	
	
	@Override
	public WebResult exportRevenueExcel(SmsDailyRevenueStatisticsQuery query, String realPath, String webUrl) {
		// 定义操作结果
		WebResult webRs = new WebResult();

		try {

			String sheetName = "营收汇总分析"; // sheet名称
			String fileName = export_Tmp_Path + "/cdr/summaryRevenue_" + DateUtil.getVDateTime() + ".xlsx"; // 输出文件路径
			String savePath = (realPath + fileName).replace("\\", "/");
			String downUrl = webUrl + fileName;
			webRs.setData(fileName.replace("\\", "/"));
			logger.info("【通道营收分析导出】params={savePath:" + savePath + ",downUrl=" + downUrl + "}");

			// 获得excel头,内容
			List<Map<String, Object>> headInfoList = initExcelHead();
			List<Map<String, Object>> dataList = initExcelBody(query);
			if (dataList.size() > 200000) {
				logger.info("【通道营收分析导出】导出失败，数据超过20万行.");
				webRs.setCode(-1);
				webRs.setMsg("导出失败，数据超过20万行");
				return webRs;
			}

			// 执行创建excel业务,填充数据
			ExcelPOIUtil.exportExcel2FilePath(savePath, sheetName, headInfoList, dataList);

		} catch (Exception e) {

			logger.error("【通道营收分析导出】失败 error=" + e.getMessage());
			webRs.setCode(-1);
			webRs.setMsg("导出数据失败.");
		}

		logger.info("【通道营收分析导出】result=" + JsonUtil.toJsonString(webRs));
		return webRs;
	}
	
	
	/**
	 * 构建短信通道跑量导出表头
	 * 
	 * @return 返回表头列表
	 */
	private List<Map<String, Object>> initAccountExcelHead() {

		List<Map<String, Object>> headInfoList = new ArrayList<Map<String, Object>>();
		Map<String, Object> itemMap = new HashMap<String, Object>();
		
		itemMap.put("title", "客户名称");
		itemMap.put("columnWidth", 50);
		itemMap.put("dataKey", "XH1");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "客户账号");
		itemMap.put("columnWidth", 35);
		itemMap.put("dataKey", "XH2");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "日期");
		itemMap.put("columnWidth", 45);
		itemMap.put("dataKey", "XH3");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "类型");
		itemMap.put("columnWidth", 35);
		itemMap.put("dataKey", "XH4");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "计费条数");
		itemMap.put("columnWidth", 35);
		itemMap.put("dataKey", "XH5");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "收入(元)");
		itemMap.put("columnWidth", 35);
		itemMap.put("dataKey", "XH6");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "成本(元)");
		itemMap.put("columnWidth", 25);
		itemMap.put("dataKey", "XH7");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "利润(元)");
		itemMap.put("columnWidth", 40);
		itemMap.put("dataKey", "XH8");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "成本均价(元)");
		itemMap.put("columnWidth", 25);
		itemMap.put("dataKey", "XH9");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "毛利率");
		itemMap.put("columnWidth", 25);
		itemMap.put("dataKey", "XH10");
		headInfoList.add(itemMap);
	
		return headInfoList;
	}
	
	/**
	 * 构建短信通道跑量导出表头
	 * 
	 * @return 返回表头列表
	 */
	private List<Map<String, Object>> initChannelExcelHead() {

		List<Map<String, Object>> headInfoList = new ArrayList<Map<String, Object>>();
		Map<String, Object> itemMap = new HashMap<String, Object>();
		
		itemMap.put("title", "通道名称");
		itemMap.put("columnWidth", 50);
		itemMap.put("dataKey", "XH1");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "通道编号");
		itemMap.put("columnWidth", 35);
		itemMap.put("dataKey", "XH2");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "通道ID");
		itemMap.put("columnWidth", 45);
		itemMap.put("dataKey", "XH3");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "日期");
		itemMap.put("columnWidth", 35);
		itemMap.put("dataKey", "XH4");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "计费条数");
		itemMap.put("columnWidth", 35);
		itemMap.put("dataKey", "XH5");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "收入(元)");
		itemMap.put("columnWidth", 35);
		itemMap.put("dataKey", "XH6");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "成本(元)");
		itemMap.put("columnWidth", 25);
		itemMap.put("dataKey", "XH7");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "利润(元)");
		itemMap.put("columnWidth", 40);
		itemMap.put("dataKey", "XH8");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "成本均价(元)");
		itemMap.put("columnWidth", 25);
		itemMap.put("dataKey", "XH9");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "毛利率");
		itemMap.put("columnWidth", 25);
		itemMap.put("dataKey", "XH10");
		headInfoList.add(itemMap);
	
		return headInfoList;
	}
	
	
	/**
	 * 构建营收汇总导出表头
	 * 
	 * @return 返回表头列表
	 */
	private List<Map<String, Object>> initExcelHead() {

		List<Map<String, Object>> headInfoList = new ArrayList<Map<String, Object>>();
		Map<String, Object> itemMap = new HashMap<String, Object>();

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "日期");
		itemMap.put("columnWidth", 35);
		itemMap.put("dataKey", "XH1");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "计费条数");
		itemMap.put("columnWidth", 35);
		itemMap.put("dataKey", "XH2");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "收入(元)");
		itemMap.put("columnWidth", 35);
		itemMap.put("dataKey", "XH3");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "成本(元)");
		itemMap.put("columnWidth", 25);
		itemMap.put("dataKey", "XH4");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "利润(元)");
		itemMap.put("columnWidth", 40);
		itemMap.put("dataKey", "XH5");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "成本均价(元)");
		itemMap.put("columnWidth", 25);
		itemMap.put("dataKey", "XH6");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "毛利率");
		itemMap.put("columnWidth", 25);
		itemMap.put("dataKey", "XH7");
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
	private List<Map<String, Object>> initAccountExcelBody(SmsDailyRevenueStatisticsQuery query) {

		logger.info("【短信通道跑量导出】查询参数 query=" + JsonUtil.toJsonString(query));
		// 定义填充数据列表
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		
		try {
			RevenueResult revenueResult =smsDailyRevenueStatisticsService.getRevenueResult(query);
			List<SmsDailyRevenueStatistics> list = smsDailyRevenueStatisticsService.listAccountRevenue(query);
			
			if (list != null && list.size() > 0) {
				Map<String, Object> dataItem = null;
				for (SmsDailyRevenueStatistics smsDailyRevenueStatistics : list) {
					dataItem = new HashMap<String, Object>();
					
					MerchantAccount merchantAccount=merchantAccountService.getMerchantAccount(smsDailyRevenueStatistics.getApiAccount());
					String businessName="";
					String merchantPhone="";
					if(merchantAccount!=null){
						businessName=merchantAccount.getBusinessName();
						merchantPhone=merchantAccount.getMerchantPhone();
					}
					
					String smsCategory="";
					if(smsDailyRevenueStatistics.getSmsCategory().equals("8")){
						smsCategory="通知";
					}else if(smsDailyRevenueStatistics.getSmsCategory().equals("9")){
						smsCategory="验证码";
					}else{
						smsCategory="营销";
					}
					
					dataItem.put("XH1",businessName);//客户名称 
					dataItem.put("XH2",merchantPhone);//客户账号
					dataItem.put("XH3",smsDailyRevenueStatistics.getDateTime());//日期
					dataItem.put("XH4",smsCategory);//短信类型
					dataItem.put("XH5",smsDailyRevenueStatistics.getFeeCount() );//计费条数
					dataItem.put("XH6",doubleToRate(smsDailyRevenueStatistics.getInCome()) );//收入(元)
					dataItem.put("XH7",doubleToRate(smsDailyRevenueStatistics.getInCome()));//成本(元)
					dataItem.put("XH8",doubleToRate(smsDailyRevenueStatistics.getInCome()-smsDailyRevenueStatistics.getCostFee()) );//利润(元)
					if(smsDailyRevenueStatistics.getFeeCount()==0){
						dataItem.put("XH9","");
					}else{
						dataItem.put("XH9",doubleToRate2(smsDailyRevenueStatistics.getCostFee()/smsDailyRevenueStatistics.getFeeCount()) );//成本均价(元)
					}
					if(smsDailyRevenueStatistics.getInCome()==0){
						dataItem.put("XH10","");
					}else{
						dataItem.put("XH10",doubleToRate3((smsDailyRevenueStatistics.getInCome()-smsDailyRevenueStatistics.getCostFee())/smsDailyRevenueStatistics.getInCome()));//毛利率
					}
					dataList.add(dataItem);
				}
			}
			
			if(revenueResult!=null){
				Map<String, Object> dataItem = new HashMap<String, Object>();
				dataItem.put("XH1","汇总");//日期  客户名称 
				dataItem.put("XH2","");//客户账号
				dataItem.put("XH3","");//日期
				dataItem.put("XH4","");//短信类型
				dataItem.put("XH5",revenueResult.getFeeCount() );//计费条数
				dataItem.put("XH6",doubleToRate(revenueResult.getInCome()) );//收入(元)
				dataItem.put("XH7",doubleToRate(revenueResult.getCostFee()) );//成本(元)
				dataItem.put("XH8",doubleToRate(revenueResult.getInCome()-revenueResult.getCostFee())  );//利润(元)
				if(revenueResult.getFeeCount()==0){
					dataItem.put("XH9","");
				}else{
					dataItem.put("XH9",doubleToRate2(revenueResult.getCostFee()/revenueResult.getFeeCount()));//成本均价(元)
				}
				if(revenueResult.getInCome()==0){
					dataItem.put("XH10","");
				}else{
					dataItem.put("XH10",doubleToRate3((revenueResult.getInCome()-revenueResult.getCostFee())/revenueResult.getInCome()) );//毛利率
				}
				dataList.add(dataItem);
			}
		} catch (Exception e) {
			logger.info("【短信通道跑量导出】出现异常,异常是:" + e.getMessage());
		}
		return dataList;
	}


	/**
	 * 构建短信通道跑量导出数据列表
	 * 
	 * @query 导出业务查询条件
	 * 
	 * @return
	 */
	private List<Map<String, Object>> initChannelExcelBody(SmsDailyRevenueStatisticsQuery query) {
	
		logger.info("【短信通道跑量导出】查询参数 query=" + JsonUtil.toJsonString(query));
		// 定义填充数据列表
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		
		try {
			RevenueResult revenueResult =smsDailyRevenueStatisticsService.getRevenueResult(query);
			List<SmsDailyRevenueStatistics> list = smsDailyRevenueStatisticsService.listChannelRevenue(query);
			
			if (list != null && list.size() > 0) {
				Map<String, Object> dataItem = null;
				for (SmsDailyRevenueStatistics smsDailyRevenueStatistics : list) {
					dataItem = new HashMap<String, Object>();
					
					SmsChannel smsChannel=smsChannelService.selectByPrimaryKey(smsDailyRevenueStatistics.getChannelId());
					String channelName="";
					String mainCode="";
					if(smsChannel!=null){
						mainCode=smsChannel.getChannelMainCode();
						channelName=smsChannel.getChannelName();
					}
					
					dataItem.put("XH1",channelName );//通道名称 
					dataItem.put("XH2",mainCode);//通道编号
					dataItem.put("XH3",smsDailyRevenueStatistics.getChannelId() );//通道ID
					dataItem.put("XH4",smsDailyRevenueStatistics.getDateTime() );//日期
					dataItem.put("XH5",smsDailyRevenueStatistics.getFeeCount() );//计费条数
					dataItem.put("XH6",doubleToRate(smsDailyRevenueStatistics.getInCome()) );//收入(元)
					dataItem.put("XH7",doubleToRate(smsDailyRevenueStatistics.getCostFee()) );//成本(元)
					dataItem.put("XH8",doubleToRate(smsDailyRevenueStatistics.getInCome()-smsDailyRevenueStatistics.getCostFee()));//利润(元)
					if(revenueResult.getFeeCount()==0){
						dataItem.put("XH9","");
					}else{
						dataItem.put("XH9",doubleToRate2(smsDailyRevenueStatistics.getCostFee()/smsDailyRevenueStatistics.getFeeCount()));//成本均价(元)
					}
					if(revenueResult.getInCome()==0){
						dataItem.put("XH10","");
					}else{
						dataItem.put("XH10",doubleToRate3((smsDailyRevenueStatistics.getInCome()-smsDailyRevenueStatistics.getCostFee())/smsDailyRevenueStatistics.getInCome()));//毛利率
					}
					
					dataList.add(dataItem);
				}
			}
			
			if(revenueResult!=null){
				Map<String, Object> dataItem = new HashMap<String, Object>();
				dataItem.put("XH1","汇总");//日期
				dataItem.put("XH2","");//通道名称 
				dataItem.put("XH3","");//通道编号
				dataItem.put("XH4","");//通道ID
				dataItem.put("XH5",revenueResult.getFeeCount() );//计费条数
				dataItem.put("XH6",revenueResult.getInCome() );//收入(元)
				dataItem.put("XH7",doubleToRate(revenueResult.getCostFee()) );//成本(元)
				dataItem.put("XH8",doubleToRate(revenueResult.getInCome()-revenueResult.getCostFee())  );//利润(元)
				if(revenueResult.getFeeCount()==0){
					dataItem.put("XH9","");//成本均价(元)
				}else{
					dataItem.put("XH9",doubleToRate2(revenueResult.getCostFee()/revenueResult.getFeeCount()));//成本均价(元)
				}
				if(revenueResult.getInCome()==0){
					dataItem.put("XH10","");
				}else{
					dataItem.put("XH10",doubleToRate3((revenueResult.getInCome()-revenueResult.getCostFee())/revenueResult.getInCome()) );//毛利率
				}

				dataList.add(dataItem);
			}
		} catch (Exception e) {
			logger.info("【短信通道跑量导出】出现异常,异常是:" + e.getMessage());
		}
		return dataList;
	}

	
	/**
	 * 构建营收汇总导出数据列表
	 * 
	 * @query 导出业务查询条件
	 * 
	 * @return
	 */
	private List<Map<String, Object>> initExcelBody(SmsDailyRevenueStatisticsQuery query) {
	
		logger.info("【营收汇总导出】查询参数 query=" + JsonUtil.toJsonString(query));
		// 定义填充数据列表
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		
		try {
			RevenueResult revenueResult =smsDailyRevenueStatisticsService.getRevenueResult(query);
			List<SmsDailyRevenueStatistics> list = smsDailyRevenueStatisticsService.listSummaryRevenue(query);
			
			if (list != null && list.size() > 0) {
				Map<String, Object> dataItem = null;
				for (SmsDailyRevenueStatistics smsDailyRevenueStatistics : list) {
					dataItem = new HashMap<String, Object>();
		
					dataItem.put("XH1",smsDailyRevenueStatistics.getDateTime() );//日期
					dataItem.put("XH2",smsDailyRevenueStatistics.getFeeCount() );//计费条数
					dataItem.put("XH3",doubleToRate(smsDailyRevenueStatistics.getInCome()) );//收入(元)
					dataItem.put("XH4",doubleToRate(smsDailyRevenueStatistics.getCostFee()) );//成本(元)
					dataItem.put("XH5",doubleToRate(smsDailyRevenueStatistics.getInCome()-smsDailyRevenueStatistics.getCostFee()));//利润(元)
					if(revenueResult.getFeeCount()==0){
						dataItem.put("XH6","");
					}else{
						dataItem.put("XH6",doubleToRate2(smsDailyRevenueStatistics.getCostFee()/smsDailyRevenueStatistics.getFeeCount()));//成本均价(元)
					}
					if(revenueResult.getInCome()==0){
						dataItem.put("XH7","");
					}else{
						dataItem.put("XH7",doubleToRate3((smsDailyRevenueStatistics.getInCome()-smsDailyRevenueStatistics.getCostFee())/smsDailyRevenueStatistics.getInCome()));//毛利率
					}
					
					dataList.add(dataItem);
				}
			}
			
			if(revenueResult!=null){
				Map<String, Object> dataItem = new HashMap<String, Object>();
				dataItem.put("XH1","汇总");//日期
				dataItem.put("XH2",revenueResult.getFeeCount() );//计费条数
				dataItem.put("XH3",revenueResult.getInCome() );//收入(元)
				dataItem.put("XH4",doubleToRate(revenueResult.getCostFee()) );//成本(元)
				dataItem.put("XH5",doubleToRate(revenueResult.getInCome()-revenueResult.getCostFee())  );//利润(元)
				if(revenueResult.getFeeCount()==0){
					dataItem.put("XH6","");//成本均价(元)
				}else{
					dataItem.put("XH6",doubleToRate2(revenueResult.getCostFee()/revenueResult.getFeeCount()));//成本均价(元)
				}
				if(revenueResult.getInCome()==0){
					dataItem.put("XH7","");
				}else{
					dataItem.put("XH7",doubleToRate3((revenueResult.getInCome()-revenueResult.getCostFee())/revenueResult.getInCome()) );//毛利率
				}

				dataList.add(dataItem);
			}
		} catch (Exception e) {
			logger.info("【营收汇总导出】出现异常,异常是:" + e.getMessage());
		}
		return dataList;
	}

	
	
	public String doubleToRate(Double rate) {
	     /*BigDecimal decimal = new BigDecimal(rate);
	     Double rateDouble = decimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();*/
		 DecimalFormat df = new DecimalFormat("0.00");
	     return df.format(rate)+"";
	}
	
	public String doubleToRate2(Double rate) {
	     /*BigDecimal decimal = new BigDecimal(rate);
	     Double rateDouble = decimal.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();*/
	     DecimalFormat df = new DecimalFormat("0.0000");
	     return df.format(rate)+"";
	}
	
	public String doubleToRate3(Double rate) {
	     /*BigDecimal decimal = new BigDecimal(rate*100);
	     Double rateDouble = decimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();*/
		 DecimalFormat df = new DecimalFormat("0.00");
	     return df.format(rate*100)+"%";
	}
	
	public static void main(String[] args) {
		DecimalFormat df = new DecimalFormat("0.0000");
		System.out.println(df.format(0.200094));
	}
}