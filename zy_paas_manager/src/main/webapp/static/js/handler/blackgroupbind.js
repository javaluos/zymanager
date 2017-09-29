/**
 * 黑名单组绑定列表查询 JS逻辑
 * 
 * @author luos
 * @date 2017-09-07
 */
var UPGLoader = {
	params : {
		groupName : '', //黑名单组
		merchantPhone : '', //用户账号
		pageNum : 1
	// 页号
	},
	loadData : function(pageNum) {

		// 构建查询条件
		this.params.groupName  = $('#groupname').val();
		this.params.merchantPhone  = $('#merchantphone').val();
		if (typeof (pageNum) == 'undefined' || parseInt(pageNum) < 1) {
			this.params.pageNum = 1;
		} else {
			this.params.pageNum = pageNum;
		}

		var loadTags = 'datagrid'; // 加载的目标容器id
		var loadUrl = '/black_group_bind/list_data';// 请求的url地址
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


	$('#btnadd').click(function() { // 点击查询按钮操作
        window.location.href = '/black_group_bind/to_add/';
    });

});
function edit(id){
    window.location.href = "/black_group_bind/to_edit/"+id+"";
}
function delbind(id){
    layer.confirm("您确定删除该绑定关系吗？", {
        area: ['450px'],
        btn: ['确认','取消'] //按钮
    }, function(){
        $.ajax({
            type: "POST",
            url: "/black_group_bind/del_bind/"+id+"",
            dataType: "json",
            success: function(data){
                if(data > 0){
                    layer.closeAll('dialog');
                    $("#btnquery").click();
                }else{
                    layer.alert("删除绑定关系失败");
                }
            }
        });
    });

}
