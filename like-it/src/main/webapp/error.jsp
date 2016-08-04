<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Error</title>
<link rel="stylesheet" href="css/main.css">

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.menu.main_page" var="main_page" />
<fmt:message bundle="${loc}" key="local.label.error" var="error" />
<fmt:message bundle="${loc}" key="local.main_text.error_text"
	var="error_text" />
</head>
<body>
	<section class="error_panel">
	<h1>
		<img alt="Логотип" class="logo_image login_image"
			src="content/like-it.png">
	</h1>
	<h2 class="error_label">
		<c:out value="${error}" />
	</h2>
	<div class="line">
		<p class="text">
			<c:out value="${sessionScope.error}" />
		</p>
	</div>
	<ul class="menu">
		<li><a href="../like-it" class="link"><c:out
					value="${main_page}" /></a></li>
	</ul>
	</section>
</body>
</html>