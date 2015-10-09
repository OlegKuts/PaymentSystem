<%@ include file="../layout/taglib.jsp"%>
<c:if test="${errorMessages != null}">
	<div class="alert alert-danger">
		<c:forEach items="${errorMessages}" var="message">
			<c:out value="${message}" />
			<br>
		</c:forEach>
	</div>
</c:if>
<div>
	<div class="col-sm-8">
		<security:authentication property="principal" var="principal" />
		<!-- Nav tabs -->
		<ul class="nav nav-tabs" role="tablist">
			<li role="presentation" class="active"><a href="#home"
				aria-controls="home" role="tab" data-toggle="tab">Payments</a></li>
			<li role="presentation"><a href="#profile"
				aria-controls="profile" role="tab" data-toggle="tab">Receives</a></li>
			<li role="presentation"><a href="#messages"
				aria-controls="messages" role="tab" data-toggle="tab">Credit
					cards</a></li>

		</ul>

		<!-- Tab panes -->
		<div class="tab-content">
			<div role="tabpanel" class="tab-pane active" id="home">
				<table id="table1"
					class="table table-bordered table-hover table-striped">
					<caption>User's payments</caption>
					<thead>
						<tr>
							<th>Amount</th>
							<th>Date</th>
							<th>Receiver User</th>
							<th>Receiver Account</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${payments}" var="payment">
							<c:set var="firstName"
								value="${payment.receiverAccount.user.userInformation.firstName}" />
							<c:set var="lastName"
								value="${payment.receiverAccount.user.userInformation.lastName}" />
							<fmt:formatDate value="${payment.paymentDate}"
								pattern="dd-MM-yyyy HH:mm:ss" var="paymentDate" />
							<tr>
								<td><c:out value="${payment.amount}" /></td>
								<td><c:out value="${paymentDate}"></c:out></td>
								<td><c:out value="${firstName}" /> <c:out
										value="${lastName}" /></td>
								<td align="center"><c:out
										value="${payment.receiverAccount.id}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div role="tabpanel" class="tab-pane" id="profile">
				<table id="table1"
					class="table table-bordered table-hover table-striped">
					<caption>User's payments</caption>
					<thead>
						<tr>
							<th>Amount</th>
							<th>Date</th>
							<th>Payer User</th>
							<th>Payer Account</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${receives}" var="receive">
							<c:set var="firstName"
								value="${receive.payerAccount.user.userInformation.firstName}" />
							<c:set var="lastName"
								value="${receive.payerAccount.user.userInformation.lastName}" />
							<fmt:formatDate value="${receive.paymentDate}"
								pattern="dd-MM-yyyy HH:mm:ss" var="receiveDate" />
							<tr>
								<td><c:out value="${receive.amount}" /></td>
								<td><c:out value="${receiveDate}"></c:out></td>
								<td><c:out value="${firstName}" /> <c:out
										value="${lastName}" /></td>
								<td align="center"><c:out
										value="${receive.payerAccount.id}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div role="tabpanel" class="tab-pane" id="messages">
				<table class="table table-bordered table-hover table-striped">
					<caption>User's credit cards</caption>
					<thead>
						<tr>
							<th>Balance</th>
							<th>Number</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${user.account.creditCards}" var="card">
							<tr>
								<td><c:out value="${card.amount}" /></td>
								<td align="center"><c:out value="${card.cardNumber}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- Button trigger modal -->
				<c:if
					test="${user.account.active eq true and principal.username eq user.username}">
					<button type="button" class="btn btn-primary  btn-default"
						data-toggle="modal" data-target="#myModal">Add new Credit
						Card</button>
				</c:if>
			</div>
		</div>
	</div>

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
				<td><c:if
						test="${user.account.active eq true and principal.username eq user.username}">
						<security:authorize access="!hasRole('ROLE_ADMIN')">
							<a href="<spring:url value="/account/block" />"
								class="btn btn-danger btn-sm triggerRemove"> block account </a>
						</security:authorize>
					</c:if> <c:if test="${user.account.active eq false }">
						<p class="btn-danger">Account is blocked</p>
					</c:if></td>
			</tr>
			<tr>
				<td>Balance:</td>
				<td>${user.account.balance}</td>

				<td class="text-warning"><c:if
						test="${user.account.active eq true and principal.username eq user.username}">
						<c:choose>
							<c:when test="${hasAnyCard}">
								<a href="<spring:url value="/account/refund" />"
									class="btn btn-info btn-sm triggerRemove"> add funds </a>
							</c:when>
							<c:otherwise>
								<c:out 
									value="You need to have atleast one credit card to refund your account" />
							</c:otherwise>
						</c:choose>
					</c:if>
			</tr>
			<tr>
				<td colspan="3"><c:if
						test="${user.account.active eq true and principal.username eq user.username }">
						<a href="<spring:url value="/payments/makepayment" />"
							class="btn btn-info btn-primary triggerRemove"> make a
							payment </a>
					</c:if></td>
			</tr>
		</table>
	</div>


	<form:form cssClass="form-horizontal" modelAttribute="newCard"
		action="${pageContext.request.contextPath}/creditcard/addnewcard">
		<!-- Modal -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Modal title</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label for="cardNumber" class="col-sm-2 control-label">Card
								Number:</label>
							<div class="col-sm-10">
								<form:input path="cardNumber" cssClass="form-control"
									pattern="\\d{12}"
									oninvalid="this.setCustomValidity('Card number must consist of 12 digits')"
									oninput="setCustomValidity('')" />
							</div>
						</div>

						<div class="form-group">
							<label for="cvv2" class="col-sm-2 control-label">cvv2:</label>
							<div class="col-sm-10">
								<form:input path="cvv2" cssClass="form-control" pattern="\\d{3}"
									oninvalid="this.setCustomValidity('cvv2 must consist of 3 digits')"
									oninput="setCustomValidity('')" />
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<input type="submit" class="btn btn-primary"
							value="Add Credit Card">
					</div>
				</div>
			</div>
		</div>
	</form:form>
</div>
