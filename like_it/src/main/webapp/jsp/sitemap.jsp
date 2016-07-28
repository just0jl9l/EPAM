<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" name="likeit, like it, forum, communication, advise">
		<title>Sitemap</title>
		<link rel="stylesheet" href="jsp/css/main.css">
		
		<fmt:setLocale value="${sessionScope.local}" />
		<fmt:setBundle basename="localization.local" var="loc" />
		<fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
		<fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
		<fmt:message bundle="${loc}" key="local.menu.main_page" var="main_page" />
		<fmt:message bundle="${loc}" key="local.menu.categories" var="categories" />
		<fmt:message bundle="${loc}" key="local.menu.personal_account" var="personal_account" />
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
		<section>
			<ul class="menu">
				<li>
					<h1 class="h1"><a href="../like_it" class="sitemap_item line"><c:out value="${main_page}" /></a></h1>					
					<ul class="menu">
						<li>
							<h2 class="h2"><a href="../like_it/Controller?command=categories" class="sitemap_item line"><c:out value="${categories}" /></a></h2>	
							<ul class="menu">
								<c:forEach var="item" items="${sessionScope.categories}">
									<li>
										<h3 class="h3">
											<a href="../like_it/Controller?category_id=${item.id}&command=category" class="category">
												<c:out value="${item.name}" />
											</a>
										</h3>
									</li>
								</c:forEach>	
							</ul>				
						</li>
						<li>
							<h2 class="h2"><a href="../like_it/Controller?command=personal_account" class="sitemap_item line"><c:out value="${personal_account}" /></a></h2>
						</li>	
					</ul>	
				</li>
			</ul>	
		</section>
	</body>
</html>


