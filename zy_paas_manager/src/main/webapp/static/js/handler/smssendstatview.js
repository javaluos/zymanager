var UPGLoader = {
	params : {
		apiAccount : '',
        category : '',
		starttime : '',// 开始时间
		endtime : '',// 结束时间
		sTime : '',
		eTime : '',
		pageNum : 1
	// 页号
	},
	loadData : function(pageNum) {

		// 构建查询条件
		this.params.apiAccount = $('#apiAccount').val();
		this.params.merchantPhone = $('#merchantPhone').val();
		this.params.businessName = $('#businessName').val();
		this.params.category = $('#smsCategory').val();
		this.params.starttime = $('#starttime').val();
		this.params.endtime = $('#endtime').val();
		this.params.sTime = $('#sTime').val();
		this.params.eTime = $('#eTime').val();
		if (typeof (pageNum) == 'undefined' || parseInt(pageNum) < 1) {
			this.params.pageNum = 1;
		} else {
			this.params.pageNum = pageNum;
		}

		var loadTags = 'datagrid'; // 加载的目标容器id
		var loadUrl = '/sms_send_stat/view_list_data';// 请求的url地址
		var boxIndex = layer.load(2);// 加载loading框
		if (loadTags != '' && loadTags != '') {
			$('#' + loadTags).load(loadUrl, {
				params : JSON.stringify(this.params)
			}, function() {
				layer.close(boxIndex); // 关闭loading框
			});
		}
	}
};

$(function() {

	UPGLoader.loadData();// 首次加载数据

	$('#btnquery').click(function() { // 点击查询按钮操作
		UPGLoader.loadData(1);
	});

	/**
	 * 提交查询-按页下标查询
	 */
	$(document).on('click', '.pgbar .link a', function() {
		var pagenum = $(this).attr('_pgnum');
		var maxpg = $('#maxpg').val();
		if (parseInt(pagenum) > parseInt(maxpg)) {
			pagenum = maxpg;
		}
		UPGLoader.loadData(pagenum);// 加载查询
	});

	/**
	 * 提交查询-跳转页查询
	 */
	$(document).on('click', '.pgbar #pggobtn', function() {
		var pagenum = $('#pageNum').val();
		var maxpg = $('#maxpg').val();
		if (parseInt(pagenum) > parseInt(maxpg)) {
			pagenum = maxpg;
		}
		UPGLoader.loadData(pagenum);// 加载查询
	});

	$('#back').click(function(){
	   var sTime = $("#sTime").val();
       var eTime = $("#eTime").val();
       var merchantPhone = $("#queryPhone").val();
       var businessName = $("#queryName").val();
       var smsCategory = $("#smsCategory").val();
       var params = {
           "merchantPhone": merchantPhone,
           "businessName": businessName,
           "smsCategory": smsCategory,
           "sTime": sTime,
           "eTime": eTime
       };
       window.location.href = '/sms_send_stat/to_list.html?params='+JSON.stringify(params);
    });

});

function detailView(apiAccount, channelId, dateTime, smsCategory, sTime, eTime){
    var params = {
            "apiAccount": apiAccount,
            "channelId": channelId,
            "dateTime": dateTime,
            "smsCategory": smsCategory,
            "sTime": sTime,
            "eTime": eTime
    };
    window.location.href="/sms_send_stat/fail_detail_view?params="+JSON.stringify(params);
}