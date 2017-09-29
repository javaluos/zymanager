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
					短信通道管理
					<small>&nbsp;-->&nbsp;
					 通道跑量管理
					</small>
				</h5>
			</div>
			<div>
				<form method="get" class="form-vertical">

					<table class="table noborder">
						<tbody>
							<tr class="noline">
							 <td class="fr" style="width: 90px;">通道名称:</td>
							  <td style="width: 20%;"><input type="text" id="channelName" class="form-control" style="width: 320px;"></td>
							  <td class="fr" style="width: 90px;">通道ID:</td>
							  <td style="width: 20%"><input type="text" id="channelSmsId" class="form-control" style="width: 180px;"></td>
							  			   
							  <td class="fr" style="width: 90px;">通道编号:</td>
							  <td style="width: 20%"><input type="text" id="channelMainCode" class="form-control" style="width: 150px;"></td>
							  <td class="fr"  style="width: 20%">
							      <button id="btnexport" class="btn btn-sm btn-success pull-left"
									type="button">导出数据</button>
							  </td>
							   
							</tr>
							
							<tr class="noline">
							   <td class="fr" style="width: 90px;">日期:</td>
							   <td class="online"  style="width: 15%">
									<li style="display: inline-block;">
										<input class="form-control" id="dateTimeStart" style="width: 150px;" value="${datetimeStart}">
										<label>&nbsp;到&nbsp;</label> 
										<input
											class="form-control" id="dateTimeEnd" style="width: 150px;" value="${datetimeEnd}">
									 </li>
								 </td>
								 
							   <td class="fr" style="width: 90px;">通道类型:</td>
							   <td class="online" style="width: 15%">
							      <select class="form-control m-b" id="channelType" style="width: 180px;">
							            <option value="-1"  selected="selected">请选择</option>
										<option value="1">通知</option>
										<option value="2">验证码</option>
										<option value="3">营销</option>
										<option value="4">通知、验证码</option>
										<option value="5">通知、验证码、营销</option>
								   </select>
							   </td>
								
							    <td class="fr" style="width: 90px;"></td>
								<td>
								</td>
								<td class="fr" >
									<button id="btnquery"
										class="btn btn-sm btn-primary pull-left"
										type="button">查询数据</button>
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
	<script type="text/javascript" src="/js/handler/channelSummary.js"></script>
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