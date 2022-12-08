<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">JobPortal</a>
<% 
			String sid = (String)request.getParameter("sid");
			String jobid = (String)request.getParameter("job_id");
			String compid = (String)request.getParameter("comp_id");
			String url1 = "StudentApplyServlet?sid="+sid+"&job_id=" + jobid + "&comp_id="+compid;
			String url2 = "StudentViewApplicationServlet?sid="+sid;
			String url3 = "StudentLogoutServlet?sid=" + sid;
			%>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="home.jsp"><i class="fas fa-home"></i>Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href=<%= url1 %>><i class="fas fa-plus-circle"></i>Apply/View Jobs</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href=<%= url2 %>><i class="fas fa-layer-group"></i> View Applications</a>
        </li>
		</ul>
      <form class="form-inline my-2 my-lg-0">
     <!-- <a href="StudentViewProfileServlet" class="btn btn-light"><i class="fas fa-user-circle"></i>View Profile</a> --> 
        <a href=<%= url3 %> class="btn btn-light mr-10"><i class="fas fa-sign-out-alt"></i>Logout</a>
        <a href="" class="btn btn-light"><i class="fas fa-user-circle"></i> Student</a>
      </form>
    </div>
  </div>
</nav>