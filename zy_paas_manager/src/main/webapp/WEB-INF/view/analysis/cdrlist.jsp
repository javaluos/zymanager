<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored ="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<jsp:include page="../top_meta.jsp" />
<jsp:include page="../common/plugin.jsp" />
<script type="text/javascript" src="/js/datepicker/WdatePicker.js"></script>
<title>管理中心-智语科技</title>

</head>
<body>
<div class="header">
	<jsp:include page="../common/top_banner_uc.jsp" />
</div>
<div class="u_main clearfix inner">
	<div class="u_left">
		<jsp:include page="../common/left.jsp" />
	</div>
	<div class="u_right">
		<div class="ur_h">
			<h2>统计分析</h2>
			<p class="pathway">
					<a href="/voiceUpload/list.html">统计分析</a><span id="natitle">语音通知</span>
			</p>
		</div>
		<div class="ur_c">
            
		 	<div class="form-inline panel es" style="width: 100%;margin-top: 10px;">
				<div class="">
					<label for="appid" style="min-width: 56px;display: inline-block;text-align: right;"> 应用</label> 		 
				    <select class="input" name="appid" id="appid" style="width: 200px;">
		                <option value=""></option>
					</select>	
						
					<label for="caller" style="margin-left: 20px;"> 主叫号码</label> 
					<input type="text" class="input" id="caller" name="caller"
					       style="width: 215px;" maxlength="100" placeholder=""  />								
			 
					<label for="caller" style="margin-left: 20px;"> 被叫号码</label>
					<input type="text" class="input" id="callee" name="callee"
					       style="width: 200px;" maxlength="100" placeholder="" />
				</div>
			 
				<div class="" style="margin-top: 10px;">
					<label for="state" style="min-width: 56px;display: inline-block;"> 通话状态</label>
					<select class="input" name="state" id="state" style="width: 200px;">
			          <option value="-1">&nbsp;</option>
			          <option value="0">正常通话</option>
			          <option value="1">被叫未接</option>
			          <option value="2">被叫拒接</option>
			          <option value="3">呼叫失败</option>
					</select>
				 
					<label for="starttime" style="min-width: 56px;display: inline-block;margin-left: 20px;text-align: right;"> 日期</label>
					<input type="text" class="input" id="starttime" name="starttime" onClick="WdatePicker()"
					       style="width: 95px;" maxlength="100" placeholder="" />
					<label for="endtime" style="min-width: 20px;display: inline-block;text-align: right; margin-left: -5px;">到</label>       
					<input type="text" class="input" id="endtime" name="endtime" onClick="WdatePicker()" 
					       style="width: 95px;" maxlength="100" placeholder="" />
					       
					<button type="button" class="button bg-main" id="sc_alysis_btn" 
					         style="width: 95px;text-align: center;margin-left: 80px;">查询</button>   
					<input type="hidden" id="querytype" value="4">             
				</div>
			</div>
			<div class="tab" style="margin-top:20px;">
				<div class="tab-head">
					<ul class="tab-nav">
						<li class="active"><a class="tbsval" href="#tab-voicenotice" data-item="4">语音通知</a> </li>
						<li><a class="tbsval" class="tbsval" href="#tab-voicecode" data-item="5">语音验证码</a> </li>
						<!-- 
						<li><a class="tbsval" href="#tab-callphone" data-item="3">直拨电话</a> </li>
						<li><a class="tbsval" href="#tab-backphone" data-item="1">回拨电话</a> </li> -->
					</ul>
				</div>
				<div class="tab-body">
					<div class="tab-panel active" id="tab-voicenotice">
					</div>
					<div class="tab-panel" id="tab-voicecode">
					</div>
					<!-- 
					<div class="tab-panel" id="tab-callphone">
					</div>
					<div class="tab-panel" id="tab-backphone">
					</div>
					 -->
				</div>
			</div>
			
	</div>
   </div>

  </div>
</div>
<jsp:include page="../common/footer_uc.jsp" />
<script type="text/javascript" src="/js/analysis/cdranalysislist.js"></script>

<script>
$(function(){
	loadApplist();//加载应用列表
	loadalysisreq(0);//
});

</script>
</body>
</html>
