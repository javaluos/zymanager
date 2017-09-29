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
<div class="col-sm-8">
	<div class="contentdv">
		<div style="padding-left: 10px;">
	               <form class="form-horizontal m-t" id="myForm" method="post"  novalidate="novalidate">
	                    <input type="hidden" id="id" value="${id}">
	                    <input type="hidden" id="oriGroupName" value="${groupName}">
						<div class="form-group">
                             <label class="col-sm-2 control-label" style="width:150px;text-align: right">黑名单组名：</label>
                             <div class="col-sm-3">
                                 <input type="text" id="groupName" name="groupName" value="${groupName}" class="form-control" style="width:300px;">
                             </div>
                         </div>

                         <br/>

                         <div class="form-group">
                             <label class="col-sm-2 control-label" style="width:150px;text-align: right">备注：</label>
                             <div class="col-sm-3">
                                 <textarea id="remark" name="remark" class="form-control"
                                  style="height: 100px;width:300px"  maxlength="500">${remark }</textarea>
                             </div>
                         </div>

                          <br/>

                         <div class="form-group">
                             <label class="col-sm-2 control-label"></label>
                             <div class="col-sm-3" style="text-align: center">
                                 <button class="btn btn-primary" type="button" style="width: 60px;margin-left: -20px;" id="btnback">返回</button>
                                 <button class="btn btn-primary" type="button" style="width: 60px;margin-left: 20px;" id="btnsave">保存</button>
                             </div>
                         </div>
				     </form>
			</div>
	  </div>
 </div>

<script type="text/javascript">
$(function() {
	// 返回按钮事件
	$('#btnback').click(function(){
		parent.layer.closeAll();
	});

	// 保存按钮事件
	$('#btnsave').click(function(){
		doSubmit();
	});

});

/**
 * 提交保存（新增,修改）
 *
 * @returns {Boolean}
 */
function doSubmit(){

	var groupName = $('#groupName').val();
	var oriGroupName = $('#oriGroupName').val();
	var remark = $('#remark').val();
    var id = $('#id').val();

	// 验证敏感词非空
	if(groupName=='' || groupName==null){
		layer.tips('拦截策略名称必填.', '#groupName');
		return false;
	}else{
		if(groupName.trim().length>20){
			layer.tips('黑名单组名称字数不能超过20个.', '#groupName');
			return false;
		}
	}

	if(remark=='' || remark==null){
		if(remark.trim().length>100){
			layer.tips('备注字数不能超过100个.', '#remark');
			return false;
		}
	}

	// 设置参数对象
	var params = {
		oriGroupName: oriGroupName,
		groupName: groupName,
		remark : remark
	};

	$.ajax({
		type: "POST",
   		url: "/sms_black_group/do_edit/"+id+"",
		data: {'params' : JSON.stringify(params)},
		success: function(data){
			if(data == '1'){
				window.parent.location.reload();
				parent.layer.closeAll();
			}else if(data == '-1'){
			    layer.alert("黑名单组名称已经存在.");
			}else{
                layer.alert("保存数据异常.");
			}
	   	}
	})
}
</script>
</body>
</html>
