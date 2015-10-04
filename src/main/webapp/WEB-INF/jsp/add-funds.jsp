<%@ include file="../layout/taglib.jsp"%>

<form:form modelAttribute="refundForm">
	<table class="table table-bordered">
		<tr>
			<td>Choose card:</td>
			<td><input type="text" name="amount" pattern="\d*\.?\d{1,2}"
				oninvalid="this.setCustomValidity('Enter valid amount')"
				oninput="setCustomValidity('')" /></td>
			<td><select name="cardId">
					<c:forEach items="${cards}" var="card">
						<option value="${card.id}">${card.cardNumber}-${card.amount}</option>
					</c:forEach>
			</select> <input type="hidden" name="accountId" value="${accountId}" /> <input
				type="submit" value="Refund" /></td>
			<td></td>
		</tr>
	</table>
</form:form>