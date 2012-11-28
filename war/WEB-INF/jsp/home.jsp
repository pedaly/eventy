<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>	
	
	
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
<script src="js/vendor/jquery-1.8.2.min.js">
	
</script>
<script src="js/vendor/modernizr-2.6.2.min.js">
	
</script>

</head>
<body>






	<nav class="top_menu">
		<a href="/home.do"> Zarządzaj wydarzeniami </a> <span>&nbsp |
			&nbsp <span> <a href="#"> Zarządzaj kategoriami</a> <span>&nbsp
					| &nbsp <span> <a href="#"> Zarządzaj uzytkownikami </a>
					
	<span style="float:right;font-size: 14px;margin-right:180px;"> 				
	<%
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if (user != null) {
			pageContext.setAttribute("user", user);
	%>
	
		Witaj, ${fn:escapeXml(user.nickname)}! (<a href="<%=userService.createLogoutURL("/home")%>">
		Wyloguj</a>.)

	<%
		} else {
		System.out.println(request.getRequestURI());
		System.out.println(request.getRequestURI());
	%>
		 <a href="<%=userService.createLoginURL("/home")%>"> Logowanie</a> 
	<% } %>
	</span>
	</nav>

	<div class="outer">
		<div class="logo"></div>
		<div class="container">
			<a href="/addEvent.do"> Dodaj</a>
			<nav class="menu">
				<a href="/addEvent.do"> Dodaj </a>
			</nav>
			<div class="spacer"></div>



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
				<c:forEach items="${events}" var="event">
					<tr class="event_row">
						<td>${event.title}</td>
						<td>${event.description}</td>
						<td>${event.dateOfEvent}</td>
						<td>${event.city}</td>
						<td>${event.phone}</td>
						<td class="edit"><a href="#"> edytuj </a> <span>&nbsp
								| &nbsp <span> <a href="#"> usuń </a></td>
					</tr>
				</c:forEach>
			</table>

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