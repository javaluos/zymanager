package com.zy.cms.service.manager.impl;

import com.zy.cms.common.ExcelPOIUtil;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.enums.SmsCategoryEnum;
import com.zy.cms.mapper.manager.SmsStatisticsDayMapper;
import com.zy.cms.mapper.master.SmsChannelMapper;
import com.zy.cms.mapper.master.SmsStatusErrorCodeMapper;
import com.zy.cms.mapper.master.VoiceMerchantAccountMapper;
import com.zy.cms.mapper.sms.SmsStatisticsMapper;
import com.zy.cms.mapper.smsmanager.SmsDailyStatisticsMapper;
import com.zy.cms.service.manager.SmsDailyStatService;
import com.zy.cms.util.DateUtil;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.vo.MerchantAccount;
import com.zy.cms.vo.SmsStatusErrorCode;
import com.zy.cms.vo.WebResult;
import com.zy.cms.vo.channel.SmsChannel;
import com.zy.cms.vo.manager.ChannelSummaryResult;
import com.zy.cms.vo.manager.FailDetailVO;
import com.zy.cms.vo.manager.SmsDailyStatistics;
import com.zy.cms.vo.query.ChannelSummaryQuery;
import com.zy.cms.vo.query.SmsSendQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by luos on 2017/3/23.
 */
@Service("smsDailyStatService")
public class SmsDailyStatServiceImpl implements SmsDailyStatService {

	private static final ZyLogger logger = ZyLogger.getLogger(SmsDailyStatServiceImpl.class);

	private String export_Tmp_Path = "/tmp";

	@Autowired
	private SmsDailyStatisticsMapper mapper;

	@Autowired
	private SmsStatisticsMapper smsStatisticsMapper;

	@Autowired
	private SmsStatisticsDayMapper smsStatisticsDayMapper;

	@Autowired
	private VoiceMerchantAccountMapper voiceMerchantAccountMapper;

	@Autowired
	private SmsChannelMapper smsChannelMapper;

	@Autowired
	private SmsStatusErrorCodeMapper smsStatusErrorCodeMapper;

	public Integer getTotalByQuery(SmsSendQuery query) throws SQLException {
		String businessName = query.getBusinessName();
		String merchantphone = query.getMerchantPhone();
		if (StringUtils.isNotBlank(businessName) || StringUtils.isNotBlank(merchantphone)) {
			List<MerchantAccount> merchantAccount = voiceMerchantAccountMapper
					.getMerchantAccountByCondition(businessName, merchantphone);
			if (null != merchantAccount && merchantAccount.size() > 0) {
				query.setApiAccount(merchantAccount.get(0).getApiAccount());
			} else {
				return 0;
			}
		}
		return mapper.selectTotalByQuery(query);
	}

	public List<SmsDailyStatistics> getListByQuery(SmsSendQuery query) throws SQLException {
		List<MerchantAccount> merchantAccountList = null;
		List<SmsDailyStatistics> smsDailyStatisticss = null;
		String businessName = query.getBusinessName();
		String merchantphone = query.getMerchantPhone();
		if (StringUtils.isNotBlank(businessName) || StringUtils.isNotBlank(merchantphone)) {
			merchantAccountList = voiceMerchantAccountMapper.getMerchantAccountByCondition(businessName, merchantphone);
			if (null != merchantAccountList && merchantAccountList.size() > 0) {
				query.setApiAccount(merchantAccountList.get(0).getApiAccount());
			} else {
				return smsDailyStatisticss;
			}
		} else {
			merchantAccountList = voiceMerchantAccountMapper.getAllMerchantAccount();
		}
		smsDailyStatisticss = mapper.selectListByQuery(query);
		for (SmsDailyStatistics smsDailyStatistics : smsDailyStatisticss) {
			for (MerchantAccount merchantAccount : merchantAccountList) {
				if (smsDailyStatistics.getApiAccount().equals(merchantAccount.getApiAccount())) {
					smsDailyStatistics.setBusinessName(merchantAccount.getBusinessName());
					smsDailyStatistics.setMerchantPhone(merchantAccount.getMerchantPhone());
				}
			}
		}
		return smsDailyStatisticss;
	}

	public SmsDailyStatistics statSmsDailyTotal(SmsSendQuery query) throws SQLException {
		return mapper.statSmsDailyTotal(query);
	}

	public Integer getViewTotalByQuery(SmsSendQuery query) throws SQLException {
		return mapper.selectViewTotalByQuery(query);
	}

	public List<SmsDailyStatistics> getViewListByQuery(SmsSendQuery query) throws SQLException {
		List<SmsChannel> list = smsChannelMapper.selectAllChannels();
		List<SmsDailyStatistics> smsDailyStatisticss = mapper.selectViewListByQuery(query);
		for (SmsDailyStatistics smsDailyStatistics : smsDailyStatisticss) {
			for (SmsChannel smsChannel : list) {
				if (smsDailyStatistics.getChannelSmsId().equals(smsChannel.getChannelId())) {
					smsDailyStatistics.setChannelName(smsChannel.getChannelName());
				}
			}
		}
		return smsDailyStatisticss;
	}

	@Override
	public List<SmsDailyStatistics> getChannelSummarysByQuery(ChannelSummaryQuery query) throws SQLException {
		return mapper.getChannelSummarysByQuery(query);
	}

	@Override
	public ChannelSummaryResult<SmsDailyStatistics> getChannelSummaryResult(ChannelSummaryQuery query)
			throws SQLException {
		return mapper.getChannelSummaryResult(query);
	}

	public WebResult exportExcel(SmsSendQuery query, String realPath, String webUrl) {
		// 定义操作结果
		WebResult webRs = new WebResult();
		try {

			String sheetName = "Sheet1"; // sheet名称
			String fileName = export_Tmp_Path + "/sms_send/sms_send_stat_" + DateUtil.getVDateTime() + ".xlsx"; // 输出文件路径
			String savePath = (realPath + fileName).replace("\\", "/");
			String downUrl = webUrl + fileName;
			webRs.setData(fileName.replace("\\", "/"));
			logger.info("【短信发送汇总导出】params={savePath:" + savePath + ",downUrl=" + downUrl + "}");

			// 获得excel头,内容
			List<Map<String, Object>> headInfoList = initExcelHead(); // initExcelHead();
			List<Map<String, Object>> dataList = initExcelBody(query);// initExcelBody(query);
			if (dataList.size() > 200000) {
				logger.info("【短信发送汇总导出】导出失败，数据超过20万行.");
				webRs.setCode(-1);
				webRs.setMsg("导出失败，数据超过20万行");
				return webRs;
			}

			// 执行创建excel业务,填充数据
			ExcelPOIUtil.exportExcel2FilePath(savePath, sheetName, headInfoList, dataList);
		} catch (Exception e) {
			logger.error("【短信发送汇总导出】失败 error=" + e.getMessage());
			webRs.setCode(-1);
			webRs.setMsg("短信发送汇总导出数据失败.");
		}

		logger.info("【短信发送汇总导出】result=" + JsonUtil.toJsonString(webRs));
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
		itemMap.put("dataKey", "XH2");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "客户账号");
		itemMap.put("columnWidth", 20);
		itemMap.put("dataKey", "XH3");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "日期");
		itemMap.put("columnWidth", 20);
		itemMap.put("dataKey", "XH1");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "类型");
		itemMap.put("columnWidth", 15);
		itemMap.put("dataKey", "XH4");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "计费条数");
		itemMap.put("columnWidth", 15);
		itemMap.put("dataKey", "XH8");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "发送条数");
		itemMap.put("columnWidth", 15);
		itemMap.put("dataKey", "XH5");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "成功条数");
		itemMap.put("columnWidth", 15);
		itemMap.put("dataKey", "XH6");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "失败条数");
		itemMap.put("columnWidth", 15);
		itemMap.put("dataKey", "XH9");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "未知条数");
		itemMap.put("columnWidth", 15);
		itemMap.put("dataKey", "XH11");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "发送成功率");
		itemMap.put("columnWidth", 20);
		itemMap.put("dataKey", "XH7");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "发送失败率");
		itemMap.put("columnWidth", 20);
		itemMap.put("dataKey", "XH10");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "未知状态比例");
		itemMap.put("columnWidth", 20);
		itemMap.put("dataKey", "XH12");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "平均发送时长");
		itemMap.put("columnWidth", 20);
		itemMap.put("dataKey", "XH13");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "平均状态报告时长");
		itemMap.put("columnWidth", 30);
		itemMap.put("dataKey", "XH14");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "10秒内到达率");
		itemMap.put("columnWidth", 20);
		itemMap.put("dataKey", "XH15");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "50秒内到达率");
		itemMap.put("columnWidth", 20);
		itemMap.put("dataKey", "XH16");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "超过50秒到达率");
		itemMap.put("columnWidth", 20);
		itemMap.put("dataKey", "XH17");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "回执24小时到达率");
		itemMap.put("columnWidth", 20);
		itemMap.put("dataKey", "XH18");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "回执48小时到达率");
		itemMap.put("columnWidth", 20);
		itemMap.put("dataKey", "XH19");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "回执72小时到达率");
		itemMap.put("columnWidth", 20);
		itemMap.put("dataKey", "XH20");
		headInfoList.add(itemMap);

		return headInfoList;
	}

	private List<Map<String, Object>> initExcelBody(SmsSendQuery query) {

		logger.info("【短信通道跑量导出】查询参数 query=" + JsonUtil.toJsonString(query));
		// 定义填充数据列表
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();

		try {
			SmsDailyStatistics smsDailyStat = statSmsDailyTotal(query);
			List<SmsDailyStatistics> list = getListByQuery(query);
			if (list != null && list.size() > 0) {
				Map<String, Object> dataItem = null;
				for (SmsDailyStatistics smsDailyStatistics : list) {
					dataItem = new HashMap<String, Object>();
					String businessName = smsDailyStatistics.getBusinessName();
					dataItem.put("XH2", smsDailyStatistics.getBusinessName() == null ? "" : businessName);// 客户名称
					String merchantPhone = smsDailyStatistics.getMerchantPhone();
					dataItem.put("XH3", smsDailyStatistics.getMerchantPhone() == null ? "" : merchantPhone);// 客户账号
					dataItem.put("XH1", smsDailyStatistics.getDateTimeFormat());// 日期
					dataItem.put("XH4", SmsCategoryEnum.getName(smsDailyStatistics.getSmsCategory()));// 类型
					dataItem.put("XH8", smsDailyStatistics.getFeeCount());// 计费条数
					dataItem.put("XH5", smsDailyStatistics.getSendCount());// 发送条数
					dataItem.put("XH6", smsDailyStatistics.getSuccessCount());// 成功条数
					dataItem.put("XH9", smsDailyStatistics.getFailedCount());// 失败条数
					dataItem.put("XH11", smsDailyStatistics.getNoreportCount());// 未知状态数

					dataItem.put("XH7", doubleToRate(smsDailyStatistics.getSuccessSendRate()));// 成功率
					dataItem.put("XH10", doubleToRate(smsDailyStatistics.getFailSendRate()));// 失败率
					dataItem.put("XH12", doubleToRate(smsDailyStatistics.getNoreportRate()));// 未知状态比例
					dataItem.put("XH13", smsDailyStatistics.getAvgSendTime());// 平均发送时长
					dataItem.put("XH14", smsDailyStatistics.getAvgStatusTime());// 平均状态报告接收时长
					dataItem.put("XH15", doubleToRate(smsDailyStatistics.getSdUs10sCountRate()));// 10秒内到达率
					dataItem.put("XH16", doubleToRate(smsDailyStatistics.getSdUs50sCountRate()));// 50秒内到达率
					dataItem.put("XH17", doubleToRate(smsDailyStatistics.getSdUsgt50sCountRate()));// 超过50秒到达率
					dataItem.put("XH18", doubleToRate(smsDailyStatistics.getStBk24hCountRate()));// 回执24小时到达率
					dataItem.put("XH19", doubleToRate(smsDailyStatistics.getStBk48hCountRate()));// 回执48小时到达率
					dataItem.put("XH20", doubleToRate(smsDailyStatistics.getStBk72hCountRate()));// 回执72小时到达率
					dataList.add(dataItem);
				}
			}
			if (smsDailyStat != null) {
				Map<String, Object> dataItem = new HashMap<String, Object>();
				dataItem.put("XH2", "汇总");
				dataItem.put("XH3", "");
				dataItem.put("XH1", "");
				dataItem.put("XH4", "");
				dataItem.put("XH8", smsDailyStat.getFeeCount());// 计费条数
				dataItem.put("XH5", smsDailyStat.getSendCount());// 发送条数
				dataItem.put("XH6", smsDailyStat.getSuccessCount());// 成功条数
				dataItem.put("XH9", smsDailyStat.getFailedCount());// 失败条数
				dataItem.put("XH11", smsDailyStat.getNoreportCount());// 未知状态数
				dataItem.put("XH7", doubleToRate(smsDailyStat.getSuccessSendRate()));// 成功率
				dataItem.put("XH10", doubleToRate(smsDailyStat.getFailSendRate()));// 失败率
				dataItem.put("XH12", doubleToRate(smsDailyStat.getNoreportRate()));// 未知状态比例
				dataItem.put("XH13", smsDailyStat.getAvgSendTime());// 平均发送时长
				dataItem.put("XH14", smsDailyStat.getAvgStatusTime());// 平均状态报告接收时长
				dataItem.put("XH15", doubleToRate(smsDailyStat.getSdUs10sCountRate()));// 10秒内到达率
				dataItem.put("XH16", doubleToRate(smsDailyStat.getSdUs50sCountRate()));// 50秒内到达率
				dataItem.put("XH17", doubleToRate(smsDailyStat.getSdUsgt50sCountRate()));// 超过50秒到达率
				dataItem.put("XH18", doubleToRate(smsDailyStat.getStBk24hCountRate()));// 回执24小时到达率
				dataItem.put("XH19", doubleToRate(smsDailyStat.getStBk48hCountRate()));// 回执48小时到达率
				dataItem.put("XH20", doubleToRate(smsDailyStat.getStBk72hCountRate()));// 回执72小时到达率
				dataList.add(dataItem);
			}
		} catch (SQLException e) {
			logger.info("【短信通道跑量导出】出现异常,异常是:" + e.getMessage());
		}
		return dataList;
	}

	@Override
	public List<SmsDailyStatistics> getChannelDetailByQuery(ChannelSummaryQuery query) throws SQLException {
		return mapper.getChannelDetailByQuery(query);
	}

	@Override
	public ChannelSummaryResult<SmsDailyStatistics> getChannelDetailResult(ChannelSummaryQuery query)
			throws SQLException {
		return mapper.getChannelDetailResult(query);
	}

	/**
	 * 将比率转换成百分比(保留两位小数)
	 *
	 * @param rate
	 * @return
	 */
	private String doubleToRate(Double rate) {
		BigDecimal decimal = new BigDecimal(rate * 100);
		Double rateDouble = decimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return rateDouble + "%";
	}

	@Override
	public int channelTotalByQuery(ChannelSummaryQuery query) throws SQLException {
		return mapper.channelTotalByQuery(query);
	}

	@Override
	public int channelDetailTotalByQuery(ChannelSummaryQuery query) throws SQLException {
		return mapper.channelDetailTotalByQuery(query);
	}

	@Override
	public Integer getTodayTotalByQuery(SmsSendQuery query) throws SQLException {
		String businessName = query.getBusinessName();
		String merchantphone = query.getMerchantPhone();
		if (StringUtils.isNotBlank(businessName) || StringUtils.isNotBlank(merchantphone)) {
			List<MerchantAccount> merchantAccount = voiceMerchantAccountMapper
					.getMerchantAccountByCondition(businessName, merchantphone);
			if (null != merchantAccount && merchantAccount.size() > 0) {
				query.setApiAccount(merchantAccount.get(0).getApiAccount());
			} else {
				return 0;
			}
		}
		return smsStatisticsDayMapper.selectTodayTotalByQuery(query);
//		return smsStatisticsMapper.selectTodayTotalByQuery(query);

	}

	@Override
	public List<SmsDailyStatistics> getTodayListByQuery(SmsSendQuery query) throws SQLException {
		List<MerchantAccount> merchantAccountList = null;
		List<SmsDailyStatistics> smsDailyStatisticss = null;
		String businessName = query.getBusinessName();
		String merchantphone = query.getMerchantPhone();
		if (StringUtils.isNotBlank(businessName) || StringUtils.isNotBlank(merchantphone)) {
			merchantAccountList = voiceMerchantAccountMapper.getMerchantAccountByCondition(businessName, merchantphone);
			if (null != merchantAccountList && merchantAccountList.size() > 0) {
				query.setApiAccount(merchantAccountList.get(0).getApiAccount());
			} else {
				return smsDailyStatisticss;
			}
		} else {
			merchantAccountList = voiceMerchantAccountMapper.getAllMerchantAccount();
		}

		smsDailyStatisticss = smsStatisticsDayMapper.selectTodayListByQuery(query);
//		smsDailyStatisticss = smsStatisticsMapper.selectTodayListByQuery(query);

		for (SmsDailyStatistics smsDailyStatistics : smsDailyStatisticss) {
			for (MerchantAccount merchantAccount : merchantAccountList) {
				if (smsDailyStatistics.getApiAccount().equals(merchantAccount.getApiAccount())) {
					smsDailyStatistics.setBusinessName(merchantAccount.getBusinessName());
					smsDailyStatistics.setMerchantPhone(merchantAccount.getMerchantPhone());
				}
			}
		}
		return smsDailyStatisticss;
	}

	@Override
	public SmsDailyStatistics statTodaySmsDailyTotal(SmsSendQuery query) throws SQLException {
		return smsStatisticsDayMapper.statTodaySmsDailyTotal(query);
		//return smsStatisticsMapper.statTodaySmsDailyTotal(query);

	}

	@Override
	public Integer getTodayViewTotalByQuery(SmsSendQuery query) throws SQLException {
		return smsStatisticsDayMapper.selectTodayViewTotalByQuery(query);
		//return smsStatisticsMapper.selectTodayViewTotalByQuery(query);

	}

	@Override
	public List<SmsDailyStatistics> getTodayViewListByQuery(SmsSendQuery query) throws SQLException {
		List<SmsChannel> smsChannelList = smsChannelMapper.selectAllChannels();
		List<SmsDailyStatistics> smsDailyStatisticss = smsStatisticsDayMapper.selectTodayViewListByQuery(query);
//		List<SmsDailyStatistics> smsDailyStatisticss = smsStatisticsMapper.selectTodayViewListByQuery(query);
		for (SmsDailyStatistics smsDailyStatistics : smsDailyStatisticss) {
			for (SmsChannel smsChannel : smsChannelList) {
				if (smsChannel.getChannelId().equals(smsDailyStatistics.getChannelSmsId())) {
					smsDailyStatistics.setChannelName(smsChannel.getChannelName());
				}
			}
		}
		return smsDailyStatisticss;
	}

	@Override
	public List<FailDetailVO> getSmsFailDetailByQuery(SmsSendQuery query) throws SQLException {
		List<FailDetailVO> list = smsStatisticsMapper.querySmsFailDetailByQuery(query);
		List<SmsStatusErrorCode> errorCodeList = smsStatusErrorCodeMapper.selectAll();
		for (FailDetailVO failDetailVO : list) {
			for (SmsStatusErrorCode smsStatusErrorCode : errorCodeList) {
				if (failDetailVO.getReceiveStatusDesc().equals(smsStatusErrorCode.getErrorCode())) {
					failDetailVO.setStatusDescCN(smsStatusErrorCode.getErrorDesc());
				}
			}
		}
		return list;
	}

	@Override
	public List<FailDetailVO> getSmsViewFailDetailByQuery(SmsSendQuery query) throws SQLException {
		List<FailDetailVO> list = smsStatisticsMapper.querySmsViewFailDetailByQuery(query);
		List<SmsStatusErrorCode> errorCodeList = smsStatusErrorCodeMapper.selectAll();
		for (FailDetailVO failDetailVO : list) {
			for (SmsStatusErrorCode smsStatusErrorCode : errorCodeList) {
				if (failDetailVO.getReceiveStatusDesc().equals(smsStatusErrorCode.getErrorCode())) {
					failDetailVO.setStatusDescCN(smsStatusErrorCode.getErrorDesc());
				}
			}
		}
		return list;
	}

	@Override
	public WebResult exportFailAnalysis(SmsSendQuery query, String realPath, String webUrl) throws SQLException {
		// 定义操作结果
		WebResult webRs = new WebResult();
		try {
			String sheetName = "Sheet1"; // sheet名称
			String fileName = export_Tmp_Path + "/sms_send/account_fail_analysis_" + DateUtil.getVDateTime() + ".xlsx"; // 输出文件路径
			String savePath = (realPath + fileName).replace("\\", "/");
			String downUrl = webUrl + fileName;
			webRs.setData(fileName.replace("\\", "/"));
			logger.info("【客户失败原因分析导出】params={savePath:" + savePath + ",downUrl=" + downUrl + "}");

			// 获得excel头,内容
			List<Map<String, Object>> headInfoList = initAccFailExcelHead(); // initExcelHead();
			List<Map<String, Object>> dataList = initAccFailExcelBody(query);// initExcelBody(query);
			if (dataList.size() > 100000) {
				logger.info("【客户失败原因分析导出】导出失败，数据超过10万行.");
				webRs.setCode(-1);
				webRs.setMsg("导出失败，数据超过10万行");
				return webRs;
			}

			// 执行创建excel业务,填充数据
			ExcelPOIUtil.exportExcelMerge(savePath, sheetName, headInfoList, dataList);
		} catch (Exception e) {
			logger.error("【客户失败原因分析导出】失败 error=" + e.getMessage());
			webRs.setCode(-1);
			webRs.setMsg("客户失败原因分析导出数据失败.");
		}

		logger.info("【客户失败原因分析导出】result=" + JsonUtil.toJsonString(webRs));
		return webRs;
	}

	@Override
	public WebResult exportChannelFailAnalysis(SmsSendQuery query, String realPath, String webUrl) throws SQLException {
		// 定义操作结果
		WebResult webRs = new WebResult();
		try {
			String sheetName = "Sheet1"; // sheet名称
			String fileName = export_Tmp_Path + "/sms_send/channel_fail_analysis_" + DateUtil.getVDateTime() + ".xlsx"; // 输出文件路径
			String savePath = (realPath + fileName).replace("\\", "/");
			String downUrl = webUrl + fileName;
			webRs.setData(fileName.replace("\\", "/"));
			logger.info("【通道失败原因分析导出】params={savePath:" + savePath + ",downUrl=" + downUrl + "}");

			// 获得excel头,内容
			List<Map<String, Object>> headInfoList = initChannelFailExcelHead(); // initExcelHead();
			List<Map<String, Object>> dataList = initChannelFailExcelBody(query);// initExcelBody(query);
			if (dataList.size() > 100000) {
				logger.info("【通道失败原因分析导出】导出失败，数据超过10万行.");
				webRs.setCode(-1);
				webRs.setMsg("导出失败，数据超过10万行");
				return webRs;
			}

			// 执行创建excel业务,填充数据
			ExcelPOIUtil.exportExcelMerge(savePath, sheetName, headInfoList, dataList);
		} catch (Exception e) {
			logger.error("【通道失败原因分析导出】失败 error=" + e.getMessage());
			webRs.setCode(-1);
			webRs.setMsg("通道失败原因分析导出数据失败.");
		}

		logger.info("【客户失败原因分析导出】result=" + JsonUtil.toJsonString(webRs));
		return webRs;
	}

	/**
	 * 取当前时间最近统计时间
	 * @return
	 */
	@Override
	public String getLatestStatDateTime() {
		Calendar today = Calendar.getInstance();
		int minute = today.get(Calendar.MINUTE);
		today.set(Calendar.MINUTE, minute / 5 * 5);
		today.set(Calendar.SECOND, 0);
		Date date = today.getTime();
		return DateUtil.getDateTime(date);
	}

	private List<Map<String, Object>> initAccFailExcelHead() {
		List<Map<String, Object>> headInfoList = new ArrayList<Map<String, Object>>();
		Map<String, Object> itemMap = new HashMap<String, Object>();

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "客户名称");
		itemMap.put("columnWidth", 50);
		itemMap.put("dataKey", "XH1");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "客户账号");
		itemMap.put("columnWidth", 50);
		itemMap.put("dataKey", "XH2");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "失败总条数");
		itemMap.put("columnWidth", 25);
		itemMap.put("dataKey", "XH3");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "失败条数");
		itemMap.put("columnWidth", 25);
		itemMap.put("dataKey", "XH4");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "比例");
		itemMap.put("columnWidth", 25);
		itemMap.put("dataKey", "XH5");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "代码描述");
		itemMap.put("columnWidth", 50);
		itemMap.put("dataKey", "XH6");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "中文描述");
		itemMap.put("columnWidth", 50);
		itemMap.put("dataKey", "XH7");
		headInfoList.add(itemMap);

		return headInfoList;
	}

	private List<Map<String, Object>> initAccFailExcelBody(SmsSendQuery query) {
		logger.info("【客户失败原因分析导出】查询参数 query=" + JsonUtil.toJsonString(query));
		// 定义填充数据列表
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		try {
			List<FailDetailVO> list = getSmsFailDetailByQuery(query);
			if (list != null && list.size() > 0) {
				Map<String, Object> dataItem = null;
				for (FailDetailVO failDetailVO : list) {
					dataItem = new HashMap<String, Object>();
					dataItem.put("XH1", query.getBusinessName());// 客户名称
					dataItem.put("XH2", query.getMerchantPhone());// 客户账号
					dataItem.put("XH3", failDetailVO.getTotal());// 失败总条数
					dataItem.put("XH4", failDetailVO.getGroupCount());// 失败条数
					BigDecimal percentage = new BigDecimal(failDetailVO.getPercentage() * 100).setScale(2,
							BigDecimal.ROUND_HALF_UP);
					dataItem.put("XH5", percentage + "%");// 比例
					dataItem.put("XH6", failDetailVO.getReceiveStatusDesc());// 代码描述
					String statusDescCN = failDetailVO.getStatusDescCN();
					dataItem.put("XH7", statusDescCN == null ? "" : statusDescCN);// 中文描述
					dataList.add(dataItem);
				}
			}
		} catch (SQLException e) {
			logger.info("【客户失败原因分析导出】出现异常,异常是:" + e.getMessage());
		}
		return dataList;
	}

	private List<Map<String, Object>> initChannelFailExcelHead() {
		List<Map<String, Object>> headInfoList = new ArrayList<Map<String, Object>>();
		Map<String, Object> itemMap = new HashMap<String, Object>();

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "通道名称");
		itemMap.put("columnWidth", 50);
		itemMap.put("dataKey", "XH1");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "通道ID");
		itemMap.put("columnWidth", 50);
		itemMap.put("dataKey", "XH2");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "失败总条数");
		itemMap.put("columnWidth", 25);
		itemMap.put("dataKey", "XH3");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "失败条数");
		itemMap.put("columnWidth", 25);
		itemMap.put("dataKey", "XH4");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "比例");
		itemMap.put("columnWidth", 25);
		itemMap.put("dataKey", "XH5");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "代码描述");
		itemMap.put("columnWidth", 50);
		itemMap.put("dataKey", "XH6");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "中文描述");
		itemMap.put("columnWidth", 50);
		itemMap.put("dataKey", "XH7");
		headInfoList.add(itemMap);

		return headInfoList;
	}

	private List<Map<String, Object>> initChannelFailExcelBody(SmsSendQuery query) {
		logger.info("【通道失败原因分析导出】查询参数 query=" + JsonUtil.toJsonString(query));
		// 定义填充数据列表
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		try {
			List<FailDetailVO> list = getSmsFailDetailByQuery(query);
			if (list != null && list.size() > 0) {
				Map<String, Object> dataItem = null;
				for (FailDetailVO failDetailVO : list) {
					dataItem = new HashMap<String, Object>();
					dataItem.put("XH1", query.getChannelName());// 客户名称
					dataItem.put("XH2", query.getChannelId());// 客户账号
					dataItem.put("XH3", failDetailVO.getTotal());// 失败总条数
					dataItem.put("XH4", failDetailVO.getGroupCount());// 失败条数
					BigDecimal percentage = new BigDecimal(failDetailVO.getPercentage() * 100).setScale(2,
							BigDecimal.ROUND_HALF_UP);
					dataItem.put("XH5", percentage + "%");// 比例
					dataItem.put("XH6", failDetailVO.getReceiveStatusDesc());// 代码描述
					String statusDescCN = failDetailVO.getStatusDescCN();
					dataItem.put("XH7", statusDescCN == null ? "" : statusDescCN);// 中文描述
					dataList.add(dataItem);
				}
			}
		} catch (SQLException e) {
			logger.info("【通道失败原因分析导出】出现异常,异常是:" + e.getMessage());
		}
		return dataList;
	}

}
