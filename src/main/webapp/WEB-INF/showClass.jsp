<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Show Class</title>
		<link rel="stylesheet" type="text/css" href="css/style.css">
	</head>
	<body>
		<h1><c:out value="${classes.name}"/></h1>
		<p>Students taking this class:</p>
		<table>
			<thead>
				<tr>
					<th>Name</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="student" items="${classes.students}">
					<tr>
						<td><c:out value="${student.firstName} ${student.lastName}"/>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<script type="text/javascript" src="js/app.js"></script>
	</body>
</html>