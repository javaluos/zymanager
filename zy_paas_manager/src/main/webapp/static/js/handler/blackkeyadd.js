/**
 * 通道信息业务
 * 
 * @author allen.yuan
 * @date 2017-2-4
 * 
 */

$(function() {
	
	// 返回按钮事件
	$('#btnback').click(function(){
		var policyId=$('#policyId').val();
		var policyName=$('#policyName').val();
	    location.href = '/black_key_list/to_list?policyId='+policyId+"&policyName="+policyName;
	});
	
	// 保存按钮事件
	$('#btnsave').click(function(){
		doSubmitChannel();
	});
})

function btnback(policyId,policyName){
	 location.href = '/black_key_list/to_list?policyId='+policyId+"&policyName="+policyName;
}

/**
 * 提交保存（新增,修改）
 * 
 * @returns {Boolean}
 */
function doSubmitChannel(){

	var industry = $('#industry').val();
	industry=industry.trim();
	
	var black_keys = $('#black_keys').val();
	black_keys=black_keys.trim();
	
	var remark = $('#remark').val();
	remark=remark.trim();
	
	var policyId=$('#policyId').val();
	var policyName=$('#policyName').val();
	
	// 验证敏感词非空
	if(empty(black_keys)){
		layer.tips('敏感词必填.', '#black_keys');
		return false;
	}
	
	if(validateKey(black_keys)){
		layer.tips('敏感词格式错误.', '#black_keys');
		return false;
	}
	
	// 设置参数对象
	var params = {
		industry: industry, 
		black_keys : black_keys,
		remark : remark,
		policyId:policyId
	};
	
	$.ajax({
		type: "POST",
   		url: "/black_key_list/to_save",
		data: {'params' : JSON.stringify(params)},
		success: function(data){
			if(data == '1'){
				btnback(policyId,policyName); 
			}else{
				messageBox('系统提示','保存数据异常.');
			}	 
	   	}
	})
	
}

function validateKey(blacks){
	var strs=blacks.split(",");
	var flag=false;
	if(strs.length>0){
		for (i=0;i<strs.length;i++ ) 
		{ 
			if(strs[i]==''){
				flag=true;
			}
		}
		return flag;
	}
	return true;
}

