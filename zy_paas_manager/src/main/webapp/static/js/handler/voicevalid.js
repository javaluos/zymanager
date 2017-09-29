/**
 * 验证文本域是否为空,大小
 * 
 * @returns {Boolean}
 */
function validFile(){

	var uf = $("#uploadFileId").val();
    /*if(uf == ""){
	   layer.tips('请选择语音文件.', $('#choicefile'));
	   $('#choicefile').focus();
	   return false;
	}*/
	var filepath = uf;
    var extStart=filepath.lastIndexOf(".");
    var ext=filepath.substring(extStart,filepath.length).toUpperCase();
    if(ext!=".WAV" && ext!=".MP3"){
  	  layer.tips('语音文件仅限于wav或Mp3格式.', $('#fileurl'));
	  $('#fileurl').focus();
      return false;
    }
        
    var size = 0;
    try { 
	    if ($.browser.msie) {//ie旧版浏览器
	    	
		    	var fileMgr = new ActiveXObject("Scripting.FileSystemObject");
		        var filePath = $('#uploadFileId')[0].value;
		        var fileObj = fileMgr.getFile(filePath);
		        size = fileObj.size; //byte
		        size = size / 1024;//kb
		        //size = size / 1024;//mb
	    	
	    	
	    } else {//其它浏览器
	        size = $('#uploadFileId')[0].files[0].size;//byte
	        size = size / 1024;//kb
	        //size = size / 1024;//mb
	    }
	} 
	catch (e) { 
		
	}
    size = size.toFixed(2);
   /* if(size > (5*1024)){ //大于5MB
    	layer.tips('语音文件不能大于5MB.', $('#fileurl'));
    	$('#fileurl').focus();
        return false;
    }*/
    return true;
}

$(function(){
	
	$('#fileName').blur(function(){
		 if($(this).val().trim() == ""){
		   layer.tips('请输入文件名称.', this);
		   return false;
		 }
	});
	
	$('#content').blur(function(){
		 if($(this).val().trim() == ""){
		   layer.tips('请输入语音文本.', this);
		   return false;
		 }
	});
	
});
 