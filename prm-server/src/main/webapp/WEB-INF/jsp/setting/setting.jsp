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
    showPage('setting/settingIndex');
}
$(function(){
    showIndexPage();
});
</script>
<title>PRM - 系统设置</title>
</head>
<body>
	<div>
		<%@include file="/WEB-INF/jsp/components/nav.jsp"%>
		<script type="text/javascript">
			$('#nav_setting').addClass('active');
		</script>
	</div>
	<%@include file="/WEB-INF/jsp/components/page_container.jsp"%>
	<script type="text/javascript" color="255,0,0" opacity='0.7' zIndex="-2" count="99" src="/js/canvas-nest.min.js"></script>
</body>
</html>