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
					   短信通道组管理
					</small>
				</h5>
			</div>
			<div>
				<form method="get" class="form-vertical">
					<table class="noborder">
						<tbody>
							<tr class="noline">
								<td  class="fr" style="width: 90px;text-align:center;valign:middle;padding-top: -40px;">通道组名称：</td>
								<td  style="width: 20%">
								    <input id="channel_group_name" type="text" id="phone" class="form-control" style="width: 150px;">
								</td>
								
								<td class="fr" style="width: 90px;text-align:center;valign:middle;">通道ID：</td>
								<td style="width: 20%">
								    <input id="channel_id" type="text" id="phone" class="form-control" style="width: 150px;">
								</td>
								
								<td class="fr" style="width: 30%"></td>
								<td>
								    <button id="btn_query" class="btn btn-sm btn-primary pull-left" type="button">查询数据</button>
								    <button id="btn_add"  style="margin-left:20px" 
								    	class="btn btn-sm btn-info pull-left" onclick="addChannelGroup('')";
										type="button">添加通道组</button>
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
	<script type="text/javascript" src="/js/handler/channelGroup.js"></script>
</body>
</html>
