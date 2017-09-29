<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
					审核信息<small>&nbsp;-->&nbsp;信息认证</small>
				</h5>
			</div>
			<div class="contentdv">
				<div style="margin-left: -150px;">
					<form id="myform" name="myform" method="post" action="/merchantAccountAuth/do_authentication"
						class="form-horizontal m-t" novalidate="novalidate">
						<div class="form-group">
							<label class="col-sm-2 control-label" for="merchantType">开发者类型:</label>
							<div class="col-sm-3" style="margin-top:8px">
								<label> <c:if test="${accAuth.merchantType == 1}">
										个人开发者
									</c:if> <c:if test="${accAuth.merchantType == 2}">
										企业开发者
									</c:if>
								</label>
							</div>
						</div>
						<c:if test="${accAuth.merchantType == 1}">
							<div class="form-group">
								<label class="col-sm-2 control-label" for="plName">真实姓名:</label>
								<div class="col-sm-3" style="margin-top:8px">
									<label>${accAuth. plName}</label>
								</div>
							</div>
							<div class="form-group">
								<label for="plCretType" class="col-sm-2 control-label">证件类型:</label>
								<div class="col-sm-3" style="margin-top:8px">
									<label>
										<c:if test="${accAuth.plCretType == 1}">
											身份证
										</c:if>
										<c:if test="${accAuth.plCretType == 2}">
											护照
										</c:if>
									</label>
								</div>
							</div>
							<div class="form-group">
								<label for="plCretNo" class="col-sm-2 control-label">证件号码:</label>
								<div class="col-sm-3" style="margin-top:8px">
									<label>${accAuth.plCretNo}</span>
								</div>
							</div>
							<div class="form-group">
								<label for="plCretFileurl" class="col-sm-2 control-label">证件照片:</label>
								<div class="col-sm-3" style="margin-top:8px">
									<img alt="个人证件照片" style="margin-top: 30px; margin-left: 10px;" width="460px" height="280px"
										name="plCretFileurl" src="${accAuth.plCretFileurl }">
									<img style="margin-top:30px;margin-left:10px;" width="460px" height="280px" alt="" name="plCretBackurl" src="${accAuth.plCretBackurl }">
								</div>
							</div>
						</c:if>
						<c:if test="${accAuth.merchantType == 2}">
							<div class="form-group">
								<label for="cyName" class="col-sm-2 control-label">公司名称:</label>
								<div class="col-sm-3" style="margin-top:8px">
									<label>${accAuth.cyName}</label>
								</div>
							</div>
							<div class="form-group">
								<label for="cyAddress" class="col-sm-2 control-label">公司地址:</label>
								<div class="col-sm-3" style="margin-top:8px">
									<label>${accAuth.cyAddress}</label>
								</div>
							</div>
							<div class="form-group">
								<label for="cyIndustry" class="col-sm-2 control-label">所属行业:</label>
								<div class="col-sm-3" style="margin-top:8px">
									<label>${accAuth.cyIndustry}</label>
								</div>
							</div>
							<div class="form-group">
								<label for="cyCretType" class="col-sm-2 control-label">证件类型:</label>
								<div class="col-sm-3" style="margin-top:8px">
									<label>
										<c:if test="${accAuth.cyCretType == 1 }">
											三证合一（一照一码）
										</c:if>
										<c:if test="${accAuth.cyCretType == 2 }">
											三证合一
										</c:if>
										<c:if test="${accAuth.cyCretType == 3 }">
											三证分离
										</c:if>
									</label>
								</div>
							</div>
							<c:if test="${accAuth.cyCretType == 1 }">
								<div class="form-group" id="uscc">
									<label for="cyUscc" class="col-sm-2 control-label">统一社会信用代码:</label>
									<div class="col-sm-3" style="margin-top:8px">
										<label>${accAuth.cyUscc }</label>
									</div>
								</div>
							</c:if>
							<c:if test="${accAuth.cyCretType == 2 }">
								<div class="form-group" id="regno">
									<label for="cyRegistrNo" class="col-sm-2 control-label">注册号:</label>
									<div class="col-sm-3" style="margin-top:8px">
										<label>${accAuth.cyRegistrNo }</label>
									</div>
								</div>
								<div class="form-group" id="trcno">
									<label for="cyTrcNo" class="col-sm-2 control-label">税务登记证号:</label>
									<div class="col-sm-3" style="margin-top:8px">
										<label>${accAuth.cyTrcNo }</label>
									</div>
								</div>
							</c:if>
							<c:if test="${accAuth.cyCretType == 3 }">
								<div class="form-group" id="trcno">
									<label for="cyTrcNo" class="col-sm-2 control-label">税务登记证号:</label>
									<div class="col-sm-3" style="margin-top:8px">
										<label>${accAuth.cyTrcNo }</label>
									</div>
								</div>
								<div class="form-group" id="trcurl">
									<label for="cyTrcFileurl" class="col-sm-2 control-label">税务登记证:</label>
									<div class="field" class="col-sm-3">
										<img alt="税务登记证" style="margin-top: 30px; margin-left: 10px;" width="460px" height="280px"
											name="cyTrcFileurl" src="${accAuth.cyTrcFileurl }">
									</div>
								</div>
								<div class="form-group" id="blno">
									<label for="cyBlNo" class="col-sm-2 control-label">营业执照号:</label>
									<div class="col-sm-3" style="margin-top:8px">
										<label>${accAuth.cyBlNo }</span>
									</div>
								</div>
							</c:if>
							<div class="form-group">
								<label for="cyBlFileurl" class="col-sm-2 control-label">营业执照:</label>
								<div class="col-sm-3" style="margin-top:8px">
									<img alt="营业执照" style="margin-top: 30px; margin-left: 10px;" width="460px" height="280px"
										name="cyBlFileurl" src="${accAuth.cyBlFileurl }">
								</div>
							</div>
						</c:if>
						<div class="form-group">
							<label for="authDesc" class="col-sm-2 control-label">不通过原因:</label>
							<div class="col-sm-4">
								<div style="margin-top:10px">
									<input type="radio" name="authDesc" value="证件内容模糊，无法辨认证件内容" onclick="disablereason();">&nbsp;证件内容模糊，无法辨认证件内容。
								</div>
								<div style="margin-top:10px">
									<input type="radio" name="authDesc" value="证件内容与填写内容不符合" onclick="disablereason();">&nbsp;证件内容与填写内容不符合。
								</div>
								<div style="margin-top:10px">
									<input type="radio" name="authDesc" value="证件错误，不是合法的证件" onclick="disablereason();">&nbsp;证件错误，不是合法的证件。
								</div>
								<div style="margin-top:10px">
									<input type="radio" name="authDesc" value="自定义原因" onclick="enablereason();">&nbsp;自定义原因：
								</div>
								<div class="col-sm-3" style="margin-top:8px">
									<textarea id="reason" class="form-control" required="" aria-required="true" disabled="disabled" style="width:400px;height: 100px;"></textarea>
								</div>
							</div>
						</div>
						<div class="form-group">
                             <div class="col-sm-2 col-sm-offset-2">
                             	<input type="hidden" name="flag" id="flag">
                                <input type="hidden" name="apiAccount" id="apiAccount" value="${accAuth.apiAccount }">
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
			$("#reason").attr("disabled", false);
		}
		function disablereason(){
			$("#reason").attr("disabled", true);
		}
		function isOk(){
			
			$('#flag').val(1);			
			//询问框
			layer.confirm('你确认要通过此认证吗？', {
			  btn: ['确认','取消'] //按钮
			}, function(){
			  //layer.msg('已确认', {icon: 0}); 
			  $('#myform')[0].submit();
			}, function(){
			  //layer.msg('已取消', {icon: 0});
			});
		}
		function isFalse(){
			var authdesc=$('input:radio[name="authDesc"]:checked').val();
			var reason=$('#reason').val();
			if(authdesc==undefined){
				alert("请选择不通过的原因");
				return false;
			}else if(authdesc=="自定义原因"){
				if(reason == ''){
					alert("请输入不通过原因");
					return false;
				}
				authdesc=reason;
				$('#authDesc').val(reason);
			}
			$('#flag').val(0);
			//询问框
			layer.confirm('你确认不通过此认证吗？', {
			  btn: ['确认','取消'] //按钮
			}, function(){
			  //layer.msg('已确认', {icon: 0}); 
			  $('#myform')[0].submit();
			}, function(){
			  //layer.msg('已取消', {icon: 0});
			});
		}
	</script>
</body>
</html>
