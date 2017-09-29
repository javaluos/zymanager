<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<script type="text/javascript" src="/js/handler/main.js"></script>
<script type="text/javascript" language="javascript"> 
function updataLog(id){
	$.ajax({
		type: "POST",
   		url: "/cdr_monitor_notice_log/do_update",
   		data: {"id" : id},
   		dataType: "json",
	   	success: function(data){
	   		if(data>0){
	   			clickOnTime();
	   			window.location.href="/cdr_monitor_notice_log/to_list";
	   		}
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
					<th style="background: 0 0;" width="5%">序号</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" width="8%">告警主体</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" width="6%">告警类型</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" width="50%">告警内容 </th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" width="8%">告警时间</th>	
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" width="5%">处理人</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1"width="3%">操作</th>
				</tr>
			</thead>
			<tbody>

				<c:if test="${not empty pgdata.data }">
					<c:forEach var="dt" items="${pgdata.data}" varStatus="dtindex">
						<tr class="gradeC odd">
							<td align="center">${(pgdata.page_num-1)*pgdata.page_size + dtindex.index+1}</td>
							<td align="left"><span align="center">${dt.monitorBody}</span></td>
							<td align="center">
							    <c:choose>
							        <c:when test="${dt.monitorType=='1'}">平台语音业务</c:when>
							        <c:when test="${dt.monitorType=='2'}">平台短信业务</c:when>
							        <c:when test="${dt.monitorType=='3'}">客户语音业务 </c:when>
							        <c:when test="${dt.monitorType=='4'}">客户短信业务  </c:when>
							        <c:when test="${dt.monitorType=='5'}">通道跑量业务 </c:when>
							        <c:when test="${dt.monitorType=='6'}">通道余额业务  </c:when>
							        <c:when test="${dt.monitorType=='7'}">客户余额业务 </c:when>
							    </c:choose>
							</td>
							
							<c:choose>
							    <c:when test="${dt.isUpMonitor=='1'}"><td align="left">${dt.monitorContent}</td></c:when>
							    <c:when test="${dt.isUpMonitor=='2'}"><td align="left" style="color: red;">${dt.monitorContent}</td></c:when>
							</c:choose>
							
							<td align="center">
								<fmt:formatDate value="${dt.statisticalTime}" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
							
							<td align="center">${dt.dealUser}</td>
							<td align="center">
							  <c:if test="${dt.isDeal=='1'}">
							       <a onclick="updataLog(${dt.id})">处理</a>
							  </c:if>
							  <c:if test="${dt.isDeal=='2'}">已处理</c:if>
							</td>
						</tr>
					</c:forEach>

				</c:if>
				<c:if test="${empty pgdata.data }">
					<tr>
						<td colspan="7" style="text-align: center;">暂无数据.</td>
					</tr>
				</c:if>
			</tbody>
		</table>
		<p class="msg">
			<jsp:include page="../comm/pgbar.jsp" />
		</p>
	</div>

</div>
