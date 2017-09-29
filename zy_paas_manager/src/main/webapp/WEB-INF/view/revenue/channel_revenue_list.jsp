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
					营收分析<small>&nbsp;-->&nbsp;通道营收分析</small>
				</h5>
			</div>
			<div>
				<form method="get" class="form-vertical">

					<table class="noborder">
						<tbody>
							<tr class="noline">
							
								<td class="fr" style="width:5%">通道名称:</td>
								<td class="online" style="width:10%">
									<input type="text" id="channelName" value="${smsDailyRevenueStatistics.channelName}" class="form-control" style="width:170px;">
								</td>
								
								<td class="fr" style="width:5%">通道编号:</td>
								<td style="width:10%">
								    <input type="text" value="${smsDailyRevenueStatistics.channelMainCode}" id="channelMainCode" class="form-control" style="width: 150px;">
								</td>
								
								<td class="fr" style="width:5%">日期:</td>
								<td class="online">
								    <input class="form-control layer-date" value="${datetimeStart}"
									     id="dateTimeStart"  style="width: 150px;">
									<label>&nbsp;至&nbsp;</label>
									<input class="form-control layer-date" value="${datetimeEnd}"
									     id="dateTimeEnd"  style="width: 150px;">
								</td>
								
								<td style="width:5% "><button id="btnexport"
										class="btn btn-sm btn-success pull-left"
										type="button">导出数据</button></td>
							   <td style="width:20% "></td>
								
							</tr>
							<tr class="noline">
							
							   <td class="fr" style="width:5%">通道ID:</td>
							   <td style="width:10%">
							     <input type="text" value="${smsDailyRevenueStatistics.channelId}" id="channelId" class="form-control" style="width: 170px;">
							   </td>
								
								
								<td class="fr" style="width: 90px;"></td>
								<td></td>
						
								<td class="fr" style="width: 100px;"></td>
								<td class="online"></td>
		
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
	<script type="text/javascript" src="/js/handler/channelRevenue.js"></script>
	
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
	

	$("#dateTimeStart").jeDate({
	    isinitVal:false,
	    ishmsVal:false,
	    minDate: '',
	    maxDate: $.nowDate(0),
	    format:"YYYY-MM-DD",
	    zIndex:3000,
	})
	$("#dateTimeEnd").jeDate({
	    isinitVal:false,
	    ishmsVal:false,
	    minDate: '',
	    maxDate: $.nowDate(0),
	    format:"YYYY-MM-DD",
	    zIndex:3000,
	})
	</script>
</body>
</html>
