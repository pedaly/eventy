<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%-- <jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" --%>
<%-- 	xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.0"> --%>
<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]> <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]> <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <meta charset="utf-8"> -->
<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> -->
<title></title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width">

<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.9.1/themes/base/jquery-ui.css" />
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/style.css">
<script src="js/vendor/jquery-1.8.2.min.js">
	<\/script>
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
		<a href="#"> Zarządzaj wydarzeniamiiii </a> <span>&nbsp | &nbsp
			<span> <a href="#"> Zarządzaj kategoriami</a> <span>&nbsp
					| &nbsp <span> <a href="#"> Zarządzaj uzytkownikami </a>
	</nav>

	<div class="outer">
		<div class="logo"></div>
		<div class="container">
			<a href="/addEvent.do"> Dodaj</a>
			<nav class="menu">
				<a href="/addEvent.do"> Dodaj </a>
			</nav>
			<div class="spacer"></div>


			<%-- 			<c:forEach items="${events}" var="event"> --%>

			<!-- 				<div> -->
			<%-- 					<c:out value="${event.title}" /> --%>
			<!-- 				</div> -->
			<%-- 			</c:forEach> --%>
			<table class="events_table">


				<thead>
					<tr>
						<td>Tytuł</td>
						<td>Opis</td>
						<td>Termin</td>
						<td>Lokalizacja</td>
						<td>Kategoria</td>
						<td></td>
						<!-- td for edit/remove column -->
					</tr>
				</thead>
				<tr class="event_row">

					<td>${events.} }</td>
					<td>Suspendisse potenti. Quisque interdum, us mi...</td>
					<td>01/01/2013</td>
					<td>Łódź</td>
					<td>Klubowe</td>
					<td class="edit"><a href="#"> edytuj </a> <span>&nbsp |
							&nbsp <span> <a href="#"> usuń </a></td>

				</tr>
				<tr class="event_row">
					<td>Suspendisse potenti</td>
					<td>Suspendisse potenti. Quisque interdum, us mi...</td>
					<td>01/01/2013</td>
					<td>Łódź</td>
					<td>Klubowe</td>
					<td class="edit"><a href="#"> edytuj </a> <span>&nbsp |
							&nbsp <span> <a href="#"> usuń </a></td>
				</tr>
				<tr class="event_row">
					<td>Suspendisse potenti</td>
					<td>Suspendisse potenti. Quisque interdum, us mi...</td>
					<td>01/01/2013</td>
					<td>Łódź</td>
					<td>Klubowe</td>
					<td class="edit"><a href="#"> edytuj </a> <span>&nbsp |
							&nbsp <span> <a href="#"> usuń </a></td>
				</tr>
				<tr class="event_row">
					<td>Suspendisse potenti</td>
					<td>Suspendisse potenti. Quisque interdum, us mi...</td>
					<td>01/01/2013</td>
					<td>Łódź</td>
					<td>Klubowe</td>
					<td class="edit"><a href="#"> edytuj </a> <span>&nbsp |
							&nbsp <span> <a href="#"> usuń </a></td>

				</tr>
			</table>

		</div>
		<div>
			<p>
				Date: <input type="text" id="datepicker" />
			</p>
		</div>

	</div>

	</div>



	<!-- <script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script> -->
	<script>
		window.jQuery
				|| document
						.write('<script src="js/vendor/jquery-1.8.2.min.js"><\/script>')
	</script>
	<script src="js/plugins.js"></script>
	<script src="js/main.js"></script>
	<script src="http://code.jquery.com/ui/1.9.1/jquery-ui.js"></script>
</body>
</html>