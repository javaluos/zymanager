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
	<div class="col-sm-12" style="border-bottom: 1px solid #e5e6e7; padding-bottom: -20px;margin-bottom: 10px;">
		<div class="ibox">
			<div class="ibox-title">
				<h5>
					系统设置<small>&nbsp;-->&nbsp;敏感词过滤</small>
				</h5>
			</div>
			<div class="contentdv">
			   <div style="padding-left: 10px;">
	               <form class="form-horizontal m-t" id="myForm" method="post"  novalidate="novalidate">
                         <div class="form-group">
                             <label class="col-sm-2 control-label">所属行业：</label>
                             <div class="col-sm-3">
                                 <input id="industry" name="industry" class="form-control" type="text" value="" maxlength="50"
                                      style="display: inline-block;">
                                 <input type="hidden" id="policyId" class="form-control" value="${policyId}" style="width:220px;">
                                 <input type="hidden" id="policyName" class="form-control" value="${policyName}" style="width:220px;">
                             </div>
                         </div>
                         <div class="form-group">
                         	 <label class="col-sm-2 control-label"><span style="color: red; vertical-align: -3px;">*&nbsp;</span>敏感词内容：</label>
                             <div class="col-sm-3">
                                 <textarea id="black_keys" name="black_keys" class="form-control" required="" 
                                 aria-required="true" style="height: 100px;"></textarea>
                             </div>
                         </div>
                         <div class="form-group">
                             <label class="col-sm-2 control-label">备注：</label>
                             <div class="col-sm-3">
                                 <textarea id="remark" name="remark" class="form-control" required="" 
                                 aria-required="true" style="height: 100px;"  maxlength="200"></textarea>
                             </div>
                         </div>
                         <div class="form-group">
                             <div class="col-sm-3 col-sm-offset-3">
                             	 <button class="btn btn-primary" type="button" style="width: 60px;margin-left: 20px;" id="btnback">返回</button>
                                 <button class="btn btn-primary" type="button" style="width: 60px;margin-left: 20px;" id="btnsave">保存</button>
                                 <div id="msg" style="color: red;margin-top:10px;"></div>
                             </div>
                         </div>
                     </form>
                     </div>
                 </div>
		</div>
	</div>
	<script type="text/javascript" src="/js/handler/blackkeyadd.js"></script> 
	<script type="text/javascript">
	
	
	
	</script>
</body>
</html>
