<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign Up</title>
<%@ include file="components/bootstrap.jsp" %>
<style><%@ include file ="components/style.css" %></style>
<style type="text/css">
</style>
</head>
<% 
			String id = (String)request.getParameter("cid");
		    String url1 = "add_job.jsp?cid="+id; 
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
          <a class="nav-link active" aria-current="page" href="index.jsp"><i class="fas fa-home"></i>Home</a>
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
	<div class="container-fluid back-img">
		<div class="text-center">
			<h1 style="color:white"class="text-black p-4">
				<i class="fas fa-user"></i> Welcome !!
			</h1>
		</div>
	</div>
</html>