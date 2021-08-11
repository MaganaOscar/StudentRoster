<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>New Student</title>
		<link rel="stylesheet" type="text/css" href="css/style.css">
	</head>
	<body>
		<h1>New Student</h1>
		<div>
			<form:form action="/student/create" method="post" modelAttribute="student">
				<p>
					<form:label path="firstName">First Name: </form:label>
					<form:input path="firstName"/>
				</p>
				<p>
					<form:label path="lastName">Last Name: </form:label>
					<form:input path="lastName"/>
				</p>
				<p>
					<form:label path="age">Age: </form:label>
					<form:input path="age"/>
				</p>
				<form:button>Create</form:button>
			</form:form>
		</div>
		<script type="text/javascript" src="js/app.js"></script>
	</body>
</html>