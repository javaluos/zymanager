<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- QueryData -->
<div id="QueryData">
	<div id="DataTables_Table_0_wrapper"
		class="dataTables_wrapper form-inline" role="grid">
		<table
			class="table table-striped table-bordered table-hover dataTables-example dataTable"
			id="DataTables_Table_0" aria-describedby="DataTables_Table_0_info">
			<thead>
				<tr role="row">
					<th style="background: 0 0;min-width:50px;">序号</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">客户名称</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">客户账号</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">通道名称</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">通道ID</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" style="min-width:80px;">通道类型</th>	
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" style="min-width:60px;">运营商类型</th>	
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" style="min-width:50px;">通道属性</th>	
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" style="min-width:50px;">签名</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" style="min-width:50px;">模板</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" style="min-width:50px;">状态</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" style="min-width:50px;">落地省份</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" style="min-width:50px;">跑量省份</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">评分</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" style="min-width:50px;">阀值(条)</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">绑定时间</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" style="max-width: 150px;">备注</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" style="min-width:80px;">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty pgdata.data }">
					<c:forEach var="dt" items="${pgdata.data}" varStatus="dtindex">
						<tr class="gradeC odd">
							<td>${(pgdata.page_num-1)*pgdata.page_size + dtindex.index+1}</td>
							<td>${dt.businessName }</td>
							<td>${dt.merchantPhone }</td>
							<td>${dt.channelName }</td>
							<td>${dt.channelId }</td>
							<td>
							   <c:choose>
									<c:when test="${dt.channelType==1}">
										 通知
									</c:when>
									<c:when test="${dt.channelType==2}">   
									              验证码
									</c:when>
									<c:when test="${dt.channelType==3}">   
									             营销         
									</c:when>
						            <c:when test="${dt.channelType==4}">   
									            通知、验证码
									</c:when>
						            <c:when test="${dt.channelType==5}">   
									            通知、验证码、营销
									</c:when>
									<c:otherwise>   
								     &nbsp;         
								  </c:otherwise>
								</c:choose>
							</td>
							<td>
							   <c:choose>
							   		<c:when test="${dt.operatorType==0}">
										三网合一
									</c:when>
									<c:when test="${dt.operatorType==1}">
										移动
									</c:when>
									<c:when test="${dt.operatorType==2}">   
									              电信
									</c:when>
									<c:when test="${dt.operatorType==3}">   
									              联通
									</c:when>
									<c:otherwise>   
								     &nbsp;         
								  </c:otherwise>
								</c:choose>
							</td>
							<td>
							   <c:choose>
									<c:when test="${dt.channelProperty=='10'}">
										移动
									</c:when>
									<c:when test="${dt.channelProperty=='20'}">   
									              电信
									</c:when>
									<c:when test="${dt.channelProperty=='30'}">   
									              联通
									</c:when>
									<c:otherwise>   
								     &nbsp;         
								  </c:otherwise>
								</c:choose>
							</td>
				            <td>
							  <c:choose>
								  <c:when test="${dt.signerAudit==0}">   
								             免报备
								  </c:when>
								  <c:when test="${dt.signerAudit==1}">   
								             报备
								  </c:when>
								  <c:otherwise>   
								    &nbsp;         
								  </c:otherwise>
							  </c:choose>
							</td>
						    <td>
							   <c:choose>
								  <c:when test="${dt.templateAudit==0}">   
								             免报备
								  </c:when>
								  <c:when test="${dt.templateAudit==1}">   
								             报备
								  </c:when>
								  <c:otherwise>   
								    &nbsp;         
								  </c:otherwise>
							  </c:choose>
							</td>	
							<td><c:choose>
								    <c:when test="${dt.status==0}">   
									               新创建
									</c:when>
								    <c:when test="${dt.status==2}">   
									              对接中
									</c:when>
									<c:when test="${dt.status==1}">   
									              运营中
									</c:when>
									<c:when test="${dt.status==3}">   
									              作废
									</c:when>
									<c:otherwise>   
								     &nbsp;         
								  </c:otherwise>
								</c:choose>
							</td>
							<td>${dt.dtnProvince }</td>
							<td>
								<c:if test="${dt.useProvince == 0}">
									全国
								</c:if>
								<c:if test="${dt.useProvince == 1}">
									本省
								</c:if>
								<c:if test="${dt.useProvince == 2}">
									非本省
								</c:if>
							</td>
							<td>${dt.channelScore } </td>
							<td>${dt.thresholdValue }</td>
							<td>
								<fmt:formatDate value="${dt.updateTime }" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td>${dt.remark } </td>
							<td>
							    <a href="javascript:;" onclick="clearsend('${dt.apiAccount }','${dt.channelId }');">发送量清零</a>
							    &nbsp;|&nbsp;
							    <a href="javascript:;" onclick="scoremodify('${dt.id }','${dt.channelName }','${dt.businessName }', ${dt.channelScore }, ${dt.thresholdValue });">修改评分</a>
							    &nbsp;|&nbsp;
							    <a href="javascript:;" onclick="del('${dt.id}');" >删除</a>
							</td>		
						</tr>
					</c:forEach>

				</c:if>
				<c:if test="${empty pgdata.data }">
					<tr>
						<td colspan="16" style="text-align: center;">暂无数据.</td>
					</tr>
				</c:if>

			</tbody>

		</table>
		<p class="msg">
			<jsp:include page="../comm/pgbar.jsp" />
		</p>
	</div>

</div>
<script type="text/javascript">
function scoremodify(id, channelName, businessName, channelScore, thresholdValue){
	
	var mht = '<li style="text-align:center"><h3>通道评分修改!</h3></li>';
	//mht += '<form method="post" action="channel_bind/score_modify">';
	mht += '<li style="margin-top:10px"><font style="margin-left:20px;">客户名称：</font><font style="margin-left:20px">'+businessName+'</font></li>';
	mht += '<li style="margin-top:10px"><font style="margin-left:20px;">通道名称：</font><font style="margin-left:20px">'+channelName+'</font></li>';
	mht += '<li style="margin-top:10px"><font style="margin-left:20px;">通道评分：</font><input style="margin-left:20px;width:140px;" type="text" name="score" id="score" maxlength="3" value="'+channelScore+'" /></li>';
	mht += '<li style="margin-top:10px"><font style="margin-left:20px;">通道伐值：</font><input style="margin-left:20px;width:140px;" type="text" name="thresholdValue" id="thresholdValue" maxlength="9" value="'+thresholdValue+'" /></li>';
	mht += '<li style="margin-top:10px;text-align:center"><button class="btn btn-primary" type="button" onclick="submit('+id+');" style="width:60px;margin-left:20px;margin-right:100px;" id="btnexce1">确认</button><button class="btn btn-primary" type="button" style="width: 60px;" id="btnexce2" onclick="cancel();">取消</button></li>';
	//mht += '</form>';
	mht += '<div id="errormsg" style="color: red;margin-top:10px;"></div>';
	
	layer.open({
		  type: 1, //page层
		  area: ['450px', '320px'],
		  title: '通道绑定管理-评分修改',
		  shade: 0.3, //遮罩透明度
		  moveType: 1, //拖拽风格，0是默认，1是传统拖动
		  shift: 1, //0-6的动画形式，-1不开启
		  content: '<div style="padding:50px; padding-top:20px;">'+ mht+ '</div>'
		}); 
}

function submit(id){
	$("#errormsg").empty();
	var score = $("#score").val();
	if(score.trim() == ''){
		$("#errormsg").text("通道评分不能为空");
		return false;
	}
	if(score > 100){
		$("#errormsg").text("通道评分不能大于100");
		return false;
	}
	if(score < 0){
		$("#errormsg").text("通道评分不能小于于0");
		return false;
	}
	var reg = /^(100|[1-9]\d|\d)$/;
	if(reg.test(score) == false){
		$("#errormsg").text("通道评分必须为0-100的整数");
		return false;
	}
	var thresholdValue = $("#thresholdValue").val();
	var thresholdValueReg = /^[1-9]{1}\d{0,8}$/;
	if(thresholdValue.trim() == ''){
		$("#errormsg").text("通道阀值不能为空");
		return false;
	}
	if(thresholdValueReg.test(thresholdValue) == false){
		$("#errormsg").text("通道阀值必须为1-9位的整数");
		return false;
	}
	$.ajax({
		type: "POST",
   		url: "/channel_bind/score_modify",
		data: {"id" : id, "score" : score, "thresholdValue" : thresholdValue},
		success: function(data){
			if(data == 'true' || data == true){
				layer.alert("通道评分修改成功");
				layer.closeAll();
                $("#btnquery").click();
			}else{
				$("#errormsg").text("通道评分修改失败");
			}	 
	   	}
	});
}

function del(id){
	layer.confirm("您确定要删除该条绑定记录吗？", {
		area: ['450px'],
		btn: ['确认','取消'] //按钮
	}, function(){
		$.ajax({
            type: "POST",
            url: "/channel_bind/del_bind",
            data: {"id" : id},
            dataType: "json",
            success: function(data){
                layer.closeAll('dialog');
                $("#btnquery").click();
            }
		});
	});
} 

function cancel(){
	layer.closeAll();
}

function clearsend(apiAccount, channelId){
	layer.confirm("您确定要将通道【"+channelId+"】的发送量清空吗？", {
		area: ['450px'],
		btn: ['确认','取消'] //按钮
	}, function(){
	    $.ajax({
            type: "POST",
            url: "/channel_bind/clear_send_total",
            data: {"apiAccount" : apiAccount, "channelId" : channelId},
            dataType: "json",
            success: function(data){
                layer.closeAll('dialog');
                $("#btnquery").click();
            }
        });
	});
}
</script>
