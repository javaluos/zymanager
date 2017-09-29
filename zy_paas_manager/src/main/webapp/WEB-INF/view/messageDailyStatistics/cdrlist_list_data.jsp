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
						rowspan="1" colspan="1">日期</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">通话次数</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">计费时长</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">计费条数</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">接通率</th>
				    <th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">应答率</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">ACD(秒)</th>
				</tr>
			</thead>
			<tbody>

				<c:if test="${not empty pgdata.data }">
					<c:forEach var="dt" items="${pgdata.data}" varStatus="dtindex">
						<tr class="gradeC odd">
							<td>${(pgdata.page_num-1)*pgdata.page_size + dtindex.index+1}</td>
							<td>${dt.business_name}</td>
							<td>${dt.merchant_phone}</td>
							<td>${dt.date_time}</td>
							<td>${dt.sum_call_times}</td>
							<td>${dt.sum_fee_time}</td>
							<td>${dt.sum_response_times}</td>
							<td>${dt.pct_call_sucdouble}%</td>
							<td>${dt.pct_response_sucdouble}%</td>
							<td>${dt.avg_acd}</td>
						</tr>
					</c:forEach>
 					<tr class="gradeC odd">
							<td style="font-weight: bold;text-align: center;">汇总</td>
							<td></td>
							<td></td>
							<td></td>
							<td style="font-weight: bold;">${pgdata.sumCallTimes}</td>
							<td style="font-weight: bold;">${pgdata.sumFeeTimes}</td>
							<td style="font-weight: bold;">${pgdata.sumResponseTimes}</td>
							<td style="font-weight: bold;">${pgdata.pctCallSucs}%</td>
							<td style="font-weight: bold;">${pgdata.pctResponseSucs}%</td>
							<td style="font-weight: bold;">${pgdata.avgAcds}</td>
						</tr>
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
