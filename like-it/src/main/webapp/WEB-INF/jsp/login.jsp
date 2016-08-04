<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Log in</title>
<link rel="stylesheet" href="css/main.css">

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.locbutton.name.ru"
	var="ru_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.en"
	var="en_button" />
<fmt:message bundle="${loc}" key="local.button.login" var="log_in" />
<fmt:message bundle="${loc}" key="local.label.sign_up" var="sign_up" />
<fmt:message bundle="${loc}" key="local.label.login" var="login" />
<fmt:message bundle="${loc}" key="local.label.password" var="password" />
<fmt:message bundle="${loc}" key="local.error.login_and_password_error"
	var="error" />
</head>
<body>
	<section class="logination">
	<form class="language-form" action="Controller" method="post">
		<input type="hidden" name="command" value="localization" /> <input
			type="hidden" name="local" value="ru" /> <input type="submit"
			class="language_button" value="${ru_button}" />
	</form>
	<form class="language-form" action="Controller" method="post">
		<input type="hidden" name="command" value="localization" /> <input
			type="hidden" name="local" value="en" /> <input type="submit"
			class="language_button" value="${en_button}" />
	</form>
	<h1 class="right">
		<img alt="Логотип" class="logo_image" src="content/like-it.png">
	</h1>
	<form action="Controller" method="post">
		<input type="hidden" name="command" value="login" />
		<c:if test="${sessionScope.error == 'yes'}">
			<span class="input_error" id="log_in_error"><c:out
					value="${error}" /></span>
		</c:if>
		<div class="line">
			<label class="input_label" for="login"><c:out
					value="${login}" /></label> <input id="login" name="login" type="text">
		</div>
		<div class="line">
			<label class="input_label" for="password"><c:out
					value="${password}" /></label> <input id="password" name="password"
				type="password">
		</div>
		<input class="menu_item inline_button" type="submit" value="${log_in}">
	</form>
	<a href="../like-it/registration" class="link"><c:out
			value="${sign_up}" /></a> </section>
</body>
</html>