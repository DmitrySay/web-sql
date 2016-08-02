<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Список групп:</title>
</head>
<body>

	<h2>Список групп:</h2>
	<table>

		<tr>
			<td>Id</td>
			<td>number</td>
			<td>department</td>
			
		</tr>


		<c:forEach items="${messageGroup}" var="group">
			<tr>
				<td>${group.id}</td>
				<td>${group.number}</td>
				<td>${group.department}</td>
				

			</tr>
		</c:forEach>

	</table>

<p>	<a href="/lesson11/">На Главную страницу</a> </p>

</body>
</html>