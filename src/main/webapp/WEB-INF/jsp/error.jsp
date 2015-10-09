<%@ include file="../layout/taglib.jsp"%>
<!DOCTYPE/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Something happened</h2>
	<c:if test="${url != null}"> URL: ${url} </c:if>
	<br> Exception: ${ex}
</body>
</html>