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

function submitSave(){
	var callBack = $('#callBack').val();
	var numberGuard=$('#numberGuard').val();
	var directDialTelephone=$('#directDialTelephone').val();
	var voiceNotification=$('#voiceNotification').val();
	var voiceVerificationCode=$('#voiceVerificationCode').val();
	var callCenter=$('#callCenter').val();
	var multiTalk=$('#multiTalk').val();
	var smsNotification=$('#smsNotification').val();
	var smsVeriicationCode=$('#smsVeriicationCode').val();
	var soundRecording=$('#soundRecording').val();
	var smsMarket=$('#smsMarket').val();
	
	if(smsNotification.trim() ==''){
		   layer.tips('请输入短信通知.', $('#smsNotification'));
		   $('#smsNotification').focus();
		   return false;
	}
	if(smsVeriicationCode.trim() ==''){
		   layer.tips('请输入短信验证码.', $('#smsVeriicationCode'));
		   $('#smsVeriicationCode').focus();
		   return false;
	}
	if(smsMarket.trim() ==''){
		   layer.tips('请输入短信营销.', $('#smsMarket'));
		   $('#smsMarket').focus();
		   return false;
	}
	if(voiceVerificationCode.trim() ==''){
		   layer.tips('请输入语音验证码.', $('#voiceVerificationCode'));
		   $('#voiceVerificationCode').focus();
		   return false;
	}
	if(voiceNotification.trim() ==''){
		   layer.tips('请输入语音通知.', $('#voiceNotification'));
		   $('#voiceNotification').focus();
		   return false;
	}
	if(callBack.trim() ==''){
	   layer.tips('请输入回拨资费.', $('#callBack'));
	   $('#callBack').focus();
	   return false;
	}
	if(directDialTelephone.trim() ==''){
		   layer.tips('请输入直拨电话.', $('#directDialTelephone'));
		   $('#directDialTelephone').focus();
		   return false;
	}
	if(numberGuard.trim() ==''){
		   layer.tips('请输入号码卫士.', $('#numberGuard'));
		   $('#numberGuard').focus();
		   return false;
	}
	if(callCenter.trim() ==''){
		   layer.tips('请输入呼叫中心.', $('#callCenter'));
		   $('#callCenter').focus();
		   return false;
	}
	if(multiTalk.trim() ==''){
		   layer.tips('请输入多方通话.', $('#multiTalk'));
		   $('#multiTalk').focus();
		   return false;
	}
	
	if(soundRecording.trim() ==''){
		   layer.tips('请输入平台通话录音.', $('#soundRecording'));
		   $('#soundRecording').focus();
		   return false;
	}
	
	//询问框
	layer.confirm('你确认要修改资费吗？', {
	  btn: ['确认','取消'] //按钮
	}, function(){
	  //layer.msg('已确认', {icon: 0}); 
	  $('#signupForm')[0].submit();
	}, function(){
	  //layer.msg('已取消', {icon: 0});
	});
}

function resetDefault(){
	$('#callBack').val($('#numberGuardDefault').val());
	$('#numberGuard').val($('#numberGuardDefault').val());
	$('#directDialTelephone').val($('#directDialTelephoneDefault').val());
	$('#voiceNotification').val($('#voiceNotificationDefault').val());
	$('#voiceVerificationCode').val($('#voiceVerificationCodeDefault').val());
	$('#callCenter').val($('#callCenterDefault').val());
	$('#multiTalk').val($('#multiTalkDefault').val());
	$('#smsNotification').val($('#smsNotificationDefault').val());
	$('#smsVeriicationCode').val($('#smsVeriicationCodeDefault').val());
	$('#soundRecording').val($('#soundRecordingDefault').val());
	$('#smsMarket').val($('#smsMarketDefault').val());
	
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
					钱包管理<small>&nbsp;-->&nbsp;资费修改</small>
				</h5>
			</div>
			<div class="contentdv">
			   <div style="padding-left: 100px;">
	               <form class="form-horizontal m-t" id="signupForm" method="post" action="/voiceAccountBusinessInfo/update" novalidate="novalidate">
                         <div class="form-group">
                             <label class="col-sm-2 control-label">姓名/企业名称：</label>
                             <label class="col-sm-2 control-label">
                                 <input id="merchantEmail" disabled="disabled" class="form-control" type="text" value="${voiceAccountBusinessInfo.businessName}">
                             </label>
                         </div>
                          <div class="form-group">
                             <label class="col-sm-2 control-label">客户账号：</label>
                             <label class="col-sm-2 control-label">
                                 <input id="apiAccount" name="apiAccount" class="form-control" type="hidden" value="${voiceAccountBusinessInfo.apiAccount}">
                                 <input id="merchantEmail" disabled="disabled" class="form-control" type="text" value="${voiceAccountBusinessInfo.merchantPhone}">
                             </label>
                         </div>
                         <div class="form-group">
                             <label class="col-sm-2 control-label" style="font-size: 20px;font-weight: bold;">产品</label>
                             <label class="col-sm-6 control-label" style="font-size: 20px;font-weight: bold;">标准资费</label>
                         </div>
                         <div class="form-group">
                             <label class="col-sm-2 control-label">短信通知(元/条)：</label>
                             <label class="col-sm-2 control-label">
                                 <input id="smsNotification" name="smsNotification" maxlength="6" class="form-control" type="text" value="${voiceAccountBusinessInfo.smsNotifications}">
                             </label>
                             
                             <label style="width: 20%;" class="col-sm-2 control-label">
                             </label>
                              
                             <label class="col-sm-2 control-label">短信通知(元/条)：</label>
                             <label class="col-sm-2 control-label">
                                 <input id="smsNotificationDefault" class="form-control" disabled="disabled" type="text" value="${voiceAccountBusinessInfoDefault.smsNotification/10000}">
                             </label>
                         </div>
                         <div class="form-group">
                             <label class="col-sm-2 control-label">短信验证码(元/条)：</label>
                             <label class="col-sm-2 control-label">
                                 <input id="smsVeriicationCode" name="smsVeriicationCode" maxlength="6" class="form-control" type="text" value="${voiceAccountBusinessInfo.smsVeriicationCodes}">
                             </label>
                             
                             <label style="width: 20%;" class="col-sm-2 control-label">
                             </label>
                             
                             <label class="col-sm-2 control-label">短信验证码(元/条)：</label>
                             <label class="col-sm-2 control-label">
                                 <input id="smsVeriicationCodeDefault" class="form-control" disabled="disabled" type="text" value="${voiceAccountBusinessInfoDefault.smsVeriicationCode/10000}">
                             </label>
                         </div>
                         <div class="form-group">
                             <label class="col-sm-2 control-label">短信营销(元/条)：</label>
                             <label class="col-sm-2 control-label">
                                 <input id="smsMarket" name="smsMarket" maxlength="6" class="form-control" type="text" value="${voiceAccountBusinessInfo.smsMarkets}">
                             </label>
                             
                             <label style="width: 20%;" class="col-sm-2 control-label">
                             </label>
                             
                             <label class="col-sm-2 control-label">短信营销(元/条)：</label>
                             <label class="col-sm-2 control-label">
                                 <input id="smsMarketDefault" class="form-control" disabled="disabled" type="text" value="${voiceAccountBusinessInfoDefault.smsVeriicationCode/10000}">
                             </label>
                         </div>
                         <div class="form-group">
                             <label class="col-sm-2 control-label">语音验证码(元/分钟)：</label>
                             <label class="col-sm-2 control-label">
                                 <input id="voiceVerificationCode"  name="voiceVerificationCode" maxlength="6" class="form-control" type="text" value="${voiceAccountBusinessInfo.voiceVerificationCodes}">
                             </label>
                             
                             <label style="width: 20%;" class="col-sm-2 control-label">
	                             <input type="radio" value="0" name="voiceVerificationCodeRule" <c:if test="${voiceAccountBusinessInfo.voiceVerificationCodeRule==0}">checked="checked"</c:if>>6+6
	                             <input type="radio" value="1" name="voiceVerificationCodeRule" style="margin-left: 20px;" <c:if test="${voiceAccountBusinessInfo.voiceVerificationCodeRule==1}">checked="checked"</c:if>>30+30
	                             <input type="radio" value="2" name="voiceVerificationCodeRule" style="margin-left: 20px;" <c:if test="${voiceAccountBusinessInfo.voiceVerificationCodeRule==2}">checked="checked"</c:if>>60+60
                             </label>
                             
                             <label class="col-sm-2 control-label">语音验证码((元/分钟))：</label>
                             <label class="col-sm-2 control-label">
                                 <input id="voiceVerificationCodeDefault" class="form-control" disabled="disabled" type="text" value="${voiceAccountBusinessInfoDefault.voiceVerificationCode/10000}">
                             </label>
                         </div>
                         <div class="form-group">
                             <label class="col-sm-2 control-label">语音通知(元/分钟)：</label>
                             <label class="col-sm-2 control-label">
                                 <input id="voiceNotification" name="voiceNotification" maxlength="6" class="form-control" type="text" value="${voiceAccountBusinessInfo.voiceNotifications}">
                             </label>
                             
                             <label style="width: 20%;" class="col-sm-2 control-label">
	                             <input type="radio" value="0" name="voiceNotificationRule" <c:if test="${voiceAccountBusinessInfo.voiceNotificationRule==0}">checked="checked"</c:if>>6+6
	                             <input type="radio" value="1" name="voiceNotificationRule" style="margin-left: 20px;" <c:if test="${voiceAccountBusinessInfo.voiceNotificationRule==1}">checked="checked"</c:if>>30+30
	                             <input type="radio" value="2" name="voiceNotificationRule" style="margin-left: 20px;" <c:if test="${voiceAccountBusinessInfo.voiceNotificationRule==2}">checked="checked"</c:if>>60+60
                             </label>
                             
                             <label class="col-sm-2 control-label">语音通知((元/分钟))：</label>
                             <label class="col-sm-2 control-label">
                                 <input id="voiceNotificationDefault" class="form-control" disabled="disabled" type="text" value="${voiceAccountBusinessInfoDefault.voiceNotification/10000}">
                             </label>
                         </div>
                         <div class="form-group">
                             <label class="col-sm-2 control-label">回拨(元/分钟)：</label>
                             <label class="col-sm-2 control-label">
                                 <input id="callBack" name="callBack" maxlength="6" class="form-control" type="text" value="${voiceAccountBusinessInfo.callBacks}">
                             </label>
                             
                             <label style="width: 20%;" class="col-sm-2 control-label">
	                             <input type="radio" value="0" name="callBackRule" <c:if test="${voiceAccountBusinessInfo.callBackRule==0}">checked="checked"</c:if>>6+6
	                             <input type="radio" value="1" name="callBackRule" style="margin-left: 20px;" <c:if test="${voiceAccountBusinessInfo.callBackRule==1}">checked="checked"</c:if>>30+30
	                             <input type="radio" value="2" name="callBackRule" style="margin-left: 20px;" <c:if test="${voiceAccountBusinessInfo.callBackRule==2}">checked="checked"</c:if>>60+60
                             </label>
                             
                             <label class="col-sm-2 control-label">回拨((元/分钟))：</label>
                             <label class="col-sm-2 control-label">
                                 <input id="callBackDefault" class="form-control" disabled="disabled" type="text" value="${voiceAccountBusinessInfoDefault.callBack/10000}">
                             </label>
                         </div>
                         <div class="form-group">
                             <label class="col-sm-2 control-label">直拨(元/分钟)：</label>
                             <label class="col-sm-2 control-label">
                                 <input id="directDialTelephone" name="directDialTelephone" maxlength="6" class="form-control" type="text" value="${voiceAccountBusinessInfo.directDialTelephones}">
                             </label>
                             
                             <label style="width: 20%;" class="col-sm-2 control-label">
	                             <input type="radio" value="0" name="directDialTelephoneRule" <c:if test="${voiceAccountBusinessInfo.directDialTelephoneRule==0}">checked="checked"</c:if>>6+6
	                             <input type="radio" value="1" name="directDialTelephoneRule" style="margin-left: 20px;" <c:if test="${voiceAccountBusinessInfo.directDialTelephoneRule==1}">checked="checked"</c:if>>30+30
	                             <input type="radio" value="2" name="directDialTelephoneRule" style="margin-left: 20px;" <c:if test="${voiceAccountBusinessInfo.directDialTelephoneRule==2}">checked="checked"</c:if>>60+60
                             </label>
                             
                             <label class="col-sm-2 control-label">直拨((元/分钟))：</label>
                             <label class="col-sm-2 control-label">
                                 <input id="directDialTelephoneDefault" class="form-control" disabled="disabled" type="text" value="${voiceAccountBusinessInfoDefault.directDialTelephone/10000}">
                             </label>
                         </div>
                           <div class="form-group">
                             <label class="col-sm-2 control-label">号码卫士(元/分钟)：</label>
                             <label class="col-sm-2 control-label">
                                 <input id="numberGuard" name="numberGuard" maxlength="6" class="form-control" type="text" value="${voiceAccountBusinessInfo.numberGuards}">
                             </label>
                             
                             <label style="width: 20%;" class="col-sm-2 control-label">
	                             <input type="radio" value="0" name="numberGuardRule" <c:if test="${voiceAccountBusinessInfo.numberGuardRule==0}">checked="checked"</c:if>>6+6
	                             <input type="radio" value="1" name="numberGuardRule" style="margin-left: 20px;" <c:if test="${voiceAccountBusinessInfo.numberGuardRule==1}">checked="checked"</c:if>>30+30
	                             <input type="radio" value="2" name="numberGuardRule" style="margin-left: 20px;" <c:if test="${voiceAccountBusinessInfo.numberGuardRule==2}">checked="checked"</c:if>>60+60
                             </label>
                             
                             <label class="col-sm-2 control-label">号码卫士((元/分钟))：</label>
                             <label class="col-sm-2 control-label">
                                 <input id="numberGuardDefault" name="numberGuardDefault" disabled="disabled" class="form-control" type="text" value="${voiceAccountBusinessInfoDefault.numberGuard/10000}">
                             </label>
                         </div>
                           <div class="form-group">
                             <label class="col-sm-2 control-label">呼叫中心(元/分钟)：</label>
                             <label class="col-sm-2 control-label">
                                 <input id="callCenter" name="callCenter" maxlength="6" class="form-control" type="text" value="${voiceAccountBusinessInfo.callCenters}">
                             </label>
                             
                             <label style="width: 20%;" class="col-sm-2 control-label">
	                             <input type="radio" value="0" name="callCenterRule" <c:if test="${voiceAccountBusinessInfo.callCenterRule==0}">checked="checked"</c:if>>6+6
	                             <input type="radio" value="1" name="callCenterRule" style="margin-left: 20px;" <c:if test="${voiceAccountBusinessInfo.callCenterRule==1}">checked="checked"</c:if>>30+30
	                             <input type="radio" value="2" name="callCenterRule" style="margin-left: 20px;" <c:if test="${voiceAccountBusinessInfo.callCenterRule==2}">checked="checked"</c:if>>60+60
                             </label>
                             
                             <label class="col-sm-2 control-label">呼叫中心((元/分钟))：</label>
                             <label class="col-sm-2 control-label">
                                 <input id="callCenterDefault" name="callCenterDefault" disabled="disabled" class="form-control" type="text" value="${voiceAccountBusinessInfoDefault.callCenter/10000}">
                             </label>
                         </div>
                           <div class="form-group">
                             <label class="col-sm-2 control-label">多方通话(元/分钟):</label>
                             <label class="col-sm-2 control-label">
                                 <input id="multiTalk" name="multiTalk" maxlength="6" class="form-control" type="text" value="${voiceAccountBusinessInfo.multiTalks}">
                             </label>
                             
                             <label style="width: 20%;" class="col-sm-2 control-label">
	                             <input type="radio" value="0" name="multiTalkRule" <c:if test="${voiceAccountBusinessInfo.multiTalkRule==0}">checked="checked"</c:if>>6+6
	                             <input type="radio" value="1" name="multiTalkRule" style="margin-left: 20px;" <c:if test="${voiceAccountBusinessInfo.multiTalkRule==1}">checked="checked"</c:if>>30+30
	                             <input type="radio" value="2" name="multiTalkRule" style="margin-left: 20px;" <c:if test="${voiceAccountBusinessInfo.multiTalkRule==2}">checked="checked"</c:if>>60+60
                             </label>
                             
                             <label class="col-sm-2 control-label">多方通话((元/分钟)):</label>
                             <label class="col-sm-2 control-label">
                                 <input id="multiTalkDefault" class="form-control" disabled="disabled" type="text" value="${voiceAccountBusinessInfoDefault.multiTalk/10000}">
                             </label>
                         </div>
                           <div class="form-group">
                             <label class="col-sm-2 control-label">录音(元/条)：</label>
                             <label class="col-sm-2 control-label">
                                 <input id="soundRecording" name="soundRecording" maxlength="6" class="form-control" type="text" value="${voiceAccountBusinessInfo.soundRecordings}">
                             </label>
                             
                             <label style="width: 20%;" class="col-sm-2 control-label">
                             </label>
                             
                             <label class="col-sm-2 control-label">录音(元/条)：</label>
                             <label class="col-sm-2 control-label">
                                 <input id="soundRecordingDefault" class="form-control" disabled="disabled" type="text" value="${voiceAccountBusinessInfoDefault.soundRecording/10000}">
                             </label>
                             
                         </div>
                         <div class="form-group">
                             <div class="col-sm-4 col-sm-offset-4">
                                 <button class="btn btn-primary" type="button" style="width: 60px;" id="btnsave" onclick="submitSave();">确定</button>
                                 <button class="btn btn-primary" type="button" style="width: 100px;margin-left: 40px;" id="btnsave" onclick="resetDefault();">恢复标准资费</button>
                                 <button class="btn btn-primary" type="button" style="width: 60px;margin-left: 40px;" onclick="history.go(-1)" id="btnsave">取消</button>
                             </div>
                         </div>
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
