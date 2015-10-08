<%@ include file="../layout/taglib.jsp"%>
<form:form modelAttribute="paymentForm">
	<table>
		<tr>
			<td><form:input path="amount" pattern="\d+(\.\d{2})?"
					oninvalid="this.setCustomValidity('Enter valid amount')"
					oninput="setCustomValidity('')" /></td>
			<td><form:input path="receiverAccountId" pattern="\d+"
			oninvalid="this.setCustomValidity('Enter valid account number')"
					oninput="setCustomValidity('')"/></td>
			<td><input type="submit" value="confirm" /></td>
		</tr>
		<tr>
			<td><form:errors path="amount" cssClass="text-danger"/></td>
			<td><form:errors path="receiverAccountId" cssClass="text-danger"/></td>
		</tr>
	</table>
</form:form>