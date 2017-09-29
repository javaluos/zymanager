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
					系统设置<small>&nbsp;-->&nbsp;敏感词管理</small>
				</h5>
			</div>
			<div>
				<form method="get" class="form-vertical">

					<table class="noborder">
						<tbody>
							<tr class="noline">
							
								<td class="fr" style="width:6%">敏感词内容：</td>
								<td class="online" style="width:8%;">
									<input type="text" id="black_key" class="form-control" style="width:220px;">
								</td>
								
								<td class="fr" style="width:5%">所属行业：</td>
								<td class="online" style="width:8%">
									<input type="text" id="industry" class="form-control"  style="width:220px;">
								</td>
								
								<td class="fr" style="width:5%">加入时间：</td>
								<td class="online" style="width:30%">
									<li style="display: inline-block;">
									<input class="form-control" id="starttime" style="width: 180px;margin-top:5px" value="${datetimeStart}">
									<label style="margin-top:5px">&nbsp;至&nbsp;</label> 
									<input class="form-control" id="endtime" style="width: 180px;margin-top:5px" value="${datetimeEnd}">
								  </li>
								</td>
								
								<td>
								    <button id="btnquery"
										class="btn btn-sm btn-primary pull-left" 
										type="button">查询数据</button>
								    <button id="btnadd"	onclick="toadd();" style="margin-left: 20px;"
										class="btn btn-sm btn-primary pull-left"
										type="button">增加敏感词</button>
								    <button id="btnexport" style="margin-left:20px" style="margin-left: 20px;"
                                        class="btn btn-sm btn-success pull-left"
                                        type="button">导出</button>
                                    <button id="btnbackparent" style="margin-left:20px" style="margin-left: 20px;"
                                        class="btn btn-sm btn-success pull-left"
                                        type="button">返回</button>
                                </td>
							</tr>
							
							<tr>
							    <td class="fr" style="width:6%">策略名称:</td>
								<td class="online" style="width:8%;">
								   ${policyName}
								</td>
								
								<td class="fr" style="width:5%"></td>
								<td class="online" style="width:8%">
								    <input type="hidden" id="policyId" class="form-control" value="${policyId}"/>
							        <input type="hidden" id="policyName" class="form-control" value="${policyName}">
								</td>
							
							    <td class="fr" style="width:5%"></td>
								<td class="online" style="width:30%"></td>
								
								<td></td>
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
	<script type="text/javascript" src="/js/handler/blackkeylist.js"></script>
	<script type="text/javascript">
		/* layer.style(index, {
		  width: '600px',
		}); */
		function dodel(id, userName){
			var policyId=$("#policyId").val();
			var policyName=$("#policyName").val();
			layer.confirm("您确定要删除敏感词【"+userName+"】吗？", {
				area: ['450px'],
				btn: ['确认','取消'] //按钮
			}, function(){
				window.location.href="/black_key_list/do_delete/"+id+"/"+policyId+"?policyName="+policyName;
			}, function(){
				layer.closeAll('dialog');
			});
		}
		function toadd(){
			var policyId=$("#policyId").val();
			var policyName=$("#policyName").val();
			window.location.href="/black_key_list/toadd?policyId="+policyId+"&policyName="+policyName;
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
				  area: ['600px', '450px'],
				  title: '修改敏感词',
				  shade: 0.3, //遮罩透明度
				  moveType: 0, //拖拽风格，0是默认，1是传统拖动
				  shift: 1, //0-6的动画形式，-1不开启
				  scrollbar:false,
				  content:"/black_key_list/to_update/"+id+""
			   }); 
		}
	</script>
</body>
</html>
