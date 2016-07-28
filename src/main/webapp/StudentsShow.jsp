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

<table>
             <tr>
				<td>Id</td>
				<td>name</td>
				<td>surname</td>
				<td>groupId</td>
			</tr>




	<c:forEach items="${message}" var="li">
		<tr>
			<td>${li.id}</td>
			<td>${li.name}</td>
			<td>${li.surname}</td>
			<td>${li.groupId}</td>

		</tr>
	</c:forEach>

</table>
	</br>
	</br>
	</br>
	<form method="POST" action="StudentServlet" name="formStudentCreate">
		<input type="hidden" name="action" value="insert" />

		<table>

			<tr>
				<td>Добавление студента</td>
			</tr>

			<tr>
				<td>id:</td>
				<td><input type="text" name="id"></td>
			</tr>

			<tr>
				<td>name:</td>
				<td><input type="text" name="name"></td>
			</tr>

			<tr>
				<td>surname:</td>
				<td><input type="text" name="surname"></td>
			</tr>

			<tr>
				<td>groupId:</td>
				<td><input type="text" name="groupId"></td>
			</tr>

			<tr>

				<td><input type="submit" value=" submit "></td>
			</tr>

		</table>
	</form>
	</br>
	</br>
	</br>
	<form method="POST" action="StudentServlet" name="formStudentUpdate">
	<input type="hidden" name="action" value="update" />

		<table>

			<tr>
				<td>Обновление студента</td>
			</tr>
			<tr>
				<td>id:</td>
				<td><input type="text" name="id"></td>
			</tr>

			<tr>
				<td>name:</td>
				<td><input type="text" name="name"></td>
			</tr>

			<tr>
				<td>surname:</td>
				<td><input type="text" name="surname"></td>
			</tr>

			<tr>
				<td>groupId:</td>
				<td><input type="text" name="groupId"></td>
			</tr>

			<tr>

				<td><input type="submit" value=" submit "></td>
			</tr>

		</table>
	</form>
	
	</br>
	</br>
	</br>
	<form method="POST" action="StudentServlet" name="formStudentDelete">
		<input type="hidden" name="action" value="delete" />
		<table>
			<tr>
				<td>Удаление студента по</td>
				<td>id:</td>
				<td><input type="text" name="id"></td>
			</tr>
			<tr>
				<td><input type="submit" value=" submit "></td>
			</tr>
		</table>
	</form>
	
	
	
	<p>	<a href="/lesson11/">На Главную страницу</a></p>
	

</body>
</html>