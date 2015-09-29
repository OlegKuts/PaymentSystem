<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp" %>

<c:forEach items="${users}" var="user">
	<a href='<spring:url value="/useraccount/${user.id}.html"/>'><c:out
			value="${user.username}" /></a>
	<br>
</c:forEach>
