<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>




<form method="POST" action="StudentServlet" name="formStudentInsert">
		<table>

			<tr>
				<td>Добавление студента:</td>
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

<p><a href="/lesson11/">На Главную страницу</a></p>
<p><a href="StudentServlet?action=listUser">Посмотрите всех студентов!</a></p>




</body>
</html>