/**
 * 账号列表查询 JS逻辑
 * 
 * @author allen.yuan
 * @date 2016-09-22
 */
var UPGLoader = {
	params : {
		merchantphone : '', // 手机号码
		callee: '',// 被叫号码
		state: '',//通话状态
		querytype: -2,// 类型
		hangupCode: '',//通话结束原因
		starttime: '',//发送时间开始
		endtime: '',//发送时间结束
		pageNum: 1// 页号
	},
	loadData : function(pageNum) {
		// 构建查询条件
		this.params.merchantphone = $('#phone').val();
		this.params.callee = $('#callee').val();
		this.params.state= $('#state').val(); 
		this.params.querytype= $('#querytype').val();
		this.params.hangupCode= $('#reason').val();
		this.params.starttime= $('#starttime').val();
		this.params.endtime= $('#endtime').val();
		this.params.querytype=-2;
		
		if (typeof (pageNum) == 'undefined' || parseInt(pageNum) < 1) {
			this.params.pageNum = 1;
		} else {
			this.params.pageNum = pageNum;
		}

		var loadTags = 'datagrid'; // 加载的目标容器id
		var loadUrl = '/directlyCallRecord/directly_call_list_data';// 请求的url地址
		var boxIndex = layer.load(2);// 加载loading框
		if (loadTags != '' && loadTags != '') {
			$('#' + loadTags).load(loadUrl, {
				params : JSON.stringify(this.params)
			}, function() {
				layer.close(boxIndex); // 关闭loading框
			});
		}
	},
	exportData: function(){
		
		// 构建查询条件
		this.params.merchantphone = $('#phone').val();
		this.params.callee = $('#callee').val();
		this.params.state= $('#state').val(); 
		this.params.querytype= $('#querytype').val();
		this.params.hangupCode= $('#reason').val();
		this.params.starttime= $('#starttime').val();
		this.params.endtime= $('#endtime').val();
		this.params.pageNum = 1;
		if(this.params.state==''){
		   this.params.state== -1;
		}
		if(this.params.querytype==''){
		   this.params.querytype== 0;
		}
		
		if(empty(this.params.merchantphone)){
		   layer.tips('【导出提示】:请输入客户手机号码.', '#phone');
		   return false;
		}
		
		if(empty(this.params.starttime)){
			layer.tips('【导出提示】:请选择发送开始时间.', '#starttime');
		   return false;
		}else if(empty(this.params.endtime)){
			var t1 = new Date(this.params.starttime);
			var t2 = new Date();
			var hour = parseInt((t2.getTime() - t1.getTime()) / 1000 / 60 / 60);
		    if(hour> (24*1)){
		    	layer.tips('【导出提示】:如果没有选择发送结束时间 ,发送开始时间只能早于当前时间1天.', '#starttime');
			   return false;
		    }
		}else{
			var t1 = new Date(this.params.starttime);
			var t2 = new Date(this.params.endtime);
			var hour = parseInt((t2.getTime() - t1.getTime()) / 1000 / 60 / 60);
		    if(hour> (24*1)){
		      layer.tips('【导出提示】:发送开始时间与结束时间间隔不能大于1天.', '#endtime');
		      return false;
		    }
		}
		//加载提示框
		expPopuleBox('语音发送记录导出');
		$.ajax({
			  type: 'post',
			  url:'/export/voicefs',
			  dataType:'json',
			  data:{params: JSON.stringify(this.params)},
			  success: function (result) {
	               if(typeof(result)=='undefined' || result.code == -1){ //导出失败
	            	 $('.expopul #eimg').hide();    
	            	 $('.expopul #etips').addClass('rs_error');
	            	 $('.expopul #einfo').html('导出数据操作失败.');
	               }
	               if(result.code == 1){ //导出成功
	            	 $('.expopul #eimg').hide();  
	            	 $('.expopul #etips').addClass('rs_sucss');
	            	 $('.expopul #einfo').html('导出数据操作成功.');
	            	 $('.expopul #ers').show();
	            	 var downUrl = $(".expopul #eurl").attr('_data') + result.data;
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
	
	// 点击导出按钮操作
	$('#btnexport').click(function() { 
		UPGLoader.exportData();//导出数据
	});
	
});

function audit(id,apiAccount){
	window.location.href="/audit/preAudit?id="+id+"&apiAccount="+apiAccount;
}

function detail(id,apiAccount){
	window.location.href="/audit/auditDetail?id="+id+"&apiAccount="+apiAccount;
}

