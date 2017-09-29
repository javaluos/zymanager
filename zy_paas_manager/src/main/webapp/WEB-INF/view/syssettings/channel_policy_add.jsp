<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 

<!DOCTYPE HTML>
<html>
<head>
<title>Paas运营管理平台</title>
<jsp:include page="../comm/plugin.jsp" />
</head>

<body>

	<!-- QueryForm -->
	<div class="col-sm-12" style="padding-bottom: -20px;margin-bottom: 10px;">
		<div class="ibox">
			<div class="ibox-title">
				<h5>
					系统设置<small>&nbsp;-->&nbsp;添加分流策略</small>
				</h5>
			</div>
			
			<div class="contentdv">
			      <div style="padding-left: 10px;">
                         <div class="form-group">
                             <label>分流策略名称：</label>
                             <div class="col-sm-3">
                                 <input id="policyName" name="policyName" class="form-control" type="text" value="${policyName}" maxlength="30px;" >
                             </div>
                             <button id="btnadd" onclick="btnadd()"
										class="btn btn-sm btn-primary pull-left"
										style="margin-right: 15px;"
										type="button">&nbsp;&nbsp;增加策略规则</button>
							<button id="btnback2"
										class="btn btn-sm btn-primary pull-left"
										type="button" style="margin-right: 20px;">返回</button>
                         </div>
                  </div>
             </div>          
		</div>	
	</div>
	
	
	
	 <div id="content">
	   <c:choose>
	     <c:when test="${not empty listRule }">
	         <c:forEach var="dt" items="${listRule}" varStatus="listrule">
	            <table border="1px;" id="table_${dt.ruleIndex}" style="width: 900px;height:630px;margin-left: 30px;margin-top:10px;border-color:#CCCCCC;">
		            <tr>
		               <td>
			               <label style="padding-left: 20px;margin-top: -290px;"><span id="ruleIndex_${dt.ruleIndex}">${dt.ruleIndex}</span>.策略规则：
			                   <a style="padding-left: 760px;" id="delete_${dt.ruleIndex}" onclick="deleteRules(${dt.ruleIndex})">删除</a> 
			                   <p/>
			               </label>
			               
			               <label style="padding-left: 40px;padding-right: 760px;">关键字：</label>
			               <div style="padding-left: 40px;">
			                  <textarea rows="4" cols="70" style="resize:none;"  name="keyword_${dt.ruleIndex}" id="keyword_${dt.ruleIndex}" onmouseleave="onMouseLeave(${dt.ruleIndex})">${dt.keyword}</textarea>
			                  <span style="color: red;">&nbsp;&nbsp;注:每个关键词之间用逗号(英文)隔开</span>
			               </div>
			               
			               <label style="padding-left: 40px;padding-right: 660px;">
			                                              号段：
			                  <input type="checkbox" name="mobileFlag_${dt.ruleIndex}" id="mobileFlagYD_${dt.ruleIndex}" <c:if test="${fn:contains(dt.mobiles, mobileYD)}" >checked="true"</c:if> value="YD" onclick="selectMobiles(${dt.ruleIndex})" style="vertical-align:-2px;">&nbsp; 移动
			                  <input type="checkbox" name="mobileFlag_${dt.ruleIndex}" id="mobileFlagLT_${dt.ruleIndex}" <c:if test="${fn:contains(dt.mobiles, mobileLT)}" >checked="true"</c:if> value="LT" onclick="selectMobiles(${dt.ruleIndex})" style="vertical-align:-2px;">&nbsp; 联通
			                  <input type="checkbox" name="mobileFlag_${dt.ruleIndex}" id="mobileFlagDX_${dt.ruleIndex}" <c:if test="${fn:contains(dt.mobiles, mobileDX)}" >checked="true"</c:if> value="DX" onclick="selectMobiles(${dt.ruleIndex})" style="vertical-align:-2px;">&nbsp; 电信
			                  <input type="hidden" id="policyId" value="${policyId}"/>
			                  <input type="hidden" id="ruleId_${dt.ruleIndex}" value="${dt.id}"/>
			               </label>
			               
			               <div class="form-group" style="padding-left: 40px;">
			                  <textarea rows="4" cols="70" name="mobiles" style="resize:none;" id="mobiles_${dt.ruleIndex}" onmouseleave="onMouseLeave(${dt.ruleIndex})">${dt.mobiles}</textarea>
			                  <span style="color: red;">&nbsp;&nbsp;注:每个号段之间用逗号(英文)隔开</span>
			               </div>
			               
			               <label style="padding-left: 20px;">分流到：</label>
			               <div>
			                  <select class="form-control m-b" id="channelType" style="width: 180px;">
			                    <option value="1"  selected="selected">通道组</option>
			                  </select>
			               </div>
			               
			               
			               <label style="padding-left: 40px;">移动：</label>
			               <div>
			                  <select class="form-control m-b" id="group_YD_${dt.ruleIndex}" onmouseleave="onMouseLeave(${dt.ruleIndex})" style="width: 180px;">
			                     <option value="-1"  selected="selected">请选择</option>
			                     <c:forEach var="group" items="${grouplist}" varStatus="groupIndex">
			                         <c:choose>
			                           <c:when test="${dt.groupYD==group.id}">
			                               <option value="${group.id}" selected="selected">${group.groupName}</option>
			                           </c:when>
			                           <c:otherwise>
			                               <option value="${group.id}">${group.groupName}</option>
			                           </c:otherwise>
			                         </c:choose>
			                     </c:forEach>
			                  </select>
			               </div>
			               
			               
			               <label style="padding-left: 40px;">联通：</label>
			               <div>
			                  <select class="form-control m-b" id="group_LT_${dt.ruleIndex}" onmouseleave="onMouseLeave(${dt.ruleIndex})" style="width: 180px;">
			                    <option value="-1"  selected="selected">请选择</option>
			                     <c:forEach var="group" items="${grouplist}" varStatus="groupIndex">
			                         <c:choose>
			                           <c:when test="${dt.groupLT==group.id}">
			                               <option value="${group.id}" selected="selected">${group.groupName}</option>
			                           </c:when>
			                           <c:otherwise>
			                               <option value="${group.id}">${group.groupName}</option>
			                           </c:otherwise>
			                         </c:choose>
			                     </c:forEach>
			                  </select>
			               </div>
			               
			               <label style="padding-left: 40px;">电信：</label>
			               <div>
			                  <select class="form-control m-b" id="group_DX_${dt.ruleIndex}" onmouseleave="onMouseLeave(${dt.ruleIndex})" style="width: 180px;">
			                     <option value="-1"  selected="selected">请选择</option>
			                     <c:forEach var="group" items="${grouplist}" varStatus="groupIndex">
			                         <c:choose>
			                           <c:when test="${dt.groupDX==group.id}">
			                               <option value="${group.id}" selected="selected">${group.groupName}</option>
			                           </c:when>
			                           <c:otherwise>
			                               <option value="${group.id}">${group.groupName}</option>
			                           </c:otherwise>
			                         </c:choose>
			                     </c:forEach>
			                  </select>
			               </div>
			               
			               <label style="padding-left: 20px;">
			                                                 当短信内容匹配【
			              	    <span id="content_${dt.ruleIndex}">
			              	    </span>
			               		】时，移动从【
			               		<span id="YD_${dt.ruleIndex}">
			               		</span>
			               		】、联通从【
			               		<span id="LT_${dt.ruleIndex}">
			               		</span>
			               		】、电信从【
			               		<span id="DX_${dt.ruleIndex}">
			               		</span>
			               		】中发送。
			               	</label>
		                </td>
		             </tr>
          		</table>
          		<div>
	              <input type="hidden" id="sendNums" value="${fn:length(listRule)}" />
	           </div>
	         </c:forEach>
	     </c:when>
	     
	     <c:otherwise>
	        <table border="1px;" id="table_1" style="width: 900px;height:630px;margin-left: 30px;border-color:#CCCCCC;">
	            <tr>
	               <td>
		               <label style="padding-left: 20px;margin-top: -290px;"><span id="ruleIndex_1">1</span>.策略规则：
		                   <a style="padding-left: 760px;" id="delete_1"  onclick="deleteRules(1)">删除</a> 
		                   <p/>
		               </label>
		               
		               <label style="padding-left: 40px;padding-right: 760px;">关键字：</label>
		               <div style="padding-left: 40px;">
		                  <textarea rows="4" cols="70" style="resize:none;" name="keyword_1" id="keyword_1" onmouseleave="onMouseLeave(1)"></textarea>
		                  <span style="color: red;">&nbsp;&nbsp;注:每个关键词之间用逗号(英文)隔开</span>
		               </div>
		               
		               <label style="padding-left: 40px;padding-right: 660px;">
		                                              号段：
		                  <input type="checkbox" name="mobileFlag_1" id="mobileFlagYD_1" value="YD" onclick="selectMobiles(1)" style="vertical-align:-2px;">&nbsp; 移动
		                  <input type="checkbox" name="mobileFlag_1" id="mobileFlagLT_1" value="LT" onclick="selectMobiles(1)" style="vertical-align:-2px;">&nbsp; 联通
		                  <input type="checkbox" name="mobileFlag_1" id="mobileFlagDX_1" value="DX" onclick="selectMobiles(1)" style="vertical-align:-2px;">&nbsp; 电信
		                  <input type="hidden" id="mobileYD" name="mobileYD" value="${mobileYD}"/>
		                  <input type="hidden" id="mobileLT" name="mobileLT" value="${mobileLT}"/>
		                  <input type="hidden" id="mobileDX" name="mobileDX" value="${mobileDX}"/>
		               </label>
		               
		               <div class="form-group" style="padding-left: 40px;">
		                  <textarea rows="4" cols="70" style="resize:none;" name="mobiles" id="mobiles_1" onmouseleave="onMouseLeave(1)"></textarea>
		                  <span style="color: red;">&nbsp;&nbsp;注:每个号段之间用逗号(英文)隔开</span>
		               </div>
		               
		               <label style="padding-left: 20px;">分流到：</label>
		               <div>
		                  <select class="form-control m-b" id="channelType" style="width: 180px;">
		                    <option value="1"  selected="selected">通道组</option>
		                  </select>
		               </div>
		               
		               
		               <label style="padding-left: 40px;">移动：</label>
		               <div>
		                  <select class="form-control m-b" id="group_YD_1" onmouseleave="onMouseLeave(1)" style="width: 180px;">
		                     <option value="-1"  selected="selected">请选择</option>
		                     <c:forEach var="group" items="${grouplist}" varStatus="groupIndex">
		                         <option value="${group.id}">${group.groupName}</option>
		                     </c:forEach>
		                  </select>
		               </div>
		               
		               
		               <label style="padding-left: 40px;">联通：</label>
		               <div>
		                  <select class="form-control m-b" id="group_LT_1" onmouseleave="onMouseLeave(1)" style="width: 180px;">
		                    <option value="-1"  selected="selected">请选择</option>
		                     <c:forEach var="group" items="${grouplist}" varStatus="groupIndex">
		                         <option value="${group.id}">${group.groupName}</option>
		                     </c:forEach>
		                  </select>
		               </div>
		               
		               <label style="padding-left: 40px;">电信：</label>
		               <div>
		                  <select class="form-control m-b" id="group_DX_1" onmouseleave="onMouseLeave(1)" style="width: 180px;">
		                    <option value="-1"  selected="selected">请选择</option>
		                     <c:forEach var="group" items="${grouplist}" varStatus="groupIndex">
		                         <option value="${group.id}">${group.groupName}</option>
		                     </c:forEach>
		                  </select>
		               </div>
		               
		               <label style="padding-left: 20px;">
		                                                 当短信内容匹配【
		              	    <span id="content_1"></span>
		               		】时，移动从【
		               		<span id="YD_1"></span>
		               		】、联通从【
		               		<span id="LT_1"></span>
		               		】、电信从【
		               		<span id="DX_1"></span>
		               		】中发送。
		               	</label>
	                </td>
	             </tr>
            </table>
            <div>
	           <input type="hidden" id="sendNums" value="1" />
	        </div>
	     </c:otherwise>
	   </c:choose>
     </div> 
     
     <div>
        <input type="hidden" id="mobileYD" name="mobileYD" value="${mobileYD}"/>
		<input type="hidden" id="mobileLT" name="mobileLT" value="${mobileLT}"/>
		<input type="hidden" id="mobileDX" name="mobileDX" value="${mobileDX}"/>
     </div>
     
     
     <c:choose>
	     <c:when test="${not empty listRule }">
	     	 <div>
		        <button id="btnback"
						class="btn btn-sm btn-primary pull-left"
						type="button" style="margin-left: 300px;margin-top: 20px;margin-bottom: 40px;">取消</button>
				<button id="policyedit"
						class="btn btn-sm btn-primary pull-left"
						type="button" style="margin-left: 80px;margin-top: 20px;margin-bottom: 40px;">保存</button>
	       </div>  
	     </c:when>
	     <c:otherwise>
	     	<div>
		        <button id="btnback"
						class="btn btn-sm btn-primary pull-left"
						type="button" style="margin-left: 300px;margin-top: 20px;margin-bottom: 40px;">取消</button>
				<button id="policysave"
						class="btn btn-sm btn-primary pull-left"
						type="button" style="margin-left: 80px;margin-top: 20px;margin-bottom: 40px;">保存</button>
	       </div>  
	     </c:otherwise>
	 </c:choose>
</body>
</html>

<script type="text/javascript" src="/js/handler/channelPolicyAdd.js"></script>
