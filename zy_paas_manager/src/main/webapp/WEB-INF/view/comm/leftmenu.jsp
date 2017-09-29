<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

	<div class="wrap_left" id="frmTitle" name="fmTitle">
		<!-- Logo -->
		<div id="Logo">
			<li style="font-size: 20px;font-weight: bold; margin-top: 10px;float: left;padding-left: 10px;">Paas 运营管理平台</li>
			<label style="margin-left: -15px;">V1.0</label>
		</div>
		<!-- /Logo -->

		<!-- menu_list -->
		<div id="leftmudv">

			<div class="menu_list">
				<dl>
					<dt>
						<span>客户管理</span>
					</dt>
					<dd>
						<a href="/account/actlist.html" title="账号信息" class="active">账号信息</a> 
						<a href="javascript:;" title="账号认证">账号认证</a> 
					</dd>
				</dl>
				<dl>
					<dt>
						<span>钱包信息</span>
					</dt>
					<dd>
						<a href="javascript:;" title="余额信息">余额信息</a> 
						<a href="javascript:;" title="资费查询">资费查询</a> 
						<a href="javascript:;" title="资费设置">资费设置</a> 
						<a href="javascript:;" title="充值记录">充值记录</a>
						<a href="javascript:;" title="月结账单">月结账单</a> 
						<a href="javascript:;" title="余额修改">余额修改</a> 
						<a href="javascript:;" title="余额修改记录">余额修改记录</a>  
					</dd>
				</dl>
				<dl>
					<dt>
						<span>详单查询</span>
					</dt>
					<dd>
						<a href="/analysis/cdrlist.html" title="发送记录汇总">发送记录汇总</a> 
						<a href="javascript:;" title="语音记录汇总">语音记录汇总</a> 
						<a href="javascript:;" title="直拨记录汇总">直拨记录汇总</a> 
						<a href="javascript:;" title="回拨记录汇总">回拨记录汇总</a> 
					</dd>
				</dl>
				<dl>
					<dt>
						<span>监控报警</span>
					</dt>
					<dd>
						<a href="javascript:;" title="通知监控">通知监控</a>
						<a href="javascript:;" title="直拨监控">直拨监控</a>
						<a href="javascript:;" title="回拨监控">回拨监控</a>
						<a href="javascript:;" title="余额监控">余额监控</a>
					</dd>
				</dl>
				<dl>
					<dt>
						<span>审核管理</span>
					</dt>
					<dd>
						<a href="/audit/audit_list.html" title="审核列表">审核列表</a>
						<a href="/audit/audit_list.html" title="待审核列表">待审核列表</a>
					</dd>
				</dl>
				<dl>
					<dt>
						<span>账号管理</span>
					</dt>
					<dd>
						<a href="javascript:;" title="账号管理">账号管理</a>
						<a href="javascript:;" title="权限管理">权限管理</a>
					</dd>
				</dl>
				<dl>
					<dt>
						<span>系统配置</span>
					</dt>
					<dd>
						<a href="javascript:;" title="系统配置">系统配置</a>
						<a href="javascript:;" title="缓存管理">缓存管理</a>
					</dd>
				</dl>
			</div>
					  
		</div>
		<!-- /menu_list -->
	</div>	

<script src="/js/side.js" type="text/javascript"></script>
<script type="text/javascript">
$(function() {

	$(".menu_list dl dt").click(function() {
		$(this).toggleClass("open").next().slideToggle("fast");
	});
	$(".menu_list dl dd a").click(function() {
		$(this).parents().find(".menu_list a").removeClass("active");
		$(this).addClass("active");
	});
});

$(function() {
	$(".select").each(
			function() {
				var s = $(this);
				var z = parseInt(s.css("z-index"));
				var dt = $(this).children("dt");
				var dd = $(this).children("dd");
				var _show = function() {
					dd.slideDown(200);
					dt.addClass("cur");
					s.css("z-index", z + 1);
				};
				var _hide = function() {
					dd.slideUp(200);
					dt.removeClass("cur");
					s.css("z-index", z);
				};
				dt.click(function() {
					dd.is(":hidden") ? _show() : _hide();
				});
				dd.find("a").click(function() {
					dt.html($(this).html());
					_hide();
				});
				$("body").click(
						function(i) {
							!$(i.target).parents(".select")
									.first().is(s) ? _hide()
									: "";
						});
			})
})
</script>
