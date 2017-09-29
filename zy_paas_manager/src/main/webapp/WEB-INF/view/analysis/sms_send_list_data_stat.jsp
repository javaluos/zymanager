<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/tags" prefix="date"%>

<!-- QueryData -->
<div id="QueryData">

	<div id="DataTables_Table_0_wrapper"
		class="dataTables_wrapper form-inline" role="grid">
		<table
			class="table table-striped table-bordered table-hover dataTables-example dataTable"
			id="DataTables_Table_0" aria-describedby="DataTables_Table_0_info">
			<thead>
				<tr role="row">
					<th style="background: 0 0; min-width: 30px">序号</th>
					<th tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
						colspan="1" style="min-width: 55px">客户名称</th>
					<th tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
						colspan="1" style="min-width: 55px">客户账号</th>
					<th tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
						colspan="1" style="min-width: 55px">通道ID</th>
					<th tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
						colspan="1" style="min-width: 300px">短信内容</th>
					<th tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
						colspan="1" style="min-width: 45px">拓展号</th>
					<th tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
						colspan="1" style="min-width: 55px">接收手机</th>
					<th tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
						colspan="1" style="min-width: 115px">客户提交时间</th>
					<th tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
						colspan="1" style="min-width: 115px">平台提交时间</th>
					<th tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
						colspan="1" style="min-width: 115px">用户接收短信时间</th>
					<th tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
						colspan="1" style="min-width: 115px">状态报告返回时间</th>
					<th tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
						colspan="1" style="min-width: 55px">发送状态</th>
					<th tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
						colspan="1" style="min-width: 55px">状态描述</th>
<!-- 					<th tabindex="0" aria-controls="DataTables_Table_0" rowspan="1" -->
<!-- 						colspan="1" style="min-width: 50px; max-width: 100px;">异常日志</th> -->
					<th tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
						colspan="1" style="min-width: 50px; max-width: 100px;">操作</th>
				</tr>
			</thead>
			<tbody>

				<c:if test="${not empty pgdata.data }">
					<c:forEach var="dt" items="${pgdata.data}" varStatus="dtindex">
						<tr class="gradeC odd">
							<td>${(pgdata.page_num-1)*pgdata.page_size + dtindex.index+1}<input
								type="hidden" name="smsId" value="${dt.SMS_ID}" /></td>
							<td>${dt.BUSINESS_NAME}</td>
							<td>${dt.MERCHANT_PHONE}</td>
							<td>${dt.SMS_CHANNEL_ID}</td>
							<td>${dt.SMS_CONTENT }</td>
							<td>${dt.EXT_NUMBER }</td>
							<td>${dt.RECEIVE_MOBILE }</td>
							<td>${dt.CREATE_TIME }</td>
							<td><date:date value ="${dt.SUBMIT_TIME}"/></td>
							<td><date:date value ="${dt.UPDATE_TIME}"/></td>
							<td><date:date value ="${dt.RECEIVE_TIME}"/></td>
							<td>
							    <c:choose>
							        <c:when test="${dt.SEND_RESULT == '1'}">
                                          提交失败
                                    </c:when>
                                    <c:otherwise>
                                         <c:if test="${dt.RECEIVE_STATUS == '-1'}">
                                            状态未知
                                        </c:if>
                                        <c:if test="${dt.RECEIVE_STATUS == '0'}">
                                            发送成功
                                        </c:if>
                                        <c:if test="${dt.RECEIVE_STATUS == '1'}">
                                            发送失败
                                        </c:if>
                                    </c:otherwise>
							    </c:choose>
							</td>
							<td>
							<c:if test="${dt.SEND_RESULT == 0}">
									${dt.RECEIVE_STATUS_DESC }
						     </c:if> 
						    </td>
<%-- 							<td><c:choose> --%>
<%-- 									<c:when test="${fn:length(dt.TRACE_LOG) > 14}"> --%>
<%-- 										<p title="${fn:escapeXml(dt.TRACE_LOG)}">${fn:substring(dt.TRACE_LOG, 0, 14)} --%>
<!-- 											...</p> -->
<%-- 									</c:when> --%>
<%-- 									<c:otherwise> --%>
<%-- 										${fn:escapeXml(dt.TRACE_LOG)} --%>
<%-- 									</c:otherwise> --%>
<%-- 								</c:choose></td> --%>
							<td>
								 <a onclick="rePush('${dt.SMS_ID}')">重推报告</a>
							</td>
						</tr>
					</c:forEach>

				</c:if>
				<c:if test="${empty pgdata.data }">
					<tr>
						<td colspan="19" style="text-align: center;">暂无数据.</td>
					</tr>
				</c:if>

			</tbody>

		</table>
		<p class="msg">
			<div class="row" style="text-align: center;margin-right: auto; margin-left: auto;">
	<div class="col-sm-11" style="text-align：left;">
		<div class="dataTables_info pgnum" id="DataTables_Table_0_info"
			role="alert" aria-live="polite" aria-relevant="all" style="margin-top: 5px;float: left;">
			<li style="border: none;">&nbsp;共计 <span style="color: red;">${pgdata.total}</span>
				条
			</li>
			<li style="border: none;">&nbsp;每页 <span style="color: red;">${pgdata.page_size}</span>
				条
			</li>
			<li style="border: none;">&nbsp;<span style="color: red;">${pgdata.page_num}</span>
				/ <span style="color: red;">${pgdata.total_page}</span> 页&nbsp;
			</li>
			
			<li style="border: none;">&nbsp;计费 <span style="color: red;">${total_fee_num}</span> 条
			</li>
		</div>
	
		<div class="dataTables_paginate paging_simple_numbers"
			id="DataTables_Table_0_paginate">
			<ul class="pagination pgbar">
				<li class="link paginate_button previous"
					aria-controls="DataTables_Table_0" tabindex="0"
					
					id="DataTables_Table_0_previous">
					<a href="javascript:;"
					_pgnum="1">首页</a>
				</li>
				<li class="link paginate_button previous"
					aria-controls="DataTables_Table_0" tabindex="0"
					id="DataTables_Table_0_previous"><a href="javascript:;"
					_pgnum="${pgdata.page_num-1 }">上一页</a></li>

				<c:forEach var="item" begin="1" end="${pgdata.viewcount }" step="1"
					varStatus="flag">
					<li
						class="link paginate_button "
						aria-controls="DataTables_Table_0" tabindex="0"><a
						href="javascript:;" _pgnum="${pgdata.pgstartno + flag.index}">${pgdata.pgstartno + flag.index}</a>
					</li>
				</c:forEach>

				<li class="link paginate_button next" aria-controls="DataTables_Table_0"
					tabindex="0" id="DataTables_Table_0_next"><a
					href="javascript:;" _pgnum="${pgdata.page_num+1 }">下一页</a></li>
				<li class="link paginate_button next" aria-controls="DataTables_Table_0"
					tabindex="0" id="DataTables_Table_0_next"><a
					href="javascript:;" _pgnum="${pgdata.total_page }">尾页</a></li>

				<li><input type="text" class="input20" id="pageNum"
					style="width: 60px; display: inline-block; line-height: 28px; height: 28px; margin-left: 1px;"
					value="${pgdata.page_num}"
					onkeyup="this.value=this.value.replace(/\D/g,'')"
					onafterpaste="this.value=this.value.replace(/\D/g,'')"></li>
				<li><button type="button" class="btn btn-white" id="pggobtn"
						style="text-align: center;line-height: 28px; height: 28px; padding: 0px 10px; margin-top: -3px; margin-left: -4px;">GO</button>
					<input type="hidden" id="maxpg" value="${pgdata.total_page}"></li>
			</ul>
		</div>
	</div>
</div>
			
		</p>
	</div>

</div>
<script>
	function rePush(smsIdVal){
		var params={};
		var merchantPhone = $('#merchantPhone').val();
		var channelId = $('#channelId').val();
		var receiveMobile = $('#receiveMobile').val();
		var operator = $('#operator').val();
		var starttime = $('#starttime').val();
		var endtime = $('#endtime').val();
		var category = $('#category').val();
		var status = $('#status').val();
		var smsContent = $('#smsContent').val();
		var smsId=smsIdVal;
	   params={"merchantPhone":merchantPhone,"channelId":channelId,"receiveMobile":receiveMobile,"operator":operator,"starttime":starttime,"endtime":endtime,"category":category,"status":status,"smsContent":smsContent,"smsId":smsId};
	   $.ajax({
			type: "POST",
	   		url: "/sms_send/rePushStat",
	   	    data:{params: JSON.stringify(params)},
	   		dataType: "json",
		   	success: function(data){
		   		if(data=="1"){
		   			window.top.location.href='/login.html';
		   		}else if(data=="2"){
// 		   			window.location.href='/sms_send_filter/to_list.html';
		   			var index2=layer.alert("提交状态报告推送线程成功！");
		   			setTimeout(function(){
		   			layer.close(index2);
		   			}, 1000);

		   		}else if(data=="0"){
		   			layer.alert("没有可推送状态报告！");
		   		}else if(data=="4"){
		   			layer.alert("已停止状态报告推送！");
		   		}
		   		else{//3
		   			layer.alert("状态报告推送失败！");
		   		}
		   	}
		});
		
		
	}
	
	function stopPush(){
		 $.ajax({
				type: "POST",
		   		url: "/sms_send/stopPushStat",
		   		dataType: "json",
			   	success: function(data){
			   		if(data==true){
			   			layer.alert("已停止状态报告推送！！");
			   		}
			   		else{//3
			   			layer.alert("状态报告推送停止失败！");
			   		}
			   	}
			});
	}


</script>
