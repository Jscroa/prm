<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<script type="text/javascript">
    var _customTableConfig = new TableConfig(
            '/api/custom/list',
            '#custom-table-toolbar',
            [
                    {
                        title : '',
                        field : 'rowNum',
                        align : 'center',
                        valign : 'middle',
                        formatter : function(value, row, index) {
                            return index + 1;
                        }
                    },
                    {
                        checkbox : true, // 使用复选框
                        field : 'state',
                        align : 'center',
                        valign : 'middle'
                    },
                    {
                        title : '姓名',
                        field : 'name',
                        align : 'center',
                        valign : 'middle',
                        formatter : function(value, row, index) {
                            return value;
                        }
                    },
                    {
                        title : '手机',
                        field : 'phone',
                        align : 'center',
                        valign : 'middle'
                    },
                    {
                        title : 'QQ',
                        field : 'qq',
                        align : 'center',
                        valign : 'middle'
                    },
                    {
                        title : '微信',
                        field : 'weixin',
                        align : 'center',
                        valign : 'middle'
                    },
                    {
                        title : '邮箱',
                        field : 'email',
                        align : 'center',
                        valign : 'middle',
                        formatter : function(value, row, index) {
                            var str = '<a href="#">' + value + '</a>';
                            return value;
                        }
                    },
                    {
                        title : '操作',
                        field : 'id',
                        align : 'center',
                        valign : 'middle',
                        formatter : function(value, row, index) {

                            return [
                                    '<button class="btn btn-primary btn-sm" data-loading-text="加载中" onclick="clickModify(this,\''
                                            + value
                                            + '\')"><span class="glyphicon glyphicon-edit"></span>&nbsp;编辑</button>',

                                    '&nbsp;',

                                    '<button class="btn btn-default btn-sm" onclick="customAddress(\''
                                            + value + '\',\'' + row.name
                                            + '\')">地址</button>',

                                    '&nbsp;',

                                    '<a class="btn btn-link btn-sm" href="/custom/passenger?custId='+value+'">管理乘客</a>' ]
                                    .join('');

                        }
                    } ]);
    
    // 点击添加
    function clickAdd() {
        showCustomAddDlg({
            success : function() {
                $('#custom_table').bootstrapTable('refresh');
            },
            warning : function(msg) {
                toastr.warning(msg);
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                toastr.error(XMLHttpRequest.status);
            }
        });

    }

    // 点击编辑
    function clickModify(btn, id) {
        $(btn).button('loading');
        showCustomModifyDlg(id, {
            loadOk : function() {
                $(btn).button('reset');
            },
            loadError : function(XMLHttpRequest, textStatus, errorThrown) {
                $(btn).button('reset');
            },
            success : function() {
                $(btn).button('reset');
                $('#custom_table').bootstrapTable('refresh');
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

    // 批量删除
    function delCustoms() {
        var selects = $('#custom_table').bootstrapTable('getSelections');
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
                    delCustom(ids, 0);
                }
            }
        });

    }

    // 递归删除
    function delCustom(ids, index) {
        if (index < 0 || ids.length <= index) {
            toastr.success('删除完毕');
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
                            // toastr.success('id -> ' + ids[index] + ' 删除成功!');
                            delCustom(ids, index + 1);
                        } else {
                            toastr.warning(data.code + ':' + data.msg);
                            delCustom(ids, index + 1);
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

    // 客户地址管理
    function customAddress(value, name) {
        showAddressPage(value, name);
    }
    
    // 乘客管理
    function customPassenger(value){
        // TODO 跳到乘客管理页面
        window.open('/custom/passenger?custId='+value);
    }

    $(function() {
        $('#custom_table').bootstrapTable(_customTableConfig);
    });
</script>

<ol class="breadcrumb">
	<li><a href="/"><span class="glyphicon glyphicon-plane"></span></a></li>
	<li class="active">客户管理</li>
</ol>

<div class="btn-toolbar" role="toolbar" id="custom-table-toolbar">
	<button class="btn btn-primary" data-toggle="modal"
		onclick="clickAdd();">
		<span class="glyphicon glyphicon-plus"></span>&nbsp;添加
	</button>
	<button class="btn btn-danger" onclick="delCustoms()">
		<span class="glyphicon glyphicon-minus"></span>&nbsp;删除
	</button>

</div>

<table id="custom_table" class="table table-condensed"></table>
