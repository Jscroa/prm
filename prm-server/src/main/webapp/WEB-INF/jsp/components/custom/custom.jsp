<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<script type="text/javascript">
	
	var _customTableConfig = new TableConfig(
			'/api/custom/list',
			[
					{
						checkbox : true, // 使用复选框
						field : 'state',
						align : 'center',
						valign : 'middle'
					},
					{
						title : '姓名',
						field : 'name',
						align : 'left',
						valign : 'middle',
						formatter : function(value, row, index) {
							var str = '<a href="#">' + value + '</a>';
							return value;
						}
					},
					{
						title : '手机',
						field : 'phone',
						align : 'left',
						valign : 'middle'
					},
					{
						title : 'QQ',
						field : 'qq',
						align : 'left',
						valign : 'middle'
					},
					{
						title : '微信',
						field : 'weixin',
						align : 'left',
						valign : 'middle'
					},
					{
						title : '邮箱',
						field : 'email',
						align : 'left',
						valign : 'middle',
						formatter : function(value, row, index) {
							var str = '<a href="#">' + value + '</a>';
							return value;
						}
					},
					{
						title : '地址',
						field : 'addr',
						align : 'left',
						valign : 'middle'
					},
					{
						title : '操作',
						field : 'id',
						align : 'center',
						valign : 'middle',
						formatter : function(value, row, index) {
							var a = '<button class="btn btn-link btn-sm" onclick="modifyCustom('
									+ value
									+ ')"><span class="glyphicon glyphicon-edit"></span>编辑</button>';
							return a;
						}
					} ]);

	var controller = {
		loadData : function() {
			$('#custom_table').bootstrapTable(_customTableConfig);
		}
	};

	function clickAdd() {

	}

	function clickModify(id) {

	}

	function addCustom() {
		$('#custom_form').ajaxSubmit({
			url : '/api/custom/add',
			type : 'post',
			dataType : 'json',
			data : $("#form1").serialize(),
			success : function(data) {
				if (data) {
					if (data.code == 100) {
						toastr.success('添加成功');
						$('#custom_form')[0].reset();
						$('#dia-custom').modal('hide');
						$('#custom_table').bootstrapTable('refresh');
					} else {
						toastr.warning(data.code + ':' + data.msg);
					}
				} else {
					toastr.warning('服务器未响应');
				}
			}
		});
	}

	function delCustoms() {
		var selects = $('#custom_table').bootstrapTable('getSelections');
		if (selects.length == 0) {
			toastr.warning('未选中任何数据');
			return;
		}
		var ids = new Array();
		for ( var i in selects) {
			var select = selects[i];
			ids[i] = select.id;

		}
		delCustom(ids, 0);
	}
	// 递归删除
	function delCustom(ids, index) {
		if (index<0 || index >= ids.length) {
			toastr.success('ok');
			$('#custom_table').bootstrapTable('refresh');
		} else {
			$.ajax({
				url : '/api/custom/del',
				type : 'post',
				dataType : 'json',
				data : {
					id : ids[index]
				},
				success : function(data) {
					if (data) {
						if (data.code == 100) {
							// 成功
							toastr.success('id -> ' + ids[index] + ' 删除成功!');
							delCustom(ids, index + 1);
						} else {
							toastr.warning(data.code + ':' + data.msg);
							delCustom(ids, index + 1);
							// 询问是否继续
						}
					} else {
						// 未响应
						toastr.warning('id -> ' + ids[index] + ' 未响应!');
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					toastr.error(XMLHttpRequest.status);
				}
			});
		}
	}

	function modifyCustom(value) {

	}

	function saveCustom() {

	}

	$(function() {
		controller.loadData();
	});
</script>


<legend id="theme-legend" class="text-default">
	<span class="glyphicon glyphicon-tags">&nbsp;</span>客户管理
</legend>

<div class="btn-toolbar" role="toolbar" id="custom-table-toolbar">
	<div class="btn-group" role="group">
		<button class="btn btn-primary" data-toggle="modal"
			data-target="#dia-custom">
			<span class="glyphicon glyphicon-plus"></span>添加
		</button>
		<button class="btn btn-danger" onclick="delCustoms()">
			<span class="glyphicon glyphicon-minus"></span>删除
		</button>
	</div>
	<div id="select-control-group" class="btn-group" role="group"></div>
</div>

<table id="custom_table" class="table table-condensed"></table>

<!-- 添加窗口 -->
<div class="modal fade" id="dia-custom" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<form class="form-horizontal" name="custom_form" id="custom_form"
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
						<label for="inputName" class="col-sm-3 control-label">姓名：</label>
						<div class="col-sm-9">
							<input type="text" name="name" class="form-control"
								placeholder="Name" id="inputName" required>
						</div>
					</div>

					<div class="form-group">
						<label for="inputSex" class="col-sm-3 control-label">性别：</label>
						<div class="col-sm-9">
							<div class="radio">
								<label> <input type="radio" name="sex" id="inputSex"
									value="true" checked /> 男
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
									type="radio" name="sex" id="inputSex" value="false" checked />
									女
								</label>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="inputPhone" class="col-sm-3 control-label">手机：</label>
						<div class="col-sm-9">
							<input type="text" name="phone" class="form-control"
								placeholder="Phone" id="inputPhone" required>
						</div>
					</div>

					<div class="form-group">
						<label for="inputEmail" class="col-sm-3 control-label">邮箱：</label>
						<div class="col-sm-9">
							<input type="text" name="email" class="form-control"
								placeholder="Email" id="inputEmail" required>
						</div>
					</div>

					<div class="form-group">
						<label for="inputAddr" class="col-sm-3 control-label">地址：</label>
						<div class="col-sm-9">
							<input type="text" name="addr" class="form-control"
								placeholder="Address" id="inputAddr" required>
						</div>
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" onclick="addCustom()">确定</button>
				</div>
			</div>
		</div>
	</form>
</div>