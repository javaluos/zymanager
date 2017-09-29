/**
 * 账号列表查询 JS逻辑
 * 
 * @author allen.yuan
 * @date 2016-09-22
 */
var UPGLoader = {
	params : {
		merchantphone : '', // 手机号码
		dateTimeStart: '',//时间开始
		dateTimeEnd: '',//时间结束
		pctCallSucStart: '',//接通率开始
		pctCallSucEnd: '',//接通率结束
		pctResponseSucStart: '',// 应答率开始
		pctResponseSucEnd: '',// 应答率结束
		avgAcdStart: '',// ACD
		querytype:5,//语音验证码
		pageNum: 1// 页号
	},
	loadData : function(pageNum) {
		// 构建查询条件
		this.params.merchantphone = $('#merchantphone').val();
		this.params.dateTimeStart = $('#dateTimeStart').val();
		this.params.dateTimeEnd= $('#dateTimeEnd').val(); 
		this.params.pctCallSucStart= $('#pctCallSucStart').val();
		this.params.pctCallSucEnd= $('#pctCallSucEnd').val();
		this.params.pctResponseSucStart= $('#pctResponseSucStart').val(); 
		this.params.pctResponseSucEnd= $('#pctResponseSucEnd').val();
		this.params.avgAcdStart= $('#avgAcdStart').val();
		this.params.querytype=5 ;
		
		if (typeof (pageNum) == 'undefined' || parseInt(pageNum) < 1) {
			this.params.pageNum = 1;
		} else {
			this.params.pageNum = pageNum;
		}

		var loadTags = 'datagrid'; // 加载的目标容器id
		var loadUrl = '/cdrDailyStatistics/cdr_daily_statistics_list_data';// 请求的url地址
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

	UPGLoader.loadData(1);// 首次加载数据

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
});

function audit(id,apiAccount){
	window.location.href="/audit/preAudit?id="+id+"&apiAccount="+apiAccount;
}

function detail(id,apiAccount){
	window.location.href="/audit/auditDetail?id="+id+"&apiAccount="+apiAccount;
}