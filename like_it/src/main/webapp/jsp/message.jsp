<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
		<title>Message</title>	
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
		<fmt:message bundle="${loc}" key="local.button.change_message" var="change_message" />
		<fmt:message bundle="${loc}" key="local.button.delete_message" var="delete_message" />
		<fmt:message bundle="${loc}" key="local.button.add_answer" var="add_answer" />
		<fmt:message bundle="${loc}" key="local.label.rate" var="rate" />
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
					<a href="../like_it" class="small_button menu_item">${main_page}</a>
				</li>
				<li>
					<a href="../like_it/categories" class="small_button menu_item">${categories}</a>
				</li>
				<li>
					<a href="../like_it/personal_account" class="small_button menu_item">${personal_account}</a>
				</li>
				<li>
					<a href="../like_it/sitemap" class="small_button menu_item">${sitemap}</a>
				</li>
			</ul>			
		</nav>
		<section class="col-6 col-m-9">
			<div class="message">
				<div class="account_information">
					<img src="jsp/content/foto1.png" alt="фото пользователя" class="photo">							
					<span class="user_name">Егор Егоров</span>
					<span class="user_rating">3/5</span>
				</div>
				<h1 class="big_message_name">Путешествие</h1>
				<a href="../like_it/category">
					<span class="small_category_name">Развлечения</span>		
				</a>
				<p class="text">Здравствуйте! Хочу посмотреть Беларусь, подскажите, с чего начать</p>
				<div class="date">2016-06-12 14:20:45</div>							
			</div>
			<div class="answer_list">
				<div class="answer">
					<div class="account_information">
						<img src="jsp/content/anonim.jpg" alt="фото пользователя" class="photo">							
						<span class="user_name">Иван Иванов</span>
						<span class="user_rating">1/5</span>
					</div>
					<p class="text">Плохой ответ. Плохой ответ. Плохой ответ. Плохой ответ. Плохой ответ. Плохой ответ. Плохой ответ. Плохой ответ. Плохой ответ.</p>	
					<div class="mark_line"> 					
						<span><c:out value="${rate}" /></span>
						<form class="language-form">
						<input type="hidden" name="person" value="login" />
							<input type="hidden" name="mark" value="-1" /> 
							<input type="submit" class="mark_button" value="-1" />
						</form>
						<form class="language-form">
						<input type="hidden" name="person" value="login" />
							<input type="hidden" name="mark" value="1" /> 
							<input type="submit" class="mark_button" value="1" />
						</form>
						<form class="language-form">
						<input type="hidden" name="person" value="login" />
							<input type="hidden" name="mark" value="2" /> 
							<input type="submit" class="mark_button" value="2" />
						</form>
						<form class="language-form">
						<input type="hidden" name="person" value="login" />
							<input type="hidden" name="mark" value="3" /> 
							<input type="submit" class="mark_button" value="3" />
						</form>
						<form class="language-form">
						<input type="hidden" name="person" value="login" />
							<input type="hidden" name="mark" value="4" /> 
							<input type="submit" class="mark_button" value="4" />
						</form>
						<form class="language-form">
						<input type="hidden" name="person" value="login" />
							<input type="hidden" name="mark" value="5" /> 
							<input type="submit" class="mark_button" value="5" />
						</form>
					</div>															
					<div class="answer_rating">0/5</div>
					<div class="date">2016-06-19 19:32:00</div>
				</div>
				<div class="answer">
					<div class="account_information">
						<img src="jsp/content/anonim.jpg" alt="фото пользователя" class="photo">					
						<span class="user_name">Пётр Петров</span>
						<span class="user_rating">4/5</span>
					</div>
					<p class="text">Хороший ответ. Хороший ответ. Хороший ответ. Хороший ответ. Хороший ответ. Хороший ответ. Хороший ответ.</p>			
					<div class="mark_line"> 					
						<span><c:out value="${rate}" /></span>
						<form class="language-form">
						<input type="hidden" name="person" value="log1" />
							<input type="hidden" name="mark" value="-1" /> 
							<input type="submit" class="mark_button" value="-1" />
						</form>
						<form class="language-form">
						<input type="hidden" name="person" value="log1" />
							<input type="hidden" name="mark" value="1" /> 
							<input type="submit" class="mark_button" value="1" />
						</form>
						<form class="language-form">
						<input type="hidden" name="person" value="log1" />
							<input type="hidden" name="mark" value="2" /> 
							<input type="submit" class="mark_button" value="2" />
						</form>
						<form class="language-form">
						<input type="hidden" name="person" value="log1" />
							<input type="hidden" name="mark" value="3" /> 
							<input type="submit" class="mark_button" value="3" />
						</form>
						<form class="language-form">
						<input type="hidden" name="person" value="log1" />
							<input type="hidden" name="mark" value="4" /> 
							<input type="submit" class="mark_button" value="4" />
						</form>
						<form class="language-form">
						<input type="hidden" name="person" value="log1" />
							<input type="hidden" name="mark" value="5" /> 
							<input type="submit" class="mark_button" value="5" />
						</form>
					</div>											
					<div class="answer_rating">3/5</div>
					<div class="date">2016-06-24 07:55:23</div>
				</div>
			</div>
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
				<li>
					<c:if test ="${sessionScope.status == 'admin'}">
						<a href="../like_it/add_message" class="small_button menu_item"><c:out value="${change_message}" /></a>
					</c:if>
				</li>			
				<li>
					<c:if test ="${sessionScope.status == 'admin'}">	
						<a href="../like_it/category" class="small_button menu_item"><c:out value="${delete_message}" /></a>
					</c:if>	
				</li>	
				<li>
					<c:if test ="${sessionScope.status == 'admin'}">
						<a href="../like_it/add_answer" class="small_button menu_item"><c:out value="${add_answer}" /></a>
					</c:if>
				</li>				
				<li>
					<c:if test ="${sessionScope.status == 'client'}">						
						<a href="../like_it/add_answer" class="small_button menu_item"><c:out value="${add_answer}" /></a>
					</c:if>
				</li>
			</ul>						
		</aside>
	</body>
</html>


