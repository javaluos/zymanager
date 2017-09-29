<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="../comm/plugin.jsp" />

<script type="text/javascript" language="javascript"> 
function view(apiAccount,smsContent,blackContent){
	window.location.href="/sms_send_filter/to_list_detail?apiAccount="+apiAccount+"&smsContent="+smsContent+"&blackContent="+blackContent;
}
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
    	layer.alert("请选择要删除的黑名单！");
        return false;
    }
    layer.confirm("你确定要删除这些黑名单吗吗？", {
		btn: ['确认','取消'] //按钮
	}, function(){
		layer.closeAll('dialog');
		$.ajax({
			type: "POST",
	   		url: "/sms_black_list/do_delete",
	   		data: {"mobiles" : mobiles},
	   		dataType: "json",
		   	success: function(data){
		   		if(data>0){
		   			layer.alert("删除黑名单号码成功,总计删除"+data+"个！");
		   		 	setTimeout('window.location.href="/sms_black_list/to_list"',2000)
		   		}else{
		   			layer.alert("删除黑名单号码失败！");
		   		}
		   	}
		});
	}, function(){
		layer.closeAll('dialog');
	});
}

function audit24(apiAccount,smsContent){
	//通过
	var arr=[]; 
	arr[0]={"apiAccount" : apiAccount,"smsContent":smsContent};
	var params={"list":arr};
	$.ajax({
		type: "POST",
   		url: "/sms_send_filter/auditPaas",
//    	data: {"apiAccount" : apiAccount,"smsContent":smsContent},
   	    data:{params: JSON.stringify(params)},
   		dataType: "json",
	   	success: function(data){
	   		if(data=="1"){
	   			window.top.location.href='/login.html';
	   		}else if(data=="2"){
	   			window.location.href='/sms_send_filter/to_list.html';
	   		}else{
	   		}
	   	}
	});
}

function audit54(apiAccount,smsContent){
	//拒绝
	var arr=[]; 
	arr[0]={"apiAccount" : apiAccount,"smsContent":smsContent};
	var params={"list":arr};
	$.ajax({
		type: "POST",
   		url: "/sms_send_filter/auditNotPaas",
   	    data:{params: JSON.stringify(params)},
   		dataType: "json",
	   	success: function(data){
	   		if(data=="1"){
	   			window.top.location.href='/login.html';
	   		}else if(data=="2"){
	   			window.location.href='/sms_send_filter/to_list.html';
	   		}else{
	   		}
	   	}
	});
}

function batchHandle(pass){
	
	var wordAllCheck=$("#wordAllCheck").is(":checked");
	var allCheckBoxs = document.getElementsByName("preDelCheck"); 
	var arr=[]; 
	var params={};
	if(!wordAllCheck){
		var n=0;
		for(var i = 0; i < allCheckBoxs.length; i ++ ) {
			if(allCheckBoxs[i].checked){ 
				var t={"apiAccount":allCheckBoxs[i].id,"smsContent":allCheckBoxs[i].value};
				arr[n]=t;
				n++;
			} 
		}
		params={"list":arr};
	}
	if(wordAllCheck){
		arr[0]={"apiAccount":"","smsContent":""};
		params={"list":arr};
	}
	if(pass=="2"){
		//通过
		$.ajax({
			type: "POST",
	   		url: "/sms_send_filter/auditPaas",
//	    	data: {"apiAccount" : apiAccount,"smsContent":smsContent},
	   	    data:{params: JSON.stringify(params)},
	   		dataType: "json",
		   	success: function(data){
		   		if(data=="1"){
		   			window.top.location.href='/login.html';
		   		}else if(data=="2"){
		   			window.location.href='/sms_send_filter/to_list.html';
		   		}else{
		   		}
		   	}
		});
	}else if(pass=="5"){
		//拒绝
		$.ajax({
			type: "POST",
	   		url: "/sms_send_filter/auditNotPaas",
	   	    data:{params: JSON.stringify(params)},
	   		dataType: "json",
		   	success: function(data){
		   		if(data=="1"){
		   			window.top.location.href='/login.html';
		   		}else if(data=="2"){
		   			window.location.href='/sms_send_filter/to_list.html';
		   		}else{
		   		}
		   	}
		});
	}
	
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
				    <th style="background: 0 0;width: 35px;" 
				        tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">
				    	<a id="allChecked" onClick="selectAllDels()">全选</a>
				    </th>
					<th style="width: 35px;">序号</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">客户名称</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">客户账号</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">短信内容</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">敏感词</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">条数</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">提交时间</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">操作</th>
				</tr>
			</thead>
			<tbody>

				<c:if test="${not empty pgdata.data }">
					<c:forEach var="dt" items="${pgdata.data}" varStatus="dtindex">
						<tr class="gradeC odd">
						    <td align="center"><input type="checkbox" name="preDelCheck" id="${dt.apiAccount}" value="${dt.smsContent}"></td>
							<td>${(pgdata.page_num-1)*pgdata.page_size + dtindex.index+1}</td>
							<td>${accountMaps[dt.apiAccount].businessName}</td>
							<td>${accountMaps[dt.apiAccount].merchantPhone}</td>
							<td>
								${dt.smsContent}【${dt.signContent}】
							</td>
							<td>${dt.blackContent}</td>
							<td><a href="javascript:view('${dt.apiAccount}','${dt.smsContent}','${dt.blackContent}')">${dt.smsNums}</a></td>
							<td>
							    <fmt:formatDate value="${dt.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td>
							   <a href="javascript:audit24('${dt.apiAccount}','${dt.smsContent}')">通过</a> 
							   &nbsp;&nbsp;&nbsp;&nbsp;
							   <a href="javascript:audit54('${dt.apiAccount}','${dt.smsContent}')">拒绝</a> 
							</td>
						</tr>
					</c:forEach>

				</c:if>
				<c:if test="${empty pgdata.data }">
					<tr>
						<td colspan="11" style="text-align: center;">暂无数据.</td>
					</tr>
				</c:if>

			</tbody>

		</table>
		<p class="msg">
			<jsp:include page="../comm/pgbar.jsp" />
		</p>
	</div>

</div>
