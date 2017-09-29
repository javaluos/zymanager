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
					权限设置<small>&nbsp;-->&nbsp;用户管理</small>
				</h5>
			</div>
			<div>
				<form method="get" class="form-vertical">

					<table class="noborder">
						<tbody>
							<tr class="noline">
								<td class="fr" style="width:5%">账号：</td>
								<td class="online" style="width:10%">
									<input type="text" id="username" class="form-control" style="width:281px;">
								</td>
								<td><button id="btnquery"
										class="btn btn-sm btn-primary pull-left"
										type="button">查询数据</button></td>
								<td><button id="btnadd"	onclick="toadd();"
										class="btn btn-sm btn-primary pull-right"
										type="button">添加用户</button></td>
							</tr>
						</tbody>
					</table>

				</form>
			</div>
		</div>
	</div>
	<!-- /QueryForm -->

	<!-- Datagrid -->
	<div id="datagrid"></div>
	</div>
	<!-- /Datagrid -->

	</div>
	<!-- /wrap_right -->
	<script type="text/javascript" src="/js/handler/userlist.js"></script>
	<script type="text/javascript">
		/* layer.style(index, {
		  width: '600px',
		}); */
		function deluser(userid, userName){
			layer.confirm("您确定要删除账号【"+userName+"】吗？", {
				area: ['450px'],
				btn: ['确认','取消'] //按钮
			}, function(){
				window.location.href="/user/user_delete/"+userid+"";
			}, function(){
				layer.closeAll('dialog');
			});
		}
		function initpwd(userid, userName){
			layer.confirm("重置密码后，新的密码为【123456】，请确认是否重置密码？", {
				btn: ['确认','取消'], //按钮
				area: ['450px']
			}, function(){
				window.location.href="/user/init_pwd/"+userid+"";
			}, function(){
				layer.closeAll('dialog');
			});
		}
		function updateuser(userid){
			layer.open({
	   			  type: 2, //page层
	   			  area: ['850px', '780px'],
	   			  title: '用户权限设置',
	   			  shade: 0.3, //遮罩透明度
	   			  moveType: 1, //拖拽风格，0是默认，1是传统拖动
	   			  shift: 1, //0-6的动画形式，-1不开启
	   			  content: ['/user/to_user_update/'+userid+'', 'no']
   			}); 
		}
		function toadd(){
			window.location.href="/user/to_user_add";
		}
	</script>
</body>
</html>
