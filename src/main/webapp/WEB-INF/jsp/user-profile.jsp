<%@ include file="../layout/taglib.jsp"%>
<div>
	<div class="col-sm-8">

		<!-- Nav tabs -->
		<ul class="nav nav-tabs" role="tablist">
			<li role="presentation" class="active"><a href="#home"
				aria-controls="home" role="tab" data-toggle="tab">Payments</a></li>
			<li role="presentation"><a href="#profile"
				aria-controls="profile" role="tab" data-toggle="tab">Credit
					cards</a></li>
		</ul>

		<!-- Tab panes -->
		<div class="tab-content ">
			<div role="tabpanel" class="tab-pane active " id="home">
				<table id="table1"
					class="table table-bordered table-hover table-striped">
					<caption>User's payments</caption>
					<thead>
						<tr>
							<th>Amount</th>
							<th>Date</th>
							<th>Receiver Account</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${payers}" var="payment">
							<tr>
								<td><c:out value="${payment.amount}" /></td>
								<td><c:out value="${payment.paymentDate}" /></td>
								<td align="center"><c:out
										value="${payment.receiverAccount.id}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div role="tabpanel" class="tab-pane" id="profile">
				<div role="tabpanel" class="tab-pane active " id="home">
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
				</div>
				<!-- Button trigger modal -->
				<security:authorize access="! hasRole('ROLE_ADMIN')">
					<button type="button" class="btn btn-primary  btn-default"
						data-toggle="modal" data-target="#myModal">Add new Credit
						Card</button>
				</security:authorize>
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
				<td><c:if test="${user.account.active eq true }">
						<security:authorize access="! hasRole('ROLE_ADMIN')">
							<a
								href="<spring:url value="/account/block" />"
								class="btn btn-danger btn-sm triggerRemove"> block account </a>
						</security:authorize>
					</c:if> <c:if test="${user.account.active eq false }">
						<p class="btn-danger">Account is blocked</p>
					</c:if></td>
			</tr>
			<tr>
				<td>Balance:</td>
				<td>${user.account.balance}</td>
				<td><c:if test="${user.account.active eq true }">
						<security:authorize access="! hasRole('ROLE_ADMIN')">
							<a href="<spring:url value="/account/refund" />"
								class="btn btn-info btn-sm triggerRemove"> add funds </a>
							<!-- <form  action="account/refund" method="get">
							<input type="submit" value="add funds" class="btn btn-info btn-sm triggerRemove"/>								
						</form> -->
						</security:authorize>
					</c:if> <c:if test="${user.account.active eq false }">
						<p class="btn-danger">Account is blocked</p>
					</c:if></td>
			</tr>
			<tr>
				<td colspan="3"><c:if test="${user.account.active eq true }">
						<security:authorize access="! hasRole('ROLE_ADMIN')">
							<a href="<spring:url value="/payments/makepayment" />"
								class="btn btn-info btn-primary triggerRemove"> make a
								payment </a>
						</security:authorize>
					</c:if></td>
			</tr>
		</table>
	</div>


	<form:form action="creditcard/addnew.html" cssClass="form-horizontal"
		modelAttribute="newCard">
	<form:errors path="*"/>
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
								<form:input path="cardNumber" cssClass="form-control" />
							</div>
						</div>

						<div class="form-group">
							<label for="cvv2" class="col-sm-2 control-label">cvv2:</label>
							<div class="col-sm-10">
								<form:input path="cvv2" cssClass="form-control" />
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
<!-- <script>alert(document.getElementById('table1').rows[0].cells.length)</script> -->
