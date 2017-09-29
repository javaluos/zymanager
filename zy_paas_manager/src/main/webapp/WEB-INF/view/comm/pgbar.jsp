<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="row" style="text-align: center;margin-right: auto; margin-left: auto;">
	<div class="col-sm-11" style="text-align：left;">
		<div class="dataTables_info pgnum" id="DataTables_Table_0_info"
			role="alert" aria-live="polite" aria-relevant="all" style="margin-top: 5px;float: left;">
			<li style="border: none;">&nbsp;共计 <span style="color: red;">${pgdata.total}</span>
				条
			</li>
			<li style="border: none;">&nbsp;每页 <span style="color: red;">${pgdata.page_size}</span>
				条
			</li>
			<li style="border: none;">&nbsp;<span style="color: red;">${pgdata.page_num}</span>
				/ <span style="color: red;">${pgdata.total_page}</span> 页&nbsp;
			</li>
		</div>
	
		<div class="dataTables_paginate paging_simple_numbers"
			id="DataTables_Table_0_paginate">
			<ul class="pagination pgbar">
				<li class="link paginate_button previous"
					aria-controls="DataTables_Table_0" tabindex="0"
					
					id="DataTables_Table_0_previous">
					<a href="javascript:;"
					_pgnum="1">首页</a>
				</li>
				<li class="link paginate_button previous"
					aria-controls="DataTables_Table_0" tabindex="0"
					id="DataTables_Table_0_previous"><a href="javascript:;"
					_pgnum="${pgdata.page_num-1 }">上一页</a></li>

				<c:forEach var="item" begin="1" end="${pgdata.viewcount }" step="1"
					varStatus="flag">
					<li
						class="link paginate_button "
						aria-controls="DataTables_Table_0" tabindex="0"><a
						href="javascript:;" _pgnum="${pgdata.pgstartno + flag.index}">${pgdata.pgstartno + flag.index}</a>
					</li>
				</c:forEach>

				<li class="link paginate_button next" aria-controls="DataTables_Table_0"
					tabindex="0" id="DataTables_Table_0_next"><a
					href="javascript:;" _pgnum="${pgdata.page_num+1 }">下一页</a></li>
				<li class="link paginate_button next" aria-controls="DataTables_Table_0"
					tabindex="0" id="DataTables_Table_0_next"><a
					href="javascript:;" _pgnum="${pgdata.total_page }">尾页</a></li>

				<li><input type="text" class="input20" id="pageNum"
					style="width: 60px; display: inline-block; line-height: 28px; height: 28px; margin-left: 1px;"
					value="${pgdata.page_num}"
					onkeyup="this.value=this.value.replace(/\D/g,'')"
					onafterpaste="this.value=this.value.replace(/\D/g,'')"></li>
				<li><button type="button" class="btn btn-white" id="pggobtn"
						style="text-align: center;line-height: 28px; height: 28px; padding: 0px 10px; margin-top: -3px; margin-left: -4px;">GO</button>
					<input type="hidden" id="maxpg" value="${pgdata.total_page}"></li>
			</ul>
		</div>
	</div>
</div>

