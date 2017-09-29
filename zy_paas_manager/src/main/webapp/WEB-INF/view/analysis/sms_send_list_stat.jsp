<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
<head>
<title>Paas运营管理平台</title>
<jsp:include page="../comm/plugin.jsp" />
<style type="text/css">

</style>
</head>

<body>

	<!-- QueryForm -->
	<div class="col-sm-10" style="border-bottom: 1px solid #e5e6e7; padding-bottom: -20px;margin-bottom: 10px;">
		<div class="ibox">
			<div class="ibox-title">
				<h5>
				           详单查询
					<small>&nbsp;-->&nbsp;
					   短信发送记录
					</small>
				</h5>
			</div>
			<div>
				<form method="get" class="form-vertical">

					<table class="table noborder">
						<tbody>
							<tr class="noline">
							
								<td class="fr" style="width: 90px;">客户账号:</td>
								<td style="width: 15%">
									<input type="text" id="merchantPhone" class="form-control" style="width: 150px;"
									 data-toggle="popover" data-placement="top" data-content="">
								</td>
								<td class="fr" style="width: 90px;">通道&nbsp;&nbsp;ID:</td>
								<td style="width: 15%">
									<input type="text" id="channelId" class="form-control" style="width: 150px;"
									 data-toggle="popover" data-placement="top" data-content="">
								</td>
								<td class="fr" style="width: 90px;">运营商:</td>
								<td style="width: 15%">
								    <select class="form-control" id="operator" style="width: 150px;">
								        <option></option>
								         <option value="yd">移动</option>
								         <option value="dx">电信</option>
								         <option value="lt">联通</option>
								    </select>
                                </td>
                                <td class="fr" style="width: 90px;">发送时间:</td>
							    <td class="online"  style="width: 30%">
								  <li style="display: inline-block;">
									<input class="form-control" id="starttime" style="width: 150px;" value="${datetimeStart}">
									<label>&nbsp;至&nbsp;</label> 
									<input
										class="form-control" id="endtime" style="width: 148px;"
                                        value="${datetimeEnd}">
								  </li>
							    </td>

                                <td style="width: 25%">
                                    <button id="btnexport"
                                        class="btn btn-sm btn-success"
                                        type="button">导出数据</button>
                                </td>
                                <td>
                                	 <button id="stopPushbtn"
                                        class="btn btn-sm btn-primary"
                                        type="button" onclick="stopPush()">停止推送</button>
                                </td>
							</tr>
							<tr class="noline">
							   	<td class="fr" style="width: 90px;">接收手机:</td>
								<td style="width: 15%">
									<input type="text" id="receiveMobile" class="form-control" style="width: 150px;"
									 data-toggle="popover" data-placement="top" data-content="">
								</td>
							    <td class="fr" style="width: 90px;">发送状态:</td>
								<td style="width: 15%">
									<select class="form-control m-b" id="status" style="width: 150px">
										<option value=""></option>
										<c:forEach var="status" items="${statusList }" >
											<option value="${status.type }">${status.name }</option>
										</c:forEach>
									</select>
								</td>
							    <td class="fr" style="width: 90px;">短信类型:</td>
								<td style="width: 15%">
									<select class="form-control m-b" id="category" style="width: 150px">
										<option value=""></option>
										<c:forEach var="category" items="${categoryList }" >
											<option value="${category.type }">${category.name }</option>
										</c:forEach>
									</select>
								</td>
								<td class="fr" style="width: 90px;">短信内容:</td>
                                <td style="width: 30%">
                                    <input class="form-control" id="smsContent" style="width: 318px;" value="">
                                </td>
								<td style="width: 100px">
                                    <button id="btnquery"
                                        class="btn btn-sm btn-primary"
                                        type="button">查询数据</button>
                                </td>
								<td style="width: 100px">
                                    <button id="rePushbtn"
                                        class="btn btn-sm btn-primary"
                                        type="button" onclick="rePush('')">重推报告</button>
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
	<script type="text/javascript" src="/js/handler/smssendliststat.js?t=20170522"></script>
	<script type="text/javascript">

		var start = {
            isinitVal:true,
            ishmsVal:false,
            format: 'YYYY-MM-DD hh:mm:ss',
            minDate: '', //设定最小日期为当前日期
            maxDate: $.nowDate(0), //最大日期
            choosefun: function(elem,datas){
                end.minDate = datas; //开始日选好后，重置结束日的最小日期
            }
        };

       var end = {
           isinitVal:true,
           ishmsVal:false,
           format: 'YYYY-MM-DD hh:mm:ss',
           minDate: '', //设定最小日期为当前日期
           maxDate: '', //最大日期
           choosefun: function(elem,datas){
               start.maxDate = datas; //将结束日的初始值设定为开始日的最大日期
           }
       };
       $("#starttime").jeDate(start);
       $("#endtime").jeDate(end);
		
	</script>
</body>
</html>
