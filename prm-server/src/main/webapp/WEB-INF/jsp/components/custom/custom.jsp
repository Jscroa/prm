<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div>
	<legend id="theme-legend">客户管理</legend>
</div>
<button class="btn btn-primary" data-toggle="modal"
	data-target="#dia-custom">添加</button>
<button class="btn btn-danger">删除</button>

<!-- 添加窗口 -->
<div class="modal fade" id="dia-custom" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<form class="form-horizontal" name="login_form"
		onSubmit="return false;">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">添加客户</h4>
				</div>
				<div class="modal-body">

					<div class="form-group">
						<label for="inputEmail" class="col-sm-3 control-label">姓名：</label>
						<div class="col-sm-9">
							<input type="text" name="name" class="form-control"
								placeholder="姓名" id="inputName" required>
						</div>
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary">确定</button>
				</div>
			</div>
		</div>
	</form>
</div>