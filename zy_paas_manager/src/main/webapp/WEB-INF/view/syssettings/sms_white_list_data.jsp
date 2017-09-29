<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>

<script type="text/javascript" language="javascript"> 
function selectAllDels(){ 
	var allCheckBoxs = document.getElementsByName("preDelCheck"); 
	var desc = document.getElementById("allChecked"); 
	var text=$("#allChecked").text();
    if(text=='全选'){
    	$("#allChecked").text("取消全选");
    }else{
    	$("#allChecked").text("全选");
    }
	
	var selectOrUnselect=false; 
	for(var i = 0; i < allCheckBoxs.length; i ++ ) { 
		if(allCheckBoxs[i].checked){ 
			selectOrUnselect=true; 
			break; 
		} 
	} 
	if (selectOrUnselect) { 
		_allUnchecked(allCheckBoxs); 
	}else { 
		_allchecked(allCheckBoxs); 
	} 
} 
	
function _allchecked(allCheckBoxs){ 
	for(var i = 0; i < allCheckBoxs.length; i ++ ) { 
	    allCheckBoxs[i].checked = true; 
    } 
} 

function _allUnchecked(allCheckBoxs){ 
	for(var i = 0; i < allCheckBoxs.length; i ++ ) { 
	   allCheckBoxs[i].checked = false; 
	} 
} 

function delAll(){
	var mobile;
	var n=0;
    var mobiles;
    var allCheckBoxs =document.getElementsByName("preDelCheck"); 
	for(i=0;i<allCheckBoxs.length;i++){
	    if(allCheckBoxs[i].checked){
	        n=n+1;
	        s=true;
	        mobile=allCheckBoxs[i].id+"";
	        if(n==1){
	        	mobiles=mobile;
	        }
	        else {
	        	mobiles=mobiles+","+mobile;
	        }
	    }
	}
   
    if(!s){
    	layer.alert("请选择要删除的白名单！");
        return false;
    }
    layer.confirm("你确定要删除这些白名单吗吗？", {
		btn: ['确认','取消'] //按钮
	}, function(){
		layer.closeAll('dialog');
		$.ajax({
			type: "POST",
	   		url: "/sms_white_list/do_delete",
	   		data: {"mobiles" : mobiles},
	   		dataType: "json",
		   	success: function(data){
		   		if(data>0){
		   			layer.alert("删除白名单号码成功,总计删除"+data+"个！");
		   		 	setTimeout('window.location.href="/sms_white_list/to_list"',2000)
		   		}else{
		   			layer.alert("删除白名单号码失败！");
		   		}
		   	}
		});
	}, function(){
		layer.closeAll('dialog');
	});
}

</script> 

<!-- QueryData -->
<div id="QueryData">

	<div id="DataTables_Table_0_wrapper"
		class="dataTables_wrapper form-inline" role="grid">
		<table
			class="table table-striped table-bordered table-hover dataTables-example dataTable"
			id="DataTables_Table_0" aria-describedby="DataTables_Table_0_info">
			<thead>
				<tr role="row">
				    <th style="background: 0 0;width: 90px;" 
				        tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">
				    	<a id="allChecked" onClick="selectAllDels()">全选</a>
				    </th>
					<th style="background: 0 0;">序号</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">白名单号码</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">所属客户</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">加入时间</th>	
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">备注</th>
				</tr>
			</thead>
			<tbody>

				<c:if test="${not empty pgdata.data }">
					<c:forEach var="dt" items="${pgdata.data}" varStatus="dtindex">
						<tr class="gradeC odd">
						    <td align="center"><input type="checkbox" name="preDelCheck" id="${dt.mobile}"></td>
							<td align="center">${(pgdata.page_num-1)*pgdata.page_size + dtindex.index+1}</td>
							<td align="center">
								${dt.mobile }
							</td>
							<td align="center">${dt.businessName}</td>
							<td align="center">
								<fmt:formatDate value="${dt.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td align="center">
								${dt.remark}
							</td>
						</tr>
					</c:forEach>

				</c:if>
				<c:if test="${empty pgdata.data }">
					<tr>
						<td colspan="6" style="text-align: center;">暂无数据.</td>
					</tr>
				</c:if>
			</tbody>
		</table>
		<p class="msg">
			<jsp:include page="../comm/pgbar.jsp" />
		</p>
	</div>

</div>
