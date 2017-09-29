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
	var uf = $("#uploadFileId").val();
    if(uf != ""){
       if(!validFile()){
    	 return false;
       } 
       $('#voiceFileType').val(2);
    }
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
	<div class="col-sm-12" style="padding-bottom: -20px;margin-bottom: 10px;">
		<div class="ibox">
			<div class="ibox-title">
				<h5>
					审核信息<small>&nbsp;-->&nbsp;审核</small>
				</h5>
			</div>
			<div class="contentdv">
			   <div style="margin-left: -150px;">
	               <form class="form-horizontal m-t" id="signupForm" method="post" action="/audit/audit" enctype = "multipart/form-data" novalidate="novalidate">
                         <div class="form-group">
                             <label class="col-sm-2 control-label">姓名/企业名称：</label>
                             <label class="col-sm-2 control-label">
                                 <input id="merchantEmail" disabled="true" class="form-control" type="text" value="${voiceUpload.businessName}">
                             </label>
                         </div>
                         <div class="form-group">
                             <label class="col-sm-2 control-label">客户账号：</label>
                             <label class="col-sm-2 control-label">
                                 <input id="merchantEmail"  disabled="true" class="form-control" type="text" value="${voiceUpload.merchantPhone}">
                             </label>
                         </div>
                         <div class="form-group">
                             <label class="col-sm-2 control-label">提交时间：</label>
                             <label class="col-sm-2 control-label">
                                 <input id="merchantEmail"  disabled="true" class="form-control" type="text" value="<fmt:formatDate value="${voiceUpload.authSubmitTime}" pattern="yyyy-MM-dd HH:mm:ss" />">
                             </label>
                         </div>

                         <div class="form-group">
                             <label class="col-sm-2 control-label">类型：</label>
                             <label class="col-sm-2 control-label">
                                <input id="merchantEmail"  disabled="true" class="form-control" type="text" value="<c:if test="${voiceUpload.voiceType=='1'}">验证码</c:if><c:if test="${voiceUpload.voiceType=='2'}">通知</c:if>">
                             </label>
                         </div>
                         <div class="form-group">
                             <label class="col-sm-2 control-label">文件名称：</label>
                             <label class="col-sm-2 control-label">
                                 <input id="merchantEmail"  disabled="true" class="form-control" type="text" value=" ${voiceUpload.fileName}">
                             </label>
                         </div>
                         <div class="form-group">
                             <label class="col-sm-2 control-label">试听：</label>
                             <div class="col-sm-2 control-label">
								<button class="btn btn-primary" type="button" style="width: 60px;" onclick="$('#dfm').attr('src','${voiceUpload.downloadUrlS}');" id="btdowload">下载</button>
								
 								<a id="play" href="#" onclick="playVoice(this);"><button class="btn btn-primary" type="button" style="width: 60px;" id="btdowload2">播放</button></a>
 								<audio src="${voiceUpload.downloadUrlS}" controls="controls" preload="" id="voice_file" hidden="">浏览器版本不支持！</audio>    
 								<iframe id="dfm" src="" style="display:none; visibility:hidden;"></iframe>          
 					        </div>
                         </div>
                         <div class="form-group">
                             <label class="col-sm-6 control-label" style="color:red">下载后请将文件转换为 8000Hz、16位、单声道的wav格式，再上传，否则vos不能进行语音播报</label>
                         </div>
                         <div class="form-group">
                             <label class="col-sm-2 control-label">上传：</label>
                             <div class="col-sm-3">
                                <input type="hidden" name="voiceFileType" id="voiceFileType" value="0">
								<input type="file" class="input" id="uploadFileId" name="file" style="display: none;" />
								<input type="text" class="input" id="fileurl" readonly="readonly"  style="float:left;margin-right: 5px;margin-top:10px;width:260px;"/>
								<button class="btn btn-primary" type="button" onclick="setfile();"  style="vertical-align:-1px;margin-top:6px;" id="choicefile">选择文件</button>
                             </div>
                         </div>
                         <div class="form-group">
                             <label class="col-sm-2 control-label">不通过原因：</label>
                             <div class="col-sm-4"  style="margin-top: 10px;">
                                  <input type="radio" value="语音内容包含敏感词，请修改" name="myrad" onclick="disablereason();">语音内容包含敏感词，请修改</p>
                                  <input type="radio" value="语音内容与营销相关，请修改" name="myrad" onclick="disablereason();">语音内容与营销相关，请修改</p>
                                  <input type="radio" value="语音内容涉及房产、贷款、教育、移民及违规违法内容，请修改" name="myrad" onclick="disablereason();">语音内容涉及房产、贷款、教育、移民及违规违法内容，请修改</p>
                                  <input type="radio" value="语音内容主题不明确，请修改" name="myrad" onclick="disablereason();">语音内容主题不明确，请修改</p>
                                  <input type="radio" value="自定义原因：" name="myrad" onclick="enablereason();">自定义原因：</p>
                                  <div class="col-sm-3">
                                    <textarea id="ccomment"class="form-control" required="" aria-required="true" disabled="disabled" style="width:400px;height: 100px;"></textarea>
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
                                 <input type="hidden" name="authDesc" id="authDesc">
                                 <input type="hidden" name="authStatus" id="authStatus">
                                 <input type="hidden" name="downloadUrl" id="downloadUrl" value="${voiceUpload.downloadUrlS}">
                                 <input type="hidden" name="apiAccount" id="apiAccount" value="${voiceUpload.apiAccount}">
                                 <input type="hidden" name="id" id="id" value="${voiceUpload.id}">
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
