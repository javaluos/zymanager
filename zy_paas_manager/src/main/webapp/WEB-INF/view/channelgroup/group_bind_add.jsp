<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>

	<head>
		<link rel="stylesheet" type="text/css" href="/css/hplus/bootstrap.min.css" />
		<jsp:include page="../comm/plugin.jsp" />
		<link rel="stylesheet" href="/css/chosen/chosen.min.css">
		<script type="text/javascript" src="/js/jquery-1.11.0.js"></script>
		<script type="text/javascript" src="/js/chosen/chosen.jquery.min.js"></script>
		<style>
			.online input, label {}
		</style>
		<title>Paas运营管理平台</title>
	</head>
	<body>
		

	<!-- QueryForm -->
	<div class="col-sm-12" style="border-bottom: 1px solid #e5e6e7; padding-bottom: -20px; margin-bottom: 10px;">
			<div class="ibox">
				<div class="ibox-title">
					<h5>
						通道组绑定管理<small>&nbsp;-->&nbsp; 添加绑定</small>
					</h5>
				</div>
				<div>
					<div style="padding-left: 10px;">
						<h3>绑定信息确认</h3>
						<h4 style="margin-top: 20px">客户账号信息</h4>
						<table class="noborder">
							<tbody>
								<tr class="noline">
									<td class="fr" style="width: 5%;">客户账号</td>
									<td class="online" style="width: 10%"><input type="text"
										id="merchant_phone" class="form-control" style="width: 250px;">
									</td>
									<td class="fr" style="width: 4%;"></td>
									<td class="fr" style="width: 5%; margin-left: 20px">客户名称</td>
									<td class="online" style="width: 10%"><input type="text"
										id="business_name" class="form-control" style="width: 250px;"
										maxlength="50"></td>
									<td >
										<button id="btnquery" class="btn btn-sm btn-primary pull-left" 
											type="button" onclick="getacclist();">查询数据</button>
									</td>
								</tr>
							</tbody>
						</table>
						
						<h3 style="margin-top: 50px;">绑定信息</h3>
						<form id="sava_group_bind" method="post" action="/channel_group/save_bind_group_channel">
							<input name="id" type="hidden" value="${merchantChannelGroupBind.id}" />
						<div style="padding-left: 10px;">
							<table class="noborder">
								<tbody id="accid">
									<c:if test="${not empty merchantChannelGroupBind}">
										<tr id='${merchantChannelGroupBind.apiAccount}' class='noline'>
											<td class='fr' style='width: 12%;'>客户账号：</td>
											<td class='online' style='width: 20%'>${merchantChannelGroupBind.merchantPhone}</td>		
											<td class='fr' style='width: 12%;'>客户名称：</td>	
											<td class='online' style='width: 25%'>
												${ merchantChannelGroupBind.businessName}
												<input type='hidden' name='apiAccount' value='${merchantChannelGroupBind.apiAccount}'/>
											</td>
										</tr>
									</c:if>
								</tbody>
							</table>
						</div>
						<div style="padding-left: 10px;">
							<table  class="noborder" style="margin-top:20px;width: 100%">
								<tbody id="channel_group_bind">
									<div style="padding-left: 10px;margin-top:20px;">
										<table id="channeltab" class="noborder">
											<tbody>
												<tr class="noline">
													<td class="fr" style="width: 30%;">移动通道组:</td>
													<td style="width: 70%">
														<select id="group_yd" name="groupYd" data-placeholder="请选择一个通道组" class="chosen-select" tabindex="2">
															<option value="">无通道组</option>
															<c:forEach var="group" items="${channelGroupSelect}">
																<option value="${group.ID}" ${group.ID==merchantChannelGroupBind.groupYd?'selected':''}>${group.GROUP_NAME}</option>
															</c:forEach>
														</select>
													</td>
												</tr>
												<tr class="noline">
													<td class="fr" style="width: 30%;">联通通道组:</td>
													<td style="width: 70%">
														<select id="group_lt" name="groupLt" data-placeholder="请选择一个通道组" class="chosen-select" tabindex="2">
															<option value="">无通道组</option>
															<c:forEach var="group" items="${channelGroupSelect}">
																<option value="${group.ID}" ${group.ID==merchantChannelGroupBind.groupLt?'selected':''}>${group.GROUP_NAME}</option>
															</c:forEach>
														</select>
													</td>
												</tr>
												<tr class="noline">
													<td class="fr" style="width: 30%;">电信通道组:</td>
													<td style="width: 70%">
														<select id="group_dx" name="groupDx" data-placeholder="请选择一个通道组" class="chosen-select" tabindex="2">
															<option value="">无通道组</option>
															<c:forEach var="group" items="${channelGroupSelect}">
																<option value="${group.ID}" ${group.ID==merchantChannelGroupBind.groupDx?'selected':''}>${group.GROUP_NAME}</option>
															</c:forEach>
														</select>
													</td>
												</tr>
											</tbody>
										</table>
									</div>
								</tbody>
							</table>
						</div>
					    </form>	
					</div>
				
					<div style="margin-top:20px;" class="form-group">
	                    <div class="col-sm-3 col-sm-offset-3">
	                       <button class="btn btn-primary" type="button" style="width: 80px;" id="btnSave" onclick="saveGroupBind();">提交绑定</button>
	                       <button class="btn btn-primary" type="button" style="width: 60px;margin-left: 20px;" onclick="history.go(-1)" id="btnback">返回</button>
	                    </div>
	            	</div>
	            	</br>
				</div>
			</div>
		</div>
</body>
<script type="text/javascript">
	$(".chosen-select").chosen({
			    no_results_text : "没有找到",
			    search_contains : true, 
			    allow_single_deselect : false,
			    width: '250px'
	});
	function getacclist(){
		var businessName  = $('#business_name').val();
		var merchantPhone = $('#merchant_phone').val();
		layer.open({
			type: 2, //page层
		  	area: ['950px', '700px'],
		  	title: '选择绑定账号',
			shade: 0.3, //遮罩透明度
		 	moveType: 0, //拖拽风格，0是默认，1是传统拖动
		 	shift: 1, //0-6的动画形式，-1不开启
		  	scrollbar:false,
		  	content:'/channel_group/to_acc_list?businessName='+businessName+'&merchantPhone='+merchantPhone
	   	});
	}
	function saveGroupBind(){
		// $("#sava_group_bind").submit();
		if(validate()){
			$("#btnSave").attr("disabled", true);
			$.ajax({
				type: 'post', // 提交方式 get/post
				url: '/channel_group/save_bind_group_channel', 
				data: $("#sava_group_bind").serialize(),
				success: function(data) { 
					if(data == 'true'){
						layer.alert("用户绑定通道组成功");
				   		setTimeout('window.location.href="/channel_group/bind_list"',500);
					}else{
						layer.alert("用户绑定通道组错误");
			   			$("#btnSave").attr("disabled", false);
					}
				}
			});
		}
	}
	
	function validate(){
		if($("#accid").has('tr').length == 0){
			layer.alert('请绑定一个用户');
			return false;
		}
		if(isEmpty($("#group_yd").val()) && isEmpty($("#group_lt").val()) && isEmpty($("#group_dx").val())){
			layer.alert('至少选择一个通道组');
			return false;
		}
		return true;
	}
	
	function isEmpty(data){
		if(data == '' || data == null || data == undefined){
			return true;
		} 
		return false;
	}
</script>

</html>