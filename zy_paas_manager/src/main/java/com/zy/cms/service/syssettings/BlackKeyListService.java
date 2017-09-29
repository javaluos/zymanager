package com.zy.cms.service.syssettings;

import java.util.List;
import java.util.Map;

import com.zy.cms.vo.WebResult;

public interface BlackKeyListService {
	WebResult export(Map<String, Object> query, String realPath, String webUrl, String type);

	List<Map<String, Object>> initExcelHead();

	List<Map<String, Object>> initData(Map<String, Object> query);
}
