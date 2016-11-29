<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>百度地图示例</title>
<style type="text/css">  
html{height:100%}  
body{height:100%;margin:0px;padding:0px}  
#map_container{height:50%}  
</style>  
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=OAMZeV6BXarRLpiTOFPWeqO8P5nmvK1G"></script>
</head>
<body>
<div id="map_container"></div>
<script type="text/javascript">

function startMap(){
	var map = new BMap.Map("map_container");
	var point = new BMap.Point(116.331398,39.897445);
	map.centerAndZoom(point,12);

	var geolocation = new BMap.Geolocation();
	geolocation.getCurrentPosition(function(r){
		if(this.getStatus() == BMAP_STATUS_SUCCESS){
			var mk = new BMap.Marker(r.point);
			map.addOverlay(mk);
			
			map.centerAndZoom(r.point,15);
			
			var convertor = new BMap.Convertor();
			var pointArr = [];
			pointArr.push(r.point);
			convertor.translate(pointArr, 1, 5, function(data){
				if(data.status === 0) {
					var mark = new BMap.Marker(data.points[0]);
					map.addOverlay(mark);
				}
			});
		}
		else {
			alert('failed'+this.getStatus());
		}        
	},{enableHighAccuracy: true});
	map.enableScrollWheelZoom(true);
}
startMap();
</script>
</body>
</html>