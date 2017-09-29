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
	<div class="col-sm-12" style="border-bottom: 1px solid #e5e6e7;margin-bottom: 10px;">
		<div class="ibox">
			<div class="ibox-title">
				<h5>
					监控信息
					<small>&nbsp;-->&nbsp;
					  通道余额监控
					</small>
				</h5>
			</div>
			<div>
				<form method="get" class="form-vertical">
                
					<table class="table noborder">
						<tbody>
							<tr class="noline">
							
								<td class="fr" style="width: 90px;">通道名称:</td>
								<td style="width: 30%"><input type="text" id="channelName" class="form-control" style="width: 150px;"></td>
								
								<td class="fr" style="width: 90px;">通道ID:</td>
								<td style="width: 30%"><input type="text" id="channelId" class="form-control" style="width: 150px;"></td>
														
                                <td class="fr" style="width: 20px;"></td>
								<td style="width: 10%">
									<button id="btnquery"
										class="btn btn-sm btn-primary pull-left"
										type="button">查询数据</button></td>
								</td>
								
								<td class="fr" style="width: 5px;"></td>
								<td style="width: 25%">
                                    <button id="btnsetting"
                                        class="btn btn-sm btn-primary pull-left"
                                        type="button">告警通知设置</button>
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
	<script type="text/javascript" src="/js/handler/chnbalmonitor.js"></script>
	<script type="text/javascript">
    function deleteMonitor(channelId, channelName){
        //询问框
        layer.confirm("您确定要取消通道【"+channelName+"】的余额监控吗？", {
            btn: ['确认','取消'] //按钮
        }, function(){
            $.ajax({
                type: "POST",
                url: "/channel_balance/delete_monitor",
                data: {"channelId" : channelId},
                dataType: "json",
                success: function(data){
                    if(data){
                        layer.alert("通道：【"+channelName+"】取消余额监控成功！");
                    }else{
                        layer.alert("通道：【"+channelName+"】取消余额监控失败！");
                    }
                    $("#btnquery").click();
                }
            });
        });
    }
	</script>
</body>
</html>
