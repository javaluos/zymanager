package com.zy.cms.mapper.master;

import java.util.List;

import com.zy.cms.vo.VoiceWhiteListInfo;
import com.zy.cms.vo.VoiceWhiteListVo;
import com.zy.cms.vo.query.VoiceWhiteListQuery;

public interface VoiceWhiteListInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VoiceWhiteListInfo record);

    int insertSelective(VoiceWhiteListInfo record);

    VoiceWhiteListInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VoiceWhiteListInfo record);

    int updateByPrimaryKey(VoiceWhiteListInfo record);

	int selectCountByEntity(VoiceWhiteListQuery query) throws Exception;

	List<VoiceWhiteListVo> selectListByEntity(VoiceWhiteListQuery query) throws Exception;

	VoiceWhiteListInfo selectByApiAccount(String apiAccount) throws Exception;
}