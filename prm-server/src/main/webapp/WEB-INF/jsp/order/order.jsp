<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<link href="/css/bootstrap-table.css" rel="stylesheet">
<link href="/css/bootstrap-datepicker.min.css" rel="stylesheet">

<%@include file="/WEB-INF/jsp/components/base.jsp"%>
<script src="/js/jquery.form.js"></script>
<script src="/js/bootstrap-table.js"></script>
<script src="/js/bootstrap-table-zh-CN.js"></script>
<script src="/js/bootstrap-datepicker.min.js"></script>
<script src="/js/bootstrap-datepicker.zh-CN.min.js"></script>
<script src="/js/bs-config.js"></script>

<script type="text/javascript">
function showPage(page){
    $.get(page,function(data){
        $('#page_container').html(data);
    });
}
function showIndexPage(){
    showPage('order/orderIndex');
}
$(function(){
    showIndexPage();
});
</script>
<title>PRM - 订单管理</title>
</head>
<body>
	<div>
		<%@include file="/WEB-INF/jsp/components/nav.jsp"%>
		<script type="text/javascript">
			$('#nav_order').addClass('active');
		</script>
	</div>
	<%@include file="/WEB-INF/jsp/components/page_container.jsp"%>
</body>
</html>