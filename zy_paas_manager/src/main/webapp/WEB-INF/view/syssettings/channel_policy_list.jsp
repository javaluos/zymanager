
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
					系统设置<small>&nbsp;-->&nbsp;分流设置</small>
				</h5>
			</div>
			<div>
				<form method="get" class="form-vertical">

					<table class="noborder">
						<tbody>
							<tr class="noline">
							
								<td class="fr" style="width:10%;">&nbsp;&nbsp;&nbsp;&nbsp;分流策略名称：</td>
								<td class="online" style="width:12%">
									<input type="text" id="policyName" class="form-control" style="width:220px;">
								</td>
								
								<td class="fr" style="width:10%"></td>
								<td class="online" style="width:12%"></td>
								
								<td class="fr" style="width:10%"></td>
								<td class="online" style="width:12%"></td>
								
								
								<td>
								    <button id="btnquery"
										class="btn btn-sm btn-primary pull-left"
										style="margin-right: 30px;"
										type="button">查询数据</button>
									<button id="btnadd"
										class="btn btn-sm btn-primary pull-left"
										style="margin-right: 30px;"
										type="button">增加分流策略</button>
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
	<script type="text/javascript" src="/js/handler/channelPolicy.js"></script>
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
