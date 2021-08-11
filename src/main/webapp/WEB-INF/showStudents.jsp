<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>All students</title>
		<link rel="stylesheet" type="text/css" href="css/style.css">
	</head>
	<body>
		<h1>All Students</h1>
		<div>
			<table>
				<thead>
					<tr>
						<th>Name</th>
						<th>Age</th>
						<th>Address</th>
						<th>City</th>
						<th>State</th>						
					</tr>
				</thead>
				<tbody>
					<c:forEach var="contact" items="${contacts}">
						<tr>
							<td><c:out value="${contact.student.firstName} ${contact.student.lastName}"/></td>
							<td><c:out value="${contact.student.age}"/></td>
							<td><c:out value="${contact.address}"/></td>
							<td><c:out value="${contact.city}"/></td>
							<td><c:out value="${contact.state}"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<script type="text/javascript" src="js/app.js"></script>
	</body>
</html>