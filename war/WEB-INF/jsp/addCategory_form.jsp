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


</head>
<body>

	<%@ include file="navi.jsp"%>

	<div class="outer">
		<div class="container">

			<form:form method="PUT" id="addEditForm" commandName="category">

				<nav class="menu">
					<button class="MyButton" type="submit" value="true" name="submit">
						Zapisz i dodaj kolejne</button>
					<button class="MyButton" type="submit" value="false" name="submit">
						Zapisz</button>
				</nav>

				<fieldset>
					<label> Nazwa kategorii <form:input path="name" name="name"
							id="name" /></label>
				</fieldset>

			</form:form>

		</div>
	</div>



	<script src="/js/plugins.js"></script>
	<script src="/js/main.js"></script>
	<script src="http://code.jquery.com/ui/1.9.1/jquery-ui.js"></script>
</body>
</html>
