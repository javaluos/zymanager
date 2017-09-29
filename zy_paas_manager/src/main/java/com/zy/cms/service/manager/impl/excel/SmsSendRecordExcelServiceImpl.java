package com.zy.cms.service.manager.impl.excel;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zy.cms.common.Constant;
import com.zy.cms.common.ExcelPOIUtil;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.elastic.service.EsService;
import com.zy.cms.enums.SmsCategoryEnum;
import com.zy.cms.enums.SmsOperatorEnum;
import com.zy.cms.service.manager.SmsSendRecordService;
import com.zy.cms.service.manager.impl.SmsDailyStatServiceImpl;
import com.zy.cms.util.DateUtil;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.vo.WebResult;
import com.zy.cms.vo.manager.SmsDailyStatistics;
import com.zy.cms.vo.query.SmsSendQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;

/**
 * Created by luos on 2017/5/22.
 */
@Service("smsSendRecordService")
public class SmsSendRecordExcelServiceImpl implements SmsSendRecordService {

	private static final ZyLogger logger = ZyLogger.getLogger(SmsDailyStatServiceImpl.class);

	private String export_Tmp_Path = "/tmp";

	@Autowired
	private EsService esService;

	@Override
	public WebResult exportExcel(SmsSendQuery query, String realPath, String webUrl) {
		// 定义操作结果
		WebResult webRs = new WebResult();
		try {

			String sheetName = "Sheet1"; // sheet名称
			String fileName = export_Tmp_Path + "/sms_send/sms_send_" + DateUtil.getVDateTime() + ".xlsx"; // 输出文件路径
			String savePath = (realPath + fileName).replace("\\", "/");
			String downUrl = webUrl + fileName;
			webRs.setData(fileName.replace("\\", "/"));
			logger.info("【短信发送记录导出】params={savePath:" + savePath + ",downUrl=" + downUrl + "}");

			// 获得excel头,内容
			List<Map<String, Object>> headInfoList = initExcelHead(); // initExcelHead();
			long startTime = System.currentTimeMillis();
			List<Map<String, Object>> dataList = initExcelBody(query);// initExcelBody(query);
			long endTime1 = System.currentTimeMillis();
			logger.info("【短信发送记录导出】获取数据时长" + (endTime1 - startTime) + "ms");
			if (null == dataList) {
				logger.info("【短信发送记录导出】导出失败，数据超过" + query.getPageCount() + "万行.");
				webRs.setCode(-1);
				webRs.setMsg("导出失败，数据超过" + query.getPageCount() + "万行");
				return webRs;
			}

			// 执行创建excel业务,填充数据
			ExcelPOIUtil.exportSmsSendExcel(savePath, sheetName, headInfoList, dataList);
			long endTime2 = System.currentTimeMillis();
			logger.info("【短信发送记录导出】生成文件时长" + (endTime2 - endTime1) + "ms");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【短信发送记录导出】失败 error=" + e.getMessage());
			webRs.setCode(-1);
			webRs.setMsg("短信发送记录导出数据失败.");
		}

		logger.info("【短信发送记录导出】result=" + JsonUtil.toJsonString(webRs));
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

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "客户名称");
		itemMap.put("columnWidth", 50);
		itemMap.put("dataKey", "XH1");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "客户账号");
		itemMap.put("columnWidth", 20);
		itemMap.put("dataKey", "XH2");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "通道ID");
		itemMap.put("columnWidth", 30);
		itemMap.put("dataKey", "XH3");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "短信内容");
		itemMap.put("columnWidth", 150);
		itemMap.put("dataKey", "XH4");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "接收手机");
		itemMap.put("columnWidth", 20);
		itemMap.put("dataKey", "XH5");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "短信类型");
		itemMap.put("columnWidth", 20);
		itemMap.put("dataKey", "XH6");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "运营商");
		itemMap.put("columnWidth", 15);
		itemMap.put("dataKey", "XH7");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "归属地");
		itemMap.put("columnWidth", 20);
		itemMap.put("dataKey", "XH8");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "发送时间");
		itemMap.put("columnWidth", 35);
		itemMap.put("dataKey", "XH9");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "计费条数");
		itemMap.put("columnWidth", 15);
		itemMap.put("dataKey", "XH10");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "发送状态");
		itemMap.put("columnWidth", 15);
		itemMap.put("dataKey", "XH11");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "状态描述");
		itemMap.put("columnWidth", 15);
		itemMap.put("dataKey", "XH12");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "发送时长");
		itemMap.put("columnWidth", 15);
		itemMap.put("dataKey", "XH13");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "状态时长");
		itemMap.put("columnWidth", 15);
		itemMap.put("dataKey", "XH14");
		headInfoList.add(itemMap);

		return headInfoList;
	}

	private List<Map<String, Object>> initExcelBody(SmsSendQuery query) {

		logger.info("【短信发送记录导出】查询参数 query=" + JsonUtil.toJsonString(query));
		// 定义填充数据列表
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		try {
			long startTime = System.currentTimeMillis();
			List<Map<String, Object>> list = esService.searchExportSmsSend(query, Constant.ES_SMS_SEND_INDEX_NAME,
					Constant.ES_SMS_SEND_INDEX_TYPE);
			if (null == list) {
				return list;
			}
			long endTime = System.currentTimeMillis();
			logger.info("【短信发送记录导出】查询ES耗时" + (endTime - startTime) + "ms");
			for (Map dataMap : list) {
				List<Map> mapList = (List<Map>) dataMap.get("data");
				for (Map map : mapList) {
					Map<String, Object> dataItem = new HashMap<String, Object>();
					String dateTime = map.get("DATE_TIME") == null ? "" : map.get("DATE_TIME").toString();
//					if (StringUtils.isNotBlank(dateTime)) {
//						dateTime = DateUtil.formatDate(dateTime);
//					}
					dataItem.put("XH0", dateTime);
					String businessName = map.get("BUSINESS_NAME") == null ? "" : map.get("BUSINESS_NAME").toString();
					dataItem.put("XH1", businessName);// 客户名称
					String merchantPhone = map.get("MERCHANT_PHONE") == null ? ""
							: map.get("MERCHANT_PHONE").toString();
					dataItem.put("XH2", merchantPhone);// 客户账号
					String channelId = map.get("SMS_CHANNEL_ID") == null ? "" : map.get("SMS_CHANNEL_ID").toString();
					dataItem.put("XH3", channelId);// 通道id
					dataItem.put("XH4", map.get("SMS_CONTENT").toString());// 短信内容

					String receiveMobile = map.get("RECEIVE_MOBILE") == null ? ""
							: map.get("RECEIVE_MOBILE").toString();
					if (receiveMobile.length() > 512) {
						receiveMobile = receiveMobile.substring(0, 512) + "...";
					}
					dataItem.put("XH5", receiveMobile);// 接收手机
					String category = map.get("SMS_CATEGORY") == null ? "" : map.get("SMS_CATEGORY").toString();
					if (SmsCategoryEnum.NOTICE.getType().equals(category)) {
						category = SmsCategoryEnum.NOTICE.getName();
					} else if (SmsCategoryEnum.VCODE.getType().equals(category)) {
						category = SmsCategoryEnum.VCODE.getName();
					} else if (SmsCategoryEnum.MARKETING.getType().equals(category)) {
						category = SmsCategoryEnum.MARKETING.getName();
					}
					dataItem.put("XH6", category);// 短信类型
					String operator = map.get("CARRIERS") == null ? "" : map.get("CARRIERS").toString();
					if (SmsOperatorEnum.YD.getType().equals(operator)) {
						operator = SmsOperatorEnum.YD.getName();
					} else if (SmsOperatorEnum.LT.getType().equals(operator)) {
						operator = SmsOperatorEnum.LT.getName();
					} else if (SmsOperatorEnum.DX.getType().equals(operator)) {
						operator = SmsOperatorEnum.DX.getName();
					} else {
						operator = "";
					}
					dataItem.put("XH7", operator);// 运营商
					String province = map.get("PROVINCE") == null ? "" : map.get("PROVINCE").toString();
					String city = map.get("CITY") == null ? "" : map.get("CITY").toString();
					dataItem.put("XH8", province + " " + city);// 归属地
					dataItem.put("XH9", map.get("CREATE_TIME"));// 发送时间
					dataItem.put("XH10", map.get("SMS_NUMS"));// 计费条数
					String status = map.get("RECEIVE_STATUS") == null ? "" : map.get("RECEIVE_STATUS").toString();
					String sendResult = map.get("SEND_RESULT") == null ? "" : map.get("SEND_RESULT").toString();
					if ("2".equals(status)) {
						status = "状态未知";
					} else if ("0".equals(status)) {
						status = "发送成功";
					} else if ("1".equals(status)) {
						status = "发送失败";
					}
					if ("1".equals(sendResult)) {
						status = "提交失败";
					}
					dataItem.put("XH11", status);// 未知状态数
					String desc = (map.get("RECEIVE_STATUS_DESC") == null || map.get("SEND_RESULT").equals("1")) ? ""
							: map.get("RECEIVE_STATUS_DESC").toString();
					dataItem.put("XH12", desc);// 状态描述
					String sendPeriod = (map.get("SEND_PERIOD") == null || map.get("SEND_RESULT").equals("1")) ? ""
							: map.get("SEND_PERIOD").toString();
					dataItem.put("XH13", sendPeriod);// 发送时长
					String statusPeriod = (map.get("STATUS_PERIOD") == null || map.get("SEND_RESULT").equals("1")) ? ""
							: map.get("STATUS_PERIOD").toString();
					dataItem.put("XH14", statusPeriod);// 状态报告接收时长

					dataList.add(dataItem);
				}
			}
		} catch (Exception e) {
			logger.info("【短信发送记录导出】出现异常,异常是:" + e.getMessage());
		}
		return dataList;
	}

}
