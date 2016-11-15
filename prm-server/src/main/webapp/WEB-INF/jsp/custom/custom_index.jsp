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
                            return index+1;
                        }
                    },
                    {
                        checkbox : true, // 使用复选框
                        field : 'state',
                        align : 'center',
                        valign:'middle'
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
                                    '<button class="btn btn-default btn-sm" onclick="customAddress(\''
                                            + value + '\',\'' + row.name
                                            + '\')">',
                                    '<span class="glyphicon glyphicon-map-marker">',
                                    '</span>',
                                    '&nbsp;地址管理',
                                    '</button>',
                                    '&nbsp;&nbsp;',
                                    '<button class="btn btn-default btn-sm" data-loading-text="加载中" onclick="clickModify(this,\''
                                            + value + '\')">',
                                    '<span class="glyphicon glyphicon-edit">',
                                    '</span>', '&nbsp;编辑', '</button>' ]
                                    .join('');
                        }
                    } ]);

    var controller = {
        loadData : function() {
            $('#custom_table').bootstrapTable(_customTableConfig);
        }
    };
    
    function exp(){
        $("#custom_table").tableExport({
            headings: true,                    // (Boolean), display table headings (th/td elements) in the <thead>
            footers: true,                     // (Boolean), display table footers (th/td elements) in the <tfoot>
            formats: ["xls", "csv", "txt"],    // (String[]), filetypes for the export
            fileName: "客户",                    // (id, String), filename for the downloaded file
            bootstrap: true,                   // (Boolean), style buttons using bootstrap
            position: "top",                 // (top, bottom), position of the caption element relative to table
            ignoreRows: null,                  // (Number, Number[]), row indices to exclude from the exported file
            ignoreCols: null,                   // (Number, Number[]), column indices to exclude from the exported file
            ignoreCSS: ".tableexport-ignore"   // (selector, selector[]), selector(s) to exclude from the exported file
        });
    }

    var customForm = [
            '<form class="form-horizontal" name="custom_form" id="custom_form" onSubmit="return false;">',
            '<div class="form-group">',
            '<label for="inputName" class="col-sm-3 control-label">',
            '<span style="color:red;">*</span>&nbsp;姓名：',
            '</label>',
            '<div class="col-sm-9">',
            '<input type="text" name="name" class="form-control" placeholder="Name" id="inputName" required>',
            '</div>',
            '</div>',
            '<div class="form-group">',
            '<label for="inputSex" class="col-sm-3 control-label">',
            '性别：',
            '</label>',
            '<div class="col-sm-9">',
            '<div class="radio">',
            '<label class="radio-inline">',
            '<input type="radio" name="sex" id="inputSex" value="1" checked />',
            '男',
            '</label>',
            '&nbsp;',
            '<label class="radio-inline">',
            '<input type="radio" name="sex" id="inputSex" value="0" />',
            '女',
            '</label>',
            '</div>',
            '</div>',
            '</div>',
            '<div class="form-group">',
            '<label for="inputPhone" class="col-sm-3 control-label">',
            '手机：',
            '</label>',
            '<div class="col-sm-9">',
            '<input type="text" name="phone" class="form-control" placeholder="Phone" id="inputPhone" required>',
            '</div>',
            '</div>',
            '<div class="form-group">',
            '<label for="inputEmail" class="col-sm-3 control-label">',
            '邮箱：',
            '</label>',
            '<div class="col-sm-9">',
            '<input type="text" name="email" class="form-control" placeholder="Email" id="inputEmail" required>',
            '</div>',
            '</div>',
            '<div class="form-group">',
            '<label for="inputQQ" class="col-sm-3 control-label">',
            'QQ：',
            '</label>',
            '<div class="col-sm-9">',
            '<input type="text" name="qq" class="form-control" placeholder="QQ" id="inputQQ" required>',
            '</div>',
            '</div>',
            '<div class="form-group">',
            '<label for="inputWeiXin" class="col-sm-3 control-label">',
            '微信：',
            '</label>',
            '<div class="col-sm-9">',
            '<input type="text" name="weixin" class="form-control" placeholder="WeiXin" id="inputWeiXin" required>',
            '</div>',
            '</div>',
            '<div class="form-group">',
            '<label for="inputBirthday" class="col-sm-3 control-label">',
            '出生年月：',
            '</label>',
            '<div class="col-sm-9">',
            '<div class="input-group">',
            '<input name="birthday" class="datepicker form-control" placeholder="Birthday" id="inputBirthday" required> ',
            '<span class="input-group-addon" id="basic-addon1">',
            '<span class="glyphicon glyphicon-calendar">',
            '</span>',
            '</span>',
            '</div>',
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
            '确定', '</button>', '</div>', '</div>', '</form>' ].join('');

    // 点击添加
    function clickAdd() {
        var dlg = bootbox.dialog({
            title : '<strong>新增客户</strong>',
            message : customForm,
            /* size : 'large', */
            closeButton : true
        });
        initDatepicker();
        $('#cancelButton').click(function() {
            dlg.modal('hide');
        });
        $('#confirmButton').click(function() {
            addCustom(dlg, this);
        });
    }

    // 点击修改
    function clickModify(btn, id) {
        $(btn).button('loading');
        $.ajax({
            url : '/api/custom/getCustom',
            type : 'post',
            dataType : 'json',
            data : {
                custId : id
            },
            success : function(data) {
                $(btn).button('reset');
                if (data) {
                    if (data.code == 100) { // 请求成功
                        var dlg = bootbox.dialog({
                            title : '<strong>编辑客户</strong>',
                            message : customForm,
                            /* size : 'large', */
                            closeButton : true
                        });
                        initDatepicker();
                        loadData(data.t);
                        $('#cancelButton').click(function() {
                            dlg.modal('hide');
                        });
                        $('#confirmButton').click(function() {
                            modifyCustom(dlg, this, id);
                        });
                    } else {
                        toastr.warning(data.code + ':' + data.msg);
                    }
                } else {
                    // 未响应
                    toastr.warning('服务器未响应!');
                }
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                $(btn).button('reset');
                toastr.error(XMLHttpRequest.status);
            }
        });

    }

    // 添加客户
    function addCustom(dlg, confirmBtn) {
        $(confirmBtn).button('loading');
        $('#custom_form').ajaxSubmit({
            url : '/api/custom/add',
            type : 'post',
            /* async : false, */
            dataType : 'json',
            data : $("#custom_form").serialize(),
            success : function(data) {
                $(confirmBtn).button('reset');
                if (data) {
                    if (data.code == 100) {
                        toastr.success('添加成功');
                        dlg.modal('hide');
                        $('#custom_table').bootstrapTable('refresh');
                    } else {
                        toastr.warning(data.code + ':' + data.msg);
                    }
                } else {
                    toastr.warning('服务器未响应');
                }
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                $(confirmBtn).button('reset');
                toastr.error(XMLHttpRequest.status);
            }
        });
        $(confirmBtn).attr("disabled", "disabled");
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
            message : '确定删除选中的&nbsp;<strong class="text-danger">' + selects.length + '</strong>&nbsp;条数据？',
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
                        //toastr.warning('id -> ' + ids[index] + ' 未响应!');
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

    // 编辑客户
    function modifyCustom(dlg, confirmBtn, id) {
        $(confirmBtn).button('loading');
        toastr.info('编辑' + id);
        $.ajax({
            url : '/api/custom/modify?custId='+id,
            type : 'post',
            dataType : 'json',
            data : $("#custom_form").serialize(),
            success : function(data) {
                $(confirmBtn).button('reset');
                if (data) {
                    if (data.code == 100) {
                        toastr.success('修改成功');
                        dlg.modal('hide');
                        $('#custom_table').bootstrapTable('refresh');
                    } else {
                        toastr.warning(data.code + ':' + data.msg);
                    }
                } else {
                    // 未响应
                    //toastr.warning('id -> ' + ids[index] + ' 未响应!');
                    toastr.warning('服务器未响应，编号为&nbsp;' + ids[index]
                            + '&nbsp;的客户删除失败！');
                }
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                $(confirmBtn).button('reset');
                toastr.error(XMLHttpRequest.status);
            }
        });
    }

    function initDatepicker() {
        $('.datepicker').datepicker({
            language : "zh-CN",
            format : "yyyy年mm月dd日"
        });
    }

    $(function() {
        controller.loadData();
    });
</script>

<ol class="breadcrumb">
	<li><a href="/"><span class="glyphicon glyphicon-plane"></span></a></li>
	<li class="active">客户管理</li>
</ol>

<div class="btn-toolbar" role="toolbar" id="custom-table-toolbar">
	<!-- <div class="btn-group" role="group"> -->
	<button class="btn btn-default" data-toggle="modal"
		onclick="clickAdd();">
		<span class="glyphicon glyphicon-plus"></span>&nbsp;添加
	</button>
	<button class="btn btn-default" onclick="delCustoms()">
		<span class="glyphicon glyphicon-minus"></span>&nbsp;删除
	</button>
	<!-- </div> -->
	<div id="select-control-group" class="btn-group" role="group"></div>
</div>

<table id="custom_table" class="table table-condensed"></table>
