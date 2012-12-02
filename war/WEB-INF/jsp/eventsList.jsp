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
<script src="/js/vendor/jquery-1.8.2.min.js">
	
</script>
<script src="/js/vendor/modernizr-2.6.2.min.js">
	
</script>

</head>
<body>
	<%@ include file="navi.jsp"%>	

	<div class="outer">
		<div class="container">
			<nav class="menu">
				<a class="MyButton" href="/admin/addEvent"> Dodaj </a>
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
					<tr class="event_row" onmouseover="ChangeColor(this, true);" 
						onmouseout="ChangeColor(this, false);" >
						<td onclick="DoNav('/event/${event.key.id}')">${event.title}</td>
						<td onclick="DoNav('/event/${event.key.id}')">${fn:substring(event.description,0,30)}</td>
						<td onclick="DoNav('/event/${event.key.id}')"><fmt:formatDate value="${event.dateOfEvent}" pattern="yyyy-MM-dd" />
						<td onclick="DoNav('/event/${event.key.id}')">${event.city}</td>
						<td onclick="DoNav('/event/${event.key.id}')">${event.category}</td>
						<td class="edit"><a href="/event/${event.key.id}/edit"> edytuj </a> <span>&nbsp
								| &nbsp <span> <a href="/event/${event.key.id }/delete"> usuń </a></td>
					</tr><tr><td>link</td></tr>
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