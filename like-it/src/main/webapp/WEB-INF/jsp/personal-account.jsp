<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Personal account</title>
<link rel="stylesheet" href="css/main.css">

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.locbutton.name.ru"
	var="ru_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.en"
	var="en_button" />
<fmt:message bundle="${loc}" key="local.label.account_data"
	var="account_data" />
<fmt:message bundle="${loc}" key="local.label.login" var="login" />
<fmt:message bundle="${loc}" key="local.label.status" var="status" />
<fmt:message bundle="${loc}" key="local.label.rating" var="rating" />
<fmt:message bundle="${loc}" key="local.label.name" var="name" />
<fmt:message bundle="${loc}" key="local.label.surname" var="surname" />
<fmt:message bundle="${loc}" key="local.label.edit" var="edit" />
<fmt:message bundle="${loc}" key="local.label.delete" var="delete" />
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
	<figure class="col-4 col-m-12 center">
	<p>
		<img src="${sessionScope.account.photo.path}"
			alt="фотография пользователя" class="big_photo">
	</p>
	</figure>
	<section class="col-6 col-m-12">
	<div class="line">
		<h1>
			<c:out value="${account_data}" />
		</h1>
	</div>
	<div class="line">
		<c:out value="${login}" />
		<span class="account_data"><c:out
				value="${sessionScope.account.login}" /></span>
	</div>
	<div class="line">
		<span><c:out value="${status}" /></span> <span class="account_data"><c:out
				value="${sessionScope.account.status}" /></span>
	</div>
	<div class="line">
		<c:out value="${name}" />
		<span class="account_data"><c:out
				value="${sessionScope.account.name}" /></span>
	</div>
	<div class="line">
		<c:out value="${surname}" />
		<span class="account_data"><c:out
				value="${sessionScope.account.surname}" /></span>
	</div>
	<div class="line">
		<c:out value="${rating}" />
		<span class="account_data"><c:out
				value="${sessionScope.account.rating}" /></span>
	</div>
	</section>
	<footer class="col-12 col-m-12 menu"> <a
		href="../like-it/Controller?command=goto-change-personal-account&accountId=${sessionScope.account.id}"
		class="menu_item inline_button"><c:out value="${edit}" /></a> 
		<a
		href="../like-it/Controller?command=delete-personal-account&accountId=${sessionScope.account.id}"
		class="menu_item inline_button"><c:out value="${delete}" /></a></footer>
</body>
</html>