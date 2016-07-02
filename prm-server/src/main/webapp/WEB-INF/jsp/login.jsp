<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<!-- css -->
<link href="/css/bootstrap.min.css" rel="stylesheet">

<!-- js -->
<script src="//cdn.bootcss.com/jquery/3.0.0/jquery.min.js"></script>

<script src="/js/bootstrap.min.js"></script>


<style type="text/css">
body {
	background: url("/img/bg.jpg") no-repeat;
	background-size: cover;
	background-position: center;
	background-attachment: fixed;
}

.login_panel {
	width: 30%;
	position: absolute;
	left: 20%;
	top: 20%;
	opacity: 0.7;
}
</style>

<title>登陆</title>
</head>
<body>
	<div class="panel panel-primary login_panel">
		<div class="panel-heading">快速登录&nbsp;PRM</div>
		<div class="panel-body">
			<form class="form-horizontal">
				<div class="form-group">
					<label for="inputEmail1" class="col-sm-2 control-label">邮箱：</label>
					<div class="col-sm-10">
						<input type="email" class="form-control" placeholder="Email"
							id="inputEmail1">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword1" class="col-sm-2 control-label">密码：</label>
					<div class="col-sm-10">
						<input type="password" class="form-control" placeholder="Password"
							id="inputPassword1">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<div class="checkbox">
							<label> <input type="checkbox">记住密码</label>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button class="btn btn-primary">登&nbsp;&nbsp;录</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>