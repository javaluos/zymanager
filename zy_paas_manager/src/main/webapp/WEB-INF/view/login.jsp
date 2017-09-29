<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>PaaS运营管理平台</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="renderer" content="webkit" />
<meta name="description" content="">
<meta name="author" content="">
<link type="image/x-icon" href="/images/index/meta.ico" rel="icon">
<link type="image/x-icon" href="/images/index/meta.ico" rel="shortcut icon">
<!-- CSS -->
<link rel="stylesheet" href="/css/login.css">
<!--[if lt IE 9]>
<script src="/js/html5.js"></script>
<script src="/js/css3-mediaqueries.js"></script>
<![endif]-->
<script type="text/javascript" src="/js/jquery-2.2.0.min.js"></script>
<script type="text/javascript" src="/js/jquery.cookie.js"></script> 
<script type="text/javascript" src="/js/md5.js"></script>
<script type="text/javascript" src="/js/login.js"></script>
<script type="text/javascript">
 if(parent.top.location != self.location){ parent.top.location=self.location; }
</script>
</head>

<body>
	<div class="top_div">
	   <p class="h3css">PaaS运营管理平台 - 正式版</p>
	   <p class="h4css">&copy; 智语科技</p>
	</div>
	<div class="top_banner">
		<div style="width: 165px; height: 96px; position: absolute;">
			<div class="tou"></div>
			<div class="initial_left_hand" id="left_hand"></div>
			<div class="initial_right_hand" id="right_hand"></div>
		</div>
		<p style="padding: 30px 0px 10px; position: relative;">
			<span class="u_logo"></span> 
			<input class="ipt" type="text" id="uname" tabindex="1"  onfocus=""
				placeholder="用户账号" value="">
		</p>
		<p style="position: relative; margin-top: 10px;">
			<span class="p_logo"></span> 
			<input class="ipt" id="upwd" tabindex="2" 
				type="password" placeholder="用户密码" value="">
		</p>
		<p style="position: relative; margin-top: 10px; text-align: center;">
		    <span class="tips" id="msgtips">&nbsp;</span>
		</p>
		<div class="lg">
			<p style="margin: 5px 35px 20px 45px;">
				
				<span style="float: left; text-align: center;">
					<input type="checkbox" id="rmbuser" style="vertical-align:-2px;"><a class="btnlink"href="#">&nbsp; 记住密码</a>
				</span>
				<span>
					<a class="btnok" href="#" id="loginbtn" tabindex="3" >登录</a> 
					<a class="btnok btncl2" href="#" id="resetbtn"  style="margin-left: 50px;"  tabindex="4">重置</a>
				</span>
			</p>
		</div>
	</div>
	<div style="text-align: center;">
		<p style="margin-top: 280px;"><span>深圳市智语科技有限公司 </span>©All Rights Reserved 粤ICP备15043911号-2 </p>
	</div>
</body>
</html>