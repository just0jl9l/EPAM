<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
		<title>Add answer</title>	
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
		<fmt:message bundle="${loc}" key="local.button.logout" var="logout" />
		<fmt:message bundle="${loc}" key="local.label.text" var="text" />
		<fmt:message bundle="${loc}" key="local.button.add" var="add" />
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
					<a href="../like_it/Controller?command=categories" class="small_button menu_item"><c:out value="${categories}" /></a>
				</li>
				<li>
					<a href="../like_it/Controller?command=personal_account" class="small_button menu_item"><c:out value="${personal_account}" /></a>
				</li>
				<li>
					<a href="../like_it/Controller?command=sitemap" class="small_button menu_item"><c:out value="${sitemap}" /></a>
				</li>
			</ul>			
		</nav>
		<section class="col-6 col-m-9">
			<div class="message">
				<div class="account_information">
					<img src="${sessionScope.message.author.photo.path}" alt="фото пользователя" class="photo">							
					<span class="user_name"><c:out value="${sessionScope.message.author.name}" /> <c:out value="${sessionScope.message.author.surname}" /></span>
					<span class="user_rating"><c:out value="${sessionScope.message.author.rating}" />/5</span>
				</div>
				<h1 class="big_message_name"><c:out value="${sessionScope.message.name}" /></h1>
				<a href="../like_it/category">
					<span class="small_category_name"><c:out value="${sessionScope.category.name}" /></span>		
				</a>
				<p class="text"><c:out value="${sessionScope.message.text}" /></p>
				<div class="date"><c:out value="${sessionScope.message.dateOfPosting}" /></div>							
			</div>
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="add_answer" /> 
				<div class="line">
					<label class="input_label" for="text"><c:out value="${text}" /></label>
					<textarea id="text" name="text"></textarea>
				</div>
				<div class="center">
					<input class="menu_item inline_button" type="submit" value="${add}">	
				</div>			
			</form>
		</section>
		<aside class="col-3 col-m-12">	
			<ul class="menu">
				<li>
					<c:if test ="${sessionScope.status != 'admin'}">
						<c:if test ="${sessionScope.status != 'client'}">
							<a href="../like_it/login" class="menu_item"><c:out value="${login}" /></a>
						</c:if>
					</c:if>
				</li>				
				<li>
					<c:if test ="${sessionScope.status == 'admin'}">
						<form action="Controller" method="post">
							<input type="hidden" name="command" value="logout" />
							<input type="submit" class="menu_item block" value="${logout}" />
						</form>
					</c:if>
				</li>				
				<li>
					<c:if test ="${sessionScope.status == 'client'}">						
						<form action="Controller" method="post">
							<input type="hidden" name="command" value="logout" />
							<input type="submit" class="menu_item block" value="${logout}" />
						</form>
					</c:if>
				</li>				
			</ul>						
		</aside>
	</body>
</html>


