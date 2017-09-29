/**
 * 黑名单组列表查询 JS逻辑
 * 
 * @author luos
 * @date 2017-09-07
 */
var UPGLoader = {
	params : {
		groupName:'', //黑名单组
		pageNum : 1
	// 页号
	},
	loadData : function(pageNum) {

		// 构建查询条件
		this.params.groupName  = $('#groupname').val();
		if (typeof (pageNum) == 'undefined' || parseInt(pageNum) < 1) {
			this.params.pageNum = 1;
		} else {
			this.params.pageNum = pageNum;
		}

		var loadTags = 'datagrid'; // 加载的目标容器id
		var loadUrl = '/sms_black_group/list_data';// 请求的url地址
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
        layer.open({
            type: 2, //page层
            area: ['620px', '350px'],
            title: '短信黑名单组添加',
            shade: 0.3, //遮罩透明度
            moveType: 0, //拖拽风格，0是默认，1是传统拖动
            shift: 1, //0-6的动画形式，-1不开启
            scrollbar:false,
            content:'/sms_black_group/to_add/'
        });
    });

});
function edit(id){
    layer.open({
        type: 2, //page层
        area: ['620px', '350px'],
        title: '短信黑名单组添加',
        shade: 0.3, //遮罩透明度
        moveType: 0, //拖拽风格，0是默认，1是传统拖动
        shift: 1, //0-6的动画形式，-1不开启
        scrollbar:false,
        content:'/sms_black_group/to_edit/'+id+''
    });
}
function del(id){
    layer.confirm("您确定删除该黑名单组吗？", {
        area: ['450px'],
        btn: ['确认','取消'] //按钮
    }, function(){
        $.ajax({
            type: "POST",
            url: "/sms_black_group/del_group/"+id+"",
            dataType: "json",
            success: function(data){
                if(data > 0){
                    layer.closeAll('dialog');
                    $("#btnquery").click();
                }else if(data == -1){
                    layer.alert("当前黑名单组已有绑定关系，不允许删除");
                }else{
                    layer.alert("删除黑名单组失败");
                }
            }
        });
    });
}

