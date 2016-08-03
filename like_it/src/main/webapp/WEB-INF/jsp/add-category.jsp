<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Add category</title>
<link rel="stylesheet" href="css/main.css">

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.locbutton.name.ru"
	var="ru_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.en"
	var="en_button" />
<fmt:message bundle="${loc}" key="local.button.add" var="add" />
<fmt:message bundle="${loc}" key="local.button.change" var="change" />
<fmt:message bundle="${loc}" key="local.label.title" var="title" />
<fmt:message bundle="${loc}" key="local.label.description"
	var="description" />
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
	<form class="center" action="Controller" method="post">
		<c:if test="${requestScope.change != 'yes'}">
			<input type="hidden" name="command" value="add_category" />
		</c:if>
		<c:if test="${requestScope.change == 'yes'}">
			<input type="hidden" name="command" value="change_category" />
		</c:if>
		<figure class="col-4 col-m-12 category_photo">
		<p>
			<img src="jsp/content/0.png" alt="изображение категории"
				class="big_photo">
		</p>
		<input value="${add}" type="file" name="image"> </figure>
		<div class="col-6 col-m-12">
			<div class="line">
				<label class="input_label" for="title1"><c:out
						value="${title}(ru)" /></label>
				<c:if test="${requestScope.change != 'yes'}">
					<input id="title_ru" name="title_ru" type="text">
				</c:if>
				<c:if test="${requestScope.change == 'yes'}">
					<input id="title_ru" name="title_ru" type="text"
						value="${sessionScope.category_ru.name}">
				</c:if>
			</div>
			<div class="line">
				<label class="input_label" for="description_ru"><c:out
						value="${description}(ru)" /></label>
				<textarea id="description_ru" name="description_ru"><c:if
						test="${requestScope.change == 'yes'}"><c:out 
						value="${sessionScope.category_ru.description}" /></c:if></textarea>
			</div>
			<div class="line">
				<label class="input_label" for="title2"><c:out
						value="${title}(en)" /></label>
				<c:if test="${requestScope.change != 'yes'}">
					<input id="title_en" name="title_en" type="text">
				</c:if>
				<c:if test="${requestScope.change == 'yes'}">
					<input id="title_en" name="title_en" type="text"
						value="${sessionScope.category_en.name}">
				</c:if>
			</div>
			<div class="line">
				<label class="input_label" for="description_en"><c:out
						value="${description}(en)" /></label>
				<textarea id="description_en" name="description_en"><c:if
						test="${requestScope.change == 'yes'}">
						<c:out value="${sessionScope.category_en.description}" />
					</c:if></textarea>
			</div>
		</div>
		<div class="col-12 col-m-12 menu">
			<c:if test="${requestScope.change != 'yes'}">
				<input class="menu_item inline_button" type="submit" value="${add}">
			</c:if>
			<c:if test="${requestScope.change == 'yes'}">
				<input class="menu_item inline_button" type="submit"
					value="${change}">
			</c:if>
		</div>
	</form>
</body>
</html>