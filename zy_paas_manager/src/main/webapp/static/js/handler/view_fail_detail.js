/**
 * 账号列表查询 JS逻辑
 * 
 * @author allen.yuan
 * @date 2016-09-22
 */
var UPGLoader = {
	params : {
		apiAccount : '',
        channelId : '', // 通道Id
        channelName : '', // 通道名称
        category : '', // 通道名称
		tableName : ''
	},
	loadData : function(pageNum) {

		// 构建查询条件
		this.params.apiAccount = $('#apiAccount').val();
		this.params.channelId = $('#channelId').val();
		this.params.channelName = $('#channelName').val();
		this.params.category = $('#smsCategory').val();
		this.params.tableName = $('#dateTime').val();
		if (typeof (pageNum) == 'undefined' || parseInt(pageNum) < 1) {
			this.params.pageNum = 1;
		} else {
			this.params.pageNum = pageNum;
		}

		var loadTags = 'datagrid'; // 加载的目标容器id
		var loadUrl = '/sms_send_stat/view_fail_analysis';// 请求的url地址
		var boxIndex = layer.load(2);// 加载loading框
		if (loadTags != '' && loadTags != '') {
			$('#' + loadTags).load(loadUrl, {
				params : JSON.stringify(this.params)
			}, function() {
				layer.close(boxIndex); // 关闭loading框
			});
		}
	},
	exportData : function(pageNum) {
        // 构建查询条件
        this.params.apiAccount = $('#apiAccount').val();
        this.params.channelId = $('#channelId').val();
        this.params.channelName = $('#channelName').val();
        this.params.tableName = $('#dateTime').val();
        this.params.pageNum = 1;

        //加载提示框
        expPopuleBox('客户失败原因分析导出');
        $.ajax({
              type: 'post',
              url:'/sms_send_stat/channel_analysis_export',
              dataType:'json',
              data:{params: JSON.stringify(this.params)},
              success: function (result) {
                   if(typeof(result)=='undefined' || result.code == -1){ //导出失败
                     $('.expopul #eimg').hide();
                     $('.expopul #etips').addClass('rs_error');
                     $('.expopul #einfo').html('导出数据操作失败.');
                   }
                   if(result.code == 1){ //导出成功
                     var downUrl = $(".expopul #eurl").attr('_data') + result.data;
                     $('.expopul #eimg').hide();
                     $('.expopul #etips').addClass('rs_sucss');
                     $('.expopul #einfo').html('导出数据操作成功.');
                     $('.expopul #ers').show();
                     $(".expopul #eurl").attr('_data', downUrl);
                     $(".expopul #eurl").click(function(){
                        $('.expopul #eframe').attr('src', $(this).attr('_data'));
                     });
                   }
              }
        });
    }
};

$(function() {

	UPGLoader.loadData();// 首次加载数据

	$('#btnquery').click(function() { // 点击查询按钮操作
		UPGLoader.loadData(1);
	});

	// 点击导出按钮操作
    $('#btnexport').click(function() {
        UPGLoader.exportData();//导出数据
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

	$('#btnback').click(function(){
	   var apiAccount = $("#apiAccount").val();
	   var smsCategory = $("#smsCategory").val();
	   var dateTime = $("#dateTime").val();
	   var sTime = $("#sTime").val();
	   var eTime = $("#eTime").val();
	   var params = {
           "apiAccount": apiAccount,
           "smsCategory": smsCategory,
           "dateTime": dateTime,
           "sTime": sTime,
           "eTime": eTime
       };
       window.location.href = "/sms_send_stat/to_view?params="+JSON.stringify(params);
    });

});