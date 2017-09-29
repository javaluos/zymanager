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
				          短信发送记录汇总
					<small>&nbsp;-->&nbsp;
					   通道失败原因分析
					</small>
				</h5>
			</div>
			<div>
				<form method="get" class="form-vertical">
				    <input type="hidden" id="apiAccount" value="${apiAccount}" />
				    <input type="hidden" id="channelId" value="${channelId}" />
				    <input type="hidden" id="channelName" value="${channelName}" />
				    <input type="hidden" id="dateTime" value="${dateTime}" />
				    <input type="hidden" id="smsCategory" value="${smsCategory}" />
				    <input type="hidden" id="sTime" value="${sTime}" />
                    <input type="hidden" id="eTime" value="${eTime}" />
					<table class="table noborder">
						<tbody>
							<tr class="noline">
								<td class="fr" style="width: 100px;">客户名称:</td>
							    <td class="online">
							       <input type="text" id="businessName" class="form-control" style="width: 180px;"
                                         data-toggle="popover" data-placement="top" data-content=""
                                         readonly="readonly" value="${businessName}" />
							    </td>
								<td class="fr" style="width: 100px;">客户账号:</td>
								<td>
                                    <input type="text" id="merchantPhone" class="form-control" style="width: 120px;"
                                         data-toggle="popover" data-placement="top" data-content=""
                                         readonly="readonly" value="${merchantPhone}" />
								</td>
								<td class="fr" style="width: 100px;">通道名称:</td>
								<td>
                                    <input type="text" id="channelName" class="form-control" style="width: 150px;"
                                         data-toggle="popover" data-placement="top" data-content=""
                                         readonly="readonly" value="${channelName}" />
								</td>
                                <td class="fr" style="width: 100px;">日期:</td>
                                <td class="online"  style="width: 30%">
                                  <li style="display: inline-block;">
                                    <input class="form-control" id="starttime" style="width: 150px;"
                                        readonly="readonly" value="${startTime}">
                                    <label>&nbsp;至&nbsp;</label>
                                    <input class="form-control" id="endtime" style="width: 150px;"
                                        readonly="readonly" value="${endTime}">
                                  </li>
                                </td>
								<td style="width: 180px;">
								    <button id="btnback"
										class="btn btn-sm btn-primary pull-left"
										type="button">返回</button>
								</td>
								<td style="width: 180px;">
                                    <button id="btnexport"
                                        class="btn btn-sm btn-success pull-left"
                                        type="button">导出数据</button>
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
	<script type="text/javascript" src="/js/handler/view_fail_detail.js?t=2017061602"></script>
	<script type="text/javascript">
	</script>
</body>
</html>
