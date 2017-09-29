<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<jsp:include page="../comm/plugin.jsp" />

<!-- QueryData -->
<div id="QueryData">

	<div id="DataTables_Table_0_wrapper"
		class="dataTables_wrapper form-inline" role="grid">
		<table
			class="table table-striped table-bordered table-hover dataTables-example dataTable"
			id="DataTables_Table_0" aria-describedby="DataTables_Table_0_info">
			<thead>
				<tr role="row">
				    <th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">序号</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">客户名称</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">客户账号</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">操作</th>
				</tr>
			</thead>
			<tbody>

				<c:if test="${not empty pgdata.data }">
					<c:forEach var="dt" items="${pgdata.data}" varStatus="dtindex">
						<tr class="gradeC odd">
							<td>${(pgdata.page_num-1)*pgdata.page_size + dtindex.index+1}</td>
							<td>
								${dt.businessName }
							</td>
							<td>${dt.merchantPhone}</td>
							<td>
								<a onclick="doadd('${dt.apiAccount}','${dt.merchantPhone}','${dt.businessName }');">绑定&nbsp;</a>
							</td>
						</tr>
					</c:forEach>

				</c:if>
				<c:if test="${empty pgdata.data }">
					<tr>
						<td colspan="4" style="text-align: center;">暂无数据.</td>
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
	function doadd(apiAccount, merchantPhone, businessName){
		parent.$("#channelId").empty();
		$.ajax({
			type: "POST",
	   		url: "/channel_bind/get_bind_channel",
	   		data: {"apiAccount" : apiAccount},
	   		dataType: "json",
		   	success: function(data){
		   		if("" != data){
		   			var channelId = parent.$("#channelId");
		   			var mht = "<tr>";
		   			mht += "<th class='fr' style='width: 15%;'>通道名称</th>";
					mht += "<th class='fr' style='width: 15%;'>通道ID</th>";
					mht += "<th class='fr' style='width: 10%;'>通道类型</th>";
					mht += "<th class='fr' style='width: 5%;'>落地省份</th>";
					mht += "<th class='fr' style='width: 5%;'>跑量省份</th>";
					mht += "<th class='fr' style='width: 5%;'>评分</th>";
					mht += "<th class='fr' style='width: 10%;'>阀值(条)</th>";
					mht += "<th class='fr' style='width: 5%;'>操作类型</th>";
					mht += "<th class='fr' style='width: 5%;'>操作</th>"
					mht += "<th></th>";
					mht +="</tr>";
			   		$.each(data, function (k, v) {
						mht +="<tr id='"+v.channelId+"' class='noline'>";
						mht +="<td class='fr'>"+v.channelName+"</td>";
						mht +="<td class='fr'>"+v.channelId+"</td>";
						if(v.channelType == 1){
							mht +="<td class='fr'>通知</td>";		
						}
						if(v.channelType == 2){
							mht +="<td class='fr'>验证码</td>";		
						}
						if(v.channelType == 3){
							mht +="<td class='fr'>营销</td>";		
						}
						if(v.channelType == 4){
							mht +="<td class='fr'>通知、验证码</td>";		
						}
						if(v.channelType == 5){
							mht +="<td class='fr'>通知、验证码、营销</td>";		
						}
						if(v.dtnProvince != ''){
							mht +="<td class='fr'>"+v.dtnProvince+"</td>";
						}else{
							mht +="<td class='fr'></td>";
						}
						if(v.useProvince == 0){
							mht +="<td class='fr'>全国</td>";
						}else if(v.useProvince == 1){
							mht +="<td class='fr'>本省</td>";
						}else if(v.useProvince == 2){
							mht +="<td class='fr'>非本省</td>";
						}else{
							mht +="<td class='fr'></td>";
						}
						mht +="<td><input type='number' name='score' id='score' value='"+v.channelScore+"' maxlength='3'  size='3' oninput='if(value.length>3)value=value.slice(0,3)' style='width:60px' /></td>";
						mht +="<td><input type='number' name='thresholdValue' id='thresholdValue' value='"+v.thresholdValue+"' size='9' maxlength='9' oninput='if(value.length>9)value=value.slice(0,9)' style='width:120px' /></td>";
						mht +="<td class='fr'>已绑定</td>";
						mht +="<td> <a onclick=delchanneltr('"+v.channelId+"');>删除</a></td></tr>";
		           	});
			   		channelId.append(mht);
		   		}
		   	}
		});
		parent.$("#accid").empty();
		var accid = parent.$("#accid");
		var html = "<tr id='"+apiAccount+"' class='noline'>";
		html += "<td class='fr' style='width: 12%;'>客户账号：</td>";
		html += "<td class='online' style='width: 20%'>"+merchantPhone+"</td>";		
		html += "<td class='fr' style='width: 12%;'>客户名称：</td>";		
		html += "<td class='online' style='width: 25%'>"+businessName+"</td>";
		html += "<td class='online'> <a style='margin-left:20px' onclick=delacctr('"+apiAccount+"');>删除</a></td></tr>";
		accid.append(html);
	}
	
	/* function getacclist(){
		var businessname = $("#businessname").val();
		var merchantphone = $("#merchantphone").val();
		window.location.href = '/channel_bind/acc_list_data?businessname='+businessname+'&merchantphone='+merchantphone+'';
	} */
</script>
