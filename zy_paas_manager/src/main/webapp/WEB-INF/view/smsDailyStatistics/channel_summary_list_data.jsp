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
					<th style="background: 0 0;min-width:40px">序号</th>
					<th tabindex="0" aria-controls="DataTables_Table_0" style="width: 70px;"
						rowspan="1" colspan="1">通道<br/>名称</th>
					<th tabindex="0" aria-controls="DataTables_Table_0" style="width: 70px;"
						rowspan="1" colspan="1">通道<br/>编号</th>
				    <th tabindex="0" aria-controls="DataTables_Table_0" style="width: 70px;"
						rowspan="1" colspan="1">通道<br/>ID</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" style="min-width:70px">日期</th>
					<th tabindex="0" aria-controls="DataTables_Table_0" style="width: 70px;"
						rowspan="1" colspan="1">通道<br/>类型</th>
					<th tabindex="0" aria-controls="DataTables_Table_0" style="width: 50px;"
						rowspan="1" colspan="1">计费<br/>条数</th>
					<th tabindex="0" aria-controls="DataTables_Table_0" style="width: 50px;"
						rowspan="1" colspan="1">发送<br/>条数</th>
					<th tabindex="0" aria-controls="DataTables_Table_0" style="width: 50px;"
						rowspan="1" colspan="1">成功<br/>条数</th>
				    <th tabindex="0" aria-controls="DataTables_Table_0" style="width: 70px;"
						rowspan="1" colspan="1" style="min-width:40px">发送<br/>成功率</th>
                    <th tabindex="0" aria-controls="DataTables_Table_0" style="width: 50px;"
						rowspan="1" colspan="1">失败<br/>条数</th>
					<th tabindex="0" aria-controls="DataTables_Table_0" style="width: 70px;"
						rowspan="1" colspan="1" style="width: 80px;">发送<br/>失败率</th>
					<th tabindex="0" aria-controls="DataTables_Table_0" style="width: 50px;"
						rowspan="1" colspan="1">未知<br/>条数</th>
					<th tabindex="0" aria-controls="DataTables_Table_0" style="width: 70px;"
						rowspan="1" colspan="1" style="min-width:40px">未知状态<br/>比例</th>
					<th tabindex="0" aria-controls="DataTables_Table_0" style="width: 80px;"
						rowspan="1" colspan="1">平均发送<br/>时长</th>
					<th tabindex="0" aria-controls="DataTables_Table_1" style="width: 80px;"
						rowspan="1" colspan="1">平均状态<br/>报告时长</th>
					<th tabindex="0" aria-controls="DataTables_Table_0" style="width: 70px;"
						rowspan="1" colspan="1">10秒内<br/>到达率</th>
					<th tabindex="0" aria-controls="DataTables_Table_0" style="width: 70px;"
						rowspan="1" colspan="1">50秒内<br/>到达率</th>
					<th tabindex="0" aria-controls="DataTables_Table_0" style="width: 70px;"
						rowspan="1" colspan="1">超过50秒<br/>到达率</th>
					<th tabindex="0" aria-controls="DataTables_Table_0" style="width: 90px;"
						rowspan="1" colspan="1">回执24小时内<br/>到达率</th>
					<th tabindex="0" aria-controls="DataTables_Table_0" style="width: 90px;"
						rowspan="1" colspan="1">回执48小时内<br/>到达率</th>
					<th tabindex="0" aria-controls="DataTables_Table_0" style="width: 90px;"
						rowspan="1" colspan="1">回执72小时内<br/>到达率</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" style="max-width: 100px;">操作</th>
				</tr>
			</thead>
			<tbody>

				<c:if test="${not empty pgdata.data }">
					<c:forEach var="dt" items="${pgdata.data}" varStatus="dtindex">
						<tr class="gradeC odd">
							<td>${(pgdata.page_num-1)*pgdata.page_size + dtindex.index+1}</td>
							<td>${dt.channelName}</td>
							<td>${dt.channelMainCode}</td>
							<td>${dt.channelSmsId}</td>
							<td>${dt.dateTimeFormat}</td>
							<td>
							  <c:choose>
							     <c:when test="${dt.channelType==1}">通知</c:when>
							     <c:when test="${dt.channelType==2}">验证码</c:when>
							     <c:when test="${dt.channelType==3}">营销</c:when>
							     <c:when test="${dt.channelType==4}">通知、验证码</c:when>
							     <c:when test="${dt.channelType==5}">通知、验证码、营销</c:when>
							     <c:otherwise></c:otherwise>
							  </c:choose>
							</td>
							<td>${dt.feeCount}</td>
							<td>${dt.sendCount}</td>
							<td>${dt.successCount}</td>
							<td><fmt:formatNumber value="${dt.successSendRate*100}" pattern="0.00" maxFractionDigits="2"/>%</td>
							<td>${dt.failedCount}</td>
							<td><fmt:formatNumber value="${dt.failSendRate*100}" pattern="0.00" maxFractionDigits="2"/>%</td>
							<td>${dt.noreportCount}</td>
							<td><fmt:formatNumber value="${dt.noreportRate*100}" pattern="0.00" maxFractionDigits="2"/>%</td>
							<td>${dt.avgSendTime}</td>
							<td>${dt.avgStatusTime}</td>
							<td><fmt:formatNumber value="${dt.sdUs10sCountRate*100}" pattern="0.00" maxFractionDigits="2"/>%</td>
							<td><fmt:formatNumber value="${dt.sdUs50sCountRate*100}" pattern="0.00" maxFractionDigits="2"/>%</td>
							<td><fmt:formatNumber value="${dt.sdUsgt50sCountRate*100}" pattern="0.00" maxFractionDigits="2"/>%</td>
							<td><fmt:formatNumber value="${dt.stBk24hCountRate*100}" pattern="0.00" maxFractionDigits="2"/>%</td>
							<td><fmt:formatNumber value="${dt.stBk48hCountRate*100}" pattern="0.00" maxFractionDigits="2"/>%</td>
							<td><fmt:formatNumber value="${dt.stBk72hCountRate*100}" pattern="0.00" maxFractionDigits="2"/>%</td>
							<td>
								<a href="javascript:detail('${dt.channelSmsId}','${dt.channelName}','${dt.dateTime}')">查看</a>
							</td>
						</tr>
					</c:forEach>
 					<tr class="gradeC odd" style="font-weight:bold">
							<td style="font-weight: bold;text-align: center">汇总</td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td>${pgdata.feeCounts}</td>
							<td>${pgdata.sendCounts}</td>
							<td>${pgdata.successCounts}</td>
							<td><fmt:formatNumber value="${pgdata.successSendRates*100}" pattern="0.00" maxFractionDigits="2"/>%</td>
							<td>${pgdata.failedCounts}</td>
							<td><fmt:formatNumber value="${pgdata.failSendRates*100}"  pattern="0.00" maxFractionDigits="2"/>%</td>
							<td>${pgdata.noreportCounts}</td>
							<td><fmt:formatNumber value="${pgdata.noreportRates*100}" pattern="0.00" maxFractionDigits="2"/>%</td>
							<td>${pgdata.avgSendTimes}</td>
							<td>${pgdata.avgStatusTimes}</td>
							<td><fmt:formatNumber value="${pgdata.sdUs10sCountRates*100}" pattern="0.00" maxFractionDigits="2"/>%</td>
							<td><fmt:formatNumber value="${pgdata.sdUs50sCountRates*100}" pattern="0.00" maxFractionDigits="2"/>%</td>
							<td><fmt:formatNumber value="${pgdata.sdUsgt50sCountRates*100}" pattern="0.00" maxFractionDigits="2"/>%</td>
							<td><fmt:formatNumber value="${pgdata.stBk24hCountRates*100}"   pattern="0.00" maxFractionDigits="2"/>%</td>
							<td><fmt:formatNumber value="${pgdata.stBk48hCountRates*100}"   pattern="0.00" maxFractionDigits="2"/>%</td>
							<td><fmt:formatNumber value="${pgdata.stBk72hCountRates*100}"   pattern="0.00" maxFractionDigits="2"/>%</td>
							<td></td>
						</tr>
				</c:if>
				<c:if test="${empty pgdata.data }">
					<tr>
						<td colspan="23" style="text-align: center;">暂无数据.</td>
					</tr>
				</c:if>

			</tbody>

		</table>
		<p class="msg">
			<jsp:include page="../comm/pgbar.jsp" />
		</p>
	</div>

</div>
