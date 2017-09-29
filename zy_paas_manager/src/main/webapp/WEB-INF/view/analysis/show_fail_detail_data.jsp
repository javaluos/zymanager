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
			class="table table-bordered dataTables-example dataTable"
			id="DataTables_Table_0" aria-describedby="DataTables_Table_0_info">
			<thead>
				<tr role="row">
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" style="min-width:70px">客户名称</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">客户账号</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" style="min-width:70px">失败总条数</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" style="min-width:40px">失败条数</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">比例</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">代码描述</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">中文描述</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty pgdata.data }">
				    <c:choose>
				        <c:when test="${size > 1}">
				            <tr>
                                <td rowspan="${pgdata.total + 1}" style="vertical-align:middle;text-align:center">${businessName}</td>
                                <td rowspan="${pgdata.total + 1}" style="vertical-align:middle;text-align:center">${merchantPhone}</td>
                                <td rowspan="${pgdata.total + 1}" style="vertical-align:middle;text-align:center">${total}</td>
                                <td>${groupCount}</td>
                                <td><fmt:formatNumber type="number" value="${percentage * 100}" pattern="0.00" maxFractionDigits="2"/>%</td>
                                <td>${receiveStatusDesc}</td>
                                <td>${statusDescCN}</td>
                            </tr>
                            <c:forEach var="dt" items="${pgdata.data}" varStatus="dtindex">
                                <tr class="gradeC odd">
                                    <td>${dt.groupCount}</td>
                                    <td><fmt:formatNumber type="number" value="${dt.percentage * 100}" pattern="0.00" maxFractionDigits="2"/>%</td>
                                    <td>${dt.receiveStatusDesc}</td>
                                    <td>${dt.statusDescCN}</td>
                                </tr>
                            </c:forEach>
				        </c:when>
				        <c:otherwise>
                            <c:forEach var="dt" items="${pgdata.data}" varStatus="dtindex">
                                <tr class="gradeC odd">
                                    <td>${businessName}</td>
                                    <td>${merchantPhone}</td>
                                    <td>${dt.total}</td>
                                    <td>${dt.groupCount}</td>
                                    <td><fmt:formatNumber type="number" value="${dt.percentage * 100}" pattern="0.00" maxFractionDigits="2"/>%</td>
                                    <td>${dt.receiveStatusDesc}</td>
                                    <td>${dt.statusDescCN}</td>
                                </tr>
                            </c:forEach>
				        </c:otherwise>
				    </c:choose>
				</c:if>
				<c:if test="${empty pgdata.data }">
					<tr>
						<td colspan="7" style="text-align: center;">暂无数据.</td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>

</div>
