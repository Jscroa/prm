<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="/js/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<script type="text/javascript">
	function saveJson() {
		var storage = window.localStorage;
		var details = $("#storageText").val();
		storage.setItem("details", JSON.stringify(details));

	}
	function lookJson() {
		var storage = window.localStorage;
		var details = storage.getItem("details");
		$("#storageLook").html(details);
	}
</script>
<body>
	<h1>test</h1>
	<fieldset>
	<legend>local storage</legend>
	<input type="text" id="storageText" />
	<button onclick="saveJson()">保存至storage</button>
	<p><button onclick="lookJson()">查看</button><label>storage:&nbsp;</label><label id="storageLook"></label></p>
	</fieldset>
</body>
</html>