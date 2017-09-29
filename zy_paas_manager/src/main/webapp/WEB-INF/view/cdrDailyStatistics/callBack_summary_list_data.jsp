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
						rowspan="1" colspan="2">通话时长 A|B</th>	
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="2" >接通率 A|B</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="2">应答率  A|B</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="2">接通时延  A|B</th>
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
							<td>${dt.sum_a_call_times}</td><!-- 通话次数 -->
							<td>${dt.sum_fee_time}</td><!-- 计费时长 -->
							<td>${dt.sum_a_holding_time}</td><!-- A路通话时间长 -->
							<td>${dt.sum_b_holding_time}</td><!-- B路通话时间长 -->
							<td>${dt.pct_a_call_sucdouble}%</td><!-- A路接通率 -->
							<td>${dt.pct_b_call_sucdouble}%</td><!-- B路接通率 -->
							<td>${dt.pct_a_response_sucdouble}%</td><!-- A路应答率 -->
							<td>${dt.pct_b_response_sucdouble}%</td><!-- B路应答率 -->
							<td>${dt.avg_a_calleepdd_time}</td><!-- A路接通时延-->
							<td>${dt.avg_b_calleepdd_time}</td><!-- B路接通时延 -->
							<td>${dt.avg_b_acd}</td><!-- ACD -->
						</tr>
					</c:forEach>
 					<tr class="gradeC odd">
 					        <td style="font-weight: bold;text-align: center">汇总</td>
							<td></td>
							<td></td>
							<td></td>
							<td style="font-weight: bold;">${pgdata.sumCallTimes}</td><!-- 通话次数 -->
							<td style="font-weight: bold;">${pgdata.sumFeeTimes}</td><!-- 计费时长 -->
							<td style="font-weight: bold;">${pgdata.sumAHoldingTimes}</td><!-- A路通话时间长 -->
							<td style="font-weight: bold;">${pgdata.sumBHoldingTimes}</td><!-- B路通话时间长 -->
							<td style="font-weight: bold;">${pgdata.pctACallSucdoubles}%</td><!-- A路接通率 -->
							<td style="font-weight: bold;">${pgdata.pctBCallSucdoubles}%</td><!-- B路接通率 -->
							<td style="font-weight: bold;">${pgdata.pctAResponseSucdouble}%</td><!-- A路应答率 -->
							<td style="font-weight: bold;">${pgdata.pctBResponseSucdouble}%</td><!-- B路应答率 -->
							<td style="font-weight: bold;">${pgdata.avgACalleepddTime}</td><!-- A路接通时延-->
							<td style="font-weight: bold;">${pgdata.avgBCalleepddTime}</td><!-- B路接通时延 -->
							<td style="font-weight: bold;">${pgdata.avgAcds}</td><!-- ACD -->
						</tr>
				</c:if>
				<c:if test="${empty pgdata.data }">
					<tr>
						<td colspan="15" style="text-align: center;">暂无数据.</td>
					</tr>
				</c:if>

			</tbody>

		</table>
		<p class="msg">
			<jsp:include page="../comm/pgbar.jsp" />
		</p>
	</div>

</div>
