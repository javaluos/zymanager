<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE HTML>
<html>

	<head>
		<title>Paas运营管理平台</title>
		<jsp:include page="../comm/plugin.jsp" />
	</head>

	<body>

		<div id="QueryData">
			<div id="DataTables_Table_0_wrapper" class="dataTables_wrapper form-inline" role="grid">
				<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="DataTables_Table_0" aria-describedby="DataTables_Table_0_info">
					<thead>
						<tr role="row">
							<th style="width: 5%;">序号</th>
							<th style="width: 15%;">客户名称</th>
							<th style="width: 15%;">客户账号</th>
							<th style="width: 30%;">通道组</th>
							<th style="width: 20%;">绑定时间</th>
							<th style="width: 15%;">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${not empty pgdata.data }">
							<c:forEach var="data" items="${pgdata.data}" varStatus="dtindex">
								<tr class="gradeC odd">
									<td align="center" style="vertical-align: middle;">${(pgdata.page_num-1)*pgdata.page_size + dtindex.index+1}</td>
									<td align="left" style="vertical-align: middle;">${data.businessName}</td>
									<td align="center" style="vertical-align: middle;">${data.merchantPhone}</td>
									<td>
										移动：${smsChannelMap[data.groupYd]}</br>
										联通：${smsChannelMap[data.groupLt]}</br>
										电信：${smsChannelMap[data.groupDx]}</br>
									</td>
									<td align="center" style="vertical-align: middle;">${fn:substring(data.updateTime, 0, 19)}</td>
									<td align="center" style="vertical-align: middle;">
										<a style="margin-right:5%;" href="#" onclick="saveChannelGroupBind(${data.id})">修改&nbsp;</a>|
							  		    <a style="margin-left:5%;" href="#" onclick="deleteChannelGroupBind('${data.apiAccount}',${data.id})">&nbsp;解除</a>
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
	</body>

</html>