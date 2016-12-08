<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<style>
.panel-addr {
	margin-bottom: 0px;
}
.panel-addr{
background-color: transparent;
}
.panel-addr:HOVER {
	/* border-color: #cccccc; */
	background-color: #eeeeee;
	-moz-box-shadow: 0px 0px 5px #aaaaaa;
	-webkit-box-shadow: 0px 0px 5px #aaaaaa;
	box-shadow: 0px 0px 5px #aaaaaa;
}
</style>
<script type="text/javascript">

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}

	var _addressTableConfig = {
		toolbar : '#address-table-toolbar',
		url : '/api/address/list?custId='+getUrlParam('custId'),
		pagination : false,
		search : false,
		showRefresh : false,
		showColumns : false,
		showExport : false,
		cardView : true,
		columns : [
			{
			    
			    field : 'id',
			    align : 'left',
			    valign : 'middle',
			    formatter : function(value, addr, index) {
			        return [
			                '<div class="col-md-12 panel panel-addr">',
			                '<div class="panel-body">',
			                '<span class="badge">',
			                addr.tip,
			                '</span>',
			                '<button type="button" onclick="delAddr(\''
			                        + addr.id
			                        + '\');" class="close"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>',
			                '<div class="container-flush">', '<div class="row">',
			                '<div class="col-md-12">',
			                '<p style="word-break:break-word;">', addr.addr, '</p>',
			                '</div>', '</div>', '</div>', '</div>', '</div>' ].join('');
                }
			}
		]
	};
	
	function delAddr(id) {
        $.ajax({
            url : '/api/address/del',
            type : 'post',
            dataType : 'json',
            data : {
                addrId : id
            },
            success : function(data) {
                if (data) {
                    if (data.code == 100) {
                        toastr.success(data.msg);
                        $('#address_table').bootstrapTable('refresh');
                    } else {
                        toastr.warning(data.code + ':' + data.msg);
                    }
                } else {
                    toastr.warning('服务器未响应');
                }
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                toastr.error(XMLHttpRequest.status);
            }
        });
    }

	$(function() {
        $('#address_table').prmTable(_addressTableConfig);
    });
	
</script>

<ol class="breadcrumb">
	<li><a href="/"><span class="glyphicon glyphicon-send"></span></a></li>
	<li><a href="/custom">客户管理</a></li>
	<li class="active">地址管理</li>
</ol>

<div class="btn-toolbar" role="toolbar" id="address-table-toolbar">
	<!-- <button class="btn btn-primary" data-toggle="modal"
		onclick="clickAdd();">
		<span class="glyphicon glyphicon-plus"></span>&nbsp;添加
	</button>
	<button class="btn btn-danger" onclick="delCustoms()">
		<span class="glyphicon glyphicon-minus"></span>&nbsp;删除
	</button> -->

</div>

<table id="address_table" class="table table-condensed"></table>