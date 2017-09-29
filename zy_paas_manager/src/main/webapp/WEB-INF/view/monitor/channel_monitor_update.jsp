<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	
	function editMonitor(flag){
		var id=$("#id").val();
		var channelId=$("#channelId").val();
		var channelName=$("#channelName").val();
		
		if(1==flag||2==flag||3==flag){
			var noticeTimeRange= $("#noticeTimeRange"+flag).val();
			var successRateDown=$("#successRateDown"+flag).val();
			var failtRateUp=$("#failtRateUp"+flag).val();
			var unknownRateUp=$("#unknownRateUp"+flag).val();
			var averageSendUp=$("#averageSendUp"+flag).val();
			var averageReveiveUp=$("#averageReveiveUp"+flag).val();
			var sendCount=$("#sendCount"+flag).val();
			
			
			if(noticeTimeRange.trim() == ''){
				$("#noticeTimeRange"+flag).focus();
				layer.tips('请输入报警时间.', $("#noticeTimeRange"+flag));
				return false;
			}
			var timereg = /^(?:[01]\d|2[0-3])(?::[0-5]\d)$/;
			var head = noticeTimeRange.substring(0,5);
			var middle = noticeTimeRange.substring(5,6);
			var tail = noticeTimeRange.substring(6);
			if(timereg.test(head) == false || middle != '~' || timereg.test(tail) == false){
				$("#noticeTimeRange1").focus();
				layer.tips('警报时间格式为HH:mm~HH:mm.',$("#noticeTimeRange1"));
				return false;
			}
			var reg = /^(0|[1-9]|[1-9]\d|100)(\.\d{1,2}|\.{0})$/;
			var regNum = /^(0|[1-9][0-9]*)$/;
			if(undefined != sendCount && sendCount.trim() != '' && regNum.test(sendCount) == false){
				$("#sendCount"+flag).focus();
				layer.tips('开始报警条数只能为数字.', $("#sendCount"+flag));
				return false;
			}
			
			if(undefined != successRateDown && successRateDown.trim() != '' && reg.test(successRateDown) == false){
				$("#successRateDown"+flag).focus();
				layer.tips('成功率(下限)只能为0-100间的数字（最多两位小数）.', $("#successRateDown"+flag));
				return false;
			}
			if(undefined != failtRateUp && failtRateUp.trim() != '' && reg.test(failtRateUp) == false){
				$("#failtRateUp"+flag).focus();
				layer.tips('失败率(上限)只能为0-100间的数字（最多两位小数）.', $("#failtRateUp"+flag));
				return false;
			}
			if(undefined != unknownRateUp && unknownRateUp.trim() != '' && reg.test(unknownRateUp) == false){
				$("#unknownRateUp"+flag).focus();
				layer.tips('未知率(上限)只能为0-100间的数字（最多两位小数）.', $("#unknownRateUp"+flag));
				return false;
			}
			if(undefined != averageSendUp && averageSendUp.trim() != '' && regNum.test(averageSendUp) == false){
				$("#averageSendUp"+flag).focus();
				layer.tips('平均发送时长（秒/上限）只能为数字.', $("#averageSendUp"+flag));
				return false;
			}
			if(undefined != averageReveiveUp && averageReveiveUp.trim() != '' && regNum.test(averageReveiveUp) == false){
				$("#averageReveiveUp"+flag).focus();
				layer.tips('平均状态报告时长（秒/上限）只能为数字.', $("#averageReveiveUp"+flag));
				return false;
			}
		}
			
		if(1==flag){
			var noticeTimeRange1= $("#noticeTimeRange1").val();
			var successRateDown1=$("#successRateDown1").val();
			var failtRateUp1=$("#failtRateUp1").val();
			var unknownRateUp1=$("#unknownRateUp1").val();
			var averageSendUp1=$("#averageSendUp1").val();
			var averageReveiveUp1=$("#averageReveiveUp1").val();
			var sendCount1=$("#sendCount1").val();
		    $.ajax({
			   async:false,
			   type: 'POST',//提交方式
               dataType: 'json',//类型
			   url: '/channel_monitor/update',
			   data: {'flag': flag,'id': id,'channelName': channelName,'smsChannelId': channelId,'sendCount1': sendCount1,'noticeTimeRange1': noticeTimeRange1, 'successRateDown1':successRateDown1,'failtRateUp1':failtRateUp1,'unknownRateUp1':unknownRateUp1,'averageSendUp1':averageSendUp1,'averageReveiveUp1':averageReveiveUp1},
			   success: function(data){
                   if(data ==1){
                	   if(id){
                	   }else{
                		   backToList();
                	   }
                   	//success
                    }else if(data== 0){
                   	//failt
				 }		 
			   },
			   error: function(msg){
				  //error
			   }
		 });		
		}
		if(2==flag){
			var noticeTimeRange2= $("#noticeTimeRange2").val();
			var successRateDown2=$("#successRateDown2").val();
			var failtRateUp2=$("#failtRateUp2").val();
			var unknownRateUp2=$("#unknownRateUp2").val();
			var averageSendUp2=$("#averageSendUp2").val();
			var averageReveiveUp2=$("#averageReveiveUp2").val();
			var sendCount2=$("#sendCount2").val();
			$.ajax({
				   async:false,
				   type: 'POST',//提交方式
	               dataType: 'json',//类型
				   url: '/channel_monitor/update',
				   data: {'flag': flag,'id': id,'channelName': channelName,'smsChannelId': channelId,'sendCount2': sendCount2,'noticeTimeRange2': noticeTimeRange2, 'successRateDown2':successRateDown2,'failtRateUp2':failtRateUp2,'unknownRateUp2':unknownRateUp2,'averageSendUp2':averageSendUp2,'averageReveiveUp2':averageReveiveUp2},
				   success: function(data){
                    if(data ==1){
                    	 if(id){
                  	   }else{
                  		   backToList();
                  	   }
                     }else if(data== 0){
                    	//failt
					 }		 
				   },
				   error: function(msg){
					  //error
				   }
			 });	
		}
		if(3==flag){
			var noticeTimeRange3= $("#noticeTimeRange3").val();
			var successRateDown3=$("#successRateDown3").val();
			var failtRateUp3=$("#failtRateUp3").val();
			var unknownRateUp3=$("#unknownRateUp3").val();
			var averageSendUp3=$("#averageSendUp3").val();
			var averageReveiveUp3=$("#averageReveiveUp3").val();
			var sendCount3=$("#sendCount3").val();
			$.ajax({
				   async:false,
				   type: 'POST',//提交方式
	               dataType: 'json',//类型
				   url: '/channel_monitor/update',
				   data: {'flag': flag,'id': id,'channelName': channelName,'smsChannelId': channelId,'sendCount3': sendCount3,'noticeTimeRange3': noticeTimeRange3, 'successRateDown3':successRateDown3,'failtRateUp3':failtRateUp3,'unknownRateUp3':unknownRateUp3,'averageSendUp3':averageSendUp3,'averageReveiveUp3':averageReveiveUp3},
				   success: function(data){
				   	if(data ==1){
					   	 if(id){
		              	   }else{
		              		   backToList();
		              	   }
                     }else if(data== 0){
                    	//failt
					 }		 
				   },
				   error: function(msg){
					  //error
				   }
			 });	
		}
	}
	function delMonitor(flag){
		var id=$("#id").val();
		if(1==flag||2==flag||3==flag){
			$.ajax({
				   async:false,
				   type: 'POST',//提交方式
	               dataType: 'json',//类型
				   url: '/channel_monitor/del',
				   data: {'id': id,'flag': flag},
				   success: function(data){
                    if(data ==1){
                    	 $("#noticeTimeRange"+flag).val("");
            			$("#successRateDown"+flag).val("");
            			$("#failtRateUp"+flag).val("");
            			$("#unknownRateUp"+flag).val("");
            			$("#averageSendUp"+flag).val("");
            			$("#averageReveiveUp"+flag).val("");
            			$("#sendCount"+flag).val("");
                     }else if(data== 0){
                    	//failt
					 }		 
				   },
				   error: function(msg){
					  //error
				   }
			 });		
		}
		
	}
	
	function backToList(){
		//history.go(-1)
		var channelId=$("#channelId").val();
		
		var preChannelId=$("#preChannelId").val();
		var preChannelName=$("#preChannelName").val();
		var preStartFlag=$("#preStartFlag").val();
		
		var subffix="";
		if(!!preChannelId){
			subffix +="&preChannelId="+preChannelId;
		}
		if(!!preChannelName){
			subffix +="&preChannelName="+encodeURI(encodeURI(preChannelName));
		}
		if(!!preStartFlag){
			subffix +="&preStartFlag="+preStartFlag;
		}
		window.location.href="/channel_monitor/list?channelId="+subffix;//+channelId
	}
	</script>
</head>

<body class="right_body">
	<div class="public_div" align="center">
		<br>
		<div class="search">
			  <div style="float: left;font-weight: bold;">   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 通道名称：       ${smsChannelMonitor.channelName }    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;             通道ID：    ${smsChannelMonitor.channelId }   </div>
			  	<input type="hidden" id="id" value="${smsChannelMonitor.id }">
			  	<input type="hidden" id="channelId" value="${smsChannelMonitor.channelId }">
			  	<input type="hidden" id="channelName" value="${smsChannelMonitor.channelName }">
			  	<input type="hidden" id="preChannelId" value="${preChannelId}">
			  	<input type="hidden" id="preChannelName" value="${preChannelName}">
			  	<input type="hidden" id="preStartFlag" value="${preStartFlag}">
			   <div style="float: right;width: 12%"><input  class="btn btn-sm btn-primary pull-left" name="back" type="button" value="&nbsp;&nbsp;&nbsp;返回   &nbsp;&nbsp;&nbsp; " onclick="backToList()" /></div>
		</div>
		<br>
		<br>
		<div class="public_inner">
			<table width="100%" border="0" cellspacing="1" cellpadding="0" class="table table-striped table-bordered table-hover dataTables-example dataTable" aria-describedby="DataTables_Table_0_info">
				   <tr>
					<th>序号</th>
					<th>报警时间</th>
					<th>开始报警条数</th>
					<th>成功率下限</th>
					<th>失败率上限</th>
					<th>未知率上限</th>
					<th>平均发送时长（秒/上限）</th>
					<th>平均状态报告时长（秒/上限）</th>
					<th>操作</th>
					</tr>
					<tr>
					<td class="text_r">1</td>
					<td class="text_r"><input id="noticeTimeRange1" style='border-left:0px;border-top:0px;border-right:0px;border-bottom:0px ;line-height: 30px' value="${smsChannelMonitor.noticeTimeRange1}"></td>
					<td class="text_r"><input id="sendCount1" style='border-left:0px;border-top:0px;border-right:0px;border-bottom:0px ;line-height: 30px' value="${smsChannelMonitor.sendCount1}"></td>
					<td class="text_r"><input id="successRateDown1" style='border-left:0px;border-top:0px;border-right:0px;border-bottom:0px;line-height: 30px' ' value="${smsChannelMonitor.successRateDown1}">%</td>
					<td class="text_r"><input id="failtRateUp1" style='border-left:0px;border-top:0px;border-right:0px;border-bottom:0px;line-height: 30px ' value="${smsChannelMonitor.failtRateUp1}">%</td>
					<td class="text_r"><input id="unknownRateUp1" style='border-left:0px;border-top:0px;border-right:0px;border-bottom:0px;line-height: 30px ' value="${smsChannelMonitor.unknownRateUp1}">%</td>
					<td class="text_r"><input id="averageSendUp1" style='border-left:0px;border-top:0px;border-right:0px;border-bottom:0px;line-height: 30px ' value="${smsChannelMonitor.averageSendUp1}"></td>
					<td class="text_r"><input id="averageReveiveUp1" style='border-left:0px;border-top:0px;border-right:0px;border-bottom:0px;line-height: 30px ' value="${smsChannelMonitor.averageReveiveUp1}"></td>
					<td class="text_r"><input class="btn btn-sm btn-primary" type="button" value="修改" onclick="editMonitor(1)"> &nbsp;&nbsp;<input onclick="delMonitor(1)" class="btn btn-sm btn-primary" type="button" value="删除"></td>
					</tr>
					
					<tr>
					<td class="text_r">2</td>
					<td class="text_r"><input id="noticeTimeRange2" value="${smsChannelMonitor.noticeTimeRange2}" style='border-left:0px;border-top:0px;border-right:0px;border-bottom:0px ;line-height: 30px'></td>
					<td class="text_r"><input id="sendCount2" style='border-left:0px;border-top:0px;border-right:0px;border-bottom:0px ;line-height: 30px' value="${smsChannelMonitor.sendCount2}"></td>
					<td class="text_r"><input id="successRateDown2" value="${smsChannelMonitor.successRateDown2}" style='border-left:0px;border-top:0px;border-right:0px;border-bottom:0px;line-height: 30px'>%</td>
					<td class="text_r"><input id="failtRateUp2" value="${smsChannelMonitor.failtRateUp2}" style='border-left:0px;border-top:0px;border-right:0px;border-bottom:0px;line-height: 30px'>%</td>
					<td class="text_r"><input id="unknownRateUp2" value="${smsChannelMonitor.unknownRateUp2}" style='border-left:0px;border-top:0px;border-right:0px;border-bottom:0px;line-height: 30px'>%</td>
					<td class="text_r"><input id="averageSendUp2" value="${smsChannelMonitor.averageSendUp2}"  style='border-left:0px;border-top:0px;border-right:0px;border-bottom:0px;line-height: 30px'></td>
					<td class="text_r"><input id="averageReveiveUp2" value="${smsChannelMonitor.averageReveiveUp2}"  style='border-left:0px;border-top:0px;border-right:0px;border-bottom:0px;line-height: 30px'></td>
					<td class="text_r"><input class="btn btn-sm btn-primary" type="button" value="修改" onclick="editMonitor(2)"> &nbsp;&nbsp;<input onclick="delMonitor(2)" class="btn btn-sm btn-primary" type="button" value="删除"></td>
					</tr>
					<tr>
					<td class="text_r">3</td>
					<td class="text_r"><input id="noticeTimeRange3" value="${smsChannelMonitor.noticeTimeRange3}" style='border-left:0px;border-top:0px;border-right:0px;border-bottom:0px ;line-height: 30px'></td>
					<td class="text_r"><input id="sendCount3" style='border-left:0px;border-top:0px;border-right:0px;border-bottom:0px ;line-height: 30px' value="${smsChannelMonitor.sendCount3}"></td>
					<td class="text_r"><input id="successRateDown3" value="${smsChannelMonitor.successRateDown3}" style='border-left:0px;border-top:0px;border-right:0px;border-bottom:0px;line-height: 30px'>%</td>
					<td class="text_r"><input id="failtRateUp3" value="${smsChannelMonitor.failtRateUp3}" style='border-left:0px;border-top:0px;border-right:0px;border-bottom:0px;line-height: 30px'>%</td>
					<td class="text_r"><input id="unknownRateUp3" value="${smsChannelMonitor.unknownRateUp3}" style='border-left:0px;border-top:0px;border-right:0px;border-bottom:0px;line-height: 30px'>%</td>
					<td class="text_r"><input id="averageSendUp3" value="${smsChannelMonitor.averageSendUp3}"  style='border-left:0px;border-top:0px;border-right:0px;border-bottom:0px;line-height: 30px'></td>
					<td class="text_r"><input id="averageReveiveUp3" value="${smsChannelMonitor.averageReveiveUp3}"  style='border-left:0px;border-top:0px;border-right:0px;border-bottom:0px;line-height: 30px'></td>
					<td class="text_r"><input class="btn btn-sm btn-primary" type="button" value="修改" onclick="editMonitor(3)"> &nbsp;&nbsp;<input onclick="delMonitor(3)" class="btn btn-sm btn-primary" type="button" value="删除"></td>
					</tr>
				
			</table>
		</div>
	</div>
	
</body>
</html>