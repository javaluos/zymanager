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
import com.zy.cms.enums.BusinessTypeEnum;
import com.zy.cms.enums.HangupCodeEnum;
import com.zy.cms.enums.StateTypeEnum;
import com.zy.cms.service.manager.excel.CdrDetailExcelService;
import com.zy.cms.service.master.MerchantAccountService;
import com.zy.cms.util.DateUtil;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.Cdr;
import com.zy.cms.vo.MerchantAccount;
import com.zy.cms.vo.WebResult;
import com.zy.cms.vo.query.VoiceQuery;

/**
 * 导出语音详单业务
 * 
 * @author allen.yuan
 * @date 2016-11-6
 *
 */
@Service("cdrDetailExcelService")
public class CdrDetailExcelServiceImpl implements CdrDetailExcelService {

	private static final ZyLogger logger = ZyLogger.getLogger(CdrDetailExcelServiceImpl.class);

	@Autowired
	private EsCdrExportService esCdrExportService;

	@Autowired
	private MerchantAccountService merchantAccountService;

	private String export_Tmp_Path ="/tmp";

	@Override
	public WebResult exportVoiceExcel(VoiceQuery query, String realPath, String webUrl) {

		// 定义操作结果
		WebResult webRs = new WebResult();

		try {

			String sheetName = "语音详单"; // sheet名称
			String fileName = export_Tmp_Path + "/cdr/voicedetails_" + DateUtil.getVDateTime() + ".xlsx"; // 输出文件路径
			String savePath = (realPath + fileName).replace("\\", "/");
			String downUrl = webUrl + fileName;
			webRs.setData(fileName.replace("\\", "/"));
			logger.info("【语音话单详单导出】params={savePath:" + savePath + ",downUrl=" + downUrl + "}");

			// 获得excel头,内容
			List<Map<String, Object>> headInfoList = initExcelHead();
			List<Map<String, Object>> dataList = initExcelBody(query);
			if (dataList.size() > 200000) {
				logger.info("【语音话单详单导出】导出失败，数据超过20万行.");
				webRs.setCode(-1);
				webRs.setMsg("导出失败，数据超过20万行");
				return webRs;
			}

			// 执行创建excel业务,填充数据
			ExcelPOIUtil.exportExcel2FilePath(savePath, sheetName, headInfoList, dataList);

		} catch (Exception e) {

			logger.error("【语音话单导出】失败 error=" + e.getMessage());
			webRs.setCode(-1);
			webRs.setMsg("导出数据失败.");
		}

		logger.info("【语音话单详单导出】result=" + JsonUtil.toJsonString(webRs));
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
		itemMap.put("title", "手机号码");
		itemMap.put("columnWidth", 25);
		itemMap.put("dataKey", "XH2");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "被叫");
		itemMap.put("columnWidth", 25);
		itemMap.put("dataKey", "XH3");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "显号");
		itemMap.put("columnWidth", 25);
		itemMap.put("dataKey", "XH4");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "类型");
		itemMap.put("columnWidth", 25);
		itemMap.put("dataKey", "XH5");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "发送时间");
		itemMap.put("columnWidth", 35);
		itemMap.put("dataKey", "XH6");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "通话状态");
		itemMap.put("columnWidth", 25);
		itemMap.put("dataKey", "XH7");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "通话结束原因");
		itemMap.put("columnWidth", 40);
		itemMap.put("dataKey", "XH8");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "接通时延(秒)");
		itemMap.put("columnWidth", 25);
		itemMap.put("dataKey", "XH9");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "通话时长(秒)");
		itemMap.put("columnWidth", 25);
		itemMap.put("dataKey", "XH10");
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

		logger.info("【语音话单详单导出】查询ES参数 query=" + JsonUtil.toJsonString(query));

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
				dataItem.put("XH3", cdr.getCallee());
				dataItem.put("XH4", cdr.getCalleeDisplayNumber());
				dataItem.put("XH5", BusinessTypeEnum.getName(cdr.getType()));
				dataItem.put("XH6", DateUtil.timeStamp2Date(Integer.valueOf(cdr.getCalleeInviteTime() + "")));
				dataItem.put("XH7", StateTypeEnum.getName(cdr.getState()));
				dataItem.put("XH8", HangupCodeEnum.getName(cdr.getHangupCode()));

				long rpct = cdr.getCalleeRingingBeginTime() - cdr.getCalleeInviteTime();
				dataItem.put("XH9", rpct < 0 ? -1 : rpct);
				dataItem.put("XH10", cdr.getHoldTime());

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
	@SuppressWarnings("rawtypes")
	private List<JSONObject> queryEsCdrData(VoiceQuery query) {

		List<JSONObject> datas = new ArrayList<JSONObject>();
		// 定义数据结果
		String result = this.esCdrExportService.searchVoiceCdr(query, Constant.ES_CDR_INDEX_NAME,
				Constant.ES_CDR_INDEX_TYPE);
		Map rs = (Map) JSON.parse(result);
		Object objStr = rs.get("data");
		if (objStr != null) {
			datas = JsonUtil.parseToObject(objStr.toString(), List.class);
		}

		logger.info("【语音话单详单导出】查询ES数据   " + datas.size() + " 条.");
		return datas;
	}
}
