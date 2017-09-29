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
	<div style="margin-left:20px;margin-top:20px">
		<span ><h4>平台账号：&nbsp;&nbsp;&nbsp;${username }</h4></span>
		<input type="hidden" id="username" value="${username }" />
	</div>
	<!-- QueryData -->
	<h4 style="margin-left:20px;">客户列表：</h4>
	<div id="QueryData" style="margin-left:20px;width: 96%">
		
	</div>

	<!-- QueryForm -->
	<div id="toshowdata" style="margin-left:220px">
		<span><button class="btn btn-primary" type="button" id="addbtn"
					style="width: 120px; margin-left: 20px;"><!-- onclick="showdata();" -->增加客户</button> 
		</span>
		<span style="margin-left:150px">
			  <button class="btn btn-primary" type="button"
					style="width: 100px; margin-left: 20px;" onclick="cancel();">取消</button>
		</span>
	</div>

	<div id="accinfo" style="display: none;margin-left:20px;width: 92%">
		<div id="accdatainfo">
		</div>
		
	</div>
	<div id="subbtn" style="width: 700px; text-align: center;margin-top: -10px;margin-bottom: 10px;display: none">
		<button class="btn btn-primary" id="okBtn" onclick="submit();" type="button">提交绑定</button>
		<button class="btn btn-primary" type="button" id="u_btn_2_cancel" style="margin-left: 50px;" onclick="cancel();">取消</button>
	</div>
	<!-- /QueryForm -->


	<!-- /wrap_right -->
	<script type="text/javascript" src="/js/handler/modifyaccountbind.js"></script>
	<!-- <script type="text/javascript" src="/js/handler/modifyaddaccount.js"></script> -->
	<script type="text/javascript">
	/* function showdata(){
		$("#toshowdata").hide();
		$("#accinfo").show();
		
		var loadUrl = "/account_bind/acc_list_data?method=modify";
		$('#accdatainfo').load(loadUrl, {
			params : JSON.stringify(this.params)
		}, function() {
			layer.close(); // 关闭loading框
		});
	} */
	
	UPGLoader.loadData();// 首次加载数据
	function cancel(){
		parent.layer.closeAll();
	}
	
	function doadd(apiAccount, merchantPhone, businessName, merchantType){
		var acctr = $("#DataTables_Table_0").find("tbody").find("tr[id='"+apiAccount+"']").length;
		var username = $("#username").val();
		$.ajax({
			type: "POST",
	   		url: "/account_bind/check_bind",
	   		data: {"username" : username, "apiAccount":apiAccount},
	   		dataType: "json",
		   	success: function(data){
		   		if(data == true ){
		   			if(acctr <= 0){
						var mht = "<tr class='gradeC odd' id='"+apiAccount+"'>";
						mht += "<td>"+businessName+"</td>";
						mht += "<td>"+merchantPhone+"</td>";
						if(merchantType == 1){
							mht += "<td>个人开发者</td>";
						}else if(merchantType == 2){
							mht += "<td>企业开发者</td>";
						}else{
							mht += "<td></td>";
						}
						mht += "<td><a href='javascript:;' onclick=del('"+apiAccount+"');>删除</a></td>";
						mht += "</tr>";
						$("#DataTables_Table_0").find("tbody").append(mht);
		   			}else{
			   			layer.alert("该客户已在绑定列表中");
		   			}
		   		}else{
		   			if(acctr <= 0){
						var mht = "<tr class='gradeC odd' id='"+apiAccount+"'>";
						mht += "<td>"+businessName+"</td>";
						mht += "<td>"+merchantPhone+"</td>";
						if(merchantType == 1){
							mht += "<td>个人开发者</td>";
						}else if(merchantType == 2){
							mht += "<td>企业开发者</td>";
						}else{
							mht += "<td></td>";
						}
						mht += "<td><a href='javascript:;' onclick=del('"+apiAccount+"');>删除</a></td>";
						mht += "</tr>";
						$("#DataTables_Table_0").find("tbody").append(mht);
		   			}else{
			   			layer.alert("该客户已在绑定列表中");
		   			}
		   		}
		   	}
		});
	}
	function del(apiAccount){
		$("#DataTables_Table_0").find("tbody").find("tr[id='"+apiAccount+"']").remove();
	}
	
	function submit(){
		$("#okBtn").attr("disabled", true);
		var username = $("#username").val();
		var accounts = "";
		var accounttrs = $("#DataTables_Table_0").find("tbody").find("tr");
		if(accounttrs.length <= 0){
			layer.alert("绑定的客户账号为空，请选择");
			$("#okBtn").attr("disabled", false);
			return false;
		}
		for(var i=0;i<accounttrs.length;i++){
			var id = accounttrs.eq(i).attr("id");
			accounts += id + ",";
		}
		$.ajax({
			type: "POST",
	   		url: "/account_bind/do_bind",
	   		data: {"username" : username, "accounts":accounts},
	   		dataType: "json",
		   	success: function(data){
		   		if(data == 'true' || data == true){
		   			layer.alert("客户绑定成功");
		   			parent.location.reload();
		   			parent.layer.closeAll();
		   		}else{
		   			layer.alert("客户绑定出现错误");
		   			$("#okBtn").attr("disabled", false);
		   		}
		   	}
		});
	}
	</script>
</body>
</html>
