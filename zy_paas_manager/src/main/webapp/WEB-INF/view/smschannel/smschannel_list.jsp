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
					短信通道管理<small>&nbsp;-->&nbsp;通道录入管理</small>
				</h5>
			</div>
			<div>
				<form method="get" class="form-vertical">

					<table class="noborder">
						<tbody>
							<tr class="noline">
								<td class="fr" style="width:2%">通道名称</td>
								<td class="online" style="width:5%">
									<input type="text" id="channelName" class="form-control" style="width: 180px;">
								</td>
								<td class="fr" style="width:2%">通道类型</td>
								<td style="width:5%">
									<select class="form-control" id="channelType" style="width: 150px;" maxlength="50">
										<option value="-1"></option>
										<option value="1">通知</option>
										<option value="2">验证码</option>
										<option value="3">营销</option>
										<option value="4">通知、验证码</option>
										<option value="5">通知、验证码、营销</option>
								    </select>
								</td>
								<td class="fr" style="width:2%">运营商类型</td>
								<td style="width: 5%">
								    <select class="form-control" id="operateType" style="width: 150px;">
										<option value="-1"></option>
										<option value="0">三网合一</option>
										<option value="1">移动专网</option>
										<option value="2">电信专网</option>
										<option value="3">联通专网</option>
								    </select>
							    </td>

								<td style="width:20%"><button id="addnquery"
										class="btn btn-sm btn-info pull-left" onclick="dosmsChannel('')";
										type="button">通道录入</button></td>
								
							</tr>
							<tr class="noline">
							   <td class="fr">通道ID</td>
								<td>
								  <input type="text" id="channelId" class="form-control" style="width:180px;" maxlength="50">
							   </td>
								<td class="fr" >通道状态</td>
								<td><select class="form-control" id="status" style="width: 150px;">
										<option value="-1"></option>
										<option value="0">新创建</option>
										<option value="2">对接中</option>
										<option value="1">运营中</option>
										<option value="3">作废</option>
								</select>
								</td>
						
								<td class="fr">通道属性</td>
								<td>
								    <select class="form-control" id="channelProperty" style="width: 150px;">
										<option value=""></option>
										<option value="10">移动</option>
										<option value="20">电信</option>
										<option value="30">联通</option>
								    </select>
							    </td>
		
								<td><button id="btnquery"
										class="btn btn-sm btn-primary pull-left"
										type="button">查询数据</button></td>
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
	<script type="text/javascript" src="/js/handler/smschannel.js"></script>
	<script type="text/javascript">
	
	$("#createStarttime").jeDate({
	    isinitVal:false,
	    ishmsVal:false,
	    minDate: '',
	    maxDate: $.nowDate(0),
	    format:"YYYY-MM-DD hh:mm:ss",
	    zIndex:3000,
	})
	$("#createEndtime").jeDate({
	    isinitVal:false,
	    ishmsVal:false,
	    minDate: '',
	    maxDate: $.nowDate(0),
	    format:"YYYY-MM-DD hh:mm:ss",
	    zIndex:3000,
	})
	</script>
</body>
</html>
