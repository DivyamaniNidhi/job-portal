<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<%@ include file="components/bootstrap.jsp" %>
<style><%@ include file ="components/style.css" %></style>
<style type="text/css">
</style>
</head>
<body style="background-color: #f0f1f2;">
	<%@ include file="components/navbar.jsp" %>
	<div class="container-fluid">
		<div class="row p-5">
			<div class="col-md-4 offset-md-4">
				<div class="card">
					<div class="card-body">
						<div class="text-center">
							<i class="fa fa-user-plus fa-2x" aria-hidden="true"></i>
							<h5>Login Page</h5>
						</div>
						<form action="StudentLoginServlet" method="post">
							<div class="form-group my-3">
								<label>Email </label><input type="email"
								required="required" class="form-control"
								id="username" aria-describedby="emailHelp" name="semail">
							</div>
							<div class="form-group my-3">
								<label>Password</label><input type="password"
								required="required" class="form-control"
								id="password" name="spassword">
							</div>
							<div class="text-center my-3">
								<button type="submit" class="btn btn-primary btn-block center">Login</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>