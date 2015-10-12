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

<div>

	<!-- Nav tabs -->
	<ul class="nav nav-tabs" role="tablist">
		<li role="presentation" class="active"><a href="#home"
			aria-controls="home" role="tab" data-toggle="tab">Refund</a></li>
		<li role="presentation"><a href="#profile"
			aria-controls="profile" role="tab" data-toggle="tab">Withdraw</a></li>
	</ul>

	<!-- Tab panes -->
	<div class="tab-content">
		<div role="tabpanel" class="tab-pane active" id="home">
			<br>
			<form:form action="${pageContext.request.contextPath}/account/refund"
				modelAttribute="refundForm" class="form-inline">
				<div class="form-group">
					<form:label class="sr-only" path="cardId" />
					<form:select path="cardId" id="card" class="form-control">
						<c:forEach items="${cards}" var="card">
							<form:option value="${card.id}">"${card.cardNumber}"-
					${card.amount}$</form:option>
						</c:forEach>
					</form:select>
					<form:hidden path="accountId" value="${accountId}" />
				</div>
				<form:label class="sr-only" path="amount" />
				<div class="input-group">
					<form:input path="amount" class="form-control"
						pattern="\d+(\.\d{1,2})?"
						oninvalid="this.setCustomValidity('Enter valid amount')"
						oninput="setCustomValidity('')" placeholder="Amount to refund" />
					<div class="input-group-addon">$</div>
				</div>
				<form:button class="btn btn-info">Refund</form:button>
				<br>
				<form:errors path="*" cssClass="text-danger" />
			</form:form>
		</div>
		<div role="tabpanel" class="tab-pane" id="profile">
			<br>
			<form:form
				action="${pageContext.request.contextPath}/account/withdraw"
				modelAttribute="refundForm" class="form-inline">
				<div class="form-group">
					<form:label class="sr-only" path="cardId" />
					<form:select path="cardId" id="card" class="form-control">
						<c:forEach items="${cards}" var="card">
							<form:option value="${card.id}">"${card.cardNumber}"-
					${card.amount}$</form:option>
						</c:forEach>
					</form:select>
					<form:hidden path="accountId" value="${accountId}" />
					<form:label class="sr-only" path="amount" />
					<div class="input-group">
						<form:input path="amount" class="form-control"
							pattern="\d+(\.\d{1,2})?"
							oninvalid="this.setCustomValidity('Enter valid amount')"
							oninput="setCustomValidity('')" placeholder="Amount to withdraw" />
						<div class="input-group-addon">$</div>
					</div>
				</div>
				<form:button class="btn btn-info">Withdraw</form:button>
				<br>
				<form:errors path="*" cssClass="text-danger" />
			</form:form>
		</div>
	</div>

</div>
