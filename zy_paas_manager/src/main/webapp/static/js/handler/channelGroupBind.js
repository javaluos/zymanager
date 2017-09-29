/**
 * 通道组客户绑定列表查询 JS逻辑
 *
 * @author allen.yuan
 * @date 2017-07-11
 */
var UPGLoader = {
	params : {
		businessName : '', // 通道组名称
		merchantPhone : '',
		startTime : '',
		endTime : '',
		pageNum: 1// 页号
	},
	loadData : function(pageNum) {
		// 构建查询条件
		this.params.businessName = trim($('#business_name').val());
		this.params.merchantPhone = trim($('#merchant_phone').val());
		this.params.startTime= $('#start_time').val(); 
		this.params.endTime= $('#end_time').val();

		if (typeof (pageNum) == 'undefined' || parseInt(pageNum) < 1) {
			this.params.pageNum = 1;
		} else {
			this.params.pageNum = pageNum;
		}

		var loadTags = 'datagrid'; // 加载的目标容器id
		var loadUrl = '/channel_group/bind_list_data';// 请求的url地址
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

function trim(str){
　　return str.replace(/(^\s*)|(\s*$)/g, "");
}

$(function() {

	UPGLoader.loadData(1);// 首次加载数据
	$('#btn_query').click(function() { // 点击查询按钮操作
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
	$('#btnadd').click(function() {
		window.location.href="/channelgroup/to_add";
	});

});



function saveChannelGroupBind(bind_id){
	window.location.href = "/channel_group/bind_add?bindId=" + bind_id;
}

function deleteChannelGroupBind(api_account,bind_id){
	layer.confirm("您确定要解除通道组和用户的绑定吗？", {
		area: ['450px'],
		btn: ['确认','取消'] //按钮
	}, function(){
		// window.location.href = "/channel_group/deleteMerchantGroupBind?bindId=" + bind_id;
		$.ajax({
			type: "POST",
	   		url: "/channel_group/deleteMerchantGroupBind",
	   		data: {apiAccount:api_account,bindId:bind_id},
		   	success:function(data){
		   		if(data == 'success'){
		   			layer.alert("解除成功");
		   		 	setTimeout('window.location.href="/channel_group/bind_list"' , 1000);
		   		}else{
		   			layer.alert("解除失败");
		   		}
		   	}
		});
	});
}

