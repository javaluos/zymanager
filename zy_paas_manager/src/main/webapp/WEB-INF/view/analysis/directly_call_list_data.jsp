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
						rowspan="1" colspan="1">被叫</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">显号</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">被叫手机归属地</th>
					<!-- <th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">调用时间</th>	
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">接口调用状态</th> -->
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">发送时间</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">通话状态</th>
				    <th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">通话结束原因</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">接通时延(秒)</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">通话时长(秒)</th>
				</tr>
			</thead>
			<tbody>

				<c:if test="${not empty pgdata.data }">
					<c:forEach var="dt" items="${pgdata.data}" varStatus="dtindex">
						<tr class="gradeC odd">
							<td>${(pgdata.page_num-1)*pgdata.page_size + dtindex.index+1}<input type="hidden" name="callId" value="${dt.callid}"/></td>
							<td>${dt.businessName}</td>
							<td>${dt.merchantPhone}</td>
							<td>${dt.callee}</td>
							<td>${dt.calleeDisplayNumber}</td>
							<td></td>
							<!-- <td>
							</td>
							<td>
							</td> -->
							<td>${dt.calleeInviteTime2}</td>
							<td>
							   <c:if test="${dt.state=='0'}">正常通话</c:if>
					    	   <c:if test="${dt.state=='1'}">被叫未接听</c:if>
					    	   <c:if test="${dt.state=='2'}">被叫拒接</c:if>
					    	   <c:if test="${dt.state=='3'}">外呼失败</c:if>
							</td>
							<td>
							   <c:if test="${dt.hangupCode=='1'}">主叫挂断</c:if>
							   <c:if test="${dt.hangupCode=='2'}">被叫挂断</c:if>
							   <c:if test="${dt.hangupCode=='3'}">主叫取消</c:if>
							   <c:if test="${dt.hangupCode=='4'}">被叫无人接听</c:if>
							   <c:if test="${dt.hangupCode=='5'}">暂时无法接通</c:if>
							   <c:if test="${dt.hangupCode=='8'}">被叫拒接</c:if>
							   <c:if test="${dt.hangupCode=='9'}">空号、号码不存在</c:if>
							   <c:if test="${dt.hangupCode=='10'}">关机</c:if>
							   <c:if test="${dt.hangupCode=='11'}">停机</c:if>
							   <c:if test="${dt.hangupCode=='12'}">用户忙、正在通话中</c:if>
							   <c:if test="${dt.hangupCode=='255'}">未知原因</c:if>
							</td>
							<td>
							  <c:if test="${dt.calleeRingingBeginTime-dt.calleeInviteTime<0}">
							     -1
							  </c:if>
							  <c:if test="${dt.calleeRingingBeginTime-dt.calleeInviteTime>=0}">
							     ${dt.calleeRingingBeginTime-dt.calleeInviteTime}
							  </c:if>
							</td>
							<td>${dt.holdTime}</td>
						</tr>
					</c:forEach>

				</c:if>
				<c:if test="${empty pgdata.data }">
					<tr>
						<td colspan="14" style="text-align: center;">暂无数据.</td>
					</tr>
				</c:if>

			</tbody>

		</table>
		<p class="msg">
			<jsp:include page="../comm/pgbar.jsp" />
		</p>
	</div>

</div>
