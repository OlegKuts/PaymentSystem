<%@ include file="../layout/taglib.jsp"%>
<c:if test="${not empty exception}">
	<div class="alert alert-warning">
		<c:out value="${exceptionMessage}" />
		<br>
	</div>
</c:if>

<c:if test="${param.successful eq true }">
	<div class="alert alert-success">Successful transaction</div>
</c:if>
<form:form modelAttribute="paymentForm" class="form-inline">
	<div class="form-group">
		<form:label class="sr-only" path="amount" />
		<div class="input-group">
			<div class="input-group-addon">$</div>
			<form:input path="amount" class="form-control"
				pattern="\d+(\.\d{1,2})?"
				oninvalid="this.setCustomValidity('Enter valid amount')"
				oninput="setCustomValidity('')" placeholder="Amount" />
		</div>
		<form:label class="sr-only" path="receiverAccountId" />
		<form:input path="receiverAccountId" class="form-control"
			pattern="\d+"
			oninvalid="this.setCustomValidity('Enter valid account number')"
			oninput="setCustomValidity('')" placeholder="Receiver AccountId" />
	</div>
	<form:button class="btn btn-info">Confirm</form:button>
	<br>
	<form:errors path="*" cssClass="text-danger" />
</form:form>

<%-- <form:form modelAttribute="paymentForm">
	<table>
		<tr>
			<td><form:input path="amount" pattern="\d+(\.\d{2})?"
					oninvalid="this.setCustomValidity('Enter valid amount')"
					oninput="setCustomValidity('')" /></td>
			<td><form:input path="receiverAccountId" pattern="\d+"
					oninvalid="this.setCustomValidity('Enter valid account number')"
					oninput="setCustomValidity('')" /></td>
			<td><input type="submit" value="confirm" /></td>
		</tr>
		<tr>
			<td><form:errors path="amount" cssClass="text-danger" /></td>
			<td><form:errors path="receiverAccountId" cssClass="text-danger" /></td>
		</tr>
	</table>
</form:form> --%>