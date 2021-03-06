﻿<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Category</title>
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
<fmt:message bundle="${loc}" key="local.button.login" var="login" />
<fmt:message bundle="${loc}" key="local.button.logout" var="logout" />
<fmt:message bundle="${loc}" key="local.button.change_category"
	var="change_category" />
<fmt:message bundle="${loc}" key="local.button.delete_category"
	var="delete_category" />
<fmt:message bundle="${loc}" key="local.button.add_message"
	var="add_message" />
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
	<section class="col-6 col-m-9">
	<div class="category">
		<img src="${sessionScope.category.image.path}"
			alt="Изображение категории" class="category_image">
		<h1 class="category_name">
			<c:out value="${sessionScope.category.name}" />
		</h1>
		<p class="category_text">
			<c:out value="${sessionScope.category.description}" />
		</p>
	</div>
	<div class="messages_list">
		<c:forEach var="item" items="${sessionScope.category.messages}">
			<a href="../like-it/Controller?messageId=${item.id}&command=message"
				class="message">
				<div class="account_information">
					<img src="${item.author.photo.path}" alt="фото пользователя"
						class="photo"> <span class="user_name"><c:out
							value="${item.author.name}" /> <c:out
							value="${item.author.surname}" /></span> <span class="user_rating"><c:out
							value="${item.author.rating}" />/5</span>
				</div>
				<h3 class="message_name">
					<c:out value="${item.name}" />
				</h3>
				<p class="text">
					<c:out value="${item.text}" />
				</p>
				<div class="date">					
					<fmt:formatDate pattern="dd/MM/yyyy" 
		            value="${item.dateOfPosting}" />
				</div>
			</a>
		</c:forEach>
	</div>
	</section>
	<aside class="col-3 col-m-12">
	<ul class="menu">
		<li><c:if test="${sessionScope.status != 'admin'}">
				<c:if test="${sessionScope.status != 'client'}">
					<a href="../like-it/login" class="menu_item"><c:out
							value="${login}" /></a>
				</c:if>
			</c:if></li>
		<li><c:if test="${sessionScope.status == 'admin'}">
				<a href="../like-it/Controller?command=logout"
					class="menu_item block"><c:out value="${logout}" /></a>
			</c:if></li>
		<li><c:if test="${sessionScope.status == 'client'}">
				<a href="../like-it/Controller?command=logout"
					class="menu_item block"><c:out value="${logout}" /></a>
			</c:if></li>
		<li><c:if test="${sessionScope.status == 'admin'}">
				<a href="../like-it/Controller?command=goto-change-category"
					class="small_button menu_item"><c:out
						value="${change_category}" /></a>
			</c:if></li>
		<li><c:if test="${sessionScope.status == 'admin'}">
				<a
					href="../like-it/Controller?command=delete-category&categoryId=${sessionScope.category.id}"
					class="small_button menu_item"><c:out
						value="${delete_category}" /></a>
			</c:if></li>
		<li><c:if test="${sessionScope.status == 'admin'}">
				<a href="../like-it/add-message" class="small_button menu_item"><c:out
						value="${add_message}" /></a>
			</c:if></li>
		<li><c:if test="${sessionScope.status == 'client'}">
				<a href="../like-it/add-message" class="small_button menu_item"><c:out
						value="${add_message}" /></a>
			</c:if></li>
	</ul>
	</aside>
</body>
</html>


