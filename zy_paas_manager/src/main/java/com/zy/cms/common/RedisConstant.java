package com.zy.cms.common;

public class RedisConstant {

	public static final String REDIS_SPLIT_STRING = "::";

	public static final String ZHIYAN_SMS_KEY_PREFIX = "ZHIYAN" + REDIS_SPLIT_STRING + "SMS";

	public static final String PRO_FREE_CODE_KEY = "PRO_FREE_CODE_KEY";

	public static final String BALANCEKEY_PREFIX = "VOICE::MERCHANT_ACCOUNT::BALANCE::%s";

	public static final String ACCOUNTFEEKEY_PREFIX = "VOICE::MERCHANT_ACCOUNT::FEE::%s";

	public static final String ACCOUNTKEY_PREFIX = "VOICE::MERCHANT_ACCOUNT::INFO::%s";

	public static final String APPKEY_PREFIX = "VOICE::MERCHANT_ACCOUNT_APP::INFO::%s::%s";// VOICE::MERCHANT_ACCOUNT_APP::INFO::hmj::appid

	/**
	 * 注册时发的短信,最后为手机号
	 */
	public static final String ZHIYAN_REG_SMS_CODE_KEY = ZHIYAN_SMS_KEY_PREFIX + REDIS_SPLIT_STRING
			+ "ZHIYAN_REG_SMS_CODE" + REDIS_SPLIT_STRING + "%s";
	/**
	 * 忘记密码时发的短信,最后为手机号
	 */
	public static final String FIND_PWD_SMS_CODE_KEY = ZHIYAN_SMS_KEY_PREFIX + REDIS_SPLIT_STRING + "FIND_PWD_SMS_CODE"
			+ REDIS_SPLIT_STRING + "%s";
	/**
	 * 更新密码时发的短信,最后为手机号
	 * 
	 */
	public static final String UPDATE_PWD_SMS_CODE_KEY = ZHIYAN_SMS_KEY_PREFIX + REDIS_SPLIT_STRING
			+ "UPDATE_PWD_SMS_CODE" + REDIS_SPLIT_STRING + "%s";

	public static final String REG_CODE_KEY = ZHIYAN_SMS_KEY_PREFIX + REDIS_SPLIT_STRING + "REG_CODE_KEY"
			+ REDIS_SPLIT_STRING + "%s";
	public static final String PWD_CODE_KEY = ZHIYAN_SMS_KEY_PREFIX + REDIS_SPLIT_STRING + "PWD_CODE_KEY"
			+ REDIS_SPLIT_STRING + "%s";

	public static final String REG_INFO_KEY = ZHIYAN_SMS_KEY_PREFIX + REDIS_SPLIT_STRING + "REG_INFO_KEY"
			+ REDIS_SPLIT_STRING + "%s";

	public static final String ACCOUNT_INFO_KEY = ZHIYAN_SMS_KEY_PREFIX + REDIS_SPLIT_STRING + "ACCOUNT_INFO_KEY"
			+ REDIS_SPLIT_STRING + "%s";

	/**
	 * 找回密码时的验证key
	 */
	public static final String FINDPWD_CODE_KEY = ZHIYAN_SMS_KEY_PREFIX + REDIS_SPLIT_STRING + "FINDPWD_CODE_KEY"
			+ REDIS_SPLIT_STRING + "%s";

	/**
	 * 存储账号存放URL的域
	 */
	public static final String ZHIYU_VOICE_ACCOUNT_STATUS_URL = "ZHIYU_VOICE_ACCOUNT_STATUS_URL";

	/**
	 * 话单回调地址url(
	 * ACC1d3c3cb0362049aaaa8a816c696dfc10_APP49933e2f3c674b49b1620f38ff320c13_1)
	 */
	public static final String PUSH_URL_FILEID = "%s" + "_" + "%s" + "_" + "%s";

	/**
	 * 余额修改时获取短信验证码的key
	 */
	public static final String BALANCE_VCODE_KEY = ZHIYAN_SMS_KEY_PREFIX + REDIS_SPLIT_STRING + "BALANCE_VCODE_KEY"
			+ REDIS_SPLIT_STRING + "%s";

	/**
	 * PaaS平台短信业务，Redis前缀
	 */
	public static final String ZHIYU_PAAS_SMS_KEY_PREFIX = "ZHIYU" + REDIS_SPLIT_STRING + "PAAS" + REDIS_SPLIT_STRING
			+ "SMS";

	/**
	 * 
	 * 短信模板 SMS_TEMPLATE_ID(%s为apiaccount)
	 */
	public static final String ZHIYU_PAAS_SMS_TEMPLATE_ID = ZHIYU_PAAS_SMS_KEY_PREFIX + REDIS_SPLIT_STRING
			+ "SMS_TEMPLATE" + REDIS_SPLIT_STRING + "%s";

	/**
	 * 短信签名 SMS_SIGNER_KEY(%s为apiaccount)
	 */
	public static final String ZHIYU_PAAS_SMS_SIGNER_KEY = ZHIYU_PAAS_SMS_KEY_PREFIX + REDIS_SPLIT_STRING + "SMS_SIGNER"
			+ REDIS_SPLIT_STRING + "%s";

	/**
	 * 通道信息 redis缓存 KEY
	 */
	public static String ZHIYU_PAAS_SMS_CHANNELS = ZHIYU_PAAS_SMS_KEY_PREFIX + REDIS_SPLIT_STRING + "CHANNELS";
	/**
	 * 加入语音白名单账户key
	 */
	public static final String VOICE_WHITE_LIST_ACCOUNT = "VOICE::WHITE_LIST::%s";
	/**
	 * 账户绑定通道列表key(%s:apiAccount)
	 */
	public static String ZHIYU_PAAS_SMS_MCHBAND_CHANNEL = ZHIYU_PAAS_SMS_KEY_PREFIX + REDIS_SPLIT_STRING
			+ "MCHBAND_CHANNELS" + REDIS_SPLIT_STRING + "%s";

	/**
	 * 记录短信通道发送量(用于阀值控制)
	 */
	public static final String ZHIYU_PAAS_SMS_ACCOUNT_CHANNEL_DAY_SEND_TOTAL = ZHIYU_PAAS_SMS_KEY_PREFIX
			+ REDIS_SPLIT_STRING + "ACCOUNT_CHANNEL_DAY_SEND_TOTAL" + REDIS_SPLIT_STRING + "%s" + REDIS_SPLIT_STRING
			+ "%s";

	/**
	 * 账号属性配置
	 */
	public static final String ACCOUNTKEY_ATTR_PREFIX = "VOICE::MERCHANT_ACCOUNT_ATTR::INFO::%s";// 账号扩展配置（%s为apiAccount）

	/**
	 * 通道余额监控key
	 */
	public static final String ZHIYU_PAAS_SMS_CHANNEL_BALANCE_MONITOR = ZHIYU_PAAS_SMS_KEY_PREFIX + REDIS_SPLIT_STRING
			+ "CHANNEL_BALANCE_MONITOR" + REDIS_SPLIT_STRING + "%s";

	/**
	 * 黑名单列表 redis缓存 KEY
	 */
	public static String ZHIYU_PAAS_SMS_BLACK_LIST = ZHIYU_PAAS_SMS_KEY_PREFIX + REDIS_SPLIT_STRING + "BLACK_LIST";

	/**
	 * 黑名单组绑定 redis缓存 KEY
	 */
	public static String ZHIYU_PAAS_SMS_BLACK_GROUP_BIND = ZHIYU_PAAS_SMS_KEY_PREFIX
			+ REDIS_SPLIT_STRING + "BLACK_GROUP_BIND" + REDIS_SPLIT_STRING +"%s";

	/**
	 * 白名单列表 redis缓存 KEY
	 */
	public static String ZHIYU_PAAS_SMS_WHITE_LIST = ZHIYU_PAAS_SMS_KEY_PREFIX + REDIS_SPLIT_STRING + "WHITE_LIST";

	/**
	 * 用户绑定通道组关系( key=ZHIYU::PAAS::SMS::ACCOUNT_CHANNELGROUP )(hash类型,
	 * filed=apiAccount, value=Json(账号绑定通道组信息))
	 */
	public static String ZHIYU_PASS_SMS_ACCOUNT_CHANNELGROUP = ZHIYU_PAAS_SMS_KEY_PREFIX + REDIS_SPLIT_STRING
			+ "ACCOUNT_CHANNELGROUP";

	/**
	 * 通道组&通道关系( key=ZHIYU::PAAS::SMS::CHANNELGROUP_RELATIN::%s(channelGroupId)
	 * )(hash类型, filed=channelId, value=Json(accountChannelInfo))
	 */
	public static String ZHIYU_PASS_SMS_CHANNELGROUP_RELATIN = ZHIYU_PAAS_SMS_KEY_PREFIX + REDIS_SPLIT_STRING
			+ "CHANNELGROUP_RELATIN" + REDIS_SPLIT_STRING+ "%s";;

	/**
	 * 导流策略规则信息( key=ZHIYU::PAAS::SMS::CHANNEL_POLICY_RULE::%S(policyId)
	 * )(hash类型, filed=ruleId(规则Id), value=Json(policlyRuleInfo))
	 */
	public static String ZHIYU_PASS_SMS_CHANNEL_POLICY_RULE = ZHIYU_PAAS_SMS_KEY_PREFIX + REDIS_SPLIT_STRING
			+ "CHANNEL_POLICY_RULE" + REDIS_SPLIT_STRING + "%s";

	/**
	 * 通道组信息
	 * (hash类型, filed=channelGroupId, value=Json(channelGroupInfo))
	 */
	public static final String ZHIYU_PASS_SMS_CHANNELGROUP_INFO = ZHIYU_PAAS_SMS_KEY_PREFIX + REDIS_SPLIT_STRING
			+ "CHANNEL_GROUP_INFO";
	
	/**
	 * 账号单号码一定秒数内发送数量(参数分别apiAccount和mobile)
	 */
	public static final String ZHIYU_PAAS_SMS_SINGLE_MOBILE_SEND_IN_SECS = ZHIYU_PAAS_SMS_KEY_PREFIX + REDIS_SPLIT_STRING
			+ "SINGLE_MOBILE_SEND_IN_SECS" + REDIS_SPLIT_STRING + "%s";

	/**
	 * 账号单号码一定分钟内发送数量
	 */
	public static final String ZHIYU_PAAS_SMS_SINGLE_MOBILE_SEND_IN_MINS = ZHIYU_PAAS_SMS_KEY_PREFIX + REDIS_SPLIT_STRING
			+ "SINGLE_MOBILE_SEND_IN_MINS" + REDIS_SPLIT_STRING + "%s";

	/**
	 * 账号单号码一定小时内发送数量
	 */
	public static final String ZHIYU_PAAS_SMS_SINGLE_MOBILE_SEND_IN_HOURS = ZHIYU_PAAS_SMS_KEY_PREFIX + REDIS_SPLIT_STRING
			+ "SINGLE_MOBILE_SEND_IN_HOURS" + REDIS_SPLIT_STRING + "%s";
	
	/**
	 * 状态报告错误码 redis缓存 KEY
	 */
	public static String ZHIYU_PAAS_SMS_STATUS_ERRORCODE_LIST = ZHIYU_PAAS_SMS_KEY_PREFIX + REDIS_SPLIT_STRING + "STATUS_ERRORCODE_LIST";
}
