package com.zy.cms.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Sequence {

	private static AtomicInteger seq = new AtomicInteger(0);

	/**
	 * 得到当前日期/时间字符串
	 * 
	 * @return 返回日期/时间字符串
	 */
	public static String getNowDateTimeString(String type) {
		Date date = new Date();
		SimpleDateFormat formattxt = new SimpleDateFormat(type);
		return formattxt.format(date);
	}

	public static String getDateTimeString(Date date,String type){
		SimpleDateFormat formattxt = new SimpleDateFormat(type);
		return formattxt.format(date);
	}
	
	public static String getOrdersId(String uid, String brand_no, String src) {
		if (brand_no == null || "".equals(brand_no)) {
			brand_no = "888";
		}
		if (src == null || "".equals(src)) {
			src = "88";
		}
		StringBuffer sbstr = new StringBuffer();
		seq.compareAndSet(999, 0);
		sbstr.append(brand_no);
		sbstr.append(src);
		sbstr.append(Sequence.getNowDateTimeString("yyyyMMddHHmmssSS"));
		sbstr.append(Integer.toString(seq.getAndAdd(1)));
		sbstr.append(uid);
		return sbstr.toString();
	}

//	 public static void main(String[] args) {
//	  Sequence s=new Sequence();
//	 
//	 System.out.println("1000"+s.getNowDateTimeString("yyyyMMddHHmmssSSSS")+"100000");
//	 // 1000201109161406011730100000
//	 // 1000201109191130070007100000
//	 // 10020120105100638819081029203
//	 System.out.println(Sequence.getOrdersId("81029203","10","3"));
////	 System.out.println((Sequence.getOrdersId("123")).substring(22));
//	 System.out.println((Sequence.getNowDateTimeString("yyyy-MM-dd HH:mm:ss")));
//	 }
}
