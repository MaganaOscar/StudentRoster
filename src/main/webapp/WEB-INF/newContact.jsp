<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>New Contact</title>
		<link rel="stylesheet" type="text/css" href="css/style.css">
	</head>
	<body>
		<h1>Contact Info</h1>
		<div>
			<form action="/contact/create" method="post">
				<p>
					<label for="student">Student: </label>
					<select name="student">
						<c:forEach var="student" items="${students}">
							<option value="${student.id}"><c:out value="${student.firstName} ${student.lastName}"/></option>>
						</c:forEach>
					</select>
				</p>
				<p>
					<label for="address">Address: </label>
					<input type="text" name="address">
				</p>
				<p>
					<label for="city">City: </label>
					<input type="text" name="city">
				</p>
				<p>
					<label for="state">State: </label>
					<input type="text" name="state">
				</p>
				<input type="submit" value="Create">
			</form>
		</div>
		<script type="text/javascript" src="js/app.js"></script>
	</body>
</html>