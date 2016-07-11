<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<%@include file="/WEB-INF/jsp/components/base.jsp"%>

<title>PRM</title>
</head>
<body>
	<div>
		<%@include file="/WEB-INF/jsp/components/nav.jsp"%>
		<script type="text/javascript">
			$('#nav_message').addClass('active');
		</script>
	</div>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-2">
				<ul class="nav nav-pills nav-stacked">
					<li class="active"><a href="#" data-toggle="tab">Home</a></li>
					<li><a href="#ddd1" data-toggle="tab">Tutorials</a></li>
					<li><a href="#ddd2" data-toggle="tab">Practice Editor </a></li>
					<li><a href="#" data-toggle="tab">Gallery</a></li>
					<li><a href="#" data-toggle="tab">Contact</a></li>
				</ul>
			</div>

			<div class="col-md-10">
				<div class="container-fluid tab-content">
					<div class="tab-pane fade in active" id="ddd1">
						<p>ddd1</p>
					</div>
					<div class="tab-pane fade" id="ddd2">
						<p>ddd2</p>
					</div>
				</div>
			</div>

		</div>
	</div>
</body>
</html>