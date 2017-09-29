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
					客户管理<small>&nbsp;-->&nbsp;账号信息</small>
				</h5>
			</div>
			<div>
				<form method="get" class="form-vertical">

					<table class="noborder">
						<tbody>
							<tr class="noline">
								<td class="fr" style="width:5%">客户名称</td>
								<td class="online" style="width:10%">
									<input type="text" id="businessname" value="${merchantAccount.businessname}" class="form-control" style="width:280px;">
								</td>
								<td class="fr" style="width:5%">客户账号</td>
								<td style="width:10%"><input type="text" value="${merchantAccount.merchantphone}" id="phone" class="form-control" style="width: 150px;"></td>
								<td class="fr" style="width:5%">邮箱</td>
								<td style="width: 10%"><input type="email" value="${merchantAccount.merchantemail}" id="email" class="form-control" style="width: 320px;"></td>
								<td style="width:5% "><button id="btnexport"
										class="btn btn-sm btn-success pull-left"
										type="button">导出数据</button></td>
							   <td style="width:20% "></td>
								
							</tr>
							<tr class="noline">
							    <td class="fr">Api Account</td>
								<td><input type="text" id="apiaccount"  value="${merchantAccount.apiaccount}" class="form-control" style="width: 280px;">
								</td> 
								<td class="fr" style="width: 90px;">是否认证</td>
								<td><select class="form-control" id="authflag" style="width: 150px;">
										<option value="0"></option>
										<option value="1" <c:if test="${merchantAccount.authflag==1}">selected="true"</c:if>>是</option>
										<option value="-1" <c:if test="${merchantAccount.authflag==-1}">selected="true"</c:if>>否</option>
								</select></td>
						
								<td class="fr" style="width: 100px;">注册时间</td>
								<td class="online"><input class="form-control layer-date" value="${merchantAccount.regstarttime}"
									id="regstarttime"  style="width: 150px;">
									<label>&nbsp;至&nbsp;</label>
									<input class="form-control layer-date" value="${merchantAccount.regendtime}"
									id="regendtime"  style="width: 150px;">
								</td>
		
								<td>
								    <button id="btnquery"
										class="btn btn-sm btn-primary pull-left"
										type="button">查询数据</button>
								</td>
							    <td>
							        <button id="btnadd"
										class="btn btn-sm btn-primary pull-left"
										type="button">创建新账号</button>
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
	<script type="text/javascript" src="/js/handler/acctlist.js"></script>
	
	<script type="text/javascript">
	$('#btnadd').click(function() { // 点击查询按钮操作
		
		var apiaccount=$('#apiaccount').val();
		var merchantphone=$('#phone').val();
		var merchantemail=$('#email').val();
		var regstarttime=$('#regstarttime').val();
		var regendtime=$('#regendtime').val();
		var authflag=$('#authflag').val();
		var businessname=$('#businessname').val();
		
		var obj=new Object();
		obj.businessName=businessname;
		obj.apiaccount=apiaccount;
		obj.merchantphone=merchantphone;
		obj.merchantemail=merchantemail;
		obj.regstarttime=regstarttime;
		obj.regendtime=regendtime;
		obj.authflag=authflag;
		var params=JSON.stringify(obj);
		
		 window.location.href="/account/to_add_account?params="+params;
	});
	
	$("#regstarttime").jeDate({
	    isinitVal:false,
	    ishmsVal:false,
	    minDate: '',
	    maxDate: $.nowDate(0),
	    format:"YYYY-MM-DD hh:mm:ss",
	    zIndex:3000,
	})
	$("#regendtime").jeDate({
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
