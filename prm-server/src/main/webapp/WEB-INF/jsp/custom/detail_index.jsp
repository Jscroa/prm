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
                    $('#index-title').html(data.t.name);

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
    getCustomName();
});
</script>

<ol class="breadcrumb">
	<li><a href="/"><span class="glyphicon glyphicon-send"></span></a></li>
	<li><a href="/custom">客户管理</a></li>
	<li class="active">客户明细</li>
</ol>
<h4 id="index-title"></h4>