<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html class="no-js">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title></title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width">


<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.9.1/themes/base/jquery-ui.css" />
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/form.css">

<script src="http://open.mapquestapi.com/sdk/js/v7.0.s/mqa.toolkit.js"></script>
<script
	src="http://www.mapquestapi.com/sdk/js/v7.0.s/mqa.toolkit.js?key=Kmjtd%7Cluua2qu7n9%2C7a%3Do5-lzbgq"></script>

<script src="js/vendor/jquery-1.8.2.min.js"></script>
<script src="js/vendor/modernizr-2.6.2.min.js"></script>
<script>
	$('#top_menu').css("backgroundColor", "blue");
	$(function() {
		$("#datepicker").datepicker({
			minDate : new Date()
		});
	});
</script>

<script type="text/javascript" src="js/map.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		var height = 400;
		var width = 600;
		EventMap.init(width, height);
		MQA.EventManager.addListener(EventMap.map, 'click', EventMap.addPoint)
	});
</script>
</head>
<body>

	<nav class="top_menu">
		<a href="#"> Zarządzaj wydarzeniami </a> <span>&nbsp | &nbsp <span>
				<a href="#"> Zarządzaj kategoriami</a> <span>&nbsp | &nbsp <span>
						<a href="#"> Zarządzaj uzytkownikami </a>
	</nav>

	<div class="outer">
		<div class="logo"></div>
		<div class="container">


			<form id="addEditForm" modelAttribute="event" action="/saveEvent"
				method="post" accept-charset="utf-8">


				<nav class="menu">
					<a href="#"> Zapisz i dodaj kolejne </a><span> | </span> <a
						href="/save2"> Zapisz </a> <input type="submit"
						value="Zapisz event do chuja" />

				</nav>

				<div id="map" class="mapquestMap">
					<button type="button" id="srch"
						onclick="EventMap.addPointByAddress()">Search</button>
				</div>


				<fieldset>
					<label> Tytuł <input type="text" id="title" name="title" />
					</label>
				</fieldset>



				<fieldset>

					<label> Miasto <input type="text" name="city" id="city" /></label>
					<label> Ulica <input type="text" name="streetAndNumber"
						id="address" /></label> <label>Latitude <input type="text"
						name="lat" id="lat" />
					</label> <label>Longtitude <input type="text" name="lng"
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
