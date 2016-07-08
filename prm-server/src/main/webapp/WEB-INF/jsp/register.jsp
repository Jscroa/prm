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
<script src="/js/ng-config.js"></script>

<style type="text/css">
body {
	background: url("/img/bg.jpg") no-repeat;
	background-size: cover;
	background-position: center;
	background-attachment: fixed;
}

.register_panel {
	/* background-color:transparent; */
	width: 500px;
	position: absolute;
	left: 15%;
	top: 15%;
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

.control-label>.ng-invalid {
	background-color: red;
	color: red;
}
</style>

<title>PRM</title>
</head>
<body>
	<div class="panel panel-primary register_panel" ng-app="registerApp"
		ng-controller="registerCtrl">
		<div class="panel-heading">
			<strong>快速注册&nbsp;PRM</strong>
		</div>
		<div class="panel-body">
			<div class="alert alert-danger" ng-show="error || incomplete" role="alert"><p><strong>验证不通过：</strong>{{checkMsg}}</p></div>
			<form class="form-horizontal" name="register_form" onSubmit="return false;">
				<div class="form-group">
					<label for="inputUserName" class="col-sm-3 control-label">账户名：</label>
					<div class="col-sm-9">
						<input name="userName" required class="form-control"
							placeholder="User Name" ng-model="userName" id="inputUserName"
							required>
					</div>
				</div>

				<div class="form-group">
					<label for="inputEmail" class="col-sm-3 control-label">邮箱：</label>
					<div class="col-sm-9">
						<input type="email" name="email" required class="form-control"
							placeholder="Email" ng-model="email" id="inputEmail" required>
					</div>
				</div>

				<div class="form-group">
					<label for="inputPassword" class="col-sm-3 control-label">密码：</label>
					<div class="col-sm-9">
						<input type="password" name="password" required
							class="form-control" placeholder="Password" ng-model="password"
							id="inputPassword" required>
					</div>
				</div>

				<div class="form-group">
					<label for="inputPassword2" class="col-sm-3 control-label">确认密码：</label>
					<div class="col-sm-9">
						<input type="password" name="password2" required
							class="form-control" placeholder="Password Confirm"
							ng-model="password2" id="inputPassword2" required>
					</div>
				</div>

				<div class="form-group">
					<label for="inputPhone" class="col-sm-3 control-label">手机：</label>
					<div class="col-sm-9">
						<input name="phone" required class="form-control"
							placeholder="Phone" ng-model="phone" id="inputPhone" required>
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-offset-3 col-sm-9">
						<button class="btn btn-primary" ng-click="register();">提&nbsp;&nbsp;交</button>
						<button class="btn btn-link"><a href="/login">登陆</a></button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		angular
		.module('registerApp', [],function($httpProvider) {ngHttpConfig($httpProvider);})
		.controller('registerCtrl',
				function($scope,$http) {
					$scope.userName = '';
					$scope.email = '';
					$scope.password = '';
					$scope.password2 = '';
					$scope.phone = '';

					$scope.checkMsg = '';
					$scope.error = false;
					$scope.incomplete = false;

					$scope.check = function() {
						$scope.error = false;
						$scope.incomplete = false;
						if (!$scope.userName) {
							$scope.incomplete = true;
							$scope.checkMsg = '用户名不能为空';
							return;
						}
						if (!$scope.email) {
							$scope.incomplete = true;
							$scope.checkMsg = '邮箱格式不正确';
							return;
						}
						if (!$scope.password) {
							$scope.incomplete = true;
							$scope.checkMsg = '请输入密码';
							return;
						}
						if (!$scope.password2) {
							$scope.incomplete = true;
							$scope.checkMsg = '请再次输入密码';
							return;
						}
						if (!$scope.phone) {
							$scope.incomplete = true;
							$scope.checkMsg = '手机号不能为空';
							return;
						}

						if ($scope.register_form.email.$invalid) {
							$scope.error = true;
							$scope.checkMsg = '不是正确的邮箱格式';
							return;
						}
						if ($scope.password != $scope.password2) {
							$scope.error = true;
							$scope.checkMsg = '两次密码输入不一致';
							return;
						}
					};
					$scope.register = function() {
						$scope.check();
						if ($scope.error || $scope.incomplete) {
							return;
						}
						
						$http.post('/api/user/register',{
							userName : $scope.userName,
							email : $scope.email,
							password : $scope.password,
							phone : $scope.phone
						}).success(function(data){
							if (data) {
								if (data.code == 100) {
									window.location.href = '/';
									return;
								}else{
									$scope.error = true;
									$scope.checkMsg = data.msg;
								}
							}else{
								$scope.error = true;
								$scope.checkMsg = '服务器未响应';
							}
						});
						
						
					};
				});
	</script>
</body>
</html>