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
		</div>
	</div>
	<table class="noborder">
		<tbody>
			<tr class="noline">
				<td style="width: 2%;"></td>
				<td class="fr" style="width: 10%;">客户账号</td>
				<td class="online" style="width: 10%"><input type="text"
					id="merchantphone" class="form-control" style="width: 250px;" value="${merchantphone }">
				</td>
				<td class="fr" style="width: 4%;"></td>
				<td class="fr" style="width: 10%; margin-left: 20px">客户名称</td>
				<td class="online" style="width: 10%"><input type="text"
					id="businessname" class="form-control" style="width: 250px;"
					maxlength="50" value="${businessname }"></td>
				<td >
					<button id="accquery" class="btn btn-sm btn-primary pull-left" 
						type="button">查询数据</button>
				</td>
				<td></td>
			</tr>
		</tbody>
	</table>
	<!-- Datagrid -->
	<div id="datagrid" style="margin-left:20px;width: 96%">
	</div>

	<script type="text/javascript" src="/js/handler/channelbindaccdata.js"></script>
</body>
</html>
