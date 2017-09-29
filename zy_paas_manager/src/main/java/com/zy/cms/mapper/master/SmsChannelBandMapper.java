package com.zy.cms.mapper.master;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zy.cms.vo.channel.SmsChannelBand;
import com.zy.cms.vo.channel.SmsChannelBandVo;
import com.zy.cms.vo.query.SmsChannelBindQuery;

public interface SmsChannelBandMapper {
    int deleteByPrimaryKey(Integer id) throws SQLException;

    int insert(SmsChannelBand record);

    int insertSelective(SmsChannelBand record);

    SmsChannelBand selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SmsChannelBand record);

    int updateByPrimaryKey(SmsChannelBand record);

	int selectCountByQuery(SmsChannelBindQuery query);

	List<SmsChannelBandVo> selectListByQuery(SmsChannelBindQuery query);

	List<SmsChannelBandVo> selectByApiAccount(String apiAccount);

	SmsChannelBand selectByAccountAndChannel(@Param("apiAccount") String apiAccount, @Param("channelId") String channelId);

	int deleteByApiAccount(String apiAccount);
}