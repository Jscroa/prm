<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
</head>
<body>
	<ul class="nav navbar-nav navbar-right">
		<%
			String userName = (String) request.getAttribute("userName");
			if ((userName == null) || ("".equals(userName))) {
		%>
				<li><a href="logout">注销</a></li>
		<%
			} else {
		%>
				<li><a href="#"><%=userName %></a></li>
				<li><a href="logout">注销</a></li>
		<%
			}
		%>
	</ul>
</body>
</html>