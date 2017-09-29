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
	<div class="col-sm-12" style="border-bottom: 1px solid #e5e6e7; padding-bottom: -20px;margin-bottom: 10px;">
		<div class="ibox">
			<div class="contentdv">
			   <div style="padding-left: 100px;">
	               <form id="myform" method="post" action="/user/do_user_update" class="form-horizontal m-t" novalidate="novalidate">
               		<div style="margin-left:-50px;font-size: 14px;"><span style="margin-left:-10px">账号名称：${user.userName }</span><span style="margin-left:130px">真实姓名：${user.fullname }</span><span style="margin-left:130px">所属部门：${department.name }</span></div>
                       <div style="margin-top:15px"></div>
                       <c:forEach var="menuRoot" items="${menuRootList }" varStatus="i">
	                        <div style="margin-left:-60px;margin-top:5px"><input name="menu" id="${i.index + 1 }" value="${menuRoot.menuid }" 
		                             <c:if test="${menuRoot.isChecked ==1}"> checked="checked" </c:if>
		                             type="checkbox" onclick="allselect(${menuRoot.menuid },${i.index + 1 } )" style="vertical-align:-2px;"/>&nbsp;${menuRoot.name }</div>
                        	  <c:if test="${menuRoot.childList != null }">
                        		<div id="${menuRoot.menuid }${i.index + 1 }" style="margin-left:-40px;">
	                         		<c:forEach var="menu" items="${menuRoot.childList }" varStatus="j">
	                         			<input style="margin-left:20px;margin-top:5px;vertical-align: -2px;" name="menu"  
	                         				<c:if test="${menu.isChecked ==1}"> checked="checked" </c:if> 
			                         			type="checkbox" value="${menu.menuid }" onclick="selectparent(${menuRoot.menuid },${i.index + 1 });"/><span style="width: 150px;display:inline-block;line-height: 20px;">&nbsp;${menu.name }</span>
	                         		</c:forEach>
                        		</div>
                        	</c:if>
                        </c:forEach>
                        <div style="margin-top:20px;" class="form-group">
                            <div class="col-sm-3 col-sm-offset-3">
                            	<input type="hidden" id="userName" name="userName" value="${user.userName }">
                               <button class="btn btn-primary" type="button" style="width: 60px;" id="btnsave" onclick="dosubmit();">提交</button>
                               <button class="btn btn-primary" type="reset" style="width: 60px;margin-left: 20px;" id="btnback" onclick="clearAll();">取消</button>
                            </div>
                        </div>
                     </form>
                     </div>
                 </div>
		</div>
	</div>
<script type="text/javascript">
function allselect(id,index){
	if($("#"+index+"").is(":checked") == true){
		$("#"+id+""+index+">input[type='checkbox']").prop("checked",true);
	}else{
		$("#"+id+""+index+">input[type='checkbox']").prop("checked",false);
	}
	
}

function selectparent(menuid, index){
	if($("#"+menuid+""+index+" :checkbox:checked").length > 0){
		$("#"+index+"").prop("checked",true);
	}else{
		$("#"+index+"").prop("checked",false);
	}
}

function dosubmit(){
	var userName = $("#userName").val();
	var obj=document.getElementsByName('menu');     
	  //取到对象数组后，我们来循环检测它是不是被选中    
	var menu = '';    
	for(var i=0; i<obj.length; i++){    
	  if(obj[i].checked) menu += obj[i].value+',';
	}    
	$.ajax({
		type: "POST",
	   	url: "/user/do_user_update",
	   	data:{
	   		"userName":userName,
	   		"menu":menu
	   	},
	   	success: function(data){
	   		if(data){
				parent.layer.closeAll();
	   		}
	   	}
	});
}

function clearAll(){
	//$("input[type='checkbox']").removeAttr("checked","checked");
	parent.layer.closeAll();
}

</script>	 
</body>
</html>
