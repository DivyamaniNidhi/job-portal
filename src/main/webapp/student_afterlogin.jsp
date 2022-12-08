<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
<%@ include file="components/bootstrap.jsp" %>
<style><%@ include file ="components/style.css" %></style>
<style type="text/css">
</style>
</head>
<% 
			String id = (String)request.getParameter("sid");
			String url1 = "StudentViewJobsServlet?sid="+id;
			String url2 = "StudentViewApplicationServlet?sid="+id;
			String url3 = "StudentLogoutServlet?sid="+id;
			if(session.getAttribute("sid") == null){
			response.sendRedirect("student_login.jsp");
			}
			%>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">JobPortal</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="home.jsp"><i class="fas fa-home"></i>Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href=<%= url1 %>><i class="fas fa-plus-circle"></i>Apply/ViewJobs</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href=<%= url2 %>><i class="fas fa-layer-group"></i> View Applications</a>
        </li>
		</ul>
      <form class="form-inline my-2 my-lg-0">
        <a href=<%= url3 %>  class="btn btn-light mr-10"><i class="fas fa-sign-out-alt"></i> Logout</a>
        <a class="btn btn-light"><i class="fas fa-user-circle"></i> Student</a>
      </form>
    </div>
  </div>
</nav>
	<div class="container-fluid back-img">
		<div class="text-center">
			<h1 style="color:white" class="text-black p-4">
				<i class="fas fa-user"></i> Welcome Student
			</h1>
		</div>
	</div>
</html>