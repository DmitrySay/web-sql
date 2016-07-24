package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.StudentDao;
import sql.SqlStudentDao;

@WebServlet("/StudentCreate")
public class StudentCreateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private String surname;
	private int groupId;
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		id = Integer.valueOf(request.getParameter("id"));
		name = request.getParameter("name");
		surname = request.getParameter("surname");
		groupId = Integer.valueOf(request.getParameter("groupId"));

		try {

			StudentDao studentDao = new SqlStudentDao();
			studentDao.insertStudent(id, name, surname, groupId);
			studentDao.close();
			response.sendRedirect("/lesson11/students/view");
		} catch (Exception e) {
			System.out.println("Ошибка выполнения webCreateStudent");
		}

	}
}
