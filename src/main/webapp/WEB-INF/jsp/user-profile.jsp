<%@ include file="../layout/taglib.jsp"%>
<div class="col-sm-4">
	<table class="table">
		<tr>
			<td>User:</td>
			<td>${user.userInformation.firstName}
				${user.userInformation.lastName}</td>
			<td></td>
		</tr>
		<tr>
			<td>AccountID:</td>
			<td>${user.account.id}</td>
			<td><c:if test="${user.account.active eq true }">
					<a
						href="<spring:url value="/users/block/${user.account.id}.html" />"
						class="btn btn-danger btn-sm triggerRemove"> block account </a>
				</c:if> <c:if test="${user.account.active eq false }">
					<p class="btn-danger">Account is blocked</p>
				</c:if></td>
		</tr>
		<tr>
			<td>Balance:</td>
			<td>${user.account.balance}</td>
			<td><c:if test="${user.account.active eq true }">
					<a
						href="<spring:url value="/users/block/${user.account.id}.html" />"
						class="btn btn-info btn-sm triggerRemove"> add funds </a>
				</c:if> <c:if test="${user.account.active eq false }">
					<p class="btn-danger">Account is blocked</p>
				</c:if></td>
		</tr>
		<tr>
			<td><c:if test="${user.account.active eq true }">
				<a
					href="<spring:url value="/users/block/${user.account.id}.html" />"
					class="btn btn-info btn-sm triggerRemove"> make a payment </a>
			</c:if></td>
		</tr>
	</table>
</div>



<table class="table table-bordered table-hover table-striped">
	<caption>User's payments</caption>
	<thead>
		<th>Amount</th>
		<th>Date</th>
		<th>Receiver Account</th>
	</thead>
	<tbody>
		<c:forEach items="${payers}" var="payment">
			<tr>
				<td><c:out value="${payment.amount}" /></td>
				<td><c:out value="${payment.paymentDate}" /></td>
				<td align="center"><c:out value="${payment.receiverAccount.id}" /></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<br />