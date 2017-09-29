<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
<head>
<title>PaaS运营管理平台</title>
<jsp:include page="../comm/plugin.jsp" />
</head>

<body>

	<!-- QueryForm -->
	<div class="col-sm-12" style="border-bottom: 1px solid #e5e6e7; padding-bottom: -20px;margin-bottom: 10px;">
		<div class="ibox">
			<div class="ibox-title">
				<h5>
					钱包管理<small>&nbsp;-->&nbsp;账号余额</small>
				</h5>
			</div>
			<div>
				<form method="get" class="form-vertical">

					<table class="table noborder">
						<tbody>
							<tr class="noline">
							    <td class="fr" style="width: 100px;">客户名称</td>
								<td style="width: 10%"><input type="text" id="businessname" class="form-control" style="width: 150px;"></td>
								<td class="fr" style="width: 90px;">客户账号</td>
								<td style="width: 10%"><input type="text" id="merchantPhone" class="form-control" style="width: 150px;"></td>
								<td class="fr" style="width: 90px;">邮箱</td>
								<td style="width: 10%"><input type="email" id="email" class="form-control" style="width: 150px;"></td>
								<td class="fr" style="width: 100px;">余额范围</td>
								<td class="online"  style="width: 25%">
									<li style="display: inline-block;">
										<input class="form-control layer-date" id="balancestart" style="width: 150px;" maxlength="15">
										<label>&nbsp;至&nbsp;</label> 
										<input
											class="form-control layer-date" id="balanceend" style="width: 150px;" maxlength="15">
										<label>&nbsp;元&nbsp;</label> 
									</li>
								</td>
								<td style="width: 15% ;"><button id="btnquery"
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
	<script type="text/javascript" src="/js/handler/acctbalancelist.js"></script>
</body>
</html>
