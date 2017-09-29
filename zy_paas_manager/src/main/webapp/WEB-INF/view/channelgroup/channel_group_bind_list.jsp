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
					资源管理
					<small>&nbsp;-->&nbsp;
					   通道组绑定管理
					</small>
				</h5>
			</div>
			<div>
				<form method="get" class="form-vertical">
					<table class="table noborder">
						<tbody>
							<tr class="noline">
								<td class="online" style="width: 20%;">
									<li style="display: inline-block;padding: 0 10px 0 20px;">
										<label>客户名称&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
										<input type="text" id="business_name" class="form-control" style="width: 70%;">
									</li>
								</td>					
								<td class="online" style="20%;">
									<li style="display: inline-block;">
									 	<label>客户帐号&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
									 	<input type="text" id="merchant_phone" class="form-control" style="width: 70%;">
								 	</li>
								</td>
							    <td class="online" style="40%;">
								  <li style="display: inline-block;">
								  	<label>绑定时间&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
									<input class="form-control" id="start_time" style="width: 150px;">
									<label>&nbsp;&nbsp;至&nbsp;&nbsp;</label> 
									<input class="form-control" id="end_time" style="width: 150px;">
								  </li>
							   </td>
							   <td class="noline" style="width: 20%;">
								    <button id="btn_query" class="btn btn-sm btn-primary pull-left" type="button">查询数据</button>
								    <button id="btn_add"  style="margin-left:20px" 
								    	class="btn btn-sm btn-info pull-left" onclick="saveChannelGroupBind(-1)";
										type="button">添加绑定</button>
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
	<script type="text/javascript" src="/js/handler/channelGroupBind.js"></script>
	<script>
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
		$("#start_time").jeDate(start);
		$("#end_time").jeDate(end);
	</script>
</body>
</html>
