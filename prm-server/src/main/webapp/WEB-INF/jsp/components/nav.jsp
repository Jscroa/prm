<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<nav class="navbar navbar-default">
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
			<a class="navbar-brand" href="/" style="font-style: oblique;"><span
				class="glyphicon glyphicon-plane" aria-hidden="true"></span>&nbsp;PRM
				机票预订管理系统</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<%
					List<String> navs = (List<String>) (request.getAttribute("navs"));
					for (String nav : navs) {
				%>
				<li><a href="#"><%=nav%></a></li>
				<%
					}
				%>
				<li><a href="#">Link</a></li>
				<li><a href="#">Link</a></li>
				<li><a href="#">Link</a></li>
				<li><a href="#">Link</a></li>
				<li><a href="#">Link</a></li>

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
						<li><a href="#">个人中心</a></li>
						<!-- <li role="separator" class="divider"></li> -->
						<li><a href="#">消息</a></li>
						<li><a href="#">系统设置</a></li>
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
<ol class="breadcrumb">
	<li><a href="#">Home</a></li>
	<li><a href="#">Library</a></li>
	<li class="active">Data</li>
</ol>