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
					账号管理<small>&nbsp;-->&nbsp;账户属性</small>
				</h5>
			</div>
			<div class="contentdv">
				<div style="padding-left: 100px;">
					<form id="myform" name="myform" method="post" action="" class="form-horizontal m-t" novalidate="novalidate">
						<div class="form-group">
							<label class="col-sm-2 control-label fb" for="plName"><span style="color:red;">*</span>客户名称:</label>
							<div class="col-sm-5">
							    <input type="hidden" id="apiaccount" class="form-control" value="${accountQuery.apiaccount}" style="width:281px;">
							    <input type="hidden" id="businessnames" class="form-control" value="${accountQuery.businessname}" style="width:281px;">
							    <input type="hidden" id="merchantphone" class="form-control" value="${accountQuery.merchantphone}" style="width:281px;">
							    <input type="hidden" id="merchantemail" class="form-control" value="${accountQuery.merchantemail}" style="width:281px;">
							    <input type="hidden" id="regstarttime" class="form-control" value="${accountQuery.regstarttime}" style="width:281px;">
							    <input type="hidden" id="regendtime" class="form-control" value="${accountQuery.regendtime}" style="width:281px;">
							    <input type="hidden" id="authflag" class="form-control" value="${accountQuery.authflag}" style="width:281px;">
							    <input type="hidden" id="apiAccount" class="form-control" value="${merchantAccount.apiAccount}" style="width:281px;">
							     <input type="hidden" id="apikey" value="${merchantAccount.apikey}"  class="form-control" style="width:281px;">
							    <input type="hidden" id="appId" value="${appInfo.appId}"  class="form-control" style="width:281px;">
								<label><input type="text" id="businessname" class="form-control" value="${merchantAccount.businessName}" style="width:281px;"></label>
							    <label>&nbsp;&nbsp;&nbsp;&nbsp;公司名称或个人姓名，不超过50字符</label>
							</div>
						</div>
						
						<div class="form-group">
							<label for="plCretType" class="col-sm-2 control-label fb">客户账号:</label>
							<div class="col-sm-3">
								<label><input type="text" id="merchantPhone"  class="form-control" value="${merchantAccount.merchantPhone}" disabled="true" style="width:281px;"></label>
							</div>
						</div>
						
						<!-- 客户密码 -->
						<div class="form-group">
							<label for="plCretType" class="col-sm-2 control-label fb">
							     <span style="color:red;">*</span>客户密码:
							</label>
							<div class="col-sm-5">
								<label><input type="text" id="merchantPwd" value="******" class="form-control"  style="width:281px;"></label>
								<label>&nbsp;&nbsp;&nbsp;&nbsp;6-20位数字、字母或特殊字符组合而成</label>
							</div>
						</div>
						
						<div class="form-group">
							<label for="plCretType" class="col-sm-2 control-label fb">
							     <span style="color:red;"></span>邮箱:
							</label>
							<div class="col-sm-3">
								<input type="text" id="email" class="form-control"  value="${merchantAccount.merchantEmail}" style="width:281px;">
							</div>
						</div>
						
						<div class="form-group">
							<label for="plCretType" class="col-sm-2 control-label fb">
							     <span style="color:red;"></span>登录账号:
							</label>
							<div class="col-sm-5">
								<label><input type="text" id="merchantAccount" class="form-control"  value="${merchantAccount.merchantAccount}" style="width:281px;"></label>
								<label>&nbsp;&nbsp;&nbsp;&nbsp;6-20位数字、字母组合</label>
							</div>
						</div>
						
						<!-- apiAccount -->
						<div class="form-group">
							<label for="plCretType" class="col-sm-2 control-label fb">apiAccount:</label>
							<div class="col-sm-5">
								<label>${merchantAccount.apiAccount}</label>
							</div>
						</div>
						
						<!-- apiKey -->
						<div class="form-group">
							<label for="plCretType" class="col-sm-2 control-label fb">apiKey:</label>
							<div class="col-sm-3">
								<label>
								${merchantAccount.apikey}
								</label>
							</div>
						</div>
						
						
						<!-- appId -->
						<div class="form-group">
							<label for="plCretType" class="col-sm-2 control-label fb">appId:</label>
							<div class="col-sm-5">
							     <c:forEach var="appInfo" items="${appInfos}" varStatus="index">
							          <label>${appInfo.appId}&nbsp;&nbsp;(${appInfo.appName})&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
		                         </c:forEach>
							</div>
						</div>
						
						<div class="form-group">
							<label for="plCretNo" class="col-sm-2 control-label fb">帐号认证:</label>
							<div class="col-sm-3" style="margin-top: 3px;">
								   <input type="radio" value="1" name="auth_flag" ${merchantAccount.authFlag==1?'checked':''}>
	                               <span class="spbtn">已认证</span>
	                               <input type="radio" value="-1" name="auth_flag" style="margin-left:20px;" ${merchantAccount.authFlag==-1?'checked':''}>
								   <span class="spbtn">未认证</span>
							</div>
						</div>
						
						
						<div class="form-group">
							<label for="plCretNo" class="col-sm-5 control-label fb" style="color: red;">
							--------------------------------------------------------------------------------------------------------------------------
							</label>
							<div></div>
						</div>
						
						
						<!------------------ 语音内容 -------------------->
						<div class="form-group">
							<label for="plCretNo" class="col-sm-2 control-label fb">语音通知:</label>
							<div class="col-sm-3">
								   <input type="radio" value="0" name="voicefileAuthFlag4" <c:if test="${voiceMerchantAttr.voicefileAuthFlag4==0}">checked="checked"</c:if>>
								   <span class="spbtn">语音文件审核</span>
								  
	                               <input type="radio" value="1" name="voicefileAuthFlag4" style="margin-left:20px;" <c:if test="${voiceMerchantAttr.voicefileAuthFlag4==1}">checked="checked"</c:if>>
								   <span class="spbtn">语音文件免审</span>
							</div>
						</div>
						<div class="form-group">
							<label for="plCretNo" class="col-sm-2 control-label fb">语音验证码:</label>
							<div class="col-sm-3">
								   <input type="radio" value="0" name="voicefileAuthFlag5" <c:if test="${voiceMerchantAttr.voicefileAuthFlag5==0}">checked="checked"</c:if>>
	                               <span class="spbtn">语音文件审核</span>
	                               
	                               <input type="radio" value="1" name="voicefileAuthFlag5" style="margin-left:20px;" <c:if test="${voiceMerchantAttr.voicefileAuthFlag5==1}">checked="checked"</c:if>>
								   <span class="spbtn">语音文件免审</span>
							</div>
						</div>
						
						
						
						<div class="form-group">
							<label for="plCretNo" class="col-sm-5 control-label fb" style="color: red;">
							--------------------------------------------------------------------------------------------------------------------------
							</label>
							<div></div>
						</div>
						
						
						<!------------------ 短信内容 -------------------->
					   <div class="form-group">
							<label for="plCretNo" class="col-sm-2 control-label fb">添加白名单:</label>
							<div class="col-sm-3" style="margin-top: 3px;">
	                              <input type="radio" value="0" name="isWhiteKey" <c:if test="${voiceMerchantAttr.isWhiteKey==0}">checked="checked"</c:if>>
	                              <span class="spbtn">是</span>
	                              
	                              <input type="radio" value="1" name="isWhiteKey" style="margin-left:20px;" <c:if test="${voiceMerchantAttr.isWhiteKey==1}">checked="checked"</c:if>>
								  <span class="spbtn">否</span>
							</div>
						</div>
						
						<div class="form-group">
							<label for="plCretNo" class="col-sm-2 control-label fb">短信审核策略:</label>
							<div class="col-sm-11" style="margin-left: -149px;">
								 <div class="form-group">
									<label for="plCretNo" class="col-sm-2 control-label fb">短信验证码:</label>
									<div class="col-sm-3" style="margin-top: 3px;">
			                              <input type="radio" value="0" name="signerAuthFlag9" <c:if test="${voiceMerchantAttr.signerAuthFlag9==0}">checked="checked"</c:if>>
			                              <span class="spbtn">签名审核</span>
			                              
			                              <input type="radio" value="1" name="signerAuthFlag9" style="margin-left:10px;" <c:if test="${voiceMerchantAttr.signerAuthFlag9==1}">checked="checked"</c:if>>
			                              <span class="spbtn">签名免审</span>
			                              
			                              &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
			                              
			                              <input type="radio" value="0" name="templateAuthFalg9" <c:if test="${voiceMerchantAttr.templateAuthFalg9==0}">checked="checked"</c:if>>
			                              <span class="spbtn">模板审核</span>
			                              
			                              <input type="radio" value="1" name="templateAuthFalg9" style="margin-left:10px;" <c:if test="${voiceMerchantAttr.templateAuthFalg9==1}">checked="checked"</c:if>>
										  <span class="spbtn">模板免审</span>
									</div>
								 </div>
								 <div class="form-group">
									<label for="plCretNo" class="col-sm-2 control-label fb">短信通知:</label>
									<div class="col-sm-3" style="margin-top: 3px;">
										   <input type="radio" value="0" name="signerAuthFlag8" <c:if test="${voiceMerchantAttr.signerAuthFlag8==0}">checked="checked"</c:if>>
			                               <span class="spbtn">签名审核</span>
			                               
			                               <input type="radio" value="1" name="signerAuthFlag8" style="margin-left:10px;" <c:if test="${voiceMerchantAttr.signerAuthFlag8==1}">checked="checked"</c:if>>
			                               <span class="spbtn">签名免审</span>
			                               
			                               &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
			                              
			                               <input type="radio" value="0" name="templateAuthFalg8" <c:if test="${voiceMerchantAttr.templateAuthFalg8==0}">checked="checked"</c:if>>
			                               <span class="spbtn">模板审核</span>
			                               
			                               <input type="radio" value="1" name="templateAuthFalg8" style="margin-left:10px;" <c:if test="${voiceMerchantAttr.templateAuthFalg8==1}">checked="checked"</c:if>>
										   <span class="spbtn">模板免审</span>
									</div>
								 </div>
								 <div class="form-group">
									<label for="plCretNo" class="col-sm-2 control-label fb">营销短信:</label>
									<div class="col-sm-3" style="margin-top: 3px;">
										   <input type="radio" value="0" name="signerAuthFlag11" <c:if test="${voiceMerchantAttr.signerAuthFlag11==0}">checked="checked"</c:if>>
			                               <span class="spbtn">签名审核</span>
			                               
			                               <input type="radio" value="1" name="signerAuthFlag11" style="margin-left:10px;" <c:if test="${voiceMerchantAttr.signerAuthFlag11==1}">checked="checked"</c:if>>
			                               <span class="spbtn">签名免审</span>
			                               
			                               &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
			                                
			                               <input type="radio" value="0" name="templateAuthFalg11" <c:if test="${voiceMerchantAttr.templateAuthFalg11==0}">checked="checked"</c:if>>
			                               <span class="spbtn">模板审核</span>
			                               
			                               <input type="radio" value="1" name="templateAuthFalg11" style="margin-left:10px;" <c:if test="${voiceMerchantAttr.templateAuthFalg11==1}">checked="checked"</c:if>>
										   <span class="spbtn">模板免审</span>
									</div>
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label for="plCretNo" class="col-sm-2 control-label fb">短信拦截策略:</label>
							<div class="col-sm-11" style="margin-left: -219px;">
								 <div class="form-group">
									<label for="plCretNo" class="col-sm-3 control-label fb">平台级敏感词拦截:</label>
									<div class="col-sm-3" style="margin-top: 3px;">
		                               <input type="radio" value="1" name="isBlackKey" <c:if test="${voiceMerchantAttr.isBlackKey==1}">checked="checked"</c:if>>
		                               <span class="spbtn">是</span>
		                              
		                               <input type="radio" value="0" name="isBlackKey" style="margin-left:20px;" <c:if test="${voiceMerchantAttr.isBlackKey==0}">checked="checked"</c:if>>
									   <span class="spbtn">否</span>
									</div>
								 </div>
					             
					             <div class="form-group">
		                            <label for="plCretNo" class="col-sm-3 control-label fb">客户免拦截策略设置:</label>
		                            <div class="col-sm-3">
		                                <select class="form-control" id="smsNoFilterPolicy" style="width: 150px;">
		                                
		                                    <option value=""></option>
		                                    <c:forEach var="blackKeyPolicy" items="${blackKeyPolicyList }" varStatus="index">
		                                        <option value="${blackKeyPolicy.id }" <c:if test="${blackKeyPolicy.id == voiceMerchantAttr.smsNoFilterPolicy }">selected="selected"</c:if>>${blackKeyPolicy.policyName }</option>
		                                    </c:forEach>
		                                </select>
		                            </div>
		                        </div>
                        
		                        <div class="form-group">
		                            <label for="plCretNo" class="col-sm-3 control-label fb">客户拦截策略设置:</label>
		                            <div class="col-sm-3">
		                                <select class="form-control" id="smsFilterPolicy" style="width: 150px;">
		                                
		                                    <option value=""></option>
		                                    <c:forEach var="blackKeyPolicy" items="${blackKeyPolicyList }" varStatus="index">
		                                        <option value="${blackKeyPolicy.id }" <c:if test="${blackKeyPolicy.id == voiceMerchantAttr.smsFilterPolicy }">selected="selected"</c:if>>${blackKeyPolicy.policyName }</option>
		                                    </c:forEach>
		                                </select>
		                            </div>
		                        </div>
                        
                                <div class="form-group">
									<label for="plCretNo" class="col-sm-3 control-label fb">拦截后是否人工审核:</label>
									<div class="col-sm-3" style="margin-top: 3px;">
		                               <input type="radio" value="1" name="isBlackAuditFlag" <c:if test="${voiceMerchantAttr.isBlackAuditFlag==1}">checked="checked"</c:if>>
		                               <span class="spbtn">是</span>
		                              
		                               <input type="radio" value="0" name="isBlackAuditFlag" style="margin-left:20px;" <c:if test="${voiceMerchantAttr.isBlackAuditFlag==0}">checked="checked"</c:if>>
									   <span class="spbtn">否</span>
									</div>
								</div>
								 
							</div>
						</div>
						
						<div class="form-group">
							<label for="plCretType" class="col-sm-2 control-label fb">CMPP短信类型:</label>
							<div class="col-sm-5">
								 <input type="radio" value="8" name="smsType" <c:if test="${cmppAccount.smsType!=9 and cmppAccount.smsType!=11}">checked="checked"</c:if>>
                                 <span class="spbtn">短信通知</span>
                              
                                 <input type="radio" value="9" name="smsType" <c:if test="${cmppAccount.smsType==9}">checked="checked"</c:if> style="margin-left:20px;">
							     <span class="spbtn">短信验证码</span>
							     
							     <input type="radio" value="11" name="smsType" <c:if test="${cmppAccount.smsType==11}">checked="checked"</c:if> style="margin-left:20px;">
							     <span class="spbtn">营销短信</span>
							</div>
						</div>
						
						<div class="form-group">
							<label for="plCretType" class="col-sm-2 control-label fb">CMPP账号:</label>
							<div class="col-sm-5">
								 <label>
								   <input type="text" class="form-control" name="cmppAccessAccount" id="cmppAccessAccount"  value="${cmppAccount.clientId}" style="width: 180px;" maxlength="6"/>
								   <input type="hidden" class="form-control" name="cmppId" id="cmppId"  value="${cmppAccount.id}" style="width: 180px;" maxlength="6"/>
								 </label> 
								 <label>&nbsp;&nbsp;&nbsp;&nbsp;只能填写数字，长度为6位</label>
							</div>
						</div>
						
						<div class="form-group">
							<label for="plCretNo" class="col-sm-2 control-label fb">CMPP密码:</label>
							<div class="col-sm-5">
								 <label><input type="text" class="form-control"  name="cmppAccessPwd" id="cmppAccessPwd" value="${cmppAccount.pwd}" style="width: 180px;" maxlength="20"/></label> 
								 <label>&nbsp;&nbsp;&nbsp;&nbsp;6-20位数字、字母或特殊字符组合而成</label>
							</div>
						</div>
						
						<div class="form-group">
							<label for="plCretNo" class="col-sm-2 control-label fb">CMPP接入码:</label>
							<div class="col-sm-5">
								 <label><input type="text" class="form-control" name="cmppAccessCode" id="cmppAccessCode"  value="${cmppAccount.baseExtNumber}"  style="width: 180px;" maxlength="20"/>&nbsp;&nbsp;</label>
								 <label>&nbsp;&nbsp;&nbsp;&nbsp;(默认值：106900008888)&nbsp;&nbsp;只能填写数字，可为空，长度为8-20位</label>  
							</div>
						</div>
						
						<div class="form-group">
							<label for="plCretNo" class="col-sm-2 control-label fb">拓展号配置:</label>
							<div class="col-sm-5">
							   <span class="spbtn">移动：</span>
							   <input type="text" name="ydExtNumber" id="ydExtNumber" value="${voiceMerchantAttr.ydExtNumber}" style="width: 80px;" maxlength="12"/>  
							   <span class="spbtn" style="margin-left: 20px;">联通：</span>
							   <input type="text" name="ltExtNumber" id="ltExtNumber" value="${voiceMerchantAttr.ltExtNumber}" style="width: 80px;" maxlength="12"/>  
							   <span class="spbtn" style="margin-left: 20px;">电信:</span>
							   <input type="text" name="dxExtNumber" id="dxExtNumber" value="${voiceMerchantAttr.dxExtNumber}" style="width: 80px;" maxlength="12"/>  
							
							    <span>&nbsp;&nbsp;&nbsp;&nbsp;只能填写数字，可为空，最长12位</span>
							</div>
						</div>
						
						
						<div class="form-group">
							<label for="plCretNo" class="col-sm-2 control-label fb">门限(账号提交速度):</label>
							<div class="col-sm-5">
								 <label><input type="text" class="form-control" name="accSendPerSecond" id="accSendPerSecond" value="${voiceMerchantAttr.accSendPerSecond}" style="width: 180px;" maxlength="20"/>&nbsp;&nbsp;</label>
								 <label>&nbsp;条/秒 &nbsp;&nbsp;&nbsp;&nbsp;<font>如不限定提交速度，则默认0</font></label>
							</div>
						</div>
						
						
						<div class="form-group">
							<label for="plCretNo" class="col-sm-2 control-label fb">单号码下发数:</label>
							<div class="col-sm-3">
								   <input type="radio" value="1" name="dowmMobileFlag" ${voiceMerchantAttr.dowmMobileFlag!=0?'checked':''}>
	                               <span class="spbtn">开启</span>
	                               <input type="radio" value="0" name="dowmMobileFlag" style="margin-left:20px;" ${voiceMerchantAttr.dowmMobileFlag==0?'checked':''}>
								   <span class="spbtn">关闭</span>
							</div>
						</div>
						<div class="form-group">
							<label for="plCretNo" class="col-sm-2 control-label fb"></label>
							<div class="col-sm-9">
								  <input type="text" name="secondItem" id="secondItem" value="${voiceMerchantAttr.secondItem==-1?2:voiceMerchantAttr.secondItem}" style="width: 80px;"/>&nbsp;条
								  <input type="text" name="secondNum"  id="secondNum" value="${voiceMerchantAttr.secondNum==-1?60:voiceMerchantAttr.secondNum}"  style="width: 80px;"/>&nbsp;秒
								  
								  &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
								  
								  <input type="text" name="minuteItem" id="minuteItem" value="${voiceMerchantAttr.minuteItem==-1?5:voiceMerchantAttr.minuteItem}"  style="width: 80px;"/>&nbsp;条
								  <input type="text" name="minuteNum"  id="minuteNum" value="${voiceMerchantAttr.minuteNum==-1?60:voiceMerchantAttr.minuteNum}"  style="width: 80px;"/>&nbsp;分
								  
								  &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
								  
								  <input type="text" name="hourItem" id="hourItem" value="${voiceMerchantAttr.hourItem==-1?8:voiceMerchantAttr.hourItem}"  style="width: 80px;"/>&nbsp;条
								  <input type="text" name="hourNum"  id="hourNum" value="${voiceMerchantAttr.hourNum==-1?24:voiceMerchantAttr.hourNum}"  style="width: 80px;"/>&nbsp;时
							
							      <span>&nbsp;&nbsp;&nbsp;&nbsp;为&nbsp;0&nbsp;时条件不生效</label>
							</div>
						</div>
						
						<div class="form-group">
                            <label for="plCretNo" class="col-sm-2 control-label fb">导流机制:</label>
                            <div class="col-sm-3">
                                <select class="form-control" id="smsChannelPolicy" style="width: 300px;">
                                
                                    <option value=""></option>
                                    <c:forEach var="smsChannelPolicy" items="${smsChannelPolicyList }" varStatus="index">
                                        <option value="${smsChannelPolicy.id }" <c:if test="${smsChannelPolicy.id == voiceMerchantAttr.smsChannelPolicy }">selected="selected"</c:if>>${smsChannelPolicy.policyName }</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
						<div class="form-group">
                             <div class="col-sm-2 col-sm-offset-2">
                                 <button class="btn btn-primary" type="button" style="width: 60px;margin-left: 20px;" onclick="submitAttr()" id="btnsave">修改</button>
                                 <button class="btn btn-primary" type="button" style="width: 60px;margin-left: 20px;" onclick="history.go(-1)" id="btnsave">返回</button>
                             </div>
                        </div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
<script src="/js/md5.js"></script>		
<script type="text/javascript">
	function submitAttr(){
		// 成功后保存的查询结果
		var apiaccount=$('#apiaccount').val();
		var businessnames=$('#businessnames').val();
		var merchantphone=$('#merchantphone').val();
		var merchantemail=$('#merchantemail').val();
		var regstarttime=$('#regstarttime').val();
		var regendtime=$('#regendtime').val();
		var authflag=$('#authflag').val();
		
		// 需要修改的字段
		
		var apiAccount=$('#apiAccount').val();
		var apikey=$('#apikey').val();
		var appId=$('#appId').val();
		var merchantPwd=$('#merchantPwd').val();
		var merchantPhone=$('#merchantPhone').val();
		var merchantAccount=$('#merchantAccount').val();
		var email=$('#email').val();
		
		//var apiAccount=$('#apiAccount').val();
		var businessname=$('#businessname').val();
		var signerAuthFlag9=$('input[name="signerAuthFlag9"]:checked').val();
		var templateAuthFalg9=$('input[name="templateAuthFalg9"]:checked').val();
		var signerAuthFlag8=$('input[name="signerAuthFlag8"]:checked').val();
		var templateAuthFalg8=$('input[name="templateAuthFalg8"]:checked').val();
		var signerAuthFlag11=$('input[name="signerAuthFlag11"]:checked').val();
		var templateAuthFalg11=$('input[name="templateAuthFalg11"]:checked').val();
		var voicefileAuthFlag4=$('input[name="voicefileAuthFlag4"]:checked').val();
		var voicefileAuthFlag5=$('input[name="voicefileAuthFlag5"]:checked').val();
		var isBlackKey=$('input[name="isBlackKey"]:checked').val();
		var isWhiteKey=$('input[name="isWhiteKey"]:checked').val();
		var modifly_auth_flag = $('input[name="auth_flag"]:checked').val();
		var smsChannelPolicy = $('#smsChannelPolicy').val();
		var dowmMobileFlag = $('input[name="dowmMobileFlag"]:checked').val();
		var secondItem= $('#secondItem').val();
		var secondNum= $('#secondNum').val();
		var minuteItem= $('#minuteItem').val();
		var minuteNum= $('#minuteNum').val();
		var hourItem= $('#hourItem').val();
		var hourNum= $('#hourNum').val();
		
		var smsNoFilterPolicy= $('#smsNoFilterPolicy').val();
		var smsFilterPolicy= $('#smsFilterPolicy').val();
		var isBlackAuditFlag= $('input[name="isBlackAuditFlag"]:checked').val();
		
		var cmppAccessCode= $('#cmppAccessCode').val();
		var ydExtNumber= $('#ydExtNumber').val();
		var ltExtNumber= $('#ltExtNumber').val();
		var dxExtNumber= $('#dxExtNumber').val();
		var accSendPerSecond= $('#accSendPerSecond').val();
		var cmppAccessAccount= $('#cmppAccessAccount').val();
		var cmppAccessPwd= $('#cmppAccessPwd').val();
		var cmppId=$('#cmppId').val();
		var smsType=$('input[name="smsType"]:checked').val();
		
		/**  客户名称校验**/
		if(businessname=='' && businessname==undefined && businessname==null){
			layer.tips('客户名称不能为空！.', $('#businessname'));
			$('#businessname').focus();
			return false;
		}else{
			if(businessname.length>50){
				layer.tips('客户名称不超过50字符！.', $('#businessname'));
				$('#businessname').focus();
				return false;
			}
		}
		
		/**  客户手机号校验**/
		if(merchantPhone=='' && merchantPhone==undefined && merchantPhone==null){
			layer.tips('手机号不能为空！.', $('#merchantPhone'));
			$('#merchantPhone').focus();
			return false;
		}else{
			var myreg = /^(((13[0-9]{1})|(14[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
			if(!myreg.test(merchantPhone)) 
			{ 
				layer.tips('请输入有效的手机号！.', $('#merchantPhone'));
				$('#merchantPhone').focus();
				return false;
			} 
		}
		
		/**  客户邮箱校验**/
		if(email!='' && email==undefined && email==null){
			var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
			if(!reg.test(email)){
				layer.tips('请输入有效的邮箱！.', $('#email'));
				$('#email').focus();
				return false;
			}
		}
		
		/**  客户账号校验**/
		if(merchantAccount!='' && merchantAccount!=undefined && merchantAccount==null){
			var reg =  /^[A-Za-z0-9]*$/;  
			if(!reg.test(merchantAccount) || merchantAccount.length<6 || merchantAccount.length>20){//6-20位数字、字母组合
				layer.tips('客户账号必须是6-20位数字、字母组合！.', $('#merchantAccount'));
				$('#merchantAccount').focus();
				return false;
			}
		}

		/**  客户密码校验**/
		if(merchantPwd=='' && merchantPwd==undefined && merchantPwd==null){
			layer.tips('客户密码不能为空！.', $('#merchantPwd'));
			$('#merchantPwd').focus();
			return false;
		}else{
			var reg = /^[a-zA-Z0-9!@#$%^&*()_+|{}?><\-\]\\[\/]*$/;
			if(!reg.test(merchantPwd) || merchantPwd.length<6 || merchantPwd.length>20){
				layer.tips('客户密码必须是6-20位数字、字母或特殊字符组合而成！.', $('#merchantPwd'));
				$('#merchantPwd').focus();
				return false;
			}
		}
		
		/** 联系人电话校验**/
		/* if(phone!=='' && phone!==undefined && phone!==null){
			if(phone.length>11){
				layer.tips('联系人电话长度不能超过11位！.', $('#phone'));
				$('#phone').focus();
				return false;
			}
		} */
		
		var re = /^(?:0|[1-9][0-9]*)$/;
		var reext = /^(?:0|[0-9][0-9]*)$/;
	    /** Cmpp账号 **/
		if(cmppAccessAccount!=='' && cmppAccessAccount!==undefined && cmppAccessAccount!==null){
			if(cmppAccessAccount.length!=6){
				 layer.tips('Cmpp账号长度只能为6位！.', $('#cmppAccessAccount'));
				   $('#cmppAccessAccount').focus();
				   return false;
			} 
			if(!re.test(cmppAccessAccount)){
				  layer.tips('请输入正整数！.', $('#cmppAccessAccount'));
				   $('#cmppAccessAccount').focus();
				   return false;
			}
		}
	    
	    /** Cmpp密码 **/
		if(cmppAccessPwd!=='' && cmppAccessPwd!==undefined && cmppAccessPwd!==null){
			var reg = /^[a-zA-Z0-9!@#$%^&*()_+|{}?><\-\]\\[\/]*$/;
			if(!reg.test(cmppAccessPwd) || cmppAccessPwd.length<6 || cmppAccessPwd.length>20){
				layer.tips('客户密码必须是6-20位数字、字母或特殊字符组合而成！.', $('#cmppAccessPwd'));
				$('#cmppAccessPwd').focus();
				return false;
			}
		}
		
		var re = /^(?:0|[1-9][0-9]*)$/;
		var reext = /^(?:0|[0-9][0-9]*)$/;
		if(secondItem!=='' && secondItem!==undefined && secondItem!==null){
			if(!re.test(secondItem)){
				  layer.tips('请输入正整数！.', $('#secondItem'));
				   $('#secondItem').focus();
				   return false;
			}
		}
		if(secondNum!=='' && secondNum!==undefined && secondNum!==null){
			if(!re.test(secondNum)){
				  layer.tips('请输入正整数！.', $('#secondNum'));
				   $('#secondNum').focus();
				   return false;
			}
		}
		if(minuteItem!=='' && minuteItem!==undefined && minuteItem!==null){
			if(!re.test(minuteItem)){
				  layer.tips('请输入正整数！.', $('#minuteItem'));
				   $('#minuteItem').focus();
				   return false;
			}
		}
		if(minuteNum!=='' && minuteNum!==undefined && minuteNum!==null){
			if(!re.test(minuteNum)){
				  layer.tips('请输入正整数！.', $('#minuteNum'));
				   $('#minuteNum').focus();
				   return false;
			}
		}
		if(hourItem!=='' && hourItem!==undefined && hourItem!==null){
			if(!re.test(hourItem)){
				  layer.tips('请输入正整数！.', $('#hourItem'));
				   $('#hourItem').focus();
				   return false;
			}
		}
		if(hourNum!=='' && hourNum!==undefined && hourNum!==null){
			if(!re.test(hourNum)){
				  layer.tips('请输入正整数！.', $('#hourNum'));
				   $('#hourNum').focus();
				   return false;
			}
		}
		
		
		if(cmppAccessCode!=='' && cmppAccessCode!==undefined && cmppAccessCode!==null){
			if(!reext.test(cmppAccessCode)){
				  layer.tips('请输入数字.', $('#cmppAccessCode'));
				   $('#cmppAccessCode').focus();
				   return false;
			}
		}else {
			// layer.tips('请输入接入号.', $('#cmppAccessCode'));
			// $('#cmppAccessCode').focus();
			// return false;
		}
		
		if(ydExtNumber!=='' && ydExtNumber!==undefined && ydExtNumber!==null){
			if(!reext.test(ydExtNumber)){
				  layer.tips('请输入数字.', $('#ydExtNumber'));
				   $('#ydExtNumber').focus();
				   return false;
			}
		}
		
		if(ltExtNumber!=='' && ltExtNumber!==undefined && ltExtNumber!==null){
			if(!reext.test(ltExtNumber)){
				  layer.tips('请输入数字.', $('#ltExtNumber'));
				   $('#ltExtNumber').focus();
				   return false;
			}
		}
		
		if(dxExtNumber!=='' && dxExtNumber!==undefined && dxExtNumber!==null){
			if(!reext.test(dxExtNumber)){
				  layer.tips('请输入数字.', $('#dxExtNumber'));
				   $('#dxExtNumber').focus();
				   return false;
			}
		}
		
				
		var params={
				    "apiAccount":apiAccount,"businessname":businessname,"signerAuthFlag9":signerAuthFlag9,"templateAuthFalg9":templateAuthFalg9,
				    "signerAuthFlag8":signerAuthFlag8,"templateAuthFalg8":templateAuthFalg8,"signerAuthFlag11":signerAuthFlag11,"templateAuthFalg11":templateAuthFalg11,
				     "voicefileAuthFlag4":voicefileAuthFlag4,"voicefileAuthFlag5":voicefileAuthFlag5,"isBlackKey":isBlackKey,"isWhiteKey":isWhiteKey,
				     "authFlag":modifly_auth_flag,"smsChannelPolicy":smsChannelPolicy,"dowmMobileFlag":dowmMobileFlag,
				     "secondItem":secondItem,"secondNum":secondNum,"minuteItem":minuteItem,"minuteNum":minuteNum,"hourItem":hourItem,"hourNum":hourNum,
				     "smsNoFilterPolicy":smsNoFilterPolicy,"smsFilterPolicy":smsFilterPolicy,"isBlackAuditFlag":isBlackAuditFlag,
				     "cmppAccessCode":cmppAccessCode,"ydExtNumber":ydExtNumber,"ltExtNumber":ltExtNumber,"dxExtNumber":dxExtNumber,
				     "accSendPerSecond":accSendPerSecond,"cmppAccessAccount":cmppAccessAccount,"cmppAccessPwd":cmppAccessPwd
				   };
		var accountParams={
		    		"businessname":businessname,"apiAccount":apiAccount,"apikey":apikey,
		    		"appId":appId,"merchantPhone":merchantPhone,"merchantPwd":hex_md5(merchantPwd),
		    		"merchantEmail":email,"merchantAccount":merchantAccount
		};
		var cmppParams={
		    		"id":cmppId,"apiAccount":apiAccount,"apikey":apikey,"appId":appId,"clientId":cmppAccessAccount,"pwd":cmppAccessPwd,
		    		"merchantAccount":merchantAccount,"smsType":smsType,"baseExtNumber":cmppAccessCode,"startFlag":"1"
		};
		$.ajax({
			  type: 'post',
			  url:'/account/saveMerchantAttr',
			  dataType:'json',
			  data:{params: JSON.stringify(params),accountParams:JSON.stringify(accountParams),cmppParams:JSON.stringify(cmppParams)},
			  success: function (result) {
				  if(result.code=="1"){
					layer.confirm('客户属性修改成功', {
					    btn: ['确认'] //按钮
					}, function(){
						var obj=new Object();
						obj.businessName=businessnames;
						obj.apiaccount=apiaccount;
						obj.merchantphone=merchantphone;
						obj.merchantemail=merchantemail;
						obj.regstarttime=regstarttime;
						obj.regendtime=regendtime;
						obj.authflag=authflag;
						var queryParams=JSON.stringify(obj);
						window.location.href="/account/actlist?params="+queryParams;
					});
				  }else if(result.code=="180"){
					  layer.confirm('该手机已经被注册', {
						    btn: ['确认'] //按钮
					    }, function(){
					    	layer.closeAll('dialog');
						});
				  }else if(result.code=="188"){
					  layer.confirm('该邮箱已经存在了', {
						    btn: ['确认'] //按钮
					    }, function(){
					    	layer.closeAll('dialog');
						});
				  }else if(result.code=="189"){
					  layer.confirm('该客户账号已经存在了', {
						    btn: ['确认'] //按钮
					    }, function(){
					    	layer.closeAll('dialog');
						});
				  }else if(result.code=="199"){
					  layer.confirm('该CMPP账号已经存在了', {
						    btn: ['确认'] //按钮
					    }, function(){
					    	layer.closeAll('dialog');
						});
				  } else{
					layer.confirm('客户属性修改失败', {
					    btn: ['确认'] //按钮
				    }, function(){
				    	window.location.href="/account/actlist?merchantAccount.businessName="+businessnames;
					});
				  }
	          }
		});
	}
	
	$(document).ready(function(){
		$(".spbtn").click(function(){
		   $(this).prev().click();
		});
	});
</script>
</body>
</html>
