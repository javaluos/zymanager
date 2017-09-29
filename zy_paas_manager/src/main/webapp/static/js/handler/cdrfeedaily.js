﻿/**
 * 月结账单列表查询 JS逻辑
 * 
 * @author allen.yuan
 * @date 2016-09-22
 */
var UPGLoader = {
	params : {
		dateTime: '',//统计时间
		apiAccount:'',//账户apiAccount
		pageNum: 1// 页号
	},
	loadData : function(pageNum) {
		// 构建查询条件
		this.params.dateTime= $('#dateTime').val();
		this.params.apiAccount=$('#apiAccount').val();
		if (typeof (pageNum) == 'undefined' || parseInt(pageNum) < 1) {
			this.params.pageNum = 1;
		} else {
			this.params.pageNum = pageNum;
		}

		var loadTags = 'datagrid'; // 加载的目标容器id
		var loadUrl = '/cdrfeemonth/cdrfeedaily_list_data';// 请求的url地址
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
		this.params.businessName = $('#businessName').val();
		this.params.dateTime= $('#dateTime').val();
		this.params.pageNum = 1;
		
		//加载提示框
		expPopuleBox('日结详单记录导出');
		$.ajax({
			  type: 'post',
			  url:'/cdrfeemonth/dayexport',
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
		var dateTime=$('#dateTime').val();
		if(dateTime==''||dateTime==undefined){
			layer.alert("请选择时间！");
			return false;
		}
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
		var dateTime=$('#dateTime').val();
		if(dateTime==''||dateTime==undefined){
			layer.alert("请选择时间！");
			return false;
		}
		UPGLoader.exportData();//导出数据
	});
	
});

function audit(id,apiAccount){
	window.location.href="/audit/preAudit?id="+id+"&apiAccount="+apiAccount;
}

function detail(id,apiAccount){
	window.location.href="/audit/auditDetail?id="+id+"&apiAccount="+apiAccount;
}

