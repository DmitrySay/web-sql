package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDao;
import domain.Student;
import sql.SqlStudentDao;

@WebServlet("/students/view")
public class StudentsViewServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();

		try {

			StudentDao studentDao = new SqlStudentDao();

			out.println("<B>Список студентов</B>");
			out.println("<br>");
			out.println("<B> Id , Имя , Фамилия , Дата поступления, Группа,  MarkId </B>");
			out.println("<table border=1>");

			List<Student> l = studentDao.selectAllStudents();

			for (Student gr : l) {
				out.println("<tr>");
				out.println("<td>" + gr.getId() + "</td>");
				out.println("<td>" + gr.getName() + "</td>");
				out.println("<td>" + gr.getSurname() + "</td>");
				out.println("<td>" + gr.getEnrolmentDate() + "</td>");
				out.println("<td>" + gr.getGroupId() + "</td>");
				out.println("<td>" + gr.getMarkId() + "</td>");
				out.println("</tr>");

			}

			studentDao.close();
		} catch (Exception e) {
			out.println("Ошибка выполнения webstudentDao");
		}
		out.println("</table>");

		out.println("<p><a href=\"/lesson11\">На Главную страницу</a></p>");
		out.println("<p><a href=\"/lesson11/StudentCreate.jsp\">Добавить студента</a></p>");
		out.println("<p><a href=\"/lesson11/StudentUpdate.jsp\">Обновить студента</a></p>");
		out.println("<p><a href=\"/lesson11/StudentDelete.jsp\">Удалить студента</a></p>");
		
		
		out.close();

	}

}