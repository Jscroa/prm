<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<script type="text/javascript">
var order_form_html_str=[
	'<form class="form-horizontal" name="order_form" id="order_form" onSubmit="return false;">',
	'<div class="form-group">',
	
	'<label for="inputName" class="col-sm-3 control-label">',
    '<span style="color:red;">*</span>&nbsp;...：',
    '</label>',
    '<div class="col-sm-9">',
    /* '<input type="text" name="name" class="form-control" placeholder="Name" id="inputName" required>', */
    '</div>',
	
	'</div>',
	'<div class="form-group">',
	
	'<label for="inputName" class="col-sm-3 control-label">',
    '<span style="color:red;">*</span>&nbsp;...：',
    '</label>',
    '<div class="col-sm-9">',
    /* '<input type="text" name="name" class="form-control" placeholder="Name" id="inputName" required>', */
    '</div>',
	
	'</div>',
	'<div class="form-group">',
    '<div class="col-sm-6">',
    '<button id="cancelButton" type="button" class="form-control btn btn-default">',
    '取消',
    '</button>',
    '</div>',
    '<div class="col-sm-6">',
    '<button id="confirmButton" type="button" data-loading-text="加载中" class="form-control btn btn-primary">',
    '确定', '</button>', '</div>', '</div>',
	'</form>'
].join('');

function initDatepicker() {
    $('.datepicker').datepicker({
        language : "zh-CN",
        format : "yyyy年mm月dd日"
    });
}

function showOrderAddDlg(obj) {
	var dlg = bootbox.dialog({
		title:'<strong>新建订单</strong>',
		message: order_form_html_str,
		closeButton : true
	});
	initDatepicker();
	$('#cancelButton').click(function() {
        dlg.modal('hide');
    });
    $('#confirmButton').click(function() {
//    	addOrder(dlg, this, obj);
    });
}

function showOrderModifyDlg(id, obj) {
	
}

function addOrder(dlg, confirmBtn, obj) {
	
}

function modifyOrder(dlg, confirmBtn, id, obj) {
	
}

</script>
</body>
</html>