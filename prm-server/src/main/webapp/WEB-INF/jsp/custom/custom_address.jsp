<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<style>
.panel-addr {
	margin-bottom: 0px;
}

.panel-addr:HOVER {
	border-color: #cccccc;
	background-color: #eeeeee;
}
</style>
<script type="text/javascript">
    function showAddress(rows) {
        if (rows) {
            if (rows.length > 0) {
                var temp = '';
                for (var i = 0; i < rows.length; i++) {
                    temp += getAddressItem(rows[i]);
                }
                $('#address_list').html(temp);
            } else {
                $('#address_list')
                        .html(
                                '<div class="alert alert-danger" role="alert">没有地址</div>');
            }
        } else {
            $('#address_list').html(
                    '<div class="alert alert-danger" role="alert">没有地址</div>');
        }
    }

    var custId = '${custId}';
    $(function() {
        loadAddrs();
    });

    function loadAddrs() {
        $.ajax({
            url : '/api/custom/addressList',
            type : 'post',
            dataType : 'json',
            data : {
                custId : custId
            },
            success : function(data) {
                if (data) {
                    if (data.code == 100) {
                        showAddress(data.rows);
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

    // 地址item
    function getAddressItem(addr) {
        var tip = '地址...';
        if(addr.tip && addr.tip!=''){
            tip = addr.tip;
        }
        return [
                '<div class="col-md-6 panel panel-addr">',
                '<div class="panel-body">',
                '<span class="badge">',
                tip,
                '</span>',
                '<button type="button" onclick="delAddr(\''
                        + addr.id
                        + '\');" class="close"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>',
                '<div class="container-flush">', '<div class="row">',
                '<div class="col-md-12">',
                '<p style="word-break:break-word;">', addr.addr, '</p>',
                '</div>', '</div>', '</div>', '</div>', '</div>' ].join('');
    }

    function addAddr(btn) {
        var addr = $('#dlg_addr').val();
        if (!addr || addr == null || addr == "") {
            toastr.error('请在地址栏输入地址');
            return;
        }
        $(btn).button('loading');
        $.ajax({
            url : '/api/custom/addAddress',
            type : 'post',
            dataType : 'json',
            data : {
                customId : custId,
                addrStr : addr
            },
            success : function(data) {
                $(btn).button('reset');
                if (data) {
                    if (data.code == 100) {
                        $('#dlg_addr').val('');
                        loadAddrs();
                    } else {
                        toastr.warning(data.code + ':' + data.msg);
                    }
                } else {
                    toastr.warning('服务器未响应');
                }
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                $(btn).button('reset');
                toastr.error(XMLHttpRequest.status);
            }
        });
    }

    function delAddr(id) {
        $.ajax({
            url : '/api/custom/delAddress',
            type : 'post',
            dataType : 'json',
            data : {
                addrId : id
            },
            success : function(data) {
                if (data) {
                    if (data.code == 100) {
                        toastr.success(data.msg);
                        loadAddrs();
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
</script>
<div class="panel">
	<div class="panel-body">
		<div class="container-flush">
			<div class="row" id="address_list">
				<div class="col-md-12">
					<div class="sk-spinner sk-spinner-double-bounce">
						<div class="sk-double-bounce1"></div>
						<div class="sk-double-bounce2"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="address_add">
	<div class="panel">
		<div class="panel-body">
			<div class="container-flush">
				<div class="row">
					<div class="col-md-12">
						<div class="input-group">
							<input type="text" class="form-control" id="dlg_addr"
								placeholder="在此输入需要添加的地址" /> <span class="input-group-btn">
								<button class="btn btn-primary" onclick="addAddr(this);"
									type="button" data-loading-text="正在添加">确定添加</button>
							</span>
						</div>
						<span class="help-block">格式： "标签##地址" 若不设置标签，直接写地址也行。<br><strong>注：</strong> 标签不超过10字，地址不超过200字。</span>
						
					</div>
				</div>
			</div>
		</div>
	</div>
</div>