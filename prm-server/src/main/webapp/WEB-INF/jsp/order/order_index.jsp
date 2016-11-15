<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<script type="text/javascript">

var _orderTableConfig = new TableConfig(
            'url',
            '#order-table-toolbar',
            []
            );
}

var controller = {
        loadData : function() {
            $('#order_table').bootstrapTable(_orderTableConfig);
        }
    };

</script>

<ol class="breadcrumb">
	<li><a href="/"><span class="glyphicon glyphicon-plane"></span></a></li>
	<li class="active">订单管理</li>
</ol>

<div class="btn-toolbar" role="toolbar" id="order-table-toolbar">
	<button class="btn btn-primary" data-toggle="modal"
		onclick="clickAdd();">
		<span class="glyphicon glyphicon-plus"></span>&nbsp;添加
	</button>
	<button class="btn btn-danger" onclick="delCustoms()">
		<span class="glyphicon glyphicon-minus"></span>&nbsp;删除
	</button>

	<div class="btn-group pull-right" data-toggle="buttons">
		<label class="btn btn-default active"> <input type="radio"
			name="options" id="option1" autocomplete="off" checked>全部
		</label> <label class="btn btn-default"> <input type="radio"
			name="options" id="option2" autocomplete="off">已支付
		</label> <label class="btn btn-default"> <input type="radio"
			name="options" id="option3" autocomplete="off">未支付
		</label>
	</div>

</div>
<table id="order_table" class="table table-condensed"></table>