<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
<%@ include file="components/bootstrap.jsp" %>
<style><%@ include file ="components/style.css" %></style>
<link
      rel="stylesheet"
      href="https://use.fontawesome.com/releases/v5.13.0/css/all.css"
      integrity="sha384-Bfad6CLCknfcloXFOyFnlgtENryhrpZCe29RTifKEixXQZ38WheV+i/6YWSzkz3V"
      crossorigin="anonymous"
    />
</head>
<body style="color: white">
<%@ include file="components/navbar.jsp" %>
	<div class="container-fluid back-img">
		<div class="text-center">
			<h1 class="text-black p-4">
				<i class="fa fa-book" aria-hidden="true"></i>
			</h1>
		<section id="about">
			<h2>
				<span> AboutUs</span>
			</h2>
			<p><b>WHO ARE WE?</b><br>JobPortal is a global online employment solution for people seeking jobs and the employers who need great people.
			 We've been doing this for more than 20 years, and have expanded from our roots as a "job board" to a global provider of a full array of job seeking, 
			 career management, recruitment and talent management products and services.
			At the heart of our success and our future is innovation: We are changing the way people think about work,
			 and we're helping them actively improve their lives and their workforce performance with new technology, tools and practices</p>
			<div class="blocks">
				<div class="block">
					<p>JobPortal Announces 2018 Best Companies for Veterans List</p>
				</div>
				<div class="block">
					<p>JobPortal Strengthens Staffing Leadership Team Ahead of Staffing World 2018</p>
				</div>
			</div>
			<p><b>WHY JOBPORTAL?</b><br> We've designed an integrated approach that focuses our more innovative technology and expertise into powerful, easy to use solutions. Why? To help you find not only the best quality candidates, but more of them. To streamline the process so you can save time and money. And to help you make smarter decisions to improve your return on investment.
			Basically, we want to give you the ability to hire like no one else can.</p>
		</section>
		</div>
	</div>
	<%--  <%@ include file="components/footer.jsp" %> --%>
	
	
</body>
</html>