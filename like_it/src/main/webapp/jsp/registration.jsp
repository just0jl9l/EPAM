<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
		<title>Registration</title>	
		<link rel="stylesheet" href="jsp/css/main.css">
		
		<fmt:setLocale value="${sessionScope.local}" />
		<fmt:setBundle basename="localization.local" var="loc" />
		<fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
		<fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
		<fmt:message bundle="${loc}" key="local.main_text.registration_text" var="registration_text" />
		<fmt:message bundle="${loc}" key="local.label.login" var="login" />
		<fmt:message bundle="${loc}" key="local.label.status" var="status" />
		<fmt:message bundle="${loc}" key="local.label.name" var="name" />
		<fmt:message bundle="${loc}" key="local.label.surname" var="surname" />
		<fmt:message bundle="${loc}" key="local.label.password" var="password" />
		<fmt:message bundle="${loc}" key="local.label.password2" var="password2" />
		<fmt:message bundle="${loc}" key="local.label.client" var="client" />
		<fmt:message bundle="${loc}" key="local.label.admin" var="admin" />
		<fmt:message bundle="${loc}" key="local.label.sign_up" var="sign_up" />
		<fmt:message bundle="${loc}" key="local.label.client" var="client" />
		<fmt:message bundle="${loc}" key="local.label.admin" var="admin" />
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
		<form>
			<figure class="col-4 col-m-12 account_photo center">
				<p><img src="jsp/content/anonim.jpg" alt="фотография пользователя" class="big_photo"></p>
				<input name="Добавить"  type="file">
			</figure>				
			<section class="col-6 col-m-12">
				<div class="line">
					<h1><c:out value="${registration_text}" /></h1>
				</div>
				<div class="line">
					<label class="input_label" for="login"><c:out value="${login}" /></label>
					<input id="login" name="login" type="text">
					<label class="input_error" id="login_input_error" for="login"></label>
				</div>
				<div class="line">
					<label class="input_label" for="name"><c:out value="${name}" /></label>
					<input id="name" name="name" type="text">
				</div>
				<div class="line">
					<label class="input_label"><c:out value="${surname}" /></label>
					<input id="surname" name="surname" type="text">
				</div>
				<div class="line">
					<label class="input_label" for="status"><c:out value="${status}" /></label>
					<select id="status" name="status">
						<option class="status"><c:out value="${client}" /></option>
						<option class="status"><c:out value="${admin}" /></option>
					</select>
				</div>
				<div class="line">
					<label class="input_label" for="password"><c:out value="${password}" /></label>
					<input id="password" name="password" type="password">
				</div>
				<div class="line">
					<label class="input_label" for="password2"><c:out value="${password2}" /></label>
					<input id="password2" name="password2" type="password">
					<label class="input_error" id="password2_input_error" for="password2"></label>
				</div>	
			</section>
			<footer class="col-12 col-m-12 menu">	
				<input class="menu_item inline_button" type="submit" value="${sign_up}">			
			</footer>
		</form>
	</body>
</html>