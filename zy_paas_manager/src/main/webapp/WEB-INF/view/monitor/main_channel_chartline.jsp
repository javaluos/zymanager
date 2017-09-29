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
			<div class="ibox-title">
				<h5>
					监控信息
					<small>&nbsp;-->&nbsp;
					通道跑量监控
					</small>
				</h5>
			</div>
			<div>
				<form method="get" class="form-horizontal m-t">
                <input type="hidden" id="apiAccount" value="0" />	
                 <div class="form-group">
                     <label class="col-sm-2 control-label" style="width: 100px;">通道编号:</label>
                     <div class="col-sm-2">
                        <input class="form-control layer-date"
                                id="channelMainCode"  style="width: 150px;" readonly="readonly" value="${channelMainCode }">
                     </div>
                     <label class="col-sm-2 control-label" style="width: 100px;">监控时间:</label>
                     <div class="col-sm-2">
                         <input class="form-control layer-date"
							    id="monitorDate"  style="width: 150px;" value="${currentDate }">
                     </div>
                     <label class="col-sm-2 control-label" style="width: 100px;">监控类型:</label>
                     <div class="col-sm-2">
                         <select class="form-control" id="monitorType" style="width: 150px;">
							<c:forEach items="${monitorTypeList}" var="monitorType">
		                       <option value="${monitorType.type }">${monitorType.name }</option>
		                    </c:forEach>
					     </select>
                     </div>
                     <div class="col-sm-1">
                        <button id="btnchart"
								class="btn btn-sm btn-primary pull-left"
								type="button">查询</button>
						<button id="btnsetting"
                                class="btn btn-sm btn-primary pull-right"
                                type="button"
                                onclick="javascript:location='/channel_monitor/list.html?paramMap=${paramMap}'">返回</button>
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
<script type="text/javascript" src="/js/handler/chart_channel.js"></script>
<script type="text/javascript">
	$("#monitorDate").jeDate({
	    isinitVal:false,
	    ishmsVal:false,
	    minDate: '',
	    maxDate: $.nowDate(0),
	    format:"YYYY-MM-DD",
	    zIndex:3000,
	})
</script>	
</body>
</html>