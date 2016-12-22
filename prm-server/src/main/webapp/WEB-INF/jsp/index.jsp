<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<%@include file="/WEB-INF/jsp/components/base.jsp"%>

<style type="text/css">
.bs-callout+.bs-callout {
	margin-top: -5px;
}

.bs-callout-danger {
	border-left-color: #d9534f;
}

.bs-callout {
	padding: 20px;
	margin: 20px 0;
	border: 1px solid #eee;
	border-left-width: 5px;
	border-radius: 3px;
}
</style>

<title>PRM</title>
</head>
<body>
	<%@include file="/WEB-INF/jsp/components/nav.jsp"%>
	<div class="container">
		<!-- <div class="container-fluid"> -->
		<div class="jumbotron">
			<div class="container">
				<div class="row">
					<div class="col-md-6">
						<h1>Hello!</h1>
						<p>Bootstrap is the world’s most popular framework for building responsive, mobile-first sites and applications. Inside you’ll find high quality HTML, CSS, and JavaScript to make starting any project easier than ever.</p>
						<p>An overview of Bootstrap, including how to download and use it, some basic templates and examples, and more.</p>
					</div>
					<div class="col-md-6">
						<ul>
							<li><a href="/test.jsp">test</a></li>
							<li><a href="/test_baidumap.jsp">test baidu map</a></li>
							<li><a href="/test_metronic.jsp">test metronic</a></li>
							<li><a href="/test_loader.jsp">test loader</a></li>
							<li><a href="/test_print.jsp">test print</a></li>
							<li><a href="/test_canvas_nest.jsp">test_canvas_nest</a></li>
						</ul>
						<button class="btn btn-primary" onclick="initCountry();">初始化国家</button>
					</div>
				</div>
			</div>


		</div>
	</div>
	<script type="text/javascript" color="255,0,0" opacity='0.8' zIndex="-2" count="299" src="/js/canvas-nest.min.js"></script>
</body>
<script type="text/javascript">
function initCountry(){
	$.ajax({
		url:'/api/country/init',
		type:'post',
		success:function(data){
			alert(data.msg);
		},
		error(e1,e2,e3){
			alert('失败'+e1+e2+e3);
		}
	});
}
</script>
</html>