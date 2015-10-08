<%@ include file="../layout/taglib.jsp"%>


<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th>Username</th>
			<th>AccountID</th>
			<th>User</th>
			<th>Email</th>
			<th>Operations</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${users}" var="user">
			<tr>
				<td><a
					href="<spring:url value="/admin/users/${user.id}.html" />"> <c:out
							value="${user.username}" />
				</a></td>
				<td><c:out value="${user.account.id}" /></td>
				<td><c:out value="${user.userInformation.firstName} ${user.userInformation.lastName}" /></td>
				<td><c:out value="${user.userInformation.email}" /></td>
				<td><c:if test="${user.account.active eq true }">
						<a
							href="<spring:url value="/admin/users/block/${user.account.id}.html" />"
							class="btn btn-danger triggerRemove"> Block Account </a>
					</c:if> <c:if test="${user.account.active eq false }">
						<a
							href="<spring:url value="/admin/users/active/${user.account.id}.html" />"
							class="btn btn-success triggerRemove"> Activate Account</a>
					</c:if></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

