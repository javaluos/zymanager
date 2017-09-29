<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>

<!-- QueryData -->
<div id="QueryData">

	<div id="DataTables_Table_0_wrapper"
		class="dataTables_wrapper form-inline" role="grid">
		<table
			class="table table-striped table-bordered table-hover dataTables-example dataTable"
			id="DataTables_Table_0" aria-describedby="DataTables_Table_0_info">
			<thead>
				<tr role="row">
					<th style="background: 0 0;" rowspan="1" >序号</th>
					
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">客户名称</th>
						
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">客户账号</th>
						
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">监控状态</th>
						
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">报警记录</th>
						
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">操作</th>
				</tr>
				
			</thead>
			<tbody>

				<c:if test="${not empty pgdata.data }">
					<c:forEach var="dt" items="${pgdata.data}" varStatus="dtindex">
						<tr class="gradeC odd">
							<td>${(pgdata.page_num-1)*pgdata.page_size + dtindex.index+1}</td>  <!-- 序号 -->
							<td><a href="/chart/account_chartline.html?apiAccount=${dt.apiAccount}">${dt.merchantAccount.businessName}</a></td> 						<!-- 客户名称 -->
							<td><a href="/chart/account_chartline.html?apiAccount=${dt.apiAccount}">${dt.merchantAccount.merchantPhone}</a></td>						<!-- 客户账号 -->
							<td> 
							   <input type="hidden" id="apiAccount" value="${dt.apiAccount}"/>                                                               <!-- 监控状态 -->
							   <c:choose>
								   <c:when test="${dt.monitorStatus==0}">
								     	 关闭&nbsp; &nbsp;&nbsp;
								   </c:when>
								   <c:otherwise>
								    		开启 &nbsp; &nbsp;&nbsp;   
								   </c:otherwise>
							   </c:choose>
							</td> 															
							<td>${dt.noticeTimes}</td>											   <!-- 报警记录 -->
							<td>
								<c:if test="${permission == true }">
									<a href="/monitor/index_setting?businessName=${dt.merchantAccount.businessName}&&merchantPhone=${dt.merchantAccount.merchantPhone}&&apiAccount=${dt.merchantAccount.apiAccount}" >指标设置</a>&nbsp;&nbsp;&nbsp;     
									<a onclick="notifysetting(${dt.id})">通知设置 </a>&nbsp; &nbsp;&nbsp; 
									<c:choose>
									   <c:when test="${dt.monitorStatus==0}">
									      <a onclick="start(${dt.id})">开启监控 </a>&nbsp; &nbsp;&nbsp;
									   </c:when>
									   <c:otherwise>
									     <a onclick="stop(${dt.id})" >暂停监控 </a>&nbsp; &nbsp;&nbsp;   
									   </c:otherwise>
									</c:choose>
									<a onclick="del(${dt.id},${dt.monitorStatus})" > 删除</a>&nbsp; &nbsp;&nbsp;  
								</c:if>
							 </td>
						</tr>
					</c:forEach>

				</c:if>
				<c:if test="${empty pgdata.data }">
					<tr>
						<td colspan="19" style="text-align: center;">暂无数据.</td>
					</tr>
				</c:if>

			</tbody>

		</table>
		<p class="msg">
			<jsp:include page="../comm/pgbar.jsp" />
		</p>
	</div>

</div>
