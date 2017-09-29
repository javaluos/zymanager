package com.zy.cms.service.manager.impl.excel;

import com.zy.cms.common.CommonService;
import com.zy.cms.common.ExcelPOIUtil;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.service.manager.excel.SmsBlackListExcelService;
import com.zy.cms.service.manager.excel.SmsWhiteListExcelService;
import com.zy.cms.service.master.SmsWhiteListService;
import com.zy.cms.util.DateUtil;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.vo.SmsBlackListInfo;
import com.zy.cms.vo.SmsWhiteListInfo;
import com.zy.cms.vo.WebResult;
import com.zy.cms.vo.query.SmsBlackListQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 导出短信白名单列表
 * 
 * @author JasonXu
 * @date 2017-03-24
 *
 */
@Service("smsWhiteListExcelService")
public class SmsWhiteListExcelServiceImpl implements SmsWhiteListExcelService {

	private static final ZyLogger logger = ZyLogger.getLogger(SmsWhiteListExcelServiceImpl.class);

	@Resource
	private  CommonService commonService;
	
	@Resource
	private SmsWhiteListService smsWhiteListService;

	private String export_Tmp_Path ="/tmp";

	/**
	 * 导出短信通道跑量
	 */
	@Override
	public WebResult exportWhiteExcel(SmsBlackListQuery query, String realPath, String webUrl) {

		// 定义操作结果
		WebResult webRs = new WebResult();

		try {

			String sheetName = "短信白名单列表"; // sheet名称
			String fileName = export_Tmp_Path + "/cdr/smsBlackList_" + DateUtil.getVDateTime() + ".xlsx"; // 输出文件路径
			String savePath = (realPath + fileName).replace("\\", "/");
			String downUrl = webUrl + fileName;
			webRs.setData(fileName.replace("\\", "/"));
			logger.info("【短信白名单列表导出】params={savePath:" + savePath + ",downUrl=" + downUrl + "}");

			// 获得excel头,内容
			List<Map<String, Object>> headInfoList = initExcelHead();
			List<Map<String, Object>> dataList = initExcelBody(query);
			if (dataList.size() > 200000) {
				logger.info("【短信白名单列表导出】导出失败，数据超过20万行.");
				webRs.setCode(-1);
				webRs.setMsg("导出失败，数据超过20万行");
				return webRs;
			}

			// 执行创建excel业务,填充数据
			ExcelPOIUtil.exportExcel2FilePath(savePath, sheetName, headInfoList, dataList);

		} catch (Exception e) {

			logger.error("【短信白名单列表导出】失败 error=" + e.getMessage());
			webRs.setCode(-1);
			webRs.setMsg("导出数据失败.");
		}

		logger.info("【短信白名单列表导出】result=" + JsonUtil.toJsonString(webRs));
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
		

		itemMap.put("title", "序号");
		itemMap.put("columnWidth", 10);
		itemMap.put("dataKey", "XH1");
		headInfoList.add(itemMap);
		
		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "白名单号码");
		itemMap.put("columnWidth", 50);
		itemMap.put("dataKey", "XH2");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "所属客户");
		itemMap.put("columnWidth", 35);
		itemMap.put("dataKey", "XH3");
		headInfoList.add(itemMap);

		itemMap = new HashMap<String, Object>();
		itemMap.put("title", "加入时间");
		itemMap.put("columnWidth", 45);
		itemMap.put("dataKey", "XH4");
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
	private List<Map<String, Object>> initExcelBody(SmsBlackListQuery query) {

		logger.info("【短信白名单列表导出】查询参数 query=" + JsonUtil.toJsonString(query));
		// 定义填充数据列表
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		
		try {
			List<SmsWhiteListInfo> list = smsWhiteListService.queryListByEntity(query);
			if (list != null && list.size() > 0) {
				Map<String, Object> dataItem = null;
				int i=1;
				for (SmsWhiteListInfo smsWhiteListInfo : list) {
					dataItem = new HashMap<String, Object>();
					
					dataItem.put("XH1",i);//序列号
					dataItem.put("XH2",smsWhiteListInfo.getMobile() );//白名单号码
					dataItem.put("XH3",smsWhiteListInfo.getBusinessName() );//所属客户
					String createDate=DateUtil.formatDate(smsWhiteListInfo.getCreateTime(), "yyyy-MM-dd HH:mm:ss");
					if(createDate==null){
						createDate="";
					}
					dataItem.put("XH4",createDate);//加入时间
					dataList.add(dataItem);
					i++;
				}
			}
		} catch (Exception e) {
			logger.info("【短信白名单列表导出】出现异常,异常是:" + e.getMessage());
		}
		return dataList;
	}
}
