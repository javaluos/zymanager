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
					系统设置<small>&nbsp;-->&nbsp;告警日志</small>
				</h5>
			</div>
			<div>
				<form method="get" class="form-vertical">

					<table class="noborder">
						<tbody>
							<tr class="noline">
							
								<td class="fr" style="width:5%">告警主体：</td>
								<td class="online" style="width:12%">
									<input type="text" id="monitorBody" class="form-control" style="width:120px;">
								</td>
								
								<td class="fr" style="width:5%">告警类型：</td>
								<td class="online" style="width:12%">
									<select class="form-control" id="monitorType" style="width: 120px;">
										<option value="0"></option>
										<option value="1">平台语音业务</option>
										<option value="2">平台短信业务</option>
										<option value="3">客户语音业务</option>
										<option value="4">客户短信业务</option>
										<option value="5">通道跑量业务</option>
										<option value="6">通道余额业务</option>
										<option value="7">客户余额业务</option>
								    </select>
								</td>
								
								<td class="fr" style="width:5%">告警时间：</td>
								<td class="online" style="width:25%">
									<li style="display: inline-block;">
									<input class="form-control" id="starttime" style="width: 150px;margin-top:5px">
									<label style="margin-top:5px">&nbsp;至&nbsp;</label> 
									<input class="form-control" id="endtime" style="width: 150px;margin-top:5px">
								  </li>
								</td>
								
								<td class="fr" style="width:3%">操作：</td>
								<td class="online" style="width:8%">
									<select class="form-control" id="isDeal" style="width: 80px;">
										<option value="0"></option>
										<option value="1">未处理</option>
										<option value="2">已处理</option>
								    </select>
								</td>
								
								<td class="fr" style="width:6%">是否升级告警：</td>
								<td class="online" style="width:8%">
									<select class="form-control" id="isUpMonitor" style="width: 80px;">
										<option value="0"></option>
										<option value="1">否</option>
										<option value="2">是</option>
								    </select>
								</td>
								
								<td>
								    <button id="btnquery"
										class="btn btn-sm btn-primary pull-left"
										style="margin-right: 10px;"
										type="button">查询数据</button>
									<button id="dealAll"
										class="btn btn-sm btn-primary pull-left"
										style="margin-right: 10px;"
										type="button">一键处理</button>
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
	<script type="text/javascript" src="/js/handler/main.js"></script>
	<script type="text/javascript" src="/js/handler/monitorLog.js"></script>
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
