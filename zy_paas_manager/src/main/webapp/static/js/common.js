
/**
 * 判断值是否为空
 * @param _val
 * @returns {Boolean}
 */
function empty(_val) {  
 return (_val.replace(/(^\s*)|(\s*$)/g,'')== '');
}

String.prototype.trim=function(){
    return this.replace(/(^\s*)|(\s*$)/g, "");
 }
 String.prototype.ltrim=function(){
    return this.replace(/(^\s*)/g,"");
 }
 String.prototype.rtrim=function(){
    return this.replace(/(\s*$)/g,"");
 }
 
/**
 * 系统提示框
 * 
 * @param mssage 消息内容
 */
function messageBox(mssage){
	//在这里面输入任何合法的js语句
	layer.open({
	  type: 1, //page层
	  area: ['430px', '200px'],
	  title: '系统提示',
	  shade: 0.3, //遮罩透明度
	  moveType: 1, //拖拽风格，0是默认，1是传统拖动
	  shift: 1, //0-6的动画形式，-1不开启
	  content: '<div style="padding:50px;">'+ mssage +'</div>'
	}); 
}

/**
 * 系统提示框
 * @param title 消息标题
 * @param mssage 消息内容
 */
function messageBox(title, mssage){
	//在这里面输入任何合法的js语句
	layer.open({
	  type: 1, //page层
	  area: ['430px', '200px'],
	  title: title,
	  shade: 0.3, //遮罩透明度
	  moveType: 1, //拖拽风格，0是默认，1是传统拖动
	  shift: 1, //0-6的动画形式，-1不开启
	  content: '<div style="padding:50px;">'+ mssage +'</div>'
	}); 
}

/**
 * 系统确认框
 * @param mssage 消息内容
 */
function confirmBox(mssage){
	
	//询问框
	layer.confirm(mssage, {
	  btn: ['取消','确认'] //按钮
	}, function(){
	  layer.msg('已取消', {icon: 0});
	}, function(){
	  layer.msg('已确认', {icon: 0}); 
	});
}

/**
 * 导出提示框
 * 
 * @param title 消息标题
 */
function expPopuleBox(title){
	var mht  = '<div class="expopul"><li><img id="eimg" src="/images/common/loading0.gif"></li>';
	    mht += '<li><span id="etips"></span>&nbsp;&nbsp;<span id="einfo">正在导出数据中...</span></li>';
	    mht += '<li><span id="ers"><a id="eurl" href="javascript:;" _data="/export/down?downUrl=">请点击此处下载文件</a></span></li>';
	    mht += '<li><iframe id="eframe" src="" style="display:none;visibility:hidden;"></iframe></li>';
	    mht += '</div>';
	layer.open({
	  type: 1, //page层
	  area: ['430px', '200px'],
	  title: title,
	  shade: 0.3, //遮罩透明度
	  moveType: 1, //拖拽风格，0是默认，1是传统拖动
	  shift: 1, //0-6的动画形式，-1不开启
	  content: '<div style="padding:50px; padding-top:20px;">'+ mht+ '</div>'
	}); 
}