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
	<div class="col-sm-12"
		style="border-bottom: 1px solid #e5e6e7; padding-bottom: -20px; margin-bottom: 10px;">
		<div class="ibox">
			<div>
				<form method="get" class="form-vertical">
					<table style="margin-top:5px" class="noborder">
						<tbody>
							<tr class="noline">
								<td class="fr" style="width: 15%">客户名称：</td>
								<td class="online" style="width: 8%"><input type="text"
									id="businessName" class="form-control" style="width: 220px;">
								</td>
								<td><button id="btnquery"
										class="btn btn-sm btn-primary pull-left" type="button">查询</button></td>
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
	<script type="text/javascript" src="/js/handler/voicewhitelistadd.js"></script>
	<script type="text/javascript">
		function doadd(apiAccount) {
			var exist = accountExist(apiAccount);
			if(exist){
				alert("该账号已加入白名单");
				return false;
			}
			$.ajax({
				type : "POST",
				url : "/voice_white_list/do_add",
				data : {
					'apiAccount' : apiAccount
				},
				success : function(data) {
					if (data) {
						window.parent.location.reload();
						parent.layer.closeAll();
					}
				}
			});
		}
		function accountExist(apiAccount){
			var result = false;;
			$.ajax({
				type : "POST",
				async: false,
				url : "/voice_white_list/account_exist",
				data : {
					'apiAccount' : apiAccount
				},
				success : function(data) {
					result = data;
				}
			});
			return result;
		}
	</script>
</body>
</html>
