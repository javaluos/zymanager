<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>

<!DOCTYPE HTML>
<html>
<head>
<title>Paas运营管理平台</title>
<jsp:include page="../comm/plugin.jsp" />
</head>
<script type="text/javascript">
	function savenotice(){
		$("#msg").text("");
		$("#msg2").text("");
		$("#msg3").text("");
		
		var noticePhone1=$("#noticePhone1").val();//手机号码
		var noticePhone2='';
		var strs= new Array(); //定义一数组 
		
		var noticeEmail1=$("#noticeEmail1").val();//邮箱地址
		var noticeEmail2='';
		var strs2= new Array(); //定义一数组 
		
		var aa = document.getElementsByName("noticeWay1");//验证是否输入报警方式
		var noticeWay1='';
		var j=0;
	    for (var i = 0; i < aa.length; i++) {
	        if (aa[i].checked) {
	            j++;
	            if(noticeWay1==''){
	            	noticeWay1=noticeWay1+aa[i].value;
	            }else{
	            	 noticeWay1=noticeWay1+','+aa[i].value;
	            }
	            if(aa[i].value==0){
	            	if(noticePhone1.trim()==''){
	            		$("#msg2").text("请输入手机号码！");
					    return false;
	            	}
	            }
	            if(aa[i].value==1){
	            	if(noticeEmail1.trim()==''){
	            		$("#msg3").text("请输入邮箱地址！");
					    return false;
	            	}
	            }
	            if(aa[i].value==2){
	            	if(noticePhone1.trim()==''){
	            		$("#msg2").text("请输入手机号码！");
					    return false;
	            	}
	            }
	        }
	    }
	    if(j==0){
	    	$("#msg").text("请选择报警方式！");
	    	return false;
	    }
		
		
		if(noticePhone1.trim()!=''){//验证手机号码是否输入合法
			strs=noticePhone1.split(","); //字符分割 
			if(strs.length>0){
				for (i=0;i<strs.length;i++ ) 
				{ 
					var flag=isphone(strs[i]);
					if(noticePhone2==''){
						noticePhone2=noticePhone2+strs[i];
					}else{
						noticePhone2=noticePhone2+','+strs[i];
					}
					
					if(!flag){
						$("#msg2").text("手机格式有误！");
					    return false;
					}
				}
			}
		}
		
		if(noticeEmail1.trim()!=''){//验证邮件地址是否输入合法
			strs2=noticeEmail1.split(","); //字符分割 
			if(strs2.length>0){
				for (i=0;i<strs2.length;i++ ) 
				{ 
					var flag=testEmail(strs2[i]);
					if(noticeEmail2==''){
						noticeEmail2=noticeEmail2+strs2[i];
					}else{
						noticeEmail2=noticeEmail2+','+strs2[i];
					}
					if(!flag){
						$("#msg3").text("邮件地址格式有误！");
					    return false;
					}
				}
			}
		}
		$.post("/monitor/save_global_setting",{"noticeWay1":noticeWay1,"noticePhone1":noticePhone2,"noticeEmail1":noticeEmail1}, function(data) {
			if(data){
				var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
			    parent.layer.close(index);
			}
		},"json");
		
		/* $("#form1").attr("action","/monitor/save_global_setting");
		$("#form1").submit(); */
		//window.parent.location.reload();
	   
	}
	
	function cacelnotice(){
	    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	    parent.layer.close(index);
	}
	/*判断输入是否为合法的手机号码*/
	function isphone(inputString)
	{
	     var partten = /^1[3,5,8]\d{9}$/;
	     var fl=false;
	     if(partten.test(inputString))
	     {
	          return true;
	     }
	     else
	     {
	          return false;
	     }
	}
	
	function testEmail(email){
	     var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	     if(!myreg.test(email))
	     {
	         return false;
	     }
	     return true;
	}
</script>
<body>
	<!-- QueryForm -->
	<div class="col-sm-12" style="width: 630px;height: 300px;">
		<div class="ibox">
			   <c:choose>
			        <c:when test="${not empty msg }">
					    <div class="form-group" style="text-align: center;width:600px;">
					        </p>
							<div class="field">
								<font color='red'>${msg}</font></span>
							</div>
						</div>
				   </c:when>
				 <c:otherwise>
				   <div>
					 <form method="get" name="form1" action="" id="form1" class="form-vertical">
						<table class="table noborder">
							<tbody>
							    
								<tr class="noline" style="width: 600px;margin-top: 20px;">
									<td style="width: 600px;margin-top: 20px;margin-left: 60px;">
									         报警方式设置:
										<input type="checkbox" name=noticeWay1  style="margin-top: 20px;vertical-align: -3px;"   <c:if test="${fn:contains(cdrMonitorNoticeSetting.noticeWay1, '0')}">checked="checked"</c:if> value="0"/>&nbsp;短信通知&nbsp;&nbsp;
										<input type="checkbox" name="noticeWay1" style="margin-top: 20px;vertical-align: -3px; margin-left: 20px;" <c:if test="${fn:contains(cdrMonitorNoticeSetting.noticeWay1, '1')}">checked="checked"</c:if> value="1"/>&nbsp;邮件通知 &nbsp;&nbsp;
										<input type="checkbox" name="noticeWay1" style="margin-top: 20px;vertical-align: -3px; margin-left: 20px;" <c:if test="${fn:contains(cdrMonitorNoticeSetting.noticeWay1, '2')}">checked="checked"</c:if> value="2"/>&nbsp;语音通知 &nbsp;&nbsp;
									    <input type="hidden"   name="apiAccount" value="0"/>
									    <span style="height: 5px;margin-top:10px;margin-left:5px;width:90px;color:red;" id="msg"></span>
									</td>
								</tr>
								
								<tr class="noline" style="width: 600px;margin-top: 20px;margin-left: 60px;">
									<td style="width: 600px;margin-top: 20px;margin-left: 60px;">
										被通知人手机:
										<textarea name="noticePhone1" id="noticePhone1" value="${cdrMonitorNoticeSetting.noticePhone1}" style="resize:none" rows="5" cols="50">${cdrMonitorNoticeSetting.noticePhone1}</textarea>
									    <span style="height: 5px;margin-top:10px;margin-left:5px;width:90px;color:red;" id="msg2"></span>(多个号码以,分隔)
									</td>
								</tr>
								
								<tr class="noline" style="width: 600px;margin-top: 20px;margin-left: 60px;">
									<td style="width: 600px;margin-top: 20px;margin-left: 60px;">
									          被通知人邮箱:
										<textarea name="noticeEmail1" id="noticeEmail1" style="resize:none" rows="5" cols="50">${cdrMonitorNoticeSetting.noticeEmail1}</textarea>
										<span style="height: 5px;margin-top:10px;margin-left:5px;width:90px;color:red;" id="msg3"></span>(多个邮箱以,分隔)
									</td>
								 </tr>
								 
								 <tr class="noline">
									<td class="fr" style="width: 600px;margin-left: 120px;margin-top: 20px;">
									   <button id="btnsa2" onclick="savenotice()" class="btn btn-sm btn-primary pull-left" type="button">保存</button>
									   <button id="btnquy" onclick="cacelnotice()" style="margin-left: 60px;" class="btn btn-sm btn-primary pull-left" type="button">取消</button>
									</td>
								 </tr>
							</tbody>
						</table>
					  </form>
				  </div>
			   </c:otherwise>
			</c:choose> 
		</div>
	</div>
	<!-- /QueryForm -->
	</div>
</body>
</html>
