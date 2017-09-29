<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
<head>
<title>Paas运营管理平台</title>
<jsp:include page="../comm/plugin.jsp" />
<style type="text/css">

</style>
</head>

<body>

	<!-- QueryForm -->
	<div class="col-sm-10" style="border-bottom: 1px solid #e5e6e7; padding-bottom: -20px;margin-bottom: 10px;">
		<div class="ibox">
			<div class="ibox-title">
				<h5>
				           系统设置
					<small>&nbsp;-->&nbsp;
					   短信黑名单组
					</small>
				</h5>
			</div>
			<div>
				<form method="get" class="form-vertical">

					<table class="table noborder">
						<tbody>
							<tr class="noline">
								<td class="fr" style="width: 90px;">黑名单组：</td>
                                <td style="width: 30%"><input type="text" id="groupname" class="form-control" style="width: 150px;"></td>

                                <td class="online" style="width: 70%">
                                    <button id="btnquery"
                                        class="btn btn-sm btn-primary pull-left"
                                        type="button">查询数据</button></td>
                                </td>

                                <td class="online" style="margin-left:100px">
                                    <button id="btnadd"
                                        class="btn btn-sm btn-primary pull-left"
                                        type="button">新增黑名单组</button></td>
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
	<script type="text/javascript" src="/js/handler/blackgroup.js?t=20170911"></script>
</body>
</html>
