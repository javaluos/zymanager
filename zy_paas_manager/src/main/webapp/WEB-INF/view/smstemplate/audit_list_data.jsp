<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>

<style type="text/css">
 .precs{
 line-height: auto;border: 0px;padding:0px;background: 0 0;font-family: "open sans","Helvetica Neue",Helvetica,Arial,sans-serif;
 font-size: 12px;white-space: pre-wrap;
word-wrap: break-word;
 }
</style>
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
						rowspan="1" colspan="1">客户名称</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">客户账号</th>
				    <th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">模板ID</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">模板内容</th>
				    <th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">提交时间</th>
				    <th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">类型</th>
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
							<td>
								${dt.businessName }
							</td>
							<td>${dt.merchantPhone}</td>
							<td>${dt.id}</td>
							<td style="max-width:650px;">
                                <pre class="precs">${dt.content}</pre>
							</td>
							<td>
								<fmt:formatDate value="${dt.authSubmitTime}" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td>
							   <c:if test="${dt.category=='8'}">通知</c:if>
					    	   <c:if test="${dt.category=='9'}">验证码</c:if> 
					    	   <c:if test="${dt.category=='11'}">营销</c:if>
							</td>
							<td>
							   <c:if test="${dt.status=='1'}">审核通过</c:if>
					    	   <c:if test="${dt.status=='2'}">待审核</c:if> 
					    	   <c:if test="${dt.status=='3'}">待审核</c:if>
					    	   <c:if test="${dt.status=='4'}">审核未通过</c:if> 
							</td>
							<td><fmt:formatDate value="${dt.authResultTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td>${dt.authUser}</td>
							<td>
								<a href="/smstemplate/to_audit/${dt.id }" >审核&nbsp;</a>
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
