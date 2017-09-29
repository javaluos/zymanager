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
					钱包管理<small>&nbsp;-->&nbsp;余额修改记录</small>
				</h5>
			</div>
			<div>
				<form method="get" class="form-vertical">

					<table class="table noborder">
						<tbody>
							<tr class="noline">
								<td class="fr">客户账号</td>
								<td class="online"><input type="text" id="merchantAccount" value="${merchantAccount }" class="form-control" style="width:280px;"></td>
								<td class="fr" style="width:90px;">操作人</td>
								<td><input type="text" id="operator" class="form-control" style="width: 150px;"></td>
								<td class="fr" style="width: 100px;">修改时间</td>
								<td class="online">
									<li style="display: inline-block;">
										<input class="form-control layer-date" id="startDate" style="width: 150px;" />
										<label>&nbsp;至&nbsp;</label> 
										<input class="form-control layer-date" id="endDate" style="width: 150px;" />
									</li>
								</td>
								<td><button id="btnquery"
										class="btn btn-sm btn-primary pull-left"
										type="button">查询数据</button>
									<c:if test="${merchantAccount != null}">
										<button class="btn btn-sm btn-primary pull-left" style="margin-left:10px;" type="button" onclick="back();">返&nbsp;&nbsp;&nbsp;&nbsp;回</button>
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
	<script type="text/javascript" src="/js/handler/accbalupdatelist.js"></script>
	<script type="text/javascript">
	var merchantAccount = $("#merchantAccount").val();
	if(merchantAccount.trim() != ''){
		$("#startDate").jeDate({
		    isinitVal:false,
		    ishmsVal:false,
		    minDate: '',
		    maxDate: $.nowDate(0),
		    format:"YYYY-MM-DD",
		    zIndex:3000,
		})
		$("#endDate").jeDate({
		    isinitVal:false,
		    ishmsVal:false,
		    minDate: '',
		    maxDate: $.nowDate(0),
		    format:"YYYY-MM-DD",
		    zIndex:3000,
		})
	}else{
		$("#startDate").jeDate({
		    isinitVal:true,
		    initAddVal:[-7],
		    ishmsVal:false,
		    minDate: '',
		    maxDate: $.nowDate(0),
		    format:"YYYY-MM-DD",
		    zIndex:3000,
		})
		$("#endDate").jeDate({
		    isinitVal:true,
		    ishmsVal:false,
		    minDate: '',
		    maxDate: $.nowDate(0),
		    format:"YYYY-MM-DD",
		    zIndex:3000,
		})
	}
	function back(){
		window.location.href = "/moneybag/actbalancelist.html"
	}
	</script>
</body>
</html>
