<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="shortcut icon" href="/prm.ico">
<!-- css -->
<link href="/css/bootstrap.min.css" rel="stylesheet">
<link href="/css/prm.css" rel="stylesheet">

<!-- js -->
<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/angular.min.js"></script>
<script src="/js/ng-config.js"></script>

<style type="text/css">
/* body {
	background: url("/img/paper.jpg") no-repeat;
	background-size: cover;
	background-position: center;
	background-attachment: fixed;
} */

/* body {
	background-image:
		url(/img/2c761da4303eccca7d9bfc8d73a40077cfbc9f651fe0-svPkdZ_fw658.png);
} */

/* .panel {
	-moz-box-shadow: 0px 0px 100px #000000;
	-webkit-box-shadow: 0px 0px 100px #000000;
	box-shadow: 0px 0px 100px #000000;
} */

.panel-heading {
	/* font-weight: bold; */
	font-size: x-large;
}

.form-control>.ng-invalid {
	background-color: red;
	color: red;
}
</style>

<script type="text/javascript">
        angular.module('loginApp', [], function($httpProvider) {
            ngHttpConfig($httpProvider);
        }).controller('loginCtrl', function($scope, $http) {
            $scope.email = '';
            $scope.password = '';

            $scope.checkMsg = '';
            $scope.error = false;
            $scope.incomplete = false;

            $scope.check = function() {
                $scope.error = false;
                $scope.incomplete = false;
                if (!$scope.email) {
                    $scope.incomplete = true;
                    $scope.checkMsg = '邮箱格式不正确';
                    return;
                }
                if (!$scope.password) {
                    $scope.incomplete = true;
                    $scope.checkMsg = '密码不能为空';
                    return;
                }
                if ($scope.login_form.email.$invalid) {
                    $scope.error = true;
                    $scope.checkMsg = '不是正确的邮箱格式';
                    return;
                }
            };
            $scope.login = function($event) {
                $($event.target).button('loading');
                $scope.check();
                if ($scope.error || $scope.incomplete) {
                    $($event.target).button('reset');
                    return;
                }
                $http.post('/api/user/login', {
                    email : $scope.email,
                    password : $scope.password
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
                }).error(function() {
                    $($event.target).button('reset');
                    $scope.error = true;
                    $scope.checkMsg = '服务器发生错误';
                });
            };
        });
    </script>

<title>PRM</title>
</head>
<body>


	<div class="container" style="margin-top: 5%">
		<div class="row">
			<div class="col-md-6 col-md-offset-3">

				<div class="panel panel-default" ng-app="loginApp"
					ng-controller="loginCtrl">
					<div class="panel-heading">快速登录</div>
					<div class="panel-body">
						<div class="alert alert-danger" ng-show="error || incomplete"
							role="alert">
							<p>
								<strong>错误：</strong>{{checkMsg}}
							</p>
						</div>
						<form class="form-horizontal" name="login_form"
							onSubmit="return false;">
							<div class="form-group">
								<label for="inputEmail" class="col-sm-3 control-label">邮箱：</label>
								<div class="col-sm-9">
									<input type="email" name="email" class="form-control"
										placeholder="Email" ng-model="email" id="inputEmail" required />
								</div>
							</div>
							<div class="form-group">
								<label for="inputPassword" class="col-sm-3 control-label">密码：</label>
								<div class="col-sm-9">
									<input type="password" name="password" class="form-control"
										placeholder="Password" ng-model="password" id="inputPassword"
										required />
								</div>
							</div>

							<div class="form-group">
								<div class="col-sm-6">
									<button class="form-control btn btn-success"
										data-loading-text="登录中..." ng-click="login($event);">登&nbsp;&nbsp;录</button>
								</div>
								<div class="col-sm-6">
									<a class="form-control btn btn-default" href="/register"
										role="button">注册新用户</a>
								</div>
							</div>

						</form>
					</div>
				</div>

			</div>
		</div>
	</div>

    <script type="text/javascript" color="255,0,0" opacity='0.8' zIndex="-2" count="99" src="/js/canvas-nest.min.js"></script>
</body>
</html>