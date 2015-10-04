<%@ include file="../layout/taglib.jsp"%>
<form:form modelAttribute="paymentForm">
	<table>
		<tr>
			<td><form:input path="amount" /></td>
			<td><form:input path="receiverAccountId" /></td>
			<td><input type="submit" value="confirm"/></td>
		</tr>
	</table>
</form:form>