<%@ include file="../layout/taglib.jsp"%>
<c:if test="${not empty exception}">
	<div class="alert alert-warning">
			<c:out value="${exceptionMessage}" />
			<br>
	</div>
</c:if>
<form:form modelAttribute="refundForm">
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
</form:form>
<!-- pattern="\d*\.?\d{1,2}" -->
<form class="form-inline">
  <div class="form-group">
    <label class="sr-only" for="exampleInputAmount">Amount (in dollars)</label>
    <div class="input-group">
      <div class="input-group-addon">$</div>
      <input type="text" class="form-control" id="exampleInputAmount" placeholder="Amount">
      <div class="input-group-addon">.00</div>
    </div>
  </div>
  <button type="submit" class="btn btn-primary">Transfer cash</button>
</form>

<div class="row">
  <div class="col-xs-2">
    <input type="text" class="form-control" placeholder=".col-xs-2">
  </div>
  <div class="col-xs-3">
    <input type="text" class="form-control" placeholder=".col-xs-3">
  </div>
  <div class="col-xs-4">
    <input type="text" class="form-control" placeholder=".col-xs-4">
  </div>
</div>