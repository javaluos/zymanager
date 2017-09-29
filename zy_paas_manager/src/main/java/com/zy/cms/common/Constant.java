package com.zy.cms.common;

public class Constant {
	
	public static final String USER_SESSION_UID = "cms_uid";
	public static final String EQUAL_MARK = "=";
	public static final String AND = "&";
	public static final String NOT_LOGIN_FROM_URL = "NOT_LOGIN_FROM_URL";
	public static final String USER_SESSION_ENCRYPT_KEY = "this_is_the_key_of_zhiyan";
	public static final String ENPTY_STRING = "";
	public static final String TRUE_STR = "true";
	public static final String FALSE_STR = "false";

	public static final int COOKIE_IS_EXPIRED_ERROR = 200;
	public static final String COOKIE_IS_EXPIRED_ERROR_DESC = "验证码已经失效";
	public static final int USER_HAS_NO_PHONE_ERROR = 210;
	public static final String USER_HAS_NO_PHONE_ERROR_DESC = "该用户没有手机号";
	public static final int SMS_SEND_ERROR = 220;
	public static final String SMS_SEND_ERROR_DESC = "验证码发送错误";
	public static final int PASSWORD_FORMAT_IS_NOT_VALID_ERROR = 230;
	public static final String PASSWORD_FORMAT_IS_NOT_VALID_ERROR_DESC = "密码格式不正确";
	public static final int NO_ENOUTH_MONEY_ERROR = 240;
	public static final String NO_ENOUTH_MONEY_ERROR_DESC = "余额不足";
	public static final int NO_ENOUTH_MONEY_THIRTY_ERROR = 241;
	public static final String NO_ENOUTH_MONEY_THIRTY_ERROR_DESC = "余额不足0.3元";

	// 验证码放在redis中的有效时间
	// 对象存放于redis中的存活周期，默认为5分钟
	public static final int OBJECT_CACHE_IN_REDIS_MINUTES = 5 * 60;
	// 用户的数字签名，保存于redis中24小时
	public static final int USER_SIGN_IN_REDIS_TWEENTY_FOUR_HOURS = 24 * 60 * 60;
	// 验证码放在redis中的有效时间
	public static final int VERIFY_CODE_IN_REDIS_MINUTES = 5 * 60;
	// 已经发送的短信ID存放在REDIS中的有效时间：一百年
	public static final int SMS_SEND_MESSAGE_ID_IN_REDIS_MINUTES = 10 * 365
			* 24 * 60 * 60;

	public static final String REG_CODE_KEY = "REG_CODE_KEY";
	public static final String PWD_CODE_KEY = "PWD_CODE_KEY";
	public static final String PRO_FREE_CODE_KEY = "PRO_FREE_CODE_KEY";
	public static final String FINDPWD_CODE_KEY = "FINDPWD_CODE_KEY";
	public static final String PARAM_ERROR = "101";
	public static final String PARAM_ERROR_DESC = "参数错误或不全";
	public static final int RECHARGE_ORDER_EX = 102;
	public static final String RECHARGE_ORDER_EX_DESC = "订单号存在";
	public static final int RECHARGE_NO_FIND_GOODS_INFO = 103;
	public static final String RECHARGE_NO_FIND_GOODS_INFO_DESC = "套餐不存在";
	public static final String NO_FIND_ACCOUNT_INFO = "105";
	public static final String NO_FIND_ACCOUNT_INFO_DESC = "账号信息不存在";
	public static final String CDR_USER_MONITOR_ACCOUNT_INFO_DESC = "该账号信息已经在客户监控列表中了";
	public static final int RECHARGE_SAVE_RECHARGE_ERROR = 106;
	public static final String RECHARGE_SAVE_RECHARGE_ERROR_DESC = "保存订单失败";
	public static final int RECHARGE_NO_FIND_ERROR = 108;
	public static final String RECHARGE_NO_FIND_ERROR_DESC = "没有找到订单";
	public static final String ACCOUNT_EXIST = "120";
	public static final String ACCOUNT_EXIST_DESC = "账号已存在";
	public static final String ACCOUNT_OR_PWD_ERROR = "130";
	public static final String ACCOUNT_OR_PWD_ERROR_DESC = "账号或密码错误";
	public static final int RE_LOGIN = 150;
	public static final String RE_LOGIN_DESC = "请重新登陆";
	public static final int SAVE_SMS_SIG_ERROR = 160;
	public static final String SAVE_SMS_SIG_ERROR_DESC = "保存短信签名失败";
	public static final String RE_SMS_CODE_VALIDE_ERROR = "170";
	public static final String RE_SMS_CODE_VALIDE_ERROR_DESC = "验证码错误";

	public static final String PHONE_EXIST = "180";
	public static final String PHONE_EXIST_DESC = "该手机已注册";
	public static final int OLD_PWD_ERROR = 190;
	public static final String OLD_PWD_ERROR_DESC = "原账号密码错误";
	public static final String NOT_EXIST_INVITED_CODE = "243";
	public static final String NOT_EXIST_INVITED_CODE_DESC = "邀请码不存在";
	public static final String OTHER_ERROR = "107";
	public static final String OTHER_ERROR_DESC = "其他错误";
	public static final String SUCCESS = "1";
	public static final String SUCCESS_DESC = "success";
	public static final int ERROR = -1;
	public static final int WARN = 0;
	public static final int NO_RESP = 400;
	public static final String NO_RESP_DESC = "No response";

	public static final String USER_SESSION_UNAME = "cms_uname";
	public static final String USER_SESSION_PHONE = "xybkdwes";
	public static final String SLASH = "/";

	public static final String USER_REMEMBER_UNAME = "remeberme";
	public static final String ON = "on";

	public static final int SIGN_LENGTH_ERROR = 307;
	public static final String SIGN_LENGTH_ERROR_DESC = "签名长度不正确";

	public static class Time {

		public static final int FIVE_MINUTES = 60 * 5;
		public static final int THIRTY_MINUTES = 60 * 30;
		public static final int ONE_HOUR = 60 * 60;
		public static final int ONE_DAY = 60 * 60 * 24;
		public static final int ONE_MONTH = 60 * 60 * 24 * 30;
		public static final int ONE_YEAR = 60 * 60 * 24 * 365;
		public static final String START_HHMMSS = " 00:00:00";
		public static final String END_HHMMSS = " 23:59:59";

	}

	public static final int PAGE_SIZE_10 = 10; // 每页展示10
	public static final int PAGE_SIZE_20 = 20; // 每页展示20
	public static final int PAGE_SIZE_5 = 5; // 每页展示5
	public static final int DEFUALT_PAGE = 1; // 默认当前页
	
	public static final String	ES_CDR_INDEX_NAME = "voice_cdr_info";
	public static final String	ES_CDR_INDEX_TYPE = "voice_cdr_type";
	
	public static final String	ES_SMS_SEND_INDEX_NAME = "paas_sms_send_info";
	public static final String	ES_SMS_SEND_INDEX_TYPE = "paas_sms_send_type";
	
	public static final String	RECHARGE_MENUID = "17100";  //充值功能菜单id
	public static final String	MONEY_UPDATE_MENUID = "17200";  //资费修改功能菜单id
	public static final String	GLOBAL_SETTING_MENUID = "17300";  //全局监控设置菜单id
	public static final String	ACCOUNT_SETTING_MENUID = "17400";  //客户功能菜单id
	public static final String	PROPERTY_VIEW_MENUID = "11200";  //账号属性查看功能菜单id
	public static final String	SMS_STATISTICS_MENUID = "14710";  //账号属性查看功能菜单id

}
