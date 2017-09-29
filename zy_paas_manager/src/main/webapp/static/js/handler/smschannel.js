/**
 * 通道列表查询 JS逻辑
 * 
 * @author allen.yuan
 * @date 2016-09-22
 */
var UPCLGLoader = {
	params : {
		channelId:'', //通道ID
		channelName : '', // 通道名称
		operateType:  -1, //运营商
		channelType : -1, // 通道类型
		status: -1, // 通道状态
		channelProperty: '', // 通道属性
        createStarttime : '', // 注册开始时间
        createEndtime : '', // 创建结束时间
		pageNum : 1 // 页号
	},
	loadData : function(pageNum) {

		// 构建查询条件
		this.params.channelId  = $('#channelId').val();
		this.params.channelName = $('#channelName').val();
		this.params.operateType = $('#operateType').val();
		this.params.channelType = $('#channelType').val();
		this.params.status = $('#status').val();
		this.params.channelProperty = $('#channelProperty').val();
		//this.params.createStarttime = $('#createStarttime').val();
		//this.params.createEndtime = $('#createEndtime').val();
		if (typeof (pageNum) == 'undefined' || parseInt(pageNum) < 1) {
			this.params.pageNum = 1;
		} else {
			this.params.pageNum = pageNum;
		}

		var loadTags = 'datagrid'; // 加载的目标容器id
		var loadUrl = '/smschannel/smschannel_data';// 请求的url地址
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

	UPCLGLoader.loadData();// 首次加载数据
	$('#btnquery').click(function() { // 点击查询按钮操作
		UPCLGLoader.loadData(1);
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
		UPCLGLoader.loadData(pagenum);// 加载查询
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
		UPCLGLoader.loadData(pagenum);// 加载查询
	});

});

function dosmsChannel(channelId){
   window.location.href="/smschannel/smschannel_show?channelId=" +channelId;
}