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
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/styles.css">
<title>TODO list</title>
</head>
<body>
	<header>
		<div class="container">
			<div class="row">
				<div class="col-md-2"></div>
				<div class="col-md-8">
					<h1>Your Personal TODO List</h1>
				</div>
				<div class="col-md-2">
					<a href="./logout" class="btn btn-default glyphicon glyphicon-log-out"></a>
				</div>
			</div>
		</div>
	</header>
	<div class="container">
		<div class="row">
			<div class="col-md-1"></div>
			<div class="col-md-10">
				<form action="list" method="post" class="form" role="form">
					<div class="input-group">
						<input type="hidden" name="action" value="add" /> <input type="text" name="item" class="form-control" placeholder="Add something you wish to do..." autofocus /> <span class="input-group-btn">
							<button type="submit" value="Add" class="btn btn-success">Add</button>
						</span>
					</div>
				</form>
			</div>
			<div class="col-md-1"></div>
		</div>
		<div class="row">
			<div class="col-md-1"></div>
			<div class="col-md-10">
				<table class="table table">
					<tr>
						<th class="lead">Hello ${login}! <c:if test="${items.size() > 0}">Some things to do...</c:if>
							<c:if test="${items.size() == 0 || items == null}">Add some things you want to do...</c:if></th>
					</tr>
					<c:forEach var="item" items="${items}">
						<tr>
							<td>
								<form action="list" method="post" class="form" role="form">
									<c:if test="${item.checked}">
										<div class="input-group">
											<input type="hidden" name="action" value="update" /> <input type="text" name="item${item.iditem}" class="form-control" value="${item.item}" disabled="disabled" /> <span
												class="input-group-btn">
												<button type="submit" name="update" value="delete${item.iditem}" class="btn btn-danger glyphicon glyphicon-trash"></button>
											</span> <span class="input-group-btn">
												<button type="submit" name="update" value="uncheck${item.iditem}" class="btn btn-primary glyphicon glyphicon-ok"></button>
											</span>
										</div>
									</c:if>
									<c:if test="${!item.checked}">
										<div class="input-group">
											<input type="hidden" name="action" value="update" /> <input type="text" name="item${item.iditem}" class="form-control" value="${item.item}" /> <span class="input-group-btn">
												<button type="submit" name="update" value="update${item.iditem}" class="btn btn-primary glyphicon glyphicon-pencil"></button>
											</span> <span class="input-group-btn">
												<button type="submit" name="update" value="check${item.iditem}" class="btn btn-success glyphicon glyphicon-ok"></button>
											</span>
										</div>
									</c:if>
								</form>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="col-md-1"></div>
		</div>
	</div>
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="bootstrap/js/jquery-1.12.1.min.js"></script>
</body>
</html>