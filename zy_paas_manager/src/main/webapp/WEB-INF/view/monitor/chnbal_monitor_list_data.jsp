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
						rowspan="1" colspan="1">通道编号</th>
						
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">通道名称</th>
						
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">通道ID</th>
						
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">通道号</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">通道类型</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">运营商类型</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">通道属性</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">落地省份</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">跑量省份</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">状态</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">付费方式</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">余额</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">余额告警下限</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">备注</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty pgdata.data }">
					<c:forEach var="dt" items="${pgdata.data}" varStatus="dtindex">
						<tr class="gradeC odd">
							<td>${(pgdata.page_num-1)*pgdata.page_size + dtindex.index+1}</td>
							<td>${dt.channelMainCode}</td>
							<td>${dt.channelName}</td>
							<td>${dt.channelId}</td>
							<td>${dt.channelCode}</td>
							<td>
                                <c:choose>
                                   <c:when test="${dt.channelType==1}">
                                        通知
                                   </c:when>
                                   <c:when test="${dt.channelType==2}">
                                        验证码
                                   </c:when>
                                   <c:when test="${dt.channelType==3}">
                                        营销
                                   </c:when>
                                   <c:when test="${dt.channelType==4}">
                                        通知、验证码
                                   </c:when>
                                   <c:when test="${dt.channelType==5}">
                                        通知、验证码、营销
                                   </c:when>
                                   <c:otherwise>
                                        &nbsp;
                                   </c:otherwise>
                                </c:choose>
							</td>
							<td>
							     <c:choose>
                                   <c:when test="${dt.operateType==0}">
                                        三网合一
                                   </c:when>
                                   <c:when test="${dt.operateType==1}">
                                        移动专网
                                   </c:when>
                                   <c:when test="${dt.operateType==2}">
                                        电信专网
                                   </c:when>
                                   <c:when test="${dt.operateType==3}">
                                        联通专网
                                   </c:when>
                                   <c:otherwise>
                                        &nbsp;
                                   </c:otherwise>
                                </c:choose>
							</td>
							<td>
							     <c:choose>
                                   <c:when test="${dt.channelProperty==0}">
                                        全网
                                   </c:when>
                                   <c:when test="${dt.channelProperty==10}">
                                        移动
                                   </c:when>
                                   <c:when test="${dt.channelProperty==11}">
                                        移动三网
                                   </c:when>
                                   <c:when test="${dt.channelProperty==20}">
                                        电信
                                   </c:when>
                                   <c:when test="${dt.channelProperty==21}">
                                        电信三网
                                   </c:when>
                                   <c:when test="${dt.channelProperty==30}">
                                        联通
                                   </c:when>
                                   <c:when test="${dt.channelProperty==31}">
                                        联通三网
                                   </c:when>
                                   <c:when test="${dt.channelProperty==40}">
                                        国际
                                   </c:when>
                                   <c:otherwise>
                                        &nbsp;
                                   </c:otherwise>
                                </c:choose>
							</td>
							<td>${dt.dtnProvince}</td>
							<td>
							    <c:if test="${dt.useProvince == 0}">
                                    全国
                                </c:if>
                                <c:if test="${dt.useProvince == 1}">
                                    本省
                                </c:if>
                                <c:if test="${dt.useProvince == 2}">
                                    非本省
                                </c:if>
							</td>
							<td>
							    <c:choose>
                                    <c:when test="${dt.status==0}">
                                                   新创建
                                    </c:when>
                                    <c:when test="${dt.status==2}">
                                                  对接中
                                    </c:when>
                                    <c:when test="${dt.status==1}">
                                                  运营中
                                    </c:when>
                                    <c:when test="${dt.status==3}">
                                                  作废
                                    </c:when>
                                    <c:otherwise>
                                     &nbsp;
                                  </c:otherwise>
                                </c:choose>
							</td>
							<td>
							    <c:if test="${dt.chargeType == 0}">
                                    预付费
                                </c:if>
                                <c:if test="${dt.chargeType == 1}">
                                    后付费
                                </c:if>
							</td>
							<td>
							   <c:if test="${dt.balanceUnit == 0}">${dt.channelBalance / 1000} 元</c:if>
							   <c:if test="${dt.balanceUnit == 1}">${dt.channelBalance} 条</c:if>
							</td>
							<td>
							    <c:if test="${dt.balanceUnit == 0}">${dt.monitorBalance / 1000} 元</c:if>
                                <c:if test="${dt.balanceUnit == 1}">${dt.monitorBalance} 条</c:if>
							</td>
							<td>${dt.channelComment}</td>
							<td><a onclick="deleteMonitor('${dt.channelId}','${dt.channelName}')">取消监控</a></td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty pgdata.data }">
					<tr>
						<td colspan="16" style="text-align: center;">暂无数据.</td>
					</tr>
				</c:if>

			</tbody>

		</table>
		<p class="msg">
			<jsp:include page="../comm/pgbar.jsp" />
		</p>
	</div>

</div>
