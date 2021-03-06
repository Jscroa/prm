<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<script type="text/javascript">
    var _orderTableConfig = {
        toolbar : '#order-table-toolbar',
        url : '/api/order/list',
        columns : [{
            checkbox : true, // 使用复选框
            field : 'state',
            align : 'center',
            valign : 'middle'
        }, {
            title : '序号',
            field : 'rowNum',
            align : 'center',
            valign : 'middle',
            formatter : function(value, row, index) {
                return index + 1;
            }
        },  {
            title : '订单号',
            field : 'orderNum',
            align : 'center',
            valign : 'middle'
        }, {
            title : '客户姓名',
            field : 'customName',
            align : 'center',
            valign : 'middle'
        }, {
            title : '地址',
            field : 'addressStr',
            align : 'center',
            valign : 'middle'
        }, {
            title : '下单时间',
            field : 'orderTime',
            align : 'center',
            valign : 'middle'
        }, {
            title : '金额',
            field : 'price',
            align : 'center',
            valign : 'middle'
        }, {
            title : '状态',
            field : 'isPay',
            align : 'center',
            valign : 'middle'
        }, {
            title : '操作',
            field : 'id',
            halign : 'center',
            align : 'right',
            valign : 'middle'
        } ]
    };
    
    // 点击添加
    function clickAdd() {
    	showOrderAddDlg({
            success : function() {
                $('#order_table').bootstrapTable('refresh');
            },
            warning : function(msg) {
                toastr.warning(msg);
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                toastr.error(XMLHttpRequest.status);
            }
        });

    }

    function orderOptionChanged() {
        var value = $('#orderOptionGroup input[name="orderOptions"]:checked')
                .val();
        var url = '';
        if (value == 1) { // 全部订单
            url = '/api/order/list?s=all';
        } else if (value == 2) { // 已支付订单
            url = '/api/order/list?s=payed';
        } else if (value == 3) { // 未支付订单
            url = '/api/order/list?s=unpayed';
        } else {
            return;
        }

        $('#order_table').bootstrapTable('refresh', {
            'url' : url
        });
    }

    $(function() {
        $('#order_table').bootstrapTable(_orderTableConfig);
    });
</script>

<ol class="breadcrumb">
	<li><a href="/"><span class="glyphicon glyphicon-send"></span></a></li>
	<li class="active">订单管理</li>
</ol>
<div class="btn-group" id="orderOptionGroup" data-toggle="buttons">
	<label class="btn btn-default active"> <input type="radio"
		name="orderOptions" value="1" onchange="orderOptionChanged()"
		checked="checked" autocomplete="off">全部
	</label> <label class="btn btn-default"> <input type="radio"
		name="orderOptions" value="2" onchange="orderOptionChanged()"
		autocomplete="off">已支付
	</label> <label class="btn btn-default"> <input type="radio"
		name="orderOptions" value="3" onchange="orderOptionChanged()"
		autocomplete="off">未支付
	</label>
</div>
<div class="btn-toolbar" role="toolbar" id="order-table-toolbar">
	<button class="btn btn-primary" data-toggle="modal"
		onclick="clickAdd();">
		<!-- <span class="glyphicon glyphicon-plus"></span>&nbsp; -->
		添加
	</button>
	<button class="btn btn-danger" onclick="delCustoms()">
		<!-- <span class="glyphicon glyphicon-minus"></span>&nbsp; -->
		删除
	</button>



</div>
<table id="order_table" class="table table-condensed"></table>