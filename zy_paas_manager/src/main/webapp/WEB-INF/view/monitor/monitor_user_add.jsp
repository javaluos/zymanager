<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>

<!DOCTYPE HTML>
<html>
<head>
<title>Paas运营管理平台</title>
<jsp:include page="../comm/plugin.jsp" />
</head>
<script type="text/javascript">
	function save(){
		var apiAccount=$("#apiAccount").val();
		var businessName=$("#businessName").val();
		var merchantPhone=$("#merchantPhone").val();
		if(apiAccount==undefined||apiAccount==null||apiAccount==''){
			$("#msg").text("请输入账号");
			return false;
		}
		$.post("/monitor/save_accountmonitor",{"apiAccount":apiAccount,"businessName":businessName,"merchantPhone":merchantPhone}, function(json) {
		     $.each(json,function(i,cdrMonitorUser){
		    	 window.parent.location.reload();
                 var index = parent.layer.getFrameIndex(window.name);
                 parent.layer.close(index);
		     })
		},"json"); 
	}
	
	function cancel(){
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	    parent.layer.close(index);
	}
	
	function selectmerchantphone() { 
		var merchantPhone=$("#Phone").val();
		$("#msg").text("");
		$.post("/monitor/get_merchantaccounts",{"merchantPhone":merchantPhone}, function(json) {
	       $.each(json,function(i,merchantAccount){
	    	   if(i=="result"){
	    		   if(merchantAccount.apiAccount==undefined){
	    			   $("#msg").text(merchantAccount);
	    		   }else{
	    			   var businessName=merchantAccount.businessName;
	    			   if(merchantAccount.businessName.length>13){
	    				   businessName=businessName.substring(0,13);
	    				   businessName=businessName+"...";
	    			   }else{
	    				    var leng=13-businessName.length;
    					    for(var j=0;j<leng;j++){
    					    	businessName=businessName+ "　";
    					    }
	    			   }
	    			   $("#index").text(1);
	    			   $("#businessName2").text(businessName);
	    			   $("#merchantPhone2").text(merchantAccount.merchantPhone);
	    			   $("#createTime2").text(merchantAccount.createTime);
	    			   $("#apiAccount").val(merchantAccount.apiAccount);
	    			   $("#businessName").val(businessName);
	    			   $("#merchantPhone").val(merchantAccount.merchantPhone);
	    		   }
	    	   }
	       })
	    },"json");
	}
	
</script>
<body>
	<!-- QueryForm -->
	<div class="col-sm-12" style="width: 630px;height: 300px;">
		<div>
		 <form method="get" name="form2" action="" id="form2">
			<table>
				<tbody>
				   <tr style="height: 5px;">
					    <td style="margin-top:5px;height: 5px;">
					       <span style="margin-top:5px;height: 5px;" > </span>
					    </td>
					</tr>
					
					<tr>
						<td>
						    <span style="width:70px;margin-left: 60px;margin-top: 40px;">账号&nbsp;:</span>
       						<input type="text" name="Phone" id="Phone"/>&nbsp;&nbsp;
    							<button id="chaxun" style="width: 60px;" onclick="selectmerchantphone()" class="btn btn-primary" type="button">查询</button>
					        <span style="height: 5px;margin-top:30px;margin-left:5px;width:90px;color:red;" id="msg"></span>
					   </td>
					</tr>
					
					<tr>
					    <td style="margin-top:5px;height: 5px;">
					       <span style="margin-top:5px;height: 5px;" > </span>
					    </td>
					</tr>
					
					<tr style="width: 630px;">
			          <td> 
			          	<span style="width:90px;margin-left: 60px;margin-top: 40px;">序号 </span>
			         	<span style="width:150px;margin-left: 10px;margin-top: 40px;">客户名称</span>
			            <span style="width:90px;margin-left: 150px;margin-top: 40px;">账号</span>
			            <span style="width:250px;margin-left: 110px;margin-top: 40px;">注册时间 </span>
			          </td>
				    </tr>
					
					<tr style="width: 630px;">
					 <td>  
					    <span style="width:90px;margin-left: 60px;margin-top: 10px;" id="index"></span>
			         	<span style="width:150px;margin-left: 30px;margin-top: 10px;" id="businessName2"></span>
			            <span style="width:90px;margin-left: 10px;margin-top: 10px;" id="merchantPhone2"></span>
			            <span style="width:250px;margin-left: 20px;margin-top: 10px;" id="createTime2"> </span>
			            <input type="hidden" name="apiAccount" id="apiAccount"/>
			            <input type="hidden" name="businessName" id="businessName"/>
			            <input type="hidden" name="merchantPhone" id="merchantPhone"/>
			          </td>
					 </tr>
					 
					 <tr>
						<td class="fr" style="width: 600px;">
						   <button id="btnsa2" style="margin-left: 100px;margin-top: 120px;" onclick="save()" class="btn btn-sm btn-primary pull-left" type="button">保存</button>
						   <button id="btnquy" style="margin-left: 100px;margin-top: 120px;"  onclick="cancel()" style="margin-left: 60px;" class="btn btn-sm btn-primary pull-left" type="button">取消</button>
						</td>
					 </tr>
				</tbody>
			</table>
		  </form>
	</div>
	<!-- /QueryForm -->
	</div>
</body>
</html>
