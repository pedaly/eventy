<%@ include file="common/taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<nav class="top_menu">
	<div class="logo"></div>
	<div class="links">
		<a href="/eventsList"> Zarządzaj wydarzeniami </a> <span>&nbsp
			| &nbsp </span> <a href="/categoriesList"> Zarządzaj kategoriami</a> <span>&nbsp |
			&nbsp </span> <a href="#"> Zarządzaj uzytkownikami </a> <span>&nbsp |
			&nbsp </span><span> <%
 	UserService userService = UserServiceFactory.getUserService();
 	User user = userService.getCurrentUser();
 	if (user != null) {
 		pageContext.setAttribute("user", user);
 %> Witaj, ${fn:escapeXml(user.nickname)}! (<a
			href="<%=userService.createLogoutURL("/index")%>"> Wyloguj</a>.)

			<%
 	} else {
 		System.out.println(request.getRequestURI());
 		System.out.println(request.getRequestURI());
 %> <a href="<%=userService.createLoginURL("/eventsList")%>">
				Logowanie</a> <%
 	}
 %>
		</span>
	</div>
</nav>