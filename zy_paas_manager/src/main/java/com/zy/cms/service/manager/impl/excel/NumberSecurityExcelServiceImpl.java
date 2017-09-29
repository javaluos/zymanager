package com.zy.cms.service.manager.impl.excel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zy.cms.common.Constant;
import com.zy.cms.common.ExcelPOIUtil;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.elastic.service.EsCdrExportService;
import com.zy.cms.enums.HangupCodeEnum;
import com.zy.cms.enums.StateTypeEnum;
import com.zy.cms.service.manager.CdrDailyStatisticsService;
import com.zy.cms.service.manager.excel.NumberSecurityExcelService;
import com.zy.cms.service.master.MerchantAccountService;
import com.zy.cms.util.DateUtil;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.Cdr;
import com.zy.cms.vo.MerchantAccount;
import com.zy.cms.vo.WebResult;
import com.zy.cms.vo.manager.CdrDailyStatistics;
import com.zy.cms.vo.manager.CdrStatisticsResult;
import com.zy.cms.vo.query.CdrDailyStatisticsQuery;
import com.zy.cms.vo.query.VoiceQuery;

/**
 * 导出号码卫士发送记录详单业务
 * 
 * @author allen.yuan
 * @date 2016-11-6
 *
 */
@Service("numberSecurityExcelService")
public class NumberSecurityExcelServiceImpl implements NumberSecurityExcelService {

	private static final ZyLogger logger = ZyLogger.getLogger(NumberSecurityExcelServiceImpl.class);

	@Autowired
	private EsCdrExportService esCdrExportService;

	@Autowired
	private MerchantAccountService merchantAccountService;
	
	@Autowired
	private CdrDailyStatisticsService cdrDailyStatisticsService;

	private String export_Tmp_Path ="/tmp";

	@Override
	public WebResult exportVoiceExcel(VoiceQuery query, String realPath, String webUrl) {

		// 定义操作结果
		WebResult webRs = new WebResult();

		try {

			String sheetName = "号码卫士详单"; // sheet名称
			String fileName = export_Tmp_Path + "/cdr/numbersecurity_" + DateUtil.getVDateTime() + ".xlsx"; // 输出文件路径
			String savePath = (realPath + fileName).replace("\\", "/");
			String downUrl = webUrl + fileName;
			webRs.setData(fileName.replace("\\", "/"));
			logger.info("【号码卫士话单详单导出】params={savePath:" + savePath + ",downUrl=" + downUrl + "}");

			// 获得excel头,内容
			List<Map<String, Object>> headInfoList = initExcelHead();
			List<Map<String, Object>> dataList = initExcelBody(query);
			if (dataList.size() > 200000) {
				logger.info("【号码卫士话单详单导出】导出失败，数据超过20万行.");
				webRs.setCode(-1);
				webRs.setMsg("导出失败，数据超过20万行");
				return webRs;
			}

			// 执行创建excel业务,填充数据
			ExcelPOIUtil.exportExcel2FilePath(savePath, sheetName, headInfoList, dataList);

		} catch (Exception e) {

			logger.error("【号码卫士话单导出】失败 error=" + e.getMessage());
			webRs.setCode(-1);
			webRs.setMsg("导出数据失败.");
		}

		logger.info("【号码卫士话单详单导出】result=" + JsonUtil.toJsonString(webRs));
		return webRs;
	}

	/**
	 * 构建导出表头
	 * 
	 * @return 返回表头列表
	 */
	private List<Map<String, Object>> initExcelHead() {

		List<Map<String, Object>> headInfoList = new ArrayList<Map<String, Object>>();
		Map<String, Object> itemMap = new HashMap<String, Object>();
		itemMap.put("title", "姓名/企业名称");
		itemMap.put("columnWidth", 50);
		itemMap.put("dataKey", "XH1");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "客户账号");
		itemMap.put("columnWidth", 25);
		itemMap.put("dataKey", "XH2");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "主叫");
		itemMap.put("columnWidth", 25);
		itemMap.put("dataKey", "XH3");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "中间号码");
		itemMap.put("columnWidth", 25);
		itemMap.put("dataKey", "XH4");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "主叫归属地");
		itemMap.put("columnWidth", 25);
		itemMap.put("dataKey", "XH5");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "被叫");
		itemMap.put("columnWidth", 35);
		itemMap.put("dataKey", "XH6");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "被叫手机归属地");
		itemMap.put("columnWidth", 30);
		itemMap.put("dataKey", "XH7");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "发起呼叫时间");
		itemMap.put("columnWidth", 40);
		itemMap.put("dataKey", "XH8");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "通话状态");
		itemMap.put("columnWidth", 25);
		itemMap.put("dataKey", "XH9");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "通话结束原因");
		itemMap.put("columnWidth", 25);
		itemMap.put("dataKey", "XH10");
		headInfoList.add(itemMap);
		
		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "接通时延(秒)");
		itemMap.put("columnWidth", 25);
		itemMap.put("dataKey", "XH11");
		headInfoList.add(itemMap);
		
		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "通话时长(秒)");
		itemMap.put("columnWidth", 25);
		itemMap.put("dataKey", "XH12");
		headInfoList.add(itemMap);

		return headInfoList;
	}

	/**
	 * 构建导出数据列表
	 * 
	 * @query 导出业务查询条件
	 * 
	 * @return
	 */
	private List<Map<String, Object>> initExcelBody(VoiceQuery query) {

		logger.info("【号码卫士话单详单导出】查询ES参数 query=" + JsonUtil.toJsonString(query));

		// 定义填充数据列表
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();

		// 查询客户信息
		MerchantAccount account = merchantAccountService.getMerchantAccount(query.getApiAccount());
		if (account == null || StringUtil.isEmpty(account.getApiAccount())) {
			return dataList;
		}

		List<JSONObject> cdrJsonList = queryEsCdrData(query);
		if (cdrJsonList != null && cdrJsonList.size() > 0) {
			Map<String, Object> dataItem = null;
			Cdr cdr = null;
			for (JSONObject cdrJson : cdrJsonList) {
				cdr = JsonUtil.parseToObject(cdrJson.toString(), Cdr.class);
				dataItem = new HashMap<String, Object>();
				dataItem.put("XH1", account.getBusinessName());
				dataItem.put("XH2", account.getMerchantPhone());
				dataItem.put("XH3", cdr.getCaller());
				dataItem.put("XH4", cdr.getCallee());
				dataItem.put("XH5", "");
				dataItem.put("XH6", cdr.getCallee());
				dataItem.put("XH7", "");
				dataItem.put("XH8",DateUtil.timeStamp2Date(Integer.valueOf(cdr.getCalleeInviteTime() + "")) );
				dataItem.put("XH9", StateTypeEnum.getName(cdr.getState()));
				dataItem.put("XH10", HangupCodeEnum.getName(cdr.getHangupCode()));
				long rpct = cdr.getCalleeRingingBeginTime() - cdr.getCalleeInviteTime();
				dataItem.put("XH11", rpct < 0 ? -1 : rpct);
				dataItem.put("XH12", cdr.getHoldTime());

				dataList.add(dataItem);
			}
		}

		return dataList;

	}

	/**
	 * 从es查询话单数据
	 * 
	 * @param query
	 *            查询条件
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<JSONObject> queryEsCdrData(VoiceQuery query) {

		List<JSONObject> datas = new ArrayList<JSONObject>();
		// 定义数据结果
		String result = this.esCdrExportService.searchVoiceCdr(query, Constant.ES_CDR_INDEX_NAME,Constant.ES_CDR_INDEX_TYPE);
		Map rs = (Map) JSON.parse(result);
		Object objStr = rs.get("data");
		if (objStr != null) {
			datas = JsonUtil.parseToObject(objStr.toString(), List.class);
		}
		logger.info("【号码卫士话单详单导出】查询ES数据   " + datas.size() + " 条.");
		return datas;
	}
	
	@Override
	public WebResult exportSummaryExcel(CdrDailyStatisticsQuery query, String realPath, String webUrl) {
		// 定义操作结果
		WebResult webRs = new WebResult();
		try {

			String sheetName = "号码卫士汇总"; // sheet名称
			String fileName = export_Tmp_Path + "/cdr/numsecurity_summary_" + DateUtil.getVDateTime() + ".xlsx"; // 输出文件路径
			String savePath = (realPath + fileName).replace("\\", "/");
			String downUrl = webUrl + fileName;
			webRs.setData(fileName.replace("\\", "/"));
			logger.info("【号码卫士汇总导出】params={savePath:" + savePath + ",downUrl=" + downUrl + "}");

			// 获得excel头,内容
			List<Map<String, Object>> headInfoList = initSummaryExcelHead();
			List<Map<String, Object>> dataList = initSummaryExcelBody(query);
			if (dataList.size() > 200000) {
				logger.info("【号码卫士汇总导出】导出失败，数据超过20万行.");
				webRs.setCode(-1);
				webRs.setMsg("号码卫士汇总导出失败，数据超过20万行");
				return webRs;
			}

			// 执行创建excel业务,填充数据
			ExcelPOIUtil.exportExcel2FilePath(savePath, sheetName, headInfoList, dataList);

		} catch (Exception e) {

			logger.error("【号码卫士汇总导出】失败 error=" + e.getMessage());
			webRs.setCode(-1);
			webRs.setMsg("号码卫士汇总导出数据失败.");
		}

		logger.info("【号码卫士汇总导出】result=" + JsonUtil.toJsonString(webRs));
		return webRs;
	}
	
	/**
	 * 构建导出表头
	 * 
	 * @return 返回表头列表
	 */
	private List<Map<String, Object>> initSummaryExcelHead() {

		List<Map<String, Object>> headInfoList = new ArrayList<Map<String, Object>>();
		Map<String, Object> itemMap = new HashMap<String, Object>();
		itemMap.put("title", "客户名称");
		itemMap.put("columnWidth", 50);
		itemMap.put("dataKey", "XH1");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "客户账号");
		itemMap.put("columnWidth", 25);
		itemMap.put("dataKey", "XH2");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "日期");
		itemMap.put("columnWidth", 25);
		itemMap.put("dataKey", "XH3");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "呼叫次数");
		itemMap.put("columnWidth", 25);
		itemMap.put("dataKey", "XH4");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "通话时长(分钟)");
		itemMap.put("columnWidth", 25);
		itemMap.put("dataKey", "XH5");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "计费时长");
		itemMap.put("columnWidth", 35);
		itemMap.put("dataKey", "XH6");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "接通数");
		itemMap.put("columnWidth", 25);
		itemMap.put("dataKey", "XH7");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "接通率");
		itemMap.put("columnWidth", 25);
		itemMap.put("dataKey", "XH8");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "应答数)");
		itemMap.put("columnWidth", 25);
		itemMap.put("dataKey", "XH9");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "应答率");
		itemMap.put("columnWidth", 25);
		itemMap.put("dataKey", "XH10");
		headInfoList.add(itemMap);
		
		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "ACD(秒");
		itemMap.put("columnWidth", 25);
		itemMap.put("dataKey", "XH11");
		headInfoList.add(itemMap);

		return headInfoList;
	}

	/**
	 * 构建导出数据列表
	 * 
	 * @query 导出业务查询条件
	 * 
	 * @return
	 */
	private List<Map<String, Object>> initSummaryExcelBody(CdrDailyStatisticsQuery query) throws Exception{
		logger.info("【号码卫士汇总导出】查询 query=" + JsonUtil.toJsonString(query));
		// 定义填充数据列表
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		List<CdrDailyStatistics> dailyStatList = cdrDailyStatisticsService.queryCdrDailyStatisticsListByEntity(query);
		if (dailyStatList != null && dailyStatList.size() > 0) {
			Map<String, Object> dataItem = null;
			for (CdrDailyStatistics cdrdailyStat : dailyStatList) {
				dataItem = new HashMap<String, Object>();
				// 查询客户信息
				MerchantAccount account = merchantAccountService.getMerchantAccount(cdrdailyStat.getApi_account());
				dataItem.put("XH1", account.getBusinessName());
				dataItem.put("XH2", account.getMerchantPhone());
				dataItem.put("XH3", cdrdailyStat.getDate_time());
				dataItem.put("XH4", cdrdailyStat.getSum_b_call_times());
				dataItem.put("XH5", cdrdailyStat.getSum_b_holding_time());
				dataItem.put("XH6", cdrdailyStat.getSum_fee_time());
				dataItem.put("XH7", cdrdailyStat.getSum_b_call_suc_times());
				dataItem.put("XH8", cdrdailyStat.getPct_call_sucdouble() + "%");
				dataItem.put("XH9", cdrdailyStat.getSum_b_response_times());
				dataItem.put("XH10", cdrdailyStat.getPct_a_response_sucdouble() + "%");
				dataItem.put("XH11", cdrdailyStat.getAvg_acd());
				dataList.add(dataItem);
			}
			CdrStatisticsResult cdrStatisticsResult = cdrDailyStatisticsService.queryVoiceUploadCountByEntity(query);
			dataItem = new HashMap<String, Object>();
			dataItem.put("XH1", "汇总");
			dataItem.put("XH2", "");
			dataItem.put("XH3", "");
			dataItem.put("XH4", cdrStatisticsResult.getTotal_call_times() / 2);
			dataItem.put("XH5", cdrStatisticsResult.getSumHoldingTime());
			dataItem.put("XH6", cdrStatisticsResult.getTotal_fee_time());
			dataItem.put("XH7", "");
			dataItem.put("XH8", cdrStatisticsResult.getTotal_call_suc() + "%");
			dataItem.put("XH9", "");
			dataItem.put("XH10", cdrStatisticsResult.getTotal_response_suc() + "%");
			dataItem.put("XH11", cdrStatisticsResult.getTotal_avg_acd());
			dataList.add(dataItem);
		}
		return dataList;
	}
}
