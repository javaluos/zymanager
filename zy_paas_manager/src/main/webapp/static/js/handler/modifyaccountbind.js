/**
 * 账号列表查询 JS逻辑
 * 
 * @author allen.yuan
 * @date 2016-09-22
 */
var UPGLoader = {
	params : {
		username:'', //企业名称
		pageNum : 1
	// 页号
	},
	loadData : function(pageNum) {
		// 构建查询条件
		this.params.username  = $('#username').val();
		if (typeof (pageNum) == 'undefined' || parseInt(pageNum) < 1) {
			this.params.pageNum = 1;
		} else {
			this.params.pageNum = pageNum;
		}

		var loadTags = 'QueryData'; // 加载的目标容器id
		var loadUrl = '/account_bind/get_bindacc_list';// 请求的url地址
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

var CPGLoader = {
		params : {
			businessname:'', //企业名称
			pageNum : 1
		// 页号
		},
		loadDataC : function(pageNum) {

			// 构建查询条件
			this.params.businessname  = $('#businessname').val();
			if (typeof (pageNum) == 'undefined' || parseInt(pageNum) < 1) {
				this.params.pageNum = 1;
			} else {
				this.params.pageNum = pageNum;
			}

			var loadTags = 'accinfo'; // 加载的目标容器id
			var loadUrl = '/account_bind/acc_list_data';// 请求的url地址
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

	// 点击查询按钮操作
	$('#addbtn').click(function() { 
		$("#toshowdata").hide();
		$("#accinfo").show();
		$("#subbtn").show();
		CPGLoader.loadDataC(1);
	});
	
	
	$('#btnquery').click(function() { 
		CPGLoader.loadDataC(1);
	});
	
	
	/**
	 * 提交查询-按页下标查询
	 */
	/*$("#QueryData").find(".pgbar .link a").click(function(){
		var pagenum = $(this).attr('_pgnum');
		var maxpg = $('#maxpg').val();
		if (parseInt(pagenum) > parseInt(maxpg)) {
			pagenum = maxpg;
		}
		UPGLoader.loadData(pagenum);// 加载查询
	});*/
	$(document).on('click', '.pgbar .link a', function() {
		var pagenum = $(this).attr('_pgnum');
		var maxpg = $('#maxpg').val();
		if (parseInt(pagenum) > parseInt(maxpg)) {
			pagenum = maxpg;
		}
		CPGLoader.loadDataC(pagenum);// 加载查询
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
		CPGLoader.loadDataC(pagenum);// 加载查询
	});

});