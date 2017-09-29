<%@page import="org.springframework.context.annotation.Import"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page isELIgnored ="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/tags" prefix="date"%>

<div class="panel admin-panel" style="margin-top: 10px;">

<table class="table table-hover">
	<tbody>
	<tr>
		<th width="80" style="text-align: center;">序号</th>
		<th width="160" style="text-align: center;">呼叫时间</th>
		<th width="80" style="text-align: center;">主叫号码</th>
		<th width="80" style="text-align: center;">被叫号码</th>
		<th width="80" style="text-align: center;">通话状态</th>
		<th width="100" style="text-align: center;">通话时长(秒)</th>
		<th width="70" style="text-align: center;">费用(元)</th>
		<th width="100" style="text-align: center;">通话结束原因</th>
	</tr>
	<c:if test="${not empty pgdata.data }">
		<c:forEach var="cdr" items="${pgdata.data}" varStatus="dtindex"> 
		<tr>
			<td>${(pgdata.page_num-1)*pgdata.page_size + dtindex.index+1}</td>
			<td>
				<date:date value="${cdr.calleeInviteTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
			<td>
				${cdr.caller}
			</td>
		    <td>
				${cdr.callee}
			</td>
			<td>
				<c:choose> 
				  <c:when test="${cdr.state==0}">   
				                正常通话
				  </c:when> 
				  <c:when test="${cdr.state==1}">   
				                被叫未接
				  </c:when>
				  <c:when test="${cdr.state==2}">   
				                被叫拒接
				  </c:when>
				  <c:when test="${cdr.state==3}">   
				                呼叫失败
				  </c:when>
				  <c:otherwise>   
				     &nbsp;
				  </c:otherwise> 
				</c:choose> 
			</td>
			<td>
				${cdr.holdTime}
			</td>
			<td>
				${cdr.fee/10000}
			</td>
			<td>
			<c:choose> 
				  <c:when test="${cdr.hangupCode==1}">   
				              主叫挂断
				  </c:when> 
				  <c:when test="${cdr.hangupCode==2}">   
				               被叫挂断
				  </c:when>
				  <c:when test="${cdr.hangupCode==3}">   
				                主叫取消
				  </c:when>
				  <c:when test="${cdr.hangupCode==4}">   
				                被叫无人接听
				  </c:when>
				  <c:when test="${cdr.hangupCode==5}">   
				                无法接通
				  </c:when>
				  <c:when test="${cdr.hangupCode==8}">   
				                被叫拒接
				  </c:when>
				  <c:when test="${cdr.hangupCode==9}">   
				              被叫不可用
				  </c:when>
				  <c:when test="${cdr.hangupCode==155}">   
				             未知原因
				  </c:when>
				  <c:otherwise>   
				     &nbsp;         
				  </c:otherwise> 
				</c:choose> 
			    
			</td>
		</tr>
		</c:forEach>
	</c:if>
	<c:if test="${empty pgdata.data }">
	   <tr>
	     <td colspan="8" style="text-align: center;">暂无数据.</td>
	   </tr>
	</c:if>
		 
	</tbody>
	</table>
	<div class="panel-foot text-center pagereq">
		<ul class="pagination">
			<li style="border: none;">&nbsp;共计 <span style="color:red;">${pgdata.total}</span> 条记录</li>
			<li style="border: none;">&nbsp;每页 <span style="color:red;">${pgdata.page_size}</span> 条</li>
			<li style="border: none;">&nbsp;<span style="color:red;">${pgdata.page_num}</span> / <span style="color:red;" id="maxpg">${pgdata.totl_page}</span> 页&nbsp;</li>
		</ul>
		<ul class="pagination">
		    <c:if test="${pgdata.page_num > 0}">
			  <li><a href="javascript:;" _pgnum="${pgdata.page_num-1 }">上一页</a></li>
			</c:if>
			<c:if test="${pgdata.page_num <= 0}">
			 <li><a href="javascript:;" _pgnum="1">上一页</a></li>
			</c:if>
		</ul>
		<ul class="pagination pagination-group ">
			<c:forEach var="item" begin="1" end="${pgdata.viewcount }" step="1" varStatus="flag">
				<li <c:if test="${pgdata.page_num == (pgdata.pgstartno + flag.index)}">class="active"</c:if>>
				    <a href="javascript:;" _pgnum="${pgdata.pgstartno + flag.index}">${pgdata.pgstartno + flag.index}</a>
				</li>
			</c:forEach> 
		</ul>
		<ul class="pagination"> 
		   <c:if test="${pgdata.page_num<pgdata.totl_page}">
			  <li><a href="javascript:;"_pgnum="${pgdata.page_num+1 }">下一页</a></li>
			</c:if>
			<c:if test="${pgdata.page_num>=pgdata.totl_page}">
			  <li><a href="javascript:;"_pgnum="${pgdata.totl_page }">下一页</a></li>
			</c:if>
		</ul>
		<ul class="pagination">
			<li><input type="text" class="input"  id="pageNum" style="width:60px;display: inline-block;" value="${pgdata.page_num}"
			      onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" ></li>
			<li><button type="button" class="button bg-main" id="pggobtn"
					         style="text-align: center; vertical-align: -2px;">GO</button>
		    </li>   
		</ul>
	</div>
</div>
<script type="text/javascript">
/**
 * 提交查询-按页下标查询
 */
$('.pagereq>ul a').on("click",function(){	  
	 var pagenum = $(this).attr('_pgnum');
	 var maxpg = $('#maxpg').text();
	 if(parseInt(pagenum) > parseInt(maxpg)){pagenum = maxpg;};
	 loadalysisreq(pagenum);//加载查询
});

/**
 * 提交查询-跳转页查询
 */
$('#pggobtn').on("click",function(){
	 var pagenum = $('#pageNum').val();
	 var maxpg = $('#maxpg').text();
	 if(parseInt(pagenum) > parseInt(maxpg)){pagenum = maxpg;};
	 loadalysisreq(pagenum);//加载查询
});
</script>