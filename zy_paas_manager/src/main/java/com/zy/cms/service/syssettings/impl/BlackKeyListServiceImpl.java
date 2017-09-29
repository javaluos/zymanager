package com.zy.cms.service.syssettings.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zy.cms.common.ExcelPOIUtil;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.mapper.master.BlackKeyListInfoMapper;
import com.zy.cms.service.syssettings.BlackKeyListService;
import com.zy.cms.util.DateUtil;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.vo.BlackKeyListInfo;
import com.zy.cms.vo.WebResult;

@Service("blackKeyListService")
public class BlackKeyListServiceImpl implements BlackKeyListService {

	private static final ZyLogger logger = ZyLogger.getLogger(BlackKeyListServiceImpl.class);

	@Autowired
	private BlackKeyListInfoMapper blackKeyListInfoMapper;

	public WebResult export(Map<String, Object> query, String realPath, String webUrl, String suffix) {
		// 定义操作结果
		WebResult webRs = new WebResult();
		try {
			String sheetName = "Sheet1"; // sheet名称
			String fileName = "/tmp" + "/blackkey/blackkey_" + DateUtil.getVDateTime() + "." + suffix; // 输出文件路径
			String savePath = (realPath + fileName).replace("\\", "/");
			String downUrl = webUrl + fileName;
			webRs.setData(fileName.replace("\\", "/"));
			logger.info("【敏感词导出】params={savePath:" + savePath + ",downUrl=" + downUrl + "}");
			List<Map<String, Object>> dataList = initData(query);// initExcelBody(query);
			if (suffix.equals("txt")) {
				savePath = savePath.replace(".xlsx", ".txt");
				StringBuilder sb = new StringBuilder();
				for (Map<String, Object> map : dataList) {
					sb.append(map.get("XH1") + "\t");
					sb.append(map.get("XH2") + "\t");
					sb.append(map.get("XH3") + "\t");
					sb.append(map.get("XH4") + "\t");
					sb.append(map.get("XH5") + "\t");
					sb.append(System.lineSeparator());
				}
				FileWriter fileWriter = new FileWriter(savePath);
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				bufferedWriter.write(sb.toString());
				bufferedWriter.flush();
				fileWriter.close();
				bufferedWriter.close();
			} else if (suffix.equals("xlsx")) {
				// 获得excel头,内容
				List<Map<String, Object>> headInfoList = initExcelHead(); // initExcelHead();
				// 执行创建excel业务,填充数据
				ExcelPOIUtil.exportExcel2FilePath(savePath, sheetName, headInfoList, dataList);
			} else {
				throw new RuntimeException("NOT FOUND THIS EXPORT_TYPE");
			}
			if (dataList.size() > 200000) {
				logger.info("【敏感词导出】导出失败，数据超过20万行.");
				webRs.setCode(-1);
				webRs.setMsg("导出失败，数据超过20万行");
				return webRs;
			}
		} catch (Exception e) {
			logger.error("【敏感词导出】失败 error=" + e.getMessage());
			webRs.setCode(-1);
			webRs.setMsg("敏感词导出数据失败.");
		}
		logger.info("【敏感词导出】result=" + JsonUtil.toJsonString(webRs));
		return webRs;
	}

	/**
	 * 构建短信通道跑量导出表头
	 *
	 * @return 返回表头列表
	 */
	public List<Map<String, Object>> initExcelHead() {

		List<Map<String, Object>> headInfoList = new ArrayList<Map<String, Object>>();
		Map<String, Object> itemMap = new HashMap<String, Object>();

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "敏感词");
		itemMap.put("columnWidth", 25);
		itemMap.put("dataKey", "XH1");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "所属行业");
		itemMap.put("columnWidth", 25);
		itemMap.put("dataKey", "XH2");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "备注");
		itemMap.put("columnWidth", 50);
		itemMap.put("dataKey", "XH3");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "创建时间");
		itemMap.put("columnWidth", 50);
		itemMap.put("dataKey", "XH4");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "更新时间");
		itemMap.put("columnWidth", 50);
		itemMap.put("dataKey", "XH5");
		headInfoList.add(itemMap);

		return headInfoList;
	}

	public List<Map<String, Object>> initData(Map<String, Object> query) {

		logger.info("【敏感词导出】查询参数 query=" + JsonUtil.toJsonString(query));
		// 定义填充数据列表
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();

		try {
			List<BlackKeyListInfo> list = blackKeyListInfoMapper.selectListByQuery(query);
			if (list != null && list.size() > 0) {
				Map<String, Object> dataItem = null;
				for (BlackKeyListInfo Info : list) {
					dataItem = new HashMap<String, Object>();

					dataItem.put("XH1", Info.getBlack_key());// 敏感词
					dataItem.put("XH2", Info.getIndustry());// 所属行业
					dataItem.put("XH3", Info.getRemark());// 备注
					dataItem.put("XH4", DateFormatUtils.format(Info.getCreateTime(), DateUtil.ISO_DATE_TIME_FORMAT));// 创建时间
					dataItem.put("XH5", DateFormatUtils.format(Info.getUpdateTime(), DateUtil.ISO_DATE_TIME_FORMAT));// 更新时间
					dataList.add(dataItem);
				}
			}
		} catch (Exception e) {
			logger.info("【敏感词导出】出现异常,异常是:" + e.getMessage());
		}
		return dataList;
	}
}
