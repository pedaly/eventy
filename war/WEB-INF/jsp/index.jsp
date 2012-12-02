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
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/style.css">
<script src="js/vendor/jquery-1.8.2.min.js">
	
</script>
<script src="js/vendor/modernizr-2.6.2.min.js">
	
</script>

</head>
<body>

	<div class="mainPage">
		<a href="/eventsList"><div class="logoMain"></div></a>
		<div>
			<p>
				<%
					UserService userService = UserServiceFactory.getUserService();
					User user = userService.getCurrentUser();
					if (user != null) {
						pageContext.setAttribute("user", user);
				%>
				Witaj, ${fn:escapeXml(user.nickname)}! (<a class="login"
					href="<%=userService.createLogoutURL("/")%>"> Wyloguj</a>.)

				<%
					} else {
						System.out.println(request.getRequestURI());
						System.out.println(request.getRequestURI());
				%>
				<a class="login"
					href="<%=userService.createLoginURL("/")%>">
					Logowanie</a>

				<%
					}
				%>
			
		</div>
	</div>
</body>
</html>