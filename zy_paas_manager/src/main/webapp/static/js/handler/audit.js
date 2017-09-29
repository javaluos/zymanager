/**
 * 账号列表查询 JS逻辑
 * 
 * @author allen.yuan
 * @date 2016-09-22
 */
var UPGLoader = {
	params : {
		merchantphone : '', // 手机号码
		merchantemail : '', // 邮箱
		voiceType:'',//语音类型
		authSubmitTimeStart:'',//提交开始时间
		authSubmitTimeEnd:'',//提交结束时间
		authResultTimeStart:'',//审核开始时间
		authResultTimeEnd:'',//审核结束时间
		authStatus:'',//标识是待审核列表还是审核列表
		pageNum : 1
	// 页号
	},
	loadData : function(pageNum) {
		// 构建查询条件
		this.params.merchantphone = $('#phone').val();
		this.params.merchantemail = $('#email').val();
		this.params.voiceType= $('#authflag').val(); 
		this.params.authSubmitTimeStart= $('#authSubmitTimeStart').val();
		this.params.authSubmitTimeEnd= $('#authSubmitTimeEnd').val();
		this.params.authStatus= $('#authStatus').val();
		this.params.authResultTimeStart=$('#authResultTimeStart').val();
		this.params.authResultTimeEnd=$('#authResultTimeEnd').val();
		
		if (typeof (pageNum) == 'undefined' || parseInt(pageNum) < 1) {
			this.params.pageNum = 1;
		} else {
			this.params.pageNum = pageNum;
		}

		var loadTags = 'datagrid'; // 加载的目标容器id
		var loadUrl = '/audit/audit_list_data';// 请求的url地址
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
});

function audit(id,apiAccount){
	window.location.href="/audit/preAudit?id="+id+"&apiAccount="+apiAccount;
}

function detail(id,apiAccount){
	window.location.href="/audit/auditDetail?id="+id+"&apiAccount="+apiAccount;
}