<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title></title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width">

<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.9.1/themes/base/jquery-ui.css" />
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/form.css">

<script src="http://open.mapquestapi.com/sdk/js/v7.0.s/mqa.toolkit.js"></script>
<script
	src="http://www.mapquestapi.com/sdk/js/v7.0.s/mqa.toolkit.js?key=Kmjtd%7Cluua2qu7n9%2C7a%3Do5-lzbgq"></script>

<script type="text/javascript">
	MQA.EventUtil.observe(window, 'load', function() {

		var options = {
			elt : document.getElementById('map'),
			zoom : 10,
			latLng : {
				lat : 39.743943,
				lng : -105.020089
			},
			mtype : 'map',
			bestFitMargin : 0,
			zoomOnDoubleClick : true

		};

		window.map = new MQA.TileMap(options);

		MQA.withModule('largezoom', function() {
			map.addControl(new MQA.LargeZoom(), new MQA.MapCornerPlacement(
					MQA.MapCorner.TOP_LEFT, new MQA.Size(5, 5)));
		});
		MQA.withModule('mousewheel', function() {
			map.enableMouseWheelZoom();
		});
		MQA.withModule('viewoptions', function() {
			map.addControl(new MQA.ViewOptions());
		});
		MQA.withModule('traffictoggle', function() {
			map.addControl(new MQA.TrafficToggle());

		});
		MQA.EventManager.addListener(map, 'click', function(evt) {
			var myPoi = new MQA.Poi();
			myPoi.draggable = true;
			myPoi.setLatLng(evt.ll);
			map.addShape(myPoi);
			$("#addEditForm #lat").val(myPoi.getLatLng().lat);

			$("#addEditForm #long").val(myPoi.getLatLng().lng);

		});

	});
</script>
<script src="js/vendor/jquery-1.8.2.min.js">
	
</script>
<script src="js/vendor/modernizr-2.6.2.min.js">
	
</script>
<script>
	$('#top_menu').css("backgroundColor", "blue");
	$(function() {
		$("#datepicker").datepicker({
			minDate : new Date()
		});
	});
</script>
</head>
<body>
	<!--[if lt IE 7]>
            <p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to improve your experience.</p>
        <![endif]-->
	<nav class="top_menu">
		<a href="#"> Zarządzaj wydarzeniami </a> <span>&nbsp | &nbsp <span>
				<a href="#"> Zarządzaj kategoriami</a> <span>&nbsp | &nbsp <span>
						<a href="#"> Zarządzaj uzytkownikami </a>
	</nav>

	<div class="outer">
		<div class="logo"></div>
		<div class="container">


			<nav class="menu">
				<a href="#"> Zapisz i dodaj kolejne </a><span> | </span> <a
					href="/save2"> Zapisz </a>
			</nav>
			<form id="addEditForm" modelAttribute="event" action="/saveEvent"
				method="post" accept-charset="utf-8">
				<input type="submit" value="Zapisz event do chuja" />
				<div id="map" class="mapquestMap"></div>

				<fieldset>
					<label> Tytuł <input type="text" id="title" name="title" />
					</label>
				</fieldset>



				<fieldset>

					<label> Miasto <input type="text" name="city" id="city" /></label>
					<label> Adres <input type="text" name="streetAndNumber"
						id="address" /></label> <label>Latitude <input type="number"
						name="lat" id="lat" />
					</label> <label>Longtitude <input type="number" name="lng"
						id="long" />

					</label> <label>Date: <input type="text" id="datepicker"
						name="dateOfEvent" />
					</label> <label> Opis <textarea name="description" cols="15"
							rows="30"> </textarea></label>

				</fieldset>

				<p class="details_button">+ Details</p>
				<div class="details">
					<fieldset>
						<label> WWW <input type="text" id="webpage" name="webpage" />
						</label> <label> Telefon <input type="text" id="phone"
							name="telephone" /></label> <label> Skype <input type="text"
							id="skype" name="skype" /></label>
					</fieldset>
				</div>
			</form>

		</div>
	</div>


	<script src="js/plugins.js"></script>
	<script src="js/main.js"></script>
	<script src="http://code.jquery.com/ui/1.9.1/jquery-ui.js"></script>
</body>
</html>
