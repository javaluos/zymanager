<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- QueryData -->
<div id="QueryData">
	<div id="DataTables_Table_0_wrapper"
		class="dataTables_wrapper form-inline" role="grid">
		<table
			class="table table-striped table-bordered table-hover dataTables-example dataTable"
			id="DataTables_Table_0" aria-describedby="DataTables_Table_0_info">
			<thead>
				<tr role="row">
					<th style="background: 0 0;min-width: 30px;">序号</th>
					<th tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
						colspan="1">客户名称</th>
					<th tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
						colspan="1">客户账号</th>
					<th tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
						colspan="1">通道ID</th>
					<th tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
						colspan="1">接收手机号</th>
					<th tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
						colspan="1">短信内容</th>
					<th tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
						colspan="1">上行内容</th>
					<th tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
						colspan="1" style="min-width: 55px">上行时间</th>
					<th tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
						colspan="1" style="min-width: 55px">上行接入号</th>
					<th tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
						colspan="1" style="min-width: 55px">上行状态</th>
					<th tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
						colspan="1" style="min-width: 55px">操作</th>
				</tr>
			</thead>
			<tbody>

				<c:if test="${not empty pgdata.data }">
					<c:forEach var="dt" items="${pgdata.data}" varStatus="dtindex">
						<tr class="gradeC odd motr">

							<td>${(pgdata.page_num-1)*pgdata.page_size + dtindex.index+1}</td>
							<td>${accountMaps[dt.apiAccount].businessName}</td>
							<td>${accountMaps[dt.apiAccount].merchantPhone}</td>
							<td>${dt.channelId}</td>
							<td>${dt.mobile }</td>
							<td>${dt.smsContent }</td>
							<td>${dt.content }</td>
							<td>${dt.reply_time }</td>
							<td>${dt.ext_number }</td>
							<td class="mostat"><c:choose>
									<c:when test="${dt.status==1}">
										 推送成功
									</c:when>
									<c:when test="${dt.status==2}">   
									              推送失败
									 </c:when>
									<c:when test="${dt.status==0}">
										 未推送
									</c:when>
									<c:otherwise>        
								    </c:otherwise>
								</c:choose>
							</td>
							<td><a onclick="javascript:;" class="dopushmo" _id="${dt.id }">重推上行</a></td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty pgdata.data }">
					<tr>
						<td colspan="11" style="text-align: center;">暂无数据.</td>
					</tr>
				</c:if>

			</tbody>

		</table>
		<p class="msg">
			<jsp:include page="../comm/pgbar.jsp" />
		</p>
	</div>

</div>

<script type="text/javascript">

$(function(){
	
	$(".dopushmo").click(function(){
		var target = $(this);
		var moId = target.attr("_id");
		$.ajax({
			//提交数据的类型 POST GET
			type : "POST",
			//提交的网址
			url : "/sms_reply/list_repushMo",
			//提交的数据
			data : {moId : moId },
			//返回数据的格式
			datatype : "application/json",
			//在请求之前调用的函数
			beforeSend : function() {
			},
			//成功返回之后调用的函数             
			success : function(data) {
				 if(data !='' && data.status==0){
					target.parent().prev().html("推送成功");
					messageBox("重推上行记录", "重推成功.");
				 }else{
					messageBox("重推上行记录","无推送地址或服务异常."); 
				 }
			},
			//调用出错执行的函数
			error : function() {
				messageBox("重推上行记录", "重推失败,无推送地址或服务异常.");
			}
		});

	});
});
</script>
