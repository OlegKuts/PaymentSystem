<%@ include file="../layout/taglib.jsp"%>

<form:form modelAttribute="userform" cssClass="form-horizontal">
	<div class="form-group">
		<label for="user.username" class="col-sm-2 control-label">Username:</label>
		<div class="col-sm-10">
			<form:input path="user.username" cssClass="form-control" />
			<form:errors path="user.username" cssClass="text-danger"/>
		</div>
	</div>
	<div class="form-group">
		<label for="user.password" class="col-sm-2 control-label">Password:</label>
		<div class="col-sm-10">
			<form:password path="user.password" cssClass="form-control" />
			<form:errors path="user.password" cssClass="text-danger"/>
		</div>
	</div>
	<div class="form-group">
		<label for="userInformation.firstName" class="col-sm-2 control-label">First
			Name:</label>
		<div class="col-sm-10">
			<form:input path="userInformation.firstName" cssClass="form-control" />
			<form:errors path="userInformation.firstName" cssClass="text-danger"/>
		</div>
	</div>
	<div class="form-group">
		<label for="userInformation.lastName" class="col-sm-2 control-label">Last
			Name:</label>
		<div class="col-sm-10">
			<form:input path="userInformation.lastName" cssClass="form-control" />
			<form:errors path="userInformation.lastName" cssClass="text-danger"/>
		</div>
	</div>
	<div class="form-group">
		<label for="userInformation.email" class="col-sm-2 control-label">Email:</label>
		<div class="col-sm-10">
			<form:input path="userInformation.email" cssClass="form-control" type="email"/>
			<form:errors path="userInformation.email" cssClass="text-danger"/>
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-10">
			<input type="submit" value="Save" class="btn btn-lg btn-primary" />
		</div>
	</div>
</form:form>