<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- css -->
<link href="/css/bootstrap.min.css" rel="stylesheet">
<link href="/css/prm.css" rel="stylesheet">
<link href="/css/prm-nav.css" rel="stylesheet">
<link href="/css/bootstrap-datepicker.min.css" rel="stylesheet">

<!-- js -->
<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>

<script src="/js/ng-config.js"></script>
<script src="/js/bootstrap-datepicker.min.js"></script>
<script src="/js/bootstrap-datepicker.zh-CN.min.js"></script>

<script type="text/javascript">
$(function(){
	$('.datepicker').datepicker({
		language: "zh-CN",
		format: "yyyy年mm月dd日"
	});
});
</script>

<title>Insert title here</title>
</head>
<script type="text/javascript">
	// local storage
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
	// ! local storage
	
	// form
	function getFormKVs(){
		var form = $('#form1')
		console.log(form.serializeArray());
	}
	// ! form
	
	
</script>
<body>
<div class="container">
	<h1>test</h1>
	
	<fieldset>
	<legend>local storage</legend>
	<input type="text" id="storageText" />
	<button onclick="saveJson()">保存至storage</button>
	<p><button onclick="lookJson()">查看</button><label>storage:&nbsp;</label><label id="storageLook"></label></p>
	</fieldset>
	
	<fieldset>
	<legend>datepicker</legend>
	<div class="input-group" style="width: 200px;">
	<input class="datepicker form-control" data-date-format="mm/dd/yyyy">
	<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-calendar"></span></span>
	</div>
	</fieldset>
	
	<fieldset>
	<legend>form</legend>
	<form id="form1">
		<input id="aaa" name="aaa" type="text"/>
		<input id="bbb" name="bbb" type="text"/>
		<select id="ccc" name="ccc">
			<option value="1001">1001</option>
			<option value="1002">1002</option>
			<option value="1003">1003</option>
		</select>
	</form>
	<button onclick="getFormKVs();">查看</button>
	</fieldset>
	</div>
</body>
</html>