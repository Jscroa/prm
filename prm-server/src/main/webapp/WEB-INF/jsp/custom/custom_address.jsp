<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

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
                $('#address_list').html('<p>没有地址</p>');
            }
        } else {
            $('#address_list').html('<p>没有地址</p>');
        }
    }

    var custId = '${custId}';
    $(function() {
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
    });
    
    // 地址item
    function getAddressItem(addr){
        return [ '<div class="panel panel-primary">',
                 /* '<div class="panel-heading">', '地址', '</div>', */
                 '<div class="panel-body">', '<p>', addr.addr,
                 '</p>',
                 '</div>', '</div>' ].join('');
    }
</script>
<div id="address_list"></div>