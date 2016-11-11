<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<!-- css -->
<link href="/css/bootstrap.min.css" rel="stylesheet">
<link href="/css/prm.css" rel="stylesheet">

<!-- js -->
<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/angular.min.js"></script>
<script src="/js/ng-config.js"></script>

<style type="text/css">
/* 
body {
	background: url("/img/bg.jpg") no-repeat;
	background-size: cover;
	background-position: center;
	background-attachment: fixed;
}
 */
body {
	background: #EEEEEE;
}

.panel-heading {
	/* font-weight: bold; */
	font-size: x-large;
}

.control-label>.ng-invalid {
	background-color: red;
	color: red;
}
</style>

<title>PRM</title>
</head>
<body>

	<div class="container" style="margin-top: 5%">
		<div class="row">
			<div class="col-md-6 col-md-offset-3">

				<div class="panel panel-default" ng-app="registerApp"
					ng-controller="registerCtrl">
					<div class="panel-heading">快速注册&nbsp;PRM</div>
					<div class="panel-body">
						<div class="alert alert-danger" ng-show="error || incomplete"
							role="alert">
							<p>
								<strong>错误：</strong>{{checkMsg}}
							</p>
						</div>
						<form class="form-horizontal" name="register_form"
							onSubmit="return false;">
							<div class="form-group">
								<label for="inputUserName" class="col-sm-3 control-label">账户名：</label>
								<div class="col-sm-9">
									<input name="userName" class="form-control"
										placeholder="User Name" ng-model="userName" id="inputUserName"
										required>
								</div>
							</div>

							<div class="form-group">
								<label for="inputEmail" class="col-sm-3 control-label">邮箱：</label>
								<div class="col-sm-9">
									<input type="email" name="email" class="form-control"
										placeholder="Email" ng-model="email" id="inputEmail" required>
								</div>
							</div>

							<div class="form-group">
								<label for="inputPassword" class="col-sm-3 control-label">密码：</label>
								<div class="col-sm-9">
									<input type="password" name="password" class="form-control"
										placeholder="Password" ng-model="password" id="inputPassword"
										required>
								</div>
							</div>

							<div class="form-group">
								<label for="inputPassword2" class="col-sm-3 control-label">确认密码：</label>
								<div class="col-sm-9">
									<input type="password" name="password2" class="form-control"
										placeholder="Password Confirm" ng-model="password2"
										id="inputPassword2" required>
								</div>
							</div>

							<div class="form-group">
								<label for="inputPhone" class="col-sm-3 control-label">手机：</label>
								<div class="col-sm-9">
									<input name="phone" class="form-control" placeholder="Phone"
										ng-model="phone" id="inputPhone" required>
								</div>
							</div>

							<div class="form-group">
								<div class="col-sm-6">
									<button class=" form-control btn btn-success"
										data-loading-text="注册中..." ng-click="register($event);">确认提交</button>
								</div>
								<div class="col-sm-6">
									<a class="form-control btn btn-default" href="/login"
										role="button">登&nbsp;&nbsp;陆</a>
								</div>
							</div>

						</form>
					</div>
				</div>


			</div>
		</div>
	</div>





	<script type="text/javascript">
		angular.module('registerApp', [], function($httpProvider) {
			ngHttpConfig($httpProvider);
		}).controller('registerCtrl', function($scope, $http) {
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
			$scope.register = function($event) {
			    $($event.target).button('loading');
				$scope.check();
				if ($scope.error || $scope.incomplete) {
				    $($event.target).button('reset');
					return;
				}

				$http.post('/api/user/register', {
					userName : $scope.userName,
					email : $scope.email,
					password : $scope.password,
					phone : $scope.phone
				}).success(function(data) {
				    $($event.target).button('reset');
					if (data) {
						if (data.code == 100) {
							window.location.href = '/';
							return;
						} else {
							$scope.error = true;
							$scope.checkMsg = data.msg;
						}
					} else {
						$scope.error = true;
						$scope.checkMsg = '服务器未响应';
					}
				}).error(function(){
				    $($event.target).button('reset');
					$scope.error = true;
					$scope.checkMsg = '服务器发生错误';
				});

			};
		});
	</script>
</body>
</html>