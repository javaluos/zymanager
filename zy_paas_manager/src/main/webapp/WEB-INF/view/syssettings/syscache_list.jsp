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
			<div class="ibox-title">
				<h5>
					系统设置<small>&nbsp;-->&nbsp;系统缓存</small>
				</h5>
			</div>
			<div>
				<form method="get" class="form-vertical">

					<div>
						<table
							class="table table-striped table-bordered table-hover dataTables-example dataTable">
							<tbody>
								<tr>
									<td style="width: 50px;">序号</td>
									<td style="width: 200px;">名称</td>
									<td style="width: 500px;">描述</td>
									<td style="width: 80px;">操作</td>
									<td></td>
								</tr>
								<tr>
									<td>1</td>
									<td>短信接口缓存</td>
									<td>短信系统配置, 手机号运营商, 短信敏感词, 短信IP鉴权配置 等</td>
									<td>
									    <button id="btnrefresh1" class="btn btn-sm btn-danger pull-left" type="button" width="80px;">刷新</button>
									</td>
									<td></td>
								</tr>
								<tr>
									<td>2</td>
									<td>短信CMPP账号缓存</td>
									<td>短信CMPP2.0账号, 短信CMPP3.0账号 等</td>
									<td>
									    <button id="btnrefresh2" class="btn btn-sm btn-danger pull-left" type="button" width="80px;">刷新</button>
									</td>
									<td></td>
								</tr>
								<tr>
									<td>3</td>
									<td>语音接口缓存</td>
									<td>语音系统配置, 语音API资源配置 等</td>
									<td>
										 <button id="btnrefresh3" class="btn btn-sm btn-danger pull-left" type="button" width="80px;">刷新</button>
									</td>
									<td></td>
								</tr>

							</tbody>
						</table>
					</div>

				</form>
			</div>
		</div>
	</div>
	<!-- /QueryForm -->

	</div>

	<script type="text/javascript">
		$(function() {

			// 刷新敏感词-缓存
			$('#btnrefresh1').click(function() {
				$.get("/syscache/refreshSmsAPICfg", function(data) {
					if (data == "SUCCESS") {//SUCCESS
						layer.alert("刷新短信接口缓存成功.");
					} else {
						layer.alert("刷新短信接口缓存失败.");
					}
				});

			});

			// 刷新CMPP账号信息-缓存
			$('#btnrefresh2').click(function() {
				$.ajax({
					async : false,
					type : "GET",
					url : "/syscache/refreshCmppAcctInfo",
					success : function(data) {
						if (data == "SUCCESS") {//SUCCESS
							layer.alert("刷新短信CMPP账号缓存成功.");
						} else {
							layer.alert("刷新短信CMPP账号缓存失败.");
						}
					}
				});
			});

			// 刷新语音接口配置-缓存
			$('#btnrefresh3').click(function() {
				$.ajax({
					async : false,
					type : "GET",
					url : "/syscache/refreshVoiceAPICfg",
					success : function(data) {
						if (data == "SUCCESS") {//SUCCESS
							layer.alert("刷新语音接口缓存成功.");
						} else {
							layer.alert("刷新语音接口缓存失败.");
						}
					}
				});
			});

		});
	</script>
</body>
</html>
