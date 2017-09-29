package com.zy.cms.mapper.master;

import java.sql.SQLException;
import java.util.List;

import com.zy.cms.vo.MerchantAccount;
import com.zy.cms.vo.channel.SmsChannel;
import com.zy.cms.vo.query.SmsChannelQuery;

public interface SmsChannelMapper {

	Integer querySmsChannelCountByEntity(SmsChannelQuery query) throws SQLException;

	List<SmsChannel> querySmsChannelListByEntity(SmsChannelQuery query) throws SQLException;

	SmsChannel selectByPrimaryKey(String channelId) throws SQLException;

	SmsChannel selectByChannelMainCode(String channelMainCode) throws SQLException;

	int insertSelective(SmsChannel channel) throws SQLException;

	int updateByPrimaryKeySelective(SmsChannel channel) throws SQLException;

	int deleteByPrimaryKey(String channelId) throws SQLException;

	List<String> selectProvinceList();
	
	List<String> queryIdsByEntity(SmsChannelQuery query) throws SQLException;

	List<SmsChannel> selectAllChannels() throws SQLException;
	
	public List<SmsChannel> queryChannelListByApis(String[] apis);

}