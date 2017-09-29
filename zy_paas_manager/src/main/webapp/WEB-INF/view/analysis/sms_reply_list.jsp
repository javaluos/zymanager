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
					详单查询<small>&nbsp;-->&nbsp;短信上行记录</small>
				</h5>
			</div>
			<div>
				<form method="get" class="form-vertical">

					<table class="noborder">
						<tbody>
							<tr class="noline">
								<td class="fr" style="width: 100px;">客户账号</td>
								<td style="width:15%">
								    <input type="text" value="${merchantPhone}" id="merchantPhone" class="form-control" style="width: 150px;">
								</td>
								<td class="fr" style="width: 100px;">通道ID</td>
								<td style="width: 15%">
								    <input type="channelId" value="${channelId}" id="channelId" class="form-control" style="width: 150px;">
								</td>
								<td class="fr" style="width: 100px;">接收手机号</td>
								<td style="width:15%">
								    <input type="text" id="mobile"  value="${mobile}" class="form-control" style="width: 150px;">
								</td>
								<td class="fr" style="width: 100px;">上行时间</td>
								<td class="online"><input class="form-control layer-date" value="${starttime}"
									id="starttime"  style="width: 150px;">
									<label>&nbsp;至&nbsp;</label>
									<input class="form-control layer-date" value="${endtime}"
									id="endtime"  style="width: 150px;">
								</td>
								
							</tr>
							<tr class="noline">
							     
							    <td class="fr">上行接入号</td>
								<td><input type="text" id=ext_number  value="${ext_number}" class="form-control" style="width: 150px;">
								</td>
								<td class="fr">上行内容</td>
								<td><input type="text" id="content"  value="${content}" class="form-control" style="width: 150px;">
								</td>
								<td class="fr">上行状态</td>
								<td><select class="form-control" id="status" style="width: 150px;">
										<option value="-1"></option>
										<option value="1" <c:if test="${status==1}">selected="true"</c:if>>推送成功</option>
										<option value="2" <c:if test="${status==0}">selected="true"</c:if>>推送失败</option>
										<option value="0" <c:if test="${status==2}">selected="true"</c:if>>未推送</option>
								</select>
								</td>
						        <td class="fr" ></td>
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
	<script type="text/javascript" src="/js/handler/smsreply.js"></script>
	<script type="text/javascript">
	
	$("#starttime").jeDate({
	    isinitVal:false,
	    ishmsVal:false,
	    minDate: '',
	    maxDate: $.nowDate(0),
	    format:"YYYY-MM-DD hh:mm:ss",
	    zIndex:3000,
	})
	$("#endtime").jeDate({
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
