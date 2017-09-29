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
					   模板审核
					</small>
				</h5>
			</div>
			<div>
				<form method="post" class="form-vertical">
					<table class="table noborder">
						<tbody>
							<tr class="noline">
							    <td class="fr" style="width: 110px;">客户名称</td>
								<td style="width: 10%"><input type="text" id="businessName" class="form-control" style="width: 250px;">
								</td>
								
								<td class="fr" style="width: 90px;">客户账号</td>
								<td style="width: 10%"><input type="text" id="merchantPhone" class="form-control" style="width: 150px;">
								</td>
								
							   <td class="fr" style="width: 90px;">审核人</td>
							 	<td style="width: 10%">
								    <input type="text" id="authUser" class="form-control" style="width: 150px;">
							 	</td> 
							   <td class="fr" style="width: 100px;">审核时间:</td>
							   <td class="online"  style="width: 20%">
								 <li style="display: inline-block;">
									<input class="form-control" id="authResultTimeStart" style="width: 150px;" >
									<label>&nbsp;至&nbsp;</label> 
									<input
										class="form-control" id="authResultTimeEnd" style="width: 150px;">
								 </li>
							   </td>
							   <td class="fr"  style="width: 20%"></td>
							</tr>
							
							<tr class="noline">	
							 	<td class="fr" style="width: 90px;">状态</td>
							 	<td>
							 		<select class="form-control m-b" id="authStatus" style="width: 250px;">
										<option value="0"></option>
										<option value="3" selected="selected">待审核</option>
										<option value="1">审核通过</option>
										<option value="4">审核未通过</option>
								    </select>
							 	</td>
								 
							 	<td class="fr" style="width: 90px;">模板ID</td>
							 	<td style="width: 10%">
								    <input type="text" id="tid" class="form-control" style="width: 150px;">
							 	</td> 
						  		<td class="fr" style="width: 90px;">模板类型</td>
							 	<td style="width: 10%">
								   	<select class="form-control m-b" id="category" style="width: 150px;">
										<option value=""></option>
										<option value="8">通知</option>
										<option value="9">验证码</option>
										<option value="11">营销</option>
								    </select>
								 </td> 
                                <td class="fr"style="width: 100px;">模板内容</td>
								<td>
								   <input type="text" id="content" class="form-control" style="width: 320px;">
							    </td>
							    <td>
							      <button id="btnquery"
										class="btn btn-sm btn-primary pull-left"
										type="button">查询数据</button>
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
	<script type="text/javascript" src="/js/handler/smstemplate.js"></script>
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
		$("#authResultTimeStart").jeDate(start);
		$("#authResultTimeEnd").jeDate(end);
		
		function del(id){
			layer.confirm("您确定删除该模板吗？", {
				area: ['450px'],
				btn: ['确认','取消'] //按钮
			}, function(){
				window.location.href="/smstemplate/delete/"+id+"";
			}, function(){
				layer.closeAll('dialog');
			});
		}
	</script>
</body>
</html>
