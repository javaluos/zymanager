package com.zy.cms.service.master;

import java.util.List;

import com.zy.cms.vo.VoiceWhiteListVo;
import com.zy.cms.vo.query.VoiceWhiteListQuery;

public interface VoiceWhiteListService {

	Integer queryCountByEntity(VoiceWhiteListQuery query) throws Exception;

	List<VoiceWhiteListVo> queryListByEntity(VoiceWhiteListQuery query) throws Exception;

	boolean add(String apiAccount, String userName) throws Exception;

	boolean getByApiAccount(String apiAccount) throws Exception;

	boolean delete(Integer id) throws Exception;

}
