<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student</title>
<%@ include file="components/bootstrap.jsp" %>
<style><%@ include file ="components/style.css" %></style>
</head>
<body style="background-color: #f0f1f2;">
<% 
			if(session.getAttribute("sid") == null){
			response.sendRedirect("student_login.jsp");
			} 
			%>
	<%@ include file="components/student_navbar.jsp" %>
	<div class="container-fluid">
		<div class="row p-5">
			<div class="col-md-4 offset-md-4">
				<div class="card">
					<div class="card-body">
						<div class="text-center">
							<i class="fa fa-user-plus fa-2x" aria-hidden="true"></i>
							<h5>Apply</h5>
						</div>
						<form action=<%= url1 %> method="post">
							<div class="form-group my-3">
								<label>CPI</label><input type="number" step = "0.01"
					 			required="required" class="form-control" name="scpi">
							</div>
							<div class="form-group my-3">
								<label>Why should we hire you? (max. 80 words)</label><input type="text"
								required="required" class="form-control"
								id="field" name="swhyhire">
							</div>
							<div class="text-center my-3">
								<button type="submit" class="btn btn-primary btn-block center">Apply</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>