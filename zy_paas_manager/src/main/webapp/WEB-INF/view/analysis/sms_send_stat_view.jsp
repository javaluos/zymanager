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
					   短信发送记录汇总
					</small>
				</h5>
			</div>
			<div>
				<form method="get" class="form-vertical">
                    <input type="hidden" id="apiAccount" value="${apiAccount}" />
                    <input type="hidden" id="queryPhone" value="${queryPhone}" />
                    <input type="hidden" id="queryName" value="${queryName}" />
                    <input type="hidden" id="smsCategory" value="${smsCategory}" />
                    <input type="hidden" id="sTime" value="${sTime}" />
                    <input type="hidden" id="eTime" value="${eTime}" />
					<table class="table noborder">
						<tbody>
							<tr class="noline">
								<td style="width: 80px;vertical-align:middle">客户名称:</td>
								<td style="width: 15%;vertical-align:middle">
									${businessName }
								</td>
								<td style="width: 80px;vertical-align:middle">客户账号:</td>
								<td style="width: 12%;vertical-align:middle">
								    ${merchantPhone }
								</td>
								<td style="width: 80px;vertical-align:middle">发送时间:</td>
                                <td class="online"  style="width: 25%;">
                                  <li style="display: inline-block;">
                                    <input class="form-control" id="starttime" style="width: 120px;" value="${dateTime }">
                                    <label>&nbsp;至&nbsp;</label>
                                    <input
                                        class="form-control" id="endtime" style="width: 120px;"
                                        value="${dateTime }">
                                  </li>
                                </td>
                                <td style="width: 15%">
                                    <button id="btnquery"
                                        class="btn btn-sm btn-primary pull-left"
                                        type="button">查询数据</button>
                                    <button id="back" style="margin-left:20px"
                                        class="btn btn-sm btn-success pull-left"
                                        type="button">返回</button>
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
	<script type="text/javascript" src="/js/handler/smssendstatview.js"></script>
	<script type="text/javascript">
	    var start = {
		    format: 'YYYY-MM-DD',
		    minDate: '', //设定最小日期为当前日期
		    maxDate: $.nowDate(0), //最大日期
		    choosefun: function(elem,datas){
		        end.minDate = datas; //开始日选好后，重置结束日的最小日期
		    }
		};
		var end = {
		    format: 'YYYY-MM-DD',
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
