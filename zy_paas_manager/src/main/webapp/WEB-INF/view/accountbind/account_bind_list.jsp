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
					权限设置<small>&nbsp;-->&nbsp;客户绑定</small>
				</h5>
			</div>
			<div>
				<form method="get" class="form-vertical">

					<table class="noborder">
						<tbody>
							<tr class="noline">
								<td class="fr" style="width:8%">账号名称：</td>
								<td class="online" style="width:20%">
									<input type="text" id="userName" class="form-control" style="width:250px;" maxlength="50">
								</td>
								<td class="fr" style="width:8%">真实姓名：</td>
								<td class="online" style="width:20%">
									<input type="text" id="fullName" class="form-control" style="width:250px;" maxlength="50">
								</td>
								<td class="fr" style="width:8%">所属部门：</td>
								<td class="online" style="width:22%">
								    <select class="form-control" id="deptNo" style="width: 250px;">
										<option value=""></option>
										<c:forEach var="dept" items="${deptList }">
											<option value="${dept.id }">${dept.name }</option>
										</c:forEach>
								    </select>
							    </td>

								<td><button id="btnquery"
										class="btn btn-sm btn-primary pull-left"
										type="button">查询数据</button></td>
								<td><button id="addbind" onclick="tobind();"
										class="btn btn-sm btn-info pull-left"
										type="button">添加绑定</button></td>
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
	<script type="text/javascript" src="/js/handler/accountbind.js"></script>
	<script type="text/javascript">
		function tobind(){
			window.location.href='/account_bind/to_bind;';
		}
		
		function modify(username){
			layer.open({
				type: 2, //page层
			  	area: ['1080px', '800px'],
			  	title: '绑定信息',
				shade: 0.3, //遮罩透明度
			 	moveType: 0, //拖拽风格，0是默认，1是传统拖动
			 	shift: 1, //0-6的动画形式，-1不开启
			  	scrollbar:false,
			  	content:'/account_bind/to_modify_bind/'+username+''
		   	});
		}
		function del(userName){
			layer.confirm("是否删除账号为：【" +userName+" 】的所有客户绑定记录？", {
				area: ['450px'],
				btn: ['确认','取消'] //按钮
			}, function(){
				window.location.href="/account_bind/accountbind_del/"+userName+"";
			}, function(){
				layer.closeAll('dialog');
			});
		}
	</script>
</body>
</html>
