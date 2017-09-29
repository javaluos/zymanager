<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
					钱包管理<small>&nbsp;-->&nbsp;账号余额修改</small>
				</h5>
			</div>
			<div class="ibox-info">
			    <div style="margin-top: 5px;">
	                <p>说明：</p>
                        <ul>
                            <li>1、修改用户余额时一定要反复确认客户是否正确。</li>
                            <li>2、修改用户余额时，系统会记录操作人的账号，请勿随意操作。</li>
                            <li>3、增加余额则直接输入需增加的金额，减少余额则输入负数即可。</li>
                        </ul>
                </div>
			</div>
			<div class="contentdv">
			   <div style="padding-left: 100px;">
	               <form class="form-horizontal m-t" id="myForm" method="post" action="/moneybag/do_balance_update" novalidate="novalidate">
	                     <input id="apiAccount" name="apiAccount" type="hidden" value="${obj.apiAccount }">
	                     <input id="phone" type="hidden" value="${user.phone}">
	                     <input id="operator" type="hidden" value="${user.userName}">
	                     <input id="opttype" type="hidden" value="1">
                         <div class="form-group">
                             <label class="col-sm-2 control-label">客户账号：</label>
                             <div class="col-sm-3">
                                 <input id="merchantAccount" name="merchantAccount" class="form-control" type="text" value="${obj.merchantPhone }"  readonly="readonly">
                             </div>
                         </div>
                         <div class="form-group">
                             <label class="col-sm-2 control-label">邮箱地址：</label>
                             <div class="col-sm-3">
                                 <input id="merchantEmail" name="merchantEmail" class="form-control" type="text" value="${obj.merchantEmail }"  readonly="readonly">
                             </div>
                         </div>
                         <div class="form-group">
                             <label class="col-sm-2 control-label">姓名/企业名称：</label>
                             <div class="col-sm-3">
                                 <input id="businessName" name="businessName" class="form-control" type="text" aria-required="true" aria-invalid="true"
                                   value="${obj.businessName }" readonly="readonly"/>
                             </div>
                         </div>

                         <div class="form-group form-inline">
                             <label class="col-sm-2 control-label">账户余额：</label>
                             <div class="col-sm-3">
                                 <input id="currentFee" class="form-control" type="text" value="<fmt:formatNumber type='number' value='${obj.currentFee/10000 }' pattern='0.00' maxFractionDigits='2' />" disabled="disabled">
                                 <span>元</span>
                             </div>
                         </div>
                         <!--
                         <div class="form-group">
                             <label class="col-sm-2 control-label">修改类型：</label>
                             <div class="col-sm-3">
                                 <select class="form-control m-b" id="opttype">
                                         <option value="0"></option>
                                        <option value="1">增加</option>
                                        <option value="2">减少</option>
                                 </select>
                             </div>
                         </div> -->
                         <div class="form-group form-inline">
                             <label class="col-sm-2 control-label">修改金额：</label>
                             <div class="col-sm-3">
                                 <input id="updateFee" name="updateFee" class="form-control" type="text" maxlength="11" >
                                 <span>元</span>
                             </div>
                         </div>
                         <div class="form-group">
                             <label class="col-sm-2 control-label">备注：</label>
                             <div class="col-sm-3">
                                 <textarea id="comment" name="comment" class="form-control" required="" aria-required="true" style="height: 100px;"></textarea>
                             </div>
                         </div>
                         <div class="form-group">
                             <div class="col-sm-3 col-sm-offset-3">
                                 <button class="btn btn-primary" type="button" style="width: 60px;margin-left: 20px;" id="btnsave">提交</button>
                                 <button class="btn btn-primary" type="button" style="width: 60px;margin-left: 20px;" id="btnback">返回</button>
                                 <div id="msg" style="color: red;margin-top:10px;"></div>
                             </div>
                         </div>
                     </form>
                     </div>
                 </div>
		</div>
	</div>
<script type="text/javascript">
$(function() {
	$('#btnback').click(function(){
	   location.href = '/moneybag/actbalancelist';
	});
	
	$('#btnsave').click(function(){
		//location.href = '/moneybag/actbalancesave';
		var updateFee = $("#updateFee").val();
		if(updateFee.trim() == ''){
			$('#updateFee').focus();
			layer.tips('请输入修改金额.', $('#updateFee'));
			return false;
		}
		var reg = /^[+-]?\d*\.?\d{0,2}$/;
		if(reg.test(updateFee) == false){
			$('#updateFee').focus();
			layer.tips('修改金额格式不正确.', $('#updateFee'));
			return false;
		}
		openSubCharge('13480824736');
	});
	
	/* $("#updateFee").keyup(function () {
        //var reg = $(this).val().match(/\d+\.?\d{0,2}/);
        var reg = $(this).val().match(/[+-]?\d*\.?\d{0,2}/);
        var txt = '';
        if (reg != null) {
            txt = reg[0];
        }
        $(this).val(txt);
    }).change(function () {
        $(this).keyup();
        
    }); */
	
	function openSubCharge(mobile){
		//短信倒计时
		var $mobile = $("#phone").val();
		$("#msg").empty();
		var apiAccount = $("#apiAccount").val();
		var updateFee = $("#updateFee").val();
		$.ajax({
			type: "POST",
	   		url: "/moneybag/check_submit",
	   		data: {"apiAccount" : apiAccount, "updateFee" : updateFee},
	   		dataType: "json",
		   	success: function(data){
		   		if(data.result == '0'){
		   			var merchantAccount = $("#merchantAccount").val();
		   			var email = $("#merchantEmail").val();
		   			var businessName = $("#businessName").val();
		   			var updateFee = $("#updateFee").val();
		   			var operator = $("#operator").val();
		   			var mht = '<li style="text-align:center"><h3>请再次确认客户信息!</h3></li>';
		   			mht += '<li><font style="margin-left:40px">邮箱地址：</font><font style="margin-left:60px">'+email+'</font></li>';
		   			mht += '<li><font style="margin-left:40px">客户账号：</font><font style="margin-left:60px">'+merchantAccount+'</font></li>';
		   			mht += '<li><font style="margin-left:40px">客户名称：</font><font style="margin-left:60px">'+businessName+'</font></li>';
		   			mht += '<li><font style="margin-left:40px">修改金额：</font><font style="margin-left:60px">'+updateFee+'&nbsp;元</font></li>';
		   			mht += '<li><font style="margin-left:40px">操&nbsp;作&nbsp;人&nbsp;：</font><font style="margin-left:60px">'+operator+'</font></li>';
					mht += '<li style="text-align:center"><h3>您正在进行余额修改操作...</h3></li><li style="text-align:center">系统已发送短信验证码到您的手机,请输入验证码!</li>';
					//mht += '<li style="margin-left:50px;margin-top:20px;text-align:center"><span style="width:70px;display: inline-block;">手机号码:</span>&nbsp;'+$mobile+'</li>';
					mht += '<li><span style="margin-left:40px;display: inline-block;">手机号码:</span><span style="margin-left:20px">'+$mobile+'</span> </li>';
					mht += '<li style="margin-left:40px;"><span style="width:70px;display: inline-block;">验&nbsp;证&nbsp;码&nbsp;:</span>&nbsp;<input id="vcode" type="text"><span><input style="width:115px;height:28px;margin-top:-5px;margin-left:5px;" type="button" class="btn btn-success" id= "opt_btn" onclick="getvcode();" value="获取验证码"/></span> </li>';
					mht += '<li style="margin-top:20px;text-align:center"><button class="btn btn-primary" type="button" onclick="submit();" style="width:60px;margin-left:20px;margin-right:100px;" id="btnexce1">确认</button><button class="btn btn-primary" type="button" style="width: 60px;" id="btnexce2" onclick="cancel();">取消</button></li>';
					mht += '<div id="errormsg" style="color: red;margin-top:10px;"></div>';
					layer.open({
					  type: 1, //page层
					  area: ['500px', '430px'],
					  title: '充值操作-短信确认',
					  shade: 0.3, //遮罩透明度
					  moveType: 1, //拖拽风格，0是默认，1是传统拖动
					  shift: 1, //0-6的动画形式，-1不开启
					  content: '<div style="padding:50px; padding-top:20px;">'+ mht+ '</div>'
					}); 
					getvcode();
		   		}else if(data.result == '1'){
				   if(data.menumsg != null){
					   $("#msg").text("当前用户没有充值权限.");
				   }
				   if(data.balancemsg != null){
					   	$('#updateFee').focus();
						layer.tips(''+data.balancemsg+'', $('#updateFee'));
				   }
			   }
		   	}
		});
	}
})

var wait = 60;
function time(o) {  
  	if (wait == 0) {  
      o.removeAttribute("disabled");            
      o.value="免费获取验证码";  
      wait = 60;  
  	} else {  
      o.setAttribute("disabled", true);
      o.value="重新发送(" + wait + ")";  
      wait--;  
      setTimeout(function() {  
          time(o);  
      },  
      1000);  
  	}  
}
function getvcode(){
	$("#errormsg").empty();
	var $mobile = $("#phone").val();
	if($mobile.trim() == ''){
		$("#errormsg").text("用户未绑定手机号码.");
		return false;
	}
	$.ajax({
	   type: "POST",
	   url: "/moneybag/get_vcode",
	   data: {mobile:$mobile},
	   dataType:"json",
	   success: function(msg){
		 if("" != msg && msg.result=="SUCCESS"){//验证和重置密码成功
			time(document.getElementById("opt_btn"));
		 }else{
			var reason = msg.reason;
			if(null != reason && "" != reason){
			 	$("#errormsg").text(reason);
			}else{
				$("#errormsg").text("验证码发送失败，请重新发送");
			}
		 }			 
	   }
	});
}
function cancel(){
	layer.closeAll();
}
function submit(){
	var vcode = $("#vcode").val();
	if(vcode.trim() == ''){
		$('#vcode').focus();
		layer.tips('请输入验证码.', $('#vcode'));
		return false;
	}
	//校验验证码
	var mobile = $("#phone").val();
	$.ajax({
		type: "POST",
   		url: "/moneybag/check_vcode",
		data: {"mobile" : mobile, "vcode" : vcode},
		success: function(data){
			if(data == 'true'){
				$("#myForm").submit();
			}else{
				$('#vcode').focus();
				layer.tips('验证码不正确.', $('#vcode'));
			}	 
	   	}
	})
}
</script>	 
</body>
</html>
