package com.zy.cms.enums;

/**
 * 账号属性Enum
 * @author xuyipeng
 * @date 2016-3-31 9:58:39
 */
public enum MerchantAttrEnum {
	
	TEMP(1, "TEMPLATE_AUTH_FLAG"),
	SIGN(2, "SIGNER_AUTH_FLAG"),
	VOICEFILE(3,"VOICEFILE_AUTH_FLAG"),
	ISBLACKKEY(4,"IS_BLACK_KEY"),
	ISWHITEKEY(5,"IS_WHITE_KEY"),
	CHANNELPOLICY(6, "CHANNEL_POLICY"),//分流策略
	SINGLEMOBILESENDFLAG(7,"SINGLE_MOBILE_SEND_FLAG"),
	SINGLEMOBILESENDINSECS(8,"SINGLE_MOBILE_SEND_IN_SECS"),
	SINGLEMOBILESENDINMINS(9,"SINGLE_MOBILE_SEND_IN_MINS"),
	SINGLEMOBILESENDINHOURS(10,"SINGLE_MOBILE_SEND_IN_HOURS"),
	SMSFILTERPOLICY(11,"SMS_FILTER_POLICY"),//客户拦截策略
	SMSNOFILTERPOLICY(12,"SMS_NO_FILTER_POLICY"),
	ISBLACKAUDITFLAG(13,"IS_BLACK_AUDIT_FLAG"),
	CMPP_ACCESS_CODE(14,"CMPP_ACCESS_CODE"),
	YD_EXTNUMBER(15,"YD_EXTNUMBER"),
	LT_EXTNUMBER(16,"LT_EXTNUMBER"),
	DX_EXTNUMBER(17,"DX_EXTNUMBER"),
	ACC_SEND_PER_SECOND(18,"ACC_SEND_PER_SECOND"),
	CMPP_ACCESS_ACCOUNT(19,"CMPP_ACCESS_ACCOUNT"),
	CMPP_ACCESS_PASSWORD(20,"CMPP_ACCESS_PASSWORD"),
	;
	
	// 成员变量
	private int type;
	private String name;

	// 构造方法
	private MerchantAttrEnum(int _type, String _name) {
		this.type = _type;
		this.name = _name;
	}

	// 普通方法
	public static String getName(int _type) {
		for (MerchantAttrEnum c : MerchantAttrEnum.values()) {
			if (c.getType() == _type) {
				return c.name;
			}
		}
		return "";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
