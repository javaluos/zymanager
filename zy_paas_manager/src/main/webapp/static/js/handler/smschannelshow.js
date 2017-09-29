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
	   location.href = '/smschannel/smschannel_list';
	});
	
	// 保存按钮事件
	$('#btnsave').click(function(){
		doSubmitChannel();
	});
})

/**
 * 提交保存（新增,修改）
 * 
 * @returns {Boolean}
 */
function doSubmitChannel(){

	var operateType = $('input:radio[name="operateType"]:checked').val();
	var channelId = $('#channelId').val();
	var channelMainCode = $('#channelMainCode').val();
	var channelName = $('#channelName').val();
	var channelCode = $('#channelCode').val();
	var channelAccessMode = $('input:radio[name="channelAccessMode"]:checked').val();
	var channelType = $("#channelType").val();
	
	var moFlag = $("#moFlag").val();
	var longSmsFlag = $("#longSmsFlag").val();
	var signerAudit = $("#signerAudit").val();
	var templateAudit = $("#templateAudit").val();
	
	var status = $("#status").val();
	var crestValue = $("#crestValue").val();
	var chargeType = $("#chargeType").val();
	var channelFee=$("#channelFee").val();
	var channelScore = $("#channelScore").val();
	
	var channelAccount = $("#channelAccount").val();
	var channelPassword = $("#channelPassword").val();
	var channelComment = $("#channelComment").val();
	var dtnProvince = $("#dtnProvince").val();
	var useProvince = $("#useProvince").val();
	channelMainCode = channelMainCode.trim();
	channelName = channelName.trim();

	// 验证通道名称非空
	if(empty(channelName)){
		layer.tips('通道名称必填.', '#channelName');
		return false;
	}
	
	// 校验验证码
	if(empty(channelMainCode)){
		layer.tips('通道编号必填.', '#channelMainCode');
		return false;
	}
	
	var regex = new RegExp('^[A-Za-z0-9-]+$', 'gi');
	var result = channelMainCode.match(regex);
	if(null==result || 0==result.length)  { 
		layer.tips('通道编号格式错误,只能包含字母,数字,中水平线.', '#channelMainCode');
		return false;
	}  
	
	// 通道编码唯一验证(新增验证，修改验证（排除当前自己）)
	var schannelMainCode = $('#schannelMainCode').val();
	if(schannelMainCode == '' || schannelMainCode != channelMainCode){
		var existCode = checkExistChannel(channelMainCode);//调用检查方法
		if(existCode){
			layer.tips('通道编号在系统中已存在.', '#channelMainCode');
			return false;
		}
	}
	
	// 验证通道类型非空
	if(channelType == -1){
		layer.tips('通道类型必选.', '#channelType');
		return false;
	}
	
	// 验证是否支持上行非空
	if(moFlag == -1){
		layer.tips('是否支持上行必选.', '#moFlag');
		return false;
	}
	
	// 验证是否支持长短信非空
	if(longSmsFlag == -1){
		layer.tips('是否支持长短信必选.', '#longSmsFlag');
		return false;
	}
	
	// 验证签名报备非空
	if(signerAudit == -1){
		layer.tips('签名报备必选.', '#signerAudit');
		return false;
	}
	
	// 验证模板报备非空
	if(templateAudit == -1){
		layer.tips('模板报备必选.', '#templateAudit');
		return false;
	}

	//付费方式
	if(chargeType == -1){
		layer.tips('付费方式必选.', '#chargeType');
		return false;
	}

	//通道资费
	var reg=/^\d+(?=\.{0,1}\d+$|$)/;
	if(!empty(channelFee)){
		if(!reg.test(channelFee)){
			layer.tips('请输入正确的通道资费.', '#channelFee');
	        return false;
		}
	}
	
	// 验证评分非空
	if(empty(channelScore)){
		layer.tips('评分必填.', '#channelScore');
		return false;
	}
	
	if(empty(crestValue)){ crestValue = 0; }
	
	// 设置参数对象
	var params = {
	    operateType: operateType, //运营商类型
		channelId: channelId, //通道ID
		channelMainCode: channelMainCode,//通道编号
		channelName : channelName, // 通道名称
		channelCode: channelCode,
		channelAccessMode:channelAccessMode,
		channelType: channelType,
		moFlag: moFlag,
		longSmsFlag: longSmsFlag,
		signerAudit : signerAudit,
		templateAudit : templateAudit,
		status: status,
		dtnProvince : dtnProvince,
		useProvince : useProvince,
		crestValue: crestValue,
		chargeType:chargeType,
		channelFee:channelFee,
		channelScore : channelScore,
		channelAccount : channelAccount,
		channelPassword : channelPassword,
		channelComment : channelComment
	};
	
	$.ajax({
		type: "POST",
   		url: "/smschannel/smschannel_save",
		data: {'params' : JSON.stringify(params)},
		success: function(data){
			if(data == '1'){
				$('#btnback').click(); 
			}else{
				messageBox('系统提示','保存数据异常.');
			}	 
	   	}
	})
	
	
}

/**
 * 检查通道编号是否重复( 通道编码唯一验证(新增验证，修改验证（排除当前自己）))
 */
function checkExistChannel(channelMainCode){
	var result = false;
	$.ajax({
		type: "POST",
   		url: "/smschannel/querySmsChannelBySmsChMainCode",
		data: {'channelMainCode' : channelMainCode},
		async: false,
		success: function(data){
			if(data != ''){
			  result = true;
			} 	 
	   	}
	})
	return result;
}