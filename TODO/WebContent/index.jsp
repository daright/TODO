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
<link href='https://fonts.googleapis.com/css?family=Lato:700' rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Pontano+Sans' rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/styles.css">
<title>TODO list</title>
</head>
<body>
	<c:if test="${empty login }">
		<c:redirect url="./login"></c:redirect>
	</c:if>
	<div class="logout">
		<a href="./logout" class="btn btn-default glyphicon glyphicon-log-out"><p>Logout</p></a>
	</div>
	<header>
		<div class="container">
			<div class="row">
				<div class="col-md-2"></div>
				<div class="col-md-8">
					<h1>Your Personal TODO List</h1>
				</div>
				<div class="col-md-2"></div>
			</div>
		</div>
	</header>
	<div class="container">
		<div class="row">
			<div class="col-md-1"></div>
			<div class="col-md-10">
				<p class="lead hello">Hello ${login}!</p>
				<div class="form">
					<form action="list" method="post" class="form" role="form">
						<div class="input-group">
							<input type="hidden" name="action" value="add" /> <input type="text" name="item" class="form-control" placeholder="Add something you wish to do..." autofocus /> <span class="input-group-btn">
								<button type="submit" value="Add" class="btn btn-success">Add</button>
							</span>
						</div>
					</form>
				</div>
			</div>
			<div class="col-md-1"></div>
		</div>
		<div class="row">
			<div class="col-md-1"></div>
			<div class="col-md-10">
				<table class="table">
					<tr>
						<th><p class="lead">
								<c:if test="${items.size() > 0}">Here are your added tasks:</c:if>
							</p></th>
					</tr>
					<c:set var="counter" value="0"></c:set>
					<c:forEach var="item" items="${items}">
						<c:set var="counter" value="${counter+1 }"></c:set>
						<tr>
							<td class="td">
								<div class="form-div">
									<form action="list" method="post" class="form" role="form">
										<c:if test="${item.checked}">
										<input type="hidden" name="action" value="update" />
											<div class="input-group">
												<span class="input-group-addon" id="basic-addon1">${counter}</span>  <input id="disabled" type="text" name="item${item.iditem}"
													class="form-control" value="${item.item}" disabled="disabled" />
												<div class="input-group-btn buttons">
													<button type="submit" name="update" value="delete${item.iditem}" class="btn btn-danger glyphicon glyphicon-trash"></button>
													<button type="submit" name="update" value="uncheck${item.iditem}" class="btn btn-primary glyphicon glyphicon-ok uncheck"></button>
												</div>
											</div>
										</c:if>
										<c:if test="${!item.checked}">
										<input type="hidden" name="action" value="update" />
											<div class="input-group">
												<span class="input-group-addon" id="basic-addon1">${counter}</span>  
												<input id="enabled" type="text" name="item${item.iditem}" class="form-control"
													value="${item.item}" />
												<div class="input-group-btn buttons">
													<button type="submit" name="update" value="update${item.iditem}" class="btn btn-primary glyphicon glyphicon-pencil"></button>
													<button type="submit" name="update" value="sublist${item.iditem}" class="btn btn-info glyphicon glyphicon-plus"></button>
													<button type="submit" name="update" value="check${item.iditem}" class="btn btn-success glyphicon glyphicon-ok check"></button>
												</div>
											</div>
										</c:if>
									</form>
								</div> <c:if test="${item.numOfSubitems > 0}">
									<p class="completed">${item.numOfCheckedSubitems}/${item.numOfSubitems} Completed subtasks</p>
								</c:if>
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
	<script type="text/javascript" src="js/script.js"></script>
</body>
</html>