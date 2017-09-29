<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
<head>
<title>Paas运营管理平台</title>
<jsp:include page="../comm/plugin.jsp" />
<style type="text/css">

</style>
</head>

<body>

	<!-- QueryForm -->
	<div class="col-sm-10" style="border-bottom: 1px solid #e5e6e7; padding-bottom: -20px;margin-bottom: 10px;">
		<div class="ibox">
			<div class="ibox-title">
				<h5>
				          详单查询
					<small>&nbsp;-->&nbsp;
					   回拨发送记录
					</small>
				</h5>
			</div>
			<div>
				<form method="get" class="form-vertical">

					<table class="table noborder">
						<tbody>
							<tr class="noline">
							
								<td class="fr" style="width: 90px;">客户账号:</td>
								<td style="width: 15%">
									<input type="text" id="phone" class="form-control" style="width: 150px;"
									 data-toggle="popover" data-placement="top" data-content="">
								</td>
								
								<td class="fr" style="width: 100px;">被叫号码:</td>
								<td style="width: 15%">
									<input type="text" id="callee" class="form-control" style="width: 150px;"
									 data-toggle="popover" data-placement="top" data-content="">
								</td>
								
							    <td class="fr" style="width: 100px;">发起呼叫时间:</td>
							    <td class="online"  style="width: 30%">
								  <li style="display: inline-block;">
									<input class="form-control" id="starttime" style="width: 150px;" value="${datetimeStart}">
									<label>&nbsp;至&nbsp;</label> 
									<input
										class="form-control" id="endtime" style="width: 150px;"
                                        value="${datetimeEnd}">
								  </li>
							    </td>
							    
							    <td class="fr" style="width: 20px;"></td>
								<td style="width: 15%">
								</td>
							</tr>
							
							<tr class="noline">
								<!-- <td class="fr" style="width: 100px;">调用时间:</td>
							    <td class="online"  style="width: 30%">
								  <li style="display: inline-block;">
									<input class="form-control layer-date" style="width: 150px;"
										onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" value="">
									<label>&nbsp;至&nbsp;</label> 
									<input
										class="form-control layer-date" style="width: 150px;"
										onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" value="">
								  </li>
							    </td> -->
								 
								 <td class="fr" style="width: 100px;">被叫通话状态:</td>
								 <td><select class="form-control m-b" id="state" style="width: 150px;">
										<option value="-1"></option>
										<option value="0">正常通话</option>
										<option value="1">被叫未接听</option>
										<option value="2">被叫拒接</option>
										<option value="3">外呼失败</option>
								    </select>
								 </td>
								 
								<!--  <td class="fr" style="width: 100px;">被叫通话状态:</td>
								 <td><select class="form-control m-b" style="width: 150px;">
										<option value="-1"></option>
										<option value="0">正常通话</option>
										<option value="1">被叫未接听</option>
										<option value="2">被叫拒接</option>
										<option value="3">外呼失败</option>
								    </select>
								 </td> -->
								 
								<td class="fr" style="width: 10px;"></td>
								<td style="width: 80px;">
								    <button id="btnquery"
										class="btn btn-sm btn-primary pull-left"
										type="button">查询数据</button>
								     <!-- <button id="btnexport" 
										class="btn btn-sm btn-success pull-left" style="margin-left: 35px;"
										type="button">导出数据</button> -->
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
	<script type="text/javascript" src="/js/handler/callBack.js"></script>
	<script type="text/javascript">
	    var start = {
		    format: 'YYYY-MM-DD hh:mm:ss',
		    minDate: '', //设定最小日期为当前日期
		    maxDate: $.nowDate(0), //最大日期
		    choosefun: function(elem,datas){
		        end.minDate = datas; //开始日选好后，重置结束日的最小日期
		    }
		};
		var end = {
		    format: 'YYYY-MM-DD hh:mm:ss',
		    minDate: '', //设定最小日期为当前日期
		    maxDate: '', //最大日期
		    choosefun: function(elem,datas){
		       start.maxDate = datas; //将结束日的初始值设定为开始日的最大日期
		    }
		};
		$("#starttime").jeDate(start);
		$("#endtime").jeDate(end);
		
	</script>
</body>
</html>
