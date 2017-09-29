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
					短信通道管理<small>&nbsp;-->&nbsp;通道绑定管理</small>
				</h5>
			</div>
			<div>
				<form method="get" class="form-vertical">

					<table class="noborder">
						<tbody>
							<tr class="noline">

								<td class="fr" style="width:5%;">通道名称</td>
								<td class="online" style="width:10%">
									<input type="text" id="channelName" class="form-control" style="width: 250px;">
							   	</td>
							   	<td class="fr" style="width:5%">通道ID</td>
								<td class="online" style="width:10%">
									<input type="text" id="channelId" class="form-control" style="width:250px;" maxlength="50">
								</td>
								<td style="width:5%">通道类型</td>
								<td class="online" style="width:10%">
									<select class="form-control" id="channelType" style="width: 150px;">
										<option value=""></option>
										<option value="1">通知</option>
										<option value="2">验证码</option>
										<option value="3">营销</option>
										<option value="4">验证码、通知</option>
										<option value="5">验证码、通知、营销</option>
									</select>
                                    <span style="float:right;margin-top:-28px;margin-right:18px">
                                        <label style="margin-right:40px">通道属性</label>
                                        <select class="form-control" id="channelProperty" style="width: 120px;">
                                            <c:forEach var="channelProperty" items="${channelPropertyList }">
                                                <option value="${channelProperty.value }">${channelProperty.name}</option>
                                            </c:forEach>
                                        </select>
                                    </span>
								</td>
								<td>
									<button id="addnquery"
										class="btn btn-sm btn-info pull-left" onclick="tobind();";
										type="button">添加绑定</button>
								</td>
							</tr>
							<tr class="noline">
								<td class="fr" style="width:5%">客户名称</td>
								<td class="online" style="width:10%">
									<input type="text" id="businessName" class="form-control" style="width:250px;" maxlength="50">
								</td>
								<td class="fr" style="width: 90px;">客户账号</td>
								<td class="online" style="width:10%">
									<input type="text" id="merchantPhone" class="form-control" style="width:250px;" maxlength="50">
								</td>
								<td class="fr" style="width:5%">绑定时间</td>
								<td class="online" style="width:20%">
									<li style="display: inline-block;">
										<input class="form-control" id="starttime" style="width: 150px;" value="${datetimeStart}">
										<label>&nbsp;至&nbsp;</label> 
										<input
											class="form-control" id="endtime" style="width: 150px;"
	                                        value="${datetimeEnd}">
								  	</li>
							    </td>
								<td style="width:5%"><button id="btnquery"
										class="btn btn-sm btn-primary pull-left"
										type="button" value="111">查询数据</button>
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
	<script type="text/javascript" src="/js/handler/channelbind.js?t=20170505"></script>
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
		
		function tobind(){
			window.location.href="/channel_bind/to_bind";
		}
	</script>
</body>
</html>
