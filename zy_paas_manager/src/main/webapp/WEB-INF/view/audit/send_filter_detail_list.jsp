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
							    <td class="fr" style="width: 90px;">接收手机:</td>
								<td style="width: 25%">
									<input type="text" id="receiveMobile" class="form-control" style="width: 150px;">
									<input type="hidden" id="apiAccount" value="${apiAccount}" class="form-control" style="width: 150px;">
								    <input type="hidden" id="smsContent" value="${smsContent}" class="form-control" style="width: 150px;">
								    <input type="hidden" id="blackContent" value="${blackContent}" class="form-control" style="width: 150px;">
								</td>
								
								<td class="fr" style="width: 100px;margin-left: -19px;">时间:</td>
							    <td class="online"  style="width: 30%">
								  <li style="display: inline-block;">
									<input class="form-control" id="createTimeStart" style="width: 150px;" >
									<label>&nbsp;至&nbsp;</label> 
									<input
										class="form-control" id="createTimeEnd" style="width: 150px;">
								  </li>
							    </td>
							    
							    <td class="fr" style="width: 20px;"></td>
								<td class="online"  style="width: 50%">
								   <button id="btnquery" class="btn btn-sm btn-primary pull-left" type="button">查询数据</button>
								   <button id="btnback" class="btn btn-sm btn-primary pull-left" style="margin-left: 40px;" type="button">返回</button>
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
	<script type="text/javascript" src="/js/handler/sendFilterDetail.js"></script>
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
