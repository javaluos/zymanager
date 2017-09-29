
function updatepwd(userId){
	var mht = '';
	mht = mht+'<li style="margin-top:20px;"><span style="width:80px;display: inline-block;"><font color="red">*</font>旧密码：</span>&nbsp;<input id="oldpwd" type="password" maxlength="10"></li><li style="margin-top:20px;"><span style="width:80px;display: inline-block;"><font color="red">*</font>新密码：</span>&nbsp;<input id="newpwd" type="password" maxlength="10"></li><li style="margin-top:20px;"><span style="width:80px;display: inline-block;"><font color="red">*</font>确认新密码：</span>&nbsp;<input id="renewpwd" type="password" maxlength="10"></li>';
	mht = mht+'<li style="margin-top:20px;"><button class="btn btn-primary" type="button" style="width:60px;margin-left:20px;margin-right:100px;" onclick="dosubmit()" id="btnexce1">确认</button><button class="btn btn-primary" type="button" style="width: 60px;" id="btnexce2" onclick="cancel()">取消</button></li>';
	
	layer.open({
	  type: 1, //page层
	  area: ['500px', '300px'],
	  title: '密码修改',
	  shade: 0.3, //遮罩透明度
	  moveType: 1, //拖拽风格，0是默认，1是传统拖动
	  shift: 1, //0-6的动画形式，-1不开启
	  content: '<div style="padding:50px; padding-top:20px;">'+ mht+ '</div>'
	}); 
}

function dosubmit(){
	var oldpwd = $("#oldpwd").val();
	if(oldpwd.trim() == ''){
		$('#oldpwd').focus();
		layer.tips('请输入旧密码.', $('#oldpwd'));
		return false;
	}
	var flag = getPwd(oldpwd);
	if(flag == false){
		return false;
	}
	var newpwd = $("#newpwd").val();
	if(newpwd.trim() == ''){
		$('#newpwd').focus();
		layer.tips('请输入新密码.', $('#newpwd'));
		return false;
	}
	var renewpwd = $("#renewpwd").val();
	if(renewpwd.trim() == ''){
		$('#renewpwd').focus();
		layer.tips('请再次输入新密码.', $('#renewpwd'));
		return false;
	}
	if(renewpwd != newpwd){
		$('#renewpwd').focus();
		layer.tips('两次输入密码不一致.', $('#renewpwd'));
		return false;
	}
	$.ajax({
		type: "POST",
	   	url: "/user/update_pwd",
	   	data:{
	   		"newpwd":newpwd
	   	},
	   	success: function(data){
	   		if(data){
	   			layer.open({
	   			  type: 1, //page层
	   			  closeBtn: 0,//不显示关闭按钮
	   			  area: ['500px', '300px'],
	   			  title: false,
	   			  shade: 0.3, //遮罩透明度
	   			  moveType: 1, //拖拽风格，0是默认，1是传统拖动
	   			  shift: 1, //0-6的动画形式，-1不开启
	   			  time:2000,
	   			  content: '<div style="padding:150px; padding-top:50px;color:green">修改密码成功，2秒后退出登录</div>',
	   			  end: function(){
	   				  window.location.href="/public/logout";
	   			  }
	   			}); 
	   		}
	   	}
	});
}
function getPwd(pwd){
	var flag = true;
	$.ajax({
		async: false,
		type: "POST",
	   	url: "/user/check_user_pwd",
	   	data:{"pwd":pwd},
	   	success: function(data){
	   		if(!data){
	   			$('#oldpwd').focus();
	   			layer.tips('旧密码不正确.', $('#oldpwd'));
	   			flag = false;
	   		}
	   	}
	});
	return flag;
}
function cancel(){
	parent.layer.closeAll();
}

window.setInterval(showbell, 500); 
var flag=false;
function showbell() 
{ 
	var totalCount=$("#totalCount2").text();
	if(totalCount>0){
		var style = $("#totalCount");
		if(flag){		
			style.removeClass("label-danger");
			style.text("");
			 flag=false;
		}else{
			 style.addClass("label-danger");
			 style.text(totalCount);
			 flag=true;
		}
	}
}

window.setInterval(clickOnTime, 10000); 
function clickOnTime(){
	$.ajax({
		type: "POST",
			url: "/cdr_monitor_notice_log/get_log_count",
			dataType: "json",
	   	success: function(data){
	   		if(data>=0){
	   			var totalCountel =$("#totalCount");
	   			var totalCountel2 =$("#totalCount2");
	   			if(totalCountel.text() == ''){
	   				totalCountel =$("#totalCount", parent.document);
	   			}
	   			if(totalCountel2.text()== ''){
	   				totalCountel2 =$("#totalCount2", parent.document);
	   			}
	   			if(data==0){
	   				totalCountel.removeClass("label-danger");
	   				totalCountel.text("");
		   			totalCountel2.text("");
	   			}else{
	   				totalCountel.text(data);
		   			totalCountel2.text(data);
	   			}
	   		}
	   	}
	});
}