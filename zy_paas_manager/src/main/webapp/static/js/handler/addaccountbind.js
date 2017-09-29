/**
 * 账号列表查询 JS逻辑
 * 
 * @author allen.yuan
 * @date 2016-09-22
 */
var UPGLoader = {
	params : {
		businessname:'', //企业名称
		pageNum : 1
	// 页号
	},
	loadData : function(pageNum) {

		// 构建查询条件
		this.params.businessname  = $('#businessname').val();
		if (typeof (pageNum) == 'undefined' || parseInt(pageNum) < 1) {
			this.params.pageNum = 1;
		} else {
			this.params.pageNum = pageNum;
		}

		var loadTags = 'accountdata'; // 加载的目标容器id
		var loadUrl = '/account_bind/add_account_bind';// 请求的url地址
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

var UPGLoader1 = {
		params : {
			fullname:'', //企业名称
			pageNum : 1
		// 页号
		},
		loadData : function(pageNum) {

			// 构建查询条件
			this.params.fullname  = $('#fullname').val();
			if (typeof (pageNum) == 'undefined' || parseInt(pageNum) < 1) {
				this.params.pageNum = 1;
			} else {
				this.params.pageNum = pageNum;
			}

			var loadTags = 'userdata'; // 加载的目标容器id
			var loadUrl = '/account_bind/user_list_data';// 请求的url地址
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
	
	$('#search').click(function() { // 点击查询按钮操作
		var businessname = $("#businessname").val();
		if(businessname.trim() == ''){
			 layer.tips('请输入客户名称查询.', $('#businessname'));
			 $('#businessname').focus();
			 return false;
		}
		UPGLoader.loadData(1);
	});
	
	$('#queryuser').click(function() { // 点击查询按钮操作
		var fullname = $("#fullname").val();
		if(fullname.trim() == ''){
			 layer.tips('请输入真实姓名查询.', $('#fullname'));
			 $('#fullname').focus();
			 return false;
		}
		UPGLoader1.loadData(1);
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

});