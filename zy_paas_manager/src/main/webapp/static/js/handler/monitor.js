/**
 * 账号列表查询 JS逻辑
 * 
 * @author allen.yuan
 * @date 2016-09-22
 */
var UPGLoader = {
	params : {
		businessName : '', // 客户名称
		merchantPhone : '', // 客户名称
		pageNum : 1         // 页号
	},
	loadData : function(pageNum) {
		// 构建查询条件
		this.params.businessName = $('#businessName').val();
		this.params.merchantPhone = $('#phone').val();
		
		if (typeof (pageNum) == 'undefined' || parseInt(pageNum) < 1) {
			this.params.pageNum = 1;
		} else {
			this.params.pageNum = pageNum;
		}

		var loadTags = 'datagrid'; // 加载的目标容器id
		var loadUrl = '/monitor/monitor_list_data';// 请求的url地址
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
	
	
	$('#btncontroller').click(function() { // 点击添加监控客户
		layer.open({
			  type: 2, //page层
			  area: ['630px', '300px'],
			  title: '客户查询',
			  shade: 0.3, //遮罩透明度
			  moveType: 0, //拖拽风格，0是默认，1是传统拖动
			  shift: 1, //0-6的动画形式，-1不开启
			  content:'/monitor/monitor_user_add'
		   }); 
	});
	
});

function save(){
	var apiAccount=$("#apiAccount").val();
	if(apiAccount==undefined){
		return false;
	}
	window.location.href="/monitor/save_accountmonitor?apiAccount="+apiAccount;
}

function cancel(){
	window.location.href="/monitor/monitor_list";
}

function start(id){
	//询问框
	layer.confirm("您确定要启动业务监控吗？", {
		btn: ['确认','取消'] //按钮
	}, function(){
		window.location.href="/monitor/start_monitor?id="+id;
	}, function(){
		layer.closeAll('dialog');
	});
}

function stop(id){
	//询问框
	layer.confirm("您确定要暂停业务监控吗？", {
		btn: ['确认','取消'] //按钮
	}, function(){
		window.location.href="/monitor/stop_monitor?id="+id;
	}, function(){
		layer.closeAll('dialog');
	});
}

function del(id,monitorStatus){
	if(monitorStatus==1){
		//询问框
		layer.confirm("该客户已启用该业务监控，您确定要删除吗？", {
			btn: ['确认','取消'] //按钮
		}, function(){
			window.location.href="/monitor/delete_monitor?id="+id;
		}, function(){
			layer.closeAll('dialog');
		});
	}else{
		//询问框
		layer.confirm("您确定要删除业务监控吗？", {
			btn: ['确认','取消'] //按钮
		}, function(){
			window.location.href="/monitor/delete_monitor?id="+id;
		}, function(){
			layer.closeAll('dialog');
		});
	}
}

function notifysetting(id){
   layer.open({
	  type: 2, //page层
	  area: ['630px', '400px'],
	  title: '报警通知设置',
	  shade: 0.3, //遮罩透明度
	  moveType: 0, //拖拽风格，0是默认，1是传统拖动
	  shift: 1, //0-6的动画形式，-1不开启
	  scrollbar:false,
	  content:'/monitor/notification_setting?id='+id
   }); 
}