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
					系统设置<small>&nbsp;-->&nbsp;拦截策略设置</small>
				</h5>
			</div>
			<div>
				<form method="get" class="form-vertical">

					<table class="noborder">
						<tbody>
							<tr class="noline">
								<td class="fr" style="width:6%">策略名称：</td>
								<td class="online" style="width:8%">
									<input type="text" id="policyName" class="form-control" style="width:220px;">
								</td>
								<td>
									<button id="btnquery"
											class="btn btn-sm btn-primary pull-left"
											type="button">查询数据</button>
									<button id="btnadd"	onclick="toadd();" 
											class="btn btn-sm btn-primary pull-left"
											style="margin-left: 30px;"
											type="button">新增策略</button>
							     </td>
							</tr>
						</tbody>
					</table>

				</form>
			</div>
		</div>
	</div>

	<div id="datagrid"></div>
	</div>

	</div>

	<script type="text/javascript" src="/js/handler/blackkeypolicylist.js"></script>
	<script type="text/javascript">
	
		function dodel(id, userName){
			layer.confirm("您确定要删除策略【"+userName+"】吗？", {
				area: ['450px'],
				btn: ['确认','取消'] //按钮
			}, function(){
				window.location.href="/black_key_policy/do_delete/"+id+"";
			}, function(){
				layer.closeAll('dialog');
			});
		}
		
		function toadd(){
			layer.open({
				  type: 2, //page层
				  area: ['600px', '350px'],
				  title: '新增策略',
				  shade: 0.3, //遮罩透明度
				  moveType: 0, //拖拽风格，0是默认，1是传统拖动
				  shift: 1, //0-6的动画形式，-1不开启
				  scrollbar:false,
				  content:"/black_key_policy/toadd"
			 }); 
		}
		
		var start = {
			    format: 'YYYY-MM-DD hh:mm:ss',
			    minDate: '', //设定最小日期为当前日期
			    maxDate: $.nowDate(0), //最大日期
			    choosefun: function(elem,datas){
			        end.minDate = datas; //开始日选好后，重置结束日的最小日期
			    }
			};
			var end = {
			    format: 'YYYY-MM-DD hh:mm:ss',
			    minDate: '', //设定最小日期为当前日期
			    maxDate: '', //最大日期
			    choosefun: function(elem,datas){
			       start.maxDate = datas; //将结束日的初始值设定为开始日的最大日期
			    }
			};
		$("#starttime").jeDate(start);
		$("#endtime").jeDate(end);
		
		function update(id){
			layer.open({
				  type: 2, //page层
				  area: ['600px', '350px'],
				  title: '修改策略',
				  shade: 0.3, //遮罩透明度
				  moveType: 0, //拖拽风格，0是默认，1是传统拖动
				  shift: 1, //0-6的动画形式，-1不开启
				  scrollbar:false,
				  content:"/black_key_policy/to_update/"+id+""
			   }); 
		}
		
		function updatePolicy(id,policyName){
			window.location.href="/black_key_list/to_list?policyId="+id+"&policyName="+policyName;
		}
	</script>
	</script>
</body>
</html>
