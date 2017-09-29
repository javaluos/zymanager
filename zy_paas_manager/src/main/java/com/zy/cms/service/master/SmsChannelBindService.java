package com.zy.cms.service.master;

import java.sql.SQLException;
import java.util.List;

import com.zy.cms.vo.channel.SmsChannelBandVo;
import com.zy.cms.vo.query.SmsChannelBindQuery;

public interface SmsChannelBindService {

	Integer getCountByQuery(SmsChannelBindQuery query);

	List<SmsChannelBandVo> getListByQuery(SmsChannelBindQuery query);

	boolean modifyScore(Integer id, Integer score, Integer thresholdValue) throws SQLException;

	boolean deleteChannelBind(Integer id, String userName) throws SQLException;

	List<SmsChannelBandVo> getByApiAccount(String apiAccount);

	boolean dobindChannel(String apiAccount, String channels, String userName) throws Exception;

	String clearSendTotal(String apiAccount, String channelId) throws Exception;

}
