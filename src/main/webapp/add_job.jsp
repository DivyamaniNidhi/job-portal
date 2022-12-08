<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Job Portal</title>
<%@ include file="components/bootstrap.jsp" %>
<style><%@ include file ="components/style.css" %></style>
</head>
<body style="background-color: #f0f1f2;">
<% 
			if(session.getAttribute("cid") == null){
			response.sendRedirect("companylogin.jsp");
			}
			String id = (String)request.getParameter("cid");
			String url1 = "AddJob?cid="+id;
			String url2 = "CompanyViewJobs?cid="+id;
			String url3 = "CompanyViewApplications?cid="+id;
			String url4 = "CompanyLogout?cid="+id;
			%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Job Portal</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="home.jsp"><i class="fas fa-home"></i>Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href=<%= url1 %>><i class="fas fa-plus-circle"></i>Add Job</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href=<%= url2 %>><i class="fas fa-layer-group"></i> View Jobs</a>
        </li>
        <li>
        <a class="nav-link"  href=<%= url3 %> class="btn btn-light"><i class="fas fa-user-circle"></i> View Applications</a>
        </li>
		</ul>
      <form class="form-inline my-2 my-lg-0">
         <a href=<%= url4 %> class="btn btn-light mr-10"><i class="fas fa-sign-out-alt"></i> Logout</a>
      </form>
    </div>
  </div>
</nav>
	<div class="container p-2">
		<div class="col-md-10 offset-md-1">
				<div class="card">
					<div class="card-body">
						<div class="text-center text-success">
							<i class="fas fa-user-friends fa-3x"></i>
							<h5>Add Job</h5>
						</div>
						<form action=<%= url1 %> method="post">
							<div class="form-group p-2">
								<label>Job Id</label><input type="text" name="jobId"
								required class="form-control">
							</div>
								<div class="form-group col-md-4 p-2">
									<label>Status</label><select name="status" class="form-control">
									<option selected value="Active" class="active">Active</option>
									<option value="Inactive" class="inactive">Inactive</option>
									</select>
								</div>
							
							<div class="form-group p-2">
									<label>Role</label>
									<input type="text" name="role" required class="form-control">
								</div>
							<div class="text-center my-3">
								<button type="submit" class="btn btn-success btn-block center">Add Job</button>
							</div>
						</form>
					</div>
				</div>
</div>
</div>
</body>
</html>