<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles-extras"
	prefix="tilesx"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">


<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:getAsString name="title" /></title>
</head>
<body>
	<tilesx:useAttribute name="current" />
	<div class="container">
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#navbar" aria-expanded="false"
						aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href='<spring:url value="/"/>'>Payment
						System</a>
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav">

						<li class="${current == 'index' ? 'active' : ''}"><a
							href='<spring:url value="/"/>'>Home</a></li>

						<security:authorize access="hasRole('ROLE_ADMIN')">
							<li class="${current == 'users' ? 'active' : ''}"><a
								href='<spring:url value="/users.html"/>'>Users</a></li>
						</security:authorize>

						<security:authorize access="! isAuthenticated()">
							<li class="${current == 'register' ? 'active' : '' }"><a
								href='<spring:url value="/register.html"/>'>Register</a></li>
							<li class="${current == 'login' ? 'active' : '' }"><a
								href='<spring:url value="/login.html"/>'>Login</a></li>
						</security:authorize>

						<security:authorize
							access="isAuthenticated() && ! hasRole('ROLE_ADMIN')">
							<li class="${current == 'users' ? 'active' : '' }"><a
								href='<spring:url value="/account.html"/>'>My Account</a></li>
						</security:authorize>

						<security:authorize access="isAuthenticated()">
							<li><a href="<spring:url value="/logout" />">Logout</a></li>
						</security:authorize>
					</ul>

				</div>
				<!--/.nav-collapse -->
			</div>
			<!--/.container-fluid -->
		</nav>

		<tiles:insertAttribute name="body" />


		<tiles:insertAttribute name="footer" />


	</div>
</body>
</html>