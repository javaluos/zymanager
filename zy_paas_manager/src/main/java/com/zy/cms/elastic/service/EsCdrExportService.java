package com.zy.cms.elastic.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Component;

import com.zy.cms.common.ZyLogger;
import com.zy.cms.elastic.ESClient;
import com.zy.cms.elastic.GlobalConfig;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.query.VoiceQuery;

@Component
public class EsCdrExportService {

	private static final ZyLogger logger = ZyLogger.getLogger(EsService.class);
	@Resource
	private ESClient esClient;
	@Resource
	private GlobalConfig globalConfig;

	/**
	 * 搜索 Voice Cdr
	 * 
	 * @param map
	 * @param indexName
	 * @param indexType
	 * @return
	 */
	public String searchVoiceCdr(VoiceQuery query, String indexName, String indexType) {

		String result = "";
		try {

			long start = System.currentTimeMillis();
			SearchRequestBuilder searchRequestBuilder = esClient.getClient().prepareSearch(indexName)
					.setSize(Integer.MAX_VALUE).setTypes(indexType).setSearchType(SearchType.DFS_QUERY_THEN_FETCH);

			if (query != null) {
				BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

				if (!StringUtil.isEmpty(query.getApiAccount())) {
					TermQueryBuilder apiAccount = QueryBuilders.termQuery("apiAccount",
							query.getApiAccount().toLowerCase());
					boolQueryBuilder.must(apiAccount);
				}

				if (!StringUtil.isEmpty(query.getAppid())) {
					TermQueryBuilder appId = QueryBuilders.termQuery("appId", query.getAppid().toLowerCase());
					boolQueryBuilder.must(appId);
				}

				if (query.getQuerytype() == 0) {// 语音通知和语音验证码一起查询出来
					QueryBuilder type = QueryBuilders.termsQuery("type", "4", "5");
					boolQueryBuilder.must(type);
				} else {
					TermQueryBuilder type = QueryBuilders.termQuery("type", query.getQuerytype());
					boolQueryBuilder.must(type);
					QueryBuilder leg = QueryBuilders.termQuery("leg","b");
					boolQueryBuilder.must(leg);
				}

				if (!StringUtil.isEmpty(query.getCallee())) {
					TermQueryBuilder callee = QueryBuilders.termQuery("callee", query.getCallee().toLowerCase());
					boolQueryBuilder.must(callee);
				}

				if (query.getState() != -1) {
					TermQueryBuilder state = QueryBuilders.termQuery("state", query.getState());
					boolQueryBuilder.must(state);
				}

				if (query.getHangupCode() != 0) {
					TermQueryBuilder hangupCode = QueryBuilders.termQuery("hangupCode", query.getHangupCode());
					boolQueryBuilder.must(hangupCode);
				}

				if (query.getStarttimeL() > 0 && query.getEndtimeL() > 0) {
					searchRequestBuilder.setPostFilter(QueryBuilders.rangeQuery("calleeInviteTime")
							.from(query.getStarttimeL()).to(query.getEndtimeL()).
							includeUpper(true).includeLower(true));
				} else if (query.getStarttimeL() > 0) {
					searchRequestBuilder
							.setPostFilter(QueryBuilders.rangeQuery("calleeInviteTime")
							.from(query.getStarttimeL()).includeLower(true));
				} else if (query.getEndtimeL() > 0) {
					searchRequestBuilder.setPostFilter(
							QueryBuilders.rangeQuery("calleeInviteTime").
							to(query.getEndtimeL()).includeUpper(false));
				}

				searchRequestBuilder = searchRequestBuilder.addSort("calleeInviteTime", SortOrder.DESC);
				searchRequestBuilder.setQuery(boolQueryBuilder);
			}

			int from = query.getPageNum();
			int pageSize = query.getPageSize();

			SearchResponse response = searchRequestBuilder.setFrom(from * pageSize).setSize(pageSize).execute()
					.actionGet();
			SearchHits hits = response.getHits();
			long times = System.currentTimeMillis() - start;
			long total = hits.getTotalHits();
			Map rs = new HashMap();
			List<Map> list = new ArrayList<Map>();
			for (int i = 0; i < hits.getHits().length; i++) {
				Map m = hits.getHits()[i].getSource();
				list.add(m);
			}

			rs.put("data", list);
			rs.put("times", times);
			rs.put("total_page", (total / pageSize) + (total % pageSize != 0 ? 1 : 0));
			rs.put("total", total);
			rs.put("page_num", (from + 1));
			rs.put("page_size", pageSize);
			result = JsonUtil.toJsonString(rs);

		} catch (Exception e) {
			logger.error("【ES】查询CDR索引数据异常:" + e.getMessage(), e);
		}
		return result;
	}
}
