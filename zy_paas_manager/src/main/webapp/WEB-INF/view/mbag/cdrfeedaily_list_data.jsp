<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored ="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

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
						rowspan="1" colspan="1">日期(天)</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">短信通知(元)</th>
				    <th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">短信验证码(元)</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">短信营销(元)</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">语音验证码(元)</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">语音通知(元)</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">回拨(元)</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">直拨(元)</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">号码卫士(元)</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">平台通话录音(元)</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">平台录音存储(元)</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">合计(元)</th>
				</tr>
			</thead>
			<tbody>

				<c:if test="${not empty pgdata.data }">
					<c:forEach var="dt" items="${pgdata.data}" varStatus="dtindex">
						<tr class="gradeC odd" _item="${dt.apiAccount }">
							<td>${(pgdata.page_num-1)*pgdata.page_size + dtindex.index+1}</td>
							<td>${dt.dateTime}</td>          <!-- 日期 -->
							<td>${dt.sumFeeBsd8}</td>          <!-- 短信通知 -->
							<td>${dt.sumFeeBsd9}</td>          <!-- 短信验证码 -->
							<td>${dt.sumFeeBsd11}</td>          <!-- 短信营销 -->
							<td>${dt.sumFeeBsd5}</td>		   <!-- 语音验证码 -->
							<td>${dt.sumFeeBsd4}</td>		   <!-- 语音通知 -->
							<td>${dt.sumFeeBsd1}</td>           <!-- 回拨 -->
							<td>${dt.sumFeeBsd3}</td>          <!-- 直拨 -->
							<td>${dt.sumFeeBsd2}</td>		   <!-- 号码卫士 -->
							<td>${dt.sumFeeBsd10}</td>		   <!-- 平台通话录音 -->
							<td>${dt.sumFeeBsd10}</td>         <!-- 平台录音存储-->
							<td>${dt.sumFeed}</td>             <!-- 合计-->
						</tr>
					</c:forEach>

				</c:if>
				<c:if test="${empty pgdata.data }">
					<tr>
						<td colspan="13" style="text-align: center;">暂无数据.</td>
					</tr>
				</c:if>

			</tbody>

		</table>
		<p class="msg">
			<jsp:include page="../comm/pgbar.jsp" />
		</p>
	</div>

</div>

