<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="assets/global/plugins/bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="assets/global/plugins/jquery.min.js"></script>
<script type="text/javascript"
	src="assets/global/plugins/jquery-migrate.min.js"></script>

<script type="text/javascript"
	src="assets/global/plugins/jquery-ui/jquery-ui.min.js"></script>
<script type="text/javascript"
	src="assets/global/plugins/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js"></script>
<script type="text/javascript"
	src="assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<script type="text/javascript"
	src="assets/global/plugins/jquery.blockui.min.js"></script>
<script type="text/javascript"
	src="assets/global/plugins/jquery.cokie.min.js"></script>
<script type="text/javascript"
	src="assets/global/plugins/uniform/jquery.uniform.min.js"></script>
<script type="text/javascript"
	src="assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js"></script>

<script type="text/javascript"
	src="assets/global/plugins/flot/jquery.flot.min.js"></script>
<script type="text/javascript"
	src="assets/global/plugins/flot/jquery.flot.resize.min.js"></script>
<script type="text/javascript"
	src="assets/global/plugins/flot/jquery.flot.pie.min.js"></script>
<script type="text/javascript"
	src="assets/global/plugins/flot/jquery.flot.stack.min.js"></script>
<script type="text/javascript"
	src="assets/global/plugins/flot/jquery.flot.crosshair.min.js"></script>
<script type="text/javascript"
	src="assets/global/plugins/flot/jquery.flot.categories.min.js"></script>

<script type="text/javascript" src="assets/global/scripts/metronic.js"></script>

<style type="text/css">
.chart {
	moz-user-select: -moz-none;
	-moz-user-select: none;
	-o-user-select: none;
	-khtml-user-select: none;
	-webkit-user-select: none;
	-ms-user-select: none;
	user-select: none;
}
</style>

<title>Metronic</title>

<script type="text/javascript">
    $(function() {

        if (!jQuery.plot) {
            return;
        }

        Metronic.init();

        initPie();
        initChart();
    });

    function initPie() {
        var score = parseInt(Math.random() * 100);
        var r = parseInt(255 * (100 - score) / 100);
        var g = parseInt(255 * score / 100);
        var b = 0;

        var clr = "rgb(" + r + "," + g + "," + b + ")";

        var data = [];

        data[0] = {
            label : "score",
            data : score
        };
        data[1] = {

            data : 100 - score
        };

        // DEFAULT
        if ($('#donut').size() !== 0) {
            $
                    .plot(
                            $("#donut"),
                            data,
                            {
                                series : {
                                    pie : {
                                        show : true,
                                        innerRadius : 0.6,
                                        radius : 1,
                                        label : {
                                            show : true,
                                            radius : 0,
                                            formatter : function(label, series) {
                                                if (label == "score") {
                                                    return '<div style="font-size:32pt;text-align:center;padding:2px;">第一名<br/>'
                                                            + score + '分</div>';
                                                } else {
                                                    return '';
                                                }
                                            },
                                            threshold : 0.1
                                        }
                                    }
                                },
                                legend : {
                                    show : false
                                },
                                colors : [ clr, "#E0E0E0" ]
                            });
        }

    }
    function initChart(){
        if ($('#interactive').size() != 1) {
            return;
        }
        function randValue() {
            return (Math.floor(Math.random() * (1 + 40 - 20))) + 20;
        }
        var pageviews = [
                         ['1', randValue()],
                         ['2', randValue()],
                         ['3', 2 + randValue()],
                         ['4', 3 + randValue()],
                         ['5', 5 + randValue()],
                         ['6', 10 + randValue()],
                         ['7', 15 + randValue()],
                         ['8', 20 + randValue()],
                         ['9', 25 + randValue()],
                         ['10', 30 + randValue()],
                         ['11', 35 + randValue()],
                         ['12', 25 + randValue()],
                         ['13', 15 + randValue()],
                         ['14', 20 + randValue()],
                         ['15', 45 + randValue()],
                         ['16', 50 + randValue()],
                         ['17', 65 + randValue()],
                         ['18', 70 + randValue()],
                         ['19', 85 + randValue()],
                         ['20', 80 + randValue()],
                         ['21', 75 + randValue()],
                         ['22', 80 + randValue()],
                         ['23', 75 + randValue()],
                         ['24', 70 + randValue()],
                         ['25', 65 + randValue()],
                         ['26', 75 + randValue()],
                         ['27', 80 + randValue()],
                         ['28', 85 + randValue()],
                         ['29', 90 + randValue()],
                         ['30', 95 + randValue()]
                     ];
                     var visitors = [
                         ['1', randValue() - 5],
                         ['2', randValue() - 5],
                         ['3', randValue() - 5],
                         ['4', 6 + randValue()],
                         ['5', 5 + randValue()],
                         ['6', 20 + randValue()],
                         ['7', 25 + randValue()],
                         ['8', 36 + randValue()],
                         ['9', 26 + randValue()],
                         ['10', 38 + randValue()],
                         ['11', 39 + randValue()],
                         ['12', 50 + randValue()],
                         ['13', 51 + randValue()],
                         ['14', 12 + randValue()],
                         ['15', 13 + randValue()],
                         ['16', 14 + randValue()],
                         ['17', 15 + randValue()],
                         ['18', 15 + randValue()],
                         ['19', 16 + randValue()],
                         ['20', 17 + randValue()],
                         ['21', 18 + randValue()],
                         ['22', 19 + randValue()],
                         ['23', 20 + randValue()],
                         ['24', 21 + randValue()],
                         ['25', 14 + randValue()],
                         ['26', 24 + randValue()],
                         ['27', 25 + randValue()],
                         ['28', 26 + randValue()],
                         ['29', 27 + randValue()],
                         ['30', 31 + randValue()]
                     ];
                     var plot = $.plot($("#interactive"), [{
                         data: pageviews,
                         label: "Unique Visits",
                         lines: {
                             lineWidth: 1,
                         },
                         shadowSize: 0

                     }, {
                         data: visitors,
                         label: "Page Views",
                         lines: {
                             lineWidth: 1,
                         },
                         shadowSize: 0
                     }], {
                         series: {
                             lines: {
                                 show: true,
                                 lineWidth: 2,
                                 fill: true,
                                 fillColor: {
                                     colors: [{
                                         opacity: 0.05
                                     }, {
                                         opacity: 0.01
                                     }]
                                 }
                             },
                             points: {
                                 show: true,
                                 radius: 3,
                                 lineWidth: 1
                             },
                             shadowSize: 2
                         },
                         grid: {
                             hoverable: true,
                             clickable: true,
                             tickColor: "#eee",
                             borderColor: "#eee",
                             borderWidth: 1
                         },
                         colors: ["#d12610", "#37b7f3", "#52e136"],
                         xaxis: {
                             ticks: 11,
                             tickDecimals: 0,
                             tickColor: "#eee",
                         },
                         yaxis: {
                             ticks: 11,
                             tickDecimals: 0,
                             tickColor: "#eee",
                         }
                     });


                     function showTooltip(x, y, contents) {
                         $('<div id="tooltip">' + contents + '</div>').css({
                             position: 'absolute',
                             display: 'none',
                             top: y + 5,
                             left: x + 15,
                             border: '1px solid #333',
                             padding: '4px',
                             color: '#fff',
                             'border-radius': '3px',
                             'background-color': '#333',
                             opacity: 0.80
                         }).appendTo("body").fadeIn(200);
                     }

                     var previousPoint = null;
                     $("#interactive").bind("plothover", function(event, pos, item) {
                         $("#x").text(pos.x.toFixed(2));
                         $("#y").text(pos.y.toFixed(2));

                         if (item) {
                             if (previousPoint != item.dataIndex) {
                                 previousPoint = item.dataIndex;

                                 $("#tooltip").remove();
                                 var x = item.datapoint[0].toFixed(2),
                                     y = item.datapoint[1].toFixed(2);

                                 showTooltip(item.pageX, item.pageY, item.series.label + " of " + x + " = " + y);
                             }
                         } else {
                             $("#tooltip").remove();
                             previousPoint = null;
                         }
                     });
    }
</script>

</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-6">
				<div class="panel panel-default">
				<div class="panel-heading">pie</div>
					<div class="panel-body">
						<div id="donut" class="chart" style="height: 300px;"></div>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="panel panel-default">
					<div class="panel-heading">chart</div>
					<div class="panel-body">
						<div id="interactive" class="chart" style="height: 300px;"></div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>