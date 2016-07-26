<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="dao.GroupDao"%>
<%@ page import="sql.SqlGroupDao"%>
<%@ page import="domain.Group"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Список групп</title>
</head>
<body>


	<table>
		<tr>
			<td>id</td>
			<td>number</td>
			<td>department</td>
		</tr>

	
	
	<tr>
	
	<%
	
	GroupDao groupDao = new SqlGroupDao();
	List<Group> l =groupDao.selectAllGroups();
    
	for (Group gr : l) {
		
		%>  
    
    <td><%= gr.getId()%></td>
    <td><%= gr.getNumber()%></td>
    <td><%= gr.getDepartment()%></td>
   
   </tr>
   
   <%
    }
    groupDao.close(); 
	%>
	
	
</table>	

<p><a href="/lesson11">На Главную страницу</a></p>


</body>
</html>

