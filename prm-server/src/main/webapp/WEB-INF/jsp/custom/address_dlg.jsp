<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<script type="text/javascript">
		var address_form_html_str = [
				'<form class="form-horizontal" name="custom_form" id="address_form" onSubmit="return false;">',
				
				'<div class="form-group">',
                '<label for="inputName" class="col-sm-3 control-label">',
                '<span style="color:red;">*</span>&nbsp;标签：',
                '</label>',
                '<div class="col-sm-9">',
                '<input type="text" name="tip" class="form-control" placeholder="Name" id="inputName" required>',
                '</div>',
                '</div>',
				
				'<div class="form-group">',
                '<label for="inputName" class="col-sm-3 control-label">',
                '<span style="color:red;">*</span>&nbsp;地址：',
                '</label>',
                '<div class="col-sm-9">',
                '<input type="text" name="addr" class="form-control" placeholder="Name" id="inputName" required>',
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
                '确定', '</button>', '</div>',
                '</div>',
				
				'</form>'
		].join('');	
	

		function showAddressAddDlg(obj){
		    var dlg = bootbox.dialog({
                title : '<strong>新增客户</strong>',
                message : address_form_html_str,
                /* size : 'large', */
                closeButton : true
            });
            $('#cancelButton').click(function() {
                dlg.modal('hide');
            });
            $('#confirmButton').click(function() {
                addAddress(dlg, this, obj);
            });
		}
		
		function showAddressModifyDlg(id, obj) {
            $.ajax({
                url : '/api/address/getAddress',
                type : 'post',
                dataType : 'json',
                data : {
                    addrId : id
                },
                success : function(data) {
                    obj.loadOk();
                    if (data) {
                        if (data.code == 100) { // 请求成功
                            var dlg = bootbox.dialog({
                                title : '<strong>编辑客户</strong>',
                                message : address_form_html_str,
                                /* size : 'large', */
                                closeButton : true
                            });
                            loadData(data.t);
                            $('#cancelButton').click(function() {
                                dlg.modal('hide');
                            });
                            $('#confirmButton').click(function() {
                                //                                            modifyCustom(dlg, this, id);
                                modifyAddress(dlg, this, id, obj);
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
                    obj.loadError(XMLHttpRequest, textStatus, errorThrown);
                }
            });
        }

        /**
         * 添加地址
         * @param dlg 添加窗口，传进来用于成功之后关闭
         * @param confirmBtn 确认按钮，用于更改按钮的loading状态
         * @param obj 回调
         */
        function addAddress(dlg, confirmBtn, obj) {
            $(confirmBtn).button('loading');
            
            var formData = getFormJson($('#address_form'));
            formData['customId'] = custId;
            //$('#address_form').ajaxSubmit({
            $.ajax({
                url : '/api/address/add',
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
        
        function modifyAddress(dlg, confirmBtn, id, obj) {
            $(confirmBtn).button('loading');
            $.ajax({
                url : '/api/address/modify?addrId=' + id,
                type : 'post',
                dataType : 'json',
                data : $("#address_form").serialize(),
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