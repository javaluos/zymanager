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
		style=" padding-bottom: -20px; margin-bottom: 10px;">
		<div class="ibox">
			<div class="ibox-title">
				<h5>
					账号管理<small>&nbsp;-->&nbsp;认证信息</small>
				</h5>
			</div>
			<div class="contentdv">
				<div style="padding-left: 100px;">
					<form id="myform" name="myform" method="post" action="" class="form-horizontal m-t" novalidate="novalidate">
						<div class="form-group">
							<label class="col-sm-2 control-label fb" for="merchantType">开发者类型:</label>
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
								<label class="col-sm-2 control-label fb" for="plName">真实姓名:</label>
								<div class="col-sm-3" style="margin-top:8px">
									<label> ${accAuth. plName}</label>
								</div>
							</div>
							<div class="form-group">
								<label for="plCretType" class="col-sm-2 control-label fb">证件类型:</label>
								<div class="col-sm-3" style="margin-top:8px">
									<label><c:if test="${accAuth.plCretType == 1}">
										身份证
									</c:if>
									<c:if test="${accAuth.plCretType == 2}">
										护照
									</c:if></label>
								</div>
							</div>
							<div class="form-group">
								<label for="plCretNo" class="col-sm-2 control-label fb">证件号码:</label>
								<div class="col-sm-3" style="margin-top:8px">
									<label>${accAuth.plCretNo}</label>
								</div>
							</div>
							<div class="form-group">
								<label for="plCretFileurl" class="col-sm-2 control-label fb">证件照片:</label>
								<div class="col-sm-3" style="margin-top:8px">
									<img alt="个人证件照片" style="margin-top: 30px; margin-left: 10px;" width="460px" height="280px"
										name="plCretFileurl" src="${accAuth.plCretFileurl }">
									<img style="margin-top:30px;margin-left:10px;" width="460px" height="280px" alt="" name="plCretBackurl" src="${accAuth.plCretBackurl }">
								</div>
							</div>
						</c:if>
						<c:if test="${accAuth.merchantType == 2}">
							<div class="form-group">
								<label for="cyName" class="col-sm-2 control-label fb">公司名称:</label>
								<div class="col-sm-3" style="margin-top:8px">
									<label>${accAuth.cyName}</label>
								</div>
							</div>
							<div class="form-group">
								<label for="cyAddress" class="col-sm-2 control-label fb">公司地址:</label>
								<div class="col-sm-5" style="margin-top:8px">
									<label>${accAuth.cyAddress}</label>
								</div>
							</div>
							<div class="form-group">
								<label for="cyIndustry" class="col-sm-2 control-label fb">所属行业:</label>
								<div class="col-sm-3" style="margin-top:8px">
									<label>${accAuth.cyIndustry}</label>
								</div>
							</div>
							<div class="form-group">
								<label for="cyCretType" class="col-sm-2 control-label fb">证件类型:</label>
								<div class="col-sm-3" style="margin-top:8px">
									<label><c:if test="${accAuth.cyCretType == 1 }">
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
									<label for="cyUscc" class="col-sm-2 control-label fb">统一社会信用代码:</label>
									<div class="col-sm-3" style="margin-top:8px">
										<label>${accAuth.cyUscc }</label>
									</div>
								</div>
							</c:if>
							<c:if test="${accAuth.cyCretType == 2 }">
								<div class="form-group" id="regno">
									<label for="cyRegistrNo" class="col-sm-2 control-label fb">注册号:</label>
									<div class="col-sm-3" style="margin-top:8px">
										<label>${accAuth.cyRegistrNo }</label>
									</div>
								</div>
								<div class="form-group" id="trcno">
									<label for="cyTrcNo" class="col-sm-2 control-label fb">税务登记证号:</label>
									<div class="col-sm-3" style="margin-top:8px">
										<label>${accAuth.cyTrcNo }</label>
									</div>
								</div>
							</c:if>
							<c:if test="${accAuth.cyCretType == 3 }">
								<div class="form-group" id="trcno">
									<label for="cyTrcNo" class="col-sm-2 control-label fb">税务登记证号:</label>
									<div class="col-sm-3" style="margin-top:8px">
										<label>${accAuth.cyTrcNo }</label>
									</div>
								</div>
								<div class="form-group" id="trcurl">
									<label for="cyTrcFileurl" class="col-sm-2 control-label fb">税务登记证:</label>
									<div class="field" class="col-sm-3" style="margin-top:8px">
										<img alt="税务登记证" style="margin-top: 30px; margin-left: 10px;" width="460px" height="280px"
											name="cyTrcFileurl" src="${accAuth.cyTrcFileurl }">
									</div>
								</div>
								<div class="form-group" id="blno">
									<label for="cyBlNo" class="col-sm-2 control-label fb">营业执照号:</label>
									<div class="col-sm-3" style="margin-top:8px">
										<label>${accAuth.cyBlNo }</label>
									</div>
								</div>
							</c:if>
							<div class="form-group">
								<label for="cyBlFileurl" class="col-sm-2 control-label fb">营业执照:</label>
								<div class="col-sm-3" style="margin-top:8px">
									<img alt="营业执照" style="margin-top: 30px; margin-left: 10px;" width="460px" height="280px"
										name="cyBlFileurl" src="${accAuth.cyBlFileurl }">
								</div>
							</div>
						</c:if>
						<div class="form-group">
                             <div class="col-sm-2 col-sm-offset-2">
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
			
		}
	</script>
</body>
</html>
