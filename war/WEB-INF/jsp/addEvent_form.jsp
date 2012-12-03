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
		var height = 500;
		var width = 600;
		EventMap.init(width, height);
		MQA.EventManager.addListener(EventMap.map, 'click', EventMap.addPoint);
		if(${event.lat}!=null && ${event.lng}!=null ){
			EventMap.showPoint(${event.lat},${event.lng},true);
		}
	});
</script>
</head>
<body>

	<%@ include file="navi.jsp"%>	

	<div class="outer">
		<div class="container">


<!-- 			<form id="addEditForm" modelAttribute="event" action="/saveEvent" -->
<!-- 				method="post" accept-charset="utf-8"> -->
			<form:form method="POST" id="addEditForm" commandName="event" >

				<nav class="menu">
					<button class="MyButton" type="submit" value="true" name="submit"> Zapisz i dodaj kolejne </button>
					<button class="MyButton" type="submit" value="false" name="submit"> Zapisz </button>
					<form:hidden path="key"></form:hidden>
				</nav>
				<div id="map" class="mapquestMap inline">
					<button  class="MyButton" type="button" id="srch"
						onclick="EventMap.addPointByAddress()">Wyszukaj lokalizację wg. podanego adresu</button>
				</div>
<div class="inline">
				<fieldset>
					<label> <span class="label" >Kategoria*</span>
					<form:select path="category">
							<c:forEach items="${categories}" var="category">

<%-- 								<form:option value="${category.name }"> ${category.name }</form:option> --%>
								<option value="${category.name}">${category.name}</option>

							</c:forEach>
					</form:select>
					</label> 
					<label> <span class="label">Tytuł*</span>
					<form:input path="title" id="title" name="title" required="true" /></label>
					<label> <span class="label">Województwo</span>
					<form:select path="wojewodztwo" id="wojewodztwo" name="wojewodztwo">
								<option value=""></option>
								<option value="Woj dolnośląskie">woj. dolnośląskie</option>
								<option value="Woj kujawsko-pomorskie">woj. kujawsko-pomorskie</option>
								<option value="Woj lubelskie">woj. lubelskie</option>
								<option value="Woj lubuskie">woj. lubuskie</option>
								<option value="Woj łódzkie">woj. łódzkie</option>
								<option value="Woj małopolskie">woj. małopolskie</option>
								<option value="Woj mazowieckie">woj. mazowieckie</option>
								<option value="Woj opolskie">woj. opolskie</option>
								<option value="Woj podkarpackie">woj. podkarpackie</option>
								<option value="Woj podlaskie">woj. podlaskie</option>								
								<option value="Woj pomorskie">woj. pomorskie</option>							
								<option value="Woj śląskie">woj. śląskie</option>		
								<option value="Woj świętokrzyskie">woj. świętokrzyskie</option>									
								<option value="Woj warmińsko-mazurskie">woj. warmińsko-mazurskie</option>	
								<option value="Woj wielkopolskie">woj. wielkopolskie</option>									
								<option value="Woj zachodniopomorskie">woj. zachodniopomorskie</option>																					
					</form:select>
					</label>
					<label> <span class="label">Miasto</span>
					<form:input path="city" name="city" id="city" /></label>
					<label> <span class="label">Ulica</span>
					<form:input path="streetAndNumber" name="streetAndNumber" id="streetAndNumber" /></label> 
					<label> <span class="label">Szerokość geograficzna*</span>
					<form:input path="lat" name="lat" id="lat" pattern="-?\d{1,3}\.\d+" required="true" /></label> 
					<label> <span class="label">Długość geograficzna*</span>
					<form:input path="lng" name="lng" id="long" pattern="-?\d{1,3}\.\d+" required="true"/></label> 
					<label> <span class="label">Termin</span>
					<fmt:formatDate value="${event.dateOfEvent}" pattern="MM/dd/yyyy" var="formatedData"/>
					<form:input path="dateOfEvent" value="${formatedData }" id="datepicker" name="dateOfEvent" /></label> 
					<label> <span class="label">Opis</span></label>
					<form:textarea path="description"  name="description" cols="15" rows="30"/>

				</fieldset>
		</div>


				
				
				
				<p class="details_button label">+ Details </p>
				<div class="details">
					<fieldset>
						<label><span class="label"> WWW </span><form:input path="webpage" id="webpage" name="webpage" /></label> 
						<label><span class="label"> Telefon </span><form:input path="phone" id="phone" name="telephone" /></label> 
						<label><span class="label"> Skype </span><form:input path="skype" id="skype" name="skype" /></label>
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
