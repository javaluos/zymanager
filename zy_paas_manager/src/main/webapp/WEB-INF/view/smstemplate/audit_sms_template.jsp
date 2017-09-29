<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>Paas运营管理平台</title>
<jsp:include page="../comm/plugin.jsp" />
</head>
<body>
	<div class="col-sm-12"
		style="border-bottom: 1px solid #e5e6e7; padding-bottom: -20px; margin-bottom: 10px;">
		<div class="ibox">
			<div class="ibox-title">
				<h5>
					审核信息<small>&nbsp;-->&nbsp;模板审核</small>
				</h5>
			</div>
			<div class="contentdv">
				<div style="margin-left: -150px;">
					<form id="myform" name="myform" method="post" action="/smstemplate/do_audit"
						class="form-horizontal m-t" novalidate="novalidate">
						<div class="form-group">
							<label class="col-sm-2 control-label" for="businessName">客户名称:</label>
							<div class="col-sm-3">
								<label> 
									${mchSmsTemplate.businessName }
								</label>
							</div>
						</div>
						<div class="form-group">
							<label for="merchantPhone" class="col-sm-2 control-label">客户账号:</label>
							<div class="col-sm-3">
								<label>${mchSmsTemplate.merchantPhone}</label>
							</div>
						</div>
						<div class="form-group">
							<label for="authSubmitTime" class="col-sm-2 control-label">提交时间:</label>
							<div class="col-sm-3">
								<label><fmt:formatDate value="${mchSmsTemplate.authSubmitTime}" pattern="yyyy-MM-dd HH:mm:ss" /></label>
							</div>
						</div>
						<div class="form-group">
                             <label class="col-sm-2 control-label">模板ID：</label>
                             <label class="col-sm-3 control-label">
                                <label>${mchSmsTemplate.id}</label>
                             </label>
                         </div>
                         
						<div class="form-group">
							<label for="category" class="col-sm-2 control-label">模板类型:</label>
							<div class="col-sm-3">
								<label>
									<c:if test="${mchSmsTemplate.category == '8' }">
										通知
									</c:if>
									<c:if test="${mchSmsTemplate.category == '9' }">
										验证码
									</c:if>
									<c:if test="${mchSmsTemplate.category == '11' }">
										营销
									</c:if>
								</label>
							</div>
						</div>
						<div class="form-group">
							<label for="content" class="col-sm-2 control-label">模板内容:</label>
							<div class="col-sm-10">
								<textarea id="content" name="content" class="form-control" aria-required="true" readonly="readonly"
                                        style="width:400px;height: 100px;">${mchSmsTemplate.content}</textarea>
								<button class="btn btn-primary" type="button" style="width: 80px;margin-left:420px;margin-top:-60px"
								        value="0" id="btnedit" onclick="edit();">启用编辑</button>
							</div>
						</div>
						<c:if test="${mchSmsTemplate.status == 1 ||  mchSmsTemplate.status == 4}">
							<div class="form-group">
								<label for="authSubmitTime" class="col-sm-2 control-label">审核时间:</label>
								<div class="col-sm-3">
									<label><fmt:formatDate value="${mchSmsTemplate.authResultTime}" pattern="yyyy-MM-dd HH:mm:ss" /></label>
								</div>
							</div>
							<div class="form-group">
								<label for="authSubmitTime" class="col-sm-2 control-label">审核人:</label>
								<div class="col-sm-3">
									<label>${mchSmsTemplate.authUser }</label>
								</div>
							</div>
							<div class="form-group">
								<label for="status" class="col-sm-2 control-label">状态:</label>
								<div class="col-sm-3">
									<label>
										<c:if test="${mchSmsTemplate.status=='1'}">审核通过</c:if>
							    	   	<c:if test="${mchSmsTemplate.status=='2'}">待审核</c:if> 
							    	   	<c:if test="${mchSmsTemplate.status=='3'}">待审核</c:if>
							    	   	<c:if test="${mchSmsTemplate.status=='4'}">审核未通过</c:if>
									</label>
								</div>
							</div>
							<c:if test="${mchSmsTemplate.status == 4 }">
								<div class="form-group">
									<label for="authSubmitTime" class="col-sm-2 control-label">不通过原因:</label>
									<div class="col-sm-6">
										<label>${mchSmsTemplate.reason }</label>
									</div>
								</div>
							</c:if>
						</c:if>
						<div class="form-group">
							<label for="reason" class="col-sm-2 control-label">不通过原因:</label>
							<div class="col-sm-4">
								<div style="margin-top:10px">
									<input type="radio" name="reason" value="模板内容包含敏感词，请修改" onclick="disablereason();">&nbsp;模板内容包含敏感词，请修改。
								</div>
								<div style="margin-top:10px">
									<input type="radio" name="reason" value="模板内容与营销相关，请修改" onclick="disablereason();">&nbsp;模板内容与营销相关，请修改。
								</div>
								<div style="margin-top:10px">
									<input type="radio" name="reason" value="模板内容涉及房产、贷款、教育、移民及违规违法内容，请修改" onclick="disablereason();">&nbsp;模板内容涉及房产、贷款、教育、移民及违规违法内容，请修改。
								</div>
								<div style="margin-top:10px">
									<input type="radio" name="reason" value="模板内容主题不明确，请修改" onclick="disablereason();">&nbsp;模板内容主题不明确，请修改。
								</div>
								<div style="margin-top:10px">
									<input type="radio" name="reason" value="自定义原因" onclick="enablereason();">&nbsp;自定义原因：
								</div>
								<div class="col-sm-3" style="margin-top:8px">
									<textarea id="reason" name="reason" class="form-control" required="" aria-required="true" disabled="disabled" 
										style="width:400px;height: 100px;"></textarea>
								</div>
							</div>
							<c:if test="${not empty blackKey }">
							    <div class="form-group" style="width:900px;margin-top:18px;margin-left:200px">
									<div class="col-sm-10" style="margin-top:5px">
										<font color='red'>模板内容中含有敏感词：${blackKey},请注意！添加至白名单才可使用！
										</font>
									</div>
								</div>
							</c:if>
						</div>
						<div class="form-group">
                             <div class="col-sm-2 col-sm-offset-2">
                             	<input type="hidden" name="flag" id="flag">
                                <input type="hidden" name="id" id="id" value="${mchSmsTemplate.id }">
                                <button class="btn btn-primary" type="button" style="width: 60px;" id="btnsave" onclick="isOk();">通过</button>
                                <button class="btn btn-primary" type="button" style="width: 60px;margin-left: 20px;" id="btnsave" onclick="isFalse();">不通过</button>
                                <button class="btn btn-primary" type="button" style="width: 60px;margin-left: 20px;" onclick="history.go(-1)" id="btnsave">返回</button>
                             </div>
                         </div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function enablereason(){
			$("#reason").text('');
			$("#reason").attr("disabled", false);
		}
		function disablereason(){
			$("#reason").attr("disabled", true);
		}
		function isOk(){
			$('#flag').val(1);			
			//询问框
			layer.confirm('你确认要通过审核吗？', {
			  btn: ['确认','取消'] //按钮
			}, function(){
			  //layer.msg('已确认', {icon: 0}); 
			  $('#myform')[0].submit();
			}, function(){
			  //layer.msg('已取消', {icon: 0});
			});
		}
		function isFalse(){
			var authdesc=$('input:radio[name="reason"]:checked').val();
			var reason=$('#reason').val();
			if(authdesc==undefined){
				alert("请选择不通过的原因");
				return false;
			}else if(authdesc=="自定义原因"){
				if(reason == ''){
					alert("请输入不通过原因");
					return false;
				}
				if(reason.length > 50){
					layer.tips('自定义原因总字数不能超过50个字（单个汉字、英文和标点都算一个字）.', $('#reason'));
			  	  	$('#reason').focus();
				  	return false;
				}
				authdesc=reason;
				$('#reason').val(reason);
			}
			$('#flag').val(0);
			//询问框
			layer.confirm('你确认不通过审核吗？', {
			  btn: ['确认','取消'] //按钮
			}, function(){
			  //layer.msg('已确认', {icon: 0}); 
			  $('#myform')[0].submit();
			}, function(){
			  //layer.msg('已取消', {icon: 0});
			});
		}
		function edit(){
            var btnvalue = $("#btnedit").val();
            if(btnvalue == 0){
                $("#content").attr("readonly", false);
                $("#btnedit").val("1");
                $("#btnedit").text("关闭编辑");
            }else{
                $("#content").attr("readonly", true);
                $("#btnedit").val("0");
                $("#btnedit").text("启用编辑");
            }
		}
	</script>
</body>
</html>
