package com.zy.cms.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.zy.cms.common.ZyLogger;

/**
 * 
 * 类说明: 日期工具类.
 * 
 * 类用途：提供日期操作的各种方法
 * 
 * <pre>
 * 修改日期      修改人    修改原因
 * 2013-04-13    qingwu   新建
 * </pre>
 */
public class DateUtil {

	private final static ZyLogger logger = ZyLogger.getLogger(DateUtil.class);

	/**
	 * 默认的日期格式,yyyy-MM-dd.
	 */
	public static final String ISO_DATE_FORMAT = "yyyy-MM-dd";
	/**
	 * 默认的日期格式,yyyy/MM/dd
	 */
	public static final String ISO_DATE_FORMAT1 = "yyyy/MM/dd";
	/**
	 * 默认的日期格式,yyyy-MM.
	 */
	public static final String DATE_MONTH_FORMAT = "yyyy-MM";

	/**
	 * 数字格式的日期格式,yyyyMMdd.
	 */
	public static final String NUMBER_DATE_FORMAT = "yyyyMMdd";

	/**
	 * 数字格式的时间字符串,HHmmss.
	 */
	public static final String NUMBER_TIME_FORMAT = "HHmmss";

	/**
	 * 数字格式的日期时间字符串, yyyyMMddHHmmss.
	 */
	public static final String NUMBER_DATE_TIME_FORMAT = "yyyyMMddHHmmss";

	/**
	 * 默认的日期时间格式,yyyy-MM-dd' 'HH:mm:ss.
	 */
	public static final String ISO_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 默认的日期时间格式,yyyy-MM-dd' 'HH:mm.
	 */
	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";
	/**
	 * 默认的日期时间格式,yyyy-MM-dd' 'HH.
	 */
	public static final String DATE_TIME_HOUR_FORMAT = "yyyy-MM-dd HH";
	/**
	 * 默认的日期时间格式,HH
	 */
	public static final String DATE_HOUR_FORMAT = "HH";
	/**
	 * 默认的日期时间格式,HHmm
	 */
	public static final String DATE_HOUR_MM_FORMAT = "HHmm";
	/**
	 * 默认的日期时间格式,HHmm
	 */
	public static final String DATE_HOUR_MM_FORMAT1 = "HH:mm";

	/**
	 * 
	 * 转换日期格式.
	 * 
	 * @param date
	 *            字符型的日期
	 * @param oldFormat
	 *            原始的日期格式
	 * @param newFormat
	 *            新的日期格式
	 * @return 新的日期字符串
	 * 
	 *         <pre>
	 * 修改日期        修改人    修改原因
	 * 2010-12-20        新建
	 *         </pre>
	 */
	public static String transformDateFormat(String date, String oldFormat, String newFormat) {
		if (date == null || date.trim().length() == 0) {
			return null;
		}
		Date tempDate = parseDate(date, oldFormat);
		return formatDate(tempDate, newFormat);
	}

	/**
	 * 
	 * 解析日期,以默认日期格式yyyy-MM-dd进行解析.<br>
	 * 相关方法:{@link #parseDate(String, String)}
	 * 
	 * @param stringDate
	 *            日期字符串
	 * @return 日期对象
	 * 
	 *         <pre>
	 * 修改日期      修改人    修改原因
	 * 2010-11-15        新建
	 * 2010-12-07        修改注释和注释格式
	 *         </pre>
	 * 
	 */
	public static Date parseDate(String stringDate) {
		return parseDate(stringDate, ISO_DATE_FORMAT);
	}

	/**
	 * 
	 * 解析日期,根据指定的格式进行解析.<br>
	 * 如果解析错误,则返回null
	 * 
	 * @param stringDate
	 *            日期字符串
	 * @param format
	 *            日期格式
	 * @return 日期类型
	 * 
	 *         <pre>
	 * 修改日期      修改人    修改原因
	 * 2010-11-15        整理
	 * 2010-12-07        修改注释和注释格式
	 *         </pre>
	 * 
	 */
	public static Date parseDate(String stringDate, String format) {
		if (stringDate == null) {
			return null;
		}

		try {
			return DateUtils.parseDate(stringDate, new String[] { format });
		} catch (ParseException e) {
			logger.error("解析日期异常[" + stringDate + ":" + format + "]", e);
		}

		return null;
	}

	/**
	 * 
	 * 解析日期,以所指定的日期格式集合进行解析.<br>
	 * <p>
	 * <li>如果满足其中一个日期格式,解析并且返回<br>
	 * <li>如果没解析成功或者解析错误,则返回null
	 * </p>
	 * 
	 * @param stringDate
	 *            日期字符串
	 * @param formates
	 *            日期格式的集合
	 * @return 日期类型
	 * 
	 *         <pre>
	 * 修改日期      修改人    修改原因
	 * 2010-11-15        整理
	 *         </pre>
	 * 
	 */
	public static Date parseDate(String stringDate, Collection<String> formates) {
		if (formates == null || formates.size() == 0) {
			throw new IllegalStateException("Date format not set.");
		}

		try {
			return DateUtils.parseDate(stringDate, formates.toArray(new String[formates.size()]));
		} catch (Exception e) {
			logger.error("日期解析错误", e);
		}

		return null;
	}

	/**
	 * 
	 * 以默认的格式"yyyy-MM-dd"格式化日期.
	 * 
	 * @param srcDate
	 *            源日期
	 * @return 格式化后的日期字符串
	 * 
	 *         <pre>
	 * 修改日期      修改人    修改原因
	 * 2010-11-15        整理
	 *         </pre>
	 * 
	 */
	public static String formatDate(Date srcDate) {
		return formatDate(srcDate, ISO_DATE_FORMAT);
	}

	/**
	 * 将yyyymmdd的日期转换成yyyy-mm-dd
	 * 
	 * @param srcDate
	 * @return
	 */
	public static String formatDate(String srcDate) {
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");
		String src = null;
		try {
			src = sf2.format(sf1.parse(srcDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return src;
	}

	/**
	 * 以指定的格式格式化日期.
	 * 
	 * @param srcDate
	 *            源日期
	 * @param pattern
	 *            格式
	 * @return 格式化的日期字符串
	 * 
	 *         <pre>
	 * 修改日期      修改人    修改原因
	 * 2010-11-15        新建
	 *         </pre>
	 * 
	 */
	public static String formatDate(Date srcDate, String pattern) {
		if (srcDate == null) {
			return null;
		}
		return DateFormatUtils.format(srcDate, pattern);
	}

	/**
	 * 格式化time获得指定格式的字符串.
	 * 
	 * @param time
	 *            时间
	 * @param pattern
	 *            格式
	 * @return 格式化后的字符串
	 * @author zhufu
	 * @version 2013年10月18日 上午9:40:01
	 */
	private static String formatDate(long time, String pattern) {
		return DateFormatUtils.format(time, pattern);
	}

	/**
	 * 格式化date获得yyyyMMddHHmmss格式的字符串.
	 * 
	 * @param date
	 *            时间
	 * @return 格式化后的字符串
	 * @author zhufu
	 * @version 2013年10月18日 上午9:41:42
	 */
	public static String formatDateyyyyMMddHHmmss(Date date) {
		return formatDate(date, NUMBER_DATE_TIME_FORMAT);
	}

	/**
	 * 格式化time获得yyyyMMddHHmmss格式的字符串.
	 * 
	 * @param time
	 *            时间
	 * @return 格式化后的字符串
	 * @author zhufu
	 * @version 2013年10月18日 上午9:42:25
	 */
	public static String formatDateyyyyMMddHHmmss(long time) {
		return formatDate(time, NUMBER_DATE_TIME_FORMAT);
	}

	/**
	 * 为指定日期添加N天.
	 * 
	 * @param date
	 *            指定日期
	 * @param amount
	 *            增加天数
	 * @return 计算后的日期
	 * 
	 *         <pre>
	 * 修改日期      修改人    修改原因
	 * 2010-11-15        新建
	 *         </pre>
	 */
	public static Date addDays(Date date, int amount) {
		return DateUtils.addDays(date, amount);
	}

	/**
	 * 为指定日期添加N月.
	 * 
	 * @param date
	 *            指定日期
	 * @param amount
	 *            增加月数
	 * @return 计算后的日期
	 * 
	 *         <pre>
	 * 修改日期      修改人    修改原因
	 * 2010-11-15        新建
	 *         </pre>
	 */
	public static Date addMonths(Date date, int amount) {
		return DateUtils.addMonths(date, amount);
	}

	/**
	 * 为指定日期添加N周.
	 * 
	 * @param date
	 *            指定日期
	 * @param amount
	 *            增加周数
	 * @return 计算后的日期
	 * 
	 *         <pre>
	 * 修改日期      修改人    修改原因
	 * 2010-11-15        新建
	 *         </pre>
	 */
	public static Date addWeeks(Date date, int amount) {
		return DateUtils.addWeeks(date, amount);
	}

	/**
	 * 为指定日期添加N年.
	 * 
	 * @param date
	 *            指定日期
	 * @param amount
	 *            增加年数
	 * @return 计算后的日期
	 * 
	 *         <pre>
	 * 修改日期      修改人    修改原因
	 * 2010-11-15        新建
	 *         </pre>
	 */
	public static Date addYears(Date date, int amount) {
		return DateUtils.addYears(date, amount);
	}

	/**
	 * 为指定日期添加N小时.
	 * 
	 * @param date
	 *            指定日期
	 * @param amount
	 *            增加小时数
	 * @return 计算后的日期
	 * 
	 *         <pre>
	 * 修改日期      修改人    修改原因
	 * 2010-11-15                  整理
	 *         </pre>
	 */
	public static Date addHours(Date date, int amount) {
		return DateUtils.addHours(date, amount);
	}

	/**
	 * 为指定日期添加N分钟.
	 * 
	 * @param date
	 *            指定日期
	 * @param amount
	 *            增加分钟数
	 * @return 计算后的日期
	 * 
	 *         <pre>
	 * 修改日期      修改人    修改原因
	 * 2010-11-15        整理
	 *         </pre>
	 */
	public static Date addMinutes(Date date, int amount) {
		return DateUtils.addMinutes(date, amount);
	}

	/**
	 * 为指定日期添加N秒.
	 * 
	 * @param date
	 *            指定日期
	 * @param amount
	 *            增加秒数
	 * @return 计算后的日期
	 * 
	 *         <pre>
	 * 修改日期      修改人    修改原因
	 * 2010-11-15        新建
	 *         </pre>
	 */
	public static Date addSeconds(Date date, int amount) {
		return DateUtils.addSeconds(date, amount);
	}

	/**
	 * 为指定日期添加N毫秒.
	 * 
	 * @param date
	 *            指定日期
	 * @param amount
	 *            增加毫秒数
	 * @return 计算后的日期
	 * 
	 *         <pre>
	 * 修改日期      修改人    修改原因
	 * 2010-11-15        新建
	 *         </pre>
	 */
	public static Date addMilliseconds(Date date, int amount) {
		return DateUtils.addMilliseconds(date, amount);
	}

	/**
	 * 获取格式为“yyyyMMdd”的日期.
	 * 
	 * @return 日期字符串
	 * 
	 *         <pre>
	 * 修改日期      修改人    修改原因
	 * 2010-04-13        整理
	 *         </pre>
	 */
	public static String getDate() {
		return getDate(new Date());
	}

	/**
	 * 获取格式为“yyyyMMdd”的日期.
	 * 
	 * @param date
	 * @return
	 * 
	 *         <pre>
	 * 修改日期        修改人    修改原因
	 * 2012-1-10    陈建榕    新建
	 *         </pre>
	 */
	public final static String getDate(Date date) {
		return formatDate(date, NUMBER_DATE_FORMAT);
	}

	/**
	 * 获取格式为“yyyyMMdd”的日期.
	 * 
	 * @param date
	 * @return 日期字符串
	 * 
	 *         <pre>
	 * 修改日期        修改人    修改原因
	 * 2011-12-14    陈建榕    新建
	 *         </pre>
	 */
	public static String getDateStr(Date date) {
		return formatDate(date, NUMBER_DATE_FORMAT);
	}

	/**
	 * 获取格式为"yyyyMMdd"的数值型日期.
	 * 
	 * @return
	 * 
	 *         <pre>
	 * 修改日期        修改人    修改原因
	 * 2011-12-14    陈建榕    新建
	 *         </pre>
	 */
	public static final Integer getNumDate() {
		return Integer.valueOf(getDate());
	}

	/**
	 * 获取格式为"yyyyMMdd"的数值型日期.
	 * 
	 * @param date
	 * @return
	 * 
	 *         <pre>
	 * 修改日期        修改人    修改原因
	 * 2011-12-14    陈建榕    新建
	 *         </pre>
	 */
	public static final Integer getNumDate(Date date) {
		return Integer.valueOf(getDate(date));
	}

	/**
	 * 获取格式“HHmmss”的时间.
	 * 
	 * @return 时间字符串
	 * 
	 *         <pre>
	 * 修改日期      修改人    修改原因
	 * 2010-04-13        整理
	 *         </pre>
	 */
	public static String getTime() {
		return formatDate(new Date(), NUMBER_TIME_FORMAT);
	}

	/**
	 * 获取格式为“yyyy-MM-dd HH:mm:ss”的日期和时间.
	 * 
	 * @return 时间日期字符串
	 * 
	 *         <pre>
	 * 修改日期      修改人    修改原因
	 * 2010-04-13        整理
	 *         </pre>
	 */
	public static String getDateTime() {
		return formatDate(new Date(), ISO_DATE_TIME_FORMAT);
	}

	/**
	 * 获取格式“HH”的点数.
	 * 
	 * @return 时间字符串
	 * 
	 *         <pre>
	 * 修改日期      修改人    修改原因
	 * 2010-04-13        整理
	 *         </pre>
	 */
	public static int getTimeHours() {
		Date d = new Date();
		int hours = d.getHours();
		return hours;
	}

	/**
	 * 获取格式为“yyyy-MM-dd”的日期和时间.
	 * 
	 * @return 时间日期字符串
	 * 
	 *         <pre>
	 * 修改日期      修改人    修改原因
	 * 2010-04-13        整理
	 *         </pre>
	 */
	public static String getDateYMDHMS() {
		return formatDate(new Date(), ISO_DATE_TIME_FORMAT);
	}

	/**
	 * 获取格式为“yyyy-MM-dd”的日期和时间.
	 * 
	 * @return 时间日期字符串
	 * 
	 *         <pre>
	 * 修改日期      修改人    修改原因
	 * 2010-04-13        整理
	 *         </pre>
	 */
	public static String getDateYMD() {
		return formatDate(new Date(), ISO_DATE_FORMAT);
	}

	/**
	 * 获取格式为“yyyyMMddHHmmss”的日期和时间.
	 * 
	 * @return 时间日期字符串
	 * 
	 *         <pre>
	 * 修改日期      修改人    修改原因
	 * 2010-04-13        整理
	 *         </pre>
	 */
	public static String getVDateTime() {
		String str = formatDate(new Date(), ISO_DATE_TIME_FORMAT);
		str = str.replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "");
		return str;
	}

	/**
	 * 获取格式为“yyyy-MM-dd HH:mm:ss”的日期和时间.
	 * 
	 * @return 时间日期字符串
	 * 
	 *         <pre>
	 * 修改日期      修改人    修改原因
	 * 2010-04-13        整理
	 *         </pre>
	 */
	public static String getDateTime(Date date) {
		return formatDate(date, ISO_DATE_TIME_FORMAT);
	}

	/**
	 * 获取格式为“yyyy-MM-dd HH:mm:ss”的日期和时间.
	 * 
	 * @return 时间日期字符串
	 * 
	 *         <pre>
	 * 修改日期      修改人    修改原因
	 * 2010-04-13        整理
	 *         </pre>
	 */
	public static String getDateTime(long time) {
		return formatDate(new Date(time), ISO_DATE_TIME_FORMAT);
	}

	/**
	 * 获取当前时间的秒数
	 * 
	 * @param timeSr
	 * @return
	 */
	public static long formatDateLong(String timeSr) {
		SimpleDateFormat sdf = new SimpleDateFormat(ISO_DATE_TIME_FORMAT);
		try {
			Date date = sdf.parse(timeSr);
			return date.getTime();
		} catch (ParseException e) {

		}
		return 0;
	}

	/**
	 * 获取清理了时分秒的日期,只保留年月日的信息.
	 * 
	 * @param date
	 * @return
	 * 
	 *         <pre>
	 * 修改日期        修改人    修改原因
	 * 2011-12-13    陈建榕    新建
	 *         </pre>
	 */
	public final static Date getClearDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.clear(Calendar.HOUR_OF_DAY);
		calendar.clear(Calendar.AM_PM);
		calendar.clear(Calendar.HOUR);
		calendar.clear(Calendar.MINUTE);
		calendar.clear(Calendar.SECOND);
		calendar.clear(Calendar.MILLISECOND);
		return calendar.getTime();
	}

	/**
	 * 获取清理了时分秒的日期,只保留年月的信息，日为1号.
	 * 
	 * @param date
	 * @return
	 * 
	 *         <pre>
	 * 修改日期        修改人    修改原因
	 * 2011-12-13    陈建榕    新建
	 *         </pre>
	 */
	public final static Date getClearMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.clear(Calendar.HOUR_OF_DAY);
		calendar.clear(Calendar.AM_PM);
		calendar.clear(Calendar.HOUR);
		calendar.clear(Calendar.MINUTE);
		calendar.clear(Calendar.SECOND);
		calendar.clear(Calendar.MILLISECOND);
		return calendar.getTime();
	}

	/**
	 * 以指定的格式格式化日期.
	 * 
	 * @param srcDate
	 *            源日期
	 * @param pattern
	 *            格式
	 * @return 格式化的日期字符串
	 * 
	 *         <pre>
	 * 修改日期         修改人    修改原因
	 * 2013-07-09  qingwu    新建
	 *         </pre>
	 * 
	 */
	public static String formatDate(Timestamp srcDate, String pattern) {
		if (srcDate == null) {
			return null;
		}
		DateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(srcDate);
	}

	/**
	 * 获取本月的最后一天
	 * 
	 * @return
	 */
	public static Date getMonthLastDay() {
		SimpleDateFormat format = new SimpleDateFormat(ISO_DATE_TIME_FORMAT);
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		ca.set(Calendar.HOUR_OF_DAY, 23);
		ca.set(Calendar.MINUTE, 59);
		ca.set(Calendar.SECOND, 59);
		String last = format.format(ca.getTime());
		return DateUtil.parseDate(last, ISO_DATE_TIME_FORMAT);
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param start
	 *            较小的时间
	 * @param end
	 *            较大的时间
	 * @return 相差天数
	 */
	public static int daysBetween(Date start, Date end) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			start = sdf.parse(sdf.format(start));
			end = sdf.parse(sdf.format(end));
			Calendar cal = Calendar.getInstance();
			cal.setTime(start);
			long time1 = cal.getTimeInMillis();
			cal.setTime(end);
			long time2 = cal.getTimeInMillis();
			long between_days = (time2 - time1) / (1000 * 3600 * 24);
			return Math.abs(Integer.parseInt(String.valueOf(between_days)));
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
		}
		return 0;
	}

	/**
	 * 计算两个日期之间相差的分钟数
	 * 
	 * @param start
	 *            较小的时间
	 * @param end
	 *            较大的时间
	 * @return 相差分钟
	 */
	public static String timeBetween(Date start, Date end) {
		if (start == null && end == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuffer millisBetween = new StringBuffer("");
		try {
			start = sdf.parse(sdf.format(start));
			end = sdf.parse(sdf.format(end));
			long diff = end.getTime() - start.getTime();// 这样得到的差值是微秒级别
			long days = diff / (1000 * 60 * 60 * 24);
			long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
			long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
			if (days != 0) {
				millisBetween.append("" + days + "天");
			}
			if (hours != 0) {
				millisBetween.append("" + hours + "小时");
			}
			if (minutes != 0) {
				millisBetween.append("" + minutes + "分");
			} else {
				return millisBetween.toString();
			}
			return millisBetween.toString();
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
		}
		return millisBetween.toString();
	}

	public static String transferLongToDate(String dateFormat, Long millSec) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Date date = new Date(millSec);
		return sdf.format(date);
	}

	public static Long getDayStartTime(Date date) {
		Calendar todayStart = Calendar.getInstance();
		todayStart.setTime(date);
		todayStart.set(Calendar.HOUR, 0);
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		todayStart.set(Calendar.MILLISECOND, 0);
		return todayStart.getTime().getTime();
	}

	public static Long getDayEndTime(Date date) {
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.setTime(date);
		todayEnd.set(Calendar.HOUR, 23);
		todayEnd.set(Calendar.MINUTE, 59);
		todayEnd.set(Calendar.SECOND, 59);
		todayEnd.set(Calendar.MILLISECOND, 999);
		return todayEnd.getTime().getTime();
	}

	/**
	 * 日期格式字符串转换成时间戳
	 * 
	 * @param date
	 *            字符串日期
	 * @param format
	 *            如：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static Long date2TimeStamp(String date_str, String format) {
		try {

			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.parse(date_str).getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0L;
	}

	/**
	 * 
	 * @param date_str
	 *            时间戳
	 * @return 字符串格式的日期
	 */
	public static String timeStamp2Date(Integer date_str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Long date_str2 = new Long((long) date_str * 1000);
		String date = sdf.format(new Date(date_str2));
		return date;
	}

	/**
	 * 
	 * @param date_str
	 *            时间戳
	 * @return 字符串格式的日期
	 */
	public static String timeStamp2LongDate(Long date_str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(new Date(date_str));
		return date;
	}

	/**
	 * 获取当前时间的前一天
	 * 
	 * @return
	 */
	public static String getNextDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1); // 得到前一天
		Date date = calendar.getTime();
		String nextDay = formatDate(date, NUMBER_DATE_FORMAT);
		return nextDay;
	}

	/**
	 * 获取当前时间的前一天
	 * 
	 * @return
	 */
	public static String getLastDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1); // 得到前一天
		Date date = calendar.getTime();
		String nextDay = formatDate(date, ISO_DATE_FORMAT);
		return nextDay;
	}

	/**
	 * 获取当前时间的前一天的年月
	 * 
	 * @param date
	 * @return
	 */
	public static String getLastMonthDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1); // 得到前一天
		Date date = calendar.getTime();
		String nextDay = formatDate(date, "yyyyMM");
		return nextDay;
	}

	/**
	 * 获取当前时间的前一天的年月
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1); // 得到前一天
		Date date = calendar.getTime();
		String nextDay = formatDate(date, "yyyy-MM-dd HH:mm:ss");
		Date nextd = parseDate(nextDay, "yyyy-MM-dd HH:mm:ss");
		return nextd;
	}

	/**
	 * 将yyyy-mm的格式转换成yyyyMM
	 * 
	 * @param strDate
	 * @return
	 */
	public static String parseDateToYYMM(String strDate) {
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat sf2 = new SimpleDateFormat("yyyyMM");
		String src = null;
		try {
			src = sf2.format(sf1.parse(strDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return src;
	}

	/**
	 * 将yyyyMM的格式转换成yyyy-mm
	 * 
	 * @param strDate
	 * @return
	 */
	public static String parseDateFromYYMM(String strDate) {
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMM");
		SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM");
		String src = null;
		try {
			src = sf2.format(sf1.parse(strDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return src;
	}

	/**
	 * 将yyyyMM的格式转换成yyyy-mm-dd
	 * 
	 * @param strDate
	 * @return
	 */
	public static String parseDateFromYYMMdd(String strDate) {
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");
		String src = null;
		try {
			src = sf2.format(sf1.parse(strDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return src;
	}

	/**
	 * 将yyyyMM的格式转换成yyyy-mm-dd
	 * 
	 * @param strDate
	 * @return
	 */
	public static String parseDateFromYY(String strDate) {
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sf3 = new SimpleDateFormat("yyyyMM");
		SimpleDateFormat sf4 = new SimpleDateFormat("yyyy-MM");
		String src = null;
		try {
			src = sf2.format(sf1.parse(strDate));
		} catch (ParseException e) {
			try {
				src = sf4.format(sf3.parse(strDate));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		return src;
	}

	/**
	 * 当前时间向前加，减几分钟
	 * 
	 * @param minNs 分钟数
	 * @return
	 */
	public static String parseBeforeMins(int minNs) {

		Calendar nowTime = Calendar.getInstance();
		nowTime.add(Calendar.MINUTE, minNs);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return sdf.format(nowTime.getTime());
	}

	
	/**
	 * 获取指定时间前一天
	 * 
	 * @param date
	 * @return
	 */
	public static String getLastDate(String date) {
		Calendar calendar = Calendar.getInstance();  
        calendar.setTime(parseDate(date));  
        calendar.add(Calendar.DAY_OF_MONTH, -1);  
        Date dates = calendar.getTime();
       
		String nextDay = formatDate(dates,DateUtil.ISO_DATE_FORMAT);
		return nextDay;
	}
	
	/**
	 * 获取指定时间前一天
	 * 
	 * @param date
	 * @return
	 */
	public static String getLast2Date(String date) {
		Calendar calendar = Calendar.getInstance();  
        calendar.setTime(parseDate(date));  
        calendar.add(Calendar.DAY_OF_MONTH, -2);  
        Date dates = calendar.getTime();
       
		String nextDay = formatDate(dates,DateUtil.ISO_DATE_FORMAT);
		return nextDay;
	}
	
	public static void main(String[] args) {
		System.out.println(getLast2Date("2017-09-21"));
	}
}
