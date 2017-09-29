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
						rowspan="1" colspan="1">开发者类型</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">客户账号</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">邮箱</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">Api Account</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">是否认证</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">状态</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">注册时间</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">修改时间</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">操作</th>
				</tr>
			</thead>
			<tbody>

				<c:if test="${not empty pgdata.data }">
					<c:forEach var="dt" items="${pgdata.data}" varStatus="dtindex">
						<tr class="gradeC odd">
						    
							<td>${(pgdata.page_num-1)*pgdata.page_size + dtindex.index+1}</td>
							<td>${dt.businessName}</td>
							<td>
								<c:choose> 
								  <c:when test="${dt.merchantType==1}">   
								              个人开发者
								  </c:when> 
								  <c:when test="${dt.merchantType==2}">   
								               企业开发者
								  </c:when>
								  <c:otherwise>   
								     &nbsp;         
								  </c:otherwise> 
								</c:choose> 
							</td>
							<td>${dt.merchantPhone}</td>
							<td>${dt.merchantEmail}</td>
							<td>${dt.apiAccount }</td>	
							<td>
							  <c:choose>
								<c:when test="${dt.authFlag==1}">   
								              已认证
								  </c:when>
								  <c:otherwise>   
								               未认证         
								  </c:otherwise>
							  </c:choose>
							</td>
							<td>
							    <c:choose>
								    <c:when test="${dt.isLocked==0}">   
									               正常
									  </c:when>
									<c:when test="${dt.isLocked==1}">
										<span class="f_cA">锁定</span>
									</c:when>
									<c:otherwise>   
								     &nbsp;         
								  </c:otherwise>
								</c:choose>
							</td>
							<td><fmt:formatDate value="${dt.createTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td><fmt:formatDate value="${dt.updateTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td>
								<c:if test="${permission == true }">
								    <a onclick="toMerchantAttr('${dt.apiAccount}')" >属性查看&nbsp;|&nbsp;</a>
								</c:if>
								<c:if test="${dt.authFlag == 1}"><a href="/account/authentication_view/${dt.apiAccount }" >认证信息</a></c:if>
							</td>		
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty pgdata.data }">
					<tr>
						<td colspan="11" style="text-align: center;">暂无数据.</td>
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
function toMerchantAttr(apiAccount){

	var apiaccount=$('#apiaccount').val();
	var merchantphone=$('#phone').val();
	var merchantemail=$('#email').val();
	var regstarttime=$('#regstarttime').val();
	var regendtime=$('#regendtime').val();
	var authflag=$('#authflag').val();
	var businessname=$('#businessname').val();
	
	var obj=new Object();
	obj.businessName=businessname;
	obj.apiaccount=apiaccount;
	obj.merchantphone=merchantphone;
	obj.merchantemail=merchantemail;
	obj.regstarttime=regstarttime;
	obj.regendtime=regendtime;
	obj.authflag=authflag;
	var params=JSON.stringify(obj);
	
	window.location.href="/account/view_merchant_attr/"+apiAccount+"?params="+params;
}
</script>
