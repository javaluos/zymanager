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
					系统设置<small>&nbsp;-->&nbsp;短信白名单</small>
				</h5>
			</div>
			<div>
				<form method="get" class="form-vertical">

					<table class="noborder">
						<tbody>
							<tr class="noline">
							
								<td class="fr" style="width:6%">白名单号码：</td>
								<td class="online" style="width:8%">
									<input type="text" id="mobile" class="form-control" style="width:220px;">
								</td>
								
								<td class="fr" style="width:5%">所属客户：</td>
								<td class="online" style="width:8%">
									<input type="text" id="businessName" class="form-control" style="width:220px;">
								</td>
								
								<td class="fr" style="width:5%">加入时间：</td>
								<td class="online" style="width:30%">
									<li style="display: inline-block;">
									<input class="form-control" id="starttime" style="width: 180px;margin-top:5px" value="${datetimeStart}">
									<label style="margin-top:5px">&nbsp;至&nbsp;</label> 
									<input class="form-control" id="endtime" style="width: 180px;margin-top:5px" value="${datetimeEnd}">
								  </li>
								</td>
								
								<td>
								    <button id="btnquery"
										class="btn btn-sm btn-primary pull-left"
										style="margin-right: 30px;"
										type="button">查询数据</button>
										
									<button id="btnexport"
										class="btn btn-sm btn-primary pull-left"
										style="margin-right: 30px;"
										type="button">导出</button>
										
									<button id="btnadd"	onclick="toAdd();"
										class="btn btn-sm btn-primary pull-left"
										style="margin-right: 30px;"
										type="button">加入白名单</button>
										
									<button id="btnadd"	onclick="delAll();"
										class="btn btn-sm btn-primary pull-left"
										style="margin-right: 30px;"
										type="button">删除</button>
										
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
	<script type="text/javascript" src="/js/handler/smswhitelist.js"></script>
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
		
		function toAdd(){
			window.location.href="/sms_white_list/to_add";
		}
	</script>
	</script>
</body>
</html>
