/**
 * 账号列表查询 JS逻辑
 * 
 * @author allen.yuan
 * @date 2016-09-22
 */
var UPGLoader = {
	params : {
		monitorBody:'',//告警主体
		monitorType:'',//告警类型
		starttime : '',// 告警开始时间
		endtime : '',// 告警结束时间
		isDeal:'',//操作
		isUpMonitor:'',//是否升级告警
		pageNum : 1
	// 页号
	},
	loadData : function(pageNum) {

		// 构建查询条件
		this.params.monitorBody  = $('#monitorBody').val();
		this.params.monitorType = $('#monitorType').val();
		this.params.starttime = $('#starttime').val();
		this.params.endtime = $('#endtime').val();
		this.params.isDeal  = $('#isDeal').val();
		this.params.isUpMonitor = $('#isUpMonitor').val();
		
		if (typeof (pageNum) == 'undefined' || parseInt(pageNum) < 1) {
			this.params.pageNum = 1;
		} else {
			this.params.pageNum = pageNum;
		}

		var loadTags = 'datagrid'; // 加载的目标容器id
		var loadUrl = '/cdr_monitor_notice_log/list_data';// 请求的url地址
		var boxIndex = layer.load(2);// 加载loading框
		if (loadTags != '' && loadTags != '') {
			$('#' + loadTags).load(loadUrl, {
				params : JSON.stringify(this.params)
			}, function() {
				layer.close(boxIndex); // 关闭loading框
			});
		}
	},
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

	// 点击导出按钮操作
	$('#btnexport').click(function() { 
		UPGLoader.exportData();//导出数据
	});
	
	$('#dealAll').click(function() { 
		$.ajax({
			type: "POST",
	   		url: "/cdr_monitor_notice_log/deal_all",
	   		dataType: "json",
		   	success: function(data){
		   		if(data>0){
		   			layer.alert("处理成功!");
		   			setTimeout('window.location.href="/cdr_monitor_notice_log/to_list"',2000);
		   		}else{
		   			layer.alert("处理失败!");
		   		}
		   	}
		});
	});
	
});
