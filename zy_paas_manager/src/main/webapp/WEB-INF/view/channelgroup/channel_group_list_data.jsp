<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- QueryData -->
<div id="QueryData">
	<div id="DataTables_Table_0_wrapper"
		class="dataTables_wrapper form-inline" role="grid">
		<table
			class="table table-striped table-bordered table-hover dataTables-example dataTable"
			id="DataTables_Table_0" aria-describedby="DataTables_Table_0_info">
			<thead>
				<tr role="row">
					<th style="width: 5%;">序号</th>
					<th style="width: 15%;">通道组名称</th>
					<th style="width: 50%;">通道配置</th>
					<th style="width: 20%;">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty pgdata.data }">
					<c:forEach var="data" items="${pgdata.data}" varStatus="dtindex">
						<tr class="gradeC odd">
							<td align="center" style="vertical-align: middle;">${(pgdata.page_num-1)*pgdata.page_size + dtindex.index+1}</td>
							<td>${data.groupName}</td>
							<td>
							  <table>
							  	<c:if test="${data.groupType == 0}">
							  		<tr>
			                 			<td width="200px;">通道：所有通道</td>	
			                 		</tr>
						  		</c:if>
							  <c:forEach var="item" items="${data.smsChannelGroupBindList}">
			                 		<tr>
			                 		    <td width="250px;">ID：${item.channelId}</td>	
			                 			<td width="250px;">通道：${smsChannelMap[item.channelId]}</td>	
				                 		<td width="50px;">评分：${item.channelScore}</td>
				                 	 	<td width="200px;">发送上线：${item.thresholdValue}</td>
			                 		</tr>
							  </c:forEach>
							  </table>
							</td>
							<td align="center">
								<c:if test="${data.groupType == 1}">
									<a style="margin-right:5%;" href="#" onclick="addChannelGroup('${data.id}')">编辑&nbsp;</a>|
							  		<a style="margin-left:5%;" href="#" onclick="deleteChannelGroup('${data.id}')">&nbsp;删除</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty pgdata.data }">
					<tr>
						<td colspan="17" style="text-align: center;">暂无数据.</td>
					</tr>
				</c:if>
			</tbody>
		</table>
		<p class="msg">
			<jsp:include page="../comm/pgbar.jsp" />
		</p>
	</div>
</div>

<style type="text/css"> 
 textarea{ resize:none;}
</style> 
