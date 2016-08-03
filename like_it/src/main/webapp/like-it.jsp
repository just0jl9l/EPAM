<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"
	name="likeit, like it, forum, communication, advise">
<title>LikeIT</title>
<link rel="stylesheet" href="css/main.css">

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.locbutton.name.ru"
	var="ru_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.en"
	var="en_button" />
<fmt:message bundle="${loc}" key="local.menu.main_page" var="main_page" />
<fmt:message bundle="${loc}" key="local.menu.categories"
	var="categories" />
<fmt:message bundle="${loc}" key="local.menu.personal_account"
	var="personal_account" />
<fmt:message bundle="${loc}" key="local.menu.sitemap" var="sitemap" />
<fmt:message bundle="${loc}" key="local.main_text.title" var="title" />
<fmt:message bundle="${loc}" key="local.main_text.question.1"
	var="question1" />
<fmt:message bundle="${loc}" key="local.main_text.answer.1"
	var="answer1" />
<fmt:message bundle="${loc}" key="local.main_text.question.2"
	var="question2" />
<fmt:message bundle="${loc}" key="local.main_text.answer.2"
	var="answer2" />
<fmt:message bundle="${loc}" key="local.main_text.question.3"
	var="question3" />
<fmt:message bundle="${loc}" key="local.label.sign_up" var="answer3" />
<fmt:message bundle="${loc}" key="local.button.login" var="login" />
<fmt:message bundle="${loc}" key="local.button.logout" var="logout" />
<fmt:message bundle="${loc}" key="local.button.add_account"
	var="add_account" />
</head>
<body>
	<header> <img alt="Логотип" class="logo_image"
		src="content/like-it.png">
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
	</header>
	<nav class="col-3 col-m-3">
	<ul class="menu">
		<li><a href="../like-it" class="small_button menu_item"><c:out
					value="${main_page}" /></a></li>
		<li><a href="../like-it/Controller?command=categories"
			class="small_button menu_item"><c:out value="${categories}" /></a></li>
		<li><a href="../like-it/Controller?command=personal-account"
			class="small_button menu_item"><c:out value="${personal_account}" /></a>
		</li>
		<li><a href="../like-it/Controller?command=sitemap"
			class="small_button menu_item"><c:out value="${sitemap}" /></a></li>
	</ul>
	</nav>
	<section class="col-6 col-m-9 main_text">
	<h2>
		<c:out value="${title}" />
	</h2>
	<h4>
		<c:out value="${question1}" />
	</h4>
	<p>
		<c:out value="${answer1}" />
	</p>
	<h4>
		<c:out value="${question2}" />
	</h4>
	<p>
		<c:out value="${answer2}" />
	</p>
	<h4>
		<c:out value="${question3}" />
	</h4>
	<a href="../like-it/registration" class="link"><c:out
			value="${answer3}" /></a> </section>
	<aside class="col-3 col-m-12">
	<ul class="menu">
		<li><c:if test="${sessionScope.status != 'admin'}">
				<c:if test="${sessionScope.status != 'client'}">
					<a href="../like-it/login" class="menu_item"><c:out
							value="${login}" /></a>
				</c:if>
			</c:if></li>
		<li><c:if test="${sessionScope.status == 'admin'}">
				<form action="Controller" method="post">
					<input type="hidden" name="command" value="logout" /> <input
						type="submit" class="menu_item block" value="${logout}" />
				</form>
			</c:if></li>
		<li><c:if test="${sessionScope.status == 'client'}">
				<form action="Controller" method="post">
					<input type="hidden" name="command" value="logout" /> <input
						type="submit" class="menu_item block" value="${logout}" />
				</form>
			</c:if></li>
		<li><c:if test="${sessionScope.status == 'admin'}">
				<a href="../like-it/registration" class="small_button menu_item"><c:out
						value="${add_account}" /></a>
			</c:if></li>
	</ul>
	</aside>
</body>
</html>