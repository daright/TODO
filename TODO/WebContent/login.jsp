<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="description" content="TODO List">
<meta name="keywords" content="list,todo">
<meta name="author" content="Piotr Kasperek">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" type="text/css" href="css/login.css">
<title>TODO list</title>
</head>
<body>
	<header>
		<div class="container">
			<div class="row">
				<div class="col-md-2"></div>
				<div class="col-md-8">
					<h1>Login to your TODO list</h1>
				</div>
				<div class="col-md-2"></div>
			</div>
		</div>
	</header>
	<div class="container">
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<form action="login" method="post" class="form-horizontal" role="form">
					<input type="hidden" name="action" value="login" />
					<div class="form-group">
						<label for="login">Login:</label> <input id="login" type="text" name="login" class="form-control" placeholder="Enter login" autofocus="autofocus" required="required" />
					</div>
					<div class="form-group">
						<label for="password">Password:</label> <input id="password" type="password" name="password" class="form-control" placeholder="Enter password" required="required" />
					</div>
					<div class="form-group">
						<label for="remember">Remember me: </label> <input type="checkbox" id="remember" name="remember" value="true">
					</div>
					<div class="form-group">
						<button type="submit" value="login" class="btn btn-primary btn-lg">Login</button>
					</div>
				</form>
				<c:if test='${invalid.equals("true") }'>
					<p>Invalid username or password</p>
				</c:if>
			</div>
			<div class="col-md-3"></div>
		</div>
		<div class="row">
			<div class="col-md-12 circles">
				<div class="circle"></div>
				<div class="circle middle"></div>
				<div class="circle"></div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<h3>If you don't have an account yet, you can create one here...</h3>
				<div class="createacc">
					<a href="./createaccount.jsp" class="btn btn-info btn-lg">Create Account</a>
				</div>
			</div>
			<div class="col-md-3"></div>
		</div>
	</div>
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="bootstrap/js/jquery-1.12.1.min.js"></script>
</body>
</html>