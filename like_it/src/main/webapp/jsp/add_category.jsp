<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
		<title>Add category</title>
		<link rel="stylesheet" href="jsp/css/main.css">
		
		<fmt:setLocale value="${sessionScope.local}" />
		<fmt:setBundle basename="localization.local" var="loc" />
		<fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
		<fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
		<fmt:message bundle="${loc}" key="local.button.add" var="add" />
		<fmt:message bundle="${loc}" key="local.label.title" var="title" />
		<fmt:message bundle="${loc}" key="local.label.description" var="description" />
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
		<form class="center">
			<figure class="col-4 col-m-12 category_photo">
				<p><img src="jsp/content/empty.png" alt="изображение категории" class="big_photo"></p>
				<input value="${add}" type="file">
			</figure>				
			<div class="col-6 col-m-12">
				<div class="line">
					<label class="input_label" for="title"><c:out value="${title}" /></label>
					<input id="title" name="title" type="text">
					<label class="note" id="title_error" for="title"></label>
				</div>
				<div class="line">
					<label class="input_label" for="description"><c:out value="${description}" /></label>
					<input id="description" name="description" type="text">
				</div>
			</div>
			<div class="col-12 col-m-12 menu">	
				<input class="menu_item inline_button" type="submit" value="${add}">	
			</div>
		</form>
	</body>
</html>