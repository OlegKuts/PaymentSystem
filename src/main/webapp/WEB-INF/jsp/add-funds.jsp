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
<form:form modelAttribute="refundForm" class="form-inline">

	<div class="form-group">
		<form:label class="sr-only" path="amount" />
		<div class="input-group">
			<div class="input-group-addon">$</div>
			<form:input path="amount" class="form-control"
				pattern="\d+(\.\d{2})?"
				oninvalid="this.setCustomValidity('Enter valid amount')"
				oninput="setCustomValidity('')" placeholder="Amount" />
		</div>
		<form:label class="sr-only" path="cardId" />
		<form:select path="cardId" id="card" class="form-control">
			<c:forEach items="${cards}" var="card">
				<form:option value="${card.id}">"${card.cardNumber}"-
					${card.amount}$</form:option>
			</c:forEach>
		</form:select>
		<form:hidden path="accountId" value="${accountId}" />
	</div>
	<form:button class="btn btn-info">Refund</form:button>
	<br>
	<form:errors path="*" cssClass="text-danger" />
</form:form>

<%-- <form:form modelAttribute="refundForm">
	<table class="table table-bordered">
		<tr>
			<td class="col-xs-1">Choose card:</td>
			<td class="col-xs-1"><form:input path="amount" pattern="\d+(\.\d{2})?"
					oninvalid="this.setCustomValidity('Enter valid amount')"
					oninput="setCustomValidity('')" /></td>
			<td class="col-xs-1"><select name="cardId" class="form-control ">
					<c:forEach items="${cards}" var="card">
						<option value="${card.id}">"${card.cardNumber}" ${card.amount}</option>
					</c:forEach>
			</select> <input type="hidden" name="accountId" value="${accountId}" /> <input
				type="submit" value="Refund" /></td>
		</tr>
	</table>
	<form:errors path="*" cssClass="text-danger"/>
</form:form> --%>
<!-- pattern="\d*\.?\d{1,2}" -->