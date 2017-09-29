package com.zy.cms.service.master;

import java.util.List;
import java.util.Map;

import com.zy.cms.vo.MerchantFeeRecharge;

/**
 * 用户充值service
 * @author hmj
 * @date 2015-5-30
 */
public interface MerchantRechargeService {
 
	/**
	 * 保存充值记录
	 * @param merchantPhone 手机号
	 * @param orderNo 订单号，是order生成
	 * @param reason 交易失败原因
	 * @param paytype 支付类型:0:支付宝 1:银联2:礼品卡
	 * @param rechargeType 充值类型：0:套餐充值 1:活动充值
	 * @param goodId 商品id
	 * @return
	 */
	public Map saveMerchantRecharge(String merchantPhone,String orderNo,String reason,int paytype,int rechargeType,String goodId);
	/**
	 * 更新充值结果
	 * @param merchantPhone 手机号
	 * @param orderNo 订单号
	 * @param status 充值的状态：0：表示提交了充值请求,充值中1：表示充值成功-1：表示充值失败
	 * @param reason 
	 * @return
	 */
	public Map updateMerchantRecharge(String merchantPhone,String orderNo,int status,String reason);
	
 
	/**
	 * 订单详情
	 * @param orderNo
	 * @return
	 */
	public MerchantFeeRecharge getMerchantRecharge(String orderNo);
	
	/**
	 * 获取充值记录
	 * @param params
	 * @return
	 */
	public List<MerchantFeeRecharge> getRechargeRecords(Map params);
	/**
	 * 获取条数
	 * @param params
	 * @return
	 */
	public Integer getCounts(Map params);
	
	/**
	 * 充值成功后的消息
	 * @param orderNo 账号
	 * @param type 类型 0：套餐充值，1：卡充值
	 * @return
	 */
	public Integer saveRechargeSysMessage(String  orderNo,String type);
	/**
	 * 保存卡充值信息
	 * @param merchantPhone
	 * @param orderNo
	 * @param reason
	 * @param paytype
	 * @param rechargeType
	 * @param goodId
	 * @return
	 */
	public Integer saveCardRecharge(String merchantPhone, String orderNo, String reason, int paytype, int rechargeType, String goodId,String money);
}
