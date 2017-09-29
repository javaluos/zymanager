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
					<th style="background: 0 0;" width="15px;">序号</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" width="105px;">姓名/企业名称</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" width="80px;">客户账号</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" width="80px;">主叫</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" width="90px;">主叫显号</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" width="80px;">主叫归属地</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" width="80px;">被叫</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" width="90px;">被叫显号</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" width="80px;">被叫归属地</th>
					<!-- <th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" width="110px;">调用时间</th>	
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" width="60px;">调用状态</th> -->
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" width="150px;">发起呼叫时间</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" width="70px;">主叫通话状态</th>
				    <th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" width="115px;">主叫通话结束原因</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" width="70px;">被叫通话状态</th>
				    <th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" width="115px;">被叫通话结束原因</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="2" width="90px;">接通时延(秒) A|B</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="2" width="90px;">通话时长(秒) A|B</th>
				</tr>
			</thead>
			<tbody>

				<c:if test="${not empty pgdata.data }">
					<c:forEach var="dt" items="${pgdata.data}" varStatus="dtindex">
						      <tr class="gradeC odd">
								<td>${(pgdata.page_num-1)*pgdata.page_size + dtindex.index+1}<input type="hidden" name="callId" value="${dt.callid}"/></td>
								<td>${dt.businessName}</td>       <!--姓名/企业名称-->
								<td>${dt.merchantPhone}</td>      <!--客户账号-->
								<td>${dt.caller}</td>             <!--主叫-->
								<td>${dt.callerDisplayNumber}</td><!--主叫显号-->
								<td></td>                         <!--主叫归属地-->
								<td>${dt.callee}</td>             <!--被叫-->
								<td>${dt.calleeDisplayNumber}</td><!--被叫显号-->
								<td></td>					 	  <!--被叫归属地-->
								<!-- <td></td>						  调用时间
								<td></td>						  调用状态 -->
								<td>${dt.calleeInviteTime2}</td>  <!--发起呼叫时间-->
								
								<c:choose>
					                <c:when test="${dt.leg=='B'}">       <!-- B路的信息 -->
					                    <td>                              <!--主叫通话状态-->
										   正常通话
										</td>
										<td>                             <!--主叫通话结束原因-->
										   
										</td>
										<td>  <!--被叫通话状态-->
										   <c:if test="${dt.state=='0'}">正常通话</c:if>
								    	   <c:if test="${dt.state=='1'}">被叫未接听</c:if>
								    	   <c:if test="${dt.state=='2'}">被叫拒接</c:if>
								    	   <c:if test="${dt.state=='3'}">外呼失败</c:if>
										</td>
										<td>  <!--被叫通话结束原因-->
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
										
										<td> <!--A接通时延(秒)-->
										   <c:if test="${dt.callerRingingBeginTime-dt.callerInviteTime<0}">
										      -1
										  </c:if>
										  <c:if test="${dt.callerRingingBeginTime-dt.callerInviteTime>=0}">
										      ${dt.callerRingingBeginTime-dt.callerInviteTime}
										  </c:if> 
										  
										</td>
										<td>  <!--B接通时延(秒)-->
										   <c:if test="${dt.calleeRingingBeginTime-dt.calleeInviteTime<0}">
										     -1
										  </c:if>
										  <c:if test="${dt.calleeRingingBeginTime-dt.calleeInviteTime>=0}">
										     ${dt.calleeRingingBeginTime-dt.calleeInviteTime}
										  </c:if> 
										</td>
										<td>
										    <c:choose>
											     <c:when test="${dt.callerAnswerTime==0}">0</c:when>
											     <c:when test="${dt.calleeHangupTime-dt.callerAnswerTime<0}">-1</c:when>
											     <c:otherwise>${dt.calleeHangupTime-dt.callerAnswerTime}</c:otherwise>
										    </c:choose>
										</td>   <!--A通话时长(秒)-->
										<td>    <!--B话时长(秒)-->
										  ${dt.holdTime}
										</td>
									</c:when>
									
									<c:otherwise>               <!-- A路的信息 -->
									    <td>                    <!--主叫通话状态-->
										   <c:if test="${dt.state=='0'}">正常通话</c:if>
								    	   <c:if test="${dt.state=='1'}">被叫未接听</c:if>
								    	   <c:if test="${dt.state=='2'}">被叫拒接</c:if>
								    	   <c:if test="${dt.state=='3'}">外呼失败</c:if>
										</td>
										<td>                             <!--主叫通话结束原因-->
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
										<td>  <!--被叫通话状态-->
										   <c:if test="${dt.state=='0'}">正常通话</c:if>
								    	   <c:if test="${dt.state=='1'}">被叫未接听</c:if>
								    	   <c:if test="${dt.state=='2'}">被叫拒接</c:if>
								    	   <c:if test="${dt.state=='3'}">外呼失败</c:if>
										</td>
										<td>  <!--被叫通话结束原因-->
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
									
									   <td>      				<!--A接通时延(秒)-->
										   <c:if test="${dt.calleeRingingBeginTime-dt.calleeInviteTime<0}">
										     -1
										   </c:if>
										   <c:if test="${dt.calleeRingingBeginTime-dt.calleeInviteTime>=0}">
										     ${dt.calleeRingingBeginTime-dt.calleeInviteTime}
										   </c:if> 
										</td>
										<td>-1</td>               <!--B接通时延(秒)-->
										<td>${dt.holdTime}</td>   <!--A通话时长(秒)-->
										<td>0</td>                <!--B通话时长(秒)-->
									</c:otherwise>
								</c:choose>
							</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty pgdata.data }">
					<tr>
						<td colspan="18" style="text-align: center;">暂无数据.</td>
					</tr>
				</c:if>

			</tbody>

		</table>
		<p class="msg">
			<jsp:include page="../comm/pgbar.jsp" />
		</p>
	</div>

</div>
