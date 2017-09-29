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
					<th style="background: 0 0;min-width:40px">序号</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">通道名称</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">通道ID</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
                        rowspan="1" colspan="1" style="min-width:70px">日期</th>
                    <th tabindex="0" aria-controls="DataTables_Table_0"
                        rowspan="1" colspan="1">计费条数</th>
                    <th tabindex="0" aria-controls="DataTables_Table_0"
                        rowspan="1" colspan="1">发送条数</th>
                    <th tabindex="0" aria-controls="DataTables_Table_0"
                        rowspan="1" colspan="1">成功条数</th>
                    <th tabindex="0" aria-controls="DataTables_Table_0"
                        rowspan="1" colspan="1">失败条数</th>
                    <th tabindex="0" aria-controls="DataTables_Table_0"
                        rowspan="1" colspan="1">未知条数</th>
                    <th tabindex="0" aria-controls="DataTables_Table_0"
                        rowspan="1" colspan="1" style="min-width:40px">发送<br/>成功率</th>
                    <th tabindex="0" aria-controls="DataTables_Table_0"
                        rowspan="1" colspan="1" style="min-width:40px">发送<br/>失败率</th>
                    <th tabindex="0" aria-controls="DataTables_Table_0"
                        rowspan="1" colspan="1" style="min-width:40px">未知状态<br/>比例</th>
                    <th tabindex="0" aria-controls="DataTables_Table_0"
                        rowspan="1" colspan="1">平均发送<br/>时长</th>
                    <th tabindex="0" aria-controls="DataTables_Table_0"
                        rowspan="1" colspan="1">平均状态<br/>报告时长</th>
                    <th tabindex="0" aria-controls="DataTables_Table_0"
                        rowspan="1" colspan="1">10秒内<br/>到达率</th>
                    <th tabindex="0" aria-controls="DataTables_Table_0"
                        rowspan="1" colspan="1">50秒内<br/>到达率</th>
                    <th tabindex="0" aria-controls="DataTables_Table_0"
                        rowspan="1" colspan="1">超过50秒<br/>到达率</th>
                    <th tabindex="0" aria-controls="DataTables_Table_0"
                        rowspan="1" colspan="1">回执24小时内<br/>到达率</th>
                    <th tabindex="0" aria-controls="DataTables_Table_0"
                        rowspan="1" colspan="1">回执48小时内<br/>到达率</th>
                    <th tabindex="0" aria-controls="DataTables_Table_0"
                        rowspan="1" colspan="1">回执72小时内<br/>到达率</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty pgdata.data }">
					<c:forEach var="dt" items="${pgdata.data}" varStatus="dtindex">
						<tr class="gradeC odd">
							<td>${(pgdata.page_num-1)*pgdata.page_size + dtindex.index+1}</td>
							<td>${dt.channelName }</td>
							<td>${dt.channelSmsId }</td>
							<td>
							    <c:choose>
                                    <c:when test="${dateStr != null && dateStr != ''}">
                                        ${dateStr }
                                    </c:when>
                                    <c:otherwise>
                                        ${dt.dateTimeFormat }
                                    </c:otherwise>
                                </c:choose>
							</td>
							<td>${dt.feeCount }</td>
							<td>${dt.sendCount }</td>
							<td>${dt.successCount }</td>
							<td>
							    <c:choose>
                                    <c:when test="${dateStr != null && dateStr != ''}">
                                        <!--<a href="/sms_send_stat/fail_detail_view/${dt.apiAccount}/${dt.channelSmsId}/${dateFormat }/${smsCategory}">${dt.failedCount }</a>-->
                                        <a href="#" onclick="detailView('${dt.apiAccount}','${dt.channelSmsId}','${dateFormat }',${smsCategory},'${sTime}','${eTime}')">${dt.failedCount }</a>
                                    </c:when>
                                    <c:otherwise>
                                        <!--<a href="/sms_send_stat/fail_detail_view/${dt.apiAccount}/${dt.channelSmsId}/${dt.dateTime }/${smsCategory}">${dt.failedCount }</a>-->
                                        <a href="#" onclick="detailView('${dt.apiAccount}','${dt.channelSmsId}','${dt.dateTime }',${smsCategory},'${sTime}','${eTime}')">${dt.failedCount }</a>
                                    </c:otherwise>
                                </c:choose>
							</td>
							<td>${dt.noreportCount }</td>
							<td><fmt:formatNumber value="${dt.successSendRate * 100}" pattern="0.00" maxFractionDigits="2"/>%</td>
							<td><fmt:formatNumber value="${dt.failSendRate * 100}" pattern="0.00" maxFractionDigits="2"/>%</td>
							<td><fmt:formatNumber value="${dt.noreportRate * 100}" pattern="0.00" maxFractionDigits="2"/>%</td>
							<c:choose>
                                <c:when test="${dateStr != null && dateStr != ''}">
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </c:when>
                                <c:otherwise>
                                    <td>${dt.avgSendTime }</td>
                                    <td>${dt.avgStatusTime   }</td>
                                    <td><fmt:formatNumber value="${dt.sdUs10sCountRate * 100}" pattern="0.00" maxFractionDigits="2"/>%</td>
                                    <td><fmt:formatNumber value="${dt.sdUs50sCountRate * 100}" pattern="0.00" maxFractionDigits="2"/>%</td>
                                    <td><fmt:formatNumber value="${dt.sdUsgt50sCountRate * 100}" pattern="0.00" maxFractionDigits="2"/>%</td>
                                    <td><fmt:formatNumber value="${dt.stBk24hCountRate * 100}" pattern="0.00" maxFractionDigits="2"/>%</td>
                                    <td><fmt:formatNumber value="${dt.stBk48hCountRate * 100}" pattern="0.00" maxFractionDigits="2"/>%</td>
                                    <td><fmt:formatNumber value="${dt.stBk72hCountRate * 100}" pattern="0.00" maxFractionDigits="2"/>%</td>
                                </c:otherwise>
                            </c:choose>
						</tr>
					</c:forEach>

				</c:if>
				<c:if test="${empty pgdata.data }">
					<tr>
						<td colspan="22" style="text-align: center;">暂无数据.</td>
					</tr>
				</c:if>

			</tbody>

		</table>
		<p class="msg">
			<jsp:include page="../comm/pgbar.jsp" />
		</p>
	</div>

</div>
