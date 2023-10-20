<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="java.text.*"%>
<%@page import ="java.io.FileOutputStream" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page import=" java.io.ObjectOutputStream" %>
<!doctype html>
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

<title>Document</title>
</head>

<body class="bg-light">
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="#"> <img
				th:src="@{/images/logo.png}" src="../static/images/logo.png"
				width="auto" height="40" class="d-inline-block align-top" alt="" />
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
	<div class="container-fluid">

		<a style="margin: 20px 0" class="btn btn-primary"
			href="/admin/products/add">Add Product</a><br>
		<table class="table">

			<tr>
				<th scope="col">Serial No.</th>
				<th scope="col">Product Name</th>
				<th scope="col">Category</th>
				<th scope="col">Preview</th>
				<th scope="col">Quantity</th>
				<th scope="col">Price</th>
				<th scope="col">Weight</th>
				<th scope="col">Descrption</th>
				<th scope="col">Delete</th>
				<th scope="col">Update</th>
			</tr>
			<tbody>

				<c:forEach var="product" items="${products}">
				<tr>



					<td>
						${product.id}
					</td>
					<td>
						${product.name }
					</td>
					<td>
						${product.category.name}

					</td>

					<td><img src="${product.image}"
						height="100px" width="100px"></td>
					<td>
						${product.quantity }
					</td>
					<td>S
						${product.price }
					</td>
					<td>
						${product.weight }
					</td>
					<td>
						${product.description }
					</td>

					<td>
					<form action="products/delete" method="get">
							<input type="hidden" name="id" value="${product.id}">
							<input type="submit" value="Delete" class="btn btn-danger">
					</form>
					</td>
					<td>
					<form action="products/update/${product.id}" method="get">
                        <input type="submit" value="Update" class="btn btn-warning">
                    </form>

					</td>

				</tr>
				</c:forEach>

			</tbody>
		</table>

	</div>