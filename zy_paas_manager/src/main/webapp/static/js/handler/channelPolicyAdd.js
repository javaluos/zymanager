function deleteRules(sendNum){
	var sendNums=parseInt($("#sendNums").val());
	
	if(sendNums==1){
		layer.confirm("分流策略至少必须有一个策略规则！", {
			area: ['450px'],
			btn: ['确认'] //按钮
		}, function(){
			layer.closeAll('dialog');
			return;
		});
	}else{
		if(sendNum==sendNums){
			$("#table_"+sendNum).remove();
			$("#sendNums").val(sendNums-1);
		}else{
			$("#table_"+sendNum).remove();
			$("#sendNums").val(sendNums-1);
			for(var i=(sendNum+1);i<=sendNums;i++){//i之后的数据
				var j=i-1;
				$("#table_"+i).attr("id","table_"+j);
			    $("#ruleIndex_"+i).text(j);
			    $("#ruleIndex_"+i).attr("id","ruleIndex_"+j);
				$("#delete_"+i).attr("onclick","deleteRules("+j+")");
				$("#delete_"+i).attr("id","delete_"+j);
				$("#keyword_"+i).attr("name","keyword_"+j);
				$("#keyword_"+i).attr("onmouseleave","onMouseLeave("+j+")");
				$("#keyword_"+i).attr("id","keyword_"+j);
				$("#mobileFlagYD_"+i).attr("name","mobileFlag_"+j);
				$("#mobileFlagYD_"+i).attr("onclick","selectMobiles("+j+")");
				$("#mobileFlagYD_"+i).attr("id","mobileFlagYD_"+j);
				$("#mobileFlagLT_"+i).attr("name","mobileFlag_"+j);
				$("#mobileFlagLT_"+i).attr("onclick","selectMobiles("+j+")");
				$("#mobileFlagLT_"+i).attr("id","mobileFlagLT_"+j);
				$("#mobileFlagDX_"+i).attr("name","mobileFlag_"+j);
				$("#mobileFlagDX_"+i).attr("onclick","selectMobiles("+j+")");
				$("#mobileFlagDX_"+i).attr("id","mobileFlagDX_"+j);
				$("#ruleId_"+i).attr("id","ruleId_"+j);
				$("#mobiles_"+i).attr("onmouseleave","onMouseLeave("+j+")");
				$("#mobiles_"+i).attr("id","mobiles_"+j);
				$("#group_YD_"+i).attr("onmouseleave","onMouseLeave("+j+")");
				$("#group_LT_"+i).attr("onmouseleave","onMouseLeave("+j+")");
				$("#group_DX_"+i).attr("onmouseleave","onMouseLeave("+j+")");
				$("#group_YD_"+i).attr("id","group_YD_"+j);
				$("#group_LT_"+i).attr("id","group_LT_"+j);
				$("#group_DX_"+i).attr("id","group_DX_"+j);
				$("#content_"+i).attr("id","content_"+j);
				$("#YD_"+i).attr("id","YD_"+j);
				$("#LT_"+i).attr("id","LT_"+j);
				$("#DX_"+i).attr("id","DX_"+j);
		}
	}
  }
}


$(function(){ 
	var sendNums=parseInt($("#sendNums").val());
	
	for(var i=1;i<=sendNums;i++){
		var keyword=$("#keyword_"+i).val();//关键字
		var mobile=$("#mobiles_"+i).val();//手机号码
		var groupYD=$("#group_YD_"+i+" option:selected").text();//移动通道组
		var groupLT=$("#group_LT_"+i+" option:selected").text();//联通通道组
		var groupDX=$("#group_DX_"+i+" option:selected").text();//电信通道组
		
		if (keyword !== '' && mobile !== ''){
			$("#content_"+i).text("关键字和号段");
		}else if(keyword !== ''){
			$("#content_"+i).text("关键字");
		}else if(mobile!==''){
			$("#content_"+i).text("号段");
		}else{
			$("#content_"+i).text("");
		}
		
		if(groupYD!="请选择"){
			$("#YD_"+i).text(groupYD);
		}
		if(groupLT!="请选择"){
			$("#LT_"+i).text(groupLT);
		}
		if(groupDX!="请选择"){
			$("#DX_"+i).text(groupDX);
		}
	}
});


function selectMobiles(sendNums){
    var obj=document.getElementsByName('mobileFlag_'+sendNums);  
	var mobileYD=$("#mobileYD").val();
	var mobileLT=$("#mobileLT").val();
	var mobileDX=$("#mobileDX").val();
	
	var mobiles=$("#mobiles_"+sendNums).val();
	
	var s=''; 
	for(var i=0; i<obj.length; i++){ 
		if(obj[i].checked){
			if(obj[i].value=='YD' && mobiles.indexOf(mobileYD)<0){
				if(s==''){
					s+=mobileYD;
				}else{
					s+=","+mobileYD;
				}
			}else if(obj[i].value=='LT' &&  mobiles.indexOf(mobileLT)<0){
				if(s==''){
					s+=mobileLT;
				}else{
					s+=","+mobileLT;
				}
			}else if(obj[i].value=='DX' &&  mobiles.indexOf(mobileDX)<0){
				if(s==''){
					s+=mobileDX;
				}else{
					s+=","+mobileDX;
				}
			}
			if(s!=''){
				if(mobiles!=''){
					$("#mobiles_"+sendNums).val(mobiles+","+s);
				}else{
					$("#mobiles_"+sendNums).val(s);
				}
			}
		}else{
			if(obj[i].value=='YD' && mobiles.indexOf(mobileYD)>=0){
				mobiles=mobiles.replace(mobileYD+",", "");
				mobiles=mobiles.replace(","+mobileYD, "");
				mobiles=mobiles.replace(mobileYD, "");
				$("#mobiles_"+sendNums).val(mobiles);
			}else if(obj[i].value=='LT' &&  mobiles.indexOf(mobileLT)>=0){
				mobiles=mobiles.replace(mobileLT+",", "");
				mobiles=mobiles.replace(","+mobileLT, "");
				mobiles=mobiles.replace(mobileLT, "");
				$("#mobiles_"+sendNums).val(mobiles);
			}else if(obj[i].value=='DX' &&  mobiles.indexOf(mobileDX)>=0){
				mobiles=mobiles.replace(mobileDX+",", "");
				mobiles=mobiles.replace(","+mobileDX, "");
				mobiles=mobiles.replace(mobileDX, "");
				$("#mobiles_"+sendNums).val(mobiles);
			}
		}
	}
	
}

function onMouseLeave(sendNums){
	var keyword=$("#keyword_"+sendNums).val();//关键字
	var mobile=$("#mobiles_"+sendNums).val();//手机号码
	var groupYD=$("#group_YD_"+sendNums+" option:selected").text();//移动通道组
	var groupLT=$("#group_LT_"+sendNums+" option:selected").text();//联通通道组
	var groupDX=$("#group_DX_"+sendNums+" option:selected").text();//电信通道组
	
	if (keyword !== '' && mobile !== ''){
		$("#content_"+sendNums).text("关键字和号段");
	}else if(keyword !== ''){
		$("#content_"+sendNums).text("关键字");
	}else if(mobile!==''){
		$("#content_"+sendNums).text("号段");
	}else{
		$("#content_"+sendNums).text("");
	}
	
	if(groupYD!="请选择"){
		$("#YD_"+sendNums).text(groupYD);
	}
	if(groupLT!="请选择"){
		$("#LT_"+sendNums).text(groupLT);
	}
	if(groupDX!="请选择"){
		$("#DX_"+sendNums).text(groupDX);
	}
}

function btnadd() { // 点击增加策略规则按钮操作
	var list_map = new Array();
	var sendNums=parseInt($("#sendNums").val())+1;
	$("#sendNums").val(sendNums);

	var obj = $("#group_YD_1 option");  
	for(var i=1;i<obj.length;i++){ 
		list_map.push(
		 {
		   listValue:obj[i].value,
		   listText:obj[i].text
		 }
		);
	}  
	
	var appendHtml="<table border='1px;' id='table_"+sendNums+"' style='width: 900px;height:630px;margin-left: 30px;margin-top:10px;border-color:#CCCCCC;'>"
                  +"<tr>"
                  +"   <td>"
                  +"      <label style='padding-left: 20px;margin-top:-30px;'><span id='ruleIndex_"+sendNums+"'>"+sendNums+"</span>.策略规则："
                  +"      	<a style='padding-left: 760px;' id='delete_"+sendNums+"' onclick=deleteRules("+sendNums+")>删除</a> "
                  +"      	<p/>"
                  +"      </label>"
        
                  +"      <label style='padding-left: 40px;padding-right: 760px;'>关键字：</label>"
                  +"      <div style='padding-left: 40px;'>"
                  +"     	 <textarea rows='6' cols='70' style='resize:none;' onmouseleave=onMouseLeave("+sendNums+") id='keyword_"+sendNums+"' name='keyword_"+sendNums+"'></textarea>"
                  +"         <span style='color: red;'>&nbsp;&nbsp;注:每个关键词之间用逗号(英文)隔开</span>"
                  +"      </div>"  
                  
                  +"      <label style='padding-left: 40px;padding-right: 660px;'>"
                  +"    	  号段："
                  +"      	<input type='checkbox'  id='mobileFlagYD_"+sendNums+"' value='YD' name='mobileFlag_"+sendNums+"'  onclick='selectMobiles("+sendNums+")'  style='vertical-align:-2px;'>&nbsp; 移动"
                  +"      	<input type='checkbox'  id='mobileFlagLT_"+sendNums+"' value='LT' name='mobileFlag_"+sendNums+"'  onclick='selectMobiles("+sendNums+")'  style='vertical-align:-2px;'>&nbsp; 联通"
                  +"      	<input type='checkbox'  id='mobileFlagDX_"+sendNums+"' value='DX' name='mobileFlag_"+sendNums+"'  onclick='selectMobiles("+sendNums+")'  style='vertical-align:-2px;'>&nbsp; 电信"
                  +"      </label>"
        
                  +"      <div class='form-group' style='padding-left: 40px;'>"
                  +"     	 <textarea rows='6' cols='70' style='resize:none;' onmouseleave=onMouseLeave("+sendNums+") id='mobiles_"+sendNums+"' name='mobiles_"+sendNums+"''></textarea>"
                  +"         <span style='color: red;'>&nbsp;&nbsp;注:每个号段之间用逗号(英文)隔开</span>"
                  +"      </div>"
        
                  +"      <label style='padding-left: 20px;'>分流到：</label>"
                  +"      <div>"
                  +"      	<select class='form-control m-b' id='channelType' style='width: 180px;'>"
                  +"      		<option value='1'  selected='selected'>通道组</option>"
                  +"      	</select>"
                  +"      </div>"
        
                  +"      <label style='padding-left: 40px;'>移动：</label>"
                  +"      <div>"
                  +"      	<select class='form-control m-b' onmouseleave=onMouseLeave("+sendNums+") id='group_YD_"+sendNums+"' name='group_YD_"+sendNums+"'  style='width: 180px;'>"
                  +"          <option value='-1'  selected='selected'>请选择</option>";
                  
                  for(var i=0;i <list_map.length;i++){
                	  appendHtml+= "<option value='"+list_map[i].listValue+"'>"+list_map[i].listText+"</option>";
                  }
                 
       appendHtml +="      	</select>"
                  +"      </div>"
        
        
                  +"      <label style='padding-left: 40px;'>联通：</label>"
                  +"      <div>"
                  +"      	<select class='form-control m-b' onmouseleave=onMouseLeave("+sendNums+") id='group_LT_"+sendNums+"' name='group_LT_"+sendNums+"' style='width: 180px;'>"
                  +"          <option value='-1'  selected='selected'>请选择</option>";
                  
       			  for(var i=0;i <list_map.length;i++){
     	 			 appendHtml+= "<option value='"+list_map[i].listValue+"'>"+list_map[i].listText+"</option>";
      			  }
                  
       appendHtml +="      	</select>"
                  +"      </div>"
        
                  +"      <label style='padding-left: 40px;'>电信：</label>"
                  +"      <div>"
                  +"      	<select class='form-control m-b' onmouseleave=onMouseLeave("+sendNums+") id='group_DX_"+sendNums+"' name='group_DX_"+sendNums+"' style='width: 180px;'>"
                  +"          <option value='-1'  selected='selected'>请选择</option>";
                  
      			  for(var i=0;i <list_map.length;i++){
 						 appendHtml+= "<option value='"+list_map[i].listValue+"'>"+list_map[i].listText+"</option>";
		          }
      			   
      appendHtml +="      	</select>"
                  +"      </div>"
        
                  +"      <label style='padding-left: 20px;'>"
                  +"            当短信内容匹配【"
                  +"           	<span id='content_"+sendNums+"'></span>"
                  +"           	】时，移动从【"
                  +"           	<span id='YD_"+sendNums+"'></span>"
                  +"           	】、联通从【"
                  +"           	<span id='LT_"+sendNums+"'></span>"
                  +"           	】、电信从【"
                  +"          	<span id='DX_"+sendNums+"'></span>"
                  +"            】中发送。</label>"
                  +"    </td>"
                  +"  </tr>"
                  +"</table>";
                  
           $("#content").append(appendHtml);
}




$('#policysave').click(function() { // 点击增加策略规则按钮操作
	var list_map = new Array();
	var policyName=$("#policyName").val();
	var sendNums=parseInt($("#sendNums").val())
	if(policyName == ''){
		$("#policyName").focus();
		layer.tips("请输入分流策略名称.", $("#policyName"));
		return false;
	}
	
	for ( var i = 1; i <= sendNums; i++) {
		var keyword=$("#keyword_"+i).val();//关键字
		var mobile=$("#mobiles_"+i).val();//手机号码
		var groupYD=$("#group_YD_"+i+" option:selected").val();//移动通道组
		var groupLT=$("#group_LT_"+i+" option:selected").val();//联通通道组
		var groupDX=$("#group_DX_"+i+" option:selected").val();//电信通道组
		
		if(keyword == '' && mobile == '' ){
			$("#keyword_"+i).focus();
			layer.tips("关键字和号段两者必须输入其中一个.", $("#keyword_"+i));
			return false;
		}
		
		if(keyword != ''){
			var keywords=keyword.split(",");
			for(var j=0;j<keywords.length;j++){
				var key=keywords[j];
				if(key.length>70){
					$("#keyword_"+i).focus();
					layer.tips("关键词长度不能超过70个.", $("#keyword_"+i));
					return false;
				}
			}
		}
		
		if(mobile!=''){
			var mobiles=mobile.split(",");
			for(var j=0;j<mobiles.length;j++){
				var mo=mobiles[j];
				var last=mo.substring(mo.length-1,mo.length);
				if(last!='*'){
					$("#mobiles_"+i).focus();
					layer.tips("号段末尾必须输入*.", $("#mobiles_"+i));
					return false;
				}
				if(mo.length<4||mo.length>12){
					$("#mobiles_"+i).focus();
					layer.tips("号段必须大于3位，小于11位.", $("#mobiles_"+i));
					return false;
				}
				for(var k=j+1;k<mobiles.length;k++){
					if(mo==mobiles[k]){
						$("#mobiles_"+i).focus();
						layer.tips("请不要填写重复的号段.", $("#mobiles_"+i));
						return false;
					}
				}
			}
		}
		
		if(groupYD=='-1' && groupLT=='-1' && groupDX=='-1'){
			$("#group_YD_"+i).focus();
			layer.tips("移动、联通、电信通道组三者必须选择其中一个.", $("#group_YD_"+i));
			return false;
		}
		
	    list_map.push( 
	      {
			policyName:policyName,
			keyword:keyword,
			mobiles:mobile,
			groupYD:groupYD,
			groupLT:groupLT,
			groupDX:groupDX,
			policyIndex:i
		  }
	    );
	}
	var param=JSON.stringify(list_map);
	$.ajax({
		type: "POST",
   		url: "/sms_channel_policy/do_save",
   		data: {"params" : param},
   		dataType: "json",
	   	success: function(data){
	   		if(data>0){
	   			layer.alert("处理成功!");
	   			setTimeout('window.location.href="/sms_channel_policy/to_list"',2000);
	   		}else if(data==-1){
	   			layer.alert("策略名称不能重复添加!");
	   		}
	   		else{
	   			layer.alert("处理失败!");
	   		}
	   	}
	});
});


$('#policyedit').click(function() { // 点击增加策略规则按钮操作
	var list_map = new Array();
	var policyName=$("#policyName").val();
	var sendNums=parseInt($("#sendNums").val());
	var policyId=$("#policyId").val();
	
	
	if(policyName == ''){
		$("#policyName").focus();
		layer.tips("请输入分流策略名称.", $("#policyName"));
		return false;
	}
	
	for ( var i = 1; i <= sendNums; i++) {
		var keyword=$("#keyword_"+i).val();//关键字
		var mobile=$("#mobiles_"+i).val();//手机号码
		var groupYD=$("#group_YD_"+i+" option:selected").val();//移动通道组
		var groupLT=$("#group_LT_"+i+" option:selected").val();//联通通道组
		var groupDX=$("#group_DX_"+i+" option:selected").val();//电信通道组
		var ruleId=$("#ruleId_"+i).val();
		
		if(keyword == '' && mobile == '' ){
			$("#keyword_"+i).focus();
			layer.tips("关键字和号段两者必须输入其中一个.", $("#keyword_"+i));
			return false;
		}
		
		if(keyword != ''){
			var keywords=keyword.split(",");
			for(var j=0;j<keywords.length;j++){
				var key=keywords[j];
				if(key.length>70){
					$("#keyword_"+i).focus();
					layer.tips("关键词长度不能超过70个.", $("#keyword_"+i));
					return false;
				}
			}
		}
		
		if(mobile!=''){
			var mobiles=mobile.split(",");
			for(var j=0;j<mobiles.length;j++){
				var mo=mobiles[j];
				var last=mo.substring(mo.length-1,mo.length);
				if(last!='*'){
					$("#mobiles_"+i).focus();
					layer.tips("号段末尾必须输入*.", $("#mobiles_"+i));
					return false;
				}
				if(mo.length<4||mo.length>12){
					$("#mobiles_"+i).focus();
					layer.tips("号段必须大于3位，小于11位.", $("#mobiles_"+i));
					return false;
				}
				for(var k=j+1;k<mobiles.length;k++){
					if(mo==mobiles[k]){
						$("#mobiles_"+i).focus();
						layer.tips("请不要填写重复的号段.", $("#mobiles_"+i));
						return false;
					}
				}
			}
		}
		
		if(groupYD=='-1' && groupLT=='-1' && groupDX=='-1'){
			$("#group_YD_"+i).focus();
			layer.tips("移动、联通、电信通道组三者必须选择其中一个.", $("#group_YD_"+i));
			return false;
		}
		
	    list_map.push( 
	      {
	    	policyId:policyId,
	    	ruleId:ruleId,
			policyName:policyName,
			keyword:keyword,
			mobiles:mobile,
			groupYD:groupYD,
			groupLT:groupLT,
			groupDX:groupDX,
			policyIndex:i
		  }
	    );
	}
	var param=JSON.stringify(list_map);
	$.ajax({
		type: "POST",
   		url: "/sms_channel_policy/do_edit",
   		data: {"params" : param},
   		dataType: "json",
	   	success: function(data){
	   		if(data>0){
	   			layer.alert("处理成功!");
	   			setTimeout('window.location.href="/sms_channel_policy/to_list"',2000);
	   		}else if(data==-1){
	   			layer.alert("策略名称不能重复添加!");
	   		}else{
	   			layer.alert("处理失败!");
	   		}
	   	}
	});
});


// 返回按钮事件
$('#btnback').click(function(){
   location.href = '/sms_channel_policy/to_list';
});

//返回按钮事件
$('#btnback2').click(function(){
   location.href = '/sms_channel_policy/to_list';
});