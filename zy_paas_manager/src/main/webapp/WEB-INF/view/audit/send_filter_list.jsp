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
					   短信审核
					</small>
				</h5>
			</div>
			<div>
				<form method="get" class="form-vertical">
                
					<table class="table noborder">
						<tbody>
							<tr class="noline">
							    <td class="fr" style="width: 90px;">客户名称:</td>
								<td style="width: 35%"><input type="text" id="businessName" class="form-control" style="width: 320px;">
								</td>
								
								<td class="fr" style="width: 90px;">客户账号:</td>
								<td style="width: 20%"><input type="text" id="merchantPhone" class="form-control" style="width: 150px;">
								</td>
								
								<td class="fr" style="width: 90px;">短信内容:</td>
								 <td>
								   <input type="text" id="smsContent" class="form-control" style="width: 200px;">
								 </td>
								
						        <td class="fr" style="width: 90px;">敏感词:</td>
								<td style="width: 20%">
								   <input type="text" id="blackContent" class="form-control" style="width: 150px;">
								</td>
																
							</tr>
							
							<tr class="noline">
							    <td class="fr" style="width: 100px;">提交时间:&nbsp;&nbsp;</td>
							    <td class="online"  style="width: 35%">
								  <li style="display: inline-block;">
									<input class="form-control" id=createTimeStart style="width: 150px;" >
									<label>&nbsp;至&nbsp;</label> 
									<input
										class="form-control" id="createTimeEnd" style="width: 150px;">
								  </li>
							    </td>
								 
								<td class="fr" style="width: 90px;">短信类型:</td>
								<td style="width: 20%">
								   <select class="form-control m-b" id="smsCategory" style="width: 150px;">
										<option value="0"></option>
										<option value="8">短信通知</option>
										<option value="9">短信验证码</option>
										<option value="11">营销短信</option>
								    </select>
								</td>
								
								<td class="fr" style="width: 90px;">排序规则:</td>
								<td style="width: 20%">
								   <select class="form-control m-b" id="orderRule" style="width: 200px;">
										<option value="0">按条数倒序</option>
										<option value="1">按条数升序</option>
								    </select>
								</td>

                                <td class="fr" style="width: 20px;"></td>
								<td>
								   <button id="btnquery" class="btn btn-sm btn-primary pull-left" type="button">查询数据</button>
							    </td>
							</tr>
							
							
						
						    <tr class="noline">
							    <td class="fr" style="width: 90px;">
							        <input id="wordAllCheck" type="checkbox" onClick="selectAllDels()" name="AllCheck" on>所有全选
							    </td>
								<td style="width: 20%">
							        <button id="batchPass" onclick="batchHandle(2)" class="btn btn-sm btn-primary pull-left" type="button">批量通过</button>
								    <button id="batchReject" onclick="batchHandle(5)" class="btn btn-sm btn-primary pull-left" style="margin-left: 40px;" type="button">批量拒绝</button>
								</td>
								
								<td class="fr" style="width: 90px;"></td>
								<td style="width: 20%">
								</td>
								
								<td class="fr" style="width: 90px;"></td>
								<td></td>
								
						        <td class="fr" style="width: 90px;"></td>
								<td style="width: 20%"></td>
																
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
	<script type="text/javascript" src="/js/handler/sendFilter.js"></script>
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
		$("#createTimeStart").jeDate(start);
		$("#createTimeEnd").jeDate(end);
		
	</script>
</body>
</html>
