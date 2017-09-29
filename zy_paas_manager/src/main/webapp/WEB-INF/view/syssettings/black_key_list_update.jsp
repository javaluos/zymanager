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

<div class="col-sm-12">
	<div class="contentdv">
		<div style="padding-left: 10px;">
	               <form class="form-horizontal m-t" id="myForm" method="post"  novalidate="novalidate">
					    <input id="id" name="id" type="hidden" value="${obj.id }">
					    <input id="keys"  type="hidden" value="${obj.black_key }">
					     <input id="policyId"  type="hidden" value="${obj.policyId }">
						<div class="form-group">
                             <label class="col-sm-2 control-label" style="width:150px;text-align: right">所属行业：</label>
                             <div class="col-sm-3">
                                 <input type="text" id="industry" name="industry" class="form-control" value="${obj.industry }" style="width:300px;">
                             </div>
                         </div>
                         <div class="form-group">
                             <label class="col-sm-2 control-label" style="width:150px;text-align: right"><span style="color: red; vertical-align: -3px;">*&nbsp;</span>敏感词内容：</label>
                             <div class="col-sm-3">
                                 <input type="text" id="black_key" name="black_key" class="form-control" value="${obj.black_key }" style="width:300px;">
                             </div>
                         </div>
                         <div class="form-group">
                             <label class="col-sm-2 control-label" style="width:150px;text-align: right">备注：</label>
                             <div class="col-sm-3">
                                 <textarea id="remark" name="remark" class="form-control"
                                  style="height: 100px;width:300px"  maxlength="200">${obj.remark }</textarea>
                             </div>
                         </div>
                         <div class="form-group">
                             <label class="col-sm-2 control-label"></label>
                             <div class="col-sm-3" style="text-align: center">
                                 <button class="btn btn-primary" type="button" style="width: 60px;margin-left: 20px;" id="btnsave">保存</button>
                             </div>
                         </div>
				     </form>
			</div>
	  </div>
 </div>
<script type="text/javascript">
		function blackkeyExist(black_key,policyId){
			var result = false;
			$.ajax({
				type : "POST",
				async: false,
				url : "/black_key_list/blackkey_exist",
				data : {
					'black_key' : black_key,
					 'policyId':policyId
				},
				success : function(data) {
					result = data;
				}
			});
			return result;
		}
		$(function() {
			$("#black_key").blur(function(){
			    if($('#black_key').val()!=$('#keys').val()){
			    	var flag=blackkeyExist($('#black_key').val(),$('#policyId').val());
			    	if(flag){
			    		layer.tips('敏感词已存在', $('#black_key'));
			    	}
			    }
			});
			$('#btnsave').click(function(){
				var blackkey = $("#black_key").val();
				if(blackkey.trim() == ''){
					$("#btnsave").attr("disabled",false);
					layer.tips('请输入敏感词.', $('#black_key'));
					$('#black_key').focus();
					return false;
				}else{
					$.ajax({
						type : "POST",
						url : "/black_key_list/update",
						data : {
							'id' : $("#id").val(),
							'industry' : $("#industry").val(),
							'black_key' : $("#black_key").val(),
							'remark' : $("#remark").val()
						},
						success : function(data) {
							if (data) {
								window.parent.location.reload();
								parent.layer.closeAll();
							}
						}
					});
				}
			});
		});
	</script>
</body>
</html>
