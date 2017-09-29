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
						rowspan="1" colspan="1">手机号</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">提交时间</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">类型</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">文件名称</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">状态</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">操作</th>
				</tr>
			</thead>
			<tbody>

				<c:if test="${not empty pgdata.data }">
					<c:forEach var="dt" items="${pgdata.data}" varStatus="dtindex">
						<tr class="gradeC odd">
							<td>${(pgdata.page_num-1)*pgdata.page_size + dtindex.index+1}</td>
							<td><a href="javascript:audit('${dt.id}','${dt.apiAccount}')">${dt.businessName}</a></td>
							<td><a href="javascript:audit('${dt.id}','${dt.apiAccount}')">${dt.merchantPhone}</a></td>
							<td>
							   <fmt:formatDate value="${dt.authSubmitTime}" pattern="yyyy-MM-dd HH:mm:ss" /> 
							</td>
							<td><c:if test="${dt.voiceType=='1'}">验证码</c:if>
					    		<c:if test="${dt.voiceType=='2'}">通知</c:if>   
							</td>
							<td>${dt.fileName}</td>
							<td>
							     <c:if test="${dt.authStatus=='0'}">未审核</c:if> 
								 <c:if test="${dt.authStatus=='1'}">审核通过</c:if>
							     <c:if test="${dt.authStatus=='2'}">待审核</c:if>
							     <c:if test="${dt.authStatus=='3'}">待审核</c:if>
							     <c:if test="${dt.authStatus=='4'}">审核失败</c:if>
							     <c:if test="${dt.authStatus=='5'}">取消审核</c:if>
							</td>
							<td>
							   <a href="javascript:audit('${dt.id}','${dt.apiAccount}')">审核</a>
							</td>
						</tr>
					</c:forEach>

				</c:if>
				<c:if test="${empty pgdata.data }">
					<tr>
						<td colspan="8" style="text-align: center;">暂无数据.</td>
					</tr>
				</c:if>

			</tbody>

		</table>
		<p class="msg">
			<jsp:include page="../comm/pgbar.jsp" />
		</p>
	</div>

</div>
