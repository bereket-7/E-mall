<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<%@page import="java.sql.*"%>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
	integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ"
	crossorigin="anonymous">
<title>Document</title>
</head>
<body class="bg-light">
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="#"> <img src="@{/images/logo.png}"
				src="../static/images/logo.png" width="auto" height="40"
				class="d-inline-block align-top" alt="" />
			</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav mr-auto"></ul>
				<ul class="navbar-nav">
					<li class="nav-item active"><a class="nav-link" href="/adminhome">Home
							Page</a></li>
					<li class="nav-item active"><a class="nav-link" href="/logout">Logout</a>
					</li>

				</ul>

			</div>
		</div>
	</nav><br>
	<div class="container">



    		<!-- Button trigger modal -->
    		<button type="button" style="margin: 20px 0" class="btn btn-primary"
    			data-toggle="modal" data-target="#exampleModalCenter">Add
    			Category</button>

    		<!-- Modal -->
    		<div class="modal fade" id="exampleModalCenter" tabindex="-1"
    			role="dialog" aria-labelledby="exampleModalCenterTitle"
    			aria-hidden="true">
    			<div class="modal-dialog modal-dialog-centered" role="document">
    				<div class="modal-content">
    					<form action="categories" method="post">
    						<div class="modal-header">
    							<h5 class="modal-title" id="exampleModalLongTitle">Add New
    								Category</h5>
    							<button type="button" class="close" data-dismiss="modal"
    								aria-label="Close">
    								<span aria-hidden="true">&times;</span>
    							</button>
    						</div>
    						<div class="modal-body  text-center">
    							<input type="text" name="categoryname" class="form-control"
    								id="name" required="required" placeholder="Category name">
    						</div>
    						<div class="modal-footer">
    							<button type="button" class="btn btn-secondary"
    								data-dismiss="modal">Close</button>
    							<input type="submit" value="Save Changes" class="btn btn-primary">
    						</div>
    					</form>
    				</div>
    			</div>
    		</div><br>

