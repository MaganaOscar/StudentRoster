<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Dorm</title>
		<link rel="stylesheet" type="text/css" href="css/style.css">
	</head>
	<body>
		<h1><c:out value="${dorm.name}"/> Dormitory</h1>
		<div>
			<form action="/dorm/${dorm.id}/add" method="post">
				<label for="student">Students: </label>
				<select name="student">
					<c:forEach var="student" items="${students}">
						<option value="${student.id}"><c:out value="${student.firstName} ${student.lastName}"/></option>
					</c:forEach>
				</select>
				<button type="submit">Add</button>
			</form>
		</div>
		<div>
			<table>
				<thead>
					<tr>
						<th>Name</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="student" items="${dorm.students}">
						<tr>
							<td><c:out value="${student.firstName} ${student.lastName}"></c:out>
							<td><a href="/dorms/${dorm.id}/remove?student=${student.id}">Remove</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<script type="text/javascript" src="js/app.js"></script>
	</body>
</html>