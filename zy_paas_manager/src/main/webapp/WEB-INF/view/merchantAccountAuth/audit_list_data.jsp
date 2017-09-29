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
						rowspan="1" colspan="1">开发者类型</th>
				    <!-- <th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">文件名称</th> -->
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">状态</th>
				    <th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">提交时间</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">认证时间</th>	
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">认证人</th>
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
							   <c:choose>
							     <c:when test="${dt.merchantType == 1}">
							         ${dt.plName}
							     </c:when>
							     <c:otherwise>
							         ${dt.cyName}
							     </c:otherwise>
							   </c:choose>
							   
							
							</td>
							<td>${dt.merchantAccount.merchantPhone}</td>
							<td><c:if test="${dt.merchantType=='1'}">个人开发者</c:if>
					    		<c:if test="${dt.merchantType=='2'}">企业开发者</c:if>   
							</td>
							<!-- <td>
							</td> -->
							<td>
							   <c:if test="${dt.authStatus=='1'}">已认证</c:if>
					    	   <c:if test="${dt.authStatus=='2'}">待认证</c:if> 
					    	   <c:if test="${dt.authStatus=='3'}">待认证</c:if>
					    	   <c:if test="${dt.authStatus=='4'}">认证失败</c:if> 
					    	   <c:if test="${dt.authStatus=='5'}">取消认证</c:if>
							</td>
							<td>
							   <fmt:formatDate value="${dt.authSubmitTime}" pattern="yyyy-MM-dd HH:mm:ss" /> 
							</td>
							<td><fmt:formatDate value="${dt.authResultTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td>${dt.authUser}</td>
							<td>
								<c:choose> 
									<c:when test="${dt.authStatus == 2 || dt.authStatus == 3}"><a href="/merchantAccountAuth/to_authentication?apiAccount=${dt.apiAccount }" >认证信息</a></c:when>
									<c:otherwise><a href="/merchantAccountAuth/authentication_view/${dt.apiAccount }" >查看</a></c:otherwise>
								</c:choose>
							</td>
						</tr>
					</c:forEach>

				</c:if>
				<c:if test="${empty pgdata.data }">
					<tr>
						<td colspan="10" style="text-align: center;">暂无数据.</td>
					</tr>
				</c:if>

			</tbody>

		</table>
		<p class="msg">
			<jsp:include page="../comm/pgbar.jsp" />
		</p>
	</div>

</div>
