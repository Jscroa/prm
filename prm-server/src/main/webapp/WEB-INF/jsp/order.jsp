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
					<li class="active"><a href="#ddd1" data-toggle="tab">全部</a></li>
					<li><a href="#ddd2" data-toggle="tab">已付款</a></li>
					<li><a href="#ddd3" data-toggle="tab">未付款</a></li>
				</ul>
			</div>
			<div class="col-md-10">
			<div class="container-fluid tab-content">
			<table class="table table-bordered">
			<thead>
				<tr>
					<th>asd</th>
					<th>asd</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>111</td>
					<td>111</td>
				</tr>
				<tr>
					<td>111</td>
					<td>111</td>
				</tr>
				<tr>
					<td>111</td>
					<td>111</td>
				</tr>
			</tbody>
			</table>
			</div>
			</div>
		</div>
	</div>
</body>
</html>