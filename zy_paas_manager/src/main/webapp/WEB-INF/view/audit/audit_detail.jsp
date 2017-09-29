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
	// if(!validFile()){
	//   return false;
	//} 
	$('#authStatus').val(1);
	$('#signupForm')[0].submit();
}

function isFalse(){
	var myrad=$('input:radio[name="myrad"]:checked').val();
	var ccomment=$('#ccomment').val();
	if(myrad==undefined){
		alert("请选择不通过的原因");
		return false;
	}else if(myrad="自定义原因："){
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
	<div class="col-sm-12" style="padding-bottom: -20px;margin-bottom: 10px;">
		<div class="ibox">
			<div class="ibox-title">
				<h5>
					审核信息<small>&nbsp;-->&nbsp;详情</small>
				</h5>
			</div>
			<div class="contentdv">
			   <div style="margin-left: -150px;">
	               <form class="form-horizontal m-t" id="signupForm" method="post" action="/audit/audit" novalidate="novalidate">
                         <div class="form-group">
                             <label class="col-sm-2 control-label">姓名/企业名称：</label>
                             <label class="col-sm-2 control-label">
                                 <input id="merchantEmail" disabled="true" class="form-control" type="text" value="${voiceUpload.businessName}">
                             </label>
                         </div>
                         <div class="form-group">
                             <label class="col-sm-2 control-label">客户账号：</label>
                             <label class="col-sm-2 control-label">
                                 <input id="merchantEmail" disabled="true" class="form-control" type="text" value="${voiceUpload.merchantPhone}">
                             </label>
                         </div>
                         <div class="form-group">
                             <label class="col-sm-2 control-label">提交时间：</label>
                             <label class="col-sm-2 control-label">
                                 <input id="merchantEmail" disabled="true" class="form-control" type="text" value="<fmt:formatDate value="${voiceUpload.authSubmitTime}" pattern="yyyy-MM-dd HH:mm:ss" /> ">
                             </label>
                         </div>

                         <div class="form-group">
                             <label class="col-sm-2 control-label">类型：</label>
                             <label class="col-sm-2 control-label">
                                <input id="merchantEmail" disabled="true" class="form-control" type="text" value="<c:if test="${voiceUpload.voiceType=='1'}">验证码</c:if><c:if test="${voiceUpload.voiceType=='2'}">通知</c:if>">
                             </label>
                         </div>
                         <div class="form-group">
                             <label class="col-sm-2 control-label">文件名称：</label>
                             <label class="col-sm-2 control-label">
                                 <input id="merchantEmail" disabled="true" class="form-control" type="text" value=" ${voiceUpload.fileName}">
                             </label>
                         </div>
                         <div class="form-group">
                             <label class="col-sm-2 control-label">试听：</label>
                             <div class="col-sm-2 control-label">
                                <button class="btn btn-primary" type="button" style="width: 60px;" onclick="$('#dfm').attr('src','${voiceUpload.downloadUrl}');" id="btdowload">下载</button>
                                
                                <a id="play" href="#" onclick="playVoice(this);"><button class="btn btn-primary" type="button" style="width: 60px;" id="btdowload2">播放</button></a>
 								<audio src="${voiceUpload.downloadUrl}" controls="controls" preload="" id="voice_file" hidden="">浏览器版本不支持！</audio> 
                                <iframe id="dfm" src="" style="display:none; visibility:hidden;"></iframe>            
                             </div>
                         </div>
                         <div class="form-group">
                             <label class="col-sm-2 control-label">审核时间：</label>
                             <label class="col-sm-2 control-label">
                                 <input id="merchantEmail" disabled="true" class="form-control" type="text" value="<fmt:formatDate value="${voiceUpload.authResultTime}" pattern="yyyy-MM-dd HH:mm:ss" />">
                             </label>
                         </div>
                         <div class="form-group">
                             <label class="col-sm-2 control-label">审核人：</label>
                             <label class="col-sm-2 control-label">
                                 <input id="merchantEmail" disabled="true" class="form-control" type="text" value=" ${voiceUpload.authUser}">
                             </label>
                         </div>
                         <div class="form-group">
                             <label class="col-sm-2 control-label">状态：</label>
                             <label class="col-sm-2 control-label">
                                 <input id="merchantEmail" disabled="true" class="form-control" type="text" value="<c:if test="${voiceUpload.authStatus=='0'}">未审核</c:if> <c:if test="${voiceUpload.authStatus=='1'}">审核通过</c:if><c:if test="${voiceUpload.authStatus=='2'}">待审核</c:if><c:if test="${voiceUpload.authStatus=='3'}">待审核</c:if><c:if test="${voiceUpload.authStatus=='4'}">审核失败</c:if><c:if test="${voiceUpload.authStatus=='5'}">取消审核</c:if>">
                             </label>
                         </div>
                         
                          <c:if test="${voiceUpload.authStatus=='4'}">
                             <div class="form-group">
                              <label class="col-sm-2 control-label">不通过原因：</label>
                              <div class="col-sm-2 control-label">
                                 <textarea rows="5" cols="35"  disabled="true" id="authDesc">${voiceUpload.authDesc}</textarea>
                             </div>
                            </div>
                          </c:if>
                         
                         <div class="form-group">
                             <div class="col-sm-2 col-sm-offset-2">
                                 <button class="btn btn-primary" type="button" style="width: 60px;margin-left: 20px;margin-top: 30px;" onclick="history.go(-1)" id="btnsave">返回</button>
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
