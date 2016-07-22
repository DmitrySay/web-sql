package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDao;
import sql.SqlStudentDao;

@WebServlet("/deletestudent")
public class DeleteStudentServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private int id;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		id = Integer.valueOf(request.getParameter("id"));
		try {

			StudentDao studentDao = new SqlStudentDao();
			studentDao.deleteStudent(id);
			studentDao.close();
			response.sendRedirect("/lesson11/students/view");
		} catch (Exception e) {
			System.out.println("Ошибка выполнения webDeleteStudent");
		}
		
	}

}
