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
					审核信息
					<small>&nbsp;-->&nbsp;
					   语音审核
					</small>
				</h5>
			</div>
			<div>
				<form method="get" class="form-vertical">
                
					<table class="table noborder">
						<tbody>
							<tr class="noline">
								<td class="fr" style="width: 90px;">客户账号</td>
								<td style="width: 20%"><input type="text" id="phone" class="form-control" style="width: 150px;">
								</td>
								
																
								 <td class="fr" style="width: 90px;">类型</td>
								 <td><select class="form-control m-b" id="authflag" style="width: 150px;">
										<option value="0"></option>
										<option value="1">验证码</option>
										<option value="2">通知</option>
								    </select>
								 </td>
								 
								<td class="fr" style="width: 100px;">提交时间</td>
							    <td class="online"  style="width: 25%">
								  <li style="display: inline-block;">
									<input class="form-control" id=authSubmitTimeStart style="width: 150px;" >
									<label>&nbsp;至&nbsp;</label> 
									<input
										class="form-control" id="authSubmitTimeEnd" style="width: 150px;">
								  </li>
							    </td>
								

							</tr>
							
							<tr class="noline">
							    <td class="fr" style="width: 90px;">状态</td>
								 <td><select class="form-control m-b" id="authStatus" style="width: 150px;">
										<option value="0"></option>
										<option value="3" selected="selected">待审核</option>
										<option value="1">审核通过</option>
										<option value="4">审核失败</option>
								    </select>
								 </td>
								
						
							    
							  <td class="fr" style="width: 100px;">审核时间</td>
							   <td class="online"  style="width: 35%">
								 <li style="display: inline-block;">
									<input class="form-control" id=authResultTimeStart style="width: 150px;" >
									<label>&nbsp;至&nbsp;</label> 
									<input
										class="form-control" id="authResultTimeEnd" style="width: 150px;">
								 </li>
							   </td>
							
                                <td class="fr" style="width: 20px;"></td>
								<td><button id="btnquery"
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
	<script type="text/javascript" src="/js/handler/audit.js"></script>
	<script type="text/javascript">
	    var start = {
		    format: 'YYYY-MM-DD hh:mm:ss',
		    minDate: '', //设定最小日期为当前日期
		    maxDate: $.nowDate(0), //最大日期
		    choosefun: function(elem,datas){
		        end.minDate = datas; //开始日选好后，重置结束日的最小日期
		    }
		};
		var end = {
		    format: 'YYYY-MM-DD hh:mm:ss',
		    minDate: '', //设定最小日期为当前日期
		    maxDate: '', //最大日期
		    choosefun: function(elem,datas){
		       start.maxDate = datas; //将结束日的初始值设定为开始日的最大日期
		    }
		};
		$("#authSubmitTimeStart").jeDate(start);
		$("#authSubmitTimeEnd").jeDate(end);
		
		$("#authResultTimeStart").jeDate(start);
		$("#authResultTimeEnd").jeDate(end);
	</script>
</body>
</html>
