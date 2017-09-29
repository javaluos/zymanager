package com.zy.cms.elastic.service;

import com.zy.cms.common.RedisOperator;
import com.zy.cms.common.ZyLogger;
import com.zy.cms.elastic.ESClient;
import com.zy.cms.elastic.GlobalConfig;
import com.zy.cms.enums.SmsSendStatusEnum;
import com.zy.cms.mapper.master.VoiceMerchantAccountMapper;
import com.zy.cms.service.master.MerchantAccountService;
import com.zy.cms.util.DateUtil;
import com.zy.cms.util.JsonUtil;
import com.zy.cms.util.SmsContentUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.MerchantAccount;
import com.zy.cms.vo.query.SmsSendQuery;
import com.zy.cms.vo.query.VoiceQuery;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Requests;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.*;
import org.elasticsearch.index.query.MoreLikeThisQueryBuilder.Item;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.*;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class EsService {

	private static final ZyLogger logger = ZyLogger.getLogger(EsService.class);
	private static ConcurrentMap<String, MerchantAccount> merchantAccountMap = new ConcurrentHashMap<String, MerchantAccount>();
	@Resource
	private ESClient esClient;
	@Resource
	private GlobalConfig globalConfig;
	@Resource
	private MerchantAccountService merchantAccountService;
	@Resource
	private RedisOperator redis;
	@Autowired
	private VoiceMerchantAccountMapper voiceMerchantAccountMapper;

	/**
	 * 添加索引数据
	 * 
	 * @param jsonv
	 *            data
	 * @param indexName
	 *            索引名称
	 * @param indexType
	 *            索引类型
	 * @return
	 */
	public boolean addIndex(Map<String, Object> map, String indexName, String indexType) {
		try {
			BulkRequestBuilder bulkRequest = esClient.getClient().prepareBulk();
			bulkRequest.add(
					esClient.getClient().prepareIndex(indexName, indexType, map.get("ID").toString()).setSource(map));
			BulkResponse bulkResponse = bulkRequest.execute().actionGet();
			return bulkResponse.hasFailures();
		} catch (Exception e) {
			logger.error("【ES】添加索引数据异常" + e.getMessage(), e);
			return true;
		}
	}

	/**
	 * 添加索引数据
	 * 
	 * @param list
	 *            data
	 * @param indexName
	 *            索引名称
	 * @param indexType
	 *            索引类型
	 * @return
	 */
	public BulkResponse addQueryIndex(List<Map<String, String>> list, String indexName, String indexType) {
		BulkResponse bulkResponse = null;
		try {
			BulkRequestBuilder bulkRequest = esClient.getClient().prepareBulk();
			for (int i = 0; i < list.size(); i++) {
				Map map = list.get(i);
				map.remove("SYC_CONT");// 移除失败次数的属性
				String json = JsonUtil.objectToJson(map);
				if (!StringUtil.isEmpty(map.get("ID") + "")) {
					bulkRequest.add(esClient.getClient().prepareIndex(indexName, indexType, map.get("ID") + "")
							.setSource(json));
				} else {
					logger.error("【ES】添加索引数据数据格式不对，没有ID===" + map);
				}
			}
			bulkResponse = bulkRequest.execute().actionGet();
			return bulkResponse;
		} catch (Exception e) {
			logger.error("【ES】添加索引数据异常" + e.getMessage(), e);
			return bulkResponse;
		}
	}

	/**
	 * 更新索引数据
	 * 
	 * @param map
	 *            data
	 * @param indexName
	 *            索引名称
	 * @param indexType
	 *            索引类型
	 * @return
	 */
	public boolean updateIndex(Map map, String indexName, String indexType) {
		try {

			BulkRequestBuilder bulkRequestBuilder = esClient.getClient().prepareBulk();
			bulkRequestBuilder
					.add(esClient.getClient().prepareUpdate(indexName, indexType, map.get("ID") + "").setDoc(map));
			BulkResponse bulkResponse = bulkRequestBuilder.execute().actionGet();
			return bulkResponse.hasFailures();
		} catch (Exception e) {
			logger.error("【ES】更新索引数据异常" + e.getMessage(), e);
			return true;
		}
	}

	/**
	 * 批量更新索引数据
	 * 
	 * @param List
	 *            <Map> list
	 * @param indexName
	 *            索引名称
	 * @param indexType
	 *            索引类型
	 * @return
	 */
	public BulkResponse updateBatchIndex(List<Map<String, Object>> list, String indexName, String indexType) {
		BulkResponse bulkResponse = null;
		try {
			BulkRequestBuilder bulkRequestBuilder = esClient.getClient().prepareBulk();
			if (list != null && list.size() > 0) {
				for (Map map : list) {
					if (map != null && map.size() > 0) {
						bulkRequestBuilder.add(esClient.getClient()
								.prepareUpdate(indexName, indexType, map.get("ID") + "").setDoc(map));
					}
				}
				bulkResponse = bulkRequestBuilder.execute().actionGet();
				return bulkResponse;
			}
			logger.error("【ES】list为空");
			return bulkResponse;
		} catch (Exception e) {
			logger.error("【ES】更新索引数据异常" + e.getMessage(), e);
			return bulkResponse;
		}
	}

	/**
	 * 创建索引
	 */
	public void createIndex() {
		String index = globalConfig.getConfigV("INDEX_NAME");
		String indexType = globalConfig.getConfigV("INDEX_TYPE");
		esClient.getClient().admin().indices().prepareCreate(index).execute().actionGet();
		try {
			XContentBuilder mapping = XContentFactory.jsonBuilder().startObject().startObject(index)
					.startObject("properties").startObject("ID").field("type", "string").field("store", "yes")
					.endObject().startObject("MERCHANT_ACCOUNT").field("type", "string").field("store", "yes")
					.endObject().startObject("SMS_SIGNER_ID").field("type", "string").field("store", "yes").endObject()
					.startObject("SMS_TEMPLATE_ID").field("type", "string").field("store", "yes").endObject()
					.startObject("SMS_CONTENT").field("type", "string").field("store", "yes").endObject()
					.startObject("SMS_CHANNEL_CODE").field("type", "string").field("store", "yes").endObject()
					.startObject("CREATE_TIME").field("type", "string").field("store", "yes").endObject()
					.startObject("RECEIVE_MOBILE").field("type", "string").field("store", "yes").endObject()
					.startObject("RECEIVE_TIME").field("type", "string").field("store", "yes").endObject()
					.startObject("SEND_RESULT").field("type", "string").field("store", "yes").endObject()
					.startObject("RESOURCE").field("type", "string").field("store", "yes").endObject()
					.startObject("RECEIVE_STATUS").field("type", "string").field("store", "yes").endObject()
					.startObject("APP_ID").field("type", "string").field("store", "yes").endObject()
					.startObject("SMS_TYPE").field("type", "string").field("store", "yes").endObject()
					.startObject("PROVINCE").field("type", "string").field("store", "yes").endObject()
					.startObject("CITY").field("type", "string").field("store", "yes").endObject()
					.startObject("CARRIERS").field("type", "string").field("store", "yes").endObject()
					.startObject("RECEIVE_STATUS_CHANNTL").field("type", "string").field("store", "yes").endObject()
					.startObject("RECEIVE_STATUS_DESC").field("type", "string").field("store", "yes").endObject()
					.startObject("GET_REPORT_TIME").field("type", "string").field("store", "yes").endObject()
					.endObject().endObject().endObject();
			PutMappingRequest mappingRequest = Requests.putMappingRequest(index).type(indexType).source(mapping);
			esClient.getClient().admin().indices().putMapping(mappingRequest).actionGet();
		} catch (IOException e) {
			logger.error("【ES】创建索引库异常" + e.getMessage(), e);
		}
	}

	/**
	 * 语音验证码发送记录ElasticSearch table
	 * 
	 * @param index
	 * @param indexType
	 */
	public void createVoiceSendIndex(String index, String indexType) {
		esClient.getClient().admin().indices().prepareCreate(index).execute().actionGet();
		try {
			XContentBuilder mapping = XContentFactory.jsonBuilder().startObject().startObject(index)
					.startObject("properties").startObject("ID").field("type", "string").field("store", "yes")
					.endObject()
					// 语音短信智验平台消息ID
					.startObject("UID").field("type", "string").field("store", "yes").endObject()
					// 客户语音消息ID
					.startObject("MERCHANT_ACCOUNT").field("type", "string").field("store", "yes").endObject()
					// 账户
					.startObject("CONTENT").field("type", "string").field("store", "yes").endObject()
					// 语音内容
					.startObject("TELEPHONE").field("type", "string").field("store", "yes").endObject()
					// 被叫号码(手机或座机)
					.startObject("INVOKE_STATUS").field("type", "string").field("store", "yes").endObject()
					// -1初始化状态 0提交成功 1提交失败
					.startObject("INVOKE_REASON").field("type", "string").field("store", "yes").endObject()
					// 提交状态描述
					.startObject("DISPLAY_NUM").field("type", "string").field("store", "yes").endObject()
					// 语音电话显示号码
					.startObject("CITY").field("type", "string").field("store", "yes").endObject()
					// 电话所在城市
					.startObject("PROVINCE").field("type", "string").field("store", "yes").endObject()
					// 电话所在省市
					.startObject("INVOKE_TIME").field("type", "long").field("store", "yes").endObject()
					// vos接口调用成功时间
					.startObject("SEND_TIME").field("type", "long").field("store", "yes").endObject()
					// 语音发送时间
					.startObject("DURATION").field("type", "integer").field("store", "yes").endObject()
					// 语音通话时长
					.startObject("STATUS_CODE").field("type", "integer").field("store", "yes").endObject()
					// 状态编号 语音通话状态 0接通并应答 1接通未应答 2未接通
					.startObject("STATUS_DES").field("type", "string").field("store", "yes").endObject()
					// 状态报告描述 语音通话状态 0接通并应答 1接通未应答 2未接通
					.startObject("STATUS_REPORT_TIME").field("type", "long").field("store", "yes").endObject()
					// 状态报告回来时间
					.startObject("CALL_START_TIME").field("type", "long").field("store", "yes").endObject()
					// 语音通话开始时间
					.startObject("CALL_STOP_TIME").field("type", "long").field("store", "yes").endObject()
					// 语音通话结束时间
					.startObject("CALLER_PDD").field("type", "integer").field("store", "yes").endObject()
					// 主叫续延
					.startObject("CALLEE_PDD").field("type", "integer").field("store", "yes").endObject()
					// 被叫接通延迟
					.startObject("PLAYVOICETIMES").field("type", "integer").field("store", "yes").endObject() // 重播次数
					.endObject().endObject().endObject();
			PutMappingRequest mappingRequest = Requests.putMappingRequest(index).type(indexType).source(mapping);
			esClient.getClient().admin().indices().putMapping(mappingRequest).actionGet();
		} catch (IOException e) {
			logger.error("【ES】创建语音通话记录索引库异常" + e.getMessage(), e);
		}
	}

	/**
	 * 搜索
	 * 
	 * @param map
	 * @param indexName
	 * @param indexType
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String search(Map map, String indexName, String indexType) {

		long start = System.currentTimeMillis();
		SearchRequestBuilder searchRequestBuilder = esClient.getClient().prepareSearch(indexName)
				.setSize(Integer.MAX_VALUE).setTypes(indexType).setSearchType(SearchType.DFS_QUERY_THEN_FETCH);
		if (map != null && map.size() > 0) {
			BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
			if (!StringUtil.isEmpty(map.get("MERCHANT_ACCOUNT") + "")) {
				TermQueryBuilder merchantaccount = QueryBuilders.termQuery("MERCHANT_ACCOUNT",
						map.get("MERCHANT_ACCOUNT") + "");
				boolQueryBuilder.must(merchantaccount);
			}
			if (!StringUtil.isEmpty(map.get("ID") + "")) {
				TermQueryBuilder id = QueryBuilders.termQuery("ID", map.get("ID") + "");
				boolQueryBuilder.must(id);
			}
			if (!StringUtil.isEmpty(map.get("UID") + "")) {
				TermQueryBuilder uid = QueryBuilders.termQuery("UID", map.get("UID") + "");
				boolQueryBuilder.must(uid);
			}
			if (!StringUtil.isEmpty(map.get("CONTENT") + "")) {
				TermQueryBuilder content = QueryBuilders.termQuery("CONTENT", map.get("CONTENT") + "");
				boolQueryBuilder.must(content);
			}
			if (!StringUtil.isEmpty(map.get("TELEPHONE") + "")) {
				TermQueryBuilder telephone = QueryBuilders.termQuery("TELEPHONE", map.get("TELEPHONE") + "");
				boolQueryBuilder.must(telephone);
			}
			if (!StringUtil.isEmpty(map.get("INVOKE_STATUS") + "")) {
				TermQueryBuilder invokeStatus = QueryBuilders.termQuery("INVOKE_STATUS", map.get("INVOKE_STATUS"));
				boolQueryBuilder.must(invokeStatus);
			}
			if (!StringUtil.isEmpty(map.get("STATUS_CODE") + "")) {
				TermQueryBuilder statusCode = QueryBuilders.termQuery("STATUS_CODE", map.get("STATUS_CODE") + "");
				boolQueryBuilder.must(statusCode);
			}
			if (!StringUtil.isEmpty(map.get("TEMPLATE_ID") + "")) {
				TermQueryBuilder templateId = QueryBuilders.termQuery("TEMPLATE_ID", map.get("TEMPLATE_ID") + "");
				boolQueryBuilder.must(templateId);
			}
			if (!StringUtil.isEmpty(map.get("TEMPLATE_TITLE") + "")) {
				MoreLikeThisQueryBuilder query = QueryBuilders.moreLikeThisQuery();
				Item item = new Item();
				item.fields("TEMPLATE_TITLE");
				query.addLikeText(map.get("TEMPLATE_TITLE").toString()).addLikeItem(item);
				searchRequestBuilder.setQuery(query);

			}
			if (!StringUtil.isEmpty(map.get("DISPLAY_NUM") + "")) {
				TermQueryBuilder display_num = QueryBuilders.termQuery("DISPLAY_NUM", map.get("DISPLAY_NUM") + "");
				boolQueryBuilder.must(display_num);
			}
			if (!StringUtil.isEmpty(map.get("INVOKE_TIME") + "")) {
				String tt = map.get("INVOKE_TIME") + "";
				String[] ts = tt.split(",");
				searchRequestBuilder.setPostFilter(
						QueryBuilders.rangeQuery("INVOKE_TIME").from(Long.parseLong(ts[0])).to(Long.parseLong(ts[1])));// 2.1
			}
			// 排序
			if (!StringUtil.isEmpty(map.get("ORDER_BY") + "")) {
				SortOrder sortOrder = SortOrder.DESC;
				String order = map.get("ORDER_BY") + "";
				if (!StringUtil.isEmpty(order)) {
					searchRequestBuilder = searchRequestBuilder.addSort(order, sortOrder);
				}
			}
			searchRequestBuilder.setQuery(boolQueryBuilder);
		}
		int from = !StringUtil.isEmpty(map.get("PAGE_NUM") + "") ? Integer.parseInt(map.get("PAGE_NUM") + "") : 0;
		int pageSize = !StringUtil.isEmpty(map.get("PAGE_SIZE") + "") ? Integer.parseInt(map.get("PAGE_SIZE") + "")
				: 10;

		SearchResponse response = searchRequestBuilder.setFrom(from * pageSize).setSize(pageSize).execute().actionGet();
		SearchHits hits = response.getHits();
		long times = System.currentTimeMillis() - start;
		long total = hits.getTotalHits();
		Map rs = new HashMap();
		List<Map> list = new ArrayList<Map>();
		for (int i = 0; i < hits.getHits().length; i++) {
			Map m = hits.getHits()[i].getSource();
			list.add(m);
		}
		rs.put("DATA", list);
		rs.put("TIMES", times);
		rs.put("TOTAL_PAGE", (total / pageSize) + (total % pageSize != 0 ? 1 : 0));
		rs.put("TOTAL", total);
		rs.put("PAGE_NUM", from);
		rs.put("PAGE_SIZE", pageSize);
		String result = JsonUtil.toJsonString(rs);
		return result;
	}

	/**
	 * 统计,统计维度为:账户,应用,短信类型
	 * 
	 * @param map
	 *            主要传时间段 CREATE_TIME
	 * @param indexName
	 * @param indexType
	 * @return
	 */
	public String group(Map map, String indexName, String indexType) {
		long start = System.currentTimeMillis();
		// 创建search
		SearchRequestBuilder searchRequestBuilder = esClient.getClient().prepareSearch(indexName).setTypes(indexType)
				.setSearchType(SearchType.COUNT);
		if (!StringUtil.isEmpty(map.get("CREATE_TIME") + "")) {
			String tt = map.get("CREATE_TIME") + "";
			String[] ts = tt.split(",");
			// searchRequestBuilder.setPostFilter(FilterBuilders.rangeFilter("CREATE_TIME").from(Long.parseLong(ts[0])).to(Long.parseLong(ts[1])));
			searchRequestBuilder.setPostFilter(
					QueryBuilders.rangeQuery("CREATE_TIME").from(Long.parseLong(ts[0])).to(Long.parseLong(ts[1])));// 2.1
		}
		searchRequestBuilder.setQuery(QueryBuilders.termQuery("MERCHANT_ACCOUNT", map.get("MERCHANT_ACCOUNT")));
		// 创建统计维度，根据应用和短信类型去统计
		TermsBuilder apps = AggregationBuilders.terms("APP_ID").field("APP_ID");
		TermsBuilder smstypes = AggregationBuilders.terms("SMS_TYPE").field("SMS_TYPE");
		apps.subAggregation(smstypes);
		searchRequestBuilder.addAggregation(apps);

		// 执行统计
		SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();
		long times = System.currentTimeMillis() - start;
		Map<String, Aggregation> aggMap = searchResponse.getAggregations().asMap();
		List<Map> list = new ArrayList<Map>();
		String termclass = aggMap.get("APP_ID").getClass().getName();
		termclass = termclass.substring(termclass.lastIndexOf(".") + 1);
		Iterator<Bucket> appBucketIt = null;
		if (termclass.equals("StringTerms")) {
			StringTerms appTerms = (StringTerms) aggMap.get("APP_ID");
			appBucketIt = appTerms.getBuckets().iterator();
		} else if (termclass.equals("LongTerms")) {
			LongTerms appTerms = (LongTerms) aggMap.get("APP_ID");
			appBucketIt = appTerms.getBuckets().iterator();
		} else if (termclass.equals("InternalTerms")) {
			InternalTerms appTerms = (InternalTerms) aggMap.get("APP_ID");
			appBucketIt = appTerms.getBuckets().iterator();
		} else if (termclass.equals("DoubleTerms")) {
			DoubleTerms appTerms = (DoubleTerms) aggMap.get("APP_ID");
			appBucketIt = appTerms.getBuckets().iterator();
		} else {
			StringTerms appTerms = (StringTerms) aggMap.get("APP_ID");
			appBucketIt = appTerms.getBuckets().iterator();
		}

		if (appBucketIt != null) {
			while (appBucketIt.hasNext()) {
				Bucket appBucket = appBucketIt.next();
				String termsmstypeclass = appBucket.getAggregations().asMap().get("SMS_TYPE").getClass().getName();
				Iterator<Bucket> smstypeBucketIt = null;
				if (termclass.equals("StringTerms")) {
					StringTerms smstypeTerms = (StringTerms) appBucket.getAggregations().asMap().get("SMS_TYPE");
					smstypeBucketIt = smstypeTerms.getBuckets().iterator();
				} else if (termclass.equals("LongTerms")) {
					LongTerms smstypeTerms = (LongTerms) appBucket.getAggregations().asMap().get("SMS_TYPE");
					smstypeBucketIt = smstypeTerms.getBuckets().iterator();
				} else if (termclass.equals("InternalTerms")) {
					InternalTerms smstypeTerms = (InternalTerms) appBucket.getAggregations().asMap().get("SMS_TYPE");
					smstypeBucketIt = smstypeTerms.getBuckets().iterator();
				} else if (termclass.equals("DoubleTerms")) {
					DoubleTerms smstypeTerms = (DoubleTerms) appBucket.getAggregations().asMap().get("SMS_TYPE");
					smstypeBucketIt = smstypeTerms.getBuckets().iterator();
				} else {
					StringTerms smstypeTerms = (StringTerms) appBucket.getAggregations().asMap().get("SMS_TYPE");
					smstypeBucketIt = smstypeTerms.getBuckets().iterator();
				}
				if (smstypeBucketIt != null) {
					while (smstypeBucketIt.hasNext()) {
						Bucket smsBucket = smstypeBucketIt.next();
						Map te = new HashMap();
						te.put("MERCHANT_ACCOUNT", map.get("MERCHANT_ACCOUNT"));
						te.put("APP_ID", appBucket.getKey());
						te.put("SMS_TYPE", smsBucket.getKey());
						te.put("SEND_TOTAL", smsBucket.getDocCount());
						list.add(te);
					}
				}
			}
		}
		Map r = new HashMap();
		r.put("DATA", list);
		r.put("TIME", times);
		return JsonUtil.toJsonString(r);
	}

	/**
	 * 话单创建 创建索引
	 */
	public void createCdrIndex(String indexName, String indexTypes) {
		String index = indexName;
		String indexType = indexTypes;

		try {
			esClient.getClient().admin().indices().prepareCreate(index).execute().actionGet();
			XContentBuilder mapping = XContentFactory.jsonBuilder().startObject().startObject(index)
					.startObject("properties").startObject("ID").field("type", "string").field("store", "yes")
					.endObject().startObject("CALLID").field("type", "string").field("store", "yes").endObject()
					.startObject("REQUEST_ID").field("type", "string").field("store", "yes").endObject()
					.startObject("TYPE").field("type", "int").field("store", "yes").endObject().startObject("CALLER")
					.field("type", "string").field("store", "yes").endObject().startObject("CALLER_DISPLAY_NUMBER")
					.field("type", "string").field("store", "yes").endObject().startObject("CALLEE")
					.field("type", "string").field("store", "yes").endObject().startObject("CALLEE_DISPLAY_NUMBER")
					.field("type", "string").field("store", "yes").endObject().startObject("CALLER_INVITE_TIME")
					.field("type", "int").field("store", "yes").endObject().startObject("CALLER_RINGING_BEGIN_TIME")
					.field("type", "int").field("store", "yes").endObject().startObject("CALLER_ANSWER_TIME")
					.field("type", "int").field("store", "yes").endObject().startObject("CALLER_HANGUP_TIME")
					.field("type", "int").field("store", "yes").endObject().startObject("CALLEE_INVITE_TIME")
					.field("type", "int").field("store", "yes").endObject().startObject("CALLEE_RINGING_BEGIN_TIME")
					.field("type", "int").field("store", "yes").endObject().startObject("CALLEE_ANSWER_TIME")
					.field("type", "int").field("store", "yes").endObject().startObject("CALLEE_HANGUP_TIME")
					.field("type", "int").field("store", "yes").endObject().startObject("HOLD_TIME")
					.field("type", "int").field("store", "yes").endObject().startObject("HANGUP_CODE")
					.field("type", "int").field("store", "yes").endObject().startObject("HANGUP_REASON")
					.field("type", "string").field("store", "yes").endObject().startObject("FEE_TIME")
					.field("type", "int").field("store", "yes").endObject().startObject("FEE").field("type", "int")
					.field("store", "yes").endObject().startObject("FEE_RATE").field("type", "int")
					.field("store", "yes").endObject().startObject("RECORD").field("type", "int").field("store", "yes")
					.endObject().startObject("RECORDING_FILE_URL").field("type", "string").field("store", "yes")
					.endObject().startObject("PUSH_STATES").field("type", "int").field("store", "yes").endObject()
					.startObject("DTMF").field("type", "string").field("store", "yes").endObject()
					.startObject("API_ACCOUNT").field("type", "string").field("store", "yes").endObject()
					.startObject("APP_ID").field("type", "string").field("store", "yes").endObject().startObject("LEG")
					.field("type", "string").field("store", "yes").endObject().startObject("CREATE_TIME")
					.field("type", "string").field("store", "yes").endObject().endObject().endObject().endObject();
			PutMappingRequest mappingRequest = Requests.putMappingRequest(index).type(indexType).source(mapping);
			esClient.getClient().admin().indices().putMapping(mappingRequest).actionGet();

		} catch (IOException e) {
			logger.error("【ES】创建cdr索引库异常" + e.getMessage(), e);
		}
	}

	/**
	 * 添加话单cdr索引数据
	 * 
	 * @param maps
	 *            参数列表 Map<String, String>[key=ID ,value=对象JSON序列数据]
	 * 
	 * @param indexName
	 *            索引名称
	 * @param indexType
	 *            索引类型
	 * @return
	 */
	public boolean addCdrIndexBatch(List<Map<String, String>> maps, String indexName, String indexType) {

		try {

			BulkRequestBuilder bulkRequest = esClient.getClient().prepareBulk();
			BulkResponse bulkResponse = null;

			for (Map<String, String> map : maps) {
				bulkRequest.add(esClient.getClient().prepareIndex(indexName, indexType, map.get("key"))
						.setSource(map.get("value")));
			}
			bulkResponse = bulkRequest.execute().actionGet();
			boolean result = !bulkResponse.hasFailures();
			if (!result) {
				Iterator<BulkItemResponse> iter = bulkResponse.iterator();
				while (iter.hasNext()) {
					BulkItemResponse itemResponse = iter.next();
					if (itemResponse.isFailed()) {
						logger.error(itemResponse.getFailureMessage());
					}
				}
			}
			return result;

		} catch (Exception e) {
			logger.error("【ES】添加CDR索引数据异常:" + e.getMessage(), e);
			return false;
		}
	}

	/**
	 * 搜索 Cdr
	 * 
	 * @param map
	 * @param indexName
	 * @param indexType
	 * @return
	 */
	public String searchCdr(VoiceQuery query, String indexName, String indexType) {

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

				if (query.getApiAccounts() != null && query.getApiAccounts().size() > 0) {
					QueryBuilder type = QueryBuilders.termsQuery("apiAccount", query.getApiAccounts());
					boolQueryBuilder.must(type);
				}

				if (!StringUtil.isEmpty(query.getAppid())) {
					TermQueryBuilder appId = QueryBuilders.termQuery("appId", query.getAppid().toLowerCase());
					boolQueryBuilder.must(appId);
				}

				if (query.getQuerytype() == -1) {// 语音通知和语音验证码一起查询出来
					QueryBuilder type = QueryBuilders.termsQuery("type", "4", "5");
					boolQueryBuilder.must(type);
				} else if (query.getQuerytype() == -2) {// 直拨数据查询出来
					QueryBuilder type = QueryBuilders.termQuery("type", "3");
					boolQueryBuilder.must(type);
					QueryBuilder leg = QueryBuilders.termQuery("leg", "b");
					boolQueryBuilder.must(leg);
				} else if (query.getQuerytype() == -3) {// 回拨数据查询出来type=1 &&
														// ((leg=a &&
														// holdtime=0)||leg=b)
														// type=1 && leg=a &&
														// holdtime=0 ||leg=b
					QueryBuilder type = QueryBuilders.termQuery("type", "1");
					boolQueryBuilder.must(type);
					boolQueryBuilder.must(QueryBuilders.boolQuery()
							.should(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("leg", "a"))
									.must(QueryBuilders.termQuery("holdTime", "0")))
							.should(QueryBuilders.termQuery("leg", "b")));
				} else if (query.getQuerytype() == -4) { // 直拨
					QueryBuilder type = QueryBuilders.termQuery("type", "2");
					boolQueryBuilder.must(type);
					// QueryBuilder leg = QueryBuilders.termQuery("leg","b");
					boolQueryBuilder.must(QueryBuilders.boolQuery()
							.should(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("leg", "a"))
									.must(QueryBuilders.termQuery("holdTime", "0")))
							.should(QueryBuilders.termQuery("leg", "b")));
				} else {
					TermQueryBuilder type = QueryBuilders.termQuery("type", query.getQuerytype());
					boolQueryBuilder.must(type);
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
							.from(query.getStarttimeL()).to(query.getEndtimeL()).includeLower(true).includeUpper(true));
				} else if (query.getStarttimeL() > 0) {
					searchRequestBuilder.setPostFilter(QueryBuilders.rangeQuery("calleeInviteTime")
							.from(query.getStarttimeL()).includeLower(true));
				} else if (query.getEndtimeL() > 0) {
					searchRequestBuilder.setPostFilter(
							QueryBuilders.rangeQuery("calleeInviteTime").to(query.getEndtimeL()).includeUpper(true));
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

	public String searchSmsSend(SmsSendQuery query, String indexName, String indexType) {
		SearchRequestBuilder searchRequestBuilder = esClient.getClient().prepareSearch(indexName)
				.setSize(Integer.MAX_VALUE).setTypes(indexType).setSearchType(SearchType.DFS_QUERY_THEN_FETCH);
		String result = "";
		try {
			long start = System.currentTimeMillis();
			searchRequestBuilder = createSearchBuilder(searchRequestBuilder, query);

			int from = query.getPageNum();
			int pageSize = query.getPageSize();
			SearchResponse response = searchRequestBuilder.setFrom(from * pageSize).setSize(pageSize).execute()
					.actionGet();
			SearchHits hits = response.getHits();
			long total = hits.getTotalHits();
			long total_fee_num = 0;
			long times = System.currentTimeMillis() - start;
			if (total > 0) {
				// 查询计费条数
				total_fee_num = createSearchFeeNumBuilder(searchRequestBuilder, query, indexName, indexType);
			}
			Map<String, Object> rs = getResult(hits, "query");
			rs.put("times", times);
			rs.put("total_page", (total / pageSize) + (total % pageSize != 0 ? 1 : 0));
			rs.put("total", total);
			rs.put("page_num", (from + 1));
			rs.put("page_size", pageSize);
			rs.put("total_fee_num", total_fee_num);
			result = JsonUtil.toJsonString(rs);

		} catch (Exception e) {
			logger.error("【ES】查询CDR索引数据异常:" + e.getMessage(), e);
		}
		return result;
	}

	public String searchRepushSmsSend(SmsSendQuery query, String indexName, String indexType) {
		SearchRequestBuilder searchRequestBuilder = esClient.getClient().prepareSearch(indexName)
				.setSize(Integer.MAX_VALUE).setTypes(indexType).setSearchType(SearchType.DFS_QUERY_THEN_FETCH);
		String result = "";
		try {
			long start = System.currentTimeMillis();
			searchRequestBuilder = createSearchBuilder(searchRequestBuilder, query);

			int from = query.getPageNum();
			int pageSize = query.getPageSize();
			SearchResponse response = searchRequestBuilder.setFrom(from * pageSize).setSize(pageSize).execute()
					.actionGet();
			SearchHits hits = response.getHits();
			long total = hits.getTotalHits();
			long total_fee_num = 0;
			long times = System.currentTimeMillis() - start;
			if (total > 0) {
				// 查询计费条数
				// total_fee_num =
				// createSearchFeeNumBuilder(searchRequestBuilder, query,
				// indexName, indexType);
			}
			Map<String, Object> rs = getResult(hits, "export");
			rs.put("times", times);
			rs.put("total_page", (total / pageSize) + (total % pageSize != 0 ? 1 : 0));
			rs.put("total", total);
			rs.put("page_num", (from + 1));
			rs.put("page_size", pageSize);
			rs.put("total_fee_num", total_fee_num);
			result = JsonUtil.toJsonString(rs);

		} catch (Exception e) {
			logger.error("【ES】查询CDR索引数据异常:" + e.getMessage(), e);
		}
		return result;
	}

	private SearchRequestBuilder createSearchBuilder(SearchRequestBuilder searchRequestBuilder, SmsSendQuery query) {
		if (query != null) {
			BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
			if (!StringUtil.isEmpty(query.getSmsId())) {
				TermQueryBuilder id = QueryBuilders.termQuery("SMS_ID", query.getSmsId().toLowerCase());
				boolQueryBuilder.must(id);
			}
			String merchantPhone = query.getMerchantPhone();
			if (!StringUtil.isEmpty(merchantPhone)) {
				MerchantAccount merchantAccount = merchantAccountService.getMerchantAccountByPhone(merchantPhone);
				if (null != merchantAccount) {
					TermQueryBuilder apiAccount = QueryBuilders.termQuery("API_ACCOUNT",
							merchantAccount.getApiAccount().toLowerCase());
					boolQueryBuilder.must(apiAccount);
				} else {
					TermQueryBuilder apiAccount = QueryBuilders.termQuery("API_ACCOUNT", merchantPhone.toLowerCase());
					boolQueryBuilder.must(apiAccount);
				}
			}

			if (query.getApiAccounts() != null && query.getApiAccounts().size() > 0) {
				QueryBuilder type = QueryBuilders.termsQuery("API_ACCOUNT", query.getApiAccounts());
				boolQueryBuilder.must(type);
			}

			if (!StringUtil.isEmpty(query.getChannelId())) {
				String channelIdStr = query.getChannelId().replaceAll("-", "_");
				TermQueryBuilder channelId = QueryBuilders.termQuery("SMS_CHANNEL_ID", channelIdStr.toLowerCase());
				boolQueryBuilder.must(channelId);
			}

			if (!StringUtil.isEmpty(query.getReceiveMobile())) {
				TermQueryBuilder receiveMobile = QueryBuilders.termQuery("RECEIVE_MOBILE",
						query.getReceiveMobile().toLowerCase());
				boolQueryBuilder.must(receiveMobile);
			}
			if (!StringUtil.isEmpty(query.getOperator())) {
				TermQueryBuilder carriers = QueryBuilders.termQuery("CARRIERS", query.getOperator().toLowerCase());
				boolQueryBuilder.must(carriers);
			}
			String starttime = query.getStarttime();
			String endtime = query.getEndtime();
			if (StringUtils.isNotBlank(starttime) || StringUtils.isNotBlank(endtime)) {
				long startTimeStamp = 0;
				long endTimeStamp = 0;
				if (StringUtils.isNotBlank(starttime)) {
					startTimeStamp = DateUtil.formatDateLong(starttime);
				}
				if (StringUtils.isNotBlank(endtime)) {
					endTimeStamp = DateUtil.formatDateLong(endtime);
				}
				if (startTimeStamp > 0 && endTimeStamp > 0) {
					RangeQueryBuilder ct = QueryBuilders.rangeQuery("CREATE_TIME").from(startTimeStamp)
							.to(endTimeStamp);
					boolQueryBuilder.must(ct);
				} else if (startTimeStamp > 0) {
					RangeQueryBuilder ct = QueryBuilders.rangeQuery("CREATE_TIME").from(startTimeStamp)
							.to(Long.MAX_VALUE);
					boolQueryBuilder.must(ct);

				} else if (endTimeStamp > 0) {
					RangeQueryBuilder ct = QueryBuilders.rangeQuery("CREATE_TIME").from(0).to(endTimeStamp);
					boolQueryBuilder.must(ct);
				}
			}
			if (!StringUtil.isEmpty(query.getCategory())) {
				TermQueryBuilder smsCategory = QueryBuilders.termQuery("SMS_CATEGORY",
						query.getCategory().toLowerCase());
				boolQueryBuilder.must(smsCategory);
			}
			if (StringUtils.isNotBlank(query.getStatus())) {
				if (query.getStatus().equals(String.valueOf(SmsSendStatusEnum.SUBMIT_FAIL.getType()))) { // 提交失败的
					TermQueryBuilder sendResult = QueryBuilders.termQuery("SEND_RESULT", "1");// (1:提交失败)
					boolQueryBuilder.must(sendResult);
				} else {
					// 成功,失败,未知的状态
					TermQueryBuilder sendResult = QueryBuilders.termQuery("SEND_RESULT", "0");// (0:提交成功)
					boolQueryBuilder.must(sendResult);
					TermQueryBuilder status = QueryBuilders.termQuery("RECEIVE_STATUS",
							query.getStatus().toLowerCase());
					boolQueryBuilder.must(status);
				}
			}

			// 内容匹配查询
			String smsContent = query.getSmsContent();
			if (StringUtils.isNotBlank(smsContent)) {
				smsContent = StringUtil.replaceSpecStr(smsContent).toLowerCase();
				Map<String, String> numberMap = SmsContentUtil.getNumbers(smsContent); // 取出内容中的所有数字字符串
				smsContent = SmsContentUtil.replaceAllNumber(numberMap, smsContent);

				for (int i = 0; i < smsContent.length();) {
					String index = smsContent.charAt(i) + "";
					if (null != numberMap && numberMap.size() > 0) {
						for (Map.Entry<String, String> entry : numberMap.entrySet()) {
							if (("$").equals(index)) {
								boolQueryBuilder.must(QueryBuilders.wildcardQuery("SMS_CONTENT", entry.getValue()));
								String before = smsContent.substring(0, i);
								String back = smsContent.substring((i + 1), smsContent.length());
								smsContent = before + entry.getValue() + back;
								i += entry.getValue().length();
								numberMap.remove(entry.getKey());
								break;
							} else {
								boolQueryBuilder
										.must(QueryBuilders.wildcardQuery("SMS_CONTENT", smsContent.charAt(i) + ""));
								i++;
								break;
							}
						}
					} else {
						boolQueryBuilder.must(QueryBuilders.wildcardQuery("SMS_CONTENT", smsContent.charAt(i) + ""));
						i++;
					}

				}

			}
			String errorCode = query.getErrorCode();
			if (StringUtils.isNotBlank(errorCode)) {
				String[] errorArray = errorCode.split(":");
				if (null != errorArray) {
					if (errorArray.length == 1) {
						TermQueryBuilder errorCodeQuery = QueryBuilders.termQuery("RECEIVE_STATUS_DESC",
								errorCode.toLowerCase());
						boolQueryBuilder.must(errorCodeQuery);
					} else if (errorArray.length > 1) {
						for (int i = 0; i < errorArray.length; i++) {
							boolQueryBuilder
									.must(QueryBuilders.termQuery("RECEIVE_STATUS_DESC", errorArray[i].toLowerCase()));
						}
					}
				}
			}

			// 分省查询
			if (!StringUtil.isEmpty(query.getProvince())) {

				char[] pe = query.getProvince().toCharArray();
				for (int i = 0; i < pe.length; i++) {
					TermQueryBuilder peBuilder = QueryBuilders.termQuery("PROVINCE", String.valueOf(pe[i]));
					boolQueryBuilder.must(peBuilder);
				}
			}
			searchRequestBuilder = searchRequestBuilder.addSort("CREATE_TIME", SortOrder.DESC);
			searchRequestBuilder.setQuery(boolQueryBuilder);
		}
		return searchRequestBuilder;
	}

	/**
	 * 查询计费条数
	 * 
	 * @param searchRequestBuilder
	 * @param query
	 * @param indexName
	 * @param indexType
	 * @return
	 */
	private Long createSearchFeeNumBuilder(SearchRequestBuilder searchRequestBuilder, SmsSendQuery query,
			String indexName, String indexType) {

		Long feeNums = 0L;
		if (query != null) {
			BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
			if (!StringUtil.isEmpty(query.getSmsId())) {
				TermQueryBuilder id = QueryBuilders.termQuery("SMS_ID", query.getSmsId().toLowerCase());
				boolQueryBuilder.must(id);
			}
			String merchantPhone = query.getMerchantPhone();
			if (!StringUtil.isEmpty(merchantPhone)) {
				MerchantAccount merchantAccount = merchantAccountService.getMerchantAccountByPhone(merchantPhone);
				if (null != merchantAccount) {
					TermQueryBuilder apiAccount = QueryBuilders.termQuery("API_ACCOUNT",
							merchantAccount.getApiAccount().toLowerCase());
					boolQueryBuilder.must(apiAccount);
				} else {
					TermQueryBuilder apiAccount = QueryBuilders.termQuery("API_ACCOUNT", merchantPhone.toLowerCase());
					boolQueryBuilder.must(apiAccount);
				}
			}

			if (query.getApiAccounts() != null && query.getApiAccounts().size() > 0) {
				QueryBuilder type = QueryBuilders.termsQuery("API_ACCOUNT", query.getApiAccounts());
				boolQueryBuilder.must(type);
			}

			if (!StringUtil.isEmpty(query.getChannelId())) {
				String channelIdStr = query.getChannelId().replaceAll("-", "_");
				TermQueryBuilder channelId = QueryBuilders.termQuery("SMS_CHANNEL_ID", channelIdStr.toLowerCase());
				boolQueryBuilder.must(channelId);
			}

			if (!StringUtil.isEmpty(query.getReceiveMobile())) {
				TermQueryBuilder receiveMobile = QueryBuilders.termQuery("RECEIVE_MOBILE",
						query.getReceiveMobile().toLowerCase());
				boolQueryBuilder.must(receiveMobile);
			}
			if (!StringUtil.isEmpty(query.getOperator())) {
				TermQueryBuilder carriers = QueryBuilders.termQuery("CARRIERS", query.getOperator().toLowerCase());
				boolQueryBuilder.must(carriers);
			}
			String starttime = query.getStarttime();
			String endtime = query.getEndtime();
			if (StringUtils.isNotBlank(starttime) || StringUtils.isNotBlank(endtime)) {
				long startTimeStamp = 0;
				long endTimeStamp = 0;
				if (StringUtils.isNotBlank(starttime)) {
					startTimeStamp = DateUtil.formatDateLong(starttime);
				}
				if (StringUtils.isNotBlank(endtime)) {
					endTimeStamp = DateUtil.formatDateLong(endtime);
				}
				if (startTimeStamp > 0 && endTimeStamp > 0) {
					RangeQueryBuilder ct = QueryBuilders.rangeQuery("CREATE_TIME").from(startTimeStamp)
							.to(endTimeStamp);
					boolQueryBuilder.must(ct);
				} else if (startTimeStamp > 0) {
					RangeQueryBuilder ct = QueryBuilders.rangeQuery("CREATE_TIME").from(startTimeStamp)
							.to(Long.MAX_VALUE);
					boolQueryBuilder.must(ct);

				} else if (endTimeStamp > 0) {
					RangeQueryBuilder ct = QueryBuilders.rangeQuery("CREATE_TIME").from(0).to(endTimeStamp);
					boolQueryBuilder.must(ct);
				}
			}
			if (!StringUtil.isEmpty(query.getCategory())) {
				TermQueryBuilder smsCategory = QueryBuilders.termQuery("SMS_CATEGORY",
						query.getCategory().toLowerCase());
				boolQueryBuilder.must(smsCategory);
			}

			if (StringUtils.isNotBlank(query.getSmsContent())) {
				QueryBuilder contentQuery = QueryBuilders.matchQuery("SMS_CONTENT", query.getSmsContent());
				boolQueryBuilder.must(contentQuery);
			}

			if (StringUtils.isNotBlank(query.getStatus())) {
				if (query.getStatus().equals(String.valueOf(SmsSendStatusEnum.SUBMIT_FAIL.getType()))) { // 提交失败的

					TermQueryBuilder sendResult = QueryBuilders.termQuery("SEND_RESULT", "1");// (1:提交失败)
					boolQueryBuilder.must(sendResult);
				} else {

					// 成功,失败,未知的状态
					TermQueryBuilder sendResult = QueryBuilders.termQuery("SEND_RESULT", "0");// (0:提交成功)
					boolQueryBuilder.must(sendResult);
					TermQueryBuilder status = QueryBuilders.termQuery("RECEIVE_STATUS",
							query.getStatus().toLowerCase());
					boolQueryBuilder.must(status);
				}
			}

			// 内容匹配查询
			String smsContent = query.getSmsContent();
			if (StringUtils.isNotBlank(smsContent)) {
				smsContent = StringUtil.replaceSpecStr(smsContent).toLowerCase();
				Map<String, String> numberMap = SmsContentUtil.getNumbers(smsContent); // 取出内容中的所有数字字符串
				smsContent = SmsContentUtil.replaceAllNumber(numberMap, smsContent);

				for (int i = 0; i < smsContent.length();) {
					String index = smsContent.charAt(i) + "";
					if (null != numberMap && numberMap.size() > 0) {
						for (Map.Entry<String, String> entry : numberMap.entrySet()) {
							if (("$").equals(index)) {
								boolQueryBuilder.must(QueryBuilders.wildcardQuery("SMS_CONTENT", entry.getValue()));
								String before = smsContent.substring(0, i);
								String back = smsContent.substring((i + 1), smsContent.length());
								smsContent = before + entry.getValue() + back;
								i += entry.getValue().length();
								numberMap.remove(entry.getKey());
								break;
							} else {
								boolQueryBuilder
										.must(QueryBuilders.wildcardQuery("SMS_CONTENT", smsContent.charAt(i) + ""));
								i++;
								break;
							}
						}
					} else {
						boolQueryBuilder.must(QueryBuilders.wildcardQuery("SMS_CONTENT", smsContent.charAt(i) + ""));
						i++;
					}

				}

			}
			String errorCode = query.getErrorCode();
			if (StringUtils.isNotBlank(errorCode)) {
				String[] errorArray = errorCode.split(":");
				if (null != errorArray) {
					if (errorArray.length == 1) {
						TermQueryBuilder errorCodeQuery = QueryBuilders.termQuery("RECEIVE_STATUS_DESC",
								errorCode.toLowerCase());
						boolQueryBuilder.must(errorCodeQuery);
					} else if (errorArray.length > 1) {
						for (int i = 0; i < errorArray.length; i++) {
							boolQueryBuilder
									.must(QueryBuilders.termQuery("RECEIVE_STATUS_DESC", errorArray[i].toLowerCase()));
						}
					}
				}
			}

			// 分省查询
			if (!StringUtil.isEmpty(query.getProvince())) {

				char[] pe = query.getProvince().toCharArray();
				for (int i = 0; i < pe.length; i++) {
					TermQueryBuilder peBuilder = QueryBuilders.termQuery("PROVINCE", String.valueOf(pe[i]));
					boolQueryBuilder.must(peBuilder);
				}
			}

			searchRequestBuilder.setQuery(boolQueryBuilder);
			searchRequestBuilder.addField("SMS_NUMS");
			SearchResponse sr = searchRequestBuilder
					.addAggregation(AggregationBuilders.terms("SMS_NUMS").field("SMS_NUMS")).execute().actionGet();
			Terms summums = sr.getAggregations().get("SMS_NUMS");

			if (summums != null) {
				Iterator<Bucket> smsBucketIt = summums.getBuckets().iterator();
				if (smsBucketIt != null) {
					while (smsBucketIt.hasNext()) {
						Bucket smsBucket = smsBucketIt.next();
						String key = smsBucket.getKey() + "";
						String count = smsBucket.getDocCount() + "";
						if (StringUtil.isEmpty(key)) {
							key = "1";
						}
						if (StringUtil.isEmpty(count)) {
							count = "0";
						}
						feeNums = Long.parseLong(key) * Long.parseLong(count) + feeNums;
					}
				}
			}
		}
		return feeNums;
	}

	private Map<String, Object> getResult(SearchHits hits, String operate) {
		Map rs = new HashMap();
		List<Map> list = new ArrayList<Map>();
		for (int i = 0; i < hits.getHits().length; i++) {
			Map m = hits.getHits()[i].getSource();
			if (null != m.get("CREATE_TIME") && null != m.get("RECEIVE_TIME")
					&& StringUtils.isNotBlank(m.get("CREATE_TIME").toString())
					&& StringUtils.isNotBlank(m.get("RECEIVE_TIME").toString())) {
				long createTime = Long.valueOf(m.get("CREATE_TIME").toString());
				long receiveTime = Long.valueOf(m.get("RECEIVE_TIME").toString());
				m.put("SEND_PERIOD", (receiveTime - createTime) / 1000);
			}
			if (null != m.get("CREATE_TIME") && null != m.get("UPDATE_TIME")
					&& StringUtils.isNotBlank(m.get("CREATE_TIME").toString())
					&& StringUtils.isNotBlank(m.get("UPDATE_TIME").toString())) {
				long createTime = Long.valueOf(m.get("CREATE_TIME").toString());
				long updateTime = Long.valueOf(m.get("UPDATE_TIME").toString());
				m.put("STATUS_PERIOD", (updateTime - createTime) / 1000);
			}
			if (null != m.get("CREATE_TIME") && StringUtils.isNotBlank(m.get("CREATE_TIME").toString())) {
				long createTime = Long.valueOf(m.get("CREATE_TIME").toString());
				m.put("CREATE_TIME", DateUtil.formatDate(new Date(createTime), DateUtil.ISO_DATE_TIME_FORMAT));
			}
			String apiAccount = m.get("API_ACCOUNT").toString();
			MerchantAccount merchantAccount = null;
			if ("export".equals(operate)) {
				merchantAccount = getByApiAccount(apiAccount);
			} else {
				merchantAccount = voiceMerchantAccountMapper.getMerchantAccount(apiAccount);
			}
			if (null != merchantAccount) {
				m.put("BUSINESS_NAME", merchantAccount.getBusinessName());
				m.put("MERCHANT_PHONE", merchantAccount.getMerchantPhone());
			}
			if (null != m.get("SMS_CHANNEL_ID") && StringUtils.isNotBlank(m.get("SMS_CHANNEL_ID").toString())) {
				m.put("SMS_CHANNEL_ID", m.get("SMS_CHANNEL_ID").toString().replaceAll("_", "-"));
			}
			list.add(m);
		}
		rs.put("data", list);
		return rs;
	}

	private Map<String, Object> getExportResult(SearchHits hits, String operate) {
		Map rs = new HashMap();
		List<Map> list = new ArrayList<Map>();
		for (int i = 0; i < hits.getHits().length; i++) {
			Map m = hits.getHits()[i].getSource();
			list.add(m);
		}
		rs.put("data", list);
		return rs;
	}

	public String searchSmsSendStat(SmsSendQuery query, String indexName, String indexType) {

		String result = "";
		try {

			long start = System.currentTimeMillis();
			SearchRequestBuilder searchRequestBuilder = esClient.getClient().prepareSearch(indexName)
					.setSize(Integer.MAX_VALUE).setTypes(indexType).setSearchType(SearchType.DFS_QUERY_THEN_FETCH);

			BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
			if (query.getApiAccounts() != null && query.getApiAccounts().size() > 0) {
				QueryBuilder apiAccount = QueryBuilders.termsQuery("API_ACCOUNT", query.getApiAccounts());
				boolQueryBuilder.must(apiAccount);
			}
			if (!StringUtil.isEmpty(query.getStarttime())) {
				TermQueryBuilder carriersBuilder = QueryBuilders.termQuery("DATE_TIME", query.getStarttime());
				boolQueryBuilder.must(carriersBuilder);
			}

			String starttime = query.getStarttime();
			String endtime = query.getEndtime();
			if (StringUtils.isNotBlank(starttime) || StringUtils.isNotBlank(endtime)) {
				long startTimeStamp = 0;
				long endTimeStamp = 0;
				if (StringUtils.isNotBlank(starttime)) {
					startTimeStamp = DateUtil.formatDateLong(starttime + " 00:00:00");
				}
				if (StringUtils.isNotBlank(endtime)) {
					endTimeStamp = DateUtil.formatDateLong(starttime + " 23:59:59");
				}
				if (startTimeStamp > 0 && endTimeStamp > 0) {
					searchRequestBuilder.setPostFilter(QueryBuilders.rangeQuery("CREATE_TIME").from(startTimeStamp)
							.to(endTimeStamp).includeLower(true).includeUpper(true));
				} else if (startTimeStamp > 0) {
					searchRequestBuilder.setPostFilter(
							QueryBuilders.rangeQuery("CREATE_TIME").from(startTimeStamp).includeLower(true));
				} else if (endTimeStamp > 0) {
					searchRequestBuilder
							.setPostFilter(QueryBuilders.rangeQuery("CREATE_TIME").to(endTimeStamp).includeUpper(true));
				}
			}

			TermQueryBuilder sendResultBuilder = QueryBuilders.termQuery("SEND_RESULT", "0");
			boolQueryBuilder.must(sendResultBuilder);

			// 创建TermsBuilder对象，分组查询
			TermsBuilder apiAccountAggBuilder = AggregationBuilders.terms("apiAccountAgg").field("API_ACCOUNT");
			TermsBuilder smsCateGoryBuilder = AggregationBuilders.terms("smsCateGoryAgg").field("SMS_CATEGORY");
			TermsBuilder reveiceSattusAggBuilder = AggregationBuilders.terms("receiveStatusAgg")
					.field("RECEIVE_STATUS");

			searchRequestBuilder.addAggregation(apiAccountAggBuilder);

			// 添加分组信息
			apiAccountAggBuilder.subAggregation(smsCateGoryBuilder);
			smsCateGoryBuilder.subAggregation(reveiceSattusAggBuilder);

			// 执行搜索
			searchRequestBuilder.setQuery(boolQueryBuilder);
			SearchResponse searchResponse = searchRequestBuilder.setFrom(1).setSize(100000).execute().actionGet();

			long end = System.currentTimeMillis();
			logger.info("times=" + (end - start) / 1000 + " s");

			// 解析返回数据，获取分组名称为aggs-class的数据
			Terms terms = searchResponse.getAggregations().get("apiAccountAgg");
			Collection<Terms.Bucket> buckets = terms.getBuckets();
			List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
			for (Terms.Bucket apiAccount_buk : buckets) {
				// 二级分组的内容
				Terms terms_category = apiAccount_buk.getAggregations().get("smsCateGoryAgg");
				for (Terms.Bucket category_buk : terms_category.getBuckets()) {

					// 级分组的内容
					Terms terms_status = category_buk.getAggregations().get("receiveStatusAgg");
					for (Terms.Bucket status_buk : terms_status.getBuckets()) {

						// 三级分组的内容
						Map<String, String> map = new HashMap<String, String>();
						map.put("API_ACCOUNT", apiAccount_buk.getKey() + "");
						map.put("SMS_CATEGORY", category_buk.getKey() + "");
						map.put("STATUS", status_buk.getKey() + "");
						map.put("COUNT", status_buk.getDocCount() + "");

						dataList.add(map);
					}
				}

			}

			result = JsonUtil.toJsonString(dataList);
			logger.info("result=" + result);

		} catch (Exception e) {
			logger.error("【ES】查询CDR索引数据异常:" + e.getMessage(), e);
		}
		return result;
	}

	public List<Map<String, Object>> searchExportSmsSend1(SmsSendQuery query, String indexName, String indexType) {
		List<MerchantAccount> accountList = voiceMerchantAccountMapper.getAllMerchantAccount();
		for (MerchantAccount merchantAccount : accountList) {
			merchantAccountMap.put(merchantAccount.getApiAccount(), merchantAccount);
		}
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		SearchRequestBuilder searchRequestBuilder = esClient.getClient().prepareSearch(indexName).setTypes(indexType)
				.setScroll(TimeValue.timeValueMinutes(5)).setSize(10000).setSearchType(SearchType.DFS_QUERY_THEN_FETCH);
		try {

			long start = System.currentTimeMillis();
			searchRequestBuilder = createSearchBuilder(searchRequestBuilder, query);

			SearchResponse response = searchRequestBuilder.setFrom(0).setSize(50).execute().actionGet();
			SearchHits hits = response.getHits();
			int shards = response.getSuccessfulShards();// 总的分片数
			long total = hits.getTotalHits();
			if (total > query.getPageCount()) { // 当前最大的导出行数
				return null;
			}
			int queryMaxSize = query.getPageSize(); // 每次查询的最大条数
			int queryCount = (((int) total - 1) / queryMaxSize) + 1; // 分批查询的次数
			if (queryCount >= 1) {

				for (int i = 1; i <= queryCount; i++) {

					int querySize = queryMaxSize;
					if (i == queryCount) { // 最后一次查询,取剩余的条数
						querySize = (int) total % queryMaxSize;
					}
					SearchResponse response1 = searchRequestBuilder.setSize(querySize).setFrom((i - 1) * queryMaxSize)
							.execute().actionGet();

					SearchHits hits1 = response1.getHits();
					Map<String, Object> rs = getResult(hits1, "export");
					list.add(rs);
				}
			}
			long times = System.currentTimeMillis() - start;
			logger.info("【发送记录导出】查询 " + total + "条记录,共耗时" + times + "ms");

		} catch (Exception e) {
			logger.error("【ES】查询CDR索引数据异常:" + e.getMessage(), e);
		}
		return list;
	}

	public List<Map<String, Object>> searchExportSmsSend(SmsSendQuery query, String indexName, String indexType) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {

			List<MerchantAccount> accountList = voiceMerchantAccountMapper.getAllMerchantAccount();
			for (MerchantAccount merchantAccount : accountList) {
				merchantAccountMap.put(merchantAccount.getApiAccount(), merchantAccount);
			}

			long start = System.currentTimeMillis();
			SearchRequestBuilder searchRequestBuilder = esClient.getClient().prepareSearch().setIndices(indexName)
					.setTypes(indexType).setScroll(TimeValue.timeValueMinutes(2)).setSearchType(SearchType.SCAN);

			searchRequestBuilder = createSearchBuilder(searchRequestBuilder, query);
			SearchResponse response = searchRequestBuilder.setSize(query.getPageSize()).execute().actionGet();

			// 第一次不返回数据
			int shards = response.getSuccessfulShards();// 总的分片数
			long total = response.getHits().getTotalHits();
			if (total > query.getPageCount()) { // 当前最大的导出行数
				return null;
			}

			int sum = 0;
			int page = (int) total / (shards * query.getPageSize());// 计算总页数,每次搜索数量为分片数*设置的size大小
			String scrollId = response.getScrollId();
			for (int i = 0; i <= page; i++) {

				response = esClient.getClient().prepareSearchScroll(scrollId).setScroll(TimeValue.timeValueMinutes(8))
						.execute().actionGet();

				scrollId = response.getScrollId();
				SearchHits hits1 = response.getHits();
				sum += hits1.hits().length;
				Map<String, Object> rs = getResult(hits1, "export");
				list.add(rs);

				logger.info("ESScroll总数:" + total + "条，已经查到：" + sum + "条。");
				if (hits1.getHits().length == 0) {
					break;
				}
			}

			long times = System.currentTimeMillis() - start;
			logger.info("【发送记录导出】查询 " + total + "条记录,共耗时" + times + "ms");

		} catch (Exception e) {
			logger.error("【ES】查询CDR索引数据异常:" + e.getMessage(), e);
		}
		return list;
	}

	private MerchantAccount getByApiAccount(String apiAccount) {
		MerchantAccount merchantAccount = merchantAccountMap.get(apiAccount);
		return merchantAccount;
	}

}
