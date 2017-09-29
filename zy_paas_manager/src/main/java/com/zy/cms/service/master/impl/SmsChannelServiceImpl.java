package com.zy.cms.service.master.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zy.cms.common.RedisConstant;
import com.zy.cms.common.RedisOperator;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.enums.ChannelPostfixEnum;
import com.zy.cms.enums.ChannelPropertyEnum;
import com.zy.cms.enums.OperateTypeEnum;
import com.zy.cms.mapper.master.SmsChannelMapper;
import com.zy.cms.service.master.SmsChannelService;
import com.zy.cms.util.DateUtil;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.MerchantAccount;
import com.zy.cms.vo.channel.SmsChannel;
import com.zy.cms.vo.query.SmsChannelQuery;

/**
 * 通道信息管理业务实现类
 * 
 * @author allen.yuan
 * @date 2017-2-7
 *
 */
@Service("smsChannelService")
public class SmsChannelServiceImpl implements SmsChannelService {

	private static final ZyLogger logger = ZyLogger.getLogger(SmsChannelServiceImpl.class);

	@Autowired
	private SmsChannelMapper mapper;

	@Autowired
	private RedisOperator redis;

	@Override
	public Integer querySmsChannelCountByEntity(SmsChannelQuery query) {

		try {
			return mapper.querySmsChannelCountByEntity(query);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}

		return 0;
	}

	@Override
	public List<SmsChannel> querySmsChannelListByEntity(SmsChannelQuery query) {

		try {
			return mapper.querySmsChannelListByEntity(query);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}

		return null;
	}

	@Override
	public SmsChannel selectByPrimaryKey(String channelId) {

		try {
			return mapper.selectByPrimaryKey(channelId);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}

		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(SmsChannel channel) {

		try {
			return mapper.updateByPrimaryKeySelective(channel);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}

		return 0;
	}

	@Override
	public int deleteByPrimaryKey(String channelId) {

		try {
			return mapper.deleteByPrimaryKey(channelId);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return 0;
	}

	@Override
	public int insertSelective(SmsChannel channel) {

		try {
			return mapper.insertSelective(channel);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return 0;
	}

	@Override
	public SmsChannel selectByChannelMainCode(String channelMainCode) {

		try {
			return mapper.selectByChannelMainCode(channelMainCode);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}

		return null;
	}

	/**
	 * 保存通道信息
	 */
	@Transactional
	@Override
	public int saveSmsChannel(SmsChannel channel) {
		int result = 0;
		try {

			// 新增通道
			if (StringUtil.isEmpty(channel.getChannelId())) {

				// 三网合一,自动生成三个通道
				if (channel.getOperateType() == OperateTypeEnum.DF_NET.getType()) {

					String postfix = "";// 通道Id后缀
					String name = channel.getChannelName();

					// 添加移动通道
					postfix = ChannelPostfixEnum.getName(OperateTypeEnum.YD_NET.getType());
					channel.setChannelId(channel.getChannelMainCode() + '-' + postfix);
					channel.setChannelName(name + "-" + ChannelPropertyEnum.YD.getName());
					channel.setChannelProperty(ChannelPropertyEnum.YD.getValue());
					result = mapper.insertSelective(channel);

					// 添加电信通道
					postfix = ChannelPostfixEnum.getName(OperateTypeEnum.DX_NET.getType());
					channel.setChannelId(channel.getChannelMainCode() + '-' + postfix);
					channel.setChannelName(name + "-" + ChannelPropertyEnum.DX.getName());
					channel.setChannelProperty(ChannelPropertyEnum.DX.getValue());
					result = mapper.insertSelective(channel);

					// 添加联通通道
					postfix = ChannelPostfixEnum.getName(OperateTypeEnum.LT_NET.getType());
					channel.setChannelId(channel.getChannelMainCode() + '-' + postfix);
					channel.setChannelName(name + "-" + ChannelPropertyEnum.LT.getName());
					channel.setChannelProperty(ChannelPropertyEnum.LT.getValue());
					result = mapper.insertSelective(channel);

				} else {

					// 生成channelId
					String postfix = ChannelPostfixEnum.getName(channel.getOperateType());
					channel.setChannelId(channel.getChannelMainCode() + '-' + postfix);

					String namePostfix = "";
					String channelProperty = "";
					if (channel.getOperateType() == OperateTypeEnum.YD_NET.getType()) {
						namePostfix = ChannelPropertyEnum.YD.getName();
						channelProperty = ChannelPropertyEnum.YD.getValue();
					}
					if (channel.getOperateType() == OperateTypeEnum.DX_NET.getType()) {
						namePostfix = ChannelPropertyEnum.DX.getName();
						channelProperty = ChannelPropertyEnum.DX.getValue();
					}
					if (channel.getOperateType() == OperateTypeEnum.LT_NET.getType()) {
						namePostfix = ChannelPropertyEnum.LT.getName();
						channelProperty = ChannelPropertyEnum.LT.getValue();
					}
					channel.setChannelName(channel.getChannelName() + "-" + namePostfix);
					channel.setChannelProperty(channelProperty);

					result = mapper.insertSelective(channel);

				}
			} else {

				// 修改通道
				channel.setUpdateTime(DateUtil.getDateTime());
				result = mapper.updateByPrimaryKeySelective(channel);

			}

			// 添加通道信息到Redis缓存中
			if (result > 0) {
				channel = mapper.selectByPrimaryKey(channel.getChannelId());
				if (channel != null) {
					redis.hset(RedisConstant.ZHIYU_PAAS_SMS_CHANNELS, channel.getChannelId(),
							JsonUtil.objectToJson(channel));
				}
			}

		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return result;
	}

	/**
	 * 删除通道信息
	 * 
	 * channelId 通道ID
	 */
	@Override
	public boolean delSmsChannel(String channelId) {

		boolean result = false;
		try {

			SmsChannel chl = mapper.selectByPrimaryKey(channelId);
			if (chl != null && chl.getStatus() != 1) { // 非运营中进行删除
				result = mapper.deleteByPrimaryKey(channelId) > 0;
				if (result) {
					redis.hdel(RedisConstant.ZHIYU_PAAS_SMS_CHANNELS, channelId);// redis删除通道信息
				}
			}
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}

		return result;
	}

	@Override
	public List<String> getProvinceList() {
		return mapper.selectProvinceList();
	}
	
	@Override
	public List<String> queryIdsByEntity(SmsChannelQuery query) {

		try {
			return mapper.queryIdsByEntity(query);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}

		return null;
	}

	@Override
	public Map<String, SmsChannel> queryChannelListByApis(String[] apis) {
		Map<String, SmsChannel> rsMap = new HashMap<String, SmsChannel>();
		List<SmsChannel> smschannelList = mapper.queryChannelListByApis(apis);
		if (smschannelList != null && smschannelList.size() > 0) {
			for (SmsChannel smsChannel : smschannelList) {
				rsMap.put(smsChannel.getChannelId(), smsChannel);
			}
		}
		return rsMap;
	}

}
