<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
		<title>Categories</title>		
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
		<fmt:message bundle="${loc}" key="local.button.add_category" var="add_category" />
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
					<a href="../like_it/Controller?command=personal_account" class="small_button menu_item"><c:out value="${personal_account}" /></a>
				</li>
				<li>
					<a href="../like_it/Controller?command=sitemap" class="small_button menu_item"><c:out value="${sitemap}" /></a>
				</li>
			</ul>			
		</nav>
		<section class="col-6 col-m-9">
			<div class="categories_menu">
				<c:forEach var="item" items="${sessionScope.categories}">
					<a href="../like_it/Controller?category_id=${item.id}&command=category" class="category">		
						<img src="${item.image.path}" alt="Изображение категории" class="category_image">
						<h2 class="category_name"><c:out value="${item.name}" /></h2>
						<p class="category_text"><c:out value="${item.description}"/></p>
					</a>
				</c:forEach>
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
						<a href="../like_it/add_category" class="small_button menu_item"><c:out value="${add_category}" /></a>
					</c:if>
				</li>
			</ul>						
		</aside>
	</body>
</html>


