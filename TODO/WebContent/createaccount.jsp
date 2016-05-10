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
					<h1>Create new account</h1>
				</div>
				<div class="col-md-2"></div>
			</div>
		</div>
	</header>
	<div class="container">
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<form action="createaccount" method="post" class="form-horizontal" role="form">
					<input type="hidden" name="action" value="create" />
					<div class="form-group">
						<label for="login">New login</label> <input id="login" type="text" name="login" class="form-control" placeholder="Enter login" autofocus="autofocus" required="required" />
					</div>
					<div class="form-group">
						<label for="password">New password</label> <input id="password" type="password" name="password" class="form-control" placeholder="Enter password" required="required" />
					</div>
					<div class="form-group">
						<label for="confirm">Confirm password</label> <input id="confirm" type="password" name="confirm" class="form-control" placeholder="Enter password" required="required" />
					</div>
					<div class="form-group">
						<button type="submit" value="login" class="btn btn-primary btn-lg">Submit account details</button>
					</div>
				</form>
				<c:if test='${exists.equals("true") }'>
					<p>Username already exists, choose a different name</p>
				</c:if>
				<c:if test='${passmismatch.equals("true") }'>
					<p>Password does not match</p>
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
			<div class="col-md-12">
				<div class="goback">
					<a href="./login.jsp" class="btn btn-info">Go back to login page</a>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="bootstrap/js/jquery-1.12.1.min.js"></script>
	<script type="text/javascript" src="js/jquery.color-2.1.2.min.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/login.js"></script>
</body>
</html>