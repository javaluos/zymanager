<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@  taglib  uri="http://java.sun.com/jsp/jstl/functions"   prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>修改</title>
	<jsp:include page="../comm/plugin.jsp" />
	<script type="text/javascript">
	var count=0;
	function checkMony2(value){
		if(value){
			if(!/^[0-9]*(\.[0-9]{0,4})?$/.test(value)){  
		       $("#monitorMinBalanceYuanTip").html("<font color='red'>请输入正确金额!</font>");
		       count++;
		    }else{
		    	$("#monitorMinBalanceYuanTip").empty();
// 		    	$("#monitorMinBalanceYuan").val(1);
		    }
		}else{
			$("#monitorMinBalanceYuanTip").empty();
// 			$("#monitorMinBalanceYuan").val(1);
		}
	}
	function checkTime(value,i){
		if(value){
			if(!/^([01][0-9]|2[0-3]):[0-6][0-9]*$/.test(value)){  
		       $("#noticeTimeTip"+i).html("<font color='red'>请输入正确时分如00:00!</font>");
		       count++;
		    }else{
		    	$("#noticeTimeTip"+i).empty();
		    }
		}else{
			$("#noticeTimeTip"+i).empty();
		}
	}
	function checkPhone(value,i){ 
		if(value){
		    if(!(/^(1[34578]\d{9}[,]*)+$/.test(value))){ 
		    	  $("#noticePhoneTip"+i).html("<font color='red'>请输入正确手机号码!</font>");
		    	  count++;
		    }else{
		    	$("#noticePhoneTip"+i).empty();
		    } 
		}else{
			$("#noticePhoneTip"+i).empty();
		}
	}
	function checkEmail(value,i){ 
		if(value){
		    if(!(/^([a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+[,]*)+$/.test(value))){
		    	  $("#noticeEmailTip"+i).html("<font color='red'>请输入正确邮箱!</font>");
		    	  count++;
		    }else{
		    	  $("#noticeEmailTip"+i).empty();
		    } 
		}else{
			 $("#noticeEmailTip"+i).empty();
		}
	}
	
	function beforeSubmit(){
		count=0;
	    checkMony2($("#monitorMinBalanceYuan").val());
	    checkTime($("#noticeTimeRangeStart1").val(),1);
   		checkTime($("#noticeTimeRangeEnd1").val(),1);
   	    checkTime($("#noticeTimeRangeStart2").val(),2);
   		checkTime($("#noticeTimeRangeEnd2").val(),2);
   		checkTime($("#noticeTimeRangeStart3").val(),3);
   		checkTime($("#noticeTimeRangeEnd3").val(),3);
   		checkPhone($("#noticePhone1").val(),1);
   	    checkPhone($("#noticePhone2").val(),2);
   		checkPhone($("#noticePhone3").val(),3);
   		checkEmail($("#noticeEmail1").val(),1);
	    checkEmail($("#noticeEmail2").val(),2);
		checkEmail($("#noticeEmail3").val(),3);
// 		console.info(count);
	    if(count==0){
	    	return true;
	    }else{
     		return false;
	    }																		
	}
	</script>
</head>

<body class="right_body">
<form id="myform" name="myform" method="post" action="/monitor/update" onsubmit="return beforeSubmit();">
    <input type="hidden" name="sid"  value="${sid }" />
	<div class="public_div" align="center">
		<div class="public_inner">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table   table-hover  dataTable">
				<tr>
					<td class="text_r">客户</td>
					<td  colspan="3">${voiceMerchantBalanceMonitor.businessName}</td>
					</tr>
					<tr>
					<td class="text_r">余额</td>
					<td><fmt:formatNumber type="number" value="${voiceMerchantBalanceMonitor.balance/10000 }" pattern="0.00" maxFractionDigits="2"/>元</td>
					<td class="text_r">报警下限：</td>
					<td>
					<input id="monitorMinBalanceYuan" name="monitorMinBalanceYuan" value="${voiceMerchantBalanceMonitor.monitorMinBalanceYuan}" onblur="checkMony2(this.value)">元
					<span id="monitorMinBalanceYuanTip"></span>
					</td>
					</tr>
					<tr>
					<td class="text_r">报警时段设置1</td>
					<td>
					 <input id="noticeTimeRangeStart1" name="noticeTimeRangeStart1" value="${voiceMerchantBalanceMonitor.noticeTimeRangeStart1}" size="5" onblur="checkTime(this.value,1)">至<input id="noticeTimeRangeEnd1" name="noticeTimeRangeEnd1" value="${voiceMerchantBalanceMonitor.noticeTimeRangeEnd1}" onblur="checkTime(this.value,1)" size="5">
					 <span id="noticeTimeTip1"></span>
					</td>
					<td class="text_r">报警方式</td>
					<td>
					 <input type="checkbox" name="noticeWay1" value="0" <c:if test="${voiceMerchantBalanceMonitor.noticeWay1!=null}">${fn:contains(voiceMerchantBalanceMonitor.noticeWay1,0)?'checked':''}</c:if>>短信通知
					 <input type="checkbox" name="noticeWay1" value="1" <c:if test="${voiceMerchantBalanceMonitor.noticeWay1!=null}">${fn:contains(voiceMerchantBalanceMonitor.noticeWay1,1)?'checked':''}</c:if>>邮件通知
					 <input type="checkbox" name="noticeWay1" value="2" <c:if test="${voiceMerchantBalanceMonitor.noticeWay1!=null}">${fn:contains(voiceMerchantBalanceMonitor.noticeWay1,2)?'checked':''}</c:if>>语音通知
					</td>
					</tr>
					<tr>
					<td class="text_r">通知人手机号</td>
					<td>
					<textarea id="noticePhone1" cols="25" rows="4" name="noticePhone1" onblur="checkPhone(this.value,1)">${voiceMerchantBalanceMonitor.noticePhone1}</textarea>
					<span id="noticePhoneTip1"></span>
					</td>
					<td class="text_r">通知人邮箱</td>
					<td>
					<textarea id="noticeEmail1" cols="25" rows="4" name="noticeEmail1" onblur="checkEmail(this.value,1)">${voiceMerchantBalanceMonitor.noticeEmail1}</textarea>
					<span id="noticeEmailTip1"></span>
					</td>
					</tr>
					<tr>
					<td class="text_r">报警时段设置2</td>
					<td>
					 <input id="noticeTimeRangeStart2" name="noticeTimeRangeStart2" value="${voiceMerchantBalanceMonitor.noticeTimeRangeStart2}" size="5" onblur="checkTime(this.value,2)">至<input id="noticeTimeRangeEnd2" name="noticeTimeRangeEnd2" value="${voiceMerchantBalanceMonitor.noticeTimeRangeEnd2}" onblur="checkTime(this.value,2)" size="5">
					<span id="noticeTimeTip2"></span>
					</td>
					<td class="text_r">报警方式</td>
					<td>
					 <input type="checkbox" name="noticeWay2" value="0" <c:if test="${voiceMerchantBalanceMonitor.noticeWay2!=null}">${fn:contains(voiceMerchantBalanceMonitor.noticeWay2,0)?'checked':''}</c:if>>短信通知
					 <input type="checkbox" name="noticeWay2" value="1" <c:if test="${voiceMerchantBalanceMonitor.noticeWay2!=null}">${fn:contains(voiceMerchantBalanceMonitor.noticeWay2,1)?'checked':''}</c:if>>邮件通知
					 <input type="checkbox" name="noticeWay2" value="2" <c:if test="${voiceMerchantBalanceMonitor.noticeWay2!=null}">${fn:contains(voiceMerchantBalanceMonitor.noticeWay2,2)?'checked':''}</c:if>>语音通知
					</td>
					</tr><tr>
					<td class="text_r">通知人手机号</td>
					<td>
					<textarea id="noticePhone2" cols="25" rows="4" name="noticePhone2" onblur="checkPhone(this.value,2)">${voiceMerchantBalanceMonitor.noticePhone2}</textarea>
					<span id="noticePhoneTip2"></span>
					</td>
					<td class="text_r">通知人邮箱</td>
					<td>
					<textarea id="noticeEmail2" cols="25" rows="4" name="noticeEmail2" onblur="checkEmail(this.value,2)">${voiceMerchantBalanceMonitor.noticeEmail2}</textarea>
					<span id="noticeEmailTip2"></span>
					</td>
					</tr><tr>
					<td class="text_r">报警时段设置3</td>
					<td>
					 <input id="noticeTimeRangeStart3" name="noticeTimeRangeStart3" value="${voiceMerchantBalanceMonitor.noticeTimeRangeStart3}" onblur="checkTime(this.value,3)" size="5">至<input id="noticeTimeRangeEnd3" name="noticeTimeRangeEnd3" value="${voiceMerchantBalanceMonitor.noticeTimeRangeEnd3}" onblur="checkTime(this.value,3)" size="5">
					<span id="noticeTimeTip3"></span>
					</td>
					
					<td class="text_r">报警方式
					</td>
					<td>
					 <input type="checkbox" name="noticeWay3" value="0" <c:if test="${voiceMerchantBalanceMonitor.noticeWay3!=null}">${fn:contains(voiceMerchantBalanceMonitor.noticeWay3,0)?'checked':''}</c:if>>短信通知
					 <input type="checkbox" name="noticeWay3" value="1" <c:if test="${voiceMerchantBalanceMonitor.noticeWay3!=null}">${fn:contains(voiceMerchantBalanceMonitor.noticeWay3,1)?'checked':''}</c:if>>邮件通知
					 <input type="checkbox" name="noticeWay3" value="2" <c:if test="${voiceMerchantBalanceMonitor.noticeWay3!=null}">${fn:contains(voiceMerchantBalanceMonitor.noticeWay3,2)?'checked':''}</c:if>>语音通知
					</td>
					</tr><tr>
					<td class="text_r">通知人手机号</td>
					<td>
					<textarea id="noticePhone3" cols="25" rows="4" name="noticePhone3" onblur="checkPhone(this.value,3)">${voiceMerchantBalanceMonitor.noticePhone3}</textarea>
					<span id="noticePhoneTip3"></span>
					</td>
					<td class="text_r">通知人邮箱</td>
					<td>
					<textarea cols="25" rows="4" id="noticeEmail3" name="noticeEmail3" onblur="checkEmail(this.value,3)">${voiceMerchantBalanceMonitor.noticeEmail3}</textarea>
					<span id="noticeEmailTip3"></span>
					<input type="hidden" name="merchantAccount" value="${voiceMerchantBalanceMonitor.merchantAccount}">
					<input type="hidden" name="apiAccount" value="${voiceMerchantBalanceMonitor.apiAccount}">
					<input type="hidden" name="id" value="${voiceMerchantBalanceMonitor.id}">
					</td>
				</tr>
			</table>
		</div>
	</div>
	
	<div class="clearfix" align="center">
	    <br>
		<input class="btn btn-sm btn-primary pull-center" name="save" type="submit" value="保存" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input  class="btn btn-sm btn-primary pull-center" name="back" type="button" value="返回" onclick="history.go(-1)" />
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</div>
	
</form>
</body>
</html>