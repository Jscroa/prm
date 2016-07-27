<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<%@include file="/WEB-INF/jsp/components/base.jsp"%>

<style type="text/css">
</style>

<title>PRM</title>
</head>
<body>
	<div>
		<%@include file="/WEB-INF/jsp/components/nav.jsp"%>
		<script type="text/javascript">
			$('#nav_center').addClass('active');
		</script>
	</div>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-2">
				<ul class="nav nav-pills nav-stacked">
					<li class="active"><a href="#basic-info" data-toggle="tab">基本信息</a></li>
					<li><a href="#acc-group" data-toggle="tab">账户组</a></li>
					<li><a href="#unpay" data-toggle="tab">未付款</a></li>
				</ul>
			</div>
			<div class="col-md-10">
				<div class="container-fluid tab-content">
					<div id="basic-info" class="tab-pane fade in active">
						<div>
							<legend id="theme-legend">基本信息</legend>
						</div>
					</div>
					<div id="acc-group" class="tab-pane fade">
						<%@include file="/WEB-INF/jsp/components/center/acc-group.jsp"%>
					</div>
					<div id="unpay" class="tab-pane fade">
						<div>
							<legend id="theme-legend">未付款</legend>
						</div>
					</div>
				</div>
			</div>
		</div>
		
	</div>
</body>
</html>