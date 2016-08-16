<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<nav class="navbar navbar-inverse" role="navigation">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/" style="font-style: oblique;">&nbsp;PRM 机票预订管理系统</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li id="nav_order"><a href="/order">订单管理</a></li>
				<li id="nav_custom"><a href="/custom">客户管理</a></li>
				<li id="nav_center"><a href="/center">个人中心</a></li>
				<li id="nav_message"><a href="/message">消息</a></li>
				<li id="nav_setting"><a href="/setting">系统设置</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<%
					String userName = (String) request.getAttribute("userName");
					if ((userName != null) && (!"".equals(userName))) {
				%>

				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false"><strong><%=userName%></strong> <span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="/logout">注销</a></li>
					</ul></li>
				<%
					}
				%>

			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
</nav>