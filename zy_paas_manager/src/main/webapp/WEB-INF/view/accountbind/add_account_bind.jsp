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
				<h3>账号-客户信息确定</h3>
			</div>
			<div>
				<div style="padding-left: 10px;">
					<table class="noborder">
						<tbody>
							<tr class="noline">
								<td class="fr" style="width: 5%;">真实姓名：</td>
								<td class="online" style="width: 10%"><input type="text"
									id="fullname" class="form-control" style="width: 250px;">
								</td>
								<td>
									<button class="btn btn-sm btn-primary pull-left" id="queryuser"
										type="button">查询数据</button>
								</td>
								<td></td>
							</tr>
						</tbody>
					</table>
				</div>

				<!-- QueryData -->
				<div id="userdata" class="dataTables_wrapper form-inline" role="grid" style="margin-left:20px;width: 59%">
					<!-- <div id="DataTables_Table_0_wrapper1"
						class="dataTables_wrapper form-inline" role="grid">
						<table
							class="table table-striped table-bordered table-hover dataTables-example dataTable"
							id="DataTables_Table_01"
							aria-describedby="DataTables_Table_0_info">
							<tbody id="userdata">

							</tbody>
						</table>
					</div> -->
				</div>

				<div style="padding-left: 10px;">
					<table class="noborder">
						<tbody>
							<tr class="noline">
								<td class="fr" style="width: 5%;">客户名称：</td>
								<td class="online" style="width: 10%"><input type="text"
									id="businessname" class="form-control" style="width: 250px;">
								</td>
								<td style="width: 5%"><button id="search"
										class="btn btn-sm btn-primary pull-left" type="button">查询数据</button></td>
								<td></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div id="accountdata" style="margin-left:20px;width: 59%">
				</div>
				<div style="padding-left: 10px;">
					<h3 style="margin-top: 50px;">绑定信息</h3>
					<div style="padding-left: 10px;margin-top:15px">
						<table class="noborder">
							<tbody id="userinfo">
								<!-- <tr>
									<td width="120px">平台账号：</td>
									<td width="150px">zhangsan</td>
								</tr> -->
							</tbody>
						</table>
					</div>
					<div style="padding-left: 10px;margin-top:10px">
						<table id="addacctab" class="table table-striped table-bordered table-hover dataTables-example dataTable" style="width:60%" aria-describedby="DataTables_Table_0_info">
							<tbody id="accinfo">
							</tbody>
						</table>
					</div>
				</div>
				<div style="margin-top: 20px;" class="form-group">
					<div class="col-sm-3 col-sm-offset-3">
						<input type="hidden" id="deptName" name="deptName">
						<button class="btn btn-primary" type="button" style="width: 80px;"
							id="btnsave" onclick="dosubmit();">提交绑定</button>
						<button class="btn btn-primary" type="button"
							style="width: 60px; margin-left: 20px;" onclick="history.go(-1)"
							id="btnback">返回</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="/js/handler/addaccountbind.js"></script>
<script type="text/javascript">
	function adduser(username){
		$("#userinfo").empty();
		$("#addacctab").find("thead").remove();
		$("#addacctab").find("tbody:not([id='accinfo'])").remove();
		$("#accinfo").find
		var mht = "<tr id='"+username+"'>";
		mht += "<td width='120px'>平台账号：</td>";
		mht += "<td width='150px'>"+username+"</td></tr>";
		$("#userinfo").append(mht);
		
		//$("#accinfo").find("tbody").remove();
		$.ajax({
			type: "POST",
	   		url: "/account_bind/getuser_accbind",
	   		data: {"username" : username},
	   		dataType: "json",
		   	success: function(data){
		   		if(data != null && data != 'null' && data != ''){
		   			var accinfo = $("#accinfo");
		   			var mht = "<thead>";
	   				mht +="<tr role='row'>";
	   				mht +="<th tabindex='0' aria-controls='DataTables_Table_0' rowspan='1' colspan='1'>客户名称</th>";
	   				mht +="<th tabindex='0' aria-controls='DataTables_Table_0' rowspan='1' colspan='1'>客户账号</th>";
	   				mht +="<th tabindex='0' aria-controls='DataTables_Table_0' rowspan='1' colspan='1'>客户类型</th>";
	   				mht +="<th tabindex='0' aria-controls='DataTables_Table_0' rowspan='1' colspan='1'>操作</th>";
	   				mht +="</tr>";
	   				mht +="</thead>";
	   				//var result = eval("("+data+")");
		   			$.each(data, function (k, v) {
		   				mht +="<tr id='"+v.apiAccount+"'>";
		   				mht +="<td>"+v.businessName+"</td>";
		   				mht +="<td>"+v.merchantPhone+"</td>";
		   				if(v.merchantType == 1){
		   					mht +="<td>个人开发者</td>";
		   				}else if(v.merchantType == 2){
		   					mht +="<td>企业开发者</td>";
		   				}else{
		   					mht +="<td></td>";
		   				}
		   				mht +="<td><a onclick=delacctr('"+v.apiAccount+"')>删除</a></td></tr>";
		           	});
		   			accinfo.after(mht);
		   		}
		   	}
		});
	}
	
	function addacc(apiAccount, businessName, merchantPhone, merchantType){
		//$("#addacctab").find("tbody:not([id='accinfo'])").remove();
		//$("#addacctab").find("thead").remove();
		if($("#"+apiAccount+"").length > 0){
			layer.alert("该客户已在绑定列表中");
			return false;
		}
		var accinfo = $("#accinfo");
		var addaccltr = $("#addacctab thead").length;
		if(addaccltr == 0){
			var mht = "<thead>";
			mht +="<tr role='row'>";
			mht +="<th tabindex='0' aria-controls='DataTables_Table_0' rowspan='1' colspan='1'>客户名称</th>";
			mht +="<th tabindex='0' aria-controls='DataTables_Table_0' rowspan='1' colspan='1'>客户账号</th>";
			mht +="<th tabindex='0' aria-controls='DataTables_Table_0' rowspan='1' colspan='1'>客户类型</th>";
			mht +="<th tabindex='0' aria-controls='DataTables_Table_0' rowspan='1' colspan='1'>操作</th>";
			mht +="</tr>";
			mht +="</thead>";
			mht +="<tr id='"+apiAccount+"'>";
			mht +="<td>"+businessName+"</td>";
			mht +="<td>"+merchantPhone+"</td>";
			if(merchantType == 1){
				mht +="<td>个人开发者</td>";
			}else if(merchantType == 2){
				mht +="<td>企业开发者</td>";
			}else{
				mht +="<td></td>";
			}
			mht +="<td><a onclick=delacctr('"+apiAccount+"')>删除</a></td></tr>";
			accinfo.after(mht);
		}else{
			var selectacc = $("#"+apiAccount+"").length;
			if(selectacc <= 0){
				var tbody = $("#addacctab").find("tbody:last");
				mht += "<tbody>";
				mht +="<tr id='"+apiAccount+"' role='row'>";
				mht +="<td>"+businessName+"</td>";
				mht +="<td>"+merchantPhone+"</td>";
				if(merchantType == 1){
					mht +="<td>个人开发者</td>";
				}else if(merchantType == 2){
					mht +="<td>企业开发者</td>";
				}else{
					mht +="<td></td>";
				}
				mht +="<td><a onclick=delacctr('"+apiAccount+"')>删除</a></td></tr>";
				mht += "</tbody>";
				tbody.after(mht);
			}
		}
	}
	
	function delacctr(trid){
		$("#"+trid+"").remove();
	}	
	
	function dosubmit(){
		$("#btnsave").attr("disabled", true);
		var usertr = $("#userinfo tr").length;
		if(usertr <= 0){
			layer.alert("绑定的平台账号为空，请选择");
			$("#btnsave").attr("disabled", false);
			return false;
		}
		var accounttr = $("#addacctab").find("tbody").find("tr").length;
		if(accounttr <= 0){
			layer.alert("绑定的客户账号为空，请选择");
			$("#btnsave").attr("disabled", false);
			return false;
		}
		var username = $("#userinfo tr").attr("id");
		var accounttrs = $("#addacctab").find("tbody").find("tr");
		var accounts = "";
		for(var i=0;i<accounttrs.length;i++){
			var id = accounttrs.eq(i).attr("id");
			accounts += id + ","
		}
		$.ajax({
			type: "POST",
	   		url: "/account_bind/do_bind",
	   		data: {"username" : username, "accounts":accounts},
	   		dataType: "json",
		   	success: function(data){
		   		if(data == 'true' || data == true){
		   			layer.alert("客户绑定成功");
		   			setTimeout('window.location.href="/account_bind/to_list"',2000)
		   		}else{
		   			layer.alert("客户绑定出错");
		   			$("#btnsave").attr("disabled", false);
		   		}
		   	}
		});
	}
</script>
</html>
