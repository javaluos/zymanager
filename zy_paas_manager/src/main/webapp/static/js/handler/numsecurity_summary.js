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
		querytype:2,//号码卫士
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
		this.params.querytype=2 ;
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
	},
	exportData: function(){
		// 构建查询条件
		
		this.params.merchantphone = $('#merchantphone').val();
		this.params.dateTimeStart = $('#dateTimeStart').val();
		this.params.dateTimeEnd= $('#dateTimeEnd').val(); 
		this.params.pctCallSucStart= $('#pctCallSucStart').val();
		this.params.pctCallSucEnd= $('#pctCallSucEnd').val();
		this.params.pctResponseSucStart= $('#pctResponseSucStart').val(); 
		this.params.pctResponseSucEnd= $('#pctResponseSucEnd').val();
		this.params.avgAcdStart= $('#avgAcdStart').val();
		this.params.querytype=2 ;
		this.params.pageNum = 1;
		/*if(empty(this.params.merchantphone)){
		   layer.tips('【导出提示】:请输入客户手机号码.', '#merchantphone');
		   return false;
		}*/
		
		if(empty(this.params.dateTimeStart)){
			layer.tips('【导出提示】:请选择开始时间.', '#dateTimeStart');
		   return false;
		}else if(empty(this.params.dateTimeEnd)){
			var t1 = new Date(this.params.dateTimeStart);
			var t2 = new Date();
			var hour = parseInt((t2.getTime() - t1.getTime()) / 1000 / 60 / 60);
		    if(hour> (24*1)){
		    	layer.tips('【导出提示】:如果没有选择结束时间 ,开始时间只能早于当前时间1天.', '#dateTimeStart');
			   return false;
		    }
		}else{
			var t1 = new Date(this.params.dateTimeStart);
			var t2 = new Date(this.params.dateTimeEnd);
			var hour = parseInt((t2.getTime() - t1.getTime()) / 1000 / 60 / 60);
		    if(hour> (24*1)){
		      layer.tips('【导出提示】:开始时间与结束时间间隔不能大于1天.', '#dateTimeEnd');
		      return false;
		    }
		}
		//加载提示框
		expPopuleBox('号码卫士汇总记录导出');
		$.ajax({
			  type: 'post',
			  url:'/numberSecurity/export_summary',
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
