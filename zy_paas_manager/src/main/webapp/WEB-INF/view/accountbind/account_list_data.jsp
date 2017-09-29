<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<jsp:include page="../comm/plugin.jsp" />


<table id="searchtable" class="noborder" >
	<tbody>
		<tr class="noline">
			<td class="fr" style="width: 10%; margin-left: 20px">客户名称</td>
			<td class="online" style="width: 10%"><input type="text"
				id="businessname" class="form-control" style="width: 250px;"
				maxlength="50" value="${businessname }"></td>
			<td >
				<button id="btnquery" class="btn btn-sm btn-primary pull-left" 
					type="button">查询数据</button>
			</td>
			<td></td>
		</tr>
	</tbody>
</table>
<!-- QueryData -->
<div id="QueryData">

	<div id="DataTables_Table_01_wrapper"
		class="dataTables_wrapper form-inline" role="grid">
		<table
			class="table table-striped table-bordered table-hover dataTables-example dataTable"
			id="DataTables_Table_01" aria-describedby="DataTables_Table_0_info">
			<thead>
				<tr role="row">
				    <th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">序号</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">客户名称</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">客户账号</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">操作</th>
				</tr>
			</thead>
			<tbody>

				<c:if test="${not empty pgdata.data }">
					<c:forEach var="dt" items="${pgdata.data}" varStatus="dtindex">
						<tr class="gradeC odd">
							<td>${(pgdata.page_num-1)*pgdata.page_size + dtindex.index+1}</td>
							<td>
								${dt.businessName }
							</td>
							<td>${dt.merchantPhone}</td>
							<td>
								<a onclick="doadd('${dt.apiAccount}','${dt.merchantPhone}','${dt.businessName }',${dt.merchantType });">添加&nbsp;</a>
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
<script type="text/javascript" src="/js/handler/modifyaccountbind.js"></script>
<script type="text/javascript">
</script>
