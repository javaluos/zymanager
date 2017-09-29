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
					短信通道管理<small>&nbsp;-->&nbsp;
					<c:choose>
					    <c:when test="${not empty obj.channelId}">   
						             通道修改
						</c:when>
						<c:otherwise>   
					                          通道录入         
					    </c:otherwise>
					</c:choose>
					</small>
				</h5>
			</div>
			<!-- 
			<div class="ibox-info">
			    <div style="margin-top: 5px;">
	                <p>说明：</p>
                        <ul>
                            <li>1、通道编号系统唯一,与通道开发对接,请按合理规则输入。</li>
                        </ul>
                </div>
			</div> -->
			<div class="contentdv">
			   <div style="padding-left: 10px;">
	               <form class="form-horizontal m-t" id="myForm" method="post" action="/smschannel/smschannel_save" novalidate="novalidate">
                         <input type="hidden" id="schannelMainCode" value="${obj.channelMainCode }"/>
                         <div class="form-group">
                             <label class="col-sm-2 control-label">运营商类型：</label>
                             <div class="col-sm-3">
                                 <label>
                                      <input type="radio" id="operateType0" name="operateType" checked="checked" value="0"
                                       <c:if test="${not empty obj.channelId }"> disabled="disabled" </c:if> > 三网合一
                                 </label>
                                 <label style="margin-left: 25px;">
                                      <input type="radio" id="operateTyp1" name="operateType" <c:if test="${obj.operateType == 1 }"> checked="checked" </c:if> value="1"
                                       <c:if test="${not empty obj.channelId }"> disabled="disabled" </c:if> > 移动专网
                                 </label>
                                 <label style="margin-left: 25px;">
                                      <input type="radio" id="operateTyp2" name="operateType" <c:if test="${obj.operateType == 2 }"> checked="checked" </c:if> value="2"
                                       <c:if test="${not empty obj.channelId }"> disabled="disabled" </c:if> > 电信专网
                                 </label>                                 
                                 <label style="margin-left: 25px;">
                                      <input type="radio" id="operateTyp3" name="operateType" <c:if test="${obj.operateType == 3 }"> checked="checked" </c:if> value="3"
                                       <c:if test="${not empty obj.channelId }"> disabled="disabled" </c:if> > 联通专网
                                 </label>
                             </div>
                         </div>

                         <div class="form-group">
                             <label class="col-sm-2 control-label"><span style="color: red; vertical-align: -3px;">*&nbsp;</span>通道名称：</label>
                             <div class="col-sm-3">
                                 <input id="channelName" name="channelName" class="form-control" type="text" value="${obj.channelName }" maxlength="50"
                                      style="display: inline-block;">
                             </div>
                             <label>(如：行业-烽火万家)</label>
                            
                         </div>
                         <div class="form-group">
                             <label class="col-sm-2 control-label"><span style="color: red; vertical-align: -3px;">*&nbsp;</span>通道编号：</label>
                             <div class="col-sm-3">
                                 <input id="channelMainCode" name="channelMainCode" class="form-control" type="text" value="${obj.channelMainCode }" maxlength="50" 
                                   <c:if test="${not empty obj.channelId }"> readonly="readonly" </c:if> > 
                             </div>
                             <label>(如：HY-FHWJ)</label>
                         </div>
                         <div class="form-group" style="<c:if test="${empty obj.channelId}"> display: none;</c:if>">
                             <label class="col-sm-2 control-label"><span style="color: red; vertical-align: -3px;">*&nbsp;</span>通道ID：</label>
                             <div class="col-sm-3">
                                 <input id="channelId" name="channelId" class="form-control" type="text" value="${obj.channelId }" maxlength="50"
                                      style="display: inline-block;" <c:if test="${not empty obj.channelId}"> disabled ='disabled' </c:if>>
                             </div>
                         </div>

                        <div class="form-group">
                             <label class="col-sm-2 control-label">通道号：</label>
                             <div class="col-sm-3">
                                 <input id="channelCode" name="channelCode" class="form-control" type="text" value="${obj.channelCode }" maxlength="50">
                             </div>
                         </div>
                         
                         <div class="form-group">
                             <label class="col-sm-2 control-label">是否直连：</label>
                             <div class="col-sm-3">
                                 <label>
                                      <input type="radio" id="channelAccessMode0" name="channelAccessMode" checked="checked" value="0"> 否
                                 </label>
                                 <label style="margin-left: 60px;">
                                      <input type="radio" id="channelAccessMode1" name="channelAccessMode" <c:if test="${obj.channelAccessMode == 1 }"> checked="checked" </c:if> value="1"
                                        > 是
                                 </label>
                             </div>
                         </div>
                                                  
                         <div class="form-group">
                             <label class="col-sm-2 control-label"><span style="color: red; vertical-align: -3px;">*&nbsp;</span>通道类型：</label>
                             <div class="col-sm-3">
                                 <select class="form-control m-b" id="channelType" >
                                        <option value="-1"></option>
										<option value="1" <c:if test="${obj.channelType == 1 }"> selected='selected' </c:if>>通知</option>
										<option value="2" <c:if test="${obj.channelType == 2 }"> selected='selected' </c:if>>验证码</option>
										<option value="3" <c:if test="${obj.channelType == 3 }"> selected='selected' </c:if>>营销</option>
										<option value="4" <c:if test="${obj.channelType == 4 }"> selected='selected' </c:if>>通知、验证码</option>
										<option value="5" <c:if test="${obj.channelType == 5 }"> selected='selected' </c:if>>通知、验证码、营销</option>
                                 </select>
                             </div>
                         </div>
                         
                         <div class="form-group">
                             <label class="col-sm-2 control-label"><span style="color: red; vertical-align: -3px;">*&nbsp;</span>是否支持上行：</label>
                             <div class="col-sm-3">
                                 <select class="form-control m-b" id="moFlag" >
                                        <option value="-1"></option>
                                        <option value="1" <c:if test="${obj.moFlag == 1 }"> selected='selected' </c:if>>支持</option>
                                        <option value="0" <c:if test="${obj.moFlag == 0 }"> selected='selected' </c:if>>不支持</option>
                                 </select>
                             </div>
                         </div>
                         
                        
                        <div class="form-group">
                             <label class="col-sm-2 control-label"><span style="color: red; vertical-align: -3px;">*&nbsp;</span>是否支持长短信：</label>
                             <div class="col-sm-3">
                                 <select class="form-control m-b" id="longSmsFlag" >
                                        <option value="-1"></option>
                                        <option value="1" <c:if test="${obj.longSmsFlag == 1 }"> selected='selected' </c:if>>支持</option>
                                        <option value="0" <c:if test="${obj.longSmsFlag == 0 }"> selected='selected' </c:if>>不支持</option>
                                 </select>
                             </div>
                         </div>
                         
                        <div class="form-group">
                             <label class="col-sm-2 control-label"><span style="color: red; vertical-align: -3px;">*&nbsp;</span>签名报备：</label>
                             <div class="col-sm-3">
                                 <select class="form-control m-b" id="signerAudit" >
                                        <option value="-1"></option>
                                        <option value="1" <c:if test="${obj.signerAudit == 1 }"> selected='selected' </c:if>>报备</option>
                                        <option value="0" <c:if test="${obj.signerAudit == 0 }"> selected='selected' </c:if>>免报备</option>
                                 </select>
                             </div>
                         </div>
                         
                        <div class="form-group">
                             <label class="col-sm-2 control-label"><span style="color: red; vertical-align: -3px;">*&nbsp;</span>模板报备：</label>
                             <div class="col-sm-3">
                                 <select class="form-control m-b" id="templateAudit" >
                                        <option value="-1"></option>
                                        <option value="1" <c:if test="${obj.templateAudit == 1 }"> selected='selected' </c:if>>报备</option>
                                        <option value="0" <c:if test="${obj.templateAudit == 0 }"> selected='selected' </c:if>>免报备</option>
                                 </select>
                             </div>
                         </div>  
                         
                         <div class="form-group">
                             <label class="col-sm-2 control-label"><span style="color: red; vertical-align: -3px;">*&nbsp;</span>落地省份：</label>
                             <div class="col-sm-3">
                                 <select class="form-control m-b" name="dtnProvince" id="dtnProvince" onchange="setuseprov();">
                                 	<option value="平台商" <c:if test="${obj.dtnProvince == '平台商' }">selected="selected"</c:if> >平台商</option>
                                 	<c:forEach var="province" items="${provinceList }">
                                 		<option value="${province }" <c:if test="${obj.dtnProvince == province}">selected="selected"</c:if> >${province }</option>
                                 	</c:forEach>
                                 </select>
                             </div>
                         </div> 
                         
                         <div class="form-group">
                             <label class="col-sm-2 control-label"><span style="color: red; vertical-align: -3px;">*&nbsp;</span>跑量省份：</label>
                             <div class="col-sm-3">
                                 <select class="form-control m-b" name="useProvince" id="useProvince" >
                                 	<c:if test="${opType == 1}">
	                                 	<c:if test="${obj.dtnProvince == '平台商'}">
	                                 		<option value="0">全国</option>
	                                 	</c:if>
	                                 	<c:if test="${obj.dtnProvince != '平台商'}">
	                                 		<option value="0" <c:if test="${obj.useProvince == 0 }">selected="selected"</c:if> >全国</option>
	                                 		<option value="1" <c:if test="${obj.useProvince == 1 }">selected="selected"</c:if>>本省</option>
	                                 		<option value="2" <c:if test="${obj.useProvince == 2 }">selected="selected"</c:if>>非本省</option>
	                                 	</c:if>
                                 	</c:if>
                                 	<c:if test="${opType == 0}">
                                 		<option value="0">全国</option>
                                 	</c:if>
                                 </select>
                             </div>
                         </div> 
                         
                         <div class="form-group" >
                             <label class="col-sm-2 control-label">状态：</label>
                             <div class="col-sm-3">
                                 <select class="form-control m-b" id="status" <c:if test="${empty obj.channelId}">  disabled ='disabled' </c:if> >
                                        <option value="0" <c:if test="${obj.status == 0 }"> selected='selected' </c:if>>新创建</option>
                                        <option value="2" <c:if test="${obj.status == 2 }"> selected='selected' </c:if>>对接中</option>
                                        <option value="1" <c:if test="${obj.status == 1 }"> selected='selected' </c:if>>运营中</option>
                                        <option value="3" <c:if test="${obj.status == 3 }"> selected='selected' </c:if>>作废</option>
                                 </select>
                             </div>
                         </div>                       
                                                                           
                         <div class="form-group">
                             <label class="col-sm-2 control-label">通道下发速度：</label>
                             <div class="col-sm-3">
                                 <input id="crestValue" name="crestValue" class="form-control" type="text" aria-required="true" aria-invalid="true"
                                   value="${obj.crestValue }"  maxlength="10"  
                                   onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
                             </div>
                             <label>条/秒</label>
                         </div>
                         
                         <div class="form-group">
                             <label class="col-sm-2 control-label"><span style="color: red; vertical-align: -3px;">*&nbsp;</span>付费方式：</label>
                             <div class="col-sm-3">
                                 <select class="form-control m-b" id="chargeType" >
                                        <option value="-1"></option>
                                        <option value="0" <c:if test="${obj.chargeType == 0 }"> selected='selected' </c:if>>预付费</option>
                                        <option value="1" <c:if test="${obj.chargeType == 1 }"> selected='selected' </c:if>>后付费</option>
                                 </select>
                             </div>
                         </div>  
                         
                          <div class="form-group">
                             <label class="col-sm-2 control-label">通道资费(单位:元)：</label>
                             <div class="col-sm-3">
                                 <input id="channelFee" name=channelFee class="form-control" type="text" aria-required="true" aria-invalid="true"
                                   value="${obj.channelFee }"  maxlength="10" />
                             </div>
                         </div>
                         
                         <div class="form-group">
                             <label class="col-sm-2 control-label"><span style="color: red; vertical-align: -3px;">*&nbsp;</span>评分：</label>
                             <div class="col-sm-3">
                                 <input id="channelScore" name=channelScore class="form-control" type="text" aria-required="true" aria-invalid="true"
                                   value="${obj.channelScore }"  maxlength="3" 
                                   onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" />
                             </div>
                         </div>
   
                         <div class="form-group">
                             <label class="col-sm-2 control-label">通道账户：</label>
                             <div class="col-sm-3">
                                 <input id="channelAccount" name="channelAccount" class="form-control" type="text" value="${obj.channelAccount }" maxlength="50" >
                             </div>
                         </div>
                         
                         <div class="form-group">
                             <label class="col-sm-2 control-label">通道密码：</label>
                             <div class="col-sm-3">
                                 <input id="channelPassword" name="channelPassword" class="form-control" type="text" value="${obj.channelPassword }"  maxlength="50" >
                             </div>
                         </div>

                         <div class="form-group">
                             <label class="col-sm-2 control-label">备注：</label>
                             <div class="col-sm-3">
                                 <textarea id="channelComment" name="channelComment" class="form-control" required="" 
                                 aria-required="true" style="height: 100px;"  maxlength="200">${obj.channelComment }</textarea>
                             </div>
                         </div>
                         <div class="form-group">
                             <div class="col-sm-3 col-sm-offset-3">
                                 <button class="btn btn-primary" type="button" style="width: 60px;margin-left: 20px;" id="btnsave">保存</button>
                                 <button class="btn btn-primary" type="button" style="width: 60px;margin-left: 20px;" id="btnback">取消</button>
                                 <div id="msg" style="color: red;margin-top:10px;"></div>
                             </div>
                         </div>
                     </form>
                     </div>
                 </div>
		</div>
	</div>
	<script type="text/javascript" src="/js/handler/smschannelshow.js"></script> 
	<script type="text/javascript">
		function setuseprov(){
			$("#useProvince").empty();
			var dtnProvince = $("#dtnProvince").val();
			if(dtnProvince == '平台商'){
				$("#useProvince").append("<option value='0'>全国</option>");
			}else{
				$("#useProvince").append("<option value='0'>全国</option>");
				$("#useProvince").append("<option value='1'>本省</option>");
				$("#useProvince").append("<option value='2'>非本省</option>");
			}
		}
	</script>
</body>
</html>
