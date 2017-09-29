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
<script src="/js/handler/voicevalid.js"></script>
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

function enablereason(){
	$("#ccomment").attr("disabled", false);
}
function disablereason(){
	$("#ccomment").attr("disabled", true);
}

function setfile(){
	$('#uploadFileId').click();
}

function isOk(){
	$('#status').val(1);
	$('#signupForm')[0].submit();
}

function isFalse(){
	var myrad=$('input:radio[name="myrad"]:checked').val();
	var reason=$('#ccomment').val();
	
	var tztemplate = $("#ccomment").val();
	var tempCts = $("#ccomment").val().replace(/\ +/g, "").replace(/[\r\n]/g,"");
	
	if(myrad==undefined){
		layer.alert("请选择不通过的原因");
		return false;
	}else if(myrad=="自定义原因："){
		if(reason.trim()==''){
			layer.alert("请输入自定义原因");
			return false;
		}else if(parseInt(tempCts.length)>=51){
			  layer.tips('审核签名自定义原因总字数不能超过50个字（单个汉字、英文和标点都算一个字）.', $('#ccomment'));
		  	  $('#ccomment').focus();
			  return false;
		}
		myrad=tempCts;
	}
	$('#status').val(4);
	$('#reason').val(myrad);
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
	<div class="col-sm-12" style="padding-bottom: -20px;margin-bottom: 10px;">
		<div class="ibox">
			<div class="ibox-title">
				<h5>
					审核信息<small>&nbsp;-->&nbsp;签名审核</small>
				</h5>
			</div>
			<div class="contentdv">
			   <div style="margin-left: -150px;">
	               <form class="form-horizontal m-t" id="signupForm" method="post" action="/signaudit/audit" enctype = "multipart/form-data" novalidate="novalidate">
                         <div class="form-group">
                             <label class="col-sm-2 control-label">姓名/企业名称：</label>
                             <label class="col-sm-2 control-label">
                                 <input id="merchantEmail" disabled="true" class="form-control" type="text" value="${merchantSmsSigner.merchantAccount.businessName}">
                             </label>
                         </div>
                         <div class="form-group">
                             <label class="col-sm-2 control-label">客户账号：</label>
                             <label class="col-sm-2 control-label">
                                 <input id="merchantEmail"  disabled="true" class="form-control" type="text" value="${merchantSmsSigner.merchantAccount.merchantPhone}">
                             </label>
                         </div>
                         <div class="form-group">
                             <label class="col-sm-2 control-label">提交时间：</label>
                             <label class="col-sm-2 control-label">
                                 <input id="merchantEmail"  disabled="true" class="form-control" type="text" value="<fmt:formatDate value="${merchantSmsSigner.authSubmitTime}" pattern="yyyy-MM-dd HH:mm:ss" />">
                             </label>
                         </div>
                         
                         <div class="form-group">
                             <label class="col-sm-2 control-label">签名ID：</label>
                             <label class="col-sm-2 control-label">
                                 <input id="merchantEmail"  disabled="true" class="form-control" type="text" value="${merchantSmsSigner.id}">
                             </label>
                         </div>
                         
                         <div class="form-group">
                             <label class="col-sm-2 control-label">签名内容：</label>
                             <label class="col-sm-2 control-label">
                                 <input id="merchantEmail"  disabled="true" class="form-control" type="text" value="${merchantSmsSigner.content}">
                             </label>
                         </div>

                         <div class="form-group">
                             <label class="col-sm-2 control-label">签名类型：</label>
                             <label class="col-sm-2 control-label">
                                <input id="merchantEmail"  disabled="true" class="form-control" type="text" value="${merchantSmsSigner.category}">
                             </label>
                         </div>
                         <c:if test="${merchantSmsSigner.status!='2'}">
	                         <div class="form-group">
	                             <label class="col-sm-2 control-label">审核时间：</label>
	                             <label class="col-sm-2 control-label">
	                                 <input id="merchantEmail"  disabled="true" class="form-control" type="text" value="<fmt:formatDate value="${merchantSmsSigner.authResultTime}" pattern="yyyy-MM-dd HH:mm:ss" />">
	                             </label>
	                         </div>
	                         <div class="form-group">
	                             <label class="col-sm-2 control-label">审核人：</label>
	                             <label class="col-sm-2 control-label">
	                                 <input id="merchantEmail"  disabled="true" class="form-control" type="text" value="${merchantSmsSigner.authUser}">
	                             </label>
	                         </div>
	                          <div class="form-group">
	                             <label class="col-sm-2 control-label">状态：</label>
	                             <label class="col-sm-2 control-label">
	                                 <input id="merchantEmail"  disabled="true" class="form-control" type="text" value="<c:if test="${merchantSmsSigner.status=='0'}">未审核</c:if> <c:if test="${merchantSmsSigner.status=='1'}">审核通过</c:if><c:if test="${merchantSmsSigner.status=='2'}">待审核</c:if><c:if test="${merchantSmsSigner.status=='3'}">待审核</c:if><c:if test="${merchantSmsSigner.status=='4'}">审核失败</c:if><c:if test="${merchantSmsSigner.status=='5'}">取消审核</c:if>">
	                             </label>
	                         </div>
							<div class="form-group">
								<label for="authSubmitTime" class="col-sm-2 control-label">不通过原因:</label>
								 <label class="col-sm-5 control-label">
									<input id="merchantEmail"  disabled="true" class="form-control" type="text" value="${merchantSmsSigner.reason}">
								 </label>
							</div>
	                     </c:if>
	                     
                         <div class="form-group">
                             <label class="col-sm-2 control-label">不通过原因：</label>
                             <div class="col-sm-4"  style="margin-top: 10px;">
                                  <input type="radio" value="签名内容包含敏感词，请修改签名"  name="myrad" onclick="disablereason();">签名内容包含敏感词，请修改签名</p>
                                  <input type="radio" value="签名内容为其他产品名称，建议不要使用"  name="myrad" onclick="disablereason();">签名内容为其他产品名称，建议不要使用</p>
                                  <input type="radio" value="该签名已被其他人使用，建议您使用其他签名"  name="myrad" onclick="disablereason();">该签名已被其他人使用，建议您使用其他签名</p>
                                  <input type="radio" value="签名请使用公司简称、品牌名或网站名作为短信签名"  name="myrad" onclick="disablereason();">签名请使用公司简称、品牌名或网站名作为短信签名</p>
                                  <input type="radio" value="自定义原因：" name="myrad" onclick="enablereason();">自定义原因：</p>
                                  <div class="col-sm-3">
                                    <textarea id="ccomment" class="form-control" required="" aria-required="true"  disabled="disabled"
                                    style="width:400px;height: 100px;"></textarea>
                                  </div>
                             </div>
                         </div>
                         <c:if test="${not empty msg }">
						    <div class="form-group" style="text-align: center;width:700px;">
								<div class="field">
									<font color='red'>${msg}</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
								</div>
							</div>
						 </c:if>
                         <div class="form-group">
                             <div class="col-sm-2 col-sm-offset-2">
                                 <input type="hidden" name="reason" id="reason">
                                 <input type="hidden" name="status" id="status">
                                 <input type="hidden" name="apiAccount" id="apiAccount" value="${merchantSmsSigner.apiAccount}">
                                 <input type="hidden" name="id" id="id" value="${merchantSmsSigner.id}">
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
$(function() {
	/* $('#btdowload').click(function(){
	   var url=$('#downloadUrl').val();
	   window.location=url;
	}); */
})


</script>	 
</body>
</html>
