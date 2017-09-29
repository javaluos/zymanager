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
		</div>
	</div>
	<table class="noborder">
		<tbody>
			<tr class="noline">
				<td style="width: 2%;"></td>
				<td class="fr" style="width: 10%;">通道名称</td>
				<td class="online" style="width: 10%"><input type="text"
					id="channelname" class="form-control" style="width: 250px;" value="${channelname }">
				</td>
				<td class="fr" style="width: 4%;"></td>
				<td class="fr" style="width: 10%; margin-left: 20px">通道ID</td>
				<td class="online" style="width: 10%"><input type="text"
					id="channelid" class="form-control" style="width: 320px;"
					maxlength="50" value="${channelid }"></td>
				<td style="width: 5%"></td>
				<td></td>
			</tr>
			<tr class="noline">
				<td style="width: 2%;"></td>
				<td class="fr" style="width: 10%;">通道类型</td>
				<td class="online" style="width: 10%">
					<select class="form-control" id="channeltype" style="width: 250px;">
						<option value=""></option>
						<c:forEach var="channelType" items="${channelTypeList }">
							<option value="${channelType.type }" <c:if test="${channelType.type == channeltype}">selected="selected"</c:if>>${channelType.name }</option>
						</c:forEach>
					</select>
				</td>
				<td class="fr" style="width: 4%;"></td>
				<td class="fr" style="width: 10%; margin-left: 20px">落地省份</td>
				<td class="online" style="width: 10%">
					<select class="form-control" id="dtnprovince" style="width: 120px;">
						<option value=""></option>
						<option value="平台商" <c:if test="${dtnprovince == '平台商'}">selected="selected"</c:if>>平台商</option>
						<c:forEach var="province" items="${provinceList }">
							<option value="${province }" <c:if test="${dtnprovince == province}">selected="selected"</c:if>>${province }</option>
						</c:forEach>
					</select>
					<span style="float:right;margin-top:-28px;margin-right:1px">
                        <label style="margin-right:16px">通道属性</label>
                        <select class="form-control" id="channelProperty" style="width: 120px;">
                            <c:forEach var="channelProperty" items="${channelPropertyList }">
                                <option value="${channelProperty.value }" <c:if test="${channelProperty.value == channelproperty}">selected="selected"</c:if>>${channelProperty.name}</option>
                            </c:forEach>
                        </select>
                    </span>
				</td>
				<td style="width: 5%"><button id="btnquery"
						class="btn btn-sm btn-primary pull-left" type="button">查询数据</button></td>
				<td></td>
			</tr>
		</tbody>
	</table>
	<!-- Datagrid -->
	<div id="datagrid" style="margin-left:20px;width: 96%">
	</div>

	<script type="text/javascript" src="/js/handler/channelbindchanneldata.js?t=20170505"></script>
</body>
</html>
