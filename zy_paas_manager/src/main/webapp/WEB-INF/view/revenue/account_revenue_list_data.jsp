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
						rowspan="1" colspan="1">客户名称</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">客户账号</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">日期</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">类型</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">计费条数</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">收入(元)</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">成本(元)</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">利润(元)</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">成本均价(元)</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">毛利率</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">通道分析</th>
				</tr>
			</thead>
			<tbody>

				<c:if test="${not empty pgdata.data }">
					<c:forEach var="dt" items="${pgdata.data}" varStatus="dtindex">
						<tr class="gradeC odd">
						    
							<td>${(pgdata.page_num-1)*pgdata.page_size + dtindex.index+1}</td>
							<td>${accountMaps[dt.apiAccount].businessName}</td>
							<td>${accountMaps[dt.apiAccount].merchantPhone}		</td>
							<td>
							    <fmt:parseDate  value="${dt.dateTime}" pattern="yyyyMMdd" var="date" />
							    <fmt:formatDate value="${date}" pattern="yyyy-MM-dd" />  
							</td>
							<td>
							   <c:choose>
									<c:when test="${dt.smsCategory==8}">
										通知
									 </c:when>
									 <c:when test="${dt.smsCategory==9}">   
									           验证码
									 </c:when>
									 <c:when test="${dt.smsCategory==11}">
										 营销
									 </c:when>
									<c:otherwise>        
								    </c:otherwise>
								</c:choose>
							</td>
							<td>${dt.feeCount }</td>	
							<td>
							   <fmt:formatNumber type="number" value="${dt.inCome }"  pattern="0.00" maxFractionDigits="2"/>
							</td>
							<td>
							  <fmt:formatNumber type="number" value="${dt.costFee }"  pattern="0.00" maxFractionDigits="2"/>
							</td>
							<td>
							  <fmt:formatNumber type="number" value="${dt.inCome-dt.costFee }"  pattern="0.00" maxFractionDigits="2"/>
							</td>
							<td>
							    <c:choose>
									<c:when test="${dt.feeCount==0}">
									</c:when>
									<c:otherwise> 
									   <fmt:formatNumber type="number" value="${dt.costFee/dt.feeCount}"  pattern="0.0000" maxFractionDigits="4"/>
								    </c:otherwise>
								</c:choose>
							</td>
							<td>
							    <c:choose>
									<c:when test="${dt.inCome==0}">
									 </c:when>
									<c:otherwise> 
									    <fmt:formatNumber type="number" value="${(dt.inCome-dt.costFee)/dt.inCome*100}"  pattern="0.00" maxFractionDigits="2"/>%
								    </c:otherwise>
								</c:choose>
							</td>	
							<td>
							    <a onclick="toMerchantAttr('${dt.apiAccount}','${dt.smsCategory}','${dt.dateTime}')">查看</a>
							</td>	
						</tr>
					</c:forEach>
					
					<tr class="gradeC odd" style="font-weight:bold">
							<td style="font-weight: bold;text-align: center">汇总</td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td>${pgdata.feeCount}</td>
							<td>
							  <fmt:formatNumber value="${pgdata.inCome}" pattern="0.00" maxFractionDigits="2"/>
							</td>
							<td>
							  <fmt:formatNumber value="${pgdata.costFee}" pattern="0.00" maxFractionDigits="2"/>
							<td>
							  <fmt:formatNumber value="${pgdata.profit}" pattern="0.00" maxFractionDigits="2"/>
							<td>
							  <fmt:formatNumber value="${pgdata.costAveragePrice}" pattern="0.0000" maxFractionDigits="4"/>	
							<td>
							  <fmt:formatNumber value="${pgdata.grossProfitRate*100}" pattern="0.00" maxFractionDigits="2"/>%
							</td>
							<td></td>
						</tr>
				</c:if>
				<c:if test="${empty pgdata.data }">
					<tr>
						<td colspan="12" style="text-align: center;">暂无数据.</td>
					</tr>
				</c:if>

			</tbody>

		</table>
		<p class="msg">
			<jsp:include page="../comm/pgbar.jsp" />
		</p>
	</div>

</div>

<script type="text/javascript">
function toMerchantAttr(apiAccount,smsCategory,dateTime){
	window.location.href="/account_revenue/revenue_attr_list?apiAccount="+apiAccount+"&smsCategory="+smsCategory+"&dateTime="+dateTime;
}
</script>