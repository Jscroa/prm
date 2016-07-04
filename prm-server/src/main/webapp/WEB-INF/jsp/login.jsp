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
<script src="//cdn.bootcss.com/angular.js/1.5.7/angular.min.js"></script>

<style type="text/css">
body {
	background: url("/img/bg.jpg") no-repeat;
	background-size: cover;
	background-position: center;
	background-attachment: fixed;
}

.login_panel {
	/* background-color:transparent; */
	width: 500px;
	position: absolute;
	left: 15%;
	top: 30%;
	/* opacity: 0.8; */
	-moz-box-shadow: 2px 2px 50px #000000;
	-webkit-box-shadow: 2px 2px 50px #000000;
	box-shadow: 2px 2px 50px #000000;
}

.panel-heading {
	/* font-weight: bold; */
	font-size: x-large;
	font-style: oblique;
}
.control-label > .ng-invalid {
	background-color: red;
	color: red;
}
</style>

<title>PRM</title>
</head>
<body>
	<div class="panel panel-primary login_panel" ng-app="loginApp" ng-controller="loginCtrl">
		<div class="panel-heading"><strong>快速登录&nbsp;PRM</strong></div>
		<div class="panel-body">
			<form class="form-horizontal" action="/api/user/login" method="post" name="login_form" novalidate>
				<div class="form-group">
					<label for="inputEmail" class="col-sm-2 control-label">邮箱：</label>
					<div class="col-sm-10">
						<input type="email" name="email" class="form-control"
							placeholder="Email" ng-model="email" id="inputEmail">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword" class="col-sm-2 control-label">密码：</label>
					<div class="col-sm-10">
						<input type="password" name="password" class="form-control"
							placeholder="Password" ng-model="password" id="inputPassword">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button class="btn btn-primary">登&nbsp;&nbsp;录</button>
						<button class="btn btn-link">注册新账号</button>
					</div>
				</div>
			</form>
		</div>
		<p>邮箱：{{login_form.email.$valid}}</p>
		<p>密码：{{login_form.password.$valid}}</p>
	</div>
	
	<script>
		angular.module('loginApp',[])
		.controller('loginCtrl',function($scope){
			$scope.email = '';
			$scope.password = '11111111111111111111';
		});
	</script>
</body>
</html>