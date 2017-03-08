<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script type="text/javascript">
    function getUrlParam(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg); //匹配目标参数
        if (r != null) {
            return unescape(r[2]);
        }
        return null; //返回参数值
    }

    var custId = getUrlParam('custId');

    var _addressTableConfig = {
        toolbar : '#address-table-toolbar',
        url : '/api/address/list?custId=' + custId,
        pagination : false,
        search : false,
        showRefresh : false,
        showColumns : false,
        showExport : false,
        cardView : false,
        columns : [
                {
                    checkbox : true, // 使用复选框
                    field : 'state',
                    align : 'center',
                    valign : 'middle',
                    visible : true
                },
                {
                    title : '序号',
                    field : 'rowNum',
                    align : 'center',
                    valign : 'middle',
                    formatter : function(value, row, index) {
                        return index + 1;
                    }
                },
                {
                    title : '标签',
                    field : 'tip',
                    align : 'left',
                    valign : 'middle',
                    formatter : function(value, row, index) {
                        return '<span class="badge">' + row.tip + '</span>';
                    }
                },
                {
                    title : '地址',
                    field : 'addr',
                    align : 'left',
                    valign : 'middle',
                    formatter : function(value, row, index) {
                        return row.addr;
                    }
                },
                {
                    title : '操作',
                    field : 'id',
                    halign : 'center',
                    align : 'center',
                    valign : 'middle',
                    formatter : function(value, row, index) {

                        return [ '<button class="btn btn-default btn-sm" data-loading-text="加载中" onclick="clickModify(this,\''
                                + value
                                + '\')"><span class="glyphicon glyphicon-edit"></span>&nbsp;编辑</button>' ]
                                .join('');

                    }
                } ]
    };

    function clickAdd() {
        showAddressAddDlg({
            success : function() {
                $('#address_table').bootstrapTable('refresh');
            },
            warning : function(msg) {
                toastr.warning(msg);
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                toastr.error(XMLHttpRequest.status);
            }
        });
    }

    function clickModify(btn, id) {
        $(btn).button('loading');
        showAddressModifyDlg(id, {
            loadOk : function() {
                $(btn).button('reset');
            },
            loadError : function(XMLHttpRequest, textStatus, errorThrown) {
                $(btn).button('reset');
            },
            success : function() {
                $(btn).button('reset');
                $('#address_table').bootstrapTable('refresh');
            },
            warning : function(msg) {
                $(btn).button('reset');
                toastr.warning(msg);
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                $(btn).button('reset');
                toastr.error(XMLHttpRequest.status);
            }
        });

    }

    function getCustomName() {
        $.ajax({
            url : '/api/custom/getCustom',
            type : 'post',
            dataType : 'json',
            data : {
                custId : custId
            },
            success : function(data) {
                if (data) {
                    if (data.code == 100) {
                        $('#index-title small').html(
                                '<a href="#">' + data.t.name + '</a>');

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

    function delAddresses() {
        var selects = $('#address_table').bootstrapTable('getSelections');
        if (selects.length == 0) {
            toastr.info('未选中任何数据');
            return;
        }
        bootbox.confirm({
            title : '确认删除',
            message : '确定删除选中的&nbsp;<strong class="text-danger">'
                    + selects.length + '</strong>&nbsp;条数据？',
            buttons : {
                confirm : {
                    label : '确认删除',
                    className : 'btn-danger'
                },
                cancel : {
                    label : '取消',
                    className : 'btn-default'
                }
            },
            callback : function(result) {
                if (result) {
                    var ids = new Array();
                    for ( var i in selects) {
                        var select = selects[i];
                        ids[i] = select.id;

                    }
                    delAddress(ids, 0);
                }
            }
        });
    }

    function delAddress(ids, index) {
        if (index < 0 || ids.length <= index) {
            toastr.success('删除完毕');
            $('#address_table').bootstrapTable('refresh');
        } else {
            $.ajax({
                url : '/api/address/del',
                type : 'post',
                dataType : 'json',
                data : {
                    addrId : ids[index]
                },
                success : function(data) {
                    if (data) {
                        if (data.code == 100) {
                            // 成功
                            // toastr.success('id -> ' + ids[index] + ' 删除成功!');
                            delAddress(ids, index + 1);
                        } else {
                            toastr.warning(data.code + ':' + data.msg);
                            delAddress(ids, index + 1);
                            // 询问是否继续
                        }
                    } else {
                        // 未响应
                        // toastr.warning('id -> ' + ids[index] + ' 未响应!');
                        toastr.warning('服务器未响应，编号为&nbsp;' + ids[index]
                                + '&nbsp;的客户删除失败！');
                    }
                },
                error : function(XMLHttpRequest, textStatus, errorThrown) {
                    toastr.error(XMLHttpRequest.status);
                }
            });
        }
    }

    $(function() {
        getCustomName();
        $('#address_table').prmTable(_addressTableConfig);
    });
</script>
<ol class="breadcrumb">
	<li><a href="/"><span class="glyphicon glyphicon-send"></span></a></li>
	<li><a href="/custom">客户管理</a></li>
	<li class="active">地址管理</li>
</ol>
<h4 id="index-title">
	客户地址 <small></small>
</h4>
<div class="btn-toolbar" role="toolbar" id="address-table-toolbar">
	<button class="btn btn-primary" data-toggle="modal"
		onclick="clickAdd();">
		<span class="glyphicon glyphicon-plus"></span>&nbsp;添加
	</button>
	<button class="btn btn-danger" onclick="delAddresses()">
		<span class="glyphicon glyphicon-minus"></span>&nbsp;删除
	</button>
</div>

<table id="address_table" class="table table-condensed"></table>