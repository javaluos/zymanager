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
					<th style="background: 0 0;">序号</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">黑名单组名称</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">客户账号</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">创建时间</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">修改时间</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">操作</th>
				</tr>
			</thead>
			<tbody>

				<c:if test="${not empty pgdata.data }">
					<c:forEach var="dt" items="${pgdata.data}" varStatus="dtindex">
						<tr class="gradeC odd">

							<td>${(pgdata.page_num-1)*pgdata.page_size + dtindex.index+1}</td>
							<td>${dt.groupName}</td>
							<td>${dt.merchantPhone}</td>
							<td><fmt:formatDate value="${dt.createTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td><fmt:formatDate value="${dt.updateTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td>
                                <a onclick="edit(${dt.id})" >编辑&nbsp;&nbsp;&nbsp;&nbsp;</a>
                                <a onclick="delbind(${dt.id})" >删除&nbsp;&nbsp;&nbsp;&nbsp;</a>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty pgdata.data }">
					<tr>
						<td colspan="6" style="text-align: center;">暂无数据.</td>
					</tr>
				</c:if>

			</tbody>

		</table>
		<p class="msg">
			<jsp:include page="../comm/pgbar.jsp" />
		</p>
	</div>

</div>
