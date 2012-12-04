<%@ include file="common/taglibs.jsp"%>		
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html class="no-js">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title></title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width">


<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.9.1/themes/base/jquery-ui.css" />
<link rel="stylesheet" href="/css/normalize.css">
<link rel="stylesheet" href="/css/main.css">
<link rel="stylesheet" href="/css/style.css">
<link rel="stylesheet" href="/css/form.css">

<script src="http://open.mapquestapi.com/sdk/js/v7.0.s/mqa.toolkit.js"></script>
<script
	src="http://www.mapquestapi.com/sdk/js/v7.0.s/mqa.toolkit.js?key=Kmjtd%7Cluua2qu7n9%2C7a%3Do5-lzbgq"></script>

<script src="/js/vendor/jquery-1.8.2.min.js"></script>
<script src="/js/vendor/modernizr-2.6.2.min.js"></script>


<script type="text/javascript" src="/js/map.js"></script>
<script type="text/javascript" src="/js/main.js"></script>
<script src="http://open.mapquestapi.com/sdk/js/v7.0.s/mqa.toolkit.js"></script>
<script src="http://www.mapquestapi.com/sdk/js/v7.0.s/mqa.toolkit.js?key=Kmjtd%7Cluua2qu7n9%2C7a%3Do5-lzbgq"></script>
<script type="text/javascript" src="/js/map.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		var height = 250;
		var width = 350;
		EventMap.init(width, height);
		if(${event.lat}!=null && ${event.lng}!=null ){
			EventMap.showPoint(${event.lat},${event.lng},false);
		}
	});
</script>
</head>
<body>
	<%@ include file="navi.jsp"%>	


	<div class="outer">
		<div class="container">
			<nav class="menu">
				<a class="MyButton" href="/event/${event.id}/edit"> Edytuj </a>
				<a class="MyButton" href="/event/${event.id}/delete"> Usuń </a>
			</nav>
			
				<div id="map" class="mapquestMap inline">				
				</div>
				
			<div class="singleEvent">
				<p><span class="label">Tytuł : </span> <span>${ fn:length(event.title) == 0 ? "b.d." :  event.title}</span></p>
				<p><span class="label">Termin : </span> <span><fmt:formatDate value="${event.dateOfEvent }" pattern="yyyy-MM-dd" /></span></p>
				<p><span class="label">Lokalizacja : </span>  <span>${event.city}, ${event.streetAndNumber},  ${event.wojewodztwo}</span></p>
				<p><span class="label">Współrzędne : </span> <span>${event.lat} , ${event.lng}</span></p>
				<p><span class="label">Opis : </span> <span> ${ fn:length(event.description) == 0 ? "b.d" : event.description}</span></p>
				<p class="details_button">+ Details</p>
					<div class="details">
						<p><span class="label">WWW : </span> <span> ${ fn:length(event.webpage) == 0 ? "b.d." :  event.webpage}</span></p>
						<p><span class="label">Menadzer imprezy : </span> <span> ${ fn:length(event.managerOfEvent) == 0 ? "b.d." : event.managerOfEvent}</span></p>
						<p><span class="label">Telefon kontaktowy : </span> <span> ${ fn:length(event.phone) == 0 ? "b.d." : event.phone}</span></p>
						<p><span class="label">Skype : </span> <span> ${ fn:length(event.skype) == 0 ? "b.d." : event.skype}</span></p>
						<p><span class="label">Kod : </span> <span> ${ fn:length(event.postcode) == 0 ? "b.d." : event.postcode}</span></p>
						<p><span class="label">Wojewodztwo : </span> <span> ${ fn:length(event.wojewodztwo) == 0 ? "b.d." : event.wojewodztwo}</span></p>
					</div>
			</div>
		
		</div>
	</div>
</body>
</html>