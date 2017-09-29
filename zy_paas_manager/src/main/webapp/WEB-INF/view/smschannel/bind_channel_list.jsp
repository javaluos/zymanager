<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
					<th style="background: 0 0;min-width:50px;">序号</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">通道名称</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1">通道ID</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" style="min-width:80px;">通道类型</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" style="min-width:80px;">通道属性</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" style="min-width:80px;">落地省份</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" style="min-width:80px;">跑量省份</th>
					<th tabindex="0" aria-controls="DataTables_Table_0"
						rowspan="1" colspan="1" style="min-width:80px;">操作</th>
				</tr>
			</thead>
			<tbody>

				<c:if test="${not empty pgdata.data }">
					<c:forEach var="dt" items="${pgdata.data}" varStatus="dtindex">
						<tr class="gradeC odd">
							<td>${(pgdata.page_num-1)*pgdata.page_size + dtindex.index+1}</td>
							<td>${dt.channelName }</td>
							<td>${dt.channelId }</td>
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
                                    <c:when test="${dt.channelProperty==10}">
                                         移动
                                    </c:when>
                                    <c:when test="${dt.channelType==20}">
                                                  联通
                                    </c:when>
                                    <c:when test="${dt.channelType==30}">
                                                 电信
                                    </c:when>
                                    <c:otherwise>
                                     &nbsp;
                                  </c:otherwise>
                                </c:choose>
                            </td>
							<td>
								<c:choose>
									<c:when test="${dt.dtnProvince != ''}">
										${dt.dtnProvince }
									</c:when>
									<c:otherwise>
									</c:otherwise>
								</c:choose>
							</td>
							<td>
								<c:choose>
									<c:when test="${dt.useProvince == 0}">
										全国
									</c:when>
									<c:when test="${dt.useProvince == 1}">
										本省
									</c:when>
									<c:when test="${dt.useProvince == 2}">
										非本省
									</c:when>
									<c:otherwise>
									</c:otherwise>
								</c:choose>
							</td>
							<td>
							    <a onclick="doadd('${dt.channelId}','${dt.channelName }',${dt.channelType },'${dt.dtnProvince }','${dt.useProvince }');" >绑定</a>
							</td>		
						</tr>
					</c:forEach>

				</c:if>
				<c:if test="${empty pgdata.data }">
					<tr>
						<td colspan="13" style="text-align: center;">暂无数据.</td>
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
	function doadd(channelid, channelName, channelType, dtnProvince, useProvince){
		var channeltr = parent.$("#channelId tr").length;
		if(channeltr <= 0){
			var channelId = parent.$("#channelId");
			var mht = "<tr>";
			mht += "<th class='fr' style='width: 15%;'>通道名称</th>";
			mht += "<th class='fr' style='width: 15%;'>通道ID</th>";
			mht += "<th class='fr' style='width: 10%;'>通道类型</th>";
			mht += "<th class='fr' style='width: 5%;'>落地省份</th>";
			mht += "<th class='fr' style='width: 5%;'>跑量省份</th>";
			mht += "<th class='fr' style='width: 5%;'>评分</th>";
			mht += "<th class='fr' style='width: 10%;'>阀值(条)</th>";
			mht += "<th class='fr' style='width: 5%;'>操作类型</th>";
			mht += "<th class='fr' style='width: 5%;'>操作</th>";
			mht += "<th></th>";
			mht += "</tr>";
			
			mht +="<tr id='"+channelid+"' class='noline'>";
			mht +="<td class='fr'>"+channelName+"</td>";
			mht +="<td class='fr'>"+channelid+"</td>";
			if(channelType == 1){
				mht +="<td class='fr'>通知</td>";		
			}
			if(channelType == 2){
				mht +="<td class='fr'>验证码</td>";		
			}
			if(channelType == 3){
				mht +="<td class='fr'>营销</td>";		
			}
			if(channelType == 4){
				mht +="<td class='fr'>通知、验证码</td>";		
			}
			if(channelType == 5){
				mht +="<td class='fr'>通知、验证码、营销</td>";		
			}
			mht +="<td class='fr'>"+dtnProvince+"</td>";
			if(useProvince == 0){
				mht +="<td class='fr'>全国</td>";
			}else if(useProvince == 1){
				mht +="<td class='fr'>本省</td>";
			}else if(useProvince == 2){
				mht +="<td class='fr'>非本省</td>";
			}else{
				mht +="<td class='fr'></td>";
			}
			mht +="<td><input type='number' name='score' id='score' maxlength='3' size='3' style='width:60px' oninput='if(value.length>3)value=value.slice(0,3)' /></td>";
			mht +="<td><input type='number' name='thresholdValue' id='thresholdValue' maxlength='9' size='9' oninput='if(value.length>9)value=value.slice(0,9)' style='width:120px' /></td>";
			mht +="<td class='fr'>新增</td>";
			mht +="<td> <a onclick=delchanneltr('"+channelid+"');>删除</a></td></tr>";
			channelId.append(mht);
		}else{
			var channelclick = parent.$("#"+channelid+"").length;
			if(channelclick <= 0){
				var channelId = parent.$("#channelId");
				mht +="<tr id='"+channelid+"' class='noline'>";
				mht +="<td class='fr'>"+channelName+"</td>";
				mht +="<td class='fr'>"+channelid+"</td>";
				if(channelType == 1){
					mht +="<td class='fr'>通知</td>";		
				}
				if(channelType == 2){
					mht +="<td class='fr'>验证码</td>";		
				}
				if(channelType == 3){
					mht +="<td class='fr'>营销</td>";		
				}
				if(channelType == 4){
					mht +="<td class='fr'>通知、验证码</td>";		
				}
				if(channelType == 5){
					mht +="<td class='fr'>通知、验证码、营销</td>";		
				}
				mht +="<td class='fr'>"+dtnProvince+"</td>";
				if(useProvince == 0){
					mht +="<td class='fr'>全国</td>";
				}else if(useProvince == 1){
					mht +="<td class='fr'>本省</td>";
				}else if(useProvince == 2){
					mht +="<td class='fr'>非本省</td>";
				}else{
					mht +="<td class='fr'></td>";
				}
				mht +="<td><input type='number' name='score' id='score' maxlength='3' size='3' oninput='if(value.length>3)value=value.slice(0,3)' style='width:60px' /></td>";
				mht +="<td><input type='number' name='thresholdValue' id='thresholdValue' maxlength='9' oninput='if(value.length>9)value=value.slice(0,9)' style='width:120px' /></td>";
				mht +="<td class='fr'>新增</td>";
				mht +="<td> <a onclick=delchanneltr('"+channelid+"');>删除</a></td></tr>";
				channelId.append(mht);
			}
		}
	}
	
	function getchannellist(){
		var channelname = $("#channelname").val();
		var channelid = $("#channelid").val();
		window.location.href = '/channel_bind/channel_list_data?channelname='+channelname+'&channelid='+channelid+'';
	}	
</script>
