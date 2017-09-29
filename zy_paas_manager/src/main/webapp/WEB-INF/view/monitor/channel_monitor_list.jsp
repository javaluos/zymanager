<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@  taglib  uri="http://java.sun.com/jsp/jstl/functions"   prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>列表查询</title>
	<jsp:include page="../comm/plugin.jsp" />
    <script type="text/javascript">
		function modify(channelId) {
			var preChannelId=$("#channelId").val();
			var preChannelName=$("#channelName").val();
			var preStartFlag=$("#startFlag").val();
			var subffix="";
			
			if(!!preChannelId){
				subffix +="&preChannelId="+preChannelId;
			}
			if(!!preChannelName){
				subffix +="&preChannelName="+encodeURI(encodeURI(preChannelName));
			}
			if(!!preStartFlag){
				subffix +="&preStartFlag="+preStartFlag;
			}
			window.location.href="/channel_monitor/preUpdate?channelId="+channelId+subffix;
		}
		function changePage(cp){
			if(cp){
				$("#cp").val(cp);
				$('#sc_btn_id').click();
			}
		}
		function changeGoPage(){
			var regNum = /^([1-9][0-9]*)$/;
			var cpCount=$("#cpCount").val();
			var totalPage='${totalPage}';
			if(undefined != cpCount && cpCount.trim() != '' && regNum.test(cpCount) == false){
				$("#cpCount").focus();
				layer.tips('页码只能为>0数字.', $("#cpCount"));
				return false;
			}
			if(cpCount){
				$("#cp").val(cpCount);
				$('#sc_btn_id').click();
			}
		}
		function checkPhone(value){
			if(value){
			    if(!(/^(1[34578]\d{0,9})$/.test(value))){
			    	  $("#merchantPhone").val("");
			    }
			}
		}
		
		
		$(function() {
			$('#btnsetting').click(function() { // 点击添加监控客户
				layer.open({
					  type: 2, //page层
					  area: ['680px', '450px'],
					  title: '告警通知设置',
					  shade: 0.3, //遮罩透明度
					  moveType: 0, //拖拽风格，0是默认，1是传统拖动
					  shift: 1, //0-6的动画形式，-1不开启
					  content:'/channel_monitor/to_setting'
				   });
			});

		});
		
		function monitorChange(channelId,startFlag,id){
			
			$.ajax({
				   async:false,
				   type: 'POST',//提交方式
	               dataType: 'json',//类型
				   url: '/channel_monitor/monitorFlagChange',
				   data: {'id': id,'channelId': channelId,'smsChannelId': channelId,'startFlag': startFlag},
				   success: function(data){
                    if(data ==1){
                    	if(1==startFlag){
                    		$(".showStop"+id).attr("style", "display:none;");  
                    		$(".showStart"+id).attr("style", "display:block;float:right;");
                    	}
                    	if(2==startFlag){
                    		$(".showStop"+id).attr("style", "display:block;float:right;");
                    		$(".showStart"+id).attr("style", "display:none;");  
                    	}
                    	//success
                     }else if(data== 0){
                    	//failt
					 }		 
				   },
				   error: function(msg){
					  //error
				   }
			 });	
		}
		
		function clearIt(){
			$("#channelId").val("");
			$("#channelName").val("");
			$("#startFlag").val("");
			$("#cpCount").val("1");
		}
		
		function codeClick(channelMainCode){
			var channelNameParam=$("#channelName").val();
			var channelIdParam=$("#channelId").val();
			var startFlagParam=$("#startFlag").val();
			
			var object=new Object();
			object.channelNameParam=channelNameParam;
			object.channelIdParam=channelIdParam;
			object.startFlagParam=startFlagParam;
			
			var param=JSON.stringify(object);
			
			window.location.href="/channel_monitor/main_channel_chartline?channelMainCode="+channelMainCode+"&param="+param;
		}
		
		function channelIdClick(channelId,channelName){
			var channelNameParam=$("#channelName").val();
			var channelIdParam=$("#channelId").val();
			var startFlagParam=$("#startFlag").val();
			
			var object=new Object();
			object.channelNameParam=channelNameParam;
			object.channelIdParam=channelIdParam;
			object.startFlagParam=startFlagParam;
			
			var param=JSON.stringify(object);
			
			window.location.href="/channel_monitor/to_channel_chartline?channelId="+channelId+"&channelName="+channelName+"&param="+param;
		}
		
    </script>
</head>

<body class="right_body">
<form action="/channel_monitor/list" method="post">
	<div class="search">
			<br>
			<br>
			<div style="float: left;">
             &nbsp;&nbsp; 通道名称：<input   id="channelName" name="channelName" value="${smsChannelMonitor.channelName}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			   通道ID：<input   id="channelId" name="channelId" value="${smsChannelMonitor.channelId}" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="hidden" name="cp" id="cp" value="${currentPage}">
			状态：<select id="startFlag" name="startFlag">
				<option value="">请选择</option>
				<option value="1" ${fn:contains(smsChannelMonitor.startFlag,1)?'selected':''}>开启监控</option>
				<option value="2" ${fn:contains(smsChannelMonitor.startFlag,2)?'selected':''}>关闭监控</option>
			</select>
			</div>
			<div style="float: right;width: 22%">
			<input id="sc_btn_id" class="btn btn-sm btn-primary " type="submit" value="查询 数据">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input id="sc_btn_id" class="btn btn-sm btn-primary " type="button" value=" 清空   " onclick="clearIt()" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input id="btnsetting" class="btn btn-sm btn-primary " type="button" value="告警通知设置">   
			</div>
			<br>
			<br>
	</div>
	<br>
	<div class="data_list">
		<div class="list_inner">
			<table width="100%" border="0" cellspacing="1" cellpadding="0" class="table table-striped table-bordered table-hover dataTables-example dataTable" aria-describedby="DataTables_Table_0_info">
				<tr>
					<th>序号</th>
					<th>通道编号</th>
					<th>通道名称</th>
					<th>通道ID</th>
					<th>成功率</th>
					<th>失败率</th>
					<th>未知率</th>
					<th>平均发送时长（秒）</th>
					<th>平均状态报告时长（秒）</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
				<c:forEach var="st" items="${list}" varStatus="i">
				<tr>
					<td>${i.index+1}</td>
					<td><a onclick="codeClick('${st.channelMainCode}')">${st.channelMainCode}</a></td>
					<td><a onclick="channelIdClick('${st.channelId}','${st.channelName}')">${st.channelName}</a></td>
					<td><a onclick="channelIdClick('${st.channelId}','${st.channelName}')">${st.channelId}</a></td>
					<td>${st.successRateDown1}</td>
					<td>${st.failtRateUp1}</td>
					<td>${st.unknownRateUp1}</td>
					<td>${st.averageSendUp1}</td>
					<td>${st.averageReveiveUp1}</td>
					<td align="center">
					<c:if test="${st.startFlag!=null}">
				       	<div class="showStart${st.id}" ${fn:contains(st.startFlag,1)?'style="display:block;"':'style="display:none;"'}>
				       	   监控开启&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
				       	<div class="showStop${st.id}" ${fn:contains(st.startFlag,2)?'style="display:block;"':'style="display:none;"'}>
				       	   监控关闭&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
					</c:if>
					</td>
					<td align="center">
						<input class="btn btn-sm btn-primary" name="editBtn" type="button" value="指标设置" title="指标设置" onclick="modify('${st.channelId}')" />
						<div  class="showStart${st.id}" ${fn:contains(st.startFlag,1)?'style="display:block;float:right;"':'style="display:none;"'}>
						     <input class="btn btn-sm btn-primary" name="editBtn" type="button" value="关闭监控" title="关闭监控" onclick="monitorChange('${st.channelId}',2,'${st.id}')" />
						</div>
						
						<div class="showStop${st.id}" ${fn:contains(st.startFlag,2)?'style="display:block;float:right;"':'style="display:none;"'}>
						       <input class="btn btn-sm btn-primary" name="editBtn" type="button" value="开启监控" title="开启监控" onclick="monitorChange('${st.channelId}',1,'${st.id}')" />
						</div>
						
						
					</td>
				</tr>
				</c:forEach>
			</table>
			<div class="page">
				<div class="clearfix">	
					<div style="display:block;text-align: center;width: 100%">
					      共有记录  <font color='red'>${totalCount }</font> 条记录&nbsp;&nbsp;
					       每页显示  <font color='red'>${pageSize}</font> 条记录&nbsp;&nbsp;
					       当前第  <font color='red'>${currentPage}</font> 页&nbsp;&nbsp; 
					       共<font color='red'> ${totalPage}</font> 页&nbsp; &nbsp; 
					  <a href='javascript:changePage(1)'>首页</a>
					  <c:if test="${currentPage >1}">
					      <a href='javascript:changePage(${currentPage-1})'>上一页</a>
					  </c:if> 
					  <c:if test="${currentPage <=1 }">
					    <font color='#808080'> 上一页</font>
		              </c:if>
		              &nbsp;|&nbsp;
		              <c:if test="${currentPage < totalPage }">
		                 <a href='javascript:changePage(${currentPage+1})'>下一页</a>
					  </c:if> 
					  <c:if test="${currentPage == totalPage}">
					     <font color='#808080'> 下一页</font>
					  </c:if> 
					   &nbsp;|
					  <a href='javascript:changePage(${totalPage})'>末页</a>
					   &nbsp;
					   <input id="cpCount" size="5" value="${currentPage}">
					   <a onclick="changeGoPage()">  &nbsp;&nbsp;GO&nbsp;&nbsp;  </a>
				   </div>
			    </div>
			</div>
		</div>
	</div>
</form>
</body>
</html>