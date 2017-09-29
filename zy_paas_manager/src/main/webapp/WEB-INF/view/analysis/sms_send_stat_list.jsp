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
                    <input type="hidden" id="today" value="${today }" />
					<table class="table noborder">
						<tbody>
							<tr class="noline">
								<td class="fr" style="width: 80px;">客户名称:</td>
								<td style="width: 15%">
									<input type="text" id="businessName" class="form-control" style="width: 260px;"
									 data-toggle="popover" data-placement="top" data-content="" value="${businessName}">
								</td>
								<td class="fr" style="width: 80px;">短信类型:</td>
								<td style="width: 12%">
									<select class="form-control m-b" id="category" style="width: 150px">
                                        <option value=""></option>
                                        <c:forEach var="category" items="${categoryList }" >
                                            <option value="${category.type }" <c:if test="${category.type == smsCategory}">checked="checked"</c:if>>${category.name }</option>
                                        </c:forEach>
                                    </select>
								</td>
								<td class="fr" style="width: 80px;">发送时间:</td>
                                <td class="online"  style="width: 25%">
                                  <li style="display: inline-block;">
                                    <input class="form-control" id="starttime" style="width: 150px;" value="${datetimeStart}">
                                    <label>&nbsp;至&nbsp;</label>
                                    <input
                                        class="form-control" id="endtime" style="width: 150px;"
                                        value="${datetimeEnd}">
                                  </li>
                                </td>
                                 <td style="width: 15%">
                                 <button id="btnexport" style="width: 80px"
                                        class="btn btn-sm btn-success pull-left"
                                        type="button">导出数据</button>
                                   
                                  </td>
                            </tr>
                            <tr class="noline">
                                <td class="fr" style="width: 80px;">客户账号:</td>
                                <td style="width: 5%">

                                    <input type="text" id="merchantPhone" class="form-control" style="width: 260px;"
									 data-toggle="popover" data-placement="top" data-content="" value="${merchantPhone}">
                                </td>
                                <td class="fr" style="width: 80px;">排序字段:</td>
                                <td style="width: 5%">
                                    <select class="form-control m-b" id="sortcolumn" style="width: 150px">
                                        <option value="0">发送条数</option>
                                        <option value="1">成功条数</option>
                                        <option value="2">计费条数</option>
                                        <option value="3">失败条数</option>
                                        <option value="4">未知条数</option>
                                        <option value="5">成功率</option>
                                        <option value="6">失败率</option>
                                        <option value="7">未知率</option>
                                    </select>
                                </td>
                                <td class="fr" style="width: 80px;">排序规则:</td>
                                <td style="width: 5%">
                                    <select class="form-control m-b" id="sorttype" style="width: 150px">
                                        <option value="0">升序排序</option>
                                        <option value="1" selected="selected">降序排序</option>
                                    </select>
                                </td>
                                <td style="width: 15%">
                                     <button id="btnquery" style="width: 80px"
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
	<script type="text/javascript" src="/js/handler/smssendstat.js"></script>
	<script type="text/javascript">
       var start = {
            isinitVal:true,
            initAddVal:[0],
            ishmsVal:false,
            format: 'YYYY-MM-DD hh:mm:ss',
            minDate: '', //设定最小日期为当前日期
            maxDate: $.nowDate(0), //最大日期
            choosefun: function(elem,datas){
                var today = $("#today").val().substring(0,10);
                if(datas.substring(0,10) != today){
                    start.format='YYYY-MM-DD';
                    $("#starttime").val(datas.substring(0,10));
                }else{
                    start.format='YYYY-MM-DD hh:mm:ss';
                    $("#starttime").val(today + " 00:00:00");
                }
                end.minDate = datas; //开始日选好后，重置结束日的最小日期
            }
        };

       var end = {
           isinitVal:true,
           initAddVal:[0],
           ishmsVal:false,
           format: 'YYYY-MM-DD hh:mm:ss',
           minDate: '', //设定最小日期为当前日期
           maxDate: '', //最大日期
           choosefun: function(elem,datas){
               var today = $("#today").val().substring(0,10);
               if(datas.substring(0,10) != today){
                   start.format='YYYY-MM-DD';
                   $("#endtime").val(datas.substring(0,10));
               }else{
                   start.format='YYYY-MM-DD hh:mm:ss';
                   $("#endtime").val(today + " 23:59:59");
               }
               start.maxDate = datas; //将结束日的初始值设定为开始日的最大日期
           }
       };
       $("#starttime").jeDate(start);
       $("#endtime").jeDate(end);

	</script>
</body>
</html>
