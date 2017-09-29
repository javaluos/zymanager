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

<div class="col-sm-8">
	<div class="contentdv">
		<div style="padding-left: 10px;">
	               <form class="form-horizontal m-t" id="myForm" method="post"  novalidate="novalidate">
					    <input id="id" name="id" type="hidden" value="${obj.id }">
					    <input id="keys"  type="hidden" value="${obj.id }">
					    
					     <br/>
					     
						<div class="form-group">
                             <label class="col-sm-2 control-label" style="width:150px;text-align: right">策略名称：</label>
                             <div class="col-sm-3">
                                 <input type="text" id="policyName" name="policyName" class="form-control" value="${obj.policyName }" style="width:300px;">
                             </div>
                         </div>
                         
                          <br/>
                          
                         <div class="form-group">
                             <label class="col-sm-2 control-label" style="width:150px;text-align: right">备注：</label>
                             <div class="col-sm-3">
                                 <textarea id="remark" name="remark" class="form-control"
                                  style="height: 100px;width:300px"  maxlength="500">${obj.remark }</textarea>
                             </div>
                         </div>
                         
                          <br/>
                          
                          
                         <div class="form-group">
                             <label class="col-sm-2 control-label"></label>
                             <div class="col-sm-3" style="text-align: center">
                                 <button class="btn btn-primary" type="button" style="width: 60px;margin-left: -20px;" id="btnback">取消</button>
                                 <button class="btn btn-primary" type="button" style="width: 60px;margin-left: 20px;" id="btnupdate">保存</button>
                             </div>
                         </div>
				     </form>
			</div>
	  </div>
 </div>
<script type="text/javascript">
$(function() {
		
	$('#btnback').click(function(){
		window.parent.location.reload();
		parent.layer.closeAll();
		window.location.href = '/black_key_policy/to_list';
	});
	
	$('#btnupdate').click(function(){
		
		var policyName = $("#policyName").val();
		if(policyName=='' || policyName==null){
			$("#btnupdate").attr("disabled",false);
			layer.tips('请输入策略名称.', $('#policyName'));
			$('#policyName').focus();
			return false;
		}else if(policyName.trim().length>20){
			layer.tips('名称字数不能超过20个.', '#policyName');
			return false;
		}else if(remark=='' || remark==null){
			if(remark.trim().length>500){
				layer.tips('备注字数不能超过500个.', '#remark');
				return false;
			}
		}
		else{
			$.ajax({
				type : "POST",
				url : "/black_key_policy/update",
				data : {
					'id' : $("#id").val(),
					'policyName' : $("#policyName").val(),
					'remark' : $("#remark").val()
				},
				success : function(data) {
					if(data == '1'){
						window.parent.location.reload();
						parent.layer.closeAll();
					}else if(data == '2'){
						layer.confirm("策略名称已经存在.", {
							area: ['450px'],
							btn: ['确认'] //按钮
						}, function(){
							window.parent.location.reload();
							parent.layer.closeAll();
						});
					}else{
						layer.confirm("保存数据异常.", {
							area: ['450px'],
							btn: ['确认'] //按钮
						}, function(){
							window.parent.location.reload();
							parent.layer.closeAll();
						});
					}	 
				}
			});
		}
	});
});
</script>
</body>
</html>
