<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@  taglib  uri="http://java.sun.com/jsp/jstl/functions"   prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>列表查询</title>
	<jsp:include page="../comm/plugin.jsp" />
    <script type="text/javascript">
    </script>
</head>

<body class="right_body">
<form action="/monitor/list" method="POST" name="myform">
	<div class="search">
			<br>
			<input style="margin-left:50px;height:30px;" id="sc_btn_id" type="button" value="报警通知设置" onclick="noticesetting();" />  
			<span style="float: right;margin-right: 20px;">                  
			   <button class="btn btn-primary" type="button" style="width: 60px;margin-left: 20px;" onclick="history.go(-1)">返回</button>
            </span>
	</div>
	<br>
	<div class="data_list">
		<div style="margin-left: 50px;">短信通知
			<span style="float: right; margin-right: 80px">说明：<font color="red">*</font>&nbsp;表示必填 </span>
		</div>
		<div class="list_inner">
			<table width="100%" border="0" cellspacing="1" cellpadding="0" class="table table-striped table-bordered table-hover dataTables-example dataTable" aria-describedby="DataTables_Table_0_info">
				<tr>
					<th>序号</th>
					<th><font color="red">*</font>报警时间</th>
					<th><font color="red"></font>开始报警条数</th>
					<th><font color="red"></font>发送量（上限）</th>
					<th><font color="red"></font>发送量（下限）</th>
					<th><font color="red"></font>成功率（下限）</th>
					<th><font color="red"></font>失败率（上限）</th>
					<th><font color="red"></font>未知率（上限）</th>
					
					<th><font color="red"></font>平均发送时长（秒/上限）</th>
					<th><font color="red"></font>平均状态报告时长（秒/上限）</th>
					<th>操作</th>
				</tr>
				<c:forEach var="smsNotice" items="${smsNoticeSettings }" varStatus="index" >
					<tr id="smsNotice${index.index + 1 }">
						<td>${index.index + 1}</td>
						<td><input type="text" name="measureTime" id="smsNoticemeasureTime${index.index + 1 }" value="${smsNotice.measureTime}" disabled="disabled" maxlength="15"  /></td>
						<td><input type="text" name="callCountaUp" id="smsNoticecallCountaUp${index.index + 1 }" value="${smsNotice.callCountaUp == -1 ? '' : smsNotice.callCountaUp}"  disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" name="callCountbUp" id="smsNoticecallCountbUp${index.index + 1 }" value="${smsNotice.callCountbUp== -1 ? '' : smsNotice.callCountbUp}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" name="callCountbDown" id="smsNoticecallCountbDown${index.index + 1 }" value="${smsNotice.callCountbDown== -1 ? '' :smsNotice.callCountbDown}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						
						<td><input type="text" name="successRatebDown" id="smsNoticesuccessRatebDown${index.index + 1 }" value="${smsNotice.successRatebDown== -1 ? '' : smsNotice.successRatebDown/ 100}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" />%</td>
						<td><input type="text" name="responseRatebDown" id="smsNoticeresponseRatebDown${index.index + 1 }" value="${smsNotice.responseRatebDown== -1 ? '' : smsNotice.responseRatebDown/ 100}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" />%</td>
						<td><input type="text" name="successRateaDown" id="smsNoticesuccessRateaDown${index.index + 1 }" value="${smsNotice.successRateaDown== -1 ? '' : smsNotice.successRateaDown/ 100}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" />%</td>
						
						<td><input type="number" name="averageTalkTimebDown" id="smsNoticeaverageTalkTimebDown${index.index + 1 }" value="${smsNotice.averageTalkTimebDown== -1 ? '' :smsNotice.averageTalkTimebDown}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" name="averageTurnOnDelaybUp" id="smsNoticeaverageTurnOnDelaybUp${index.index + 1 }" value="${smsNotice.averageTurnOnDelaybUp== -1 ? '' :smsNotice.averageTurnOnDelaybUp}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td align="center">
							<input class="btn btn-sm btn-primary" id="smsNotice${index.index + 1 }edit" type="button" value="修改" onclick="edit('smsNotice',${index.index + 1 });"  />
							<input class="btn btn-sm btn-primary" id="smsNotice${index.index + 1 }save" type="button" style="display: none" value="保存" onclick="doedit(${smsNotice.id},'smsNotice',${index.index + 1 });"  />
							<input class="btn btn-sm btn-primary" id="smsNotice${index.index + 1 }delete" type="button" value="删除" onclick="dodel(${smsNotice.id},'smsNotice',${index.index + 1 });"  />
							<input class="btn btn-sm btn-primary" id="smsNotice${index.index + 1 }add" name="addBtn" type="button" style="display: none" value="增加" onclick="save('smsNotice',8,${index.index + 1 });"  />
						</td>
					</tr>
				</c:forEach>
				<c:forEach var="index" begin="${smsNoticeSettings.size() + 1}" end="3" varStatus="i">
					<tr id="smsNotice${index }">
						<td>${index}</td>
						<td><input type="text" name="measureTime" id="smsNoticemeasureTime${index }" value=""  maxlength="15" /></td>
						<td><input type="text" name="callCountaUp" id="smsNoticecallCountaUp${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" name="callCountbUp" id="smsNoticecallCountbUp${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="text" name="callCountbDown" id="smsNoticecallCountbDown${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						
						<td><input type="text" name="successRatebDown" id="smsNoticesuccessRatebDown${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" />%</td>
						<td><input type="text" name="responseRatebDown" id="smsNoticeresponseRatebDown${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" />%</td>
						<td><input type="text" name="successRateaDown" id="smsNoticesuccessRateaDown${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" />%</td>
						
						<td><input type="number" name="averageTalkTimebDown" id="smsNoticeaverageTalkTimebDown${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" name="averageTurnOnDelaybUp" id="smsNoticeaverageTurnOnDelaybUp${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td align="center">
							<input type="hidden" id="smsNotice${index }id" />
							<input class="btn btn-sm btn-primary" id="smsNotice${index}add" name="addBtn" type="button" value="增加" onclick="save('smsNotice',8,${index});"  />
							<input class="btn btn-sm btn-primary" id="smsNotice${index}edit" type="button" style="display: none" value="修改" onclick="edit('smsNotice',${index});"  />
							<input class="btn btn-sm btn-primary" id="smsNotice${index}save" type="button" style="display: none" value="保存" onclick="doedit('','smsNotice',${index});"  />
							<input class="btn btn-sm btn-primary" id="smsNotice${index}delete" type="button" style="display: none" value="删除" onclick="dodel('','smsNotice',${index});"  />
						</td>
					</tr>
				</c:forEach>
			</table>
			
			<div style="margin-left: 50px;margin-top:8px;">短信验证码</div>
			<table width="100%" border="0" cellspacing="1" cellpadding="0" class="table table-striped table-bordered table-hover dataTables-example dataTable" aria-describedby="DataTables_Table_0_info">
				<tr>
					<th>序号</th>
					<th><font color="red">*</font>报警时间</th>
					<th><font color="red"></font>开始报警条数</th>
					<th><font color="red"></font>发送量（上限）</th>
					<th><font color="red"></font>发送量（下限）</th>
					<th><font color="red"></font>成功率（下限）</th>
					<th><font color="red"></font>失败率（上限）</th>
					<th><font color="red"></font>未知率（上限）</th>
					
					<th><font color="red"></font>平均发送时长（秒/上限）</th>
					<th><font color="red"></font>平均状态报告时长（秒/上限）</th>
					<th>操作</th>
				</tr>
				<c:forEach var="smsCode" items="${smsCodeSettings }" varStatus="index" >
					<tr id="smsCode${index.index + 1 }">
						<td>${index.index + 1}</td>
						<td><input type="text" name="measureTime" id="smsCodemeasureTime${index.index + 1 }" value="${smsCode.measureTime}" disabled="disabled" maxlength="15"  /></td>
						<td><input type="text" name="callCountaUp" id="smsCodecallCountaUp${index.index + 1 }" value="${smsCode.callCountaUp == -1 ? '' :smsCode.callCountaUp}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" name="callCountbUp" id="smsCodecallCountbUp${index.index + 1 }" value="${smsCode.callCountbUp == -1 ? '' :smsCode.callCountbUp}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" name="callCountbDown" id="smsCodecallCountbDown${index.index + 1 }" value="${smsCode.callCountbDown == -1 ? '' :smsCode.callCountbDown}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						
						<td><input type="text" name="successRatebDown" id="smsCodesuccessRatebDown${index.index + 1 }" value="${smsCode.successRatebDown == -1 ? '' :smsCode.successRatebDown/ 100}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" />%</td>
						<td><input type="text" name="responseRatebDown" id="smsCoderesponseRatebDown${index.index + 1 }" value="${smsCode.responseRatebDown == -1 ? '' : smsCode.responseRatebDown/ 100}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" />%</td>
						<td><input type="text" name="successRateaDown" id="smsCodesuccessRateaDown${index.index + 1 }" value="${smsCode.successRateaDown == -1 ? '' :smsCode.successRateaDown/ 100}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" />%</td>
						
						<td><input type="number" name="averageTalkTimebDown" id="smsCodeaverageTalkTimebDown${index.index + 1 }" value="${smsCode.averageTalkTimebDown == -1 ? '' :smsCode.averageTalkTimebDown}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" name="averageTurnOnDelaybUp" id="smsCodeaverageTurnOnDelaybUp${index.index + 1 }" value="${smsCode.averageTurnOnDelaybUp == -1 ? '' :smsCode.averageTurnOnDelaybUp}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td align="center">
							<input class="btn btn-sm btn-primary" id="smsCode${index.index + 1 }edit" type="button" value="修改" onclick="edit('smsCode',${index.index + 1 });"  />
							<input class="btn btn-sm btn-primary" id="smsCode${index.index + 1 }save" type="button" style="display: none" value="保存" onclick="doedit(${smsCode.id},'smsCode',${index.index + 1 });"  />
							<input class="btn btn-sm btn-primary" id="smsCode${index.index + 1 }delete" type="button" value="删除" onclick="dodel(${smsCode.id},'smsCode',${index.index + 1 });"  />
							<input class="btn btn-sm btn-primary" id="smsCode${index.index + 1 }add" name="addBtn" type="button" style="display: none" value="增加" onclick="save('smsCode',9,${index.index + 1 });"  />
						</td>
					</tr>
				</c:forEach>
				<c:forEach var="index" begin="${smsCodeSettings.size() + 1}" end="3" varStatus="i">
					<tr id="smsCode${index }">
						<td>${index}</td>
						<td><input type="text" name="measureTime" id="smsCodemeasureTime${index }" value=""  maxlength="15" /></td>
						<td><input type="text" name="callCountaUp" id="smsCodecallCountaUp${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" name="callCountbUp" id="smsCodecallCountbUp${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="text" name="callCountbDown" id="smsCodecallCountbDown${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						
						<td><input type="text" name="successRatebDown" id="smsCodesuccessRatebDown${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" />%</td>
						<td><input type="text" name="responseRatebDown" id="smsCoderesponseRatebDown${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" />%</td>
						<td><input type="text" name="successRateaDown" id="smsCodesuccessRateaDown${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" />%</td>
						
						<td><input type="number" name="averageTalkTimebDown" id="smsCodeaverageTalkTimebDown${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" name="averageTurnOnDelaybUp" id="smsCodeaverageTurnOnDelaybUp${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td align="center">
							<input type="hidden" id="smsCode${index }id" />
							<input class="btn btn-sm btn-primary" id="smsCode${index}add" name="addBtn" type="button" value="增加" onclick="save('smsCode',9,${index});"  />
							<input class="btn btn-sm btn-primary" id="smsCode${index}edit" type="button" style="display: none" value="修改" onclick="edit('smsCode',${index});"  />
							<input class="btn btn-sm btn-primary" id="smsCode${index}save" type="button" style="display: none" value="保存" onclick="doedit('','smsCode',${index});"  />
							<input class="btn btn-sm btn-primary" id="smsCode${index}delete" type="button" style="display: none" value="删除" onclick="dodel('','smsCode',${index});"  />
						</td>
					</tr>
				</c:forEach>
			</table>
			<div style="margin-left: 50px;margin-top:8px;">营销短信</div>
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="table table-striped table-bordered table-hover dataTables-example dataTable" aria-describedby="DataTables_Table_0_info">
				<tr>
					<th>序号</th>
					<th><font color="red">*</font>报警时间</th>
					<th><font color="red"></font>开始报警条数</th>
					<th><font color="red"></font>发送量（上限）</th>
					<th><font color="red"></font>发送量（下限）</th>
					<th><font color="red"></font>成功率（下限）</th>
					<th><font color="red"></font>失败率（上限）</th>
					<th><font color="red"></font>未知率（上限）</th>
					
					<th><font color="red"></font>平均发送时长（秒/上限）</th>
					<th><font color="red"></font>平均状态报告时长（秒/上限）</th>
					<th>操作</th>
				</tr>
				<c:forEach var="smsSell" items="${smsSellSettings }" varStatus="index" >
					<tr id="smsSell${index.index + 1 }">
						<td>${index.index + 1}</td>
						<td><input type="text" name="measureTime" id="smsSellmeasureTime${index.index + 1 }" value="${smsSell.measureTime}" disabled="disabled" maxlength="15"  /></td>
						<td><input type="text" name="callCountaUp" id="smsSellcallCountaUp${index.index + 1 }" value="${smsSell.callCountaUp == -1 ? '' :smsSell.callCountaUp}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" name="callCountbUp" id="smsSellcallCountbUp${index.index + 1 }" value="${smsSell.callCountbUp == -1 ? '' :smsSell.callCountbUp}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" name="callCountbDown" id="smsSellcallCountbDown${index.index + 1 }" value="${smsSell.callCountbDown == -1 ? '' :smsSell.callCountbDown}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						
						<td><input type="text" name="successRatebDown" id="smsSellsuccessRatebDown${index.index + 1 }" value="${smsSell.successRatebDown  == -1 ? '' :smsSell.successRatebDown/ 100}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" />%</td>
						<td><input type="text" name="responseRatebDown" id="smsSellresponseRatebDown${index.index + 1 }" value="${smsSell.responseRatebDown  == -1 ? '' :smsSell.responseRatebDown/ 100}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" />%</td>
						<td><input type="text" name="successRateaDown" id="smsSellsuccessRateaDown${index.index + 1 }" value="${smsSell.successRateaDown  == -1 ? '' :smsSell.successRateaDown/ 100}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" />%</td>
						
						<td><input type="number" name="averageTalkTimebDown" id="smsSellaverageTalkTimebDown${index.index + 1 }" value="${smsSell.averageTalkTimebDown == -1 ? '' :smsSell.averageTalkTimebDown}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" name="averageTurnOnDelaybUp" id="smsSellaverageTurnOnDelaybUp${index.index + 1 }" value="${smsSell.averageTurnOnDelaybUp == -1 ? '' :smsSell.averageTurnOnDelaybUp}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td align="center">
							<input class="btn btn-sm btn-primary" id="smsSell${index.index + 1 }edit" type="button" value="修改" onclick="edit('smsSell',${index.index + 1 });"  />
							<input class="btn btn-sm btn-primary" id="smsSell${index.index + 1 }save" type="button" style="display: none" value="保存" onclick="doedit(${smsSell.id},'smsSell',${index.index + 1 });"  />
							<input class="btn btn-sm btn-primary" id="smsSell${index.index + 1 }delete" type="button" value="删除" onclick="dodel(${smsSell.id},'smsSell',${index.index + 1 });"  />
							<input class="btn btn-sm btn-primary" id="smsSell${index.index + 1 }add" name="addBtn" type="button" style="display: none" value="增加" onclick="save('smsSell',11,${index.index + 1 });"  />
						</td>
					</tr>
				</c:forEach>
				<c:forEach var="index" begin="${smsSellSettings.size() + 1}" end="3" varStatus="i">
					<tr id="smsSell${index }">
						<td>${index}</td>
						<td><input type="text" name="measureTime" id="smsSellmeasureTime${index }" value=""  maxlength="15" /></td>
						<td><input type="text" name="callCountaUp" id="smsSellcallCountaUp${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" name="callCountbUp" id="smsSellcallCountbUp${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="text" name="callCountbDown" id="smsSellcallCountbDown${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						
						<td><input type="text" name="successRatebDown" id="smsSellsuccessRatebDown${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" />%</td>
						<td><input type="text" name="responseRatebDown" id="smsSellresponseRatebDown${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" />%</td>
						<td><input type="text" name="successRateaDown" id="smsSellsuccessRateaDown${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" />%</td>
						
						<td><input type="number" name="averageTalkTimebDown" id="smsSellaverageTalkTimebDown${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" name="averageTurnOnDelaybUp" id="smsSellaverageTurnOnDelaybUp${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td align="center">
							<input type="hidden" id="smsSell${index }id" />
							<input class="btn btn-sm btn-primary" id="smsSell${index}add" name="addBtn" type="button" value="增加" onclick="save('smsSell',11,${index});"  />
							<input class="btn btn-sm btn-primary" id="smsSell${index}edit" type="button" style="display: none" value="修改" onclick="edit('smsSell',${index});"  />
							<input class="btn btn-sm btn-primary" id="smsSell${index}save" type="button" style="display: none" value="保存" onclick="doedit('','smsSell',${index});"  />
							<input class="btn btn-sm btn-primary" id="smsSell${index}delete" type="button" style="display: none" value="删除" onclick="dodel('','smsSell',${index});"  />
						</td>
					</tr>
				</c:forEach>
			</table>
	
	
	
	
		<div style="margin-left: 50px;">语音通知
			<span style="float: right; margin-right: 80px">说明：&nbsp;<font color="red">*</font>表示必填 </span>
		</div>
		<div class="list_inner">
			<table width="100%" border="0" cellspacing="1" cellpadding="0" id="notice" class="table table-striped table-bordered table-hover dataTables-example dataTable" aria-describedby="DataTables_Table_0_info">
				<tr>
					<th>序号</th>
					<th><font color="red">*</font>报警时间</th>
					<th>并发数（上限）</th>
					<th>并发数（下限）</th>
					<th>接通率（下限）</th>
					<th>应答率（下限）</th>
					<th>平均通话时长（秒/下限）</th>
					<th>平均接通时延（秒/上限）</th>
					<th>平均接续时长（秒/上限）</th>
					<th>操作</th>
				</tr>
				<c:forEach var="notice" items="${noticeSettings }" varStatus="index" >
					<tr id="notice${index.index + 1 }">
						<td>${index.index + 1}</td>
						<td><input type="text" name="measureTime" id="noticemeasureTime${index.index + 1 }" value="${notice.measureTime}" disabled="disabled" maxlength="15"  /></td>
						<td><input type="number" name="callCountbUp" id="noticecallCountbUp${index.index + 1 }" value="${notice.callCountbUp == -1 ? '' : notice.callCountbUp}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" name="callCountbDown" id="noticecallCountbDown${index.index + 1 }" value="${notice.callCountbDown == -1 ? '' : notice.callCountbDown}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="text" name="successRatebDown" id="noticesuccessRatebDown${index.index + 1 }" value="${notice.successRatebDown == -1 ? '' : notice.successRatebDown / 100}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" />%</td>
						<td><input type="text" name="responseRatebDown" id="noticeresponseRatebDown${index.index + 1 }" value="${notice.responseRatebDown == -1 ? '' : notice.responseRatebDown / 100}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" />%</td>
						<td><input type="number" name="averageTalkTimebDown" id="noticeaverageTalkTimebDown${index.index + 1 }" value="${notice.averageTalkTimebDown == -1 ? '' : notice.averageTalkTimebDown}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" name="averageTurnOnDelaybUp" id="noticeaverageTurnOnDelaybUp${index.index + 1 }" value="${notice.averageTurnOnDelaybUp == -1 ? '' : notice.averageTurnOnDelaybUp}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" name="averageInTimebUp" id="noticeaverageInTimebUp${index.index + 1 }" value="${notice.averageInTimebUp == -1 ? '' : notice.averageInTimebUp}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td align="center">
							<input class="btn btn-sm btn-primary" id="notice${index.index + 1 }edit" type="button" value="修改" onclick="edit('notice',${index.index + 1 });"  />
							<input class="btn btn-sm btn-primary" id="notice${index.index + 1 }save" type="button" style="display: none" value="保存" onclick="doedit(${notice.id},'notice',${index.index + 1 });"  />
							<input class="btn btn-sm btn-primary" id="notice${index.index + 1 }delete" type="button" value="删除" onclick="dodel(${notice.id},'notice',${index.index + 1 });"  />
							<input class="btn btn-sm btn-primary" id="notice${index.index + 1 }add" name="addBtn" type="button" style="display: none" value="增加" onclick="save('notice',4,${index.index + 1 });"  />
						</td>
					</tr>
				</c:forEach>
				<c:forEach var="index" begin="${noticeSettings.size() + 1}" end="3" varStatus="i">
					<tr id="notice${index }">
						<td>${index}</td>
						<td><input type="text" name="measureTime" id="noticemeasureTime${index }" value=""  maxlength="15" /></td>
						<td><input type="number" name="callCountbUp" id="noticecallCountbUp${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" name="callCountbDown" id="noticecallCountbDown${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="text" name="successRatebDown" id="noticesuccessRatebDown${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" />%</td>
						<td><input type="text" name="responseRatebDown" id="noticeresponseRatebDown${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" />%</td>
						<td><input type="number" name="averageTalkTimebDown" id="noticeaverageTalkTimebDown${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" name="averageTurnOnDelaybUp" id="noticeaverageTurnOnDelaybUp${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" name="averageInTimebUp" id="noticeaverageInTimebUp${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td align="center">
							<input type="hidden" id="notice${index }id" />
							<input class="btn btn-sm btn-primary" id="notice${index}add" name="addBtn" type="button" value="增加" onclick="save('notice',4,${index});"  />
							<input class="btn btn-sm btn-primary" id="notice${index}edit" type="button" style="display: none" value="修改" onclick="edit('notice',${index});"  />
							<input class="btn btn-sm btn-primary" id="notice${index}save" type="button" style="display: none" value="保存" onclick="doedit('','notice',${index});"  />
							<input class="btn btn-sm btn-primary" id="notice${index}delete" type="button" style="display: none" value="删除" onclick="dodel('','notice',${index});"  />
						</td>
					</tr>
				</c:forEach>
			</table>
			<div style="margin-left: 50px;margin-top:8px;">语音验证码</div>
			<table width="100%" border="0" cellspacing="1" id="code" cellpadding="0" class="table table-striped table-bordered table-hover dataTables-example dataTable" aria-describedby="DataTables_Table_0_info">
				<tr>
					<th>序号</th>
					<th><font color="red">*</font>报警时间</th>
					<th>并发数（上限）</th>
					<th>并发数（下限）</th>
					<th>接通率（下限）</th>
					<th>应答率（下限）</th>
					<th>平均通话时长（秒/下限）</th>
					<th>平均接通时延（秒/上限）</th>
					<th>平均接续时长（秒/上限）</th>
					<th>操作</th>
				</tr>
				<c:forEach var="code" items="${codeSettings }" varStatus="index" >
					<tr id="code${index.index + 1 }">
						<td>${index.index + 1}</td>
						<td><input type="text" name="measureTime" id="codemeasureTime${index.index + 1 }" value="${code.measureTime}" disabled="disabled" maxlength="15" /></td>
						<td><input type="number" name="callCountbUp" id="codecallCountbUp${index.index + 1 }" value="${code.callCountbUp == -1 ? '' : code.callCountbUp}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" name="callCountbDown" id="codecallCountbDown${index.index + 1 }" value="${code.callCountbDown == -1 ? '' : code.callCountbDown}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="text" name="successRatebDown" id="codesuccessRatebDown${index.index + 1 }" value="${code.successRatebDown == -1 ? '' : code.successRatebDown / 100}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" />%</td>
						<td><input type="text" name="responseRatebDown" id="coderesponseRatebDown${index.index + 1 }" value="${code.responseRatebDown == -1 ? '' : code.responseRatebDown / 100}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" />%</td>
						<td><input type="number" name="averageTalkTimebDown" id="codeaverageTalkTimebDown${index.index + 1 }" value="${code.averageTalkTimebDown == -1 ? '' : code.averageTalkTimebDown}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" name="averageTurnOnDelaybUp" id="codeaverageTurnOnDelaybUp${index.index + 1 }" value="${code.averageTurnOnDelaybUp == -1 ? '' : code.averageTurnOnDelaybUp}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" name="averageInTimebUp" id="codeaverageInTimebUp${index.index + 1 }" value="${code.averageInTimebUp == -1 ? '' : code.averageInTimebUp}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td align="center">
							<input class="btn btn-sm btn-primary" id="code${index.index + 1 }edit" type="button" value="修改" onclick="edit('code',${index.index + 1 });"  />
							<input class="btn btn-sm btn-primary" id="code${index.index + 1 }save" type="button" style="display: none" value="保存" onclick="doedit(${code.id},'code',${index.index + 1 });"  />
							<input class="btn btn-sm btn-primary" id="code${index.index + 1 }delete" type="button" value="删除" onclick="dodel(${code.id},'code',${index.index + 1 });"  />
							<input class="btn btn-sm btn-primary" id="code${index.index + 1 }add" type="button" style="display: none" value="增加" onclick="save('code',5,${index.index + 1 });"  />
						</td>
					</tr>
				</c:forEach>
				<c:forEach var="index" begin="${codeSettings.size() + 1 }" end="3" varStatus="i">
					<tr id="code${index }">
						<td>${index}</td>
						<td><input type="text" name="measureTime" id="codemeasureTime${index }" value="" maxlength="15" /></td>
						<td><input type="number" name="callCountbUp" id="codecallCountbUp${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" name="callCountbDown" id="codecallCountbDown${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="text" name="successRatebDown" id="codesuccessRatebDown${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" />%</td>
						<td><input type="text" name="responseRatebDown" id="coderesponseRatebDown${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" />%</td>
						<td><input type="number" name="averageTalkTimebDown" id="codeaverageTalkTimebDown${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" name="averageTurnOnDelaybUp" id="codeaverageTurnOnDelaybUp${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" name="averageInTimebUp" id="codeaverageInTimebUp${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td align="center">
							<input type="hidden" id="code${index }id" />
							<input class="btn btn-sm btn-primary" id="code${index}add" name="addBtn" type="button" value="增加" onclick="save('code',5,${index});"  />
							<input class="btn btn-sm btn-primary" id="code${index}edit" type="button" style="display: none" value="修改" onclick="edit('code',${index});"  />
							<input class="btn btn-sm btn-primary" id="code${index}save" type="button" style="display: none" value="保存" onclick="doedit('','code',${index});"  />
							<input class="btn btn-sm btn-primary" id="code${index}delete" type="button" style="display: none" value="删除" onclick="dodel('','code',${index});"  />
						</td>
					</tr>
				</c:forEach>
			</table>
			<div style="margin-left: 50px;margin-top:8px;">直拨电话</div>
			<table width="100%" border="0" cellspacing="1" cellpadding="0" id="callPhone" class="table table-striped table-bordered table-hover dataTables-example dataTable" aria-describedby="DataTables_Table_0_info">
				<tr>
					<th>序号</th>
					<th><font color="red">*</font>报警时间</th>
					<th>并发数（上限）</th>
					<th>并发数（下限）</th>
					<th>接通率（下限）</th>
					<th>应答率（下限）</th>
					<th>平均通话时长（秒/下限）</th>
					<th>平均接通时延（秒/上限）</th>
					<th>平均接续时长（秒/上限）</th>
					<th>操作</th>
				</tr>
				<c:forEach var="callPhone" items="${callPhoneSettings }" varStatus="index" >
					<tr id="callPhone${index.index + 1 }">
						<td>${index.index + 1}</td>
						<td><input type="text" name="measureTime" id="callPhonemeasureTime${index.index + 1 }" value="${callPhone.measureTime}" disabled="disabled" maxlength="15" /></td>
						<td><input type="number" name="callCountbUp" id="callPhonecallCountbUp${index.index + 1 }" value="${callPhone.callCountbUp == -1 ? '' : callPhone.callCountbUp}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" name="callCountbDown" id="callPhonecallCountbDown${index.index + 1 }" value="${callPhone.callCountbDown == -1 ? '' : callPhone.callCountbDown}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="text" name="successRatebDown" id="callPhonesuccessRatebDown${index.index + 1 }" value="${callPhone.successRatebDown == -1 ? '' : callPhone.successRatebDown / 100}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" />%</td>
						<td><input type="text" name="responseRatebDown" id="callPhoneresponseRatebDown${index.index + 1 }" value="${callPhone.responseRatebDown == -1 ? '' : callPhone.responseRatebDown/ 100}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" />%</td>
						<td><input type="number" name="averageTalkTimebDown" id="callPhoneaverageTalkTimebDown${index.index + 1 }" value="${callPhone.averageTalkTimebDown == -1 ? '' : callPhone.averageTalkTimebDown}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" name="averageTurnOnDelaybUp" id="callPhoneaverageTurnOnDelaybUp${index.index + 1 }" value="${callPhone.averageTurnOnDelaybUp == -1 ? '' : callPhone.averageTurnOnDelaybUp}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" name="averageInTimebUp" id="callPhoneaverageInTimebUp${index.index + 1 }" value="${callPhone.averageInTimebUp == -1 ? '' : callPhone.averageInTimebUp}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td align="center">
							<input class="btn btn-sm btn-primary" id="callPhone${index.index + 1 }edit" type="button" value="修改" onclick="edit('callPhone',${index.index + 1 });"  />
							<input class="btn btn-sm btn-primary" id="callPhone${index.index + 1 }save" type="button" style="display: none" value="保存" onclick="doedit(${callPhone.id},'callPhone',${index.index + 1 });"  />
							<input class="btn btn-sm btn-primary" id="callPhone${index.index + 1 }delete" type="button" value="删除" onclick="dodel(${callPhone.id},'callPhone',${index.index + 1 });"  />
							<input class="btn btn-sm btn-primary" id="callPhone${index.index + 1 }add" type="button" style="display: none" value="增加" onclick="save('callPhone',3,${index.index + 1 });"  />
						</td>
					</tr>
				</c:forEach>
				<c:forEach var="index" begin="${callPhoneSettings.size() + 1 }" end="3" varStatus="i">
					<tr id="callPhone${index }">
						<td>${index}</td>
						<td><input type="text" name="measureTime" id="callPhonemeasureTime${index }" value="" maxlength="15" /></td>
						<td><input type="number" name="callCountbUp" id="callPhonecallCountbUp${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" name="callCountbDown" id="callPhonecallCountbDown${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="text" name="successRatebDown" id="callPhonesuccessRatebDown${index }" value="" maxlength="10" />%</td>
						<td><input type="text" name="responseRatebDown" id="callPhoneresponseRatebDown${index }" value="" maxlength="10" />%</td>
						<td><input type="number" name="averageTalkTimebDown" id="callPhoneaverageTalkTimebDown${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" name="averageTurnOnDelaybUp" id="callPhoneaverageTurnOnDelaybUp${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" name="averageInTimebUp" id="callPhoneaverageInTimebUp${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td align="center">
							<input type="hidden" id="callPhone${index }id" />
							<input class="btn btn-sm btn-primary" id="callPhone${index}add" name="addBtn" type="button" value="增加" onclick="save('callPhone',3,${index});"  />
							<input class="btn btn-sm btn-primary" id="callPhone${index}edit" type="button" style="display: none" value="修改" onclick="edit('callPhone',${index});"  />
							<input class="btn btn-sm btn-primary" id="callPhone${index}save" type="button" style="display: none" value="保存" onclick="doedit('','callPhone',${index});"  />
							<input class="btn btn-sm btn-primary" id="callPhone${index}delete" type="button" style="display: none" value="删除" onclick="dodel('','callPhone',${index});"  />
						</td>
					</tr>
				</c:forEach>
			</table>
			<div style="margin-left: 50px;margin-top:8px;">回拨电话</div>
			<table width="100%" border="0" cellspacing="1" cellpadding="0" id="backPhone" class="table table-striped table-bordered table-hover dataTables-example dataTable" aria-describedby="DataTables_Table_0_info">
				<tr>
					<th>序号</th>
					<th><font color="red">*</font>报警时间</th>
					<th>A路并发数（上限）</th>
					<th>B路并发数（上限）</th>
					<th>A路并发数（下限）</th>
					<th>B路并发数（下限）</th>
					<th>A路接通率（下限）</th>
					<th>B路接通率（下限）</th>
					<th>A路应答率（下限）</th>
					<th>B路应答率（下限）</th>
					<th>A路平均通话时长（秒/下限）</th>
					<th>B路平均通话时长（秒/下限）</th>
					<th>A路平均接通时延（秒/上限）</th>
					<th>B路平均接通时延（秒/上限）</th>
					<th>A路平均接续时长（秒/上限）</th>
					<th>B路平均接续时长（秒/上限）</th>
					<th>操作</th>
				</tr>
				<c:forEach var="backPhone" items="${backPhoneSettings }" varStatus="index" >
					<tr id="backPhone${index.index + 1 }">
						<td>${index.index + 1}</td>
						<td><input type="text" style="width: 115%;margin-left:-5px;" name="measureTime" id="backPhonemeasureTime${index.index + 1 }" value="${backPhone.measureTime}" disabled="disabled" maxlength="15" /></td>
						<td><input type="number" style="width: 100%" name="callCountaUp" id="backPhonecallCountaUp${index.index + 1 }" value="${backPhone.callCountaUp == -1 ? '' : backPhone.callCountaUp}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" style="width: 100%" name="callCountbUp" id="backPhonecallCountbUp${index.index + 1 }" value="${backPhone.callCountbUp == -1 ? '' : backPhone.callCountbUp}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" style="width: 100%" name="callCountaDown" id="backPhonecallCountaDown${index.index + 1 }" value="${backPhone.callCountaDown == -1 ? '' : backPhone.callCountaDown}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" style="width: 100%" name="callCountbDown" id="backPhonecallCountbDown${index.index + 1 }" value="${backPhone.callCountbDown == -1 ? '' : backPhone.callCountbDown}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="text" style="width: 90%" name="successRateaDown" id="backPhonesuccessRateaDown${index.index + 1 }" value="${backPhone.successRateaDown == -1 ? '' : backPhone.successRateaDown / 100}" disabled="disabled" maxlength="10" /><font style="float:right;width: 5%">%</font></td>
						<td><input type="text" style="width: 90%" name="successRatebDown" id="backPhonesuccessRatebDown${index.index + 1 }" value="${backPhone.successRatebDown == -1 ? '' : backPhone.successRatebDown / 100 }" disabled="disabled" maxlength="10" /><font style="float:right;width: 5%">%</font></td>
						<td><input type="text" style="width: 90%" name="responseRateaDown" id="backPhoneresponseRateaDown${index.index + 1 }" value="${backPhone.responseRateaDown == -1 ? '' : backPhone.responseRateaDown / 100}" disabled="disabled" maxlength="10" /><font style="float:right;width: 5%">%</font></td>
						<td><input type="text" style="width: 90%" name="responseRatebDown" id="backPhoneresponseRatebDown${index.index + 1 }" value="${backPhone.responseRatebDown == -1 ? '' : backPhone.responseRatebDown / 100}" disabled="disabled" maxlength="10" /><font style="float:right;width: 5%">%</font></td>
						<td><input type="number" style="width: 100%" name="averageTalkTimeaDown" id="backPhoneaverageTalkTimeaDown${index.index + 1 }" value="${backPhone.averageTalkTimeaDown == -1 ? '' : backPhone.averageTalkTimeaDown}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" style="width: 100%" name="averageTalkTimebDown" id="backPhoneaverageTalkTimebDown${index.index + 1 }" value="${backPhone.averageTalkTimebDown == -1 ? '' : backPhone.averageTalkTimebDown}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" style="width: 100%" name="averageTurnOnDelayaUp" id="backPhoneaverageTurnOnDelayaUp${index.index + 1 }" value="${backPhone.averageTurnOnDelayaUp == -1 ? '' : backPhone.averageTurnOnDelayaUp}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" style="width: 100%" name="averageTurnOnDelaybUp" id="backPhoneaverageTurnOnDelaybUp${index.index + 1 }" value="${backPhone.averageTurnOnDelaybUp == -1 ? '' : backPhone.averageTurnOnDelaybUp}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" style="width: 100%" name="averageInTimeaUp" id="backPhoneaverageInTimeaUp${index.index + 1 }" value="${backPhone.averageInTimeaUp == -1 ? '' : backPhone.averageInTimeaUp}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" style="width: 100%" name="averageInTimebUp" id="backPhoneaverageInTimebUp${index.index + 1 }" value="${backPhone.averageInTimebUp == -1 ? '' : backPhone.averageInTimebUp}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td align="center">
							<input class="btn btn-sm btn-primary" id="backPhone${index.index + 1 }edit" type="button" value="修改" onclick="edit('backPhone',${index.index + 1 });"  />
							<input class="btn btn-sm btn-primary" id="backPhone${index.index + 1 }save" type="button" style="display: none" value="保存" onclick="doedit(${backPhone.id},'backPhone',${index.index + 1 });"  />
							<input class="btn btn-sm btn-primary" id="backPhone${index.index + 1 }delete" type="button" value="删除" onclick="dodel(${backPhone.id},'backPhone',${index.index + 1 });"  />
							<input class="btn btn-sm btn-primary" id="backPhone${index.index + 1 }add" type="button" style="display: none" value="增加" onclick="save('backPhone',1,${index.index + 1 });"  />
						</td>
					</tr>
				</c:forEach>
				<c:forEach var="index" begin="${backPhoneSettings.size() + 1 }" end="3" varStatus="i">
					<tr id="backPhone${index }">
						<td>${index}</td>
						<td><input type="text" style="width: 115%;margin-left:-5px;" name="measureTime" id="backPhonemeasureTime${index }" value="" maxlength="15" /></td>
						<td><input type="number" style="width: 100%" name="callCountaUp" id="backPhonecallCountaUp${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" style="width: 100%" name="callCountbUp" id="backPhonecallCountbUp${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" style="width: 100%" name="callCountaDown" id="backPhonecallCountaDown${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" style="width: 100%" name="callCountbDown" id="backPhonecallCountbDown${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="text" style="width: 90%" name="successRateaDown" id="backPhonesuccessRateaDown${index }" value="" maxlength="10" /><font style="float:right;width: 5%">%</font></td>
						<td><input type="text" style="width: 90%" name="successRatebDown" id="backPhonesuccessRatebDown${index }" value="" maxlength="10" /><font style="float:right;width: 5%">%</font></td>
						<td><input type="text" style="width: 90%" name="responseRateaDown" id="backPhoneresponseRateaDown${index }" value="" maxlength="10" /><font style="float:right;width: 5%">%</font></td>
						<td><input type="text" style="width: 90%" name="responseRatebDown" id="backPhoneresponseRatebDown${index }" value="" maxlength="10" /><font style="float:right;width: 5%">%</font></td>
						<td><input type="number" style="width: 100%" name="averageTalkTimeaDown" id="backPhoneaverageTalkTimeaDown${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" style="width: 100%" name="averageTalkTimebDown" id="backPhoneaverageTalkTimebDown${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" style="width: 100%" name="averageTurnOnDelayaUp" id="backPhoneaverageTurnOnDelayaUp${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" style="width: 100%" name="averageTurnOnDelaybUp" id="backPhoneaverageTurnOnDelaybUp${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" style="width: 100%" name="averageInTimeaUp" id="backPhoneaverageInTimeaUp${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" style="width: 100%" name="averageInTimebUp" id="backPhoneaverageInTimebUp${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td align="center">
							<input type="hidden" id="backPhone${index }id" />
							<input class="btn btn-sm btn-primary" id="backPhone${index}add" name="addBtn" type="button" value="增加" onclick="save('backPhone',1,${index});"  />
							<input class="btn btn-sm btn-primary" id="backPhone${index}edit" type="button" style="display: none" value="修改" onclick="edit('backPhone',${index});"  />
							<input class="btn btn-sm btn-primary" id="backPhone${index}save" type="button" style="display: none" value="保存" onclick="doedit('','backPhone',${index});"  />
							<input class="btn btn-sm btn-primary" id="backPhone${index}delete" type="button" style="display: none" value="删除" onclick="dodel('','backPhone',${index});"  />
						</td>
					</tr>
				</c:forEach>
			</table>
			<div style="margin-left: 50px;margin-top:8px;">号码卫士</div>
			<table width="100%" border="0" cellspacing="1" cellpadding="0" id="guard" class="table table-striped table-bordered table-hover dataTables-example dataTable" aria-describedby="DataTables_Table_0_info">
				<tr>
					<th>序号</th>
					<th><font color="red">*</font>报警时间</th>
					<th>并发数（上限）</th>
					<th>并发数（下限）</th>
					<th>接通率（下限）</th>
					<th>应答率（下限）</th>
					<th>平均通话时长（秒/下限）</th>
					<th>平均接通时延（秒/上限）</th>
					<th>平均接续时长（秒/上限）</th>
					<th>操作</th>
				</tr>
				<c:forEach var="guard" items="${guardSettings }" varStatus="index" >
					<tr id="guard${index.index + 1 }">
						<td>${index.index + 1}</td>
						<td><input type="text" name="measureTime" id="guardmeasureTime${index.index + 1 }" value="${guard.measureTime}" disabled="disabled" maxlength="15" /></td>
						<td><input type="number" name="callCountbUp" id="guardcallCountbUp${index.index + 1 }" value="${guard.callCountbUp == -1 ? '' : guard.callCountbUp}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" name="callCountbDown" id="guardcallCountbDown${index.index + 1 }" value="${guard.callCountbDown == -1 ? '' : guard.callCountbDown}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="text" name="successRatebDown" id="guardsuccessRatebDown${index.index + 1 }" value="${guard.successRatebDown == -1 ? '' : guard.successRatebDown / 100}" disabled="disabled" maxlength="10" />%</td>
						<td><input type="text" name="responseRatebDown" id="guardresponseRatebDown${index.index + 1 }" value="${guard.responseRatebDown == -1 ? '' : guard.responseRatebDown / 100}" disabled="disabled" maxlength="10" />%</td>
						<td><input type="number" name="averageTalkTimebDown" id="guardaverageTalkTimebDown${index.index + 1 }" value="${guard.averageTalkTimebDown == -1 ? '' : guard.averageTalkTimebDown}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" name="averageTurnOnDelaybUp" id="guardaverageTurnOnDelaybUp${index.index + 1 }" value="${guard.averageTurnOnDelaybUp == -1 ? '' : guard.averageTurnOnDelaybUp}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" name="averageInTimebUp" id="guardaverageInTimebUp${index.index + 1 }" value="${guard.averageInTimebUp == -1 ? '' : guard.averageInTimebUp}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td align="center">
							<input class="btn btn-sm btn-primary" id="guard${index.index + 1 }edit" type="button" value="修改" onclick="edit('guard',${index.index + 1 });"  />
							<input class="btn btn-sm btn-primary" id="guard${index.index + 1 }add" type="button" style="display: none" value="增加" onclick="save('guard',2,${index.index + 1 });"  />
							<input class="btn btn-sm btn-primary" id="guard${index.index + 1 }save" type="button" style="display: none" value="保存" onclick="doedit(${guard.id},'guard',${index.index + 1 });"  />
							<input class="btn btn-sm btn-primary" id="guard${index.index + 1 }delete" type="button" value="删除" onclick="dodel(${guard.id},'guard',${index.index + 1 });"  />
						</td>
					</tr>
				</c:forEach>
				<c:forEach var="index" begin="${guardSettings.size() + 1 }" end="3" varStatus="i">
					<tr id="guard${index }">
						<td>${index}</td>
						<td><input type="text" name="measureTime" id="guardmeasureTime${index }" value="" maxlength="15" /></td>
						<td><input type="number" name="callCountbUp" id="guardcallCountbUp${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" name="callCountbDown" id="guardcallCountbDown${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="text" name="successRatebDown" id="guardsuccessRatebDown${index }" value="" maxlength="10" />%</td>
						<td><input type="text" name="responseRatebDown" id="guardresponseRatebDown${index }" value="" maxlength="10" />%</td>
						<td><input type="number" name="averageTalkTimebDown" id="guardaverageTalkTimebDown${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" name="averageTurnOnDelaybUp" id="guardaverageTurnOnDelaybUp${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" name="averageInTimebUp" id="guardaverageInTimebUp${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td align="center">
							<input type="hidden" id="guard${index }id" />
							<input class="btn btn-sm btn-primary" id="guard${index}add" type="button" value="增加" onclick="save('guard',2,${index});"  />
							<input class="btn btn-sm btn-primary" id="guard${index}edit" type="button" style="display: none" value="修改" onclick="edit('guard',${index});"  />
							<input class="btn btn-sm btn-primary" id="guard${index}save" type="button" style="display: none" value="保存" onclick="doedit('','guard',${index});"  />
							<input class="btn btn-sm btn-primary" id="guard${index}delete" type="button" style="display: none" value="删除" onclick="dodel('','guard',${index});"  />
						</td>
					</tr>
				</c:forEach>
			</table>
			<div style="margin-left: 50px;margin-top:8px;">短信(老平台)</div>
				<table width="100%" border="0" cellspacing="1" cellpadding="0" id="guard" class="table table-striped table-bordered table-hover dataTables-example dataTable" aria-describedby="DataTables_Table_0_info">
					<tr>
						<th>序号</th>
						<th><font color="red">*</font>报警时间</th>
						<th>并发数（上限）</th>
						<th>并发数（下限）</th>
						<th>&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;</th>
						<th>&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;</th>
						<th>&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;</th>
						<th>&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;</th>
						<th>&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;</th>
						<th>操作</th>
					</tr>
				<c:forEach var="sms" items="${smsSettings }" varStatus="index" >
					<tr id="sms${index.index + 1 }">
						<td>${index.index + 1}</td>
						<td><input type="text" name="measureTime" id="smsmeasureTime${index.index + 1 }" value="${sms.measureTime}" disabled="disabled" maxlength="15" /></td>
						<td><input type="number" name="callCountbUp" id="smscallCountbUp${index.index + 1 }" value="${sms.callCountbUp == -1 ? '' : sms.callCountbUp}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" name="callCountbDown" id="smscallCountbDown${index.index + 1 }" value="${sms.callCountbDown == -1 ? '' : sms.callCountbDown}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<%-- <td><input type="text" name="successRatebDown" id="guardsuccessRatebDown${index.index + 1 }" value="${guard.successRatebDown == -1 ? '' : guard.successRatebDown / 100}" disabled="disabled" maxlength="10" />%</td>
						<td><input type="text" name="responseRatebDown" id="guardresponseRatebDown${index.index + 1 }" value="${guard.responseRatebDown == -1 ? '' : guard.responseRatebDown / 100}" disabled="disabled" maxlength="10" />%</td>
						<td><input type="number" name="averageTalkTimebDown" id="guardaverageTalkTimebDown${index.index + 1 }" value="${guard.averageTalkTimebDown == -1 ? '' : guard.averageTalkTimebDown}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" name="averageTurnOnDelaybUp" id="guardaverageTurnOnDelaybUp${index.index + 1 }" value="${guard.averageTurnOnDelaybUp == -1 ? '' : guard.averageTurnOnDelaybUp}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" name="averageInTimebUp" id="guardaverageInTimebUp${index.index + 1 }" value="${guard.averageInTimebUp == -1 ? '' : guard.averageInTimebUp}" disabled="disabled" oninput="if(value.length>9)value=value.slice(0,9)" /></td> --%>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td align="center">
							<input class="btn btn-sm btn-primary" id="sms${index.index + 1 }edit" type="button" value="修改" onclick="edit('sms',${index.index + 1 });"  />
							<input class="btn btn-sm btn-primary" id="sms${index.index + 1 }add" type="button" style="display: none" value="增加" onclick="save('sms',8,${index.index + 1 });"  />
							<input class="btn btn-sm btn-primary" id="sms${index.index + 1 }save" type="button" style="display: none" value="保存" onclick="doedit(${sms.id},'sms',${index.index + 1 });"  />
							<input class="btn btn-sm btn-primary" id="sms${index.index + 1 }delete" type="button" value="删除" onclick="dodel(${sms.id},'sms',${index.index + 1 });"  />
						</td>
					</tr>
				</c:forEach>
				<c:forEach var="index" begin="${smsSettings.size() + 1 }" end="3" varStatus="i">
					<tr id="sms${index }">
						<td>${index}</td>
						<td><input type="text" name="measureTime" id="smsmeasureTime${index }" value="" maxlength="15" /></td>
						<td><input type="number" name="callCountbUp" id="smscallCountbUp${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" name="callCountbDown" id="smscallCountbDown${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<%-- <td><input type="text" name="successRatebDown" id="guardsuccessRatebDown${index }" value="" maxlength="10" />%</td>
						<td><input type="text" name="responseRatebDown" id="guardresponseRatebDown${index }" value="" maxlength="10" />%</td>
						<td><input type="number" name="averageTalkTimebDown" id="guardaverageTalkTimebDown${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" name="averageTurnOnDelaybUp" id="guardaverageTurnOnDelaybUp${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" /></td>
						<td><input type="number" name="averageInTimebUp" id="guardaverageInTimebUp${index }" value="" oninput="if(value.length>9)value=value.slice(0,9)" /></td> --%>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td align="center">
							<input type="hidden" id="sms${index }id" />
							<input class="btn btn-sm btn-primary" id="sms${index}add" type="button" value="增加" onclick="save('sms',80,${index});"  />
							<input class="btn btn-sm btn-primary" id="sms${index}edit" type="button" style="display: none" value="修改" onclick="edit('sms',${index});"  />
							<input class="btn btn-sm btn-primary" id="sms${index}save" type="button" style="display: none" value="保存" onclick="doedit('','sms',${index});"  />
							<input class="btn btn-sm btn-primary" id="sms${index}delete" type="button" style="display: none" value="删除" onclick="dodel('','sms',${index});"  />
						</td>
					</tr>
			</c:forEach>
		</div>
	</div>
</form>
<script type="text/javascript">
	function save(biz,bizId,index){
		var measureTime = $("#"+biz+"measureTime"+index+"").val();
		if(measureTime.trim() == ''){
			$('#'+biz+'measureTime'+index+'').focus();
			layer.tips('请输入报警时间.', $('#'+biz+'measureTime'+index+''));
			return false;
		}
		var timereg = /^(?:[01]\d|2[0-3])(?::[0-5]\d)$/;
		var head = measureTime.substring(0,5);
		var middle = measureTime.substring(5,6);
		var tail = measureTime.substring(6);
		if(timereg.test(head) == false || middle != '~' || timereg.test(tail) == false){
			$('#'+biz+'measureTime'+index+'').focus();
			layer.tips('警报时间格式为HH:mm~HH:mm.', $('#'+biz+'measureTime'+index+''));
			return false;
		}
		if(index > 1){
			var lastMeasureTime	= $("#"+biz+"measureTime"+(index-1)+"").val();	
			var flag = measureTimeJudge(biz, index, measureTime, lastMeasureTime);
			if(flag == false){
				return false;
			}
		}
		var regnum = /^-*\d+$/;
		var callCountaUp = $("#"+biz+"callCountaUp"+index+"").val();
	   if(biz=='smsNotice'||biz=='smsCode'||biz=='smsSell'){
		  if(undefined != callCountaUp&&callCountaUp!='' &&!regnum.test(callCountaUp)){
			  
				$('#'+biz+'callCountaUp'+index+'').focus();
				layer.tips('请输入开始报警条数数值.', $('#'+biz+'callCountaUp'+index+''));
				return false;
		    } 
		}
		var callCountbUp = $("#"+biz+"callCountbUp"+index+"").val();
		/* if(callCountbUp.trim() == ''){
			$('#'+biz+'callCountbUp'+index+'').focus();
			if(biz == 'backPhone'){
				layer.tips('请输入B路并发数（上限）.', $('#'+biz+'callCountbUp'+index+''));
			}else{
				layer.tips('请输入并发数（上限）.', $('#'+biz+'callCountbUp'+index+''));
			}
			return false;
		} */
		var callCountaDown = $("#"+biz+"callCountaDown"+index+"").val();
		/* if(undefined != callCountaDown && callCountaDown.trim() == ''){
			$('#'+biz+'callCountaDown'+index+'').focus();
			layer.tips('请输入A路并发数(下限).', $('#'+biz+'callCountaDown'+index+''));
			return false;
		} */
		var callCountbDown = $("#"+biz+"callCountbDown"+index+"").val();
		/* if(callCountbDown.trim() == ''){
			$('#'+biz+'callCountbDown'+index+'').focus();
			if(biz == 'backPhone'){
				layer.tips('请输入B路并发数(下限).', $('#'+biz+'callCountbDown'+index+''));
			}else{
				layer.tips('请输入并发数(下限).', $('#'+biz+'callCountbDown'+index+''));
			}
			return false;
		} */
		var reg = /^(0|[1-9]|[1-9]\d|100)(\.\d{1,2}|\.{0})$/;
		var successRateaDown = $("#"+biz+"successRateaDown"+index+"").val();
		/* if(undefined != successRateaDown && successRateaDown.trim() == ''){
			$('#'+biz+'successRateaDown'+index+'').focus();
			layer.tips('请输入A路接通率（下限）.', $('#'+biz+'successRateaDown'+index+''));
			return false;
		} */
		if(undefined != successRateaDown && successRateaDown.trim() != '' && reg.test(successRateaDown) == false){
			$('#'+biz+'successRateaDown'+index+'').focus();
			if(biz=='smsNotice'||biz=='smsCode'||biz=='smsSell'){
			   layer.tips('未知率（上限）只能为0-100间的数字（最多两位小数）.', $('#'+biz+'successRateaDown'+index+''));
			}else{
				layer.tips('A路接通率（下限）只能为0-100间的数字（最多两位小数）.', $('#'+biz+'successRateaDown'+index+''));
			}
			
			return false;
		}
		var successRatebDown = $("#"+biz+"successRatebDown"+index+"").val();
		
	 	if(undefined != successRatebDown && successRatebDown.trim() != '' && reg.test(successRatebDown) == false){
			$('#'+biz+'successRatebDown'+index+'').focus();
			if(biz == 'backPhone'){
				layer.tips('B路接通率（下限）只能为0-100间的数字（最多两位小数）.', $('#'+biz+'successRatebDown'+index+''));
			}else if(biz=='smsNotice'||biz=='smsCode'||biz=='smsSell'){
				layer.tips('成功率（下限）只能为0-100间的数字（最多两位小数）.', $('#'+biz+'successRatebDown'+index+''));
			}else{
				layer.tips('接通率（下限）只能为0-100间的数字（最多两位小数）.', $('#'+biz+'successRatebDown'+index+''));
			}
			return false;
		}
		var responseRateaDown = $("#"+biz+"responseRateaDown"+index+"").val();
		/* if(undefined != responseRateaDown && responseRateaDown.trim() == ''){
			$('#'+biz+'responseRateaDown'+index+'').focus();
			layer.tips('请输入A路应答率（下限）.', $('#'+biz+'responseRateaDown'+index+''));
			return false;
		} */
		if(undefined != responseRateaDown && responseRateaDown.trim() != '' &&  reg.test(responseRateaDown) == false){
			$('#'+biz+'responseRateaDown'+index+'').focus();
			layer.tips('A路应答率（下限）只能为0-100间的数字（最多两位小数）.', $('#'+biz+'responseRateaDown'+index+''));
			return false;
		}
		var responseRatebDown = $("#"+biz+"responseRatebDown"+index+"").val();
		
		if(undefined != responseRatebDown  && responseRatebDown.trim() != '' && reg.test(responseRatebDown) == false){
			$('#'+biz+'responseRatebDown'+index+'').focus();
			if(biz == 'backPhone'){
				layer.tips('B路应答率（下限）只能为0-100间的数字（最多两位小数）.', $('#'+biz+'responseRatebDown'+index+''));
			}else if(biz=='smsNotice'||biz=='smsCode'||biz=='smsSell'){
			  layer.tips('失败率（上限）只能为0-100间的数字（最多两位小数）.', $('#'+biz+'responseRatebDown'+index+''));
			}
			else{
				layer.tips('应答率（下限）只能为0-100间的数字（最多两位小数）.', $('#'+biz+'responseRatebDown'+index+''));
			}
			return false;
		}
		var averageTalkTimeaDown = $("#"+biz+"averageTalkTimeaDown"+index+"").val();
		/* if(undefined != averageTalkTimeaDown && averageTalkTimeaDown.trim() == ''){
			$('#'+biz+'averageTalkTimeaDown'+index+'').focus();
			layer.tips('请输入A路平均通话时长（秒/下限）.', $('#'+biz+'averageTalkTimeaDown'+index+''));
			return false;
		} */
		var averageTalkTimebDown = $("#"+biz+"averageTalkTimebDown"+index+"").val();
		/* if(averageTalkTimebDown.trim() == ''){
			$('#'+biz+'averageTalkTimebDown'+index+'').focus();
			if(biz == 'backPhone'){
				layer.tips('请输入B路平均通话时长（秒/下限）.', $('#'+biz+'averageTalkTimebDown'+index+''));
			}else{
				layer.tips('请输入平均通话时长（秒/下限）.', $('#'+biz+'averageTalkTimebDown'+index+''));
			}
			return false;
		} */
		var averageTurnOnDelayaUp = $("#"+biz+"averageTurnOnDelayaUp"+index+"").val();
		/* if(undefined != averageTurnOnDelayaUp && averageTurnOnDelayaUp.trim() == ''){
			$('#'+biz+'averageTurnOnDelayaUp'+index+'').focus();
			layer.tips('请输入A路平均接通时延（秒/上限）.', $('#'+biz+'averageTurnOnDelayaUp'+index+''));
			return false;
		} */
		var averageTurnOnDelaybUp = $("#"+biz+"averageTurnOnDelaybUp"+index+"").val();
		/* if(averageTurnOnDelaybUp.trim() == ''){
			$('#'+biz+'averageTurnOnDelaybUp'+index+'').focus();
			if(biz == 'backPhone'){
				layer.tips('请输入B路平均接通时延（秒/上限）.', $('#'+biz+'averageTurnOnDelaybUp'+index+''));
			}else{
				layer.tips('请输入平均接通时延（秒/上限）.', $('#'+biz+'averageTurnOnDelaybUp'+index+''));
			}
			return false;
		} */
		var averageInTimeaUp = $("#"+biz+"averageInTimeaUp"+index+"").val();
		/* if(undefined != averageInTimeaUp && averageInTimeaUp.trim() == ''){
			$('#'+biz+'averageInTimeaUp'+index+'').focus();
			layer.tips('请输入A路接续时长（秒/上限）.', $('#'+biz+'averageInTimeaUp'+index+''));
			return false;
		} */
		var averageInTimebUp = $("#"+biz+"averageInTimebUp"+index+"").val();
		/* if(averageInTimebUp.trim() == ''){
			$('#'+biz+'averageInTimebUp'+index+'').focus();
			if(biz == 'backPhone'){
				layer.tips('请输入B路接续时长（秒/上限）.', $('#'+biz+'averageInTimebUp'+index+''));
			}else{
				layer.tips('请输入接续时长（秒/上限）.', $('#'+biz+'averageInTimebUp'+index+''));
			}
			return false;
		} */
		$.ajax({
			type: "POST",
		   	url: "/monitor/save_monitor_setting",
		   	data: {
               'measureTime': measureTime,
               'callCountaUp': callCountaUp,
               'callCountbUp': callCountbUp,
			   'callCountaDown': callCountaDown,
			   'callCountbDown': callCountbDown,
			   'successRateaDown': successRateaDown,
			   'successRatebDown': successRatebDown,
			   'responseRateaDown': responseRateaDown,
			   'responseRatebDown': responseRatebDown,
			   'averageTalkTimeaDown': averageTalkTimeaDown,
			   'averageTalkTimebDown': averageTalkTimebDown,
			   'averageTurnOnDelayaUp': averageTurnOnDelayaUp,
			   'averageTurnOnDelaybUp': averageTurnOnDelaybUp,
			   'averageInTimeaUp': averageInTimeaUp,
			   'averageInTimebUp': averageInTimebUp,
			   'bizId': bizId
           },
		   success: function(data){
			   var trid = biz + index; 
			   $("#"+trid+"id").val(data);
			   $("#"+trid+" :input[type='text']").attr("disabled","disabled");
			   $("#"+trid+" :input[type='number']").attr("disabled","disabled");
			   $("#"+trid+"add").hide();
			   $("#"+trid+"edit").show();
			   $("#"+trid+"delete").show();
		   }
		});
		
	}
	
	function edit(biz, index){
		var id = biz + index;
		$("#"+id+" :input[type='text']").removeAttr("disabled","disabled");
		$("#"+id+" :input[type='number']").removeAttr("disabled","disabled");
		$("#"+id+"edit").hide();
		$("#"+id+"save").show();
	}
	
	function doedit(id, biz, index){
		var trid = biz + index;
		if(id == ''){
			id = $("#"+trid+"id").val();
		}
		var measureTime = $("#"+biz+"measureTime"+index+"").val();
		if(measureTime.trim() == ''){
			$('#'+biz+'measureTime'+index+'').focus();
			layer.tips('请输入报警时间.', $('#'+biz+'measureTime'+index+''));
			return false;
		}
		var timereg = /^(?:[01]\d|2[0-3])(?::[0-5]\d)$/;
		var head = measureTime.substring(0,5);
		var middle = measureTime.substring(5,6);
		var tail = measureTime.substring(6);
		if(timereg.test(head) == false || middle != '~' || timereg.test(tail) == false){
			$('#'+biz+'measureTime'+index+'').focus();
			layer.tips('警报时间格式为HH:mm~HH:mm.', $('#'+biz+'measureTime'+index+''));
			return false;
		}
		if(index > 1){
			var lastMeasureTime	= $("#"+biz+"measureTime"+(index-1)+"").val();	
			var flag = measureTimeJudge(biz, index, measureTime, lastMeasureTime);
			if(flag == false){
				return false;
			}
		}
		var regnum = /^-*\d+$/;
		var callCountaUp = $("#"+biz+"callCountaUp"+index+"").val();
		if(biz=='smsNotice'||biz=='smsCode'||biz=='smsSell'){
		  if(undefined != callCountaUp&&callCountaUp!='' &&!regnum.test(callCountaUp)){
			  
				$('#'+biz+'callCountaUp'+index+'').focus();
				layer.tips('请输入开始报警条数数值.', $('#'+biz+'callCountaUp'+index+''));
				return false;
		    } 
		}
		/* if(undefined != callCountaUp && callCountaUp.trim() == ''){
			$('#'+biz+'callCountaUp'+index+'').focus();
			layer.tips('请输入A路并发数（上限）.', $('#'+biz+'callCountaUp'+index+''));
			return false;
		} */
		var callCountbUp = $("#"+biz+"callCountbUp"+index+"").val();
		/* if(callCountbUp.trim() == ''){
			$('#'+biz+'callCountbUp'+index+'').focus();
			if(biz == 'backPhone'){
				layer.tips('请输入B路并发数（上限）.', $('#'+biz+'callCountbUp'+index+''));
			}else{
				layer.tips('请输入并发数（上限）.', $('#'+biz+'callCountbUp'+index+''));
			}
			return false;
		} */
		var callCountaDown = $("#"+biz+"callCountaDown"+index+"").val();
		/* if(undefined != callCountaDown && callCountaDown.trim() == ''){
			$('#'+biz+'callCountaDown'+index+'').focus();
			layer.tips('请输入A路并发数(下限).', $('#'+biz+'callCountaDown'+index+''));
			return false;
		} */
		var callCountbDown = $("#"+biz+"callCountbDown"+index+"").val();
		/* if(undefined != callCountbDown && callCountbDown.trim() == ''){
			$('#'+biz+'callCountbDown'+index+'').focus();
			if(biz == 'backPhone'){
				layer.tips('请输入B路并发数(下限).', $('#'+biz+'callCountbDown'+index+''));
			}else{
				layer.tips('请输入并发数(下限).', $('#'+biz+'callCountbDown'+index+''));
			}
			return false;
		} */
		var reg = /^(0|[1-9]|[1-9]\d|100)(\.\d{1,2}|\.{0})$/;
		var successRateaDown = $("#"+biz+"successRateaDown"+index+"").val();
		/* if(undefined != successRateaDown && successRateaDown.trim() == ''){
			$('#'+biz+'successRateaDown'+index+'').focus();
			layer.tips('请输入A路接通率（下限）.', $('#'+biz+'successRateaDown'+index+''));
			return false;
		} */
		if(undefined != successRateaDown&& successRateaDown.trim() != '' && reg.test(successRateaDown) == false){
			$('#'+biz+'successRateaDown'+index+'').focus();
			if(biz=='smsNotice'||biz=='smsCode'||biz=='smsSell'){
				layer.tips('未知率（上限）只能为0-100间的数字（最多两位小数）.', $('#'+biz+'successRateaDown'+index+''));
			}else{
				
			layer.tips('A路接通率（下限）只能为0-100间的数字（最多两位小数）.', $('#'+biz+'successRateaDown'+index+''));
			}
			return false;
		}
		var successRatebDown = $("#"+biz+"successRatebDown"+index+"").val();
		/* if(successRatebDown.trim() == ''){
			$('#'+biz+'successRatebDown'+index+'').focus();
			if(biz == 'backPhone'){
				layer.tips('请输入B路接通率（下限）.', $('#'+biz+'successRatebDown'+index+''));
			}else{
				layer.tips('请输入接通率（下限）.', $('#'+biz+'successRatebDown'+index+''));
			}
			return false;
		} */
	 	if(undefined != successRatebDown && successRatebDown.trim() != '' && reg.test(successRatebDown) == false){
			$('#'+biz+'successRatebDown'+index+'').focus();
			if(biz == 'backPhone'){
				layer.tips('B路接通率（下限）只能为0-100间的数字（最多两位小数）.', $('#'+biz+'successRatebDown'+index+''));
			}else if(biz=='smsNotice'||biz=='smsCode'||biz=='smsSell'){
				layer.tips('成功率（下限）只能为0-100间的数字（最多两位小数）.', $('#'+biz+'successRatebDown'+index+''));
			}
			else{
				layer.tips('接通率（下限）只能为0-100间的数字（最多两位小数）.', $('#'+biz+'successRatebDown'+index+''));
			}
			return false;
		}
		var responseRateaDown = $("#"+biz+"responseRateaDown"+index+"").val();
		/* if(undefined != responseRateaDown && responseRateaDown.trim() == ''){
			$('#'+biz+'responseRateaDown'+index+'').focus();
			layer.tips('请输入A路应答率（下限）.', $('#'+biz+'responseRateaDown'+index+''));
			return false;
		} */
		if(undefined != responseRateaDown &&'' != responseRateaDown.trim() && reg.test(responseRateaDown) == false){
			$('#'+biz+'responseRateaDown'+index+'').focus();
			layer.tips('A路应答率（下限）只能为0-100间的数字（最多两位小数）.', $('#'+biz+'responseRateaDown'+index+''));
			return false;
		}
		var responseRatebDown = $("#"+biz+"responseRatebDown"+index+"").val();
		/* if(responseRatebDown.trim() == ''){
			$('#'+biz+'responseRatebDown'+index+'').focus();
			if(biz == 'backPhone'){
				layer.tips('请输入B路应答率（下限）.', $('#'+biz+'responseRatebDown'+index+''));
			}else{
				layer.tips('请输入应答率（下限）.', $('#'+biz+'responseRatebDown'+index+''));
			}
			return false;
		} */
		if(undefined != responseRatebDown && responseRatebDown.trim() != '' && reg.test(responseRatebDown) == false){
			$('#'+biz+'responseRatebDown'+index+'').focus();
			if(biz == 'backPhone'){
				layer.tips('B路应答率（下限）只能为0-100间的数字（最多两位小数）.', $('#'+biz+'responseRatebDown'+index+''));
			}else if(biz=='smsNotice'||biz=='smsCode'||biz=='smsSell'){
				layer.tips('失败率（上限）只能为0-100间的数字（最多两位小数）.', $('#'+biz+'successRatebDown'+index+''));
			}
			else{
				layer.tips('应答率（下限）只能为0-100间的数字（最多两位小数）.', $('#'+biz+'responseRatebDown'+index+''));
			}
			return false;
		} 
		var averageTalkTimeaDown = $("#"+biz+"averageTalkTimeaDown"+index+"").val();
		/* if(undefined != averageTalkTimeaDown && averageTalkTimeaDown.trim() == ''){
			$('#'+biz+'averageTalkTimeaDown'+index+'').focus();
			layer.tips('请输入A路平均通话时长（秒/下限）.', $('#'+biz+'averageTalkTimeaDown'+index+''));
			return false;
		} */
		var averageTalkTimebDown = $("#"+biz+"averageTalkTimebDown"+index+"").val();
		/* if(averageTalkTimebDown.trim() == ''){
			$('#'+biz+'averageTalkTimebDown'+index+'').focus();
			if(biz == 'backPhone'){
				layer.tips('请输入B路平均通话时长（秒/下限）.', $('#'+biz+'averageTalkTimebDown'+index+''));
			}else{
				layer.tips('请输入平均通话时长（秒/下限）.', $('#'+biz+'averageTalkTimebDown'+index+''));
			}
			return false;
		} */
		var averageTurnOnDelayaUp = $("#"+biz+"averageTurnOnDelayaUp"+index+"").val();
		/* if(undefined != averageTurnOnDelayaUp && averageTurnOnDelayaUp.trim() == ''){
			$('#'+biz+'averageTurnOnDelayaUp'+index+'').focus();
			layer.tips('请输入A路平均接通时延（秒/上限）.', $('#'+biz+'averageTurnOnDelayaUp'+index+''));
			return false;
		} */
		var averageTurnOnDelaybUp = $("#"+biz+"averageTurnOnDelaybUp"+index+"").val();
		/* if(averageTurnOnDelaybUp.trim() == ''){
			$('#'+biz+'averageTurnOnDelaybUp'+index+'').focus();
			if(biz == 'backPhone'){
				layer.tips('请输入B路平均接通时延（秒/上限）.', $('#'+biz+'averageTurnOnDelaybUp'+index+''));
			}else{
				layer.tips('请输入平均接通时延（秒/上限）.', $('#'+biz+'averageTurnOnDelaybUp'+index+''));
			}
			return false;
		} */
		var averageInTimeaUp = $("#"+biz+"averageInTimeaUp"+index+"").val();
		/* if(undefined != averageInTimeaUp && averageInTimeaUp.trim() == ''){
			$('#'+biz+'averageInTimeaUp'+index+'').focus();
			layer.tips('请输入A路接续时长（秒/上限）.', $('#'+biz+'averageInTimeaUp'+index+''));
			return false;
		} */
		var averageInTimebUp = $("#"+biz+"averageInTimebUp"+index+"").val();
		/* if(averageInTimebUp.trim() == ''){
			$('#'+biz+'averageInTimebUp'+index+'').focus();
			if(biz == 'backPhone'){
				layer.tips('请输入B路接续时长（秒/上限）.', $('#'+biz+'averageInTimebUp'+index+''));
			}else{
				layer.tips('请输入接续时长（秒/上限）.', $('#'+biz+'averageInTimebUp'+index+''));
			}
			return false;
		} */
		$.ajax({
			type: "POST",
		   	url: "/monitor/edit_monitor_setting",
		   	data: {
		   		'id': id, 
            	'measureTime': measureTime,
            	'callCountaUp': callCountaUp,
                'callCountbUp': callCountbUp,
 			   	'callCountaDown': callCountaDown,
 			   	'callCountbDown': callCountbDown,
 			   	'successRateaDown': successRateaDown,
 			   	'successRatebDown': successRatebDown,
 			   	'responseRateaDown': responseRateaDown,
 			   	'responseRatebDown': responseRatebDown,
 			   	'averageTalkTimeaDown': averageTalkTimeaDown,
 			   	'averageTalkTimebDown': averageTalkTimebDown,
 			   	'averageTurnOnDelayaUp': averageTurnOnDelayaUp,
 			   	'averageTurnOnDelaybUp': averageTurnOnDelaybUp,
 			   	'averageInTimeaUp': averageInTimeaUp,
 			   	'averageInTimebUp': averageInTimebUp
           },
		   success: function(data){
			   if(data){
				   $("#"+trid+" :input[type='text']").attr("disabled","disabled");
				   $("#"+trid+" :input[type='number']").attr("disabled","disabled");
				   $("#"+trid+"edit").show();
				   $("#"+trid+"save").hide();
			   }
		   }
		});
	}
	
	function dodel(id, biz, index){
		var trid = biz + index;
		if(id == ''){
			id = $("#"+trid+"id").val();
		}
		$.ajax({
			type: "POST",
		   	url: "/monitor/monitor_setting_delete",
		   	data: {
		   		'id': id
           },
		   success: function(data){
  				if(data){
 				   	$("#"+trid+" :input[type='text']").val('');
 				   	$("#"+trid+" :input[type='number']").val('');
 					$("#"+trid+" :input[type='text']").removeAttr("disabled","disabled");
 				   	$("#"+trid+" :input[type='number']").removeAttr("disabled","disabled");
					$("#"+trid+"add").show();
			   		$("#"+trid+"edit").hide();
			   		$("#"+trid+"delete").hide();
		   		}
		   }
			
		});
	}
	
	function measureTimeJudge(biz, index,measureTime, lastMeasureTime){
		var flag = true;
		$.ajax({
			type: "POST",
			async: false,
		   	url: "/monitor/measureTimeJudge",
		   	data: {
               'measureTime': measureTime,
               'lastMeasureTime': lastMeasureTime
           },
		   success: function(data){
		   	   if(data){
		   		   $('#'+biz+'measureTime'+index+'').focus();
				   layer.tips('报警开始时间应大于上条监控结束时间.', $('#'+biz+'measureTime'+index+''));
				   flag = false;
	   		   }
		   }
		});
		return flag;
	}
	
	function noticesetting(){
		   layer.open({
			  type: 2, //page层
			  area: ['630px', '400px'],
			  title: '报警通知设置',
			  shade: 0.3, //遮罩透明度
			  moveType: 0, //拖拽风格，0是默认，1是传统拖动
			  shift: 1, //0-6的动画形式，-1不开启
			  scrollbar:false,
			  content:'/monitor/global_notice_setting'
		   }); 
		}
</script>
</body>
</html>