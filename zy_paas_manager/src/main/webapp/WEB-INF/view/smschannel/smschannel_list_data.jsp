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
					<th style="background: 0 0;min-width:30px;">序号</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">通道编号</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">通道ID</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" style="min-width:50px;">通道名称</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">通道号</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" style="min-width:50px;">是否直连</th>	
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" style="min-width:80px;">通道类型</th>	
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" style="min-width:50px;">运营商</th>	
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" style="min-width:50px;">通道属性</th>	
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" style="min-width:50px;">落地省份</th>	
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" style="min-width:50px;">跑量省份</th>	
					<!-- 
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" style="min-width:35px;">上行</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" style="min-width:50px;">长短信</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" style="min-width:50px;">签名报备</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" style="min-width:50px;">模板报备</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1"  style="min-width:55px;">下发速度</th>
					 -->
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" style="min-width:50px;">通道状态</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" style="min-width:50px;">付款方式</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" style="min-width:50px;">通道资费</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" style="min-width:50px;">余额</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" style="min-width:35px;">评分</th>
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
							<td>${dt.channelMainCode }</td>
							<td>${dt.channelId }</td>
							<td>${dt.channelName }</td>
							<td>${dt.channelCode }</td>
							<td>
							   <c:choose>
									<c:when test="${dt.channelAccessMode==0}">
										否
									</c:when>
									<c:when test="${dt.channelAccessMode==1}">   
									              是
									</c:when>
									<c:otherwise>   
								     &nbsp;         
								  </c:otherwise>
								</c:choose>
							</td>
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
							   		<c:when test="${dt.operateType==0}">
										三网合一
									</c:when>
									<c:when test="${dt.operateType==1}">
										移动
									</c:when>
									<c:when test="${dt.operateType==2}">   
									              电信
									</c:when>
									<c:when test="${dt.operateType==3}">   
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
							<!--  
							<td>
							  <c:choose>

								  <c:when test="${dt.moFlag==0}">   
								          不支持
								  </c:when>
								  <c:when test="${dt.moFlag==1}">   
								           支持
								  </c:when>
								  <c:otherwise>   
								    &nbsp;         
								  </c:otherwise>
							  </c:choose>
							</td>
							<td>
							  <c:choose>
								  <c:when test="${dt.longSmsFlag==0}">   
								             不支持 
								  </c:when>
								  <c:when test="${dt.longSmsFlag==1}">   
								             支持
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
							<td width="55px;">${dt.crestValue } </td>
							-->
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
							<td>
							   <c:if test="${dt.chargeType == 0}">
									预付费
								</c:if>
								<c:if test="${dt.chargeType == 1}">
									后付费
								</c:if>
							 </td>
							<td>
							   <c:if test="${not empty dt.channelFee}">
									${dt.channelFee}元
								</c:if>
							</td>
							<td>
							    <c:if test="${dt.balanceUnit == 0}">
									${dt.channelBalance/1000}元
								</c:if>
								<c:if test="${dt.balanceUnit == 1}">
									${dt.channelBalance}条
								</c:if>
							</td>
							<td>${dt.channelScore }</td>
							<td width="55px;">
								 <c:choose>
									<c:when test="${fn:length(dt.channelComment) > 15}"><p title="${fn:escapeXml(dt.channelComment)}">${fn:substring(dt.channelComment, 0, 15)} ...</p></c:when>
									<c:otherwise>
										${fn:escapeXml(dt.channelComment)}
									</c:otherwise>
								</c:choose>
							</td>
							<td>
							    <a href="/smschannel/smschannel_show?channelId=${dt.channelId }" >编辑</a>
							    <c:if test="${dt.status !=1 && dt.status !=2 }">
							    &nbsp;|&nbsp;
							    <a href="javascript:;" onclick="delChannel('${dt.channelId}','${dt.channelName}');" >删除</a>
							    </c:if>
							    
							    <c:if test="${dt.balanceMonitorFlag ==0 }">
							    &nbsp;|&nbsp;
							    <a href="javascript:;" onclick="addMonitor('${dt.channelId}','${dt.channelName}','${dt.channelBalance}','${dt.balanceUnit}');" >加入监控</a>
							    </c:if>
							     <c:if test="${dt.balanceMonitorFlag ==1 }">
							    &nbsp;|&nbsp;
							    <a href="javascript:;" onclick="delMonitor('${dt.channelId}','${dt.channelName}');" >取消监控</a>
							    </c:if>
							    
							    <c:if test="${dt.status ==1 || dt.status ==2 }">
							    &nbsp;|&nbsp;
							    <a href="javascript:;" onclick="testMonitor('${dt.channelId}');" >测试</a>
							    </c:if>
							     &nbsp;|&nbsp;
							      <a href="/smschannel/channeldispatch_show?channelId=${dt.channelId }" >对接配置</a>
							    
							</td>		
						</tr>
					</c:forEach>

				</c:if>
				<c:if test="${empty pgdata.data }">
					<tr>
						<td colspan="19" style="text-align: center;">暂无数据.</td>
					</tr>
				</c:if>

			</tbody>

		</table>
		<p class="msg">
			<jsp:include page="../comm/pgbar.jsp" />
		</p>
	</div>

</div>

<style type="text/css"> 
 textarea{ resize:none;}
</style> 

<script type="text/javascript">
function delChannel(channelId, channeName){
	layer.confirm("您确定要删除通道【" +channeName+" 】吗？", {
		area: ['450px'],
		btn: ['确认','取消'] //按钮
	}, function(){
		$.ajax({
            type: "POST",
            url: "/smschannel/smschannel_del",
            data: {"channelId" : channelId},
            dataType: "json",
            success: function(data){
                layer.closeAll('dialog');
                $("#btnquery").click();
            }
        });
	});
}

function addMonitor(channelId, channeName,channelBalance,balanceUnit){
	var balanceUnits='';
	if(balanceUnit==0){
		balanceUnits="元";
	}else{
		balanceUnits="条";
	}
	var mht = '<li style="text-align:center"><h3>通道录入管理-加入监控</h3></li>';
	mht += '<li style="margin-top:10px"><font style="margin-left:20px;">通道名称：</font><font style="margin-left:20px">'+channeName+'</font></li>';
	mht += '<li style="margin-top:10px"><font style="margin-left:20px;">通道ID：&nbsp;&nbsp;&nbsp;</font><font style="margin-left:20px">'+channelId+'</font></li>';
	mht += '<li style="margin-top:10px"><font style="margin-left:20px;">通道余额：</font><font style="margin-left:20px">'
	           +channelBalance+balanceUnits+
	       '</font></li>';
	mht += '<li style="margin-top:10px"><font style="margin-left:20px;">告警余额：</font>'+
	          '<input style="margin-left:20px;width:140px;" type="text" name="channelBalance" id="channelBalance" maxlength="9" value="'+channelBalance+'"/>'+
	          '<input type="hidden" name="balanceUnit" id="balanceUnit" maxlength="9" value="'+balanceUnit+'"/>'+
	           '&nbsp;&nbsp;'+balanceUnits+
	       '</li>';
	mht += '<li style="margin-top:10px;text-align:center">'+
			 '<button class="btn btn-primary" type="button" onclick="cancel();" style="width:60px;margin-left:20px;margin-right:80px;" id="btnexce2">取消</button>'+
			 '<button class="btn btn-primary" type="button" onclick=submit("'+channelId+'","'+channeName+'"); style="width: 60px;" id="btnexce1">确认</button>'+
		   '</li>';
	mht += '<div id="errormsg" style="color: red;margin-top:10px;"></div>';
	
	layer.open({
		  type: 1, //page层
		  area: ['450px', '320px'],
		  title: '通道录入管理-加入监控',
		  shade: 0.3, //遮罩透明度
		  moveType: 1, //拖拽风格，0是默认，1是传统拖动
		  shift: 1, //0-6的动画形式，-1不开启
		  content: '<div style="padding:50px; padding-top:20px;">'+ mht+ '</div>'
		}); 
}

function testMonitor(channelId){
	var mht = '<li style="text-align:center"><h3>短信通道测试</h3></li>';
	mht += '<li style="margin-top:10px"><font style="margin-left:90px;">手机号码：</font>'+
	          '<input style="margin-left:10px;width:240px;height:30px;" type="text" name="mobile" id="mobile">'+
	       '</li>';
    mht += '<li style="margin-top:10px"><font style="margin-left:90px;">扩展号：</font>'+
             '<input style="margin-left:22px;width:240px;height:30px" type="text" name="extNumber" id="extNumber"/>'+
           '</li>';
    mht += '<li style="margin-top:10px"><font style="margin-left:90px;">短信类型：</font>'+
		    '    <select name="smsType" id="smsType" style="margin-left:8px;width: 140px;height:30px;">'+
			'	    <option value="-1">--请选择短信类型--</option>'+
			'		<option value="8">通知</option>'+
			'		<option value="9" selected="selected">验证码</option>'+
			'		<option value="11">营销</option>'+
			'	 </select>'+
          '</li>';
    mht += '<div style="margin-top:0px"><font style="margin-left:90px;">短信内容：</font>'+
	          '<textarea style="margin-left:10px;margin-top:20px;" name="smsContent" id="smsContent" rows="5" cols="40">【智语科技】你好，你的验证是：1136，请及时验证。</textarea>'+
	       '</div>';
	mht += '<li style="margin-top:10px;text-align:center">'+
			 '<button class="btn btn-primary" type="button" onclick="cancel();" style="width:60px;margin-left:20px;margin-right:80px;" id="btnexce2">取消</button>'+
			 '<button class="btn btn-primary" type="button" onclick=submit2("'+channelId+'"); style="width: 60px;" id="btnexce1">发送</button>'+
		   '</li>';
	mht += '<div id="errormsg" style="color: red;margin-top:10px;"></div>';
	
	layer.open({
		  type: 1, //page层
		  area: ['650px', '430px'],
		  title: '短信通道测试',
		  shade: 0.3, //遮罩透明度
		  moveType: 1, //拖拽风格，0是默认，1是传统拖动
		  shift: 1, //0-6的动画形式，-1不开启
		  scrollbar: false,
		  content: '<div style="padding:50px; padding-top:20px;">'+ mht+ '</div>'
		}); 
}


function cancel(){
	layer.closeAll();
}

function submit2(channelId){
	var smsContent=$("#smsContent").val();
	var mobile=$("#mobile").val();
	var extNumber=$("#extNumber").val();
	var smsType=$("#smsType").val();
	if(mobile==''){
		layer.alert("请输入手机号码!");
		return;
	}if(smsType==-1){
		layer.alert("请选择短信类型!");
		return;
	}if(smsContent==''){
		layer.alert("请输入短信内容!");
		return;
	}
	
	$.ajax({
		type: "POST",
   		url: "/smschannel/test_channel",
		data: {"channelId":channelId,"smsContent" : smsContent, "mobile" : mobile,"extNumber":extNumber,"smsType":smsType},
		success: function(data){
			var obj = eval('(' + data + ')');
			if(obj.status==0){
				layer.confirm("短信提交成功,请查看短信发送记录了解发送情况！", {
					area: ['450px'],
					btn: ['确认'] //按钮
				},function(){
					layer.closeAll('dialog');
					layer.closeAll();
					$("#btnquery").click();
				});
			}else{
				layer.confirm("短信提交失败,失败原因为:"+obj.desc, {
					area: ['450px'],
					btn: ['确认'] //按钮
				},function(){
					layer.closeAll('dialog');
					//layer.closeAll();
					//$("#btnquery").click(); 
				});
			}
	   	},
	   	beforeSend:function(){
	   		beforeTips("短信通道测试");
	   	}
	});
}

/**
 * 导出提示框
 * 
 * @param title 消息标题
 */
function beforeTips(title){
	var mht  = '<div class="expopul"><li><img id="eimg" src="/images/common/loading0.gif"></li>';
	    mht += '<li><span id="etips"></span>&nbsp;&nbsp;<span id="einfo">正在发送短信中，请稍候...</span></li>';
	    mht += '</div>';
	layer.open({
	  type: 1, //page层
	  area: ['430px', '200px'],
	  title: title,
	  shade: 0.3, //遮罩透明度
	  moveType: 1, //拖拽风格，0是默认，1是传统拖动
	  shift: 1, //0-6的动画形式，-1不开启
	  content: '<div style="padding:50px; padding-top:20px;">'+ mht+ '</div>'
	}); 
}



function submit(channelId,channeName){
	var channelBalance=$("#channelBalance").val();
	var balanceUnit=$("#balanceUnit").val();
	$.ajax({
		type: "POST",
   		url: "/smschannel/save_channel_balance",
		data: {"channelId" : channelId, "channelBalance" : channelBalance,"balanceUnit":balanceUnit},
		success: function(data){
			if(data == 'true'){
				layer.confirm("通道:"+channeName+"加入余额监控成功！", {
					area: ['450px'],
					btn: ['确认'] //按钮
				},function(){
					layer.closeAll('dialog');
					layer.closeAll();
					$("#btnquery").click();
				});
			}else if(data == 'false'){
				$("#errormsg").text("通道:"+channeName+"加入余额监控失败");
			}else {
                $("#errormsg").text("页面已过期");
                window.location.href="/login.html";
            }
	   	}
	});
}

function delMonitor(channelId,channeName){
	$.ajax({
		type: "POST",
   		url: "/smschannel/remove_channel_balance",
		data: {"channelId" : channelId},
		success: function(data){
			if(data == 'true'){
				layer.confirm("通道:"+channeName+"取消余额监控成功！", {
					area: ['450px'],
					btn: ['确认'] //按钮
				},function(){
					layer.closeAll('dialog');
					layer.closeAll();
					$("#btnquery").click();
				});
			}else{
				layer.confirm("通道:"+channeName+"取消余额监控失败！", {
					area: ['450px'],
					btn: ['确认'] //按钮
				},function(){
					layer.closeAll('dialog');
					layer.closeAll();
					$("#btnquery").click();
				});
			}	 
	   	}
	});
}
</script>
