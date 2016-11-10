<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<%@include file="/WEB-INF/jsp/components/base.jsp"%>

<link href="/css/bootstrap-table.css" rel="stylesheet">
<link href="/css/bootstrap-datepicker.min.css" rel="stylesheet">

<script src="/js/jquery.form.js"></script>
<script src="/js/bootstrap-table.js"></script>
<script src="/js/bootstrap-table-zh-CN.js"></script>
<script src="/js/bootstrap-datepicker.min.js"></script>
<script src="/js/bootstrap-datepicker.zh-CN.min.js"></script>
<script src="/js/bs-config.js"></script>
<style type="text/css">
/* .table thead tr {
	background-color: #494949;
	color: #CCCCCC;
}

.table thead tr :HOVER {
	color: #FFFFFF;
} */
</style>

<script type="text/javascript">
function showPage(page){
    $.get(page,function(data){
        $('#page_container').html(data);
    });
}
function showIndexPage(){
    showPage('custom/customIndex');
}
function showAddressPage(custId,custName){
    /* showPage('custom/customAddress?custId='+custId); */
    $.get('custom/customAddress?custId='+custId,function(data){
        bootbox.dialog({
            title:'<strong>地址管理</strong>&nbsp;&nbsp;'+custName,
            message:data,
            size: 'large',
            closeButton: true
        });
    });
	
}
$(function(){
    showIndexPage();
});
</script>

<title>PRM - 客户管理</title>
</head>
<body>
	<div>
		<%@include file="/WEB-INF/jsp/components/nav.jsp"%>
		<script type="text/javascript">
			$('#nav_custom').addClass('active');
		</script>
	</div>
	<div class="container mainland" id="page_container">
		<%-- <%@include file="custom_index.jsp"%> --%>
	</div>
</body>
</html>