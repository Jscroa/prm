<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<%@include file="/WEB-INF/jsp/components/base.jsp"%>

<link href="/css/bootstrap-table.css" rel="stylesheet">
<script src="/js/bootstrap-table.js"></script>

<style type="text/css">
</style>

<title>PRM</title>
</head>
<body>
	<div>
		<%@include file="/WEB-INF/jsp/components/nav.jsp"%>
		<script type="text/javascript">
			$('#nav_order').addClass('active');
		</script>
	</div>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-2">
				<ul class="nav nav-pills nav-stacked">
					<li class="active"><a href="#all" data-toggle="tab">全部</a></li>
					<li><a href="#paid" data-toggle="tab">已付款</a></li>
					<li><a href="#unpay" data-toggle="tab">未付款</a></li>
				</ul>
			</div>
			<div class="col-md-10">
				<div class="container-fluid tab-content">
					<div id="all" class="tab-pane fade in active">全部</div>
					<div id="paid" class="tab-pane fade">已付款</div>
					<div id="unpay" class="tab-pane fade">未付款</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>