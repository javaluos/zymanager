package com.zy.cms.common;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * poi 导出excel工具类
 *
 * @author allen.yuan
 * @date 2016-11-05
 */
public class ExcelPOIUtil {

	/**
	 * 1.创建 workbook
	 *
	 * @return
	 */
	public HSSFWorkbook getHSSFWorkbook() {
		return new HSSFWorkbook();
	}

	public XSSFWorkbook getXSSFWorkbook() {
		return new XSSFWorkbook();
	}

	public SXSSFWorkbook getSXSSFWorkbook() {
		return new SXSSFWorkbook(100);
	}

	/**
	 * 1.创建 workbook(支持模板)
	 *
	 * @return
	 */
	public HSSFWorkbook getHSSFWorkbook(String templatePath) {

		try {
			return new HSSFWorkbook(new FileInputStream(new File(templatePath)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new HSSFWorkbook();
	}

	/**
	 * 2.创建 sheet
	 *
	 * @param hssfWorkbook
	 * @param sheetName
	 *            sheet 名称
	 * @return
	 */
	public HSSFSheet getHSSFSheet(HSSFWorkbook hssfWorkbook, String sheetName) {
		return hssfWorkbook.createSheet(sheetName);
	}

	public XSSFSheet getXSSFSheet(XSSFWorkbook xssfWorkbook, String sheetName) {
		return xssfWorkbook.createSheet(sheetName);
	}

	public Sheet getSXSSFWorkSheet(SXSSFWorkbook xssfWorkbook, String sheetName) {
		return xssfWorkbook.createSheet(sheetName);
	}

	/**
	 * 3.写入表头信息
	 *
	 * @param hssfWorkbook
	 * @param hssfSheet
	 * @param headInfoList
	 *            List<Map<String, Object>> key: title 列标题 columnWidth 列宽
	 *            dataKey 列对应的 dataList item key
	 */
	@SuppressWarnings("deprecation")
	public void writeHeader(HSSFWorkbook hssfWorkbook, HSSFSheet hssfSheet, List<Map<String, Object>> headInfoList) {

		HSSFCellStyle cs = hssfWorkbook.createCellStyle();
		HSSFFont font = hssfWorkbook.createFont();
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(font.BOLDWEIGHT_BOLD);
		cs.setFont(font);
		cs.setAlignment(cs.ALIGN_CENTER);

		HSSFRow r = hssfSheet.createRow(0);
		r.setHeight((short) 380);
		HSSFCell c = null;
		Map<String, Object> headInfo = null;

		// 处理excel表头
		for (int i = 0, len = headInfoList.size(); i < len; i++) {
			headInfo = headInfoList.get(i);
			c = r.createCell(i);
			c.setCellValue(headInfo.get("title").toString());
			c.setCellStyle(cs);
			if (headInfo.containsKey("columnWidth")) {
				hssfSheet.setColumnWidth(i, (short) (((Integer) headInfo.get("columnWidth") * 8) / ((double) 1 / 20)));
			}
		}
	}

	/**
	 * 3.写入表头信息2
	 *
	 * @param hssfWorkbook
	 * @param hssfSheet
	 * @param headInfoList
	 *            List<Map<String, Object>> key: title 列标题 columnWidth 列宽
	 *            dataKey 列对应的 dataList item key
	 */
	@SuppressWarnings("deprecation")
	public void writeHeader2(SXSSFWorkbook hssfWorkbook, Sheet hssfSheet, List<Map<String, Object>> headInfoList) {

		CellStyle cs = hssfWorkbook.createCellStyle();
		Font font = hssfWorkbook.createFont();
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(font.BOLDWEIGHT_BOLD);
		cs.setFont(font);
		cs.setAlignment(cs.ALIGN_CENTER);

		Row r = hssfSheet.createRow(0);
		r.setHeight((short) 380);
		Cell c = null;
		Map<String, Object> headInfo = null;

		// 处理excel表头
		for (int i = 0, len = headInfoList.size(); i < len; i++) {
			headInfo = headInfoList.get(i);
			c = r.createCell(i);
			c.setCellValue(headInfo.get("title").toString());
			c.setCellStyle(cs);
			if (headInfo.containsKey("columnWidth")) {
				hssfSheet.setColumnWidth(i, (short) (((Integer) headInfo.get("columnWidth") * 8) / ((double) 1 / 20)));
			}
		}
	}

	public void writeHeader(XSSFWorkbook xssfWorkbook, XSSFSheet xssfSheet, List<Map<String, Object>> headInfoList) {

		XSSFCellStyle cs = xssfWorkbook.createCellStyle();
		XSSFFont font = xssfWorkbook.createFont();
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(font.BOLDWEIGHT_BOLD);
		cs.setFont(font);
		cs.setAlignment(cs.ALIGN_CENTER);

		XSSFRow r = xssfSheet.createRow(0);
		r.setHeight((short) 380);
		XSSFCell c = null;
		Map<String, Object> headInfo = null;

		// 处理excel表头
		for (int i = 0, len = headInfoList.size(); i < len; i++) {
			headInfo = headInfoList.get(i);
			c = r.createCell(i);
			c.setCellValue(headInfo.get("title").toString());
			c.setCellStyle(cs);
			if (headInfo.containsKey("columnWidth")) {
				xssfSheet.setColumnWidth(i, (short) (((Integer) headInfo.get("columnWidth") * 8) / ((double) 1 / 20)));
			}
		}
	}

	/**
	 * 4.写入内容部分
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
	public void writeContent(HSSFWorkbook hssfWorkbook, HSSFSheet hssfSheet, int startIndex,
			List<Map<String, Object>> headInfoList, List<Map<String, Object>> dataList) {
		Map<String, Object> headInfo = null;
		HSSFRow r = null;
		HSSFCell c = null;

		// 处理数据
		Map<String, Object> dataItem = null;
		Object v = null;
		for (int i = 0, rownum = startIndex, len = (startIndex + dataList.size()); rownum < len; i++, rownum++) {
			r = hssfSheet.createRow(rownum);
			r.setHeightInPoints(16);
			dataItem = dataList.get(i);
			for (int j = 0, jlen = headInfoList.size(); j < jlen; j++) {
				headInfo = headInfoList.get(j);
				c = r.createCell(j);
				v = dataItem.get(headInfo.get("dataKey").toString());

				if (v instanceof String) {
					c.setCellValue((String) v);
				} else if (v instanceof Boolean) {
					c.setCellValue((Boolean) v);
				} else if (v instanceof Calendar) {
					c.setCellValue((Calendar) v);
				} else if (v instanceof Double) {
					c.setCellValue((Double) v);
				} else if (v instanceof Integer || v instanceof Long || v instanceof Short || v instanceof Float) {
					c.setCellValue(Double.parseDouble(v.toString()));
				} else if (v instanceof HSSFRichTextString) {
					c.setCellValue((HSSFRichTextString) v);
				} else {
					c.setCellValue(v.toString());
				}
			}
		}

	}

	public void writeContent(XSSFWorkbook xssfWorkbook, XSSFSheet xssfSheet, int startIndex,
			List<Map<String, Object>> headInfoList, List<Map<String, Object>> dataList) {
		Map<String, Object> headInfo = null;
		XSSFRow r = null;
		XSSFCell c = null;

		// 处理数据
		Map<String, Object> dataItem = null;
		Object v = null;
		for (int i = 0, rownum = startIndex, len = (startIndex + dataList.size()); rownum < len; i++, rownum++) {
			r = xssfSheet.createRow(rownum);
			r.setHeightInPoints(16);
			dataItem = dataList.get(i);
			for (int j = 0, jlen = headInfoList.size(); j < jlen; j++) {
				headInfo = headInfoList.get(j);
				c = r.createCell(j);
				v = dataItem.get(headInfo.get("dataKey").toString());

				if (v instanceof String) {
					c.setCellValue((String) v);
				} else if (v instanceof Boolean) {
					c.setCellValue((Boolean) v);
				} else if (v instanceof Calendar) {
					c.setCellValue((Calendar) v);
				} else if (v instanceof Double) {
					c.setCellValue((Double) v);
				} else if (v instanceof Integer || v instanceof Long || v instanceof Short || v instanceof Float) {
					c.setCellValue(Double.parseDouble(v.toString()));
				} else if (v instanceof HSSFRichTextString) {
					c.setCellValue((HSSFRichTextString) v);
				} else {
					c.setCellValue(v.toString());
				}
			}
		}

	}

	public void writeSendRecordContent(SXSSFWorkbook xssfWorkbook, Sheet xssfSheet, int startIndex,
			List<Map<String, Object>> headInfoList, List<Map<String, Object>> dataList) throws IOException {
		try {

			Row r = null;

			// 处理数据
			Map<String, Object> dataItem = null;

			int len = startIndex + dataList.size();
			int rowaccess = 100;// 内存中缓存记录行数
			for (int rownum = startIndex; rownum < len; rownum++) {
				r = xssfSheet.createRow(rownum);
				r.setHeightInPoints(16);
				dataItem = dataList.get(rownum - 1);
				r.createCell(0).setCellValue(dataItem.get("XH1") == null ? "" : dataItem.get("XH1").toString());
				r.createCell(1).setCellValue(dataItem.get("XH2") == null ? "" : dataItem.get("XH2").toString());
				r.createCell(2).setCellValue(dataItem.get("XH3") == null ? "" : dataItem.get("XH3").toString());
				r.createCell(3).setCellValue(dataItem.get("XH4") == null ? "" : dataItem.get("XH4").toString());
				r.createCell(4).setCellValue(dataItem.get("XH5") == null ? "" : dataItem.get("XH5").toString());
				r.createCell(5).setCellValue(dataItem.get("XH6") == null ? "" : dataItem.get("XH6").toString());
				r.createCell(6).setCellValue(dataItem.get("XH7") == null ? "" : dataItem.get("XH7").toString());
				r.createCell(7).setCellValue(dataItem.get("XH8") == null ? "" : dataItem.get("XH8").toString());
				r.createCell(8).setCellValue(dataItem.get("XH9") == null ? "" : dataItem.get("XH9").toString());
				r.createCell(9).setCellValue(dataItem.get("XH10") == null ? "" : dataItem.get("XH10").toString());
				r.createCell(10).setCellValue(dataItem.get("XH11") == null ? "" : dataItem.get("XH11").toString());
				r.createCell(11).setCellValue(dataItem.get("XH12") == null ? "" : dataItem.get("XH12").toString());
				r.createCell(12).setCellValue(dataItem.get("XH13") == null ? "" : dataItem.get("XH13").toString());
				r.createCell(13).setCellValue(dataItem.get("XH14") == null ? "" : dataItem.get("XH14").toString());

				// 每当行数达到设置的值就刷新数据到硬盘,以清理内存
				if (rownum % rowaccess == 0) {
					((SXSSFSheet) xssfSheet).flushRows();
				}
			}
		} catch (IOException e) {
			throw new IOException();
		}

	}

	/**
	 * 输出文件
	 *
	 * @param hssfWorkbook
	 * @param filePath
	 * @throws IOException
	 */
	public void write2FilePath(HSSFWorkbook hssfWorkbook, String filePath) throws IOException {

		FileOutputStream fileOut = null;
		try {

			// 判断目录不存在则创建
			File f = new File(filePath);
			if (!f.getParentFile().exists()) {
				f.getParentFile().mkdirs();
			}
			fileOut = new FileOutputStream(filePath);
			hssfWorkbook.write(fileOut);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (fileOut != null) {
				fileOut.close();
			}
		}
	}

	public void write2FilePath(XSSFWorkbook xssfWorkbook, String filePath) throws IOException {

		FileOutputStream fileOut = null;
		try {

			// 判断目录不存在则创建
			File f = new File(filePath);
			if (!f.getParentFile().exists()) {
				f.getParentFile().mkdirs();
			}
			fileOut = new FileOutputStream(filePath);
			xssfWorkbook.write(fileOut);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (fileOut != null) {
				fileOut.close();
			}
		}
	}

	public void write2BufferFilePath(XSSFWorkbook xssfWorkbook, String filePath) throws IOException {

		FileOutputStream fileOut = null;
		BufferedOutputStream bos = null;
		try {

			// 判断目录不存在则创建
			File f = new File(filePath);
			if (!f.getParentFile().exists()) {
				f.getParentFile().mkdirs();
			}
			fileOut = new FileOutputStream(filePath);
			bos = new BufferedOutputStream(fileOut);
			xssfWorkbook.write(bos);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (bos != null) {
				bos.close();
			}
		}
	}

	public void write2BufferFilePath2(SXSSFWorkbook xssfWorkbook, String filePath) throws IOException {

		FileOutputStream fileOut = null;
		BufferedOutputStream bos = null;
		try {

			// 判断目录不存在则创建
			File f = new File(filePath);
			if (!f.getParentFile().exists()) {
				f.getParentFile().mkdirs();
			}
			fileOut = new FileOutputStream(filePath);
			bos = new BufferedOutputStream(fileOut);
			xssfWorkbook.write(bos);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (bos != null) {
				bos.close();
			}
		}
	}

	/**
	 * 导出excel code example: List<Map<String, Object>> headInfoList = new
	 * ArrayList<Map<String,Object>>(); Map<String, Object> itemMap = new
	 * HashMap<String, Object>(); itemMap.put("title", "序号1");
	 * itemMap.put("columnWidth", 25); itemMap.put("dataKey", "XH1");
	 * headInfoList.add(itemMap);
	 * <p>
	 * itemMap = new HashMap<String, Object>(); itemMap.put("title", "序号2");
	 * itemMap.put("columnWidth", 50); itemMap.put("dataKey", "XH2");
	 * headInfoList.add(itemMap);
	 * <p>
	 * itemMap = new HashMap<String, Object>(); itemMap.put("title", "序号3");
	 * itemMap.put("columnWidth", 25); itemMap.put("dataKey", "XH3");
	 * headInfoList.add(itemMap);
	 * <p>
	 * List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
	 * Map<String, Object> dataItem = null; for(int i=0; i < 100; i++){ dataItem
	 * = new HashMap<String, Object>(); dataItem.put("XH1", "data" + i);
	 * dataItem.put("XH2", 88888888f); dataItem.put("XH3", "脉兜V5..");
	 * dataList.add(dataItem); }
	 * POIUtil.exportExcel2FilePath("F:\\temp\\customer2.xls","test sheet 1",
	 * headInfoList, dataList);
	 *
	 * @param filePath
	 *            文件存储路径， 如：f:/a.xls
	 * @param sheetName
	 *            sheet名称
	 * @param headInfoList
	 *            List<Map<String, Object>> key: title 列标题 columnWidth 列宽
	 *            dataKey 列对应的 dataList item key
	 * @param dataList
	 *            List<Map<String, Object>> 导出的数据
	 * @throws java.io.IOException
	 */
	public static void exportExcel2FilePath(String filePath, String sheetName, List<Map<String, Object>> headInfoList,
			List<Map<String, Object>> dataList) throws IOException {

		ExcelPOIUtil poiUtil = new ExcelPOIUtil();

		// 1.创建 Workbook
		XSSFWorkbook xssfWorkbook = poiUtil.getXSSFWorkbook();

		// 2.创建 Sheet
		XSSFSheet xssfSheet = poiUtil.getXSSFSheet(xssfWorkbook, sheetName);

		// 3.写入 head
		poiUtil.writeHeader(xssfWorkbook, xssfSheet, headInfoList);

		// 4.写入内容
		poiUtil.writeContent(xssfWorkbook, xssfSheet, 1, headInfoList, dataList);

		// 5.保存文件到filePath中
		poiUtil.write2FilePath(xssfWorkbook, filePath);

	}

	public static void exportSmsSendExcel(String filePath, String sheetName, List<Map<String, Object>> headInfoList,
			List<Map<String, Object>> dataList) throws IOException {

		ExcelPOIUtil poiUtil = new ExcelPOIUtil();

		// 1.创建 Workbook
		SXSSFWorkbook xssfWorkbook = poiUtil.getSXSSFWorkbook();

		// 2.创建 Sheet
		Sheet xssfSheet = poiUtil.getSXSSFWorkSheet(xssfWorkbook, sheetName);

		// 3.写入 head
		poiUtil.writeHeader2(xssfWorkbook, xssfSheet, headInfoList);

		// 4.写入内容
		poiUtil.writeSendRecordContent(xssfWorkbook, xssfSheet, 1, headInfoList, dataList);

		// 5.保存文件到filePath中
		poiUtil.write2BufferFilePath2(xssfWorkbook, filePath);

	}

	/**
	 * 可合并单元格方法
	 *
	 * @param filePath
	 * @param sheetName
	 * @param headInfoList
	 * @param dataList
	 * @throws IOException
	 */
	public static void exportExcelMerge(String filePath, String sheetName, List<Map<String, Object>> headInfoList,
			List<Map<String, Object>> dataList) throws IOException {

		ExcelPOIUtil poiUtil = new ExcelPOIUtil();

		// 1.创建 Workbook
		XSSFWorkbook xssfWorkbook = poiUtil.getXSSFWorkbook();

		// 2.创建 Sheet
		XSSFSheet xssfSheet = poiUtil.getXSSFSheet(xssfWorkbook, sheetName);

		// 表示合并A2,AN
		xssfSheet.addMergedRegion(new CellRangeAddress(1, // first row (0-based)
				(dataList.size() + 1), // last row (0-based)
				(short) 0, // first column (0-based)
				(short) 0 // last column (0-based)
		));

		// 表示合并B2,AN
		xssfSheet.addMergedRegion(new CellRangeAddress(1, // first row (0-based)
				(dataList.size() + 1), // last row (0-based)
				(short) 1, // first column (0-based)
				(short) 1 // last column (0-based)
		));

		// 表示合并C2,AN
		xssfSheet.addMergedRegion(new CellRangeAddress(1, // first row (0-based)
				(dataList.size() + 1), // last row (0-based)
				(short) 2, // first column (0-based)
				(short) 2 // last column (0-based)
		));

		// 3.写入 head
		poiUtil.writeHeader(xssfWorkbook, xssfSheet, headInfoList);

		// 4.写入内容
		poiUtil.writeContent(xssfWorkbook, xssfSheet, 1, headInfoList, dataList);

		// 5.保存文件到filePath中
		poiUtil.write2FilePath(xssfWorkbook, filePath);

	}

	public static void exportExcel2ReadTemplate(String templatepath, String filePath, String sheetName,
			List<Map<String, Object>> headInfoList, List<Map<String, Object>> dataList) throws IOException {

		ExcelPOIUtil poiUtil = new ExcelPOIUtil();

		// 1.创建 Workbook
		HSSFWorkbook hssfWorkbook = null;
		if (templatepath != null && templatepath.trim().length() > 0) {
			hssfWorkbook = poiUtil.getHSSFWorkbook(templatepath);
		} else {
			hssfWorkbook = poiUtil.getHSSFWorkbook();
		}

		// 2.创建 Sheet
		HSSFSheet hssfSheet = poiUtil.getHSSFSheet(hssfWorkbook, sheetName);

		// 3.写入 head
		// poiUtil.writeHeader(hssfWorkbook, hssfSheet, headInfoList);

		// 4.写入内容
		poiUtil.writeContent(hssfWorkbook, hssfSheet, 1, headInfoList, dataList);

		// 5.保存文件到filePath中
		poiUtil.write2FilePath(hssfWorkbook, filePath);
	}

}
