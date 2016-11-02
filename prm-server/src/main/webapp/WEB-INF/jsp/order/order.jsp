<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<%@include file="/WEB-INF/jsp/components/base.jsp"%>

<link href="/css/bootstrap-table.css" rel="stylesheet">
<script src="/js/bootstrap-table.js"></script>

<script type="text/javascript">
function showPage(page){
    $.get(page,function(data){
        $('#page_container').html(data);
        $('#page_container').fadeIn(slow);
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
	<div class="container" id="page_container"></div>
</body>
</html>