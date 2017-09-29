package com.zy.cms.service.manager.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.zy.cms.common.ExcelPOIUtil;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.mapper.manager.CdrFeeMonthSumMapper;
import com.zy.cms.service.manager.CdrFeeMonthSumService;
import com.zy.cms.util.DateUtil;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.vo.WebResult;
import com.zy.cms.vo.manager.CdrFeeMonthResult;
import com.zy.cms.vo.manager.CdrFeeMonthSum;
import com.zy.cms.vo.query.CdrFeeMonthSumQuery;
import com.zy.cms.vo.query.VoiceQuery;

/**
 * 月结账单
 * @author xu
 *
 */
@Service("cdrFeeMonthSumService")
@Transactional
public class CdrFeeMonthSumServiceImpl implements CdrFeeMonthSumService {
	private static final ZyLogger logger = ZyLogger.getLogger(CdrFeeMonthSumServiceImpl.class);

	@Autowired
	private CdrFeeMonthSumMapper cdrFeeMonthSumMapper;
	
	 private String export_Tmp_Path ="/tmp";
	
	public int deleteByPrimaryKey(Integer id){
		return cdrFeeMonthSumMapper.deleteByPrimaryKey(id);
	}

    public int insert(CdrFeeMonthSum record){
    	return cdrFeeMonthSumMapper.insert(record);
    }

    public int insertSelective(CdrFeeMonthSum record){
       return cdrFeeMonthSumMapper.insertSelective(record);
    }

    public CdrFeeMonthSum selectByPrimaryKey(Integer id){
    	return cdrFeeMonthSumMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(CdrFeeMonthSum record){
    	return cdrFeeMonthSumMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(CdrFeeMonthSum record){
    	return cdrFeeMonthSumMapper.updateByPrimaryKey(record);
    }
	
    public List<CdrFeeMonthSum> queryCdrFeeMonthByEntity(CdrFeeMonthSumQuery cdrFeeMonthSum){
    	return cdrFeeMonthSumMapper.queryCdrFeeMonthByEntity(cdrFeeMonthSum);
    }
    
    public List<CdrFeeMonthSum> queryExportFeeMonthByEntity(CdrFeeMonthSumQuery cdrFeeMonthSum){
    	return cdrFeeMonthSumMapper.queryExportFeeMonthByEntity(cdrFeeMonthSum);
    }
    public List<CdrFeeMonthSum> queryExportFeeDailyByEntity(CdrFeeMonthSumQuery cdrFeeMonthSum){
    	return cdrFeeMonthSumMapper.queryExportFeeDailyByEntity(cdrFeeMonthSum);
    }
    public int queryCdrFeeMonthAccount(CdrFeeMonthSumQuery cdrFeeMonthSum){
    	return cdrFeeMonthSumMapper.queryCdrFeeMonthAccount(cdrFeeMonthSum);
    }
    
    public List<CdrFeeMonthSum> queryCdrFeeDailyByEntity(CdrFeeMonthSumQuery cdrFeeMonthSum){
    	return cdrFeeMonthSumMapper.queryCdrFeeDailyByEntity(cdrFeeMonthSum);
    }
    
    public int queryCdrFeeDailyAccount(CdrFeeMonthSumQuery cdrFeeMonthSum){
    	return cdrFeeMonthSumMapper.queryCdrFeeDailyAccount(cdrFeeMonthSum);
    }
    
	@Override
	public CdrFeeMonthResult queryCdrMonthStatisticsCount(CdrFeeMonthSumQuery cdrFeeMonthSum) {
		return cdrFeeMonthSumMapper.queryCdrMonthStatisticsCount(cdrFeeMonthSum);
	} 
    
    /**
   	 * 导出月结详单
   	 * 
   	 * @param query条件查询
   	 * @realPath 文本上下文路径
   	 * @webUrl 文本上下文url
   	 * 
   	 * @return
   	 */
	@Override
	public WebResult exportMonthExcel(CdrFeeMonthSumQuery query, String realPath, String webUrl) {
		// 定义操作结果
		WebResult webRs = new WebResult();
		try {

			String sheetName = "月结账单详单"; // sheet名称
			String fileName = export_Tmp_Path + "/fee/cdrfeemonth_" + DateUtil.getVDateTime() + ".xls"; // 输出文件路径
			String savePath = (realPath + fileName).replace("\\", "/");
			String downUrl = webUrl + fileName;
			webRs.setData(fileName.replace("\\", "/"));
			logger.info("【月结账单详单导出】params={savePath:" + savePath + ",downUrl=" + downUrl + "}");

			// 获得excel头,内容
			List<Map<String, Object>> headInfoList =null; //initExcelHead();
			List<CdrFeeMonthSum> dataList = queryExportFeeMonthByEntity(query);//initExcelBody(query);
			if (dataList.size() > 200000) {
				logger.info("【月结账单详单导出】导出失败，数据超过20万行.");
				webRs.setCode(-1);
				webRs.setMsg("导出失败，数据超过20万行");
				return webRs;
			}

			// 执行创建excel业务,填充数据
			exportExcel2ReadTemplate(realPath+"/template/monthtemplate.xls",savePath, sheetName, headInfoList, dataList);
			
		} catch (Exception e) {
			logger.error("【月结账单导出】失败 error=" + e.getMessage());
			webRs.setCode(-1);
			webRs.setMsg("导出数据失败.");
		}

		logger.info("【月结账单详单导出】result=" + JsonUtil.toJsonString(webRs));
		return webRs;
	}
	
	 /**
   	 * 导出日结详单
   	 * 
   	 * @param query条件查询
   	 * @realPath 文本上下文路径
   	 * @webUrl 文本上下文url
   	 * 
   	 * @return
   	 */
	@Override
	public WebResult exportDayExcel(CdrFeeMonthSumQuery query, String realPath, String webUrl) {
		// 定义操作结果
		WebResult webRs = new WebResult();
		try {

			String sheetName = "日结账单详单"; // sheet名称
			String fileName = export_Tmp_Path + "/fee/cdrfeeday_" + DateUtil.getVDateTime() + ".xls"; // 输出文件路径
			String savePath = (realPath + fileName).replace("\\", "/");
			String downUrl = webUrl + fileName;
			webRs.setData(fileName.replace("\\", "/"));
			logger.info("【日结账单详单导出】params={savePath:" + savePath + ",downUrl=" + downUrl + "}");

			// 获得excel头,内容
			List<Map<String, Object>> headInfoList =null; //initExcelHead();
			List<CdrFeeMonthSum> dataList = queryExportFeeDailyByEntity(query);//initExcelBody(query);
			if (dataList.size() > 200000) {
				logger.info("【日结账单详单导出】导出失败，数据超过20万行.");
				webRs.setCode(-1);
				webRs.setMsg("导出失败，数据超过20万行");
				return webRs;
			}

			// 执行创建excel业务,填充数据
			exportExcel2ReadTemplate(realPath+"/template/daytemplate.xls",savePath, sheetName, headInfoList, dataList);
			
		} catch (Exception e) {
			logger.error("【日结账单导出】失败 error=" + e.getMessage());
			webRs.setCode(-1);
			webRs.setMsg("导出数据失败.");
		}

		logger.info("【日结账单详单导出】result=" + JsonUtil.toJsonString(webRs));
		return webRs;
	}
	
	public static void exportExcel2ReadTemplate(String templatepath, String filePath, String sheetName,
			List<Map<String, Object>> headInfoList, List<CdrFeeMonthSum> dataList) throws IOException {

		try {
			ExcelPOIUtil poiUtil = new ExcelPOIUtil();
			
			// 1.创建 Workbook
			HSSFWorkbook hssfWorkbook = null;
			if (templatepath != null && templatepath.trim().length() > 0) {
				hssfWorkbook = poiUtil.getHSSFWorkbook(templatepath);
			} else {
				hssfWorkbook = poiUtil.getHSSFWorkbook();
			}

			// 2.获取 Sheet
			HSSFSheet hssfSheet =hssfWorkbook.getSheet(sheetName);

			// 3.写入 head
			//poiUtil.writeHeader(hssfWorkbook, hssfSheet, headInfoList);

			// 4.写入内容
			writeContent(hssfWorkbook, hssfSheet, 2, headInfoList, dataList);

			// 5.保存文件到filePath中
			poiUtil.write2FilePath(hssfWorkbook, filePath);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 写入内容部分
	 * 
	 * @param hssfWorkbook
	 * @param hssfSheet
	 * @param startIndex
	 *            从1开始，多次调用需要加上前一次的dataList.size()
	 * @param headInfoList
	 *            List<Map<String, Object>> key: title 列标题 columnWidth 列宽
	 *            dataKey 列对应的 dataList item key
	 * @param dataList
	 */
	public static void writeContent(HSSFWorkbook hssfWorkbook, HSSFSheet hssfSheet, int startIndex,
			List<Map<String, Object>> headInfoList, List<CdrFeeMonthSum> dataList) {
		
		HSSFRow r = null;
		HSSFCell c = null;

		// 处理数据
		CdrFeeMonthSum dataItem = null;
		for (int i = 0, rownum = startIndex, len = (startIndex + dataList.size()); rownum < len; i++, rownum++) {
			r = hssfSheet.createRow(rownum);
			r.setHeightInPoints(16);
			dataItem = dataList.get(i);
			
			c = r.createCell(0);
			c.setCellValue(dataItem.getBusinessName());//客户名称
			
			c = r.createCell(1);
			c.setCellValue(dataItem.getMerchantPhone());//客户账号
			
			c = r.createCell(2);
			String dateTime=DateUtil.parseDateFromYY(dataItem.getDateTime());
			c.setCellValue(dateTime);//时间
			
			c = r.createCell(3);
			c.setCellValue(calcMul(dataItem.getSumFeeBs8()));//短信通知
			
			c = r.createCell(4);
			c.setCellValue(calcMul(dataItem.getFeeRate8()));//单价
			
			c = r.createCell(5);
			c.setCellValue(dataItem.getFeeCount8());//计费条数
			
			c = r.createCell(6);
			c.setCellValue(calcMul(dataItem.getSumFeeBs9()));//短信验证码
			
			c = r.createCell(7);
			c.setCellValue(calcMul(dataItem.getFeeRate9()));//单价
			
			c = r.createCell(8);
			c.setCellValue(dataItem.getFeeCount9());//计费条数
			
			
			c = r.createCell(9);
			c.setCellValue(calcMul(dataItem.getSumFeeBs11()));//短信营销
			
			c = r.createCell(10);
			c.setCellValue(calcMul(dataItem.getFeeRate11()));//单价
			
			c = r.createCell(11);
			c.setCellValue(dataItem.getFeeCount11());//计费条数
			
			
			c = r.createCell(12);
			c.setCellValue(calcMul(dataItem.getSumFeeBs5()));//语音验证》金额  5
			
			c = r.createCell(13);
			c.setCellValue(calcMul(dataItem.getFeeRate5()));//单价
			
			c = r.createCell(14);
			c.setCellValue(dataItem.getFeeCount5());//计费条数
			
			c = r.createCell(15);
			c.setCellValue(dataItem.getFeeTime5());//计费时长
			
			c = r.createCell(16);
			c.setCellValue(calcMul(dataItem.getSumFeeBs4()));//语音通知》金额 4
			
			c = r.createCell(17);
			c.setCellValue(calcMul(dataItem.getFeeRate4()));//单价
			
			c = r.createCell(18);
			c.setCellValue(dataItem.getFeeCount4());//计费条数
			
			c = r.createCell(19);
			c.setCellValue(dataItem.getFeeTime4());//计费时长
			
			c = r.createCell(20);
			c.setCellValue(calcMul(dataItem.getSumFeeBs1()));//回拨》金额 1
			
			c = r.createCell(21);
			c.setCellValue(calcMul(dataItem.getFeeRate1()));//单价
			
			c = r.createCell(22);
			c.setCellValue(dataItem.getFeeCount1());//计费条数
			
			c = r.createCell(23);
			c.setCellValue(dataItem.getFeeTime1());//计费时长
			
			c = r.createCell(24);
			c.setCellValue(calcMul(dataItem.getSumFeeBs3()));//直拨》金额 3
			
			c = r.createCell(25);
			c.setCellValue(calcMul(dataItem.getFeeRate3()));//单价
			
			c = r.createCell(26);
			c.setCellValue(dataItem.getFeeCount3());//计费条数
			
			c = r.createCell(27);
			c.setCellValue(dataItem.getFeeTime3());//计费时长
			
			c = r.createCell(28);
			c.setCellValue(calcMul(dataItem.getSumFeeBs2()));//号码卫士》金额 2
			
			c = r.createCell(29);
			c.setCellValue(calcMul(dataItem.getFeeRate2()));//单价
			
			c = r.createCell(30);
			c.setCellValue(dataItem.getFeeTime2());//计费时长
			
			c = r.createCell(31);
			c.setCellValue(calcMul(dataItem.getSumFeeBs10()));//平台通话录音》金额 9
			
			c = r.createCell(32);
			c.setCellValue(dataItem.getFeeTime10());//计费时长
			
			c = r.createCell(33);
			c.setCellValue(calcMul(dataItem.getSumFeeBs10()));//平台录音存储》金额 10
			
			c = r.createCell(34);
			c.setCellValue(dataItem.getFeeCount10());//计费容量
			
			c = r.createCell(35);
			int sum=dataItem.getSumFeeBs1()+dataItem.getSumFeeBs2()+dataItem.getSumFeeBs3()+dataItem.getSumFeeBs4()+dataItem.getSumFeeBs5()+
					dataItem.getSumFeeBs6()+dataItem.getSumFeeBs7()+dataItem.getSumFeeBs8()+dataItem.getSumFeeBs9()+dataItem.getSumFeeBs10()+ dataItem.getSumFeeBs11();
			c.setCellValue(calcMul(sum));//合计
		}

	}
    
	
	public static double calcMul(int d1){  
		BigDecimal b1=new BigDecimal(Double.toString(d1)); 
		BigDecimal b3=new BigDecimal(10000);
        return b1.divide(b3).doubleValue();  
          
    }
}
