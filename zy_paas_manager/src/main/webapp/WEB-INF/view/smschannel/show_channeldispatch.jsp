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
	<div class="col-sm-12" style="border-bottom: 1px solid #e5e6e7; padding-bottom: -20px;margin-bottom: 10px;">
		<div class="ibox">
			<div class="ibox-title">
				<h5>
					短信通道管理<small>&nbsp;-->&nbsp;
					<c:choose>
					    <c:when test="${not empty obj.channelId}">   
						             对接配置修改
						</c:when>
						<c:otherwise>   
					                           对接配置录入         
					    </c:otherwise>
					</c:choose>
					</small>
				</h5>
			</div>
			
			<div id="DataTables_Table_0_wrapper"
		class="dataTables_wrapper form-inline" role="grid">
		<form class="form-horizontal m-t" id="myForm" method="post" action="/smschannel/smschannel_save" novalidate="novalidate">
		<input type="hidden" id="channelId" value="${cfgMap["CHANNEL_ID"]}"/>
		<table
			class="table table-striped table-bordered table-hover dataTables-example dataTable"
			id="DataTables_Table_0" aria-describedby="DataTables_Table_0_info">
			<thead>
				<tr role="row">
				    <th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">序号</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" style="width: 150px;">参数描述</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" style="width: 150px;">参数键</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">参数值</th>
				</tr>
			</thead>
			<tbody>
										
				    <tr class="gradeC odd">
					    <td>1</td>
						<td>通道ID<span style="color: red; vertical-align: -3px;">*&nbsp;</span></td>
						<td>
							<input class="form-control" type="text" 
                                   value="CHANNEL_ID" maxlength="50" style="width: 150px;" readonly="readonly">
						</td>
						<td>
                            <input id="channelId2" name="channelId2" class="form-control" type="text" value="${cfgMap["CHANNEL_ID"]}" maxlength="150"
                                      style="display: inline-block;width: 500px;" readonly="readonly">
                        </td>
					</tr>
					
					<tr class="gradeC odd">
					    <td>2</td>
						<td>协议类型 <span style="color: red; vertical-align: -3px;">*&nbsp;</span></td>
						<td>
							<input class="form-control" type="text" 
                                   value="PROTOCOL_TYPE" maxlength="50" style="width: 150px;" style="width: 150px;" readonly="readonly">
                            <c:set var="pt" scope="request" value="${cfgMap['PROTOCOL_TYPE']}"/>
						</td>
						<td>
						    <label>
                                 <input type="radio" id="protocalType0" name="protocalType" checked="checked" value="0"> Other
                            </label>
                            <label style="margin-left: 25px;">
                                 <input type="radio" id="protocalType1" name="protocalType" value="1" <c:if test="${pt==1}"> checked="checked" </c:if>> HTTP
                            </label>
                            <label style="margin-left: 25px;">
                                 <input type="radio" id="protocalType2"  name="protocalType" value="2" <c:if test="${pt==2}"> checked="checked" </c:if>> CMPP
                            </label>
                            <label style="margin-left: 25px;">
                                 <input type="radio" id="protocalType3"  name="protocalType" value="3" <c:if test="${pt==3}"> checked="checked" </c:if>> SGIP
                            </label>                                 
                            <label style="margin-left: 25px;">
                                 <input type="radio" id="protocalType4"  name="protocalType" value="4" <c:if test="${pt==4}"> checked="checked" </c:if>> SMGP
                            </label>
                        </td>
					</tr>
					
					<tr class="gradeC odd">
					    <td>3</td>
						<td>通道名称<span style="color: red; vertical-align: -3px;">*&nbsp;</span></td>
						<td>
							<input class="form-control" type="text" 
                                   value="CHANNEL_NAME" maxlength="50" style="width: 150px;" style="width: 150px;" readonly="readonly">
						</td>
						<td>
                              <input id="channelName" name="channelName" class="form-control" type="text" value="${cfgMap["CHANNEL_NAME"]}" maxlength="150"
                                     style="display: inline-block;width: 500px;">
                        </td>
					</tr>
					

			        <tr class="gradeC odd">
					    <td>4</td>
						<td>对接账号<span style="color: red; vertical-align: -3px;">*&nbsp;</span></td>
						<td>
							<input class="form-control" type="text" 
                                   value="ACCOUNT" maxlength="50" style="width: 150px;" style="width: 150px;" readonly="readonly">
						</td>
						<td>
                              <input id="account" name="account" class="form-control" type="text" value="${cfgMap["ACCOUNT"]}" maxlength="150"
                                     style="display: inline-block;width: 500px;">
                        </td>
					</tr>
					
					 <tr class="gradeC odd">
					    <td>5</td>
						<td>对接密码<span style="color: red; vertical-align: -3px;">*&nbsp;</span></td>
						<td>
							<input class="form-control" type="text" 
                                   value="PASSWORD" maxlength="50" style="width: 150px;" style="width: 150px;" readonly="readonly">
						</td>
						<td>
                              <input id="password" name="password" class="form-control" type="text" value="${cfgMap["PASSWORD"]}" maxlength="150"
                                     style="display: inline-block;width: 500px;">
                        </td>
					</tr>
					
					<tr class="gradeC odd">
					    <td>6</td>
						<td>短信下行URL<span style="color: red; vertical-align: -3px;">*&nbsp;</span></td>
						<td>
							<input class="form-control" type="text" 
                                   value="SMS_MT_URL" maxlength="50" style="width: 150px;" style="width: 150px;" readonly="readonly">
						</td>
						<td>
                              <input id="smsMtUrl" name="smsMtUrl" class="form-control" type="text" value="${cfgMap["SMS_MT_URL"]}" maxlength="150"
                                     style="display: inline-block;width: 500px;">
                        </td>
					</tr>
					<tr class="gradeC odd">
					    <td>7</td>
						<td>短信上行URL<span style="color: red; vertical-align: -3px;">*&nbsp;</span></td>
						<td>
							<input class="form-control" type="text" 
                                   value="SMS_MO_URL" maxlength="50" style="width: 150px;" style="width: 150px;" readonly="readonly">
						</td>
						<td>
                              <input id="smsMoUrl" name="smsMoUrl" class="form-control" type="text" value="${cfgMap["SMS_MO_URL"]}" maxlength="150"
                                     style="display: inline-block;width: 500px;">
                        </td>
					</tr>
					<tr class="gradeC odd">
					    <td>8</td>
						<td>状态报告URL<span style="color: red; vertical-align: -3px;">*&nbsp;</span></td>
						<td>
							<input class="form-control" type="text" 
                                   value="SMS_STATUS_URL" maxlength="50" style="width: 150px;" style="width: 150px;" readonly="readonly">
						</td>
						<td>
                              <input id="smsStatusUrl" name="smsStatusUrl" class="form-control" type="text" value="${cfgMap["SMS_STATUS_URL"]}" maxlength="150"
                                     style="display: inline-block;width: 500px;">
                        </td>
					</tr>
					
					<tr class="gradeC odd">
					    <td>9</td>
						<td>接入用户</td>
						<td>
							<input class="form-control" type="text" 
                                   value="USERID" maxlength="50" style="width: 150px;" style="width: 150px;" readonly="readonly">
						</td>
						<td>
                              <input id="userId" name="userId" class="form-control" type="text" value="${cfgMap["USERID"]}" maxlength="150"
                                     style="display: inline-block;width: 500px;">
                        </td>
					</tr>
					
					<tr class="gradeC odd">
					    <td>10</td>
						<td>接入实例</td>
						<td>
							<input class="form-control" type="text" 
                                   value="TASKNAME" maxlength="50" style="width: 150px;" style="width: 150px;" readonly="readonly">
						</td>
						<td>
                              <input id="taskName" name="taskName" class="form-control" type="text" value="${cfgMap["TASKNAME"]}" maxlength="150"
                                     style="display: inline-block;width: 500px;">
                        </td>
					</tr>
					<tr class="gradeC odd">
					    <td>11</td>
						<td>扩展参数1</td>
						<td>
							<input class="form-control" type="text" id="extKey1" name="extKey1"
                                   value="${extList[0].PARAM_KEY}" maxlength="50" style="width: 150px;" >
						</td>
						<td>
                              <input id="extVal1" name="extVal1" class="form-control" type="text" maxlength="150"
                                    value="${extList[0].PARAM_VALUE}"  style="display: inline-block;width: 500px;" >
                        </td>
					</tr>
					<tr class="gradeC odd">
					    <td>12</td>
						<td>扩展参数2</td>
						<td>
							<input class="form-control" type="text" id="extKey2" name="extKey2"
                                   value="${extList[1].PARAM_KEY}"  maxlength="50" style="width: 150px;">
						</td>
						<td>
                              <input id="extVal2" name="extVal2" class="form-control" type="text" maxlength="150"
                                     value="${extList[1].PARAM_VALUE}" style="display: inline-block;width: 500px;">
                        </td>
					</tr>
					<tr class="gradeC odd">
					    <td>13</td>
						<td>扩展参数3</td>
						<td>
							<input class="form-control" type="text" id="extKey3" name="extKey3" 
                                   value="${extList[2].PARAM_KEY}"  maxlength="50" style="width: 150px;">
						</td>
						<td>
                              <input id="extVal3" name="extVal3" class="form-control" type="text" maxlength="150"
                                     value="${extList[2].PARAM_VALUE}" style="display: inline-block;width: 500px;">
                        </td>
					</tr>
					<tr class="gradeC odd">
					    <td>14</td>
						<td>扩展参数4</td>
						<td>
							<input class="form-control" type="text"  id="extKey4" name="extKey4"
                                   value="${extList[3].PARAM_KEY}"  maxlength="50" style="width: 150px;">
						</td>
						<td>
                              <input id="extVal4" name="extVal4" class="form-control" type="text"  maxlength="150"
                                     value="${extList[3].PARAM_VALUE}" style="display: inline-block;width: 500px;">
                        </td>
					</tr>
					
					<tr class="gradeC odd">
					    <td>15</td>
						<td>扩展参数5</td>
						<td>
							<input class="form-control" type="text"  id="extKey5" name="extKey5"
                                   value="${extList[4].PARAM_KEY}"  maxlength="50" style="width: 150px;">
						</td>
						<td>
                              <input id="extVal5" name="extVal5" class="form-control" type="text" maxlength="150"
                                     value="${extList[4].PARAM_VALUE}" style="display: inline-block;width: 500px;">
                        </td>
					</tr>
                    
                    <tr class="gradeC odd">
                     <td></td>
                     <td></td>
                     <td></td>
                     <td>
                     <c:if test="${vo.status !=1 }">
                     <button class="btn btn-primary" type="button" style="width: 60px;margin-left: 40px;" id="btnsave" onclick="saveChanelCfg();">保存</button>
                     </c:if>
                     <button class="btn btn-primary" type="button" style="width: 60px;margin-left: 40px;" id="btnback" onclick="history.go(-1);">返回</button>
                     </td>
                    </tr>

					
			</tbody>

		 </table>
 
	</div>
	<script type="text/javascript" src="/js/handler/smschanneldispatch.js"></script> 
	 
</body>
</html>
