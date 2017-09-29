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
						rowspan="1" colspan="1">姓名/企业名称</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">客户账号</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">签名ID</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">签名内容</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">提交时间</th>
				    <th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">签名类型</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">状态</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">审核时间</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">审核人</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">操作</th>
				</tr>
			</thead>
			<tbody>

				<c:if test="${not empty pgdata.data }">
					<c:forEach var="dt" items="${pgdata.data}" varStatus="dtindex">
						<tr class="gradeC odd">
							<td>${(pgdata.page_num-1)*pgdata.page_size + dtindex.index+1}</td>
							<td>${dt.merchantAccount.businessName}</td>
							<td>${dt.merchantAccount.merchantPhone}</td>
							<td>${dt.id}</td>
							<td>${dt.content}</td>
							<td>
							    <fmt:formatDate value="${dt.authSubmitTime}" pattern="yyyy-MM-dd HH:mm:ss" /> 
							</td>
							<td>${dt.category}</td>
							<td>
							     <c:if test="${dt.status=='0'}">未审核</c:if> 
								 <c:if test="${dt.status=='1'}">审核通过</c:if>
							     <c:if test="${dt.status=='2'}">待审核</c:if>
							     <c:if test="${dt.status=='3'}">待审核</c:if>
							     <c:if test="${dt.status=='4'}">审核未通过</c:if>
							     <c:if test="${dt.status=='5'}">取消审核</c:if>
							</td>
							<td>
								<fmt:formatDate value="${dt.authResultTime}" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td>${dt.authUser}</td>
							<td>
							   <a href="javascript:audit('${dt.id}','${dt.apiAccount}')">审核</a> 
							</td>
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
