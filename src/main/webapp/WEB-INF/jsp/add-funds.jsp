<%@ include file="../layout/taglib.jsp"%>
<form:form method="post">
	<tr>
		<td>Choose card:</td>
		<td><form:select path="card" items="${cards}" multiple="false" /></td>
		<td><input type="submit" value="Refund" /></td>
	</tr>
</form:form>