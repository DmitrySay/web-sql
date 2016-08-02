<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Список студентов:</title>

</head>
<body>

	<h2>Список студентов:</h2>
	<table>

		<tr>
			<td>Id</td>
			<td>name</td>
			<td>surname</td>
			<td>groupId</td>
		</tr>


		<c:forEach items="${listStudent}" var="student">
			<tr>
				<td>${student.id}</td>
				<td>${student.name}</td>
				<td>${student.surname}</td>
				<td>${student.groupId}</td>
              		
					
		 <td><a href="StudentServlet?action=delete&id=<c:out value="${student.id}"/>">Delete</a></td> 
		 <td><a href="StudentServlet?action=updateForm&id=<c:out value="${student.id}"/>">Update</a></td>
			
			</tr>
		</c:forEach>

	</table>
<p><a href="StudentServlet?action=insert">AddStudent</a></p>


	<p><a href="/lesson11/">На Главную страницу</a></p>

</body>
</html>