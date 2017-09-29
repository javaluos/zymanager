// JavaScript Document

$(function() {
	
	//得到焦点
	$("#upwd").focus(function() {
		$("#left_hand").animate({
			left : "150",
			top : " -38"
		}, {
			step : function() {
				if (parseInt($("#left_hand").css("left")) > 140) {
					$("#left_hand").attr("class", "left_hand");
				}
			}
		}, 2000);
		$("#right_hand").animate({
			right : "-64",
			top : "-38px"
		}, {
			step : function() {
				if (parseInt($("#right_hand").css("right")) > -70) {
					$("#right_hand").attr("class", "right_hand");
				}
			}
		}, 2000);
	});
	
	//失去焦点
	$("#upwd").blur(function() {
		$("#left_hand").attr("class", "initial_left_hand");
		$("#left_hand").attr("style", "left:100px;top:-12px;");
		$("#right_hand").attr("class", "initial_right_hand");
		$("#right_hand").attr("style", "right:-112px;top:-12px");
	});
});

// 输入框获得光标
window.onload = function(){
	$('#uname').focus();
};

//支持Enter键登录
document.onkeydown = function(event){ 
	e = event ? event :(window.event ? window.event : null); 
	if(e.keyCode==13){ 
		$('#loginbtn').click();
	} 
};

$(function(){
	
	initUserCookie();//加载cookieUser
	
	// 重置按钮
	$('#resetbtn').click(function(){
		$('#uname').val('');
		$('#upwd').val('');
	});
	
	//提交表单
	$('#loginbtn').click(function(){
		var uname = $('#uname').val().trim();
		var upwd  =  $('#upwd').val().trim();
		if(uname == ''){
		  $('#msgtips').html('请输入登录账号.');
		  $('#uname').focus();
		  return false;
		}else if(upwd == ''){
		  $('#msgtips').html('请输入登录密码.');
		  $('#upwd').focus();
		  return false;
		} else{
			$.ajax({
				   async:false,
				   type: 'POST',//提交方式
	               dataType: 'json',//类型
				   url: '/public/login',
				   data: {'userName': uname, 'password': hex_md5(upwd)},
				   success: function(data){
                       if(data == null || data == 'undefined'){
                    	 $('#msgtips').html('登录系统异常.');  
                       }else if(data.code== -1){
                    	 $('#msgtips').html(data.msg);  
					   }else if(data.code== -2){
						 $('#msgtips').html(data.msg);  
					   }else if(data.code== -3){
						 $('#msgtips').html(data.msg);  
					   }else if(data.code== 1){//登陆成功
						   saveUserCookie();//保存用户到cookie中
						   window.location.href = "/main/index.html"; 			
					   }else{
						 $('#msgtips').html('登录系统失败.');  
					   }			 
				   },
				   error: function(msg){
					   $('#msgtips').html("服务器繁忙,请稍后再试!");
				   }
			 });	
		}
	});
});

// 保存用户信息 到cookie
function saveUserCookie() { 
    
	if ($("#rmbuser").is(':checked') == true) { 
		var username = $("#uname").val(); 
        var password = $("#upwd").val(); 
        $.cookie("rmbuser", "true", { expires: 7 }); // 存储一个带7天期限的 cookie 
        $.cookie("username", username, { expires: 7 }); // 存储一个带7天期限的 cookie 
        $.cookie("password", password, { expires: 7 }); // 存储一个带7天期限的 cookie 
    }else { 
        $.cookie("rmbuser", "false", { expires: -1 }); 
        $.cookie("username", '', { expires: -1 }); 
        $.cookie("password", '', { expires: -1 }); 
    } 
}

// 获得用户信息 到cookie
function initUserCookie() { 
	
	if ($.cookie("rmbuser") == "true") { 
	    $("#rmbuser").attr("checked", true); 
	    $("#uname").val($.cookie("username")); 
	    $("#upwd").val($.cookie("password")); 
	} 
}