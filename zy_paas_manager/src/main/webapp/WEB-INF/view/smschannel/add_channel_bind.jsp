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
	<div class="col-sm-12"
		style="border-bottom: 1px solid #e5e6e7; padding-bottom: -20px; margin-bottom: 10px;">
		<div class="ibox">
			<div class="ibox-title">
				<h5>
					通道绑定管理<small>&nbsp;-->&nbsp; 添加绑定</small>
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
									id="merchantphone" class="form-control" style="width: 250px;">
								</td>
								<td class="fr" style="width: 4%;"></td>
								<td class="fr" style="width: 5%; margin-left: 20px">客户名称</td>
								<td class="online" style="width: 10%"><input type="text"
									id="businessname" class="form-control" style="width: 250px;"
									maxlength="50"></td>
								<td >
									<button id="btnquery" class="btn btn-sm btn-primary pull-left" 
										type="button" onclick="getacclist();">查询数据</button>
								</td>
								<tr></td>
							</tr>
						</tbody>
					</table>

					<h4 style="margin-top: 40px">通道信息</h4>
					<table class="noborder" style="width: 80%">
						<tbody>
							<tr class="noline">
								<td class="fr" style="width: 5%;">通道名称</td>
								<td class="online" style="width: 10%"><input type="text"
									id="channelname" class="form-control" style="width: 250px;">
								</td>
								<td class="fr" style="width: 4%;"></td>
								<td class="fr" style="width: 5%; margin-left: 20px">通道ID</td>
								<td class="online" style="width: 10%"><input type="text"
									id="channelid" class="form-control" style="width: 320px;"
									maxlength="50"></td>
								<td>
								</td>
								<td>
								</td>
							</tr>
							<tr class="noline">
								<td class="fr" style="width: 5%;">通道类型</td>
								<td class="online" style="width: 10%">
									<select class="form-control" id="channeltype" style="width: 250px;">
										<option value=""></option>
										<c:forEach var="channelType" items="${channelTypeList }">
											<option value="${channelType.type }">${channelType.name }</option>
										</c:forEach>
									</select>
								</td>
								<td class="fr" style="width: 4%;"></td>
								<td class="fr" style="width: 5%; margin-left: 20px">落地省份</td>
								<td class="online" style="width: 10%">
									<select class="form-control" id="dtnprovince" style="width: 120px;">
										<option value=""></option>
										<option value="平台商">平台商</option>
										<c:forEach var="province" items="${provinceList }">
											<option value="${province }">${province }</option>
										</c:forEach>
									</select>
									<span style="float:right;margin-top:-28px;margin-right:1px">
                                        <label style="margin-right:5px">通道属性</label>
                                        <select class="form-control" id="channelProperty" style="width: 120px;">
                                            <c:forEach var="channelProperty" items="${channelPropertyList }">
                                                <option value="${channelProperty.value }">${channelProperty.name}</option>
                                            </c:forEach>
                                        </select>
                                    </span>
								</td>
								<td style="width: 5%"><button id="btnquery2"
										class="btn btn-sm btn-primary pull-left" type="button"
										onclick="getchannellist();">查询数据</button>
								</td>
							</tr>
						</tbody>
					</table>
					<h3 style="margin-top: 50px;">绑定信息</h3>
					<div style="padding-left: 10px;">
						<table class="noborder">
							<tbody id="accid">
							</tbody>
						</table>
					</div>
					<div style="padding-left: 10px;">
						<table  class="noborder" style="margin-top:20px;width: 100%">
							<tbody id="channelId">
							</tbody>
						</table>
					</div>
				</div>
				<div style="margin-top:20px;" class="form-group">
                    <div class="col-sm-3 col-sm-offset-3">
                    	<input type="hidden" id="deptName" name="deptName" >
                       <button class="btn btn-primary" type="button" style="width: 80px;" id="btnsave" onclick="dosubmit();">提交绑定</button>
                       <button class="btn btn-primary" type="button" style="width: 60px;margin-left: 20px;" onclick="history.go(-1)" id="btnback">返回</button>
                    </div>
            	</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	function getacclist(){
		var businessname  = $('#businessname').val();
		var merchantphone = $('#merchantphone').val();
		layer.open({
			type: 2, //page层
		  	area: ['950px', '700px'],
		  	title: '选择绑定账号',
			shade: 0.3, //遮罩透明度
		 	moveType: 0, //拖拽风格，0是默认，1是传统拖动
		 	shift: 1, //0-6的动画形式，-1不开启
		  	scrollbar:false,
		  	content:'/channel_bind/to_acc_list?businessname='+businessname+'&merchantphone='+merchantphone+''
	   	});
	}
	function getchannellist(){
		var channelname  = $('#channelname').val();
		var channelid = $('#channelid').val();
		var channeltype = $('#channeltype').val();
		var channelProperty = $('#channelProperty').val();
		var dtnprovince = $('#dtnprovince').val();
		layer.open({
			type: 2, //page层
		  	area: ['950px', '700px'],
		  	title: '选择绑定通道',
			shade: 0.3, //遮罩透明度
		 	moveType: 0, //拖拽风格，0是默认，1是传统拖动
		 	shift: 1, //0-6的动画形式，-1不开启
		  	scrollbar:false,
		  	content:'/channel_bind/to_channel_list?channelname='+channelname+'&channelid='+channelid+'&channeltype='+channeltype+'&dtnprovince='+dtnprovince+'&channelProperty='+channelProperty+''
	   	});
	}
	function delacctr(trid){
		$("#"+trid+"").remove();
	}	
	function delchanneltr(trid){
		$("#"+trid+"").remove();
	}
	function dosubmit(){
		$("#btnsave").attr("disabled", true);
		var accounttr = $("#accid tr").length;
		if(accounttr <= 0){
			layer.alert("绑定的客户账号为空，请选择");
			$("#btnsave").attr("disabled", false);
			return false;
		}
		var channeltr = $("#channelId tr").length;
		if(channeltr <= 1){
			layer.alert("绑定的通道为空，请选择");
			$("#btnsave").attr("disabled", false);
			return false;
		}
		var apiAccount = $("#accid").find("tr:first").attr("id");
		var channels = "";
		var channeltrs = $("#channelId").find("tr");
		for(var i=1;i<channeltrs.length;i++){
			var id = channeltrs.eq(i).attr("id");
			var score = channeltrs.eq(i).find('#score').val();
			if(score.trim() == ''){
				layer.alert("通道评分不能为空");
				$("#btnsave").attr("disabled", false);
				return false;
			}
			if(score > 100){
				layer.alert("通道评分不能大于100");
				$("#btnsave").attr("disabled", false);
				return false;
			}
			if(score < 0){
				layer.alert("通道评分不能小于0");
				$("#btnsave").attr("disabled", false);
				return false;
			}
			var thresholdValue = channeltrs.eq(i).find('#thresholdValue').val();
			if(thresholdValue.trim() == ''){
				layer.alert("通道阀值不能为空");
				$("#btnsave").attr("disabled", false);
				return false;
			}
			channels += (id + "_" + score + "_" + thresholdValue) + ","
		}
		$.ajax({
			type: "POST",
	   		url: "/channel_bind/do_bind_channel",
	   		data: {"apiAccount" : apiAccount, "channels" : channels},
	   		dataType: "json",
		   	success: function(data){
		   		if(data == 'true' || data == true){
		   			layer.alert("通道绑定成功");
		   		 	setTimeout('window.location.href="/channel_bind/to_list"',2000)
		   		}else{
		   			layer.alert("通道绑定出现错误");
		   			$("#btnsave").attr("disabled", false);
		   		}
		   	}
		});
	}
</script>
</html>
