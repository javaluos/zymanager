<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE HTML>
<html>
<head>
<title>Paas运营管理平台</title>
<jsp:include page="../comm/plugin.jsp" />
</head>
<script>
$(function(){
      $('.m_table tr:gt(1):even').hover(function(){
          $(this).css("background-color","#E3F5D8"); 
      },function(){
        $(this).css("background-color","white");
      });
      $('.m_table tr:odd').hover(function(){
          $(this).css("background-color","#E3F5D8"); 
      },function(){
        $(this).css("background-color","white");
      });
      
      
      
});

$(function(){
	
	var oldname = '';
    $('#uploadFileId').change(function (){ 
  	  var filename = $(this).val();
  	  $('#fileurl').val(filename);
  	  if(oldname==''){
  		  var extStart = filename.lastIndexOf("\\");
	      var extEnd = filename.lastIndexOf(".");
	      var name = filename.substring(extStart+1, extEnd);
	      $('#fileName').val(name);
  	  }
    });
    $('#fileName').blur(function(){
       oldname = $('#fileName').val().trim();
    });
   
});

function setfile(){
	$('#uploadFileId').click();
}

function isOk(){
	$('#authStatus').val(1);
	$('#signupForm')[0].submit();
}

function isFalse(){
	var myrad=$('input:radio[name="myrad"]:checked').val();
	var ccomment=$('#ccomment').val();
	if(myrad==undefined){
		alert("请选择不通过的原因");
		return false;
	}else if(myrad=="自定义原因："){
		myrad=ccomment;
	}
	$('#authStatus').val(4);
	$('#authDesc').val(myrad);
	$('#signupForm')[0].submit();
}

function playVoice(obj){
	 var audio = $(obj).next()[0];
	 if(audio!==null){             
		  if(audio.paused){                 
		      audio.play();
		  }else{
		   	  audio.pause();
		  }
	 } 
}
</script>
<body>

	<!-- QueryForm -->
	<div class="col-sm-12" style="border-bottom: 1px solid #e5e6e7; padding-bottom: -20px;margin-bottom: 10px;">
		<div class="ibox">
			<div class="ibox-title">
				<h5>
					审核信息<small>&nbsp;-->&nbsp;认证详情</small>
				</h5>
			</div>
			<div class="contentdv">
			   <div style="padding-left: 100px;">
	               <form class="form-horizontal m-t" id="signupForm" method="post" action="/merchantAccountAuth/audit" novalidate="novalidate">
                         <div class="form-group">
                             <label class="col-sm-2 control-label">开发者类型：</label>
                             <label class="col-sm-2 control-label">
                                 <input id="merchantType" class="form-control" disabled="true" type="text" value="<c:if test="${voiceMerchantAccountAuth.merchantType=='1'}">个人</c:if><c:if test="${voiceMerchantAccountAuth.merchantType=='2'}">企业</c:if>">
                             </label>
                         </div>
                         <div class="form-group">
                             <label class="col-sm-2 control-label">公司名称：</label>
                             <label class="col-sm-2 control-label">
                                 <input id="cyName" class="form-control" disabled="true" type="text" value="${voiceMerchantAccountAuth.cyName}">
                             </label>
                         </div>
                         <div class="form-group">
                             <label class="col-sm-2 control-label">公司地址：</label>
                             <label class="col-sm-2 control-label">
                                 <input id="plCretType" class="form-control" disabled="true" type="text" value="${voiceMerchantAccountAuth.cyAddress}">
                             </label>
                         </div>

                         <div class="form-group">
                             <label class="col-sm-2 control-label">所属行业：</label>
                             <label class="col-sm-2 control-label">
                                <input id="plCretNo" class="form-control" disabled="true" type="text" value="${voiceMerchantAccountAuth.cyIndustry}">
                             </label>
                         </div>
                         <div class="form-group">
                             <label class="col-sm-2 control-label">证件类型：</label>
                             <label class="col-sm-2 control-label">
                                <input id="plCretNo" class="form-control" disabled="true" type="text" value="<c:if test="${voiceMerchantAccountAuth.cyCretType=='1'}">三证合一（一照一码）</c:if><c:if test="${voiceMerchantAccountAuth.cyCretType=='2'}">三证合一</c:if><c:if test="${voiceMerchantAccountAuth.cyCretType=='3'}">三证分离</c:if>">
                             </label>
                         </div>
                         
                         <c:choose>
                             <c:when test="${voiceMerchantAccountAuth.cyCretType=='1'}">
                         	 
                         	 <div class="form-group">
	                             <label class="col-sm-2 control-label">统一社会信用代码：</label>
	                             <label class="col-sm-2 control-label">
	                                 <input id="businessName" class="form-control" disabled="true" type="text" value="${voiceMerchantAccountAuth.cyUscc}">
	                             </label>
	                         </div>
	                         
	                         <div class="form-group">
	                             <label class="col-sm-2 control-label">营业执照：</label>
	                             <label class="col-sm-2 control-label" style="width: 60px;height: 60px;">
                                	<img alt="营业执照" name="plCretFileurl" src="${voiceMerchantAccountAuth.cyBlFileurl }">
	                             </label>
	                          </div>
                            </c:when>
                            
                            <c:when test="${voiceMerchantAccountAuth.cyCretType=='2'}">
	                        	  <div class="form-group">
		                             <label class="col-sm-2 control-label">注册号：</label>
		                             <label class="col-sm-2 control-label">
		                                 <input id="businessName" class="form-control" disabled="true" type="text" value="${voiceMerchantAccountAuth.cyRegistrNo}">
		                             </label>
		                          </div>
		                          <div class="form-group">
		                             <label class="col-sm-2 control-label">税务登记证：</label>
		                             <label class="col-sm-2 control-label" style="width: 60px;height: 60px;">
										<img alt="税务登记证" name="plCretFileurl" src="${voiceMerchantAccountAuth.cyTrcFileurl }">	           
	                  				 </label>
		                          </div>
		
		                          <div class="form-group" style="margin-top: 160px;">
		                             <label class="col-sm-2 control-label">营业执照：</label>
		                             <label class="col-sm-2 control-label" style="width: 60px;height: 60px;">
										<img alt="营业执照" name="plCretFileurl" src="${voiceMerchantAccountAuth.cyBlFileurl }">
									</label>
		                          </div>
                           </c:when>
                          
                           <c:when test="${voiceMerchantAccountAuth.cyCretType=='3'}">
                        	<div class="form-group">
	                             <label class="col-sm-2 control-label">税务登记号：</label>
	                             <label class="col-sm-2 control-label">
	                                 <input id="businessName" class="form-control" disabled="true" type="text" value="${voiceMerchantAccountAuth.cyTrcNo}">
	                             </label>
	                         </div>
	                         <div class="form-group">
	                             <label class="col-sm-2 control-label">税务登记证：</label>
	                             <label style="width: 60px;height: 60px;" class="col-sm-2 control-label">
	                                 <img alt="税务登记证" name="plCretFileurl" src="${voiceMerchantAccountAuth.cyTrcFileurl }">
	                             </label>
	                         </div>
	
	                         <div class="form-group" style="margin-top: 160px;">
	                             <label class="col-sm-2 control-label">营业执照号：</label>
	                             <label class="col-sm-2 control-label">
	                                <input id="plCretNo" class="form-control" disabled="true" type="text" value="${voiceMerchantAccountAuth.cyBlNo}">
	                             </label>
	                         </div>
	                         <div class="form-group">
	                             <label class="col-sm-2 control-label">营业执照证：</label>
	                             <label style="width: 60px;height: 60px;" class="col-sm-2 control-label">
	                                 <img alt="税务登记证" name="plCretFileurl" src="${voiceMerchantAccountAuth.cyBlFileurl }">
	                             </label>
	                         </div>
	                     </c:when>
                        </c:choose>
                        
                         
                         <c:choose>
                             <c:when test="${voiceMerchantAccountAuth.authStatus!='1'&&voiceMerchantAccountAuth.authStatus!='4'}">
                                 <div class="form-group" style="margin-top: 160px;">
	                              <label class="col-sm-2 control-label">不通过原因：</label>
	                              <div class="col-sm-4" style="margin-top: 20px;">
	                                 <input type="radio" value="证件内容模糊，无法辨认证件内容" name="myrad">证件内容模糊，无法辨认证件内容</p>
	                                 <input type="radio" value="证件内容与填写内容不符合" name="myrad">证件内容与填写内容不符合</p>
	                                 <input type="radio" value="证件错误，不是合法的证件" name="myrad">证件错误，不是合法的证件</p>
	                                 <input type="radio" value="自定义原因：" name="myrad">自定义原因：</p>
	                                 <div class="col-sm-3">
	                                    <textarea id="ccomment"class="form-control" required="" aria-required="true" style="width:400px;height: 100px;"></textarea>
	                                 </div>
	                             </div>
		                         </div>
		                         <div class="form-group">
		                             <div class="col-sm-2 col-sm-offset-2" style="margin-top: 20px;">
		 								  <input type="hidden" name="id" id="id" value="${voiceMerchantAccountAuth.id}">
		 								  <input type="hidden" name="authStatus" id="authStatus">
		 								  <input type="hidden" name="authDesc" id="authDesc">
		                                 <button class="btn btn-primary" type="button" style="width: 60px;" id="btnsave" onclick="isOk();">通过</button>
		                                 <button class="btn btn-primary" type="button" style="width: 60px;margin-left: 20px;" id="btnsave" onclick="isFalse();">不通过</button>
		                                 <button class="btn btn-primary" type="button" style="width: 60px;margin-left: 20px;" onclick="history.go(-1)" id="btnsave">返回</button>                            
		                                 </div>
		                         </div>
                             </c:when>
                             <c:otherwise>
                                <div class="form-group">
		                            <div class="col-sm-2 col-sm-offset-2">
		                                 <button class="btn btn-primary" type="button" style="width: 60px;margin-left: 20px;" onclick="history.go(-1)" id="btnsave">返回</button>                            
		                            </div>
	                           </div>
                             </c:otherwise>
                         </c:choose>
                     </form>
                  </div>
              </div>
		</div>
	</div>
<script type="text/javascript">
$(function() {
	$('#btdowload').click(function(){
	   var url=$('#downloadUrl').val();
	   window.location=url;
	});
})


</script>	 
</body>
</html>
