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
<fmt:message bundle="${loc}" key="local.error.not_all_error"
	var="not_all_error" />
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
			<img src="content/0.png" alt="изображение категории"
				class="big_photo">
		</p>
		<input value="${add}" type="file" name="image"> </figure>
		<div class="col-6 col-m-12">
			<c:if test="${sessionScope.notAllDataError == 'yes'}">
			<label class="input_error" id="login_input_error" for="login"><c:out
					value="${not_all_error}" /></label>
					</c:if>
			<div class="line">
				<label class="input_label" for="titleRu"><c:out
						value="${title}(ru)" /></label>
				<c:if test="${requestScope.change != 'yes'}">
					<input id="title_ru" name="titleRu" type="text">
				</c:if>
				<c:if test="${requestScope.change == 'yes'}">
					<input id="title_ru" name="titleRu" type="text"
						value="${sessionScope.categoryRu.name}">
				</c:if>
			</div> 
			<div class="line">
				<label class="input_label" for="descriptionRu"><c:out
						value="${description}(ru)" /></label>
				<textarea id="description_ru" name="descriptionRu"><c:if
						test="${requestScope.change == 'yes'}"><c:out 
						value="${sessionScope.categoryRu.description}" /></c:if></textarea>
			</div>
			<div class="line">
				<label class="input_label" for="titleEn"><c:out
						value="${title}(en)" /></label>
				<c:if test="${requestScope.change != 'yes'}">
					<input id="title_en" name="titleEn" type="text">
				</c:if>
				<c:if test="${requestScope.change == 'yes'}">
					<input id="title_en" name="titleEn" type="text"
						value="${sessionScope.categoryEn.name}">
				</c:if>
			</div>
			<div class="line">
				<label class="input_label" for="descriptionEn"><c:out
						value="${description}(en)" /></label>
				<textarea id="description_en" name="descriptionEn"><c:if
						test="${requestScope.change == 'yes'}"><c:out 
						value="${sessionScope.categoryEn.description}" /></c:if></textarea>
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