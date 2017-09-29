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
			<div class="ibox-title">
				<h5>
					用户管理<small>&nbsp;-->&nbsp;添加用户</small>
				</h5>
			</div>
			<div class="contentdv">
			   <div style="padding-left: 100px;">
	               <form id="myform" method="post" action="/user/do_user_add" class="form-horizontal m-t" id="signupForm" novalidate="novalidate">
               		<div><span>用户账号信息</span><span style="float:right;margin-right:50px;">说明:<font color="red">*</font>表示必须项</span></div>
                         <div class="form-group">
                             <label class="col-sm-2 control-label"><font color="red">*</font>账号名称：</label>
                             <div class="col-sm-3">
                                 <input name="userName" id="userName" class="form-control" type="text" aria-required="true" aria-invalid="true" value="" maxlength="20" />
                             </div>
                         </div>
                         <div class="form-group">
                             <label class="col-sm-2 control-label"><font color="red">*</font>真实姓名：</label>
                             <div class="col-sm-3">
                                 <input id="fullname" name="fullname" class="form-control" type="text" value="" maxlength="10">
                             </div>
                         </div>
                         <div class="form-group">
                             <label class="col-sm-2 control-label"><font color="red">*</font>手机号码：</label>
                             <div class="col-sm-3">
                                 <input id="phone" name="phone" class="form-control" type="text" value="" maxlength="11">
                             </div>
                         </div>
                         <div class="form-group">
                             <label class="col-sm-2 control-label"><font color="red">*</font>所属部门：</label>
                             <div class="col-sm-3">
                             	<select class="form-control" id="department" name="department">
                             		<option value="">请选择</option>
                             		<c:forEach var="department" items="${departmentList }" varStatus="i">
                             			<option value="${department.id }">${department.name }</option>
                             		</c:forEach>
                             	</select>
                                 <!-- <input id="department" class="form-control" type="text" value="" > -->
                             </div>
                         </div>
                         
                         <div>用户权限信息 </div>
                         <c:forEach var="menuRoot" items="${menuRootList }" varStatus="i">
                         	<div style="margin-left:120px;margin-top:5px"><input name="menu" id="${i.index + 1 }" value="${menuRoot.menuid }" type="checkbox" onclick="allselect(${menuRoot.menuid },${i.index + 1 } )" />${menuRoot.name }</div>
                         	<c:if test="${menuRoot.childList != null }">
                         		<div id="${menuRoot.menuid }${i.index + 1 }" style="margin-left:150px;width:80%">
	                         		<c:forEach var="menu" items="${menuRoot.childList }" varStatus="j">
	                         			<input style="margin-left:30px;margin-top:5px" name="menu" type="checkbox" value="${menu.menuid }" onclick="selectparent(${menuRoot.menuid },${i.index + 1 });" /><span style="width: 128px;display:inline-block;">${menu.name }</span>
	                         		</c:forEach>
                         		</div>
                         	</c:if>
                         </c:forEach>
                         
                         <div style="margin-top:20px;" class="form-group">
                             <div class="col-sm-3 col-sm-offset-3">
                             	<input type="hidden" id="deptName" name="deptName" >
                                <button class="btn btn-primary" type="button" style="width: 60px;" id="btnsave" onclick="dosubmit();">提交</button>
                                <button class="btn btn-primary" type="reset" style="width: 60px;margin-left: 20px;" id="btnclear" onclick="clearAll()">清空</button>
                                <button class="btn btn-primary" type="button" style="width: 60px;margin-left: 20px;" onclick="history.go(-1)" id="btnback">返回</button>
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
	if(userName.trim() == ''){
		$("#userName").focus();
		layer.tips("请输入账号名称.", $("#userName"));
		return false;
	}
	var userNameReg = /^[0-9a-zA_Z]+$/;
	if(userNameReg.test(userName) == false){
		$("#userName").focus();
		layer.tips("账号格式必须英文和数字组合.", $("#userName"));
		return false;
	}
	var flag = checkUserName(userName);
	if(flag == false){
		return false;
	}
	var fullname = $("#fullname").val();
	if(fullname.trim() == ''){
		$("#fullname").focus();
		layer.tips("请输入真实姓名.", $("#fullname"));
		return false;
	}
	var fullnameReg = /^[\u4e00-\u9fa5]{2,4}$/; 
	if(fullnameReg.test(fullname) == false){
		$("#fullname").focus();
		layer.tips("真实姓名必须是四字或以内的中文.", $("#fullname"));
		return false;
	}
	var phone = $("#phone").val();
	if(phone.trim() == ''){
		$("#phone").focus();
		layer.tips("请输入手机号码.", $("#phone"));
		return false;
	}
	var phoneReg = /^1[34578]\d{9}$/;
	if(phoneReg.test(phone) == false){
		$("#phone").focus();
		layer.tips("手机号码格式不正确.", $("#phone"));
		return false;
	}
	var department = $('#department option:selected').val();
	var deptname = $('#department option:selected').text();
	$("#deptName").val(deptname);
	if(department.trim() == ''){
		$("#department").focus();
		layer.tips("请选择所属部门.", $("#department"));
		return false;
	}
	$("#myform").submit();
}

function checkUserName(userName){
	var flag = true;
	$.ajax({
		type: "POST",
		async: false,
	   	url: "/user/check_username_exist",
	   	data: {
		   'userName': userName
	   },
	   success: function(data){
		   if(data){
			   $("#userName").focus();
				layer.tips("账号名称已存在.", $("#userName"));
				flag = false;
		   }
	   }
	});
	return flag;
}

function clearAll(){
	$("input[type='checkbox']").removeAttr("checked","checked");
}

</script>	 
</body>
</html>
