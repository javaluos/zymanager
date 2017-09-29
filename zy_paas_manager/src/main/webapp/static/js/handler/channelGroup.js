/**
 * 通道组列表查询 JS逻辑
 *
 * @author peter-zhu
 * @date 2016-09-22
 */
var UPGLoader = {
	params : {
		channelGroupName : '', // 通道组名称
		pageNum: 1// 页号
	},
	loadData : function(pageNum) {
		// 构建查询条件
		this.params.channelGroupName = trim($('#channel_group_name').val());
		this.params.channelId = trim($('#channel_id').val());
		if (typeof (pageNum) == 'undefined' || parseInt(pageNum) < 1) {
			this.params.pageNum = 1;
		} else {
			this.params.pageNum = pageNum;
		}
		
		var loadTags = 'datagrid'; // 加载的目标容器id
		var loadUrl = '/channel_group/list_data';// 请求的url地址
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



function addChannelGroup(channel_group_id){
	window.location.href = "/channel_group/to_add?groupId=" + channel_group_id;
}

function deleteChannelGroup(channel_group_id){
	// window.location.href = "/channel_group/delete?groupId=" + channel_group_id;
	layer.confirm("您确定要删除通道组吗？", {
		area: ['450px'],
		btn: ['确认','取消'] //按钮
	}, function(){
		$.ajax({
			type: "POST",
	   		url: "/channel_group/delete",
	   		data: {groupId:channel_group_id},
		   	success:function(data){
		   		if(data == 'success'){
		   			layer.alert("删除成功");
		   		 	setTimeout('window.location.href="/channel_group/to_list"' , 1000);
		   		}else if (data == 'fail'){
		   			layer.alert("无法删除，通道正在被使用");
		   		}else{
		   			layer.alert("删除失败");
		   		}
		   	}
		});
	});
}

