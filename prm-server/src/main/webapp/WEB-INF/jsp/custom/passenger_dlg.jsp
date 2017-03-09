<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<script type="text/javascript">
        var dlg_load_html_str = [
                '<div class="sk-spinner sk-spinner-wave">',
                '<div class="sk-rect1">',
                '</div>',
                '<div class="sk-rect2">',
                '</div>',
                '<div class="sk-rect3">',
                '</div>',
                '<div class="sk-rect4">',
                '</div>',
                '<div class="sk-rect5">',
                '</div>',
                '</div>' ].join('');
        
        var passenger_form_html_str = [
                '<form class="form-horizontal" name="passenger_form" id="passenger_form" onSubmit="return false;">',

                '<div class="form-group">',
                '<label for="inputName" class="col-sm-3 control-label">',
                '<span style="color:red;">*</span>&nbsp;姓名：',
                '</label>',
                '<div class="col-sm-9">',
                '<input type="text" name="name" class="form-control" placeholder="Name" id="inputName" required>',
                '</div>',
                '</div>',

                '<div class="form-group">',
                '<label for="inputName" class="col-sm-3 control-label">',
                '<span style="color:red;">*</span>&nbsp;身份证号：',
                '</label>',
                '<div class="col-sm-9">',
                '<input type="text" name="idCard" class="form-control" placeholder="ID Card" id="inputIdCard" required>',
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
                '<label for="inputName" class="col-sm-3 control-label">',
                '国籍：',
                '</label>',
                '<div class="col-sm-9">',
                '<select name="countryId" class="form-control" id="countryId" required>',
                '<option></option>',
                '</select>',
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
                '确定',
                '</button>',
                '</div>',
                '</div>',

                '</form>' ].join('');

        var country_options_html_str = '';

        function showPassengerAddDlg(obj) {
            var dlg = bootbox.dialog({
                title : '<strong>新增乘客</strong>',
                message : dlg_load_html_str,
                closeButton : true
            });
            if (country_options_html_str != '') {
                dlg.find('.bootbox-body').html(passenger_form_html_str);
                $('#countryId').html(country_options_html_str);
                $('#cancelButton').click(function() {
                    dlg.modal('hide');
                });
                $('#confirmButton').click(function() {
                    addPassenger(dlg, this, obj);
                });
            } else {

                $.ajax({
                    url : '/api/country/list',
                    type : 'get',
                    dataType : 'json',
                    success : function(data) {
                        if (data) {
                            if (data.code == 100) {
                                country_options_html_str = '<option></option>';
                                for (var i = 0; i < data.rows.length; i++) {
                                    country_options_html_str += '<option value="'+data.rows[i].id+'">' + data.rows[i].cnName + ',' + data.rows[i].enName + ','
                                            + data.rows[i].shortName + '</option>';
                                }
                                dlg.init(function() {
                                    dlg.find('.bootbox-body').html(passenger_form_html_str);
                                    $('#countryId').html(country_options_html_str);
                                    $('#cancelButton').click(function() {
                                        dlg.modal('hide');
                                    });
                                    $('#confirmButton').click(function() {
                                        addPassenger(dlg, this, obj);
                                    });
                                });

                            }
                        }
                    },
                    error : function(XMLHttpRequest, textStatus, errorThrown) {
                        obj.loadError(XMLHttpRequest, textStatus, errorThrown);
                    }
                });
            }
        }

        function showPassengerModifyDlg(id, obj) {
            $.ajax({
                url : '/api/passenger/getPassenger',
                type : 'post',
                dataType : 'json',
                data : {
                    psgId : id
                },
                success : function(data) {
                    obj.loadOk();
                    if (data) {
                        if (data.code == 100) { // 请求成功
                            var dlg = bootbox.dialog({
                                title : '<strong>编辑乘客</strong>',
                                message : passenger_form_html_str,
                                /* size : 'large', */
                                closeButton : true
                            });

                            var formData = data.t;
                            if (country_options_html_str != '') {

                                dlg.init(function() {
                                    dlg.find('.bootbox-body').html(passenger_form_html_str);
                                    $('#countryId').html(country_options_html_str);
                                    loadData(formData);
                                    $('#cancelButton').click(function() {
                                        dlg.modal('hide');
                                    });
                                    $('#confirmButton').click(function() {
                                        modifyPassenger(dlg, this, id, obj);
                                    });
                                });

                            } else {

                                $.ajax({
                                    url : '/api/country/list',
                                    type : 'get',
                                    dataType : 'json',
                                    success : function(data) {
                                        if (data) {
                                            if (data.code == 100) {
                                                var country_options_html_str = '<option></option>';
                                                for (var i = 0; i < data.rows.length; i++) {
                                                    country_options_html_str += '<option value="'+data.rows[i].id+'">' + data.rows[i].cnName + ',' + data.rows[i].enName + ','
                                                            + data.rows[i].shortName + '</option>';
                                                }
                                                dlg.init(function() {
                                                    dlg.find('.bootbox-body').html(passenger_form_html_str);
                                                    $('#countryId').html(country_options_html_str);
                                                    loadData(formData);
                                                    $('#cancelButton').click(function() {
                                                        dlg.modal('hide');
                                                    });
                                                    $('#confirmButton').click(function() {
                                                        modifyPassenger(dlg, this, id, obj);
                                                    });
                                                });

                                            }
                                        }
                                    },
                                    error : function(XMLHttpRequest, textStatus, errorThrown) {
                                        obj.loadError(XMLHttpRequest, textStatus, errorThrown);
                                    }
                                });
                            }

                        } else {
                            obj.warning(data.code + ':' + data.msg);
                        }
                    } else {
                        // 未响应
                        obj.warning('服务器未响应!');
                    }
                },
                error : function(XMLHttpRequest, textStatus, errorThrown) {
                    obj.loadError(XMLHttpRequest, textStatus, errorThrown);
                }
            });
        }

        /**
         * 添加乘客
         * @param dlg 添加窗口，传进来用于成功之后关闭
         * @param confirmBtn 确认按钮，用于更改按钮的loading状态
         * @param obj 回调
         */
        function addPassenger(dlg, confirmBtn, obj) {
            $(confirmBtn).button('loading');

            var formData = getFormJson($('#passenger_form'));
            formData['customId'] = custId;
            //$('#address_form').ajaxSubmit({
            $.ajax({
                url : '/api/passenger/add',
                type : 'post',
                /* async : false, */
                dataType : 'json',
                data : formData,
                success : function(data) {
                    $(confirmBtn).button('reset');
                    if (data) {
                        if (data.code == 100) {
                            dlg.modal('hide');
                            obj.success();
                            // toastr.success('添加成功');
                            // $('#custom_table').bootstrapTable('refresh');
                        } else {
                            // toastr.warning(data.code + ':' + data.msg);
                            obj.warning(data.code + ':' + data.msg);
                        }
                    } else {
                        // toastr.warning('服务器未响应');
                        obj.warning('服务器未响应');
                    }
                },
                error : function(XMLHttpRequest, textStatus, errorThrown) {
                    $(confirmBtn).button('reset');
                    // toastr.error(XMLHttpRequest.status);
                    obj.error(XMLHttpRequest, textStatus, errorThrown);
                }
            });
            $(confirmBtn).attr("disabled", "disabled");
        }

        function modifyPassenger(dlg, confirmBtn, id, obj) {
            $(confirmBtn).button('loading');
            $.ajax({
                url : '/api/passenger/modify?psgId=' + id,
                type : 'post',
                dataType : 'json',
                data : $("#passenger_form").serialize(),
                success : function(data) {
                    $(confirmBtn).button('reset');
                    if (data) {
                        if (data.code == 100) {
                            dlg.modal('hide');
                            // toastr.success('修改成功');
                            // $('#custom_table').bootstrapTable('refresh');
                            obj.success();
                        } else {
                            obj.warning(data.code + ':' + data.msg);
                        }
                    } else {
                        // 未响应
                        //toastr.warning('id -> ' + ids[index] + ' 未响应!');
                        obj.warning('服务器未响应，编号为&nbsp;' + ids[index] + '&nbsp;的客户删除失败！');
                    }
                },
                error : function(XMLHttpRequest, textStatus, errorThrown) {
                    $(confirmBtn).button('reset');
                    obj.error(XMLHttpRequest, textStatus, errorThrown);
                }
            });
        }

        function getFormJson(form) {
            var o = {};
            var a = $(form).serializeArray();
            $.each(a, function() {
                if (o[this.name] !== undefined) {
                    if (!o[this.name].push) {
                        o[this.name] = [ o[this.name] ];
                    }
                    o[this.name].push(this.value || '');
                } else {
                    o[this.name] = this.value || '';
                }
            });
            return o;
        }
    </script>
</body>
</html>