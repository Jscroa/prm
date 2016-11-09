<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<%@include file="/WEB-INF/jsp/components/base.jsp"%>


<style type="text/css">
</style>
<script type="text/javascript">
function showPage(page){
    $.get(page,function(data){
        $('#page_container').html(data);
    });
}
function showIndexPage(){
    showPage('center/centerIndex');
}
$(function(){
    showIndexPage();
});
</script>
<title>PRM - 个人中心</title>
</head>
<body>
	<div>
		<%@include file="/WEB-INF/jsp/components/nav.jsp"%>
		<script type="text/javascript">
			$('#nav_center').addClass('active');
		</script>
	</div>
	<div class="container mainland" id="page_container"></div>
</body>
</html>