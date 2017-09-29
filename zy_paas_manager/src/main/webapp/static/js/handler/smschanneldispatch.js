/**
 * 保存通道信息 JS逻辑
 * 
 * @author allen.yuan
 * @date 2017-7-10
 */
function saveChanelCfg() {

	var channelId = $('#channelId').val();
	var channelName = $('#channelName').val();
	var protocalType = $('input:radio[name="protocalType"]:checked').val();
	var account = $('#account').val();
	var password = $('#password').val();
	var smsMtUrl = $('#smsMtUrl').val();
	var smsMoUrl = $('#smsMoUrl').val();
	var smsStatusUrl = $('#smsStatusUrl').val();
	var userId = $('#userId').val();
	var taskName = $('#taskName').val();
	var extKey1 = $('#extKey1').val();
	var extVal1 = $('#extVal1').val();
	var extKey2 = $('#extKey2').val();
	var extVal2 = $('#extVal2').val();
	var extKey3 = $('#extKey3').val();
	var extVal3 = $('#extVal3').val();
	var extKey4 = $('#extKey4').val();
	var extVal4 = $('#extVal4').val();
	var extKey5 = $('#extKey5').val();
	var extVal5 = $('#extVal5').val();

	// 设置参数对象
	var params = {
		CHANNEL_ID : channelId.trim(),
		CHANNEL_NAME : channelName.trim(),
		PROTOCOL_TYPE : protocalType.trim(),
		ACCOUNT : account.trim(),
		PASSWORD : password.trim(),
		SMS_MT_URL : smsMtUrl.trim(),
		SMS_MO_URL : smsMoUrl.trim(),
		SMS_STATUS_URL : smsStatusUrl.trim(),
		USERID : userId.trim(),
		TASKNAME : taskName.trim()
	};

	if (typeof (extKey1) != "undefined" && extKey1.trim() != '') {
		params[extKey1 + ""] = extVal1;
	}
	if (typeof (extKey2) != "undefined" && extKey2.trim() != '') {
		params[extKey2 + ""] = extVal2;
	}
	if (typeof (extKey3) != "undefined" && extKey3.trim() != '') {
		params[extKey3 + ""] = extVal3;
	}
	if (typeof (extKey4) != "undefined" && extKey4.trim() != '') {
		params[extKey4 + ""] = extVal4;
	}
	if (typeof (extKey5) != "undefined" && extKey5.trim() != '') {
		params[extKey5 + ""] = extVal5;
	}

	var mssage = "系统提示：当前功能提供给开发者，保存配置可能影响短信平台运行，请确认是否要保存操作？";
	// 询问框
	layer.confirm(mssage,{
		btn : [ '取消保存', '确认保存' ]
	// 按钮
	}, function() {
		layer.msg('已取消', {icon: 0});
	}, function() {
		 
		$.ajax({
			type : "POST",
			url : "/smschannel/channeldispatch_save",
			data : {
				'params' : JSON.stringify(params)
			},
			success : function(data) {
				if (data == '1') {
					$('#btnback').click();
				} else {
					messageBox('系统提示', '保存数据异常.');
				}
			}
		});
	});

};
