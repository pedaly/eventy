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
<script src="http://www.mapquestapi.com/sdk/js/v7.0.s/mqa.toolkit.js?key=Kmjtd%7Cluua2qu7n9%2C7a%3Do5-lzbgq"></script>

<script src="/js/vendor/jquery-1.8.2.min.js"></script>
<script src="/js/vendor/modernizr-2.6.2.min.js"></script>
<script>
	$('#top_menu').css("backgroundColor", "blue");
	$(function() {
		$("#datepicker").datepicker({
			minDate : new Date()
		});
	});
</script>

<script type="text/javascript" src="/js/map.js"></script>
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

	<%@ include file="navi.jsp"%>	

	<div class="outer">
		<div class="container">


<!-- 			<form id="addEditForm" modelAttribute="event" action="/saveEvent" -->
<!-- 				method="post" accept-charset="utf-8"> -->
			<form:form method="PUT" id="addEditForm" commandName="event" >

				<nav class="menu">
					<button class="MyButton" type="submit" value="true" name="submit"> Zapisz i dodaj kolejne </button>
					<button class="MyButton" type="submit" value="false" name="submit"> Zapisz </button>
				</nav>

				<div id="map" class="mapquestMap">
					<button type="button" id="srch"
						onclick="EventMap.addPointByAddress()">Search</button>
				</div>


	



				<fieldset>
					<label> Kategoria <select name="category">
							<c:forEach items="${categories}" var="category">

								<option value="${category.name}">${category.name}</option>

							</c:forEach>
					</select>
					</label> <label> Tytuł <input type="text" id="title" name="title" />
					</label>
					<label> Miasto <form:input path="city" name="city" id="city" /></label>
					<label> Ulica <form:input path="streetAndNumber" name="streetAndNumber" id="address" /></label> 
					<label>Latitude <form:input path="lat" name="lat" id="lat" /></label> 
					<label>Longtitude <form:input path="lng" name="lng" id="long" /></label> 
					<label>Date: <form:input path="dateOfEvent" id="datepicker" name="dateOfEvent" /></label> 
					<label> Opis <form:textarea path="description"  name="description" cols="15" rows="30"/> </label>

				</fieldset>

				<p class="details_button">+ Details</p>
				<div class="details">
					<fieldset>
						<label> WWW <form:input path="webpage" id="webpage" name="webpage" /></label> 
						<label> Telefon <form:input path="phone" id="phone" name="telephone" /></label> 
						<label> Skype <form:input path="skype" id="skype" name="skype" /></label>
					</fieldset>
				</div>
			</form:form>

		</div>
	</div>



	<script src="/js/plugins.js"></script>
	<script src="/js/main.js"></script>
	<script src="http://code.jquery.com/ui/1.9.1/jquery-ui.js"></script>
</body>
</html>
