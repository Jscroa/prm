<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<script type="text/javascript">
	function showAddress(rows) {
		if (rows) {
			if (rows.length > 0) {
				var temp = '';
				for(var j=0;j<100;j++){
					for (var i = 0; i < rows.length; i++) {
						/* temp += '<p>' + rows[i].id + ': ' + rows[i].addr + '</p>'; */
						temp += [ 
						          '<div class="panel panel-info">',
						          '<div class="panel-heading">',
						          '地址'+(j+1),
						          '</div>',
						          '<div class="panel-body">',
						          '<p>',
						          rows[i].addr,
						          '</p>',
						          
						          '</div>',
						          '</div>' ].join('');
					}
					
					//<div class="panel-heading">Panel heading without title</div>
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
</script>

<!-- <ol class="breadcrumb">
	<li><a href="/"><span class="glyphicon glyphicon-plane"></span></a></li>
	<li><a href="/custom">客户管理</a></li>
	<li class="active">地址管理</li>
</ol> -->
<div id="address_list"></div>