<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- QueryData -->
<div id="QueryData3">
	<div id="DataTables_Table_0_wrapper"
		class="dataTables_wrapper form-inline" role="grid">
		<table
			class="table table-striped table-bordered table-hover dataTables-example dataTable"
			id="DataTables_Table_0" aria-describedby="DataTables_Table_0_info">
			<thead>
				<tr role="row">
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">客户名称</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">客户账号</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">客户类型</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty pgdata.data }">
					<c:forEach var="dt" items="${pgdata.data}" varStatus="dtindex">
						<tr class="gradeC odd" id="${dt.apiAccount }">
							<td>
								${dt.businessName }
							</td>
							<td>${dt.merchantPhone}</td>
							<td>
								<c:choose>
									<c:when test="${dt.merchantType == 1 }">
										个人开发者
									</c:when>
									<c:when test="${dt.merchantType == 2 }">
										个人开发者
									</c:when>
									<c:otherwise>
									</c:otherwise>
								</c:choose>
							</td>
							<td>
							    <a href="javascript:;" onclick="del('${dt.apiAccount}');" >删除</a>
							</td>		
						</tr>
					</c:forEach>

				</c:if>
				<c:if test="${empty pgdata.data }">
					<tr>
						<td colspan="3" style="text-align: center;">暂无数据.</td>
					</tr>
				</c:if>

			</tbody>

		</table>
		<%-- <p class="msg">
			<jsp:include page="../comm/pgbar.jsp" />
		</p> --%>
	</div>

</div>
<script type="text/javascript">
</script>
