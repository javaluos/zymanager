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
	<div class="col-sm-12" style="border-bottom: 1px solid #e5e6e7;margin-bottom: 10px;">
		<div class="ibox">
			<div class="ibox-title" >
				<h5>
					监控信息
					<small>&nbsp;-->&nbsp;
					客户监控业务
					</small>
				</h5>
			</div>
			<div>
    		<form method="get" class="form-horizontal m-t">
                <input type="hidden" id="apiAccount" value="${mAccount.apiAccount }" />	
    			<div class="form-group">
                     <label class="col-sm-2 control-label" style="width: 100px;margin-left: 10px;">客户名称：</label>
                     <div class="col-sm-2">
                         <label class="control-label" style="margin-left: -10px;">${mAccount.businessName }</label>
                     </div>
                     <label class="col-sm-2 control-label" style="width: 100px;">客户账号：</label>
                     <div class="col-sm-2">
                         <label class="control-label" style="margin-left: -10px;">${mAccount.merchantPhone }</label>
                     </div>
                 </div>
                 
                 <div class="form-group">
                     <label class="col-sm-2 control-label" style="width: 100px;">监控时间:</label>
                     <div class="col-sm-2">
                         <input class="form-control layer-date"
							    id="monitorDate"  style="width: 150px;" value="${currentDate }">
                     </div>
                     <label class="col-sm-2 control-label" style="width: 100px;">业务类型:</label>
                     <div class="col-sm-2">
                         <select class="form-control" id="businessId" style="width: 150px;" onchange="smsVoiceChane()">
							<c:forEach items="${btList}" var="bt">
		                       <option value="${bt.id }">${bt.name }</option>
		                    </c:forEach>
					     </select>
                     </div>
                     
                     <label class="col-sm-2 control-label" style="width: 100px;">监控类型:</label>
                     <div class="col-sm-2">
                          <select class="form-control" id="monitorType" style="width: 150px;">
							<c:forEach items="${mtList}" var="mt">
		                       <option value="${mt.id }">${mt.name }</option>
		                    </c:forEach>
						  </select>
                     </div>
                     
                     <div class="col-sm-1" style="width: 100px;">
                        <button id="btnchart"
								class="btn btn-sm btn-primary pull-left"
								type="button">查看图表</button>
                     </div>
                     <div class="col-sm-1" style="width: 100px;">
                     	<c:if test="${permission == true }">
	                        <button id="btnsetting"
							class="btn btn-sm btn-info pull-left"
							type="button" 
							onclick="javascript:location='/monitor/index_setting?businessName=${mAccount.businessName }&&merchantPhone=${mAccount.merchantPhone }&&apiAccount=${mAccount.apiAccount }'">报警指标设置</button>
						</c:if>
                     </div>
                     <div class="col-sm-1" style="width: 100px;">
                         <button class="btn btn-sm btn-white pull-left" type="button" style="width: 60px;margin-left: 20px;" onclick="history.go(-1)">返回</button>
                     </div>
                 </div>
				
				</form>
			</div>
		</div>
	</div>
	<!-- /QueryForm -->
	
	<!-- charview -->
	<div class="graphic">
	  <div id="line-chart"></div> 
	</div>


<script type="text/javascript" src="/js/echarts/echarts.js"></script>
<script type="text/javascript" src="/js/echarts/theme/macarons.js"></script>
<script type="text/javascript" src="/js/handler/chart_account.js"></script>	
<script type="text/javascript">
	$("#monitorDate").jeDate({
	    isinitVal:false,
	    ishmsVal:false,
	    minDate: '',
	    maxDate: $.nowDate(0),
	    format:"YYYY-MM-DD",
	    zIndex:3000,
	});
	
	function smsVoiceChane(){
		var tv=$("#businessId").val();
		if(!!tv){
			if(tv==8||tv==9||tv==11){
				$("#monitorType").empty();
				$("#monitorType").append("<option value='7'>并发数</option><option value='8'>成功率</option><option value='9'>失败率</option><option value='10'>未知率</option><option value='11'>平均发送时长</option><option value='12'>平均状态报告时长</option>");
				
			}else{
				$("#monitorType").empty();
				$("#monitorType").append("<option value='1'>并发数</option><option value='2'>接通率</option><option value='3'>应答率</option><option value='4'>平均接通时延</option><option value='5'>平均通话时长</option><option value='6'>平均接续时长</option>");
			}
		}
	}
	smsVoiceChane();
</script>
</body>
</html>


