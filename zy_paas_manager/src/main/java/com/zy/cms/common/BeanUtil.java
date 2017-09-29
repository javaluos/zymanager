package com.zy.cms.common;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.zy.cms.vo.MerchantConsumeBill;
import com.zy.cms.vo.MerchantFeeRecharge;
import com.zy.cms.vo.SysParam;

@Component
public class BeanUtil {

	@Resource
	private CacheService cacheService;

	public List<MerchantFeeRecharge> sortMerchantFeeRechargeByTime(
			List<MerchantFeeRecharge> merchantFeeRecharges) {
		Collections.sort(merchantFeeRecharges,
				new Comparator<MerchantFeeRecharge>() {
					@Override
					public int compare(MerchantFeeRecharge o1,
							MerchantFeeRecharge o2) {
						return o2.getCreateTime().compareTo(o1.getCreateTime());
					}
				});
		return merchantFeeRecharges;
	}

	public List<MerchantConsumeBill> sortMerchantConsumeBillByTime(
			List<MerchantConsumeBill> serchantConsumeBills) {
		Collections.sort(serchantConsumeBills,
				new Comparator<MerchantConsumeBill>() {
					@Override
					public int compare(MerchantConsumeBill o1,
							MerchantConsumeBill o2) {
						return o2.getBillDate().compareTo(o1.getBillDate());
					}
				});
		return serchantConsumeBills;
	}

	public List<SysParam> sortSysParamById(List<SysParam> list) {
		Collections.sort(list, new Comparator<SysParam>() {
			@Override
			public int compare(SysParam o1, SysParam o2) {
				return o1.getId().compareTo(o2.getId());
			}
		});
		return list;
	}
}
