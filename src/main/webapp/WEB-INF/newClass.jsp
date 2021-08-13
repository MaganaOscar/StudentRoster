<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" type="text/css" href="css/style.css">
	</head>
	<body>
		<h1>Classes</h1>
		<form:form action="/class/create" method="post" modelAttribute="class">
			<form:label path="name">Name: </form:label>
			<form:input path="name"/>
			<form:button>Create</form:button>
		</form:form>
		<script type="text/javascript" src="js/app.js"></script>
	</body>
</html>