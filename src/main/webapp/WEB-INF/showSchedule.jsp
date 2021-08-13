<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Show Schedule</title>
		<link rel="stylesheet" type="text/css" href="css/style.css">
	</head>
	<body>
		<h1><c:out value="${student.firstName} ${student.lastName}"/></h1>
		<form action="/students/add" method="post">
			<input type="hidden" value="${student.id}" name="student">
			<label for="class">Classes: </label>
			<select name="class">
				<c:forEach var="thisClass" items="${classes}">
					<option value="${thisClass.id}"><c:out value="${thisClass.name}"/></option>
				</c:forEach>
			</select>
			<button>Add</button>
		</form>
		<p>Your Schedule: </p>
		<table>
			<thead>
				<tr>
					<th>Class Name</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="stuClass" items="${student.classes}">
					<tr>
						<td><c:out value="${stuClass.name}"/></td>
						<td>
							<form action="/students/${student.id}/drop" method="post">
								<input type="hidden" value="${stuClass.id}">
								<input type="submit" value="Drop">
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<script type="text/javascript" src="js/app.js"></script>
	</body>
</html>