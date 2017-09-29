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
					短信通道组管理<small>&nbsp;-->&nbsp; 添加通道组</small>
				</h5>
				</div>
				<div>
					<input id="group_id" type="hidden" value="${smsChannelGroup.id}" />
					<form id="save_obj_form" method="post">
					 <div style="padding-left: 10px;">
						<table class="noborder">
							<tbody>
								<tr class="noline">
									<td class="fr" style="width: 5%;">通道组名称：</td>
									<td class="online" style="width: 10%"><input name="groupName" tag="通道组" type="text" value="${smsChannelGroup.groupName}" class="form-control" style="width: 250px;">
									</td>
									<td class="fr" style="width: 4%;"></td>
									<td>
										<button  class="btn btn-sm btn-primary pull-left" type="button" onclick="history.go(-1);">返回</button>
									</td>
								</tr>
							</tbody>
						</table>
					 </div>
					</form>
					 <form id="save_array_form" method="post">
						<div style="padding-left: 10px;margin-top:20px;">
						  <table id="channeltab" class="noborder" >
						    <tbody>
					 	<c:choose>
					 		<c:when test="${empty smsChannelGroup}">
								<tr class="noline" id="index_0">
									<td class="fr" style="width: 5%;">通道:</td>
									<td style="width: 20%">
										<select name="channelId" tag="请选择一个通道" data-placeholder="请选择一个通道" class="chosen-select" tabindex="2">
											<option value="">请选择一个通道</option>
											<c:forEach var="channel" items="${channelSelect}">
												<option value="${channel.CHANNEL_ID}">${channel.CHANNEL_NAME}&nbsp;(${channel.STATUS})</option>
											</c:forEach>
										</select>
									</td>
									<td class="fr" style="width: 5%;text-align:center">评分:</td>
									<td class="online" style="width: 8%">
										<input type="text" tag="请输入评分" number="评分必须为正整数"  maxlength="3" size="3" name="channelScore" class="form-control" style="width: 150px;" />
										<td class="fr" style="width: 5%;text-align:center">阀值:</td>
										<td class="online" style="width: 8%">
										<input type="text" tag="请输入阀值" number="阀值必须为正整数"  maxlength="9" size="9" name="thresholdValue" class="form-control" style="width: 150px;" />
										<td>
											<a href="#" onclick="deltr(0)">删除</a>
										</td>
								</tr>
					 		</c:when>
					 		<c:otherwise>
					 			<c:forEach var="item" items="${smsChannelGroup.smsChannelGroupBindList}" varStatus="status">
									<tr class="noline" id="index_${status.index}">
										<td class="fr" style="width: 5%;">通道:</td>
										<td style="width: 20%">
											<select name="channelId" tag="请选择一个通道" data-placeholder="请选择一个通道" value="${item.channelId}" class="chosen-select" tabindex="2">
												<option value="">请选择一个通道</option>
												<c:forEach var="channel" items="${channelSelect}">
													<option value="${channel.CHANNEL_ID}" ${channel.CHANNEL_ID==item.channelId?'selected':''}>${channel.CHANNEL_NAME}&nbsp;(${channel.STATUS})</option>
												</c:forEach>
											</select>
										</td>
										<td class="fr" style="width: 5%;text-align:center">评分:</td>
										<td class="online" style="width: 8%">
											<input type="text" tag="请输入评分" number="评分必须为正整数" maxlength="3" size="3" name="channelScore" value="${item.channelScore}" class="form-control" style="width: 150px;" />
										<td class="fr" style="width: 5%;text-align:center">阀值:</td>
										<td class="online" style="width: 8%">
											<input type="text" tag="请输入阀值" number="阀值必须为正整数" maxlength="9" size="9" name="thresholdValue" value="${item.thresholdValue}" class="form-control" style="width: 150px;" />
										<td>
											<a href="#" onclick="deltr(${status.index})">删除</a>
										</td>
									</tr>
								</c:forEach>
					 		</c:otherwise>
					 	</c:choose>
						   </tbody>
						  </table>
						</div>
					   </form>
				</div>
				<div style="margin:20px;" class="form-group">
					<div class="col-sm-3 col-sm-offset-3">
						<button class="btn btn-primary" type="button" style="width: 80px;" id="btnAdd" onclick="addtr();">增加通道</button>
						<button class="btn btn-primary" type="button" style="width: 60px;margin-left: 20px;" onclick="save();" id="btnSave">保存</button>
					</div>
				</div>
				</br>
			</div>
		</div>



		<table id="template" class="hidden">
		  <tbody>
			<tr class="noline" id="index_">
				<td class="fr" style="width: 5%;">通道:</td>
				<td style="width: 20%">
					<select name="channelId" tag="请选择一个通道" class="chosen-select" data-placeholder="请选择一个通道" tabindex="2">
						<option value="">请选择一个通道</option>
						<c:forEach var="channel" items="${channelSelect}">
							<option value="${channel.CHANNEL_ID}">${channel.CHANNEL_NAME}&nbsp;(${channel.STATUS})</option>
						</c:forEach>
					</select>
				</td>
				<td class="fr" style="width: 5%;text-align:center">评分:</td>
				<td class="online" style="width: 8%">
					<input type="text" tag="请输入评分" number="评分必须为正整数" maxlength="3" size="3" name="channelScore" class="form-control" style="width: 150px;">
				</td>
				<td class="fr" style="width: 5%;text-align:center">阀值:</td>
				<td class="online" style="width: 8%">
					<input type="text" tag="请输入阀值" number="阀值必须为正整数" maxlength="9" size="9"  name="thresholdValue" class="form-control" style="width: 150px;">
				</td>
				<td>
					<a href="#">删除</a>
				</td>
			</tr>
		  </tbody>
		</table>
	</body>
	<script type="text/javascript">

		/*$.fn.serializeObject = function()
		{
		   var o = {};
		   var a = this.serializeArray();
		   $.each(a, function() {
		       if (o[this.name]) {
		           if (!o[this.name].push) {
		               o[this.name] = [o[this.name]];
		           }
		           o[this.name].push(this.value || '');
		       } else {
		           o[this.name] = this.value || '';
		       }
		   });
		   return o;
		};  */

		/*var config = {
			'.chosen-select': {},
			'.chosen-select-deselect': { allow_single_deselect: true },
			'.chosen-select-no-single': { disable_search_threshold: 10 },
			'.chosen-select-no-results': { no_results_text: 'Oops, nothing found!' },
			'.chosen-select-rtl': { rtl: true },
			'.chosen-select-width': { width: '100%' }
		}
		$('.chosen-select').chosen(config);*/
		initChosen(-1);
		//$(".chosen-select").trigger('chosen:updated');
		//class="chosen-select"
		function initChosen(row){
			let chosen;
			if(row == -1){
				chosen = $("#channeltab .chosen-select");
			}else{
				chosen = $("#channeltab .chosen-select").eq(row);
			}
			chosen.chosen({
			    no_results_text : "没有找到",
			    search_contains : true,
			    allow_single_deselect : false,
			    width: '250px'
			});
		}

		var rows = 0;
		function addtr() {
			let tem = $("#template>tbody>tr");
			let tr = tem.clone();
			rows++;
			tr.attr("id","index_"+rows);
			let a = tr.children("td").last().find("a");
			a.attr("onclick","deltr("+rows+");");
			$("#channeltab").append(tr);
			let len = $("#channeltab>tbody tr").length;
			initChosen(len-1);
			/*a[0].addEventListener("click",()=>{
				deltr(rows);
			});*/
			//$(".chosen-select").trigger('chosen:updated');
		}

		function deltr(index) {
			let len = $("#channeltab>tbody tr").length;
			if(len<=1){
				layer.alert('至少保留一个通道组');
				return;
			}
			$("#index_" + index).remove();
		}

		function isEmpty(data){
			if(data == '' || data == null || data == undefined){
				return true;
			}
			return false;
		}

		function save(){
			//document.getElementById("save_form").submit();
			let formData = $('#save_obj_form').serializeArray();
			let formDataArray = $('#save_array_form').serializeArray();
			let smsChannelGroup = new Object();
			let flag = true;
			for(let i=0;i<formData.length;i++){
				let data = formData[i];
				if(isEmpty(data.value)){
					// layer.alert('必填选项为空');
					let element = document.getElementsByName(data.name)[0];
					element.focus();
					layer.tips("请输入" + element.getAttribute("tag") + "名称", element);
					flag = false;
					return;
				}
				smsChannelGroup[data.name] = data.value;
			}
			if(!flag){
				return;
			}
			// 每行有几个name
			let colName = 3;
			let smsChannelGroupBindList = new Array();
			let len = formDataArray.length / colName;
			for(let i = 0;i < len ;i++){
				let channel = formDataArray.splice(0,colName);
				let channelObj = {};
				for(let j = 0;j < channel.length ;j++){
					let c = channel[j];
					let element = document.getElementsByName(c.name)[i];
					if(isEmpty(c.value) && flag){
						let tipMsg = element.getAttribute("tag");
						if(element.className=='chosen-select'){
						    element = $(element).next()[0];
						}
						element.focus();
						layer.tips(tipMsg, element);
						flag = false;
						return;
					}
					// 验证数字
					let numTipMsg = element.getAttribute("number");
					if(!isEmpty(numTipMsg) && flag){
						let numberRegExp = new RegExp("^\\d+$");
						if(!numberRegExp.test(c.value)){
							layer.tips(numTipMsg, element);
							flag = false;
							return;
						}else{
							if(c.name == 'channelScore' && c.value > 100){
								layer.tips('评分不能超过100分', element);
								flag = false;
								return;
							}
						}
					}
					channelObj[c.name] = c.value;
				}
				smsChannelGroupBindList.push(channelObj);
			}
			if(!flag){
				return;
			}

			smsChannelGroup.smsChannelGroupBindList = smsChannelGroupBindList;
			let group_id = $("#group_id").val();
			if(!isEmpty(group_id)){
				smsChannelGroup.id = group_id;
			}
			/*废弃//每行几个
			let rows = 3;
		    //截取开始
			let start = 0;
			//从开始到中间截取到object 类型
			let middle = 1;
			//从中间截取到最后Array 类型
			let end = ($("#channeltab>tbody tr").length) * 3 + middle;
			console.info(end);
			// 截取form 非数组
			let formDataObj = data.splice(start , middle) ;
			// 截取form 数组集合
			let formDataArray = data.splice(start , end) ;废弃*/
			$("#btnSave").attr("disabled", true);
			$.ajax({
				type: "POST",
		   		url: "/channel_group/save_group_channel",
		   		data: JSON.stringify(smsChannelGroup),
		   		dataType: "json",
		   		contentType: "application/json",
			   	success:function(data){
			   		if(data == '1'){
			   			layer.alert("保存通道组成功");
			   		 	setTimeout('window.location.href="/channel_group/to_list"' , 1000);
			   		}else if(data == '2'){
			   			layer.alert("通道组名称已重复");
			   		}else if(data == '3'){
			   			layer.alert("保存通道组失败");
			   		}else{
			   			layer.alert("保存通道组异常");
			   		}
			   		$("#btnSave").attr("disabled", false);
			   	}
			});
		}
	</script>

</html>