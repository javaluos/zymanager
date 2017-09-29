<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<script type="text/javascript" src="/js/handler/main.js"></script>
<script type="text/javascript" language="javascript"> 
function updateRule(id){
	window.location.href="/sms_channel_policy/to_edit?id="+id;
}

function deleteRule(id){
	$.ajax({
		type: "POST",
   		url: "/sms_channel_policy/do_delete",
   		data: {"id" : id},
   		dataType: "json",
	   	success: function(data){
   			layer.confirm(data, {
				area: ['450px'],
				btn: ['确认'] //按钮
			}, function(){
				layer.closeAll('dialog');
				 window.location.href="/sms_channel_policy/to_list";
			});
	   	}
	});
}
</script> 

<!-- QueryData -->
<div id="QueryData">

	<div id="DataTables_Table_0_wrapper"
		class="dataTables_wrapper form-inline" role="grid">
		<table
			class="table table-striped table-bordered table-hover dataTables-example dataTable"
			id="DataTables_Table_0" aria-describedby="DataTables_Table_0_info">
			<thead>
				<tr role="row">
					<th style="background: 0 0;" width="1%">序号</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" width="3%">分流策略名称</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" width="8%">分流策略内容</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1"width="1%">操作</th>
				</tr>
			</thead>
			<tbody>

				<c:if test="${not empty pgdata.data }">
					<c:forEach var="dt" items="${pgdata.data}" varStatus="dtindex">
						<tr class="gradeC odd">
							<td align="center">${(pgdata.page_num-1)*pgdata.page_size + dtindex.index+1}</td>
							<td align="left"><span align="center">${dt.policyName}</span></td>
							<td align="left">
							<c:forEach var="rule" items="${ruleList}" varStatus="ruleLindex">
							    <c:if test="${dt.id==rule.policyId}">
								   	  当短信内容匹配【 
								   	  <c:choose>
								   	      <c:when test="${not empty rule.mobiles and not empty rule.keyword }">关键字和号段</c:when>
								   	      <c:when test="${not empty rule.mobiles }">号段</c:when>
								   	      <c:when test="${not empty rule.keyword }">关键字</c:when>
								   	      <c:otherwise></c:otherwise>
								   	   </c:choose>
								   	  】时，移动从【 ${rule.groupYD}】、联通从【${rule.groupLT}】、电信从【${rule.groupDX}】中发送。<br/>
							    </c:if>
							</c:forEach>
							</td>
							<td align="center">
							   <a onclick="updateRule('${dt.id}')">编辑</a>
							   &nbsp;&nbsp;&nbsp;
							   <a onclick="deleteRule('${dt.id}')">删除</a>
							</td>
						</tr>
					</c:forEach>

				</c:if>
				<c:if test="${empty pgdata.data }">
					<tr>
						<td colspan="4" style="text-align: center;">暂无数据.</td>
					</tr>
				</c:if>
			</tbody>
		</table>
		<p class="msg">
			<jsp:include page="../comm/pgbar.jsp" />
		</p>
	</div>

</div>
