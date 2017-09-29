<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
<head>
<title>Paas运营管理平台</title>
<jsp:include page="../comm/plugin.jsp" />
</head>

<body>

	<!-- QueryForm -->
	<div class="col-sm-12" style="border-bottom: 1px solid #e5e6e7; padding-bottom: -20px;margin-bottom: 10px;">
		<div class="ibox">
			<div class="ibox-title">
				<h5>
					详单信息
					<small>&nbsp;-->&nbsp;
					    回拨电话汇总
					</small>
				</h5>
			</div>
			<div>
				<form method="get" class="form-vertical">

					<table class="table noborder">
						<tbody>
							<tr class="noline">
							
							   <td class="fr" style="width: 70px;">时间:</td>
							   <td class="online"  style="width: 22%">
									<li style="display: inline-block;">
										<input class="form-control" id="dateTimeStart" style="width: 150px;" value="${datetimeStart}">
										<label>&nbsp;到&nbsp;</label> 
										<input
											class="form-control" id="dateTimeEnd" style="width: 150px;" value="${datetimeEnd}">
									 </li>
								 </td>
								 
								<td class="fr" style="width: 80px;">A路接通率:</td>
								<td class="online" style="width: 22%">
								    <input type="email" id="pctCallSucStartA" class="form-control" style="width: 80px;">
								    <label>&nbsp;&nbsp;%&nbsp;到&nbsp;</label> 
								    <input type="email" id="pctCallSucEndA" class="form-control" style="width: 80px;" value="${authStatus}">
									<label>&nbsp;&nbsp;%&nbsp;</label>
								</td>
								
								<td class="fr" style="width: 80px;">A路应答率:</td>
								<td class="online" style="width: 15%">
								    <input type="email" id="pctResponseSucStartA" class="form-control" style="width: 80px;">
								    <label>&nbsp;&nbsp;%&nbsp;到&nbsp;</label> 
								    <input type="email" id="pctResponseSucEndA" class="form-control" style="width: 80px;" value="${authStatus}">
									<label>&nbsp;&nbsp;%&nbsp;</label>
								</td>
								
								<td class="fr" style="width: 90px;">ACD:</td>
								 <td class="online" style="width: 22%">
									<input type="email" id="avgAcdStart" class="form-control" style="width: 80px;">
									<label>&nbsp;秒</label>
								 </td>
							</tr>
							
							<tr class="noline">	
								<td class="fr" style="width: 90px;">客户账号:</td>
								<td style="width: 22%"><input type="text" id="merchantphone" class="form-control" style="width: 150px;"></td>
								
								<td class="fr" style="width:80px;">B路接通率:</td>
								<td class="online" style="width: 22%">
								    <input type="email" id="pctCallSucStartB" class="form-control" style="width: 80px;">
								    <label>&nbsp;&nbsp;%&nbsp;到&nbsp;</label> 
								    <input type="email" id="pctCallSucEndB" class="form-control" style="width: 80px;" value="${authStatus}">
									<label>&nbsp;&nbsp;%&nbsp;</label>
								</td>
								
								<td class="fr" style="width: 80px;">B路应答率:</td>
								<td class="online" style="width: 15%">
								    <input type="email" id="pctResponseSucStartB" class="form-control" style="width: 80px;">
								    <label>&nbsp;&nbsp;%&nbsp;到&nbsp;</label> 
								    <input type="email" id="pctResponseSucEndB" class="form-control" style="width: 80px;" value="${authStatus}">
									<label>&nbsp;&nbsp;%&nbsp;</label>
								</td>
								 
  
								 <td class="fr" style="width: 90px;"></td>
								 <td style="width: 22%">
								   <button id="btnquery"
										class="btn btn-sm btn-primary pull-left"
										type="button">查询数据</button></td>
								 </td>
							</tr>
							
						</tbody>
					</table>

				</form>
			</div>
		</div>
	</div>
	<!-- /QueryForm -->

	<!-- Datagrid -->
	<div id="datagrid"></div>
	</div>
	<!-- /Datagrid -->

	</div>
	<!-- /wrap_right -->
	<script type="text/javascript" src="/js/analysis/callBackSummary.js"></script>
	<script type="text/javascript">
	    var start = {
		    format: 'YYYY-MM-DD',
		    minDate: '', //设定最小日期为当前日期
		    maxDate: $.nowDate(0), //最大日期
		    choosefun: function(elem,datas){
		        end.minDate = datas; //开始日选好后，重置结束日的最小日期
		    }
		};
		var end = {
		    format: 'YYYY-MM-DD',
		    minDate: '', //设定最小日期为当前日期
		    maxDate: '', //最大日期
		    choosefun: function(elem,datas){
		       start.maxDate = datas; //将结束日的初始值设定为开始日的最大日期
		    }
		};
		$("#dateTimeStart").jeDate(start);
		$("#dateTimeEnd").jeDate(end);
		
	</script>
</body>
</html>
