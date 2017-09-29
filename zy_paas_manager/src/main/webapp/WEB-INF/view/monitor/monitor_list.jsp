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
	<div class="col-sm-12" style="border-bottom: 1px solid #e5e6e7;margin-bottom: 10px;">
		<div class="ibox">
			<div class="ibox-title">
				<h5>
					监控信息
					<small>&nbsp;-->&nbsp;
					  客户业务监控
					</small>
				</h5>
			</div>
			<div>
				<form method="get" class="form-vertical">
                
					<table class="table noborder">
						<tbody>
							<tr class="noline">
							
								<td class="fr" style="width: 90px;">客户名称:</td>
								<td style="width: 30%"><input type="text" id="businessName" class="form-control" style="width: 150px;"></td>
								
								<td class="fr" style="width: 90px;">客户账号:</td>
								<td style="width: 30%"><input type="text" id="phone" class="form-control" style="width: 150px;"></td>
														
                                <td class="fr" style="width: 20px;"></td>
								<td style="width: 10%">
									<button id="btnquery"
										class="btn btn-sm btn-primary pull-left"
										type="button">查询数据</button></td>
								</td>
								
								<td class="fr" style="width: 5px;"></td>
								<td style="width: 25%">
									<c:if test="${permission == true }">
									    <button id="btncontroller"
											class="btn btn-sm btn-primary pull-left"
											type="button">添加监控客户</button>
									</c:if>
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
	<script type="text/javascript" src="/js/handler/monitor.js"></script>
</body>
</html>
