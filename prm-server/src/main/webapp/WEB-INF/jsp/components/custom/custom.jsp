<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<script type="text/javascript">
	$(function() {
		loadData();
	});

	function loadData() {
		var $table = $('#custom_table');
		$table.bootstrapTable({
			url : '/api/custom/list', // 请求地址
			dataType : "json", // 数据格式
			toolbar:'#custom-table-toolbar', // 工具栏
			cache: false, // 关闭缓存
			pagination : true, // 分页
			singleSelect : false, // 非单选
			search : true, // 搜索框
			sidePagination : "server", // 服务端分页
			showColumns: true, // 开启选择显示列
			smartDisplay: true, // 智能显示
			clickToSelect: true, // 单击行选中
			responseHandler: responseHandler, // 返回处理
			columns : [ {
		        field: 'state',
				align : 'center',
				valign : 'middle',
		        checkbox: true // 使用复选框
		    }, {
				title : '姓名',
				field : 'name',
				align : 'left',
				valign : 'middle'
			}, {
				title : '手机',
				field : 'phone',
				align : 'left',
				valign : 'middle'
			}, {
				title : '邮箱',
				field : 'email',
				align : 'left',
				valign : 'middle'
			}, {
				title : '地址',
				field : 'addr',
				align : 'left',
				valign : 'middle'
			},{
				title : '操作',
				field : 'id',
				align : 'center',
				valign : 'middle',
				formatter:function(value,row,index){
					var a = '<a href="" >'+value+'测试'+index+'</a>';
					return a;
				}
			} ]
		});
	}
	
	function responseHandler(res) {
		if(res.code==100){
			return {
				"page":res.page,
				"total":res.total,
				"rows":res.rows
				};
		}else{
			return {
				"page":0,
				"total":0,
				"rows":[]
				};
		}
	}
	
	function addCustom() {
		$('#custom_form').ajaxSubmit({
			url : '/api/custom/add',
			type : 'post',
			dataType : "json",
			data : $("#form1").serialize(),
			success : function(data) {
				if (data) {
					if (data.code == 100) {
						console.log('ok');
					} else {
						console.log(data.msg);
					}
				} else {
					console.log("服务器未响应");
				}
			}
		});
	}
</script>

<div>
	<legend id="theme-legend">客户管理</legend>
</div>

<div id="custom-table-toolbar">
	<button class="btn btn-primary" data-toggle="modal"
		data-target="#dia-custom">添加</button>
	<button class="btn btn-danger">删除</button>
</div>
<table id="custom_table" ></table>

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