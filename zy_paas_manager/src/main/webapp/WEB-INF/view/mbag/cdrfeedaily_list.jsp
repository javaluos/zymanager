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
					钱包管理<small>&nbsp;-->&nbsp;月结账单</small>
				</h5>
			</div>
			<div>
				<form method="get" class="form-vertical">

					<table class="table noborder">
						<tbody>
							<tr class="noline">
							    <td class="fr" style="width: 100px;">姓名/企业名称:</td>
								<td style="width: 25%;padding-top: 3px;height: 28px;line-height: 28px;display: inline-block;">
								   ${merchantAccount.businessName}
								   <input type="hidden" id="apiAccount" value="${apiAccount}" class="form-control" style="width: 150px;">
								</td>
							
								<td class="fr" style="width: 90px;">客户账号:</td>
								<td style="width: 25%;padding-top: 3px;height: 28px;line-height: 28px;display: inline-block;">
									 ${merchantAccount.merchantPhone}
								</td>
								
								<td class="fr" style="width: 90px;">时间:</td>
								<td style="width: 25%;padding-top: 3px;height: 28px;line-height: 28px;display: inline-block;">
								     <input class="form-control layer-date" id="dateTime" style="width: 150px;" value="${dateTime}">			    
								</td>
								
								<td style="width: 25% ;">
								       <button id="btnquery"
										class="btn btn-sm btn-primary pull-left"
										type="button">查询数据</button>
									    <button id="btnexport" 
										class="btn btn-sm btn-success pull-left" style="margin-left: 20px;"
										type="button">导出数据</button>
										<button class="btn btn-sm btn-primary pull-left" type="button"
										  style="width: 60px;margin-left: 20px;" 
										  onclick="history.go(-1)">返回</button>
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
	<script type="text/javascript" src="/js/handler/cdrfeedaily.js"></script>
	<script type="text/javascript">
		$("#dateTime").jeDate({
		    isinitVal:true,
		    ishmsVal:false,
		    minDate: '',
		    maxDate: $.nowDate(0),
		    format:"YYYY-MM",
		    zIndex:3000,
		})
	</script>
</body>
</html>
