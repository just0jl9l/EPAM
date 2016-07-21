<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
		<title>Add message</title>	
		<link rel="stylesheet" href="jsp/css/main.css">
		
		<fmt:setLocale value="${sessionScope.local}" />
		<fmt:setBundle basename="localization.local" var="loc" />
		<fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
		<fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
		<fmt:message bundle="${loc}" key="local.menu.main_page" var="main_page" />
		<fmt:message bundle="${loc}" key="local.menu.categories" var="categories" />
		<fmt:message bundle="${loc}" key="local.menu.personal_account" var="personal_account" />
		<fmt:message bundle="${loc}" key="local.menu.sitemap" var="sitemap" />
		<fmt:message bundle="${loc}" key="local.button.login" var="login" />
		<fmt:message bundle="${loc}" key="local.button.add" var="add" />
		<fmt:message bundle="${loc}" key="local.label.title" var="title" />
		<fmt:message bundle="${loc}" key="local.label.text" var="text" />
	</head>
	<body>
		<header>    
			<img alt= "Логотип" class="logo_image" src="jsp/content/like_it.png">
			<form class="language-form" action="Controller" method="post">
				<input type="hidden" name="command" value="localization" /> 
				<input type="hidden" name="local" value="ru" />  
				<input type="submit" class="language_button" value="${ru_button}" />
			</form>
			<form class="language-form" action="Controller" method="post">
				<input type="hidden" name="command" value="localization" /> 
				<input type="hidden" name="local" value="en" /> 
				<input type="submit" class="language_button" value="${en_button}" />
			</form>
		</header>
		<nav class="col-3 col-m-3">
			<ul class="menu">
				<li>
					<a href="../like_it" class="small_button menu_item"><c:out value="${main_page}" /></a>
				</li>
				<li>
					<a href="../like_it/categories" class="small_button menu_item"><c:out value="${categories}" /></a>
				</li>
				<li>
					<a href="../like_it/personal_account" class="small_button menu_item"><c:out value="${personal_account}" /></a>
				</li>
				<li>
					<a href="../like_it/sitemap" class="small_button menu_item"><c:out value="${sitemap}" /></a>
				</li>
			</ul>			
		</nav>
		<section class="col-6 col-m-9">
			<form>
				<div class="category">
					<img src="jsp/content/entertainment.png" alt="Категория Развлечения" class="category_image">
					<h1 class="category_name">Развлечения</h1>
					<p class="category_text">Хобби, отпуск, туризм, спорт - всё здесь!</p>
				</div>
				<div class="line">
					<label class="input_label" for="title"><c:out value="${title}" /></label>
					<input id="title" name="title" type="text">
					<label class="note" id="title_error" for="title"></label>
				</div>
				<div class="line">
					<label class="input_label" for="text"><c:out value="${text}" /></label>
					<textarea id="text" name="text"></textarea>
				</div>	
				<div class="center">
					<input class="menu_item inline_button" type="submit" name="${add}">	
				</div>
			</form>
		</section>
		<aside class="col-3 col-m-12">	
			<ul class="menu">
				<li>
					<a href="../like_it/login" class="menu_item"><c:out value="${login}" /></a>	
				</li>
			</ul>
		</aside>
	</body>
</html>