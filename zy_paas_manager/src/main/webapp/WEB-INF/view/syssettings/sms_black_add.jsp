<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
<head>
<title>Paas运营管理平台</title>
<jsp:include page="../comm/plugin.jsp" />
</head>

<body>

	<!-- QueryForm -->
	<div class="col-sm-4"
		style="padding-bottom: -20px; margin-bottom: 10px;">
		    <div class="ibox">
				<div class="ibox-title">
					<h5>
						短信黑名单<small>&nbsp;-->&nbsp;添加</small>
					</h5>
				</div>
			<div>
		    <table class="noborder" style="margin-left: 20px;width:800px">
		        <input type="hidden" id="groupId" value="${groupId}"/>
				<tbody>
					<tr class="noline">
						<td class="fr" style="width: 80px;margin-left: 20px;">所属客户:</td>
                        <td class="online" style="width: 400px">
                          <input type="text" id="businessName" class="form-control" style="width: 250px;">
                        </td>
                        <td class="online" style="width: 120px">
                        </td>
					</tr>
					
					<tr class="noline">
						<td class="fr" style="width: 80px; margin-left: 20px"><span style="color: red;">*</span>黑名单号码:</td>
                        <td class="online" style="width: 400px">
                            <textarea id="mobile" class="form-control" required="" aria-required="true"  style="width:400px;height: 100px;"></textarea>
                            <span style="color: red;" id="errmsg"></span>
                        </td>
                        <td class="online" style="width: 120px;" valign="middle">
                            <font style="width:200px;color:red;margin-left:-120px">提示：多个号码之间使用英语逗号隔开</font>
                        </td>
				    </tr>
				    
				    <tr class="noline">
						<td class="fr" style="width: 80px; margin-left: 20px">备注:</td>
                        <td class="online" style="width:400px">
                           <textarea id="remark" class="form-control" required="" aria-required="true"  style="width:400px;height: 100px;"></textarea>
                        </td>
                        <td class="online" style="width: 120px">
                        </td>
				    </tr>
				</tbody>
			</table>
			<div style="margin-top:20px;" class="form-group">
                <input type="hidden" id="deptName" name="deptName" >
                <button class="btn btn-primary" type="button" style="width: 60px;margin-left: 160px;" onclick="history.go(-1)" id="btnback">取消</button>
                <button class="btn btn-primary" type="button" style="width: 60px;margin-left: 60px;" id="btnsave" onclick="dosubmit();">添加</button>
           	</div>
		</div>
	</div>
</body>

<script type="text/javascript">
	function dosubmit(){
	    $("#errmsg").text("");
		var businessname=$('#businessName').val();
		var mobile=$('#mobile').val();
		var remark=$('#remark').val();
		var groupId = $('#groupId').val();
		if(businessname.length > 20){
		    layer.tips("所属客户名称长度不能大于20", $('#businessName'));
            return ;
		}
		if(mobile==null||mobile==''){
			layer.tips("请输入黑名单号码!", $('#mobile'));
			return ;
		}
        var errmsg = checkphone(mobile);
        if(errmsg != null && errmsg != ''){
            $("#errmsg").text("号码" + errmsg + "已加入白名单");
            return;
        }
		$.ajax({
			type: "POST",
	   		url: "/sms_black_list/do_add/"+groupId+"",
	   		data: {"businessname" : businessname, "mobile" : mobile,"remark":remark},
	   		dataType: "json",
		   	success: function(data){
		   		if(data>0){
		   			layer.alert("添加黑名单号码成功,总计添加"+data+"个！");
		   		 	setTimeout('window.location.href="/sms_black_list/to_list/'+groupId+'"', 2000)
		   		}else{
		   			layer.tips("请输入正确的手机号码!", $('#mobile'));
		   			$("#btnsave").attr("disabled", false);
		   		}
		   	}
		});
	}

	function checkphone(mobile){
        var phones;
        $.ajax({
            async: false,
            type: "POST",
            url: "/sms_black_list/check_phone",
            data: {"mobiles" : mobile},
            dataType: "text",
            success: function(data){
                phones = data;
            }
        });
        return phones;
    }
</script>
</html>
