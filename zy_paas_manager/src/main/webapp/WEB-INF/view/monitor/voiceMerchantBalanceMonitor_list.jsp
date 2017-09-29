<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@  taglib  uri="http://java.sun.com/jsp/jstl/functions"   prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>列表查询</title>
	<jsp:include page="../comm/plugin.jsp" />
    <script type="text/javascript">
		function modify(apiAccount) {
			window.location.href="/monitor/preUpdate?apiAccount="+apiAccount;
		}
		function changePage(cp){
			if(cp){
				$("#cp").val(cp);
				$('#sc_btn_id').click();
			}
		}
		function checkPhone(value){
			if(value){
			    if(!(/^(1[34578]\d{0,9})$/.test(value))){
			    	  $("#merchantPhone").val("");
			    }
			}
		}
		
    </script>
</head>

<body class="right_body">
<form action="/monitor/list" method="POST" name="myform">
	<div class="search">
			<br>
			客户：<input  id="businessName" name="businessName" value="${voiceMerchantBalanceMonitor.businessName}">
<%-- 			邮箱：<input id="merchantEmail" name="merchantEmail" value="${voiceMerchantBalanceMonitor.merchantEmail}"> --%>
			<input type="hidden" name="cp" id="cp" value="${cp}">
			手机：<input   id="merchantPhone" name="merchantPhone" value="${voiceMerchantBalanceMonitor.merchantPhone}" onblur="checkPhone(this.value)">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input id="sc_btn_id" class="btn btn-sm btn-primary " type="submit" value="查询数据">
			<c:if test="${sid==0 }"><input class="btn btn-sm btn-primary " type="button" value="返回" onclick="history.go(-2)" style="margin-left: 10px;"> </c:if>
	</div>
	<br>
	<div class="data_list">
		<div class="list_inner">
			<table width="100%" border="0" cellspacing="1" cellpadding="0" class="table table-striped table-bordered table-hover dataTables-example dataTable" aria-describedby="DataTables_Table_0_info">
				<tr>
					<th>序号</th>
					<th>客户名称</th>
					<th>手机号</th>
					<th>余额(元)</th>
					<th>报警下限(元)</th>
					<th>通知时间段</th>
					<th>通知方式</th>
					<th>操作</th>
				</tr>
				<c:forEach var="st" items="${list}" varStatus="i">
				<tr>
					<td>${i.index+1}</td>
					<td>${st.businessName}</td>
					<td>${st.merchantPhone}</td>
					<td><fmt:formatNumber type="number" value="${st.balance/10000}" pattern="0.00" maxFractionDigits="2"/></td>
					<td>${st.monitorMinBalance/10000}</td>
					<td>${st.noticeTimeRange1}</td>
					<td>
					<c:if test="${st.noticeWay1!=null}">
					     ${fn:contains(st.noticeWay1,0)?'短信':''}
					     ${fn:contains(st.noticeWay1,1)?'邮箱':''}
					     ${fn:contains(st.noticeWay1,2)?'语音':''}
					</c:if>
					</td>
					<td align="center">
						<input class="btn btn-sm btn-primary" name="editBtn" type="button" value="设置" title="设置" onclick="modify('${st.apiAccount}')" />
					</td>
				</tr>
				</c:forEach>
			</table>
			<div class="page">
				<div class="clearfix">	
					<div style="display:block;text-align: center;width: 100%">
					      共有记录  <font color='red'>${totalCount }</font> 条记录&nbsp;&nbsp;
					       每页显示  <font color='red'>${pageSize}</font> 条记录&nbsp;&nbsp;
					       当前第  <font color='red'>${currentPage}</font> 页&nbsp;&nbsp; 
					       共<font color='red'> ${totalPage}</font> 页&nbsp; &nbsp; 
					  <a href='javascript:changePage(1)'>首页</a>
					  <c:if test="${currentPage >1}">
					      <a href='javascript:changePage(${currentPage-1})'>上一页</a>
					  </c:if> 
					  <c:if test="${currentPage <=1 }">
					    <font color='#808080'> 上一页</font>
		              </c:if>
		              &nbsp;|&nbsp;
		              <c:if test="${currentPage < totalPage }">
		                 <a href='javascript:changePage(${currentPage+1})'>下一页</a>
					  </c:if> 
					  <c:if test="${currentPage == totalPage}">
					     <font color='#808080'> 下一页</font>
					  </c:if> 
					   &nbsp;|
					  <a href='javascript:changePage(${totalPage})'>末页</a>
				   </div>
			    </div>
			</div>
		</div>
	</div>
</form>
</body>
</html>