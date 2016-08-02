<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>



<h2>Cтудент:</h2>
	<table>

		<tr>
			<td>Id</td>
			<td>name</td>
			<td>surname</td>
			<td>groupId</td>
		</tr>

<tr>
				<td>${student.id}</td>
				<td>${student.name}</td>
				<td>${student.surname}</td>
				<td>${student.groupId}</td>

</tr>
</table>



<form method="POST" action="StudentServlet" name="formStudentUpdate">
		<table>

			<tr>
				<td>Обновление студента:</td>
			</tr>
		
			<tr>
				<td>id:</td>
				<td> <input type="text" name="id"  readonly="readonly" 	value="<c:out value="${student.id}"/>" ></td>
			</tr>

			<tr>
				<td>name:</td>
				<td><input type="text" name="name" value="${student.name}"></td>
			</tr>

			<tr>
				<td>surname:</td>
				<td><input type="text" name="surname" value="<c:out value="${student.surname}"/>"></td>
			</tr>

			<tr>
				<td>groupId:</td>
				<td><input type="text" name="groupId" value="<c:out value="${student.groupId}"/>"></td>
			</tr>

			<tr>

				<td><input type="submit" value=" submit "></td>
			</tr>

		</table>
	</form>

<p><a href="/lesson11/">На Главную страницу</a></p>
<p><a href="StudentServlet?action=listUser">Посмотрите всех студентов!</a></p>
</body>
</html>