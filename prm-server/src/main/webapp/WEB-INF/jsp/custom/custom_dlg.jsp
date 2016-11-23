<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<script type="text/javascript">
        var custom_form_html_str = [
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

        function initDatepicker() {
            $('.datepicker').datepicker({
                language : "zh-CN",
                format : "yyyy年mm月dd日"
            });
        }

        /**
         * 
         * @param obj 回调
         */
        function showCustomAddDlg(obj) {
            var dlg = bootbox.dialog({
                title : '<strong>新增客户</strong>',
                message : custom_form_html_str,
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

        /**
         * 
         * @param id 客户id
         * @param obj 回调
         */
        function showCustomModifyDlg(id, obj) {
            $.ajax({
                url : '/api/custom/getCustom',
                type : 'post',
                dataType : 'json',
                data : {
                    custId : id
                },
                success : function(data) {
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
                                obj.cancel();
                            });
                            $('#confirmButton').click(function() {
                                //                                            modifyCustom(dlg, this, id);
                                modifyCustom(dlg, this, id, obj);
                            });
                        } else {
                            obj.warning(data.code + ':' + data.msg);
                        }
                    } else {
                        // 未响应
                        obj.warning('服务器未响应!');
                    }
                },
                error : function(XMLHttpRequest, textStatus, errorThrown) {
                    $(btn).button('reset');
                    obj.error(XMLHttpRequest, textStatus, errorThrown);
                }
            });
        }

        /**
         * 添加客户
         * @param dlg 添加窗口，传进来用于成功之后关闭
         * @param confirmBtn 确认按钮，用于更改按钮的loading状态
         * @param obj 回调
         */
        function addCustom(dlg, confirmBtn, obj) {
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
                            dlg.modal('hide');
                            obj.success();
                            //                                        toastr.success('添加成功');
                            //                                        $('#custom_table').bootstrapTable('refresh');
                        } else {
                            //                                        toastr.warning(data.code + ':' + data.msg);
                            obj.warning(data.code + ':' + data.msg);
                        }
                    } else {
                        //                                    toastr.warning('服务器未响应');
                        obj.warning('服务器未响应');
                    }
                },
                error : function(XMLHttpRequest, textStatus, errorThrown) {
                    $(confirmBtn).button('reset');
                    //                                toastr.error(XMLHttpRequest.status);
                    obj.error(XMLHttpRequest, textStatus, errorThrown);
                }
            });
            $(confirmBtn).attr("disabled", "disabled");
        }

        /**
         * 
         * @param dlg 编辑窗口，传进来用于成功之后关闭
         * @param confirmBtn 确认按钮，用于更改按钮的loading状态
         * @param id 客户id
         * @param obj 回调
         */
        function modifyCustom(dlg, confirmBtn, id, obj) {
            $(confirmBtn).button('loading');
            $.ajax({
                url : '/api/custom/modify?custId=' + id,
                type : 'post',
                dataType : 'json',
                data : $("#custom_form").serialize(),
                success : function(data) {
                    $(confirmBtn).button('reset');
                    if (data) {
                        if (data.code == 100) {
                            dlg.modal('hide');
                            //                                        toastr.success('修改成功');
                            //                                        $('#custom_table').bootstrapTable('refresh');
                            obj.success();
                        } else {
                            obj.warning(data.code + ':' + data.msg);
                        }
                    } else {
                        // 未响应
                        //toastr.warning('id -> ' + ids[index] + ' 未响应!');
                        obj.warning('服务器未响应，编号为&nbsp;' + ids[index]
                                + '&nbsp;的客户删除失败！');
                    }
                },
                error : function(XMLHttpRequest, textStatus, errorThrown) {
                    $(confirmBtn).button('reset');
                    obj.error(XMLHttpRequest, textStatus, errorThrown);
                }
            });
        }
    </script>
</body>
</html>