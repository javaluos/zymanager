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
								<a onclick="doadd('${dt.apiAccount}','${dt.merchantPhone}','${dt.businessName }');">添加绑定&nbsp;</a>
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
	   		url: "/channel_group/get_bind_channel_group",
	   		data: {"apiAccount" : apiAccount},
	   		dataType: "json",
		   	complete: function(xhr,status){
		   		let data = xhr.responseText;
		   		let group_yd = parent.$("#group_yd");
	   			let group_lt = parent.$("#group_lt");
	   			let group_dx = parent.$("#group_dx");
		   		if("" != data){
		   			let obj = JSON.parse(data);
		   			group_yd.val(obj.groupYd);
		   			group_lt.val(obj.groupLt);
		   			group_dx.val(obj.groupDx);
		   		}else{
		   			group_yd.val("");
		   			group_lt.val("");
		   			group_dx.val("");
		   		}
		   		group_yd.trigger('chosen:updated');
		   		group_lt.trigger('chosen:updated');
		   		group_dx.trigger('chosen:updated');
		   	}
		});
		parent.$("#accid").empty();
		var accid = parent.$("#accid");
		var html = "<tr id='"+apiAccount+"' class='noline'>";
		html += "<td class='fr' style='width: 12%;'>客户账号：</td>";
		html += "<td class='online' style='width: 20%'>"+merchantPhone+"</td>";		
		html += "<td class='fr' style='width: 12%;'>客户名称：</td>";		
		html += "<td class='online' style='width: 25%'>"+businessName+"<input type='hidden' name='apiAccount' value='"+apiAccount+"'/>";
		html += "<input type='hidden' name='merchantPhone' value='"+merchantPhone+"'/></td>";
		// html += "<td class='online'> <a style='margin-left:20px' onclick=delacctr('"+apiAccount+"');>删除</a></td></tr>";
		html += "</tr>";
		accid.append(html);
		console.info(apiAccount);
	}
</script>
