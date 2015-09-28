<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<p>User: ${user.userInformation.firstName}
		${user.userInformation.lastName}</p>

	<p>Account ID: ${account.id}</p>
	<p>Balance: ${account.balance}</p>
	<div>
	<p>User's payments</p>
		<table border="1px">
			<thead>
				<th>Amount</th>
				<th>Date</th>
				<th>Receiver Account</th>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="payment">
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
</body>
</html>