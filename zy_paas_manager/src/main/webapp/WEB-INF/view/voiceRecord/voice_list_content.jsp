<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE HTML>
<html>
<head>
<title>Paas运营管理平台</title>
<jsp:include page="../comm/plugin.jsp" />
</head>
<body>

	<!-- QueryForm -->
	<div class="col-sm-12" style="padding-bottom: -20px;margin-bottom: 10px;">
		<div class="ibox">
			<div class="ibox-title">
				<h5>
					详单查询<small>&nbsp;-->&nbsp;语音发送记录</small><small>&nbsp;-->&nbsp;语音文件信息</small>
				</h5>
			</div>
			<div class="contentdv">
			   <div style="margin-left: -150px;">
	               <form class="form-horizontal m-t" id="signupForm" method="post" action="" enctype = "multipart/form-data" novalidate="novalidate">
                         <div class="form-group">
                             <label class="col-sm-2 control-label">语音文件：</label>
                             <label class="col-sm-2 control-label"  style="height: auto;text-align: left;">
                                 
                                 <c:forEach var="dt" items="${pgdata.dataMap}" varStatus="dtindex">
                                    <a href="javascript:;" onclick="$('#dfm').attr('src','${dt.value}');">${dt.key}</a>&nbsp;&nbsp;&nbsp;&nbsp;
                                 </c:forEach>
                             </label>
                             <iframe id="dfm" src="" style="display:none; visibility:hidden;"></iframe>   
                         </div>
                         <div class="form-group">
                             <label class="col-sm-2 control-label">文本内容：</label>
                             <label class="col-sm-2 control-label" style="height: auto; text-align: left;">
                                    ${pgdata.content}
                             </label>
                         </div>
                         
                         <div class="form-group">
                             <label class="col-sm-2 control-label">验证码：</label>
                             <label class="col-sm-2 control-label" style="height: auto; text-align: left;">
                                    ${pgdata.verify_code}
                             </label>
                         </div>
                          
                         <div class="form-group">
                             <div class="col-sm-2 col-sm-offset-2">
                                 <button class="btn btn-primary" type="button" style="width: 60px;margin-left: 20px;" onclick="history.go(-1)" id="btnsave">返回</button>
                             </div>
                         </div>
                     </form>
                  </div>
              </div>
		</div>
	</div>	 
</body>
</html>
